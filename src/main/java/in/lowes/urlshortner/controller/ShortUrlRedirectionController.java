package in.lowes.urlshortner.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.lowes.urlshortner.jsons.ResponseJson;
import in.lowes.urlshortner.service.UrlShortenerService;

@RestController
public class ShortUrlRedirectionController {

	@Autowired
	private UrlShortenerService urlShortenerService;

	@GetMapping("/{shortenString}")
	public ResponseJson redirectToFullUrl(HttpServletResponse response, @PathVariable String shortenString)
			throws  IOException {
		return urlShortenerService.getOriginalUrl(shortenString);
	}
}
