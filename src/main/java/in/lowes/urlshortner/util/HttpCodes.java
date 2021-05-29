package in.lowes.urlshortner.util;

public enum HttpCodes {

	HTTP_201("200", "URL_SHORT_201", "Shorten url generated and stored"),
	HTTP_202("200", "URL_SHORT_202", "Retrieved all url details"),
	HTTP_203("200", "URL_SHORT_203", "Original Url Retrieved"),
	HTTP_400_1("400", "URL_SHORT_401", "Original Url Already present"),
	HTTP_400_2("400", "URL_SHORT_402", "No Records in table"),
	HTTP_400_3("400", "URL_SHORT_403", "Input url is invalid"), 
	HTTP_400_4("400", "URL_SHORT_404", "Invalid short url");

	private String httpCode;
	private String specificCode;
	private String description;

	private HttpCodes(final String httpCode, final String specificCode, final String description) {
		this.httpCode = httpCode;
		this.specificCode = specificCode;
		this.description = description;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public String getSpecificCode() {
		return specificCode;
	}

	public String getDescription() {
		return description;
	}

	public static HttpCodes resolveHTTPCode(String specificCode) {
		HttpCodes code = null;
		for (HttpCodes codes : HttpCodes.values()) {
			if (codes.getSpecificCode() == specificCode) {
				code = codes;
			}
		}
		return code;
	}

}
