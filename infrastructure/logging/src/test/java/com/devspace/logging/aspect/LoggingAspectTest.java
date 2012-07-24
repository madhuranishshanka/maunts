package com.devspace.logging.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:logging-context.xml"})
public class LoggingAspectTest {

    @Autowired
    SimpleBean simpleBean;

    @Test
    public void testLog(){
        int integerMax = simpleBean.getIntegerMax();
        assertTrue(integerMax>0);
    }
}
