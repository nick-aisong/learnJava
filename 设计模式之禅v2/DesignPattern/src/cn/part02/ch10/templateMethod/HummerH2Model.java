package cn.part02.ch10.templateMethod;

/**
 * Created by NKS on 2017/9/16.
 */
public class HummerH2Model extends HummerModel {
    @Override
    public void start() {
        System.out.println("悍马H2发动...");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H2鸣笛...");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H2引擎声音是这样在...");
    }

    //默认没有喇叭的
    protected boolean isAlarm() {
        return false;
    }

}
