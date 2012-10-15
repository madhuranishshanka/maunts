package com.devspace.order.fulfilment.service;

import com.devspace.order.fulfilment.domain.UserOrder;
import com.devspace.order.fulfilment.exception.UserOrderNotFoundException;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface UserOrderService {

    UserOrder createUserOrder(UserOrder userOrder);

    UserOrder getUserOrderById(long userOrderId) throws UserOrderNotFoundException;

}
