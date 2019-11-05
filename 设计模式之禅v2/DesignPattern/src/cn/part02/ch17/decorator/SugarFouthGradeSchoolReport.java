package cn.part02.ch17.decorator;

/**
 * Created by NKS on 2017/9/16.
 */
public class SugarFouthGradeSchoolReport extends FouthGradeSchoolReport {

    private void reportHighScore() {
        System.out.println("这次考试语文最高是75，数学是78，自然是80");
    }

    private void reportSort() {
        System.out.println("我是排名第38名...");
    }

    public void report() {
        this.reportHighScore();
        super.report();
        this.reportHighScore();
    }

}
