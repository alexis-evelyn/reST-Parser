package me.alexisevelyn.restparser.document.tokens;

import me.alexisevelyn.restparser.utility.LexerHelper;

// https://docutils.sourceforge.io/docs/user/rst/quickref.html#doctest-blocks

public class DoctestBlock implements Token {
	private static final String DEFAULT_DOCTEST_BLOCK_REGEX = "^>>> .*";
	private static final String DEFAULT_DOCTEST_BLOCK_REGEX_ALT = "^>>>";

	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	@Override
	public boolean isHandled(String token) {
		return isDoctestBlock(token);
	}

	public static boolean isDoctestBlock(String token) {
		String firstLine = LexerHelper.getLine(token, 0);

		return firstLine.matches(DEFAULT_DOCTEST_BLOCK_REGEX) || firstLine.matches(DEFAULT_DOCTEST_BLOCK_REGEX_ALT);
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String getName() {
		return "Doctest Block";
	}
}
