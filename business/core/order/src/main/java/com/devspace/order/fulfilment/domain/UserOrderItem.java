package com.devspace.order.fulfilment.domain;

import com.devspace.order.request.domain.OrderItem;
import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class UserOrderItem extends Entity{

    private OrderItem orderItem;
    private UserProductAggregateId userProductAggregateId;

    public UserOrderItem(OrderItem orderItem, UserProductAggregateId userProductAggregateId) {
        this.orderItem = orderItem;
        this.userProductAggregateId = userProductAggregateId;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public UserProductAggregateId getUserProductAggregateId() {
        return userProductAggregateId;
    }
}
