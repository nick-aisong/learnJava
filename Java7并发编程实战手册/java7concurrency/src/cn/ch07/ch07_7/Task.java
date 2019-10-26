package cn.ch07.ch07_7;

// 9.创建一个名为Task的类，继承MyWorkerTask类
public class Task extends MyWorkerTask {
    private static final long serialVersionUID = 1L;
    // 10.声明一个名为array的私有int数组
    private int[] array;
    private String name;
    private int start;
    private int end;

    // 11.实现类构造器来初始化属性
    public Task(String name, int[] array, int start, int end) {
        super(name);
        this.name = name;
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // 12.实现compute()方法。该方法增加由开始和结束属性决定的数组的元素块
    // 如果元素块中有超过100个元素，则分拆这个块为两部分，并创建两个Task对象来处理各部分
    // 使用invokeAll()方法传递这些任务到pool
    @Override
    protected void compute() {
        if (end - start > 100) {
            int mid = (end + start) / 2;
            Task task1 = new Task(this.getName() + "1", array, start, mid);
            Task task2 = new Task(this.getName() + "2", array, mid, end);
            invokeAll(task1, task2);
            // 13.如果元素块中有少于100个元素，则使用一个for循环对所有的元素加1
        } else {
            for (int i = start; i < end; i++) {
                array[i]++;
            }
        }
        // 14.使当前线程休眠50毫秒
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
