package com.devspace.logging.aspect;

import com.devspace.logging.domain.LogLevel;
import org.springframework.stereotype.Component;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Component
public class SimpleBean {

    @Log(LogLevel.INFO)
    public int getIntegerMax(){
        return Integer.MAX_VALUE;
    }
}
