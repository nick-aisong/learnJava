package cn.part02.ch28.flyweight3;

import java.util.HashMap;

//代码清单28-9 享元工厂
public class FlyweightFactory {
    //定义一个池容器
    private static HashMap<String, Flyweight> pool = new HashMap<String, Flyweight>();

    //享元工厂
    public static Flyweight getFlyweight(String Extrinsic) {
        //需要返回的对象
        Flyweight flyweight = null;
        //在池中没有该对象
        if (pool.containsKey(Extrinsic)) {
            flyweight = pool.get(Extrinsic);
        } else {
            //根据外部状态创建享元对象
            flyweight = new ConcreteFlyweight1(Extrinsic);
            //放置到池中
            pool.put(Extrinsic, flyweight);
        }
        return flyweight;
    }
}