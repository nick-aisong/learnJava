package cn.part02.ch17.decoratorImpl;


/**
 * Created by NKS on 2017/9/16.
 */
public class Father {

    public static void main(String[] args) {
        SchoolReport sr;
        sr = new FouthGradeSchoolReport();

        sr = new HighScoreDecorator(sr);

        sr = new SortDecorator(sr);

        sr.report();

        sr.sign("xxx");

    }
}
