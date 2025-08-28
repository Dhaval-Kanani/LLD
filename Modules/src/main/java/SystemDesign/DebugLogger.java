package SystemDesign;

public class DebugLogger extends AbstarctLogger{
    public DebugLogger(int level){
        this.level = level;
    }

    @Override
    protected void display(String msg) {
        System.out.println("Debug: "+  msg);
    }
}
