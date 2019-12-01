package cn.part04.ch36.observer_mediator;

//代码清单36-1 产品类
public class Product implements Cloneable {
    //产品名称
    private String name;
    //是否可以属性变更
    private boolean canChanged = false;

    //产生一个新的产品
    public Product(ProductManager manager, String _name) {
        //允许建立产品
        if (manager.isCreateProduct()) {
            canChanged = true;
            this.name = _name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (canChanged) {
            this.name = name;
        }
    }

    //覆写clone方法
    @Override
    public Product clone() {
        Product p = null;
        try {
            p = (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}

//在产品类中，我们只定义产品的一个属性：产品名称（name），并实现了getter/setter方
//法，然后我们实现了它的clone方法，确保对象是可以被拷贝的。还有一个特殊的地方是我们
//的构造函数，它怎么会要求传递进来一个工厂对象ProductManager呢？保留你的好奇心，马
//上为你揭晓答案