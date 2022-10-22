package v.o.i.d.java9.process;

public class Kill {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: " + ProcessHandle.current().info().commandLine().orElse("<kill>") + " <pid>");
			return;
		}

		ProcessHandle.of(Long.parseLong(args[0])).ifPresent(handle -> handle.destroy());
	}
}
