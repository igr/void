package v.o.i.d.lombok;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor(staticName="of")
public class Library {
	private final List<Book> books;

	@NonNull
	public boolean isBookInLibrary(Book book) {
		return books.stream().anyMatch(book::equals);
	}
}
