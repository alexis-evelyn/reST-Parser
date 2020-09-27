package me.alexisevelyn.restparser;

public class LexerHelper {
	// This Regex Was Harder to Figure Out Than I Would've Thought. I'm making it available for public domain explicitly to save others the trouble.
	private static final String DEFAULT_HEADING_REGEX = "^(?=\\W)(\\S)+$"; // https://regexr.com/5cr56 - ^(?=\W)(\S)+$
	private static final String DEFAULT_LINE_DELIMITER = "\n";
	
	public static boolean isHeading(String token) {
		return isHeading(token, DEFAULT_HEADING_REGEX);
	}

	public static boolean isHeading(String token, String markerRegex) {
		return isTwoLineHeading(token, markerRegex) || isThreeLineHeading(token, markerRegex);
	}

	public static boolean isTwoLineHeading(String token) {
		return isTwoLineHeading(token, DEFAULT_HEADING_REGEX);
	}

	public static boolean isTwoLineHeading(String token, String markerRegex) {
		int count = countLines(token);

		if (count < 2)
			return false;

		try {
			String headingLine = getLine(token, 0);
			String delimiterLine = getLine(token, 1);

			if (delimiterLine.length() < headingLine.length())
				return false;

//			System.err.println("2 Token: " + token);
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

	public static boolean isThreeLineHeading(String token, String markerRegex) {
		int count = countLines(token);

		if (count < 3)
			return false;

		try {
			String overLine = getLine(token, 0);
			String headingLine = getLine(token, 1);
			String underLine = getLine(token, 2);

			if (overLine.length() != underLine.length() || overLine.length() < headingLine.length())
				return false;

			return overLine.matches(markerRegex) && underLine.matches(markerRegex);
		} catch (Exception e) {
			System.err.println("Warning - Token \"" + token + "\" breaks the three liner heading check!!!");
			System.err.println("Exception Is: " + e.getMessage());

			return false;
		}
	}

	private static int countLines(String str) {
		return countDelimiter(str.trim(), DEFAULT_LINE_DELIMITER);
	}

	// Modified From: https://stackoverflow.com/a/18816371/6828099
	private static int countDelimiter(String str, String delimeter) {
		if(str == null || str.isEmpty())
			return 0;

		// Count How Often Delimiter Is Found
		int lines = 1;
		int pos = 0;
		while ((pos = str.indexOf(delimeter, pos) + 1) != 0)
			lines++;

		return lines;
	}

	private static String getLine(String str, int lineNumber) {
		return getSubstring(str, DEFAULT_LINE_DELIMITER, lineNumber);
	}

	private static String getSubstring(String str, String delimeter, int lineNumber) {
		return str.split(delimeter)[lineNumber];
	}
}
