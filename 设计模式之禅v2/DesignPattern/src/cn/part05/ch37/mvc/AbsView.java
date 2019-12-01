package cn.part05.ch37.mvc;

//代码清单37-22 抽象视图
public abstract class AbsView {
    private AbsLangData langData;

    //必须有一个语言文件
    public AbsView(AbsLangData _langData) {
        this.langData = _langData;
    }

    //获得当前的语言
    public AbsLangData getLangData() {
        return langData;
    }

    //页面的URL路径
    public String getURI() {
        return null;
    }

    //组装一个页面
    public abstract void assemble();
}
