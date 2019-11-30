package cn.part04.ch34.command_chainOfResponsibility;

//代码清单34-15 df命令的具体实现类
public class DF extends AbstractDF {
    //定义一下自己能处理什么参数
    protected String getOperateParam() {
        return super.DEFAULT_PARAM;
    }

    //命令处理
    protected String echo(CommandVO vo) {
        return DiskManager.df();
    }
}

/*

#ls
file1
file2
file3
file4
#df
/ 10485760
/usr 104857600
/home 1048576000
#df -k
/ 10240
/usr 102400
/home t10240000
#df -g
/ 10
/usr 100
/home t10000
#

*/