package cn.part05.ch38.specification;

//代码清单38-6 规格书接口
public interface IUserSpecification {
    //候选者是否满足要求
    public boolean isSatisfiedBy(User user);
    //and操作
    public IUserSpecification and(IUserSpecification spec);
    //or操作
    public IUserSpecification or(IUserSpecification spec);
    //not操作
    public IUserSpecification not();
}
