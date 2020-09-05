package v.o.i.d.lombok;

import lombok.val;

import java.util.List;

public class LombokExample {
	public static void main(String[] args) {
		System.out.println("https://projectlombok.org");

		val book = new Book("84");

		val library = Library.of(List.of(book));

		//library.isBookInLibrary(null);

		System.out.println(library.isBookInLibrary(new Book("84")));

		// person

		Person person = Person.builder()
				.name("John Malkovich")
				.address("Ulica")
				.age(25)
				.build();

		System.out.println(person);

		// value

		ValueExample.Exercise.of("name", 173);

		ValueExample ve = new ValueExample("hello", 22, 2.3).withAge(24);
		System.out.println(ve);

	}
}
