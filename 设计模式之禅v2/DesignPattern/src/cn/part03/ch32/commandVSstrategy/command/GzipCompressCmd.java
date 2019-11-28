package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-9 gzip压缩命令
public class GzipCompressCmd extends AbstractCmd {
    public boolean execute(String source, String to) {
        return super.gzip.compress(source, to);
    }
}