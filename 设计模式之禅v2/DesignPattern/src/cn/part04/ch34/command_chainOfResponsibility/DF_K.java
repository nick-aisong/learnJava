package cn.part04.ch34.command_chainOfResponsibility;

public class DF_K extends AbstractDF {
    //定义一下自己能处理什么参数
    protected String getOperateParam() {
        return super.K_PARAM;
    }

    //命令处理
    protected String echo(CommandVO vo) {
        return DiskManager.df_k();
    }
}