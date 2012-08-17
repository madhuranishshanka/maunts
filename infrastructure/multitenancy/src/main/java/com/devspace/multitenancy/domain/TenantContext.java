package com.devspace.multitenancy.domain;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class TenantContext {

    private static final ThreadLocal<String> tenant = new ThreadLocal<String>();

    public static void setTenant(String tenantId) {
        tenant.set(tenantId);
    }

    public static String getTenant() {
        return tenant.get();
    }

    public static void reset() {
        tenant.remove();
    }
}
