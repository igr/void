package v.o.i.d.fun;

import jodd.io.FileUtil;

import java.io.IOException;

public class OblacMaker {

	static String $$ = "WMODBA806QRN#$@H";
	static String $$$ = "https://oblac.rs";
	static int counter;

	public static void main(String[] args) throws IOException {
		final String message = FileUtil.readString("Oblac.txt");
		final byte[] bytes = message.getBytes();

		for (byte b : bytes) {
			b ^= $$$.charAt(counter);

			int x = b / 16;
			int y = b % 16;

			System.out.print($$.charAt(x));
			System.out.print($$.charAt(y));

			if (++counter == $$$.length()) {
				counter = 0;
			}
		}
	}

}