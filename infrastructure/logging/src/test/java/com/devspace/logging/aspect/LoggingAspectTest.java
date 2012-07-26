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
    TestBean simpleBean;

    MockLogger mockLogger;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        mockLogger = new MockLogger(MockLogLevel.ERROR);
        LoggingAspect loggingAspect = (LoggingAspect) context.getBean("loggingAspect");
        loggingAspect.setLogger(mockLogger);
    }

    @Test
    public void testBeforeAfterLogging() {
        List<String> errorLogMessages = mockLogger.getErrorLogMessages();
        int prevCount = errorLogMessages.size();
        simpleBean.getIntegerMax();
        errorLogMessages = mockLogger.getErrorLogMessages();
        int newCount = errorLogMessages.size();
        assertEquals(2, newCount - prevCount);
    }

    @Test
    public void testExceptionLogging() {
        List<String> errorLogMessages = mockLogger.getErrorLogMessages();
        int prevCount = errorLogMessages.size();
        try {
            simpleBean.throwException();
            fail("An Exception should be thrown");
        } catch (Exception e) {
        }
        errorLogMessages = mockLogger.getErrorLogMessages();
        int newCount = errorLogMessages.size();
        assertEquals(2, newCount - prevCount);
    }
}
