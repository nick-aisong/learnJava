package cn.part02.ch17.decorator;

//代码清单17-5 老爸查看修饰后的成绩单
public class Father2 {

    public static void main(String[] args) {
        //把美化过的成绩单拿过来
        SchoolReport sr = new SugarFouthGradeSchoolReport();
        //看成绩单
        sr.report();
        //然后老爸，一看，很开心，就签名了
        sr.sign("老三"); //我叫小三，老爸当然叫老三
    }
}
