package in.lowes.urlshortner.util;

import org.apache.commons.validator.routines.UrlValidator;

import in.lowes.urlshortner.exception.InvalidUrlException;

public class Validator {
	private static Validator instance;

	private Validator() {
	}

	public static Validator getInstance() {
		if (instance == null) {
			synchronized (Validator.class) {
				if (instance == null) {
					instance = new Validator();
				}
			}
		}
		return instance;
	}

	public void validateUrl(String url) throws InvalidUrlException {
		String[] schemes = { "http", "https" };
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (!urlValidator.isValid(url)) {
			throw new InvalidUrlException();
		}
	}

}
