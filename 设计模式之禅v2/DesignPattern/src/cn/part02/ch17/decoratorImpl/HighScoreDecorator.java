package cn.part02.ch17.decoratorImpl;

/**
 * Created by NKS on 2017/9/16.
 */
public class HighScoreDecorator extends Decorator {
    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportHighScore(){
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }

    public void report(){
        this.reportHighScore();
        super.report();
    }
}
