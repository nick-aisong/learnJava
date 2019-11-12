package cn.part02.ch20.iterator2;

//代码清单20-4 项目信息接口
public interface IProject {

    //增加项目
    public void add(String name, int num, int cost);

    //从老板这里看到的就是项目信息
    public String getProjectInfo();

    //获得一个可以被遍历的对象
    public IProjectIterator iterator();
}
