package cn.part05.ch38.specification;

import java.util.ArrayList;

//代码清单38-3 用户操作类
public class UserProvider implements IUserProvider {
//    //用户列表
//    private ArrayList<User> userList;
//
//    //构造函数传递用户列表
//    public UserProvider(ArrayList<User> _userList) {
//        this.userList = _userList;
//    }
//
//    //年龄大于指定年龄的用户
//    public ArrayList<User> findUserByAgeThan(int age) {
//        ArrayList<User> result = new ArrayList<User>();
//        for (User u : userList) {
//            if (u.getAge() > age) { //符合条件的用户
//                result.add(u);
//            }
//        }
//        return result;
//    }
//
//    //姓名等于指定姓名的用户
//    public ArrayList<User> findUserByNameEqual(String name) {
//        ArrayList<User> result = new ArrayList<User>();
//        for (User u : userList) {
//            if (u.getName().equals(name)) {//符合条件
//                result.add(u);
//            }
//        }
//        return result;
//    }

    //用户列表
    private ArrayList<User> userList;
    //传递用户列表
    public UserProvider(ArrayList<User> _userList){
        this.userList = _userList;
    }
    //根据指定的规格书查找用户
    public ArrayList<User> findUser(IUserSpecification userSpec) {
        ArrayList<User> result = new ArrayList<User>();
        for(User u:userList){
            if(userSpec.isSatisfiedBy(u)){//符合指定规格
                result.add(u);
            }
        }
        return result;
    }
}