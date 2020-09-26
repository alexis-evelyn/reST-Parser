package me.alexisevelyn.restparser;

public class ParserHelper {
	public static boolean isHeading(String tag) {
		return isHeading(tag, "[#~]+");
	}

	public static boolean isHeading(String tag, String markerRegex) {
		int count = countLines(tag);

		if (count == 2)
			return isTwoLineHeading(tag, markerRegex);
		if (count == 3)
			return isThreeLineHeading(tag, markerRegex);

		return false;
	}

	public static boolean isTwoLineHeading(String tag, String markerRegex) {
		try {
			String HeadingLine = getLine(tag, 0);
			String delimiterLine = getLine(tag, 1);

			if (delimiterLine.length() < HeadingLine.length())
				return false;

			return delimiterLine.matches(markerRegex);
		} catch (Exception e) {
			System.err.println("Warning - Tag \"" + tag + "\" breaks the two liner Heading check!!!");
			System.err.println("Exception Is: " + e.getMessage());

			return false;
		}
	}

	public static boolean isThreeLineHeading(String tag, String markerRegex) {
		String overLine = getLine(tag, 0);
		String HeadingLine = getLine(tag, 1);
		String underLine = getLine(tag, 2);

		if (overLine.length() != underLine.length() || HeadingLine.length() < overLine.length())
			return false;

		return overLine.matches(markerRegex) && underLine.matches(markerRegex);
	}

	private static int countLines(String str) {
		return countDelimiter(str, "\n");
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
		return getLine(str, "\n", lineNumber);
	}

	private static String getLine(String str, String delimeter, int lineNumber) {
		return str.split(delimeter)[lineNumber];
	}
}
