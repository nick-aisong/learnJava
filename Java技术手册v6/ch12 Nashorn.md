#### 第 12 章 Nashorn

甲骨文在 Java 8 中加入了一个运行在 JVM 中的 JavaScript 新实现——Nashorn。Nashorn 的目的是取代前一个运行在 JVM 中的 JavaScript 项目，即 Rhino（Nashorn 是德文中的 “rhino”）。

>  1：rhino 的意思是犀牛。

Nashorn 是完全重写的实现，争取做到能和 Java 轻易互通，获得高性能，并且严格遵守 JavaScript ECMA 规范。Nashorn 是首个 100% 遵守规范的 JavaScript 实现，而且在多数应用场合至少比 Rhino 快 20 倍。

##### 12.1 介绍 Nashorn

本章假定你对 JavaScript 有一定的了解。如果你还不熟悉 JavaScript 的基本概念，Michael Morrison 写的 Head First JavaScript（O’Reilly 出版）是本不错的入门书。

回忆一下 1.5.4 节介绍的 Java 和 JavaScript 之间的区别，你会发现，这两种语言十分不同。因此，能在 Java 的虚拟机中运行 JavaScript 看起来有点奇怪。

###### 12.1.1 在 JVM 中运行 Java 之外的语言

其实，除了 Java 之外有相当多的语言都运行在 JVM 中，而且其中有些与 Java 的差异大于 JavaScript 与 Java 的差异。之所以能在 JVM 中运行其他语言，是因为 Java 语言和 JVM 耦合得非常松，两者之间只通过特定格式的类文件交互。在 JVM 中运行其他语言有两种方式。

- 目标语言使用 Java 实现的解释器。

解释器在 JVM 中运行，执行使用目标语言编写的程序。

- 目标语言提供编译器，把目标语言代码转换成类文件。

编译得到的类文件直接在 JVM 中执行，通常还会提供一些语言专用的运行时功能。

Nashorn 采用的是第二种方式，不过做了改进，把编译器嵌入运行时，所以，执行程序前绝不会先编译 JavaScript 源代码。这意味着，不是专为 Nashorn 编写的 JavaScript 代码也能轻易部署到这个平台中。

> Nashorn 和很多其他运行在 JVM 中的语言（例如 JRuby）有个区别，它没有实现任何解释器。Nashorn 总是把 JavaScript 代码编译成 JVM 字节码，然后直接执行字节码。

从技术的角度来看，这种实现方式很有趣。不过很多开发者好奇的是，Nashorn 在已经建立好的 Java 成熟生态系统中扮演着怎样的角色。下面就来看看 Nashorn 扮演的角色。

###### 12.1.2 目的

在 Java 和 JVM 生态系统中，Nashorn 有多种用途。首先，它为 JavaScript 开发者提供了一个可用的环境，用于探索 JVM 的功能。其次，它让企业继续利用对 Java 技术的现有投资，采用 JavaScript 作为一门开发语言。最后，它为 HotSpot 使用的先进虚拟机技术提供了一个很好的工程样板。

JavaScript 不断发展，应用范围越来越宽，以前只能在浏览器中使用，而现在则能在更通用的计算和服务器端使用。Nashorn 在稳固的 Java 现有生态系统和一波有前途的新技术之间架起了一座桥梁。

下面我们要介绍 Nashorn 运作的技术细节，以及如何开始使用这个平台。在 Nashorn 中运行 JavaScript 代码有多种不同的方式，下一节会介绍其中最常使用的两种。

##### 12.2 在 Nashorn 中执行 JavaScript 代码

本节介绍 Nashorn 环境，还会讨论两种执行 JavaScript 代码的方式（这两种方式使用的工具都在 $JAVA_HOME 的子目录 bin 中）。

jrunscript

这是一个简单的脚本运行程序，执行 .js 格式的 JavaScript 文件。

jjs

这是一个功能更完整的 shell，既能运行脚本，也能作为交互式读取 - 求值 - 输出循环
（Read-Eval-Print-Loop，REPL）环境使用，用于探索 Nashorn 及其功能。

我们先介绍基本的运行程序 jrunscript，它适用于大多数简单的 JavaScript 应用。

###### 12.2.1 在命令行中运行

