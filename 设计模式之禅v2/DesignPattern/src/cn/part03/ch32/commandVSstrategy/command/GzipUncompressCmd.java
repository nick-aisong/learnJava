package cn.part03.ch32.commandVSstrategy.command;

//代码清单32-10 gzip解压缩命令
public class GzipUncompressCmd extends AbstractCmd {
    public boolean execute(String source, String to) {
        return super.gzip.uncompress(source, to);
    }
}