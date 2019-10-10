package cn.nks.decorator;

/**
 * Created by NKS on 2017/9/16.
 */
public class Father2 {

    public static void main(String[] args){
        SchoolReport sr = new SugarFouthGradeSchoolReport();
        sr.report();
        sr.sign("xxx");
    }
}
