package v.o.i.d.obfuscate.v1;

public class JavaEncoder {
	public void encode() {
		String message =
		"\nUkoliko citate ovu poruku, to znaci da imate dovoljno vremena ili da\n" +
		"odlicno poznajete Javu. U jednom i drugom slucaju Vas pozivamo da se\n" +
		"aktivno ukljucite u rad JavaSveta.\n\n" +
		"JavaSvet je otvorena Java zajednica\n" +
		"za sve kojima je Java vaznija od sna.\n\nDobrodosli!\n\n";

		System.out.println(message);

		String a = "JavaSvet"; String d = "WMODBA806QRN#$@H";
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

	public static void main(String[] args) {
		new JavaEncoder().encode();
	}
}