package org.ch09.serversocket;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// 9-2 TimeServer.class
// 时间服务器
public class Demo2 {

	public final static int PORT = 37;

	public static void main(String[] args) throws Exception {
		// 时间协议将时间起点设置为1900年
		// 而Date类设置为从1970年开始计算。利用下面这个数
		// 可以在二者之间进行转换
		long differenceBetweenEpochs = 2208988800L;

		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try (Socket connection = server.accept()) {
					OutputStream out = connection.getOutputStream();
					Date now = new Date();
					long msSince1970 = now.getTime();
					long secondsSince1970 = msSince1970 / 1000;
					long secondsSince1900 = secondsSince1970 + differenceBetweenEpochs;
					byte[] time = new byte[4];
					time[0] = (byte) ((secondsSince1900 & 0x00000000FF000000L) >> 24);
					time[1] = (byte) ((secondsSince1900 & 0x0000000000FF0000L) >> 16);
					time[2] = (byte) ((secondsSince1900 & 0x000000000000FF00L) >> 8);
					time[3] = (byte) (secondsSince1900 & 0x0000000000000000FFL);
					System.out.println(time);
					out.write(time);
				}
			}
		}
	}
}
