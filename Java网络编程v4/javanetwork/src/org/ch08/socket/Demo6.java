package org.ch08.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// 8-6 SocketInfo.class
// 获得Socket的信息
public class Demo6 {

    public static void main(String[] args) {

        args = new String[]{"www.oreilly.com", "www.oreilly.com", "www.elharo.com"};

        for (String host : args) {
            try (Socket theSocket = new Socket(host, 80)) {
                System.out.println("Connected to " + theSocket.getInetAddress() + " on port " + theSocket.getPort()
                        + " from port " + theSocket.getLocalPort() + " of " + theSocket.getLocalAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
