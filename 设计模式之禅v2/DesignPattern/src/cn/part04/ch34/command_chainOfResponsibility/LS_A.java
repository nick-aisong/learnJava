package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-5 ls-a命令
public class LS_A extends AbstractLS {
    //ls -a命令
    protected String echo(CommandVO vo) {
        return FileManager.ls_a(vo.formatData());
    }

    protected String getOperateParam() {
        return super.A_PARAM;
    }
}