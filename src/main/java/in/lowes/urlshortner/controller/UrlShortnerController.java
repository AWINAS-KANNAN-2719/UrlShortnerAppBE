package in.lowes.urlshortner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.lowes.urlshortner.jsons.ResponseJson;
import in.lowes.urlshortner.service.UrlShortenerService;

@RestController
@RequestMapping(value = "/urlshortner")
public class UrlShortnerController {

	private static final Logger logger = LoggerFactory.getLogger(UrlShortnerController.class);

	@Autowired
	private UrlShortenerService urlShortenerService;

	@RequestMapping(value = "/shorten", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseJson shortenUrl(@RequestParam("origUrl") String originalUrl) {
		logger.debug("<<UrlShortnerController >>  <<shortenUrl>> " +originalUrl );
		return urlShortenerService.shortenUrl(originalUrl);
	}


	@RequestMapping(value = "/urls", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseJson getAllExistingUrlWithCount(String originalUrl) {
		logger.debug("<<UrlShortnerController >>  <<getAllExistingUrlWithCount>>");
		return urlShortenerService.getAllUrlDetails();

	}

}
