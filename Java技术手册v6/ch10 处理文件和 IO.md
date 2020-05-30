#### 第 10 章 处理文件和 I/O

从第 1 版开始， Java 就支持输入 / 输出（ I/O）。可是，由于 Java 极力想实现平台独立性，所以早期版本中的 I/O 功能更加强调可移植性而不是功能，因此不是那么好用

本章后面会介绍，原来的 API 已经得到补充，变得很丰富，而且功能完善，易于使用。首先，我们要介绍 Java 以前处理 I/O 的“经典”方式，这是现代方式的基础

##### 10.1 Java 处理 I/O 的经典方式

File 类是以前 Java 处理文件 I/O 的基础。这个抽象既能表示文件，也能表示目录，不过有时使用起来有些麻烦，写出的代码如下所示：
```java
// 创建一个文件对象，表示用户的家目录
File homedir = new File(System.getProperty("user.home"));
// 创建一个对象，表示配置文件
//（家目录中应该存在这个文件）
File f = new File(homedir, "app.conf");
// 检查文件是否存在，是否真是文件，以及是否可读
if (f.exists() && f.isFile() && f.canRead()) {
    // 创建一个文件对象，表示新配置目录
    File configdir = new File(f, ".configdir");
    // 然后创建这个目录
    configdir.mkdir();
    // 最后，把配置文件移到新位置
    f.renameTo(new File(configdir, ".config"));
}
```
上述代码展现了 File 类使用灵活的一面，但也演示了这种抽象带来的一些问题。一般情况下，需要调用很多方法查询 File 对象才能判断这个对象到底表示的是什么，以及具有什么能力

###### 10.1.1 文件

File 类中有相当多的方法，但根本没有直接提供一些基本功能（尤其是无法读取文件的内容）

下述代码简要总结了 File 类中的方法：
```java
// 权限管理
boolean canX = f.canExecute();
boolean canR = f.canRead();
boolean canW = f.canWrite();
boolean ok;
ok = f.setReadOnly();
ok = f.setExecutable(true);
ok = f.setReadable(true);
ok = f.setWritable(false);
// 使用不同的方式表示文件名
File absF = f.getAbsoluteFile();
File canF = f.getCanonicalFile();
String absName = f.getAbsolutePath();
String canName = f.getCanonicalPath();
String name = f.getName();
String pName = getParent();
URI fileURI = f.toURI(); // 创建文件路径的URI形式
// 文件的元数据
boolean exists = f.exists();
boolean isAbs = f.isAbsolute();
boolean isDir = f.isDirectory();
boolean isFile = f.isFile();
boolean isHidden = f.isHidden();
long modTime = f.lastModified(); // 距Epoch时间的毫秒数
boolean updateOK = f.setLastModified(updateTime); // 毫秒
long fileLen = f.length();
// 文件管理操作
boolean renamed = f.renameTo(destFile);
boolean deleted = f.delete();
// 创建文件不会覆盖现有文件
boolean createdOK = f.createNewFile();
// 处理临时文件
File tmp = File.createTempFile("my-tmp", ".tmp");
tmp.deleteOnExit();
// 处理目录
boolean createdDir = dir.mkdir();
String[] fileNames = dir.list();
File[] files = dir.listFiles();
```
File 类中还有一些方法不完全符合这种抽象。其中多数方法都要查询文件系统（例如，查询可用空间）：
```java
long free, total, usable;
free = f.getFreeSpace();
total = f.getTotalSpace();
usable = f.getUsableSpace();
File[] roots = File.listRoots(); // 所有可用的文件系统根目录
```
###### 10.1.2 流

I/O 流抽象（不要跟 Java 8 集合 API 使用的流搞混了）出现在 Java 1.0 中，用于处理硬盘或其他源发出的连续字节流

这个 API 的核心是一对抽象类， InputStream 和 OutputStream。这两个类使用广泛，事实上，“标准”输入和输出流（ System.in 和 System.out）就是这种流。标准输入和输出流是System 类的公开静态字段，在最简单的程序中也能用到：
```java
System.out.println("Hello World!");
```
流的某些特定的子类，例如 FileInputStream 和 FileOutputStream，可以操作文件中单独的字节。例如，下述代码用于统计文件中 ASCII 97（小写的 a）出现的次数：
```java
try (InputStream is = new FileInputStream("/Users/ben/cluster.txt")) {
  byte[] buf = new byte[4096];
  int len, count = 0;
  while ((len = is.read(buf)) > 0) {
    for (int i=0; i<len; i++)
       if (buf[i] == 97) count++;
    }
    System.out.println("'a's seen: "+ count);
} catch (IOException e) {
    e.printStackTrace();
}
```
使用这种方式处理硬盘中的数据缺乏灵活性，因为多数开发者习惯以字符而不是字节的方式思考问题。因此，这种流经常和高层的 Reader 和 Writer 类结合在一起使用。 Reader和 Writer 类处理的是字符流，而不是 InputStream 和 OutputStream 及其子类提供的低层字节流

