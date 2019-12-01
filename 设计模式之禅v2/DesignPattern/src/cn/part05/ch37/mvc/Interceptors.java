package cn.part05.ch37.mvc;

import java.util.ArrayList;
import java.util.Iterator;

//代码清单37-16 拦截器链
public class Interceptors implements Iterable<AbstractInterceptor> {
    //根据拦截器列表建立一个拦截器链
    public Interceptors(ArrayList<AbstractInterceptor> list) {
    }

    //列出所有的拦截器
    public Iterator<AbstractInterceptor> iterator() {
        return null;
    }

    //拦截器链的执行方法
    public void intercept() {
        //委托拦截器执行
    }
}