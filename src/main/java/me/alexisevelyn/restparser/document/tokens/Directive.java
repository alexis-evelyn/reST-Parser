package me.alexisevelyn.restparser.document.tokens;

// https://docutils.sourceforge.io/docs/ref/rst/directives.html

import me.alexisevelyn.restparser.LexerHelper;

// TODO: Figure out how to pull tokens out of this.
public class Directive implements Token {
	private static final String DEFAULT_DIRECTIVE_REGEX = "\\.\\. [A-Za-z0-9\\-]+::";
	private static final String DEFAULT_DIRECTIVE_REGEX_ENDING_TEXT = "\\.\\. [A-Za-z0-9\\-]+::[ \\w.]+";

	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	@Override
	public boolean isHandled(String token) {
		return isDirective(token);
	}

	public static boolean isDirective(String token) {
		String firstLine = LexerHelper.getLine(token, 0);

		return firstLine.matches(DEFAULT_DIRECTIVE_REGEX) || firstLine.matches(DEFAULT_DIRECTIVE_REGEX_ENDING_TEXT);
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String getName() {
		return "Directive";
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
