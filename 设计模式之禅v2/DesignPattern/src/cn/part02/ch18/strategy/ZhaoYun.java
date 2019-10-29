package cn.part02.ch18.strategy;


public class ZhaoYun {

    /**
     * 赵云出场了，他根据诸葛亮给他的交代，依次拆开妙计
     */

    public static void main(String[] args) {
        Context context;

        System.out.println("-----------刚刚到吴国的时候拆第一个-------------");
        context = new Context(new BackDoor());
        context.operate();
        System.out.println("\n\n\n\n\n\n\n\n");

        System.out.println("-----------刘备乐不思蜀了，拆第二个了-------------");
        context = new Context(new GivenGreenLight());
        context.operate();
        System.out.println("\n\n\n\n\n\n\n\n");

        System.out.println("-----------孙权的小兵追了，咋办？拆第三个-------------");
        context = new Context(new BlockEnemy());
        context.operate();
        System.out.println("\n\n\n\n\n\n\n\n");
    }


}
