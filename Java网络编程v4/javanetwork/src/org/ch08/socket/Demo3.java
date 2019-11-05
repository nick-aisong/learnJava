package org.ch08.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

// 8-3 Time.class
// 时间协议客户端
public class Demo3 {

    private static final String HOSTNAME = "time.nist.gov";

    public static void main(String[] args) throws IOException, ParseException {
        Date d = Demo3.getDateFromNetwork();
        System.out.println("It is " + d);
    }

    public static Date getDateFromNetwork() throws IOException {
        // 时间协议设置时间起点为1900年
        // Java Date类起始于1970年
        // 利用这个数字，在它们之间进行转换
        long differenceBetweenEpochs = 2208988800L;

        // 如果不愿意使用这个魔法数，就使用以下注释
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        Calendar epoch1900 = Calendar.getInstance(gmt);
        epoch1900.set(1900, 01, 01, 00, 00, 00);
        long epoch1900ms = epoch1900.getTime().getTime();
        Calendar epoch1970 = Calendar.getInstance(gmt);
        epoch1970.set(1970, 01, 01, 00, 00, 00);
        long epoch1970ms = epoch1970.getTime().getTime();

        long differenceInMS = epoch1970ms - epoch1900ms;
        long differenceBetweenEpochs2 = differenceInMS / 1000;
        System.out.println(differenceBetweenEpochs2);

        Socket socket = null;

        try {
            socket = new Socket(HOSTNAME, 37);
            socket.setSoTimeout(15000);
            InputStream raw = socket.getInputStream();
            long secondsSince1900 = 0;
            for (int i = 0; i < 4; i++) {
                secondsSince1900 = (secondsSince1900 << 8 | raw.read());
            }
            long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
            long msSince1970 = secondsSince1970 * 1000;
            Date time = new Date(msSince1970);
            return time;

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e2) {
            }
        }
    }
}
