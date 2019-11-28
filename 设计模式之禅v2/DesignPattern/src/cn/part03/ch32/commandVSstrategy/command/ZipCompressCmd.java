package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-7 zip压缩命令
public class ZipCompressCmd extends AbstractCmd {
    public boolean execute(String source, String to) {
        return super.zip.compress(source, to);
    }
}

//========执行压缩命令========
//c:\windows --> d:\windows.zip ZIP压缩成功!
