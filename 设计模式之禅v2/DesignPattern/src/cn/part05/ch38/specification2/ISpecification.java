package cn.part05.ch38.specification2;

public interface ISpecification {
    //候选者是否满足要求
    public boolean isSatisfiedBy(Object candidate);

    //and操作
    public ISpecification and(ISpecification spec);

    //or操作
    public ISpecification or(ISpecification spec);

    //not操作
    public ISpecification not();
}