###### 10.1.3 Reader 和 Writer 类

把抽象从字节提升到字符后，开发者就更熟悉所面对的 API 了，而且这样也能规避很多由字符编码和 Unicode 等引起的问题

Reader 和 Writer 类架构在字节流相关的类之上，无需再处理低层 I/O 流。这两个类有几个子类，往往都两两结合在一起使用，例如：

- FileReader
- BufferedReader
- InputStreamReader
- FileWriter
- PrintWriter
- BufferedWriter

若想读取一个文件中的所有行，并把这些行打印出来，可以在 FileReader 对象的基础上使用 BufferedReader 对象，如下述代码所示：
```java
try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
    String line;
    
    while((line = in.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    // 这处理FileNotFoundException等异常
}
```
如果想从终端读取行，而不是文件，一般会在 System.in 对象上使用 InputStreamReader 对象。我们来看个例子，在这个示例中我们想从终端读取行，但特殊对待以特殊字符开头的行——这种行是要处理的命令（“元”），而不是普通文本。很多聊天程序，包括 IRC，都需要这种功能。这里，我们要借助第 9 章介绍的正则表达式：
```java
Pattern SHELL_META_START = Pattern.compile("^#(\\w+)\\s*(\\w+)?");

try (BufferedReader console =
    new BufferedReader(new InputStreamReader(System.in))) {
    String line;
    
    READ: while((line = console.readLine()) != null) {
        // 检查特殊的命令
        Matcher m = SHELL_META_START.matcher(line);
        if (m.find()) {
            String metaName = m.group(1);
            String arg = m.group(2);
            doMeta(metaName, arg);
            continue READ;
        }
        
        System.out.println(line);
    }
} catch (IOException e) {
    // 这里处理FileNotFoundException等异常
}
```
若想把文本输出到文件中，可以使用如下代码：
```java
File f = new File(System.getProperty("user.home")
    + File.separator + ".bashrc");
    try (PrintWriter out
        = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
        out.println("## Automatically generated config file. DO NOT EDIT");
} catch (IOException iox) {
    // 处理异常
}
```
Java 处理 I/O 的旧风格中有些功能偶尔也有用。例如，处理文本文件时， FilterInputStream 类往往非常有用。对于想使用类似于经典“管道” I/O 方式通信的线程来说， Java 提供了 PipedInputStream 和 PipedReader 类，以及对应的写入器


到目前为止，本章多次用到了一种语言特性——“处理资源的 try 语句”（ try-with-resources， TWR）。这种语句的句法在 2.5.18 节简单介绍过，但要结合 I/O 等操作才能充分发挥潜能，而且还给旧 I/O 风格带来了新生

###### 10.1.4 再次介绍 TWR

为了充分发挥 Java 的 I/O 能力，一定要理解如何以及何时使用 TWR。何时使用很好确定，只要可以用就用

在 TWR 出现之前，必须手动关闭资源，而且处理资源之间复杂交互的代码可能有缺陷，无法关闭资源，从而导致资源泄露

事实上，根据甲骨文工程师的估计，在 JDK 6 的初始版本中，处理资源的代码有 60% 都不正确。因此，既然连平台的作者都无法完全正确地手动处理资源，那么所有新代码显然都应该使用 TWR

实现 TWR 的关键是一个新接口——AutoCloseable。这个新接口（在 Java 7 中出现）是Closeable 的直接超接口，表示资源必须自动关闭。为此，编译器会插入特殊的异常处理代码

在 TWR 的资源子句中，只能声明实现了 AutoCloseable 接口的对象，而且数量不限：
```java
try (BufferedReader in = new BufferedReader(new FileReader("profile"));
PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("profile.bak")))) {
    String line;
    
    while((line = in.readLine()) != null) {
        out.println(line);
    }
} catch (IOException e) {
    // 这里处理FileNotFoundException等异常
}
```
这样写，资源的作用域就自动放入 try 块中，各个资源（不管是可读的还是可写的）会按照正确的顺序自动关闭，而且编译器插入的异常处理代码会考虑到资源之间的相互依赖关系

TWR 的作用大致和 C# 的 using 关键字类似，开发者可以把 TWR 看成“正确的终结方式”。 6.4 节说过，新代码绝对不能直接使用终结机制，而一定要使用 TWR。旧代码应该根据情况尽早重构，换用 TWR

