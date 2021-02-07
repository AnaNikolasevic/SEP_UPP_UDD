package com.project.paypal.service;

import com.paypal.api.payments.*;
import com.paypal.api.payments.Currency;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.project.paypal.dto.SubscriptionRequestDTO;
import com.project.paypal.dto.SubscriptionResponseDTO;
import com.project.paypal.model.Seller;
import com.project.paypal.model.Subscription;
import com.project.paypal.repository.SellerRepository;
import com.project.paypal.repository.SubscriptionRepository;
import com.project.paypal.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SubscriptionService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${paypal.mode}")
    private String mode;

    private static String KP_URL = "https://localhost:8672/paypal";

    public SubscriptionResponseDTO createSubscription(SubscriptionRequestDTO subscriptionRequestDTO){

        SubscriptionResponseDTO responseDTO = new SubscriptionResponseDTO();

        Seller seller = sellerRepository.getOne(subscriptionRequestDTO.getSellerId());
        Subscription subscription = new Subscription();
        subscription.setSellerId(seller.getId());
        subscription.setSuccessUrl(subscriptionRequestDTO.getSuccessUrl());
        subscription.setFailureUrl(subscriptionRequestDTO.getFailureUrl());
        subscription.setName(subscriptionRequestDTO.getName());
        subscription.setDescription(subscriptionRequestDTO.getDescription());
        subscriptionRepository.save(subscription);

        Plan plan = null;

        try {
            plan = createBillingPlan(subscriptionRequestDTO, seller);
        } catch (PayPalRESTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Agreement agreement = new Agreement();

        agreement.setName(subscriptionRequestDTO.getName());
        agreement.setDescription(subscriptionRequestDTO.getDescription());


        Date date = new Date(System.currentTimeMillis() + 86400000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));

        agreement.setStartDate(sdf.format(date));

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        agreement.setPayer(payer);

        Plan newPlan = new Plan();
        newPlan.setId(plan.getId());
        agreement.setPlan(newPlan);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8083/cancelSubscription");
        redirectUrls.setReturnUrl("http://localhost:8083/confirmSubscription");

        MerchantPreferences merchantPreferences = new MerchantPreferences();
        merchantPreferences.setCancelUrl("http://localhost:8083/cancelSubscription");
        merchantPreferences.setReturnUrl("http://localhost:8083/confirmSubscription");
        agreement.setOverrideMerchantPreferences(merchantPreferences);


        try {
            agreement = agreement.create(getApiContext(seller.getPaypalClientId(), seller.getPaypalSecret()));
        } catch (MalformedURLException | UnsupportedEncodingException | PayPalRESTException e) {
            // TODO Auto-generated catch block
            responseDTO.setPaymentUrl("error");
            e.printStackTrace();
        }


        subscription.setPlanId(plan.getId());
        subscription.setAggrementToken(agreement.getToken());
        subscription.setAggrementId(agreement.getId());
        subscription.setStatus("created");
        subscriptionRepository.save(subscription);

        for (Links links : agreement.getLinks()) {
            if ("approval_url".equals(links.getRel())) {
                responseDTO.setPaymentUrl(links.getHref());
            }
        }

        responseDTO.setCancelUrl(KP_URL+"/cancelSubscription/"+agreement.getToken());
        responseDTO.setSubscriptionId(subscription.getId());
        responseDTO.setToken(subscription.getAggrementToken());
        return responseDTO;
    }

    private Plan createBillingPlan(SubscriptionRequestDTO subscriptionRequestDTO, Seller seller) throws PayPalRESTException {
        // Build Plan object
        Plan plan = new Plan();
        plan.setName(subscriptionRequestDTO.getName());
        plan.setDescription(subscriptionRequestDTO.getDescription());
        plan.setType(subscriptionRequestDTO.getType());

        //payment_definitions
        PaymentDefinition paymentDefinition = new PaymentDefinition();
        paymentDefinition.setName("Regular Payments");
        paymentDefinition.setType("REGULAR");
        paymentDefinition.setFrequency(subscriptionRequestDTO.getFrequency());
        paymentDefinition.setFrequencyInterval(subscriptionRequestDTO.getFrequencyIntrval());
        paymentDefinition.setCycles(subscriptionRequestDTO.getCycles());


        //currency
        Currency currency = new Currency();
        currency.setCurrency(subscriptionRequestDTO.getCurrency());
        currency.setValue(subscriptionRequestDTO.getAmount());
        paymentDefinition.setAmount(currency);

        //charge_models
        ChargeModels chargeModels = new com.paypal.api.payments.ChargeModels();
        chargeModels.setType("SHIPPING");
        chargeModels.setAmount(currency);
        List<ChargeModels> chargeModelsList = new ArrayList<ChargeModels>();
        chargeModelsList.add(chargeModels);
        paymentDefinition.setChargeModels(chargeModelsList);


        //payment_definition
        List<PaymentDefinition> paymentDefinitionList = new ArrayList<PaymentDefinition>();
        paymentDefinitionList.add(paymentDefinition);
        plan.setPaymentDefinitions(paymentDefinitionList);

        //merchant_preferences
        MerchantPreferences merchantPreferences = new MerchantPreferences();
        if(subscriptionRequestDTO.getFrequency().equals("MONTH")){
            merchantPreferences.setSetupFee(currency);
            System.out.println(merchantPreferences.getSetupFee() + "---------------------");
        }

        merchantPreferences.setCancelUrl("http://localhost:8083/cancelSubscription");
        merchantPreferences.setReturnUrl("http://localhost:8083/confirmSubscription");
        //merchantPreferences.setMaxFailAttempts("0");
        //merchantPreferences.setAutoBillAmount("YES");
        //merchantPreferences.setInitialFailAmountAction("CONTINUE");*/
        plan.setMerchantPreferences(merchantPreferences);

        APIContext context = getApiContext(seller.getPaypalClientId(), seller.getPaypalSecret());
        plan = plan.create(context);


        List<Patch> patchRequestList = new ArrayList<Patch>();
        Map<String, String> value = new HashMap<String, String>();
        value.put("state", "ACTIVE");

        Patch patch = new Patch();
        patch.setPath("/");
        patch.setValue(value);
        patch.setOp("replace");
        patchRequestList.add(patch);

        plan.update(context, patchRequestList);
        return plan;
    }

    public String executeSubscription(String token, long sellerId) {

        Seller seller = sellerRepository.findOneById(sellerId);
        Agreement agreement = new Agreement();
        agreement.setToken(token);
        System.out.println("Milica++++++++++" + token);

        try {
            agreement = Agreement.execute(getApiContext(seller.getPaypalClientId(), seller.getPaypalSecret()), token);
        } catch (PayPalRESTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
        Subscription subscription = subscriptionRepository.findOneByAggrementToken(token);
        subscription.setStatus("active");
        subscription.setAggrementId(agreement.getId());
        subscriptionRepository.save(subscription);
        RestTemplate rt = new RestTemplate();
        //rt.postForLocation("http://localhost:8083/paypalSuccess/?orderId=1/"+subscription.getId(), null);
        return "success";
    }

    private APIContext getApiContext(String clientId, String clientSecret) throws PayPalRESTException {

        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);

        APIContext context = new APIContext(new OAuthTokenCredential(clientId, clientSecret, configMap).getAccessToken());
        context.setConfigurationMap(configMap);
        return context;
    }



}