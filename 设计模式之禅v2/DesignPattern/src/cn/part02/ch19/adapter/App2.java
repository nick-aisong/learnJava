package cn.part02.ch19.adapter;

//代码清单19-7 查看劳动服务公司人员信息场景
public class App2 {

    public static void main(String[] args) {
        //老板一想不对呀，兔子不吃窝边草，还是找借用人员好点
        //我们只修改了这句话
        IUserInfo youngGirl = new OuterUserInfo();
        //从数据库中查到101个
        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }
    }
}
