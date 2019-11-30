package cn.part03.ch33.wrappingPattern.adaptor;

//代码清单33-40 电影明星
public class FilmStar implements IStar {
    public void act(String context) {
        System.out.println("明星演戏：" + context);
    }
}