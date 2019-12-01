package cn.part05.ch38.specification;

//代码清单38-7 姓名相同的规格书
//public class UserByNameEqual implements IUserSpecification {
public class UserByNameEqual extends CompositeSpecification {
    //基准姓名
    private String name;

    //构造函数传递基准姓名
    public UserByNameEqual(String _name) {
        this.name = _name;
    }

    //检验用户是否满足条件
    public boolean isSatisfiedBy(User user) {
        return user.getName().equals(name);
    }
}