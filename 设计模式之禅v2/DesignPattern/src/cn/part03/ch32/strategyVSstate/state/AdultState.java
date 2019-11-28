package cn.part03.ch32.strategyVSstate.state;

//代码清单32-27 成年人状态
public class AdultState extends HumanState {
    //成年人的工作就是先养活自己，然后为社会做贡献
    public void work() {
        System.out.println("成年人的工作就是先养活自己，然后为社会做贡献！");
        super.human.setState(Human.OLD_STATE);
    }
}
