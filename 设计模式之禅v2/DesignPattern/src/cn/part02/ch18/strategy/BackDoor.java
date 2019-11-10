package cn.part02.ch18.strategy;

//代码清单18-2 乔国老开后门
public class BackDoor implements IStrategy {
    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
