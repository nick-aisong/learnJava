package cn.part02.ch29.bridge2;

//代码清单29-1 抽象公司
public abstract class Corp {
    /*
    * 如果是公司就应该有生产，不管是软件公司还是制造业公司
    * 每家公司生产的东西都不一样，所以由实现类来完成
    */
    protected abstract void produce();

    /*
    * 有产品了，那肯定要销售啊，不销售公司怎么生存
    */
    protected abstract void sell();

    //公司是干什么的？赚钱的
    public void makeMoney() {
        //每个公司都是一样，先生产
        this.produce();
        //然后销售
        this.sell();
    }
}
