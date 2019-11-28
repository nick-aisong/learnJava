package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-18 场景类
public class Client {
    public static void main(String[] args) {
        //定义一个命令,压缩一个文件
        AbstractCmd cmd = new ZipCompressCmd();
        /*
        * 想换一个？执行解压命令
        * AbstractCmd cmd = new ZipUncompressCmd();
        */
        //定义调用者
        Invoker invoker = new Invoker(cmd);
        //我命令你对这个文件进行压缩
        System.out.println("========执行压缩命令========");
        invoker.execute("c:\\windows", "d:\\windows.zip");
    }
}