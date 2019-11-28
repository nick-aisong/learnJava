package cn.part03.ch31.decoratorVSadaptor.decorator;

//代码清单31-10 外形美化
public class BeautifyAppearance extends Decorator {
    //要美化谁
    public BeautifyAppearance(Swan _swan) {
        super(_swan);
    }

    //外表美化处理
    public void desAppaearance() {
        System.out.println("外表是纯白色的，非常惹人喜爱！");
    }
}
