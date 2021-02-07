package com.project.paypal.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import com.project.paypal.dto.PaymentRequestDTO;
import com.project.paypal.model.PaymentOrder;
import com.project.paypal.model.PaymentOrderStatus;
import com.project.paypal.model.Seller;
import com.project.paypal.repository.PaymentOrderRepository;
import com.project.paypal.repository.SellerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.invoke.ConstantCallSite;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private SellerRepository sellerRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${paypal.mode}")
    private String mode;

    public Payment createPayment( PaymentRequestDTO pr ) throws PayPalRESTException{

        Seller seller = sellerRepository.findOneById(pr.getSellerId());
        System.out.println(seller.getId() + " " + seller.getEmail());

        PaymentOrder po = new PaymentOrder();
        po.setId(pr.getId());
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
        redirectUrls.setCancelUrl("http://localhost:8083/paypalError/?orderId=" + po.getId());
        redirectUrls.setReturnUrl("http://localhost:8083/paypalSuccess/?orderId=" + po.getId());
        payment.setRedirectUrls(redirectUrls);

        payment = payment.create(getApiContext(seller.getPaypalClientId(), seller.getPaypalSecret()));

        po.setPaymentId(payment.getId());
        paymentOrderRepository.save(po);
        logger.info("[PAYPAL] Order with price: " + po.getPrice()+po.getCurrency()
		+ " bought at Literary association " + po.getSeller().getId()+ ","
		+ " created in PAYPAL service and saved with status " + po.getStatus());
        return payment;
    }

    private APIContext getApiContext(String clientId, String clientSecret) throws PayPalRESTException {

        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);

        APIContext context = new APIContext(new OAuthTokenCredential(clientId, clientSecret, configMap).getAccessToken());
        context.setConfigurationMap(configMap);
        return context;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{

        PaymentOrder po = paymentOrderRepository.findOneByPaymentId(paymentId);

        Seller seller = po.getSeller();

        Payment payment = new Payment();

        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        payment = payment.execute(getApiContext(seller.getPaypalClientId(), seller.getPaypalSecret()), paymentExecute);

        if(payment.getState().equals("approved")) {
            po.setStatus(PaymentOrderStatus.PAID);
            System.out.println("Paypal order paypalId="+ paymentId +" approved");
            logger.info("[PAYPAL] payment request with id: " + po.getId() + "updated status to: " + po.getStatus());

        }else {
            po.setStatus(PaymentOrderStatus.FAILED);
            System.out.println("Paypal order paypalId="+ paymentId +" failed");
            logger.info("[PAYPAL] payment request with id: " + po.getId() + "updated status to: " + po.getStatus());

        }

        paymentOrderRepository.save(po);

        return payment;
    }

    public void cancelPaymentOrder(Long id) {

        PaymentOrder po = paymentOrderRepository.getOne(id);
        po.setStatus(PaymentOrderStatus.CANCELED);
        logger.info("Paypal orderId="+ id +" canceled");
        paymentOrderRepository.save(po);

    }

}
