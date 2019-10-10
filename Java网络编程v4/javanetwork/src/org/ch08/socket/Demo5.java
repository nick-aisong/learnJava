package org.ch08.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// 8-5 LowPortScanner.class
// 查看指定主机上前1024个端口中哪些安装有TCP服务器
public class Demo5 {

	public static void main(String[] args) {
		
		args = new String[] {"127.0.0.1"};

		String host = args.length > 0 ? args[0] : "localhost";

		for (int i = 1; i < 1024; i++) {
			try {
				Socket s = new Socket(host, i);
				System.out.println("There is a server on port "+i+" of "+host);
				s.close();
			} catch (UnknownHostException e) {
				break;
			} catch (IOException e) {
			}
		}
	}
}
