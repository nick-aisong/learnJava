package cn.ch05.ch05_1;

import java.util.List;
import java.util.concurrent.RecursiveAction;

// 8.创建一个名为Task的类，并继承RecursiveAction类
public class Task extends RecursiveAction {
    // 9.声明这个类的serialVersionUID属性
    // 这个元素是必需的，因为RecursiveAction的父类ForkJoinTask实现了Serializable接口
    private static final long serialVersionUID = 1L;
    // 10.声明一个名为products私有的List<Product>属性
    private List<Product> products;
    // 11.声明两个私有的int属性，分别命名为first和last。 这两个属性将决定任务执行时对产品的分块
    private int first;
    private int last;
    // 12.声明一个名为increment的私有double属性，用来存储产品价格的增加额
    private double increment;

    // 13.实现类的构造器，用来初始化类的这些属性
    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    // 14.实现compute()方法，实现任务的执行逻辑
    @Override
    protected void compute() {
        // 15.如果last和first属性值的差异小于10(一个任务只能更新少于10件产品的价格)
        // 则调用updatePrices()方法增加这些产品的价格
        if (last - first < 10) {
            updatePrice();
            // 16.如果last和first属性值的差异大于或等于10，就创建两个新的Task对象
            // 一个处理前一半的产品，另一个处理后一半的产品
            // 然后调用ForkJoinPool的invokeAll()方法来执行这两个新的任务
        } else {
            int middle = (first + last) / 2;
            System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
            Task t1 = new Task(products, first, middle + 1, increment);
            Task t2 = new Task(products, middle + 1, last, increment);
            invokeAll(t1, t2);
        }
    }

    // 17.实现updatePrices()方法。这个方法用来更新在产品列表中处于first和last属性之间的产品
    private void updatePrice() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }
}