若想在 Nashorn 中执行名为 my_script.js 的 JavaScript 文件，使用 jrunscript 命令即可：
```
jrunscript my_script.js
```
除了 Nashorn，jrunscript 还能使用其他脚本引擎（12.3 节会详细介绍脚本引擎）。如果需要使用其他引擎，可以通过 -l 选项指定：
```
jrunscript –l nashorn my_script.js
```
> 如果有合适的脚本引擎，使用这个选项还能让 jrunscript 运行使用其他语言编写的脚本。

这个基本的运行程序特别适合在简单的应用场景中使用，不过它有一定的局限性，因此，在重要场合下，我们需要使用功能更强的执行环境——jjs，也就是 Nashorn shell。

###### 12.2.2 使用 Nashorn shell

启动 Nashorn shell 的命令是 jjs。Nashorn shell 既可以交互式使用，也可以非交互式使用，能直接替代 jrunscript。

最简单的 JavaScript 示例当然是经典的“Hello World”，我们看一下如何在交互式 shell 中编写这个示例：
```
$ jjs
jjs> print("Hello World!");
Hello World!
jjs>
```
在 shell 中可以轻易处理 Nashorn 和 Java 之间的相互操作，12.4.1 节会详细介绍，不过现在先举个例子。若想在 JavaScript 中直接访问 Java 类和方法，使用完全限定的类名即可。下面这个实例获取 Java 原生的正则表达式功能：
```
jjs> var pattern = java.util.regex.Pattern.compile("\\d+");
jjs> var myNums = pattern.split("a1b2c3d4e5f6");

jjs> print(myNums);
[Ljava.lang.String;@10b48321

jjs> print(myNums[0]);
a
```

