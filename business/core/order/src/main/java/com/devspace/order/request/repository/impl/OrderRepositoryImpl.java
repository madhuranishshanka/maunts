package com.devspace.order.request.repository.impl;

import com.devspace.order.commons.repository.impl.BaseRepositoryImpl;
import com.devspace.order.request.domain.Order;
import com.devspace.order.request.repository.OrderRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("orderRepository")
public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {

    @Override
    public Class<Order> getClassType() {
        return Order.class;
    }
}
