package org.ch09.serversocket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 9-4 PooledDaytimeServer.class
// 使用线程池的daytime服务器
public class Demo4 {

	public final static int PORT = 13;

	public static void main(String[] args) {

		ExecutorService pool = Executors.newFixedThreadPool(50);

		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try {
					Socket connection = server.accept();
					Callable<Void> task = new DaytimeTask(connection);
					pool.submit(task);
				} catch (IOException e) {
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class DaytimeTask implements Callable<Void> {
		private Socket connection;

		public DaytimeTask(Socket connection) {
			this.connection = connection;
		}

		@Override
		public Void call() {
			try {
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString() + "\r\n");
				out.flush();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}
}