###### 10.1.5 I/O 经典处理方式的问题

即便添加了受欢迎的 TWR， File 及相关的类还是有一些问题，就算执行标准的 I/O 操作
也不理想，无法广泛使用。例如：

- 缺少处理常见操作的方法；
- 在不同的平台中不能使用一致的方式处理文件名；
- 没有统一的文件属性模型（例如，读写模型）；
- 难以遍历未知的目录结构；
- 没有平台或操作系统专用的特性；
- 不支持使用非阻塞方式处理文件系统。

为了改善这些缺点， Java 的 I/O API 在过去的几个主版本中一直在改进。直到 Java 7，处理 I/O 才真正变得简单而高效

##### 10.2 Java 处理 I/O 的现代方式

Java 7 引入了全新的 I/O API（一般称为 NIO.2），几乎可以完全取代以前使用 File 类处理I/O 的方式。新添加的各个类都在 java.nio.file 包中

很多情况下，使用 Java 7 引入的新 API 处理 I/O 更简单。新 API 分为两大部分：第一部分是一个新抽象， Path 接口（这个接口的作用可以理解为表示文件的位置，这个位置可以有内容，也可以没有）；第二部分是很多处理文件和文件系统的新方法，方便且实用。这些新方法都是 Files 类的静态方法

###### 10.2.1 文件

例如，使用 Files 类的新功能执行基本的复制操作非常简单，如下所示：
```java
File inputFile = new File("input.txt");
try (InputStream in = new FileInputStream(inputFile)) {
    Files.copy(in, Paths.get("output.txt"));
} catch(IOException ex) {
    ex.printStackTrace();
}
```
下面我们纵览一下 Files 类中的一些重要方法，多数方法执行的操作都不言自明。很多情况下，这些方法都有返回类型。不过，除了人为的个例，或者重复等效 C 代码的行为，很少使用返回类型
```java
Path source, target;
Attributes attr;
Charset cs = StandardCharsets.UTF_8;

// 创建文件
//
// 示例路径 --> /home/ben/.profile
// 示例属性 --> rw-rw-rw
Files.createFile(target, attr);

// 删除文件
Files.delete(target);
boolean deleted = Files.deleteIfExists(target);

// 复制/移动文件
Files.copy(source, target);
Files.move(source, target);

// 读取信息的实用方法
long size = Files.size(target);
FileTime fTime = Files.getLastModifiedTime(target);
System.out.println(fTime.to(TimeUnit.SECONDS));
Map<String, ?> attrs = Files.readAttributes(target, "*");
System.out.println(attrs);

// 处理文件类型的方法
boolean isDir = Files.isDirectory(target);
boolean isSym = Files.isSymbolicLink(target);

// 处理读写操作的方法
List<String> lines = Files.readAllLines(target, cs);
byte[] b = Files.readAllBytes(target);
BufferedReader br = Files.newBufferedReader(target, cs);
BufferedWriter bwr = Files.newBufferedWriter(target, cs);
InputStream is = Files.newInputStream(target);
OutputStream os = Files.newOutputStream(target);
```
Files 类中的某些方法可以接受可选的参数，为方法执行的操作指定其他行为（可能是针对特定实现的行为）

这个 API 的某些决策偶尔会导致让人烦恼的行为。例如，默认情况下，复制操作不会覆盖已经存在的文件，所以需要使用一个复制选项指定这种行为：
```java
Files.copy(Paths.get("input.txt"), Paths.get("output.txt"), StandardCopyOption.REPLACE_EXISTING);
```
StandardCopyOption 是一个枚举，实现了 CopyOption 接口。而且， LinkOption 枚举也实现了 CopyOption 接口。所以， Files.copy() 方法能接受任意个 LinkOption 或 StandardCopyOption 参数。 LinkOption 用于指定如何处理符号链接（当然，前提是底层操作系统支持符号链接）

###### 10.2.2 路径

Path 接口可用于在文件系统中定位文件。这个接口表示的路径具有下述特性：

- 系统相关
- 有层次结构
- 由一系列路径元素组成
- 假设的（可能还不存在，或者已经删除）

因此， Path 对象和 File 对象完全不同。其中特别重要的一点是， Path 是接口，而不是类，这体现了系统相关性。因此，不同的文件系统提供方可以使用不同的方式实现 Path 接口，提供系统专用的特性，但同时还保有整体的抽象

组成 Path 对象的元素中有一个可选的根组件，表示实例所属文件系统的层次结构。注意，有些 Path 对象可能没有根组件，例如表示相对路径的 Path 对象。除了根组件之外，每个Path 实例都有零个或多个目录名和名称元素

