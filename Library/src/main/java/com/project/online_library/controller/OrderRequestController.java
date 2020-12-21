package com.project.online_library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.online_library.dto.OrderRequestDto;
import com.project.online_library.model.OrderRequest;
import com.project.online_library.service.OrderRequestService;
@CrossOrigin
@RestController
@RequestMapping("/orderRequest")
public class OrderRequestController {
	@Autowired
	OrderRequestService service;
	
	@PostMapping("")
    public ResponseEntity<OrderRequest> add(@RequestBody OrderRequestDto orderRequestDto)  {
		try {
			service.add(orderRequestDto);
	        return new ResponseEntity<OrderRequest>(service.add(orderRequestDto), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<OrderRequest>(null);
		}
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getOrderRequest(@PathVariable int id) {
        return new ResponseEntity<OrderRequest>(service.getOrderRequest(Long.valueOf(id)), HttpStatus.OK);
    }
}
