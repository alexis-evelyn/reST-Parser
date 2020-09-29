package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.Document;
import me.alexisevelyn.restparser.document.tokens.Token;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public static void printTokenCounts(Document document) {
		ArrayList<String> tokenNames = new ArrayList<>();

		// Is there a more efficient way than creating the above temporary variable?
		for (Token token : document) {
//			if (token instanceof Unidentified) {
////				System.out.println("(Debug) Token Type: " + token.getName());
//				System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "(Debug) Token: " + token.getRawToken());
//			}

			tokenNames.add(token.getName());
		}

		Stream.of(tokenNames.toArray())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.forEach((key, value)
						-> System.out.println(String.valueOf(TerminalColors.ANSI_TEXT_GREEN) + key + ": " + value));
	}

	/**
	 * Pulled From: https://stackoverflow.com/a/3976656/6828099
	 *
	 * @param str
	 * @param substr
	 * @param n
	 * @return
	 */
	public static int ordinalIndexOf(String str, String substr, int n) {
		int pos = str.indexOf(substr);
		while (--n > 0 && pos != -1)
			pos = str.indexOf(substr, pos + 1);
		return pos;
	}

	public static String getDefaultLineDelimiter() {
		return DEFAULT_LINE_DELIMITER;
	}
}
