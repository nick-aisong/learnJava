package cn.part05.ch37.mvc;

import java.util.Map;

//代码清单37-19 抽象语言
public abstract class AbsLangData {
    //获得所有的动态元素的配置项
    public abstract Map<String, String> getItems();
}