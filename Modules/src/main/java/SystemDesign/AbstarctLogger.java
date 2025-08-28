package SystemDesign;

public abstract class AbstarctLogger {
    int level;

    AbstarctLogger nextLogger;

    public void setNextLogger(AbstarctLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    void logMessage(int level, String msg){
        if(level==this.level){
            display(msg);
        }
        if(nextLogger!=null){
            nextLogger.logMessage(level, msg);
        }
    }

    protected abstract void display(String msg);
}
