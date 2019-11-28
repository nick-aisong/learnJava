package cn.part03.ch32.strategyVSstate.state;

//代码清单32-28 老年人状态
public class OldState extends HumanState {
    //老年人的工作就是享受天伦之乐
    public void work() {
        System.out.println("老年人的工作就是享受天伦之乐！");
    }
}