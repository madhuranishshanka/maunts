package com.devspace.order.fulfilment.domain;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class UserProductAggregateId {

    private long id;

    public UserProductAggregateId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
