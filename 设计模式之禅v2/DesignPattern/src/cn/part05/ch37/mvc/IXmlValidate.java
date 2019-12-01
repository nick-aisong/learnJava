package cn.part05.ch37.mvc;

//代码清单37-4 XML文件校验
public interface IXmlValidate {
    //只有一个方法，检查XML是否符合条件
    public boolean validate(String xmlPath);
}
