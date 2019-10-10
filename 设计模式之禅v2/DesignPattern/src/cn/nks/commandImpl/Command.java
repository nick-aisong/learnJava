package cn.nks.commandImpl;

import cn.nks.commandImpl.receiver.CodeGroup;
import cn.nks.commandImpl.receiver.PageGroup;
import cn.nks.commandImpl.receiver.RequirementGroup;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class Command {

    protected RequirementGroup rg = new RequirementGroup();
    protected PageGroup pg = new PageGroup();
    protected CodeGroup cg = new CodeGroup();

    public abstract void execute();
}