名称元素是离目录层次结构的根最远的元素，表示文件或目录的名称。 Path 对象的内容可以理解为使用特殊的分隔符把各个路径元素联接在一起

Path 对象是个抽象概念，和任何物理文件路径都没关联。因此，可以轻易表示还不存在的文件路径。 Java 提供的 Paths 类中有创建 Path 实例的工厂方法

Paths 类提供了两个 get() 方法，用于创建 Path 对象。普通的版本接受一个 String 对象，使用默认的文件系统提供方。另一个版本接受一个 URI 对象，利用了 NIO.2 能插入其他提供方定制文件系统的特性。这是高级用法，有兴趣的开发者可以参阅相关文档
```java
Path p = Paths.get("/Users/ben/cluster.txt");
Path p = Paths.get(new URI("file:///Users/ben/cluster.txt"));
System.out.println(p2.equals(p));

File f = p.toFile();
System.out.println(f.isDirectory());
Path p3 = f.toPath();
System.out.println(p3.equals(p));
```
这个示例还展示了 Path 对象和 File 对象之间可以轻易地相互转换。有了 Path 类中的toFile() 方法和 File 类中的 toPath() 方法，开发者可以毫不费力地在两个 API 之间切换，而且可以使用一种直观的方式重构使用 File 类的代码，换用 Path 接口

除此之外，还可以使用 Files 类中一些有用的“桥接”方法。通过这些方法可以轻易使用旧的 I/O API，例如，有些便利方法可以创建 Writer 对象，把内容写入 Path 对象指定的位置：
```java
Path logFile = Paths.get("/tmp/app.log");
try (BufferedWriter writer =
    Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
    writer.write("Hello World!");
    // ...
} catch (IOException e) {
    // ...
}
```
这里使用了 StandardOpenOption 枚举，其作用和复制选项类似，不过用于指定打开新文件的行为

在这个示例中，我们使用 Path API 完成了下述操作：

- 创建一个 Path 对象，对应于一个新文件；
- 使用 Files 类创建那个新文件；
- 创建一个 Writer 对象，打开那个文件；
- 把内容写入那个文件；
- 写入完毕后自动关闭那个文件。

下面再举个例子。这个示例基于前面的代码，把一个 .jar 文件本身当成 FileSystem 对象处理，直接把一个新文件添加到这个 JAR 文件中。 JAR 文件其实就是 ZIP 文件，所以这种技术也适用于 .zip 压缩文件
```java
Path tempJar = Paths.get("sample.jar");
try (FileSystem workingFS = FileSystems.newFileSystem(tempJar, null)) {
    Path pathForFile = workingFS.getPath("/hello.txt");
    List<String> ls = new ArrayList<>();
    ls.add("Hello World!");
    
    Files.write(pathForFile, ls, Charset.defaultCharset(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
}
```
这个示例展示了如何使用 getPath() 方法在 FileSystem 对象中创建 Path 对象。使用这种技术，开发者其实可以把 FileSystem 对象当成黑盒

Java 最初提供的 I/O API 受到的批评之一是，不支持本地 I/O 和高性能 I/O。 Java 1.4 首次对此提出了解决方案——New I/O (NIO) API，而且在后续版本中一直在改善

###### 10.3 NIO 中的通道和缓冲区

NIO 中的缓冲区是对高性能 I/O 的一种低层抽象，为指定基本类型组成的线性序列提供容器。后面的示例都以处理 ByteBuffer 对象（最常见）为例

###### 10.3.1 ByteBuffer 对象

ByteBuffer 对象是字节序列，理论上，在注重性能的场合中可以代替 byte[] 类型的数组。为了得到最好的性能， ByteBuffer 支持直接使用 JVM 所在平台提供的本地功能处理缓冲区

这种方式叫作“直接缓冲区”，只要可能就会绕过 Java 堆内存。直接缓冲区在本地内存中分配，而不是在标准的 Java 堆内存中。而且，垃圾回收程序对待直接缓冲区的方式和普通的堆中 Java 对象不同

若想创建 ByteBuffer 类型的直接缓冲区对象，可以调用工厂方法 allocateDirect()。除此之外，还有 allocate() 方法，用于创建堆中缓冲区，不过现实中不常使用

