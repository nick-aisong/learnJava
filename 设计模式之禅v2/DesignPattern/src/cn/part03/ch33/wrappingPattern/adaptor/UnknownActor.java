package cn.part03.ch33.wrappingPattern.adaptor;

//代码清单33-42 普通演员
public class UnknownActor implements IActor {
    //普通演员演戏
    public void playact(String context) {
        System.out.println("普通演员：" + context);
    }
}