package org.modules.SystemDesign.LoggingFrameWorkv2.Observer;

import org.modules.SystemDesign.LoggingFrameWorkv2.LogMessage;

public interface LogObserver {
    void log(LogMessage logMessage);
}
