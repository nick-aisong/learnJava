package cn.part03.ch33.wrappingPattern.decorator;

//代码清单33-34 假明星
public class FreakStar implements IStar {
    public void act() {
        System.out.println("演中：演技很拙劣");
    }
}