package org.ch07.urlconnection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo {
	// 7-1 SourceViewer2.class
	public static void main(String[] args) {

		args = new String[] { "https://www.baidu.com" };

		if (args.length > 0) {
			URL u;
			try {
				u = new URL(args[0]);
				URLConnection uc = u.openConnection();
				try (InputStream raw = uc.getInputStream()) { // 自动关闭
					InputStream buffer = new BufferedInputStream(raw);
					// 将InputStream串链到一个Reader
					Reader reader = new InputStreamReader(buffer);
					int c;
					while ((c = reader.read()) != -1) {
						System.out.print((char) c);
					}
				}
			} catch (MalformedURLException e) {
				System.err.println(args[0] + " is not a parseable URL");
				System.err.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
