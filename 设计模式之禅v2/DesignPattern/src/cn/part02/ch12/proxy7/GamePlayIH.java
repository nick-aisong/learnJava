package cn.part02.ch12.proxy7;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//代码清单12-21 动态代理类
public class GamePlayIH implements InvocationHandler {
    //被代理者
    Class cls = null;
    //被代理的实例
    Object obj = null;

    //我要代理谁
    public GamePlayIH(Object _obj) {
        this.obj = _obj;
    }

    //调用被代理的方法
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = method.invoke(this.obj, args);
        //如果是登录方法，则发送信息
        if (method.getName().equalsIgnoreCase("login")) {
            System.out.println("有人在用我的账号登录！");
        }
        return result;
    }
}