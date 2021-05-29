package in.lowes.urlshortner.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.lowes.urlshortner.jsons.ResponseJson;
import in.lowes.urlshortner.service.UrlShortenerService;

@RestController
public class ShortUrlRedirectionController {

	private static final Logger logger = LoggerFactory.getLogger(ShortUrlRedirectionController.class);

	@Autowired
	private UrlShortenerService urlShortenerService;

	@GetMapping("/{shortenString}")
	public ResponseJson redirectToFullUrl(@PathVariable String shortenString)
			throws IOException {
		logger.debug("<<ShortUrlRedirectionController >>  <<redirectToFullUrl>> " + shortenString);
		return urlShortenerService.getOriginalUrl(shortenString);
	}
}
