package com.devspace.multitenancy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Aspect
@Component
public class MultitenantAspect {

    @Before("execution(* javax.persistence.EntityManager.create*(..))")
    public void before(JoinPoint joinPoint) throws Throwable {
        EntityManager entityManager = (EntityManager) joinPoint.getTarget();
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("tenantFilter").setParameter("tenantId", "TID1");
        System.out.println("done");
    }
}
