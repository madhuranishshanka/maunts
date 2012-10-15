package com.devspace.order.fulfilment.service.impl;

import com.devspace.order.fulfilment.domain.UserOrder;
import com.devspace.order.fulfilment.exception.UserOrderNotFoundException;
import com.devspace.order.fulfilment.repository.UserOrderRepository;
import com.devspace.order.fulfilment.service.UserOrderService;
import com.devspace.persistence.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("userOrderService")
public class UserOrderServiceImpl implements UserOrderService {

    @Resource(name = "userOrderRepository")
    private UserOrderRepository userOrderRepository;

    public UserOrder createUserOrder(UserOrder userOrder) {
        // TODO
        userOrder.setExternalId("OrderId_"+System.currentTimeMillis());
        userOrderRepository.save(userOrder);
        return userOrder;
    }

    public UserOrder getUserOrderById(long userOrderId) throws UserOrderNotFoundException {
        try {
            return userOrderRepository.findById(userOrderId);
        } catch (EntityNotFoundException e) {
            throw new UserOrderNotFoundException("User order not found for id " + userOrderId);
        }
    }
}
