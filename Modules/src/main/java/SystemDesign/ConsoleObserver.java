package SystemDesign;

public class ConsoleObserver implements LogObserver{
    @Override
    public void log(String msg) {
        System.out.println("Printing logs in Console: " + msg);
    }
}
