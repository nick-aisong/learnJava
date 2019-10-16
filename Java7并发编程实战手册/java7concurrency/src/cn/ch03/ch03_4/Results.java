package cn.ch03.ch03_4;

// 8.实现结果类Results。这个类将保存矩阵中每行找到指定数字的次数
public class Results {

    // 9.声明私有int数组data
    private int[] data;

    // 10.实现构造器。它的传入参数将指定这个data数组的长度
    public Results(int size) {
        data = new int[size];
    }

    // 11.实现setData()方法。它的传入参数指定了数组的索引postion及其对应的值value
    public void setData(int position, int value) {
        data[position] = value;
    }

    // 12.实现getData()方法。这个方法返回结果数组
    public int[] getData() {
        return data;
    }
}
