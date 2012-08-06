package com.devspace.security.mock;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface TestBean {

    @PreAuthorize("hasRole('GUEST')")
    boolean isSecuredMethod();

    boolean isNonSecuredMethod();
}
