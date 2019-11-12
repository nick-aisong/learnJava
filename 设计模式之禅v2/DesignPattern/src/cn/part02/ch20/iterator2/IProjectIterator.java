package cn.part02.ch20.iterator2;

import java.util.Iterator;

//代码清单20-6 项目迭代器接口
public interface IProjectIterator extends Iterator {
}

// 大家可能对该接口感觉很奇怪，你定义的这个接口方法、变量都没有，有什么意义呢？
// 有意义，所有的Java书上都会说要面向接口编程，你的接口是对一个事物的描述，也就是说
// 我通过接口就知道这个事物有哪些方法，哪些属性，我们这里的IProjectIterator是要建立一个
// 指向Project类的迭代器，目前暂时定义的就是一个通用的迭代器，可能以后会增加
// IProjectIterator的一些属性或者方法。当然了，你也可以在实现类上实现两个接口，一个是
// Iterator,一个是IProjectIterator（这时候，这个接口就不用继承Iterator），杀猪杀尾巴，各有各
// 的杀法。我的习惯是：如果我要实现一个容器或者其他API提供接口时，我一般都自己先写
// 一个接口继承，然后再继承自己写的接口，保证自己的实现类只用实现自己写的接口（接口
// 传递，当然也要实现顶层的接口），程序阅读也清晰一些