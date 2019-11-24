package cn.part02.ch29.bridge;

public class Client {
    public static void main(String[] args) {
        System.out.println("-------房地产公司是这样运行的-------");
        //先找到我的公司
        HouseCorp houseCorp = new HouseCorp();
        //看我怎么挣钱
        houseCorp.makeMoney();
        System.out.println("\n");
        System.out.println("-------服装公司是这样运行的-------");
        ClothesCorp clothesCorp = new ClothesCorp();
        clothesCorp.makeMoney();
    }
}

//-------房地产公司是这样运行的-------
//房地产公司盖房子...
//房地产公司出售房子...
//房地产公司赚大钱了...
//
//
//-------服装公司是这样运行的-------
//服装公司生产衣服...
//服装公司出售衣服...
//服装公司赚小钱...