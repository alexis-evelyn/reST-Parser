package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.Document;
import me.alexisevelyn.restparser.document.Heading;
import me.alexisevelyn.restparser.document.Token;

public class LexerHelper {
	private static final String DEFAULT_LINE_DELIMITER = "\n";

	public static int countLines(String str) {
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

	public static String getLine(String str, int lineNumber) {
		return getSubstring(str, DEFAULT_LINE_DELIMITER, lineNumber);
	}

	private static String getSubstring(String str, String delimeter, int lineNumber) {
		return str.split(delimeter)[lineNumber];
	}

	// TODO: Generalize The Check
	public static void countTokens(Document document) {
		int heading = 0;

		for (Token token : document) {
			System.out.println("(Debug) Token Type: " + token.getName());
			System.out.println("(Debug) Token: " + token);

			if (token instanceof Heading) {
				heading++;
			}
		}

		System.out.println(TerminalColors.ANSI_TEXT_GREEN + "Headings: " + heading);
	}
}
