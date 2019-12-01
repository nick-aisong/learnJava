package cn.part05.ch38.servant;

//代码清单38-33 抽象的清洁者
public class Cleaner {
    //清洁
    public void clean(Cleanable clean) {
        clean.celaned();
    }
}
