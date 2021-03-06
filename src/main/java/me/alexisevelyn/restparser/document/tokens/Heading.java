package me.alexisevelyn.restparser.document.tokens;

import me.alexisevelyn.restparser.utility.LexerHelper;

public class Heading implements Token {
	// This Regex Was Harder to Figure Out Than I Would've Thought. I'm making it available for public domain explicitly to save others the trouble.
	private static final String DEFAULT_HEADING_REGEX = "^(?=\\W)(\\S)+$"; // https://regexr.com/5cr56 - ^(?=\W)(\S)+$

	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	@Override
	public boolean isHandled(String token) {
		return isHeading(token);
	}

	public static boolean isHeading(String token) {
		return isHeading(token, DEFAULT_HEADING_REGEX);
	}

	public static boolean isHeading(String token, String markerRegex) {
		return isTwoLineHeading(token, markerRegex) || isThreeLineHeading(token, markerRegex);
	}

	public static boolean isTwoLineHeading(String token) {
		return isTwoLineHeading(token, DEFAULT_HEADING_REGEX);
	}

	public boolean isTwoLineHeading() {
		return isTwoLineHeading(this.token, DEFAULT_HEADING_REGEX);
	}

	private static boolean isTwoLineHeading(String token, String markerRegex) {
		int count = LexerHelper.countLines(token);

		if (count < 2)
			return false;

//		System.out.println("Two+ Liner!!!");

		try {
			String headingLine = LexerHelper.getLine(token, 0);
			String delimiterLine = LexerHelper.getLine(token, 1);

			if (delimiterLine.length() < headingLine.length())
				return false;

//			System.out.println("Succeeded Delimiter Line Length Check For 2+ Liner!!!");

//			System.err.println("2 Token: \"" + token + "\" | Passes Final Check: " + delimiterLine.matches(markerRegex));
			return delimiterLine.matches(markerRegex);
		} catch (Exception e) {
			System.err.println("Warning - Token \"" + token + "\" breaks the two liner heading check!!!");
			System.err.println("Exception Is: " + e.getMessage());

			return false;
		}
	}

	public static boolean isThreeLineHeading(String token) {
		return isThreeLineHeading(token, DEFAULT_HEADING_REGEX);
	}

	public boolean isThreeLineHeading() {
		return isThreeLineHeading(this.token, DEFAULT_HEADING_REGEX);
	}

	private static boolean isThreeLineHeading(String token, String markerRegex) {
		int count = LexerHelper.countLines(token);

		if (count < 3)
			return false;

		try {
			String overLine = LexerHelper.getLine(token, 0);
			String headingLine = LexerHelper.getLine(token, 1);
			String underLine = LexerHelper.getLine(token, 2);

			if (overLine.length() != underLine.length() || overLine.length() < headingLine.length())
				return false;

			return overLine.matches(markerRegex) && underLine.matches(markerRegex);
		} catch (Exception e) {
			System.err.println("Warning - Token \"" + token + "\" breaks the three liner heading check!!!");
			System.err.println("Exception Is: " + e.getMessage());

			return false;
		}
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String getName() {
		return "Heading";
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
