package in.lowes.urlshortner.jsons;

import java.io.Serializable;

public abstract class ResponseJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3933352469785303195L;

	protected String httpCode;

	protected String responseCode;

	protected String responseDescription;

	public String getHttpCode() {
		return httpCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

}