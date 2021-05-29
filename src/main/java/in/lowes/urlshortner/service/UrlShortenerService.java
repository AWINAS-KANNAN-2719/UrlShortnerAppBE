package in.lowes.urlshortner.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.lowes.urlshortner.exception.InvalidUrlException;
import in.lowes.urlshortner.jsons.ResponseJson;
import in.lowes.urlshortner.jsons.UrlDetails;
import in.lowes.urlshortner.jsons.UrlListJson;
import in.lowes.urlshortner.model.Url;
import in.lowes.urlshortner.repository.UrlShortenerRepository;
import in.lowes.urlshortner.util.HttpCodes;
import in.lowes.urlshortner.util.RandomNumberGenerator;
import in.lowes.urlshortner.util.Validator;

@Service
public class UrlShortenerService {

	private static final Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

	@Autowired
	public UrlShortenerRepository repo;

	@Autowired
	public RandomNumberGenerator generator;

	@Autowired
	public Validator validator;

	public ResponseJson shortenUrl(String originalUrl) {
		logger.debug("<<UrlShortenerService >>  <<shortenUrl>>");
		try {
			validator.validateUrl(originalUrl);
		} catch (InvalidUrlException e) {
			return new UrlDetails(HttpCodes.HTTP_400_3);
		}

		Url url;
		if ((url = repo.findByOriginalUrl(originalUrl)) != null) {
			return new UrlDetails(HttpCodes.HTTP_400_1, url);
		}
		String shortUrl = generator.generateRandomString();
		logger.debug(String.format("<<UrlShortenerService >>  <<shortenUrl>> Short Url id  %s", shortUrl));
		while (repo.isShortUrlAlreadyPresent(shortUrl)) {
			shortUrl = generator.generateRandomString();
		}
		logger.debug(String.format("<<UrlShortenerService >>  <<shortenUrl>> Short Url id  %s", shortUrl));
		url = repo.save(new Url(originalUrl, shortUrl));
		logger.debug(String.format("<<UrlShortenerService >>  <<shortenUrl>>  Url Detatil  %s", url));
		return new UrlDetails(HttpCodes.HTTP_201, url);
	}

	public ResponseJson getAllUrlDetails() {
		logger.debug("<<UrlShortenerService >>  <<getAllUrlDetails>>");
		List<Url> urlList = repo.findAll();
		if (urlList.isEmpty()) {
			return new UrlListJson(HttpCodes.HTTP_400_2);
		}
		return new UrlListJson(HttpCodes.HTTP_202, urlList);

	}

	public ResponseJson getOriginalUrl(String shortUrl) {
		logger.debug("<<UrlShortenerService >>  <<getAllUrlDetails>>");
		Url origUrl = repo.findByShortUrl(shortUrl);
		if (origUrl == null) {
			HttpCodes code = HttpCodes.resolveHTTPCode("URL_SHORT_404");
			return new UrlDetails(code);
		}
		origUrl.setAccessCount(origUrl.getAccessCount() + 1);
		repo.save(origUrl);
		return new UrlDetails(HttpCodes.valueOf("HTTP_203"), origUrl);

	}
}
