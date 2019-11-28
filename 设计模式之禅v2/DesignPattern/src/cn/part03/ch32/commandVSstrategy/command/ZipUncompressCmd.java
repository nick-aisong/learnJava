package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-8 zip解压缩命令
public class ZipUncompressCmd extends AbstractCmd {
    public boolean execute(String source, String to) {
        return super.zip.uncompress(source, to);
    }
}