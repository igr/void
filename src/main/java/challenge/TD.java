package challenge;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.io.FileUtil;
import jodd.json.JsonSerializer;
import jodd.util.CsvUtil;
import jodd.util.StringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.UnknownFormatConversionException;

public class TD {

	public static void main(String[] args) throws IOException {
		String[] lines = FileUtil.readLines(FileUtil.file("~/TD.csv"));

		for (String line : lines) {
			String[] columns = CsvUtil.toStringArray(line);

			final int counter = Integer.parseInt(columns[0]);
			final String dateString = parseDate(columns[1]);
			final String meetupLink = columns[8];
			final String name = columns[3];
			final String link = columns[6];
			final String description = columns[7];
			final String speakers = columns[4] + " <" + columns[5] + ">";

			if (counter == 32) {
//				createMeetup(counter, dateString, meetupLink);
//				return;

				createPresenter(name, description, link, speakers);
			}
		}
	}

	private static String parseDate(String dateString) {
		String[] dateFields = StringUtil.splitc(dateString, '-');

		int month = 0;
		switch (dateFields[1]) {
			case "Dec": month++;
			case "Nov": month++;
			case "Oct": month++;
			case "Sep": month++;
			case "Aug": month++;
			case "Jul": month++;
			case "Jun": month++;
			case "May": month++;
			case "Apr": month++;
			case "Mar": month++;
			case "Feb": month++;
			case "Jan": month++; break;
			default:
				throw new UnknownFormatConversionException(dateString);
		}

		return dateFields[2] + "-" + month + "-" + dateFields[0];
	}

	private static void createMeetup(int count, String dateString, String URL) {
		System.out.println("Create meetup: " + count);
		final HashMap<String, Object> fields = new HashMap<>();
		fields.put("Date", dateString);
		fields.put("ID", count);
		fields.put("URL", URL);

		final HashMap<String, Object> map = new HashMap<>();
		map.put("fields", fields);

		HttpResponse httpResponse =
			HttpRequest
			.post("https://api.airtable.com/v0/appNUbQdF6KjbUOHy/Meetups")
			.contentTypeJson()
			.tokenAuthentication("keydx1Av5cQ2kIScq")
			.body(JsonSerializer.create().serialize(map))
			.send();

		System.out.println(httpResponse.statusCode());
	}

	private static void createPresenter(String name, String description, String URL, String speakers) {
		System.out.println("Create presenter: " + name);
		final HashMap<String, Object> fields = new HashMap<>();
		fields.put("Name", name);
		fields.put("Description", description);
		fields.put("URL", URL);
		fields.put("Speakers", speakers);

		final HashMap<String, Object> map = new HashMap<>();

		map.put("fields", fields);

		HttpRequest httpRequest = HttpRequest
			.post(" https://api.airtable.com/v0/appNUbQdF6KjbUOHy/Presenters")
			.tokenAuthentication("keydx1Av5cQ2kIScq")
			.bodyText(JsonSerializer.create().serialize(map))
			.contentTypeJson();

		HttpResponse httpResponse = httpRequest.send();

		System.out.println(httpResponse.statusCode());
	}

}