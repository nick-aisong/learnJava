package org.ch11.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

// IntgenClient.class
// Intgen客户端
public class Demo4 {

	public static int DEFAULT_PORT = 1919;

	public static void main(String[] args) {

		args = new String[] { "localhost" };

		if (args.length == 0) {
			System.out.println("Usage: java IntegnClient host [port]");
			return;
		}
		int port;
		try {
			port = Integer.parseInt(args[1]);
		} catch (Exception e) {
			port = DEFAULT_PORT;
		}

		try {
			SocketAddress address = new InetSocketAddress(args[0], port);
			SocketChannel client = SocketChannel.open(address);
			ByteBuffer buffer = ByteBuffer.allocate(4);
			IntBuffer view = buffer.asIntBuffer();
			for (int expected = 0;; expected++) {
				client.read(buffer);
				int actual = view.get();
				buffer.clear();
				view.rewind();
				if (actual != expected) {
					System.err.println("Expected " + expected + "; was " + actual);
					break;
				}
				System.out.println(actual);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
