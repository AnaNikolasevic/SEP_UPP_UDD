package com.project.aik_bank.service;

import com.google.common.hash.Hashing;
import com.project.aik_bank.dto.KpResponseDTO;
import com.project.aik_bank.dto.PayeerFormDTO;
import com.project.aik_bank.dto.PaymentRequestDTO;
import com.project.aik_bank.dto.ResponseDTO;
import com.project.aik_bank.model.Customer;
import com.project.aik_bank.model.PCCRequest;
import com.project.aik_bank.model.Payment;
import com.project.aik_bank.repository.CustomerRepository;
import com.project.aik_bank.repository.PCCRequestRepository;
import com.project.aik_bank.repository.PaymentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.ValidationException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    private static String PCC_URL = "http://localhost:8092";
    private static String KP_URL = "http://localhost:8082";


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PCCRequestRepository pccRequestRepository;

    Logger logger = LoggerFactory.getLogger(PaymentService.class);

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
        logger.info("[CARD] Order with price: " + paymentRequestDTO.getPrice()+paymentRequestDTO.getCurrency()
		+ " bought at Literary association " + paymentRequestDTO.getSellerId()+ ","
		+ " created in CARD service and saved with status " + payment.getStatus());
        return payment;
    }

    public String pay (PayeerFormDTO payeerFormDTO) throws ParseException {

        String hashPAN = Hashing.sha256()
                .hashString(payeerFormDTO.getPan(), StandardCharsets.UTF_8)
                .toString();
        Customer payeer = customerRepository.findByPAN(hashPAN);
        Payment payment = paymentRepository.findById(payeerFormDTO.getPaymentId()).orElse(null);

        // ukoliko se kupac ne nadje kao korsnik u ovoj banci, zahtev se prosledjuje na PCC
        if (payeer == null){
    		logger.info("Sending request to PCC . . .");

            //slajne zahteva na pcc
            PCCRequest pccRequest = this.createPCCRequest(payeerFormDTO, payment);
            ResponseEntity<ResponseDTO> response = restTemplate.postForEntity(PCC_URL + "/createPayment", pccRequest, ResponseDTO.class);


            if (response.getBody().getStatus().equals("SUSCCESSFUL")){
                Customer payee = payment.getPayee();
                payee.setAvailableFunds(payee.getAvailableFunds() + payment.getAmount());
                customerRepository.save(payee);
                payment.setStatus("SUSCCESSFUL");
                paymentRepository.save(payment);
                logger.info("[CARD] payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
                //slanje podataka na kp
                KpResponseDTO kpResponseDTO = this.crateKpResponse(response.getBody(), payment);
                restTemplate.put(KP_URL + "/orderRequest/edit", kpResponseDTO);
                return payment.getSuccess_url();

            } else if (response.getBody().getStatus().equals("FAILED")){
                payment.setStatus("FAILED");
                paymentRepository.save(payment);
                logger.info("[CARD] payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
                KpResponseDTO kpResponseDTO = this.crateKpResponse(response.getBody(), payment);
                restTemplate.put(KP_URL + "/orderRequest/edit", kpResponseDTO);
               return payment.getFailed_url();
               
            } else {

                payment.setStatus("ERROR");
                paymentRepository.save(payment);
                logger.info("[CARD] payment request with id: " + payment.getId() + "updated status to: " + payment.getStatus());
                KpResponseDTO kpResponseDTO = this.crateKpResponse(response.getBody(), payment);
                restTemplate.put(KP_URL + "/orderRequest/edit", kpResponseDTO);
                return payment.getError_url();

            }
        } else {

            if (validation(payeerFormDTO, payeer) == true) {
                payment.setPayeer(payeer);

                if (payeer.getAvailableFunds() >= payment.getAmount()) {

                    payeer.setAvailableFunds(payeer.getAvailableFunds() - payment.getAmount());
                    customerRepository.save(payeer);
                    Customer payee = payment.getPayee();
                    payee.setAvailableFunds(payee.getAvailableFunds() + payment.getAmount());
                    customerRepository.save(payee);
                    payment.setStatus("SUSCCESSFUL");
                    paymentRepository.save(payment);
                    logger.info("[CARD] payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
                    KpResponseDTO kpResponseDTO =  this.crateKpResponse(payment);
                    restTemplate.put(KP_URL + "/orderRequest/edit", kpResponseDTO);
                    return payment.getSuccess_url();
                } else {
                    payment.setStatus("FAILED");
                    paymentRepository.save(payment);
                    logger.info("[CARD] payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
                    KpResponseDTO kpResponseDTO =  this.crateKpResponse(payment);
                    restTemplate.put(KP_URL + "/orderRequest/edit", kpResponseDTO);
                    return payment.getFailed_url();
                }


            } else {
                payment.setStatus("ERROR");
                paymentRepository.save(payment);
                KpResponseDTO kpResponseDTO =  this.crateKpResponse(payment);
                logger.info("Card payment request with id: " + payment.getId() + " updated status to: " + payment.getStatus());
                restTemplate.put(KP_URL + "/orderRequest/edit", kpResponseDTO);
                return payment.getError_url();
            }
        }

    }
    public PCCRequest createPCCRequest(PayeerFormDTO payeerFormDTO, Payment payment){

        PCCRequest pccRequest = new PCCRequest();
        pccRequest.setAcquirerOrderId(payment.getId());
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        pccRequest.setAcquirerTimestamp(timeStamp);
        pccRequest.setAmount(payment.getAmount());
        pccRequest.setCardHolderName(payeerFormDTO.getCardHolderName());
        pccRequest.setExpirationDate(payeerFormDTO.getExpirationDate());
        String hashPAN = Hashing.sha256()
                .hashString(payeerFormDTO.getPan(), StandardCharsets.UTF_8)
                .toString();
        pccRequest.setPan(hashPAN);
        String hashSC = Hashing.sha256()
                .hashString(Long.toString(payeerFormDTO.getSecurityCode()), StandardCharsets.UTF_8)
                .toString();
        pccRequest.setSecurityCode(hashSC);
        pccRequest.setBankName("AikBank");
        pccRequest.setPanId(payeerFormDTO.getPan().substring(0,3));
        pccRequestRepository.save(pccRequest);
        logger.info("PCC request created.");
        return pccRequest;

    }
    public KpResponseDTO crateKpResponse(ResponseDTO responseDTO, Payment payment){
        KpResponseDTO kpResponseDTO = new KpResponseDTO();
        kpResponseDTO.setMerchantOrderId(payment.getMerchant_order_id());
        kpResponseDTO.setPaymentId(payment.getId());
        kpResponseDTO.setAcquirerOrderId(responseDTO.getAcquirerOrderId());
        kpResponseDTO.setAcquirerTimestamp(responseDTO.getAcquirerTimestamp());
        kpResponseDTO.setStatus(payment.getStatus());
        return kpResponseDTO;

    }
    public KpResponseDTO crateKpResponse(Payment payment){
        KpResponseDTO kpResponseDTO = new KpResponseDTO();
        kpResponseDTO.setMerchantOrderId(payment.getMerchant_order_id());
        kpResponseDTO.setPaymentId(payment.getId());
        kpResponseDTO.setStatus(payment.getStatus());
        return kpResponseDTO;

    }

    public boolean validation(PayeerFormDTO payeerFormDTO, Customer payeer) throws ParseException {

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date expirationDate = formatter.parse(payeer.getExpirationDate());
        if (today.after(expirationDate)){
            return false;
        }
        if (!payeerFormDTO.getExpirationDate().equals(payeer.getExpirationDate())) {
            return false;
        }
        if (!payeerFormDTO.getCardHolderName().equals(payeer.getCardHolderName())){
            return false;
        }
        String hashSC = Hashing.sha256()
                .hashString(Long.toString(payeerFormDTO.getSecurityCode()), StandardCharsets.UTF_8)
                .toString();
        if (!hashSC.equals(payeer.getCreditCardSecurityNumber())){
            return false;
        }
        return  true;
    }



}
