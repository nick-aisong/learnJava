package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-17 可执行的df命令
public class DFCommand extends Command {

    public String execute(CommandVO vo) {
        return super.buildChain(AbstractDF.class).get(0).handleMessage(vo);
    }
}