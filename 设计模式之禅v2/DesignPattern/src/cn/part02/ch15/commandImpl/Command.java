package cn.part02.ch15.commandImpl;

import cn.part02.ch15.commandImpl.receiver.CodeGroup;
import cn.part02.ch15.commandImpl.receiver.PageGroup;
import cn.part02.ch15.commandImpl.receiver.RequirementGroup;

/**
 * Created by NKS on 2017/9/16.
 */
public abstract class Command {

    protected RequirementGroup rg = new RequirementGroup();
    protected PageGroup pg = new PageGroup();
    protected CodeGroup cg = new CodeGroup();

    public abstract void execute();
}
