package cn.part05.ch37.mvc;

import java.util.Observable;
import java.util.Observer;

//代码清单37-7 策略的场景类
public class Checker implements Observer {
    //使用哪一个策略
    private IXmlValidate validate;
    //xml配置文件的路径
    String xmlPath;

    //构造函数传递
    public Checker(IXmlValidate _validate) {
        this.validate = _validate;
    }

    //代码清单37-10 修正后的检查者
    public void update(Observable arg0, Object arg1) {
        //检查是否符合条件
        arg1 = check();
    }

    public void setXmlPath(String _xmlPath) {
        this.xmlPath = _xmlPath;
    }

    //检查
    public boolean check() {
        return validate.validate(xmlPath);
    }
}

/*
与通用策略模式稍有不同，每个模式在实际应用环境中都有其个性，很少出现完全照搬
一个模式的情况，灵活应用设计模式才是关键
*/