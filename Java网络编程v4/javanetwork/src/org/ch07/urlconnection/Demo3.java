package org.ch07.urlconnection;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo3 {
    // 7-3 BinarySaver.class
    // 从Web网站下载二进制文件并保存到磁盘
    public static void main(String[] args) {

        args = new String[]{
                "file://////C://Users//NKS/Desktop//temp//Java+Web%E4%BB%8E%E5%85%A5%E9%97%A8%E5%88%B0%E7%B2%BE%E9%80%9A.pdf"};

        for (int i = 0; i < args.length; i++) {
            try {
                URL root = new URL(args[i]);
                saveBinaryFile(root);
            } catch (MalformedURLException e) {
                System.err.println(args[i] + " is not URL I understand.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveBinaryFile(URL u) throws IOException {
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        System.out.println(uc.getContentType());
        System.out.println(uc.getContentEncoding());
        System.out.println(uc.getContentLength());
        // uc.getContentLengthLong()// java 7
        if (contentType.startsWith("text/") || contentLength == -1) { // 不是文件或者没有长度
            throw new IOException("This is not a binary file.");
        }

        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;
            // http有可能断断续续发送文件
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1)
                    break;
                offset += bytesRead;
            }
            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
            }
            String filename = u.getFile();
            filename = filename.substring(filename.lastIndexOf('/') + 1);
            System.out.println(filename);
            try (FileOutputStream fout = new FileOutputStream(filename)) {
                // fout.write(data);
                System.out.println((data.length / 1024.0 / 1024.0 + "").substring(0, 4) + "MB");
                fout.flush();
            }
        }
    }
}
// application/pdf
// null
// 3005225
// Java+Web%E4%BB%8E%E5%85%A5%E9%97%A8%E5%88%B0%E7%B2%BE%E9%80%9A.pdf
// 2.86MB
