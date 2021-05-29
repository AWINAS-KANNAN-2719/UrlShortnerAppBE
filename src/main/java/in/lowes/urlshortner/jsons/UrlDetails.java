package in.lowes.urlshortner.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import in.lowes.urlshortner.model.Url;
import in.lowes.urlshortner.util.HttpCodes;

public class UrlDetails extends ResponseJson {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5253621234426142195L;
	@JsonInclude(Include.NON_NULL)
	private Long id;
	@JsonInclude(Include.NON_NULL)
	private String shortUrl;
	@JsonInclude(Include.NON_NULL)
	private String originalUrl;
	@JsonInclude(Include.NON_NULL)
	private Integer accessCount;

	public UrlDetails() {
	}
	
	public UrlDetails(final HttpCodes httpCodes) {
		this.httpCode = httpCodes.getHttpCode();
		this.responseCode = httpCodes.getSpecificCode();
		this.responseDescription = httpCodes.getDescription();
	}

	public UrlDetails(final HttpCodes httpCodes, final Url url) {
		this.httpCode = httpCodes.getHttpCode();
		this.responseCode = httpCodes.getSpecificCode();
		this.responseDescription = httpCodes.getDescription();
		this.id = url.getId();
		this.shortUrl = url.getShortUrl();
		this.originalUrl = url.getOriginalUrl();
		this.accessCount = url.getAccessCount();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public Integer getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}

}
