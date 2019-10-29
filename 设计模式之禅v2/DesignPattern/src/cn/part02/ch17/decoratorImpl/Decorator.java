package cn.part02.ch17.decoratorImpl;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class Decorator extends SchoolReport {

    private SchoolReport sr;

    public Decorator(SchoolReport sr){
        this.sr = sr;
    }

    public void report(){
        this.sr.report();
    }

    public void sign(String name){
        this.sr.sign(name);
    }

}
