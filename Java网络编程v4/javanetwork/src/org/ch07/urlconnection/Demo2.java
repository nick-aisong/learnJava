package org.ch07.urlconnection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo2 {
	// 7-2 EncodingAwareSourceViewer.class
	// 用正确的字符集下载一个Web页面
	public static void main(String[] args) {

		args = new String[] { "http://www.baidu.com", "http://www.4399.com" };

		for (int i = 0; i < args.length; i++) {
			// 设置默认编码方式
			String encoding = "GB2312"; // for 4399, http default general is ISO-8859-1
			try {
				URL u = new URL(args[i]);
				URLConnection uc = u.openConnection();
				String contentType = uc.getContentType();
				System.err.println(contentType);
				System.err.println(uc.getContentEncoding());
				int encodingStart = contentType.indexOf("charset=");
				if (encodingStart != -1) {
					encoding = contentType.substring(encodingStart + 8);
					System.err.println(encoding);
				}
				InputStream in = new BufferedInputStream(uc.getInputStream());
				Reader r = new InputStreamReader(in, encoding);
				int c;
				while ((c = r.read()) != -1) {
					System.out.print((char) c);
				}
				r.close();
			} catch (MalformedURLException e) {
				System.err.println(args[i] + " is not a parseable URL");
				System.err.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
	}
}
