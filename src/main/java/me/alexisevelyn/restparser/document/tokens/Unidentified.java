package me.alexisevelyn.restparser.document.tokens;

public class Unidentified implements Token {
	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	/**
	 * This is a special token meant to be used when other tokens fail.
	 * This will always return false.
	 *
	 * @param token token in {@link String} form.
	 * @return false
	 */
	@Override
	public boolean isHandled(String token) {
		return false;
	}

	@Override
	public String getName() {
		return "Unidentified";
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
