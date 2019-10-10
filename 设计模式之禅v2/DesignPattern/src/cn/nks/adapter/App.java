package cn.nks.adapter;

/**
 * Created by NKS on 2017/9/16.
 */
public class App {

    public static void main(String[] args){
        IUserInfo youngGirl = new UserInfo();
        //从数据库中查到100个
        for (int i=0; i<100; i++){
            youngGirl.getMobileNumber();
        }
    }
}
