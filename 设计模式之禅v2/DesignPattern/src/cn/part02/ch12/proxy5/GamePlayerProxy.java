package cn.part02.ch12.proxy5;

//代码清单12-15 强制代理的代理类
public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer gamePlayer = null;

    //构造函数传递用户名
    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    //代练杀怪
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    //代练登录
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    //代练升级
    public void upgrade() {
        this.gamePlayer.upgrade();
    }

    //代理的代理暂时还没有，就是自己
    public IGamePlayer getProxy() {
        return this;
    }
}
