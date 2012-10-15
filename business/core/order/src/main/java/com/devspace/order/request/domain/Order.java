package com.devspace.order.request.domain;

import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.order.request.exception.InvalidOrderItemException;
import com.devspace.persistence.domain.Entity;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Order extends Entity {

    private Set<OrderItem> orderItems = new HashSet<OrderItem>();
    private String userExternalId;
    private String paymentInstrumentId;

    public Order() {
    }

    public Order(Set<OrderItem> orderItems, String userExternalId) throws
            MissingMandatoryParamException, InvalidOrderItemException {
        validateOrder(userExternalId, orderItems, null);
        this.orderItems = orderItems;
        this.userExternalId = userExternalId;
    }

    public Order(Set<OrderItem> orderItems, String userExternalId,
                 String paymentInstrumentId) throws MissingMandatoryParamException, InvalidOrderItemException {
        validateOrder(userExternalId, orderItems, paymentInstrumentId);
        this.orderItems = orderItems;
        this.userExternalId = userExternalId;
        this.paymentInstrumentId = paymentInstrumentId;
    }

    private void validateOrder(String userExternalId, Set<OrderItem> orderItems,
                               String paymentInstrumentId) throws MissingMandatoryParamException,
            InvalidOrderItemException {
        if (userExternalId == null || StringUtils.isEmpty(this.userExternalId)) {
            throw new MissingMandatoryParamException("Missing user external id");
        }

        if (orderItems.isEmpty()) {
            throw new MissingMandatoryParamException("Missing order items");
        }

        if (paymentInstrumentId == null || StringUtils.isEmpty(this.paymentInstrumentId)) {

            for (OrderItem orderItem : orderItems) {
                String childPaymentInstrumentXId = orderItem.getPaymentInstrumentExternalId();
                if (childPaymentInstrumentXId == null || StringUtils.isEmpty("childPaymentInstrumentXId")) {
                    throw new InvalidOrderItemException("All the child product should have payment instruments");
                }
            }
        }
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getUserExternalId() {
        return userExternalId;
    }

    public String getPaymentInstrumentId() {
        return paymentInstrumentId;
    }
}
