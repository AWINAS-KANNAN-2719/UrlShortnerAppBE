package in.lowes.urlshortner;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import in.lowes.urlshortner.controller.UrlShortnerController;
import in.lowes.urlshortner.jsons.UrlDetails;
import in.lowes.urlshortner.jsons.UrlListJson;
import in.lowes.urlshortner.util.HttpCodes;

@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UrlShortnerApplicationTests {

	@Autowired
	UrlShortnerController controller;

	@LocalServerPort
	int randomServerPort;

	private static final String LOCAL_HOST = "http://localhost:";

	@Test
	@Order(1)
	public void createShortInvalidOriginalUrl() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String createNewShortUrl = LOCAL_HOST + randomServerPort
				+ "/urlshortner/shorten?origUrl=http://www.gmail.c";
		URI uri = new URI(createNewShortUrl);
		ResponseEntity<UrlDetails> result = restTemplate.postForEntity(uri, null, UrlDetails.class);
		Assertions.assertEquals(HttpCodes.HTTP_400_3.getSpecificCode(), result.getBody().getResponseCode());
	}

	@Test
	@Order(2)
	public void getAllUrlNoRecord() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String retrieveAllUrls = LOCAL_HOST + randomServerPort + "/urlshortner/urls";
		URI uri = new URI(retrieveAllUrls);
		ResponseEntity<UrlListJson> result = restTemplate.getForEntity(uri, UrlListJson.class);
		Assertions.assertEquals(HttpCodes.HTTP_400_2.getSpecificCode(), result.getBody().getResponseCode());

	}

	@Test
	@Order(3)
	public void createShortValidOriginalUrl() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String createNewShortUrl = LOCAL_HOST + randomServerPort
				+ "/urlshortner/shorten?origUrl=http://www.gmail.com";
		URI uri = new URI(createNewShortUrl);
		ResponseEntity<UrlDetails> result = restTemplate.postForEntity(uri, null, UrlDetails.class);
		Assertions.assertEquals(HttpCodes.HTTP_201.getSpecificCode(), result.getBody().getResponseCode());

		result = restTemplate.postForEntity(uri, null, UrlDetails.class);
		Assertions.assertEquals(HttpCodes.HTTP_400_1.getSpecificCode(), result.getBody().getResponseCode());

	}

	@Test
	@Order(4)
	public void getAllUrlRecordPresentSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String retrieveAllUrls = LOCAL_HOST + randomServerPort + "/urlshortner/urls";
		URI uri = new URI(retrieveAllUrls);
		ResponseEntity<UrlListJson> result = restTemplate.getForEntity(uri, UrlListJson.class);
		Assertions.assertEquals(HttpCodes.HTTP_202.getSpecificCode(), result.getBody().getResponseCode());

	}

	@Order(5)
	@Test
	public void redirectToFullUrlFail() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String shortUrl = "/fkjvaj";
		System.out.println("randomServerPort" + randomServerPort);
		final String baseUrl = LOCAL_HOST + randomServerPort + shortUrl;
		URI uri = new URI(baseUrl);
		ResponseEntity<UrlDetails> result = restTemplate.getForEntity(uri, UrlDetails.class);
		Assertions.assertEquals(HttpCodes.HTTP_400_4.getSpecificCode(), result.getBody().getResponseCode());
	}

	@Order(6)
	@Test
	public void redirectToFullUrlSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String createNewShortUrl = LOCAL_HOST + randomServerPort
				+ "/urlshortner/shorten?origUrl=http://www.google.com";
		URI uri = new URI(createNewShortUrl);
		ResponseEntity<UrlDetails> result = restTemplate.postForEntity(uri, null, UrlDetails.class);
		final String shortUrl = LOCAL_HOST + randomServerPort + "/" + result.getBody().getShortUrl();
		uri = new URI(shortUrl);
		ResponseEntity<UrlDetails> shortUrlResult = restTemplate.getForEntity(uri, UrlDetails.class);
		Assertions.assertEquals("http://www.google.com", shortUrlResult.getBody().getOriginalUrl());
	}

}
