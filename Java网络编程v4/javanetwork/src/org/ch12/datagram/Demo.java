package org.ch12.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// 12-1
// DaytimeUDPClient.class
// 一个daytime协议客户端
public class Demo {

    private final static int PORT = 13;
    private static final String HOSTNAME = "time.nist.gov";

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(10000);
            InetAddress host = InetAddress.getByName(HOSTNAME);
            InetAddress ip = InetAddress.getByAddress(new byte[]{(byte) 129, (byte) 6, (byte) 15, (byte) 30});
            System.out.println(host);
            System.out.println(ip);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, ip, PORT);
            DatagramPacket reponse = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(reponse);
            String result = new String(reponse.getData(), 0, reponse.getLength(), "US-ASCII");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
