package cn.part02.ch11.builder2;

//代码清单11-2 奔驰模型代码
public class BenzModel extends CarModel {
    protected void alarm() {
        System.out.println("奔驰车的喇叭声音是这个样子的...");
    }

    protected void engineBoom() {
        System.out.println("奔驰车的引擎是这个声音的...");
    }

    protected void start() {
        System.out.println("奔驰车跑起来是这个样子的...");
    }

    protected void stop() {
        System.out.println("奔驰车应该这样停车...");
    }
}