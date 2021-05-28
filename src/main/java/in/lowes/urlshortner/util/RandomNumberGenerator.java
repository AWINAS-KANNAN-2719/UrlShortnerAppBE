package in.lowes.urlshortner.util;

import java.util.Random;

public class RandomNumberGenerator {

	private static RandomNumberGenerator instance;
	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

	private RandomNumberGenerator() {

	}

	public static RandomNumberGenerator getInstance() {
		if (instance == null) {
			synchronized (RandomNumberGenerator.class) {
				if (instance == null) {
					instance = new RandomNumberGenerator();
				}
			}
		}
		return instance;
	}

	/**
	 * @return generated alpha numeric string
	 */
	public String generateRandomString() {
		int randomNumber = getRandomNumber();
		StringBuilder randomString = new StringBuilder();
		while (randomNumber > 0) {
			int index = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			randomString.append(ALPHA_NUMERIC_STRING.charAt(index));
			randomNumber--;
		}
		return randomString.toString();
	}

	/**
	 * @return generated random number between 5 and 10
	 */
	private int getRandomNumber() {
		Random r = new Random();
		int low = 5;
		int high = 11;
		int result = r.nextInt(high - low) + low;
		return result;
	}
}
