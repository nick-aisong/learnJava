package org.ch11.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

// 11-6
// NonblockingSingleFileHTTPServer.class
// 提供一个文件的非阻塞HTTP服务器
public class Demo6 {

    private ByteBuffer contentBuffer;
    private int port = 80;

    public Demo6(ByteBuffer data, String encoding, String MIMEType, int port) {
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n" + "Server: NonblickingSingleFileHTTPServer\r\n" + "Content-length: "
                + data.limit() + "\r\n" + "Content-type: " + MIMEType + "\r\n\r\n";
        byte[] headerData = header.getBytes(Charset.forName("US-ASCII"));

        ByteBuffer buffer = ByteBuffer.allocate(data.limit() + headerData.length);
        buffer.put(headerData);
        buffer.put(data);
        buffer.flip();
        this.contentBuffer = buffer;
    }

    public void run() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket ss = serverChannel.socket();
        Selector selector = Selector.open();
        InetSocketAddress localPort = new InetSocketAddress(port);
        ss.bind(localPort);
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                try {
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_ACCEPT);
                    } else if (key.isWritable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        if (buffer.hasRemaining()) {
                            channel.write(buffer);
                        } else {
                            channel.close();
                        }
                    } else if (key.isReadable()) {
                        // 不用费力解析HTTP首部
                        // 只需读取
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(4096);
                        channel.read(buffer);
                        // 将通道切换为制只写模式
                        key.interestOps(SelectionKey.OP_WRITE);
                        key.attach(contentBuffer.duplicate());
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException e1) {
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java NonblockingSingleFileHTTPServer file port encoding");
            return;
        }

        try {
            // 读取要提供的文件
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
            Path file = FileSystems.getDefault().getPath(args[0]);
            byte[] data = Files.readAllBytes(file);
            ByteBuffer input = ByteBuffer.wrap(data);

            // 设置要监听的端口
            int port;
            try {
                port = Integer.parseInt(args[1]);
                if (port < 1 || port > 65535) {
                    port = 80;
                }
            } catch (RuntimeException e) {
                port = 80;
            }
            String encoding = "UTF-8";
            if (args.length > 2) {
                encoding = args[2];
            }
            Demo6 server = new Demo6(input, encoding, contentType, port);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
