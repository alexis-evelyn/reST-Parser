package me.alexisevelyn.restparser;

import java.util.List;

public class Lexer {
	// TODO: Replace with checking tags from Document object.
	public static void countTags(List<String> tags) {
		int headingTwoLine = 0;
		int headingThreeLine = 0;

		for (String tag : tags) {
			// Sadly Switch Statements Do Not Work With This.
			if (LexerHelper.isTwoLineHeading(tag))
				headingTwoLine++;
			if (LexerHelper.isThreeLineHeading(tag))
				headingThreeLine++;
		}

		System.out.println(TerminalColors.ANSI_TEXT_GREEN + "Underline Heading: " + headingTwoLine);
		System.out.println(TerminalColors.ANSI_TEXT_GREEN + "Overline and Underline Heading: " + headingThreeLine);
		System.out.println(TerminalColors.ANSI_TEXT_RED + "All Headings: " + (headingTwoLine + headingThreeLine));
	}
}
