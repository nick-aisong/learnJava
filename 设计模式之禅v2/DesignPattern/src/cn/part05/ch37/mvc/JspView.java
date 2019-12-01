package cn.part05.ch37.mvc;

import java.util.Map;

//代码清单37-23 JSP视图
public class JspView extends AbsView {
    //传递语言配置
    public JspView(AbsLangData _langData) {
        super(_langData);
    }

    @Override
    public void assemble() {
        Map<String, String> langMap = getLangData().getItems();
        for (String key : langMap.keySet()) {
        /*
        * 直接替换文件中的语言条目
        *
        */
        }
    }
}