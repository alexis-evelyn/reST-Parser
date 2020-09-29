package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.tokens.Heading;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class TokenizerHelper {
	// TODO: Create better tokenizer to handle edge cases
	public static List<String> tokenizeContents(@NotNull String fileContents) {
		List<String> tokens = splitOnNewLines(fileContents);

		// Fixes Edge Case Mashed Headings By Splitting Them Into Separate Tokens
		splitEdgeCaseHeadings(tokens);

		// Fixes Directives by combining the tokens
		joinDirectiveTokens(tokens);

		return tokens;
	}

	private static List<String> splitOnNewLines(@NotNull String fileContents) {
		return Arrays.asList(fileContents.split("\n\n"));
	}

	private static void splitEdgeCaseHeadings(@NotNull List<String> tokens) {
		// TODO: Check For Mashed Headers Specifically Here Too Before Return


	}

	private static void joinDirectiveTokens(@NotNull List<String> tokens) {
		// TODO: Detect Directive Marker and Check For Indented Lines in Future Tokens Before Return

		for (String token : tokens) {
			if (!Heading.isHeading(token) || LexerHelper.countLines(token) <= 3)
				continue;

			System.out.println("Heading: " + token + " | 3 Line: " + Heading.isThreeLineHeading(token));
		}
	}
}
