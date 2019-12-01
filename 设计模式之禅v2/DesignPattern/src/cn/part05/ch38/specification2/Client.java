package cn.part05.ch38.specification2;

import java.util.ArrayList;

//代码清单38-28 场景类
public class Client {
    public static void main(String[] args) {
        //待分析的对象
        ArrayList<Object> list = new ArrayList<Object>();
        //定义两个业务规格书
        ISpecification spec1 = new BizSpecification(new Object());
        ISpecification spec2 = new BizSpecification(new Object());
        //规则的调用
        for (Object obj : list) {
            if (spec1.and(spec2).isSatisfiedBy(obj)) { //and操作
                System.out.println(obj);
            }
        }
    }
}
