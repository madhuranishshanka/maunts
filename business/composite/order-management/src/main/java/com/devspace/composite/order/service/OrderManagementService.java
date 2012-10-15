package com.devspace.composite.order.service;

import com.devspace.order.request.domain.Order;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface OrderManagementService {

    String placeOrder(Order order);
}
