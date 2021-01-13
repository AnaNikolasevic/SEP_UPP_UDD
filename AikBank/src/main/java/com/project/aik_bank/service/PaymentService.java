package com.project.aik_bank.service;

import com.project.aik_bank.dto.PayeerFormDTO;
import com.project.aik_bank.dto.PaymentRequestDTO;
import com.project.aik_bank.model.Customer;
import com.project.aik_bank.model.Payment;
import com.project.aik_bank.repository.CustomerRepository;
import com.project.aik_bank.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public Payment create(PaymentRequestDTO paymentRequestDTO) throws ValidationException {

        Customer customer = customerRepository.findByMerchantId(paymentRequestDTO.getMerchant_id());
        //provera da li postoji korsnik banke sa poslatim idem
        if (customer == null){
            throw new NoSuchElementException("there is no customer with this id");
        }
        //provera lozinke
        if (!customer.getPassword().equals(paymentRequestDTO.getMerchant_password())){
            throw new ValidationException("incorect password");
        }
        Payment payment = new Payment();
        payment.setPayee(customer);
        payment.setMerchant_order_id(paymentRequestDTO.getId());
        payment.setMerchant_timestamp(paymentRequestDTO.getMerchant_timestamp());
        payment.setAmount(paymentRequestDTO.getPrice());
        payment.setError_url(paymentRequestDTO.getError_url());
        payment.setSuccess_url(paymentRequestDTO.getSuccess_url());
        payment.setFailed_url(paymentRequestDTO.getFailed_url());
        payment.setStatus("CREATED");
        paymentRepository.save(payment);
        return payment;
    }
    public String pay (PayeerFormDTO payeerFormDTO) throws ParseException {

        Customer payeer = customerRepository.findByPAN(payeerFormDTO.getPan());
        Payment payment = paymentRepository.findById(payeerFormDTO.getPaymentId()).orElse(null);

        // trenutno je grska jer ima samo jedan banka, bice uslov kod implentacije dve banke
        if (payeer == null){
            payment.setStatus("ERROR");
            paymentRepository.save(payment);
            return "http://localhost:8083/aikBank/error/" + payment.getMerchant_order_id();
        }
        if (validation(payeerFormDTO, payeer) == false){
            payment.setPayeer(payeer);

            if (payeer.getAvailableFunds() >= payment.getAmount()){

                payeer.setAvailableFunds(payeer.getAvailableFunds() - payment.getAmount());
                customerRepository.save(payeer);
                Customer payee = payment.getPayee();
                payee.setAvailableFunds(payee.getAvailableFunds() + payment.getAmount());
                customerRepository.save(payee);
                payment.setStatus("SUSCCESSFUL");
                paymentRepository.save(payment);
                return "http://localhost:8083/aikBank/success/" + payment.getMerchant_order_id();

            } else {
                payment.setStatus("FAILED");
                paymentRepository.save(payment);
                return "http://localhost:8083/aikBank/failed/" + payment.getMerchant_order_id();
            }


        } else {
            payment.setStatus("ERROR");
            paymentRepository.save(payment);
            return "http://localhost:8083/aikBank/error/" + payment.getMerchant_order_id();
        }

    }

    public boolean validation(PayeerFormDTO payeerFormDTO, Customer payeer) throws ParseException {

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date expirationDate = formatter.parse(payeer.getExpirationDate());
        if (today.before(expirationDate)){
            return false;
        }
        if (!payeerFormDTO.getExpirationDate().equals(payeer.getExpirationDate())) {
            return false;
        }
        if (payeerFormDTO.getCardHolderName().equals(payeer.getCardHolderName())){
            return false;
        }
        if (payeerFormDTO.getSecurityCode() != payeer.getCreditCardSecurityNumber()){
            return false;
        }
        return  true;
    }

}
