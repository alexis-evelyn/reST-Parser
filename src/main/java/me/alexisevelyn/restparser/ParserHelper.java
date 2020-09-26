package me.alexisevelyn.restparser;

public class ParserHelper {
	// This Regex Was Harder to Figure Out Than I Would've Thought. I'm making it available for public domain explicitly to save others the trouble.
	private static final String DEFAULT_HEADING_REGEX = "^(?=\\W)(\\S)+$"; // https://regexr.com/5cr56 - ^(?=\W)(\S)+$

	public static boolean isHeading(String tag) {
		return isHeading(tag, DEFAULT_HEADING_REGEX);
	}

	public static boolean isHeading(String tag, String markerRegex) {
		return isTwoLineHeading(tag, markerRegex) || isThreeLineHeading(tag, markerRegex);
	}

	public static boolean isTwoLineHeading(String tag) {
		return isTwoLineHeading(tag, DEFAULT_HEADING_REGEX);
	}

	public static boolean isTwoLineHeading(String tag, String markerRegex) {
		int count = countLines(tag);

		if (count < 2)
			return false;

		try {
			String headingLine = getLine(tag, 0);
			String delimiterLine = getLine(tag, 1);

			if (delimiterLine.length() < headingLine.length())
				return false;

			System.err.println("2 Tag: " + tag);
			return delimiterLine.matches(markerRegex);
		} catch (Exception e) {
			System.err.println("Warning - Tag \"" + tag + "\" breaks the two liner heading check!!!");
			System.err.println("Exception Is: " + e.getMessage());

			return false;
		}
	}

	public static boolean isThreeLineHeading(String tag) {
		return isThreeLineHeading(tag, DEFAULT_HEADING_REGEX);
	}

	public static boolean isThreeLineHeading(String tag, String markerRegex) {
		int count = countLines(tag);

		if (count < 3)
			return false;

		try {
			String overLine = getLine(tag, 0);
			String headingLine = getLine(tag, 1);
			String underLine = getLine(tag, 2);

			if (overLine.length() != underLine.length() || overLine.length() < headingLine.length())
				return false;

			return overLine.matches(markerRegex) && underLine.matches(markerRegex);
		} catch (Exception e) {
			System.err.println("Warning - Tag \"" + tag + "\" breaks the three liner heading check!!!");
			System.err.println("Exception Is: " + e.getMessage());

			return false;
		}
	}

	private static int countLines(String str) {
		return countDelimiter(str.trim(), "\n");
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
		return getSubstring(str, "\n", lineNumber);
	}

	private static String getSubstring(String str, String delimeter, int lineNumber) {
		return str.split(delimeter)[lineNumber];
	}
}
