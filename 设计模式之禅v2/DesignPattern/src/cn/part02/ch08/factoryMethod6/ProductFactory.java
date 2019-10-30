package cn.part02.ch08.factoryMethod6;

import java.util.HashMap;
import java.util.Map;

//代码清单8-22 延迟加载的工厂类
public class ProductFactory {
    private static final Map<String, Product> prMap = new HashMap<>();

    public static synchronized Product createProduct(String type) throws Exception {
        Product product = null;
        //如果Map中已经有这个对象
        if (prMap.containsKey(type)) {
            product = prMap.get(type);
        } else {
            if (type.equals("Product1")) {
                product = new ConcreteProduct1();
            } else {
                product = new ConcreteProduct2();
            }
            //同时把对象放到缓存容器中
            prMap.put(type, product);
        }
        return product;
    }
}

//代码还比较简单，通过定义一个Map容器，容纳所有产生的对象
//如果在Map容器中已经有的对象，则直接取出返回
//如果没有，则根据需要的类型产生一个对象并放入到Map容器中，以方便下次调用