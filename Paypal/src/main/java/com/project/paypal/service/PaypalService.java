package com.project.paypal.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.project.paypal.dto.PaymentRequestDTO;
import com.project.paypal.model.PaymentOrder;
import com.project.paypal.model.Seller;
import com.project.paypal.repository.PaymentOrderRepository;
import com.project.paypal.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public static final String SUCCESS_URL = "payments/success";
    public static final String CANCEL_URL = "payments/cancel";

    @Value("${paypal.mode}")
    private String mode;

    public Payment createPayment( PaymentRequestDTO pr ) throws PayPalRESTException{

        Seller seller = sellerRepository.findOneById(pr.getSellerId());
        System.out.println(seller.getId() + " " + seller.getEmail());

        PaymentOrder po = new PaymentOrder();
        po.setSeller(seller);
        po.setPrice(pr.getPrice());
        po.setCurrency(pr.getCurrency());
        //po.setSellersPaymentId(tokenUtils.getSellersPaymentId());
        paymentOrderRepository.save(po);

        Amount amount = new Amount();
        amount.setCurrency(pr.getCurrency());
        double total = new BigDecimal(pr.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription("");
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("PAYPAL");

        Payment payment = new Payment();
        payment.setIntent("SALE");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8082/" + CANCEL_URL);
        redirectUrls.setReturnUrl("http://localhost:8082/" + SUCCESS_URL);
        payment.setRedirectUrls(redirectUrls);

        payment = payment.create(getApiContext(seller.getPaypalClientId(), seller.getPaypalSecret()));

        po.setPaymentId(payment.getId());
        paymentOrderRepository.save(po);

        return payment;
    }

    private APIContext getApiContext(String clientId, String clientSecret) throws PayPalRESTException {

        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);

        APIContext context = new APIContext(new OAuthTokenCredential(clientId, clientSecret, configMap).getAccessToken());
        context.setConfigurationMap(configMap);
        return context;
    }

}
