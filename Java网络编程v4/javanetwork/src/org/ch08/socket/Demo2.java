package org.ch08.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 8-2 Daytime.class
// 通过与time.nist.gov对话构造一个Date
public class Demo2 {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(Demo2.getDateFromNetwork().toString());
    }

    public static Date getDateFromNetwork() throws IOException, ParseException {
        try (Socket socket = new Socket("time.nist.gov", 13)) {
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c);
            }
            System.out.println(time.toString());
            return parseDate(time.toString());
        }
    }

    static Date parseDate(String s) throws ParseException {
        String[] pieces = s.split(" ");
        String dateTime = pieces[1] + " " + pieces[2] + " UTC";
        System.out.println(dateTime);
        DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
        return format.parse(dateTime);
    }

}
