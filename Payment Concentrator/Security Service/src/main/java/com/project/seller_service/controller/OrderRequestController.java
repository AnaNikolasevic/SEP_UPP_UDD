package com.project.seller_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.seller_service.dto.OrderRequestDto;
import com.project.seller_service.model.OrderRequest;
import com.project.seller_service.service.OrderRequestService;
@CrossOrigin
@RestController
@RequestMapping("/orderRequest")
public class OrderRequestController {
	@Autowired
	OrderRequestService service;
	
	@PostMapping("")
	public ResponseEntity<OrderRequest> add(@RequestBody OrderRequestDto orderRequestDto)  {
		try {
			return new ResponseEntity<OrderRequest>(service.add(orderRequestDto), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<OrderRequest>(null);
		}
	}

	@PutMapping("/{id}/{paymentType}")
    public ResponseEntity<?> getOrderRequest(@PathVariable int id, @PathVariable String paymentType) {
        return new ResponseEntity<OrderRequest>(service.getOrderRequest(Long.valueOf(id), paymentType), HttpStatus.OK);
    }

	@PutMapping("/edit/{id}/{status}")
	public ResponseEntity<?> editStatus(@PathVariable Long id, @PathVariable String status) {

		service.setStatus(id, status);
		return new ResponseEntity<OrderRequest>(HttpStatus.OK);
	}
}
