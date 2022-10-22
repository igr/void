package v.o.i.d.primes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class SimpleFileAccess {

	private final String fileName;
	private PrintWriter out;

	public SimpleFileAccess(String v) throws IOException {
		fileName = v;
	}

	public String getFileName() {
		return fileName;
	}

	public void open() throws IOException {
		out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
	}

	public void close() {
		out.close();
	}

	public void println(int i) {
		out.println(i);
	}
}
