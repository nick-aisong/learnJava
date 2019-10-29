package cn.part02.ch12.proxy;

/*
* 水浒里是这样写的：西门庆被潘金莲用竹竿敲了一下难道，痴迷了，
* 被王婆看到了, 就开始撮合两人好事，王婆作为潘金莲的代理人
* 收了不少好处费，那我们假设一下：
* 如果没有王婆在中间牵线，这两个不要脸的能成吗？难说的很！
*/
public class XiMenQing {

    public static void main(String[] args) {
        //把王婆叫出来
        WangPo wangPo = new WangPo();

        wangPo.makeEyesWithMan();
        wangPo.happyWithMan();

        System.out.println("-----改编历史-----");

        //改编一下历史，贾氏被西门庆勾走：
        JiaShi jiaShi = new JiaShi();
        WangPo wangPo1 = new WangPo(jiaShi);
        wangPo1.makeEyesWithMan();
        wangPo1.happyWithMan();

    }

}
