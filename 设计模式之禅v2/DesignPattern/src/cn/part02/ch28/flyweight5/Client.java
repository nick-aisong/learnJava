package cn.part02.ch28.flyweight5;

//代码清单28-15 场景类
public class Client {
    public static void main(String[] args) {
        //初始化对象池
        ExtrinsicState state1 = new ExtrinsicState();
        state1.setSubject("科目1");
        state1.setLocation("上海");
        SignInfoFactory.getSignInfo(state1);
        ExtrinsicState state2 = new ExtrinsicState();
        state2.setSubject("科目1");
        state2.setLocation("上海");
        //计算执行100万次需要的时间
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            SignInfoFactory.getSignInfo(state2);
        }
        long tailTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (tailTime - currentTime) + " ms");
    }
}

//执行时间：314 ms
