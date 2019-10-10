package cn.nks.singleton;

/**
 * Created by NKS on 2017/9/12.
 */
public class Emperor {

    private static Emperor emperor = null;

    private Emperor(){
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }

    public static Emperor getInstance(){
        if (emperor == null){
            emperor = new Emperor();
        }
        return emperor;
    }

    //皇帝叫什么名字呀
    public static void emperorInfo(){
        System.out.println("我就是皇帝某某某....");
    }
}
