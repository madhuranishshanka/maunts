package com.devspace.security.dummy;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface DummyServiceBean {

    @PreAuthorize("hasRole('GUEST')")
    boolean isSecuredMethod();

    boolean isNonSecuredMethod();
}
