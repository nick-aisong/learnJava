package cn.part02.ch07.multition;


public class Minister {
    public static void main(String[] args) {
        //定义5个大臣
        int ministerNum = 5;
        for (int i = 0; i < ministerNum; i++) {
            Emperor emperor = Emperor.getInstance();
            System.out.print("第" + (i + 1) + "个大臣参拜的是：");
            emperor.say();
        }
    }
}

//第1个大臣参拜的是：皇2帝
//第2个大臣参拜的是：皇1帝
//第3个大臣参拜的是：皇1帝
//第4个大臣参拜的是：皇2帝
//第5个大臣参拜的是：皇1帝
