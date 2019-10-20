package cn.ch05.ch05_2;

import java.util.Random;

// 1.创建一个名为Document的类。它将生成一个字符串矩阵来模拟一个文档
public class Document {
    // 2.用一些词来创建一个字符串数组。这个数组将被用来生成字符串矩阵
    private String[] words = {"the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class",
            "main"};

    // 3.实现generateDocument()方法
    // 它接收3个参数，分别是行数numLines，每一行词的个数numWords，和准备查找的词word
    // 然后返回一个字符串矩阵
    public String[][] generateDocument(int numLines, int numWords, String word) {
        // 4.创建用来生成文档所需要的对象：String矩阵，和用来生成随机数的Random对象
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        // 5.为字符串矩阵填上字符串。通过随机数取得数组words中的某一字符串
        // 然后存入到字符串矩阵document对应的位置上，同时计算生成的字符串矩阵中将要查找的词出现的次数
        // 这个值可以用来与后续程序运行查找任务时统计的次数相比较，检查两个值是否相同
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(word.length());
                document[i][j] = words[index];
                if (document[i][j].equals(word)) {
                    counter++;
                }
            }
        }
        // 6.在控制台输出这个词出现的次数，并返回生成的矩阵document
        System.out.println("DocumentMock: The word appears " + counter + " times in the document");
        return document;
    }
}
