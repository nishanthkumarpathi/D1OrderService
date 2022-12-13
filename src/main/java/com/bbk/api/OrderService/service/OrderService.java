package com.bbk.api.OrderService.service;

import com.bbk.api.OrderService.model.OrderRequest;

public interface OrderService {

	long placeOrder(OrderRequest orderRequest);

}
