package com.devspace.logging.aspect;

import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Aspect
@Component
public class LoggingAspect {

    private org.apache.commons.logging.Log logger = LogFactory.getLog(this.getClass());

    @Before("@annotation(log)")
    public void logBefore(JoinPoint joinPoint, Log log) {

        logger.info("Join point king : " + joinPoint.getKind());
        logger.info("Signature declaring type : " + joinPoint.getSignature().getDeclaringTypeName());
        logger.info("Entering to method : " + joinPoint.getSignature().getName());
        logger.info("Arguments : " + Arrays.asList(joinPoint.getArgs()));
        logger.info("Target class : " + joinPoint.getTarget().getClass().getName());
        logger.info("This class : " + joinPoint.getThis().getClass().getName());

    }
    @AfterReturning(value = "@annotation(log)", returning = "result",argNames = "joinPoint,result,log")
    public void logAfter(JoinPoint joinPoint, Object result,Log log) {

        logger.info("Exiting from Method :" + joinPoint.getSignature().getName());
        logger.info("Return value :" + result);

    }

}
