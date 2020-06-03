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
```javascript
jrunscript my_script.js
```
除了 Nashorn，jrunscript 还能使用其他脚本引擎（12.3 节会详细介绍脚本引擎）。如果需要使用其他引擎，可以通过 -l 选项指定：
```javascript
jrunscript –l nashorn my_script.js
```
> 如果有合适的脚本引擎，使用这个选项还能让 jrunscript 运行使用其他语言编写的脚本。

这个基本的运行程序特别适合在简单的应用场景中使用，不过它有一定的局限性，因此，在重要场合下，我们需要使用功能更强的执行环境——jjs，也就是 Nashorn shell。

###### 12.2.2 使用 Nashorn shell

启动 Nashorn shell 的命令是 jjs。Nashorn shell 既可以交互式使用，也可以非交互式使用，能直接替代 jrunscript。

最简单的 JavaScript 示例当然是经典的“Hello World”，我们看一下如何在交互式 shell 中编写这个示例：
```javascript
$ jjs
jjs> print("Hello World!");
Hello World!
jjs>
```
在 shell 中可以轻易处理 Nashorn 和 Java 之间的相互操作，12.4.1 节会详细介绍，不过现在先举个例子。若想在 JavaScript 中直接访问 Java 类和方法，使用完全限定的类名即可。下面这个实例获取 Java 原生的正则表达式功能：
```javascript
jjs> var pattern = java.util.regex.Pattern.compile("\\d+");
jjs> var myNums = pattern.split("a1b2c3d4e5f6");

jjs> print(myNums);
[Ljava.lang.String;@10b48321

jjs> print(myNums[0]);
a
```

