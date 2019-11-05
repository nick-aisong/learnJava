package cn.part02.ch20.iteratorImpl;

/**
 * Created by NKS on 2017/9/16.
 */
public interface IProject {

    //增加项目
    public void add(String name, int num, int cost);

    //从老板这里看到的就是项目信息
    public String getProjectInfo();

    //获得一个可以被遍历的对象
    public IProjectIterator iterator();
}
