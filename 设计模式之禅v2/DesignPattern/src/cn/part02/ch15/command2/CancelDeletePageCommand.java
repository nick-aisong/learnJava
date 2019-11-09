package cn.part02.ch15.command2;

//代码清单15-21 撤销命令
public class CancelDeletePageCommand extends Command {
    //撤销删除一个页面的命令
    public void execute() {
        super.pg.rollBack();
    }
}
