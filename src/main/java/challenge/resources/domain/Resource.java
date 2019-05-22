package challenge.resources.domain;

public class Resource {
	private final String id;
	private final String url;

	public Resource(String id, String url) {
		this.id = id;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}
}
