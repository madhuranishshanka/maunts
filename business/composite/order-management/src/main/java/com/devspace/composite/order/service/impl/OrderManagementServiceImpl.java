package com.devspace.composite.order.service.impl;

import com.devspace.aggregator.merchandise.domain.ProductAggregate;
import com.devspace.aggregator.merchandise.exception.ProductAggregateNotFoundException;
import com.devspace.aggregator.merchandise.service.ProductAggregateService;
import com.devspace.aggregator.userdmerchandise.domain.UserProductAggregate;
import com.devspace.aggregator.userdmerchandise.service.UserProductAggregateService;
import com.devspace.composite.order.service.OrderManagementService;
import com.devspace.merchandise.product.domain.Product;
import com.devspace.merchandise.product.exception.ProductNotFoundException;
import com.devspace.merchandise.product.service.ProductService;
import com.devspace.order.fulfilment.domain.UserOrder;
import com.devspace.order.fulfilment.domain.UserOrderItem;
import com.devspace.order.fulfilment.domain.UserProductAggregateId;
import com.devspace.order.fulfilment.service.UserOrderService;
import com.devspace.order.request.domain.Order;
import com.devspace.order.request.domain.OrderItem;
import com.devspace.order.request.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class OrderManagementServiceImpl implements OrderManagementService {

    private OrderService orderService;
    private UserOrderService userOrderService;
    private ProductAggregateService productAggregateService;
    private UserProductAggregateService userProductAggregateService;
    private ProductService productService;

    public String placeOrder(Order order) {
        long userId = 0;

        orderService.persistOrder(order);

        List<UserOrderItem> userOrderItems = new ArrayList<UserOrderItem>();
        for (OrderItem orderItem : order.getOrderItems()) {

            String productXId = orderItem.getProductExternalId();
            String paymentInstrumentXId = orderItem.getPaymentInstrumentExternalId();
            double quantity = orderItem.getQuantity();
            try {
                Product product = productService.getProductByXId(productXId);
                ProductAggregate productAggregate = productAggregateService.getProductAggregateByProductId(
                        product.getId());
                UserProductAggregate userProductAggregate = userProductAggregateService.createUserProductAggregate
                        (productAggregate.getId(), userId);
                UserOrderItem userOrderItem = new UserOrderItem(
                        orderItem, new UserProductAggregateId(userProductAggregate.getId()));
                userOrderItems.add(userOrderItem);
            } catch (ProductNotFoundException e) {
                // TODO
            } catch (ProductAggregateNotFoundException e) {
                // TODO
            }

        }
        UserOrder userOrder = new UserOrder(userOrderItems, order);
        userOrder = userOrderService.createUserOrder(userOrder);
        return userOrder.getExternalId();
    }
}
