package cn.part03.ch31.decoratorVSadaptor.decorator;

//代码清单31-12 场景类
public class Client {
    public static void main(String[] args) {
        //很久很久以前，这里有一个丑陋的小鸭子
        System.out.println("===很久很久以前，这里有一只丑陋的小鸭子===");
        Swan duckling = new UglyDuckling();
        //展示一下小鸭子
        duckling.desAppaearance(); //小鸭子的外形
        duckling.cry(); //小鸭子的叫声
        duckling.fly(); //小鸭子的行为
        System.out.println("\n===小鸭子终于发现自己是一只天鹅====");
        //首先外形变化了
        duckling = new BeautifyAppearance(duckling);
        //其次行为也发生了改变
        duckling = new StrongBehavior(duckling);
        //虽然还是叫丑小鸭，但是已经发生了很大变化
        duckling.desAppaearance(); //小鸭子的新外形
        duckling.cry(); //小鸭子的新叫声
        duckling.fly(); //小鸭子的新行为
    }
}

//===很久很久以前，这里有一只丑陋的小鸭子===
//外形是脏兮兮的白色，毛茸茸的大脑袋
//叫声是克噜——克噜——克噜
//不能飞行
//
//===小鸭子终于发现自己是一只天鹅====
//外表是纯白色的，非常惹人喜爱！
//叫声是克噜——克噜——克噜
//会飞行了！