package cn.part02.ch28.flyweight5;

import java.util.HashMap;

//代码清单28-14 享元工厂
public class SignInfoFactory {
    //池容器
    private static HashMap<ExtrinsicState, SignInfo> pool = new HashMap<ExtrinsicState, SignInfo>();
    //池容器(对照组String作为key)
    private static HashMap<String, SignInfo> pool2 = new HashMap<String, SignInfo>();

    //从池中获得对象
    public static SignInfo getSignInfo(ExtrinsicState key) {
        //设置返回对象
        SignInfo result = null;
        //池中没有该对象，则建立，并放入池中
        if (!pool.containsKey(key)) {
            result = new SignInfo();
            pool.put(key, result);
        } else {
            result = pool.get(key);
        }
        return result;
    }

    //从池中获得对象(对照组String作为key)
    public static SignInfo getSignInfo(String key) {
        //设置返回对象
        SignInfo result = null;
        //池中没有该对象，则建立，并放入池中
        if (!pool2.containsKey(key)) {
            result = new SignInfo();
            pool2.put(key, result);
        } else {
            result = pool2.get(key);
        }
        return result;
    }
}