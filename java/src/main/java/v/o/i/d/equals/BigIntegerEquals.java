package v.o.i.d.equals;

import java.math.BigInteger;

public class BigIntegerEquals {

	public static void main(String[] args) {
		BigInteger a = new BigInteger("0");

		System.out.println(a == BigInteger.ZERO);
		System.out.println(a.equals(BigInteger.ZERO));

		BigInteger b = new BigInteger("1").subtract(new BigInteger("1"));
		System.out.println(b.equals(BigInteger.ZERO));

	}
}
