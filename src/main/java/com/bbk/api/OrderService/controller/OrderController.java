package com.bbk.api.OrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbk.api.OrderService.model.OrderRequest;
import com.bbk.api.OrderService.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {

		long orderId = orderService.placeOrder(orderRequest);
		
		log.info("Order Placed with OrderId: {}", orderId);

		return new ResponseEntity<>(orderId, HttpStatus.OK);

	}

}