创建字节缓冲区的第三种方式是打包现有的 byte[] 数组。这种方式创建的是堆中缓冲区，目的是以更符合面向对象的方式处理底层字节：
```java
ByteBuffer b = ByteBuffer.allocateDirect(65536);
ByteBuffer b2 = ByteBuffer.allocate(4096);

byte[] data = {1, 2, 3};
ByteBuffer b3 = ByteBuffer.wrap(data);
```
字节缓冲区只能使用低层方式访问字节，因此开发者要手动处理细节，例如需要处理字节
的字节顺序和 Java 整数基本类型的符号：
```java
b.order(ByteOrder.BIG_ENDIAN);

int capacity = b.capacity();
int position = b.position();
int limit = b.limit();
int remaining = b.remaining();
boolean more = b.hasRemaining();
```
把数据存入缓冲区或从缓冲区中取出有两种操作方式：一种是单值操作，一次读写一个值；另一种是批量操作，一次读写一个 byte[] 数组或 ByteBuffer 对象，处理多个值（可能很多）。 使用批量操作才能获得预期的性能提升：
```java
b.put((byte)42);
b.putChar('x');
b.putInt(0xcafebabe);

b.put(data);
b.put(b2);

double d = b.getDouble();
b.get(data, 0, data.length);
```
单值形式还支持直接处理缓冲区中绝对位置上的数据：
```java
b.put(0, (byte)9);
```

缓冲区这种抽象只存在于内存中，如果想影响外部世界（例如文件或网络），需要使用Channel（通道）对象。 Channel 接口在 java.nio.channels 包中定义，表示支持读写操作的实体连接。文件和套接字是两种常见的通道，不过我们要意识到，用于低延迟数据处理的自定义实现也属于通道

通道在创建时处于打开状态，随后可以将其关闭。一旦关闭，就无法再打开。一般来说，通道要么可读要么可写，不能既可读又可写。若想理解通道，关键是要知道：

- 从通道中读取数据时会把字节存入缓冲区
- 把数据写入通道时会从缓冲区中读取字节

假如我们要计算一个大文件前 16M 数据片段的校验和：
```java
FileInputStream fis = getSomeStream();
boolean fileOK = true;

try (FileChannel fchan = fis.getChannel()) {
    ByteBuffer buffy = ByteBuffer.allocateDirect(16 * 1024 * 1024);
    while(fchan.read(buffy) != -1 || buffy.position() > 0 || fileOK) {
        fileOK = computeChecksum(buffy);
        buffy.compact();
    }
} catch (IOException e) {
    System.out.println("Exception in I/O");
}
```
上述代码会尽量使用本地 I/O，不会把字节大量复制进出 Java 堆内存。如果 computeChecksum() 方法实现得好，上述代码的性能就很高

###### 10.3.2 映射字节缓冲区

这是一种直接字节缓冲区，包含一个内存映射文件（或内存映射文件的一部分）。这种 缓 冲 区 由 FileChannel 对 象 创 建， 不 过 要 注 意， 内 存 映 射 操 作 之 后 决 不 能 使 用MappedByteBuffer 对象对应的 File 对象，否则会抛出异常。为了规避这种问题，我们可以使用处理资源的 try 语句，严格限制相关对象的作用域：
```java
try (RandomAccessFile raf = 
    new RandomAccessFile(new File("input.txt"), "rw");
    FileChannel fc = raf.getChannel();) {
    
        MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_WRITE, 0,  fc.size());
    byte[] b = new byte[(int)fc.size()];
    mbf.get(b, 0, b.length);
    for (int i=0; i<fc.size(); i++) {
        b[i] = 0; // 这是一个副本，不会写入原文件
    }
    mbf.position(0);
    mbf.put(b); // 清空文件
}
```
就算有了缓冲区， Java 在单个线程中同步执行大型 I/O 操作（例如，在文件系统之间传输10G 数据）时还是会遇到一些限制。在 Java 7 之前，遇到这种操作时往往要自己编写多线程代码，而且要管理一个单独的线程执行后台复制操作。下面介绍 JDK 7 新添加的异步 I/O 功能

##### 10.4 异步 I/O

新异步功能的关键组成部分是一些实现 Channel 接口的类，这些类可以处理需要交给后台线程完成的 I/O 操作。这种功能还可以应用于长期运行的大型操作和其他几种场合

这一节专门介绍处理文件 I/O 的 AsynchronousFileChannel 类，除此之外还要了解一些其他异步通道。本章末尾会介绍异步套接字。这一节介绍的内容包括：

- 使用 AsynchronousFileChannel 类处理文件 I/O
- 使用 AsynchronousSocketChannel 类处理客户端套接字 I/O
- 使用 AsynchronousServerSocketChannel 类处理能接受连入连接的异步套接字

和异步通道交互有两种不同的方式：使用 Future 接口的方式和回调方式

###### 10.4.1 基于 Future 接口的方式

