package cn.part04.ch36.observer_mediator;

//代码清单36-2 工厂类
public class ProductManager {
    //是否可以创建一个产品
    private boolean isPermittedCreate = false;

    //建立一个产品
    public Product createProduct(String name) {
        //首先修改权限，允许创建
        isPermittedCreate = true;
        Product p = new Product(this, name);
        //产生一个创建事件
        new ProductEvent(p, ProductEventType.NEW_PRODUCT);
        return p;
    }

    //废弃一个产品
    public void abandonProduct(Product p) {
        //销毁一个产品，例如删除数据库记录
        //产生删除事件
        new ProductEvent(p, ProductEventType.DEL_PRODUCT);
        p = null;
    }

    //修改一个产品
    public void editProduct(Product p, String name) {
        //修改后的产品
        p.setName(name);
        //产生修改事件
        new ProductEvent(p, ProductEventType.EDIT_PRODUCT);
    }

    //获得是否可以创建一个产品
    public boolean isCreateProduct() {
        return isPermittedCreate;
    }

    //克隆一个产品
    public Product clone(Product p) {
        //产生克隆事件
        new ProductEvent(p, ProductEventType.CLONE_PRODUCT);
        return p.clone();
    }
}

/*
仔细看看工厂类，产品的创建、修改、遗弃、克隆方法都很简单，但有一个方法可不简
单——isCreateProduct方法，它的作用是告诉产品类“我是能创建产品的”，注意看我们的程
序，在工厂类ProductManager中定义了一个私有变量isCreateProduct，该变量只有在工厂类的
createProduct函数中才能设置为true，在创建产品的时候，产品类Product的构造函数要求传递
工厂对象，然后判断是否能够创建产品，即使你想使用类似这样的方法：
Product p = new Product(new ProductManager(),"abc");
也是不可能创建出产品的，它在产品类中限制必须是当前有效工厂才能生产该产品，而
且也只有有效的工厂才能修改产品，看看产品类的canChanged属性，只有它为true时，产品
才可以修改，那怎么才能为true呢？在构造函数中判断是否可以为true。这就类似工厂要创建
产品了，产品就问“你有权利创建我吗？”于是工厂类出示了两个证明材料证明自己可以创建
产品：一是“我是你的工厂类”，二是“我的isCreateProduct返回true，我有权创建”，于是产品
就被创建出来了。这种一个对象只能由固定的对象初始化的方法就叫做单来源调用（Single
Call）——很简单，但非常有用的方法
 */

/*
在每个方法中增加了事件的产生机制，在createProduct方法中增加了创建产品事件，在
editProduct方法中增加了修改产品事件，在delProduct方法中增加了遗弃产品事件，在clone方
法中增加克隆产品事件，而且每个事件都是通过组合产生的，产品和事件的扩展性非常优秀
 */