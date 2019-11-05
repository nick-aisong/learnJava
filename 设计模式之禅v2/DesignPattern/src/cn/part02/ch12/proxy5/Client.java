package cn.part02.ch12.proxy5;

//代码清单12-16 直接访问真实角色
public class Client {
    public static void main(String[] args) {
        //定义一个游戏的角色
        IGamePlayer player = new GamePlayer("张三");
        //开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        player.login("zhangSan", "password");
        //开始杀怪
        player.killBoss();
        //升级
        player.upgrade();
        //记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }
}

//开始时间是：2009-8-25 10:45
//请使用指定的代理访问
//请使用指定的代理访问
//请使用指定的代理访问
//结束时间是：2009-8-26 03:40