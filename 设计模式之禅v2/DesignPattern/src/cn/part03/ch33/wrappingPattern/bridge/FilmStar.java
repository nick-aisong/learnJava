package cn.part03.ch33.wrappingPattern.bridge;

//代码清单33-49 电影明星
public class FilmStar extends AbsStar {
    //默认的电影明星的主要工作是拍电影
    public FilmStar() {
        super(new ActFilm());
    }

    //也可以重新设置一个新职业
    public FilmStar(AbsAction _action) {
        super(_action);
    }

    //细化电影明星的职责
    public void doJob() {
        System.out.println("\n======影星的工作=====");
        super.doJob();
    }
}
