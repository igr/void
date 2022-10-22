package v.o.i.d.obfuscate.v3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JavaEncoder {
	public void encode() throws IOException {
		File f = new File("message.txt");
		FileReader rd = new FileReader(f);
		char[] buf = new char[(int)f.length()];
		rd.read(buf);
		String message = new String(buf);

		System.out.println(message);

		String a = "labs.jodd.org"; String d = "WMODBA806QRN#$@H";
		int p = 0, x = 0; String result = "";
		int l = message.length();
		int b = 0;
		while (p < l) {
			x = (char) (message.charAt(p++) ^ a.charAt(b++));
			if (b == a.length()) b = 0;
			result += d.charAt(x / 16);
			result += d.charAt(x % 16);
		}
		System.out.println(result);
		System.out.println(result.length());
	}

	public static void main(String[] args) throws IOException {
		new JavaEncoder().encode();
	}
}