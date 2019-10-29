package cn.part02.ch15.commandImpl;

/**
 * Created by NKS on 2017/9/16.
 */
public class AddRequirementCommand extends Command {

    @Override
    public void execute() {
        super.rg.find();
        super.rg.add();
        super.rg.plan();
    }
}
