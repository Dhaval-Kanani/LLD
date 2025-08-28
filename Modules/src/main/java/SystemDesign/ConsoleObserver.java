package SystemDesign;

public class ConsoleObserver extends LogObserver{
    @Override
    public void display(String msg) {
        System.out.println("CONSOLE: " + msg);
    }
}
