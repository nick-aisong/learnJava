package cn.part02.ch12.proxy7;

//代码清单12-1 游戏者接口
public interface IGamePlayer {
    //登录游戏
    public void login(String user, String password);

    //杀怪，网络游戏的主要特色
    public void killBoss();

    //升级
    public void upgrade();
}