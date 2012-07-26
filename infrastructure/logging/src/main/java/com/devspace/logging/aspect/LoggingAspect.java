package com.devspace.logging.aspect;

import com.devspace.logging.domain.Log;
import com.devspace.logging.domain.LogLevel;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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

        LogLevel logLevel = log.logLevel();

        if (logger.isTraceEnabled() && logLevel.equals(LogLevel.TRACE)) {
            logger.trace(constructMethodEnteringLogMessage(joinPoint));
        } else if (logger.isDebugEnabled() && logLevel.equals(LogLevel.DEBUG)) {
            logger.debug(constructMethodEnteringLogMessage(joinPoint));
        } else if (logger.isInfoEnabled() && logLevel.equals(LogLevel.INFO)) {
            logger.info(constructMethodEnteringLogMessage(joinPoint));
        } else if (logger.isWarnEnabled() && logLevel.equals(LogLevel.WARN)) {
            logger.warn(constructMethodEnteringLogMessage(joinPoint));
        } else if (logger.isErrorEnabled() && logLevel.equals(LogLevel.ERROR)) {
            logger.error(constructMethodEnteringLogMessage(joinPoint));
        } else if (logger.isFatalEnabled() && logLevel.equals(LogLevel.FATAL)) {
            logger.fatal(constructMethodEnteringLogMessage(joinPoint));
        }

    }

    @AfterReturning(value = "@annotation(log)", returning = "result", argNames = "joinPoint,result,log")
    public void logAfter(JoinPoint joinPoint, Object result, Log log) {

        LogLevel logLevel = log.logLevel();

        if (logger.isTraceEnabled() && logLevel.equals(LogLevel.TRACE)) {
            logger.trace(constructMethodExitingLogMessage(joinPoint, result));
        } else if (logger.isDebugEnabled() && logLevel.equals(LogLevel.DEBUG)) {
            logger.debug(constructMethodExitingLogMessage(joinPoint, result));
        } else if (logger.isInfoEnabled() && logLevel.equals(LogLevel.INFO)) {
            logger.info(constructMethodExitingLogMessage(joinPoint, result));
        } else if (logger.isWarnEnabled() && logLevel.equals(LogLevel.WARN)) {
            logger.warn(constructMethodExitingLogMessage(joinPoint, result));
        } else if (logger.isErrorEnabled() && logLevel.equals(LogLevel.ERROR)) {
            logger.error(constructMethodExitingLogMessage(joinPoint, result));
        } else if (logger.isFatalEnabled() && logLevel.equals(LogLevel.FATAL)) {
            logger.fatal(constructMethodExitingLogMessage(joinPoint, result));
        }

    }

    @AfterThrowing(value = "@annotation(log)", throwing = "throwable", argNames = "joinPoint,throwable,log")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable, Log log) {

        LogLevel logLevel = log.logLevel();
        if (log.logException()) {
            if (logger.isTraceEnabled() && logLevel.equals(LogLevel.TRACE)) {
                logger.trace(constructExceptionLogMessage(joinPoint, throwable));
            } else if (logger.isDebugEnabled() && logLevel.equals(LogLevel.DEBUG)) {
                logger.debug(constructExceptionLogMessage(joinPoint, throwable));
            } else if (logger.isInfoEnabled() && logLevel.equals(LogLevel.INFO)) {
                logger.info(constructExceptionLogMessage(joinPoint, throwable));
            } else if (logger.isWarnEnabled() && logLevel.equals(LogLevel.WARN)) {
                logger.warn(constructExceptionLogMessage(joinPoint, throwable));
            } else if (logger.isErrorEnabled() && logLevel.equals(LogLevel.ERROR)) {
                logger.error(constructExceptionLogMessage(joinPoint, throwable));
            } else if (logger.isFatalEnabled() && logLevel.equals(LogLevel.FATAL)) {
                logger.fatal(constructExceptionLogMessage(joinPoint, throwable));
            }
        }
    }

    public org.apache.commons.logging.Log getLogger() {
        return logger;
    }

    public void setLogger(org.apache.commons.logging.Log logger) {
        this.logger = logger;
    }

    private String constructExceptionLogMessage(JoinPoint joinPoint, Throwable throwable) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Exception .. ");
        logMessage.append(" Method name : " + joinPoint.getSignature().getName());
        logMessage.append(" Message :" + throwable.getMessage());
        logMessage.append(" Cause : " + throwable.getCause());
        return logMessage.toString();
    }

    private String constructMethodEnteringLogMessage(JoinPoint joinPoint) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Entering .. ");
        logMessage.append(" Class name : " + joinPoint.getTarget().getClass().getName());
        logMessage.append(" Method name : " + joinPoint.getSignature().getName());
        logMessage.append(" Input parameters : " + Arrays.asList(joinPoint.getArgs()));
        return logMessage.toString();
    }

    private String constructMethodExitingLogMessage(JoinPoint joinPoint, Object result) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Exiting .. ");
        logMessage.append(" Method name : " + joinPoint.getSignature().getName());
        logMessage.append(" Return value : " + result);
        return logMessage.toString();
    }

}
