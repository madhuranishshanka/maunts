package com.devspace.order.request.service;

import com.devspace.order.request.domain.Order;
import com.devspace.order.request.exception.OrderNotFoundException;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface OrderService {

    Order persistOrder(Order order);

    Order getOrderById(long orderRequestId) throws OrderNotFoundException;
}
