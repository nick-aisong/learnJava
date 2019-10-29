package cn.part02.ch29.bridge;

/**
 * Created by NKS on 2017/9/16.
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("-------房地产公司是这个样子运行的-------");
        HouseCorp houseCorp = new HouseCorp();
        houseCorp.makeMoney();

        System.out.println("\n");

        System.out.println("-------服装公司是这样运行的-------");
        ClothesCorp clothesCorp = new ClothesCorp();
        clothesCorp.makeMoney();
    }
}