第 11 章会详细介绍 Future 接口，现在你只需知道这个接口表示进行中的任务，可能已经完成，也可能还未完成。这个接口有两个关键的方法

- isDone()
返回布尔值，表示任务是否已经完成。

- get()
返回结果。如果已经结束，立即返回；如果还未结束，在完成前一直阻塞。

面看个示例程序。这个程序异步读取一个大型文件（可能有 100 Mb）：
```java
try (AsynchronousFileChannel channel =
        AsynchronousFileChannel.open(Paths.get("input.txt"))) {
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
    Future<Integer> result = channel.read(buffer, 0);
    
    while(!result.isDone()) {
        // 做些其他有用的操作……
    }
    
    System.out.println("Bytes read: " + result.get());
}
```

###### 10.4.2 基于回调的方式

处理异步 I/O 的回调方式基于 CompletionHandler 接口实现，这个接口定义了两个方法，completed() 和 failed()，分别在操作成功和失败时调用

处理异步 I/O 时，如果想立即收到事件提醒，可以使用这种方式。例如，有大量 I/O 操作要执行，但其中某次操作失败不会导致重大错误，这种情况就可以使用回调方式：
```java
byte[] data = {2, 3, 5, 7, 11, 13, 17, 19, 23};
ByteBuffer buffy = ByteBuffer.wrap(data);

CompletionHandler<Integer,Object> h = new CompletionHandler() {
    public void completed(Integer written, Object o) {
        System.out.println("Bytes written: " + written);
    }
    
    public void failed(Throwable x, Object o) {
        System.out.println("Asynch write failed: "+ x.getMessage());
    }
};

try (AsynchronousFileChannel channel = 
        AsynchronousFileChannel.open(Paths.get("primes.txt"),
        StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
    channel.write(buffy, 0, null, h);
    Thread.sleep(1000); // 必须这么做，防止退出太快
}
```
AsynchronousFileChannel 对象关联一个后台线程池，所以处理 I/O 操作时，原线程可以继续处理其他任

默认情况下，这个线程池由运行时提供并管理。如果需要，线程池也可以由应用创建和管理（通过 AsynchronousFileChannel.open() 方法的某个重载形式），不过一般不需要这么做

最后，为了完整性，我们还要简单介绍 NIO 对多路复用 I/O 的支持。在多路复用 I/O 中，单个线程能管理多个通道，而且会检测哪个通道做好了读或写的准备。支持多路复用 I/O的类在 java.nio.channels 包中，包括 SelectableChannel 和 Selector

编写需要高伸缩性的高级应用时，这种非阻塞式多路复用技术特别有用，不过对这个话题的完整讨论超出了本书的范畴

###### 10.4.3 监视服务和目录搜索

我们要介绍的最后一种异步服务是监视目录，或访问目录（或树状结构）。监视服务会观察目录中发生的所有事情，例如创建或修改文件：
```java
try {
    WatchService watcher = FileSystems.getDefault().newWatchService();
    Path dir = FileSystems.getDefault().getPath("/home/ben");
    WatchKey key = dir.register(watcher,
        StandardWatchEventKinds.ENTRY_CREATE,
        StandardWatchEventKinds.ENTRY_MODIFY,
        StandardWatchEventKinds.ENTRY_DELETE);
    while(!shutdown) {
        key = watcher.take();
        for (WatchEvent<?> event: key.pollEvents()) {
            Object o = event.context();
            if (o instanceof Path) {
                System.out.println("Path altered: "+ o);
            }
        }
        key.reset();
    }
}
```
相比之下，目录流提供的是单个目录中当前所有文件的情况。例如，若想列出所有 Java 源码文件及其大小（以字节为单位）， 可以使用如下代码：
```java
try(DirectoryStream<Path> stream =
        Files.newDirectoryStream(Paths.get("/opt/projects"), "*.java")) {
    for (Path p : stream) {
        System.out.println(p +": "+ Files.size(p));
    }
}
```
这个 API 有个缺点，即只能返回匹配通配模式的元素，这有时不够灵活。我们可以更进一步，使用新方法 Files.find() 和 Files.walk()，递归遍历目录，找出每个元素：
```java
final Pattern isJava = Pattern.compile(".*\\.java$");
final Path homeDir = Paths.get("/Users/ben/projects/");
Files.find(homeDir, 255,
    (p, attrs) -> isJava.matcher(p.toString()).find())
        .forEach(q -> {System.out.println(q.normalize());});
```
我们还可以更进一步，使用 java.nio.file 包中的 FileVisitor 接口编写高级的解决方案，不过，此时需要开发者实现 FileVisitor 接口中的全部四个方法，不能像上述代码那样只使用一个 lambda 表达式

