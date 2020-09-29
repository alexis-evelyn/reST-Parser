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

		for (String token : tokens) {
			if (!Heading.isHeading(token) || LexerHelper.countLines(token) <= 3) // 3 Default - 6 For Debugging
				continue;

//			System.out.println("Heading: " + token + " | 3 Line: " + Heading.isThreeLineHeading(token));

			boolean completedTokenSplit = false;
			int splitPos;
			String temporaryToken = token;
			while (!completedTokenSplit) {
//				System.out.println("Token: " + temporaryToken);

				splitPos = 0; // TODO: Set to Zero
				if (Heading.isTwoLineHeading(temporaryToken)) {
					splitPos = LexerHelper.ordinalIndexOf(temporaryToken, LexerHelper.getDefaultLineDelimiter(), 2);
//					System.out.println("Two Liner Split!!! Pos: " + splitPos);
				}

				if (Heading.isThreeLineHeading(temporaryToken)) {
					splitPos = LexerHelper.ordinalIndexOf(temporaryToken, LexerHelper.getDefaultLineDelimiter(), 3);
//					System.out.println("Three Liner Split!!! Pos: " + splitPos);
				}

				String temporaryHeading = temporaryToken.substring(0, splitPos).strip();
				String tempHeadingTwo = temporaryToken.substring(splitPos).strip();

				System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Token 1: " + temporaryHeading.replace("\n", ""));
//				System.out.println(TerminalColors.ANSI_TEXT_GREEN + "Token 2: " + tempHeadingTwo.replace("\n", ""));

				if (LexerHelper.countLines(tempHeadingTwo) <= 3) {
					System.out.println(TerminalColors.ANSI_TEXT_GREEN + "Token 2: " + tempHeadingTwo.replace("\n", ""));

					completedTokenSplit = true;
				} else {
					temporaryToken = tempHeadingTwo;
				}

//				completedTokenSplit = true; // Temporary Stop to Prevent Infinite Loop
			}
		}
	}

	private static void joinDirectiveTokens(@NotNull List<String> tokens) {
		// TODO: Detect Directive Marker and Check For Indented Lines in Future Tokens Before Return
	}
}
