package me.alexisevelyn.restparser.document.tokens;

import me.alexisevelyn.restparser.utility.LexerHelper;

public class BulletedList implements Token {
	private static final String DEFAULT_BULLET_REGEX = "^[*\\-+] .+";
	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	@Override
	public boolean isHandled(String token) {
		String line;
		for (int lineNumber = 0; lineNumber < LexerHelper.countLines(token); lineNumber++) {
			line = LexerHelper.getLine(token, lineNumber);

			if (!line.matches(DEFAULT_BULLET_REGEX)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String getName() {
		return "Bulleted List";
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
