package com.devspace.logging.mock;

import org.apache.commons.logging.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class MockLogger implements Log {

    private List<String> traceLogMessages = new ArrayList<String>();
    private List<String> debugLogMessages = new ArrayList<String>();
    private List<String> infoLogMessages = new ArrayList<String>();
    private List<String> warnLogMessages = new ArrayList<String>();
    private List<String> errorLogMessages = new ArrayList<String>();
    private List<String> fatalLogMessages = new ArrayList<String>();

    private boolean trace;
    private boolean debug;
    private boolean info;
    private boolean warn;
    private boolean error;
    private boolean fatal;

    public MockLogger(MockLogLevel mockLogLevel) {
        if (mockLogLevel.equals(MockLogLevel.TRACE)) {
            trace = true;
        } else if (mockLogLevel.equals(MockLogLevel.DEBUG)) {
            debug = true;
        } else if (mockLogLevel.equals(MockLogLevel.INFO)) {
            info = true;
        } else if (mockLogLevel.equals(MockLogLevel.WARN)) {
            warn = true;
        } else if (mockLogLevel.equals(MockLogLevel.ERROR)) {
            error = true;
        } else if (mockLogLevel.equals(MockLogLevel.FATAL)) {
            fatal = true;
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return debug;
    }

    @Override
    public boolean isErrorEnabled() {
        return error;
    }

    @Override
    public boolean isFatalEnabled() {
        return fatal;
    }

    @Override
    public boolean isInfoEnabled() {
        return info;
    }

    @Override
    public boolean isTraceEnabled() {
        return trace;
    }

    @Override
    public boolean isWarnEnabled() {
        return warn;
    }

    @Override
    public void trace(Object o) {
        traceLogMessages.add(o.toString());
    }

    @Override
    public void trace(Object o, Throwable throwable) {
        traceLogMessages.add(o.toString());
    }

    @Override
    public void debug(Object o) {
        debugLogMessages.add(o.toString());
    }

    @Override
    public void debug(Object o, Throwable throwable) {
        debugLogMessages.add(o.toString());
    }

    @Override
    public void info(Object o) {
        infoLogMessages.add(o.toString());
    }

    @Override
    public void info(Object o, Throwable throwable) {
        infoLogMessages.add(o.toString());
    }

    @Override
    public void warn(Object o) {
        warnLogMessages.add(o.toString());
    }

    @Override
    public void warn(Object o, Throwable throwable) {
        warnLogMessages.add(o.toString());
    }

    @Override
    public void error(Object o) {
        errorLogMessages.add(o.toString());
    }

    @Override
    public void error(Object o, Throwable throwable) {
        errorLogMessages.add(o.toString());
    }

    @Override
    public void fatal(Object o) {
        fatalLogMessages.add(o.toString());
    }

    @Override
    public void fatal(Object o, Throwable throwable) {
        fatalLogMessages.add(o.toString());
    }

    public List<String> getTraceLogMessages() {
        return traceLogMessages;
    }

    public List<String> getDebugLogMessages() {
        return debugLogMessages;
    }

    public List<String> getInfoLogMessages() {
        return infoLogMessages;
    }

    public List<String> getWarnLogMessages() {
        return warnLogMessages;
    }

    public List<String> getErrorLogMessages() {
        return errorLogMessages;
    }

    public List<String> getFatalLogMessages() {
        return fatalLogMessages;
    }
}
