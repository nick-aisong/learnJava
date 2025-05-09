# 97 Things Every Java Programmer Should Know



## 1. All You Need Is Java 

### English

---

While working on the first major revision of Visual Studio, the team at Microsoft introduced the world to three developer personas: Mort, Elvis, and Einstein.
Mort was the opportunistic developer, doing quick fixes and making things up as he went along.
Elvis was the pragmatic programmer, building solutions for the ages while learning on the job.
Einstein was the paranoid programmer, obsessed with designing the most efficient solution and figuring everything out before writing his code.

On the Java side of the religious divide of programming languages, we laughed at Morts, and we wanted to be Einsteins building frameworks to make sure the Elvises wrote their code the “right way.”
This was the dawn of the age of the frameworks, and unless you were proficient with the latest, greatest object relational mapper and inversion of control framework, you weren’t a proper Java programmer.
Libraries grew into frameworks with prescripted architectures.
And as these frameworks became technology ecosystems, many of us forgot about the little language that could — Java.

Java is a great language and its class library has something for every occasion.
Need to work with files? `java.nio`’s got you covered.
Databases? `java.sql` is the place to go.
Almost every Java distribution even sports a full-blown HTTP server, even if you have to climb off the Java-named branch and onto `com.sun.net.httpserver`.

As our applications move toward serverless architectures, where the deployment units can be single functions, the benefits we get from application frameworks diminish.
This is because we’ll likely spend less time on handling technical and infrastructural concerns, focusing our programming efforts toward the business capabilities our programs realize.

As Bruce Joyce put it:

> We have to reinvent the wheel every once in a while, not because we need a lot of wheels; but because we need a lot of inventors.

Many have set out to build generic business logic frameworks to maximize reuse.
Most have failed since there really aren’t any generic business problems.
Doing something special in a specific way is what sets one business apart from the next.
This is why you’re guaranteed to be writing business logic on just about every project.

In the name of coming up with something generic and reusable, one might be tempted to introduce a rules engine or similar.
At the end of the day, configuring a rules engine is programming, often in a language inferior to Java.
Why not try to just write Java instead?
You’ll be surprised to find that the end result will be easy to read, which in turn makes the code easy to maintain — even by non-Java programmers.

Quite often you’ll find that Java’s class library is a little limited, and you might need something to make working with dates, networking, or something else a little more comfortable.
That’s fine. Use a library.
The difference is that you’ll now be using that library because a specific need occurred, not because it was part of the stack you’ve always been using.

The next time an idea for a small program springs to mind, awaken your knowledge of the Java class library from hibernation rather than reaching for that JHipster scaffold.
Hipsterism is passé; living a simple life is where it’s at now.
I bet Mort loved the simple life.

---

### 中文

---

在开发 Visual Studio 的第一个重大版本时，微软团队向世人介绍了三种开发者角色：Mort、Elvis 和 Einstein。

Mort 是机会主义型的开发者，哪里出问题就修哪里，边做边想；
Elvis 是务实的程序员，在工作的同时不断学习，打造经得起时间考验的解决方案；
Einstein 则是偏执的程序员，痴迷于在编码前设计出最高效的解决方案、想明白一切。

在编程语言阵营的“宗教分裂”中，Java 一方常常嘲笑 Morts，而我们都想成为 Einstein，构建各种框架，确保 Elvis 们“正确地”写代码。
这就是框架时代的黎明时期。除非你熟练掌握最新、最先进的对象关系映射工具（ORM）和控制反转框架，否则你就不算一个合格的 Java 程序员。
库逐渐演化为预设架构的框架。
而随着这些框架变成庞大的技术生态系统，我们中的许多人却忘记了那个“小而强大”的语言 —— Java。

Java 是一门优秀的语言，它的类库几乎应有尽有。
需要处理文件？`java.nio` 可以搞定。
数据库？`java.sql` 就是你的目的地。
几乎每个 Java 发行版甚至都内置了完整的 HTTP 服务器 —— 尽管你可能需要从以 Java 命名的包跳到 `com.sun.net.httpserver`。

如今我们的应用逐步迈向无服务器架构，部署单元可以是一个个单独的函数，应用框架带来的好处也就日渐减少。
这是因为，我们会把更多精力从处理技术细节和基础设施转移到实现业务能力上。

正如 Bruce Joyce 所说：

> 我们需要时不时重新发明轮子，不是因为我们需要很多轮子，而是因为我们需要很多发明者。

很多人尝试构建通用的业务逻辑框架，以最大化复用。
但大多数都失败了，因为并不存在真正意义上的“通用”业务问题。
每个企业正是因为以特定的方式做一些特别的事，才与众不同。
这也是为什么你几乎在每个项目中都逃不开要写业务逻辑。

为了构建某种通用、可复用的机制，有人可能会引入规则引擎之类的东西。
但归根结底，配置规则引擎本质上也是在编程，而且常常是用一种不如 Java 的语言。
那么，为什么不干脆直接写 Java 呢？
你可能会惊讶地发现，这样写出来的代码可读性极强，也更易于维护 —— 甚至非 Java 程序员都能轻松理解。

当然，有时你会发现 Java 的类库在某些方面稍显不足，比如处理日期、网络等，这没关系，你可以使用第三方库。
但这一次你使用库是出于实际需要，而不是因为它“总是被包括在你的技术栈中”。

下次当你脑海中浮现一个小程序的点子时，不妨唤醒你对 Java 类库的记忆，而不是立刻去生成一个 JHipster 的脚手架项目。
潮流编程已过时，如今追求的是简约生活。
我敢打赌，Mort 一定很享受这种简单的生活。

## 2. Approval Testing  

### English

------

Have you ever written a test assertion with a dummy or blank expectation? Something like this:

```java
assertEquals("", functionCall())
```

Where `functionCall` is returning a string and you’re not sure exactly what that string should be, but you’ll know it’s right when you see it?
 When you run the test the first time, of course, it fails because `functionCall` returns a string that isn’t empty.
 (You might have several tries, until the return value looks correct.)
 Then you paste this value instead of the empty string in the `assertEquals`.
 Now the test should pass. Result! That’s what I’d call **approval testing**.

The crucial step here is when you decide the output is correct and use it as the expected value.
 You “approve” a result — it’s good enough to keep.
 I expect you’ve done this kind of thing without really thinking about it.
 Perhaps you call it by a different name: it’s also called **snapshot testing** or **golden master testing**.
 In my experience, if you have a testing framework specifically designed to support it, then a lot of things fall into place and testing this way gets easier.

With a classic unit testing framework like JUnit, it can be a bit painful to update those expected strings when they change.
 You end up pasting stuff around in the source code.
 With an approval testing tool, the approved string gets stored in a file instead.
 That immediately opens up new possibilities.
 You can use a proper diff tool to go through changes and merge them one by one.
 You can get syntax highlighting for JSON strings and such.
 You can search and replace updates across several tests in different classes.

------

**So, what are good situations for approval testing? Here are a few:**

**Code without unit tests that you need to change**
 If the code is in production, then anything it does is, by default, considered correct and can be approved.
 The hard part about creating tests turns into a problem of finding seams and carving out pieces of logic that return something interesting you can approve.

**REST APIs and functions that return JSON or XML**
 If the result is a longer string, then storing it outside the source code is a big win.
 JSON and XML can both be formatted with consistent white space so they are easy to compare against an expected value.
 If there are values in the JSON or XML that vary a lot — dates and times, for example — you might need to check them separately before replacing them with a fixed string and approving the remainder.

**Business logic that builds a complex return object**
 Start by writing a Printer class that can take your complex return object and format it as a string.
 Think of a Receipt or a Prescription or an Order.
 Any of those could be represented well as a human-readable multiline string.
 Your Printer can choose to only print a summary — traverse the object graph to pull out relevant details.
 Your tests will then exercise various business rules and use the Printer to create a string for approval.
 If you have a noncoding product owner or business analyst, they could even read the test results and verify that they are correct.

------

If you already have tests that make assertions about strings that are longer than one line, then I recommend finding out more about approval testing and starting to use a tool that supports it.

---

### 中文

---

------

你有没有写过一种“占位”或“空预期”的测试断言？像下面这样：

```java
assertEquals("", functionCall())
```

这里的 `functionCall` 返回一个字符串，但你并不确定它具体应该返回什么；你只是知道当你“看起来对了”，那就是对的。
 第一次运行测试时，它当然会失败，因为 `functionCall` 返回的并不是空字符串。
 （你可能要试几次，直到返回值“看起来正确”。）
 然后你把这个返回值复制粘贴到 `assertEquals` 中替换掉空字符串。
 这时候测试应该就会通过。成功了！我把这种方式叫作 **批准测试（approval testing）**。

关键的一步是你决定某个输出是“正确的”，并把它作为期望值保留下来。
 你“批准”了这个结果 —— 它好到足以作为标准。
 我猜你以前可能不假思索地做过类似的事。
 或许你叫它别的名字：这种方式也被称为 **快照测试（snapshot testing）** 或 **黄金母本测试（golden master testing）**。
 在我的经验中，如果你使用一个专门支持这种测试方式的框架，一切会变得顺畅很多。

使用传统的单元测试框架（比如 JUnit）时，当预期字符串发生变化，更新它们会变得很痛苦。
 你得在源码里复制粘贴来回改。
 而使用批准测试工具时，批准的字符串会被存储在一个文件中。
 这立刻带来了新的可能性：
 你可以使用专业的差异对比工具逐条审查并合并变更；
 可以为 JSON 字符串等添加语法高亮；
 还可以跨多个测试类批量查找和替换更新。

------

**那么，哪些场景适合使用批准测试呢？下面是一些典型例子：**

**需要修改但没有单元测试的代码**
 如果代码已经在生产中运行，那么它的行为默认就是“正确的”，可以直接作为批准基准。
 编写测试的难点，就转变成了找到逻辑的“缝隙”，提取出可以返回有意义内容的部分，并对其进行批准。

**返回 JSON 或 XML 的 REST API 或函数**
 如果结果是比较长的字符串，把它存放在源码之外会大有裨益。
 JSON 和 XML 都可以用一致的空白格式化，使得它们更容易与期望值进行比较。
 如果 JSON 或 XML 中包含变化频繁的值（如日期、时间等），你可能需要在批准之前单独校验这些字段，并将其替换为固定字符串。

**包含复杂返回对象的业务逻辑**
 你可以先写一个 Printer 类，把复杂的返回对象格式化成字符串。
 例如小票（Receipt）、处方（Prescription）或订单（Order）等，都可以很好地表示成多行的可读字符串。
 Printer 可以选择只输出摘要 —— 遍历对象图，提取相关细节。
 你的测试就可以验证各种业务规则，并使用 Printer 输出一个字符串用于批准。
 如果你的产品负责人或业务分析师不懂代码，他们也可以通过查看测试输出判断结果是否正确。

------

如果你已经有一些测试断言检查的是多行字符串，那么我推荐你深入了解一下批准测试，并开始使用支持它的工具。

---

## 3. Augment Javadoc with AsciiDoc  

### English

---

Java developers already know Javadoc.
 Those who’ve been around a long time remember how transformative it was, as Java became the first mainstream language to integrate a documentation generator right into the compiler and standard toolchain.
 The resulting explosion of API documentation (even if not always great or polished) has hugely benefited us all, and the trend has spread to many other languages.

As James Gosling reported, Javadoc was initially controversial because “a good tech writer could do a lot better job”—but there are vastly more APIs than tech writers to document them, and the value of having something universally available has been well established.

Sometimes you need more than API documentation, though—much more than you can fit in the package and project overview pages Javadoc offers.
 End-user-focused guides and walk-throughs, detailed background on architecture and theory, explanations of how to fit together multiple components… none of these fit comfortably within Javadoc.

------

**So what can we use to meet these other needs?**

The answers have changed over time.
 FrameMaker was a groundbreaking cross-platform GUI technical document powerhouse in the ’80s.
 Javadoc even used to include a MIF Doclet for generating attractive printed API documentation with FrameMaker—but only a vestigial Windows version remains.

DocBook XML offers similar structural and linking power, with an open specification and cross-platform toolchain, but its raw XML format is impractical to work with directly.
 Keeping up with its editing tools became expensive and tedious, and even the good ones felt clunky and hampered the flow of writing.

------

I’m thrilled to have found a better answer: **AsciiDoc** offers all the power of DocBook in an easy-to-write (and read) text format, where doing simple things is trivial and doing complex things is possible.

Most AsciiDoc constructs are as immediately readable and accessible as other lightweight markup formats like Markdown, which are becoming familiar through online discussion forums.
 And when you need to get fancy, you can include complex equations using MathML or LaTeX formats, formatted source code listings with numbered and linked callouts to text paragraphs, admonition blocks of different kinds, and more.

AsciiDoc was introduced with a Python implementation in 2002.
 The current official implementation (and steward of the language) is **Asciidoctor**, released in 2013.
 Its Ruby code can also be run in the JVM through **AsciidoctorJ** (with Maven and Gradle plug-ins) or transpiled to JavaScript, all of which work nicely in continuous integration environments.

When you need to build an entire site of related documentation (even from multiple repositories), tools like **Antora** make it shockingly easy.
 The community is friendly and supportive, and watching its growth and progress over the past year has been inspiring.
 And, if it matters to you, the process of standardizing a formal AsciiDoc specification is underway.

------

I like creating rich, attractive documentation for the projects that I share.
 AsciiDoc has made that so much easier, and given me such rapid turnaround cycles, that polishing and perfecting that documentation has become fun.
 I hope you find the same.
 And, coming full circle, if you decide to go all in with AsciiDoc, there’s even a Doclet named **Asciidoclet** that lets you write Javadoc using AsciiDoc!

---

### 中文

---

Java 开发者早已熟悉 Javadoc。
 那些资历较深的人还记得，Javadoc 的出现是多么具有变革意义 —— Java 成为第一个将文档生成器直接集成进编译器和标准工具链的主流语言。
 这一变革引发了 API 文档的爆炸式增长（虽然质量未必总是优秀或精致），但它极大地造福了整个生态系统，也影响了许多其他语言。

正如 James Gosling 所说，Javadoc 的最初版本曾引发争议，因为“一个优秀的技术写作者能写得更好”。
 但问题在于，API 的数量远远超过技术写作者的数量，而让“人人都能写文档”的价值早已被证明是不可替代的。

不过，有时候我们需要的不仅仅是 API 文档 —— 比如 Javadoc 提供的包说明和项目概览页面所能承载的内容远远不够。
 面向最终用户的操作指南和演示、架构和原理的深入解析、多个组件如何整合工作的说明……这些都不适合写在 Javadoc 中。

那么，我们该如何满足这些更高层次的文档需求？

这个问题的答案随着时间不断演变。
 在 80 年代，FrameMaker 是一个具有划时代意义的跨平台图形化技术文档工具。
 当时 Javadoc 甚至内置了一个 MIF Doclet，可以用 FrameMaker 生成美观的打印版 API 文档 —— 不过现在只剩下一个残存的 Windows 版本。

DocBook XML 提供了类似的结构性和链接能力，它是开放标准、跨平台的文档工具链。
 但其原始 XML 格式太不便于直接编写，编辑工具不仅昂贵又繁琐，即便是好用的，也难以流畅地写作。

我很高兴终于找到了更好的方案：**AsciiDoc**。
 它拥有 DocBook 的所有强大功能，却采用了易读易写的文本格式。
 简单的事情写起来非常轻松，而复杂需求也完全可以实现。

AsciiDoc 的语法和 Markdown 这类轻量级标记语言一样易于阅读和上手，很多开发者已经在各种在线讨论平台中熟悉了这类格式。
 当你需要更复杂的文档结构时，它也支持数学公式（MathML 或 LaTeX）、带编号和注释的源码高亮、各种提示块（admonition blocks）等丰富功能。

AsciiDoc 最初是 2002 年以 Python 实现推出的。
 目前的官方实现是 2013 年发布的 **Asciidoctor**，使用 Ruby 编写，但可以通过 AsciidoctorJ 在 JVM 上运行（支持 Maven 和 Gradle 插件），或转译为 JavaScript，非常适合持续集成环境。

当你需要构建一个完整的文档网站（甚至是由多个仓库组成），**Antora** 这类工具可以轻松搞定。
 AsciiDoc 社区也非常友好，过去一年它的成长令人鼓舞。
 而且，如果你关心规范化，AsciiDoc 的正式语言规范也正在标准化中。

我喜欢为我分享的项目制作丰富、漂亮的文档。
 AsciiDoc 让这件事变得容易许多，甚至让我乐在其中，愿意投入时间不断打磨和完善文档。
 我希望你也能有同样的体验。

最后再绕回开头：如果你决定全面使用 AsciiDoc，它还有一个叫做 **Asciidoclet** 的 Doclet 插件，可以让你用 AsciiDoc 来写 Javadoc！

---

## 4. Be Aware of Your Container Surroundings  

### English

------

There is a danger to containerizing legacy Java applications *as is*, with their legacy Java Virtual Machine (JVM), because the ergonomics of those older JVMs will be fooled when running inside Docker containers.

Containers have become the de facto runtime packaging mechanism.
 They provide many benefits: a certain level of isolation, improved resource utilization, the ability to deploy applications across different environments, and more.
 Containers also help reduce the coupling between an application and the underlying platform, as that application can be packaged into a portable container.

This technique is sometimes used to modernize legacy applications.
 In the case of Java, a container embeds a legacy Java application along with its dependencies, including an older version of the JVM used by that application.

------

The practice of containerizing legacy Java applications with their environments can certainly help keep older applications running on modern, supported infrastructure by decoupling them from older, unsupported infrastructure.
 But the potential benefits of such a practice come with their own set of risks due to the **JVM ergonomics**.

JVM ergonomics enables the JVM to tune itself by looking at two key environmental metrics:

- the number of CPUs
- and the available memory.

With these metrics, the JVM determines important parameters such as:

- which garbage collector to use,
- how to configure it,
- the heap size,
- the size of the `ForkJoinPool`, and so on.

------

Linux Docker container support, added in **JDK 8 update 191**, allows the JVM to rely on Linux cgroups to get the metrics of resources allocated to the container it runs in.
 Any JVM **older than that** is **not aware** that it is running within a container and will access metrics from the **host OS**, not from the container itself.

And, given that in most cases a container is configured to only use a **subset** of the host resources,
 the JVM will rely on **incorrect metrics** to tune itself.

This quickly leads to an **unstable situation** in which the container will likely get **killed by the host** as it tries to consume more resources than are available.

------

The following command shows which JVM parameters are configured by the JVM ergonomics:

```
java -XX:+PrintFlagsFinal -version | grep ergonomic
```

JVM container support is enabled by default but can be disabled by using the `-XX:-UseContainerSupport` JVM flag.
 Using this JVM flag in a container with restricted resources (CPU and memory) allows you to observe and explore the impact of JVM ergonomics with and without container support.

------

**Running legacy JVMs in Docker containers is clearly not recommended.**
 But if that is the only option, the legacy JVM should at least be configured to **not exceed the resources allocated** to the container it runs in.

The **ideal**, obvious solution is to use a **modern, supported JVM** (for example, JDK 11 or later)
 that will not only be **container-aware by default** but will also provide an **up-to-date and secure runtime**.

---

### 中文

---

将遗留 Java 应用程序原封不动地容器化（包括它们原有的 JVM）存在明显的风险，原因在于旧版 JVM 的**自适应调优机制（JVM Ergonomics）**在容器环境中会被“误导”。

------

容器如今已成为事实上的运行时打包标准。它们提供了很多好处，例如隔离性、资源利用效率、跨环境部署能力等。
 容器还能减少应用与底层平台的耦合，使得应用可以被打包成可移植单元。

因此，很多人在“现代化”旧系统时选择将遗留 Java 应用打包进容器，连同它的依赖和旧版本 JVM 一起。

这种方式**确实**可以让旧应用运行在现代受支持的基础设施上，但也会带来严重问题 —— **JVM 可能会做出错误的资源假设**，从而自我调优失败。

------

JVM 的自适应调优依赖两个环境指标：

- 可用 **CPU 数**
- 可用 **内存**

基于这些信息，JVM 会决定：

- 使用哪种垃圾回收器（GC）
- 如何配置 GC
- 堆内存大小
- `ForkJoinPool` 的线程数等参数

在旧版本的 JVM（如 JDK 8 191 之前的版本）中，JVM **不知道自己运行在容器中**。
 它会错误地去读取宿主机的资源指标，而不是容器实际分配的资源。

这在实际中会导致灾难性后果：**JVM 以为它拥有比实际更多的资源，从而试图使用超出限制的 CPU 或内存，最终导致容器被宿主机杀死。**

------

从 JDK 8u191 开始，JVM 开始支持读取 Linux cgroups，能够感知容器所分配的资源。
 这一特性在 JDK 11 中已经默认启用，无需额外配置。

要查看当前 JVM 是如何自动调优的，可以运行：

```
java -XX:+PrintFlagsFinal -version | grep ergonomic
```

你也可以通过显式关闭容器支持来比较差异：

```
-XX:-UseContainerSupport
```

------

**因此，不建议将不支持容器的旧 JVM 放进 Docker 容器中运行。**

如果确实无法升级 JVM，至少要：

- 手动配置 JVM 的最大堆大小（`-Xmx`）等参数，
- 确保它不会超出容器可用的资源。

**最理想的方案当然是升级 JVM 至受支持的版本（例如 JDK 11+）**，不仅能避免错误调优问题，还能获得安全性与性能方面的改进。

---

## 5. Behavior Is “Easy”; State Is Hard  

### English

------

When I was first introduced to object-oriented programming, some of the very first concepts taught were the triple of **polymorphism**, **inheritance**, and **encapsulation**.
 And to be honest, we spent quite some time trying to understand and code with them.
 But, at least for me, too much emphasis was given to the first two, and very little to the third and most important of all: **encapsulation**.

Encapsulation allows us to **tame the growing state and complexity** that is a constant in the software development field.
 The idea that we can internalize the state, hide it from other components, and offer only a carefully designed API surface for any state mutation is **core** to the design and coding of complex information systems.

------

But, at least in the Java world, we have **failed to spread some of the best practices** about the construction of well-encapsulated systems.
 **JavaBean properties** on anemic classes that simply expose internal state through getters and setters are common,
 and with Java Enterprise architectures we seem to have popularized the concept that most—if not all—business logic should be implemented in **service classes**.

Within them we use getters to extract the information, process them to get a result, and then put the result back into our objects with setters.

And when the bugs bite, we dig through log files, use debuggers, and try to figure out what’s happening with our code in production.
 It’s fairly “easy” to spot bugs caused by behavior issues: pieces of code doing something they’re not supposed to do.
 On the other hand, when our code **seems to be doing the right thing** and we still have bugs, it becomes much more complicated.

------

From my experience, the **hardest bugs to solve** are the ones caused by **inconsistent state**.
 You’ve reached a state in your system that shouldn’t happen, but there it is:

- a `NullPointerException` for a property that was never supposed to be null,
- a negative value that should only be positive, and so on.

The odds of finding the steps that led to such an inconsistent state are low.
 Our classes have surfaces that are **too mutable and too easily accessed**: any piece of code, anywhere in the system, can mutate our state without any kind of checks or balances.

We sanitize user-provided inputs through validation frameworks,
 but that “innocent” setter is still there allowing any piece of code to call it.

And I won’t even discuss the likelihood of someone using `UPDATE` statements directly on the database to change some columns in database-mapped entities.

------

**How can we solve this problem?**
 **Immutability** is one of the possible answers.

If we can guarantee that our objects are immutable, and the state consistency is checked on object creation,
 we’ll **never have an inconsistent state** in our system.

But we have to take into account that most Java frameworks **do not cope very well with immutability**,
 so we should at least aim to **minimize mutability**.

Having properly coded **factory methods** and **builders** can also help us to achieve this minimally mutable state.

------

Therefore, don’t generate your setters automatically.
 **Take time to think about them.**
 Do you really need that setter in your code?

And if you decide that you do—perhaps because of some framework requirement—
 consider using an **anticorruption layer** to protect and validate your internal state after those setter interactions.

---

### 中文

---

当我第一次接触面向对象编程时，最先学到的几个概念就是**多态（polymorphism）**、**继承（inheritance）**和**封装（encapsulation）**这三大支柱。
 说实话，我们花了不少时间去理解它们，并尝试用代码实现。
 但至少对我而言，当时过于强调前两个，而对最重要的——**封装**——关注却太少了。

封装使我们能够**驾驭不断增长的状态和复杂性**，这是软件开发中持续存在的挑战。
 我们可以将状态内部化，隐藏起来，不暴露给其他组件，并仅通过精心设计的 API 接口来控制状态的变化——这个思想对复杂信息系统的设计与编码至关重要。

------

但至少在 Java 世界里，我们**未能广泛传播关于构建良好封装系统的一些最佳实践**。
 我们常常看到贫血模型中的**JavaBean 属性**，它们只是简单地通过 getter 和 setter 暴露内部状态；
 而在 Java 企业架构中，我们似乎又推广了一种理念：几乎所有的业务逻辑都应当写在**服务类（service classes）**中。

在这些服务类中，我们用 getter 提取对象信息，处理完后再用 setter 把结果写回对象。

等到 bug 出现时，我们就在日志中排查、使用调试器，试图弄清楚生产环境中的代码到底发生了什么。
 如果是因为行为出错导致的 bug（例如代码做了它不该做的事），我们相对比较容易定位。
 但如果代码**看上去做的是对的事情**，却依然出现了 bug，那就难得多了。

------

从我的经验来看，**最难解决的 bug 通常是由“状态不一致”引起的**。
 系统进入了一种**本不应该发生的状态**，但它就这样发生了：

- 某个属性抛出了 `NullPointerException`，但它原本就**不应该为 null**；
- 某个值出现了负数，尽管它理应只能是正数，等等。

要追溯出导致状态不一致的那些步骤，几乎不太可能。
 我们的类**暴露得太多、太易变**：系统中任意位置的代码都可能修改状态，而且**没有任何校验机制或约束**。

虽然我们用验证框架对用户输入进行校验，
 但那个看似“无害”的 setter 依然存在，允许任意代码调用它。

我甚至都懒得提，有人可能直接用 `UPDATE` SQL 语句去修改数据库映射实体的某些列这种情况。

------

**那我们该如何解决这个问题呢？**
 一个可行的答案是：**不可变性（immutability）**。

如果我们能保证对象是不可变的，并在对象创建时就校验状态一致性，
 那么系统中就**永远不会出现不一致的状态**。

但我们也必须承认，大多数 Java 框架**对不可变对象支持并不好**，
 因此我们至少应该努力**最小化可变性**。

通过合理地编写**工厂方法（factory methods）\**和\**建造者（builders）**，
 也可以帮助我们实现“尽量不可变”的状态管理。

------

因此，不要再自动生成 setter 方法了。
 **花点时间思考一下它们的存在是否真的有必要。**
 你的代码真的需要那个 setter 吗？

如果你决定保留它——也许是因为某个框架的要求——
 请考虑使用一个**防腐层（anticorruption layer）**，在 setter 被调用后，对内部状态进行保护和验证。

---

## 6. Benchmarking Is Hard—JMH Helps  

### English

------

**Benchmarking on the JVM**, especially microbenchmarking, is hard.
 It’s not enough to throw a nanosecond measurement around a call or loop and be done.
 You have to take into account:

- **warm-up**,
- **HotSpot compilation**,
- **code optimizations** like inlining and dead code elimination,
- **multithreading**,
- **consistency of measurement**, and more.

Fortunately, **Aleksey Shipilëv**, the author of many great JVM tools, contributed **JMH**, the Java Microbenchmarking Harness, to the OpenJDK.

It consists of:

- a **small library** and
- a **build system plug-in**.

------

The library provides annotations and utilities to declare your benchmarks as annotated Java classes and methods, including a `BlackHole` class to **consume generated values to avoid code elimination**.
 The library also offers **correct state handling** in the presence of multithreading.

The build system plug-in generates a **JAR with the relevant infrastructure code** for:

- running and measuring the tests correctly,
- including **dedicated warm-up phases**,
- proper multithreading,
- running multiple forks and **averaging across them**, and much more.

The tool also **outputs important advice** on how to use the gathered data and **limitations** thereof.

------

Here is an example for measuring the impact of **presizing collections**:

```java
public class MyBenchmark {
    static final int COUNT = 10000;

    @Benchmark
    public List<Boolean> testFillEmptyList() {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            list.add(Boolean.TRUE);
        }
        return list;
    }

    @Benchmark
    public List<Boolean> testFillAllocatedList() {
        List<Boolean> list = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            list.add(Boolean.TRUE);
        }
        return list;
    }
}
```

To generate the project and run it, you can use the **JMH Maven archetype**:

```bash
mvn archetype:generate \
  -DarchetypeGroupId=org.openjdk.jmh \
  -DarchetypeArtifactId=jmh-java-benchmark-archetype \
  -DinteractiveMode=false \
  -DgroupId=com.example \
  -DartifactId=coll-test \
  -Dversion=1.0

cd coll-test

# Add com/example/MyBenchmark.java, then:
mvn clean install
java -jar target/benchmarks.jar -w 1 -r 1
```

------

Example output:

```
# JMH version: 1.21
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.example.MyBenchmark.testFillEmptyList

Result "com.example.MyBenchmark.testFillEmptyList":
  30966.686 ±(99.9%) 2636.125 ops/s [Average]
  (min, avg, max) = (18885.422, 30966.686, 35612.643),
  stdev = 3519.152
  CI (99.9%): [28330.561, 33602.811]
```

------

⚠️ **REMEMBER**: The numbers below are just data. To gain reusable insights, you need to:

- follow up on *why* the numbers are the way they are,
- use **profilers** (`-prof`, `-lprof`),
- design **factorial experiments**,
- perform **baseline and negative tests**,
- ensure the benchmarking environment is **safe on JVM/OS/HW** level,
- **ask for reviews from domain experts**.

Do not assume the numbers tell you what you want them to tell.

------

**Benchmark Summary**

| Benchmark Method        | Mode  | Count | Score (ops/s) | Error (±) |
| ----------------------- | ----- | ----- | ------------- | --------- |
| `testFillAllocatedList` | thrpt | 25    | 56786.708     | ±1609.633 |
| `testFillEmptyList`     | thrpt | 25    | 30966.686     | ±2636.125 |

We see that our **preallocated collection** is almost **twice as fast** as the default instance,
 because it doesn’t have to be resized during the addition of elements.

------

**JMH** is a powerful tool in your toolbox to write **correct microbenchmarks**.
 If you run them in the same environment, they are even **comparable**, which should be the **main way of interpreting their results**.
 They can also be used for **profiling purposes**, as they provide **stable, repeatable results**.
 Aleksey has much more to say about the topic if you’re interested.

---

### 中文

------

在 JVM 上进行基准测试（Benchmarking），尤其是微基准测试（microbenchmarking），**是一件很难的事情**。
 你不能只是简单地在某个调用或循环周围套上一个纳秒计时器就完事了。
 你必须考虑以下因素：

- **预热（warm-up）**，
- **HotSpot 编译**，
- **代码优化**（如内联、死代码消除），
- **多线程**，
- **测量结果的一致性**，等等。

幸运的是，许多优秀 JVM 工具的作者 **Aleksey Shipilëv** 向 OpenJDK 贡献了 **JMH**（Java Microbenchmarking Harness，Java 微基准测试工具）。

它由以下两部分组成：

- 一个**小型库**，
- 以及一个**构建系统插件**。

------

该库提供了一系列注解和工具，可以将你的基准测试代码声明为带注解的 Java 类和方法。
 包括一个 `BlackHole` 类，用于**消费生成的值，防止代码被优化掉**。
 这个库还提供了在**多线程场景下正确管理状态**的功能。

构建系统插件会生成一个**包含必要基础设施代码的 JAR 包**，用于：

- 正确运行和测量测试，
- 包括**专用的预热阶段**，
- 支持多线程，
- 支持多次 fork 并**取平均结果**，以及其他高级功能。

该工具还会输出一些**关于如何使用收集到的数据的建议**以及其**局限性**。

------

以下是一个**预设集合大小（presizing collections）影响**的基准测试示例：

```java
public class MyBenchmark {
    static final int COUNT = 10000;

    @Benchmark
    public List<Boolean> testFillEmptyList() {
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            list.add(Boolean.TRUE);
        }
        return list;
    }

    @Benchmark
    public List<Boolean> testFillAllocatedList() {
        List<Boolean> list = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            list.add(Boolean.TRUE);
        }
        return list;
    }
}
```

你可以使用 **JMH 的 Maven 原型（archetype）**来生成项目并运行：

```bash
mvn archetype:generate \
  -DarchetypeGroupId=org.openjdk.jmh \
  -DarchetypeArtifactId=jmh-java-benchmark-archetype \
  -DinteractiveMode=false \
  -DgroupId=com.example \
  -DartifactId=coll-test \
  -Dversion=1.0

cd coll-test

# 添加 com/example/MyBenchmark.java 文件后执行：
mvn clean install
java -jar target/benchmarks.jar -w 1 -r 1
```

------

示例输出：

```
# JMH version: 1.21
# Warmup: 5 iterations, 1 s each
# Measurement: 5 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: com.example.MyBenchmark.testFillEmptyList

Result "com.example.MyBenchmark.testFillEmptyList":
  30966.686 ±(99.9%) 2636.125 ops/s [Average]
  (min, avg, max) = (18885.422, 30966.686, 35612.643),
  stdev = 3519.152
  CI (99.9%): [28330.561, 33602.811]
```

------

⚠️ **请牢记**：下面的数字只是“数据”。
 要获得有价值的洞察，你需要：

- 深入思考这些数值**为什么是这样**，
- 使用**分析器（profiler）**（如 `-prof`、`-lprof`），
- 设计**因子实验（factorial experiments）**，
- 执行**基线测试和负面测试**，
- 确保你的基准测试环境在 **JVM / 操作系统 / 硬件层面是稳定的**，
- **请专家审查你的测试设计和结果**。

不要想当然地认为这些数字能告诉你你想听的结论。

------

**基准测试摘要**

| 基准方法                | 模式  | 次数 | 吞吐率 (ops/s) | 误差 (±)  |
| ----------------------- | ----- | ---- | -------------- | --------- |
| `testFillAllocatedList` | thrpt | 25   | 56786.708      | ±1609.633 |
| `testFillEmptyList`     | thrpt | 25   | 30966.686      | ±2636.125 |

我们可以看到，**预先分配容量的集合**性能几乎是默认实例的**两倍**，
 因为它在添加元素时**无需调整容量**。

------

**JMH** 是你编写**正确微基准测试**的重要工具。
 如果你在相同环境中运行，它们的结果甚至可以**相互比较**，这是**解释测试结果的主要方式**。
 它还可以用于**性能分析**，因为它提供了**稳定、可重复的测试结果**。
 如果你感兴趣，Aleksey 还有更多深入见解可供学习。

---

## 7. The Benefits of Codifying and Asserting Architectural Quality  

### English

------

Your continuous delivery build pipeline should be the primary location where agreed-upon architectural qualities for your applications are codified and enforced. However, these automated quality assertions shouldn’t replace continued team discussions about standards and quality levels, and they should definitely not be used to avoid intra- or inter-team communication. That said, checking and publishing quality metrics within the build pipeline can prevent the gradual decay of architectural quality that might otherwise be hard to notice.

If you’re wondering why you should test your architecture, the ArchUnit motivation page has you covered. It starts with a familiar story: once upon a time, an architect drew a series of nice architectural diagrams that illustrated the components of the system and how they should interact. Then the project got bigger and use cases more complex, new developers dropped in and old developers dropped out. This eventually led to new features being added in any way that fit. Before long, everything depended on everything, and any change could have an unforeseeable effect on any other component. I’m sure many readers will recognize this scenario.

ArchUnit is an open source, extensible library for checking the architecture of your Java code by using a Java unit-test framework like JUnit or TestNG. ArchUnit can check for cyclic dependencies and check dependencies between packages and classes, and layers and slices, and more. It does all this by analyzing Java bytecode and importing all classes for analysis.

To use ArchUnit in combination with JUnit 4, include the following dependency from Maven Central:

```xml
<dependency>
  <groupId>com.tngtech.archunit</groupId>
  <artifactId>archunit-junit</artifactId>
  <version>0.5.0</version>
  <scope>test</scope>
</dependency>
```

At its core, ArchUnit provides infrastructure to import Java bytecode into Java code structures. You can do this using `ClassFileImporter`. You can make architectural rules such as “services should be accessed only by controllers” by using a DSL-like fluent API, which can in turn be evaluated against imported classes:

```java
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

// ...
@Test
public void Services_should_only_be_accessed_by_Controllers() {
    JavaClasses classes =
        new ClassFileImporter().importPackages("com.mycompany.myapp");

    ArchRule myRule = ArchRuleDefinition.classes()
        .that().resideInAPackage("..service..")
        .should().onlyBeAccessed()
        .byAnyPackage("..controller..", "..service..");

    myRule.check(classes);
}
```

Extending the preceding example, you can also enforce more layer-based access rules using this test:

```java
@ArchTest
public static final ArchRule layer_dependencies_are_respected =
    layeredArchitecture()
        .layer("Controllers").definedBy("com.tngtech.archunit.eg.controller..")
        .layer("Services").definedBy("com.tngtech.archunit.eg.service..")
        .layer("Persistence").definedBy("com.tngtech.archunit.eg.persistence..")
        .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
        .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
        .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");
```

You can also ensure that naming conventions such as class name prefixes are followed, or specify that a class named a certain way must be in an appropriate package. GitHub contains a host of ArchUnit examples to get you started and give you ideas.

You could attempt to detect and fix all of the architectural issues mentioned here by having an experienced developer or architect look at the code once a week, identify violations, and correct them. However, humans are notorious for not acting consistently and, when the inevitable time pressures are placed on a project, often the first thing to be sacrificed is manual verification.

A more practical method is to codify the agreed-upon architectural guidelines and rules using automated tests, using ArchUnit or another tool, and include them as part of your continuous integration build. Any issues can then be quickly detected and fixed by the engineer who caused the issue.

---

### 中文

------

你的持续交付构建流水线应该是**落实和强化系统架构质量要求的主要场所**。不过，这些自动化的质量检查**不应替代团队对标准和质量水平的持续讨论**，更不应该被用来回避团队内部或团队之间的沟通。话虽如此，在构建流水线中检查并发布质量指标，可以有效地**防止架构质量的逐渐退化**——这种退化在日常开发中往往难以察觉。

如果你还在疑惑为什么要测试你的架构，可以参考 ArchUnit 的动机页面。它讲了一个常见的故事：曾几何时，架构师画出了一系列漂亮的架构图，展示了系统的各个组件以及它们之间应有的交互方式。但随着项目扩大、用例变复杂，新开发者加入、旧成员离开，新功能被随意添加，只要能实现需求就行。最后，系统变成了“万物互相依赖”，任何变更都可能对其他组件产生不可预知的影响。相信很多读者都经历过这种情况。

**ArchUnit 是一个开源的、可扩展的库**，用于在 Java 中检查代码的架构结构，它可以配合 JUnit 或 TestNG 等 Java 单元测试框架使用。ArchUnit 能检测**循环依赖**，检查**包、类、层级之间的依赖关系**，并支持更多功能。它通过分析 Java 字节码并导入所有类来实现这些功能。

要将 ArchUnit 与 JUnit 4 配合使用，可以添加如下 Maven 依赖：

```xml
<dependency>
  <groupId>com.tngtech.archunit</groupId>
  <artifactId>archunit-junit</artifactId>
  <version>0.5.0</version>
  <scope>test</scope>
</dependency>
```

ArchUnit 的核心功能是将 Java 字节码导入为可供 Java 使用的代码结构，可以使用 `ClassFileImporter` 实现。你可以使用类似 DSL 的流式 API 编写架构规则，例如“服务只能被控制器访问”，然后对导入的类进行验证：

```java
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

// ...
@Test
public void Services_should_only_be_accessed_by_Controllers() {
    JavaClasses classes =
        new ClassFileImporter().importPackages("com.mycompany.myapp");

    ArchRule myRule = ArchRuleDefinition.classes()
        .that().resideInAPackage("..service..")
        .should().onlyBeAccessed()
        .byAnyPackage("..controller..", "..service..");

    myRule.check(classes);
}
```

在上述例子的基础上，你还可以编写更复杂的分层访问控制规则，例如：

```java
@ArchTest
public static final ArchRule layer_dependencies_are_respected =
    layeredArchitecture()
        .layer("Controllers").definedBy("com.tngtech.archunit.eg.controller..")
        .layer("Services").definedBy("com.tngtech.archunit.eg.service..")
        .layer("Persistence").definedBy("com.tngtech.archunit.eg.persistence..")
        .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
        .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
        .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");
```

你还可以用 ArchUnit 检查命名规范，比如类名是否以特定前缀开头，或者某些命名的类是否位于合适的包中。GitHub 上有很多 ArchUnit 示例项目，可以帮助你快速上手并获得灵感。

当然，你也可以每周安排一个资深开发者或架构师审查代码，查找并修复这些架构问题。但众所周知，人类难以持续保持一致性，并且一旦项目压力增大，最先被牺牲的通常就是这些人工检查。

一种更务实的方法是：**使用 ArchUnit 或其他工具，把约定好的架构准则编码为自动化测试**，并将其纳入持续集成流水线中。这样，一旦出现问题，触发它的开发者就可以**立即检测并修复问题**。

---

## 8. Break Problems and Tasks into Small Chunks  

### English

------

You’re learning to program. You receive a small assignment. You write under a thousand lines of code. You type it in and test. Then you add print statements or use a debugger. Maybe you get coffee. Then you puzzle over what you were thinking.

Sound familiar? And that’s just a toy problem. Work tasks and systems are far larger. Big problems take time to solve. And worse, there is too much to hold in your brain’s RAM.

A good way to deal with this is to break the problem into small chunks. The smaller the better. If you can get that one small piece working, then you don’t have to think about it anymore and can move on to the next piece. When doing this well, you want to write automated tests for each small problem. You should also commit frequently. That gives you a rollback point when things don’t work as expected.

I remember helping out a teammate who was stuck. I asked when he had last committed, because the easiest fix would be to roll back and reapply the change. The answer was “a week ago.” Then he had two problems: the original one and that I wouldn’t help him debug a week’s worth of work.

After that experience, I ran a training session for my team on how to break tasks into smaller chunks. I was told by the senior developers that their tasks were “special” and “couldn’t possibly be broken up.” When you hear the word *special* in relation to a task, you should immediately be suspicious.

I decided to schedule a second meeting. Everyone was responsible for bringing an example of a “special” task, and I would help them break it up. The first example was a screen that was scheduled to take two weeks to develop. I split it up like this:

- Create a hello world screen at the right URL—no fields, just prints hello world.
- Add functionality to display a list from a database.
- Add a text area.
- Add a select pull-down.
- \<A long list of more tiny tasks\>

And guess what? After each of these tiny tasks, there could be a commit. This means commits could happen many times a day.

Then I was told that this could be done for screens, but file processing was “special.” Now what did I say about the word *special*? I split that up as well:

- Read a line from the file.
- Validate the first field, including the database call.
- Validate the second field and transform it using business logic.
- \<A bunch of fields later\>
- Apply the first business logic rule to all fields.
- \<A bunch of rules later\>
- Add a message to the queue.

Again, the task wasn’t special. If you think a task is special, pause and think about why. Often you will find this technique still applies.

Finally, a developer told me he couldn’t commit his code in any less than a week. The task wound up being reassigned to me. I did some extra committing to make a point. Counting, I committed 22 times in the 2 days it took to me complete the task. Maybe if he’d committed more frequently, it would have been done faster!

---

### 中文

------

你正在学习编程。你接到一个小任务，写了不到一千行代码。你把代码敲进去，然后测试一下。接着加上打印语句，或者用调试器。可能你还去喝了杯咖啡。然后，你开始苦思冥想：“我当时到底在想什么？”

听起来熟悉吗？而这还只是个小练习题。工作中的任务和系统要大得多。**大问题需要时间解决**，更糟的是，有太多细节你无法全部装进大脑的“内存”里。

一个应对方式是：**把问题拆成尽可能小的部分**。越小越好。只要能把一个小模块搞定了，你就不用再操心它，可以继续处理下一个模块。如果你做得好，还应该**为每一个小问题写自动化测试**，并且**频繁地提交代码**。这样一旦出错，就可以回滚。

我记得有次帮一个卡住的同事。我问他上次提交是什么时候，因为最简单的解决方案就是回滚再重做。他说：“一周前。”那他现在就有两个问题了：一个是原来的 bug，另一个是——我不会帮他调试一周的代码。

经历这事后，我给团队做了一次培训，讲解如何把任务拆分成小块。结果有资深开发者告诉我，他们的任务“很特殊”，“根本没法拆”。**当你听到有人说任务“很特殊”时，你就应该提高警惕了。**

于是我安排了第二次会议。每个人都得带一个“特殊”的任务来，我来帮他们拆解。第一个例子是一个预估需要两周开发的界面。我这样拆分它：

- 在正确的 URL 上创建一个 Hello World 页面——不用任何字段，只显示 Hello World。
- 添加显示数据库列表的功能。
- 添加一个文本输入框。
- 添加一个下拉选择框。
- （后面还有一长串的小任务）

你猜怎么着？**每完成一个小任务，就可以提交一次代码。**这意味着一天可以提交很多次。

然后有人说，这种方法对界面开发是可行的，但“文件处理”就“很特殊”。我之前说过什么来着？一听“特殊”就得警惕。我又把文件处理任务拆成这样：

- 读取文件中的一行。
- 验证第一个字段，包括调用数据库。
- 验证第二个字段，并用业务逻辑转换它。
- （若干字段之后）
- 对所有字段应用第一个业务规则。
- （更多业务规则）
- 将一条消息加入队列。

还是那句话，这个任务并不特殊。如果你觉得一个任务特殊，**请停下来思考一下为什么**。很多时候，你会发现这套拆解技巧照样适用。

最后，有个开发者说他**至少得写一周的代码才能提交一次**。任务后来转给了我。我为了证明一个观点，特地多提交了几次。在我花了两天完成这个任务的过程中，我提交了 **22 次**。也许，如果他提交得更频繁些，任务早就完成了！

---

## 9. Build Diverse Teams  

### English

------

Years ago, a good doctor knew it all, did it all: set a fracture, performed surgery, drew blood. A good doctor was independent and self-sufficient, and autonomy was highly valued.

Fast forward to today. Knowledge has exploded, surpassing the individual and bringing about specialization. In order to provide an adequate solution from beginning to end, many specialists will be involved, and different teams will have to interact.

This is true in software development as well.

Cooperation is now one of the highest-valued traits in “good” professionals. In the past, independence and self-sufficiency was enough to be “good.” Now we all need to behave like pit crews: team members.

The challenge is to build teams that are both successful and diverse. Four types of diversity—industry background, country of origin, career path, and gender—positively correlate with innovation. In a homogenous team, regardless of academic background, there may be redundant perspectives. Women, for example, bring disruptive innovation.

How big is the impact? In management teams with a high gender diversity, an increase of 8% in revenue from innovation has been observed.

Differences among group members can also be a source of insight—members with different backgrounds, experiences, and ideas increase the pool of information, skills, and networks. With more perspectives, reaching consensus requires constructive debate. If the environment where ideas are exchanged is positive, creative solutions will emerge naturally.

But increasing group diversity is not an easy task. Conflict can arise when heterogeneous groups don’t communicate effectively or divide themselves into factions. People prefer to collaborate with those similar to them. A close-knit group will develop its own language and culture, and outsiders will be distrusted. Distance, along with the pitfalls of mishaps in digital communication, make software teams especially prone to the problems of “us versus them” and incomplete information.

So how do we get the benefits of diversity and avoid the drawbacks? The key in collaboration is developing psychological safety and trust within your team. When we are surrounded with people we can trust, even if they are different from us, we’re more confident to take risks and experiment. When we trust each other, we can look to others to provide information or perspective that will help solve a challenging problem, thus creating opportunities for cooperation. We can overcome vulnerable situations when feedback is requested.

In teams with psychological safety, it’s easier for people to believe that the benefits of speaking up outweigh the costs. Participation leads to less resistance to change, and the more frequently people participate, the more likely they are to offer novel ideas.

Personality matters in software development, too; it’s equally important to build an environment of trust for different personalities. We all have a colleague who is willing to test every new library, framework, or tool, someone thinking how to use or explore the new shiny red toy, sometimes with surprising results. Some are inclined to establish new processes, code format styles, or templates for commit messages, and will remind us when we are not following proper procedure. You may have teammates who will underpromise and overdeliver, and ones who are thinking of everything that can go wrong: updating dependencies, installing patches, security risks, etc.

Consider everyone’s differences, and don’t push too hard.

We can increase diversity in our teams in two dimensions: background and personality. If we have good team dynamics and continue to build trust in each other, we will be more successful as programmers.

---

### 中文

------

很多年前，一个好医生什么都会：接骨、动手术、抽血。好医生是独立自主的，自给自足的，**自主性**是最受重视的品质。

快进到今天，知识已经爆炸式增长，超出了个人可以掌握的范围，这也催生了**专业化分工**。如今，为了从头到尾提供一个完整的解决方案，往往需要多个专家参与，不同的团队必须协作配合。

**软件开发也是如此。**

如今，**协作能力**已成为“优秀”专业人士最重要的特质之一。过去，有独立能力就够格。现在，我们都需要像**赛车维修团队**那样行动：彼此配合、分工明确。

但打造一个**既成功又多元**的团队并不简单。研究发现，有四种“多样性”有助于推动创新：

- 行业背景
- 国家/地区
- 职业路径
- 性别

如果团队成员背景相似，即使学历高，也容易视角雷同。比如女性往往能带来“颠覆式创新”。

**影响到底有多大？**管理团队中如果性别多样性高，来自创新的收入可能提高 **8%**。

成员之间的差异也能带来洞察力——不同的背景、经验和思维方式能扩大信息、技能与人脉的覆盖范围。**视角越多，要达成共识就越需要建设性讨论。**如果团队氛围积极，讨论就会自然带来更具创造力的解决方案。

但要提升团队多样性并不容易。**如果异质团队沟通不畅，就容易产生冲突或内部分裂。**人总是更愿意和自己相似的人合作。一个关系紧密的小团体会发展出属于自己的“语言”和文化，而对外人产生不信任。再加上远程合作、数字沟通常出现误解，软件团队就更容易陷入“我们 vs 他们”的困境与信息不对称的问题。

那么，如何获得多样性的好处，同时避免坏处？**关键在于建立“心理安全感”与信任。**

当我们身边是值得信任的人，即便他们与我们不同，我们也更敢冒险、敢尝试。信任他人时，我们愿意向他们求助，获取新的信息或观点，从而解决复杂问题、创造协作机会。我们甚至可以坦然地请求反馈、面对脆弱。

在一个有心理安全感的团队中，人们更容易相信——**说出自己的想法，带来的好处大于风险。**积极参与能减少对变革的抵触，也更可能带来新的创意。

**在软件开发中，“性格”同样重要。**要为不同性格的同事构建信任氛围。你可能有这样的同事：

- 喜欢尝鲜，总是在试用新的库、框架、工具，有时会带来意想不到的惊喜；
- 热衷于建立流程、制定代码风格、提交模板，并会在你违反规范时提醒你；
- 喜欢低调许诺、高效交付；
- 总是担心各种潜在风险，比如依赖项更新、打补丁、安全隐患等。

**要学会接纳彼此的不同，也别强迫他人接受你的方式。**

我们可以从两个维度提升团队多样性：

1. 背景（教育、行业、经历等）
2. 性格（谨慎型、探索型、规整型等）

如果我们拥有良好的团队氛围，**持续建立彼此信任**，我们就能成为更成功的软件开发者。

---

## 10. Builds Don’t Have To Be Slow and Unreliable  

### English

---

A while back, I was working at an early-stage start-up where the codebase and development team were growing every day. As we added more and more tests, the builds were taking longer and longer to run. At around the eight-minute mark I started to notice it, which is why I remember that specific number. From eight minutes, build times nearly doubled. At first, it was kinda nice. I would kick off a build, go grab a coffee, and chat with coworkers on other teams. But after a few months, it became irritating. I’d had enough coffee and I knew what everyone was working on, so I would check Twitter or help other developers on my team while waiting for my builds to finish. I would then have to context switch when I went back to my work.

The build was also unreliable. As is normal for any software project, we had a number of flaky tests. The first, albeit naive, solution was to turn off the tests (i.e., @Ignore) that were failing. Eventually, it got to the point where it was easier to push the changes and rely on the continuous integration (CI) server than to run the tests locally. The problem with this tactic was that it moved the problem further down the line. If a test failed at the CI step, it took much longer to debug. And if a flaky test passed initially and only showed up after merging, it blocked the entire team until we determined whether it was a legitimate issue.

Frustrated, I tried to fix some of the problematic tests. One test in particular stands out in my mind. It only appeared when the entire test suite ran, so each time I made a change, I had to wait 15-plus minutes for feedback. These incredibly long feedback cycles and a general lack of relevant data meant I wasted days tracking down this bug.

This isn’t just about one company, though. One of the advantages of being a job hopper is that I’ve seen the way many different teams work. I thought these issues were normal until I started at a company where we work on exactly these problems.

Teams that follow Developer Productivity Engineering, the practice and philosophy of improving developer experience through data, are able to improve their slow and unreliable builds. These teams are happier and have higher throughput, making the business happier too. No matter what build tool they are using, the people responsible for developer productivity can effectively measure build performance and track outliers and regressions for both local and CI builds. They spend time analyzing the results and finding bottlenecks in the build process. When something does go wrong, they share the reports (e.g., Gradle build scans) with teammates and compare failing and passing builds to pinpoint the exact problem—even if they can’t reproduce the issues on their own machines.

With all this data, they can actually do something to optimize the process and reduce the frustration developers are facing. This work is never done, so they keep iterating to maintain developer productivity. It’s not an easy task, but the teams who work at it are able to prevent the problems I described from happening in the first place.

---

### 中文

------

不久前，我在一家早期创业公司工作。公司的代码库和开发团队每天都在扩张。随着我们不断添加测试用例，**构建（build）所需的时间也越来越长**。我记得是在构建耗时达到 **8 分钟**的时候开始注意到这个问题的，这也是我记得这个具体数字的原因。后来，构建时间几乎翻倍。

起初这还挺惬意的。我会在启动构建之后去拿杯咖啡，顺便和其他团队的同事聊聊天。但几个月后，这就开始让我烦了：咖啡喝够了，别人的工作也都了解了，于是我就开始刷 Twitter，或者趁机帮帮其他开发者。可当我回到自己的任务时，总得**重新切换上下文**，非常打断思路。

更糟的是，构建也不可靠。像所有软件项目一样，我们有一些**不稳定的测试（flaky tests）**。最初（也最幼稚）的做法是直接把失败的测试标记为 `@Ignore`，暂时关闭它们。最终，情况变成了：**与其本地跑测试，不如直接推代码上 CI**。因为 CI 会自动构建和运行测试。

但这种策略的副作用是：问题被**推迟**了。如果测试在 CI 阶段才失败，那调试的时间成本就高得多。而如果某个不稳定测试在刚开始是通过的，但在 merge 之后才出问题，**整个团队都被卡住了**，我们不得不花时间去判断这到底是不是个真实的 bug。

我尝试去修复一些问题测试。有一个测试我记得特别清楚，它**只有在整个测试套件一起跑的时候才会失败**，所以每改一次代码，我都得等上 **15 分钟以上** 才能获得反馈。**反馈周期太长**，再加上**缺乏有效信息**，让我在追踪这个 bug 上浪费了好几天时间。

但这不仅仅是某一家公司才有的问题。换工作多了之后，我见识了许多不同团队的工作方式。我曾以为这些问题是“行业常态”，直到我进入了一家**专门解决这些问题的公司**。

有些团队会采用一种方法论，叫做 **开发者生产力工程（Developer Productivity Engineering）**，它的核心理念就是通过**数据**来改善开发体验。

采用这种方法的团队，能有效改进构建速度慢和测试不稳定的问题。这类团队不仅开发者更开心，产出也更高，连公司业务层面也能受益。

不论你用的是哪种构建工具，这些专注开发者效率的团队都可以：

- **监控构建性能**；
- **追踪异常和性能回退**；
- **同时关注本地构建和 CI 构建**。

他们会花时间**分析数据、识别构建瓶颈**。一旦出了问题，他们会**生成构建报告（如 Gradle Build Scan）**，并与队友分享，用于对比成功与失败的构建，以便精准定位问题，即便问题无法在自己的机器上复现也一样。

正是因为有了这些数据，团队**才能真正做出优化**，缓解开发者面临的挫败感。这项工作永无止境，但**那些愿意持续投入的团队，可以预防我前面所描述的所有问题的发生**。

---

## 11. “But It Works on My Machine!”  

### English

------

Have you ever joined a new team or project and had to try to find your way around the infrastructure needed to build the source code on your developer’s machine? You’re not alone, and you may have had questions:

- What JDK version and distribution are required to compile the code?
- What if I’m running Linux, but everyone else is on Windows?
- What IDE do you use, and which version do I need?
- What version of Maven or other build tool do I need to install to properly run through developer workflows?

I hope the answer you got to these questions wasn’t “Let me have a look at the tools installed on my machine”—every project should have a clearly defined set of tools that are compatible with the technical requirements to compile, test, execute, and package the code. If you’re lucky, these requirements are documented in a playbook or wiki, although as we all know, documentation easily becomes outdated, and keeping the instructions in sync with the latest changes takes concerted effort.

There’s a better way to solve the problem. In the spirit of infrastructure as code, tooling providers came up with the *wrapper*, a solution that helps with provisioning a standardized version of the build tool runtime without manual intervention. It wraps the instructions required to download and install the runtime. In the Java space, you’ll find the **Gradle Wrapper** and the **Maven Wrapper**. Even other tooling, like Bazel, Google’s open source build tool, provides a launching mechanism.

Let’s see how the Maven Wrapper works in practice. You have to have the Maven runtime installed on your machine to generate the so-called Wrapper files. Wrapper files represent the scripts, configuration, and instructions every developer of the project uses to build the project with a predefined version of the Maven runtime. Consequently, those files should be checked into SCM alongside the project source code for further distribution.

The following runs the Wrapper goal provided by the Takari Maven plug-in:

```
mvn -N io.takari:maven:0.7.6:wrapper
```

The following directory structure shows a typical Maven project augmented by the Wrapper files, marked in **bold**:

```
.
├── .mvn
│   └── wrapper
│       ├── MavenWrapperDownloader.java
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    └── ...
```

With the Wrapper files in place, building the project on any machine is straightforward: run your desired goal with the `mvnw` script. The script automatically ensures the Maven runtime will be installed with the predefined version set in `maven-wrapper.properties`. Of course, the installation process is only invoked if the runtime isn’t already available on the system.

The following command execution uses the script to run the goals `clean` and `install` on a Linux, Unix, or macOS system:

```
./mvnw clean install
```

On Windows, use the batch script ending with the file extension `.cmd`:

```
mvnw.cmd clean install
```

What about running typical tasks in the IDE or from your CI/CD pipeline? You’ll find other execution environments derive the same runtime configuration from the Wrapper definition as well. You just have to ensure the Wrapper scripts are called to invoke the build.

Gone are the days of “But it works on my machine!”—**standardize once, build everywhere!** Introduce the wrapper concept to any JVM project to improve build reproducibility and maintainability.

---

### 中文

------

你是否曾经加入一个新的团队或项目，然后不得不自己摸索如何在开发者本地机器上构建源代码所需的基础设施？如果是的话，你并不孤单，也可能有过类似的疑问：

- 编译代码需要使用哪个版本和哪个发行版的 JDK？
- 如果我用的是 Linux，但其他人用的是 Windows，会不会有问题？
- 我应该使用什么 IDE？需要哪个版本？
- 需要安装哪个版本的 Maven（或其他构建工具）才能顺利执行开发流程？

希望你当时听到的答案不是“让我看看我机器上装了什么”。**每个项目都应该有一套明确定义的工具链，确保它们符合构建、测试、运行和打包代码的技术要求。**

如果你运气好，团队可能在 playbook 或 wiki 中有记录这些要求。但众所周知，文档很容易过时，而保持说明文档与最新变更同步则需要持续的投入。

不过其实有更好的解决方案。秉承“基础设施即代码”的精神，各种工具的开发者提出了一个叫做 **wrapper（包装器）** 的方法，用于**自动化提供标准化的构建工具运行时环境**。wrapper 负责封装所有下载和安装工具运行时所需的说明。

在 Java 领域，你会遇到：

- **Gradle Wrapper**
- **Maven Wrapper**

甚至像 Google 的开源构建工具 **Bazel**，也有自己的 launcher（启动器）机制。

我们来看看 **Maven Wrapper** 在实际中的使用方式。

要使用 Wrapper，你首先需要在本地安装 Maven 运行时，以生成所谓的 Wrapper 文件。这些文件包括脚本、配置、说明等，用于确保**所有开发者都用预定义的 Maven 版本来构建项目**。因此，**这些 Wrapper 文件应当与项目源码一起提交到版本控制系统（SCM）中**。

下面这条命令使用了 Takari 提供的 Maven 插件，来生成 Wrapper：

```bash
mvn -N io.takari:maven:0.7.6:wrapper
```

生成之后，项目目录结构如下，其中加粗的部分为 Wrapper 文件：

```
.
├── .mvn
│   └── wrapper
│       ├── MavenWrapperDownloader.java
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    └── ...
```

有了这些 Wrapper 文件，**任何机器上构建项目都非常简单**。直接运行 `mvnw` 脚本即可。该脚本会根据 `maven-wrapper.properties` 中定义的 Maven 版本自动安装运行时（如果系统中还没有）。

在 Linux、Unix 或 macOS 上运行以下命令：

```bash
./mvnw clean install
```

在 Windows 上则使用带 `.cmd` 后缀的批处理脚本：

```cmd
mvnw.cmd clean install
```

那 IDE 或 CI/CD 环境呢？其实其他运行环境也可以使用 Wrapper 提供的同一套运行时配置。只要确保这些环境调用 Wrapper 脚本来执行构建任务就可以了。

**“在我机器上可以跑”** 这样的借口将成为过去。现在你可以：

> **一次标准化，到处构建！**

将 wrapper 概念引入任何 JVM 项目中，能有效提升构建的**可重现性**和**可维护性**。

---

## 12. The Case Against Fat JARs  

### English

------

In modern Java web development, the thought of packaging and running applications in anything other than a fat JAR is almost becoming heretical. However, there can be distinct disadvantages to building and deploying these artifacts. One obvious issue is the typically large size of fat JARs, which can consume excess storage space and network bandwidth. In addition, the monolithic build process can take a long time and cause developers to context switch while waiting. The lack of shared dependencies can also cause inconsistency across the use of utilities, such as logging, and challenges with integration of communication or serialization across services.

The use of fat JARs for deploying Java applications became popular alongside the rise of the microservice architecture style, DevOps, and cloud-native technologies, such as public cloud, containers, and orchestration platforms. As applications were being decomposed into a collection of smaller services that were being run and managed independently, it made sense from an operational perspective to bundle all of the application code into a single executable binary. A single artifact is easier to keep track of, and the standalone execution removes the need to run additional application servers.

However, some organizations are now bucking the trend and creating **“skinny JARs.”** The HubSpot engineering team has discussed how the challenges listed above were impacting their development life cycle in a blog post, *“The Fault in Our JARs: Why We Stopped Building Fat JARs.”* They ultimately created a new Maven plug-in: **SlimFast**.

This plug-in differs from the classic Maven Shade plug-in that the majority of Java developers are familiar with, in that it separates the application code from the associated dependencies and accordingly builds and uploads two separate artifacts. It may sound inefficient to build and upload the application dependencies separately, but this step occurs only if the dependencies have changed. With many applications the dependencies change infrequently, and so this step is often a no-op; the package dependencies’ JAR file is uploaded to remote storage only a minimal number of times.

The SlimFast plug-in uses the Maven JAR plug-in to add a `Class-Path` manifest entry to the skinny JAR that points to the dependencies’ JAR file, and generates a JSON file with information about all the dependency artifacts in S3 so that these can be downloaded later.

At deploy time:

- The build downloads all of the application’s dependencies.
- These artifacts are cached on each of the application servers, so this step is usually a no-op as well.

**Net result:**

- At build time, only the application’s skinny JAR is uploaded to remote storage, typically only a few hundred kilobytes.
- At deploy time, only this thin JAR needs to be downloaded to the target environment, which takes a fraction of a second.

One of the core ideas behind the emergence of DevOps is that the development and operations team (and all the other teams) should work together for a common goal. The choice of deployment artifact format is an important decision within the goal of being able to continuously deploy functionality to end users. Everyone should collaborate in order to understand the requirements in relation to how this impacts the developer experience and ability to manage resources involved in deploying.

The SlimFast plug-in is currently tied to **AWS S3** for the storage of artifacts, but the code is available on GitHub, and the principles can be adapted for any type of external storage.

---

### 中文

---

在现代 Java Web 开发中，几乎没人再考虑使用 fat JAR 之外的方式来打包和运行应用程序。**Fat JAR（胖 JAR）** 的方式已经成为一种主流，反其道而行几乎成了“异端”。然而，这种做法也有不少明显的劣势。

首先，fat JAR 往往体积庞大，占用存储空间和网络带宽。其次，这种“单体式”构建过程耗时较长，常常迫使开发者在等待时进行上下文切换。再者，由于不共享依赖库，多个服务之间可能在日志记录等工具库的使用上出现不一致，也会影响服务之间的通信或序列化集成。

fat JAR 的流行是随着微服务架构、DevOps 以及云原生技术（如公有云、容器、编排平台）而兴起的。应用被拆分成多个独立运行和管理的小服务，因此将所有应用代码打包成一个可执行文件，从运维角度看是合理的。使用单一构建产物便于管理，独立运行的模式也不需要额外启动应用服务器。

然而，如今一些组织正在反其道而行，开始构建所谓的 **“skinny JAR（瘦 JAR）”**。HubSpot 的工程团队在他们的博客文章《The Fault in Our JARs: Why We Stopped Building Fat JARs（胖 JAR 的错误：为什么我们停止构建 fat JAR）》中，分享了他们在开发周期中遇到的问题。最终，他们开发了一个新的 Maven 插件：**SlimFast**。

该插件与 Java 开发者熟悉的 Maven Shade 插件不同，它**将应用程序代码与依赖项分开打包和上传**，生成两个独立的构建产物。你可能会担心上传依赖项看起来效率低，但实际上，**只有当依赖项变更时，才会重新上传**。而在很多应用中，依赖库变化频率很低，因此这个过程通常是个 no-op（无操作）。

SlimFast 插件利用 Maven JAR 插件，向瘦 JAR 添加 `Class-Path` 清单条目，指向依赖 JAR 文件；同时生成一个 JSON 文件，记录所有依赖项，并上传至 S3，以便后续下载使用。

**在部署阶段：**

- 构建系统会下载应用所需的所有依赖库；
- 这些构件会缓存在每台应用服务器上，因此大多数时候这个步骤也是 no-op。

**最终效果：**

- 构建阶段：只上传瘦 JAR 文件到远程存储，通常只有几百 KB；
- 部署阶段：只需要从远程下载这个瘦 JAR 文件，用时不到一秒。

DevOps 的核心理念之一是：开发、运维和其他所有相关团队应当共同为最终目标努力。而部署产物格式的选择，正是影响能否持续交付功能给用户的关键决策之一。**各团队应协作讨论，理解这一选择对开发体验和部署资源管理的影响。**

目前，**SlimFast 插件依赖 AWS S3 存储构件**，但其源码已开放在 GitHub 上，其设计理念也可以适配其他类型的外部存储服务。

---

## 13. The Code Restorer  

### English

------

> **“Always remember, the person we’re really working for is the person who’s restoring the piece a hundred years from now. He’s the one we want to impress.”**

That quote is from **Hobie**, a character in *Donna Tartt’s novel **The Goldfinch***. Hobie is an antique furniture restorer. I am particularly thankful for this quote because it beautifully expresses what I’ve always thought about code: **the best code is written thinking about the programmers that come after.**

I think current software practices suffer from an illness caused by **too much haste**. Much like **trees in a crowded jungle**, the aim is to outgrow the competition. Trees competing for light often **overstretch themselves**, growing tall and thin and becoming susceptible to small disturbances. Strong winds or mild disease can make them collapse.

I’m not saying we don’t need to look at short-term benefits—in fact, I encourage it—**just not at the expense of long-term stability.**

Today’s software industry is like these trees. Many “modern” teams focus only on the **next week or month**. Companies struggle just to live another day, another sprint, another cycle. And nobody seems to worry about this. Developers can always find another job, and so can managers. Entrepreneurs can try and **cash out before the company has lost its value**. So can the VC that backed the initial investment. Too often, the key to success lies in **timing the exit**, leaving just before people realize that the amazing growth was just a tumor.

On the other hand, maybe that’s not so bad. Some pieces of furniture are meant to last hundreds of years, and some will likely **crumble within a decade**. You can spend thousands at Sotheby’s on a china cabinet—or go to IKEA and **probably furnish your whole house**.

Maybe we just need to understand this new economy we’ve created, where **everything is ephemeral and transient**. Assets aren’t expected to last long, just long enough. We aren’t supposed to create things that stand the test of time, just **the test of profit**.

And yet I believe there is a middle point, a **new role** beginning to take form: **the code restorer**.

Doing something that lasts forever at the first go is so expensive that it isn’t worth it. But focusing only on short-term profit will create code that collapses under its own weight. This is where the **code restorer** comes in—somebody whose job isn’t to “recreate the same thing but better” (a common wish that almost always fails), but rather to **take the existing codebase and slowly reshape it to make it manageable again**.

> Add some tests here.
>  Break down that ugly class there.
>  Remove unused functionality.
>  And give it back—improved.

We, as programmers, have to decide **what kind of software we want to build**. We can focus on profit for a while, build up something that holds, but at some point we have to choose between:

- **Durability**, carefully reshaping the code,
- or **Profit**, abandoning it and starting afresh.

After all, **profits are essential**,  but **some things are bigger than money**.

---

### 中文

---

> **“永远记住，我们真正为之工作的人是那个百年后恢复这些作品的人。他才是我们真正想打动的对象。”**

这句话来自《金翅雀》一书中的人物 **霍比**。霍比是一名古董家具修复师。我特别感激这句话，因为它美丽地表达了我一直以来对代码的看法：**最好的代码是以考虑后来的程序员为基础写成的**。

我认为当前的软件实践正遭受着**过于急功近**的病态。就像**丛林中的树木**，目标是超越竞争对手。争夺阳光的树木往往会**过度生长**，变得又高又瘦，容易受到轻微干扰的影响。强风或轻微的疾病就能让它们倒塌。

我并不是说我们不需要关注短期利益——事实上，我鼓励这样做——**只是不要以牺牲长期稳定为代价**。

今天的软件行业就像这些树木。许多“现代”团队只专注于**下周或下个月**。公司只是为了活过另一天、一个sprint、一个周期而苦苦挣扎。似乎没有人关注这个问题。开发人员总能找到另一个工作，管理者也是如此。企业家们可以试图在公司失去价值之前**套现**，最初投资的风险资本家也是如此。成功的关键往往在于**选择退出的时机**，在大家意识到快速增长只不过是个肿瘤之前就离开。

另一方面，也许这并不那么糟糕。有些家具注定要历经几百年，而有些则很可能在**十年内崩塌**。你可以在苏富比花费数千美元购买一个瓷器柜，或者去宜家**可能就能把整个家装饰完**。

也许我们只需要理解我们创造的这个新经济：**一切都是短暂的、转瞬即逝的**。资产不再期望能够持久，只要足够长久。我们不再需要创造经得起时间考验的东西，只需要**经得起利润的考验**。

然而，我相信还是存在一个中间点，一个正在形成的新角色：**代码修复师**。

一开始就做出永恒的东西是如此昂贵，根本不值得。然而，只专注于短期利润会创造出在自身重压下崩塌的代码。这就是**代码修复师**的作用——他们的工作不是“重新创造更好的东西”（一种常见的失败愿望），而是**对现有代码库进行慢慢重塑，让它重新变得可管理**。

> 在这里增加一些测试。
>  把那个丑陋的类拆开。
>  删除未使用的功能。
>  然后交还给它——得到改进的版本。

作为程序员的我们，必须决定**我们想构建什么样的软件**。我们可以先关注利润，做出一个持久的东西，但最终，我们必须在以下两者之间做出选择：

- **耐久性**，小心地重塑代码，
- 还是**利润**，放弃现有的，重新开始。

毕竟，**利润至关重要**，但**有些事情比金钱更重要**。

---

## 14. Concurrency on the JVM  

### English

---

Originally, raw threads were the only concurrency model available on the JVM, and they’re still the default choice for writing parallel and concurrent programs in Java. When Java was designed 25 years ago, however, the hardware was dramatically different. The demand for running parallel applications was lower, and the concurrency advantages were limited by the lack of multicore processors—tasks could be decoupled, but not executed simultaneously.

Nowadays, the availability and expectation of parallelization has made the limitations of explicit multithreading clear. Threads and locks are too low-level: using them correctly is hard; understanding the Java Memory Model even harder. Threads that communicate through shared mutable state are unfit for massive parallelism, leading to nondeterministic surprises when access isn’t properly synchronized. Moreover, even if your locks are arranged correctly, the purpose of a lock is to restrict threads running in parallel, thus reducing the degree of parallelism of your application.

Because Java does not support distributed memory, it’s impossible to scale multithreaded programs horizontally across multiple machines. And if writing multithreaded programs is difficult, testing them thoroughly is nearly impossible—they frequently become a maintenance nightmare.

The simplest way to overcome the shared memory limitations is to coordinate threads via distributed queues instead of locks. Here, message passing replaces shared memory, which also improves decoupling. Queues are good for unidirectional communication but may introduce latency.

Akka makes the actor model, popularized by Erlang, available on the JVM, and is more familiar to Scala programmers. Each actor is an object responsible for manipulating only its own state. Concurrency is implemented with message flow between actors, so they can be seen as a more structured way of using queues. Actors can be organized in hierarchies, providing for built-in fault tolerance and recovery through supervision. Actors also have some drawbacks: untyped messages don’t play well with Java’s current lack of pattern matching, message immutability is necessary but cannot currently be enforced in Java, composition can be awkward, and deadlocking between actors is still possible.

Clojure takes a different approach with its built-in software transactional memory, turning the JVM heap into a transactional data set. Like a regular database, data is modified with (optimistic) transactional semantics. A transaction is automatically retried when it runs into some conflict. This has the advantage of being nonblocking, eliminating many problems associated with explicit synchronization. This makes them easy to compose. Additionally, many developers are familiar with transactions. Unfortunately, this approach is inefficient in massively parallel systems where concurrent writes are more likely. In these situations retries are increasingly costly and performance can become unpredictable.

Java 8 lambdas promote the use of functional programming properties in code, such as immutability and referential transparency. While the actor model reduces the consequences of mutable state by preventing sharing, functional programming makes the state shareable because it prohibits mutability. Parallelizing code made of pure, side-effect-free functions can be trivial, but a functional program can be less time efficient than its imperative equivalent and may place a bigger burden on the garbage collector. Lambdas also facilitate the use of the reactive programming paradigm in Java consisting in asynchronous processing of streams of events.

There is no silver bullet for concurrency, but there are many different options with different trade-offs. Your duty as a programmer is to know them and choose the one that best fits the problem at hand.

---

### 中文

---

最初，原始线程是JVM中唯一的并发模型，它们仍然是编写并行和并发程序的默认选择。然而，当Java在25年前设计时，硬件与今天截然不同。那时并行应用的需求较低，并发优势受限于缺乏多核处理器——任务可以解耦，但不能同时执行。

如今，随着并行化的普及和期望，显式多线程的局限性变得明显。线程和锁过于底层：正确使用它们很难，理解Java内存模型更为复杂。通过共享可变状态进行通信的线程不适合大规模并行化，若访问没有得到正确同步，容易导致非确定性的结果。而且，即使锁正确配置，锁的目的是限制线程并行运行，从而降低应用的并行度。

由于Java不支持分布式内存，无法将多线程程序横向扩展到多个机器上。如果编写多线程程序已经困难，那么彻底测试它们几乎是不可能的——它们通常会变成维护的噩梦。

克服共享内存限制的最简单方法是通过分布式队列而不是锁来协调线程。在这里，消息传递取代了共享内存，同时也改善了解耦。队列适用于单向通信，但可能引入延迟。

Akka 将 Erlang 推广的 Actor 模型带到了JVM，并且对Scala程序员更为熟悉。每个 Actor 是一个只负责操作自己状态的对象。并发通过 Actor 之间的消息流来实现，因此它们可以看作是更有结构的队列使用方式。Actor 可以组织成层级结构，提供内置的容错和通过监督实现恢复。然而，Actor 也有一些缺点：未类型化的消息与Java当前缺乏模式匹配不兼容，消息不可变性是必要的，但在Java中无法强制执行，组合可能会显得别扭，而且Actor之间仍可能发生死锁。

Clojure 采用了不同的方法，利用内置的事务性内存，将JVM堆转变为事务性数据集。像常规数据库一样，数据以（乐观）事务语义进行修改。当事务遇到冲突时，会自动重试。这种方法的优点是非阻塞，消除了许多与显式同步相关的问题，使代码更容易组合。此外，许多开发者对事务较为熟悉。然而，这种方法在大规模并行系统中效率较低，因为并发写入的可能性较大。在这种情况下，重试变得越来越昂贵，性能可能变得不可预测。

Java 8的Lambda函数促进了在代码中使用函数式编程特性，如不可变性和参照透明性。虽然Actor模型通过防止共享来减少可变状态的影响，但函数式编程使得状态可以共享，因为它禁止可变性。由纯粹、无副作用的函数组成的代码并行化可能很简单，但函数式程序可能不如命令式程序高效，并且可能给垃圾回收器带来更大的负担。Lambda还促进了Java中反应式编程范式的使用，涉及异步处理事件流。

并发没有银弹，但有许多不同的选择，每种选择都有不同的权衡。作为程序员，了解这些选项并选择最适合当前问题的解决方案是我们的责任。

---

## 15. CountDownLatch—Friend or Foe?  

### English

------

Let’s imagine a situation in which we’d like to launch multiple concurrent tasks, and then wait on their completion before proceeding further. The ExecutorService makes the first part easy:

```java
ExecutorService pool = Executors.newFixedThreadPool(8);
Future<?> future = pool.submit(() -> {
    // Your task here
});
```

But how do we wait for all of them to complete? CountDownLatch comes to our rescue. A CountDownLatch takes the number of invocations as a constructor argument. Each task then holds a reference to it, calling the `countDown` method when the task completes:

```java
int tasks = 16;
CountDownLatch latch = new CountDownLatch(tasks);
for (int i = 0; i < tasks; i++) {
    Future<?> future = pool.submit(() -> {
        try {
            // Your task here
        } finally {
            latch.countDown();
        }
    });
}

if (!latch.await(2, TimeUnit.SECONDS)) {
    // Handle timeout
}
```

This example code will launch 16 tasks, then wait for them to finish before proceeding further. There are some important points to take note of, though:

1. Make sure that you release the latch in a `finally` block. Otherwise, if an exception occurs, your main thread may wait forever.
2. Use the `await` method that accepts a timeout period. That way, even if you forget about the first point, your thread will wake up sooner or later.
3. Check the return value of the method. It returns `false` if the time has elapsed, or `true` if all the tasks managed to complete on time.

As mentioned earlier, CountDownLatch receives its count on creation. It can be neither increased nor reset. If you’re looking for capabilities that are similar to those of CountDownLatch but with the ability to reset the count, you should check out `CyclicBarrier` instead.

CountDownLatch is useful in many different situations. It becomes especially useful when you’re testing your concurrent code, since it allows you to make sure that all the tasks are complete before checking their results.

Consider the following real-world example. You have a proxy and an embedded server, and you’d like to test that when the proxy is called, it invokes the correct endpoint on your server.

Obviously, it doesn’t make much sense to issue a request before both the proxy and server have started. One solution is to pass a CountDownLatch to both methods, and continue with the test only when both parties are ready:

```java
CountDownLatch latch = new CountDownLatch(2);
Server server = startServer(latch);
Proxy proxy = startProxy(latch);
boolean timedOut = !latch.await(1, TimeUnit.SECONDS);
assertFalse(timedOut, "Timeout reached");
// Continue with test if assertion passes
```

You just need to make sure that both the `startServer` and `startProxy` methods call `latch.countDown` once they have successfully started.

CountDownLatch is very useful, but there’s one important catch: you shouldn’t use it in production code that makes use of concurrent libraries or frameworks, such as Kotlin’s coroutines, Vert.x, or Spring WebFlux. This is because CountDownLatch blocks the current thread. Different concurrency models don’t play well together.

---

### 中文

---

假设我们希望启动多个并发任务，然后等待它们完成再继续执行下一步。`ExecutorService` 使得任务启动变得简单：

```java
ExecutorService pool = Executors.newFixedThreadPool(8);
Future<?> future = pool.submit(() -> {
    // 这里是任务内容
});
```

但是，如何等待所有任务完成呢？`CountDownLatch` 可以帮助我们。`CountDownLatch` 需要一个构造参数来指定调用次数。每个任务在完成时都会调用 `countDown` 方法：

```java
int tasks = 16;
CountDownLatch latch = new CountDownLatch(tasks);
for (int i = 0; i < tasks; i++) {
    Future<?> future = pool.submit(() -> {
        try {
            // 任务内容
        } finally {
            latch.countDown();
        }
    });
}

if (!latch.await(2, TimeUnit.SECONDS)) {
    // 处理超时
}
```

这个示例代码会启动16个任务，并在它们完成后再继续执行。需要注意一些关键点：

1. 确保在 `finally` 块中释放 `latch`。否则，如果任务抛出异常，主线程可能永远等待。
2. 使用带有超时时间的 `await` 方法。即使忘记了第一点，线程也会在一定时间后醒来。
3. 检查方法的返回值。如果超时，返回 `false`；如果所有任务按时完成，返回 `true`。

如前所述，`CountDownLatch` 在创建时接收计数值，并且计数值无法增加或重置。如果你需要类似于 `CountDownLatch` 的功能，并且能够重置计数值，可以使用 `CyclicBarrier`。

`CountDownLatch` 在许多情况下都非常有用，尤其是在测试并发代码时，它可以确保所有任务在检查结果之前已经完成。

考虑一个实际的例子。你有一个代理和一个嵌入式服务器，想要测试当代理被调用时，是否会调用服务器上的正确端点。

显然，在代理和服务器都启动之前发出请求是没有意义的。一种解决方案是将 `CountDownLatch` 传递给这两个方法，只有在两者都准备好时才继续测试：

```java
CountDownLatch latch = new CountDownLatch(2);
Server server = startServer(latch);
Proxy proxy = startProxy(latch);
boolean timedOut = !latch.await(1, TimeUnit.SECONDS);
assertFalse(timedOut, "Timeout reached");
// 如果断言通过，继续测试
```

你只需要确保 `startServer` 和 `startProxy` 方法在成功启动后都调用 `latch.countDown`。

`CountDownLatch` 非常有用，但有一个重要的注意事项：你不应该在生产代码中使用它，尤其是当使用并发库或框架时，例如 Kotlin 的协程、Vert.x 或 Spring WebFlux。因为 `CountDownLatch` 会阻塞当前线程，而不同的并发模型往往不兼容。

---

## 16. Declarative Expression Is the Path to Parallelism  

### English

------

In the beginning, Java was an imperative, object-based programming language. Indeed, it still is. Over the years, though, Java has evolved, at each stage becoming more and more a language of declarative expression. Imperative is all about the code explicitly telling the computer what to do. Declarative is about the code expressing a goal abstracting over the way in which the goal is achieved. Abstraction is at the heart of programming, and so the move from imperative code to declarative code is a natural one.

At the core of declarative expression is the use of higher-order functions, functions that take functions as parameters and/or return functions. This was not an integral part of Java originally, but with Java 8 it moved front and center: Java 8 was a turning point in the evolution of Java, allowing replacement of imperative expression with declarative expression.

An example—trivial but nonetheless indicative of the main issue—is to write a function that returns a List containing the squares of the argument List to the function. Imperatively, we might write:

```java
List<Integer> squareImperative(final List<Integer> datum) {
    var result = new ArrayList<Integer>();
    for (var i = 0; i < datum.size(); i++) {
        result.add(i, datum.get(i) * datum.get(i));
    }
    return result;
}
```

The function creates an abstraction over some low-level code, hiding the details from the code that uses it.

With Java 8 and beyond, we can use streams and express the algorithm in a more declarative way:

```java
List<Integer> squareDeclarative(final List<Integer> datum) {
    return datum.stream()
                .map(i -> i * i)
                .collect(Collectors.toList());
}
```

This sets out at a higher level of expression of what is to be done; the details of how are left to the library implementation. Classic abstraction. True, the implementation is within a function that already abstracts and hides, but which would you rather maintain: the low-level imperative implementation or the high-level declarative implementation?

Why is this such a big deal? The above is a classic example of an embarrassingly parallel computation. The evaluation of each result depends only on one item of input; there is no coupling. So we can write:

```java
List<Integer> squareDeclarative(final List<Integer> datum) {
    return datum.parallelStream()
                .map(i -> i * i)
                .collect(Collectors.toList());
}
```

Doing so, we will get the maximum parallelism that the library is able to extract from the platform. Because we are abstracting away from the details of how, focusing only on the goal, we can turn a sequential data-parallel computation into a parallel one trivially.

It will be left as an exercise for the reader to (attempt to) write a parallel version of the imperative code should they so wish. Why? Because for data parallel problems, using Streams is the right abstraction. To do anything else is to deny the Java 8 evolution of Java.

---

### 中文

---

最初，Java 是一种命令式的、面向对象的编程语言，至今仍然如此。然而，随着时间的推移，Java 逐渐演变，变得越来越具有声明式表达的特性。命令式编程是明确告诉计算机该做什么，而声明式编程则是让代码表达一个目标，抽象出实现该目标的方式。抽象是编程的核心，因此从命令式代码到声明式代码的转变是自然而然的。

声明式表达的核心是使用高阶函数，即接受函数作为参数和/或返回函数的函数。最初，Java 并不包含这一特性，但自 Java 8 起，高阶函数成为了 Java 编程的重要组成部分：Java 8 成为 Java 演变的一个转折点，使得命令式表达式能够被声明式表达式取代。

一个简单的例子（尽管很基础，但仍能体现主要问题）是编写一个返回包含传入列表中元素的平方的新列表的函数。在命令式编程中，我们可能会写如下代码：

```java
List<Integer> squareImperative(final List<Integer> datum) {
    var result = new ArrayList<Integer>();
    for (var i = 0; i < datum.size(); i++) {
        result.add(i, datum.get(i) * datum.get(i));
    }
    return result;
}
```

该函数将一些底层代码抽象为一个函数，隐藏了细节。

在 Java 8 及之后版本中，我们可以使用流（Streams）以更声明式的方式表达算法：

```java
List<Integer> squareDeclarative(final List<Integer> datum) {
    return datum.stream()
                .map(i -> i * i)
                .collect(Collectors.toList());
}
```

这就用更高层次的表达方式描述了要做的事情；至于如何实现，则交给库的实现去处理。经典的抽象方式。虽然实现代码本身已经在一个函数内进行了抽象和隐藏，但你更愿意维护低级的命令式实现，还是更高层次的声明式实现呢？

那么，为什么这如此重要呢？上述代码是一个典型的尴尬并行计算（embarrassingly parallel）。每个结果的计算仅依赖于一个输入项；它们之间没有耦合。因此我们可以写出如下代码：

```java
List<Integer> squareDeclarative(final List<Integer> datum) {
    return datum.parallelStream()
                .map(i -> i * i)
                .collect(Collectors.toList());
}
```

这样做，我们就能从平台中提取出最大的并行度。因为我们抽象了“如何做”的细节，只关注目标，因此我们可以轻松地将一个顺序的数据并行计算转换为并行计算。

如果读者愿意，可以尝试为命令式代码编写并行版本。为什么呢？因为对于数据并行问题，使用流（Streams）是正确的抽象方式。做其他的事情就等同于否定 Java 8 对 Java 演变的贡献。

---

## 17. Deliver Better Software, Faster  

### English

---

For me, *Deliver Better Software, Faster* is a guiding principle, and one I strongly recommend you adopt because it describes what must happen to keep your users happy. In addition (and perhaps more importantly), following it can result in a more enjoyable and interesting career. To see how, let’s examine the three parts of this important idea:

1. **Deliver** means taking responsibility for more than just writing and debugging code. Despite appearances, you aren’t paid to write code. You’re paid to make it easier for your users to do something they find valuable, and until your code is running in production, they won’t benefit from your hard work.

   Changing your focus from writing code to delivering software requires understanding the overall process for getting your changes into production and then doing two key things:

   - Making sure you aren’t doing things that hinder the process, like guessing the meaning of a vague requirement instead of asking for clarification before implementing it.
   - Making sure you are doing things that speed up the process, like writing and running automated tests to show your code meets the acceptance criteria.

2. **Better Software** is shorthand for two ideas you should already be familiar with: “building the right thing” and “building the thing right.” The first means ensuring that what you’ve written meets all the requirements and acceptance criteria. The second is about writing code that is easily understood by another programmer so they can successfully fix bugs or add new features.

   While this may sound easy to do, especially if you follow a practice like test-driven development (TDD), many teams tend to lean one way or the other:

   - Nonprogrammers might push developers to take shortcuts to deliver new features sooner, with promises to come back and “do it right” later.
   - Sometimes programmers who just learned something will try to use it everywhere possible, even if they know a simpler solution would work just as well.

   In either case, the balance is lost and the resulting technical debt increases the time needed to deliver value to your users until the balance is regained.

3. **Faster** refers to both Deliver and Better Software, and could be a challenging goal because people trying to do complicated things quickly tend to make mistakes. To me, the obvious solution includes:

   - Using a process like TDD to create automated tests, then regularly running the automated unit, integration, and user acceptance tests to verify the system’s behavior.
   - Building and running an automated process that runs all the tests in multiple environments and, assuming they all pass, deploys the code to production.

   Both of these processes will be done multiple times and require great attention to detail—just the sort of task a computer does faster and more accurately than a person.

   That’s good because I have one more recommendation: deploy changes to production more often so each deployment has fewer changes and is therefore less likely to have problems, and your users get the benefits of your work sooner.

Adopting *Deliver Better Software, Faster* as a guiding principle is both challenging and fun. Be aware that it will take time to find and fix all the places that need work, but the rewards are worth it.

---

### 中文

---

“交付更好的软件，更快地交付”是我个人的指导原则，我强烈建议你也采纳这一原则，因为它描述了保持用户满意所必需的工作方式。此外（或许更重要的是），遵循这一原则也会让你的职业生涯变得更加有趣和充实。为了更好地理解这一原则，我们可以将其分为三个部分：

1. **交付**意味着承担比仅仅编写和调试代码更大的责任。虽然看起来你是为写代码而付钱，但实际上你是为让用户更容易地做他们认为有价值的事情而付钱，直到你的代码在生产环境中运行，他们才会从你的辛勤工作中受益。

   从编写代码转向交付软件，意味着你需要理解将更改推送到生产环境的整个过程，然后做两件关键的事情：

   - 确保你不会做出阻碍过程的事，比如在没有询问明确要求的情况下，凭猜测来实现不明确的需求。
   - 确保你做的是能加速过程的事，比如编写和运行自动化测试，以确保你的代码满足验收标准。

2. **更好的软件**是对两个你应该已经熟悉的概念的简写：“构建正确的东西”和“正确地构建东西”。第一个意味着确保你编写的代码满足所有的需求和验收标准。第二个意味着编写代码时，使其易于其他程序员理解，这样他们就能顺利地修复 bug 或添加新功能。

   虽然这看起来很简单，特别是如果你遵循像测试驱动开发（TDD）这样的实践，但许多团队往往偏向一方：

   - 非程序员可能会推动开发人员采取捷径，尽早交付新功能，并承诺稍后再“做对”。
   - 有时刚刚学到新技术的程序员会试图将其应用到所有可能的地方，即使他们知道更简单的解决方案也同样有效。

   在这两种情况下，平衡被打破，结果是技术债务增加，直到重新找回平衡，交付用户价值的时间才会减少。

3. **更快地交付**涉及到交付和更好的软件，可能是一个挑战性的目标，因为试图快速完成复杂任务的人往往容易犯错。对此，我认为明显的解决方案包括：

   - 使用像 TDD 这样的过程来创建自动化测试，然后定期运行自动化单元测试、集成测试和用户验收测试，来验证系统行为。
   - 构建并运行一个自动化流程，该流程在多个环境中运行所有测试，并且假设它们都通过，则将代码部署到生产环境。

   这些过程将被多次执行，并且需要非常注意细节——这正是计算机比人类更快、更准确地完成的任务。

   这很重要，因为我还有一个建议：更频繁地将更改部署到生产环境，这样每次部署的更改较少，因此发生问题的可能性较低，用户也能更早地获得你工作的成果。

采纳“交付更好的软件，更快地交付”作为指导原则既具有挑战性，也充满乐趣。请意识到，找到并修复所有需要改进的地方将需要时间，但收获是值得的。

---

## 18. Do You Know What Time It Is?  

### English

------

At what time does the Scandinavian Airlines plane from Oslo to Athens arrive on Monday? Why are questions that seem so easy in day-to-day life so difficult in programming? Time should be simple, just seconds passing, something a computer is very good at measuring:
 System.currentTimeMillis() = 1570964561568
 Although correct, 1570964561568 is not what we want when we ask what time it is. We prefer 1:15 p.m., October 13, 2019.

It turns out that time is two separate things. On the one hand, we have seconds passing. On the other, we have an unhappy marriage between astronomy and politics. Answering the question “What time is it?” depends on the location of the sun in the sky relative to your position along with the political decisions made in that region up to that point in time.

Many of the problems we have with date and time in code come from mixing these two concepts. Using the latest java.time library (or Noda Time in .NET) will help you. Here are three main concepts to help you reason correctly about time: LocalDateTime, ZonedDateTime, and Instant.

LocalDateTime refers to the concept 1:15 p.m., October 13, 2019. There can be any number of these on the timeline. Instant refers to a specific point on the timeline. It is the same in Boston as in Beijing. To get from a LocalDateTime to an Instant, we need a TimeZone, which comes with Coordinated Universal Time (UTC) offsets and daylight saving time (DST) rules at the time.

ZonedDateTime is a LocalDateTime with a TimeZone.

Which ones do you use? There are so many pitfalls. Let me show you a few. Let’s say we’re writing software to organize an international conference. Will this work?

```java
public class PresentationEvent {
    final Instant start, end;
    final String title;
}
```

Nope.
 Although we need to represent a particular point in time, for future events, even when we know the time and the time zone, we cannot know the instant ahead of time because DST rules or UTC offsets might change between now and then. We need a ZonedDateTime.

How about regularly occurring events, like a flight? Will this work?

```java
public class Flight {
    final String flightReference;
    final ZonedDateTime departure, arrival;
}
```

Nope.
 This can fail twice a year. Imagine a flight leaving Saturday at 10:00 p.m. and arriving Sunday at 6:00 a.m. What happens when we move the clock back an hour because of daylight savings? Unless the aircraft circles uselessly during the extra hour, it’s going to land at 5:00 a.m., not 6:00 a.m. When we move ahead one hour, it’ll arrive at 4:00 a.m.

For recurring events with duration, we cannot fix both the start and the end. Here’s what we need:

```java
public class Flight {
    final String flightReference;
    final ZonedDateTime departure;
    final Duration duration;
}
```

What about events that start at 2:30 a.m.? Which one? There may be two, or it might not exist at all. In Java, the following methods handle the autumnal DST transition:
 ZonedDateTime.withEarlierOffsetAtOverlap()
 ZonedDateTime.withLaterOffsetAtOverlap()

In Noda Time, specify both DST transitions explicitly with Resolvers.

I have only scratched the surface of potential issues, but as they say, good tools are half the work. Use java.time (or Noda Time), and you’ve saved yourself a lot of errors.

---

### 中文

---

当我们问“斯堪的纳维亚航空从奥斯陆飞往雅典的航班星期一几点到达？”这种看似简单的问题时，为什么编程中却如此困难？时间应该很简单，计算机在衡量时间上非常擅长：
 `System.currentTimeMillis() = 1570964561568`
 虽然正确，1570964561568 代表的是时间戳，但当我们问“现在是什么时间”时，我们更希望看到的是 2019年10月13日 下午1:15。

原来，时间是两个不同的概念。一方面是秒数流逝，另一方面是天文学和政治的复杂结合。回答“现在几点”取决于太阳相对于你的位置在天空中的位置，以及该地区在此时为止的政治决策。

我们在编程中处理日期和时间时，很多问题来源于混淆这两个概念。使用最新的 `java.time` 库（或者 .NET 中的 Noda Time）可以帮助解决这个问题。以下是帮助你正确理解时间的三个主要概念：`LocalDateTime`、`ZonedDateTime` 和 `Instant`。

- **LocalDateTime** 表示具体的时间，比如 2019年10月13日下午1:15。这个时间点可以有多个。
- **Instant** 表示时间线上的一个特定点。无论是在波士顿还是北京，这个时间点都是一样的。要从一个 `LocalDateTime` 转换为 `Instant`，我们需要一个时区，时区包含了协调世界时（UTC）偏移和夏令时（DST）规则。
- **ZonedDateTime** 是包含时区的 `LocalDateTime`。

那么，应该使用哪个呢？有很多坑，以下是几个例子。假设我们正在编写一个国际会议的软件系统，这样写能行吗？

```java
public class PresentationEvent {
    final Instant start, end;
    final String title;
}
```

不能行。
 虽然我们需要表示某个特定的时间点，但对于未来的事件，即使我们知道时间和时区，也无法提前知道 `Instant`，因为夏令时规则或UTC偏移可能会在未来发生变化。我们需要使用 `ZonedDateTime`。

那对于定期发生的事件，比如航班呢？这样写能行吗？

```java
public class Flight {
    final String flightReference;
    final ZonedDateTime departure, arrival;
}
```

不能行。
 每年可能会发生两次错误。假设航班在星期六晚上10点起飞，星期天早上6点到达。假如我们因为夏令时的调整将时间拨回一小时，那航班将会在早上5点到达，而不是原定的早上6点。当时间提前一小时时，它会在早上4点到达。

对于有持续时间的周期性事件，我们不能同时确定开始时间和结束时间。正确的做法是：

```java
public class Flight {
    final String flightReference;
    final ZonedDateTime departure;
    final Duration duration;
}
```

那么对于那些在凌晨2:30开始的事件呢？哪一个呢？可能会有两个，或者根本就没有。在 Java 中，以下方法处理秋季的夏令时转换：

- `ZonedDateTime.withEarlierOffsetAtOverlap()`
- `ZonedDateTime.withLaterOffsetAtOverlap()`

在 Noda Time 中，可以使用 `Resolvers` 显式指定两个夏令时转换。

这只是潜在问题的冰山一角，但正如人们所说，好工具是成功的一半。使用 `java.time`（或者 Noda Time），你就能避免很多错误。

---

## 19. Don’t hIDE Your Tools  

### English

---

What is the one essential tool every Java programmer needs? Eclipse?
 IntelliJ IDEA? NetBeans? No. It’s javac. Without it, all you have is files of weird-looking text. It is possible to do the job without integrated development environments (IDEs)—ask people like me who programmed in the olden days. It is not possible to program without essential development tools.

Given that they are central to the task, it’s surprising how rarely people use tools like javac directly. While knowing how to make effective use of an IDE is important, understanding what it is doing, and how, is crucial.

Once upon a time, I worked on a project with two subsystems, one in C++ and the other in Java. C++ programmers worked with their editor of choice and the command line. Java programmers used an IDE. One day, the incantation to interact with the version control system changed. It was a simple command-line change for the C++ programmers, who went on their way without delay. The Java team spent the whole morning wrestling with their Eclipse configuration. They finally got back to productive work in the afternoon.

This unfortunate story doesn’t reflect well on the Java team’s mastery of their chosen tools. But it also illustrates how distanced they were in their day-to-day work from the essential tools of their trade by working exclusively in an IDE. Information hiding is a great principle for enabling focus on a useful abstraction rather than a mass of detail, for sure. But it implies a choice to delve into details only when relevant, not ignorance of the details.

Relying solely on an IDE can undermine a programmer’s mastery of their tools because the IDE purposely hides the nuts and bolts. The configuration—often just a case of following someone else’s instructions—can be forgotten as soon as it’s done. There are many advantages to also knowing how to use the essential tools directly:

- “It works on my machine” scenarios are less likely and simpler to resolve if you understand the relationships among tools, source code, other resources, and generated files. It also helps with knowing what to package for installation.
- It’s extraordinarily quick and easy to set different options. Start with commands like javac --help so you can see what those options are.
- Familiarity with the essential tools is valuable when helping people who use a different environment. It also helps when something goes wrong; it’s hard to troubleshoot when integrated tools are not working. Visibility is better on the command line and you can isolate parts of the process, just as you would when debugging code.
- You have access to a richer tool set. You can integrate any combination of tools that have a command-line interface (for example, scripts or Linux commands), not just those supported in the IDE.

End users will not run your code in an IDE! In the interest of good user experience, test from the start by running the code as it will be run on a user’s machine.

None of this denies the benefits of an IDE. But to be truly skilled at your craft, understand your essential tools and don’t let them get rusty.

---

### 中文

---

每个 Java 程序员需要的唯一必备工具是什么？是 Eclipse？IntelliJ IDEA？NetBeans？不，答案是 `javac`。没有它，所有你拥有的只是一些看起来怪异的文本文件。虽然使用集成开发环境（IDE）是可以的，但没有必要依赖它们来完成工作——问问像我这样的老程序员，他们当年是怎么编程的。不过没有基本的开发工具，你根本无法编程。

考虑到这些工具在任务中的重要性，令人惊讶的是，很多人很少直接使用像 `javac` 这样的工具。虽然知道如何高效使用 IDE 非常重要，但理解它在做什么，如何做的，才是至关重要的。

曾经，我参与一个有两个子系统的项目，一个是 C++，另一个是 Java。C++ 程序员使用他们选择的编辑器和命令行，而 Java 程序员则使用 IDE。有一天，版本控制系统的操作命令发生了变化。对于 C++ 程序员来说，这是一个简单的命令行变更，他们没有任何延迟继续工作。而 Java 团队却花了一整个上午与他们的 Eclipse 配置作斗争，最终下午才恢复了生产工作。

这个不幸的故事并不代表 Java 团队对他们选择的工具掌握得不好。但它也反映出他们日常工作中远离了他们工具的核心，完全依赖 IDE。信息隐藏的原则当然是很棒的，它帮助人们专注于有用的抽象，而不是细节。但这意味着，选择只在相关时刻深入细节，而不是对这些细节一无所知。

单纯依赖 IDE 可能会削弱程序员对工具的掌握，因为 IDE 故意隐藏了工具的“螺丝钉”。配置——往往只是按照别人给出的指示进行设置——完成后就容易忘记。直接使用基本工具有很多好处：

- “它在我的机器上可以运行”这种情况不太可能发生，并且如果你理解工具、源代码、其他资源和生成文件之间的关系，也更容易解决问题。这也有助于知道应该打包什么进行安装。
- 设置不同选项非常快捷简单。可以先从 `javac --help` 这样的命令开始，看看有哪些选项。
- 熟悉基本工具在帮助使用不同环境的人时非常有价值。当出现问题时，它也很有帮助；当集成工具无法正常工作时，排查问题会变得很困难。命令行上可见性更好，你可以像调试代码一样，隔离过程中的不同部分。
- 你可以使用更丰富的工具集。你可以集成任何有命令行接口的工具（例如脚本或 Linux 命令），而不仅仅是 IDE 支持的那些。

最终用户不会在 IDE 中运行你的代码！为了获得良好的用户体验，从一开始就应该在最终用户机器上运行代码进行测试。

这并不是否定 IDE 的好处。但如果你想真正精通你的手艺，就要理解你的基本工具，避免让它们生锈。

---

## 20. Don’t Vary Your Variables  

### English

------

I try to make as many variables as possible final because I find it easier to reason about immutable code. It makes my coding life simpler, which is a high priority for me—I’ve spent too much time trying to figure out exactly how the contents of a variable change throughout a block of code. Of course, Java’s support for immutability is more limited than some other languages, but there are still things we can do.

**Assign Once**

Here’s a small example of a structure I see everywhere:

```java
Thing thing;  
if (nextToken == MakeIt) {  
    thing = makeTheThing();  
} else {  
    thing = new SpecialThing(dependencies);  
}  
thing.doSomethingUseful();
```

To me this doesn’t irrevocably express that we’re going to set the value of thing before we use it and not change it again. It takes me time to walk through the code and figure out that it won’t be null. It’s also an accident waiting to happen when we need to add more conditions and don’t quite get the logic right. Modern IDEs will warn about an unset thing—but then lots of programmers ignore warnings. A first fix would be to use a conditional expression:

```java
final var thing = nextToken == MakeIt  
    ? makeTheThing()  
    : new SpecialThing(dependencies);  
thing.doSomething();
```

The only way through this code is to assign thing a value.

A next step is to wrap up this behavior in a function to which I can give a descriptive name:

```java
final var thing = aThingFor(nextToken);  
thing.doSomethingUseful();

private Thing aThingFor(Token aToken) {  
    return aToken == MakeIt  
        ? makeTheThing()  
        : new SpecialThing(dependencies);  
}
```

Now the life cycle of thing is easy to see. Often this refactoring shows that thing is only used once, so I can remove the variable:

```java
aThingFor(aToken).doSomethingUseful();
```

This approach sets us up for when, inevitably, the condition becomes more complicated; note that the switch statement is simpler without the need for repeated break clauses:

```java
private Thing aThingFor(Token aToken) {  
    switch (aToken) {  
        case MakeIt:  
            return makeTheThing();  
        case Special:  
            return new SpecialThing(dependencies);  
        case Green:  
            return mostRecentGreenThing();  
        default:  
            return Thing.DEFAULT;  
    }  
}
```

**Localize Scope**

Here’s another variant:

```java
var thing = Thing.DEFAULT;  
// lots of code to figure out nextToken  
if (nextToken == MakeIt) {  
    thing = makeTheThing();  
}  
thing.doSomethingUseful();
```

This is worse because the assignments to thing aren’t close together and might not even happen. Again, we extract this into a supporting method:

```java
final var thing = theNextThingFrom(aStream);

private Thing theNextThingFrom(Stream aStream) {  
    // lots of code to figure out nextToken  
    if (nextToken == MakeIt) {  
        return makeTheThing();  
    }  
    return Thing.DEFAULT;  
}
```

Alternatively, separating concerns further:

```java
final var thing =  
    aThingForToken(nextTokenFrom(aStream));
```

Localizing the scope of anything that is variable into a supporting method makes the top-level code predictable. Finally, although some coders aren’t used to it, we could try a streaming approach:

```java
final var thing = nextTokenFrom(aStream)  
    .filter(t -> t == MakeIt)  
    .findFirst()  
    .map(t -> makeTheThing())  
    .orElse(Thing.DEFAULT);
```

I’ve regularly found that trying to lock down anything that does not vary makes me think more carefully about my design and flushes out potential bugs. It forces me to be clear about where things can change and to contain such behavior into local scopes.

---

### 中文

---

我尽量让尽可能多的变量保持 `final`，因为我发现这样做可以更容易推理不可变的代码。这让我的编程生活变得更简单，而简化编程对我来说是一个高优先级的目标——我已经花了太多时间试图弄清楚变量在代码块中的内容是如何变化的。当然，Java 对不可变性的支持不如某些其他语言那么强大，但仍然有一些我们可以做的事情。

**一次赋值**

这是我在很多地方看到的一个结构：

```java
Thing thing;
if (nextToken == MakeIt) {
    thing = makeTheThing();
} else {
    thing = new SpecialThing(dependencies);
}
thing.doSomethingUseful();
```

对我来说，这段代码并没有明确表达出我们会在使用 `thing` 之前为其赋值，并且不会再次更改它。需要花时间梳理代码，弄清楚它不会是 `null`。当我们需要添加更多条件并且逻辑不太对时，这也是一个潜在的意外风险。现代 IDE 会警告未初始化的 `thing`，但很多程序员会忽略警告。第一个改进方法是使用条件表达式：

```java
final var thing = nextToken == MakeIt
    ? makeTheThing()
    : new SpecialThing(dependencies);
thing.doSomething();
```

通过这段代码，唯一的选择就是给 `thing` 赋一个值。

下一步是将这种行为封装到一个函数中，给它一个有描述性的名字：

```java
final var thing = aThingFor(nextToken);
thing.doSomethingUseful();

private Thing aThingFor(Token aToken) {
    return aToken == MakeIt
        ? makeTheThing()
        : new SpecialThing(dependencies);
}
```

现在 `thing` 的生命周期变得清晰可见。通常，这种重构会显示出 `thing` 只使用了一次，因此我可以直接移除这个变量：

```java
aThingFor(aToken).doSomethingUseful();
```

这种方法为条件变得更复杂时做好了准备；请注意，`switch` 语句变得更简单，因为不再需要重复的 `break` 子句：

```java
private Thing aThingFor(Token aToken) {
    switch (aToken) {
        case MakeIt:
            return makeTheThing();
        case Special:
            return new SpecialThing(dependencies);
        case Green:
            return mostRecentGreenThing();
        default:
            return Thing.DEFAULT;
    }
}
```

**限制作用域**

这里是另一个变体：

```java
var thing = Thing.DEFAULT;
// lots of code to figure out nextToken
if (nextToken == MakeIt) {
    thing = makeTheThing();
}
thing.doSomethingUseful();
```

这种方式更糟，因为赋值操作不在一起，甚至可能不会发生。我们可以把它提取到一个辅助方法中：

```java
final var thing = theNextThingFrom(aStream);

private Thing theNextThingFrom(Stream aStream) {
    // lots of code to figure out nextToken
    if (nextToken == MakeIt) {
        return makeTheThing();
    }
    return Thing.DEFAULT;
}
```

或者，进一步分离关注点：

```java
final var thing =
    aThingForToken(nextTokenFrom(aStream));
```

将任何变化的作用域局部化到辅助方法中，可以使顶层代码更可预测。最后，尽管一些程序员不习惯这样做，但我们可以尝试使用流式处理方法：

```java
final var thing = nextTokenFrom(aStream)
    .filter(t -> t == MakeIt)
    .findFirst()
    .map(t -> makeTheThing())
    .orElse(Thing.DEFAULT);
```

我经常发现，尽量将不变化的东西固定下来，会让我更加仔细地思考我的设计，并且发现潜在的bug。这迫使我明确地知道哪里可能会发生变化，并将这种行为限定在局部作用域中。

---

## 21. Embrace SQL Thinking  

### English

---

Look at this query:

```sql
SELECT c.id, c.name, c.address, o.items
FROM customers c
JOIN orders o ON o.customer_id = c.id
GROUP BY c.id
```

We acquire all the customers who have orders, including their names and addresses, along with the details of their orders. Four lines of code. Anyone with a little SQL experience, including nonprogrammers, can understand this query.

Now think about a Java implementation. We might declare classes for Customer and Order. I remember well-meaning consultants saying we should also create classes to encapsulate collections of them, rather than use “naked” Java collections. We still need to query the database, so we pull in an object-relational mapper (ORM) tool and write code for that. Four lines of code quickly turn into dozens or even hundreds of lines. The few minutes it took to write and refine the SQL query stretch into hours or days of editing, writing unit tests, code reviews, and so on.

Can’t we just implement the whole solution with only the SQL query? Are we sure we can’t? Even if we really can’t, can we eliminate waste and write only what’s essential?

Consider the qualities of the SQL query:
**We don’t need a new table for the join output, so we don’t create one.** 
​    The biggest failing of applied object-oriented programming has been the belief that you should faithfully reproduce your domain model in code. In reality, a few core type definitions are useful for encapsulation and understanding, but tuples, sets, arrays, and so forth are all we need the rest of the time. Unnecessary classes become a burden as the code evolves.

**The query is declarative.** 
​    Nowhere does it tell the database how to do the query; it just states the relational constraints the database must satisfy. Java is an imperative language, so we tend to write code that says what to do. Instead, we should declare constraints and desired outcomes, and then isolate the how implementation in one place or delegate to a library that can implement it for us. Like functional programming, SQL is declarative. In functional programming, equivalent declarative implementations are achieved using composable primitives, such as map, filter, reduce, and so on.

**The domain-specific language (DSL) is well matched to the problem.** 
​    DSLs can be somewhat controversial. It’s very hard to design a good one, and the implementations can be messy. SQL is a data DSL. It’s quirky, but its longevity is proof of how well it expresses typical data-processing needs.

All applications are really data applications. At the end of the day, everything we write is a data manipulation program, whether or not we think of it that way. Embrace that fact and the unnecessary boilerplate will reveal itself, allowing you to write only what’s essential.

---

### 中文

------

看看这个查询语句：

```sql
SELECT c.id, c.name, c.address, o.items
FROM customers c
JOIN orders o ON o.customer_id = c.id
GROUP BY c.id
```

我们获取了**所有有订单的客户**，包括他们的名字和地址，以及订单的详细信息。**只用了四行代码**。任何有一点 SQL 经验的人，甚至非程序员，也能理解这个查询。

现在想想 Java 的实现方式。我们可能会为 `Customer` 和 `Order` 声明类。我记得一些出于好意的顾问建议我们**不要直接使用裸露的 Java 集合，而应该为这些集合也封装成类**。但我们仍然需要查询数据库，于是引入一个对象关系映射（ORM）工具，并为此编写代码。原本四行的 SQL 查询，**很快就膨胀成几十甚至上百行代码**。几分钟能写完的 SQL 查询，变成了**需要花费数小时甚至几天的编辑、单元测试和代码审查工作**。

我们就不能只用这个 SQL 查询来实现整个解决方案吗？我们真的不能吗？**即使真的不能，是否也能精简流程，只写必要的部分？**

思考这个 SQL 查询的几个特点：

- **我们不需要为连接结果创建新表，因此我们就不会创建。** 面向对象编程中最大的误区就是试图在代码中“忠实地再现”领域模型。现实中，少量核心类型定义有助于封装和理解，但在其他大多数时候，**元组、集合、数组就足够了**。**不必要的类，随着代码演进，会成为负担。**
- **这个查询是声明式的。** 它没有告诉数据库该“怎么做”，而只是描述了数据库必须满足的关系约束。而 Java 是命令式语言，我们常常写出“该做什么”的代码。**我们应该声明约束和期望结果**，把具体实现方式封装在某处，或者委托给可复用的库。就像函数式编程一样，SQL 是声明式的；而在函数式编程中，声明式的实现往往由 `map`、`filter`、`reduce` 等可组合的原语完成。
- **SQL 是一种非常贴合问题的数据领域专用语言（DSL）**。DSL（领域专用语言）有时颇具争议，因为设计一个好的 DSL 很难，实现过程也可能很混乱。但 SQL 是一种数据 DSL，虽然有些怪异，但**它的长寿说明它非常适合表达典型的数据处理需求**。

最后一点：

> **所有应用归根结底都是数据应用。** 无论我们是否意识到，其实我们写的每个程序，本质上都是**数据处理程序**。**接受这个事实，你会发现很多冗余代码都是可以省掉的，最终只需写下真正必要的部分。**

---

## 22. Events Between Java Components  

### English

------

One of the core concepts of object orientation in Java is that every class can be considered to be a component. Components can be extended or included to form bigger components. The final application is also considered a component. Components are like Lego blocks that build up a bigger structure.

An event in Java is an action that changes the state of a component. For example, if your component is a button, then clicking on that button is an event that changes the state of the button to be clicked.

Events do not necessarily happen only on visual components. For example, you can have an event on a USB component that a device is connected. Or an event on a network component that data is transferred. Events help to decouple the dependencies between components.

Assume we have an Oven component and a Person component. These two components exist in parallel and work independently of one another. We should not make Person part of Oven, nor the other way around. To build a smart house, we want the Oven to prepare food once Person is hungry. Here are two possible implementations:

1. Oven checks Person in fixed, short intervals. This annoys Person and is also expensive for Oven if we want it to check on multiple instances of Person.
2. Person comes with a public event, Hungry, to which Oven is subscribed. Once Hungry is fired, Oven is notified and starts preparing food.

The second solution uses the event architecture to handle the listening and communication between components efficiently and without a direct coupling between Person and Oven, because Person will fire the event, and any component, such as Oven, Fridge, and Table, can listen to that event without any special handling from the Person component.

Implementing events for a Java component can take different forms, depending on how they are expected to be handled. To implement a minimal HungerListener in the Person component, first, create a listener interface:

```java
@FunctionalInterface  
public interface HungerListener {  
    void hungry();  
}
```

Then, in the Person class, define a list to store the listeners:

```java
private List<HungerListener> listeners = new ArrayList<>();
```

Define an API to insert a new listener:

```java
public void addHungerListener(HungerListener listener) {  
    listeners.add(listener);  
}
```

You can create a similar API for removing a listener. Also, add a method to trigger the action of being hungry to notify all listeners of the event:

```java
public void becomesHungry() {  
    for (HungerListener listener : listeners)  
        listener.hungry();  
}
```

Finally, from the Oven class, add code that listens to the event and implements the action when the event is fired:

```java
Person person = new Person();  
person.addHungerListener(() -> {  
    System.err.println("The person is hungry!");  
    // Oven takes action here  
});
```

And to try it out:

```java
person.becomesHungry();
```

For fully decoupled code, the last section should be in an independent class that has an instance of Person and Oven, and handles the logic between them. Similarly, we can add other actions for Fridge, Table, and so on. They all will get notified only once the Person becomesHungry.

---

### 中文

------

Java 中面向对象的核心概念之一是：**每个类都可以被视为一个组件（Component）**。这些组件可以被扩展或组合，形成更大的组件，最终构建成完整的应用程序。**组件就像乐高积木**，可以搭建出复杂的结构。

在 Java 中，**事件（Event）是改变组件状态的一种操作**。例如，如果你的组件是一个按钮，那么点击该按钮就是一个事件，会将按钮的状态改为“已点击”。

**事件不一定只发生在可视化组件上**。比如，一个 USB 组件可能会有“设备已连接”的事件；一个网络组件可能会有“数据已传输”的事件。**事件的引入帮助解耦了组件之间的依赖关系**。

假设我们有两个组件：**Oven（烤箱）和 Person（人）**。这两个组件是并行存在、独立工作的，我们**不应该让一个组件成为另一个的子组件**。在智能家居的情境中，我们希望当 Person 感到饿时，Oven 能开始准备食物。

有两种实现方式：

1. **Oven 每隔一段时间主动检查 Person 是否饿了**。这种方式会打扰 Person，而且如果要监控多个 Person，会给 Oven 增加很多负担。
2. **Person 提供一个公开的“Hungry（饿了）”事件，Oven 订阅这个事件**。一旦 Hungry 事件被触发，Oven 会收到通知并开始准备食物。

第二种方案使用了**事件驱动架构**，它可以**高效地监听和传递消息，同时避免 Oven 与 Person 之间的直接耦合**。Person 只需要触发事件，像 Oven、Fridge（冰箱）、Table（餐桌）等其他组件都可以监听这个事件，无需 Person 做任何特别处理。

------

**如何在 Java 中实现一个最小化的事件机制（以“饿了”为例）：**

**定义监听器接口**：

```java
@FunctionalInterface  
public interface HungerListener {  
    void hungry();  
}
```

**在 Person 类中定义监听器列表**：

```java
private List<HungerListener> listeners = new ArrayList<>();
```

**提供注册监听器的 API**：

```java
public void addHungerListener(HungerListener listener) {  
    listeners.add(listener);  
}
```

> 可类比地添加删除监听器的 API。

**添加触发“饿了”事件的方法**：

```java
public void becomesHungry() {  
    for (HungerListener listener : listeners)  
        listener.hungry();  
}
```

**在 Oven 类中注册监听器并处理事件**：

```java
Person person = new Person();  
person.addHungerListener(() -> {  
    System.err.println("The person is hungry!");  
    // Oven 在此处执行响应操作  
});
```

**测试触发事件**：

```java
person.becomesHungry();
```

------

为了实现真正解耦的代码，**最后一部分（事件监听逻辑）应写在一个独立类中**，它包含 Person 和 Oven 的实例，并负责它们之间的交互。同样地，你也可以为 Fridge、Table 等添加对应的监听逻辑。**一旦 Person 触发 becomesHungry，所有监听它的组件都会收到通知**。

---

## 23. Feedback Loops  

### English

---

- Because our product managers don’t know what they want, they find out from the customers. They sometimes get this wrong.
- Because our product managers don’t know everything about systems, they invite other experts to become stakeholders in the project. The stakeholders get it wrong.
- Because I don’t know what to code, I find out from our product managers. We sometimes get this wrong.
- Because I make mistakes while writing code, I work with an IDE. My IDE corrects me when I’m wrong.
- Because I make mistakes in understanding the existing code, I use a statically typed language. The compiler corrects me when I’m wrong.
- Because I make mistakes while thinking, I work with a pair. My pair corrects me when I’m wrong.
- Because my pair is human and also makes mistakes, we write unit tests. Our unit tests correct us when we’re wrong.
- Because we have a team that is also coding, we integrate with their code. Our code won’t compile if we’re wrong.
- Because our team makes mistakes, we write acceptance tests that exercise the whole system. Our acceptance tests will fail if we’re wrong.
- Because we make mistakes writing acceptance tests, we get three amigos together to talk through them. Our amigos will tell us if we’re wrong.
- Because we forget to run the acceptance tests, we get our build to run them for us. Our build will tell us if we’re wrong.
- Because we didn’t think of every scenario, we get testers to explore the system. Testers will tell us if it’s wrong.
- Because we only made it work on Henry’s laptop, we deploy the system to a realistic environment. The tests will tell us if it’s wrong.
- Because we sometimes misunderstand our product manager and other stakeholders, we showcase the system. Our stakeholders will tell us if we’re wrong.
- Because our product manager sometimes misunderstands the people that want the system, we put the system in production. The people who want it tell us if we’re wrong.
- Because people notice things going wrong more than things going right, we don’t just rely on opinions. We use analytics and data. The data will tell us if we’re wrong.
- Because the market keeps changing, even if we were right before, eventually we’ll be wrong.
- Because it costs money to get it wrong, we do all these things as often as we can. That way we are only ever a little bit wrong.
- Don’t worry about getting it right. Worry about how you’ll know it’s wrong, and how easy it will be to fix when you find out. Because it’s probably wrong.
- It’s OK to be wrong.

---

### 中文

---

- **因为我们的产品经理不知道他们真正想要什么**，他们会去从客户那里了解。但**他们有时会搞错**。
- **因为产品经理并不完全了解系统**，他们会邀请其他专家成为项目的干系人。**但干系人也会搞错**。
- **因为我不知道该写什么代码**，我去问产品经理。**我们有时也会搞错**。
- **因为我写代码时会犯错**，我使用 IDE。**IDE 会在我出错时纠正我**。
- **因为我理解现有代码时会犯错**，我使用静态类型语言。**编译器会在我出错时提示我**。
- **因为我思考时也会犯错**，我进行结对编程。**我的搭档会在我出错时指出问题**。
- **因为我的搭档也是人，也会犯错**，我们写单元测试。**单元测试会在我们出错时告诉我们**。
- **因为团队其他成员也在写代码**，我们需要与他们的代码集成。**如果我们做错了，代码不会通过编译**。
- **因为我们的团队也会犯错**，我们写验收测试来验证整个系统。**如果我们做错了，验收测试会失败**。
- **因为我们写验收测试时也会出错**，我们组织“三人小组”（Three Amigos）一起讨论。**他们会在我们出错时指出问题**。
- **因为我们有时会忘记运行验收测试**，我们配置自动构建系统来运行它们。**构建系统会告诉我们哪里错了**。
- **因为我们不可能想到所有场景**，我们请测试人员去探索系统。**测试人员会指出系统哪里错了**。
- **因为我们只在 Henry 的电脑上让系统跑通**，我们会部署到一个真实的环境中。**测试会告诉我们是否有问题**。
- **因为我们有时误解了产品经理和干系人**，我们展示系统的实际运行效果。**干系人会告诉我们哪里不对**。
- **因为产品经理有时也误解了最终用户的需求**，我们把系统部署到生产环境中。**用户会告诉我们哪里错了**。
- **因为人们更容易注意到错误而非正确的地方**，我们不会只靠主观判断。**我们用分析工具和数据，数据会告诉我们哪里错了**。
- **因为市场在不断变化**，即便我们之前做对了，**最终也会出错**。
- **因为犯错是要付出代价的**，所以我们尽可能频繁地做以上这些事情。**这样我们最多只会“稍微出错”**。
- **别担心怎么做对。请担心你怎么知道错了，以及发现错误后是否容易修复。因为它**大概率**是错的。**
- **出错没关系。**

---

## 24. Firing on All Engines  

### English

------

Traditional Java profilers use either byte code instrumentation or sampling (taking stack traces at short intervals) to determine where time was spent. Both approaches add their own skews and oddities. Understanding the output of those profilers is an art of its own and requires quite some experience.

Fortunately, Brendan Gregg, a performance engineer at Netflix, came up with flame graphs, an ingenious kind of diagram for stack traces that can be gathered from almost any system.
 A flame graph sorts and aggregates the traces up to each stack level, so that their count per level represents the percentage of the total time spent in that part of the code. Rendering those blocks as actual blocks (rectangles) with the width being proportional to the percentage and stacking the blocks onto each other turned out to be very useful.

![](img\01.png)

 The “flames” represent from bottom to top the progression from the entry point of the program or thread (main or an event loop) to the leaves of the execution in the tips of the flames. Note that the left-to-right order has no significance; often, it’s just alphabetical sorting. The same is true for colors. Only the relative widths and stack depths are relevant.

You can immediately see if certain parts of the program take an unexpectedly large amount of time. The higher up in the diagram that happens, the worse it is. Especially if you have a flame that’s very wide on top, you know you’ve found a bottleneck that is not delegating work elsewhere. After fixing the issue, measure again, and if the overall performance issue persists, revisit the diagram for new indications.

To address the shortcomings of traditional profilers, many modern tools make use of an internal JVM feature (AsyncGetCallTrace) that allows the gathering of stack traces outside of safepoints. Additionally, they combine measurement of JVM operations with native code and system calls to the operating system so that time spent in network, input/output (I/O), or garbage collection can become part of the flame graph as well.

Tools like Honest Profiler, perf-map-agent, async-profiler, and even IntelliJ IDEA make capturing the information and generating flame graphs really easy.
 In most cases, you just download the tool, provide the process ID (PID) of your Java process, and tell the tool to run for a certain amount of time and generate the interactive scalable vector graphics (SVG):

```bash
# download and unzip async profiler for your OS from:
# https://github.com/jvm-profiling-tools/asyncprofiler
./profiler.sh -d <duration> -f flamegraph.svg -s -o svg <pid> && \
open flamegraph.svg -a "Google Chrome"
```

The SVG that the tools produce is not just colorful but also interactive. You can zoom into sections, search for symbols, and more.
 Flame graphs are an impressively powerful tool to quickly get an overview of the performance characteristics of your programs; you can see hotspots immediately and focus on those. Including non-JVM aspects also helps with the bigger picture.

---

### 中文

------

传统的 Java 性能分析工具通常采用**字节码插桩**或**采样（在短时间间隔内获取堆栈跟踪）**的方法来确定代码执行的耗时。这两种方法各自有其偏差和怪癖。理解这些分析工具的输出本身就是一门艺术，需要相当的经验。

幸运的是，**Brendan Gregg**，Netflix 的性能工程师，提出了 **火焰图（flame graphs）**，这是一种巧妙的堆栈跟踪图表，可以从几乎任何系统中收集到堆栈信息。

**火焰图**将堆栈的每一层堆栈跟踪进行排序和聚合，使得每一层的数量代表了在该部分代码中花费的总时间的百分比。通过将这些块（矩形）渲染为实际的块，并使每个块的宽度与百分比成正比，并将这些块堆叠在一起，结果非常有用。

![img](img\01.png)

图中的“火焰”从下到上代表了程序或线程的入口点（主函数或事件循环）到执行路径的叶节点（火焰的顶部）。注意，**从左到右的顺序没有特别的意义**，通常只是按字母排序。颜色也只是装饰性，只有**相对宽度和堆栈深度**才是关键。

你可以立即看到程序的某些部分是否花费了意外的大量时间。**越往图表上方靠近的部分越严重**，尤其是如果顶部的火焰非常宽，就说明你找到了一个瓶颈，这个瓶颈没有将工作委托给其他地方。修复该问题后，重新测量，如果整体性能问题仍然存在，再次查看图表以寻找新的指示。

为了弥补传统性能分析工具的不足，许多现代工具使用了 JVM 的一个内部功能（**AsyncGetCallTrace**），允许在 safepoint 外部收集堆栈跟踪。此外，它们结合了 JVM 操作、原生代码和操作系统调用的测量，这样网络、输入/输出（I/O）或垃圾回收的时间也可以成为火焰图的一部分。

像 **Honest Profiler**、**perf-map-agent**、**async-profiler** 甚至 **IntelliJ IDEA** 等工具，使得捕获信息并生成火焰图变得非常简单。在大多数情况下，你只需下载工具，提供 Java 进程的 **进程 ID（PID）**，并指定工具运行的时间和生成交互式可缩放的矢量图（SVG）：

```bash
# 下载并解压 async-profiler 工具：
# https://github.com/jvm-profiling-tools/asyncprofiler
./profiler.sh -d <duration> -f flamegraph.svg -s -o svg <pid> && \
open flamegraph.svg -a "Google Chrome"
```

这些工具生成的 SVG 文件不仅色彩丰富，而且是交互式的。你可以放大查看某些部分，搜索符号等。

火焰图是一个非常强大的工具，可以快速获取程序性能特征的概览；你可以立即看到热点，集中精力解决这些问题。**包括非 JVM 方面的信息也有助于呈现更全面的全局视图**。

---

## 25. Follow the Boring Standards  

### English

---

At the beginning of the Java age, there were dozens of incompatible application servers on the market, and the server vendors followed completely different paradigms. Some servers were even partially implemented in native languages like C++. Understanding multiple servers was hard, and porting an application from one server to another was nearly impossible.
 APIs like JDBC (introduced with JDK 1.1), JNDI (introduced with JDK 1.3), JMS, JPA, or Servlets abstracted, simplified, and unified already established products. EJBs and CDI made the deployment and programming models vendor agnostic. J2EE, later Java EE and now Jakarta EE, and MicroProfile defined a minimal set of APIs an application server had to implement. With the advent of J2EE, a developer only had to know a set of J2EE APIs to develop and deploy an application.
 Although the servers evolved, the J2EE and Java EE APIs remained compatible. You never had to migrate your application to run on a newer release of the application server. Even upgrading to a higher Java EE version was painless. You only had to re-test the application without even recompiling it. Only if you wanted to take advantage of newer APIs did you have to refactor the application. With the introduction of J2EE, developers could master multiple application servers without delving too deep into their specifics.
 We have a very similar situation in the web/JavaScript ecosystem right now. Frameworks like jQuery, Backbone.js, AngularJS 1, Angular 2+ (completely different from AngularJS 1), ReactJS, Polymer, Vue.js, and Ember.js follow completely different conventions and paradigms. It has become hard to master multiple frameworks at the same time.
 The initial goal of many frameworks was to address incompatibility issues among different browsers. As browsers became surprisingly compatible, frameworks started to support data binding, unidirectional data flow, and even enterprise Java features like dependency injection.
 At the same time, browsers became not only more compatible but also provided features previously available only with third-party frameworks. The function querySelector is available in all browsers and provides comparable functionality to jQuery’s DOM access capabilities. Web Components with Custom Elements, Shadow DOM, and Templates enable developers to define new elements containing UI and behavior, and even to structure entire applications.
 As of ECMAScript 6, JavaScript became more similar to Java, and ES6 modules made bundling optional. The MDN (Mozilla Developer’s Framework) became a unified effort from Google, Microsoft, Mozilla, W3C, and Samsung to provide a home for web standards.
 Now it’s possible also to build frontends without frameworks. Browsers have an excellent track record for being backward compatible. All the frameworks have to use the browser APIs regardless, so by learning the standards you also understand the frameworks better. As long as browsers don’t introduce any breaking changes, just relying on web standards without any frameworks is enough to make your application last.
 Focusing on standards allows you to gain knowledge incrementally over time—an efficient way to learn. Evaluating popular frameworks is exciting, but the gained knowledge isn’t necessarily applicable to the next “hot thing.”

---

### 中文

---

在 Java 时代初期，市场上有数十种不兼容的应用服务器，且不同的服务器供应商遵循完全不同的设计理念。有些服务器甚至部分使用像 C++ 这样的本地语言实现。理解多个服务器的工作方式非常困难，而将应用从一个服务器迁移到另一个服务器几乎是不可能的。

像 **JDBC**（JDK 1.1 引入）、**JNDI**（JDK 1.3 引入）、**JMS**、**JPA**、**Servlets** 等 API 抽象化、简化并统一了已经存在的产品。**EJB** 和 **CDI** 使得部署和编程模型不再依赖特定厂商。**J2EE**，后来的 **Java EE** 和现在的 **Jakarta EE** 以及 **MicroProfile** 定义了应用服务器必须实现的最小 API 集。随着 J2EE 的出现，开发人员只需了解一组 J2EE API 就可以开发和部署应用程序。

尽管服务器不断发展，但 J2EE 和 Java EE 的 API 保持兼容。你不需要将应用迁移到更新的应用服务器版本。即使升级到更高版本的 Java EE，也几乎没有痛点。你只需要重新测试应用，而不需要重新编译。只有在想要利用新 API 时，才需要重构应用程序。通过 J2EE 的引入，开发者可以掌握多个应用服务器，而无需深入了解它们的具体细节。

现在，**Web/JavaScript 生态系统**也面临着类似的情况。像 **jQuery**、**Backbone.js**、**AngularJS 1**、**Angular 2+**（与 AngularJS 1 完全不同）、**ReactJS**、**Polymer**、**Vue.js** 和 **Ember.js** 等框架遵循了完全不同的约定和编程范式。与此同时，掌握多个框架变得越来越困难。

许多框架最初的目标是解决不同浏览器之间的不兼容问题。随着浏览器的兼容性不断提升，框架开始支持数据绑定、单向数据流，甚至像依赖注入这样的企业级 Java 特性。

同时，浏览器不仅变得更加兼容，还提供了以前只有第三方框架才能实现的功能。比如 **querySelector** 在所有浏览器中都可用，提供了类似 jQuery 的 DOM 访问能力。**Web 组件**（包括自定义元素、Shadow DOM 和模板）使开发人员能够定义新的包含 UI 和行为的元素，甚至可以构建整个应用程序。

随着 **ECMAScript 6** 的引入，JavaScript 越来越像 Java，并且 **ES6 模块** 使得打包变得可选。**MDN（Mozilla Developer Network）** 成为了一个统一的平台，得到了 Google、Microsoft、Mozilla、W3C 和 Samsung 等多方的支持，旨在为 Web 标准提供一个家园。

如今，开发前端应用不依赖框架也完全可行。浏览器有着出色的向后兼容性记录。所有框架都必须使用浏览器的 API，因此，学习 Web 标准同样能帮助你更好地理解框架。只要浏览器没有引入破坏性的变更，单纯依赖 Web 标准即可确保你的应用在未来仍能长时间使用。

专注于标准使你能够逐步积累知识，这是一种高效的学习方式。虽然评估流行的框架非常令人兴奋，但所获得的知识不一定适用于下一个“热门框架”。

---

## 26. Frequent Releases Reduce Risk  

### English

---

“Frequent releases reduce risk”—this is something you hear all the time in conversations about continuous delivery. How exactly is this the case? It sounds counterintuitive. Surely, releasing more often is introducing more volatility into production? Isn’t it less risky to hold off releasing as long as possible, taking your time with testing to guarantee confidence in the package? Let’s think about what we mean by risk.

**What Is Risk?**
 Risk is a factor of the likelihood of a failure happening combined with the worst-case impact of that failure:

Risk = Likelihood of failure × Worst-case impact of failure

Therefore, an extremely low-risk activity is when failure is incredibly unlikely to happen and the impact of the failure is negligible. Low-risk activities also include those where either of these factors—likelihood or impact—is so low that it severely reduces the effect of the other.
 Playing the lottery is low-risk: the chance of failing (i.e., not winning) is very high, but the impact of failing (i.e., losing the cost of the ticket) is minimal, so playing the lottery has few adverse consequences.
 Flying is also low-risk due to the factors being balanced the opposite way. The chance of a failure is extremely low—flying has a very good safety record—but the impact of a failure is extremely high. We fly often, as we consider the risk to be very low.
 High-risk activities are when both sides of the product are high—a high likelihood of failure and high impact. For example, they include extreme sports such as free solo climbing and cave diving.

**Large, Infrequent Releases Are Riskier**
 Rolling a set of changes into a single release package increases the likelihood of a failure occurring—a lot of change is happening all at once.
 The worst-case impact of a failure includes the release causing an outage, or severe data loss. Each change in a release could cause this to happen.
 The reaction to try and test for every failure is a reasonable one, but it is impossible. We can test for the known scenarios, but we can’t test for scenarios we don’t know about until they are encountered (the “unknown unknowns”).
 This is not to say that testing is pointless—on the contrary, it provides confidence that the changes have not broken expected, known behavior. The tricky part is balancing the desire for thorough testing against the likelihood of tests finding a failure, and the time taken to perform and maintain them.

Build up an automated suite of tests that protect against the failure scenarios you know about. Each time a new failure is encountered, add it to the test suite. Increase your suite of regression tests, but keep them light, fast, and repeatable.

No matter how much you test, production is the only place where success counts. Small, frequent releases reduce the likelihood of a failure. A release containing as small a change as possible reduces the likelihood that the release will contain a failure.
 There’s no way to reduce the impact of a failure—the worst case is still that the release could bring the whole system down and incur severe data loss—but we lower the overall risk with the smaller releases.
 **Release small changes often to reduce the likelihood of a failure and, therefore, the risk of change.**

------

### 中文

---

**“频繁发布减少风险”**——这是关于持续交付讨论中常常听到的一句话。究竟为什么会这样呢？这听起来有些违反直觉。肯定发布得越频繁，生产环境中的波动性就越大，不是吗？难道不是应该尽可能推迟发布，花更多时间进行测试，以确保对发布包有足够信心吗？让我们思考一下什么是“风险”。

**什么是风险？**
 风险是失败发生的可能性和失败的最坏影响相结合的因素：

风险 = 失败的可能性 × 失败的最坏影响

因此，极低风险的活动指的是失败发生的可能性非常小，且失败的影响微乎其微。低风险活动还包括那些其中一个因素（可能性或影响）非常低，从而大大降低另一个因素的影响的情况。
 买彩票是低风险的：失败的可能性（即不中）非常高，但失败的影响（即损失购票的钱）非常小，所以买彩票的负面后果很少。
 飞行也是低风险的，原因是这两者的因素恰好相反。失败的可能性极低——飞行有着非常好的安全记录——但失败的影响极大。我们经常乘坐飞机，因为我们认为风险非常低。
 高风险活动是指两者都很高——失败的可能性高且影响大。例如，极限运动，如无保护攀岩和洞潜，属于高风险活动。

**大规模、较少的发布反而更具风险**
 将一系列更改集中在单次发布包中，会增加失败发生的可能性——大量更改一次性发生。
 失败的最坏影响包括发布可能导致停机或严重的数据丢失。每个更改都可能导致这种情况发生。
 尽力去测试每一种失败是合理的，但这几乎是不可能的。我们可以测试已知的场景，但无法测试那些我们不知道的场景，直到它们发生为止（即“未知的未知”）。
 这并不是说测试毫无意义——相反，它能提供信心，确保更改没有破坏已知的预期行为。关键是要平衡全面测试的需求与测试发现失败的可能性，以及执行和维护测试所花费的时间。

构建一个自动化的测试套件，用来防范已知的失败场景。每当遇到新失败时，将其加入测试套件中。增加回归测试的覆盖范围，但保持测试轻量、快速且可重复。

无论你进行多少测试，生产环境才是衡量成功的唯一地方。小而频繁的发布减少了失败发生的可能性。发布包包含尽可能小的更改，降低了发布包包含失败的可能性。
 无法减少失败的影响——最坏的情况仍然是发布可能导致整个系统崩溃并造成严重数据丢失——但通过小范围发布，我们降低了整体风险。
 **频繁发布小的更改，以减少失败的可能性，从而降低变更风险。**

---

## 27. From Puzzles to Products  

### English

---

I went into programming because it was easy. I solved puzzles all day, then went home at five thirty and hung out with my friends. Twenty years later, I stay in software because it is hard.

It is hard because I moved from solving puzzles to growing products, from obsessing over correctness to optimizing for change.

Early in my career, I focused on one area of the system. My team leader gave me requirements for new features. This defined “correct,” and when the code achieved it, my task was done. The available means were restricted: we worked in C, with the standard library plus Oracle. For bonus points, we made the code look like everyone else’s.

Within a few years, my perspective broadened: I met with customers; I participated in the negotiation between design and implementation. If a particular new feature took the code in an awkward direction, then we went back to the customer with other suggestions to solve the same problem. I now help define the puzzles, as well as solve them.

Puzzle solving is a prerequisite, not the essence of my work. The essence of my work is to provide a capability to the rest of the organization (or to the world); I do this by operating a useful product. Puzzles have an end state as a goal—like a game of baseball, there is a fixed end. With products, the goal is to continue being useful—like a career in baseball, we want to keep playing.

Puzzles have defined means, like a board game. Growing products, we have the world of libraries and services, a plethora of puzzles solved for us. It is more like a game of pretend, open to what we can find.

Later in my career, my perspective broadened. When I push satisfactory code, this is only the beginning of my work. I want more than code change: I aim for system change. A new feature in my app must work with the current systems that depend on mine. I work with the people who own those systems to help them start using the new feature.

Now I see my job as designing change, not code. Code is a detail. Designing change means feature flags, backward compatibility, data migrations, and progressive deployment. It means documentation, helpful error messages, and social contact with adjacent teams.

A plus: all those if statements for feature flags, deprecated methods, and backward compatibility handling? These are no longer ugly. They express change—and change is the point, not some particular state of the code.

Designing change means building in observability so I can tell who is still using the deprecated feature, and who is getting value from the new one. In puzzle solving, I didn’t have to care whether people liked the feature, or even whether it was in production. Growing a product, I care very much. From experience in production, we learn how to make our products more useful.

Products don’t have one definition of “correct.” Many things are definitely not correct, so we can be careful about “not broken.” Beyond that, we aim for “better.”

Growing a product is hard in different ways than solving puzzles. Instead of hard work followed by a feeling of accomplishment, there is a slog of mushy work, through ambiguity and politics and context. The reward is more than a feeling, though: it can have a real impact on your company and thereby the world. That is more satisfying than ordinary fun.

---

### 中文

---

**我进入编程是因为它很简单**。我整天解谜，五点半回家和朋友们一起玩。二十年后，我留在软件行业是因为它很难。

它之所以很难，是因为我从解谜转向了发展产品，从专注于正确性转向了优化变更。

在我职业生涯的早期，我专注于系统的某一领域。我的团队领导给了我新功能的需求，这定义了“正确”，当代码实现了这些要求时，我的任务就完成了。那时可用的工具非常有限：我们用的是 C 语言，标准库加上 Oracle。如果要加分，我们还得让代码看起来像其他人的。

几年后，我的视野变得更加广阔：我开始与客户见面；我参与了设计与实现之间的谈判。如果某个新特性让代码走向一个尴尬的方向，我们会回去找客户，提出其他解决相同问题的建议。我现在不仅解决谜题，还帮助定义这些谜题。

解谜是我的工作的一部分，但不是本质。我的工作的本质是为组织（或世界）提供能力；我通过运营一个有用的产品来实现这一点。

谜题有一个终极目标——就像棒球比赛，有一个固定的结束。而产品的目标是持续有用——就像棒球生涯，我们希望继续比赛。

谜题有固定的解决方式，就像棋盘游戏。而在产品发展中，我们拥有库和服务的世界，许多谜题已经为我们解决。这更像是一场假装游戏，开放式的，取决于我们能找到什么。

随着我职业生涯的深入，我的视角变得更加开阔。当我提交一段满意的代码时，这只是我工作的开始。我不只想要代码的变更，我追求的是系统的变革。我的应用中的新特性必须与依赖于我的系统的现有系统兼容。我与那些系统的拥有者合作，帮助他们开始使用新特性。

现在，我将我的工作看作是设计变革，而不是编写代码。代码只是细节。

设计变革意味着特性标志、向后兼容性、数据迁移和渐进式部署。这意味着文档、友好的错误信息以及与相关团队的社交联系。

一个额外的好处：所有那些为特性标志、弃用方法和向后兼容性处理编写的 if 语句？它们不再丑陋了。它们表达了变革——而变革才是重点，而不是代码的某种特定状态。

设计变革意味着构建可观察性，这样我就能知道谁还在使用弃用的特性，谁从新特性中获得了价值。在解谜时，我不必关心人们是否喜欢这个特性，甚至不关心它是否已经投入生产。而在产品发展的过程中，我非常关心。从生产环境中，我们学会了如何让我们的产品更有用。

产品没有一个“正确”的定义。很多东西显然是错误的，所以我们可以小心“没有坏掉”。在此基础上，我们追求的是“更好”。

发展产品的难度与解谜的难度不同。它不像是努力工作后得到成就感，而是经历一段模糊的、充满政治和上下文的艰苦过程。然而，奖励不仅仅是成就感：它可能对你的公司甚至世界产生真正的影响。这比普通的乐趣更令人满足。

---

## 28. “Full-Stack Developer” Is a Mindset  

### English

---

In 2007—the year I started working my first job as a Java developer—the spectrum of technologies involved in day-to-day web development was quite narrow. Relational databases were in most cases the only type of database a developer needed to know. Frontend development was limited to HTML and CSS, spiced with a bit of JavaScript. Java development itself meant primarily working with Hibernate plus either Spring or Struts. This set of technologies covered almost everything necessary for building applications at that time. Most Java developers were actually full-stack developers, though that term had not yet been coined.

Things have changed significantly since 2007. We started building more and more complex user interfaces and handling this complexity with advanced JavaScript frameworks. We now use NoSQL databases, and almost every one of them is very different from the others. We stream data with Kafka, message with RabbitMQ, and do a lot more. In many cases, we also are responsible for setting up or maintaining the infrastructure with Terraform or CloudFormation, and we use or even configure Kubernetes clusters. Overall complexity has grown to the point that we have separate positions for frontend developer, backend developer, and DevOps engineer. Is it still possible to be a full-stack developer? That depends on how you understand the term.

You can’t be an expert in everything. Considering how much the Java ecosystem has grown, it’s hard to even be an expert in Java itself. The good thing is that you don’t have to be one. For many projects, especially in smaller companies, the most beneficial team setup is when each area of expertise is covered by at least one expert, but these experts don’t limit themselves to working only on that one area. Developers specialized in developing backend services can write frontend code—even if the code isn’t perfect—and the same thing goes for frontend developers. This helps move projects forward more quickly, as one person can develop a change that requires touching every layer of the application. It also leads to greater engagement during refinement meetings, as there are no tasks isolated only to a certain group of people.

Most importantly, not being strictly limited to one area changes how you approach tasks. There are no “It’s not my job” discussions anymore—developers are encouraged to learn. Having one person go on vacation is not an issue because there are always others who can cover for them—maybe not as efficiently, and maybe with results that aren’t quite as good, but enough to keep things moving forward. It also means that when there is a need to introduce a new technology to the stack, you don’t need to find a new team member, because existing team members are already comfortable leaving the comfort zone of their expertise.

Full-stack developer is therefore a mindset. It’s being senior and junior at the same time, with a can-do attitude.

---

### 中文

---

**2007年，我开始了我的第一份Java开发工作——那时日常Web开发所涉及的技术范围相对较窄。**
 关系型数据库通常是开发者需要了解的唯一类型的数据库。前端开发仅限于HTML和CSS，偶尔加一点JavaScript。Java开发本身主要意味着使用Hibernate并结合Spring或Struts。这一套技术几乎涵盖了当时构建应用所需的一切。大多数Java开发者实际上是全栈开发者，尽管当时还没有这个术语。

**自2007年以来，情况发生了显著变化。**
 我们开始构建越来越复杂的用户界面，并通过先进的JavaScript框架来处理这些复杂性。现在，我们使用NoSQL数据库，而几乎每一种NoSQL数据库都有着与众不同的特性。我们使用Kafka进行数据流处理，使用RabbitMQ进行消息传递，还有很多其他工具。在很多情况下，我们还需要使用Terraform或CloudFormation来设置或维护基础设施，甚至配置Kubernetes集群。整体的复杂性增长到我们需要为前端开发、后端开发和DevOps工程师分别设立职位。那还可能是全栈开发者的时代吗？这取决于你如何理解这个术语。

**你不可能在所有领域都成为专家。**
 考虑到Java生态系统的发展，甚至很难在Java本身上成为专家。幸运的是，你不需要成为专家。在许多项目中，尤其是在小公司里，最有效的团队结构是每个领域都有至少一个专家，但这些专家不局限于只做自己擅长的领域。专注于后端服务的开发者也能写前端代码——即使代码不完美，前端开发者也是如此。这有助于项目更快地推进，因为一个人就能开发一个需要触及应用每一层的变更。同时，这也促进了更大的参与感，因为没有任务是单独由某个特定小组来完成的。

**最重要的是，不再严格局限于一个领域，改变了你处理任务的方式。**
 不再有“这不是我的工作”的讨论——开发者被鼓励去学习。当有一个人去度假时，也不成问题，因为总会有其他人能替代他们——可能不那么高效，或者结果不完全一样好，但足以保持项目进展。这也意味着，当需要将新技术引入技术栈时，你不需要找新的团队成员，因为现有的团队成员已经习惯了走出自己擅长领域的舒适区。

**因此，全栈开发者是一种思维方式。**
 它意味着同时具备资深和初级的素质，并且拥有一种能做就做的态度。

---

## 29. Garbage Collection Is Your Friend  

### English

---

Poor old garbage collection. One of the unsung heroes of Java, often blamed, rarely praised. Before Java made garbage collection mainstream, programmers had little choice but to track all the memory they’d allocated manually, and deallocate it once nothing was using it anymore. This is hard. Even with discipline, manual deallocation is a frequent cause of memory leaks (if too late) and crashes (if too early).

Java GC (garbage collection) is often thought of as a necessary cost, and “reduce time spent in GC” is common performance guidance. However, modern garbage collection can be faster than `malloc/free`, and time spent in GC can speed everything up. Why? Garbage collectors do more than memory deallocation: they also handle the allocation of memory and the arrangement of objects in memory. A good memory management algorithm can make allocation efficient by reducing fragmentation and contention. It can also boost throughput and lower response times by rearranging objects.

Why does the location of an object in memory affect application performance? A high proportion of a program’s execution time is spent stalled in hardware, waiting for memory access. Heap access is geologically slow compared to instruction processing, so modern computers use caches. When an object is fetched into a processor’s cache, its neighbors are also brought in; if they happen to be accessed next, that access will be fast. Having objects that are used at the same time near each other in memory is called object locality, and it’s a performance win.

The benefits of efficient allocation are more obvious. If the heap is fragmented, when a program tries to create an object, it will have a long search to find a chunk of free memory big enough, and allocation becomes expensive. As an experiment, you can force GC to compact more; it will massively increase GC overhead, but often application performance will improve.

GC strategies vary by JVM implementation, and each JVM offers a range of configurable options. JVM defaults are usually a good start, but it is worth understanding some of the mechanics and variations possible. Throughput may be traded off against latency, and workload affects the optimum choice.

Stop-the-world collectors halt all program activity so they can collect safely. Concurrent collectors offload collection work to application threads, so there are no global pauses; instead, each thread will experience tiny delays. Although they do not have obvious pauses, concurrent collectors are less efficient than stop-the-world ones, so they’re suitable for applications where pauses would be noticed (such as music playback or a GUI).

Collection itself is done by copying or by marking and sweeping. With mark-and-sweep, the heap is crawled to identify free space, and new objects get allocated into those gaps. Copying collectors divide the heap into two areas. Objects are allocated in the “new space.” When that space is full, its nongarbage contents are copied to the reserve space and the spaces are swapped. In a typical workload, most objects die young (this is known as the generational hypothesis). With short-lived objects, the copying step will be super fast (there’s nothing to copy!). However, if objects hang around, collection will be inefficient.

Copying collectors are great for immutable objects and a disaster with object pooling “optimizations” (usually a bad idea anyway). As a bonus, copying collectors compact the heap, which allows near-instant object allocation and fast object access (fewer cache misses).

When evaluating performance, it should be related to business value. Optimize transactions per second, mean service time, or worst-case latency. But don’t try to micro-optimize time spent in GC, because time invested in GC can actually help program speed.

---

### 中文

---

**可怜的垃圾回收（GC）常被责怪，却鲜少被称赞，是Java中一个被低估的英雄。**
 在Java让垃圾回收成为主流之前，程序员几乎只能手动追踪所有分配的内存，并在不再使用时手动释放。这很难做到——即使再有纪律，手动释放内存仍然常常导致内存泄漏（释放太晚）或程序崩溃（释放太早）。

**Java的GC经常被视为“必要的代价”，而“减少GC时间”则成了性能优化的常见建议。**
 但实际上，现代GC可能比malloc/free更快，GC时间有时反而能提升整体性能。为什么？因为垃圾回收不仅仅是“释放内存”这么简单：它还负责**分配内存**，并优化**内存中对象的布局**。优秀的内存管理算法可以减少碎片与线程争用，使对象分配更高效；还可以通过重新排列对象提升吞吐量并降低响应时间。

**那对象在内存中的位置为什么会影响应用性能？**
 因为程序的大量运行时间都消耗在等待内存访问上——这是CPU级别的瓶颈。相比处理指令，访问堆内存“地质般缓慢”，所以现代计算机依赖缓存机制（cache）。当某个对象被加载进CPU缓存时，它的“邻居”对象通常也会被带入；如果下一个要访问的对象就在附近，那么访问会非常快。这种将**同时使用的对象靠近放置**的现象叫做**对象局部性**（object locality），能显著提升性能。

**更明显的例子是分配效率：**
 如果堆出现碎片，程序分配新对象时必须搜索足够大的空闲内存块，过程就会变慢。你可以试着让GC更频繁地压缩内存（compact heap），虽然GC开销会上升，但程序运行可能会更快。

**不同的JVM实现使用不同的GC策略，且每个JVM都支持大量可配置项。**
 JVM的默认配置通常够用，但了解其底层原理与可选策略是有益的。比如，为了不同的业务需求，你可以在**吞吐量**与**延迟**之间做取舍，而最优方案也会随**工作负载**而变。

**比如：**

- **Stop-the-world回收器**会暂停所有程序线程，确保安全回收。这会造成可感知的停顿。
- **并发回收器（concurrent collectors）**则把GC工作分散到各应用线程上，从而避免全局暂停，不过每个线程会有些小的延迟。虽然并发GC没有明显的停顿，但效率通常低于stop-the-world，因此适用于对暂停特别敏感的程序，比如音乐播放或GUI。

**具体回收策略也不同：**

- **Mark-and-sweep（标记-清除）**：遍历堆，标记活动对象，再清除未标记的，最后将新对象插入腾出的空隙。
- **Copying（复制式）GC**：将堆划分为两部分，对象一开始都分配在“新空间”，等用满时将活跃对象复制到另一空间，并交换角色。

大多数程序的工作负载符合**“代际假设”**（generational hypothesis）：大多数对象“短命”。这种情况下复制成本很低，因为大多数对象早早就成了垃圾（根本不需要复制）。但如果对象生命周期较长，复制GC就会变得低效。

**复制式GC非常适合不可变对象，但如果你试图通过对象池来“优化”性能，那就适得其反——GC效率会急剧下降。**
 （其实，对象池在现代Java中通常是反模式。）
 复制式GC还能带来额外好处：它**顺带整理了堆内存（heap compaction）**，使得新对象的分配近乎瞬时，并降低缓存未命中率（cache misses）。

**性能优化最终要回归到业务价值。**
 关注每秒事务数、平均响应时间、或者最坏情况下的延迟，而**不是一味追求GC时间最小化**。因为合理的GC时间其实是在**帮助程序更快运行**。

---

## 30. Get Better at Naming Things  

### English

------

> *What is above all needed is to let the meaning choose the word, and not the other way around… the worst thing one can do with words is surrender to them.*
>  —George Orwell

Getting better at naming things improves the maintainability of the code you write more than anything else. There’s more to maintainable code than good naming, but naming things is famously hard—and usually neglected. Fortunately, programmers like a challenge.

First, avoid names that are meaningless (`foo`), too abstract (`data`), duplicated (`data2`), vague (`DataManager`), abbreviated or short (`dat`). Single letters (`d`) are the worst of all. These names are ambiguous, which slows everyone down because programmers spend more time reading code than writing it.

Next, adopt guidelines for better names—words with precise meanings that make the code say what it means.

Use up to four words for each name, and don’t use abbreviations (except for `id` and those you adopt from the problem domain). One word is rarely enough; using more than four is clumsy and stops adding meaning. Java programmers use long class names but often prefer short local variable names, even when they’re worse.

Learn and use problem domain terminology—*domain-driven design*’s ubiquitous vocabulary. This is often concise: in publishing, the correct term for text changes might be *revision* or *edit*, depending on who makes the change. Instead of making words up, read the topic’s Wikipedia page, talk to people who work in that domain, and add the words they use to your glossary.

Replace plurals with collective nouns (e.g., rename `appointment_list` to `calendar`). More generally, enlarge your English vocabulary so you can make names shorter and more precise. This is harder if you’re a non-native English speaker, but everyone has to learn the domain jargon anyway.

Rename pairs of entities with relationship names (for instance, rename `company_person` to `employee`, `owner`, or `shareholder`). When this is a field, you’re naming the relationship between the field’s type and the class it’s a member of. In general, it’s often worth extracting a new variable, method, or class just so you can explicitly name it.

Java helps you with good naming because you name classes separately from objects. Don’t forget to actually name your types instead of relying on primitive and JDK classes: instead of `String`, you should usually introduce a class with a more specific name, such as `CustomerName`. Otherwise, you need comments to document unacceptable strings, such as empty ones.

Don’t mix up class and object names: rename a date field called `dateCreated` to `created`, and a Boolean field called `isValid` to `valid`, to avoid duplicate type noise. Give objects different names: instead of a `Customer` called `customer`, use a more specific name, such as `recipient` when sending a notification, or `reviewer` when posting a product review.

The first step in naming is to apply the basic naming conventions, such as using noun phrases for class names. The next step is good naming technique using guidelines like these. But guidelines have limits. The JavaBeans specification taught a generation of Java programmers to break object encapsulation and use vague method names, like `setRating` when `rate` might be better, for example. You don’t need to name methods that aren’t imperative with verb phrases, as in builder APIs like:

```java
Customer.instance().rating(FIVE_STARS).active();
```

In the end, naming mastery is about choosing which rules to break.

---

### 中文

------

> **“最重要的是让‘含义’选择‘词语’，而不是反过来……使用语言最糟糕的方式，就是向语言屈服。”**
>  —— 乔治·奥威尔

在所有提升代码可维护性的技巧中，**提升命名能力比任何事都更有效**。虽然可维护性不仅仅靠命名，但**命名既难又常被忽视**。好在程序员向来喜欢接受挑战。

首先，**避免使用无意义或模糊不清的名字**，例如：

- `foo`（无意义）
- `data`（过于抽象）
- `data2`（重复）
- `DataManager`（模糊）
- `dat`（缩写/太短）
- `d`（单字母，是最差的命名）

这类名字含义不清，让人难以理解，**因为程序员花在读代码上的时间远比写代码多**。

------

**接着，应遵循一些命名指南，选用有准确含义的词语，让代码表达它真正的含义。**

- 每个名字**最多使用四个词**，避免使用缩写（除了 `id` 或业务领域的术语）。
- 一个词通常不够表达清楚，**四个以上又显得冗长且无益**。
- Java 开发者通常**类名很长，但局部变量名却太短，即使不合适也照用**。

------

**学会并使用领域术语**（领域驱动设计中的通用语言）。这些词往往简洁又准确：

- 例如在出版行业中，修改文本可以称为 `revision` 或 `edit`，取决于是谁改的。
- **不要发明新词**，不如查 Wikipedia 或请教领域专家，然后将术语加入术语表。

------

**避免使用复数，改用集合名词**：

- 如把 `appointment_list` 改名为 `calendar`。

更进一步，**扩大你的英语词汇量**，让名字更简洁精准。对非英语母语者来说可能更难，但**每个人都必须掌握行业术语**。

------

**用更具语义的关系名来替代中性组合名**：

- 比如将 `company_person` 重命名为 `employee`、`owner` 或 `shareholder`。
- 如果是字段名，它表示这个字段的类型与其所在类之间的关系。
- **必要时可为此单独抽出变量、方法或类来命名**，只为表达更明确的语义。

------

Java 的类型系统也鼓励好命名：

- 不要总用原始类型或标准类（如 `String`），
- **用更具体的类型名替代**，如 `CustomerName`，否则你需要注释来说明哪些字符串不合法（如空字符串）。

------

**避免类名和变量名重复或混淆**：

- `dateCreated` 可改为 `created`（避免类型噪声），
- `isValid` 可简化为 `valid`。

实例命名也要更具体：

- 不要用 `Customer customer`，
- 改为更具上下文含义的名词，如 `recipient`（通知收件人）、`reviewer`（产品评价人）。

------

命名的第一步是**遵守基本命名约定**，比如类名用名词短语。接下来要使用更成熟的技巧和命名指南。但**规则总有例外**：

- JavaBeans 规范教会程序员写出模糊又破坏封装的方法名，如 `setRating`，其实 `rate` 更合适。
- 有些方法不需要用动词短语命名，尤其是 builder 风格的链式调用，如：

```java
Customer.instance().rating(FIVE_STARS).active();
```

------

✅ 最终，命名的真正功力在于：**知道什么时候可以、也应该打破规则。**

---

## 31. Hey Fred, Can You Pass Me the HashMap?  

### English

------

Picture the scene: an old, cramped office with several old wooden desks set back-to-back. Each desk equipped with an old black rotary phone and ashtrays dotted about. On one of the desks is a black HashMap that contains an ArrayList filled with customer data.
 Sam, needing to contact Acme Inc., scans the office looking for the HashMap. Eyes darting, he spots the HashMap and shouts out, “Hey Fred, can you please pass me the HashMap?” Can you picture that… yup, I didn’t think so…

An important part of writing a program is the development of a vocabulary. Each word in that vocabulary should be an expression of something that is part of the domain we’re modeling. After all, it is this code expression of our model that others will have to read and understand. Consequently, our choice of vocabulary can either help or hinder understanding of our code. Oddly enough, the choice of vocabulary impacts much more than readability: the words we use affect how we think about the problem at hand, which, in turn, impacts the structure of our code, our choice of algorithms, how we shape our APIs, how well the system will fit our purpose, how easily it will be maintained and extended, and, finally, how well it will perform. Yes, the vocabulary we develop when writing code matters a lot. So much so that keeping a dictionary at hand can be strangely useful when writing code.

Returning to the ridiculous example, of course, no one would ask for the HashMap. You’d most likely draw a blank stare from Fred if you asked him to pass the HashMap. Yet when we look at how to model the domain, we hear about the need to look up customer contact data that is organized by name. That screams HashMap. If we dig deeper into the domain, then we’ll likely discover that the contact information is written on an index card that is neatly packed away in a Rolodex.
 Replacing the word HashMap with the word Rolodex not only offers a better abstraction in our code but it will also have an immediate impact on how we think about the problem at hand, and it offers a better way to express our thoughts to the reader of our code.

The takeaway here is that technical classes rarely have a place in the vocabulary of the domains we’re working in. Instead, what they offer are building blocks for deeper, more meaningful abstractions. The need for utility classes should be a red flag that you’re missing an abstraction. Additionally, technical classes in APIs should also be a red flag.

For example, consider the case where a method signature takes a String to represent a first name and a String for a last name. These are used to look up data held in a HashMap:

```java
return listOfNames.get(firstName + lastName);
```

The question is, what is the missing abstraction? Having two fields forming a key is commonly known as a composite key. Using this abstraction we get:

```java
return listOfNames.get(new CompositeKey(firstName, lastName));
```

When you make this change in a benchmark, the code runs three times faster. I would argue it is also more expressive: using CompositeKey better expresses the essence of the problem at hand.

---

### 中文

------

想象这样一个场景：一间狭小、陈旧的办公室，几张老旧木桌背靠背地摆着。每张桌上都有一部黑色转盘电话，桌上随处可见烟灰缸。其中一张桌子上放着一个黑色的 `HashMap`，里面装着一个包含客户数据的 `ArrayList`。

山姆需要联系 Acme 公司，他扫视全办公室寻找那份 `HashMap`，眼睛一转，发现它后大喊：“嘿 Fred，你能把那个 `HashMap` 递给我吗？”你能想象这个画面吗？嗯，我猜你不能……

------

**编程中最重要的部分之一就是建立一个词汇体系**。这个词汇表中的每一个词，**都应当表达我们所建模领域中的某个具体概念**。毕竟，**他人要理解的正是这些代码中所体现的模型**。

所以，**我们使用的词语不仅影响代码的可读性，更会影响我们如何思考问题**，进而影响：

- 代码结构的设计、
- 所选算法、
- API 的组织方式、
- 系统是否贴合实际需求、
- 系统的可维护性和可扩展性，
- 乃至系统的性能。

因此，**开发代码时所使用的词汇极其重要**。重要到什么程度？——**手边放一本词典其实是很有帮助的！**

------

回到刚才那个荒谬的例子：当然没人会真的对 Fred 说“把 HashMap 递给我”。要是你真这么说，**Fred 很可能会一脸茫然**。

但当我们思考如何建模这个领域时，我们常常说的是“根据名字查找客户联系信息”——这听上去确实像是 `HashMap`。

但如果我们深入挖掘业务领域，你会发现客户联系信息其实是写在卡片上的，并且**整齐地存放在一个 Rolodex（旋转式名片册）中**。

于是，**用 `Rolodex` 替换 `HashMap`，不仅提供了更贴切的抽象**，还会**立即改变我们对问题的思考方式**，同时也更清晰地向阅读代码的人表达了意图。

------

**关键要点是：技术类（如 HashMap）通常不应出现在业务领域的语言中**。它们的作用是作为底层构建块，**让我们构建出更深层、更有意义的抽象**。

- **当你频繁依赖工具类（utility class）时，其实是个警告信号：说明你缺失了一个更合理的抽象层**。
- **如果技术类出现在 API 中，同样应当引起警惕**。

------

例如，假设某个方法接收两个字符串，一个代表名、一个代表姓，然后通过拼接去查 `HashMap`：

```java
return listOfNames.get(firstName + lastName);
```

这里的问题是：**缺少了合适的抽象**。两个字段组成键，这其实就是**复合键（composite key）**的典型场景。

我们可以将代码重构为：

```java
return listOfNames.get(new CompositeKey(firstName, lastName));
```

- 在性能基准测试中，这种改动**让代码运行速度提升了三倍**。
- 更重要的是，**它更具表现力——CompositeKey 明确表达了当前问题的本质**。

---

## 32. How to Avoid Null  

### English

------

Tony Hoare calls null the “billion-dollar mistake.” It’s a mistake, and that’s why you should get in the habit of forbidding code from using null. If you have a reference to an object that might be null, you have to remember to do a null check before trying to call any method of it. But since there’s no obvious difference between a null reference and a non-null one, it’s too easy to forget and get a NullPointerException.

The most future-proof way to avoid issues is to use an alternative when possible.

**Avoid Initializing Variables to Null**
 It is usually not a good idea to declare a variable until you know what value it should hold. For complex initialization, move all the initialization logic to a method. For example, instead of doing this:

```java
public String getEllipsifiedPageSummary(Path path) {
    String summary = null;
    Resource resource = this.resolver.resolve(path);
    if (resource.exists()) {
        ValueMap properties = resource.getProperties();
        summary = properties.get("summary");
    } else {
        summary = "";
    }
    return ellipsify(summary);
}
```

Do the following:

```java
public String getEllipsifiedPageSummary(Path path) {
    var summary = getPageSummary(path);
    return ellipsify(summary);
}

public String getPageSummary(Path path) {
    var resource = this.resolver.resolve(path);
    if (!resource.exists()) {
        return "";
    }
    var properties = resource.getProperties();
    return properties.get("summary");
}
```

Initializing a variable to null might leak null unintentionally if you are not careful with your error-handling code. Another developer might change the control flow without realizing the issue—and that other developer might be you three months after the code was first written.

**Avoid Returning Null**
 When you read the signature of a method, you should be able to understand if it always returns a T or if sometimes it doesn’t. Returning an `Optional<T>` is a better option that makes the code more explicit. Optional’s API makes it very easy to deal with the scenario where no T was produced.

**Avoid Passing and Receiving Null Parameters**
 If you need a T, ask for it; if you can get by without one, then don’t ask for it. For an operation that can have an optional parameter, create two methods: one with the parameter and one without.

For example, the method `drawImage` from the Graphics class in the JDK has a version that receives five parameters and a sixth parameter, an `ImageObserver`, which is optional. If you don’t have an `ImageObserver`, you need to pass null like this:

```java
g.drawImage(original, X_COORD, Y_COORD, IMG_WIDTH, IMG_HEIGHT, null);
```

It would have been better to have another method with just the first five parameters.

**Acceptable Nulls**
 When is it acceptable to use null, then? As an implementation detail of a class, i.e., the value of an attribute. The code that needs to be aware of that absence of value is contained to the same file, and it’s much more simple to reason about it and not leak null.

So remember, unless you have an attribute, it’s always possible to avoid using null using a superior construct in your code. If you stop using null where you don’t need it, then it becomes impossible to leak null and have a NullPointerException. And if you avoid these exceptions, you’ll be part of the solution to the billion-dollar problem instead of being part of it.

---

### 中文

------

Tony Hoare 称 `null` 是“**价值十亿美元的错误**”。它的确是一个错误，因此你应该**养成禁止使用 `null` 的习惯**。
 如果一个对象引用可能为 `null`，你必须每次调用它的方法之前都进行检查。**但因为 `null` 和非 `null` 的引用在代码中看不出明显区别，这种检查非常容易被忘记，从而导致 NullPointerException**。

最稳妥的方式是：**尽可能使用替代方案来避免 `null` 的问题**。

------

✅ **不要将变量初始化为 null**

通常，在你**清楚变量应持有什么值之前，不要声明它**。如果初始化过程较复杂，**将初始化逻辑提取到一个方法中**。例如，不要这样写：

```java
public String getEllipsifiedPageSummary(Path path) {
    String summary = null;
    Resource resource = this.resolver.resolve(path);
    if (resource.exists()) {
        ValueMap properties = resource.getProperties();
        summary = properties.get("summary");
    } else {
        summary = "";
    }
    return ellipsify(summary);
}
```

应改为：

```java
public String getEllipsifiedPageSummary(Path path) {
    var summary = getPageSummary(path);
    return ellipsify(summary);
}

public String getPageSummary(Path path) {
    var resource = this.resolver.resolve(path);
    if (!resource.exists()) {
        return "";
    }
    var properties = resource.getProperties();
    return properties.get("summary");
}
```

**变量初始化为 `null` 很容易在出错时不小心泄漏出去**，而后续的开发者（可能就是你自己几个月后）在修改控制流时可能不会意识到这个问题。

------

✅ **不要返回 null**

看一个方法签名时，**我们应该清楚它是否总是返回某个类型 `T`，还是可能不返回**。
 **返回 `Optional<T>` 是更好的做法，能显式表达“可能为空”的语义**。并且 `Optional` 的 API 能够很好地处理“没有值”的情况。

------

✅ **不要传入或接收 null 参数**

- **如果你需要一个对象，就明确要求它；如果不需要，就不要让它成为参数**。
- 对于可选参数，**应当提供两个重载方法：一个有参数，一个没有参数**。

例如，JDK 中 `Graphics` 类的 `drawImage` 方法接收 6 个参数，其中第六个 `ImageObserver` 是可选的。没有时你必须传 `null`：

```java
g.drawImage(original, X_COORD, Y_COORD, IMG_WIDTH, IMG_HEIGHT, null);
```

更好的做法是：**提供一个只接收前 5 个参数的方法重载版本**。

------

✅ **什么时候可以使用 null？**

**仅当它是类的内部实现细节，比如一个属性的值时可以考虑使用 null**。
 此时，**需要关心该值是否为 null 的代码只存在于同一个类中，范围受限，易于控制，不易外泄**。

------

总结：

- 除非是属性，否则几乎总能用更好的方式避免使用 `null`。
- **减少对 `null` 的依赖，就能从根本上防止 NullPointerException 的出现**。
- 如果你能避免这些异常，你就**从“十亿美元的问题”变成了“解决问题的人”**。

---

## 33. How to Crash Your JVM  

### English

---

There are so many new APIs, cool libraries, and must-try techniques you need to know that it can be hard to stay up-to-date.
 But is this really all you need to know as a Java developer? What about the environment your software is running in? Couldn’t it be that a problem here could crash your software, and you wouldn’t even be able to understand or find that problem because it’s outside the world of libraries and code? Are you prepared to consider another perspective?

Here is a challenge: try to find ways to crash your Java Virtual Machine! (Or, at least, bring its normal execution to a sudden and unexpected stop.) The more ways you know, the better you understand your surroundings and appreciate what can go wrong with a running software system.

Here are a few to get you started:

1. Try to allocate as much memory as you can. RAM is not endless—if no more RAM can be allocated, your allocation will fail.
2. Try to write data to your hard disk until it is full. Same problem as with RAM: though bigger than RAM, disk space is not endless either.
3. Try to open as many files as you can. Do you know the maximum number of file descriptors for your environment?
4. Try to create as many threads as you can. On a Linux system, you can look at `/proc/sys/kernel/pid_max` and you will see how many processes may be running on your system. How many threads are you allowed to create on your system?
5. Try to modify your own `.class` files in the filesystem—the current run of your application will be its last!
6. Try to find your own process ID, and then try to kill it by using `Runtime.exec` (e.g., by calling `kill -9` on your process ID).
7. Try to create a class at runtime that only calls `System.exit`, load that class dynamically via the class loader, then call it.
8. Try to open as many socket connections as possible. On a Unix system, the maximum number of possible socket connections equals the maximum number of file descriptors (often 2,048). How many are available where your application is running?
9. Try to hack your system. Download an exploit via code or by using `wget`. Execute the exploit, and then call `shutdown -h` as root on a Unix system or `shutdown /s` as administrator on a Windows system.
10. Try jumping without a safety net. Part of Java’s safety comes from its language design and part from the bytecode verification in your JVM. Run your JVM with `-noverify` or `-Xverify:none`, which disables all bytecode verification, and write something that would otherwise not be allowed to run.
11. Try using `Unsafe`. This backdoor class is used to get access to low-level facilities such as memory management. All the syntax of Java, all the safety of C!
12. Try going native. Write some native code. All the syntax of C, all the safety of C!

Try to find your own ways to crash your JVM and ask colleagues for their ideas. Also consider asking job interview candidates how they might go about this. Whatever their answer, you will soon learn whether the interviewee is able to see the world outside their IDE window.

P.S. If you find other creative ways to crash a JVM, please let me know!

---

### 中文

------

随着新的 API、酷炫的库和必须掌握的技术层出不穷，保持与时俱进确实很难。
 但作为一个 Java 开发者，这些真的是你需要知道的全部吗？那么，你的软件运行环境呢？是否可能在这个环境中出现问题，导致软件崩溃，而你却无法理解或找到问题，因为它超出了库和代码的范畴？你准备好从另一个角度思考了吗？

### 挑战：尝试找到让你的 Java 虚拟机崩溃的方法！

（或者，至少让它的正常执行突然并意外地停止。）
 你了解的方式越多，就越能理解你的运行环境，也能更好地意识到在运行中的软件系统中可能出现的问题。

以下是几个起点，帮助你开始：

- **尝试分配尽可能多的内存**。RAM 不是无限的——如果无法分配更多内存，分配就会失败。
- **尝试将数据写入硬盘，直到硬盘满**。和 RAM 一样，尽管硬盘比 RAM 更大，但它也不是无限的。
- **尝试打开尽可能多的文件**。你知道你所在环境中最大文件描述符的数量吗？
- **尝试创建尽可能多的线程**。在 Linux 系统中，你可以查看 `/proc/sys/kernel/pid_max`，它显示你系统上最多可以运行多少个进程。你的系统允许你创建多少个线程？
- **尝试修改文件系统中的 `.class` 文件**——当前运行的应用程序将是它的最后一次运行！
- **尝试找到自己的进程 ID，然后尝试通过 Runtime.exec 杀死它**（例如，使用 `kill -9` 命令终止进程）。
- **尝试在运行时创建一个只调用 `System.exit` 的类**，通过类加载器动态加载该类，然后调用它。
- **尝试打开尽可能多的套接字连接**。在 Unix 系统中，套接字连接的最大数量等于文件描述符的最大数量（通常为 2048）。你的应用程序所在的系统中有多少个可用？
- **尝试入侵你的系统**。通过代码或使用 `wget` 下载一个漏洞，执行该漏洞，然后在 Unix 系统上以 root 身份调用 `shutdown -h`，或在 Windows 系统上以管理员身份调用 `shutdown /s`。
- **尝试没有安全网地跳跃**。Java 的安全性部分来自于语言设计，部分来自 JVM 的字节码验证。运行 JVM 时加上 `-noverify` 或 `-Xverify:none`，禁用所有字节码验证，并编写一些在正常情况下无法运行的代码。
- **尝试使用 Unsafe**。这个后门类用于访问低级设施，例如内存管理。Java 的语法，一切都像 C 一样安全！
- **尝试使用本地代码**。编写一些本地代码，拥有 C 的语法，却具备 C 的安全性！

------

### **总结**：

尝试自己找到让 JVM 崩溃的方法，并向同事请教他们的想法。如果是面试候选人，你可以问他们如何实现这个目标。无论他们的答案是什么，你都能很快了解到面试者是否能够看到 IDE 窗口之外的世界。

------

**附言**：如果你找到其他创造性的方式让 JVM 崩溃，欢迎告诉我！

---

## 34. Improving Repeatability and Auditability with Continuous Delivery  

### English

---

Handcrafting is valued because of the time and effort involved and small imperfections that give character and uniqueness. While these qualities might be valued in food, furniture, or art, when it comes to delivering code, these qualities are serious impediments to an organization’s success.

Humans are not well suited to performing repetitive tasks. No matter how detail-oriented a person might be, mistakes happen when performing the series of complex steps required to deploy an application. A step might be skipped, run in the wrong environment, or otherwise performed incorrectly, leading to a deployment failure.

When deployment failures happen, a considerable amount of time can be spent investigating what went wrong. This investigative process is hindered as manual processes often lack a central point of control and can be opaque. When a root cause is determined, the typical resolution is to add more layers of control to prevent the problem from happening again, but this usually only succeeds in making the deployment process more complicated and painful!

Organizations struggling to deliver code is not news, so to address this, organizations have begun to migrate to continuous delivery (CD). CD is an approach of automating the steps of delivering code to production. From the time when a developer commits a change to when that change is deployed to production, any step that can be automated, should be—testing, change control, the process of deployment, etc.

When migrating to CD, a primary motivation is to reduce the time and effort required to deploy code. While reduced time and effort are significant advantages to CD, they aren’t the only ones! CD also improves the repeatability and auditability of your deployment process. Here is why you should care about these qualities.

**Repeatable**
 Automating the steps to deploy code means scripting each step so it can be executed by a computer instead of a human. This greatly improves the repeatability of the deployment process, as computers excel at performing repetitive tasks.

A repeatable process is inherently less risky, which can encourage organizations to release more often and with smaller changesets. This can lead to second-order benefits of targeting a release to fix specific issues, such as performance. A release can contain only performance changes, which can make it possible to measure if those changes improved, degraded, or had no impact on performance.

**Auditable**
 Automating deployments greatly improves transparency, which naturally improves auditability. The scripts used to execute steps and values supplied to them can be stored in version control, allowing for easy review. Automated deployments can also generate reports that can also help with auditing. The improved auditability of the deployment process is what moves CD from a niche concept for start-ups and non-mission-critical applications to essential in even the most tightly regulated and controlled industries.

When I first heard about CD, I found the deployments on demand concept intoxicating. After reading *Continuous Delivery* by Jez Humble and David Farley (Addison-Wesley), I learned that the reduced time and effort are in many ways secondary to the repeatability and auditability that CD offers. If your organization has been struggling to deliver code to production, I hope this can help build your case to management for why you should switch to CD.

---

### 中文

---

**手工制作**因其所需的时间和精力，以及小小的不完美所带来的个性和独特性而被重视。虽然这些特点在食品、家具或艺术中可能被看作是值得珍惜的，但在交付代码时，这些特点则是阻碍组织成功的严重障碍。

**人类并不适合进行重复性工作**。无论一个人有多注重细节，在执行一系列复杂步骤来部署应用时，总会发生错误。可能会跳过某个步骤、在错误的环境中运行，或者以其他方式执行不正确，从而导致部署失败。

当部署失败发生时，通常需要花费大量时间调查问题的根源。这个调查过程往往会受到阻碍，因为手动流程通常缺乏中央控制点，且过程不透明。一旦确定了根本原因，典型的解决办法是增加更多的控制层，以防止问题再次发生，但这通常只会使部署过程变得更加复杂和痛苦！

**组织在交付代码方面遇到困难并不是什么新闻**，为了应对这个问题，许多组织开始迁移到 **持续交付（CD）**。CD 是一种自动化交付代码到生产环境的过程。从开发人员提交更改开始，到更改被部署到生产环境，每一个可以自动化的步骤都应该自动化——测试、变更控制、部署过程等。

当迁移到 CD 时，减少部署代码所需的时间和精力是主要动机之一。虽然减少时间和精力是 CD 的重要优势，但它们并不是唯一的优势！CD 还改善了部署过程的**可重复性**和**可审计性**。以下是为什么你应该关注这些特点的原因。

**可重复性**

自动化部署代码的步骤意味着将每个步骤脚本化，让计算机而非人工执行。这大大提高了部署过程的**可重复性**，因为计算机擅长执行重复性任务。

一个**可重复的过程**本质上风险较小，这可以鼓励组织更频繁且小范围地发布版本。这可能带来一些二次效益，比如针对特定问题（如性能）发布版本。一个版本只包含性能方面的更改，这使得我们能够衡量这些更改是改善了性能、降低了性能，还是对性能没有影响。

**可审计性**

自动化部署大大提高了透明度，从而自然改善了**可审计性**。用于执行步骤和提供给它们的值的脚本可以存储在版本控制中，便于审查。自动化部署还可以生成报告，这也有助于审计。部署过程的可审计性改善了，使得 CD 从初创公司和非关键业务应用的利基概念，发展为在最严格监管和控制的行业中也变得至关重要。

**总结**

当我第一次听说 CD 时，**按需部署**的概念让我深感吸引。阅读了 Jez Humble 和 David Farley 合著的《持续交付》（Addison-Wesley）后，我了解到，减少时间和精力在许多方面是次要的，**可重复性**和**可审计性**才是 CD 所提供的核心优势。如果你的组织一直在努力将代码交付到生产环境，希望这些信息能帮助你向管理层说明为什么应该切换到 CD。

---

## 35. In the Language Wars, Java Holds Its Own  

### English

---

We all pick our favorites and downplay other options (colors, cars, sports teams, and so on). Programming language choice is not exempt. Whether it’s the one we are most comfortable with or the one that got us a job, we cling to that choice.

Today, we will focus on Java. There are perfectly valid complaints and praises for this language. These are my experiences, and others may see things differently.

**My History with Java**

First, let’s see the lens through which I view this language. My introduction to programming applications was in college using—wait for it—Java. Prior to that, I had a couple of intro classes using HTML, Alice, and Visual Basic. None of those was designed to dive into complex code structures. So, Java was my first exposure to programming for enterprise environments and critical processes. I’ve since had experience with many other languages, but I still go back to Java.

**Java’s Design and Background**

Java was created in 1995 with a C-like syntax and following the WORA principle (write once, run anywhere). Its goal was to simplify complex programming required in C-family languages and achieve platform independence via the JVM.

I think knowing a language’s history helps put positives and negatives into context, as understanding the background shows what the creators sacrificed to reach other goals.

**Java’s Downsides**

Most complaints are that deployables are larger and the syntax is verbose. While valid, I think the previous paragraph on Java’s history explains why these exist.

First, Java deployables are larger overall. As we saw in Java’s history, it was created to “write once, run anywhere” so the same application could run on any JVM. This means all dependencies have to be included for deployment, whether rolled into a single JAR or across various components (WAR file + app server + JRE + dependencies). This affects the size of the deployment.

Second, Java is verbose. Again, I attribute this to its design. It was created when C and similar languages ruled the space, which required developers to specify low-level details. Java’s goal was to be more user-friendly by abstracting some of those details.

**Why I Like Java**

Java tells me what I am building and how. With other languages, I may be able to write something in fewer lines, but I’m less sure what it’s doing under the hood, which I don’t like as much.

It’s a widely applicable skill. Dealing with Java in various capacities has given me knowledge in both the business and the technical market. Java is not the only language with this benefit, but it seems the most enduring one with this property.

Java allows me to play with technology in all stacks and areas. It seems to bridge all those. I like to dabble and explore, and Java has enabled that.

**What Does It Mean for Developers?**

The market is diverse, with many options fitting business needs. One size does not (and should not) fit all, so each developer needs to decide the best language for the job. Even if you don’t favor Java as a primary language, I still think it’s a valuable skill to have.

---

### 中文

---

**我们都会有自己的偏好，并会轻视其他选择**（比如颜色、汽车、运动队等等）。编程语言的选择也不例外。无论是我们最为熟悉的，还是那种帮我们找到工作的语言，我们总会紧抓不放。

今天，我们将专注于 **Java**。这门语言有其完全合理的批评和赞扬。这些是我个人的经历，其他人可能会有不同的看法。

### **我与Java的历史**

首先，让我们来看一下我如何看待这门语言。我的编程应用介绍是在大学时进行的——使用的就是 **Java**。在那之前，我曾学习过HTML、Alice和Visual Basic的入门课程，但这些课程并没有深入探讨复杂的代码结构。因此，Java是我第一次接触到用于企业环境和关键流程的编程语言。从那时起，我虽然也接触过许多其他编程语言，但我依然回归Java。

### **Java的设计与背景**

Java于1995年创建，采用类似C语言的语法，并遵循“**WORA**”（一次编写，到处运行）原则。它的目标是简化C系列语言中复杂的编程工作，并通过JVM实现平台独立性。

我认为了解一门语言的历史有助于我们将其优点和缺点放入上下文中，理解背景有助于我们明白创作者在实现其他目标时所做的取舍。

### **Java的缺点**

大多数人对Java的抱怨集中在部署包更大和语法冗长上。虽然这些抱怨是有道理的，但我认为前面提到的Java历史解释了为什么会有这些问题。

- **部署包更大**：正如我们在Java的历史中看到的，Java被设计为“写一次，到处运行”，以便同一个应用可以在任何JVM上运行。这意味着所有依赖项都必须包含在部署包中，无论是打包成单个JAR文件，还是跨多个组件（如WAR文件 + 应用服务器 + JRE + 依赖项）。这直接影响了部署包的大小。
- **语法冗长**：这同样可以归因于Java的设计。Java诞生于C语言等类似语言占主导地位的时代，这些语言要求开发者指定许多低级细节。而Java的目标是通过抽象化一些细节，使其更加用户友好。

### **我喜欢Java的原因**

- **Java告诉我在构建什么以及如何构建**。使用其他语言，我可能能够用更少的代码实现某些功能，但我不太确定其底层是如何运作的，而这一点我并不喜欢。
- **它是一项广泛适用的技能**。在不同领域接触Java使我在业务和技术市场中积累了经验。Java不是唯一具有这种优势的语言，但它似乎是最持久的。
- **Java让我能涉足所有技术栈和领域**。它似乎能够跨越这些领域。我喜欢尝试和探索，Java让我有机会做到这一点。

### **这对开发者意味着什么？**

市场多种多样，有很多选项可以满足不同的业务需求。并非所有情况都能适用同一种语言，因此每个开发者需要决定适合该工作的最佳语言。即使你不偏爱Java作为主力语言，我仍然认为它是一项值得掌握的技能。

---

## 36. Inline Thinking  

### English

---

Computers changed. They changed in many ways, but for the purpose of this text, they changed in one significant way: the relative cost of reading from RAM became extremely high.

This was something that happened gradually, until RAM accesses could completely dominate the performance metrics of an application. The CPU was constantly waiting for memory accesses to finish. And as the cost of going to RAM, relative to registers, grew and grew, chip manufacturers introduced more and more levels of cache and made them bigger and bigger.

And caches are great! If what you need is in them…

Caches are complex, but as a rule they will predict that a subsequent memory access will be close to, or preferably adjacent to, a recent, previous access. This is done by fetching a bit more than needed from memory and storing this excess in the cache, often called prefetching. If a later access can get its value from the cache instead of RAM, it is referred to as a “cache-friendly” access.

Imagine that you need to iterate through a big array of relatively small objects, maybe a bunch of triangles. In Java today, you don’t really have an array of triangles; you have an array of pointers to triangle objects because regular objects in Java are “reference types,” meaning you access them through Java pointers/references. So even though the array is probably a contiguous section of memory, the triangle objects themselves can be anywhere on the Java heap. Looping through this array will be “cache-unfriendly” since we will be jumping around in memory from triangle object to triangle object, and the cache prefetching will probably not help us much.

Imagine instead that the array contained the actual triangle objects, not pointers to them. Now they are close in memory, and looping over them is much more “cache-friendly.” The next triangle might be waiting for us right there in the cache. Object types that can be stored directly into an array like that are called “value types” or “inline types.” Java already has several inline types, for example int and char, and will soon have user-defined ones, probably called “inline classes.” These will be similar to regular classes but simpler.

Another way to be cache-friendly is to store objects in your stack frame or directly in registers. A difference between inline types and reference types is that you don’t have to allocate inline types on the heap. This is useful for objects that live only for the scope of this method call. Since the relevant parts of the stack are probably in the cache, access to objects on the stack will tend to be cache-friendly. As a bonus, objects that are not allocated on the Java heap do not need to be garbage collected.

These cache-friendly behaviors are already present in Java when using so-called “primitive types,” like ints and chars. Primitive types are inline types and come with all of their advantages. So even though inline types may seem foreign in the beginning, you have worked with them before; you just might not have thought of them as objects. So, when “inline classes” seem confusing, you could try thinking, “What would an int do?”

---

### 中文

---

计算机发生了变化。它们以多种方式发生了变化，但就本文而言，变化有一个显著的方面：从RAM读取数据的相对成本变得极高。

这种变化是逐渐发生的，直到RAM访问完全主导了应用程序的性能指标。CPU不断等待内存访问完成。随着访问RAM的成本相对于寄存器的成本不断增加，芯片制造商引入了越来越多级别的缓存，并且缓存也越来越大。

缓存很棒！如果你需要的数据就在缓存中……

缓存很复杂，但一般来说，它们会预测随后一次内存访问将接近或者最好是紧邻最近的一次访问。通过从内存中取出比实际需要多一点的数据并将其存储到缓存中，这种方式通常被称为预取。如果之后的访问可以从缓存中获取数据而不是从RAM中读取，这被称为“缓存友好”的访问。

想象一下，你需要遍历一个包含大量小对象的数组，可能是一堆三角形。在今天的Java中，你实际上没有一个三角形数组，而是一个指向三角形对象的指针数组，因为Java中的常规对象是“引用类型”，意味着你通过Java指针/引用访问它们。因此，即使数组可能是内存中的一个连续区域，三角形对象本身可以位于Java堆的任何位置。遍历这个数组会是“缓存不友好”的，因为我们会在内存中跳来跳去，从一个三角形对象跳到另一个三角形对象，而缓存的预取可能帮不上什么忙。

相反，假设数组包含的是实际的三角形对象，而不是指向它们的指针。现在它们在内存中很接近，遍历它们会更“缓存友好”。下一个三角形可能就在缓存中等待我们。可以直接存储到数组中的对象类型被称为“值类型”或“内联类型”。Java已经有几种内联类型，例如int和char，并且很快会有用户自定义的内联类型，可能称为“内联类”。这些将类似于常规类，但会更简单。

另一种缓存友好的方式是将对象存储在你的栈帧中或直接存储在寄存器中。内联类型和引用类型的一个区别是，你不需要在堆上为内联类型分配空间。这对于只在方法调用范围内存在的对象很有用。由于相关的栈部分可能已经在缓存中，访问栈上的对象往往会更“缓存友好”。额外的好处是，不在Java堆上分配的对象不需要进行垃圾回收。

这些缓存友好的行为在Java中已经存在，特别是在使用所谓的“基本类型”时，如int和char。基本类型是内联类型，并具备所有优势。因此，尽管内联类型在一开始可能显得陌生，但你之前已经使用过它们；只不过你可能没有把它们看作是对象。所以，当“内联类”让你感到困惑时，你可以尝试想一下：“一个int会怎么做？”

---

## 37. Interop with Kotlin  

### English

---

In recent years, Kotlin has been a hot-button topic in the JVM community; the usage of the language is constantly increasing, from mobile to backend projects. One of Kotlin’s advantages is its great degree of interoperability with Java right off the bat.

Calling into any Java code from Kotlin just works. Kotlin understands Java perfectly well, but there’s one minor annoyance that may present itself if you’re not following Java best practices to the letter: the lack of non-nullable types in Java. If you don’t apply nullability annotations in Java, Kotlin assumes all those types have unknown nullability—they’re so-called platform types. If you’re certain they will never be null, you can coerce them into a non-null type with the `!!` operator or by casting them to a non-null type. In either case, you’ll get a crash if the value is null at runtime. The best way to handle this scenario is to add nullability annotations such as `@Nullable` and `@NotNull` to your Java APIs. There are a variety of supported annotations: JetBrains, Android, JSR-305, FindBugs, and more. This way, Kotlin will know the type nullability, and when coding in Java, you’ll receive additional IDE insights and warnings about potential nulls. Win-win!

When invoking Kotlin code from Java, you should find that while the majority of the code will work just fine, you may see quirks with some advanced Kotlin language features that don’t have a direct equivalent in Java. The Kotlin compiler has to adopt some creative solutions to implement them in bytecode. These are hidden when in Kotlin, but Java isn’t aware of these mechanisms and lays them bare, resulting in a usable but suboptimal API.

Top-level declarations are an example. Since the JVM bytecode doesn’t support methods and fields outside of classes, the Kotlin compiler puts them in a synthetic class with the same name as the file they’re in. For example, all top-level symbols in a `FluxCapacitor.kt` file will appear as static members of the `FluxCapacitorKt` class, from Java. You can change the synthetic class name to something nicer by annotating the Kotlin file with:

`@file:JvmName("FluxCapacitorFuncs")`.

You may expect members defined in a (companion) object to be static in bytecode, but that’s not the case. Kotlin under the hood moves them into a field named `INSTANCE`, or a synthetic Companion inner class. If you need to access them as static members, just annotate them with `@JvmStatic`. You can also make (companion) object properties appear as fields in Java by annotating them as `@JvmField`.

Lastly, Kotlin offers optional parameters with default values. It’s a very convenient feature, but unfortunately, Java doesn’t support it. In Java, you need to provide values for all the parameters, including the ones that are supposed to be optional. To avoid this, you can use the `@JvmOverloads` annotation, which tells the compiler to generate telescopic overloads for all optional parameters. Ordering of the parameters is important as you don’t get all possible permutations in the overloads, but rather one extra overload for each optional parameter, in the order in which they appear in Kotlin.

To summarize, Kotlin and Java are almost entirely interoperable out of the box: that’s one of Kotlin’s advantages over other JVM languages. In some scenarios, though, a minute of work on your APIs will make its usage much more pleasant from the other language. There’s really no reason not to go the extra mile, given how big of an impact you can make with such little effort!

---

### 中文

---

近年来，Kotlin在JVM社区中成为了一个热门话题，语言的使用不断增加，从移动端到后端项目都有应用。Kotlin的一个优点是，它与Java的高度互操作性。

从Kotlin调用Java代码非常顺畅，Kotlin对Java的理解非常好，但有一个小问题，如果你没有严格遵循Java的最佳实践，它可能会表现出来：Java中没有不可为空的类型。如果你没有在Java中应用空值注解，Kotlin会假定这些类型具有未知的空值性——它们被称为平台类型。如果你确定它们永远不会为空，可以使用`!!`操作符或将它们强制转换为非空类型。无论哪种方式，如果值在运行时为null，都会导致崩溃。处理这种情况的最佳方式是，在你的Java API中添加如`@Nullable`和`@NotNull`这样的空值注解。支持的注解有很多：JetBrains、Android、JSR-305、FindBugs等。这样，Kotlin就能知道类型的空值性，而在Java编码时，你也能收到更多IDE的提示和关于潜在空值的警告，双赢！

当从Java调用Kotlin代码时，你会发现大多数代码运行得很顺利，但可能会遇到一些Kotlin高级语言特性，它们在Java中没有直接的对应项。Kotlin编译器需要采用一些创造性的解决方案，将它们实现为字节码。在Kotlin中，这些特性是隐藏的，但Java并不知晓这些机制，因此它们暴露出来，导致生成一个可用但并非最优的API。

顶层声明就是一个例子。由于JVM字节码不支持类外的方法和字段，Kotlin编译器将它们放入一个与文件同名的合成类中。例如，FluxCapacitor.kt文件中的所有顶层符号将作为FluxCapacitorKt类的静态成员出现在Java中。你可以通过在Kotlin文件中添加以下注解来更改合成类的名称：

```
@file:JvmName("FluxCapacitorFuncs")
```

你可能会期望在（伴生）对象中定义的成员在字节码中是静态的，但事实并非如此。Kotlin在后台将它们移到名为INSTANCE的字段中，或者放入一个合成的Companion内部类中。如果你需要将它们作为静态成员访问，只需使用`@JvmStatic`注解。你还可以通过将伴生对象属性注解为`@JvmField`，使它们在Java中以字段的形式出现。

最后，Kotlin提供了带默认值的可选参数。这是一个非常方便的特性，但不幸的是，Java不支持它。在Java中，你需要为所有参数提供值，包括那些本应是可选的参数。为了避免这个问题，你可以使用`@JvmOverloads`注解，这会告诉编译器为所有可选参数生成不同的重载方法。参数的顺序非常重要，因为你不会获得所有可能的重载排列，而是根据Kotlin中可选参数出现的顺序，生成每个可选参数的一个额外重载。

总的来说，Kotlin和Java几乎是开箱即用的完全互操作的，这是Kotlin相较于其他JVM语言的一个优点。然而，在某些场景下，只需要对API进行一些小的修改，就能让在另一种语言中的使用体验更加愉快。鉴于这些小努力就能带来很大的影响，完全没有理由不去付出这些额外的努力！

---

## 38. It’s Done, But…  

### English

---

How many times have you been to a stand-up, daily Scrum or status meeting and heard the phrase “It’s done, but...”? When I hear that, my first thought is “So, it’s not done.” There are three issues with using the word done when it isn’t done.

1. Communication and Clarity
    Ideally your team has a definition of done. But even if they don’t, there is probably some expectation of what done means. And, even better, the person reporting on status knows that. Otherwise, we wouldn’t have a disclaimer on the task’s done-ness.
    Common things that aren’t done include writing tests, documentation, and edge cases. Take a moment and see if you can think of any more.
    Similarly, I don’t like the term done done. It implicitly blesses the idea that done doesn’t actually mean done. Be a clear communicator. If something isn’t done, don’t say it’s done.
    This is an opportunity for you to convey more information. For example, “I coded the happy path and next I will add validation” or “I finished all the code—the only thing remaining is for me to update the user manual” or even “I thought I was done and then discovered the widget doesn’t work on Tuesdays.” All of these give information to your team.

2. Perception
    Managers like hearing the word done. It means you are free to take on more work. Or help a teammate. Or pretty much anything that does not include spending more time on the task. As soon as they hear done, that becomes the perception. The but either gets forgotten or becomes a small thing.
    Now you are moving on to the next thing when you didn’t finish the first thing. That’s where technical debt comes from! Sometimes technical debt is a choice. However, making that choice by discussing it is far better than having it made for you because you claimed to be done.
    OK. I’m done with this article, but I still have to write the last part. See how that worked? I’m not actually done at all.

3. There’s No Partial Credit for Done
    Done is a binary state. It’s either done or it isn’t. There’s no such thing as half done. Suppose you are building a pair of stilts and say you are 50% done. Think about what that means. It could mean you have one stilt. Not particularly useful. More likely it means that you think you have one stilt but still have to build the other one and then test. Testing is likely to reveal that you have to go back and change something. This rework means you weren’t even 50% done. You were optimistic.


Remember: don’t say you are done until you are done!

---

### 中文

---

你参加过多少次站会（stand-up）、每日 Scrum 或状态会议，听到有人说：“**完成了，但是……**”？
 每次我听到这句话，我的第一反应就是：“**那就是还没完成**。”

这种错误使用“完成”会带来三个主要问题：

------



理想情况下，**团队应有统一的“完成定义”（Definition of Done）**。即使没有明确文档，大家心里通常也有个标准。说“完成了但……”其实就说明你自己也知道这项任务还没真正完成。

常见的“其实还没完成”的情况包括：

- **缺少测试**
- **缺少文档**
- **边界情况没处理**

还有人会说“done done”（真正完成），**这更是默认“done”可以不是真的“done”**，模糊了沟通。

**正确的做法是明确说出当前状态**，例如：

- “我实现了主流程，接下来加输入验证。”
- “代码都写好了，只剩下更新用户文档。”
- “我以为完成了，但发现这个功能在周二会出问题。”

这些说法都比“完成了但是……”**传达更多有用信息**。

------

**2. 认知与期望的问题**

**管理者听到“完成”两个字，会默认你可以开始下一个任务。**
 或者去帮别人，或者干别的，反正**不会期望你再继续这个任务**。

于是就变成：**你在任务还没真正完成时就被拉去做别的事了**。

这就是**技术债（Technical Debt）**的来源之一。技术债有时是战略性的选择，但前提是大家明确知道哪些东西被“欠下了”。
 **而不是你口头上说“完成”了，别人就当真了。**

就像我说：“我写完这篇文章了，但还差最后一段。”你看，其实还**没写完**。

------

**3. “完成”不能打折，没有“完成一半”**

**完成是一个“非黑即白”的状态：要么完成，要么没完成。**
 比如你说：“这对高跷我已经做了50%。”什么意思？只做了一只？有啥用？

更可能是：你以为你做了一只，但你还没测试；测试后又发现得返工。
 **这说明你连50%都没做完，只是太乐观了。**

------

✅ **结语：**

> **完成了，才是真的完成了。别提前说出口。**

这么做能避免误解、减少技术债、提升沟通效率和团队信任感。

---

## 39. Java Certifications: Touchstone in Technology  

### English

---

Imagine you need to undergo a robotic surgery. The surgeon is experienced and qualified but has no credentials with robotic equipment for surgery. Would you still move forward with the robotic surgery with that surgeon? Unless I was convinced of the surgeon’s skills on robotic equipment, I wouldn’t.

Taking the analogy further, how would you ascertain a candidate’s skills before adding them to your critical projects? A university degree in computer science is not enough. The gap in skills gained through a university curriculum and a job’s requirements is wide.

Independent skill training organizations are stepping in to bridge this gap. But it is not enough. Who would measure the quality of their content and how? This is where the industry steps in.

An apt metaphor would be the touchstone—the wonderstone used in ancient times to measure the purity of gold and other precious metals that were used as currency. A metal coin was rubbed against a dark siliceous stone like jasper, and a colorful residue would be indicative of the metal’s purity.

Organizations like Oracle have defined these benchmarks in the form of professional certifications, to play the role of touchstones, measuring IT skills in a standardized manner.

People often ask whether these professional certifications are necessary for computer science graduates or postgraduates. Has the university curriculum covered the content already? Here one needs to put the short-term and long-term objectives in perspective. Graduation or postgraduation in computer science at a university can be a strategic choice to chalk out a long-term career path, whereas earning professional certifications are tactical choices to gain proven skills in technologies that need to be applied in immediate projects and achieve short-term goals.

Professional certifications in Java by the Oracle Corporation are in great demand. They are awarded when a candidate meets the defined requirements. Depending on the certification, a candidate may be required to complete a course or project, or pass an examination. The purpose is to establish that the individual is qualified to hold certain types of positions or work on certain projects. Certified skills bridge the gap between their existing skills and skills required by the industry, resulting in a higher rate of success on projects. These certifications are regularly updated.

Oracle offers multiple options in Java certifications, which define topics and a pathway to be followed by developers. Developers can choose the right certification as per their interest.

Validated skills establish the credibility of an individual’s ability in programming in a particular language or their understanding of a platform, methodology, or practice to prospective employers. They help professionals to clear the initial hurdle of résumé reviews and selections for interviews.

Java certifications help an individual advance in their career. When people are searching for jobs and organizations and teams are trying to find talent with verified skills, these certifications can be a first step.

---

### 中文

---

**想象一下你要接受一次机器人手术。\**虽然外科医生经验丰富、资质齐全，但\**他并没有使用手术机器人设备的资质**，你还会继续接受这次手术吗？**除非我确信这位医生掌握了机器人设备的操作技能，否则我不会接受手术。**

将这个比喻延伸到职场，在你将某人加入一个**关键项目**之前，你如何确定他们具备相应技能？**计算机科学的大学学位远远不够**，因为**大学课程所教授的技能与实际工作的需求之间存在巨大差距**。

如今，**第三方技能培训机构**正在努力弥合这一差距，但这还不够。**谁来评估这些培训内容的质量？如何评估？\**这正是\**业界参与的地方**。

一个恰当的比喻是**试金石**——古人用来检验金属纯度的工具。将金属在像碧玉一样的黑色硅质石上摩擦，根据留下的颜色残留来判断金属的纯度。

就像试金石一样，**Oracle 等组织通过职业认证制定了统一的技能标准**，来衡量 IT 技能的“纯度”。

很多人会问：**计算机科学专业的本科或研究生是否还有必要获得这些认证？大学课程是否已经涵盖了相关内容？\**这时我们需要区分\**短期目标和长期规划**：

- **大学学位**更像是为**长期职业发展**做准备；
- **职业认证**则是为了**短期内掌握可应用的实际技能**，迅速上手当前的项目。

以 Oracle 提供的 **Java 职业认证** 为例，**市场需求非常高**。认证的授予依据包括：

- 完成课程或项目，或
- 通过特定考试。
   其目的是**证明持证人具备在某些岗位或项目中胜任的能力**。
   通过认证，人才可以**弥合现有技能与行业要求之间的差距**，从而在项目中获得更高的成功率。认证内容也会**定期更新**。

Oracle 提供多种 Java 认证路径，**开发人员可根据兴趣选择合适的方向**。

**认证后的技能能显著提升个人的可信度**，无论是编程语言能力，还是平台、方法论等方面的理解。这也**有助于简历筛选和面试通过率的提升**。

总之，**Java 认证是职业发展的助推器**。在求职或企业招聘时，**认证是“技能已验证”的有力凭证**，可以作为**职业发展的第一步**。

---

## 40. Java Is a ’90s Kid  

### English

---

> **There are only two kinds of languages: the ones people complain about and the ones nobody uses.**
>  —Bjarne Stroustrup

Whether Stroustrup’s insight says more about programming languages or human nature, I’m not sure. However, it does draw attention to the often-forgotten truism that the design of programming languages is a human endeavor. As such, languages always carry traces of the environment and context in which they were created.

So it shouldn’t come as a surprise that traces of the late 1990s can be seen everywhere in the design of Java, if you know where to look.

For example, the sequence of bytes to load an object reference from local variable 0 onto the temporary evaluation stack is this two-byte sequence:
 `19 00 // aload 00`

However, the JVM’s bytecode instruction set provides a variant form that is one byte shorter:
 `2A // aload_0`

One byte saved may not sound like much, but it can start to add up over an entire class file.

Now, remember, in the late ’90s, Java classes (often applets) were downloaded over dial-up modems, incredible devices that were capable of reaching blistering speeds of 14.4 kilobits per second. With that kind of bandwidth, saving bytes wherever possible was a huge motivation for Java.

You could even argue that the entire concept of primitive types is a combination of a performance hack and a sop to C++ programmers newly arrived in the Java world—products of the 1990s, when Java was created.

Even the “magic number” (the first few bytes of a file, which allow the operating system to identify the file type) for all Java class files feels dated:
 `CA FE BA BE`

“Cafe babe” is maybe not a great look for Java today. Unfortunately, it’s not something that can realistically be changed now.

It’s not only the bytecode: in the Java standard library (especially the older parts of it), APIs that replicate equivalent C APIs are everywhere. Every programmer who’s been forced to read the contents of a file by hand knows that only too well. Worse yet, the mere mention of `java.util.Date` is enough to break many Java programmers out in a rash.

Through the lens of 2020 and beyond, Java is sometimes seen as a mainstream, middle-of-the-road language. What that narrative misses is that the world of software has radically changed since Java’s debut. Big ideas such as virtual machines, dynamic self-management, JIT compilation, and garbage collection are now part of the general landscape of programming languages.

Though some may view Java as The Establishment, it’s really the mainstream that has moved to encompass the space where Java has always been. Underneath the veneer of enterprise respectability, Java is still a ’90s kid.

---

### 中文

---

> **只有两种语言：人们抱怨的语言和没人使用的语言。**
>  — Bjarne Stroustrup

斯特劳斯特鲁普（Bjarne Stroustrup）的这句话，不确定它更多地在讲编程语言还是人性。不过，它确实引起了我们对一个常被忽视的真理的关注：编程语言的设计是人类的努力成果。因此，语言总是带有它们诞生环境和背景的印记。

因此，如果你知道该看哪里，你就不会惊讶于Java的设计中处处可见1990年代末期的痕迹。

例如，从局部变量0加载对象引用到临时计算栈的字节序列是这段两字节序列：
 `19 00 // aload 00`

然而，JVM的字节码指令集提供了一种变体形式，节省了一个字节：
 `2A // aload_0`

节省一个字节看似不多，但它在整个类文件中加起来可能就不小了。

记住，在90年代末，Java类（通常是小应用程序）是通过拨号调制解调器下载的，这些设备的速度高达14.4千比特每秒。在这样的带宽条件下，尽可能节省字节是Java的一大动力。

你甚至可以认为，原始数据类型的概念是性能优化和对新进入Java世界的C++程序员的一种妥协——这些程序员正是90年代的产物，Java也正是在那个时期诞生的。

即使是Java类文件的“魔法数字”（文件的前几个字节，用于操作系统识别文件类型）也显得过时：
 `CA FE BA BE`

“Cafe babe”可能今天不再是Java的好形象了。不幸的是，这已经不可能真正改变了。

不仅仅是字节码：在Java标准库（特别是较旧部分）中，模仿等效C API的API随处可见。每个被迫手动读取文件内容的程序员对此感同身受。更糟糕的是，光提到`java.util.Date`，就足以让很多Java程序员暴躁不安。

从2020年及以后的视角来看，Java有时被视为主流的、平庸的语言。这个观点忽略了一个事实，那就是自Java诞生以来，软件世界发生了根本性的变化。虚拟机、动态自我管理、JIT编译和垃圾回收等大理念现在已成为编程语言的普遍特征。

尽管有些人将Java视为“传统”，但事实上，主流已经扩展到了Java所一直处于的领域。尽管Java表面上看似企业级的稳重，它仍然是90年代的产物。

---

## 41. Java Programming from a JVM Performance Perspective  

### English

---

**Tip #1: Don’t Obsess Over Garbage**
 I find that sometimes Java developers obsess over the amount of garbage their applications produce. Very few cases warrant this sort of obsession. A garbage collector (GC) helps the Java Virtual Machine (JVM) in memory management. For OpenJDK HotSpot VM, the GC along with the dynamic just-in-time (JIT) tiered compiler (client (C1) + server class (C2)) and the interpreter make up its execution engine. There are a slew of optimizations that a dynamic compiler can perform on your behalf. For example, C2 can utilize dynamic branch prediction and have a probability (“always” or “never”) for code branches taken (or not). Similarly, C2 excels in optimizations related to constants, loops, copies, deoptimizations, and so on.
 Trust the adaptive compiler, but when in doubt verify using “serviceability,” “observability,” logging, and all the other such tools that we have thanks to our rich ecosystem. What matters to a GC is an object’s liveness/age, its “popularity,” the “live set size” for your application, the long-lived transients, allocation rate, marking overhead, your promotion rate (for the generational collector), and so forth.

------

**Tip #2: Characterize and Validate Your Benchmarks**
 A peer of mine once brought in some observations of a benchmarking suite with various sub-benchmarks. One of these was characterized as a “start-up and related” benchmark. After taking a look at the performance numbers and the premise that was the comparison between OpenJDK 8u and OpenJDK 11u LTS releases, I realized that the difference in numbers could have been due to the default GC changing from Parallel GC to G1 GC. So, it seems that the (sub-)benchmark either was not properly characterized or wasn’t validated. Both are important benchmarking exercises and help identify and isolate the “unit of test” (UoT) from other components of the test system that could act as detractors.

------

**Tip #3: Allocation Size and Rate Still Matter**
 In order to be able to get to the bottom of the issue discussed above, I asked to see the GC logs. Within minutes, it was clear that the (fixed) region size, which is based on the heap size of the application, was categorizing the “regular” objects as “humongous.” For the G1 GC, humongous objects are objects that span 50% or more of a G1 region. Such objects don’t follow the fast path for allocations and are allocated out of the old generation. Hence, allocation size matters for regionalized GCs.
 A GC keeps up with the live object graph mutation and moves objects from the “From” space into the “To” space. If your application is allocating at a rate faster than your GC’s (concurrent) marking algorithm can keep up with, then that can become a problem. Also, a generational GC may prematurely promote short-lived objects or not age transients properly due to the influx of allocations. OpenJDK’s G1 GC is still working on not being dependent on its fallback, fail-safe, nonincremental, full heap traversing, (parallel) stop-the-world collector.

------

**Tip #4: An Adaptive JVM Is Your Right and You Should Demand It**
 It’s great to see an adaptive JIT and all the advancements geared toward start-up, ramp-up, JIT availability, and footprint optimizations. Similarly, various GC-level algorithmic smartness is available. Those GCs that aren’t there yet should get there soon, but that won’t happen without our help. As Java developers, please provide feedback on your use case to the community and help drive innovation in this area. Also, do test out the features that are continually getting added to the JIT.

---

### 中文

---

### **建议一：不要过度关注垃圾（Garbage）**

有些 Java 开发者**过度关注应用程序产生的垃圾量**，其实**绝大多数情况下都不需要如此执着**。垃圾收集器（GC）是帮助 JVM 管理内存的工具。在 OpenJDK 的 HotSpot 虚拟机中，**垃圾收集器、JIT 分层编译器（C1 + C2）和解释器共同组成执行引擎**。

JIT 编译器（尤其是 C2 编译器）可以自动进行大量优化，比如**动态分支预测**、常量折叠、循环优化、内存拷贝优化、反优化（deoptimization）等。你可以**信任自适应编译器**，但若有疑虑，可以通过**可观测性工具（如日志、监控工具等）进行验证**。

对于 GC 来说，真正重要的是：

- 对象的**生命周期和年龄**；
- 对象的**“受欢迎程度”**（即引用频率）；
- 应用的**存活集大小**（Live Set Size）；
- **短暂但生命周期较长的对象**；
- **分配速率**；
- **标记开销**；
- **对象晋升率**（对分代 GC 而言）。

------

### **建议二：要对基准测试进行清晰定义与验证**

我的一位同事做了一组基准测试，其中一项被归为“启动及相关”测试。在比较 OpenJDK 8u 和 OpenJDK 11u 的表现时，发现结果差异很大。深入分析后发现，**差异可能是由于默认垃圾收集器从 Parallel GC 更换为 G1 GC 所导致的**。

这说明该子基准测试**要么定义不清，要么未经过充分验证**。**清晰的基准测试定义与验证非常重要**，它可以帮助你**识别测试的“基本单元”（Unit of Test）并与测试系统中的其他因素隔离**。

------

### **建议三：分配大小与速率仍然重要**

为调查上述问题，我查看了 GC 日志。很快发现，由于应用的堆大小，G1 GC 的固定区域（region）大小使得一些普通对象被**错误地分类为“巨大对象”（humongous）**。

在 G1 GC 中，**占据 50% 或以上单个区域的对象被认为是巨大对象**，这些对象无法走快速分配路径，只能分配在老年代，因此会影响性能。**这说明对象分配的大小对区域化 GC 非常关键**。

此外，如果你的应用**分配速率超过了 GC 的并发标记能力**，也会引起问题。而且，**分代 GC 可能会将短生命周期对象过早晋升，或者对瞬时对象分类不当**。

目前，OpenJDK 的 G1 GC 仍在改进中，目标是**减少对非增量、全堆遍历、Stop-the-World 回退策略的依赖**。

------

### **建议四：你应当拥有一个“自适应的 JVM”，并勇于要求它**

现在 JIT 编译器在启动时间、加速运行、可用性和内存占用方面的优化已经取得巨大进展。同时，GC 层也有越来越多的**算法智能化特性**。

但那些还没做到这些的 GC 实现，需要大家的推动才能改进。因此，作为 Java 开发者，**请主动向社区反馈你的使用案例，推动创新**。

此外，**也要积极测试 JIT 持续新增的功能**，只有社区参与，技术才会进步。

---

## 42. Java Should Feel Fun  

### English

---

I started my Java career using J2EE 1.2. I had questions. Why were there four classes and hundreds of lines of generated code for each bean? Why did compiling tiny projects take half an hour? It wasn’t productive, and it wasn’t fun. Those two often go together: things feel un-fun because we know they’re waste. Think about meetings where nothing is decided, status reports no one reads…

If un-fun is bad, what is fun? Is it good? And how do we get it? Fun can have different faces:

- Exploration (focused investigation)
- Play (for its own sake, no goal)
- Puzzles (rules and a goal)
- Games (rules and a winner)
- Work (a satisfying goal)

Java allows all of these—the work part is obvious, and anyone who’s debugged a Java program knows about the puzzle part. (Debugging isn’t necessarily fun, but finding the solution is great.) We learn through exploration (when we’re new to something) and play (when we know enough to do stuff).

Leaving aside the fun we can have with it, is Java inherently fun? Java is verbose compared to younger languages. Boilerplate isn’t fun, but some of it is fixable. For example, Lombok neatly generates getters and setters, as well as hashCode and equals methods (tedious and error-prone otherwise). Manually writing entry and exit trace is un-fun, but aspects or tracing libraries can instrument dynamically (and massively improve code readability).

What makes something fun to use? In part it’s about being expressive and understandable, but there’s more to it than that. I’m not convinced lambdas are generally shorter or clearer than class-based alternatives. But they’re fun! When Java 8 came out, developers dove into lambdas like kids in a ball pit. We wanted to learn how it worked (exploration) and the challenge of expressing algorithms in a functional style (puzzles).

With Java, the fun thing to do is often also the best thing (win). Autoinstrumenting trace bypasses un-fun, eliminating method-name copy-and-paste errors and improving clarity. Or consider performance. For niche scenarios, weird, complicated code is needed to scrape every inch of speed. In most cases, however, the simplest code is also the fastest. (Which is not necessarily true for languages like C.) The Java JIT optimizes code as it runs; it’s smartest for clean, idiomatic code. Straightforward code is nicely readable, so errors will be more obvious.

Misery-making code has a knock-on effect. Psychological research shows happiness and workplace success go together. One study showed that people with a positive mindset were 31% more productive than those with neutral or negative mindsets. You’ll achieve less using poorly designed libraries, and then you’ll continue to achieve less afterward because the bad code made you miserable.

Is “fun is good” an excuse to be irresponsible? Not at all! Consider whether everyone is having fun: everyone includes customers, colleagues, and future maintainers of your code. Compared to dynamically typed scripting languages, which can be fast and loose, Java already ticks the safe and responsible box. But the programs we write also need to be responsibly coded.

The good news is that for almost all boring tasks, computers can do the job faster and more correctly than people. Computers don’t expect to have fun (yet), so take advantage of them! Don’t accept tedium. If something seems un-fun, look for a better way. If there isn’t one, invent one. We’re programmers: we can fix boring.

---

### 中文

------

### **我从 J2EE 1.2 开始学 Java，那时并不“有趣”**

我刚开始学 Java 的时候是用 J2EE 1.2。那时我有很多疑问：
 **为什么每个 Bean 要生成四个类和几百行代码？**
 **为什么一个小项目的编译要半个小时？**

这既**没效率**，也**不有趣**。通常，事情让人觉得“无趣”，就是因为它们让我们感觉在浪费时间。就像那些**毫无结论的会议**，或者**没人会看的状态报告**一样。

------

### **什么是“有趣”？它重要吗？**

如果“无趣”是负面的，那“有趣”是不是就是正面的？我们该怎么获得它？

“有趣”有很多形式：

- **探索**（有目标的研究）
- **玩耍**（纯粹为了好玩，无特定目标）
- **解谜**（有规则和目标）
- **游戏**（有规则和胜利者）
- **工作**（有满足感的目标）

Java 可以满足所有这些形式。比如：

- 写代码是**工作**；
- 调试是**解谜**；
- 学新技术是**探索**；
- 有时我们也会“玩”代码。

------

### **Java 天生“有趣”吗？**

坦率说，**Java 语法啰嗦**，比一些新语言缺少趣味。**样板代码（boilerplate）并不有趣**，但可以通过工具改进，比如：

- **Lombok 自动生成 getter、setter、equals 和 hashCode 方法**，避免手写重复代码；
- **日志或监控代码的埋点可以用 AOP 或 tracing 库自动完成**，不必手写进出方法的 trace。

这些技术不仅提升了**代码可读性**，也让开发过程更“有趣”。

------

### **“有趣”也可以是最优解**

为什么有些技术被广泛使用？不仅因为它们好用，还因为它们**有趣**。比如 Java 8 推出的 **Lambda 表达式**：

- 不一定比类定义短或清晰；
- 但**写起来有挑战，像玩谜题一样，让人跃跃欲试**。

很多时候，**“最有趣的方式”也是“最好的方式”**：

- 自动化追踪可以避免重复粘贴方法名等低效操作；
- 性能方面，**最简单的代码往往也是最快的**，因为 JIT 编译器对**清晰、规范的代码优化效果最好**。

------

### **糟糕代码会让你“持续不开心”**

糟糕的库、难用的 API、不合理的设计不仅会让你写得慢，还会**拖累你之后的工作效率**。研究也发现：

> **有积极情绪的人比情绪中立或消极的人工作效率高出 31%**。

所以，**写出让自己开心的代码，不只是自我愉悦，更是高效的关键**。

------

### **“有趣”≠ 不负责任**

别误会，“追求有趣”**不是放纵的借口**，而是对“写好代码”的补充。所谓“有趣的代码”，也应该让：

- **客户满意**；
- **同事易懂**；
- **未来的维护者不抓狂**。

相比一些“灵活但危险”的动态语言，Java 的静态类型本身就代表了“负责任”。**我们既要安全，也要愉快地开发**。

------

### **程序员的使命之一：消灭“无趣”**

好消息是：几乎所有“无趣”的任务，**电脑都能做得更快更准确**。电脑不需要享乐，所以我们要**让它们干重复活**。

- 如果一件事“无趣”，那就**找更好的方式做**；
- 如果没有，那就**自己发明一个方法**！

**我们是程序员，我们可以修复“无趣”**。

---

## 43. Java’s Unspeakable Types  

### English

------

**What is null?**
 New Java programmers often struggle with this idea. A simple example reveals the truth:

```java
String s = null;  
Integer i = null;  
Object o = null;
```

The symbol `null` must therefore be a value.

As every value in Java has a type, `null` must therefore have a type.
 **What is it?**
 It obviously cannot be any type that we ordinarily encounter. A variable of type `String` cannot hold a value of type `Object`—the Liskov substitution properties simply do not work that way.

Nor does Java 11 local variable type inference help:

```java
jshell> var v = null;  
| Error:  
| cannot infer type for local variable v  
| (variable initializer is 'null')  
| var v = null;  
| ^ — — — — — -^
```

The pragmatic Java programmer may simply scratch their head and decide, as many have done, that it doesn’t really matter all that much.
 Instead, they can pretend “null is merely a special literal that can be of any reference type.”

However, for those of us who find this approach unsatisfying, the true answer can be found in the Java Language Specification (JLS), in Section 4.1:

> There is also a special null type, the type of the expression null (§3.10.7, §15.8.1), which has no name.
>  Because the null type has no name, it is impossible to declare a variable of the null type or to cast to the null type.

There it is. Java allows us to write down values whose types we cannot declare as the types of variables.
 We might call these **“unspeakable types”** or, formally, **nondenotable types**.

As `null` shows, we’ve actually been using them all along.
 There are two more obvious places where this sort of types appear. The first arrived in Java 7, and the JLS has this to say about them:

> An exception parameter may denote its type as either a single class type or a union of two or more class types (called alternatives).

The true type of a `multicatch` parameter is the union of the distinct possible types being caught. In practice, only code that conforms to the API contract of the nearest common supertype of the alternatives will compile.
 The real type of the parameter is not something we can use as the type of a variable.

In the following, what is the type of `o`?

```java
jshell> var o = new Object() {
   ...> public void bar() { System.out.println("bar!"); }
   ...> }
o ==> $0@3bfdc050

jshell> o.bar();
bar!
```

It can’t be `Object`, because we can call `bar()` on it, and the `Object` type has no such method. Instead, the true type is **nondenotable**—it doesn’t have a name we can use as the type of a variable in Java code.
 At runtime, the type is just a compiler-assigned placeholder (`$0` in our example).

By using `var` as a “magic type,” the programmer can preserve type information for each distinct usage of `var`, until the end of the method.
 We cannot carry the types from method to method. To do so, we would have to declare the return type—and that’s precisely what we can’t do!

The applicability of these types is therefore restricted—Java’s type system remains very much a **nominal** system, and it seems unlikely that true **structural types** will ever appear in the language.

Finally, we should point out that many of the more advanced uses of generics (including the mysterious “capture of ?” errors) are really best understood in terms of **nondenotable types** as well—but that’s another story.

---

### 中文

------

### **什么是 `null`？**

许多 Java 初学者对 `null` 感到困惑。一个简单的例子说明问题：

```java
String s = null;  
Integer i = null;  
Object o = null;
```

可见，**`null` 是一个值。**

既然 Java 中**每个值都有类型**，那么 `null` 也应当有一个类型。
 **那么它的类型是什么？**

显然，它不可能是我们平时接触到的那些类型。
 例如：`String` 类型的变量不能存储 `Object` 类型的值——**Liskov 替换原则**并不支持这种反向赋值。

甚至在 Java 11 中尝试使用 `var` 也行不通：

```java
jshell> var v = null;  
| Error:  
| 无法推断变量 v 的类型  
```

很多务实的程序员会选择“不深究”，觉得：**“null 就是一个可以赋给任何引用类型的特殊字面量”**。
这个说法虽不准确，但够用。

------

### **真正的答案在 Java 语言规范（JLS）中**

Java 语言规范第 4.1 节指出：

> 还有一种特殊的 null 类型，是表达式 `null` 的类型，它没有名称。
> 因为 null 类型没有名字，所以不能声明为变量类型，也不能强制转换为该类型。

**这就是真相**：Java 中存在一种我们不能命名的类型。
 这种类型**不能被声明**、**不能被显示引用**。我们称之为：

- **“不可说的类型”（unspeakable type）**
- 或更正式地称为：**“不可表示类型”（nondenotable type）**

而 `null`，正是这种“隐形类型”的经典例子。我们一直在用，只是没意识到。

------

### **还有哪些地方出现了“不可表示类型”？**

1. **Java 7 的 multi-catch 异常处理**

Java 语言规范提到：

> 多重捕获中的异常参数类型，其实是多个类型的并集（union）。

尽管我们在代码中看到的，是它们的最近公共父类，但实际的类型是一个**联合类型**，这类类型无法直接在 Java 中声明变量时使用。

------

1. **匿名类对象的真实类型**

来看这个例子：

```java
var o = new Object() {
   public void bar() { 
       System.out.println("bar!"); 
   }
};

o.bar(); // 输出 bar!
```

变量 `o` 的类型看似是 `Object`，但我们可以调用 `bar()` 方法，这说明它**并不是 Object 类型**。

实际上，它的类型是 Java 编译器在后台**匿名生成的类类型**，比如 `$0`，而这**是无法在代码中直接表示的类型**。

------

### **`var` 可以延续这些“无名类型”的生命周期**

使用 `var` 可以暂时保存这种“不可表示类型”的信息，让我们在方法体内部安全使用它。
 但如果我们想把它作为方法返回值，**就必须写出类型名**——而这正是我们无法做到的！

所以这种类型的使用范围很受限制。Java 的类型系统依然是 **“名义类型系统”（nominal type system）**，这也意味着：

> Java 未来**很可能永远不会支持结构化类型（structural types）**。

------

### **泛型中的高级用法其实也涉及不可表示类型**

像是 `capture of ?` 等神秘的泛型错误，其实背后也是**不可表示类型的副作用**。
 不过，这是另一个复杂的话题。

------

### ✅ **总结：**

- `null` 是一个值，它的类型是“null 类型”，但这个类型没有名字。
- Java 中存在“不可表示类型”，我们无法直接声明、命名、或返回它们。
- 匿名类、multi-catch、泛型中的某些复杂情况都会出现这种类型。
- `var` 可以短暂保留这些类型的信息，但一旦要跨方法使用，就不行了。

---

## 44. The JVM Is a Multiparadigm Platform: Use This to Improve Your Programming  

### English

---

**Java is an imperative language:**
 Java programs tell the JVM what to do and when to do it. But computing is all about building abstractions.
 Java is touted as an object-oriented language: the abstractions of Java are objects, methods, and message passing via method call. Over the years, people have built larger and larger systems using objects, methods, updatable state, and explicit iteration, and the cracks have appeared. Many are “papered over” using high-quality testing, but still, programmers end up “hacking” to get around various problems.

With the arrival of Java 8, Java underwent an extremely revolutionary change: it introduced method references, lambda expressions, default methods on interfaces, higher-order functions, implicit iteration, and various other things. Java 8 introduced a very different way of thinking about the implementation of algorithms.

**Imperative and declarative thinking** are very different ways of expressing algorithms. During the 1980s and 1990s, these mindsets were seen as being distinct and irreconcilable: we had the object-oriented versus functional programming war. Smalltalk and C++ were the champions of object-orientation, and Haskell was the champion of functional. Later, C++ stopped being an object-oriented language and marketed itself as a multiparadigm language; Java took over as the champion of object-oriented. With Java 8, though, Java has become multiparadigm.

Back in the early 1990s, the JVM was constructed as the way of making Java portable—we can gloss over the history of the Green project and the Oak programming language. Initially, this was for making web browser plug-ins, but it rapidly moved to creating server-side systems. Java compiles to hardware-independent JVM bytecode, and an interpreter executes the bytecode. Just-in-time (JIT) compilers enable the whole interpretation model to execute much faster without changing the computational model of the JVM.

As the JVM became the hugely popular platform it is, other languages were created that made use of the bytecode as a target platform:

- Groovy, JRuby, and Clojure are dynamic languages using the JVM for execution.
- Scala, Ceylon, and Kotlin are static languages.
   Scala, in particular, showed in the late 2000s that object-orientation and functional programming can be integrated into a single, multiparadigm language. While Clojure is a functional language, Groovy and JRuby were multiparadigm from the outset. Kotlin is taking the lessons of Java, Scala, Groovy, etc. to create languages for the 2010s and 2020s on the JVM.

---

### 中文

---

### **Java 是一种命令式语言**

Java 程序告诉 JVM 做什么以及什么时候做。但是，计算的核心在于构建抽象。
 Java 被推崇为一种面向对象的语言：Java 的抽象是对象、方法以及通过方法调用进行的消息传递。
 多年来，程序员们用对象、方法、可更新状态和显式迭代构建了越来越大的系统，然而其中的裂缝也逐渐显现。
 很多问题通过高质量的测试掩盖了，但最终程序员还是不得不“hack”来绕过这些问题。

------

### **Java 8 的革命性变革**

随着 Java 8 的推出，Java 发生了一个极为革命性的变化：
 它引入了方法引用、lambda 表达式、接口的默认方法、高阶函数、隐式迭代等多种特性。
 Java 8 引入了一种非常不同的思维方式来实现算法。

**命令式编程**和**声明式编程**是两种完全不同的算法表达方式。
 在 1980 年代和 1990 年代，这两种思维方式被认为是截然不同且不可调和的：
 我们曾经历过面向对象编程与函数式编程的大战。Smalltalk 和 C++ 是面向对象编程的代表，而 Haskell 则是函数式编程的代表。
 后来，C++ 不再仅仅是面向对象的语言，而是自诩为多范式语言；Java 成为了面向对象编程的代表。而 Java 8 出现后，Java 变成了一个**多范式语言**。

------

### **JVM 的发展与多语言支持**

在 1990 年代初期，JVM 的构建旨在使 Java 跨平台运行——我们可以略过 Green 项目和 Oak 编程语言的历史。
 最初，JVM 是为了创建 Web 浏览器插件，但它迅速转向创建服务器端系统。Java 编译成硬件独立的 JVM 字节码，并由解释器执行这些字节码。
 **即时编译器（JIT）**使得这种解释执行模式可以在不改变 JVM 计算模型的情况下更快速地执行。

随着 JVM 成为广受欢迎的平台，其他语言也开始利用 JVM 字节码作为目标平台：

- **Groovy、JRuby 和 Clojure** 是使用 JVM 执行的动态语言。
- **Scala、Ceylon 和 Kotlin** 是静态语言。

其中，**Scala** 特别在 2000 年代后期证明了面向对象编程和函数式编程可以集成到一个单一的多范式语言中。
 而 **Clojure** 是一种纯函数式语言，**Groovy** 和 **JRuby** 从一开始就是多范式的。
 **Kotlin** 则在 Java、Scala、Groovy 等语言的基础上汲取经验，为 2010 年代和 2020 年代的 JVM 语言开辟了新的方向。

------

### **总结**

- Java 最初是作为一种命令式编程语言设计的，依赖对象、方法和显式迭代来构建应用。
- Java 8 引入了命令式和声明式编程的结合，使得 Java 成为多范式编程语言。
- JVM 的跨平台特性和 JIT 编译使得 Java 变得更加高效，并促使了其他语言（如 Groovy、Scala、Kotlin 等）在 JVM 上的开发。

Java 的演变和 JVM 平台的多样性让它不仅仅是一个面向对象语言，更是一个支持多种编程范式的平台。

---

## 45. Keep Your Finger on the Pulse  

### English

---

I learned Java version 1.1 at university (I wish this was because my university was using old technology instead of it being because I’m old). At that time Java was small enough, and I was naïve enough, that it was possible to believe I had learned all the Java I needed to know, and that I was set for life as a Java programmer.

During my first job, while I was still at university and had been using Java for less than a year, Java 1.2 was released. It had an entirely different user interface (UI) library, called Swing, so I spent that summer learning Swing in order to use it to provide our users with a better experience.

A couple of years later, in my first job as a graduate, I discovered that applets were out and servlets were in. I spent the next six months learning about servlets and JSPs so we could give our users an online registration form.

In my next job, I found out that apparently we didn’t use Vector anymore—we used ArrayList. This shook me to my core. How can the very fundamentals of the language, the data structures themselves, be changing underneath me? My first two discoveries involved learning additions to the language. This third one was about changes to things I thought I already knew. If I wasn’t at university anymore being taught things, how was I supposed to just know this stuff?

I was fortunate in those early jobs to have people around me who were aware of the technology changes that impacted the Java projects I worked on. That should be the role of senior team members—not simply to do what they’re told but to make suggestions on how to do it and to help the rest of the team improve too.

To survive as a Java programmer, you need to accept that Java is not a stationary language. It evolves, not only into new versions but as libraries, frameworks, and even new JVM languages. At first, this can be intimidating and overwhelming. But staying up-to-date doesn’t mean you have to learn everything that’s out there—it just means keeping your finger on the pulse, listening for common keywords, and understanding technology trends. You only need to drill down deeper when it’s relevant for your job or when it’s something that’s personally interesting to you (or ideally both).

Knowing what’s available in the current version of Java and what is planned for upcoming ones can help you implement features or functionality that will help your users do what they need to do. Which means it helps you as a developer be more productive. Java now releases a new version every six months. Keeping your finger on that pulse can actually make your life easier.

---

### 中文

---

我在大学时学习了Java 1.1版本（我希望这不是因为我的大学使用了旧技术，而是因为我年纪大了）。那时Java相对简单，而我也很天真，以为自己已经学到了所有需要知道的Java知识，认为自己可以一直做Java程序员。

在大学期间的第一份工作中，我开始使用Java不到一年时，Java 1.2发布了。它引入了一个完全不同的用户界面（UI）库——Swing。因此，我在那个夏天花时间学习Swing，以便为我们的用户提供更好的体验。

几年来，在我的第一份工作中，我发现Applet已经过时，Servlet成为主流。我花了接下来的六个月学习Servlet和JSP，以便为用户提供在线注册表单。

在我下一份工作中，我发现我们似乎不再使用Vector了，而是改用了ArrayList。这让我非常震惊。怎么可能语言的基础部分、数据结构发生了变化呢？我之前的两个发现是关于语言的新增特性，而这第三个发现则涉及我认为自己已经掌握的内容发生了变化。如果我不再在大学里被教授这些内容，我该如何知道这些变化呢？

在这些早期的工作中，我很幸运周围有一些了解技术变化的同事，帮助我跟上Java项目中的技术变动。作为资深团队成员，除了完成任务外，更应帮助团队其他成员提升，跟进技术变化，提出改进建议。

要想作为Java程序员生存下来，就必须接受Java是一门不断发展的语言。它不仅会推出新版本，还有各种库、框架，甚至新的JVM语言不断出现。刚开始，这可能让人感到害怕和不知所措。但保持更新并不意味着要学习所有的新技术，而是保持对技术脉搏的敏感，了解行业中的常见关键词和技术趋势。当这对你的工作有帮助时，或者是你个人感兴趣的内容时，再深入研究。

了解当前版本的Java以及即将发布的功能，能帮助你实现对用户有用的特性和功能。这意味着它能帮助你作为开发者提高工作效率。现在Java每六个月发布一个新版本，跟上这个节奏反而能让你的工作变得更加轻松。

---

## 46. Kinds of Comments  

### English

------

Assume you want to put some comments into your Java code. Do you use `/**`, `/*`, or `//`? And where exactly do you put them? Beyond syntax, there are established practices that attach semantics to which is used where.

#### Javadoc Comments for Contracts

Javadoc comments (the ones enclosed in `/** ... */`) are exclusively used on **classes**, **interfaces**, **fields**, and **methods** and are placed directly above them. Here's an example from `Map::size`:

```java
/**
 * Returns the number of key-value mappings in this map. If the
 * map contains more than Integer.MAX_VALUE elements, returns
 * Integer.MAX_VALUE.
 *
 * @return the number of key-value mappings in this map
 */
int size();
```

The example demonstrates both **syntax** and **semantics**: a Javadoc comment is a **contract**. It promises API users what they can expect while keeping the type’s central abstraction intact by not talking about implementation details. At the same time, it binds implementers to provide the specified behavior.

Java 8 relaxed this strictness a little while formalizing different interpretations by introducing the (nonstandardized) tags `@apiNote`, `@implSpec`, and `@implNote`. The prefixes `api` or `impl` specify whether the comment addresses users or implementers, while the suffixes `Spec` or `Note` clarify whether this is actually a specification or only for illustration. Notice how `@apiSpec` is missing? That’s because the comment’s **untagged** text is supposed to fulfill that role: specifying the API.

#### Block Comments for Context

Block comments are enclosed in `/* ... */`. There are no restrictions on where to put them, and tools usually ignore them. A common use is at the beginning of a **class** or **method** to provide insights into its implementation. These comments can include **technical details** or outline the **context** in which the code was created (i.e., the "why" behind the code).

A good example of providing implementation details can be found in `HashMap`, which starts like this:

```java
/*
 * Implementation notes.
 * This map usually acts as a binned (bucketed) hashtable,
 * but when bins get too large, they are transformed into bins
 * of TreeNodes, each structured similarly to those in
 * java.util.TreeMap.
 * [...]
 */
```

As a rule of thumb: when your first solution isn’t your last, when you make a trade-off, or when a weird requirement or a dependency’s awkward API shapes your code, consider documenting that **context**. Your colleagues and future self will thank you (silently).

#### Line Comments for Weird Things

Line comments start with `//`, and they must be repeated on every line. There are no restrictions on where to use them, but it is common to put them **above** the commented line or block (as opposed to at the end). Tools ignore them—many developers do as well. Line comments are often used to narrate what the code does, which has been identified as a bad practice in general. However, they can still be helpful in specific cases, such as when the code has to use **arcane language features** or is **easy to break** in a subtle way (e.g., concurrency issues).

#### Last Words

- Make sure to pick the right kind of comment.
- Don’t break expectations.
- **Comment your &#!\*@$ code!**

---

### 中文

---

### Java代码中的注释使用：`/**`、`/*` 还是 `//`？

在Java代码中，注释不仅仅是语法上的需求，它们的选择和使用位置通常有一定的语义要求。这里我们讨论了三种常见的注释类型：**Javadoc注释**、**块注释**和**行注释**。

#### 1. **Javadoc注释 (/** ... */) 用于合同**

Javadoc注释专门用于**类**、**接口**、**字段**和**方法**上，通常放在它们的直接上方。例如：

```java
/**
 * 返回此映射中的键值对数。如果映射包含超过 Integer.MAX_VALUE 个元素，则返回 Integer.MAX_VALUE。
 *
 * @return 映射中键值对的数量
 */
int size();
```

**语法和语义**：

- Javadoc注释不仅遵循语法规则，它还充当**合同**，明确告知API用户期望的行为。
- 它不涉及实现细节，而是专注于接口的行为规范。
- Java 8 引入了 `@apiNote`、`@implSpec` 和 `@implNote` 标签，允许文档中明确区分面向用户和实现者的说明。

#### 2. **块注释 (/\* ... \*/) 用于上下文**

块注释没有特别的限制，可以放在任何地方。它们通常用来为类或方法提供实施细节或背景信息。例如：

```java
/*
 * 实现说明：
 * 这个映射通常作为一个分桶的哈希表（binned hashtable）实现，
 * 当桶过大时，会转化为由TreeNode构成的桶，每个桶的结构类似于java.util.TreeMap。
 * [...]
 */
```

**何时使用块注释**：

- 当你需要说明代码的上下文（例如为什么做出某些决策或选择某种实现方案）时，块注释是一个好选择。
- 块注释适合解释那些复杂的技术细节、设计决策，或者是代码中不可避免的权衡。

#### 3. **行注释 (//) 用于特定情况**

行注释以 `//` 开头，通常用于简短的解释或备注。它们需要重复在每一行上，且通常位于被注释行的**上方**（而不是行尾）。例如：

```java
// 使用一个较为复杂的递归算法
// 处理大数据量时会面临栈溢出的风险
someComplexRecursiveFunction();
```

**使用行注释的场合**：

- 行注释一般不用于叙述代码逻辑（这通常被认为是坏习惯），而是用于解释一些复杂的语言特性、代码中潜在的细微问题或特殊的并发问题。

#### 总结：

- **Javadoc注释**用于API的合同，放在类、接口、字段、方法等的上方，描述行为而非实现。
- **块注释**用于提供上下文、背景或技术细节，放置在代码段的开头或必要的地方。
- **行注释**用于简单的说明或解释，但要避免过度解释代码本身的行为。

### 最后建议：

- 选择正确的注释类型，确保它们符合语义和预期。
- 避免打破注释的预期用途。
- **不要忘记注释你的代码！**

---

## 47. Know Thy flatMap  

### English

------

Job titles morph constantly. As in the medical community, where the focus may be broader or more specialized, some of us who were once just programmers are now filling other job titles. One of the newest specialized disciplines is data engineer. The data engineer shepherds in the data, building pipelines, filtering data, transforming it, and molding it into what they or others need to make real-time business decisions with stream processing.

Both the general programmer and data engineer must master the flatMap, one of the most important tools for any functional, capable language like our beloved Java, but also for big data frameworks and streaming libraries. flatMap, like its partners map and filter, is applicable for anything that is a “container of something”—for example, Stream and CompletableFuture. If you want to look beyond the standard library, there is also Observable (RXJava) and Flux (Project Reactor).

In Java, we will use Stream. The idea for map is simple—take all elements of a stream or collection and apply a function to it:

```java
Stream.of(1, 2, 3, 4).map(x -> x * 2).collect(Collectors.toList())
```

This produces:

```
[2, 4, 6, 8]
```

What happens if we do the following?

```java
Stream.of(1, 2, 3, 4)
    .map(x -> Stream.of(-x, x, x + 1))
    .collect(Collectors.toList())
```

Unfortunately, we get a List of Stream pipelines:

```
[java.util.stream.ReferencePipeline$Head@3532ec19,
 java.util.stream.ReferencePipeline$Head@68c4039c,
 java.util.stream.ReferencePipeline$Head@ae45eb6,
 java.util.stream.ReferencePipeline$Head@59f99ea]
```

But, thinking about it, of course for every element of the Stream we’re creating another Stream. And take a deeper look in the map(x -> Stream.of(...)). For every singular element, we’re creating a plural. If you perform a map with a plural, it’s time to break out the flatMap:

```java
Stream.of(1, 2, 3, 4)
    .flatMap(x -> Stream.of(-x, x, x+1))
    .collect(Collectors.toList())
```

That will produce what we were aiming for:

```
[-1, 1, 2, -2, 2, 3, -3, 3, 4, -4, 4, 5]
```

The opportunities for using flatMap are immense.

Let’s move on to something more challenging that is apt for any functional programming or data engineering task. Consider the following relationship, where getters, setters, and toString are elided:

```java
class Employee {
    private String firstName, lastName;
    private Integer yearlySalary;
    // getters, setters, toString
}

class Manager extends Employee {
    private List<Employee> employeeList;
    // getters, setters, toString
}
```

Suppose we are given only a Stream and our goal is to determine all the salaries of all employees, including Managers and their Employees. We might be tempted to jump right to the forEach and start digging through those salaries. This, unfortunately, would model our code to the structure of the data and would cause needless complexity. A better solution would be to go the opposite way and structure the data to that of our code. That is where flatMap comes in:

```java
List.of(manager1, manager2).stream()
    .flatMap(m -> 
        Stream.concat(m.getEmployeeList().stream(), Stream.of(m)))
    .distinct()
    .mapToInt(Employee::getYearlySalary)
    .sum();
```

This code takes every manager and returns a plural—the manager and their employees. We then flatMap these collections to make one Stream and perform a distinct to filter out all duplicates. Now we can treat them all as one collection. The rest is easy. First we perform a Java-specific call, mapToInt, that extracts their yearlySalary and returns an IntStream, a specialized Stream type for integers. Finally, we sum the Stream. Concise code.

Whether you use Stream or another kind of C, where C is any stream or collection, keep processing your data using map, filter, flatMap, or groupBy before reaching for the forEach or any other terminal operation like collect. If you go with the terminal operation prematurely, you’ll lose any laziness and optimization that Java Streams, streaming libraries, or big data frameworks grant you.

---

### 中文

---

职位名称不断变化。就像医学领域一样，焦点可能更广泛或更专门化，一些曾经只是程序员的人现在承担着其他职位。最新的专门化学科之一是数据工程师。数据工程师负责数据的引入，构建数据管道、过滤数据、转换数据，并将其塑造成他们或他人需要的形式，以便通过流处理进行实时业务决策。

无论是程序员还是数据工程师，都必须掌握 **flatMap**，这是任何功能强大的语言（如我们喜爱的 Java），以及大数据框架和流库中最重要的工具之一。flatMap，像它的伙伴 map 和 filter 一样，适用于任何“包含某物的容器”——例如，Stream 和 CompletableFuture。如果你想超越标准库，还有 Observable（RXJava）和 Flux（Project Reactor）。

在 Java 中，我们将使用 Stream。map 的思路很简单——对流或集合中的所有元素应用一个函数：

```java
Stream.of(1, 2, 3, 4).map(x -> x * 2).collect(Collectors.toList())
```

这将生成：

```
[2, 4, 6, 8]
```

如果我们这样做，会发生什么呢？

```java
Stream.of(1, 2, 3, 4)
    .map(x -> Stream.of(-x, x, x + 1))
    .collect(Collectors.toList())
```

不幸的是，我们得到的是一系列 Stream 管道：

```
[java.util.stream.ReferencePipeline$Head@3532ec19,
 java.util.stream.ReferencePipeline$Head@68c4039c,
 java.util.stream.ReferencePipeline$Head@ae45eb6,
 java.util.stream.ReferencePipeline$Head@59f99ea]
```

但仔细想想，当然，对于 Stream 中的每个元素，我们都在创建另一个 Stream。再仔细看看 map(x -> Stream.of(...))，对于每一个单一元素，我们都在创建一个复数。如果你对复数执行 map，那么是时候使用 flatMap 了：

```java
Stream.of(1, 2, 3, 4)
    .flatMap(x -> Stream.of(-x, x, x + 1))
    .collect(Collectors.toList())
```

这将生成我们想要的结果：

```
[-1, 1, 2, -2, 2, 3, -3, 3, 4, -4, 4, 5]
```

flatMap 的应用场景是巨大的。

接下来让我们挑战一些更复杂的功能，这适用于任何函数式编程或数据工程任务。考虑以下关系，其中省略了 getters、setters 和 toString 方法：

```java
class Employee {
    private String firstName, lastName;
    private Integer yearlySalary;
    // getters, setters, toString
}

class Manager extends Employee {
    private List<Employee> employeeList;
    // getters, setters, toString
}
```

假设我们只得到一个 Stream，我们的目标是计算所有员工的薪水，包括经理和他们的员工。我们可能会想直接使用 forEach 来遍历这些薪水。不幸的是，这样会让我们的代码模型与数据结构直接挂钩，造成不必要的复杂性。一个更好的解决方案是反过来操作，按代码的结构来组织数据。这时，flatMap 就派上用场了：

```java
List.of(manager1, manager2).stream()
    .flatMap(m -> 
        Stream.concat(m.getEmployeeList().stream(), Stream.of(m)))
    .distinct()
    .mapToInt(Employee::getYearlySalary)
    .sum();
```

这段代码为每个经理返回一个复数——经理和他们的员工。然后我们使用 flatMap 将这些集合合并成一个 Stream，并使用 distinct 来去除重复项。现在，我们可以将它们作为一个集合处理。接下来的步骤很简单。首先，我们使用 Java 特有的 mapToInt 方法提取每个员工的年薪，并返回一个 IntStream，这是专为整数设计的 Stream 类型。最后，我们对 Stream 求和。简洁的代码。

无论你使用 Stream 还是其他类型的 C（C 代表任何流或集合），在使用 forEach 或其他终端操作（如 collect）之前，尽量使用 map、filter、flatMap 或 groupBy 进行数据处理。如果过早地使用了终端操作，你将失去 Java Streams、流库或大数据框架所提供的懒加载和优化。

---

## 48. Know Your Collections  

### English

---

Collections are a staple in any programming language. They constitute one of the basic building blocks of commonly developed code. The Java language introduced the Collections framework a long time ago in JDK 1.2. Many programmers reach for ArrayList as their de facto collection to use. However, there’s more to collections than ArrayList, so let’s explore.

Collections can be classified as ordered or unordered. Ordered collections have a predictable iteration order; unordered collections do not have a predictable iteration order. Another way to classify collections is sorted or unsorted. The elements in a sorted collection are sequenced from start to end based on a comparator; unsorted collections have no particular sequence based on elements. Although sorted and ordered have similar meanings in English, they cannot always be used interchangeably for collections. The important distinction is that ordered collections have a predictable iteration order but no sort order. Sorted collections have a predictable sort order, hence they have a predictable iteration order. Remember: all sorted collections are ordered collections, but not all ordered collections are sorted collections.

There are various ordered, unordered, sorted, and unsorted collections in the JDK. Let’s take a look at a few of them.

List is an interface for ordered collections with a stable indexing order. Lists allow duplicate elements to be inserted and provide a predictable iteration order. The JDK offers List implementations like ArrayList and LinkedList. To find a particular element, the contains method can be used. The contains operation traverses the list from the beginning, hence finding elements in a List is an O(n) operation.

Map is an interface that maintains key-to-value relationships, and retains only unique keys. If the same key and different value is added to a map, the old value is replaced by the new value. The JDK offers Map implementations like HashMap, LinkedHashMap, and TreeMap. A HashMap is unordered, whereas a LinkedHashMap is ordered; both rely on hashCode and equals to determine unique keys. A TreeMap is sorted: the keys are sorted according to a comparator or by the sort order of Comparable keys. TreeMap relies on compareTo to determine sort order and uniqueness of keys. To find a particular element, Map provides the containsKey and containsValue methods. For HashMap, containsKey looks up the key in the internal hash table. If the look-up results in a non-null object, it is checked for equality with the object passed to containsKey. The containsValue operation traverses all the values from the beginning. Hence, finding keys in a HashMap is an O(1) operation, whereas finding values in a HashMap is an O(n) operation.

Set is an interface for collections of unique elements. In the JDK, sets are backed by maps where the keys are the elements and values are null. The JDK offers Set implementations like HashSet (backed by HashMap), LinkedHashSet (backed by LinkedHashMap), and TreeSet (backed by TreeMap). To find a particular element, the contains method can be used for Set. The contains method on a Set delegates to containsKey of a Map and therefore is an O(1) operation.

Collections are an important piece of a software puzzle. To use them effectively, it is necessary to understand their functionality, their implementation, and last but not least, the implications of using an iteration pattern. Remember to read the documentation, and write tests while using these versatile and basic building blocks of code.

---

### 中文

---

**集合（Collections）在任何编程语言中都是必不可少的。**
 它们构成了常用代码的基本构建块。Java语言很早在JDK 1.2中引入了集合框架。许多程序员默认使用**ArrayList**作为常用集合，但集合远不止于此，我们来深入了解一下。

### 集合的分类

1. **有序和无序集合**
   - **有序集合**：有可预测的迭代顺序。
   - **无序集合**：没有可预测的迭代顺序。
2. **排序和未排序集合**
   - **排序集合**：元素根据比较器从开始到结束有序排列。
   - **未排序集合**：元素没有特定顺序。

### 重要区别：

- **有序集合**有可预测的迭代顺序，但没有排序顺序。
- **排序集合**不仅有可预测的迭代顺序，还基于比较器或**Comparable**的排序顺序。
- **记住**：所有排序集合都是有序集合，但并非所有有序集合都是排序集合。

### JDK中的集合类型

1. **List（列表）**
   - 有序集合，提供稳定的索引顺序。
   - 允许插入重复元素，并提供可预测的迭代顺序。
   - 常见实现：**ArrayList** 和 **LinkedList**。
   - 查找特定元素时，使用**contains**方法，时间复杂度为O(n)。
2. **Map（映射）**
   - 维护键值对关系，只保留唯一键。
   - 常见实现：**HashMap**（无序）、**LinkedHashMap**（有序）、**TreeMap**（排序）。
   - **HashMap**：查找键的时间复杂度是O(1)，查找值是O(n)。
   - **TreeMap**：按比较器或**Comparable**排序，查找操作基于排序，时间复杂度较高。
3. **Set（集合）**
   - 唯一元素的集合。
   - JDK中的实现：**HashSet**（基于HashMap）、**LinkedHashSet**（基于LinkedHashMap）、**TreeSet**（基于TreeMap）。
   - 查找特定元素时，使用**contains**方法，时间复杂度为O(1)。

### 总结

集合是软件开发中的一个重要组成部分。要有效使用集合，必须了解它们的功能、实现方式，以及使用迭代模式时的影响。记得阅读文档，并在使用这些基本构建块时编写测试代码。

---

## 49. Kotlin Is a Thing  

### English

------

Java is maybe the most mature and vetted language still in common use, and that is unlikely to change dramatically in the foreseeable future. To facilitate modern notions of what a programming language should do, some smart folks decided to write a new language that did all the Java Things, plus some cool new Things that would be fairly painless to learn and be largely interoperable. Someone like me, who’s been working on the same huge Android app for years, can decide to write a single class in Kotlin without committing to a complete migration.

Kotlin is meant to let you write shorter, cleaner, more modern code. While modern and preview versions of Java do address a lot of the issues Kotlin manages, Kotlin can be especially useful for Android developers, who are stuck somewhere between Java 7 and Java 8. Let’s look at a few examples, like Kotlin’s property constructor pattern for models, starting with a simple example of what a Java model may look like:

```java
public class Person {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

We could create a special constructor to take some initial values:

```java
public class Person {
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    ...
}
```

Not too bad, but you can probably see how a few more properties could make the definition for this pretty simple class get bloated really quickly. Let’s take a look at that class in Kotlin:

```kotlin
class Person(val name: String, var age: Int)
```

That’s it! Another neat example is delegation. Kotlin delegates allow you to provide logic for any number of read operations. One example is the lazy initialization, a concept sure to be familiar to Java developers. It might look like this:

```java
public class SomeClass {
    private SomeHeavyInstance someHeavyInstance = null;

    public SomeHeavyInstance getSomeHeavyInstance() {
        if (someHeavyInstance == null) {
            someHeavyInstance = new SomeHeavyInstance();
        }
        return someHeavyInstance;
    }
}
```

Again, not too terrible, done simply and without configuration, but chances are you’ll repeat this same code several times in your code, violating the DRY principle (Don’t Repeat Yourself). Also, not threadsafe. Here’s the Kotlin version:

```kotlin
val someHeavyInstance by lazy {
    return SomeHeavyInstance()
}
```

Short and sweet and readable. All that boilerplate is tucked away nicely under the covers. Oh, and it’s thread-safe too. Null safety is also a big upgrade. You’ll see a lot of question mark operators following a nullable reference in Kotlin:

```kotlin
val something = someObject?.someMember?.anotherMember
```

Here’s the same thing in Java:

```java
Object something = null;
if (someObject != null) {
    if (someObject.someMember != null) {
        if (someObject.someMember.anotherMember != null) {
            something = someObject.someMember.anotherMember;
        }
    }
}
```

The null-check operator (?) will stop evaluating immediately and return null as soon as any of the referents in the chain resolve to null.

Let’s close out with another killer feature: coroutines. In a nutshell, a coroutine performs work asynchronous to the calling code, although that work may be handed off to some number of threads. It’s important to note that even if a single thread handles multiple coroutines, Kotlin performs some context-switching magic that runs multiple jobs concurrently. While specific behavior is configurable, coroutines naturally use a dedicated thread pool, but use context switching within a single thread (so hot). Since they’re Kotlin, they also can be fancy and sophisticated and overengineered, but by default they’re also super simple:

```kotlin
launch {
    println("Hi from another context")
}
```

Be aware of the differences between threads and coroutines though—for example, an object.wait() invocation in one job will pause all the other jobs working in the containing thread. Give Kotlin a spin and see what you think.

---

### 中文

---

**Kotlin 语言简介：**

**Kotlin**是为了让开发者写出更简洁、更现代的代码而设计的。它与**Java**具有很好的互操作性，允许开发者在不进行完整迁移的情况下，轻松地将**Kotlin**与现有的Java代码混合使用。这对于像我这样长期在同一个Android应用中工作的人来说，尤其方便。

### Kotlin 优势：

- **简洁现代**：Kotlin 提供了比 Java 更简洁的语法，并解决了 Java 的一些问题，特别适合 Android 开发者。
- **与 Java 兼容**：Kotlin 可以与现有的 Java 代码无缝集成，便于过渡和逐步迁移。

### Kotlin 的几个特点：

1. **简化模型类**：
    在 Java 中，定义模型类需要编写冗长的 getter 和 setter 方法，如下所示：

   ```java
   public class Person {
       private String name;
       private Integer age;
   
       public String getName() {
           return name;
       }
   
       public void setName(String name) {
           this.name = name;
       }
   
       public Integer getAge() {
           return age;
       }
   
       public void setAge(int age) {
           this.age = age;
       }
   }
   ```

   Kotlin 通过简洁的构造器语法，可以将其转化为：

   ```kotlin
   class Person(val name: String, var age: Int)
   ```

2. **延迟初始化（Lazy Initialization）**：
    Java 版本的延迟初始化可能如下所示：

   ```java
   public class SomeClass {
       private SomeHeavyInstance someHeavyInstance = null;
   
       public SomeHeavyInstance getSomeHeavyInstance() {
           if (someHeavyInstance == null) {
               someHeavyInstance = new SomeHeavyInstance();
           }
           return someHeavyInstance;
       }
   }
   ```

   Kotlin 使用 `by lazy` 实现更加简洁且线程安全的延迟初始化：

   ```kotlin
   val someHeavyInstance by lazy {
       return SomeHeavyInstance()
   }
   ```

3. **空安全（Null Safety）**：
    Kotlin 提供了更简洁的空检查操作符 `?.`，可以直接链式调用，而不必编写多个 `if` 判断：

   ```kotlin
   val something = someObject?.someMember?.anotherMember
   ```

   Java 中则需要手动逐层检查：

   ```java
   Object something = null;
   if (someObject != null) {
       if (someObject.someMember != null) {
           if (someObject.someMember.anotherMember != null) {
               something = someObject.someMember.anotherMember;
           }
       }
   }
   ```

4. **协程（Coroutines）**：
    协程允许异步执行任务，可以在单线程中高效地运行多个任务，而不需要线程切换。Kotlin 的协程提供了极其简洁的写法：

   ```kotlin
   launch {
       println("Hi from another context")
   }
   ```

   与线程不同，协程不会阻塞其他任务，并且能通过上下文切换在单线程内并发执行多个任务。

### 总结：

Kotlin 提供了比 Java 更简洁、更现代的编程方式，同时保持与 Java 的良好互操作性。它适合开发Android应用，尤其对于那些使用Java 7和Java 8之间版本的开发者而言，Kotlin 是一个非常值得尝试的选择。

---

## 50. Learn Java Idioms and Cache in Your Brain  

### English

------

As programmers, there are some tasks we need to do frequently. For example, going through data and applying a condition are common. Here are two ways to count how many positive numbers are in a list:

```java
public int loopImplementation(int[] nums) {
    int count = 0;
    for (int num : nums) {
        if (num > 0) {
            count++;
        }
    }
    return count;
}
public long streamImplementation(int[] nums) {
    return Arrays.stream(nums)
                 .filter(n -> n > 0)
                 .count();
}
```

Both of these accomplish the same thing, and they both use common Java idioms. An idiom is a common way of expressing some small piece of functionality that the community has general agreement on. Knowing how to write these quickly without having to think about them enables you to write code much faster. As you write code, look for patterns like these. You can even practice them to get faster and learn them by heart.

Some idioms, like looping, conditions, and streams, apply to all Java programmers. Others are more specific to the types of code you work on. For example, I do a lot with regular expressions and file I/O. The following idiom is one I commonly use in file I/O. It reads a file, removes any blank lines, and writes it back:

```java
Path path = Paths.get("words.txt");
List<String> lines = Files.readAllLines(path);
lines.removeIf(t -> t.trim().isEmpty());
Files.write(path, lines);
```

If I were on a team where files didn’t fit in memory, I’d have to use a different programming idiom. However, I deal with small files where this is not an issue, so the convenience of four lines to do something powerful is worth it.

Notice with these idioms that much of the code is common regardless of your task. If I want to get negative numbers or odd numbers, I just change the if statement or filter. If I want to remove all lines that are more than 60 characters long, I just change the condition in removeIf:

```java
lines.removeIf(t -> t.length() <= 60);
```

Regardless, I’m thinking about what I want to accomplish. I’m not looking up how to read a file or how to count values. That’s an idiom I learned long ago.

An interesting thing about idioms is that you don’t always learn them intentionally. I never sat down and decided to learn the idiom for reading/writing a file. I learned it from using it a lot. Looking up information repeatedly helps you learn it. Or at least helps you know where to find it. For example, I have trouble remembering the regular expression flags. I know what they do, but mix up ?s and ?m. I have looked it up enough times that I know I should google “javadoc pattern” to get the answer.

In conclusion, let your brain serve as a cache. Learn the idioms and common library API calls. Know where to look up the rest quickly. This will free you up to let your brain work on the hard stuff!

---

### 中文

---

**程序员常用的编程习惯用法：**

作为程序员，有些任务是我们经常需要执行的。例如，遍历数据并应用条件是很常见的。以下是两种计算列表中正数个数的方式：

```java
// 循环实现
public int loopImplementation(int[] nums) {
    int count = 0;
    for (int num : nums) {
        if (num > 0) {
            count++;
        }
    }
    return count;
}

// 流式实现
public long streamImplementation(int[] nums) {
    return Arrays.stream(nums)
                 .filter(n -> n > 0)
                 .count();
}
```

这两种方式实现的功能相同，都是常见的 Java 编程习惯用法。**习惯用法**是指社区普遍认可的实现某些功能的常见方式。掌握这些快速写出代码的技巧，可以让你提高编程效率。

### 习惯用法的类型：

1. **通用习惯用法**：如循环、条件判断和流（Streams）是每个 Java 程序员都应该掌握的。

2. **特定领域习惯用法**：例如，我经常使用正则表达式和文件 I/O。以下是我常用的文件 I/O 习惯用法，它读取文件、去除空行并写回文件：

   ```java
   Path path = Paths.get("words.txt");
   List<String> lines = Files.readAllLines(path);
   lines.removeIf(t -> t.trim().isEmpty());
   Files.write(path, lines);
   ```

   如果文件太大无法完全加载到内存，我需要使用不同的编程习惯。然而，对于我处理的小文件来说，这四行代码完成一个强大的功能是非常方便的。

### 习惯用法的灵活性：

这些习惯用法的大部分代码都是常见的，无论你在做什么任务。比如，如果我想获取负数或奇数，只需要改变 `if` 语句或过滤条件。如果我要去除所有超过 60 个字符的行，只需要修改 `removeIf` 的条件：

```java
lines.removeIf(t -> t.length() <= 60);
```

无论如何，我的关注点都是我想要实现的目标，而不是查找如何读取文件或如何计数。这样的习惯用法早已是我熟知的。

### 学习习惯用法的过程：

有趣的是，习惯用法并不总是通过有意识的学习获得的。我从未专门去学习读取/写入文件的习惯用法，而是通过反复使用它学到的。反复查找信息有助于你记住它，或者至少知道去哪找。

例如，我常常记不住正则表达式的标志。虽然我知道它们的功能，但经常混淆 `?s` 和 `?m`。我查过足够多次，已经知道可以搜索“javadoc pattern”来找到答案。

### 总结：

让大脑作为缓存来工作，学习常用的编程习惯用法和库 API 调用。剩下的可以快速查找，这样能让你的大脑专注于更复杂的任务！

---

## 51. Learn to Kata and Kata to Learn  

### English

------

Every Java developer needs to learn new skills and keep their existing skills sharp. The Java ecosystem is enormous and continues to evolve. With so much to learn, the prospect of keeping up may seem daunting. We can help each other keep up in this rapidly changing space if we work together as a community, sharing knowledge and practice. Taking, creating, and sharing code katas is one of the ways we can do this.

A code kata is a hands-on programming exercise that helps you hone specific skills through practice. Some code katas will provide you structure to validate that a skill has been acquired by getting unit tests to pass. Code katas are a great way for developers to share practice exercises with their future selves and other developers to learn from.

Here’s how to create your first code kata:

1. Select a topic you want to learn.
2. Write a passing unit test that demonstrates some piece of knowledge.
3. Refactor the code repeatedly until you are satisfied with the final solution. Make sure the test passes after each refactoring.
4. Delete the solution in the exercise and leave a failing test.
5. Commit the failing test with supporting code and build artifacts to a version control system (VCS).
6. Open source the code to share with others.

Now I’ll demonstrate how to create a small kata by following the first four steps:

1. Topic: Learn how to join strings in a List.
2. Write a passing JUnit test that shows how to join strings in a List:

```java
@Test
public void joinStrings() {
    List<String> names = Arrays.asList("Sally", "Ted", "Mary");
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < names.size(); i++) {
        if (i > 0) {
            builder.append(", ");
        }
        builder.append(names.get(i));
    }
    String joined = builder.toString();
    Assert.assertEquals("Sally, Ted, Mary", joined);
}
```

1. Refactor the code to use StringJoiner in Java 8. Rerun the test:

```java
StringJoiner joiner = new StringJoiner(", ");
for (String name : names) {
    joiner.add(name);
}
String joined = joiner.toString();
```

Refactor the code to use Java 8 streams. Rerun the test:

```java
String joined = names.stream().collect(Collectors.joining(", "));
```

Refactor the code to use String.join. Rerun the test:

```java
String joined = String.join(", ", names);
```

1. Delete the solution and leave a failing test with a comment:

```java
@Test
public void joinStrings() {
    List<String> names = Arrays.asList("Sally", "Ted", "Mary");
    // Join the names and separate them by ", "
    String joined = null;
    Assert.assertEquals("Sally, Ted, Mary", joined);
}
```

Pay it forward—I’ll leave steps 5 and 6 as an exercise for the reader. This example should be simple enough to illustrate how to create your own katas of varying complexity, leveraging unit tests to provide the structure necessary to build confidence and understanding.

Value your own learning and knowledge. When you learn something useful, write it down. Saving practice exercises to recall how things work can be quite helpful. Capture your knowledge and exploration in code katas. Katas you have used to sharpen your own skills may also be valuable to others.

We all have things to learn and that we can teach. When we share what we learn with others, we improve the whole Java community. This is vitally important to helping ourselves and our fellow Java developers collectively improve our coding skills.

---

### 中文

---

**每个 Java 开发者都需要学习新技能，并保持现有技能的敏锐度。**

Java 生态系统庞大并且不断发展。面对如此多的知识，跟上进度可能会让人感到困难。如果我们作为社区一起合作，分享知识和实践，就能相互帮助，跟上这个快速变化的领域。进行、创建和分享代码练习（code katas）是我们可以做的其中一种方式。

**什么是代码练习（Code Kata）？**
 代码练习是通过实践来帮助你磨练特定技能的编程练习。一些代码练习通过让单元测试通过来验证你已经掌握了某项技能。代码练习是开发者与未来的自己以及其他开发者分享练习的好方法。

**创建你的第一个代码练习：**

1. 选择一个你想学习的主题。
2. 编写一个通过的单元测试，展示某个知识点。
3. 不断重构代码，直到你对最终解决方案满意。确保每次重构后测试依然通过。
4. 删除练习中的解决方案，留下一个失败的测试。
5. 将失败的测试和支持代码提交到版本控制系统（VCS）。
6. 开源代码，分享给他人。

**示例：创建一个简单的代码练习**

1. 主题：学习如何连接列表中的字符串。
2. 编写一个通过的 JUnit 测试，展示如何连接列表中的字符串：

```java
@Test
public void joinStrings() {
    List<String> names = Arrays.asList("Sally", "Ted", "Mary");
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < names.size(); i++) {
        if (i > 0) {
            builder.append(", ");
        }
        builder.append(names.get(i));
    }
    String joined = builder.toString();
    Assert.assertEquals("Sally, Ted, Mary", joined);
}
```

1. 使用 Java 8 的 `StringJoiner` 进行重构，重新运行测试：

```java
StringJoiner joiner = new StringJoiner(", ");
for (String name : names) {
    joiner.add(name);
}
String joined = joiner.toString();
```

1. 使用 Java 8 流（Streams）进行重构，重新运行测试：

```java
String joined = names.stream().collect(Collectors.joining(", "));
```

1. 使用 `String.join` 进行重构，重新运行测试：

```java
String joined = String.join(", ", names);
```

1. 删除解决方案，留下一个失败的测试，并加上注释：

```java
@Test
public void joinStrings() {
    List<String> names = Arrays.asList("Sally", "Ted", "Mary");
    // Join the names and separate them by ", "
    String joined = null;
    Assert.assertEquals("Sally, Ted, Mary", joined);
}
```

**继续前行**—步骤 5 和 6 留给读者自己完成。这个例子应该足够简单，能够展示如何创建不同复杂度的代码练习，并利用单元测试提供必要的结构来建立信心和理解。

**珍视自己的学习和知识。**
 当你学到有用的东西时，记下来。保存练习以回顾事物是如何运作的，非常有帮助。通过代码练习捕捉你的知识和探索。你用来磨练自己技能的练习也可能对其他人有价值。

我们每个人都有要学习的东西，也有可以教给他人的东西。当我们与他人分享所学时，我们提升了整个 Java 社区的水平。这对于帮助自己和其他 Java 开发者共同提高编程技能至关重要。

---

## 52. Learn to Love Your Legacy Code  

### English

------

What is a legacy system? It is old software that is very hard to maintain, to extend, and to improve. On the other hand, it is also a system that is working and is serving the business; otherwise, it would not have survived. Perhaps, when it was first created, a legacy system had an excellent design, a design so good that people started to say, “OK, maybe we can use it also for this, and this, and this.” It becomes overloaded with technical debt, but it still works. These systems can be amazingly resilient.

Still, developers hate working on legacy systems. It can seem there’s more technical debt than anybody could ever repay. Perhaps we should just declare bankruptcy and move on. Much easier. What if you really have to maintain it? What do you do when you have to fix a bug?

Solution number one: duct tape. Hold your nose, fix the defect—“OK, we may regret this one day, but let’s do this copy–paste now, just to fix it.” From there it will only get worse. Like in an abandoned building, it may stay undamaged for a long time, but as soon as there is a single broken window, it will soon be left without any windows intact. Just seeing one broken window encourages people to break others. This is the law of broken windows.

Solution number two: forget the old system and rewrite from scratch. Can you imagine what the problem with this solution is? More often than not, the rewrite will not work or it will never be finished. This comes from survival bias. You see the old system code and say, “Oh, come on, if whoever wrote this terrible code was able to make it work, it must be quite easy.” But it’s not. You may consider the code horrible, but it’s code that has already survived many battles. When you start from scratch, you don’t know the battle stories, and you’ve lost a lot of knowledge about the domain.

So what should we do? In Japan, there is an art called kintsugi. When a precious object breaks, instead of throwing it away, it is put back together using gold powder along its cracking lines. The gold emphasizes that it was broken, but it’s still beautiful. Perhaps we are looking at the legacy code from the wrong point of view? I am not saying we should goldplate the old code, but we should learn how to fix it in a way that makes us proud of it.

The strangler pattern allows us to do precisely this. It is named for a fig tree (not for homicide!) that wraps around other trees. Its growth progressively surrounds the host tree, which withers away until all that is left are the fig vines around a hollow core. Similarly, we start replacing a smelly line of code with a new, clean one that has been thoroughly tested. And then, proceeding from there, we create a new application that creeps on top of the previous one until it completely replaces the old one.

But even if we don’t complete it, the mix of new and old is much better than letting the old one rot. It is much safer than a complete rewrite because we will validate the new behavior continuously, and we can always roll back the latest version in case we introduced bugs.

Legacy code deserves a little love.

---

### 中文

---

**什么是遗留系统？**

遗留系统是指那些非常难以维护、扩展和改进的旧软件。另一方面，它们仍然在工作，仍然为业务提供服务，否则它们不会生存下来。也许，当它最初创建时，遗留系统设计得非常优秀，设计如此之好，以至于人们开始说：“好吧，也许我们可以用它来做这个、这个和这个。”但它变得充满了技术债务，但它仍然在工作，这些系统表现出惊人的韧性。

**开发者讨厌工作在遗留系统上。**
 它看起来有更多的技术债务，似乎是永远也还不完的。也许我们应该宣布破产，继续前进。这样更容易。但如果你真的必须维护它呢？当你需要修复一个 bug 时该怎么办？

**解决方案一：临时修补**
 捏着鼻子修复缺陷——“好吧，可能有一天我们会后悔这个，但现在先做这个复制–粘贴，只是为了修复它。”从此以后，情况只会变得更糟。就像在废弃的建筑物中，可能会长时间没有损坏，但一旦有一扇窗户破了，其他窗户也很快会被打破。看到一扇破窗户就会鼓励人们破坏其他窗户。这就是破窗效应。

**解决方案二：放弃旧系统，从头重写**
 你能想象这个解决方案的缺点吗？更多时候，重写不会成功，或者永远也写不完。这源于幸存者偏差。你看到旧系统代码时，会说：“哦，拜托，如果写这些糟糕代码的人都能让它工作，那一定很简单。”但事实并非如此。你可能认为代码很糟糕，但它已经经历了许多战斗。当你从头开始时，你不知道这些战斗的故事，你失去了大量的领域知识。

**那我们应该怎么办？**

在日本，有一种艺术叫做金缮。当一件珍贵物品破损时，不是把它丢掉，而是沿着裂缝用金粉将它重新修复。金粉突出了它曾经破碎的痕迹，但它依然美丽。也许我们在看待遗留代码时，应该换个角度？我不是说我们应该给旧代码镀金，但我们应该学会以一种让我们为之骄傲的方式修复它。

**缠绕模式（Strangler Pattern）**
 缠绕模式可以让我们做到这一点。它得名于一种榕树（不是谋杀！），这种树会环绕其他树生长。它逐渐围绕着宿主树生长，直到宿主树枯萎，最后只剩下环绕着空心核心的榕树藤。类似地，我们开始用经过充分测试的新、干净的代码替换掉有问题的代码行。然后，从这里开始，我们创建一个新的应用程序，它逐渐覆盖旧的应用程序，直到完全替换掉旧的系统。

即使我们没有完成，新的和旧的混合状态也比让旧系统腐烂要好得多。这比完全重写要安全得多，因为我们会持续验证新行为，并且可以随时回滚到上一个版本，以防引入 bug。

**遗留代码值得一点关爱。**

---

## 53. Learn to Use New Java Features  

### English

------

Java 8 introduced lambdas and streams, two game-changing features that give Java programmers significant language constructs. From Java 9 onward, release cycles occur every six months with more features popping up in each release. You should care about these new features because they help you write better code. And, your skills will improve as you incorporate new language paradigms into your programming arsenal.

Much has been written about streams and how they support a functional programming style, reduce bulky code, and make code more readable. So, let’s look at an example with streams, not so much to convince you to use streams everywhere but to entice you to learn about this and other Java features introduced since Java 8.

Our example computes the maximum, average, and minimum for systolic, diastolic, and pulse values from collected blood-pressure monitoring data. We want to visualize these computed summary statistics with a JavaFX bar chart.

Here’s a portion of our BPData model class, showing just the getter methods we need:

```java
public class BPData {
    ...
    public final Integer getSystolic() {
        return systolic.get();
    }
    public final Integer getDiastolic() {
        return diastolic.get();
    }
    public final Integer getPulse() {
        return pulse.get();
    }
    ...
}
```

The JavaFX bar chart creates the magic for this visualization. First, we need to build the correct series and feed our transformed data to the bar chart object. Since the operation is repeated for each series, it makes sense to create a single method to parameterize both the bar chart series and the specific BPData getter required to access this data. Our source data is stored in the variable sortedList, a date-sorted collection of BPData elements. Here’s the `computeStatData` method that builds our chart data:

```java
private void computeStatData(
    XYChart.Series<String, Number> targetList,
    Function<BPData, Integer> f) {
    // Set Maximum
    targetList.getData().get(MAX).setYValue(sortedList.stream()
        .mapToInt(f::apply).max().orElse(1));
    // Set Average
    targetList.getData().get(AVG).setYValue(sortedList.stream()
        .mapToInt(f::apply).average().orElse(1.0));
    // Set Minimum
    targetList.getData().get(MIN).setYValue(sortedList.stream()
        .mapToInt(f::apply).min().orElse(1));
}
```

Parameter `targetList` is the bar chart series data that corresponds to one of systolic, diastolic, or pulse data. We want to create a bar chart with the maximum, average, and minimum corresponding to each of these series. Thus, we set the chart’s Y-value to these computed values. The second parameter is the specific getter from `BPData`, passed as a method reference. We use this in the stream `mapToInt` method to access the specific values for that series. Each stream sequence returns the maximum, average, or minimum of the source data. Each terminating stream method returns `orElse`, an `Optional` object, making our bar chart display a placeholder value of 1 (or 1.0) if the source data stream is empty.

Here’s how to invoke this `computeStatData` method. The convenient method reference notation makes it easy to specify which `BPData` getter method to invoke for each data series:

```java
computeStatData(systolicStats, BPData::getSystolic);
computeStatData(diastolicStats, BPData::getDiastolic);
computeStatData(pulseStats, BPData::getPulse);
```

Prior to Java 8, this code was much more tedious to write. So, learning and using new Java features is a worthwhile skill to embrace as Java continues to improve.

For your next feature, how about checking out Java 14’s record syntax, a preview feature, to simplify the `BPData` class?

---

### 中文

---

**Java 8 引入了 Lambda 和 Stream，这两个改变游戏规则的特性，赋予了 Java 开发者强大的语言构造。** 从 Java 9 开始，发布周期为每六个月一次，每个版本都带来更多的新特性。你应该关注这些新特性，因为它们能帮助你编写更好的代码，同时通过将新的语言范式融入到编程工具集中，你的技能也会不断提升。

**Stream 支持函数式编程风格，减少冗长的代码，使代码更具可读性。** 这里我们通过一个使用 Stream 的例子，展示如何计算收集的血压监测数据中的最大值、平均值和最小值，并使用 JavaFX 条形图展示这些统计数据。这个例子不仅是为了说服你在所有地方使用 Stream，而是为了激发你去了解自 Java 8 以来引入的其他 Java 特性。

### BPData 模型类的一部分（只显示需要的 getter 方法）：

```java
public class BPData {
    ...
    public final Integer getSystolic() {
        return systolic.get();
    }
    public final Integer getDiastolic() {
        return diastolic.get();
    }
    public final Integer getPulse() {
        return pulse.get();
    }
    ...
}
```

### JavaFX 条形图代码：

首先，我们需要构建正确的系列并将转换后的数据传递给条形图对象。由于每个系列的操作都是重复的，因此我们创建一个方法来参数化条形图系列和所需的 BPData getter 方法。我们的源数据存储在 `sortedList` 中，这是一个按日期排序的 BPData 元素集合。以下是构建图表数据的 `computeStatData` 方法：

```java
private void computeStatData(XYChart.Series<String, Number> targetList, Function<BPData, Integer> f) {
    // 设置最大值
    targetList.getData().get(MAX).setYValue(sortedList.stream()
        .mapToInt(f::apply).max().orElse(1));
    // 设置平均值
    targetList.getData().get(AVG).setYValue(sortedList.stream()
        .mapToInt(f::apply).average().orElse(1.0));
    // 设置最小值
    targetList.getData().get(MIN).setYValue(sortedList.stream()
        .mapToInt(f::apply).min().orElse(1));
}
```

**方法解释：**

- `targetList` 是条形图的系列数据，代表收缩压、舒张压或脉搏数据中的一个。
- 第二个参数是 `BPData` 中的特定 getter，通过方法引用传递。在 `stream` 的 `mapToInt` 方法中使用它来访问该系列的特定值。
- 每个 `stream` 序列返回源数据的最大值、平均值或最小值。每个终止的 `stream` 方法返回一个 `orElse`，这是一个 `Optional` 对象，如果源数据流为空，将在条形图中显示占位符值 1 或 1.0。

### 调用 `computeStatData` 方法：

```java
computeStatData(systolicStats, BPData::getSystolic);
computeStatData(diastolicStats, BPData::getDiastolic);
computeStatData(pulseStats, BPData::getPulse);
```

**总结：**
 在 Java 8 之前，这段代码写起来要复杂得多。学习和使用新的 Java 特性是一个值得掌握的技能，随着 Java 的不断改进，你的编程能力也会随之提升。

接下来，你可以尝试看看 Java 14 中的 `record` 语法（预览特性），它能简化 `BPData` 类的编写。

---

## 54. Learn Your IDE to Reduce Cognitive Load  

### English

------

I work for a firm that sells IDEs, so of course I’m going to say you should know how your IDE works and use it properly. Before that, I spent 15 years working with multiple IDEs, learning how they help developers create something useful and how to use them to easily automate tasks.

We all know IDEs provide code highlighting and show errors and potential problems, but any Java IDE can do so much more than that. Learning what your IDE is capable of and using the features that apply to your daily work can help level up your productivity.

For example, your IDE:

- Can generate code for you so you don’t have to type it. Getters and setters, equals and hashCode, and toString are the most frequent examples.
- Has refactoring tools that can automatically move your code in a particular direction while keeping the compiler happy.
- Can run your tests and help you debug problems. If you’re using System.out for debugging, it’s going to take you much longer than if you’re inspecting the values of objects at runtime.
- Should integrate with your build and dependency management system so your development environment works the same way as your testing and production environments.
- Can even help you with tools or systems external to your application code—for example, version control, database access, or code review (remember, the I in IDE stands for integrated). You don’t have to leave the tool to work with all aspects of your software delivery pipeline.

Using the IDE, you can navigate through the code naturally—finding the methods that call this piece of code, or moving into the method that this code calls. You can move directly to files (or even to specific code snippets) using a few keystrokes instead of the mouse to navigate a file structure.

The tool you choose to write code in should be helping you focus on what you’re developing. You shouldn’t be thinking about the intricacies of how you code it. By offloading the tedious stuff onto the IDE, you reduce your cognitive load and can spend more brain power on the business problem you’re trying to solve.

---

### 中文

---

我在一家销售IDE的公司工作，因此我当然认为你应该了解如何正确使用IDE。在此之前，我花了15年时间使用多个IDE，学习它们如何帮助开发者创造有用的东西，以及如何利用它们轻松地自动化任务。

我们都知道IDE可以提供代码高亮、显示错误和潜在问题，但任何Java IDE都能做得更多。了解IDE的功能并使用适合日常工作的特性，可以帮助提高生产力。

例如，IDE可以：

- 为你生成代码，免去你手动输入的麻烦。常见的例子包括生成getter、setter、equals和hashCode方法，以及toString方法。
- 拥有重构工具，可以自动将代码移动到特定的位置，同时保持编译器的正常工作。
- 运行你的测试并帮助调试问题。如果你使用System.out来调试，肯定比检查对象在运行时的值要慢得多。
- 应该与构建和依赖管理系统集成，这样你的开发环境就能与测试和生产环境一致。
- 甚至可以帮助你处理应用程序代码之外的工具或系统，例如版本控制、数据库访问或代码审查（记住，IDE中的I代表集成）。你无需离开IDE即可处理软件交付管道的所有方面。

使用IDE，你可以自然地浏览代码——查找调用某段代码的方法，或进入这段代码所调用的方法。你可以通过几个快捷键直接跳转到文件（甚至是具体的代码片段），而不需要使用鼠标去导航文件结构。

你选择用来写代码的工具应该帮助你专注于开发内容，而不是代码的细节。通过将繁琐的工作交给IDE，你可以减少认知负担，腾出更多精力来解决业务问题。

---

## 55. Let’s Make a Contract: The Art of Designing a Java API  

### English

------

An API is what developers use to achieve some task. More precisely, it establishes a contract between them and the designers of the software, exposing its services through that API. In this sense, we’re all API designers: our software doesn’t work in isolation but becomes useful only when it interacts with other software written by other developers. When writing software, we’re not only consumers but also providers of one or more APIs, which is why every developer should know the characteristics of good APIs and how to achieve them.

Firstly, a good API should be easily understandable and discoverable. It should be possible to start using it and, ideally, learn how it works without reading its documentation. To this end, it’s important to use consistent naming and conventions. This sounds pretty obvious; nevertheless, it’s easy to find, even in the standard Java API, situations where this suggestion hasn’t been followed. For instance, since you can invoke skip(n) to skip the first n items of a Stream, what could be a good name for the method that skips all the Stream’s items until one of them doesn’t satisfy a predicate p? A reasonable name could be skipWhile(p), but actually this method is called dropWhile(p).

There’s nothing wrong with the name dropWhile per se, but it isn’t consistent with skip performing a very similar operation. Don’t do this.

Keeping your API minimal is another way to make it easy to use. This reduces both the concepts to be learned and its maintenance costs. Once again, you can find examples breaking this simple principle in the standard Java API. Optional has a static factory method of(object) that creates an Optional wrapping the object passed to it. Incidentally, using factory methods instead of constructors is another valuable practice since it allows greater flexibility: doing so, you can also return an instance of a subclass or even a null when the method is called with illegal arguments. Unfortunately, Optional.of throws a NullPointerException when invoked with null, something unexpected from a class designed to prevent NullPointerExceptions (NPEs). This not only breaks the principle of least astonishment—another thing to consider when designing your API—but requires the introduction of a second method ofNullable returning an empty Optional when called with null. The of method has an inconsistent behavior and, if implemented correctly, the ofNullable one could have been left out.

Other good hints that could improve your API are: break apart large interfaces into smaller pieces; consider implementing a fluent API, for which, this time, Java Streams is a very good example; never return null, use empty collections and Optional instead; limit usage of exceptions, and possibly avoid checked ones. Regarding method arguments: avoid long lists of them, especially of the same type; use the weakest possible type; keep them in consistent order among different overloads; consider varargs. Moreover, the fact that a good API is self-explanatory doesn’t mean that you shouldn’t document it clearly and extensively.

Finally, don’t expect to write a great API the first time. Designing an API is an iterative process, and dogfooding is the only way to validate and improve it. Write tests and examples against your API and discuss them with colleagues and users. Iterate multiple times to eliminate unclear intentions, redundant code, and leaky abstraction.

---

### 中文

---

API是开发者用来完成任务的工具。更准确地说，它建立了开发者与软件设计者之间的契约，通过API暴露软件的服务。从这个角度来看，我们每个人都是API设计师：我们的软件并非独立运行，只有在与其他开发者编写的软件交互时，它才变得有用。在编写软件时，我们不仅是API的使用者，也是一个或多个API的提供者，这也是为什么每个开发者都应该了解优秀API的特点以及如何实现它们。

### 优秀API的特点：

1. **易于理解和发现**
    一个好的API应该容易上手，理想情况下，开发者可以在不阅读文档的情况下了解其工作原理。为此，使用一致的命名和约定非常重要。虽然这听起来很明显，但即便在标准Java API中，也常常会遇到没有遵循这一建议的情况。例如，`skip(n)`可以跳过Stream的前n个元素，那么跳过Stream中所有元素直到满足某个条件的函数应该叫什么呢？合理的名字是`skipWhile(p)`，但实际上，这个方法叫做`dropWhile(p)`。
    **注意：**`dropWhile`没有错，但它和`skip`执行的是非常相似的操作，命名上没有保持一致，避免这种情况。
2. **保持API简洁**
    简化API有助于提高易用性，减少需要学习的概念以及维护成本。例如，`Optional`类有一个静态工厂方法`of(object)`，它返回一个包含传入对象的`Optional`。但遗憾的是，`Optional.of`当传入`null`时会抛出`NullPointerException`，这一行为与设计时避免`NullPointerException`的初衷不一致，导致还需要引入一个`ofNullable`方法来处理传入`null`时返回一个空的`Optional`。这种不一致的行为可以通过合理实现`ofNullable`来避免。
3. **其他改善API的建议**
   - 将大接口拆分为多个小接口
   - 考虑实现流畅的API（例如Java Streams）
   - 永远不要返回`null`，使用空集合和`Optional`代替
   - 限制异常的使用，尽量避免检查型异常
   - 方法参数：避免过多相同类型的参数；使用最弱的类型；确保不同重载方法的参数顺序一致；考虑使用可变参数（varargs）
4. **自解释性**
    优秀的API应该是自解释的，但这并不意味着不需要进行清晰、详尽的文档编写。

### 设计API的过程：

- **迭代过程**
   不要指望第一次就写出完美的API。设计API是一个迭代过程，通过“自家使用”（dogfooding）来验证和改进它。编写测试和示例，和同事及用户讨论，反复迭代，消除不清晰的意图、冗余代码和泄露的抽象。

---

## 56. Make Code Simple and Readable  

### English

---

I am a big fan of simple and readable code. Every line of code should be as self-explanatory as possible. Every line of code should be necessary. To achieve readable and simple code, there are two aspects: format and content. Here are some tips to help you write code that is readable and simple:

Use indentation to lay out your code clearly.
 Use it consistently. If you work in a project, there should be a code template. Everyone on the team should adopt the same code format.
 Don’t mix spaces with tabs. I always have the IDE configured to display spaces and tabs so that I can spot the mix and fix them. (Personally, I love spaces.) Choose either spaces or tabs, and stick to it.

Use meaningful variable names and method names.
 The code is much easier to maintain if it is self-explanatory. With meaningful identifiers, your code can talk for itself instead of needing a separate comment line to explain what it does. Steer clear of single-letter variable names. If your variable and method names have clear meaning, you will not normally need comments to explain what your code does.

Comment your code if necessary.
 If the logic is very complex, such as regex queries, etc., use documentation to explain what the code is trying to do. Once there are comments, you need to ensure they are maintained. Unmaintained comments cause confusion. If you need to warn a maintainer about something, make sure you document it and make it stand out, such as adding “WARNING” at the start of a comment. Sometimes a bug can be spotted and fixed more easily if the original author expresses their intention or puts a warning somewhere.

Don’t check in commented-out code.
 Delete it to improve the readability. One of the common arguments for the commented-out code is that someday the commented-out code might be needed. The truth is that it might stay there for years, unmaintained and causing confusion. Even if one day you want to uncomment it, the code block might not compile or work as expected as the base might have changed significantly. Don’t hesitate. Just delete it.

Don’t overengineer by adding might-be-useful-in-the-future code.
 If you are tasked to deliver some functionality, don’t overdo it by including additional speculative logic. Any extra code runs the risk of introducing bugs and maintenance overhead.

Avoid writing verbose code.
 Aim to write fewer lines of code to achieve a task. More lines introduce more bugs. Prototype first via brainstorming to get the task done, and then polish the code. Make sure each line has a strong reason to exist. If you are a manager or architect, don’t judge your developers by how many lines of code they deliver but by how clean and readable their code is.

Learn functional programming, if you have not already.
 One of the advantages of using features introduced in Java 8, such as lambdas and streams, is that these features can help to improve your code readability.

Adopt pair programming.
 Pair programming is a great way for a junior developer to learn from someone who is more experienced. It is also a great way to write meaningful code, as you need to explain your choices and reasoning to the other person. A great process encourages you to write code with care instead of dumping code.

Code will have fewer bugs if it is simple and readable: code that is complex is likely to have more bugs; code that is not easily understood is likely to have more bugs. Hopefully, these tips can help you to improve your skills and your code, to deliver code that is simple and readable!

---

### 中文

---

**我非常支持简单且易读的代码。** 每一行代码都应尽可能自解释，并且每一行代码都必须是必要的。要实现易读且简单的代码，涉及两个方面：格式和内容。以下是一些有助于写出易读且简单代码的建议：

### 1. **使用缩进清晰地排列代码**

- 保持一致性。如果在一个项目中工作，应该有一个代码模板，团队中的每个人都应采用相同的代码格式。
- 不要混用空格和制表符。建议在IDE中显示空格和制表符，便于检查并修正混用问题。（个人偏好：我喜欢空格。）选择空格或制表符之一，并坚持使用。

### 2. **使用有意义的变量名和方法名**

- 如果代码能自解释，维护起来更容易。使用有意义的标识符，可以让代码“自己说话”，避免需要额外的注释来解释其功能。
- 避免使用单个字母的变量名。如果变量和方法名有明确含义，通常不需要注释。

### 3. **必要时添加注释**

- 如果逻辑非常复杂，例如正则表达式查询等，使用文档来解释代码的目的。
- 注释需要保持更新，避免不维护的注释引起混淆。如果需要警告维护者某些事情，请确保在注释开始时加上“WARNING”等醒目的标记。

### 4. **不要提交注释掉的代码**

- 删除它，以提高可读性。常见的辩解是“注释掉的代码将来可能会用到”，但实际上，它可能多年未被维护并引起混乱。即便以后需要恢复，它也可能无法编译或按预期工作。
- **直接删除，而不是留下注释掉的代码。**

### 5. **避免过度设计**

- 如果任务是交付某个功能，不要因为将来可能用到某些代码而加入额外的推测性逻辑。多余的代码容易引入bug，并增加维护成本。

### 6. **避免写冗长的代码**

- 目标是用更少的代码行实现任务。更多的代码行会带来更多的bug。先通过头脑风暴构思解决方案，再优化代码。每一行代码都要有充分的理由存在。
- **不按代码行数来评价开发人员，而是看代码的简洁性和可读性。**

### 7. **学习函数式编程（如果还没有的话）**

- Java 8引入的特性如lambda表达式和stream可以帮助提升代码的可读性。

### 8. **采纳结对编程**

- 结对编程是初级开发者向更有经验的人学习的好方法。它有助于写出有意义的代码，因为你需要向另一方解释你的选择和思路。一个优秀的过程会促使你小心地编写代码，而不是随便编写。

### 9. **保持代码简单且易读**

- 简单且易读的代码通常会有更少的bug。复杂的代码往往会有更多的bug，不易理解的代码也容易出问题。希望这些建议能够帮助你提升技能和代码质量，交付简单且易读的代码！

---

## 57. Make Your Java Groovier  

### English

------

The screen was the color of a cyberpunk novel opened to the first line.
 I stared at it, worried I would never finish tonight. There was a knock on the wall of my cubicle. My boss stood there, waiting.
 “How’s it going?” she said.
 “Java is so verbose,” I sighed. “I just want to download some data from a service and save it to a database. I’m swimming in builders, factories, library code, try/catch blocks…”
 “Just add Groovy.”
 “Huh? How would that help?”
 She sat down. “Mind if I drive?”
 “Please.”
 “Let me give you a quick demo.” She opened a command prompt and typed groovyConsole. A simple GUI appeared on the screen.
 “Say you want to know how many astronauts are in space at the moment. There’s a service at Open Notify that gives you that.”
 She executed the following in the Groovy console:
 `def jsonTxt = 'http://api.opennotify.org/astros.json'.toURL().text`
 The JSON response came back with the number of astronauts, a status message, and nested objects relating each astronaut to a craft.
 “Groovy adds `toURL` to `String` to generate a `java.net.URL`, and `getText` to `URL` to retrieve the data, which you access as text.”
 “Sweet,” I said. “Now I have to map that to Java classes and use a library like Gson or Jackson—”
 “Nah. If all you want is the number of people in space, just use a JsonSlurper.”
 “A what?”
 She typed:
 `def number = new JsonSlurper().parseText(jsonTxt).number`
 “The `parseText` method returns `Object`,” she said, “but we don’t care about the type here, so just drill down.”
 It turned out there were six people in space, all aboard the International Space Station.
 “OK,” I said. “Say I want to parse the response into classes. What then? Is there a port of Gson to Groovy?”
 She shook her head. “Don’t need it. It’s all bytecodes under the hood. Just instantiate the Gson class and invoke methods as usual:

```groovy
@Canonical  
class Assignment { String name; String craft }  
@Canonical  
class Response { String message; int number; Assignment[] people }  
new Gson().fromJson(jsonTxt, Response).people.each {  
  println it  
}
```

The `@Canonical` annotation adds `toString`, `equals`, `hashCode`, a default constructor, a named argument constructor, and a tuple constructor to each class.”
 “Awesome! Now how do I save the astronauts in a database?”
 “Easy enough. Let’s use H2 for this sample:

```groovy
Sql sql = Sql.newInstance(url: 'jdbc:h2:~/astro', driver: 'org.h2.Driver')  
sql.execute '''  
  create table if not exists ASTRONAUTS(  
    id int auto_increment primary key,  
    name varchar(50),  
    craft varchar(50)  
  )  
'''  
response.people.each {  
  sql.execute "insert into ASTRONAUTS(name, craft) values ($it.name, $it.craft)"  
}  
sql.close()
```

The Groovy `Sql` class creates a table using a multiline string and inserts values using interpolated strings:

```groovy
sql.eachRow('select * from ASTRONAUTS') { row ->  
  println "${row.name.padRight(20)} aboard ${row.craft}"  
}
```

“Done,” she said, “with a formatted print and everything.”
 I stared at the result. “Do you have any idea how many lines of Java that would have been?” I asked.
 She smirked. “A lot. By the way, all exceptions in Groovy are unchecked, so you don’t even need a try/catch block. If we use `withInstance` rather than `newInstance`, the connection will close automatically too. Good enough?”
 I nodded.
 “Now just wrap the different parts into a class, and you can call it from Java.”
 She left, and I looked forward to making the rest of my Java groovier.

---

### 中文

------

🖥️ **背景设定**
 屏幕呈现出赛博朋克小说封面的色调，我望着它，担心今晚完不成任务。老板走到工位旁问我进展如何。

👨‍💻 **抱怨 Java 繁琐**
 我叹气说：“Java 太啰嗦了，我只想从服务下载数据保存到数据库，却要处理各种 builder、factory、库代码、try/catch 块……”

👩‍💼 **建议：用 Groovy 简化**
 老板说：“用 Groovy。”
 我疑惑：“这怎么能帮上忙？”
 她坐下，打开 `groovyConsole`，展示了一个简单 GUI。

🌍 **Groovy 获取太空人信息示例**
 她演示从 Open Notify 服务获取当前太空人数量：

```groovy
def jsonTxt = 'http://api.opennotify.org/astros.json'.toURL().text
```

解释道：Groovy 扩展了 String，使其可直接转为 URL 并获取文本内容。

😲 **无需复杂 JSON 映射，直接解析**
 我说：“那我还得映射成 Java 类、用 Gson 或 Jackson 吧？”
 她回答：“不用，如果你只要人数，用 `JsonSlurper` 就行”：

```groovy
def number = new JsonSlurper().parseText(jsonTxt).number
```

结果：有 6 位宇航员在国际空间站。

📦 **需要类映射也很简单**
 我问：“如果我想转为类呢？”
 她答：“直接用 Gson，Groovy 支持它”：

```groovy
@Canonical
class Assignment { String name; String craft }
@Canonical
class Response { String message; int number; Assignment[] people }

new Gson().fromJson(jsonTxt, Response).people.each {
  println it
}
```

说明：`@Canonical` 会自动生成 `toString`、`equals`、构造函数等方法。

💾 **保存到数据库也不难**
 她继续演示如何使用 H2 数据库存储宇航员信息：

```groovy
Sql sql = Sql.newInstance(url: 'jdbc:h2:~/astro', driver: 'org.h2.Driver')
sql.execute '''
  create table if not exists ASTRONAUTS(
    id int auto_increment primary key,
    name varchar(50),
    craft varchar(50)
  )
'''
response.people.each {
  sql.execute "insert into ASTRONAUTS(name, craft) values ($it.name, $it.craft)"
}
sql.close()
```

📄 **读取并格式化输出表数据**

```groovy
sql.eachRow('select * from ASTRONAUTS') { row ->
  println "${row.name.padRight(20)} aboard ${row.craft}"
}
```

她说：“完成，带格式化输出的。”

📉 **Groovy 的优势总结**
 我惊叹：“这用 Java 得写多少代码？”
 她微笑：“很多。而且 Groovy 所有异常都是非受检的，不需要 try/catch；用 `withInstance` 还能自动关闭连接。”

🏁 **结语**
 她建议我将这些逻辑封装成类，就能从 Java 调用了。她离开后，我开始期待让我的 Java 更加 groovy（酷）。

---

## 58. Minimal Constructors  

### English

------

A pattern I regularly see is significant work done in the constructor: take in a set of arguments and convert them into values for the fields. It often looks like this:

```java
public class Thing {
    private final Fixed fixed;
    private Details details;
    private NotFixed notFixed;
    // more fields

    public Thing(Fixed fixed, Dependencies dependencies, OtherStuff otherStuff) {
        this.fixed = fixed;
        setup(dependencies, otherStuff);
    }
}
```

I assume that setup initializes the remaining fields based on dependencies and otherStuff, but it’s not clear to me from the constructor signature exactly what values are necessary to create a new instance. It’s also not obvious which fields can change during the life of the object, as they cannot be made final unless they’re initialized in a constructor. Finally, this class is harder to unit test than it should be because instantiating it requires creating the right structure in the arguments to be passed to setup.

Worse, I occasionally used to see constructors like this:

```java
public class Thing {
    private Weather currentWeather;

    public Thing(String weatherServiceHost) {
        currentWeather = getWeatherFromHost(weatherServiceHost);
    }
}
```

which requires an internet connection and a service to create an instance. Thankfully, this is now rare.

All of this was done with the best of intentions to make creating instances easier by “encapsulating” behavior. I believe this approach is a legacy from C++ where programmers can use constructors and destructors creatively to control resources. It’s easier to combine classes in an inheritance hierarchy if each manages its own internal dependencies.

I prefer to use an approach inspired by my experience of Modula-3, which is that all a constructor does is assign values to fields: its only job is to create a valid instance. If there’s more work to do, I use a factory method:

```java
public class Thing {
    private final Fixed fixed;
    private final Details details;
    private NotFixed notFixed;

    public Thing(Fixed fixed, Details details, NotFixed notFixed) {
        this.fixed = fixed;
        this.details = details;
        this.notFixed = notFixed;
    }

    public static Thing forInternationalShipment(Fixed fixed, Dependencies dependencies, OtherStuff otherStuff) {
        final var intermediate = convertFrom(dependencies, otherStuff);
        return new Thing(fixed, intermediate.details(), intermediate.initialNotFixed());
    }

    public static Thing forLocalShipment(Fixed fixed, Dependencies dependencies) {
        return new Thing(fixed, localShipmentDetails(dependencies), NotFixed.DEFAULT_VALUE);
    }
}
final var internationalShipment = Thing.forInternationalShipment(fixed, dependencies, otherStuff);
final var localShipment = Thing.forLocalShipment(fixed, dependencies);
```

The advantages are that:
 I’m now very clear about the life cycle of the instance fields.
 I’ve separated code for the instantiation of an object from its use.
 The name of the factory method describes itself, unlike a constructor.
 The class and its instantiation are easier to unit test separately.

There is a disadvantage around not being able to share constructor implementation in inheritance hierarchies, but that can be addressed by making the supporting helper methods accessible and, more usefully, by taking the hint to avoid deep inheritance.

Finally, to me, this is also a reason to be careful about how to work with dependency injection frameworks. If creating an object is complicated, then putting everything in the constructor because that makes reflection-based tooling easier to use feels backward to me. One can usually register the factory method instead as a way to create new instances. Similarly, using reflection to set private fields directly for “encapsulation” (or to avoid writing a constructor) breaks the type system and makes unit testing more difficult; it’s better to set the fields through a minimal constructor. Use `@Inject` or `@Autowired` cautiously and make everything explicit.

---

### 中文

------

### 常见反模式：构造函数承担过多逻辑

我经常看到的一种模式是：**构造函数中执行了大量逻辑**，例如接收多个参数并将其转换为字段值，类似如下代码：

```java
public class Thing {
    private final Fixed fixed;
    private Details details;
    private NotFixed notFixed;

    public Thing(Fixed fixed, Dependencies dependencies, OtherStuff otherStuff) {
        this.fixed = fixed;
        setup(dependencies, otherStuff);
    }
}
```

#### 问题分析：

- 从构造函数签名**无法清楚看出**创建一个实例所需的具体参数。
- 无法知道哪些字段会在对象生命周期中改变，哪些是不可变的（因为不能设为 `final`）。
- 构造函数耦合了 `setup` 的依赖逻辑，**单元测试变得困难**。

------

### 更糟的做法：构造函数中直接访问外部服务

```java
public class Thing {
    private Weather currentWeather;

    public Thing(String weatherServiceHost) {
        currentWeather = getWeatherFromHost(weatherServiceHost);
    }
}
```

> ⚠️ 这个构造函数**依赖网络服务**，会导致创建实例必须访问外部系统（好在现在已很少见）。

------

### 背后动机

这种写法的本意是“**封装行为**”，使对象创建看起来简单。它**源自 C++ 的构造/析构习惯**，适合继承结构中每个类独立管理依赖。

------

### 更推荐的做法：构造函数只负责字段赋值，复杂逻辑交给工厂方法

受到 Modula-3 启发，作者更倾向于构造函数**只做字段赋值**，如果需要复杂构建逻辑，则用**工厂方法（factory method）**。

```java
public class Thing {
    private final Fixed fixed;
    private final Details details;
    private NotFixed notFixed;

    public Thing(Fixed fixed, Details details, NotFixed notFixed) {
        this.fixed = fixed;
        this.details = details;
        this.notFixed = notFixed;
    }

    public static Thing forInternationalShipment(Fixed fixed, Dependencies dependencies, OtherStuff otherStuff) {
        final var intermediate = convertFrom(dependencies, otherStuff);
        return new Thing(fixed, intermediate.details(), intermediate.initialNotFixed());
    }

    public static Thing forLocalShipment(Fixed fixed, Dependencies dependencies) {
        return new Thing(fixed, localShipmentDetails(dependencies), NotFixed.DEFAULT_VALUE);
    }
}
```

使用方式：

```java
final var internationalShipment = Thing.forInternationalShipment(fixed, dependencies, otherStuff);
final var localShipment = Thing.forLocalShipment(fixed, dependencies);
```

------

### 工厂方法的优势：

- ✅ 更清晰地表达了**字段生命周期**。
- ✅ **实例化逻辑**从类本身中分离出来，职责更单一。
- ✅ 工厂方法名称具备**描述性**，不需要猜测参数含义。
- ✅ 类和实例化逻辑**可以单独测试**，更易于单元测试。

------

### 潜在缺点：

- ❌ **继承结构中**不能共享构造函数实现，但可以通过公开辅助方法或**避免深继承结构**来规避。

------

### 与依赖注入（DI）框架的关系

- 构造函数过于复杂，是因为为了配合 DI 框架的**反射机制**。
- 更推荐注册工厂方法作为创建对象的方式。
- ❗ 使用反射直接设置私有字段、或用反射跳过构造函数，会**破坏类型系统，增加测试难度**。
- 建议：使用最小构造器 + `@Inject` / `@Autowired` 时应**小心使用，尽量显式**。

---

## 59. Name the Date  

### English

------

As `java.util.Date` is slowly but surely deprecated into the Sunset, with `java.time` taking up its mantle, it’s worth pausing to learn some lessons from its troubled life before letting it rest in peace.
 The most obvious lesson is that date–time handling is harder than people expect—even when they’re expecting it.
 It is a truth universally acknowledged that a single programmer in possession of the belief they understand dates and times must be in want of a code review.
 But that’s not what I want to focus on here, nor is it the importance of immutability for value types, what makes a class (un)suitable for subclassing, or how to use classes rather than integers to express a rich domain.

Source code is made up of spacing, punctuation, and names.
 All these convey meaning to the reader, but names are where most meaning is carried (or dropped). Names matter. A lot.

Given its name, it would be nice if a `Date` represented a calendar date, i.e., a specific day…but it doesn’t.
 It represents a point in time that can be viewed as having a date component.
 This is more commonly referred to as a date–time or, if you want to put it into code, a `DateTime`.
 `Time` also works, as it is the overarching concept.
 Sometimes finding the right name is hard; in this case it’s not.

Now we understand what we mean by date, date–time, and `Date`, what does `getDate()` do?
 Does it return the whole date–time value? Or perhaps just the date component?
 Neither: it returns the day of the month.
 In programming circles, this value is more commonly and specifically referred to as *day of month*, not *date*, a term normally reserved for representing a calendar date.

And while we’re here, yes, `getDay()` would have been better named `getDayOfWeek()`.
 Not only is it important to choose a name that is correct but it is important to recognize and resolve ambiguous terms such as *day* (of week, of month, of year…?).
 Note that it is better to resolve naming issues by choosing a better name rather than by Javadoc.

Names are tied to conventions, and conventions are tied to names.
 When it comes to conventions, prefer one (not many), prefer to express it clearly, and prefer one that is widely recognized and easy to use rather than one that is niche and error-prone (yeah, C, I’m looking at you).

For example, Apollo 11 landed on the moon at 20:17 on the twentieth day of July (the seventh month) in 1969 (CE, UTC, etc.).
 But if you call `getTime()`, `getDate()`, `getMonth()`, and `getYear()` expecting these numbers, expect disappointment:
 `getTime()` returns a negative number of milliseconds from the start of 1970;
 `getDate()` returns 20 (as expected, it counts from 1);
 `getMonth()` returns 6 (months count from 0);
 and `getYear()` returns 69 (years count from 1900, not 0 and not 1970).

Good naming is part of design.
 It sets expectations and communicates a model, showing how something should be understood and used.
 If you mean to tell the reader `getMillisSince1970()`, don’t say `getTime()`.
 Specific names inspire you to consider alternatives, to question whether you’re capturing the right abstraction in the right way.
 It’s not just labeling, and it’s not just `java.util.Date`: this is about the code you write and the code you use.

---

### 中文

------

### 🌇 `java.util.Date` 即将退出历史舞台，`java.time` 正式接班

在它彻底“安息”之前，我们应该从它充满问题的一生中学点教训。

> ✅ **最明显的教训**：
>  日期时间的处理**比想象的要难**——哪怕你已经预料到它很难。

人们常说：

> “一个坚信自己理解日期时间的程序员，一定急需代码审查。”

但本文重点不是讨论这些问题：

- 值类型的不变性（immutability）；
- 类是否适合继承；
- 用类替代整数表达丰富的领域概念。

------

### 🔤 名字很重要，真的很重要

源代码由**空格、标点和命名**组成。

- 这三者都有意义；
- 但**名字传递最多的含义**，也最容易传错。

------

### 😓 `Date` 名不副实

你可能以为 `Date` 表示一个**日历日期**，即某一天——但它不是。

- 它实际上表示的是一个**时间点**；
- 你可以从中“看出”一个日期部分；
- 更常见的说法应该是：`date-time` 或代码中的 `DateTime`；
- 或者干脆叫 `Time` 也行。

> 🔎 找到正确名字通常不容易，但这次很明显：`Date` 不是好名字。

------

### ❓ 那么 `getDate()` 干嘛用？

它既不返回完整的 date-time，也不返回 date 部分。

> 它返回的是：**当月的第几天（day of month）**。
>  而不是“日期”（date），后者通常表示一个**完整的日历日期**。

------

### 🤦‍♂️ `getDay()` 也该叫 `getDayOfWeek()`

- 选择**准确**的名字非常重要；
- 还要**避免歧义**，比如 “day” 是指星期几？几号？还是一年中第几天？

> ✅ 命名问题应该靠**更好的命名**解决，**不是靠 Javadoc 来补锅**。

------

### 📏 命名与规范密切相关

- 命名依赖规范，规范也依赖命名；
- 遇到多个选择时：
   **选一个最清晰、通用、易懂、少出错的**；
   **不要选冷门、易错、让人抓狂的那种（比如 C 语言的风格）**。

------

### 🌕 例子：阿波罗 11 号着陆

Apollo 11 于 1969 年 7 月 20 日 20:17（UTC）登月。

如果你希望用 `getTime()`、`getDate()`、`getMonth()`、`getYear()` 拿到这些数字，准备好失望吧：

- `getTime()` 返回从 1970 年开始起的**负毫秒数**；
- `getDate()` 返回 20（这个没问题，**从 1 开始计数**）；
- `getMonth()` 返回 6（注意，**从 0 开始计数**）；
- `getYear()` 返回 69（代表 1969，**从 1900 开始计数**）。

------

### 🎯 好的命名是设计的一部分

- 它**设定期望**，**传达模型**；
- 它告诉使用者一个方法应该怎么理解、怎么用。

如果你是想表达 `getMillisSince1970()`，那就**别叫 `getTime()`**！

> ✅ **具体的名字**能帮你思考更好的抽象，找出更合理的设计。
>  🧠 这不只是标签问题，也不只是 `java.util.Date` 的问题——
>  它关乎你写的每一行代码，也关乎你用的每一个类库。

---

## 60. The Necessity of Industrial-Strength Technologies  

### English

---

Java may have been called the next COBOL, but that’s not necessarily a bad thing.
 COBOL has been an incredibly successful technology. Reliable, consistent, and easy to read, it has been the workhorse of the Information Age, managing the bulk of the world’s mission-critical systems.
 If the syntax requires lots of extra typing, that is offset by the sheer number of readers that have had to ponder its behavior.

Trendy software stacks sound cool—and, as most are quite immature, there is always plenty to learn—but the world needs reliable industrial-strength software to function.
 A new clever idiom or slightly obfuscated paradigm can be great fun to play with, but by definition they are shrouded in unknowns.
 We’re obsessed with finding some magical way to just snap our fingers and will the next enterprise-class system into existence, but we keep forgetting that over three decades ago Frederick Brooks Jr. said those kinds of magic bullets—silver or otherwise—just can’t exist.

We don’t need the next trendy toy to solve real problems for people.
 We need to put in the thinking and the work to fully understand and codify reliable solutions.
 Systems that only work on sunny days, or that need to be rewritten every year or so, don’t satisfy our growing needs for managing the complexities of modern society.
 It doesn’t matter how it works if it is unpredictable when it fails.
 Instead, we have to fully encapsulate our knowledge into reliable, reusable, recomposable components, leveraging them for as long as possible to keep up with the chaotic nature of our current period in history.
 If the code doesn’t last, it probably wasn’t worth writing.

Java is a great technology for this purpose: new enough to contain modern language features, but mature enough to be trustworthy.
 We’ve gotten better at organizing large codebases well, and there is a great enough wealth of supporting products, tools, and ecosystems to shift the focus back to real business problems and away from the purely technical ones.
 It’s a strong stack for decoupling the systems from their environments, yet standard enough to find experienced staff.
 If it isn’t the talk of the town, it is at least a very reliable, stable platform on which to build systems that last for decades, and that, it seems, is what we both want and need for our current development efforts.

Fashion should not dictate engineering.
 Software development is a discipline of knowledge and organization.
 If you don’t know how the parts will behave, you can’t ensure that the whole will behave.
 If the solution is unreliable, then it really just adds to the problem rather than solving it.
 It may be fun just to toss together some code that kind of works, but it is only professional if we build stuff that can withstand reality and keep humming along.

---

### 中文

---

Java 可能被称为下一个 COBOL，但这并不一定是坏事。

COBOL 是一种非常成功的技术。它可靠、一致、易于阅读，是信息时代的主力军，管理着全球大多数关键任务系统。如果语法需要大量额外的输入，这可以通过大量读者的经验来弥补，他们已经思考过其行为。

流行的软件堆栈听起来很酷——而且大多数都相当不成熟，总有很多需要学习的东西——但世界需要可靠的工业级软件来运行。新的巧妙表达式或稍微复杂的范式可以很好玩，但按定义它们充满了未知。我们总是痴迷于找到某种神奇的方法，希望轻松创造下一个企业级系统，但我们总是忘记了，三十多年前，Frederick Brooks Jr. 就说过，那些所谓的“魔法子弹”——无论是银色的还是其他——根本不存在。

我们不需要下一个流行玩具来解决实际问题。我们需要付出思考和努力，充分理解并编写可靠的解决方案。那些只能在晴天工作，或者每年都需要重写的系统，不能满足我们日益增长的管理现代社会复杂性的需求。如果一个系统无法预测它何时会失败，那么它的工作原理就不重要了。相反，我们必须将知识完全封装成可靠、可重用、可重组的组件，尽可能长时间地利用它们，以应对当前历史时期的混乱局面。如果代码不能长久，它可能就不值得编写。

Java 是一种非常适合此目的的技术：足够现代化，包含现代语言特性，又足够成熟，值得信赖。我们在组织大型代码库方面变得更好，支持的产品、工具和生态系统也足够丰富，可以将重点从纯技术问题转移到真正的业务问题上。它是一个强大的堆栈，可以将系统与环境解耦，同时足够标准化，便于找到有经验的员工。如果它不是街头巷尾的热议话题，至少它是一个非常可靠、稳定的平台，可以构建几十年都能使用的系统，这正是我们当前开发工作所需的。

时尚不应主导工程。软件开发是一门知识和组织的学科。如果你不知道各个部分如何运作，你就无法保证整体运作正常。如果解决方案不可靠，它反而只会加剧问题，而不是解决问题。随便拼凑一些代码，看起来好像能工作，可能很有趣，但只有当我们构建能经得起现实考验并持续运行的系统时，这才算专业。

---

## 61. Only Build the Parts That Change and Reuse the Rest  

### English

---

As Java programmers, we spend a lot of time waiting for builds to run, often because we don’t run them efficiently. We can make small improvements by changing our behavior. For example, we could only run a submodule instead of the entire project, and not run clean before every build. To make a bigger difference, we should take advantage of the build caching offered by our build tools, namely Gradle, Maven, and Bazel.

Build caching is the reuse of results from a previous run to minimize the number of build steps (e.g., Gradle tasks, Maven goals, Bazel actions) executed during the current run. Any build step that is idempotent, meaning that it produces the same output for a given set of inputs, can be cached.

The output of Java compilation, for example, is the tree of class files generated by the Java compiler, and the inputs are factors that impact the produced class files, such as the source code itself, Java version, operating system, and any compiler flags. Given the same run conditions and source code, the Java compilation step produces the same class files every time. So instead of running the compilation step, the build tool can look in the cache for any previous runs with the same inputs and reuse the output.

Build caching isn’t limited to compilation. Build tools define standard inputs and outputs for other common build steps, like static analysis and documentation generation, and also allow us to configure the inputs and outputs for any cacheable build step.

This type of caching is especially useful for multimodule builds. In a project with 4 modules, each of which has 5 build steps, a clean build must execute 20 steps. Most of the time, though, we are only modifying the source code in one module. If no other projects depend on that module, then that means we only need to execute the steps downstream from source code generation; in this example, only 4: the outputs of the other 16 steps can be pulled from the cache, saving time and resources.

Gradle’s incremental build, which we see as UP-TO-DATE in the build output, implements build caching at the project level. A local cache, like the one built into Gradle and available as an extension to Maven, works even when changing workspaces, Git branches, and command-line options.

The collaborative effect of remote build caching available in Gradle, Maven, and Bazel adds additional benefits. One of the common use cases for remote caching is the first build after pulling from a remote version control repository. After we pull from the remote, we have to build the project on our machine to take advantage of those changes. But since we have never built those changes on our machine, they aren’t in our local cache yet. However, the continuous integration system has already built those changes and uploaded the results to the shared remote cache so we get a cache hit from the remote cache, saving the time required to execute those build steps locally.

By using build caching in our Java builds, we can share the results across our local builds, the agents of the CI server, and the entire team, resulting in faster builds for everyone and fewer resources computing the same operations over and over again.

---

### 中文

---

作为 Java 程序员，我们花费了大量时间等待构建完成，通常是因为我们没有高效地运行它们。我们可以通过改变行为来做出一些小改进。例如，我们可以只运行子模块而不是整个项目，并且不在每次构建前都运行 clean。为了产生更大的效果，我们应该利用构建工具（如 Gradle、Maven 和 Bazel）提供的构建缓存。

**构建缓存**是重用先前运行的结果，以最小化当前运行时执行的构建步骤（例如 Gradle 任务、Maven 目标、Bazel 操作）的数量。任何具有幂等性的构建步骤（即对于一组给定的输入，它始终产生相同的输出）都可以被缓存。

以 Java 编译为例，输出是由 Java 编译器生成的类文件树，输入则是影响生成的类文件的因素，如源代码、Java 版本、操作系统以及任何编译器标志。在相同的运行条件和源代码下，Java 编译步骤每次都产生相同的类文件。因此，构建工具可以查找缓存中是否有之前相同输入的运行结果，并重用输出，而不是重新执行编译步骤。

构建缓存不仅限于编译。构建工具为其他常见的构建步骤（如静态分析和文档生成）定义了标准的输入和输出，并允许我们为任何可缓存的构建步骤配置输入和输出。

这种类型的缓存对于多模块构建尤其有用。在一个包含 4 个模块的项目中，每个模块有 5 个构建步骤，清理构建必须执行 20 个步骤。然而，大多数时候，我们只是修改了一个模块中的源代码。如果没有其他项目依赖于该模块，那就意味着我们只需要执行源代码生成后的步骤；在这个例子中，只需执行 4 步，其他 16 步的输出可以从缓存中提取，从而节省时间和资源。

Gradle 的增量构建（在构建输出中显示为 UP-TO-DATE）在项目级别实现了构建缓存。像 Gradle 内置的本地缓存以及作为 Maven 扩展的缓存，即使在更改工作空间、Git 分支和命令行选项时也能正常工作。

Gradle、Maven 和 Bazel 中的远程构建缓存协作效果提供了额外的好处。远程缓存的常见使用案例之一是在从远程版本控制仓库拉取代码后进行的第一次构建。拉取远程代码后，我们必须在本地构建项目以利用这些变化。但是，由于我们之前从未在本地构建过这些变化，它们还不在我们的本地缓存中。然而，持续集成系统已经构建了这些变化并将结果上传到共享的远程缓存中，因此我们可以从远程缓存中获取缓存命中，从而节省执行这些构建步骤的时间。

通过在我们的 Java 构建中使用构建缓存，我们可以在本地构建、CI 服务器代理和整个团队之间共享构建结果，从而实现更快速的构建，并减少资源重复计算相同的操作。

---

## 62. Open Source Projects Aren’t Magic  

### English

---

One of my biggest pet peeves is hearing people say that X technology, language, build tool, etc., works by magic. If that project is open source, then what I hear is “I’m too lazy to look up how it works,” and I’m reminded of Clarke’s Third Law that “any sufficiently advanced technology is indistinguishable from magic.”

In the days of the modern web, it is easier than ever before to look up the reference guides and source code and find out how that technology works. Many open source projects like the Apache Groovy programming language, for example, have a website (in this case, groovy-lang.org) that lists where you can find the documentation, reference guides, bug tracker, and even links to the source code itself.

If you’re looking for help getting started, guides and tutorials are a great place to begin. If you are more of a visual or hands-on learner, many online learning platforms offer introductory courses for learning new languages through labs, exercises, and group work. Sometimes these are even freely available so that the technologies will be more widely known.

After learning the basic syntax and data structures and starting to use them in your own projects, you’ll likely start encountering unexpected behaviors or even bugs. No matter which ecosystem you choose, this will happen at some point. It’s just a part of the world we live in. You should first look for an issue tracker like Jira or GitHub issues to see if others are having the same problem. If so, there may be workarounds, a fix in a newer version, or a timeline for when this issue will be fixed.

It may take a little work to find out where your technology’s community collaborates. Sometimes it is in chat rooms, forums, or mailing lists. Projects in the Apache foundation, in particular, tend to use Apache infrastructure rather than commercial products. Finding this place is the best way to move from “magic” to clarity.

Even after you master a particular technology, learning is a continuous process and you’ll need to keep doing it. New releases may add new features or change behaviors in ways you will need to understand. Join the mailing list or attend conferences with the open source committers to learn what you need for upgrading your projects. If you are already a subject matter expert, this is a great way you can also contribute to uncovering the “magic” for everyone else.

Lastly, if you find something is unclear or missing, many projects are happy to accept contributions, especially to documentation. The project leads are often people with regular day jobs and other priorities, so they may not respond right away, but this is the best way to help everyone succeed and to uncover the “magic” for the next generation of users.

---

### 中文

---

我最讨厌的一件事就是听到有人说某项技术、语言、构建工具等是“靠魔法”工作的。如果项目是开源的，那么我听到的意思是“我懒得查它是怎么工作的”，让我想起了克拉克的第三定律：“任何足够先进的技术都无法与魔法区分。”

### 1. 现代网络时代让了解技术变得更容易

- 如今，查找参考指南和源代码，比以往任何时候都容易，很多开源项目提供了完整的文档、参考指南、错误跟踪器等。例如，Apache Groovy编程语言的官网（groovy-lang.org）列出了文档和源代码等资源。

### 2. 入门学习的资源

- **指南和教程**：适合入门的好方式。
- **在线平台**：许多平台提供通过实验室、练习和小组作业学习新语言的课程，部分课程免费开放，帮助技术普及。

### 3. 遇到意外行为或Bug时的解决方法

- 学习基本语法和数据结构后，开始在项目中使用时，可能会遇到意外行为或Bug，这是任何技术生态系统都会遇到的。
- **查找问题跟踪器**（如Jira或GitHub issues）：查看是否有人遇到同样的问题，是否有解决方法、版本修复或修复时间表。

### 4. 加入社区，避免“魔法”

- **技术社区**：查找你的技术社区合作平台，如聊天室、论坛或邮件列表。特别是Apache基金会的项目更倾向于使用Apache的基础设施，而非商业产品。
- **从“魔法”到清晰**：加入这些平台，能帮助你理解技术并解决问题。

### 5. 学习是一个持续的过程

- 即使你掌握了某项技术，学习依然是一个持续的过程。
- **新版本发布**：新版本可能会添加新特性或改变行为，需要理解其变化。
- 加入邮件列表或参加开源会议，了解如何升级你的项目。

### 6. 做出贡献，帮助更多人

- 如果你发现某些内容不清楚或缺失，很多项目欢迎贡献，尤其是文档方面。
- 项目负责人通常有其他工作和优先事务，可能不会立即回应，但这是一种帮助大家成功，并帮助下一个用户了解技术的好方式。

---

## 63. Optional Is a Lawbreaking Monad but a Good Type  

### English

---

In most programming languages, empty-or-not-empty types are well-behaved monads. (Yes, I used the M-word—don’t worry, no math.) This means their mechanics fulfill a couple of definitions and follow a number of laws that guarantee safe (de)composition of computations. Optional’s methods fulfill these definitions but break the laws. Not without consequences...

### Monad Definition

You need three things to define a monad—in Optional’s terms:

1. The type `Optional<T>` itself
2. The method `ofNullable(T)` that wraps a value `T` into an `Optional<T>`
3. The method `flatMap(Function<T, Optional<U>>)`, that applies the given function to the value that is wrapped by the `Optional` on which it is called

There’s an alternative definition using `map` instead of `flatMap`, but it’s too long to fit here.

### Monad Laws

Now it gets interesting—a monad has to fulfill three laws to be one of the cool kids. In Optional’s terms:

1. For a `Function<T, Optional<U>> f` and a value `v`,
    `f.apply(v)` must equal `Optional.ofNullable(v).flatMap(f)`. This left identity guarantees it doesn’t matter whether you apply a function directly or let Optional do it.
2. Calling `flatMap(Optional::ofNullable)` returns an `Optional` that equals the one you called it on. This right identity guarantees applying no-ops doesn’t change anything.
3. For an `Optional<T> o` and two functions `Function<T, Optional<U>> f` and `Function<U, Optional<V>> g`, the results of `o.flatMap(f).flatMap(g)` and `o.flatMap(v -> f.apply(v).flatMap(g))` must be equal. This associativity guarantees that it doesn’t matter whether functions are flat-mapped individually or as a composition.

### The Issue with Optional

While Optional holds up in most cases, it doesn’t for a specific edge case. Have a look at `flatMap`’s implementation:

```java
public <U> Optional<U> flatMap(Function<T, Optional<U>> f) {
    if (!isPresent()) {
        return empty();
    } else {
        return f.apply(this.value);
    }
}
```

You can see that it doesn’t apply the function to an empty `Optional`, which makes it easy to break left identity:

```java
Function<Integer, Optional<String>> f = i -> Optional.of(i == null ? "NaN" : i.toString());
// the following are not equal
Optional<String> containsNaN = f.apply(null);
Optional<String> isEmpty = Optional.ofNullable(null).flatMap(f);
```

That’s not great, but it’s even worse for `map`. Here, associativity means that given an `Optional<T> o` and two functions `Function<T, U> f` and `Function<U, V> g`, the results of `o.map(f).map(g)` and `o.map(f.andThen(g))` must be equal:

```java
Function<Integer, Integer> f = i -> i == 0 ? null : i;
Function<Integer, String> g = i -> i == null ? "NaN" : i.toString();
// the following are not equal
Optional<String> containsNaN = Optional.of(0).map(f.andThen(g));
Optional<String> isEmpty = Optional.of(0).map(f).map(g);
```

### So What?

The examples may seem contrived and the importance of the laws unclear, but the impact is real: in an `Optional` chain, you can’t mechanically merge and split operations because that may change the code’s behavior. That is unfortunate because proper monads let you ignore them when you want to focus on readability or domain logic.

But why is Optional a broken monad? Because null-safety is more important! To uphold the laws, an `Optional` would have to be able to contain null while being nonempty. And it would have to pass it to functions given to `map` and `flatMap`. Imagine if everything you did in `map` and `flatMap` had to check for null! That `Optional` would be a great monad, but provide zero null-safety.

No, I’m happy we got the `Optional` that we got.

---

### 中文

---

在大多数编程语言中，空或非空类型是表现良好的单子（monad）。(是的，我用了 M 这个词——别担心，跟数学无关。) 这意味着它们的机制满足一些定义，并遵循一些法则，保证了计算的安全（解）组合。Optional 的方法满足这些定义，但违反了这些法则，后果不小……

### 单子的定义

要定义一个单子，在 Optional 的术语中，你需要三个元素：

1. 类型 `Optional<T>` 本身
2. 方法 `ofNullable(T)`，将值 `T` 包装成 `Optional<T>`
3. 方法 `flatMap(Function<T, Optional<U>>)`，将给定的函数应用于 `Optional` 包装的值

还有一个使用 `map` 而不是 `flatMap` 的替代定义，但它太长，无法在这里展示。

### 单子法则

现在有趣的来了——一个单子必须满足三条法则，才能成为“酷孩子”。在 Optional 的术语中：

1. 对于 `Function<T, Optional<U>> f` 和一个值 `v`，
    `f.apply(v)` 必须等于 `Optional.ofNullable(v).flatMap(f)`。这个左身份法则保证了无论是直接应用函数，还是让 Optional 来做，结果都一样。
2. 调用 `flatMap(Optional::ofNullable)` 会返回一个与调用它的 `Optional` 相等的 `Optional`。这个右身份法则保证了应用无操作不会改变任何东西。
3. 对于 `Optional<T> o` 和两个函数 `Function<T, Optional<U>> f` 和 `Function<U, Optional<V>> g`，
    `o.flatMap(f).flatMap(g)` 和 `o.flatMap(v -> f.apply(v).flatMap(g))` 的结果必须相等。这个结合律保证了无论是单独执行 flatMap 还是作为组合执行，结果都一样。

### Optional 的问题

虽然 Optional 在大多数情况下表现良好，但在某些特定的边界情况下并不符合要求。看看 `flatMap` 的实现：

```java
public <U> Optional<U> flatMap(Function<T, Optional<U>> f) {
    if (!isPresent()) {
        return empty();
    } else {
        return f.apply(this.value);
    }
}
```

你可以看到，当 `Optional` 为空时，它不会应用函数，这很容易破坏左身份法则：

```java
Function<Integer, Optional<String>> f = i -> Optional.of(i == null ? "NaN" : i.toString());
// 以下两者不相等
Optional<String> containsNaN = f.apply(null);
Optional<String> isEmpty = Optional.ofNullable(null).flatMap(f);
```

这还算不算糟糕，更糟糕的是 `map`。这里，结合律要求对于 `Optional<T> o` 和两个函数 `Function<T, U> f` 和 `Function<U, V> g`，
 `o.map(f).map(g)` 和 `o.map(f.andThen(g))` 的结果必须相等：

```java
Function<Integer, Integer> f = i -> i == 0 ? null : i;
Function<Integer, String> g = i -> i == null ? "NaN" : i.toString();
// 以下两者不相等
Optional<String> containsNaN = Optional.of(0).map(f.andThen(g));
Optional<String> isEmpty = Optional.of(0).map(f).map(g);
```

### 那又怎样？

这些例子看起来可能有些牵强，法则的重要性也不太明显，但影响是实际存在的：在一个 `Optional` 链中，你不能机械地合并和拆分操作，因为这可能改变代码的行为。很不幸，因为合适的单子可以让你忽略这些法则，当你专注于可读性或领域逻辑时。

那么为什么说 Optional 是一个“破碎”的单子呢？因为 null 安全更重要！为了遵守这些法则，一个 `Optional` 必须能够在非空的情况下包含 null。并且它必须将 null 传递给 `map` 和 `flatMap` 中给定的函数。想象一下，如果在 `map` 和 `flatMap` 中的每个操作都必须检查 null！那样的 `Optional` 会是一个很好的单子，但完全没有 null 安全。

不，我很高兴我们得到了现在这个 `Optional`。

---

## 64. Package-by-Feature with the Default Access Modifier  

### English

---

A lot of business applications are written using a three-tier architecture: view, business, and data layers, and all model objects are used by all three layers. In some codebases, the classes for these applications are organized by layer. In some applications, which have the need to register various users and the company they work for, the code structure would result in something like:

```
tld.domain.project.model.Company  
tld.domain.project.model.User  
tld.domain.project.controllers.CompanyController  
tld.domain.project.controllers.UserController  
tld.domain.project.storage.CompanyRepository  
tld.domain.project.storage.UserRepository  
tld.domain.project.service.CompanyService  
tld.domain.project.service.UserService  
```

Using such a package-by-layer structure for your classes requires a lot of methods to be public. The `UserService` needs to be able to read and write Users into storage, and since the `UserRepository` is in another package, almost all methods of the `UserRepository` would need to be public.

The organization might have a policy to send an email to a user to notify them when their password has been changed. Such a policy might be implemented in the `UserService`. Since the methods in the `UserRepository` are public, there is no protection against another part of the application invoking a method in `UserRepository`, which changes the password but does not trigger the notification to be sent.

When this application is updated to include some customer-care module or a web-care interface, some of the features in those modules might want to reset the password. Since these features are built at a later point in time, perhaps after new developers have joined the team, these developers might be tempted to access the `UserRepository` directly from a `CustomerCareService` instead of calling the `UserService` and triggering the notification.

The Java language provides a mechanism to prevent this: access modifiers.

The default access modifier means we do not explicitly declare an access modifier for a class, field, method, etc. A variable or method declared without any access control modifier is available only to other classes in the same package. This is also called package-private.

In order to benefit from that access protection mechanism, the codebase should be organized into a package-by-feature package hierarchy. The same classes as before would be packaged like this:

```
tld.domain.project.company.Company  
tld.domain.project.company.CompanyController  
tld.domain.project.company.CompanyService  
tld.domain.project.company.CompanyRepository  
tld.domain.project.user.User  
tld.domain.project.user.UserController  
tld.domain.project.user.UserService  
tld.domain.project.user.UserRepository  
```

When organized like this, none of the methods in the `UserRepository` would have to be public. They all could be package-private and still be available to the `UserService`. The methods of the `UserService` could be made public.

Any developer building the `CustomerCareService`, in the package `tld.domain.project.support`, would not be able to invoke methods on the `UserRepository` and should call the methods of the `UserService`. This way the code structure and the access modifiers help to ensure that the application still adheres to the policy to send the notification.

This strategy for organizing the classes in your codebase will help reduce the coupling in your codebase.

---

### 中文

---

许多业务应用程序采用三层架构：视图层、业务层和数据层，所有模型对象都被三个层使用。在一些代码库中，应用程序的类按层次组织。对于一些需要注册各种用户及其所在公司的应用程序，代码结构可能如下所示：

```
tld.domain.project.model.Company  
tld.domain.project.model.User  
tld.domain.project.controllers.CompanyController  
tld.domain.project.controllers.UserController  
tld.domain.project.storage.CompanyRepository  
tld.domain.project.storage.UserRepository  
tld.domain.project.service.CompanyService  
tld.domain.project.service.UserService  
```

使用这种按层组织类的结构需要很多方法是公开的。`UserService` 需要能够读取和写入用户数据到存储中，由于 `UserRepository` 在另一个包中，几乎所有 `UserRepository` 的方法都需要是公开的。

该组织可能有一个政策，在密码更改时发送电子邮件通知用户。这种政策可能会在 `UserService` 中实现。由于 `UserRepository` 中的方法是公开的，应用程序的其他部分可以直接调用 `UserRepository` 中的方法，修改密码但不触发发送通知的操作。

当该应用程序更新以包含一些客户关怀模块或网页接口时，这些模块中的一些功能可能需要重置密码。由于这些功能可能是在之后加入的，可能是新开发者加入团队后，这些开发者可能会直接从 `CustomerCareService` 访问 `UserRepository`，而不是调用 `UserService` 并触发通知。

Java 提供了防止这种情况发生的机制：访问修饰符。

默认的访问修饰符意味着我们没有显式声明类、字段、方法等的访问修饰符。没有访问控制修饰符声明的变量或方法仅对同一个包中的其他类可用，这也称为包私有（package-private）。

为了利用这个访问保护机制，代码库应该按功能组织成一个包结构。与之前相同的类会以以下方式组织：

```
tld.domain.project.company.Company  
tld.domain.project.company.CompanyController  
tld.domain.project.company.CompanyService  
tld.domain.project.company.CompanyRepository  
tld.domain.project.user.User  
tld.domain.project.user.UserController  
tld.domain.project.user.UserService  
tld.domain.project.user.UserRepository  
```

以这种方式组织时，`UserRepository` 中的方法都不需要是公开的，它们都可以是包私有的，仍然可以被 `UserService` 访问。`UserService` 的方法可以是公开的。

任何开发 `CustomerCareService` 的开发者，在 `tld.domain.project.support` 包中，都无法直接调用 `UserRepository` 中的方法，应该调用 `UserService` 的方法。通过这种方式，代码结构和访问修饰符确保了应用程序仍然遵守发送通知的政策。

这种按功能组织类的策略将有助于减少代码库中的耦合。

---

## 65. Production Is the Happiest Place on Earth  

### English

---

Production is my first favorite place on the internet. I love production. You should love production. Go as early and often as possible. Bring the kids. Bring the family. The weather is amazing. It’s the happiest place on Earth. It’s better than Disneyland!
 Getting there isn’t always easy, but trust me: once you get there, you’re going to want to stay. It’s like Mauritius. You’ll love it! Here are some tips to make your journey as pleasant as possible:

**Take the continuous delivery highway.**
 There’s no faster way to production. Continuous delivery lets you move quickly and consistently from the latest Git commit to production. In a continuous delivery pipeline, code moves automatically from developer to deployment, and every step in between, in one smooth motion. Continuous integration tools like Travis CI or Jenkins help, but try to mine information gleaned while in production. Canary releases are a technique to reduce the risk of introducing a new software version in production by slowly rolling out the change to a small cross-section of users. Continuous delivery tools like Netflix’s Spinnaker can automate this sort of nuanced deployment strategy.

**Production can be surprising.**
 Be prepared! Services will fail. Don’t leave your clients in the lurch. Specify aggressive client-side timeouts. Service-level agreements (SLAs) dominate a lot of technical discussions. Use service-hedging—a pattern in which multiple idempotent calls to identically configured service instances on discrete nodes are launched and all but the fastest response discarded—to meet SLAs. Failures will happen. Use circuit breakers to explicitly define failure modes and isolate failures. Spring Cloud has an abstraction, Spring Cloud Circuit Breaker, that supports reactive and nonreactive circuits.

**In production, nobody can hear your application scream.**
 Embrace observability from the get-go. Production is a busy place! If everything goes well, you’ll have more users and demand than you’ll know what to do with. As demand increases, scale. Cloud infrastructure like Cloud Foundry, Heroku, and Kubernetes have long supported horizontal scale-out by fronting an ensemble of nodes with a load balancer. This is particularly easy if you’re building stateless, 12-Factor-style microservices. This strategy works even if your application monopolizes otherwise precious resources like threads.

**Your code shouldn’t monopolize threads.**
 Threads are super expensive. The best solutions to this problem—cooperative multithreading—are about giving signals to the runtime about when it can move work on and off a finite set of actual, operating-system threads. Learn about things like reactive programming as supported by Project Reactor (fairly common on the server side) and Spring Webflux and RxJava (fairly common on Android). If you understand how reactive programming works, it’s a natural next step to embrace things like Kotlin’s coroutines. Cooperative multithreading lets you multiply the number of users supported or divide infrastructure costs.

**Autonomy is a key to success.**
 Microservices enable small, singly-focused teams, able to release software to production autonomously.

**Ninety percent of your application is mundane.**
 Embrace frameworks like Spring Boot to let you focus on the bottom-line production deliverables, and not on supporting code. Is the Java programming language not your cup of tea—er—coffee? The JVM ecosystem is rich with productive alternatives like Kotlin. Remove the friction of going to production. Eschew what Amazon CTO Werner Vogels calls “undifferentiated heavy lifting.” Clear the path to production and people will want to go early and often. They’ll yearn for what has been called Antoine de Saint-Exupéry’s “vast and endless seas.”

---

### 中文

---

**生产环境是我最喜欢的互联网地方。我热爱生产环境。你也应该热爱生产环境。尽早尽可能多地去生产环境吧。带上孩子，带上家人，天气非常好，这是地球上最幸福的地方，甚至比迪士尼乐园还要好！**

到达生产环境并不总是容易的，但相信我，一旦你到达那里，你就会想要停留。它就像毛里求斯，你会喜欢的！以下是一些建议，帮助你让旅程尽可能愉快：

### **走上持续交付的高速公路**

没有比这更快的方式了。持续交付让你从最新的Git提交到生产环境的过渡快速且一致。在持续交付流水线中，代码自动从开发者到部署，并且每个环节顺畅衔接。像Travis CI或Jenkins这样的持续集成工具可以提供帮助，但也要试着从生产环境中提取信息。金丝雀发布是一种技术，通过慢慢将新版本的软件发布给一小部分用户，以减少在生产环境中引入新版本的风险。像Netflix的Spinnaker这样的持续交付工具可以自动化这种复杂的部署策略。

### **生产环境可能会让你吃惊**

要做好准备！服务会失败，不要让你的客户陷入困境。指定激进的客户端超时。服务级别协议（SLA）在很多技术讨论中占据主导地位。使用服务对冲——一种模式，其中会向不同节点上的服务实例发出多个幂等调用，所有响应中除了最快的都被丢弃——以满足SLA。失败是难免的。使用断路器显式定义失败模式并隔离故障。Spring Cloud提供了Spring Cloud Circuit Breaker的抽象，支持反应式和非反应式电路。

### **在生产环境中，没人能听到你的应用叫喊**

从一开始就要拥抱可观察性。生产环境是一个非常繁忙的地方！如果一切顺利，你将拥有比你能应对的更多的用户和需求。随着需求的增加，进行扩展。像Cloud Foundry、Heroku和Kubernetes这样的云基础设施长期支持通过负载均衡器将一组节点组合进行水平扩展。如果你构建的是无状态的12因素风格的微服务，这尤其容易。即使你的应用程序独占了其他宝贵资源（如线程），这个策略也能奏效。

### **你的代码不应当独占线程**

线程非常昂贵。解决这一问题的最佳方案是——协作式多线程——它通过向运行时发出信号，告诉运行时何时可以在有限的操作系统线程上移动工作。了解像Project Reactor（在服务器端非常常见）、Spring Webflux和RxJava（在Android上很常见）等支持的反应式编程。如果你理解反应式编程的原理，那么拥抱像Kotlin协程这样的技术是自然而然的下一步。协作式多线程让你可以支持更多的用户，或者降低基础设施成本。

### **自治是成功的关键**

微服务使小型、专注的团队能够独立地将软件发布到生产环境中。

### **你的应用程序90%的部分是平凡的**

拥抱像Spring Boot这样的框架，让你能够专注于生产环境的交付，而不是支撑代码。如果Java编程语言不是你的“茶”——呃——“咖啡”？JVM生态系统中有丰富的生产力替代品，比如Kotlin。消除进入生产环境的摩擦。摒弃Amazon首席技术官Werner Vogels所说的“没有差异化的重型工作”。清除进入生产环境的障碍，人们会愿意尽早并频繁地进入生产环境。他们会渴望去追求被称为安托万·德·圣-埃克苏佩里的“浩瀚无垠的海洋”。

---

## 66. Program with GUTs  

### English

---

So you’re writing unit tests? Great! Are they any good? To borrow a term from Alistair Cockburn, do you have **GUTs**? Good unit tests? Or have you landed someone (future you?) with interest-accumulating technical debt in their testbase?

What do I mean by good? Good question. Hard question. Worth an answer.

Let’s start with names. Reflect what is being tested in the name. Yup, you don’t want `test1`, `test2`, and `test3` as your naming scheme. In fact, you don’t want **test** in your test names: `@Test` already does that. Tell the reader what you’re testing, not that you’re testing.

Ah, no, I don’t mean name it after the method under test: tell the reader what behavior, property, capability, etc. is under test. If you’ve got a method **addItem**, you don’t want a corresponding **addItemIsOK** test. That’s a common test smell. Identify the cases of behavior, and test only one case per test case. Oh, and no, that doesn’t mean **addItemSuccess** and **addItemFailure**.

Let me ask you, what’s the purpose of your test? To test that “it works”? That’s only half the story. The biggest challenge in code is not to determine whether “it works,” but to determine what “it works” means. You have the chance to capture that meaning, so try **additionOfItemWithUniqueKeyIsRetained** and **additionOfItemWithExistingKeyFails**.

Because these names are long, and also aren’t production code, consider using underscores to improve readability—camel case doesn’t scale—so
 **Addition_of_item_with_unique_key_is_retained**.

With **JUnit 5** you can use **DisplayNameGenerator.ReplaceUnderscores** with `@DisplayName` generation to pretty-print as “Addition of item with unique key is retained.” You can see that naming as a proposition has a nice property: if the test passes, you have some confidence the proposition might be true; if it fails, the proposition is false.

Which is a good point. Passing tests don’t guarantee that the code works. But, for a unit test to be good, the meaning of failure should be clear: it should mean the code doesn’t work. Like Dijkstra said, “Program testing can be used to show the presence of bugs, but never to show their absence!”

In practice, this means a unit test shouldn’t depend on things that can’t be controlled within the test. **Filesystem**? **Network**? **Database**? **Asynchronous ordering**? You may have influence, but not control. The unit under test shouldn’t depend on things that could cause failure when the code is correct.

Also, watch out for overfitting tests. You know the ones: brittle assertions on implementation details rather than required features. You update something—spelling, a magic value, a quality outcome—and tests fail. They fail because the tests were at fault, not the production code.

Oh, and keep your eyes open for underfitting tests too. They’re vague, passing at the drop of a hat, even with code that’s wildly and obviously wrong. You successfully add your first item. Don’t just test the number of items is greater than zero. There’s only one right outcome: one item. Many integers are greater than zero; billions are wrong.

Speaking of outcome, you may find many tests follow a simple three-act play: **arrange–act–assert**, aka **given–when–then**. Keeping this in mind helps you focus on the story that the test is trying to tell. Keeps it cohesive, suggests other tests, and helps with the name.

Oh, and as we’re back on names, you may find names get repetitive. Factor out the repetition. Use it to group tests into inner classes with `@Nested`. So, you could nest **with_unique_key_is_retained** and **with_existing_key_fails** inside **Addition_of_item**.

I hope that’s been useful. You’re off to revisit some tests? OK, catch you later.

---

### 中文

---

你在写单元测试吗？太棒了！它们好用吗？借用 Alistair Cockburn 的术语，你有 **GUTs** 吗？好的单元测试？还是你为别人（未来的你？）积累了技术债务，造成了测试库的负担？

那么，什么是好的单元测试呢？这是个好问题，虽然有点难，但值得回答。

### 1. 命名

- 命名应该反映被测试的内容。不要使用 `test1`、`test2` 这样的命名方式。
- 实际上，不要在测试名中使用 **test**：`@Test` 已经做了这个工作。要告诉读者你在测试什么，而不是你在测试。

### 2. 测试方法的命名

- 不要简单地根据测试的方法命名，例如 **addItemIsOK**。这种命名方式常见且不佳。
- 应该专注于测试行为、属性或能力等的命名。例如，测试 **addItem** 方法时，命名应为 **additionOfItemWithUniqueKeyIsRetained** 和 **additionOfItemWithExistingKeyFails**。

### 3. 使用下划线提高可读性

- 如果命名过长，可以使用下划线来提高可读性（避免使用 camel case），例如：
  - **Addition_of_item_with_unique_key_is_retained**
- 使用 **JUnit 5** 的 `@DisplayName` 和 `DisplayNameGenerator.ReplaceUnderscores` 可以将其显示为“Addition of item with unique key is retained”。

### 4. 测试的目的

- 你的测试目的是“它工作”吗？这只是问题的一半。最大的挑战是确定“它工作”意味着什么。
- 在测试中捕获这个含义，例如，**additionOfItemWithUniqueKeyIsRetained** 和 **additionOfItemWithExistingKeyFails**。

### 5. 失败的含义

- 通过测试不能保证代码一定有效。一个好的单元测试应该使失败的含义清晰明确：失败应该意味着代码无效。
- 正如 Dijkstra 所说，“程序测试可以用来显示存在的错误，但永远不能证明错误的不存在！”

### 6. 控制范围

- 单元测试不应该依赖于无法在测试中控制的因素。例如，**文件系统**、**网络**、**数据库** 或 **异步顺序**。你可能能影响它们，但不能完全控制它们。

### 7. 过拟合的测试

- 小心过拟合的测试：对实现细节的脆弱断言，而不是必需的特性。
- 比如修改了拼写、魔法值或质量结果时，测试失败了。失败的原因是测试本身有问题，而不是生产代码有问题。

### 8. 欠拟合的测试

- 小心欠拟合的测试：模糊的测试，轻易通过，即使代码明显错误。
- 例如，你成功地添加了第一个项目，但不要只是测试“项目数大于零”。正确的结果是“一个项目”，而不是任意大于零的数字。

### 9. 测试结构

- 许多测试遵循一个简单的三步结构：**arrange–act–assert**，即 **given–when–then**。保持这个结构有助于你聚焦于测试想要表达的故事。

### 10. 重复的命名

- 如果命名重复，考虑提取重复部分。你可以将测试分组到带有 `@Nested` 注解的内部类中。比如，可以将 **with_unique_key_is_retained** 和 **with_existing_key_fails** 嵌套到 **Addition_of_item** 内。

希望这些建议对你有帮助。你准备好重新检查一些测试了吗？好了，祝你好运！

---

## 67. Read OpenJDK Daily  

### English

---

OpenJDK consists of millions of lines of Java code. Almost every class violates some “clean code” guidelines. The real world is messy. There is no such thing as “clean code,” and we will struggle to even define what that is.

Experienced Java programmers can read code that follows different styles. OpenJDK has over a thousand authors. Even though there is some consistency in the formatting, they code in disparate ways.

For example, consider the `Vector.writeObject` method:

```java
private void writeObject(java.io.ObjectOutputStream s)
throws java.io.IOException {
    final java.io.ObjectOutputStream.PutField fields = s.putFields();
    final Object[] data;
    synchronized (this) {
        fields.put("capacityIncrement", capacityIncrement);
        fields.put("elementCount", elementCount);
        data = elementData.clone();
    }
    fields.put("elementData", data);
    s.writeFields();
}
```

Why did the programmer mark the local variables `fields` and `data` as `final`? There is no reason why this was necessary. It is a coding style decision. Good programmers can read code equally well, whether the local variables are `final` or not. It does not bother them either way.

Why is `fields.put("elementData", data)` outside of the synchronized block? This may have been due to a premature optimization, wanting to reduce the serial section of code. Or perhaps the programmer was careless? It is easy to want to optimize everything we see, but we need to resist this urge.

Here is another method from the Spliterator inside `ArrayList`:

```java
public Spliterator<E> trySplit() {
    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
    return (lo >= mid) ? null : // divide range in half unless too small
        new RandomAccessSpliterator<>(this, lo, index = mid);
}
```

This method would definitely raise all sorts of “clean code” warning bells. Those in love with `final` would complain that `hi`, `lo`, and `mid` could be `final`. Yeah, they could. But they are not. In OpenJDK, they generally do not mark local variables as `final`.

Why do we have this obscure `(lo + hi) >>> 1`? Could we not rather say `(lo + hi) / 2`? (Answer: it’s not exactly the same.) And why are all three local variables declared on a single line? Is that not violating all that is good and proper?

Turns out, according to research, the number of bugs is in proportion to the lines of code. Spread out your method the way that your university professor asked you to, and you have more lines of code (LOC). And with more LOC, you also end up with more bugs for the same functionality. It can also be that rookie programmers tend to spread their code over many pages. Experts write tight, compact code.

We need to learn to read many different styles of coding, and for that, I recommend OpenJDK. Read the `java.util` classes, `java.io`, and so many others out there.

---

### 中文

------

### ✅ **现实中的代码不“干净”**

- OpenJDK 拥有数百万行 Java 代码，几乎每个类都违反了某些“Clean Code（整洁代码）”的原则。
- 现实世界是混乱的，不存在真正意义上的“干净代码”，甚至连定义都很困难。

------

### ✅ **经验程序员可以理解不同风格的代码**

- OpenJDK 有上千名作者，虽然格式略有统一，但风格各异。
- 有经验的 Java 程序员能理解这些不同风格。

------

### 🔍 **示例：`Vector.writeObject` 方法**

```java
private void writeObject(java.io.ObjectOutputStream s)
throws java.io.IOException {
    final java.io.ObjectOutputStream.PutField fields = s.putFields();
    final Object[] data;
    synchronized (this) {
        fields.put("capacityIncrement", capacityIncrement);
        fields.put("elementCount", elementCount);
        data = elementData.clone();
    }
    fields.put("elementData", data);
    s.writeFields();
}
```

- ❓ **为何将 `fields` 和 `data` 标为 `final`？**
   → 没有必要，仅仅是风格选择。好程序员无论加不加 `final` 都能读懂。
- ❓ **为何 `fields.put("elementData", data)` 放在同步块外？**
   → 可能是想优化同步时间段，也可能是粗心。
   → 我们要克制“过度优化”的冲动。

------

### 🔍 **另一个例子：`ArrayList` 中的 `trySplit` 方法**

```java
public Spliterator<E> trySplit() {
    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
    return (lo >= mid) ? null :
        new RandomAccessSpliterator<>(this, lo, index = mid);
}
```

- 🧹 “Clean Code” 爱好者会抱怨：`hi`、`lo`、`mid` 应该是 `final`。
   → 在 OpenJDK 中通常不会这么做。
- ❓ `(lo + hi) >>> 1` 看着怪怪的，为什么不写 `(lo + hi) / 2`？
   → 因为这两者其实不完全相同（位运算效率更高、避免溢出）。
- ❓ 为何三个变量写在一行？
   → 虽然“看起来不整洁”，但其实这样能减少代码行数。

------

### 📊 **代码行数 vs Bug 数量**

- 研究表明：**代码行数越多，bug 越多**。
- 照教授教的方式排版，会导致方法更长，bug 更多。
- **专家写紧凑代码，新手则喜欢写满好几页。**

------

### 📚 **建议：阅读 OpenJDK 源码**

- 要学会阅读不同风格的代码。
- 推荐阅读 OpenJDK 的 `java.util`、`java.io` 等类。

---

## 68. Really Looking Under the Hood  

### English

------

**Java** is a complete platform and should be treated that way.
 In my Java development career, I’ve met hundreds of developers who are deeply familiar with the language’s syntax.
 They understand **lambdas** and **streams** and know every API from `String` to `nio` off the top of their heads.
 But understanding the following would make them more complete professionals:

------

### Garbage collection algorithms

The **JVM GC** has improved a lot since its first versions.
 The JVM’s ergonomics allow it to automatically adjust to have optimal parameters for the detected environment.
 A good understanding of what is going on can sometimes improve the JVM performance further.

------

### JVM profilers

**JVM tuning** is not a guessing game.
 You should understand how the application is behaving before you make any changes.
 Knowing how to connect and interpret the profiler’s data will help you tune the JVM for better performance, find memory leaks, or understand
 why a method is taking so long to execute.

**Cloud-native applications** make it clear that code can be executed on multiple machines across a network over different operating systems.
 Knowing the following can help Java pros develop a resilient and portable application:

------

### Character encoding

Different OSs can work with different character encodings.
 Understanding what they are and how to set them up can prevent your application from presenting weird characters.

------

### TCP/IP networking

Cloud-native applications are distributed systems.
 In a world of cloud, internet, and network, understanding how to route tables, latency, firewalls, and everything related to **TCP/IP networking**
 is important, especially when things don’t work as expected.

------

### HTTP protocol

In a world where the browser is the client, understanding how **HTTP 1.1** and **2.0** work can help you design your application better.
 Knowing what happens when you store your data in an **HTTP session**, especially in a multiclustered environment, can be quite helpful.

It’s even good to know what frameworks are doing under the hood
 Here we can take **object relational mapping (ORM)** frameworks like **JPA** and **Hibernate** as examples:

------

### Enable SQL output during development

With **SQL output enabled**, you can see what commands are being sent to the database before finding out an odd SQL call is behaving badly.

------

### Query fetch size

Most **JPA/Hibernate** implementations have a default fetch size of one (1).
 That means that if your query brings 1,000 entities from the database, 1,000 SQL commands will be executed to populate those entities.
 You can tune the fetch size to reduce the number of SQL instructions performed.
 You can identify this problem by having the SQL output enabled (see previous item).

------

### One-to-many and many-to-one relationships

Although one-to-many relationships are lazy loaded by default,
 some developers make the mistake of changing the relationship to eager load the entities or manually initialize them before returning the collection of entities.
 Be careful about doing that because each eager-loaded entity can also create the many-to-one relationship,
 causing you to fetch almost every table/entity from the database.
 Enabling SQL output can help you to identify this problem as well (again, see first item).

------

In short, don’t let yourself be controlled—**be in control**!

---

### 中文

------

#### Java 是一个完整的平台，应当以此看待

在我的 Java 开发生涯中，我遇到过数百位对语言语法非常熟悉的开发者。他们理解 **lambdas（λ 表达式）** 和 **streams（流）**，甚至可以脱口而出从 `String` 到 `nio` 的各种 API。

但如果掌握以下内容，将使他们成为更**全面的专业人士**：

------

#### 一、垃圾回收算法（Garbage Collection）

- **JVM 的 GC（垃圾回收器）** 自最初版本以来已有显著改进。
- 它可以根据环境自动调整参数，达到优化目的。
- 如果开发者对其机制有深入理解，**还可以进一步提升性能**。

------

#### 二、JVM 分析工具（Profilers）

- **JVM 优化不是猜谜游戏**。
- 修改配置前，应先了解应用运行状况。
- 掌握如何连接分析工具并解读数据，有助于：
  - 性能调优
  - 排查内存泄漏
  - 分析慢方法的原因

------

#### 三、构建云原生应用需掌握的基础

现代云原生应用跨操作系统、跨网络运行，具备下列能力能让 Java 开发者写出**更健壮、可移植的应用**：

##### 1. 字符编码（Character Encoding）

- 不同操作系统支持的编码可能不同。
- 了解并正确设置编码，**可避免乱码问题**。

##### 2. TCP/IP 网络

- 云原生应用是**分布式系统**。
- 理解路由表、延迟、防火墙等 TCP/IP 相关概念，
  - **对排查网络问题尤其重要**。

##### 3. HTTP 协议

- 在浏览器为客户端的架构下，掌握 **HTTP 1.1 / 2.0** 的工作原理，有助于系统设计。
- 特别是多节点环境下，了解 **HTTP Session** 如何存储数据，**极为重要**。

------

#### 四、了解框架底层原理（以 ORM 框架为例）

框架并非黑盒，理解其内部机制可避免性能陷阱。以 **JPA / Hibernate** 为例：

##### 1. 开发时启用 SQL 输出

- 启用后可查看发送给数据库的 SQL 命令。
- 有助于及早发现**异常或低效的查询**。

##### 2. 查询抓取大小（Query Fetch Size）

- 默认抓取大小通常为 1。
- 如果查询 1000 条记录，系统将执行 1000 次 SQL。
- 可通过设置更大的 fetch size 来**显著减少数据库访问次数**。
- 开启 SQL 输出可辅助识别此类问题。

##### 3. 一对多 / 多对一关系

- 一对多默认是懒加载（lazy loading）。
- 开发者有时会错误地使用急加载（eager loading）或手动初始化。
- 这样会引发级联加载，**导致几乎所有表的数据都被拉取**。
- 同样，通过开启 SQL 输出可以定位此问题。

------

#### 总结

不要让系统控制你， **要掌控系统！**

---

## 69. The Rebirth of Java  

### English

------

Java has been declared dead perhaps more than any other programming language, it seems.
 Perhaps unsurprisingly, reports of its death are greatly exaggerated.

Java has an enormous footprint in backend development, and most enterprises develop systems in Java.
 However, there’s a kernel of truth in every rumor—Java was a slow-moving language in the age of dynamic languages like **Ruby** and **JavaScript**.

Traditionally, major Java releases spanned three to four years of development.
 It’s hard to keep up with other platforms at this pace.

In 2017, all this changed.
 **Oracle**—Java’s steward—announced the Java platform would be released twice a year.
 **Java 9**, released toward the end of 2017, was the last big and long-awaited release.

After Java 9, every year in **March** and **September** a new major Java release is delivered. Like clockwork.

Switching to this time-based release schedule has many consequences.
 Releases can no longer wait on features that are not yet complete.
 Also, because there’s less time between releases and the team developing Java remains the same size, fewer features make it into a release.
 But that’s OK—we get another release in only six months.
 A steady stream of new features and improvements is what we can count on.

Interestingly, new language features are now also delivered incrementally.
 The Java language is now evolving in a more agile manner.
 For example, **Java 12** shipped **Switch Expressions** as a preview language feature,
 with the express intent of later extending this feature to support full **pattern matching**.

One of the reasons why Java releases took so much time and effort is that the platform became somewhat ossified in its 20-plus years of existence.
 In **Java 9**, the platform is fully modularized.
 Every part of the platform is now put into its own **module**, with explicit dependencies on other parts.

The **module system** introduced in Java 9 ensures this platform architecture is adhered to from now on.
 Platform internals are now safely encapsulated inside modules,
 preventing (ab)use by application and library code.

Previously, many applications and libraries depended on these platform internals,
 making it hard to evolve Java without breaking lots of existing code.

It’s also possible to use the module system for your own applications.
 It can make your codebase more maintainable, flexible, and future-proof as well.

Moving from a long and unpredictable release cycle to regular calendar-based releases is a great achievement by the Java team.
 Adapting to this new reality has definitely taken time for us as a developer community.
 Fortunately, the changes in Java are now smaller and more incremental.
 These more frequent and regular releases are easier to adopt and adapt to.

For slower movers, a version of Java is marked as **Long-Term Supported (LTS)** every six releases, starting with **Java 11**.
 Meaning, you can move between LTS releases every three years if you want.

It’s important to understand that the **LTS commitment** is offered by vendors like **Oracle**, **Red Hat**, or even **Amazon**,
 and is not necessarily free of charge.

In any case, the vendor-neutral **OpenJDK** project keeps producing supported builds for the latest Java release that is developed.
 Many things may and will change in releases between the LTS releases, though.

If you can, hop on the frequent-release train and enjoy a steady stream of better Java.
 It’s not as scary as it may sound.

---

### 中文

------

### 🧟 Java“已死”？其实远未如此

- Java 多次被宣称“已死”，但这些传言被大大夸张。
- 实际上，Java 在后端开发中仍占据着巨大的地位，大多数企业仍依赖 Java 开发系统。

------

### 🐢 为什么说 Java 曾经“迟缓”

- 在 **Ruby** 和 **JavaScript** 这类动态语言崛起的年代，Java 更新节奏缓慢。
- 过去 Java 的重大版本通常需要 **3~4 年**的开发周期，导致跟不上其他平台的发展速度。

------

### 🚀 重大转变：半年发布一次

- **2017 年起**，Oracle 宣布 Java 平台采用**每年两次（3 月、9 月）固定发布**的节奏。
- **Java 9** 是旧时代的最后一次“大而慢”的版本。
- 从此之后，Java 每 6 个月就发布一个新版本。

------

### 📦 模块化与敏捷开发

- 新的发布时间表带来了以下变化：
  - 发布不会再因未完成的功能而延期。
  - 由于开发团队规模不变，每个版本包含的功能更少，但**更新频率更高**。
- **Java 12** 开始支持以“预览功能”的形式引入语言特性（如：**Switch Expressions**），并逐步迭代（如：后续扩展为 **Pattern Matching**）。
- **Java 9** 推出了 **模块化系统**，将平台拆分为多个明确依赖的模块：
  - 增强封装性，限制了对底层实现的滥用。
  - 避免破坏旧代码的前提下继续演进语言。
  - 你也可以将模块系统用于自己的项目，提高**可维护性、灵活性和未来兼容性**。

------

### 📅 版本节奏带来的好处

- Java 团队将发布节奏从不可预测变为**固定周期**，这是一次巨大进步。
- Java 的变化现在更加**小步快跑**，更容易被开发者社区接受和采用。

------

### 🧷 面向保守用户的 LTS 策略

- 从 **Java 11** 开始，每 6 个版本会标记一个为**长期支持（LTS）版本**。
  - 大约每三年一次，适合不愿频繁升级的用户。
- 需要注意的是，**LTS 支持由厂商（如 Oracle、Red Hat、Amazon）提供，可能并非免费**。
- 同时，开源社区主导的 **OpenJDK** 项目也会持续发布对最新 Java 版本的支持构建。

------

### 🚄 建议：尽量跟上快车

- LTS 之间的版本会有大量变化。
- 如果可以，尽量采用频繁发布的版本，享受**持续改进的 Java 语言体验**。
- 其实并不像你想象的那么“吓人”。

---

## 70. Rediscover the JVM Through Clojure  

### English

------

Sometime around 2007, my office book club read *Java Concurrency in Practice* by Brian Goetz (Addison-Wesley).
 We weren’t far past the preface of this important book when we panicked about how wrong our naive understanding of Java’s memory model had been, and how easily bugs are introduced into multithreaded code.
 There were audible gasps, and at least one reported nightmare.

In developing a highly concurrent cloud offering, we needed a language that wouldn’t litter our codebase with landmines of shared, mutable state.
 We chose `Clojure`: it has solid concurrency answers and favors functional, efficient transformation of immutable data.
 It runs on the familiar `JVM`, interoperating smoothly with the huge ecosystem of Java libraries.

Though some were hesitant about the unfamiliar `Lisp` syntax and about relearning how to program without mutating variables, it was a great decision.

We discovered the benefits of a `REPL`-centric (read–eval–print loop) workflow:

- No rebuilding or relaunching to test changes
- Exploring the running system and trying variations instantly
- Building and refining ideas incrementally

We appreciated `Clojure`’s bias toward working with data using standard structures and its rich, opinionated core library.
 You don’t have to create lots of classes—each with its own mutually incompatible API—to model anything.

I rediscovered joy and energy in programming.
 A talk at the `Strange Loop` conference about live-coding musical performances in `Clojure` using `Overtone` made me wonder:
 If `Clojure` was fast enough to make music, surely it could run stage lighting?

That led to `Afterglow`, a project that consumed me for a while.
 Figuring out how to write lighting effects in a functional style was a puzzle,
 but `Overtone`’s functional metronome inspired my effect functions,
 mapping musical time to lighting positions, colors, and intensities.

I relearned trigonometry and linear algebra to aim different lights at the same point in space.
 I discovered how to create a desired color using a fixture’s different-hued LEDs.
 Live-coding stage lighting is a ton of fun.

Then I wanted to synchronize `Afterglow`’s metronome with tracks playing on the `CDJs` (today’s digital DJ turntables) I use to mix music.
 Their protocol is proprietary and undocumented, but I was determined.
 I set up a network sniffer and figured it out.
 Early success led to excited contributions from around the world, so I wrote the library `Beat Link` to make using what we learned easy.

I wrote it in `Java` to be widely understandable but discovered that using `Clojure` had made writing `Java` feel cumbersome.

People built on it and ported it to other languages.
 I created a quick demo for a show producer on using `Beat Link` to trigger `MIDI` events that his video software and lighting console could respond to.
 It became my most popular project because it’s useful to nonprogrammers.

Artists are still doing cool new things with `Beat Link Trigger` all the time, and as a guest at music festivals and touring shows, I’ve seen the results.
 Since it’s `Clojure`, users can extend it, and their code gets byte-compiled and loaded into the `JVM` as if it were part of the project all along—another secret weapon `Clojure` can give you.

I encourage anyone working in `Java` to take a serious look at `Clojure`, and see how it can change your experience of life on the `JVM`.

---

### 中文

------

### 一次关于并发编程的“惊吓体验”

- 2007年，我和同事一起读了 Brian Goetz 的 *Java Concurrency in Practice*。
- 读完前言没多久，我们就惊觉自己对 Java 内存模型的理解是多么幼稚，也意识到多线程代码中 bug 是多么容易产生。
- 有人当场惊呼，甚至有人做了噩梦。

------

### 为什么选择 `Clojure`？

我们当时在开发一个高并发的云服务，需要一种避免“共享可变状态陷阱”的语言，最终我们选择了：

- ✅ `Clojure`：擅长处理并发、强调不可变数据、函数式编程高效清晰。
- ✅ 运行于 `JVM`，可无缝调用现有 Java 库。
- ⚠️ 一开始有人对 `Lisp` 语法不熟悉，也不习惯“无变量变异式编程”，但事实证明是个明智的选择。

------

### `REPL` 驱动的开发体验

我们很快爱上了 `Clojure` 的 `REPL` 工作流带来的好处：

- 🔁 无需重编或重启即可测试代码改动
- 🔍 可以即时探索正在运行的系统
- ✍️ 渐进式地构建和打磨功能

此外，`Clojure` 偏好使用标准数据结构 + 精简核心库，无需创建一堆互不兼容的类或对象建模。

------

### 从音乐到灯光，开发带来的乐趣

- 我重新找回了对编程的热情。
- 在 `Strange Loop` 大会上，看到用 `Clojure` + `Overtone` 进行音乐表演的 live-coding 演讲，让我萌生了“用来控制舞台灯光”的念头。
- 最终催生出项目 **`Afterglow`**：用函数式方式实现灯光控制、颜色变化、亮度强度等。

为此，我还重新学习了三角函数和线性代数，以便多盏灯能聚焦同一点，实现精准照明和颜色混合。

------

### 破解 DJ 设备协议，打造 `Beat Link`

- 我想让 `Afterglow` 的节拍器与 `CDJ`（数字 DJ 播放器）同步。
- 虽然其协议是私有且无文档，我还是通过抓包成功破解了。
- 为了方便复用我写了 `Beat Link` 库（用 Java 实现，便于理解），但写完才发现：习惯 `Clojure` 后，写 Java 变得沉重。

`Beat Link` 被全球开发者广泛使用，并被移植到其他语言，成为我最受欢迎的项目之一，尤其是对非程序员也很友好。

------

### `Beat Link Trigger` 持续发挥影响力

- 艺术家用它做出各种创意作品，我在多个音乐节和演出中亲眼见证了它的成果。
- 基于 `Clojure` 的特性，用户可以扩展功能，其代码会直接编译加载到 `JVM` 中，仿佛是原生功能——这正是 `Clojure` 的“秘密武器”。

------

### 结语：鼓励 Java 程序员拥抱 `Clojure`

> 我强烈建议所有 Java 开发者认真了解 `Clojure`，它可能彻底改变你在 JVM 世界里的开发体验。

---

## 71. Refactor Boolean Values to Enumerations  

### English

------

You wouldn’t use “magic numbers” in your code, so don’t use magic Booleans either!
 Boolean literals are worse than hardcoded numbers: a `42` in the code might look familiar, but `false` could be anything, and anything could be `false`.

When two variables are both `true`, you don’t know whether that’s a coincidence or whether they’re both “true” for the same reason and should change together.
 This makes the code harder to read, and causes bugs when you read it wrong.
 As with magic numbers, you should refactor to named constants.

Refactoring `42` to an `ASCII_ASTERISK` or `EVERYTHING` constant improves code readability,
 and so does refactoring `true` to a Boolean constant called `AVAILABLE` in a `Product` class, for example.

However, you probably shouldn’t have any Boolean fields in your domain model: some Boolean values aren’t really Boolean.
 Suppose your `Product` entity has a Boolean `available` field, to indicate whether the product is currently being sold.
 This isn’t really a Boolean: it’s an optional “available” value, which isn’t the same thing because “not available” really means something else, like “out of stock.”

When a type has two possible values, that’s a coincidence, and can change—by adding a “discontinued” option, for example.
 The existing Boolean field cannot accommodate the additional value.

**Beware:** using `null` to mean something is the worst possible way to implement a third value.
 You’ll end up needing a code comment like:

> “`true` when the product is available, `false` when out of stock, `null` when discontinued.”

Please don’t do that.

The most obvious model for products you no longer sell is a Boolean `discontinued` field, in addition to the `available` field.
 This works, but is harder to maintain because there’s no hint that these fields are related.
 Fortunately, Java has a way to group named constants.

Refactor related Boolean fields like these to a Java `enum` type:

```java
enum ProductAvailability {
    AVAILABLE,
    OUT_OF_STOCK,
    DISCONTINUED,
    BANNED
}
```

`enum` types are great because then you get more things to name.
 Also, the values are more readable than a `true` that means that the value is really some other value, such as `AVAILABLE`.
 `enum` types also turn out to be more convenient than you might expect, which makes laziness a weak excuse for not refactoring.

The `enum` type can still have Boolean convenience methods, which you might want if your original code had lots of conditional checks for available products.
 In fact, `enum` types go further than simply grouping constants, with fields, constructors, and methods.
 A less obvious but more important benefit is that you now have a destination for other refactorings that move availability-related logic to the `ProductAvailability` type.

Serializing an `enum` type is more work, e.g., than using JSON or a database.
 However, it’s less than you might expect.
 You’re probably already using a library that handles this nicely and lets you choose how to serialize to a Single Value Object representation.

Domain models often suffer from *primitive obsession*—overuse of Java primitive types.
 Refactoring numbers and dates to domain classes allows your code to become more expressive and readable,
 and the new types provide a better home for related code, such as validations and comparisons.

In the problem domain’s language, Boolean types are false, and enumerated types are the truth.

---

### 中文

------

### ❌ 不要使用“魔法布尔值”

就像你不会在代码中随意使用魔法数字（如 `42`）一样，
 你也不该随便用布尔字面量（如 `true` / `false`）。

- 魔法布尔值比魔法数字更糟：
   数字 `42` 可能还看得懂，但 `false` 可能代表任何东西，含义模糊。

------

### ✅ 应该使用具名常量

比如：

- 把 `42` 重构为 `ASCII_ASTERISK` 或 `EVERYTHING`；
- 把 `true` 重构为 `AVAILABLE`，尤其在 `Product` 类中表示“可售状态”。

好处：

- 提高代码可读性；
- 避免因布尔值含义不清而导致的误解和 Bug。

------

### ⚠️ 布尔字段常常不适合出现在领域模型中

例如：
 `Product` 实体中用 `boolean available` 表示“是否在售”，
 其实并不是真正的布尔类型：

- `false` 不一定表示“不可用”，可能意味着“缺货”；
- 以后还可能增加“已下架”等状态，`boolean` 根本无法扩展。

------

### ❌ 更糟糕的写法是：用 `null` 表示第三种状态

> 示例注释：“`true` 表示可售，`false` 表示缺货，`null` 表示已下架”

这样会极其混乱，维护困难。**请千万别这么做。**

------

### ✅ 正确做法：使用 `enum` 枚举类型代替多个布尔字段

例如，用以下枚举类型代替 `available + discontinued`：

```java
enum ProductAvailability {
    AVAILABLE,
    OUT_OF_STOCK,
    DISCONTINUED,
    BANNED
}
```

好处包括：

- 语义清晰，读起来比 `true` / `false` 易懂；
- 可扩展（如新增 `BANNED`）；
- 可添加字段、构造函数、方法，支持更多逻辑；
- 能作为重构逻辑的承载地（例如可添加 `isAvailable()` 等辅助方法）。

------

### 💡 关于序列化

有人担心 `enum` 序列化比普通值更麻烦，但其实并不严重：

- 大多数库（如 Jackson）都能很好地支持枚举序列化；
- 可以轻松映射为 JSON 中的单值对象表示法。

------

### ⚠️ 原始类型滥用：Primitive Obsession

很多领域模型过度使用 Java 的原始类型（如 `int`, `boolean`, `Date`）。

应该改为使用领域类（如 `Money`, `ProductAvailability`），这样可以：

- 提升表达力与可读性；
- 把相关逻辑（验证、比较）归入合适的类中。

------

### ✅ 总结：布尔值是假的，枚举类型才是真理

布尔值太简陋，不能正确表达领域状态。
 使用枚举类型，让你的模型更真实、更稳固、更易维护。

---

## 72. Refactoring Toward Speed Reading  

### English

------

A casual reader usually reaches **150–200 wpm (words per minute)** with a good comprehension rate.
 People who are into **speed-reading** can easily reach up to **700 wpm**.

But don’t worry, we don’t need to set a new world record for speed-reading to learn the basic concepts and apply them to our code.
 We’ll look at three areas that are particularly helpful when it comes to reading code:
 **skimming**, **meta guiding**, and **visual fixation**.

------

So what makes speed-reading that fast?
 One of the first steps is to **suppress subvocalization**.

*Subvocalization?*
 Exactly. That voice in your head that just tried to properly articulate that word.
 And yes, you’re now aware of that voice. But don’t worry, it will go away soon!

Subvocalization can be unlearned and is an essential first step to seriously improve reading speed.

------

Let’s look at this method with three parameters, which all need validating.
 One way to read the code is to follow where and how the input parameters are used:

```java
public void printReport(Header header, Body body, Footer footer) {
    checkNotNull(header, "header must not be null");
    validate(body);
    checkNotNull(footer, "footer must not be null");
}
```

After locating `header`, we have to find the next parameter, `body`, which requires us to look down and left.
 We can start with a simple refactoring to align the first and third check so we only break the horizontal flow once:

```java
public void printReport(Header header, Body body, Footer footer) {
    checkNotNull(header, "header must not be null");
    checkNotNull(footer, "footer must not be null");
    validate(body);
}
```

Alternatively, given that checking for null is a validation of the parameter as well,
 we could extract the `checkNotNull` method calls into their own properly named methods to help guide the reader.
 Whether these are the same or overloaded version of the method depends on the code at hand:

```java
public void printReport(Header header, Body body, Footer footer) {
    validateReportElement(header);
    validateReportElement(body);
    validateReportElement(footer);
}
```

------

**Meta guiding** is another technique for speed-reading.
 Instead of trying to read word by word in a book, you try to capture the whole line at once.

Children usually do that by using their finger to keep track of the word they’re reading.
 Using some sort of guidance helps us to keep moving forward and avoid jumping back a word or two.

Funny enough, code itself can act as such a device as it has an inherent structure that we can leverage to guide our eye:

```java
List<String> items = new ArrayList<>(zeros);
items.add("one");
items.add("two");
items.add("three");
```

How many items are in the list?
 One, two, three!
 Actually, it’s four.
 Maybe more.
 Oops, missed that `zeros` argument too?

The structure that should help us actually gets in our way.
 While we have allowed our reader to be guided by the alignment of the `add` methods,
 we totally misguided the eye and missed the constructor argument.

Rewriting this allows the reader to follow the guide easily without missing any important information:

```java
List<String> items = new ArrayList<>();
items.addAll(zeros);
items.add("one");
items.add("two");
items.add("three");
```

------

Next time you write a piece of code, see if you can **speed-read** it.
 Keep in mind the basics about **visual fixation** and **meta guiding**.

Try to find a structure that makes logical sense while guiding the eye to see the relevant information.

Not only will it help you to read code faster in the future but it also helps keep you **in the flow**.

---

### 中文

------

**普通读者**的阅读速度通常是 **150-200 字/分钟**，理解率良好。
 而喜欢 **速读** 的人可以轻松达到 **700 字/分钟**。

但不必担心，我们不需要创造速读的新世界纪录，只需学习基础概念，并将其应用于我们的代码。
 我们将探讨三个特别有助于阅读代码的领域：
 **略读 (skimming)**、**元引导 (meta guiding)** 和 **视觉固定 (visual fixation)**。

------

**速读为什么这么快？**
 其中一个第一步是 **抑制内心发音 (subvocalization)**。

*内心发音？*
 没错，就是你脑海中那个试图正确表达单词的声音。
 现在你已经意识到这个声音了。别担心，它很快会消失！

内心发音是可以被“去除”的，这是提高阅读速度的必要第一步。

------

让我们用三个需要验证的参数来看看这种方法。
 一种读取代码的方法是跟踪输入参数的使用位置和方式：

```java
public void printReport(Header header, Body body, Footer footer) {
    checkNotNull(header, "header must not be null");
    validate(body);
    checkNotNull(footer, "footer must not be null");
}
```

在定位到 `header` 后，我们需要找到下一个参数 `body`，这要求我们向下和向左查看。
 我们可以通过简单的重构，将第一个和第三个检查对齐，从而只打破一次水平流：

```java
public void printReport(Header header, Body body, Footer footer) {
    checkNotNull(header, "header must not be null");
    checkNotNull(footer, "footer must not be null");
    validate(body);
}
```

或者，考虑到对 `null` 的检查本身就是一种验证，我们可以将 `checkNotNull` 方法调用提取到单独的命名方法中，以帮助引导读者。
 这些方法是相同的还是重载版本，取决于手头的代码：

```java
public void printReport(Header header, Body body, Footer footer) {
    validateReportElement(header);
    validateReportElement(body);
    validateReportElement(footer);
}
```

------

**元引导 (Meta guiding)** 是另一种速读技巧。
 与逐字阅读书籍不同，你试图一次捕捉整行内容。

孩子们通常通过用手指追踪他们阅读的单词来做到这一点。
 使用某种引导方法有助于我们保持前进，并避免回退一两次。

有趣的是，代码本身可以作为这种设备，因为它具有固有的结构，我们可以利用它来引导我们的眼睛：

```java
List<String> items = new ArrayList<>(zeros);
items.add("one");
items.add("two");
items.add("three");
```

列表中有多少项？
 一、二、三！
 实际上是四项。
 也许更多。
 哎呀，错过了 `zeros` 参数？

本应帮助我们的结构反而成了障碍。
 尽管我们让读者通过 `add` 方法的对齐来引导视线，
 但我们完全误导了眼睛，错过了构造函数的参数。

重写代码后，读者可以更容易地跟随引导，避免错过任何重要信息：

```java
List<String> items = new ArrayList<>();
items.addAll(zeros);
items.add("one");
items.add("two");
items.add("three");
```

------

下次写代码时，看看你是否能 **速读** 它。
 记住 **视觉固定** 和 **元引导** 的基本知识。

尝试找到一个逻辑合理的结构，同时引导眼睛看到相关信息。

这不仅会帮助你未来更快地阅读代码，也有助于你保持 **工作流**。

---

## 73. Simple Value Objects  

### English

------

Classes that represent **Value Objects** don’t need getters or setters.
 Java developers are usually taught to use getters for accessing fields, like this:

```java
public class Coordinate {
    private Latitude latitude;
    private Longitude longitude;

    public Coordinate(Latitude latitude, Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return the latitude of the Coordinate
     */
    public Latitude getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude of the Coordinate
     */
    public Longitude getLongitude() {
        return longitude;
    }
}
System.out.println(thing.getLatitude());
```

The idea is that getters encapsulate how values are represented in an object, providing a consistent approach across a codebase. It also allows for protection against aliasing, for example, by cloning collections before returning them.

The style has its origins in the early days of **JavaBeans**, when there was a lot of interest in graphical tooling using reflection. There might also have been some influence from **Smalltalk** (the classic object-oriented language), in which all fields are private unless exposed via an accessor; read-only fields have getters, but no setters.

In practice, not all classes play the same role and, lacking an alternative structure in the language, many coders write Java classes that are actually **Value Objects**: a simple set of fields that never change, where equality is based on value rather than identity. In our example, two `Coordinate` objects that have the same latitude and longitude are effectively the same. I can use instances of `Coordinate` as constants throughout my code because they’re immutable.

Some years ago, I, like many of my colleagues, started to tire of the boilerplate duplication that getters require and simplified my style for **Value Objects**. I made all the fields `public final`, like a **C struct**:

```java
public class Coordinate {
    public final Latitude latitude;
    public final Longitude longitude;

    public Coordinate(Latitude latitude, Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
System.out.println(coordinate.latitude);
```

I can do this because the object is immutable (again, one has to be careful about aliasing if any of the values are structured), and I tend to avoid inheritance or implementing much behavior. This represents a change in approach from the earlier days of Java. For example, `java.awt.Point` is mutable, and the `move` method updates its `x` and `y` fields in place. Nowadays, after twenty years of improvements in the **JVM** and wider adoption of **functional programming**, such transient objects are cheap enough that we would expect `move` to return a new immutable copy with the new location. An example for our `Coordinate` would be:

```java
public class Coordinate {
    public Coordinate atLatitude(Latitude latitude) {
        return new Coordinate(latitude, this.longitude);
    }
}
```

I’ve found simplified **Value Objects** to be a useful convention for clarifying the role of a type, with less distracting noise in the code. They’re easy to refactor into and often provide a useful target for accumulating methods that express the domain of the code better.

Occasionally, the behavioral features of a **Value Object** become more significant, and I find I can express what I need with methods and make the fields private.

It also turns out that the **Java** language team has recognized this too and introduced a **record** structure in **Java 14**. Until this is widespread, we’ll have to rely on convention.

---

### 中文

---

### 值对象（Value Object）

表示**值对象（Value Objects）**的类不需要使用getter和setter方法。

Java开发者通常会被教导使用getter来访问字段，像这样：

```java
public class Coordinate {
    private Latitude latitude;
    private Longitude longitude;

    public Coordinate(Latitude latitude, Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return the latitude of the Coordinate
     */
    public Latitude getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude of the Coordinate
     */
    public Longitude getLongitude() {
        return longitude;
    }
}
System.out.println(thing.getLatitude());
```

**核心思想**：getter封装了如何在对象中表示值，提供了一种一致的方式来访问代码库中的字段。它还可以通过克隆集合等方式，防止别名问题（aliasing）。

这种风格源于**JavaBeans**的早期，那个时候图形化工具和反射使用受到极大关注。它可能还受到**Smalltalk**语言的影响，在Smalltalk中，所有字段都是私有的，除非通过访问器暴露；只读字段有getter，但没有setter。

### 实际应用

在实际开发中，并非所有类的角色都相同。在没有其他替代结构的情况下，许多开发者将Java类写成**价值对象（Value Objects）**：它们是简单的字段集合，值一旦创建就不会改变，且平等性是基于值而非身份。在我们的例子中，两个具有相同纬度和经度的`Coordinate`对象实际上是相同的。我可以在整个代码中将`Coordinate`的实例作为常量使用，因为它们是不可变的。

### 简化Getter与Setter

几年前，我和许多同事一样，开始厌倦了getter所要求的样板代码，简化了**值对象（Value Objects）**的风格。我将所有字段设置为`public final`，类似于**C语言结构体（C struct）**的写法：

```java
public class Coordinate {
    public final Latitude latitude;
    public final Longitude longitude;

    public Coordinate(Latitude latitude, Longitude longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
System.out.println(coordinate.latitude);
```

我之所以能这样做，是因为该对象是不可变的（当然，如果其中的值是结构化的，仍需小心别名问题），而且我通常避免继承或实现太多行为。这代表了Java早期风格的变化。例如，`java.awt.Point`是可变的，`move`方法会直接修改`x`和`y`字段。如今，经过20年的JVM改进和函数式编程的广泛采用，这种瞬态对象已经足够便宜，我们更倾向于让`move`方法返回一个带有新位置的新不可变副本。比如，我们的`Coordinate`可以这样写：

```java
public class Coordinate {
    public Coordinate atLatitude(Latitude latitude) {
        return new Coordinate(latitude, this.longitude);
    }
}
```

### 值对象的简化应用

我发现简化后的**值对象（Value Objects）**是一种很有用的约定，它能更清晰地表达类型的角色，且能减少代码中的干扰噪音。它们很容易重构，并且通常为聚合更好地表达领域的代码提供了很好的目标。

有时候，**值对象（Value Objects）**的行为特性变得更加重要，我发现我可以通过方法来表达所需功能，并将字段设置为私有。

### Java 14 引入的 Record

实际上，**Java**语言团队也认识到了这一点，并在**Java 14**中引入了**record**结构。直到这种方式被广泛采用之前，我们仍然需要依赖这种约定。

---

## 74. Take Care of Your Module Declarations  

### English

---

If you’re creating Java modules, your module declarations (`module-info.java` files) are easily your most important source files. Each one represents an entire JAR and governs how it interacts with other JARs, so take good care of your declarations! Here are a few things to look out for.

### Keep Module Declarations Clean

Module declarations are code and should be treated as such, so make sure your code style is applied. Beyond that, rather than placing directives randomly, structure your module declarations. Here’s the order the JDK uses:

1. **Requires**, including static and transitive
2. **Exports**
3. **Exports to**
4. **Opens**
5. **Opens to**
6. **Uses**
7. **Provides**

Whatever you decide, if you have a document defining your code style, record the decision there. If you have your IDE, build tool, or code analyzer check such things for you, even better. Try to bring it up to speed so it can automatically check—or even apply—your chosen style.

### Comment Module Declarations

Opinions on code documentation, like Javadoc or inline comments, vary wildly, but whatever your team’s position on comments is, extend it to module declarations. If you like abstractions to have a sentence or two explaining their meaning and importance, add such a Javadoc comment to each module. Even if that’s not your style, most people agree that it’s good to document why a specific decision was made.

In a module declaration, that could mean adding an inline comment to:

- An optional dependency to explain why the module might be absent
- A qualified export to explain why it isn’t public API, but is partially accessible
- An open package explaining which frameworks are expected to access it

Module declarations present a new opportunity: never before has it been this easy to document the relationships of your project’s artifacts in code.

### Review Module Declarations

Module declarations are the central representation of your modular structure, and examining them should be an integral part of any kind of code review you do. Whether it’s looking over your changes before a commit or before opening a pull request, wrapping up after a pair-programming session, or during a formal code review, anytime you inspect a body of code, pay special attention to `module-info.java`:

- Are new module dependencies necessary (consider replacement with services) and in line with the project’s architecture?
- Is the code prepared to handle the absence of optional dependencies?
- Are new package exports necessary? Are all public classes in there ready for use? Can you reduce the API surface area?
- Does it make sense that an export is qualified, or is it a copout to get access to an API that’s not ready to be public?
- Were changes made that could cause problems for downstream consumers that are not part of the build process?

Investing time into diligently reviewing module descriptors might sound like waste, but I see it as an opportunity: never before has it been this easy to analyze and review the relationships of your project’s artifacts and its structure. And not the photographed whiteboard sketch that was uploaded to your wiki a few years ago; no, the real deal, the actual relationships between your artifacts. Module declarations show the naked reality instead of outdated good intentions.

---

### 中文

---

如果你正在创建 Java 模块，`module-info.java` 文件很可能是你最重要的源文件。每个文件表示一个完整的 JAR，并控制它与其他 JAR 的交互，因此要好好处理这些声明！以下是一些需要注意的事项。

### 保持模块声明简洁

模块声明是代码，应当按照代码风格来处理，因此确保应用你的代码风格。除此之外，不要将指令随意排列，要有结构地组织你的模块声明。JDK 使用的顺序如下：

1. **Requires**，包括静态和传递性依赖
2. **Exports**
3. **Exports to**
4. **Opens**
5. **Opens to**
6. **Uses**
7. **Provides**

无论你选择什么样的结构，如果你有定义代码风格的文档，应该记录下这个决定。如果你有 IDE、构建工具或代码分析工具来检查这些内容，那就更好了。尽量让它们保持最新，这样它们可以自动检查，甚至应用你选择的风格。

### 给模块声明添加注释

关于代码文档的意见（如 Javadoc 或内联注释）各不相同，但无论团队的注释策略如何，都应将其扩展到模块声明。如果你希望抽象类有一两句话解释其含义和重要性，可以在每个模块上添加 Javadoc 注释。即使这不是你的风格，大多数人认为在模块声明中记录特定决策的原因是很好的做法。

在模块声明中，可能需要添加内联注释来解释：

- 可选依赖：解释为什么模块可能缺失
- 有资格的导出：解释为什么它不是公共 API，但可以部分访问
- 开放的包：解释哪些框架预计会访问该包

模块声明提供了一个新的机会：从未像现在这样，能够轻松地在代码中记录项目构件之间的关系。

### 审查模块声明

模块声明是模块化结构的核心表现，检查它们应当是任何代码审查的一部分。无论是提交前检查修改，还是在打开拉取请求前，或者是在配对编程结束后，或是进行正式代码审查时，每次检查代码时，都要特别关注 `module-info.java`：

- 新的模块依赖是否必要（考虑替换为服务）并符合项目架构？
- 代码是否准备好处理可选依赖的缺失？
- 是否有必要导出新的包？所有公共类都准备好供使用了吗？能否减少 API 面积？
- 该导出是否合理，还是为了获取尚未准备好公开的 API 而采取的变通方法？
- 做出的修改是否可能导致下游消费者出现问题，尤其是那些不在构建过程中的？

尽管投入时间仔细审查模块描述符可能看起来像是在浪费时间，但我认为这是一个机会：从未像现在这样，能够轻松分析和审查项目构件及其结构之间的关系。这不是几年前上传到你 Wiki 的白板草图，而是真正的构件间的实际关系。模块声明展现了项目构件之间的真实关系，而不是过时的良好意图。

---

## 75. Take Good Care of Your Dependencies  

### English

---

Modern Java development is heavily dependent on third-party libraries. By using Maven or Gradle, we have easy mechanisms in place to import and use published packages. As developers do not want to create and maintain boilerplate functionality but rather focus on the specific business logic, using frameworks and libraries can be a wise choice.

When looking at an average project, the amount of your code can be as little as 1%, and the rest will be imported libraries and frameworks. A lot of code that is put into production is simply not ours, but we do depend on it heavily.

As we look at our code and the way we treat contributions by team members, we often turn to processes like code reviews before we merge new code into our master branch as a first-pass quality assurance measure. Alternatively, this quality control process might also be covered by practicing pair programming. The way we treat our dependencies, however, is very different from how we treat our own code. Dependencies are often just used without any form of validation. Importantly, the top-level dependencies, on many occasions, in turn pull in transitive dependencies that can go many levels deep. For example, a 200-line Spring application with 5 direct dependencies can end up using 60 dependencies in total, which amounts to almost half a million lines of code being shipped to production.

By only using these dependencies we blindly trust other people’s code, which is odd compared to how we handle our own code.

### Vulnerable Dependencies

From a security point of view, you should scan your dependencies for known vulnerabilities. If a vulnerability in a dependency is found and disclosed, you should be aware of this and replace or update those dependencies. Using outdated dependencies with known vulnerabilities can be disastrous if you look at some examples in the past.

By scanning your dependencies during every step in your development process, you might prevent that vulnerable dependency surprise before you ship your code to production. You should also keep scanning your production snapshot, as new vulnerabilities may be disclosed while you are already using it in your production environment.

### Updating Dependencies

You should choose your dependencies wisely. Look at how well a library or framework is maintained and how many contributors are working on it. Depending on outdated or poorly maintained libraries is a large risk. If you want to stay up-to-date, you can use your package manager to help you detect if newer versions are available. By using the Maven or Gradle version plug-in, you can use the following commands to check for newer versions:

- **Maven**: `mvn versions:display-dependencyupdates`
- **Gradle**: `gradle dependencyUpdates -Drevision=release`

### A Strategy for Your Dependencies

When handling dependencies in your system, you should have a strategy in place. Questions about dependency health and the reason why a particular dependency is used should be made explicit. Next, you should also carefully think about what your update strategy should be. Updating often is considered less painful in general. Last, but not least, you should have tooling in place that scans your libraries for known vulnerabilities to prevent being breached.

In any case, you should take good care of your dependencies and choose the right library with the right version for the right reason.

---

### 中文

---

现代Java开发在很大程度上依赖于第三方库。通过使用Maven或Gradle，我们可以轻松地导入和使用已发布的包。由于开发人员不想创建和维护模板化的功能，而是希望专注于具体的业务逻辑，使用框架和库是一个明智的选择。

在一个典型的项目中，你的代码可能只占1%左右，其余部分将是导入的库和框架。很多投入生产的代码并不是我们自己写的，但我们却严重依赖它。

在查看代码和处理团队成员贡献时，我们通常会在将新代码合并到主分支之前，进行代码审查，作为初步的质量保证措施。或者，这个质量控制过程也可以通过实践结对编程来覆盖。然而，我们对待依赖项的方式与我们处理自己代码的方式非常不同。依赖项往往被直接使用，而没有任何形式的验证。值得注意的是，顶级依赖项通常会拉入多个层级的传递依赖。例如，一个包含5个直接依赖的200行Spring应用程序，最终可能会使用60个依赖，总共有近50万行代码被推送到生产环境。

仅使用这些依赖项就等于我们盲目地信任其他人的代码，这与我们处理自己代码的方式相对立。

### 漏洞依赖

从安全角度来看，您应该扫描依赖项是否存在已知漏洞。如果发现依赖项中存在漏洞并已披露，您应该意识到并更新或替换这些依赖项。使用带有已知漏洞的过时依赖项是非常危险的，历史上已经有很多例子证明了这一点。

通过在开发过程中每个步骤扫描依赖项，您可以避免在将代码部署到生产环境之前遇到漏洞依赖项的惊喜。您还应该继续扫描生产环境中的快照，因为在您已经使用它的生产环境中，可能会披露新的漏洞。

### 更新依赖

选择依赖项时应慎重。查看一个库或框架的维护状况以及有多少贡献者参与其中。依赖过时或维护不良的库风险很大。如果您想保持最新，您可以使用包管理工具帮助您检测是否有新版本可用。通过使用Maven或Gradle的版本插件，您可以使用以下命令检查是否有新版本：

- **Maven**: `mvn versions:display-dependencyupdates`
- **Gradle**: `gradle dependencyUpdates -Drevision=release`

### 依赖管理策略

在管理系统中的依赖项时，您应该有一个策略。明确依赖项健康状况以及为什么使用某个特定依赖项。接下来，您还应该仔细考虑更新策略。一般来说，频繁更新被认为不太痛苦。最后，您应当使用工具扫描您的库，以检查是否存在已知漏洞，防止被攻击。

无论如何，您应该好好照顾您的依赖项，选择合适的库和版本，确保其使用的正确性。

---

## 76. Take “Separation of Concerns” Seriously  

### English

---

If you studied computer science, you may have learned about an idea called **separation of concerns**. This is best characterized by the sound byte “One class, one thing, one method, one thing.” The idea is that your classes and methods (and functions) should always be focused on a single outcome.

Think carefully about the responsibilities of your classes and methods. I sometimes teach classes in test-driven design. I use adding fractions as a simple coding kata to explore TDD. The most common first test I see people write often looks something like this:

```java
assertEquals("2/3", Fractions.addFraction("1/3", "1/3"));
```

For me, this test is screaming “poor design.” First, where is the fraction? It only exists implicitly, presumably inside the `addFraction` function.

Worse than this, let’s think about what is going on here. How would we describe the behavior of the `addFraction` function? Perhaps something like “It takes two strings, parses them, and calculates their sum.” As soon as you see, or think, the word “and” in the description of a function, method, or class, you should hear alarm bells ringing inside your head. There are two concerns here: one is string parsing, and the other is fraction adding.

What if we wrote our test like this instead:

```java
Fraction fraction = new Fraction(1, 3);
assertEquals(new Fraction(2, 3), fraction.add(new Fraction(1, 3)));
```

How would we describe the `add` method in this second example? Perhaps, “It returns the sum of the two fractions.” This second solution is simpler to implement, simpler to test, and the code inside will be simpler to understand. It is also significantly more flexible because it is more modular and therefore more composable. For example, if we wanted to add three fractions instead of two, how would that work? In the first example, we would have to add a second method or refactor the first, so we could call something like:

```java
assertEquals("5/6", Fractions.addFraction("1/3", "1/3", "1/6"));
```

In the second case, no code changes are necessary:

```java
Fraction fraction1 = new Fraction(1, 3);
Fraction fraction2 = new Fraction(1, 3);
Fraction fraction3 = new Fraction(1, 6);
assertEquals(new Fraction(5, 6), fraction1.add(fraction2).add(fraction3));
```

Let’s imagine that we did want to start with a string representation. We could add a new, second class called something like `FractionParser` or `StringToFraction`:

```java
assertEquals(new Fraction(1, 3), StringFractionTranslator.createFraction("1/3"));
```

`StringFractionTranslator.createFraction` converts a string representation of a fraction into a `Fraction`. We could imagine other methods on this class that take a `Fraction` and render a `String`. Now we can test this code more thoroughly, and we can test it separately from the complexity of adding fractions, or multiplying them or anything else.

Test-driven development is very helpful in this respect because it highlights issues of poor separation of concerns clearly. It is often the case that if you are finding it difficult to write a test, it is a result of either poor coupling in your design or poor separation of concerns.

**Separation of concerns** is a very effective design strategy to employ in any code. Code with good separation of concerns is, by definition, more modular, and it’s usually much more composable, flexible, testable, and readable too.

Always strive to make every single method, class, and function focused on a single outcome. As soon as you notice that your code is trying to do two things, pull out a new class or method to make it simpler and clearer.

---

### 中文

---

如果你学习过计算机科学，可能会学到一个叫做**关注点分离**的概念。它的核心思想可以用一句话来概括：“一个类做一件事，一个方法做一件事。” 这意味着你的类和方法（以及函数）应该始终专注于一个单一的目标。

### 关注类和方法的责任

认真思考你类和方法的职责。我有时会教测试驱动设计（TDD）课程，使用分数相加作为一个简单的编程练习来探索TDD。在我看到的最常见的第一个测试通常是这样的：

```java
assertEquals("2/3", Fractions.addFraction("1/3", "1/3"));
```

对我来说，这个测试显然是在说“设计不好”。首先，分数在哪里？它仅仅隐含存在，可能在`addFraction`函数内部。

更糟的是，我们思考一下这里发生了什么。如何描述`addFraction`函数的行为？也许可以描述为：“它接受两个字符串，将它们解析并计算它们的和。” 一旦你在描述函数、方法或类时看到或想到“和”这个词，就应该警觉了。这里有两个关注点：一个是字符串解析，另一个是分数相加。

### 改进的测试写法

假设我们改写测试：

```java
Fraction fraction = new Fraction(1, 3);
assertEquals(new Fraction(2, 3), fraction.add(new Fraction(1, 3)));
```

如何描述这个`add`方法的行为？也许可以说：“它返回两个分数的和。” 这个解决方案更简洁易于实现，也更容易测试，代码也更容易理解。它也更具灵活性，因为它更模块化，因此更易于组合。例如，如果我们想加三个分数而不是两个，如何实现呢？在第一个例子中，我们需要添加一个新方法或重构现有的方法，才能调用类似这样的代码：

```java
assertEquals("5/6", Fractions.addFraction("1/3", "1/3", "1/6"));
```

而在第二个例子中，不需要修改代码：

```java
Fraction fraction1 = new Fraction(1, 3);
Fraction fraction2 = new Fraction(1, 3);
Fraction fraction3 = new Fraction(1, 6);
assertEquals(new Fraction(5, 6), fraction1.add(fraction2).add(fraction3));
```

### 使用字符串表示的情况

假设我们确实想从字符串表示开始。我们可以添加一个新的类，比如`FractionParser`或`StringToFraction`：

```java
assertEquals(new Fraction(1, 3), StringFractionTranslator.createFraction("1/3"));
```

`StringFractionTranslator.createFraction`将字符串表示的分数转换为`Fraction`对象。我们可以想象这个类中还可以有其他方法，用于将`Fraction`转换为字符串。现在我们可以更彻底地测试这些代码，并且可以与分数相加、相乘或其他任何操作的复杂性分开测试。

### 测试驱动开发的优势

测试驱动开发（TDD）在这方面非常有帮助，因为它能清晰地突出关注点分离不良的问题。通常，如果你发现编写测试很困难，原因可能是设计中的耦合过高或关注点分离不良。

**关注点分离**是一种非常有效的设计策略。遵循良好的关注点分离原则的代码通常是更加模块化的，通常也更易于组合、灵活、可测试和可读。

### 结论

始终努力让每个方法、类和函数专注于一个单一的目标。一旦你发现你的代码在做两件事，就应该提取出一个新的类或方法，使代码更加简洁和清晰。

---

## 77. Technical Interviewing Is a Skill Worth Developing  

### English

---

I’m going to let you into a secret: our industry is horrible at interviewing developers. What’s really silly is that we almost never sit a candidate down to write actual code in the actual environment they’re going to be developing in. That’s like testing a musician on theory but never listening to them play.

The good news is that interviewing is a skill like any other, meaning it can be learned. As with acquiring any other skill, you can research what’s involved and practice, practice, practice. If you get rejected during interviews, it doesn’t mean you’re not a good developer. It might just mean you’re not good at interviews. That’s something you can improve on, and each interview is another opportunity to gather more data and to practice.

Interviewers will often ask similar sorts of questions. Here are three that are fairly typical:

### Multithreading Gotchas

It’s still common to be asked to inspect code with `synchronized` scattered liberally around and find the race condition or deadlock. Organizations with this sort of code have bigger problems than hiring developers (although if they show that code in interviews, they’ll definitely have a problem hiring developers), so maybe you don’t want to work there anyway.

Having a working understanding of concurrency in Java will help you navigate most of these interview questions. If you don’t know old-school Java concurrency, talk about how modern Java has abstracted away these problems and explain how you might use `Fork/Join` or parallel streams instead.

### Compiler Gotchas

“Does this code compile?” Well, I dunno, that’s what a computer and IDE are for—the tools can answer the question while I worry about other things. If you get asked these sorts of questions in interviews, use some of the Java Certification study materials (for example, actual books) to learn how to answer them.

### Data Structures

Java data structures are fairly straightforward: understanding the difference between a `List`, a `Set`, and a `Map` is a good place to start. Knowing what a hash code is for helps, and so does how `equals` is used in the contexts of collections.

A quick web search for common Java interview questions will also give you a good set of topics to research.

### Is This Cheating?

If you learn just enough to get through the interview, will you really know enough to do the job? Remember: our industry is horrible at interviewing developers. The interview experience is often miles away from the job experience. Ask plenty of questions to see if you can get a glimpse of what working there is really like.

You can learn new technologies easily enough—that’s what we do all the time. It’s all that people-related stuff that often determines whether you’ll succeed. But that’s a topic for another article.

---

### 中文

---

我要透露一个秘密：我们行业在面试开发者方面做得很差。更可笑的是，我们几乎从不让候选人在他们将要开发的实际环境中写实际代码。这就像是测试一个音乐家的理论知识，但从不听他们演奏。

好消息是，面试是一项像其他技能一样可以学习的技能。和学习其他技能一样，你可以研究涉及的内容并反复练习。如果你在面试中被拒绝，并不意味着你不是一个好开发者。可能只是说明你不擅长面试。这是可以改进的，每一次面试都是收集更多数据和练习的机会。

面试官通常会问一些类似的问题。以下是三种比较典型的问题：

### 多线程难题

仍然很常见的是，让你检查代码中到处都是` synchronized` 关键字的地方，找出竞争条件或死锁。拥有这种代码的组织比招开发者的问题要大得多（尽管如果他们在面试中展示这段代码，他们肯定会有招不到开发者的问题），所以也许你不想去那种公司。

了解 Java 中的并发机制将帮助你应对大多数这样的面试问题。如果你不懂传统的 Java 并发，可以谈谈现代 Java 如何抽象化了这些问题，并解释如何使用 `Fork/Join` 或并行流来解决。

### 编译器难题

“这段代码能编译吗？”嗯，我不知道，那是计算机和 IDE 的工作——工具可以回答这个问题，而我可以专注于其他事情。如果面试中问到这类问题，可以使用一些 Java 认证学习资料（例如实际的书籍）来学习如何回答这些问题。

### 数据结构

Java 的数据结构相对简单：理解 `List`、`Set` 和 `Map` 之间的区别是一个很好的起点。了解哈希码的作用以及 `equals` 在集合上下文中的使用方式也很有帮助。

快速搜索常见的 Java 面试题也会给你一个很好的研究主题。

### 这是作弊吗？

如果你只学习足够通过面试的内容，你是否真的知道足够做这份工作？记住：我们行业在面试开发者方面做得很差。面试经验通常和实际工作经验差别很大。多问一些问题，看看能否了解那里工作的实际情况。

你可以很容易学会新技术——这正是我们日常做的事。往往决定你是否能成功的，是那些与人相关的因素。这是另一个话题，我们下次再讨论。

---

## 78. Test-Driven Development  

### English

---

Test-driven development (TDD) is widely misunderstood. Before TDD, the only thing that applied pressure for high quality in software was the knowledge, experience, and commitment of a programmer. After TDD, there was something else.

High quality in software is widely agreed to include the following properties in code:

- Modularity
- Loose coupling
- Cohesion
- Good separation of concerns
- Information hiding

Testable code has those properties. TDD is development (design) driven by tests. In TDD, we write the test before writing code that makes the test pass. TDD is much more than “good unit testing.” Writing the test first is important; it means that we always end up with “testable” code. It also means that coverage is never an issue. If we write the test first, we always have great coverage and don’t need to worry about it as a metric—and it is a poor metric.

TDD amplifies the talent of a software developer. It doesn’t make bad programmers great, but it makes any programmer better.

TDD is very simple—the process is **Red, Green, Refactor**:

1. We write a test and see it fail (**Red**).
2. We write the minimum code to make it pass and see it pass (**Green**).
3. We refactor the code, and the test, to make them as clean, expressive, elegant, and simple as we can (**Refactor**).

These steps represent three distinct phases in the design of our code. We should be thinking differently during each of these steps.

### Red

Focus on expressing the behavioral intent of your code. Concentrate only on the **public interface** of your code. That is all that we are designing at this point—nothing else. Think only about how to write a nice, clear test that captures just what you would like your code to do. Focus on the design of the **public interface** by making the test simple to write. If your ideas are easy to express in your test, they will also be easy to express when someone uses your code.

### Green

Do the simplest thing that makes the test pass. Even if that simple thing seems naive. As long as the test is failing, your code is broken, and you are at an unstable point in the development. Get back to safety (**Green**) as quickly and simply as you can.

Your tests should grow to form a **“behavioral specification”** for your code. Adopting the discipline of writing code only when you have a failing test helps to better elaborate and evolve that specification.

### Refactor

Once back to **Green**, you can safely refactor. This keeps you honest and stops you from wandering off into the weeds and getting lost! Make small simple steps, and then rerun the tests to confirm that everything still works.

Refactoring is not an afterthought. This is an opportunity to think more strategically about your design. If the setup of your tests is too complex, your code probably has poor separation of concerns and may be too tightly coupled to other things. If you need to include too many other classes to test your code, perhaps your code is not very cohesive.

Practice a pause for refactoring every time you achieve a passing test. Always look and reflect, “Could I do this better?” The three phases of TDD are distinct, and your mental focus should also be distinct to maximize the benefit of each phase.

---

### 中文

---

**测试驱动开发（TDD）** 是一种常被误解的开发方法。在TDD出现之前，软件的高质量只依赖于程序员的知识、经验和承诺。而TDD之后，质量的保证加入了新的因素。

高质量的软件通常具备以下特点：

- **模块化**
- **松耦合**
- **内聚性**
- **良好的关注点分离**
- **信息隐藏**

可测试的代码具备这些特性。TDD是由测试驱动的开发（设计）。在TDD中，我们在编写使测试通过的代码之前，先编写测试。TDD不仅仅是“良好的单元测试”。**先写测试是关键**，它保证我们最终编写的是“可测试”的代码。这也意味着覆盖率永远不是问题。如果我们先写测试，我们总能保证很好的覆盖率，而不需要担心它作为一个度量标准——事实上，它本身是一个不好的度量标准。

TDD放大了软件开发者的才能。它不会让差的程序员变得更好，但它能让任何程序员变得更好。

TDD非常简单，过程为 **红、绿、重构（Red, Green, Refactor）**：

1. 我们先编写一个测试，看到它失败（**红**）。
2. 我们编写最小的代码来让测试通过，并看到它通过（**绿**）。
3. 我们重构代码和测试，使它们变得更加简洁、优雅和清晰（**重构**）。

这三个步骤代表了我们代码设计中的三个独立阶段。在每个阶段，我们应该以不同的思维方式来处理。

### **红**（Red）

专注于表达代码的行为意图。此时只关注代码的 **公共接口**，其他都不需要考虑。只考虑如何编写一个清晰的测试，准确捕捉你希望代码实现的功能。通过简化测试编写，专注于 **公共接口** 的设计。如果你的设计能在测试中简单清晰地表达，那么它在别人使用你的代码时，也会变得同样容易理解。

### **绿**（Green）

做出使测试通过的最简单的实现，即使这个简单的实现看起来很幼稚。只要测试失败，说明你的代码有问题，并且处于不稳定的开发状态。尽快且简洁地恢复到安全的（**绿**）状态。

你的测试应该逐渐形成代码的 **“行为规范”**。在编写代码之前，只有当测试失败时再编写代码，这样有助于更好地细化和演进这个规范。

### **重构**（Refactor）

当回到 **绿** 状态时，你可以安全地进行重构。这可以保持你的代码简洁，避免陷入复杂细节而迷失方向！进行小的改动，然后重新运行测试确认一切正常。

重构不是事后的补充，而是一个机会，帮助你从战略上考虑代码的设计。如果测试的设置过于复杂，可能说明你的代码存在关注点分离不良的情况，可能与其他部分耦合过紧。如果你需要引入过多其他类来测试代码，可能说明你的代码缺乏内聚性。

每次通过测试后，都要暂停并思考是否可以改进代码。TDD的三个阶段是不同的，你在每个阶段的思维方式也应该有所不同，从而最大化每个阶段的效果。

---

## 79. There Are Great Tools in Your bin/ Directory  

### English

---

Every Java developer is familiar with `javac` for compiling, `java` for running, and probably `jar` for packaging Java applications. However, many other useful tools come installed with the JDK. They are already on your computer in your JDK’s `bin/` directory and are invokable from your PATH. It’s good to get acquainted with some of these tools so you know what’s at your disposal:

### `jps`

If you’ve ever found yourself running `ps aux | grep java` to find the running JVMs, you probably just want to run `jps`. This dedicated tool lists all the running JVMs, but instead of showing you a lengthy command with CLASSPATHs and arguments, `jps` simply lists the process ID and the application’s main class name, making it far easier to figure out which process is which.

- `jps -l` will list the fully qualified main class name.
- `jps -m` will show the arguments passed to the main method.
- `jps -v` will show all the arguments passed to the JVM itself.

### `javap`

The JDK comes with a Java class file disassembler. Run `javap <class file>` to see that class file’s fields and methods, which can often be very enlightening for understanding what code written in JVM-based languages such as Scala, Clojure, or Groovy is turned into under the hood.

- Run `javap -c <class file>` to see the complete bytecode of those methods.

### `jmap`

Running `jmap -heap <process id>` will print a summary of the JVM process’s memory space, such as how much memory is being used in each of the JVM’s memory generations, as well as the heap configuration and type of GC being used.

- `jmap -histo <process id>` will print a histogram of each class in the heap, how many instances there are of that class, and how many bytes of memory are consumed.
- Most critically, running `jmap -dump:format=b,file=<filename> <process id>` will dump a snapshot of the entire heap to a file.

### `jhat`

Running `jhat <heap dump file>` will take the file generated by `jmap` and run a local web server. You can connect to this server in a browser to explore the heap space interactively, grouped by package name.

- The “Show instance counts for all classes (excluding platform)” link shows only instances of classes outside of Java itself.
- You can also run “OQL” queries, allowing you to query the heap space via SQL-esque syntax.

### `jinfo`

Run `jinfo <process id>` to see all system properties the JVM loaded with and JVM command-line flags.

### `jstack`

Running `jstack <process id>` will print stack traces for all current Java threads running in a JVM.

### `jconsole` and `jvisualvm`

These are graphical tools that allow connecting to JVMs and interactively monitoring running JVMs. They offer visual graphs and histograms of various aspects of a running process and are a mouse-friendly alternative to many of the tools listed above.

### `jshell`

As of Java 9, Java has an honest-to-goodness REPL—a great tool to check syntax, run quick Java-based commands, or try out code and experiment without building full programs.

Many of these tools can run not only locally but against JVM processes running on remote machines as well. These are only some of the useful programs you already have installed; take some time to see what else is in your JDK’s directory of executables and read their man pages—it’s always handy to know what tools are in your toolbelt.

---

### 中文

---

每个Java开发者都熟悉 `javac` 用于编译、`java` 用于运行，可能还知道 `jar` 用于打包Java应用程序。然而，JDK中还包含了许多其他有用的工具，它们已经安装在JDK的 `bin/` 目录下，并可以通过PATH调用。了解这些工具能够帮助你更高效地工作：

### `jps`

如果你曾经使用 `ps aux | grep java` 来查找正在运行的JVM进程，那么你很可能只需要使用 `jps`。这个专用工具列出了所有正在运行的JVM进程，而不是显示包含类路径和参数的冗长命令，`jps` 只显示进程ID和应用程序的主类名称，方便你快速识别各个进程。

- `jps -l` 将列出完整的主类名。
- `jps -m` 将显示传递给主方法的参数。
- `jps -v` 将显示传递给JVM的所有参数。

### `javap`

JDK自带了一个Java类文件反汇编工具。运行 `javap <类文件>` 可以查看该类文件的字段和方法，这对于理解使用JVM语言（如Scala、Clojure或Groovy）编写的代码如何在底层转化成字节码非常有帮助。

- 运行 `javap -c <类文件>` 可以查看方法的完整字节码。

### `jmap`

运行 `jmap -heap <进程ID>` 可以打印出JVM进程的内存空间摘要，显示JVM内存各代的使用情况、堆配置以及使用的垃圾回收器类型。

- `jmap -histo <进程ID>` 将打印出堆中每个类的直方图，包括该类的实例数量和占用的内存字节数。
- 最关键的是，运行 `jmap -dump:format=b,file=<文件名> <进程ID>` 会将整个堆的快照转储到文件中。

### `jhat`

运行 `jhat <堆转储文件>` 将启动一个本地Web服务器，你可以通过浏览器连接到该服务器，交互式地浏览堆空间，按包名分组。

- “Show instance counts for all classes (excluding platform)” 链接只显示Java平台之外的类实例。
- 你还可以运行“OQL”查询，使用类似SQL的语法查询堆空间。

### `jinfo`

运行 `jinfo <进程ID>` 将显示JVM加载的所有系统属性和JVM命令行标志。

### `jstack`

运行 `jstack <进程ID>` 将打印出JVM中所有当前Java线程的堆栈跟踪信息。

### `jconsole` 和 `jvisualvm`

这两个图形工具允许连接到JVM并交互式地监控正在运行的JVM。它们提供了关于运行进程的各种图形和直方图，是许多上述工具的鼠标友好替代品。

### `jshell`

从Java 9开始，Java引入了一个真正的REPL（交互式编程环境），这是一个非常好的工具，用于检查语法、快速运行Java命令或在不构建完整程序的情况下尝试代码和进行实验。

这些工具不仅可以本地运行，还可以连接到远程机器上的JVM进程。以上列出的是你已经安装的一些有用工具，建议花些时间查看JDK目录中的其他可执行程序，并阅读它们的手册页，了解工具箱中有哪些其他工具。

---

## 80. Think Outside the Java Sandbox  

### English

---

“Java is the best language ever, for every purpose.” If you believe this, you need to get out more. Sure, Java’s a great language, but it’s not the only good language, nor the best for every purpose. In fact, every so often you should—as a professional developer—take the time to learn and use a new language, either at work or on your own. Go deep enough to recognize how it differs in some fundamental way from what you’re used to and whether it might be useful in your projects. In other words: try it, you might like it. Here are a few languages you may want to learn:

**JavaScript** is the language of the browser. Despite similar names and a dozen or so keywords, `JavaScript` and `Java` are very different. `JavaScript` comes with hundreds of different web frameworks, some of which go beyond the frontend. For example, `Node.js` lets you run `JavaScript` server-side, which opens up many new possibilities.

**Kotlin** is a `JVM` language that, like most of these languages, has a more relaxed syntax than `Java`, along with other features that can give it an advantage over `Java`. `Google` uses `Kotlin` for much of its work in `Android` and encourages its use in `Android` apps. 'Nuff said!

**Dart and Flutter**: `Dart` is a compiled scripting language from `Google`. Originally for web programming, it didn’t blossom until `Flutter` began using `Dart` for `Android` and `iOS` apps (and browser-side, someday) from one codebase.

**Python**, **Ruby**, and **Perl** have been around for decades and remain among the most popular languages. The first two have `JVM` implementations, `Jython` and `JRuby`, though the former isn’t being actively maintained.

**Scala**, **Clojure**, and **Frege** (an implementation of `Haskell`) are `JVM` functional programming (`FP`) languages. `FP` has a long, narrow history but has been making inroads into the mainstream in recent years. Many `FP` languages don’t run on the `JVM` as of this writing, such as `Idris` and `Agda`. Learning `FP` may help you to use the functional facilities in `Java 8+`, if you’re not really comfortable there.

**R** is an interpreted language for data manipulation. Cloned from `Bell Labs’ S` for statisticians, `R` is now popular with data scientists or anyone going “beyond the spreadsheet.” Lots of stats, math, and graphics functions built-ins and add-ons.

**Rust** is a compiled language aimed at systems development with features for concurrency and strong typing.

**Go** (“`Golang`”) is a compiled language invented at `Google` by `Robert Griesemer`, `Rob Pike`, and `Ken Thompson` (co-creator of Unix). There are multiple compilers, targeting different operating systems natively and web development by compiling down to `JavaScript` and `WebAssembly`.

**C** is ancestral to `C++`, `Objective-C`, and, to some extent, `Java`, `C#`, and `Rust`. (`C` gave these languages the basic syntax of built-in types, method syntax, and curly braces for code blocks and is the language to blame for `int i = 077;` having the value `63` in `Java`.) If you didn’t learn assembly language, “`C level`” is a place to start understanding memory models that will give you an appreciation for `Java’s` way of doing things.

**JShell** isn’t a language, per se—it’s a different way of doing `Java`. Instead of having to write out `public class Mumble {` and `public static void main(String[] args) {` just to try out an expression or some new API, just forget all the ceremony and boilerplate and use `JShell`.

So go on. Step outside of `Java`.

---

### 中文

---

“Java 是最棒的编程语言，适用于所有用途。”如果你相信这一点，那你应该走出去看看。确实，Java 是一种很棒的语言，但它并不是唯一优秀的语言，也不是适用于所有场景的最佳选择。实际上，作为一名专业开发者，你应该时不时地花时间学习并使用一种新语言，不论是在工作中还是自己学习。深入学习，了解它与自己熟悉的语言在某些根本上的区别，并判断它是否对你的项目有用。换句话说，试试看，你可能会喜欢它。以下是一些你可能想学习的语言：

**JavaScript** 是浏览器的编程语言。尽管名字相似并且有一些相同的关键字，`JavaScript` 和 `Java` 是非常不同的。`JavaScript` 配备了数百种不同的网页框架，其中一些甚至超越了前端开发。例如，`Node.js` 让你能够在服务器端运行 `JavaScript`，这为开发提供了许多新的可能性。

**Kotlin** 是一种 `JVM` 语言，和大多数语言一样，`Kotlin` 的语法比 `Java` 更加宽松，并且有其他一些特性，使其在某些方面优于 `Java`。`Google` 使用 `Kotlin` 完成大量的 `Android` 工作，并鼓励在 `Android` 应用中使用它。足够说明问题了！

**Dart 和 Flutter**：`Dart` 是 `Google` 推出的编译型脚本语言。最初用于网页编程，直到 `Flutter` 开始使用 `Dart` 开发 `Android` 和 `iOS` 应用（未来也许会用于浏览器端）时，才得到了广泛应用，能够从一个代码库生成多平台应用。

**Python**、**Ruby** 和 **Perl** 已经存在几十年，依然是最受欢迎的编程语言之一。前两者有 `JVM` 实现，分别是 `Jython` 和 `JRuby`，尽管 `Jython` 并未积极维护。

**Scala**、**Clojure** 和 **Frege**（`Haskell` 的实现）是 `JVM` 上的函数式编程（`FP`）语言。`FP` 历史悠久但较为狭窄，近几年逐渐进入主流。许多 `FP` 语言目前不能在 `JVM` 上运行，例如 `Idris` 和 `Agda`。学习 `FP` 可以帮助你更好地理解和使用 `Java 8+` 的函数式特性，尤其是如果你对这部分功能还不太熟悉的话。

**R** 是一种解释型语言，专用于数据处理。它源自 `Bell Labs’ S`，目前在数据科学家及任何需要“超越电子表格”的人中都非常流行。内置大量统计、数学和图形函数，并提供丰富的扩展功能。

**Rust** 是一种编译型语言，专为系统开发设计，具有并发特性和强类型支持。

**Go**（“`Golang`”）是 `Google` 发明的编译型语言，由 `Robert Griesemer`、`Rob Pike` 和 `Ken Thompson`（`Unix` 的共同创造者）开发。它有多个编译器，能够针对不同操作系统进行本地编译，也支持通过编译成 `JavaScript` 和 `WebAssembly` 来进行网页开发。

**C** 是 `C++`、`Objective-C`，以及在一定程度上，`Java`、`C#` 和 `Rust` 的祖先语言。（`C` 为这些语言提供了内置类型、方法语法和大括号语法，也就是导致 `int i = 077;` 在 `Java` 中值为 63 的语言。）如果你没有学习过汇编语言，“`C` 级”是理解内存模型并更加欣赏 `Java` 做法的起点。

**JShell** 本身并不是一种语言，它是另一种使用 `Java` 的方式。你不需要编写 `public class Mumble {` 和 `public static void main(String[] args) {` 这些冗长的代码来测试一个表达式或新API，只需忽略所有的繁琐和模板，使用 `JShell` 即可。

所以，来吧，走出 `Java` 的世界。

---

## 81. Thinking in Coroutines  

### English

---

Coroutines are functions or methods that can be suspended and resumed. In Kotlin, they can be used in place of threads for asynchronous work because many coroutines can run efficiently on a single thread.

To see how coroutines work, we’re going to create an example program that plays these drum sequences in parallel:

**Instrument Sequence**

- Toms: x-x-x-x-x-x-x-x
- High hat: x-x-x---x-x-x---
- Crash cymbal: ----------------x----

We could use threads to do this, but in most systems, the sound is played by the sound subsystem, while the code pauses until it can play the next sound. It’s wasteful to block a valuable resource like a thread in this way. Instead, we’re going to create a set of coroutines: one for each of the instruments. We’ll have a method called `playBeats`, which takes a drum sequence and the name of a sound file. The full code is at https://oreil.ly/6x0GK; a simplified version looks like this:

```kotlin
suspend fun playBeats(beats: String, file: String) {
    for (...) { // for each beat
        ...
        playSound(file)
        ...
        delay(<time in milliseconds>)
        ...
    }
}
```

Call this with `playBeats("x-x-x---x-x-x---", "high_hat.aiff")`, and it will play the sequence using the `high_hat.aiff` sound file. There are two things in this code that you find in any Kotlin coroutine:

1. It begins with the `suspend` keyword, which means that the function can suspend its operation at some point until some external code restarts it.
2. It includes a nonblocking call to the `delay` function.

The `delay` function is analogous to something like `Thread.sleep`, except it works by handing back control to the outside world, with a request to resume again after the specified pause.

If that’s what a coroutine looks like, how do you call it? What calls the coroutine, copes with it suspending, and then reschedules it when it needs to restart? The `launch` function takes care of everything for us.

The main method to run the coroutines looks like this:

```kotlin
fun main() {
    runBlocking {
        launch { playBeats("x-x-x-x-x-x-x-x-", "toms.aiff") }
        launch { playBeats("x-x-x---x-x-x---", "high_hat.aiff") }
        launch { playBeats("----------------x----", "crash_cymbal.aiff") }
    }
}
```

Each call to `launch` accepts a block of code that calls the coroutine. A block of code in Kotlin is like a lambda in Java. The `launch` function registers the coroutine call with a scope provided by the `runBlocking` function.

`runBlocking` runs a scheduling loop on the main thread, which coordinates the calls to each of the coroutines. It calls each of the `playBeats` coroutines in turn and waits for it to suspend by calling `delay`. `runBlocking` then waits until some other `playBeats` coroutine needs to resume. `runBlocking` does this until all the coroutines complete.

You can think of coroutines as lightweight threads: they allow you to mentally split work into separate simple tasks, which appear to run concurrently while running on the same thread.

Coroutines are invaluable when writing code for Android, which enforces a strict threading model in which some operations must run on the main UI thread. But they’re also useful for creating scalable server-side applications that must make efficient use of existing threads.

---

### 中文

---

协程是可以暂停并恢复的函数或方法。在 Kotlin 中，协程可以替代线程用于异步工作，因为多个协程可以高效地在单个线程上运行。

为了理解协程的工作原理，我们将创建一个示例程序，并行播放这些鼓点序列：

**乐器序列**

- Toms: x-x-x-x-x-x-x-x
- High hat: x-x-x---x-x-x---
- Crash cymbal: ----------------x----

我们可以使用线程来实现这一点，但在大多数系统中，声音由声音子系统播放，代码在等待播放下一个声音时会暂停。以这种方式阻塞宝贵的线程资源是浪费的。因此，我们将创建一组协程，每个乐器一个协程。我们将有一个名为 `playBeats` 的方法，它接受一个鼓点序列和一个声音文件名。完整的代码可以参考 https://oreil.ly/6x0GK，简化版如下：

```kotlin
suspend fun playBeats(beats: String, file: String) {
    for (...) { // 针对每个节拍
        ...
        playSound(file)
        ...
        delay(<时间毫秒>)
        ...
    }
}
```

调用 `playBeats("x-x-x---x-x-x---", "high_hat.aiff")`，它将使用 `high_hat.aiff` 音频文件播放该序列。代码中有两个关键点，在任何 Kotlin 协程中都会出现：

1. 它以 `suspend` 关键字开始，这意味着该函数可以在某个点暂停执行，直到外部代码重新启动它。
2. 它包括对 `delay` 函数的非阻塞调用。

`delay` 函数类似于 `Thread.sleep`，但不同的是，它通过将控制权交回外部世界，并请求在指定暂停时间后恢复执行。

那么，如何调用协程呢？谁来调用协程、处理它的暂停，并在需要时重新调度它？`launch` 函数为我们处理了这一切。

运行协程的主要方法如下：

```kotlin
fun main() {
    runBlocking {
        launch { playBeats("x-x-x-x-x-x-x-x-", "toms.aiff") }
        launch { playBeats("x-x-x---x-x-x---", "high_hat.aiff") }
        launch { playBeats("----------------x----", "crash_cymbal.aiff") }
    }
}
```

每次调用 `launch` 都接受一个代码块，调用协程。在 Kotlin 中，代码块类似于 Java 中的 lambda。`launch` 函数将协程调用注册到 `runBlocking` 函数提供的作用域中。

`runBlocking` 在主线程上运行一个调度循环，协调对每个协程的调用。它按顺序调用每个 `playBeats` 协程，等待它通过调用 `delay` 暂停。然后，`runBlocking` 等待直到其他 `playBeats` 协程需要恢复执行。`runBlocking` 会持续这一过程，直到所有协程完成。

你可以将协程视为轻量级线程：它们允许你将工作分解为独立的简单任务，这些任务看似并发执行，但实际上在同一线程上运行。

在编写 Android 代码时，协程是非常有价值的，因为 Android 强制要求某些操作必须在主 UI 线程上运行。它们同样适用于创建可扩展的服务器端应用程序，能够高效地利用现有线程。

---

## 82. Threads Are Infrastructure; Treat Them as Such  

### English

---

How many Java programmers manage—or even think about—stack use during their programming? More or less none. The vast majority of Java programmers leave stack management to the compiler and runtime system.

How many Java programmers manage—or even think about—heap use during their programming? Very few. The majority of Java programmers assume the garbage collection system will deal with all heap management.

So why are so many Java programmers managing all their threads manually? Because that is what they were taught to do. From the beginning, Java supported shared memory multithreading, which was almost certainly a big error.

Almost all that most Java programmers know about concurrency and parallelism is founded on the theory of constructing operating systems from the 1960s. If you are writing an operating system then this is all good stuff, but are most Java programs actually operating systems? No. So a rethink is in order.

If your code has any `synchronized` statements, locks, mutexes—all the paraphernalia of operating systems—then in all likelihood, you are doing it wrong. This is the wrong level of abstraction for most Java programmers. Just as stack space and heap space are managed resources, threads should be considered managed resources. Instead of creating threads explicitly and managing them, construct tasks and submit them to a thread pool. Tasks should be single-threaded—obviously! If you have many tasks that need to communicate with one another, then rather than using shared memory, use a thread-safe queue instead.

All of this was already known in the 1970s, culminating in Sir Charles Antony (Tony) Richard Hoare creating *Communicating Sequential Processes* (CSP) as an algebra for describing concurrent and parallel computation. Sadly, it was ignored by the majority of programmers in the rush to use shared memory multithreading, with every program being a new operating system. During the 2000s, though, many looked to get back to sequential processes communicating. Perhaps the most high-profile advocate of this in recent years has been the *Go* programming language. It is all about sequential processes communicating, made to execute via an underlying thread pool.

Many use the terms *actors*, *dataflow*, *CSP*, or *active objects*, all of which are variations on the sequential process and communication theme. *Akka*, *Quasar*, and *GPars* are all frameworks providing various forms of task over a thread pool. The Java platform comes with the *Fork/Join* framework, which can be used explicitly and also underpins the *Streams* library, the revolution of Java introduced in Java 8.

Threads as a managed resource is the correct level of abstraction for the vast majority of Java programmers. *Actors*, *dataflow*, *CSP*, and *active objects* are the abstractions for the vast majority of programmers to use. Giving up manual control over threads releases Java programmers to write simpler, more comprehensible, more maintainable systems.

---

### 中文

---

有多少 Java 程序员在编程时管理或思考栈的使用？几乎没有。绝大多数 Java 程序员将栈管理交给编译器和运行时系统。

有多少 Java 程序员在编程时管理或思考堆的使用？非常少。大多数 Java 程序员认为垃圾回收系统会处理所有堆的管理。

那么，为什么那么多 Java 程序员仍然手动管理所有线程呢？因为他们从一开始就被教导这样做。从一开始，Java 就支持共享内存的多线程，这几乎可以说是一个大错误。

几乎所有 Java 程序员对并发和并行的理解都基于 1960 年代构建操作系统的理论。如果你在写操作系统，那这些理论是不错的，但大多数 Java 程序真的在写操作系统吗？不是。那么，需要重新思考了。

如果你的代码中有任何 `synchronized` 语句、锁、互斥量——所有操作系统的工具——那么很可能你在做错事。这是大多数 Java 程序员不该使用的抽象层次。就像栈空间和堆空间是受管理的资源一样，线程也应被视为受管理的资源。与其显式创建线程并管理它们，不如构建任务并将它们提交给线程池。任务应该是单线程的——显然！如果你有许多任务需要相互通信，那么与其使用共享内存，不如使用线程安全的队列。

这一切早在 1970 年代就已为人所知，最终由查尔斯·安东尼（Tony）理查德·霍尔创建了 *Communicating Sequential Processes*（CSP），作为描述并发和并行计算的代数。不幸的是，大多数程序员在匆忙使用共享内存多线程时忽视了它，认为每个程序都是一个新的操作系统。然而，在 2000 年代，许多人开始重新关注顺序进程间的通信。最近几年，也许最知名的倡导者就是 *Go* 编程语言。它专注于顺序进程之间的通信，并通过底层线程池来执行。

许多人使用 *actors*、*dataflow*、*CSP* 或 *active objects* 等术语，它们都是顺序进程和通信主题的变体。*Akka*、*Quasar* 和 *GPars* 都是提供线程池上任务处理的框架。Java 平台自带了 *Fork/Join* 框架，既可以显式使用，也支持 Java 8 中引入的 *Streams* 库。

线程作为受管理的资源是绝大多数 Java 程序员应该使用的正确抽象层次。*Actors*、*dataflow*、*CSP* 和 *active objects* 是大多数程序员应该使用的抽象。放弃对线程的手动控制，使 Java 程序员能够编写更简单、更易理解、更易维护的系统。

---

## 83. The Three Traits of Really, Really Good Developers  

### English

------

My undergraduate degree was in computer science and math, and the first few years of my career were spent working as a Java developer.
 I really enjoyed my time as a developer. Like many mathematicians, I had an obsession with writing clean and elegant code, and I would refactor my code for ages until it was as near to perfection as it could get.
 I was aware of the end users, but only to the extent that they provided the requirements that created the challenges that I would then have to solve.

Fast-forward to 20 years after I graduated, and I’m now on a completely different path, consulting on financial markets regulation and market structure,
 with a particular interest in financial innovation, which also keeps me in touch with my techie roots.
 I’ve worked with many developers over the years, from the other side of the fence as the person who provides and clarifies the requirements.
 And over time, I’ve developed a greater appreciation of certain traits that really, really good developers have that go beyond technical ability.

The first and most important is curiosity.
 The same drive that causes you to want to solve problems, to understand how things work, and to build new things,
 can and should be applied to your interactions with your clients and stakeholders.
 It’s great when developers ask lots of questions about the business domain because it shows that they really want to understand and to learn.
 It also leads to a better understanding of the business domain and the ability to address the problems of end users more effectively.
 I’ve encountered loads of development managers who actively dissuade their teams from “bothering” the business too much with questions.
 That’s so wrong.

The second and third are empathy and imagination.
 It’s about the ability to put yourself in your end user’s shoes and try to understand their priorities and experience of using your software.
 It’s also the ability to then come up with creative solutions to the challenges that they face, using your technical expertise.
 Many developers tend to dismiss a lot of this stuff as unimportant or to assume that it’s for someone else to deal with.
 But it’s much more effective, and makes you a better developer, if you are able to communicate directly with the business yourself.

These might sound like obvious things. But they are so important.
 I recently attended a conference on tech and innovation that focused on the importance of collaboration between technology and the business
 in order to best leverage emerging technologies like the `cloud`, `distributed ledger technology`, and `artificial intelligence/machine learning`.
 Many speakers emphasized the importance of breaking down walls between developers and end users.
 Some now embed developers into their business teams and expect them to know just as much about the business domain.
 So this is also about the future and about how to work smarter. If you can cultivate these skills, it can also open doors for you.

---

### 中文

------

### 我的背景与 Java 开发经历

- 我大学主修计算机科学和数学，职业生涯初期曾是一名 **Java 开发者**。
- 我非常热爱编程，像许多数学家一样，对编写**干净、优雅的代码**充满执念。
- 经常不断重构代码，直到尽可能接近“完美”。
- 对最终用户的关注仅限于他们提供的需求，这些需求构成了我要解决的技术挑战。

------

### 职业转型与新视角

- 毕业 20 年后，我进入了金融市场监管与市场结构咨询领域，专注于**金融创新**，同时保留着技术背景。
- 多年来，我从需求方的角度与许多开发者合作，并逐渐理解了**优秀开发者**除技术以外的重要特质。

------

### 真正优秀的开发者特质

#### 1. **好奇心**

- 优秀开发者不仅对技术问题充满好奇，也愿意探索业务领域。
- 经常主动提问，想真正理解业务逻辑，进而提出更精准的技术解决方案。
- **问题驱动学习**是沟通和理解客户与利益相关者的关键。

> ❗ 有些开发经理会阻止团队频繁“打扰”业务方，其实这是完全错误的做法。

#### 2. **共情能力与想象力**

- 能设身处地从**最终用户角度**思考他们的需求与使用体验。
- 善于运用技术知识，提出**有创意的解决方案**，解决用户面临的挑战。
- 很多开发者忽视这些软技能，认为是“别人”的职责，其实主动沟通会让自己变得更出色。

------

### 向未来发展看齐

- 这些看似“理所当然”的能力，其实对开发者至关重要。
- 我最近参加了一场关于技术与创新的会议，强调了**技术与业务协作的重要性**。
- 随着 `云计算`、`分布式账本技术`、`人工智能/机器学习` 的崛起，许多公司正打破开发者与用户之间的“墙”。
- 一些企业已将开发者直接嵌入业务团队，要求他们具备与业务方同样深的领域理解。

> ✅ 拥有这些能力不仅让你成为更好的开发者，也会为你的职业发展**打开新大门**。

---

## 84. Trade-Offs in a Microservices Architecture  

### English

------

Is there an optimal software architecture? What does it look like?
 How do we measure “optimal” when it comes to building and operating software?

An optimal software architecture is one that has maximal flexibility for change at the lowest possible cost.
 Here, cost is measured in terms of certain qualities that represent a software architecture’s design and implementation—in addition to the cost of the infrastructure to operate it.

The defining trait of a software quality is that it can be tangibly measured and has an impact on other qualities.

For example, if a software architecture requires strong consistency guarantees, there is an impact on qualities like performance and availability.
 Eric Brewer created the `CAP theorem` to describe a set of measurable trade-offs where you can only choose two out of three guarantees for running a database:

- `consistency`
- `availability`
- `partition tolerance`

The theorem states that when applications share state across the boundaries of a network, you must choose between consistency or availability, but you cannot have both.

------

One of the main problems with `microservices` is that there is no single comprehensive definition.
 Moreover, microservices are a collection of concepts and ideas that are based on a set of constraints for delivering a services architecture.
 A microservice, or any piece of software you build, is a history of choices—which will affect your ability to make new choices today.

Microservices may not have a single definition, but they do most commonly have the following characteristics:

- Independent deployability
- Organized around business capabilities
- Database per service
- One application, one team
- API-first
- Continuous delivery

------

As you go out into the world of software development, you will eventually find that there is no such thing as the right choice.
 Indeed, most developers or operators might believe there is a best choice, and you may find that they argue strongly in favor of that choice.
 As you encounter more and more opportunities to make a decision between multiple choices—for instance, which database to use—you’ll eventually come to discover that all available options introduce certain trade-offs.

That is, you will usually have to lose something to gain something.

Here is a short list of trade-offs you might encounter when making a decision to include a dependency for your microservice:

- `Availability`: How often is my system available to its users?
- `Performance`: What is the overall performance of my system?
- `Consistency`: What guarantees does my system provide about consistency?
- `Speed`: How fast can I deploy a single line of code change to production?
- `Composability`: What percentage of an architecture and codebase can be reused instead of duplicated?
- `Compute`: What is the cost of my system’s compute under peak load?
- `Scalability`: What is the cost of adding capacity if peak load continues to increase?
- `Marginality`: What is the average diminishing marginal return of adding developers to my team?
- `Partition tolerance`: If a partition in the network causes an outage or latency, will my application experience or cause a cascading failure?

------

How does answering one question affect answering the others?
 You will find each of these questions often has some kind of relation to the other questions.

If you ever find yourself making a tough decision in a software architecture that uses microservices, come back to this list of questions.

---

### 中文

------

### 什么是“最优”的软件架构？如何衡量“最优”？

- **最优软件架构**：具备“最大灵活性 + 最低成本”。
- **成本定义**：不仅包括运维基础设施的成本，也包括架构设计与实现所体现的“质量特征”（可量化，且会影响其他质量）。

#### 示例：一致性对性能和可用性的影响

- 当系统需要强一致性时，往往会牺牲**性能**和**可用性**。
- `Eric Brewer` 提出了著名的 **CAP 定理**：
  - 在一个跨网络共享状态的系统中，**一致性（Consistency）**、**可用性（Availability）** 和 **分区容忍性（Partition Tolerance）** 三者只能选其二。

------

### 微服务的核心问题与特征

#### 问题：

- 没有统一、权威的定义。
- 本质是围绕“服务架构”的一组理念和约束集合。
- 每个微服务都是“历史选择”的产物，会影响你未来做出的技术决策。

#### 常见特征：

- **独立部署**（Independent deployability）
- **按业务能力组织**（Organized around business capabilities）
- **每个服务独立数据库**（Database per service）
- **一应用一团队**（One application, one team）
- **API优先**（API-first）
- **持续交付**（Continuous delivery）

------

### 软件开发中的“选择”与“权衡”

- **没有完美的选择**，只有适合当前场景的权衡方案。
- 每一次技术决策（如选数据库）都会涉及“有所失才能有所得”。

#### 微服务架构中的典型权衡项：

| 指标                  | 说明                           |
| --------------------- | ------------------------------ |
| `Availability`        | 系统对用户的可用频率？         |
| `Performance`         | 系统整体性能如何？             |
| `Consistency`         | 提供何种一致性保证？           |
| `Speed`               | 一行代码多快能部署上线？       |
| `Composability`       | 架构/代码可复用率有多高？      |
| `Compute`             | 高峰期计算资源成本？           |
| `Scalability`         | 负载上升时扩容代价多高？       |
| `Marginality`         | 增加开发人数的边际效益？       |
| `Partition tolerance` | 网络分区下系统是否会雪崩失效？ |

------

### 建议

- 这些问题之间往往是**相互影响**的。
- 每当你在微服务架构中面临艰难决策时，**回顾这份权衡清单**将非常有帮助。

---

## 85. Uncheck Your Exceptions  

### English

------

If you ever want to walk to hell, the journey will be easy on your feet.
 The whole road is very well paved, with good intentions as far as the eye can see.
 At least one of those paving stones is dedicated to Java’s `checked exception` model.

A `checked exception` is one that, if not handled within a method, must appear in the method’s `throws` clause.
 Any class descended from `Throwable` can be listed after `throws`, but unhandled checked exceptions
 (not descended from either `RuntimeException` or `Error`) must appear.
 This is a feature of the Java language, but it has no meaning for the JVM and is not a requirement for JVM languages.

The good intention here promotes a method’s failures to the same type-level significance
 as its success-scenario inputs and outputs.
 At first sight, this seems reasonable.
 Indeed, in a small and closed codebase, this type-level confidence that some exceptions are not overlooked
 is an easy goal to meet and, once met, offers some (very) basic reassurance about the completeness of the code.

Practices that might work in the small, however, are under no obligation to scale.
 Java’s `checked exceptions` were an experiment in combining control flow with types, and experiments produce results.
 The designers of C# learned from the experience:
 C# neither requires nor allows such exception specifications.

> Examination of small programs leads to the conclusion that requiring exception specifications
>  could both enhance developer productivity and enhance code quality,
>  but experience with large software projects suggests a different result—
>  decreased productivity and little or no increase in code quality.

The designers of C#, of other JVM languages, of other non-JVM languages…
 whatever the original intent, the day-to-day reality of `checked exceptions` is they’re perceived as obstacles.
 And if there’s one thing programmers are skilled at, it’s working around obstacles.

Compiler complaining about an unhandled `checked exception`?
 One IDE shortcut later, the obstacle is gone!
 In its place, you have an ever-lengthening `throws` clause that pushes incidental information into published signatures,
 often leaking details that should be encapsulated.
 Or perhaps you add `throws Exception` or `throws OurCompanyException` to every method,
 noisily defeating the goal of being specific about failure?

How about catch-and-kill?
 If you’re in a rush to push your code, there’s nothing an empty `catch` block can’t fix!
 You are Gandalf to the `checked exception`’s Balrog—“You shall not pass!”

`Checked exceptions` bring and inspire syntactic baggage.
 But the issues run deeper.
 This is not simply a matter of programmer discipline or tolerating verbosity:
 for frameworks and extensible code, `checked exceptions` are flawed from the outset.

When publishing an interface, you’re committing to a contract signed with method signatures.
 As Tolstoy recognized in *Anna Karenina*, the rainy-day scenarios are not as simple, as certain,
 or as knowable up front as the happy-day scenarios:

> All happy families are alike; each unhappy family is unhappy in its own way.

Interface stability is hard. Interface evolution is hard.
 Adding `throws` makes everything harder.

If someone plugs code into yours, and uses your code in their application,
 they know what they might be throwing, but you neither know nor care.
 Your code should let exceptions pass from their plugged-in code through to the handlers in their main application code.
 Open inversion of control demands exception transparency.

If they’re using `checked exceptions`, however, they can’t use your interfaces unless you:

- add `throws Exception` to every method—noise that creates a burden on all dependent code
- or tunnel their exceptions wrapped in a `RuntimeException`
- or change their approach, standardizing on `unchecked exceptions` instead.

This last option is the lightest, most stable, and most open approach of all.

---

### 中文

---

### Java中的`checked exception`模型

**概述：**

- **`checked exception`** 是一种异常类型，如果在方法中没有处理，必须在方法的 `throws` 声明中列出。
- 任何继承自 `Throwable` 的类都可以在 `throws` 中列出，但未处理的 `checked exception`（不继承 `RuntimeException` 或 `Error`）必须列出。
- 这是Java语言的特性，但对JVM没有实际意义，也不是JVM语言的要求。

**设计初衷与实践：**

- 这种设计意图是将方法的失败与成功场景一样，提升到相同的类型级别。这在小型、封闭的代码库中看似合理，可以帮助开发者确认某些异常不会被忽视。
- 然而，实践证明，这种设计在大型软件项目中往往导致 **生产力下降**，并且代码质量提升有限。

**C#和其他语言的反思：**

- C#从Java的`checked exception`模型中吸取了教训：C#既不要求也不允许这样的异常规范。
- 尽管在小型程序中要求异常规范可能提高开发者生产力并改善代码质量，但在大型项目中却可能导致 **生产力降低**，并且对代码质量几乎没有提升。

**`checked exception`的实际问题：**

- `checked exception` 通常被视为障碍，程序员们通过不同的方式绕过这些障碍：
  - **IDE快捷键**：在编译器报错时通过快捷键快速解决未处理的 `checked exception`，结果导致 `throws` 声明越来越长，暴露了不该暴露的信息。
  - **空的catch块**：为了快速提交代码，使用空的 `catch` 块来“消灭”异常，忽略了失败的具体信息。
  - **添加`throws Exception`**：将 `throws Exception` 添加到所有方法，导致代码冗长，且无法精确表达失败的类型。

**`checked exception`的影响：**

- **语法负担**：`checked exception` 增加了代码的复杂性，并且为框架和可扩展代码带来了固有的缺陷。
- **接口的演变**：当你发布接口时，你承诺了与方法签名相关的契约。正如托尔斯泰在《安娜·卡列尼娜》中所说：“所有幸福的家庭都是相似的；每个不幸的家庭则各有各的不幸”——**接口稳定性和演变是困难的**，而添加 `throws` 只会让一切变得更复杂。

**`checked exception`对接口的影响：**

- 如果其他代码将代码接入你的接口，他们知道他们可能抛出的异常，但你既不知道也不关心。
- 如果使用 `checked exception`，为了让他们的代码与你的接口兼容，你需要：
  - 在每个方法中添加 `throws Exception`，这会增加代码负担。
  - 或者将他们的异常包裹在 `RuntimeException` 中。
  - 或者改变方法，统一使用 `unchecked exception`。

**最佳做法：**

- **统一使用`unchecked exception`** 是最简洁、最稳定且最开放的做法。

---

## 86. Unlocking the Hidden Potential of Integration Testing Using Containers  

### English

------

Most Java developers have probably encountered the **testing pyramid** at one point in their career, whether as part of a computer science curriculum or mentioned in conference talks, articles, or blog posts.

We can find a multitude of origin stories and variations of this metaphor (with a deep dive into those worthy of an article on its own) but, in general, it boils down to having a sizeable foundation of **unit tests**, followed by a smaller chunk of **integration tests** on top of that, and an even smaller tip of **end-to-end UI tests**.

This shape is proposed as an ideally optimal ratio of the different test classes. However, as with everything in software and computers, these guidelines need to be assessed in context, which means assuming integration tests to be slow and brittle.

And this assumption is probably true if integration tests are expected to be run in a shared testing environment or require an extensive setup of local dependencies.
 But would the ideal shape still be a pyramid if we challenge these assumptions?

With ever more powerful machines, we can either use **virtual machines (VMs)** to wholly contain the complete development environment or use them to manage and run the external dependencies necessary for integration testing (such as databases or message brokers).

But since most VM implementations aren’t overhead free, this will add considerable load and resource consumption to the developer workstation.
 Also, start and creation times of VMs are too high for an ad hoc setup of a required environment as part of test execution.

The advent of user-friendly **container technology**, on the other hand, allows new testing paradigms to emerge. These low-overhead container implementations (being essentially an isolated process with its own self-contained file system) enable the creation and instrumentation of required services on demand and the usage of uniform tooling.

Still, this instrumentation has been mostly done manually and laboriously outside of the actual test execution, slowing onboarding of new developers and introducing the potential for clerical mistakes.

In my opinion, the goal we as a community should strive for is to make the setup and instrumentation of the test environment an integral part of the test execution and even of the test code itself.

In the case of **Java**, this means that executing a **JUnit** test suite, whether done by the IDE or the build tool, would implicitly lead to the creation and configuration of a set of containers necessary for the tests.

And this goal is achievable with today’s technology!

We can interact directly with the container engine using existing APIs or command-line tools, thereby writing our own “container driver” — note, however, the distinction between starting a container and the readiness of the service inside the container for testing.

Alternatively, there is also the opportunity to explore the **Java ecosystem** for existing projects that deliver these functionalities on a higher level of abstraction.

Either way, it’s time to unleash the power of good integration tests and to emancipate them from the shackles of their past!

---

### 中文

---

以下是这段关于 Java 的英文内容的中文翻译，已突出重点、简洁有条理地格式化输出：

------

### Java 测试金字塔的演变与集成测试的现代化

#### 1. 测试金字塔的基本结构

- 大多数 Java 开发者都接触过 **测试金字塔**，常见于课程、会议、文章或博客中。
- 测试金字塔的核心思想是：
  - **底层大量单元测试（Unit Tests）**
  - **中层较少的集成测试（Integration Tests）**
  - **顶层最少的端到端 UI 测试（End-to-End UI Tests）**
- 这种结构被认为是不同测试类型的最优比例。

#### 2. 对传统假设的质疑

- 传统观点认为：**集成测试慢且脆弱**，特别是在共享测试环境或依赖本地服务时。
- 问题：如果我们挑战这个前提，测试金字塔的形状还合理吗？

#### 3. VM 与容器技术的比较

- **虚拟机（VM）**
  - 可完整封装开发环境或运行所需依赖服务（如数据库、中间件）
  - 缺点：
    - 开销大
    - 启动慢，不适合临时集成测试
- **容器技术**
  - 更轻量（本质是带文件系统的隔离进程）
  - 可以按需创建和配置服务，统一工具链
  - 问题：当前容器的配置仍多为手动完成，影响效率与易用性

#### 4. 目标：测试环境自动集成到测试执行中

- 理想状态：
  - 测试环境的搭建应成为测试执行的一部分，甚至嵌入测试代码本身
  - 在 Java 中，执行一个 **JUnit** 测试套件时，自动完成所需容器的创建与配置

#### 5. 实现路径

- 可直接使用容器引擎的 API 或 CLI，编写自定义的“容器驱动”
  - 注意：启动容器 ≠ 服务就绪，需额外判断
- 或者，利用 Java 生态中的现有高层抽象库，减少重复劳动

#### 6. 结语

> 是时候释放优秀集成测试的力量，摆脱历史包袱了

---

## 87. The Unreasonable Effectiveness of Fuzz Testing  

### English

------

Whether using **test-driven development** or not, programmers writing automated tests suffer from **positive test bias**: they are more likely to test that the software behaves correctly when given valid input than that the software is robust when given invalid input. As a result, our test suites fail to detect entire classes of defects. **Fuzz testing** is an unreasonably effective technique for negative testing that is easy to include in existing automated test suites. Including fuzz tests in your test-driven development process will help you build more robust systems.

For example, we were extending the software of a widely used consumer product to fetch data from web services. Although we were careful to write robust networking code and test-drove negative as well as positive cases, fuzzing immediately uncovered a surprising number of inputs that would make the software throw unexpected exceptions. Many of the standard **Java APIs** that parse data throw unchecked exceptions, so the type checker hadn’t been able to ensure that the application handled all possible parsing errors. These unexpected exceptions could leave the device in an unknown state. In a consumer device, even one that can be updated remotely, that can mean an expensive increase in customer support calls or engineer callouts.

A fuzz test generates many random inputs, feeds them into the software under test, and checks that the software continues to exhibit acceptable behavior. To provide useful coverage, a fuzzer must generate inputs that are valid enough not to be rejected by the software immediately, but invalid enough to uncover corner cases that are not covered or defects in error-handling logic.

There are two ways to approach this:

1. **Mutation-based fuzzers** mutate examples of good input to create possibly invalid test inputs.
2. **Generation-based fuzzers** generate inputs from a formal model, such as a grammar, that defines the structure of valid inputs.

**Mutation-based fuzzers** are considered impractical for black box testing because it is difficult to obtain enough samples of valid input. However, when we test-drive our code, the positive test cases provide a ready-made collection of valid inputs that exercise many of the control paths in the software. Mutation-based fuzzing becomes not just practical, but easy to apply.

Running thousands of random inputs through the entire system can take a long time. Again, if we fuzz during development, we can fuzz test particular functions of our system and design them so they can be tested in isolation. We then use fuzzing to check the correct behavior of those units and type checking to ensure that they compose correctly with the rest of the system.

Here’s an example fuzz test that, along with the type checker, ensures a **JSON** message parser will throw only the checked exceptions declared in its signature:

```java
@Test 
public void only_throws_declared_exceptions_on_unexpected_json() {
    JsonMutator mutator = new JsonMutator();
    mutator.mutate(validJsonMessages(), 1000)
    .forEach(possiblyInvalidJsonMessage -> {
        try {
            // we don't care about the parsed result in this test
            parseJsonMessage(possiblyInvalidJsonMessage);
        } catch (FormatException e) {
            // allowed
        } catch (RuntimeException t) {
            fail("unexpected exception: " + t + " for input: " + possiblyInvalidJsonMessage);
        }
    });
}
```

Fuzz testing is now an essential part of my **test-driven development** toolbox. It helps eliminate defects and guides the design of the system to be more compositional.

A simple library for doing **mutation-based fuzz testing** in **Java** and **Kotlin** projects is available on GitHub.

---

### 中文

------

无论是否使用**测试驱动开发**，编写自动化测试的程序员都受到**正向测试偏见**的影响：他们更倾向于测试软件在给定有效输入时的正确行为，而不是测试软件在给定无效输入时的健壮性。因此，我们的测试套件无法检测到整个缺陷类别。**模糊测试**是一种非常有效的负向测试技术，且易于纳入现有的自动化测试套件中。在测试驱动开发过程中加入模糊测试将帮助你构建更健壮的系统。

例如，我们在扩展一款广泛使用的消费产品软件，以便从 Web 服务获取数据。尽管我们小心编写了健壮的网络代码并进行正向和负向案例的测试驱动，但模糊测试立刻揭露了大量输入，这些输入会导致软件抛出意外的异常。许多标准的**Java API**在解析数据时会抛出未检查的异常，因此类型检查器未能确保应用程序处理所有可能的解析错误。这些意外的异常可能会导致设备处于未知状态。在消费设备中，即使是可以远程更新的设备，也可能意味着客户支持电话或工程师出差的成本大幅增加。

模糊测试会生成许多随机输入，送入待测软件中，并检查软件是否继续表现出可接受的行为。为了提供有效的覆盖，模糊器必须生成足够有效的输入，以免被软件立即拒绝，同时又要足够无效，以揭示未覆盖的边界情况或错误处理逻辑中的缺陷。

有两种方法可以实现这一点：

1. **基于变异的模糊器**：通过变异有效输入的示例来创建可能无效的测试输入。
2. **基于生成的模糊器**：通过正式模型（如语法）生成输入，该模型定义了有效输入的结构。

**基于变异的模糊器**被认为在黑箱测试中不实用，因为很难获得足够的有效输入样本。然而，当我们进行代码的测试驱动时，正向测试用例提供了一个现成的有效输入集合，涵盖了软件中的许多控制路径。基于变异的模糊测试变得不仅实用，而且容易应用。

将成千上万的随机输入通过整个系统进行测试可能需要很长时间。同样，如果我们在开发过程中进行模糊测试，可以将系统的特定功能设计为可以独立测试的单元。然后，我们使用模糊测试检查这些单元的正确行为，并通过类型检查确保它们与系统的其他部分正确组合。

下面是一个模糊测试的示例，它与类型检查器一起确保**JSON**消息解析器只会抛出其签名中声明的已检查异常：

```java
@Test 
public void only_throws_declared_exceptions_on_unexpected_json() {
    JsonMutator mutator = new JsonMutator();
    mutator.mutate(validJsonMessages(), 1000)
    .forEach(possiblyInvalidJsonMessage -> {
        try {
            // 在此测试中，我们不关心解析结果
            parseJsonMessage(possiblyInvalidJsonMessage);
        } catch (FormatException e) {
            // 允许的异常
        } catch (RuntimeException t) {
            fail("unexpected exception: " + t + " for input: " + possiblyInvalidJsonMessage);
        }
    });
}
```

模糊测试现在是我**测试驱动开发**工具箱中不可或缺的一部分。它有助于消除缺陷，并指导系统设计使其更加组合化。

有一个简单的库可用于在**Java**和**Kotlin**项目中进行**基于变异的模糊测试**，可以在 GitHub 上找到。

---

## 88. Use Coverage to Improve Your Unit Tests  

### English

------

Measuring the coverage of your tests is easier than ever. In a modern **IDE**, the button to run your tests with coverage is right next to the ones to run or debug them. The coverage results are presented class by class with little chart graphics, as well as relevant lines being highlighted in color in the source code.
 Coverage data is easy to get hold of. What is the best way to use it, though?

### When You’re Writing New Code

Most people agree that you should deliver unit tests together with all the code you write. You can argue about which order to do things in, but in my experience, what works best is short feedback loops. Write a little test code, write a little production code, and build up the functionality together with the tests.
 When I’m working like this, I will run the tests with coverage from time to time as additional insurance that I haven’t forgotten to test some new code I’ve just written.

The main danger here is that you become very satisfied with a high coverage number and don’t notice you’re missing both code and tests for a crucial piece of functionality. Perhaps you forgot to add error handling. Perhaps you missed out on a business rule. If you never wrote the production code in the first place, then coverage measurements can’t discover that for you.

### When You Have to Change Code You Didn’t Write

Modifying code that you didn’t write yourself and that has poor or missing tests can be challenging—particularly if you don’t really understand what it does but still have to change it.
 When I’m faced with this situation, test coverage is one of the ways I learn about how good the tests are and which parts I can refactor more confidently.
 I can also lean on the coverage data to discover new test cases and increase the covered areas. This can get dangerous, though. If I write tests purely to increase coverage, I can end up coupling the tests quite closely to the implementation.

### When You’re Working in a Team

One of the characteristics of a team is that you have “norms” or accepted behaviors that everyone agrees on, whether implicitly or explicitly. One of your team norms could be that you make coverage measurements part of your code and test review process. It can help you to see where tests are missing—perhaps some team members need more support and training to write better tests. It can also be encouraging when you see that complicated new functionality is well covered.
 If you regularly measure test coverage for your whole codebase, I would encourage you to look at trends more than absolute numbers.
 I’ve seen arbitrary coverage targets lead to people preferring to test only what’s easy to test. People can avoid doing refactoring because it will introduce new lines of code and lower their coverage overall. I’ve seen tests written with missing or very weak assertions just to improve the coverage numbers.

Coverage is supposed to help you improve your unit tests, and unit tests are supposed to make it easier to refactor. Coverage measurements are a tool to help you improve your unit tests and make your life easier.

---

### 中文

---

### 测量测试覆盖率变得前所未有的简单

在现代的 **IDE** 中，运行带有覆盖率的测试按钮就在运行或调试按钮旁边。覆盖率结果会按类呈现，并通过小图表和源代码中以颜色高亮的相关行显示出来。
 覆盖率数据容易获取。那么，如何才能最好地利用它呢？

### 当你编写新代码时

大多数人都同意，你应该和代码一起交付单元测试。你可以争论先做哪一件事，但根据我的经验，最有效的做法是短反馈循环：写一点测试代码，写一点生产代码，并随着测试一起构建功能。
 当我这样工作时，我会不时运行带有覆盖率的测试，作为额外的保险，确保没有忘记测试我刚写的某些新代码。

这里的主要风险是，你可能会对高覆盖率数字感到非常满意，却没有注意到缺少了某些关键功能的代码和测试。也许你忘了添加错误处理，或者错过了某个业务规则。如果你根本没有写生产代码，那么覆盖率测量也无法发现这些问题。

### 当你需要修改不是自己编写的代码时

修改自己没有写过、且缺乏或没有测试的代码是具有挑战性的，尤其是在你不太理解代码作用的情况下，但仍然需要修改它。
 当我面临这种情况时，测试覆盖率是我了解测试质量和可以更自信地重构哪些部分的方式之一。
 我还可以依靠覆盖率数据发现新的测试用例并增加覆盖的区域。不过，这可能会变得危险。如果我纯粹为了增加覆盖率而编写测试，可能会导致测试与实现的耦合过于紧密。

### 当你在团队中工作时

团队的一个特点是，大家有“规范”或一致的行为，无论是隐性还是显性地达成的共识。你的团队规范之一可能是将覆盖率测量作为代码和测试审查过程的一部分。它可以帮助你发现测试中缺失的部分，也许有些团队成员需要更多的支持和培训，以编写更好的测试。当你看到复杂的新功能得到了很好的覆盖时，这也会是一种鼓励。
 如果你定期测量整个代码库的测试覆盖率，我建议你更多关注趋势，而不是绝对数字。
 我曾见过一些任意设定的覆盖率目标，导致人们只倾向于测试那些容易测试的部分。人们可能会避免重构，因为这会引入新的代码行，从而整体降低覆盖率。我还见过一些测试，仅仅是为了提高覆盖率数字，写得缺少或极其薄弱的断言。

覆盖率的目的是帮助你改进单元测试，而单元测试则应该使重构更容易。覆盖率测量是一个工具，帮助你改进单元测试，简化工作。

---

## 89. Use Custom Identity Annotations Liberally  

### English

------

**Annotations in Java** are easy to write, easy to use, and very powerful—at least, some are. Traditionally, annotations in Java have provided a convenient way to implement aspect-oriented programming (**AOP**), a technique intended to separate out common behavioral concerns by injecting behavior at specified points in the code. However, most developers have largely abandoned **AOP** due to undesirable side effects as well as the desire to have all the code in one place—the class file.

**Identity annotations** are entirely different in that they don’t contain any functionality. Instead, they only provide programmatic information that can be used to govern, analyze, or document some aspect of the code or architecture. You can use identity annotations to identify transaction boundaries or a domain or subdomain, describe a service taxonomy, denote framework code, and employ them in dozens of other use cases.

For example, identifying classes that are part of the underlying framework (or template code in microservices) is often important so changes can be closely monitored or guarded. The following annotation does just this:

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Framework {}
@Framework
public class Logger {...}
```

Wait—this annotation does nothing! Or does it? It denotes this class as a framework-related class, meaning changes to this class can impact almost all other classes. You can write automated tests to send a notification if any framework code has changed this iteration. It also lets developers know they are modifying a class that is part of the underlying framework code.

The following is a list of other common identity annotations I use on a regular basis (all of these are specified at the class level):

```java
public @interface ServiceEntrypoint {}
```

Identifies the entry point of a microservice. It’s also used as a placeholder for other service description annotations listed below.
 Usage: `@ServiceEntrypoint`

```java
public @interface Saga {
    public Transaction[] value() {...}
}
```

Identifies services that are involved in a distributed transaction. The `Transaction` value lists the transactions that span multiple services. It’s added to classes that contain an `@ServiceEntrypoint` annotation.
 Usage: `@Saga({Transaction.CANCEL_ORDER})`

```java
public @interface ServiceDomain {
    public Domain value() {...}
}
```

Identifies the logical domain (e.g., **Payment**, **Shipping**, **Issuer**, etc.) that the service belongs to (identified by the `Domain` value). It’s added to classes that contain an `@ServiceEntrypoint` annotation.
 Usage: `@ServiceDomain(Domain.PAYMENT)`

```java
public @interface ServiceType {
    public Type value() {...}
}
```

Identifies the classification of a service. The `Type` value enumerates the defined service types (classification). It’s added to classes that contain an `@ServiceEntrypoint` annotation.
 Usage: `@ServiceType(Type.ORCHESTRATION)`

```java
public @interface SharedService {}
```

Identifies a class as one that contains common (shared) code across the application (e.g., formatters, calculators, logging, security, etc.).
 Usage: `@SharedService`

**Identity annotations** are a form of programmatic documentation. Unlike unstructured class comments, identity annotations provide a consistent means to ensure compliance or perform analytics, or they can be used to inform a developer of the context of a class or service.

For example, you can leverage annotations when writing fitness functions using **ArchUnit** to ensure all shared classes reside in the services layer of the application:

```java
@Test
public void shared_services_should_reside_in_services_layer() {
    classes().that().areAnnotatedWith(SharedService.class)
        .should().resideInAPackage("..services..").check(myClasses);
}
```

Instead of comments, consider embracing identity annotations. Use them liberally to gain information, analytics, and programmatic control over your services or applications.

---

### 中文

---

**Java 注解** 易于编写、易于使用，并且非常强大——至少有些注解是这样的。传统上，Java 注解提供了一种便捷的方式来实现面向切面编程 (**AOP**)，这是一种通过在代码中的指定位置注入行为来分离常见行为关注点的技术。然而，由于 **AOP** 存在不良副作用，且开发者更倾向于将所有代码集中在一个地方——类文件中，许多开发者已经逐渐放弃了 **AOP**。

**身份注解** 完全不同，它们不包含任何功能。相反，它们只提供程序化的信息，可以用于管理、分析或记录代码或架构的某些方面。你可以使用身份注解来识别事务边界或领域、描述服务分类、标识框架代码，并在其他多种场景中使用它们。

例如，识别属于基础框架（或微服务中的模板代码）中的类通常很重要，以便可以密切监控或保护这些类。以下注解正是为了这个目的：

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Framework {}
@Framework
public class Logger {...}
```

等一下——这个注解什么也不做！或者说，它其实有作用？它标识了这个类为框架相关的类，意味着对这个类的更改可能会影响几乎所有其他类。你可以编写自动化测试，在框架代码每次更改时发送通知。它还可以让开发者知道他们正在修改一个属于基础框架代码的类。

以下是我常用的其他常见身份注解（这些注解全部在类级别指定）：

```java
public @interface ServiceEntrypoint {}
```

标识一个微服务的入口点。它也可以作为其他服务描述注解的占位符。
 用法：`@ServiceEntrypoint`

```java
public @interface Saga {
    public Transaction[] value() {...}
}
```

标识参与分布式事务的服务。`Transaction` 值列出了跨多个服务的事务。它被添加到包含 `@ServiceEntrypoint` 注解的类中。
 用法：`@Saga({Transaction.CANCEL_ORDER})`

```java
public @interface ServiceDomain {
    public Domain value() {...}
}
```

标识服务所属的逻辑领域（例如：**支付**、**运输**、**发行商**等），由 `Domain` 值识别。它被添加到包含 `@ServiceEntrypoint` 注解的类中。
 用法：`@ServiceDomain(Domain.PAYMENT)`

```java
public @interface ServiceType {
    public Type value() {...}
}
```

标识服务的分类。`Type` 值列举了已定义的服务类型（分类）。它被添加到包含 `@ServiceEntrypoint` 注解的类中。
 用法：`@ServiceType(Type.ORCHESTRATION)`

```java
public @interface SharedService {}
```

标识一个类为包含应用程序中共享代码的类（例如：格式化程序、计算器、日志、安全性等）。
 用法：`@SharedService`

**身份注解** 是一种程序化文档的形式。与无结构的类注释不同，身份注解提供了一种一致的方式来确保合规性或执行分析，或者用于通知开发者有关类或服务的上下文信息。

例如，你可以在编写 **ArchUnit** 健全性函数时利用注解，确保所有共享类都位于应用程序的服务层：

```java
@Test
public void shared_services_should_reside_in_services_layer() {
    classes().that().areAnnotatedWith(SharedService.class)
        .should().resideInAPackage("..services..").check(myClasses);
}
```

与其使用注释，不如考虑使用身份注解。充分利用它们来获得信息、分析和对服务或应用程序的程序化控制。

---

## 90. Use Testing to Develop Better Software Faster  

### English

---

Testing your code will help you verify that your code does what you expect it to do. Tests will also help you add, change, or remove functionality without breaking anything. But testing can have additional benefits.

Merely thinking about what to test will help identify different ways the software will be used, discover things that are not clear yet, and better understand what the code should (and shouldn’t) do. Thinking about how to test these things before even starting your implementation could also improve your application’s testability and architecture. All of this will help you build a better solution before the tests and code are written.

Alongside the architecture of your system, think not only about what to test but also where to test. Business logic should be tested as close as possible to where it lives: unit tests to test small units (methods and classes), integration tests to test the integration between different components, contract tests to prevent breaking your API, etc.

Consider how to interact with your application in the context of a test, and use tools designed for that particular layer, from unit tests (e.g., **JUnit**, **TestNG**), to API (e.g., **Postman**, **REST-assured**, **RestTemplate**), to UI (e.g., **Selenium**, **Cypress**).

Keep the goal of a particular test type in mind, and use the tools for that purpose, such as **Gatling** or **JMeter** for performance tests, **Spring Cloud Contract testing** or **Pact** for contract testing, and **PITest** for mutation testing.

But it is not enough to just use those tools: they should be used as intended. You could take a hammer to a screw, but both the wood and the screw will be worse off.

Test automation is part of your system and will need to be maintained alongside production code. Make sure those tests add value, and consider the cost of running and maintaining them.

Tests should be reliable and increase confidence. If a test is flaky, either fix it or delete it. Don’t ignore it—you’ll waste time later wondering why that test is being ignored. Delete tests (and code) that are no longer valuable.

A failing test should tell you exactly what is wrong quickly, without you having to spend a lot of time analyzing the failure. This means:

- Each test should test one thing.
- Use meaningful, descriptive names. Don’t just describe what the test does either (we can read the code); tell us why it does this. This can help decide whether a test should be updated inline with changed functionality or whether an actual failure that should be fixed has been found.

**Matcher libraries**, such as **Hamcrest**, can help provide detailed information about the difference between expected and actual results.

Never trust a test you haven’t seen fail.

Not everything can (or should) be automated. No tool can tell you what it’s actually like to use your application. Don’t be afraid to fire up your application and explore; humans are way better at noticing things that are slightly “off” than machines. And besides, not everything will be worth the effort of automating.

Testing should give you the right feedback at the right time to provide enough confidence to take the next step in your software development life cycle, from committing to merging to deploying and unlocking features. Doing this well will help you deliver better software faster.

---

### 中文

---

**测试代码**有助于验证代码是否按照预期执行。测试还能帮助在添加、修改或删除功能时避免破坏已有功能。但测试还有额外的好处。

单单思考应该测试什么，有助于识别软件可能的使用方式，发现尚未明确的问题，更好地理解代码应当（或不应当）做什么。在开始实现之前思考如何测试这些内容，也能提高应用程序的可测试性和架构。所有这些都能帮助你在编写测试和代码之前构建一个更好的解决方案。

在设计系统架构时，不仅要考虑测试什么，还要考虑在哪里测试。业务逻辑应尽可能接近其实际位置进行测试：单元测试用于测试小单元（方法和类），集成测试用于测试不同组件之间的集成，契约测试用于防止API被破坏等。

考虑如何在测试的上下文中与应用程序进行交互，并使用为特定层设计的工具，从单元测试（如**JUnit**、**TestNG**），到API（如**Postman**、**REST-assured**、**RestTemplate**），再到UI（如**Selenium**、**Cypress**）。

牢记每种测试类型的目标，并使用相应工具，例如：**Gatling** 或 **JMeter** 用于性能测试，**Spring Cloud Contract** 或 **Pact** 用于契约测试，**PITest** 用于变异测试。

但仅仅使用这些工具还不够：它们应该按预期使用。就像用锤子去拧螺丝一样，木头和螺丝都会受损。

**测试自动化**是系统的一部分，需要与生产代码一起维护。确保这些测试能带来价值，并考虑运行和维护它们的成本。

测试应具有可靠性并增强信心。如果某个测试不稳定，要么修复它，要么删除它。不要忽视它——你以后会浪费时间去想为什么忽视了这个测试。删除不再有价值的测试（和代码）。

**失败的测试**应能迅速告诉你问题出在哪里，而不需要你花费大量时间分析失败原因。这意味着：

- 每个测试应只测试一件事。
- 使用有意义的描述性名称。不要只描述测试做了什么（我们可以读代码）；告诉我们它为什么这样做。这有助于判断测试是否应该根据功能变化进行更新，或者是否发现了真正需要修复的失败。

**匹配器库**（如 **Hamcrest**）可以帮助提供期望结果与实际结果之间差异的详细信息。

**永远不要相信你没见过失败的测试**。

并非所有东西都可以（或应该）自动化。没有任何工具能告诉你实际使用应用程序的感受。不要害怕启动应用程序进行探索；人类在发现稍微“偏离”的问题时远比机器更敏感。而且，并不是所有的内容都值得自动化。

测试应能在合适的时间提供正确的反馈，让你有足够的信心进行下一步的开发工作，从提交到合并，再到部署和解锁功能。做好这些，将有助于更快速地交付更好的软件。

---

## 91. Using Object-Oriented Principles in Test Code  

### English

---

When writing test code, it’s important to exercise the same care that you’d use when developing production code. Here are common ways to use object-oriented (OO) principles when implementing test code.

### Encapsulation

The **Page Object Model** design pattern is commonly used in test automation. This pattern prescribes creating a class to interact with a page of the application under test. Within this class are the locator objects for the elements of the web page and the methods to interact with those elements.

It’s best to properly encapsulate by restricting access to the locators themselves and only exposing their corresponding methods:

```java
public class SearchPage {
    private WebDriver driver;
    private By searchButton = By.id("searchButton");
    private By queryField = By.id("query");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String query) {
        driver.findElement(queryField).sendKeys(query);
        driver.findElement(searchButton).click();
    }
}
```

### Inheritance

While inheritance should not be abused, it can certainly be useful in test code. For example, given there are header and footer components that exist on every page, it’s redundant to create fields and methods for interacting with these components within every Page Object class.

Instead, create a base Page class containing the common members that exist on every page, and have your Page Object classes inherit from this class. Your test code will now have access to anything in the header and footer no matter what Page Object they are currently interacting with.

Another good use case for inheritance within test code is when a given page has various implementations. For example, your app may contain a **User Profile** page that has different functionality based on roles (e.g., Administrator, Member). While there are differences, there could also be overlap. Duplicating code across two classes is not ideal. Instead, create a **ProfilePage** class that contains the common elements/interactions, and create subclasses (e.g., **AdminProfilePage**, **MemberProfilePage**) that implement the unique interactions and inherit the common ones.

### Polymorphism

Assume we have a convenience method that goes to the User Profile page. This method doesn’t know what type of profile page it is—an Administrator or a Member.

You’re faced with a design decision here. Do you make two methods—one for each of the profile types? This seems like overkill since they both would do the exact same thing but just have a different return type.

Instead, return the superclass (**ProfilePage**) since both **AdminProfilePage** and **MemberProfilePage** are both subclasses of **ProfilePage**. The test method that is calling this convenience method has more context and can cast accordingly:

```java
@Test
public void badge_exists_on_admin_profile() {
    var adminProfile = (AdminProfilePage) page.goToProfile("@admin");
    ...
}
```

### Abstraction

Abstraction is used sparingly in test code, but there are valid use cases. Consider a type of widget that has been customized for different usages throughout the app. Creating an abstract class that specifies the behaviors expected is helpful when developing classes that interact with specific implementations of that widget:

```java
public abstract class ListWidget {
    protected abstract List<WebElement> getItems();

    int getNumberOfItems() {
        return getItems().size();
    }
}

public class ProductList extends ListWidget {
    private By productLocator = By.cssSelector(".product-item");

    @Override
    protected List<WebElement> getItems() {
        return driver.findElements(productLocator);
    }
}
```

Test code is indeed code, meaning that it has to be maintained, enhanced, and scaled. Therefore, it’s in your best interest to follow good programming practices when developing it—including the foundational OO principles.

---

### 中文

---

在编写测试代码时，重要的是要像编写生产代码一样谨慎。以下是实现测试代码时使用面向对象（OO）原则的常见方式。

### 封装

**Page Object Model**（页面对象模型）设计模式在测试自动化中常用。该模式建议创建一个类来与被测试应用的页面进行交互。在这个类中包含了页面元素的定位器对象以及与这些元素交互的方法。

最好通过限制对定位器本身的访问，并仅公开相应的方法来实现正确的封装：

```java
public class SearchPage {
    private WebDriver driver;
    private By searchButton = By.id("searchButton");
    private By queryField = By.id("query");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String query) {
        driver.findElement(queryField).sendKeys(query);
        driver.findElement(searchButton).click();
    }
}
```

### 继承

虽然继承不应滥用，但在测试代码中是非常有用的。例如，假设每个页面上都有头部和底部组件，如果每个页面对象类都创建字段和方法来与这些组件交互，那就显得非常冗余。

可以创建一个基础的 Page 类，包含每个页面上都有的公共成员，然后让页面对象类继承这个类。这样，不管当前正在与哪个页面对象交互，测试代码都可以访问头部和底部的所有内容。

在测试代码中，继承的另一个良好用例是当一个页面有多种实现时。例如，您的应用可能包含一个 **用户个人资料** 页面，根据角色（例如，管理员、会员）有不同的功能。虽然它们之间有差异，但也可能有重叠。跨两个类复制代码并不理想。相反，可以创建一个 **ProfilePage**（个人资料页）类，包含公共元素/交互，并创建子类（例如，**AdminProfilePage**、**MemberProfilePage**）来实现特有的交互，并继承公共部分。

### 多态

假设我们有一个便利方法，进入用户个人资料页面。该方法并不知道它是哪个类型的个人资料页面——管理员还是会员。

在这里，你面临一个设计决策。你是做两个方法——每个类型一个吗？这似乎有些过头，因为它们都会做相同的事情，只是返回类型不同。

相反，可以返回父类（**ProfilePage**），因为 **AdminProfilePage** 和 **MemberProfilePage** 都是 **ProfilePage** 的子类。调用此便利方法的测试方法有更多上下文，并且可以根据需要进行类型转换：

```java
@Test
public void badge_exists_on_admin_profile() {
    var adminProfile = (AdminProfilePage) page.goToProfile("@admin");
    ...
}
```

### 抽象

抽象在测试代码中使用较少，但确实有有效的用例。考虑一个在应用中根据不同用途定制的部件。创建一个抽象类，指定预期的行为，当开发与该部件的具体实现交互的类时会非常有帮助：

```java
public abstract class ListWidget {
    protected abstract List<WebElement> getItems();

    int getNumberOfItems() {
        return getItems().size();
    }
}

public class ProductList extends ListWidget {
    private By productLocator = By.cssSelector(".product-item");

    @Override
    protected List<WebElement> getItems() {
        return driver.findElements(productLocator);
    }
}
```

测试代码确实是代码，这意味着它需要被维护、增强和扩展。因此，在开发时，遵循良好的编程实践是非常有益的，包括面向对象的基本原则。

---

## 92. Using the Power of Community to Enhance Your Career  

### English

---

It’s no longer enough to just be a great **Java** developer. If you want to advance your career, you need to be blogging, speaking at conferences, engaging on social media, committing to open source, and the list goes on. This can feel like a daunting task and you’re probably asking yourself, “Why? Why is my technical ability not enough?” Well, the short answer is that a lot of the time, the people making decisions about your career will never see your code. You need to ensure that those people are hearing and seeing your name.

### The Silver Lining

You don’t need to do it all, and there are communities to help you along the way. If the idea of standing on a stage in front of 10, 50, 100, or more people literally puts you into a panic attack, don’t do it. On the other hand, if you’re nervous and feel like you’ve got nothing to say, that’s something that a community can help with. Have you ever fixed an issue you’ve been fighting with and thought to yourself, “If only I could have learned from someone who’s done this already”? Everyone has these thoughts; they make for great subjects to cover in a talk or blog post.

If it’s the fear of talking on stage, then start off small: present something to your team before submitting to a local **Java User Group** (**JUG**) or conference.

### How Can Community Help?

As well as building your profile, another reason why engaging in the community is so valuable is the content shared and conversations had. **Technology** is moving so fast that being a part of a community means you don’t need to wait for a book to be published to get access to great content. The people writing those books, researching the latest technologies, are sharing their insights at community events, on blogs, and discussing them on forums.

The people in the communities that you’re likely already involved with can all help you become better. From the speakers to the attendees, the things you learn from each other are sometimes more valuable than the overarching content of the event. Don’t be afraid to ask questions of everyone in the room. **Thought leadership** can be shared in so many ways, and the people sitting next to you may have the answers you’ve been looking for.

If you’re from a location that does not have a thriving **Java** community, don’t panic—check out the **Virtual JUG**.

### Looking for Your Next Challenge?

If you’re after a new challenge, then community can really help you in your job search. If a hiring manager can avoid looking through the hundred applications on their desk to hire someone they know will fit in the team with the right skills, they’ll do it.

What’s the best way to get to the top of the pile? Find ways to interact outside of the application process. Meeting in person at local user groups will also allow you to understand what it’s really like to work with the team. None of this candy-coated interview process only to find out on your first day that you’re not in an environment that’s right for you.

This circles back to where we started: the people making decisions about your career don’t always see your code!

---

### 中文

---

仅仅成为一名优秀的 **Java** 开发者已经不再足够。如果你想在职业生涯中取得进展，你需要写博客、在会议上发言、参与社交媒体互动、贡献开源代码等等。这个任务可能让人感到压倒性，你可能会问自己，“为什么？为什么仅凭我的技术能力还不够？”简单的回答是，很多时候，决定你职业生涯的人根本不会看到你的代码。你需要确保这些人听到并看到你的名字。

### 一线希望

你不必做所有的事情，社区可以帮助你。 如果站在一个有 10、50、100 或更多人面前的想法会让你感到恐慌，那就不要做。另一方面，如果你感到紧张，觉得自己没有什么可以说的，那就是社区可以帮忙的地方。你是否曾经解决了一个一直困扰你的问题，并想过，“如果我能从一个已经做过这个的人那里学习就好了”？每个人都有这种想法；这些都可以成为讲座或博客文章的好题材。

如果你害怕在舞台上讲话，那么可以从小做起：在提交给本地 **Java 用户组**（**JUG**）或会议之前，先向你的团队展示一些东西。

### 社区如何帮助？

除了建立个人资料外，参与社区的另一个重要原因是共享的内容和讨论。**技术**发展得非常快，作为社区的一部分，你不需要等待书籍出版就能获得优秀的内容。那些写书、研究最新技术的人，会在社区活动、博客和论坛上分享他们的见解。

你可能已经参与的社区中的人都可以帮助你变得更好。从讲师到与会者，彼此之间学到的东西有时比活动的总体内容更有价值。不要害怕向在场的每个人提问。**思想领导力**可以通过许多方式分享，坐在你旁边的人可能拥有你一直在寻找的答案。

如果你所在的地方没有繁荣的 **Java** 社区，不要惊慌——可以查看 **虚拟 JUG**。

### 寻找下一个挑战？

如果你在寻找新的挑战，那么社区在找工作时可以真正帮助你。如果招聘经理能够避免查看桌上堆积如山的简历，而是选择一个自己知道具备合适技能并能融入团队的人，他们会这么做。

如何让自己脱颖而出？找到与申请过程外的互动方式。参加本地用户组的面对面会议也能帮助你了解与团队一起工作的真实情况。避免那种粉饰过的面试过程，最终发现你第一天上班时就发现自己并不适合这个环境。

这又回到了我们最初说的：做出职业生涯决策的人并不总是能看到你的代码！

---

## 93. What Is the JCP Program and How to Participate  

### English

---

The **Java Community Process (JCP)** Program is the process by which the international Java community standardizes and ratifies the specifications for Java technologies. The JCP Program ensures high-quality specifications are developed using an inclusive, consensus-based approach. Specifications ratified by the JCP Program must be accompanied by a **Reference Implementation** (to prove the Specification can be implemented) and a **Technology Compatibility Kit** (a suite of tests, tools, and documentation used to test implementations for compliance with the Specification).

Experience has shown that the best way to produce a technology specification is to use an open and inclusive process to develop a specification and implementation, informed by a group of industry experts with a variety of viewpoints. This also includes giving the community opportunities to review and comment, and also a strong technical lead to ensure the technical goals are met and the specification integrates with other relevant specifications.

An **Executive Committee (EC)** representing a cross-section of major stakeholders—such as Java vendors, large financial institutions utilizing Java to run their business, open source groups, and other members of the Java community, including individuals and user groups—is responsible for approving the passage of Specifications through the JCP Program’s various stages and for reconciling discrepancies between Specifications and their associated test suites.

After being introduced in 1999, the JCP Program has continued to evolve over time using the process itself, through an effort called **JCP.next**, with the work being carried out in the open by the JCP EC. **JCP.next** is a series of **Java Specification Requests (JSRs)** designed to focus on transparency, streamlining the JCP program, and broadening its membership. These JSRs modify the JCP’s processes by modifying the **JCP Process Document**. Once the changes are complete, they apply to all new JSRs and to future Maintenance Releases of existing JSRs for the Java platform.

For example, **JSR 364**, Broadening JCP Membership, was put into effect as **JCP version 2.10**. This JSR broadened JCP participation by defining new membership classes, enabling greater participation by the community, and helping ensure the appropriate intellectual property commitments from JCP Members. Any Java developer can join the JCP Program, and depending on the type of membership, JCP Members can participate as a **JSR Spec Lead**, **Expert Group Member**, or **Contributor**.

**JSR 387**, Streamline the JCP Program, was put into effect as version **2.11**. This JSR streamlines the JSR life cycle process to bring it in line with the way Java technology is developed today, specifically enabling JSRs to be able to complete and keep up with the six-month Java platform release cycle cadence. Through this JSR we also resized the JCP EC.

With many changes in the Java community, the continuation of the JCP Program remains constant. Anyone can apply to join and participate in the JCP Program—either as a Corporation or Non-Profit (Full Member), **Java User Group (Partner Member)**, or Individual (Associate Member). The stability of the JCP Program and participation from community members ensures continued success of the Java platform and its future. Standards enable execution of technical strategies, and the JCP enables collaboration of industry and participation from the developer community.

**Compatibility matters**—the **Spec**, **RI**, and **TCK** required by the JCP Program enable an ecosystem to be built up around Java technologies. The JCP Program provides the foundation and structure for this—IP rights and obligations are covered, and choice in implementations that pass the TCK benefits the ecosystem—this is key to success and continued popularity of Java technology.

---

### 中文

---

### Java社区过程（JCP）简介

**Java社区过程（JCP）** 是国际Java社区用来标准化和批准Java技术规范的流程。JCP程序确保通过包容性和共识基础的方式开发出高质量的规范。被JCP程序批准的规范必须附带**参考实现**（证明该规范可以被实现）和**技术兼容性套件**（一套测试、工具和文档，用于测试实现是否符合规范）。

### 规范的制定方式

最佳的技术规范制定方式是使用开放和包容的过程，通过一组来自不同领域的行业专家共同开发规范和实现。这一过程还包括让社区有机会进行审查和评论，同时需要强有力的技术领导，确保技术目标得以实现，并且规范能够与其他相关规范兼容。

### 执行委员会（EC）

**执行委员会（EC）** 由主要利益相关者组成，包括Java供应商、大型金融机构、开源团体以及其他Java社区成员（如个人和用户组）。执行委员会负责批准规范通过JCP程序的各个阶段，并调解规范与其相关测试套件之间的差异。

### JCP程序的发展

自1999年引入以来，JCP程序通过自身的过程不断发展，其中包括名为**JCP.next**的努力。JCP.next通过开放的方式进行，目的是增强透明度、简化JCP程序并扩大其会员群体。**Java规范请求（JSR）**是用来修改JCP流程的，修改后的流程适用于所有新的JSR以及现有JSR的维护版本。

### 重要的JSR实例

- **JSR 364**，扩展JCP会员：该JSR已被纳入**JCP版本2.10**，通过定义新的会员类别，扩大了社区参与度，确保JCP会员能够履行适当的知识产权承诺。
- **JSR 387**，简化JCP程序：该JSR已被纳入**版本2.11**，通过简化JSR生命周期过程，使其能够跟上Java平台每六个月发布的节奏。

### JCP程序的参与和影响

任何人都可以申请加入JCP程序，无论是作为公司、非营利组织（正式会员）、Java用户组（合作伙伴会员）还是个人（助理会员）。JCP程序的稳定性和社区成员的参与确保了Java平台的持续成功和未来发展。

### 兼容性至关重要

JCP程序要求的**规范**、**参考实现（RI）**和**技术兼容性套件（TCK）**使Java技术能够围绕着生态系统构建。JCP程序为此提供了基础和结构，涵盖了知识产权权利和义务，而通过TCK测试的实现方案为生态系统带来更多选择，这对于Java技术的成功和持续流行至关重要。

---

## 94. Why I Don’t Hold Any Value in Certifications  

### English

---

Some time back—it must have been around the mid-noughties—one of my friends had taken and passed the **Java Certified Programmer** exam with an impressive score of 98%. Eager to keep up, I took one of the practice tests during a lunch break and, although I didn’t score as high, I got a passing grade. One question on the exam has always stuck in my mind. It was to do with the inheritance hierarchy in **Swing** applications, something I had no problem answering as my day job was working with **Swing**, but it did strike me as odd to ask something that could easily be looked up in your **IDE**. I never did get around to taking the exam, mostly due to being partway through studying for my master’s degree at the time.

Fast-forward a few years, and I had just started a new job. During the first week, I was asked by one of my new colleagues if I was **Java 5** certified. “No,” I replied, “but I have been using it for the last year.” Turns out he was certified, so good news for me that someone on my team would have a base level of knowledge and skill. It was less than two weeks later that he asked why we have to bother overriding **hashCode** when we override **equals**. He genuinely didn’t understand the relationship between the two methods. This was just the tip of what he didn’t know, yet he was certified!

Fast-forward another few years, and I’m contracting at a place where the company policy was that every permanent employee be certified, at least to what was then the **Java Certified Programmer** level. I did meet some good developers there, and good developers had passed through the ranks, but there were some truly awful developers as well—all of whom were certified.

A quick look at the **Oracle** site for **Java Certification** tells you that being certified will “Help you position yourself with validation that you possess the full skill set and knowledge to be a Professional **Java Developer**” and “Earn you more credibility, help you perform better in your daily job, and lead your team and company forward.” Rubbish. Being a “professional developer” and performing “better in your day job” have little to do with what you’ll need in order to become certified. You can learn enough to pass the exams without ever writing a line of code. As an industry, we can’t even definitively tell you what “good” and “bad” are, so a piece of paper claiming to do so is worthless.

There are, of course, exceptions to every rule. I have met a few people—well, at least one—who have used **Java certification** as a way to bolster their own knowledge. They used it as a way to learn things they otherwise wouldn’t have had to as part of their day job, and to those people I take my hat off. In over twenty years of writing software professionally, one thing about certifications has never changed: the good developers don’t need it, but the bad ones can easily achieve it.

---

### 中文

---

有一段时间，大概在2000年代中期，我的一位朋友通过了**Java认证程序员**考试，并且得到了令人印象深刻的98%分数。为了跟上进度，我在午休时做了一次模拟测试，尽管我的分数没有那么高，但还是通过了考试。有一道题让我印象深刻，关于**Swing**应用程序中的继承层次结构。作为日常工作的内容，我对这个问题并不陌生，但我觉得问这样的问题有些奇怪，因为它完全可以通过**IDE**查找答案。我最终并没有去参加正式考试，主要是因为当时正忙于攻读硕士学位。

几年后，我开始了新工作。第一周时，一位新同事问我是否有**Java 5**认证。“没有，”我回答，“但我已经使用了快一年了。”结果他有认证，这对我来说是个好消息，至少团队里有一个人具备基本的知识和技能。不足两周后，他问我为什么在重写**equals**时还要重写**hashCode**。他居然完全不理解这两个方法之间的关系。这只是他不知道的冰山一角，而他居然有认证！

再过几年，我在一家公司做合同工，公司的政策要求每个正式员工至少拥有当时的**Java认证程序员**级别的认证。我遇到了一些优秀的开发者，确实有一些优秀的开发人员曾在这里工作过，但也有一些糟糕的开发人员——他们都有认证。

快速查看一下**Oracle**网站的**Java认证**页面，会看到认证能“帮助你证明自己拥有成为专业**Java开发者**所需的完整技能和知识”，“获得更多的可信度，帮助你在日常工作中表现更好，推动团队和公司向前发展。”这些话毫无意义。成为“专业开发者”和在“日常工作中表现更好”与认证要求并无太大关系。你完全可以学到足够的知识通过考试，而不写一行代码。作为行业，我们甚至无法明确告诉你什么是“好”或“坏”，因此一张声称能判断这些的纸毫无价值。

当然，每条规则都有例外。我遇到过一些人——至少有一个——把**Java认证**当作提升自己知识的方式。他们通过认证学习了在日常工作中不会接触到的内容，对这些人我表示敬意。在我二十多年的软件开发职业生涯中，关于认证的一点从未改变：优秀的开发者不需要认证，而糟糕的开发者却能轻松获得认证。

---

## 95. Write One-Sentence Documentation Comments  

### English

---

>  A common fallacy is to assume authors of incomprehensible code will somehow be able to express themselves lucidly and clearly in comments.
>  —Kevlin Henney

You’re probably either writing too many comments in your code, or none at all. Too many generally means too many to maintain, which risk becoming dangerously inaccurate comments that you’re better off deleting. Too many is also likely to mean that they’re badly written and unimproved, because it’s hard to write “lucidly and clearly.” None at all means relying on perfect naming, code structure, and tests, which is even harder than it sounds.

We’ve all seen a lot of code whose authors didn’t write any comments at all, whether to save time, because they didn’t want to, or because they thought their code was self-documenting. Sometimes code really is that well written: the first thousand lines of a new project, the hobby project written in artisanal handcrafted code, and maybe the mature well-maintained library project whose narrow focus keeps the codebase small.

Large applications are different, especially enterprise business applications. Comments are a problem when you’re maintaining 100,000 lines of code that other people wrote and are still adding to. That code isn’t all going to be perfect, and needs some explanation. The hard question is how much explanation: how many comments?

The answer to commenting large application codebases is to write one-sentence documentation comments, as follows:

1. Write the best code you can.
2. Write a one-sentence documentation comment for every public class and method/function.
3. Refactor the code.
4. Delete unnecessary comments.
5. Rewrite bad comments (because all good writing requires rewriting).
6. Only add detail where absolutely necessary.

This approach helps you discover which comments are necessary, either because the code cannot explain things like why it exists or because you haven’t had time to refactor it yet. You find out when you write the one-sentence comment: if a good comment takes several minutes to write, then it’s necessary and will save you and other readers time in the future.

If you wrote a good comment as fast as you can type, then you identified “obvious” code that doesn’t need the comment, which you must immediately delete. The trick is that this discovery requires actually writing the comment, however obvious you think the code is, and especially if you wrote it yourself. Do not skip this step!

You always need a minimum number of comments that comment only what the code cannot say, answering the why questions that you can’t answer in code. Limiting these to one sentence per public interface makes the writing, code review, and maintenance effort realistic, and lets you focus on quality and brevity.

Don’t write more than one sentence unless you really have to. There might be more why questions, unusual complexity, or obscure domain language jargon to explain, especially abbreviations. Delegate where you can: problem domains often have Wikipedia pages you can link to.

Comments are amazingly useful if they’re good, mainly because we spend more time reading code than writing it. Comments are also the only feature common to all general programming languages. When programming, use the best language for the job. Sometimes, it’s English.

---

### 中文

---

> 一个常见的错误是认为写不清楚代码的作者能够在注释中清晰地表达自己。
>  —Kevlin Henney

你可能要么写了太多注释，要么根本没有写注释。太多的注释通常意味着它们太多，难以维护，最终可能变得不准确，反而不如删除掉。太多的注释也可能意味着它们写得很糟糕，没有经过改进，因为写“清晰明了”是很难的。完全没有注释则意味着依赖完美的命名、代码结构和测试，这比听起来还要难。

我们都见过很多没有写注释的代码，无论是为了节省时间、因为不想写，还是因为认为代码本身就能自解释。有时候，代码确实写得那么好：新项目的前千行代码，手工编写的小项目，或者可能是维护良好的库项目，其专注的范围使得代码库保持得很小。

大规模应用程序则不同，尤其是企业级应用程序。当你维护100,000行别人写的代码，并且这些代码仍在不断增加时，注释就成了一个问题。这些代码不可能完美无缺，需要一些解释。难题是需要多少解释：写多少注释？

评论大型应用程序代码库的答案是写一句话的文档注释，具体如下：

1. 写出你能写的最好代码。
2. 为每个公共类和方法/函数写一句话的文档注释。
3. 重构代码。
4. 删除不必要的注释。
5. 重写糟糕的注释（因为所有好的写作都需要重写）。
6. 只有在绝对必要时才添加详细信息。

这种方法有助于你发现哪些注释是必要的，要么是因为代码无法解释其存在的原因，要么是因为你还没来得及重构它。你在写一句话的注释时会发现：如果一个好的注释需要几分钟才能写出来，那它是必要的，将来能节省你和其他读者的时间。

如果你写一个好的注释快得像打字一样，那你就找到了“显而易见”的代码，这些代码不需要注释，你必须立即删除它。诀窍是，这个发现需要实际写出注释，无论你认为代码有多么显而易见，尤其是如果是你自己写的代码。不要跳过这一步！

你总是需要一个最小数量的注释，这些注释只说明代码不能解释的内容，回答代码中无法回答的“为什么”的问题。将这些注释限制为每个公共接口一句话，使得编写、代码审查和维护工作变得现实，并让你专注于质量和简洁。

除非确实需要，否则不要写超过一句话的注释。可能会有更多的“为什么”问题，或者需要解释不寻常的复杂性、晦涩的领域语言术语，特别是缩写。你可以委托别人：问题领域通常有可以链接到的维基百科页面。

如果注释写得好，它们是非常有用的，主要是因为我们花费更多时间在阅读代码上，而不是写代码。注释也是所有通用编程语言中唯一共同的特性。编程时，使用最适合的语言。 有时候，它是英语。

---

## 96. Write “Readable Code”  

### English

---

We have all heard that good code is “readable,” but what does that really mean?

The first principle of readability is to keep the code simple. Avoid lengthy methods and functions; instead, break them into smaller pieces. Name the pieces for what they do.

Automate your coding standards so you can test them in your deployment pipeline. For example, you could fail your build if you have a method of more than 20 to 30 lines of code, or parameter lists of more than 5 or 6 parameters.

Another way toward better readability is to take “readable” literally. Don’t interpret it as meaning “Can I read my code five minutes after I wrote it?” Rather, try to write code that a nonprogrammer could understand.

Here is a simple function:

```java
void function(X x, String a, double b, double c) {
    double r = method1(a, b);
    x.function1(a, r);
}
```

What does it do? Without looking into the implementation of `X` and `method1`, you have no way of telling, programmer or not.

But if instead I wrote this:

```java
void displayPercentage(Display display, String message, double value, double percentage) {
    double result = calculatePercentage(value, percentage);
    display.show(message, result);
}
```

It would be clear what was going on. Even a nonprogrammer could probably guess from the names what is happening here. Some things are still hidden—we don’t know how the `display` works or how the percentage is calculated—but that is a good thing. We can understand what this code is attempting to do.

For simple examples like this, this kind of change looks too trivial to discuss, but how much of the code you see at work looks like this?

Taking naming seriously, combined with simple refactoring techniques, allows you to quickly gain deeper insight into what is happening in your code.

Here is another example, in this case from some real-world code:

```java
if (unlikely(!ci)) { // 361 lines of code
} else {
    // 45 lines of 'else'
}
```

Highlight the `unlikely(!ci)` and create a new method called `noConnection`.

Highlight the 361 lines in the `if` statement and name it `createConnection`, and you end up with:

```java
if (noConnection(ci)) {
    ci = createConnection();
} else {
    // 45 lines of 'else'
}
```

Naming things sensibly, even if that means pulling out a function that is only used once in order to name it, creates clarity in code that is missing otherwise. It will also often highlight the fact that there are significant opportunities to simplify the code. In this example, there were five other places in the same file that could have reused the new `createConnection` method. I would take this further and rename `ci` to `connection` or something more appropriate.

Because we have improved the code’s modularity, this approach also gives us more options for further change. For example, we could now decide to hide some more of the complexity in this method and simply use the connection, whether created here for the first time or not:

```java
ci = createConnection(ci);
```

Make functions and methods simple. Make all names meaningful in the context of the problem you are solving: functions, methods, variables, parameters, constants, fields, anything!

Imagine your nontechnical grandpa or grandma reading the code: could they guess at what it was doing? If not, make the code simpler through refactoring, and more expressive through the selection of good names.

---

### 中文

---

我们都听说过“好的代码是可读的”，但这到底是什么意思？

### 可读性的第一个原则是保持代码简单。

避免编写过长的方法和函数；相反，将它们拆分成更小的部分，并为这些部分命名，使其能准确描述功能。

### 自动化编码标准，以便在部署管道中进行测试。

例如，您可以设置规则，如果某个方法的代码行数超过20到30行，或参数列表超过5或6个参数，则使构建失败。

### 另一种提高可读性的方法是字面理解“可读性”。

不要把它理解为“我写完代码五分钟后能读懂吗？” 而是要尝试编写一个非程序员也能理解的代码。

### 示例 1：

```java
void function(X x, String a, double b, double c) {
    double r = method1(a, b);
    x.function1(a, r);
}
```

这段代码做了什么？如果不查看 `X` 和 `method1` 的实现，您是无法知道的，不论您是否是程序员。

但如果我改写成这样：

```java
void displayPercentage(Display display, String message, double value, double percentage) {
    double result = calculatePercentage(value, percentage);
    display.show(message, result);
}
```

现在就清楚了。即使是非程序员，也能从名称中大致猜出它的作用。尽管某些细节仍然隐藏——我们不知道 `display` 是如何工作的，或者百分比是如何计算的——但这是好事。我们可以理解这段代码的意图。

对于像这样的简单示例，改动看起来微不足道，但您在实际工作中看到的代码大部分也是这样吗？

认真对待命名，结合简单的重构技巧，可以帮助您快速深入了解代码的实际情况。

### 示例 2：

这是来自实际代码的另一个例子：

```java
if (unlikely(!ci)) { // 361行代码
} else {
    // 45行 'else'
}
```

将 `unlikely(!ci)` 提取并创建一个名为 `noConnection` 的新方法。

将 `if` 语句中的361行代码提取并命名为 `createConnection`，然后结果是：

```java
if (noConnection(ci)) {
    ci = createConnection();
} else {
    // 45行 'else'
}
```

为事物命名时要有意义，即使这意味着提取一个仅使用一次的函数并命名它，也能为代码带来清晰度，否则这些清晰度是缺失的。这也通常会突显出代码简化的机会。在这个例子中，文件中有五个地方也可以复用新的 `createConnection` 方法。我会进一步将 `ci` 重命名为 `connection` 或更合适的名字。

通过提高代码的模块化，这种方法也为我们提供了更多的修改选项。例如，我们现在可以选择隐藏该方法中的更多复杂性，只使用连接，无论它是否是第一次创建：

```java
ci = createConnection(ci);
```

### 总结：

1. **保持函数和方法简单**：使所有名称在解决问题的上下文中有意义：函数、方法、变量、参数、常量、字段等。
2. **命名合理**：想象一下您的非技术性祖父母或祖母在阅读这段代码时，能否猜出它在做什么？如果不能，通过重构使代码更简单，通过选择好的名称使其更具表现力。

---

## 97. The Young, the Old, and the Garbage   

### English

---

One of the major advantages of Java is that developers have not had to worry (much) about memory. In contrast to many other languages around at the time of its launch, Java has, since the beginning, freed unused memory automatically. But that doesn’t mean Java developers don’t need to know the basics of how Java handles memory. There can still be memory leaks and bottlenecks.

Java divides its memory into two segments:

- **Heap**: Instances, variables…your data
- **Nonheap/perm**: Code, metadata…for the JVM

To care about memory in Java, we should focus on the heap. It is divided into two generations depending on their lifetime: **young** and **old**. The **young generation** (aka the nursery) contains short-lived objects. The **old generation** contains structures that have survived longer.

The young generation is divided in two:

- **Eden**: Where objects are created
- **Survivor**: An in-between, limbo state through which an instance will pass when moving from the young to the old space

### The Garbage Collector

The garbage collector (GC) is the system cleaning the memory. There are different implementations, but in general it performs two tasks:

- **Minor collection**: Reviews the young generation
- **Major collection**: Reviews all memory, young and old

The GC runs at the same time as the normal app execution. Each execution of the GC involves a pause (usually milliseconds) in all running threads. While your application remains healthy, the GC usually limits its actions to minor collections as not to interfere with it.

### GC Strategies

For proper operation and cleaning of memory, we should have small, short-lived objects rather than objects that live a long time. The temporary objects will stay in Eden, so the GC will remove them earlier and faster.

Having unused objects in memory doesn’t disrupt the execution of your app, but it may affect your hardware performance. It may also slow down the GC execution, as it will process them over and over again on each execution.

It may seem tempting to force a GC execution by calling `System.gc()`. However, this will force a major collection, disrupting heuristics and stopping your application while this collection lasts.

### References

The GC frees instances that are no longer referenced, meaning if you create an instance with an attribute referencing a second instance, both instances will be either removed at the same time or never. The more cross-referenced instances, the more complex and error-prone the GC task is. You can help the GC by nulling attributes on objects to break links between instances.

All static objects live forever. This means all their referenced attributes will also live forever.

To help the GC collect unwanted objects, there are special types of references whose corresponding classes can be found in `java.lang.ref`:

- **WeakReference**: Does not count as a reference for cleanup. For example, we can use `WeakHashMap`, which works as a `HashMap`, but using weak references. So, if the map contains an object that is only referenced in the map, it can be removed.
- **SoftReference**: The GC respects the link and removes the instance, depending on demand for memory.
- **PhantomReference**: Always returns null. The link doesn’t really point to the object. Used to clear instances before taking the object that binds it.

Remember that the garbage collector is your friend. It tries to make your life easier. You can return the favor by making its job easier too.

---

### 中文

---

Java 的主要优点之一是开发者不需要太担心内存管理。与 Java 推出时的许多其他编程语言不同，Java 从一开始就能够自动回收未使用的内存。但这并不意味着 Java 开发者不需要了解 Java 如何处理内存，依然可能出现内存泄漏和瓶颈问题。

### Java 内存划分

Java 将内存分为两个区域：

- **堆（Heap）**：实例、变量……你的数据
- **非堆/永久区（Nonheap/perm）**：代码、元数据……JVM 使用的内存

关注 Java 内存管理时，重点是堆内存。堆内存根据对象的生命周期分为两代：**年轻代（young）**和**老年代（old）**。**年轻代**（也叫做“新生代”）包含短生命周期的对象；**老年代**包含存活时间较长的对象。

年轻代进一步划分为两部分：

- **Eden**：对象的创建区
- **Survivor**：一个中间状态，实例从年轻代迁移到老年代时会经过这里

### 垃圾回收器（GC）

垃圾回收器（GC）负责清理内存。GC 有不同的实现，但一般来说它执行两个任务：

- **Minor collection**：回收年轻代内存
- **Major collection**：回收所有内存，包括年轻代和老年代

GC 与应用程序的正常执行并行工作。每次 GC 执行时，会暂停所有正在运行的线程（通常是几毫秒）。为了保证应用的健康运行，GC 通常仅进行年轻代的回收，以避免干扰正常的应用执行。

### GC 策略

为了正确地操作和清理内存，我们应尽量使用小而短生命周期的对象，而不是长时间存在的对象。临时对象会停留在 Eden 区域，这样 GC 就会更早、更快地回收它们。

虽然内存中有未使用的对象不会干扰应用程序的执行，但会影响硬件性能，也可能会使 GC 执行变慢，因为它会在每次回收时反复处理这些对象。

虽然调用 `System.gc()` 强制执行 GC 可能会有诱惑，但这样做会强制执行一次老年代回收，打破 GC 的启发式算法，并暂停应用程序，直到回收过程结束。

### 引用类型

GC 会回收不再被引用的实例。这意味着，如果你创建了一个实例，它的属性引用了第二个实例，那么这两个实例要么会同时被回收，要么永远不会被回收。交叉引用的实例越多，GC 的任务就越复杂，出错的可能性也越大。你可以通过将对象的属性置为 `null`，打破实例间的链接，来帮助 GC 回收内存。

所有的静态对象都会永远存在，这意味着它们引用的属性也会永远存在。

为了帮助 GC 回收不需要的对象，Java 提供了特殊类型的引用，可以在 `java.lang.ref` 包中找到相应的类：

- **WeakReference**：不会作为回收的引用。例如，`WeakHashMap` 使用弱引用来实现 `HashMap`，如果 map 中包含的对象只有在 map 中有引用，它就会被回收。
- **SoftReference**：GC 会根据内存需求来决定是否回收该对象。
- **PhantomReference**：总是返回 `null`，链接不再指向对象。通常用于在销毁对象之前清除绑定它的实例。

### 总结

记住，垃圾回收器是你的朋友，它试图简化你的工作。你可以通过让 GC 的工作变得更轻松，来回报它的帮助。

---

