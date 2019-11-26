package cn.part03.ch30.factoryVSbuilder.builder;

//代码清单30-7 抽象建造者
public abstract class Builder {
    //定义一个超人的应用
    protected final SuperMan superMan = new SuperMan();

    //构建出超人的躯体
    public void setBody(String body) {
        this.superMan.setBody(body);
    }

    //构建出超人的特殊技能
    public void setSpecialTalent(String st) {
        this.superMan.setSpecialTalent(st);
    }

    //构建出超人的特殊标记
    public void setSpecialSymbol(String ss) {
        this.superMan.setSpecialSymbol(ss);
    }

    //构建出一个完整的超人
    public abstract SuperMan getSuperMan();
}

//一个典型的模板方法模式，超人的各个部件（躯体、灵魂、标志）都准备好了，具体怎么组装则是由实现类来决定