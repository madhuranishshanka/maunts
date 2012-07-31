package com.devspace.logging.domain;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Log {

    LogLevel logLevel() default LogLevel.DEBUG;
    boolean logException() default true;
}
