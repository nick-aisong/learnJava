package cn.part05.ch38.specification;

import java.util.ArrayList;

//代码清单38-2 用户操作对象接口
public interface IUserProvider {
    //根据用户名查找用户
    //public ArrayList<User> findUserByNameEqual(String name);

    //年龄大于指定年龄的用户
   // public ArrayList<User> findUserByAgeThan(int age);

    //代码清单38-5 修正后的接口s
    //根据条件查找用户
    //public ArrayList<User> findUser(boolean condition);

    //根据条件查找用户
    public ArrayList<User> findUser(IUserSpecification userSpec);
}

//在这里只定义了两个查询实现，分别是名字相同的用户和年龄大于指定年龄的用户，大
//家都知道，相似的查询条件还有很多，比如名字中包含指定字符、年龄小于指定年龄等，我
//们仅以实现这两个查询作为代表