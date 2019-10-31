package cn.part02.ch10.templateMethod4;

//代码清单10-11 扩展后的H2悍马
public class HummerH2Model extends HummerModel {
    protected void alarm() {
        System.out.println("悍马H2鸣笛...");
    }

    protected void engineBoom() {
        System.out.println("悍马H2引擎声音是这样的...");
    }

    protected void start() {
        System.out.println("悍马H2发动...");
    }

    protected void stop() {
        System.out.println("悍马H2停车...");
    }

    //默认没有喇叭的
    protected boolean isAlarm() {
        return false;
    }
}