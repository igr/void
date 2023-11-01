package v.o.i.d.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ProxyExample {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("dps.iso.apple.com", 443));
		URL url = new URI("http://google.com").toURL();

		System.out.println("open");
		HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
		System.out.println("connect");
		uc.connect();
		System.out.println("read");

		StringBuilder page = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			page.append(line).append("\n");
		}
		System.out.println(page);
	}

}
