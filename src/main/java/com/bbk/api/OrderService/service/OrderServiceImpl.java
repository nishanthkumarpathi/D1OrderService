package com.bbk.api.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbk.api.OrderService.entity.Order;
import com.bbk.api.OrderService.external.client.ProductService;
import com.bbk.api.OrderService.model.OrderRequest;
import com.bbk.api.OrderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductService productService;

	@Override
	public long placeOrder(OrderRequest orderRequest) {

		log.info("Placing the Order Request, its time to reduce the product and write a logic");

		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
		log.info("Now Value from the Productdb is decreased and lets build the Order and save it");

		Order order = Order.builder()
				.amount(orderRequest.getTotalAmount()).orderStatus("CREATED").productId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity())
				.orderDate(Instant.now()).build();

		order = orderRepository.save(order);

		return order.getId();
	}

}