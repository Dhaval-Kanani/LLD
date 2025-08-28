package SystemDesign;

public class ErrorLogger extends AbstarctLogger{
    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void display(String msg) {
        System.out.println("Error: "+  msg);
    }
}
