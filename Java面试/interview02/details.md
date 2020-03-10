# volatile
### 00volatile理解总览
![](src\part01_volatile\img\00volatile理解总览.png)
### 01JMM你谈谈
![](src\part01_volatile\img\01JMM你谈谈.png)
### 02JMM你谈谈
![](src\part01_volatile\img\02JMM你谈谈.png)
### 03volatileDemo演示可见性
![](src\part01_volatile\img\03volatileDemo演示可见性.png)
### 04volatileDemo演示可见性
![](src\part01_volatile\img\04volatileDemo演示可见性.png)
### 05volatileDemo演示不保证原子性
![](src\part01_volatile\img\05volatileDemo演示不保证原子性.png)
### 06volatile不保证原子性理论
![](src\part01_volatile\img\06volatile不保证原子性理论.png)
### 07volatile不保证原子性理论
![](src\part01_volatile\img\07volatile不保证原子性理论.png)
### 08Java字节码对应的指令的解释举例
![](src\part01_volatile\img\08Java字节码对应的指令的解释举例.png)
### 09volatile不保证原子性问题解决
![](src\part01_volatile\img\09volatile不保证原子性问题解决.png)
### 10有序性和指令重排
![](src\part01_volatile\img\10有序性和指令重排.png)
### 11指令重排概念
![](src\part01_volatile\img\11指令重排概念.png)
### 12指令重排概念
![](src\part01_volatile\img\12指令重排概念.png)
### 13指令重排案例
![](src\part01_volatile\img\13指令重排案例.png)
### 14禁止指令重排小总结
![](src\part01_volatile\img\14禁止指令重排小总结.png)
### 15线程安全性获得保证
![](src\part01_volatile\img\15线程安全性获得保证.png)
### 16单例模式在多线程环境下可能存在安全问题
![](src\part01_volatile\img\16单例模式在多线程环境下可能存在安全问题.png)
### 17单例模式volatile分析
![](src\part01_volatile\img\17单例模式volatile分析.png)
# CAS_ABA
### 18CAS是什么
![](src\part02_CAS_ABA\img\18CAS是什么.png)
### 19CAS是什么CASDemo
![](src\part02_CAS_ABA\img\19CAS是什么CASDemo.png)
### 20CAS底层原理
![](src\part02_CAS_ABA\img\20CAS底层原理.png)
### 21Unsafe类
![](src\part02_CAS_ABA\img\21Unsafe类.png)
### 22CAS是什么
![](src\part02_CAS_ABA\img\22CAS是什么.png)
### 23CAS底层原理
![](src\part02_CAS_ABA\img\23CAS底层原理.png)
### 24CAS是什么之CPUCAS原语
![](src\part02_CAS_ABA\img\24CAS是什么之CPUCAS原语.png)
### 24底层汇编
![](src\part02_CAS_ABA\img\24底层汇编.png)
### 25简单版小总结
![](src\part02_CAS_ABA\img\25简单版小总结.png)
### 26CAS缺点
![](src\part02_CAS_ABA\img\26CAS缺点.png)
### 27ABA问题是怎么产生的
![](src\part02_CAS_ABA\img\27ABA问题是怎么产生的.png)
### 28AtomicReference原子引用
![](src\part02_CAS_ABA\img\28AtomicReference原子引用.png)
### 29AtomicStampedReference版本号原子引用
![](src\part02_CAS_ABA\img\29AtomicStampedReference版本号原子引用.png)
### 30ABA问题的解决
![](src\part02_CAS_ABA\img\30ABA问题的解决.png)
# unsafeCollections
### 31编码写一个不安全ArrayList的案例
![](src\part03_unsafeCollections\img\31编码写一个不安全ArrayList的案例.png)
### 32直接newArrayList是空的
![](src\part03_unsafeCollections\img\32直接newArrayList是空的.png)
### 33调用ArrayList的add方法后，空间初始为10
![](src\part03_unsafeCollections\img\33调用ArrayList的add方法后，空间初始为10.png)
### 34编码写一个不安全ArrayList的案例
![](src\part03_unsafeCollections\img\34编码写一个不安全ArrayList的案例.png)
### 35开30个线程对ArrayList进行add
![](src\part03_unsafeCollections\img\35开30个线程对ArrayList进行add.png)
### 36对集合不安全的解决方法
![](src\part03_unsafeCollections\img\36对集合不安全的解决方法.png)
### 37解决方法3CopyOnWriteArrayList
![](src\part03_unsafeCollections\img\37解决方法3CopyOnWriteArrayList.png)
### 38CopyOnWriteArraySet
![](src\part03_unsafeCollections\img\38CopyOnWriteArraySet.png)
### 39ConcurrentHashMap（没仔细讲）
![](src\part03_unsafeCollections\img\39ConcurrentHashMap（没仔细讲）.png)
### 40TransferValue醒脑小练习
![](src\part03_unsafeCollections\img\40TransferValue醒脑小练习.png)
# lock
### 41公平和非公平锁
![](src\part04_lock\img\41公平和非公平锁.png)
### 42可重入锁和递归锁理论知识
![](src\part04_lock\img\42可重入锁和递归锁理论知识.png)
### 43可重入锁和递归锁代码验证
![](src\part04_lock\img\43可重入锁和递归锁代码验证.png)
### 44可重入锁和递归锁代码验证
![](src\part04_lock\img\44可重入锁和递归锁代码验证.png)
### 45自旋锁理论知识
![](src\part04_lock\img\45自旋锁理论知识.png)
### 46自旋锁代码验证
![](src\part04_lock\img\46自旋锁代码验证.png)
### 47读写锁理论知识
![](src\part04_lock\img\47读写锁理论知识.png)
### 48读写锁代码验证
![](src\part04_lock\img\48读写锁代码验证.png)
### 49CountDownLatch和enum
![](src\part04_lock\img\49CountDownLatch和enum.png)
### 50CountDownLatch小总结
![](src\part04_lock\img\50CountDownLatch小总结.png)
### 51CyclicBarrierDemo
![](src\part04_lock\img\51CyclicBarrierDemo.png)
### 52SemaphoreDemo
![](src\part04_lock\img\52SemaphoreDemo.png)
# blockQueue
### 53阻塞队列理论、接口结构和实现类
![](src\part05_blockQueue\img\53阻塞队列理论、接口结构和实现类.png)
### 54阻塞队列api之抛出异常组
![](src\part05_blockQueue\img\54阻塞队列api之抛出异常组.png)
### 55阻塞队列api之返回布尔值组
![](src\part05_blockQueue\img\55阻塞队列api之返回布尔值组.png)
### 56阻塞队列api之阻塞和超时控制
![](src\part05_blockQueue\img\56阻塞队列api之阻塞和超时控制.png)
### 57阻塞队列之同步SynchronousQueue队列
![](src\part05_blockQueue\img\57阻塞队列之同步SynchronousQueue队列.png)
### 58线程通信之生产者消费者传统版
![](src\part05_blockQueue\img\58线程通信之生产者消费者传统版.png)
### 59Synchronized和Lock有什么区别
![](src\part05_blockQueue\img\59Synchronized和Lock有什么区别.png)
### 60锁绑定多个条件Condition
![](src\part05_blockQueue\img\60锁绑定多个条件Condition.png)
### 61线程通信之生产者消费者阻塞队列版
![](src\part05_blockQueue\img\61线程通信之生产者消费者阻塞队列版.png)
### 62Callable接口
![](src\part05_blockQueue\img\62Callable接口.png)
# threadPool
### 63线程池使用及优势
![](src\part06_threadPool\img\63线程池使用及优势.png)
### 64线程池3个常用方式
![](src\part06_threadPool\img\64线程池3个常用方式.png)
### 65线程池7大参数入门简介
![](src\part06_threadPool\img\65线程池7大参数入门简介.png)
### 66线程池7大参数深入介绍
![](src\part06_threadPool\img\66线程池7大参数深入介绍.png)
### 67线程池底层工作原理
![](src\part06_threadPool\img\67线程池底层工作原理.png)
### 68线程池的4种拒绝策略理论简介
![](src\part06_threadPool\img\68线程池的4种拒绝策略理论简介.png)
### 69线程池实际中使用哪一个
![](src\part06_threadPool\img\69线程池实际中使用哪一个.png)
### 70线程池的手写改造和拒绝策略
![](src\part06_threadPool\img\70线程池的手写改造和拒绝策略.png)
### 71线程池配置合理线程数
![](src\part06_threadPool\img\71线程池配置合理线程数.png)
### 72死锁编码及定位分析
![](src\part06_threadPool\img\72死锁编码及定位分析.png)
# jvm
### 73JVMGC下半场技术加强说明和前提知识要求
![](src\part07_jvm\img\73JVMGC下半场技术加强说明和前提知识要求.png)
### 74JVMGC快速回顾复习串讲
![](src\part07_jvm\img\74JVMGC快速回顾复习串讲.png)
### 75谈谈你对GCRoots的理解
![](src\part07_jvm\img\75谈谈你对GCRoots的理解.png)
### 76JVM的标配参数和X参数
![](src\part07_jvm\img\76JVM的标配参数和X参数.png)
### 77JVM的XX参数之布尔类型
![](src\part07_jvm\img\77JVM的XX参数之布尔类型.png)
### 78JVM的XX参数之设值类型
![](src\part07_jvm\img\78JVM的XX参数之设值类型.png)
### 79JVM的XX参数之XmsXmx坑题
![](src\part07_jvm\img\79JVM的XX参数之XmsXmx坑题.png)
### 80JVM盘点家底查看初始默认值
![](src\part07_jvm\img\80JVM盘点家底查看初始默认值.png)
### 81JVM盘点家底查看修改变更值
![](src\part07_jvm\img\81JVM盘点家底查看修改变更值.png)
### 82堆内存初始大小快速复习
![](src\part07_jvm\img\82堆内存初始大小快速复习.png)
### 83常用基础参数栈内存Xss讲解
![](src\part07_jvm\img\83常用基础参数栈内存Xss讲解.png)
### 84常用基础参数元空间MetaspaceSize讲解
![](src\part07_jvm\img\84常用基础参数元空间MetaspaceSize讲解.png)
### 85常用基础参数PrintGCDetails回收前后对比讲解
![](src\part07_jvm\img\85常用基础参数PrintGCDetails回收前后对比讲解.png)
### 86常用基础参数SurvivorRatio讲解
![](src\part07_jvm\img\86常用基础参数SurvivorRatio讲解.png)
### 87常用基础参数NewRatio讲解
![](src\part07_jvm\img\87常用基础参数NewRatio讲解.png)
### 88常用基础参数MaxTenuringThreshold讲解
![](src\part07_jvm\img\88常用基础参数MaxTenuringThreshold讲解.png)
### 89强引用Reference
![](src\part07_jvm\img\89强引用Reference.png)
### 90软引用SoftReference
![](src\part07_jvm\img\90软引用SoftReference.png)
### 91弱引用WeakReference
![](src\part07_jvm\img\91弱引用WeakReference.png)
### 92软引用和弱引用的适用场景
![](src\part07_jvm\img\92软引用和弱引用的适用场景.png)
### 93WeakHashMap案例演示和解析
![](src\part07_jvm\img\93WeakHashMap案例演示和解析.png)
### 94虚引用简介
![](src\part07_jvm\img\94虚引用简介.png)
### 95ReferenceQueue引用队列介
![](src\part07_jvm\img\95ReferenceQueue引用队列介.png)
### 96虚引用PhantomReference
![](src\part07_jvm\img\96虚引用PhantomReference.png)
### 97GCRoots和四大引用小总结
![](src\part07_jvm\img\97GCRoots和四大引用小总结.png)
### 98SOFE之StackOverflowError
![](src\part07_jvm\img\98SOFE之StackOverflowError.png)
### 99OOM之Java heap space
![](src\part07_jvm\img\99OOM之Java heap space.png)
### 100OOM之GC overhead limit exceeded
![](src\part07_jvm\img\100OOM之GC overhead limit exceeded.png)
### 101OOM之Direct buffer memory
![](src\part07_jvm\img\101OOM之Direct buffer memory.png)
### 102OOM之unable to create new native thread故障演示
![](src\part07_jvm\img\102OOM之unable to create new native thread故障演示.png)
### 103OOM之unable to create new native thread上限调整
![](src\part07_jvm\img\103OOM之unable to create new native thread上限调整.png)
### 104OOM之Metaspace
![](src\part07_jvm\img\104OOM之Metaspace.png)
### 105垃圾收集器回收种类
![](src\part07_jvm\img\105垃圾收集器回收种类.png)
### 106串行并行并发G1四大垃圾回收方式
![](src\part07_jvm\img\106串行并行并发G1四大垃圾回收方式.png)
### 107如何查看默认的垃圾收集器
![](src\part07_jvm\img\107如何查看默认的垃圾收集器.png)
### 108JVM默认的垃圾收集器有哪些
![](src\part07_jvm\img\108JVM默认的垃圾收集器有哪些.png)
### 109GC之7大垃圾收集器概述
![](src\part07_jvm\img\109GC之7大垃圾收集器概述.png)
### 110GC之约定参数说明
![](src\part07_jvm\img\110GC之约定参数说明.png)
### 111GC之Serial收集器
![](src\part07_jvm\img\111GC之Serial收集器.png)
### 112GC之ParNew收集器
![](src\part07_jvm\img\112GC之ParNew收集器.png)
### 113GC之Parallel收集器
![](src\part07_jvm\img\113GC之Parallel收集器.png)
### 114GC之ParallelOld收集器
![](src\part07_jvm\img\114GC之ParallelOld收集器.png)
### 115GC之CMS收集器
![](src\part07_jvm\img\115GC之CMS收集器.png)
### 116GC之SerialOld收集器
![](src\part07_jvm\img\116GC之SerialOld收集器.png)
### 117GC之如何选择垃圾收集器
![](src\part07_jvm\img\117GC之如何选择垃圾收集器.png)
### 118GC之G1收集器
![](src\part07_jvm\img\118GC之G1收集器.png)
### 119GC之G1底层原理
![](src\part07_jvm\img\119GC之G1底层原理.png)
### 120GC之G1参数配置及和CMS的比较
![](src\part07_jvm\img\120GC之G1参数配置及和CMS的比较.png)
### 121JVMGC结合SpringBoot微服务优化简介
![](src\part07_jvm\img\121JVMGC结合SpringBoot微服务优化简介.png)
# linux
### 122Linux命令之top
![](src\part08_linux\img\122Linux命令之top.png)
### 123Linux之cpu查看vmstat
![](src\part08_linux\img\123Linux之cpu查看vmstat.png)
### 124Linux之cpu查看pidstat
![](src\part08_linux\img\124Linux之cpu查看pidstat.png)
### 125Linux之内存查看free和pidstat
![](src\part08_linux\img\125Linux之内存查看free和pidstat.png)
### 126Linux之硬盘查看df
![](src\part08_linux\img\126Linux之硬盘查看df.png)
### 127Linux之磁盘IO查看iostat和pidstat
![](src\part08_linux\img\127Linux之磁盘IO查看iostat和pidstat.png)
### 128Linux之网络IO查看ifstat
![](src\part08_linux\img\128Linux之网络IO查看ifstat.png)
### 130CPU占用过高的定位分析思路
![](src\part08_linux\img\130CPU占用过高的定位分析思路.png)
### 131对于JDK自带的JVM工具基本使用大概
![](src\part08_linux\img\131对于JDK自带的JVM工具基本使用大概.png)
# github
### 132GitHub骚操作之开启
![](src\part09_github\img\132GitHub骚操作之开启.png)
### 133GitHub骚操作之常用词
![](src\part09_github\img\133GitHub骚操作之常用词.png)
### 134GitHub骚操作之in限制搜索
![](src\part09_github\img\134GitHub骚操作之in限制搜索.png)
### 135GitHub骚操作之star和fork范围搜索
![](src\part09_github\img\135GitHub骚操作之star和fork范围搜索.png)
### 136GitHub骚操作之awesome搜索
![](src\part09_github\img\136GitHub骚操作之awesome搜索.png)
### 137GitHub骚操作之#L数字
![](src\part09_github\img\137GitHub骚操作之#L数字.png)
### 138GitHub骚操作之T搜索
![](src\part09_github\img\138GitHub骚操作之T搜索.png)
### 139GitHub骚操作之搜索区域活跃用户
![](src\part09_github\img\139GitHub骚操作之搜索区域活跃用户.png)