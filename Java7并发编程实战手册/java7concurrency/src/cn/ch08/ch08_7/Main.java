package cn.ch08.ch08_7;

public class Main {
    public static void main(String[] args) {

    }
}

// 1.选择菜单项Window，然后单击Preferences选项
// 2.在左边菜单栏展开Java选项
// 3.在左边菜单栏选择Debug选项
// 4.设置Default suspend policy for new breakpoints的值为Suspend VM
// 5.单击OK按钮确认更改
// 如本节中提到的，缺省时，当你在Eclipse中调试并发应用程序并且发现一个断点时，调试器仅暂停有断点的线程而其他线程继续执行

// 如果改变Default suspend policy for new breakpoints的值为Suspend VM，当调试一个并发应用程序到一个断点时，所有线程都会暂停执行
// 应建议根据应用程序的需要来选择最能满足需求的线程暂停策略