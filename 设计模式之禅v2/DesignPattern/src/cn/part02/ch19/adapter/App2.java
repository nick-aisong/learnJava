package cn.part02.ch19.adapter;

/**
 * Created by NKS on 2017/9/16.
 */
public class App2 {

    public static void main(String[] args) {
        IUserInfo youngGirl = new OuterUserInfo();
        for (int i = 0; i < 10; i++) {
            youngGirl.getMobileNumber();
        }
    }
}
