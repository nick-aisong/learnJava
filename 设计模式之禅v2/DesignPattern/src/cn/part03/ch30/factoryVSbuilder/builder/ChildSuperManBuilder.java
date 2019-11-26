package cn.part03.ch30.factoryVSbuilder.builder;

//代码清单30-9 未成年超人建造者
public class ChildSuperManBuilder extends Builder {
    public SuperMan getSuperMan() {
        super.setBody("强壮的躯体");
        super.setSpecialTalent("刀枪不入");
        super.setSpecialSymbol("胸前带小S标记");
        return super.superMan;
    }
}

//大家注意看我们这两个具体的建造者，它们都关注了产品的各个部分，在某些应用场景
//下甚至会关心产品的构建顺序，即使是相同的部件，装配顺序不同，产生的结果也不同，这
//也正是建造者模式的意图：通过不同的部件、不同装配产生不同的复杂对象