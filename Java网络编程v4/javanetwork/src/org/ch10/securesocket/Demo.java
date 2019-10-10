package org.ch10.securesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

// 10-1 HTTPSClient.class
public class Demo {

	public static void main(String[] args) {

		args = new String[] { "www.usps.com" };

		if (args.length == 0) {
			System.out.println("Usage: java HTTPSClient2 host");
			return;
		}

		int port = 443; // 默认https端口
		String host = args[0];
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket socket = null;
		try {
			socket = (SSLSocket) factory.createSocket(host, port);

			// 启用所有密码组
			String[] supported = socket.getSupportedCipherSuites();
			for (String string : supported) {
				System.err.println(string);
			}
			System.err.flush();
			socket.setEnabledCipherSuites(supported);

			Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			// https在GET行中需要完全URL
			out.write("GET http://" + host + "/ HTTP/1.1\r\n");
			out.write("Host: " + host + "\r\n");
			out.write("\r\n");
			out.flush();

			// 读取响应
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 读取首部
			String s;
			while (!(s = in.readLine()).equals("")) {
				System.out.println(s);
			}
			System.out.println();

			// 读取长度
			String contentLength = in.readLine();
			int length = Integer.MAX_VALUE;
			try {
				length = Integer.parseInt(contentLength.trim(), 16);
			} catch (NumberFormatException e) {
				// 这个服务器在相应体的第一行
				// 没有发送content-length
			}
			System.out.println(contentLength);

			int c;
			int i = 0;
			while ((c = in.read()) != -1 && i++ < length) {
				System.out.write(c);
			}
			System.out.println();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
