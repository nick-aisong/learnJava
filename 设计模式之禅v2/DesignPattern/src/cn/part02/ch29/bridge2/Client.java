package cn.part02.ch29.bridge2;

//代码清单29-6 场景类
public class Client {
    public static void main(String[] args) {
        System.out.println("-------房地产公司是按这样运行的-------");
        //先找到我的公司
        HouseCorp houseCorp = new HouseCorp();
        //看我怎么挣钱
        houseCorp.makeMoney();
        System.out.println("\n");
        System.out.println("-------山寨公司是按这样运行的-------");
        IPodCorp iPodCorp = new IPodCorp();
        iPodCorp.makeMoney();
    }
}

//-------房地产公司是按这样运行的-------
//房地产公司盖房子...
//房地产公司出售房子...
//房地产公司赚大钱了...
//
//
//-------山寨公司是按这样运行的-------
//我生产iPod...
//iPod畅销...
//我赚钱呀...
