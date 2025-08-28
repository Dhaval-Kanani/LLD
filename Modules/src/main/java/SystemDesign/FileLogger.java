package SystemDesign;

public class FileLogger extends LogObserver{
    @Override
    public void display(String msg) {
        System.out.println("FILE: " + msg);
    }
}
