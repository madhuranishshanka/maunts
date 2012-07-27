package com.devspace.logging.aspect;

import com.devspace.logging.mock.MockLogLevel;
import com.devspace.logging.mock.MockLogger;
import com.devspace.logging.mock.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:logging-context.xml"})
public class LoggingAspectTest implements ApplicationContextAware {
    @Autowired
    TestBean testBean;

    LoggingAspect loggingAspect;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        loggingAspect = (LoggingAspect) context.getBean("loggingAspect");
    }

    @Test
    public void testErrorLoggingWithLogLevelError() {
        MockLogger mockLogger = new MockLogger(MockLogLevel.ERROR);
        loggingAspect.setLogger(mockLogger);

        List<String> logMessages = mockLogger.getErrorLogMessages();
        int prevCount = logMessages.size();

        testBean.logLevelErrorMethod();

        logMessages = mockLogger.getErrorLogMessages();
        int newCount = logMessages.size();
        assertEquals(2, newCount - prevCount);
    }

    @Test
    public void testInfoLoggingWithLogLevelError() {
        MockLogger mockLogger = new MockLogger(MockLogLevel.ERROR);
        loggingAspect.setLogger(mockLogger);

        List<String> logMessages = mockLogger.getInfoLogMessages();
        int prevCount = logMessages.size();

        testBean.logLevelInfoMethod();

        logMessages = mockLogger.getInfoLogMessages();
        int newCount = logMessages.size();
        assertEquals(0, newCount - prevCount);
    }

    @Test
    public void testInfoLoggingWithLogLevelInfo() {
        MockLogger mockLogger = new MockLogger(MockLogLevel.INFO);
        loggingAspect.setLogger(mockLogger);

        List<String> logMessages = mockLogger.getInfoLogMessages();
        int prevCount = logMessages.size();

        testBean.logLevelInfoMethod();

        logMessages = mockLogger.getInfoLogMessages();
        int newCount = logMessages.size();
        assertEquals(2, newCount - prevCount);
    }

    @Test
    public void testExceptionLogging() {
        MockLogger mockLogger = new MockLogger(MockLogLevel.ERROR);
        loggingAspect.setLogger(mockLogger);

        List<String> logMessages = mockLogger.getErrorLogMessages();
        int prevCount = logMessages.size();
        try {
            testBean.throwException();
            fail("An Exception should be thrown");
        } catch (Exception e) {
        }
        logMessages = mockLogger.getErrorLogMessages();
        int newCount = logMessages.size();
        assertEquals(2, newCount - prevCount);
    }
}
