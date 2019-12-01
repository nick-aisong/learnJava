package cn.part05.ch38.specification;

//代码清单38-18 或规格书
public class OrSpecification extends CompositeSpecification {
    //左右两个规格书
    private IUserSpecification left;
    private IUserSpecification right;

    public OrSpecification(IUserSpecification _left, IUserSpecification _right) {
        this.left = _left;
        this.right = _right;
    }

    //or运算
    @Override
    public boolean isSatisfiedBy(User user) {
        return left.isSatisfiedBy(user) || right.isSatisfiedBy(user);
    }
}