本章的最后一节要讨论 Java 对网络的支持以及 JDK 中相应的核心类

##### 10.5 网络

Java 平台支持大量标准的网络协议，因此编写简单的网络应用非常容易。 Java 对网络支持的核心 API 在 java.net 包中，其他扩展 API 则由 javax.net 包（尤其是 javax.net.ssl包）提供

开发应用时最易于使用的协议是超文本传输协议（ HyperText Transmission Protocol，HTTP）， 这个协议是 Web 的基础通信协议

###### 10.5.1 HTTP

HTTP 是 Java 原生支持的最高层网络协议。这个协议非常简单，基于文本，在 TCP/IP 标准协议族的基础上实现。 HTTP 可以在任何网络端口中使用，不过通常使用 80 端口

URL 是关键的类——这个类原生支持 http://、 ftp://、 file:// 和 https:// 形式的 URL。这个类使用起来非常简单，最简单的示例是下载指定 URL 对应页面的内容。在 Java 8 中，使用下面的代码即可：
```java
URL url = new URL("http://www.jclarity.com/");
try (InputStream in = url.openStream()) {
    Files.copy(in, Paths.get("output.txt"));
} catch(IOException ex) {
    ex.printStackTrace();
}
```
若想深入低层控制，例如获取请求和响应的元数据，可以使用 URLConnection 类，编写如下代码：
```java
try {
    URLConnection conn = url.openConnection();
    String type = conn.getContentType();
    String encoding = conn.getContentEncoding();
    Date lastModified = new Date(conn.getLastModified());
    int len = conn.getContentLength();
    InputStream in = conn.getInputStream();
} catch (IOException e) {
    // 处理异常
}
```
HTTP 定义了多个“请求方法”，客户端使用这些方法操作远程资源。这些方法是：

GET、 POST、 HEAD、 PUT、 DELETE、 OPTIONS、 TRACE

各个方法的用法稍微不同，例如：

- GET 只能用于取回文档，不能执行任何副作用；
- HEAD 和 GET 作用一样，但是不返回主体——如果程序只想检查 URL 对应的网页是否
有变化，可以使用 HEAD；
- 如果想把数据发送给服务器处理，要使用 POST。

默认情况下， Java 始终使用 GET 方法，不过也提供了使用其他方法的方式，用于开发更复杂的应用。然而，这需要做一些额外工作。在下面这个示例中，我们使用 BBC 网站提供的搜索功能搜索关于 Java 的新闻：
```java
URL url = new URL("http://www.bbc.co.uk/search");
String rawData = "q=java";
String encodedData = URLEncoder.encode(rawData, "ASCII");
String contentType = "application/x-www-form-urlencoded";
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setInstanceFollowRedirects(false);
conn.setRequestMethod("POST");
conn.setRequestProperty("Content-Type", contentType );
conn.setRequestProperty("Content-Length",
String.valueOf(encodedData.length()));
conn.setDoOutput(true);
OutputStream os = conn.getOutputStream();
os.write(encodedData.getBytes());
int response = conn.getResponseCode();
if (response == HttpURLConnection.HTTP_MOVED_PERM
        || response == HttpURLConnection.HTTP_MOVED_TEMP) {
    System.out.println("Moved to: "+ conn.getHeaderField("Location"));
} else {
    try (InputStream in = conn.getInputStream()) {
        Files.copy(in, Paths.get("bbc.txt"),
                StandardCopyOption.REPLACE_EXISTING);
    }
}
```
注意，请求参数要在请求的主体中发送，而且发送前要编码。我们还要禁止跟踪 HTTP 重定向，手动处理服务器返回的每个重定向响应。这是因为 HttpURLConnection 类有个缺陷，不能正确处理 POST 请求的重定向响应

开发这种高级 HTTP 应用时，多数情况下开发者一般都会使用专门的 HTTP 客户端库，例如 Apache 提供的那个库，而不会使用 JDK 提供的类从零编写所有代码

下面介绍网络协议栈的下一层，传输控制协议（ Transmission Control Protocol， TCP）

###### 10.5.2 TCP

TCP 是互联网中可靠传输网络数据的基础，确保传输的网页和其他互联网流量完整且易于理解。从网络理论的视角来看，由于 TCP 具有下述特性，才能作为互联网流量的“可靠性层”

- 基于连接
数据属于单个逻辑流（连接）。
- 保证送达
如果未收到数据包，会一直重新发送，直到送达为止。
- 错误检查
能检测到网络传输导致的损坏，并自动修复。

