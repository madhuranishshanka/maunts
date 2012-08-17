package com.devspace.security.mock.impl;

import com.devspace.security.mock.DummyServiceBean;
import org.springframework.stereotype.Service;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("dummyServiceBean")
public class DummyServiceBeanImpl implements DummyServiceBean {

    public boolean isSecuredMethod() {
        return true;
    }

    public boolean isNonSecuredMethod() {
        return true;
    }
}
