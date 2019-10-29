package cn.part02.ch15.commandImpl;

/**
 * Created by NKS on 2017/9/16.
 */
public class DeletePageCommand extends Command {
    @Override
    public void execute() {
        super.pg.find();
        super.pg.delete();
        super.pg.plan();
    }
}
