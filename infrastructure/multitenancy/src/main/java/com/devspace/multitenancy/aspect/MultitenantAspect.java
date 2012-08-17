package com.devspace.multitenancy.aspect;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.multitenancy.domain.TenantEntity;
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
    public void beforeQuery(JoinPoint joinPoint) throws Throwable {
        EntityManager entityManager = (EntityManager) joinPoint.getTarget();
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("tenantFilter").setParameter("tenantId", TenantContext.getTenant());
    }

    @Before("execution(* javax.persistence.EntityManager.persist(..))")
    public void beforeSave(JoinPoint joinPoint) throws Throwable {
        EntityManager entityManager = (EntityManager) joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];
        if (entity instanceof TenantEntity) {
            ((TenantEntity) entity).setTenantId(TenantContext.getTenant());
        }
    }
}
