package com.devspace.security.mock.impl;

import com.devspace.security.mock.TestBean;
import org.springframework.stereotype.Service;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("testBean")
public class TestBeanImpl implements TestBean{

    public boolean isSecuredMethod() {
        return true;
    }

    public boolean isNonSecuredMethod() {
        return true;
    }
}
