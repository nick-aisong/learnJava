package cn.part04.ch34.command_chainOfResponsibility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//代码清单34-13 场景类
public class Client {
    public static void main(String[] args) throws IOException {
        Invoker invoker = new Invoker();
        while (true) {
            //UNIX下的默认提示符号
            System.out.print("#");
            //捕获输出
            String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            //输入quit或exit则退出
            if (input.equals("quit") || input.equals("exit")) {
                return;
            }
            System.out.println(invoker.exec(input));
        }
    }
}
