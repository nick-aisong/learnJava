package org.ch09.serversocket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// 9-3 MultithreadedDaytimeServer.class
// 多线程daytime服务器
public class Demo3 {

	public final static int PORT = 13;

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try {
					Socket connection = server.accept();
					Thread task = new DaytimeThread(connection);
					task.start();
				} catch (IOException e) {
				}
			}
		} catch (IOException e) {
			System.err.println("Couldn't start server");
			System.err.flush();
		}
	}

	public static class DaytimeThread extends Thread {
		private Socket connection;

		public DaytimeThread(Socket connection) {
			this.connection = connection;
		}

		@Override
		public void run() {
			try {
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString() + "\r\n");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
