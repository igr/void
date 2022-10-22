package v.o.i.d.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@Builder
public class Person {
	@NonNull
	private final String name;
	private final Integer age;
	private final LocalDate memberSince;
	private final String address;
}
