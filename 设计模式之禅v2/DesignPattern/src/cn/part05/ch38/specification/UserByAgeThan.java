package cn.part05.ch38.specification;

//代码清单38-8 大于基准年龄的规格书
//public class UserByAgeThan implements IUserSpecification {
public class UserByAgeThan extends CompositeSpecification {
    //基准年龄
    private int age;

    //构造函数传递基准年龄
    public UserByAgeThan(int _age) {
        this.age = _age;
    }

    //检验用户是否满足条件
    public boolean isSatisfiedBy(User user) {
        return user.getAge() > age;
    }
}