package cn.part02.ch17.decorator2;

//代码清单17-9 老爸查看修饰后的成绩单
public class Father {

    public static void main(String[] args) {
        //把成绩单拿过来
        SchoolReport sr;
        //原装的成绩单
        sr = new FouthGradeSchoolReport();
        //加了最高分说明的成绩单
        sr = new HighScoreDecorator(sr);
        //又加了成绩排名的说明
        sr = new SortDecorator(sr);
        //看成绩单
        sr.report();
        //然后老爸一看，很开心，就签名了
        sr.sign("老三"); //我叫小三，老爸当然叫老三
    }
}

// 这次考试语文最高是75，数学是78，自然是80
// 尊敬的XXX家长:
//  ......
//  语文 62 数学65 体育 98 自然 63
//  .......
//  家长签名：
// 我是排名第38名...
// 家长签名为：老三