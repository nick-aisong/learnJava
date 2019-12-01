package cn.part05.ch37.mvc;

//代码清单37-29 访问者
public class ViewPathVisitor extends VisitorSupport {
    //获得指定的路径
    private String viewPath;
    private String result;

    //传递模型结果
    public ViewPathVisitor(String _result) {
        result = _result;
    }

    @Override
    public void visit(Element el) {
        Attribute att = el.attribute("name");
        if (att != null) {
            if (att.getName().equals("name") && att.getText().equals(result)) {
                viewPath = el.getText();
            }
        }
    }

    public String getViewPath() {
        return viewPath;
    }
}
