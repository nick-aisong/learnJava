package cn.part03.ch33.facadeVSmediator.facade;

import java.util.Random;

//代码清单33-25 税收
public class Tax {
    //收取多少税金
    public int getTax() {
        //交纳一个随机数量的税金
        return (new Random()).nextInt(300);
    }
}