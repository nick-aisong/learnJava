package cn.part03.ch33.wrappingPattern.proxy;

//代码清单33-30 明星
public class Singer implements IStar {
    public void sign() {
        System.out.println("明星签字：我是XXX大明星");
    }
}
