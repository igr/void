package v.o.i.d.java10;

import java.util.List;

/**
 * The identifier var is not a keyword; instead it is a reserved type name or a context-sensitive keyword.
 * This means that code that uses var as a variable, method, or package name will not be affected;
 * code that uses var as a class or interface name will be affected (but these names are rare in practice,
 * since they violate usual naming conventions).
 */
public class VarExample {

	public static void main(String[] args) {
		var s = "String";
		var list = List.of(1,2,3);

		System.out.println(s);
		System.out.println(list);
	}
}
