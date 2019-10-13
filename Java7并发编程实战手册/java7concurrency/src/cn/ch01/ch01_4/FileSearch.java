package cn.ch01.ch01_4;

import java.io.File;

// 1.创建一个名为FileSearch的类，并且实现Runnable接口
public class FileSearch implements Runnable {

	// 2.声明两个私有属性，一个是我们将要查找的文件名称，另一个是初始文件夹
	// 实现这个类的构造器，用来初始化这两个属性
	private String initPath;
	private String fileName;

	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}

	// 3.在FileSearch中实现run()方法。它将检查fileName属性是不是一个目录，如果是，就调用processDirectory()方法
	// processDirectory()方法会抛出InterruptedException异常，因此必须捕获并处理这个异常
	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
			}
		}
	}

	// 4.实现directoryProcess()方法，这个方法会获取一个文件夹里的所有文件和子文件夹，并进行处理
	// 对于每一个目录，这个方法将递归调用，并且用相应目录名作为传入参数
	// 对于每个文件，这个方法将调用fileProcess()方法
	// 处理完所有的文件和文件夹后，这个方法将检查线程是不是被中断了，如果是，就抛出InterruptedException 异常
	private void directoryProcess(File file) throws InterruptedException {
		File[] list = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					directoryProcess(list[i]);
				} else {
					fileProcess(list[i]);
				}
			}
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

// 5.实现processFile()方法
// 这个方法将比较当前文件的文件名和要查找的文件名，如果文件名匹配，就将信息打印到控制台
// 做完比较后，线程将检查是不是被中断了，如果是，它将抛出InterruptedException 异常
	private void fileProcess(File file) throws InterruptedException {
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}
}
