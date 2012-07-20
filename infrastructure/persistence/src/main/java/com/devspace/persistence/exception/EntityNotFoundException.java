package com.devspace.persistence.exception;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class EntityNotFoundException extends Exception {

    private Class entityClass;

    public EntityNotFoundException(Class entityClass, String message) {
        super(message);
        this.entityClass = entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }
}
