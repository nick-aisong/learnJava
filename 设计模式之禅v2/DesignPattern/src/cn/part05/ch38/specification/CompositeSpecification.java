package cn.part05.ch38.specification;

//代码清单38-16 组合规格书
public abstract class CompositeSpecification implements IUserSpecification {
    //是否满足条件由实现类实现
    public abstract boolean isSatisfiedBy(User user);

    //and操作
    public IUserSpecification and(IUserSpecification spec) {
        return new AndSpecification(this, spec);
    }

    //not操作
    public IUserSpecification not() {
        return new NotSpecification(this);
    }

    //or操作
    public IUserSpecification or(IUserSpecification spec) {
        return new OrSpecification(this, spec);
    }
}