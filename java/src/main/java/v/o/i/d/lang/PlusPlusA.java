package v.o.i.d.lang;

public class PlusPlusA {

	public static void main(String[] args) {
		{
			int x = 5;
			x = x++;
			System.out.println(x);  // 5
		}
		{
			int x = 5;
			x = ++x;
			System.out.println(x);  // 5
		}
		{
			int a = 5;
			int b = ++a+a++;

			System.out.println(a);  // 7
			System.out.println(b);  // 12
		}

	}
}
