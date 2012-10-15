package com.devspace.order.fulfilment.domain;

import com.devspace.order.request.domain.Order;
import com.devspace.persistence.domain.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class UserOrder extends Entity {

    private String externalId;
    private List<UserOrderItem> userOrderItems = new ArrayList<UserOrderItem>();
    private Order order;

    public UserOrder(List<UserOrderItem> userOrderItems, Order order) {
        this.userOrderItems = userOrderItems;
        this.order = order;
    }

    public List<UserOrderItem> getUserOrderItems() {
        return userOrderItems;
    }

    public Order getOrder() {
        return order;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
