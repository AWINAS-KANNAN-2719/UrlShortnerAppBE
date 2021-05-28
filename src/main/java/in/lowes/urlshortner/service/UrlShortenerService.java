package in.lowes.urlshortner.service;

import java.util.List;

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

	@Autowired
	public UrlShortenerRepository repo;

	public ResponseJson shortenUrl(String originalUrl) {
		try {
			Validator.getInstance().validateUrl(originalUrl);
		} catch (InvalidUrlException e) {
			return new UrlDetails(HttpCodes.HTTP_400_3);
		}

		Url url;
		if ((url = repo.findByOriginalUrl(originalUrl)) != null) {
			return new UrlDetails(HttpCodes.HTTP_400_1, url);
		}
		String shortUrl = RandomNumberGenerator.getInstance().generateRandomString();
		System.out.println(shortUrl);
		while (repo.isShortUrlAlreadyPresent(shortUrl)) {
			shortUrl = RandomNumberGenerator.getInstance().generateRandomString();
			System.out.println(shortUrl);
		}
		url = repo.save(new Url(originalUrl, shortUrl));
		System.out.println(url);
		return new UrlDetails(HttpCodes.HTTP_201, url);

	}

	public ResponseJson getAllUrlDetails() {
		List<Url> urlList = repo.findAll();
		if (urlList.isEmpty()) {
			return new UrlListJson(HttpCodes.HTTP_400_2);
		}
		return new UrlListJson(HttpCodes.HTTP_202, urlList);

	}
	

	public ResponseJson getOriginalUrl(String shortUrl)  {
		Url origUrl = repo.findByShortUrl(shortUrl);
		if (origUrl == null) {
			return new UrlDetails(HttpCodes.HTTP_400_4);
		}
		origUrl.setAccessCount(origUrl.getAccessCount() + 1);
		repo.save(origUrl);
		return new UrlDetails(HttpCodes.HTTP_203,origUrl);

	}
}
