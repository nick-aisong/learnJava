package cn.part02.ch28.flyweight5;

//代码清单28-16 场景类
public class Client2 {
    public static void main(String[] args) {
        String key1 = "科目1上海";
        String key2 = "科目1上海";
        //初始化对象池
        SignInfoFactory.getSignInfo(key1);
        //计算执行10万次需要的时间
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            SignInfoFactory.getSignInfo(key2);
        }
        long tailTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (tailTime - currentTime) + " ms");
    }
}

//执行时间：105 ms