> 在 REPL 中 打 印 JavaScript 变 量 myNums 时， 得 到 的 结 果 是 [Ljava.lang.String;@10b48321。这表明，虽然 myNums 是 JavaScript 代码中的变量，但其实它是 Java 中的字符串数组。

稍后会详细说明 Nashorn 和 Java 之间的相互操作，现在先介绍 jjs 的其他功能。jjs 命令的通用格式是：
```
jjs [<options>] <files> [-- <arguments>]
```
能传给 jjs 的选项有很多，其中最常使用的如下所示。

- -cp 或 -classpath：指定在哪个位置寻找额外的 Java 类（稍后会发现，通过 Java.type 机制实现）。
- -doe 或 -dump-on-error：如果强制退出 Nashorn，转储完整的错误信息。
- -J：把选项传给 JVM。例如，如果想增加 JVM 可用的最大内存，可以这么做：
```
$ jjs -J-Xmx4g
jjs> java.lang.Runtime.getRuntime().maxMemory()
3817799680
```
- -strict：在 JavaScript 严格模式中执行所有脚本和函数。这是 ECMAScript 5 引入的 JavaScript 特性，目的是减少缺陷和错误。所有新编写的 JavaScript 代码都推荐使用严格模式，如果你对这个特性还不了解，应该找些资料看看。
- -D：让开发者把键值对表示的系统属性传给 Nashorn，这和 JVM 的通常做法一样。例如：
```
$ jjs –DmyKey=myValue
jjs> java.lang.System.getProperty("myKey");
myValue
```
- -v 或 -version：打印标准的 Nashorn 版本字符串。
- -fv 或 -fullversion：打印完整的 Nashorn 版本字符串。
- -fx：把脚本当成 JavaFX GUI 应用执行。JavaFX 程序员使用 Nashorn 可以少编写一些样板代码。
- -h：显示帮助信息。
- -scripting：启用 Nashorn 专用的脚本扩展。下一节会介绍这个功能。

> 注 2：JavaFX 是开发 GUI 应用的标准 Java 技术，不过超出了本书范畴。

###### 12.2.3 在 jjs 中编写脚本

jjs shell 可用于测试一些基本的 JavaScript 代码，或者使用交互式方式试验不熟悉的 JavaScript 包（例如，学习使用包时）。不过，jjs 有个限制，不能输入多行代码，也没提供其他大量使用 REPL 的语言常用的高级功能。

其实，jjs 非常适合在非交互式场合下使用，例如启动使用 JavaScript 编写的守护进程。对于这种情况，可以使用下述方式调用 jjs：
```
$ jjs -scripting my_script.js
```
这样调用，可以使用增强的 jjs 功能，其中包含一些有用的扩展。很多扩展都能让脚本程序员通过更熟悉的方式使用 Nashorn。

1. 脚本中的注释

在传统的 Unix 脚本中，# 符号表示注释，一直到行尾结束。而 JavaScript 使用 C/C++ 风格的注释，使用 // 表示注释，一直到行尾结束。Nashorn 支持这种注释方式，不过在脚本模式中也能使用 Unix 脚本的注释方式，因此，下述代码完全合法：
```
#!/usr/bin/jjs

# 在脚本模式中，这样写注释完全合法

print("After the comment");
```

2. 行内执行命令

资深 Unix 程序员通常把这个功能称为“反引号”。在 bash 脚本中，我们可以编写如下的代
码，使用 Unix 的 curl 命令下载谷歌首页的内容：
```
echo "Google says: " `curl http://www.google.co.uk`
```
类似地，在 Nashorn 脚本中，我们也可以使用反引号（`）执行 Unix shell 命令。如下所示：
```
print("Google says: "+ `curl http://www.google.co.uk`);
```

3. 字符串插值

字符串插值是一种特殊的句法，无需连接字符串，就能直接插入变量的内容。在 Nashorn 脚本中，我们可以使用 `${<variable name>}` 把变量的值插入字符串。例如，前面下载网页内容的示例，使用插值后可以改写成：
```
var url = "www.google.co.uk";

var pageContents = `curl http://${url}`;

print("Google says: ${pageContents}");
```

4. 特殊变量

Nashorn 还提供了几个特殊的全局变量和函数，编写脚本时特别有用，而且普通的 JavaScript 中没有。例如，传入脚本的参数可以通过 $ARG 变量获取。参数必须使用约定的 -- 方式传入，像下面这样：
```
jjs test1.jjs -- aa bbb cccc
```
获取参数的方式如下所示：
```
print($ARG);

for(var i=0; i < $ARG.length; i++) {
    print("${i}: "+ $ARG[i]);
}
```
> $ARG 变量是一个 JavaScript 数组（观察传给 print() 方法后的表现可以看出来），而且要当成数组处理。学过其他语言的程序员可能觉得这种句法有点让人困惑，因为有些语言使用 $ 符号表示标量变量。

我们能遇到的另一个特殊的全局变量是 $ENV，这个变量用于获取当前的环境变量。例如，下述代码打印当前用户的家目录：
```
print("HOME = "+ $ENV.HOME); # 在我的电脑中打印的是/home/ben
```
Nashorn 还提供了一个特殊的全局函数，$EXEC()。这个函数的作用和前面介绍的反引号类似，如下述示例所示：
```
var execOutput = $EXEC("echo Print this on stdout");
print(execOutput);
```
你可能注意到了，使用反引号或 $EXEC() 函数时，不会打印所执行命令的输出，而是返回函数的返回值。这是为了避免命令的输出扰乱主脚本的输出。

Nashorn 提供了另外两个特殊的变量，有助于程序员处理脚本中所执行命令的输出：$OUT 和 $ERR。这两个变量分别用于捕获脚本中所执行命令的输出和错误消息。例如：
```
$EXEC("echo Print this on stdout");

// 没有修改标准输出的代码

var saveOut = $OUT;
print("- - - - - - -");
print(saveOut);
```
$OUT 和 $ERR 中的内容一直存在，除非主脚本中的后续代码修改这些内容（例如执行其他命令）。

5. 行内文档

JavaScript 和 Java 一样，不支持把包围字符串的两个引号放在不同的行（这种字符串叫多行字符串）。可是，在脚本模式中，Nashorn 通过扩展对此提供了支持。这种功能也叫行内文档或 heredoc，是脚本语言的通用特性。

heredoc 以 <<END_TOKEN 开头，从下一行开始，直到结束符号（可以使用任何字符串，不过经常全部大写，经常使用的字符串有 END、END_DOC、END_STR、EOF 和 EOSTR），中间都是多行字符串的内容。在结束符号之后，脚本恢复正常。我们看一个示例：
```
var hw = "Hello World!";
var output = <<EOSTR;

This is a multiline string
It can interpolate too - ${hw}
EOSTR
print(output);
```

6. Nashorn提供的辅助函数

Nashorn 还提供了一些辅助函数，让开发者能轻易实现 shell 脚本经常要执行的常见任务。













##### 12.3 Nashorn 和 javax.script 包




###### 12.4 Nashorn 的高级用法




###### 12.4.1 在 Nashorn 中调用 Java 代码




###### 12.4.2 Nashorn 对 JavaScript 语言所做的扩展




###### 12.4.3 实现细节




##### 12.5 小结