> 在 REPL 中 打 印 JavaScript 变 量 myNums 时， 得 到 的 结 果 是 [Ljava.lang.String;@10b48321。这表明，虽然 myNums 是 JavaScript 代码中的变量，但其实它是 Java 中的字符串数组。

稍后会详细说明 Nashorn 和 Java 之间的相互操作，现在先介绍 jjs 的其他功能。jjs 命令的通用格式是：
```javascript
jjs [<options>] <files> [-- <arguments>]
```
能传给 jjs 的选项有很多，其中最常使用的如下所示。

- -cp 或 -classpath：指定在哪个位置寻找额外的 Java 类（稍后会发现，通过 Java.type 机制实现）。
- -doe 或 -dump-on-error：如果强制退出 Nashorn，转储完整的错误信息。
- -J：把选项传给 JVM。例如，如果想增加 JVM 可用的最大内存，可以这么做：
```javascript
$ jjs -J-Xmx4g
jjs> java.lang.Runtime.getRuntime().maxMemory()
3817799680
```
- -strict：在 JavaScript 严格模式中执行所有脚本和函数。这是 ECMAScript 5 引入的 JavaScript 特性，目的是减少缺陷和错误。所有新编写的 JavaScript 代码都推荐使用严格模式，如果你对这个特性还不了解，应该找些资料看看。
- -D：让开发者把键值对表示的系统属性传给 Nashorn，这和 JVM 的通常做法一样。例如：
```javascript
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
```javascript
$ jjs -scripting my_script.js
```
这样调用，可以使用增强的 jjs 功能，其中包含一些有用的扩展。很多扩展都能让脚本程序员通过更熟悉的方式使用 Nashorn。

1. 脚本中的注释

在传统的 Unix 脚本中，# 符号表示注释，一直到行尾结束。而 JavaScript 使用 C/C++ 风格的注释，使用 // 表示注释，一直到行尾结束。Nashorn 支持这种注释方式，不过在脚本模式中也能使用 Unix 脚本的注释方式，因此，下述代码完全合法：
```javascript
#!/usr/bin/jjs

# 在脚本模式中，这样写注释完全合法

print("After the comment");
```

2. 行内执行命令

资深 Unix 程序员通常把这个功能称为“反引号”。在 bash 脚本中，我们可以编写如下的代
码，使用 Unix 的 curl 命令下载谷歌首页的内容：
```javascript
echo "Google says: " `curl http://www.google.co.uk`
```
类似地，在 Nashorn 脚本中，我们也可以使用反引号（`）执行 Unix shell 命令。如下所示：
```javascript
print("Google says: "+ `curl http://www.google.co.uk`);
```

3. 字符串插值

字符串插值是一种特殊的句法，无需连接字符串，就能直接插入变量的内容。在 Nashorn 脚本中，我们可以使用 `${<variable name>}` 把变量的值插入字符串。例如，前面下载网页内容的示例，使用插值后可以改写成：
```javascript
var url = "www.google.co.uk";

var pageContents = `curl http://${url}`;

print("Google says: ${pageContents}");
```

4. 特殊变量

Nashorn 还提供了几个特殊的全局变量和函数，编写脚本时特别有用，而且普通的 JavaScript 中没有。例如，传入脚本的参数可以通过 $ARG 变量获取。参数必须使用约定的 -- 方式传入，像下面这样：
```javascript
jjs test1.jjs -- aa bbb cccc
```
获取参数的方式如下所示：
```javascript
print($ARG);

for(var i=0; i < $ARG.length; i++) {
    print("${i}: "+ $ARG[i]);
}
```
> $ARG 变量是一个 JavaScript 数组（观察传给 print() 方法后的表现可以看出来），而且要当成数组处理。学过其他语言的程序员可能觉得这种句法有点让人困惑，因为有些语言使用 $ 符号表示标量变量。

我们能遇到的另一个特殊的全局变量是 $ENV，这个变量用于获取当前的环境变量。例如，下述代码打印当前用户的家目录：
```javascript
print("HOME = "+ $ENV.HOME); # 在我的电脑中打印的是/home/ben
```
Nashorn 还提供了一个特殊的全局函数，$EXEC()。这个函数的作用和前面介绍的反引号类似，如下述示例所示：
```javascript
var execOutput = $EXEC("echo Print this on stdout");
print(execOutput);
```
你可能注意到了，使用反引号或 $EXEC() 函数时，不会打印所执行命令的输出，而是返回函数的返回值。这是为了避免命令的输出扰乱主脚本的输出。

Nashorn 提供了另外两个特殊的变量，有助于程序员处理脚本中所执行命令的输出：$OUT 和 $ERR。这两个变量分别用于捕获脚本中所执行命令的输出和错误消息。例如：
```javascript
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
```javascript
var hw = "Hello World!";
var output = <<EOSTR;

This is a multiline string
It can interpolate too - ${hw}
EOSTR
print(output);
```

6. Nashorn提供的辅助函数

Nashorn 还提供了一些辅助函数，让开发者能轻易实现 shell 脚本经常要执行的常见任务。

- print()/echo()

前面的示例都用到了 print() 函数。这两个函数的表现和预期完全一样，把传入的字符串打印出来，后面还会加一个换行符。

- quit()/exit()

这两个函数的作用完全一样——退出脚本。可以把一个整数参数传给这两个函数，作为脚本进程的返回码。如果没传入参数，返回码默认为 0——这是 Unix 进程的习惯做法。

- readLine()

从标准输入（通常是键盘）读取一行输入。默认情况下，这个函数会把读取的内容打印到标准输出，不过，如果把 readLine() 函数的返回值赋值给变量，输入的数据就在此结束，如下述示例所示：
```javascript
print("Please enter your name: ");
var name = readLine();
print("Please enter your age: ");
var age = readLine();

print(<<EOREC);
Student Record
-+-+-+-+-+-+-+-
Name: ${name}
Age: ${age}
EOREC
```
- readFully()

readFully() 函数不从标准输入读取数据，而是加载一个文件中的全部内容。和 readLine() 函数一样，加载的内容不是打印到标准输出，就是赋值给变量：
```javascript
var contents = readFully("input.txt");
```
- load()

这个函数用于加载并执行脚本（使用 JavaScript 的 eval 函数执行）。脚本可以从本地路径或 URL 中加载。除此之外，还可以使用 JavaScript 的脚本对象表示法把脚本文件定义成一个字符串。

> 使用 load() 函数执行其他脚本可能出现意料之外的错误。JavaScript 支持使用 try-catch 块处理异常，所以加载代码时要使用这种方式。

下面举个简单的例子，这个例子在 Nashorn 中加载图形可视化库 D3:
```javascript
try {
    load(“http://d3js.org/d3.v3.min.js”);
} catch (e) {
    print(“Something went wrong, probably that we’re not a web browser”);
}
```
- loadWithNewGlobal()

load() 函数会在当前 JavaScript 上下文中执行加载的脚本。而有时我们想把代码放入属于它自己的纯净上下文中。此时，可以使用 loadWithNewGlobal() 函数，在全新的全局上下文中执行脚本。

7. shebang句法

本节介绍的所有功能都是为了方便编写 shell 脚本，让 jjs 能替代 bash、Perl 或其他脚本语言。为了完善这种支持，最后还需要一个功能——“shebang”句法，用来启动使用Nashorn 编写的脚本。

> 如果可执行脚本的第一行以 #! 开头，而且后面是一个可执行文件的路径，那么 Unix 操作系统会假定这个路径指向一个解释器，而且这个解释器能处理这种脚本。执行脚本时，操作系统会启动指定的解释器，并把脚本文件传给解释器处理。

对 Nashorn 来说，最好创建一个符号链接（可能需要 sudo 访问），把 /usr/bin/jjs（或 /usr/local/bin/jjs）指向 jjs 的真正位置（通常是 $JAVA_HOME/bin/jjs）。然后，可以像下面这样编写 Nashorn shell 脚本：
```
#!/usr/bin/jjs

# ……脚本中的其他内容
```

对更高级的应用场景来说（例如长时间运行的守护进程），Nashorn 甚至提供了对 Node.js 的支持。这个功能由 Avatar 项目中的 Avatar.js 提供，详情参见 12.5 节的“Avatar 项目”。

本节介绍的工具便于直接在命令行中执行 JavaScript 代码，不过多数情况下，我们希望使用另一种方式执行 JavaScript 代码——在 Java 程序中调用 Nashorn，执行 JavaScript 代码。实现这种执行方式的 API 包含在 Java 包 javax.script 中，所以，接下来我们要介绍这个包，并讨论 Java 如何与解释脚本语言的引擎交互。

##### 12.3 Nashorn 和 javax.script 包

Nashorn 不是 Java 平台提供的第一个脚本语言。早在 Java 6 中就提供了 javax.script 包，这个包为引擎提供了通用接口，让脚本语言能和 Java 相互操作。

这个通用接口中包含脚本语言的基本概念，例如脚本代码的执行和编译方式（完整的脚本或者单个脚本语句是否在现有的上下文中）。而且还提出了脚本实体和 Java 绑定的概念，以及发现脚本引擎的功能。最后，javax.script 包提供了可选的调用功能（有别于执行，因为调用功能的作用是从脚本语言的运行时中导出中间代码，提供给 JVM 运行时使用）。

本节的示例使用 Rhino 语言编写，不过也有很多其他语言利用了 javax.script 包提供的功能。Java 8 移除了 Rhino，现在 Java 平台提供的默认脚本语言是 Nashorn。

通过javax.script包使用Nashorn

我们看一个非常简单的示例，这个示例展示了如何 JavaScript 代码：
```java
import javax.script.*;

ScriptEngineManager m = new ScriptEngineManager();
ScriptEngine e = m.getEngineByName("nashorn");

try {
    e.eval("print('Hello World!');");
} catch (final ScriptException se) {
    // ...
}
```
这段代码的重点是 ScriptEngine 对象，这个对象通过 ScriptEngineManager 对象获取。ScriptEngine 对象提供一个空脚本环境，然后调用 eval() 方法把代码添加到这个环境中。

Nashorn 引擎只提供了一个全局 JavaScript 对象，所以每次调用 eval() 方法都会在同一个环境中执行 JavaScript 代码。也就是说，我们可以多次调用 eval() 方法，在脚本引擎中逐渐积累 JavaScript 状态。例如：
```java
e.eval("i = 27;");
e.put("j", 15);
e.eval("var z = i + j;");

System.out.println(((Number) e.get("z")).intValue()); // 打印42
```
注意，直接在 Java 代码中与脚本引擎交互有个问题：一般不知道值的任何类型信息。

然而，Nashorn 和 Java 的类型系统绑定得相当紧密，所以要稍微小心一些。在 Java 代码中使用 JavaScript 中等价的基本类型时，往往都会转换成对应的（包装）类型。例如，如果把下面这行代码添加到前一个示例的末尾：
```java
System.out.println(e.get("z").getClass());
```
很明显，e.get("z") 获得的值是 java.lang.Integer 类型。如果稍微修改一下，改成：
```java
e.eval("i = 27.1;");
e.put("j", 15);
e.eval("var z = i + j;");

System.out.println(e.get("z").getClass());
```
那么 e.get("z") 返回值的类型就变成了 java.lang.Double，由此体现了两种类型系统之间的区别。在其他 JavaScript 实现中，可能会把两种情况的返回值都当成数字类型（因为 JavaScript 没有定义整数类型）。可是，Nashorn 对数据的真正类型知道的更多。

> 使用 JavaScript 时，Java 程序员必须清醒地认识到，Java 的静态类型和 JavaScript 类型的动态本性之间是有区别的。如果没认识到这一点，很容易在不经意间引入缺陷。

在上述示例中，我们在 ScriptEngine 对象上调用 get() 和 put() 方法。这么做可以直接获取和设定 Nashorn 引擎当前全局作用域中的对象，无需直接编写或使用 eval() 方法执行 JavaScript 代码。

javax.script API

本节最后，我们要简介 javax.script API 中的关键类和接口。这个 API 相当小（6 个接口，5 个类，1 个异常），自从 Java 6 引入之后就没改动过。

- ScriptEngineManager类

这个类是脚本功能的入口点，维护着一组当前进程中可用的脚本实现。这个功能由 Java 的服务提供者（service provider）机制实现，这种机制经常用于管理不同实现之间可能有较大差异的 Java 平台扩展。默认情况下，唯一可用的脚本扩展是 Nashorn，不过，也能使用其他脚本环境（例如 Groovy 和 JRuby）。

- ScriptEngine接口

这个接口表示脚本引擎，作用是维护解释脚本的环境。

- Bindings接口

这个接口扩展 Map 接口，把字符串（变量或其他符号的名称）映射到脚本对象上。Nashorn 使用这个接口实现 ScriptObjectMirror 机制，让 Java 和 JavaScript 代码能相互操作。

其实，多数应用只会使用 ScriptEngine 这个相对不透明的接口提供的方法，例如 eval()、get() 和 put() 方法，不过，理解这个接口在整个脚本 API 中的作用，会对你有所帮助。

###### 12.4 Nashorn 的高级用法

Nashorn 是个复杂的编程环境，也是个适合部署应用的稳健平台，而且能和 Java 相互操作。这一节我们介绍一些高级用法，把 Java 代码集成在 JavaScript 代码中，还会深入 Nashorn 的一些实现细节，说明实现这种集成的方式。

###### 12.4.1 在 Nashorn 中调用 Java 代码

我们知道，每个 JavaScript 对象都会编译成某个 Java 类的实例，那么，你或许就不会奇怪，在 Nashorn 中能无缝集成 Java 代码——尽管二者的类型系统和语言特性有重要区别。不过，为了实现这种集成，还需要实现一些机制。

我们已经知道，在 Nashorn 中可以直接访问 Java 类和方法，例如：
```
$ jjs -Dkey=value
jjs> print(java.lang.System.getProperty("key"));
value
```
下面仔细分析一下这个句法，看看 Nashorn 是如何实现这种功能的。

1. JavaClass和JavaPackage类型

在 Java 中，表达式 java.lang.System.getProperty("key") 的意思是通过完全限定的名称调用 java.lang.System 类中的 getProperty() 静态方法。不过，在 JavaScript 的句法中，这个表达式的意思是，从符号 java 开始，链式访问属性。下面在 jjs shell 中看一下这些符号的表现：
```
jjs> print(java);
[JavaPackage java]

jjs> print(java.lang.System);
[JavaClass java.lang.System]
```
可以看出，java 是个特殊的 Nashorn 对象，用于访问 Java 系统中的包。在 JavaScript 中，Java 包使用 JavaPackage 类型表示，而 Java 类使用 JavaClass 类型表示。任何顶层包都能直接作为包导航对象，子包则可以赋值给 JavaScript 对象。因此，可以使用简短的句法访问 Java 类：
```
jjs> var juc = java.util.concurrent;
jjs> var chm = new juc.ConcurrentHashMap;
```
除了可以使用包对象导航之外，还可以使用另一个对象 Java。在这个对象上可以调用一些有用的方法，其中一个最重要的方法是 Java.type()。使用这个方法可以查询 Java 的类型系统，访问 Java 类。例如：
```
jjs> var clz = Java.type("java.lang.System");
jjs> print(clz);
[JavaClass java.lang.System]
```
如果在类路径（例如，使用 jjs 的 -cp 选项指定）中找不到指定的类，会抛出  ClassNotFoundException 异常（jjs 会把这个异常包装在一个 Java RuntimeException 异常对象中）：
```
jjs> var klz = Java.type("Java.lang.Zystem");
java.lang.RuntimeException: java.lang.ClassNotFoundException:
  Java.lang.Zystem
```
多数情况下，JavaScript 中的 JavaClass 对象都可以像 Java 的类对象一样使用（这两个类型稍微有所不同，不过可以把 JavaClass 理解为类对象在 Nashorn 中的镜像）。例如，在 Nashorn 中可以直接使用 JavaClass 创建 Java 新对象：
```
jjs> var clz = Java.type("java.lang.Object");
jjs> var obj = new clz;
jjs> print(obj);
java.lang.Object@73d4cc9e

jjs> print(obj.hashCode());
1943325854

// 注意，这种句法不起作用
jjs> var obj = clz.new;
jjs> print(obj);
undefined
```
不过，使用时要稍微小心一些。jjs 环境会自动打印表达式的结果，这可能会导致一些意料之外的行为：
```
jjs> var clz = Java.type("java.lang.System");
jjs> clz.out.println("Baz!");
Baz!
null
```
这里的问题是，java.lang.System.out.println() 方法有返回值，类型为 void。而在 jjs 中，如果表达式没赋值给变量，就会得到一个值，并打印出来。所以，println() 方法的返回值会映射到 JavaScript 的 null 值上，并打印出来。

> 不熟悉 JavaScript 的 Java 程序员要注意，在 JavaScript 中处理 null 和缺失值很麻烦，尤其是 null != undefined。

2. JavaScript函数和Java lambda表达式

JavaScript 和 Java 之间的相互操作层级非常深，甚至可以使用 JavaScript 函数作为 Java 接口的匿名实现（或者作为 lambda 表达式）。下面举个例子，使用 JavaScript 函数作为 Callable 接口的实例（表示后续调用的代码块）。Callable 接口中只有一个方法，call()，这个方法没有参数，返回值是 void。在 Nashorn 中，我们可以使用 JavaScript 函数作为 lambda 表达式：
```
jjs> var clz = Java.type("java.util.concurrent.Callable");
jjs> print(clz);
[JavaClass java.util.concurrent.Callable]
jjs> var obj = new clz(function () { print("Foo"); } );
jjs> obj.call();
Foo
```
这个示例要表明的基本事实是，在 Nashorn 中，JavaScript 函数和 Java lambda 表达式之间没有区别。和在 Java 中一样，函数会被自动转换成相应类型的对象。下面看一下如何在 Java 线程池中使用 Java 的 ExecutorService 对象执行一些 JavaScript 代码：
```
jjs> var juc = java.util.concurrent;
jjs> var exc = juc.Executors.newSingleThreadExecutor();
jjs> var clbl = new juc.Callable(function (){
  \java.lang.Thread.sleep(10000); return 1; });
jjs> var fut = exc.submit(clbl);
jjs> fut.isDone();
false
jjs> fut.isDone();
true
```
与等效的 Java 代码相比（就算使用 Java 8 引入的 lambda 表达式），样板代码的减少量十分惊人。不过，lambda 表达式的实现方式导致了一些限制。例如：
```
jjs> var fut=exc.submit(function (){\
java.lang.Thread.sleep(10000); return 1;});
java.lang.RuntimeException: java.lang.NoSuchMethodException: Can't
unambiguously select between fixed arity signatures
[(java.lang.Runnable), (java.util.concurrent.Callable)] of the method
java.util.concurrent.Executors.FinalizableDelegatedExecutorService
.submit for argument types
[jdk.nashorn.internal.objects.ScriptFunctionImpl]
```
这里的问题是，线程池中有一个重载的 submit() 方法。一个版本的参数是一个 Callable 对象，而另一个版本的参数是一个 Runnable 对象。可是，JavaScript 函数（作为 lambda 表达式时）既能转换成 Callable 对象，也能转换成 Runnable 对象。这就是错误消息中出现“unambiguously select”（明确选择）的原因。运行时能选择其中任何一个，但不能在二者之间作出抉择。

###### 12.4.2 Nashorn 对 JavaScript 语言所做的扩展

前面说过，Nashorn 是完全遵守 ECMAScript 5.1 标准（这是 JavaScript 的标准）的实现。不过，除此之外，Nashorn 还实现了一些 JavaScript 语言句法扩展，让开发者的生活更轻松。经常使用 JavaScript 的开发者应该会熟悉这些扩展，有相当一部分扩展实现的都是 Mozilla JavaScript 方言中的功能。下面介绍几个最常使用和最有用的扩展。

1. 遍历循环

标准的 JavaScript 没有提供等同于 Java 遍历循环的句法，不过 Nashorn 实现了 Mozilla 使用的 for each in 循环，如下所示：
```
var jsEngs = [ "Nashorn", "Rhino", "V8", "IonMonkey", "Nitro" ];
for each (js in jsEngs) {
    print(js);
}
```
2. 单表达式函数

Nashorn 还支持另一个小小的句法增强，目的是让只由一个表达式组成的函数更易于阅读。如果函数（具名或匿名）只有一个表达式，那么可以省略花括号和返回语句。在下述示例中，cube() 和 cube2() 这两个函数完全等效，不过 cube() 函数使用的句法在普通的 JavaScript 中不合法：
```
function cube(x) x*x*x;

function cube2(x) {
    return x*x*x;
}

print(cube(3));
print(cube2(3));
```
3. 多个catch子句

JavaScript 支持 try、catch 和 throw 语句，而且处理方式和 Java 类似。





###### 12.4.3 实现细节




##### 12.5 小结



