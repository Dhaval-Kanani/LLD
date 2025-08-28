package SystemDesign;

public class InfoLogger extends AbstarctLogger{
    public InfoLogger(int level){
        this.level = level;
    }

    @Override
    protected void display(String msg) {
        System.out.println("Info: "+  msg);
    }
}
