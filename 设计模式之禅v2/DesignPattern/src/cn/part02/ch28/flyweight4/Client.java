package cn.part02.ch28.flyweight4;

//代码清单28-12 场景类
public class Client {
    public static void main(String[] args) {
        //在对象池中初始化4个对象
        SignInfoFactory.getSignInfo("科目1");
        SignInfoFactory.getSignInfo("科目2");
        SignInfoFactory.getSignInfo("科目3");
        SignInfoFactory.getSignInfo("科目4");
        //取得对象
        SignInfo signInfo = SignInfoFactory.getSignInfo("科目2");
        while (true) {
            signInfo.setId("ZhangSan");
            signInfo.setLocation("ZhangSan");
            (new MultiThread(signInfo)).start();
            signInfo.setId("LiSi");
            signInfo.setLocation("LiSi");
            (new MultiThread(signInfo)).start();
        }
    }
}

//编号：ZhangSan
//考试地址：LiSi
//线程不安全了！
//编号：LiSi
//考试地址：ZhangSan
//线程不安全了！
//编号：LiSi
//考试地址：ZhangSan
//线程不安全了！
//编号：LiSi
//考试地址：ZhangSan
//线程不安全了！
//编号：ZhangSan
//考试地址：ZhangSan
//线程不安全了！
//编号：LiSi
//考试地址：LiSi
//线程不安全了！
//......