package com.example.erste_bank.service;


import com.example.erste_bank.dto.PCCRequestDTO;
import com.example.erste_bank.dto.ResponseDTO;
import com.example.erste_bank.model.Customer;
import com.example.erste_bank.model.Payment;
import com.example.erste_bank.repository.CustomerRepository;
import com.example.erste_bank.repository.PaymentRepository;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PaymentRepository paymentRepository;
    
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public ResponseDTO pay (PCCRequestDTO pccRequestDTO) throws ParseException {

        Customer payeer = customerRepository.findByPAN(pccRequestDTO.getPan());
        Payment payment = this.createPaymet(pccRequestDTO, payeer);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setAcquirerOrderId(pccRequestDTO.getAcquirerOrderId());
        responseDTO.setAcquirerTimestamp(pccRequestDTO.getAcquirerTimestamp());
        responseDTO.setIssuerTimestamp(payment.getTimestamp());
        responseDTO.setIssuerOrderId(payment.getId());

        if (payeer == null || validation(pccRequestDTO, payeer) == false ){
            responseDTO.setStatus("ERROR");
            payment.setStatus("ERROR");
            paymentRepository.save(payment);
            logger.info("[CARD] payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
            return responseDTO;

        } else {

            if (payeer.getAvailableFunds() >= payment.getAmount()) {

                payeer.setAvailableFunds(payeer.getAvailableFunds() - payment.getAmount());
                customerRepository.save(payeer);
                responseDTO.setStatus("SUSCCESSFUL");
                payment.setStatus("SUSCCESSFUL");
                paymentRepository.save(payment);
                logger.info("[CARD] payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
                return responseDTO;

            } else {
                responseDTO.setStatus("FAILED");
                payment.setStatus("FAILED");
                paymentRepository.save(payment);
                logger.info("[CARD] payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
                return responseDTO;
            }

        }

    }


    public boolean validation(PCCRequestDTO pccRequestDTO, Customer payeer) throws ParseException {

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date expirationDate = formatter.parse(payeer.getExpirationDate());
        if (today.after(expirationDate)){
            return false;
        }
        if (!pccRequestDTO.getExpirationDate().equals(payeer.getExpirationDate())) {
            return false;
        }
        if (!pccRequestDTO.getCardHolderName().equals(payeer.getCardHolderName())){
            return false;
        }
        if (!pccRequestDTO.getSecurityCode().equals(payeer.getCreditCardSecurityNumber())){
            return false;
        }
        return  true;
    }

    public Payment createPaymet(PCCRequestDTO pccRequestDTO, Customer payeer){
        Payment payment = new Payment();
        payment.setPayeer(payeer);
        payment.setAmount(pccRequestDTO.getAmount());
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        payment.setTimestamp(timeStamp);
        payment.setStatus("CREATED");
        paymentRepository.save(payment);
        logger.info("[CARD] Order with price: " + payment.getAmount()
		+ " created in CARD service and saved with status " + payment.getStatus());

        return payment;
    }


}
