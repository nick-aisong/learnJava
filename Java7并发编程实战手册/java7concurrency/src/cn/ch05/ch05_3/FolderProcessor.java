package cn.ch05.ch05_3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

// 1.创建名为FolderProcessor的类，并继承RecursiveTask类，RecursiveTask类的泛型参数为List<String>类型
public class FolderProcessor extends RecursiveTask<List<String>> {
	// 2.声明类的serialVersionUID属性。这个元素是必需的，因为RecursiveTask类的父类ForkJoinTask实现了Serializable接口
	private static final long serialVersionUID = 1L;
	// 3.声明一个名为path的私有String属性，用来存储任务将要处理的文件夹的完整路径
	private String path;
	// 4.声明一个名为extension的私有String属性，用来存储任务将要查找的文件的扩展名
	private String extension;
	// 5.实现类的构造器，用来初始化这些属性
	public FolderProcessor(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}
	// 6.实现compute()方法。既然指定了RecursiveTask类泛型参数为List<String>类型，那么，这个方法必须返回一个同样类型的对象
	@Override
	protected List<String> compute() {
		// 7.声明一个名为list的String对象列表，用来存储文件夹中文件的名称
		List<String> list = new ArrayList<>();
		// 8.声明一个名为tasks的FolderProcessor任务列表，用来存储子任务，这些子任务将处理文件夹中的子文件夹
		List<FolderProcessor> tasks = new ArrayList<>();
		// 9.获取文件夹的内容
		File file = new File(path);
		File[] content = file.listFiles();
		// 10.对于文件夹中的每一个元素，如果它是子文件夹，就创建一个新的FolderProcessor对象，然后调用fork()方法采用异步方式来执行它
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					task.fork();
					tasks.add(task);
					// 11.否则，调用checkFile()方法来比较文件的扩展名
					// 如果文件的扩展名与将要搜索的扩展名相同，就将文件的完整路径存储到第7步声明的列表中
				} else {
					if (checkFile(content[i].getName())) {
						list.add(content[i].getAbsolutePath());
					}
				}
			}
			// 12.如果FolderProcessor子任务列表超过50个元素，那么就在控制台输出一条信息表示这种情景
			if (tasks.size() > 50) {
				System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
			}
			// 13.调用addResultsFromTask()辅助方法
			// 它把通过这个任务而启动的子任务返回的结果增加到文件列表中
			// 传递两个参数给这个方法，一个是字符串列表list，一个是FolderProcessor子任务列表tasks
			addResultsFromTask(list, tasks);
		}
		// 14.返回字符串列表
		return list;
	}

	// 15.实现addResultsFromTasks()方法。遍历任务列表中存储的每一个任务
	// 调用join()方法等待任务执行结束，并且返回任务的结果
	// 然后，调用addAll()方法将任务的结果增加到字符串列表中
	private void addResultsFromTask(List<String> list, List<FolderProcessor> tasks) {
		for (FolderProcessor item : tasks) {
			list.addAll(item.join());
		}
	}
	// 16.实现checkFile()方法。这个方法检查作为参数而传递进来的文件名
	// 如果是以正在搜索的文件扩展名为结尾，那么方法就返回true，否则就返回false
	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}
}
