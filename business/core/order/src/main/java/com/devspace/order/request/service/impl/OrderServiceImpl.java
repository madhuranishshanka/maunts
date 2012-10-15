package com.devspace.order.request.service.impl;

import com.devspace.order.request.domain.Order;
import com.devspace.order.request.exception.OrderNotFoundException;
import com.devspace.order.request.repository.OrderRepository;
import com.devspace.order.request.service.OrderService;
import com.devspace.persistence.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("orderRequestService")
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;

    public Order persistOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    public Order getOrderById(long orderRequestId) throws OrderNotFoundException {
        try {
            return orderRepository.findById(orderRequestId);
        } catch (EntityNotFoundException e) {
            throw new OrderNotFoundException("Order not found for the id "+orderRequestId);
        }
    }
}
