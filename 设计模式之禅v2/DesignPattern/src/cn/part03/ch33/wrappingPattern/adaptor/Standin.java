package cn.part03.ch33.wrappingPattern.adaptor;

//代码清单33-43 替身演员
public class Standin implements IStar {
    private IActor actor;

    //替身是谁
    public Standin(IActor _actor) {
        this.actor = _actor;
    }

    public void act(String context) {
        actor.playact(context);
    }
}