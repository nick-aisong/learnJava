package cn.part02.ch10.templateMethod4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//代码清单10-12 扩展后的场景类
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("-------H1型号悍马--------");
        System.out.println("H1型号的悍马是否需要喇叭声响？0-不需要 1-需要");
        String type = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        HummerH1Model h1 = new HummerH1Model();
        if (type.equals("0")) {
            h1.setAlarm(false);
        }
        h1.run();
        System.out.println("\n-------H2型号悍马--------");
        HummerH2Model h2 = new HummerH2Model();
        h2.run();
    }
}

//-------H1型号悍马--------
//H1型号的悍马是否需要喇叭声响？0-不需要 1-需要
//0
//悍马H1发动...
//悍马H1引擎声音是这样的...
//悍马H1停车...
//
//-------H2型号悍马--------
//悍马H2发动...
//悍马H2引擎声音是这样的...
//悍马H2停车...


//-------H1型号悍马--------
//H1型号的悍马是否需要喇叭声响？0-不需要 1-需要
//1
//悍马H1发动...
//悍马H1引擎声音是这样的...
//悍马H1鸣笛...
//悍马H1停车...
//
//-------H2型号悍马--------
//悍马H2发动...
//悍马H2引擎声音是这样的...
//悍马H2停车...