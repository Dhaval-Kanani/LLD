package org.modules.SystemDesign.LoggingFrameWorkv2;

public class LogMessage {
    private String message;
    private int level;
    private String detailedMessage;

    public LogMessage(int level, String message) {
        this.message = message;
        this.level = level;
        this.detailedMessage = createDetailedMessage(level);
    }

    private String createDetailedMessage(int level) {
        return switch (level) {
            case 1 -> "INFO: " + message + " " + Thread.currentThread().getName() + " " + System.currentTimeMillis();
            case 2 -> "DEBUG: " + message + " " + Thread.currentThread().getName() + " " + System.currentTimeMillis();
            case 3 -> "ERROR: " + message + " " + Thread.currentThread().getName() + " " + System.currentTimeMillis();
            default ->
                    "UNKNOWN LEVEL: " + message + " " + Thread.currentThread().getName() + " " + System.currentTimeMillis();
        };
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public String getMessage() {
        return message;
    }

    public int getLevel() {
        return level;
    }
}
