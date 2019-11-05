package cn.part02.ch12.proxy5;

//代码清单12-18 强制代理的场景类
public class Client3 {
    public static void main(String[] args) {
        //定义一个游戏的角色
        IGamePlayer player = new GamePlayer("张三");
        //获得指定的代理
        IGamePlayer proxy = player.getProxy();
        //开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy.login("zhangSan", "password");
        //开始杀怪
        proxy.killBoss();
        //升级
        proxy.upgrade();
        //记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }
}

//开始时间是：2009-8-25 10:45
//登录名为zhangSan的用户张三登录成功！
//张三在打怪！
//张三 又升了一级！
//结束时间是：2009-8-26 03:40