TCP 是双向通信通道，使用特殊的编号机制（ TCP 序号）为数据块指定序号，确保通信流的两端保持同步。为了在同一个网络主机中支持多个不同的服务， TCP 使用端口号识别服务，而且能确保某个端口的流量不会走另一个端口传输

Java 使用 Socket 和 ServerSocket 类表示 TCP。这两个类分别表示连接中的客户端和服务器端。也就是说， Java 既能连接网络服务，也能用来实现新服务

举个例子，我们来重新实现 HTTP。这个协议基于文本，相对简单。连接的两端都要实现，下面先基于 TCP 套接字实现 HTTP 客户端。为此，其实我们需要实现 HTTP 协议的细节，不过我们有个优势——完全掌控着 TCP 套接字

我们既要从客户端套接字中读取数据，也要把数据写入客户端套接字，而且构建请求时要遵守 HTTP 标准（RFC 2616）。最终写出的代码如下所示：
```java
String hostname = "www.example.com";
int port = 80;
String filename = "/index.html";
try (Socket sock = new Socket(hostname, port);
    BufferedReader from = new BufferedReader(
            new InputStreamReader(sock.getInputStream()));
    PrintWriter to = new PrintWriter(
            new OutputStreamWriter(sock.getOutputStream())); ) {
     // HTTP协议
    to.print("GET " + filename +
        " HTTP/1.1\r\nHost: "+ hostname +"\r\n\r\n");
    to.flush();
    for(String l = null; (l = from.readLine()) != null; )
        System.out.println(l);
}
```
在服务器端，可能需要处理多个连入连接。因此，需要编写一个服务器主循环，然后使用accept() 方法从操作系统中接收一个新连接。随后，要迅速把这个新连接传给单独的类处理，好让服务器主循环继续监听新连接。服务器端的代码比客户端复杂：
```java
// 处理连接的类
private static class HttpHandler implements Runnable {
    private final Socket sock;
    HttpHandler(Socket client) { this.sock = client; }
    
    public void run() {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(sock.getInputStream()));
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(sock.getOutputStream())); ) {
            out.print("HTTP/1.0 200\r\nContent-Type: text/plain\r\n\r\n");
            String line;
            while((line = in.readLine()) != null) {
                if (line.length() == 0) break;
                out.println(line);
            }
        } catch(Exception e) {
            // 处理异常
        }
    }
}

// 服务器主循环
public static void main(String[] args) {
    try {
        int port = Integer.parseInt(args[0]);
        ServerSocket ss = new ServerSocket(port);
        for(;;) {
            Socket client = ss.accept();
            HTTPHandler hndlr = new HTTPHandler(client);
            new Thread(hndlr).start();
        }
    } catch (Exception e) {
        // 处理异常
    }
}
```
为通过 TCP 通信的应用设计协议时，要谨记一个简单而意义深远的网络架构原则——Postel 法则（以互联网之父之一 Jon Postel 的名字命名）。这个法则有时表述为：“发送时要保守，接收时要开放。”这个简单的原则表明，网络系统中的通信有太多可能性，即便非常不完善的实现也是如此

如果开发者遵守 Postel 法则，还遵守尽量保持协议简单这个通用原则（有时也叫 KISS 原则 ），那么，基于 TCP 的通信实现起来要比不遵守时更简单

>  KISS 是“ Keep it simple, stupid”的简称

TCP 下面是互联网通用的运输协议——互联网协议（Internet Protocol， IP）

###### 10.5.3 IP

IP 是传输数据的最低层标准，抽象了把字节从 A 设备移动到 B 设备的物理网络技术

和 TCP 不同， IP 数据包不能保证一定送达，在传输的路径中，任何过载的系统都可能会丢掉数据包。 IP 数据包有目的地，但一般没有路由数据——真正传送数据的是沿线的物理传输介质（可能有多种不同的介质）

在 Java 中可以创建基于单个 IP 数据包（首部除了可以指定使用 TCP 协议，还可以指定使用 UDP2 协议）的数据报服务，不过，除了延迟非常低的应用之外很少需要这么做。Java 使用 DatagramSocket 类实现这种功能，不过很少有开发者需要深入到网络协议栈的这一层

> UDP 是 User Datagram Protocol 的简称

最后，值得注意的是，互联网使用的寻址方案目前正在经历一些变化。目前使用的 IP 版本是 IPv4，可用的网络地址有 32 位存储空间。现在，这个空间严重不足，因此已经开始部署多种缓解技术

IP 的下一版（IPv6）已经出现，但还没广泛使用。不过，在未来十年， IPv6 应该会更为普及。令人欣慰的是， Java 已经对这种新寻址方案提供了良好支持
