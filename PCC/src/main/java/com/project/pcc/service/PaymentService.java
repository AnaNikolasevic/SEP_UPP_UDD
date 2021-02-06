package com.project.pcc.service;

import com.project.pcc.dto.PCCRequestDTO;
import com.project.pcc.dto.ResponseDTO;
import com.project.pcc.model.Bank;
import com.project.pcc.model.Payment;
import com.project.pcc.repository.BankRepository;
import com.project.pcc.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BankRepository bankRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public ResponseDTO createPayment(PCCRequestDTO pccRequestDTO){

        String panID = pccRequestDTO.getPan().substring(0,3);
        Bank bank = bankRepository.findByPanID(panID);
        if (bank == null){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setAcquirerOrderId(pccRequestDTO.getAcquirerOrderId());
            responseDTO.setAcquirerTimestamp(pccRequestDTO.getAcquirerTimestamp());
            responseDTO.setStatus("ERROR");
        }

        Payment payment = new Payment();
        payment.setIssuerBank(bank.getName());
        payment.setAcquirerOrderId(pccRequestDTO.getAcquirerOrderId());
        payment.setAcquirerTimestamp(pccRequestDTO.getAcquirerTimestamp());
        payment.setAcquirerBank(pccRequestDTO.getBankName());



        ResponseEntity<ResponseDTO> response = restTemplate.postForEntity(bank.getUrl(), pccRequestDTO, ResponseDTO.class);
        payment.setStatus(response.getBody().getStatus());
        payment.setIssuerOrderId(response.getBody().getIssuerOrderId());
        payment.setIssuerTimestamp(response.getBody().getIssuerTimestamp());
        paymentRepository.save(payment);

        return response.getBody();
    }


}
