package cn.part02.ch17.decoratorImpl;

/**
 * Created by NKS on 2017/9/16.
 */
public class SortDecorator extends Decorator {
    public SortDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportSort(){
        System.out.println("我是排名第38名...");
    }

    public void report(){
        super.report();
        this.reportSort();
    }
}
