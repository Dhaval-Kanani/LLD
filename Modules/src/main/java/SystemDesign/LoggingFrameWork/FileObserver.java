package SystemDesign.LoggingFrameWork;

public class FileObserver implements LogObserver{
    @Override
    public void log(String msg) {
        System.out.println("Write to File: " + msg);
    }
}
