package in.lowes.urlshortner.util;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import in.lowes.urlshortner.exception.InvalidUrlException;

@Component
public class Validator {
	private static final Logger logger = LoggerFactory.getLogger(Validator.class);
	
	public void validateUrl(String url) throws InvalidUrlException {
		String[] schemes = { "http", "https" };
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (!urlValidator.isValid(url)) {
			logger.debug("<<Validator >>  <<validateUrl>> In-Valid Url");
			throw new InvalidUrlException();
		}
		logger.debug("<<Validator >>  <<validateUrl>> Valid Url");
	}

}
