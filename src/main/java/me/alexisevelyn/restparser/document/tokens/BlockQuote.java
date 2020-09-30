package me.alexisevelyn.restparser.document.tokens;

// https://docutils.sourceforge.io/docs/user/rst/cheatsheet.html

import me.alexisevelyn.restparser.utility.LexerHelper;

// TODO: Fix this in the tokenizer!!!
public class BlockQuote implements Token {
	private static final String DEFAULT_BLOCKQUOTE_REGEX = "^[\\t ].+";

	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	@Override
	public boolean isHandled(String token) {
		return isBlockQuote(token);
	}

	public static boolean isBlockQuote(String token) {
		String firstLine = LexerHelper.getLine(token, 0);

		return firstLine.matches(DEFAULT_BLOCKQUOTE_REGEX);
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String getName() {
		return "Block Quote";
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
