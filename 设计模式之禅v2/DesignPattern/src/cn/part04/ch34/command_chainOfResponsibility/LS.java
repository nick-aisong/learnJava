package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-3 ls命令
public class LS extends AbstractLS {
    //最简单的ls命令
    protected String echo(CommandVO vo) {
        return FileManager.ls(vo.formatData());
    }

    //参数为空
    protected String getOperateParam() {
        return super.DEFAULT_PARAM;
    }
}
