package cn.part02.ch11.builder3;

import java.util.ArrayList;

//代码清单11-10 导演类
public class Director {
    private ArrayList<String> sequence = new ArrayList();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    /*
    * A类型的奔驰车模型，先start，然后stop，其他什么引擎、喇叭一概没有
    */
    public BenzModel getABenzModel() {
        //清理场景，这里是一些初级程序员不注意的地方
        this.sequence.clear();
        //ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");
        //按照顺序返回一个奔驰车
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /*
    * B型号的奔驰车模型，是先发动引擎，然后启动，然后停止，没有喇叭
    */
    public BenzModel getBBenzModel() {
        this.sequence.clear();
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /*
    * C型号的宝马车是先按下喇叭（炫耀嘛），然后启动，然后停止
    */
    public BMWModel getCBMWModel() {
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }

    /*
    * D类型的宝马车只有一个功能，就是跑，启动起来就跑，永远不停止
    */
    public BMWModel getDBMWModel() {
        this.sequence.clear();
        this.sequence.add("start");
        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel) this.benzBuilder.getCarModel();
    }
    /*
    * 这里还可以有很多方法，你可以先停止，然后再启动，或者一直停着不动，静态的嘛
    * 导演类嘛，按照什么顺序是导演说了算
    */
}

//顺便说一下，大家看一下程序中有很多this调用
//这个我一般是这样要求项目组成员的，如果你要调用类中的成员变量或方法
//需要在前面加上this关键字，不加也能正常地跑起来，但是不清晰
//加上this关键字，我就是要调用本类中的成员变量或方法，而不是本方法中的一个变量
//还有super方法也是一样，是调用父类的成员变量或者方法，那就加上这个关键字
//不要省略，这要靠约束，还有就是程序员的自觉性，他要是死不悔改，那咱也没招

//注意：上面每个方法都有一个this.sequence.clear()，估计你一看就明白。但是作为一个
//系统分析师或是技术经理一定要告诉项目成员，ArrayList和HashMap如果定义成类的成员变
//量，那你在方法中的调用一定要做一个clear的动作，以防止数据混乱。如果你发生过一次类
//似问题的话，比如ArrayList中出现一个“出乎意料”的数据，而你又花费了几个通宵才解决这
//个问题，那你会有很深刻的印象