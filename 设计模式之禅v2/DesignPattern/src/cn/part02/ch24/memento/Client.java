package cn.part02.ch24.memento;

//代码清单24-2 场景类
public class Client {
    public static void main(String[] args) {
        //声明出主角
        Boy boy = new Boy();
        boy.setState("心情很棒！");
        //初始化当前状态
        System.out.println("=====男孩现在的状态======");
        System.out.println(boy.getState());
        //需要记录下当前状态呀
        Boy backup = new Boy();
        backup.setState(boy.getState());
        //男孩去追女孩，状态改变
        boy.changeState();
        System.out.println("\n=====男孩追女孩子后的状态======");
        System.out.println(boy.getState());
        //追女孩失败，恢复原状
        boy.setState(backup.getState());
        System.out.println("\n=====男孩恢复后的状态======");
        System.out.println(boy.getState());
    }
}

//=====男孩现在的状态======
//心情很棒！
//
//=====男孩追女孩子后的状态======
//心情可能很不好
//
//=====男孩恢复后的状态======
//心情很棒！
