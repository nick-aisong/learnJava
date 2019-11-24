package cn.part02.ch28.flyweight;

//代码清单28-2 报考信息工厂
public class SignInfoFactory {
    //报名信息的对象工厂
    public static SignInfo getSignInfo() {
        return new SignInfo();
    }
}

//工厂类就这么简单？非也，这是我们的教学代码，真实的ObjectFactory要复杂得多，主要是注入了部分Handler的管理