package in.lowes.urlshortner.exception;

public class InvalidShortUrl extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 746014116887810171L;

	public InvalidShortUrl() {
		super();
	}
	
	public InvalidShortUrl(String message) {
		super(message);
	}
	
}
