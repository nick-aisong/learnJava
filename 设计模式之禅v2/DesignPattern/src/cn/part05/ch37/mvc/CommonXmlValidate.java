package cn.part05.ch37.mvc;

//代码清单37-5 普通XML校验
public class CommonXmlValidate implements IXmlValidate {
    //XML语法检查，比如是否少写了一个结束标志
    public boolean validate(String xmlPath) {
        return false;
    }
}

/*
由于读写XML文件一般使用DOM4J或者JDOM，都提供对XML文件的语法校验功能，不
符合XML语法（比如一个节点少写了结束标志</node）的文件是不能解析的，读者可以在自
己编写框架时使用该类型工具
*/