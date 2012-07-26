package com.devspace.logging.mock;

import com.devspace.logging.domain.Log;
import com.devspace.logging.domain.LogLevel;
import org.springframework.stereotype.Component;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Component
public class TestBean {

    @Log(logLevel = LogLevel.ERROR)
    public int getIntegerMax(){
        return Integer.MAX_VALUE;
    }

    @Log(logLevel = LogLevel.ERROR)
    public void throwException() throws Exception{
        throw new Exception("Simple bean exception");
    }
}
