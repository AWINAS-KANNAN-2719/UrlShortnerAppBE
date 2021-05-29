package in.lowes.urlshortner.jsons;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import in.lowes.urlshortner.model.Url;
import in.lowes.urlshortner.util.HttpCodes;

public class UrlListJson extends ResponseJson {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5538346239214759806L;
	@JsonInclude(Include.NON_NULL)
	List<Url> urlList;

	public UrlListJson() {}
	
	public UrlListJson(final HttpCodes httpCodes) {
		this.httpCode = httpCodes.getHttpCode();
		this.responseCode = httpCodes.getSpecificCode();
		this.responseDescription = httpCodes.getDescription();
		this.urlList = null;
	}

	public UrlListJson(final HttpCodes httpCodes, List<Url> urlList) {
		this.httpCode = httpCodes.getHttpCode();
		this.responseCode = httpCodes.getSpecificCode();
		this.responseDescription = httpCodes.getDescription();
		this.urlList = new ArrayList<>(urlList);
	}

	public List<Url> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<Url> urlList) {
		this.urlList = urlList;
	}

}
