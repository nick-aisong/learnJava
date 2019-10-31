package cn.part02.ch10.templateMethod2;

public class Client {
    public static void main(String[] args) {
        //XX公司要H1型号的悍马
        HummerModel h1 = new HummerH1Model();
        //H1模型演示
        h1.run();
    }
}

//悍马H1发动...
//悍马H1引擎声音是这样的...
//悍马H1鸣笛...
//悍马H1停车...