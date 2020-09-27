package me.alexisevelyn.restparser.document.tokens;

import me.alexisevelyn.restparser.LexerHelper;

public class EnumeratedList implements Token {
	private static final String DEFAULT_DOUBLE_BRACKET_LIST = "^\\([a-zA-Z0-9]\\) .+";
	private static final String DEFAULT_SINGLE_LIST = "^[a-zA-Z0-9][.)] .+";

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

			if (!line.matches(DEFAULT_DOUBLE_BRACKET_LIST) && !line.matches(DEFAULT_SINGLE_LIST)) {
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
		return "Enumerated List";
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
