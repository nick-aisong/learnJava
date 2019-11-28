package cn.part03.ch32.strategyVSstate.state;

//代码清单32-26 孩童状态
public class ChildState extends HumanState {
    //儿童的工作就是玩耍
    public void work() {
        System.out.println("儿童的工作是玩耍！");
        super.human.setState(Human.ADULT_STATE);
    }
}

//ChildState类代表孩童状态，在该状态下的工作就是玩耍。读者看着可能有点惊奇，在
//work方法中为什么要设置下一个状态？因为我们的状态变化都是单方向的，从孩童到成年
//人，然后到老年人，每个状态转换到其他状态只有一个方向，因此会在这里看到work有两个
//职责：完成工作逻辑和定义下一状态