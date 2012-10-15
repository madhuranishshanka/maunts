package com.devspace.order.fulfilment.repository.impl;

import com.devspace.order.commons.repository.impl.BaseRepositoryImpl;
import com.devspace.order.fulfilment.domain.UserOrder;
import com.devspace.order.fulfilment.repository.UserOrderRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("userOrderRepository")
public class UserOrderRepositoryImpl extends BaseRepositoryImpl<UserOrder> implements UserOrderRepository {

    @Override
    public Class<UserOrder> getClassType() {
        return UserOrder.class;
    }
}
