package cn.ch05.ch05_1;

import java.util.ArrayList;
import java.util.List;

// 4.创建一个名为ProductListGenerator的类，用来生成一个随机产品列表
public class ProductListGenerator {
	// 5.实现generate()方法。接收一个表示列表大小的int参数，并返回一个生成产品的List<Product>列表
	public List<Product> generate(int size) {
		// 6.创建返回产品列表的对象ret
		List<Product> ret = new ArrayList<>();
		// 7.生成产品列表，给所有的产品分配相同的价格，比如可以检查程序是否运行良好的数字10
		for (int i = 0; i < size; i++) {
			Product product = new Product();
			product.setName("Product " + i);
			product.setPrice(10);
			ret.add(product);
		}
		return ret;
	}
}
