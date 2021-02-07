package com.project.seller_service.controller;

import com.project.seller_service.dto.KpResponseDTO;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.seller_service.dto.OrderRequestDto;
import com.project.seller_service.model.OrderRequest;
import com.project.seller_service.service.OrderRequestService;
//import sun.security.ec.CurveDB;

@CrossOrigin
@RestController
@RequestMapping("/orderRequest")
public class OrderRequestController {

	@Autowired
	OrderRequestService orderRequestservice;
	
	@PostMapping("")
	public ResponseEntity<OrderRequest> add(@RequestBody OrderRequestDto orderRequestDto)  {
		try {
	        return new ResponseEntity<OrderRequest>(orderRequestservice.add(orderRequestDto), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<OrderRequest>(null);
		}
	}

	@PutMapping("/{id}/{paymentType}")
    public ResponseEntity<?> getOrderRequest(@PathVariable int id, @PathVariable String paymentType) {
        return new ResponseEntity<OrderRequest>(orderRequestservice.getOrderRequest(Long.valueOf(id), paymentType), HttpStatus.OK);
    }

	@PutMapping("/edit/{id}/{status}")
	public ResponseEntity<?> editStatus(@PathVariable Long id, @PathVariable String status) {

		orderRequestservice.setStatus(id, status);
		return new ResponseEntity<OrderRequest>(HttpStatus.OK);

	}

	@PutMapping("/edit")
	public ResponseEntity<?> editOrder(@RequestBody KpResponseDTO kpResponseDTO) {
		orderRequestservice.editOrder(kpResponseDTO);
		return new ResponseEntity<OrderRequest>(HttpStatus.OK);

	}

	/*@GetMapping("/{id}")

    public ResponseEntity<?> getOrderRequest(@PathVariable Long id) {
        return new ResponseEntity<OrderRequest>(orderRequestservice.getOrderRequest(id, HttpStatus.OK);
    } */

	/*@PutMapping("/{orderId}/{status}")
	public ResponseEntity changeOrderRequestStatus(@PathVariable Long orderId, @PathVariable String status) {

		orderRequestservice.changeOrderRequestStatus(orderId, status);
		return new ResponseEntity<> ("Payment status changed", HttpStatus.OK);
*/


}
