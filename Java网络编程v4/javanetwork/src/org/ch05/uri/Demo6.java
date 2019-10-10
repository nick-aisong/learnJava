package org.ch05.uri;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class Demo6 {
	// 5-6 URISplitter.class
	public static void main(String[] args) {

		args = new String[] { "tel:+1-800-8888-0008", "http://www.xml.com/测试pub/a/2003/09/07/stax.html#id=_hbc",
				"urn:isbn:1-565-02322-9" };

		for (int i = 0; i < args.length; i++) {
			try {
				URI u = new URI(args[i]);
				System.out.println("The URI is " + u);
				if (u.isOpaque()) {
					System.out.println("This is an opaque URI.");
					System.out.println("The scheme is " + u.getScheme());
					System.out.println("The scheme specific part is " + u.getSchemeSpecificPart());
					System.out.println("The fragment ID is " + u.getFragment());
				} else {
					System.out.println("This is a hierarchical URI.");
					System.out.println("The scheme is " + u.getScheme());
					try {
						u = u.parseServerAuthority();
						System.out.println("The host is " + u.getHost());
						System.out.println("The user info is " + u.getUserInfo());
						System.out.println("The port is " + u.getPort());
						System.out.println("The authority is " + u.getAuthority());
					} catch (URISyntaxException e) {
						// 必须是基于注册的授权机构
						System.out.println("The authority is " + u.getAuthority());
					}
					System.out.println("The path is " + u.getPath());
					//System.out.println(u.getRawPath());
					System.out.println("The query string is " + u.getQuery());
					System.out.println("The fragment ID is " + u.getFragment());
				}
			} catch (URISyntaxException e) {
				System.err.println(args[i] + " does not seem to be a URI.");
				System.err.flush();
			}
			System.out.println();
		}
	}

	@Test
	public void demo1() {
		URI absolute;
		try {
			absolute = new URI("http://www.example.com/");
			URI relatvie = new URI("images/logo.png");
			URI resolved = absolute.resolve(relatvie);
			System.out.println(resolved);

			URI top = new URI("javafaq/books/");
			URI resolved2 = top.resolve("jnp3/examples/07/index.html");
			System.out.println(resolved2);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void demo2() throws URISyntaxException {
		URI absolute = new URI("http://www.example.com/images/logo.png");
		URI top = new URI("http://www.example.com/");
		URI relative = top.relativize(absolute);
		System.out.println(relative);
	}
}
