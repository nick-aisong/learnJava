package org.ch09.serversocket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.junit.Test;

// 9-1 DaytimeServer.class
// daytime服务器
public class Demo {

	public final static int PORT = 13;

	public static void main(String[] args) throws IOException {
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try (Socket connection = server.accept()) {
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString() + "\r\n");
					out.flush();
					connection.close();
				}
			}
		}
	}

	@Test
	public void test() throws Exception {
		while (true) {
			try (Socket s = new Socket("localhost", 13)) {
				InputStreamReader in = new InputStreamReader(s.getInputStream());
				int len;
				while ((len = in.read()) != -1) {
					System.out.print((char) len);
				}
			}
		}
	}
}
