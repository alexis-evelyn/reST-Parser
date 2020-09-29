package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.tokens.Directive;
import me.alexisevelyn.restparser.document.tokens.Heading;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Attempt at Better Tokenization!!!
 */
public class TokenizerHelper {
	public static List<String> tokenizeContents(@NotNull String fileContents) {
		List<String> tokens = splitOnNewLines(fileContents);

		// Fixes Edge Case Mashed Headings By Splitting Them Into Separate Tokens
		tokens = splitEdgeCaseHeadings(tokens);

		// Fixes Directives by combining the tokens
		return joinDirectiveTokens(tokens);
	}

	private static List<String> splitOnNewLines(@NotNull String fileContents) {
		return Arrays.asList(fileContents.split("\n\n"));
	}

	@NotNull
	private static ArrayList<String> splitEdgeCaseHeadings(@NotNull List<String> tokens) {
		ArrayList<String> modifiedTokens = new ArrayList<>(tokens);

		int currentTokenPos = 0;
		for (String token : tokens) {
			currentTokenPos++;

			if (!Heading.isHeading(token) || LexerHelper.countLines(token) <= 3)
				continue;

			ArrayList<String> fixedHeadings = new ArrayList<>();
			boolean completedTokenSplit = false;
			String temporaryToken = token;
			while (!completedTokenSplit) {
				int splitPos = 0;
				if (Heading.isTwoLineHeading(temporaryToken))
					splitPos = LexerHelper.ordinalIndexOf(temporaryToken, LexerHelper.getDefaultLineDelimiter(), 2);

				if (Heading.isThreeLineHeading(temporaryToken))
					splitPos = LexerHelper.ordinalIndexOf(temporaryToken, LexerHelper.getDefaultLineDelimiter(), 3);

				String tempHeadingOne = temporaryToken.substring(0, splitPos).strip();
				String tempHeadingTwo = temporaryToken.substring(splitPos).strip();

				fixedHeadings.add(tempHeadingOne);
//				System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Token 1: " + tempHeadingOne.replace("\n", ""));

				if (LexerHelper.countLines(tempHeadingTwo) <= 3) {
					fixedHeadings.add(tempHeadingTwo);
//					System.out.println(TerminalColors.ANSI_TEXT_GREEN + "Token 2: " + tempHeadingTwo.replace("\n", ""));

					completedTokenSplit = true;
				} else {
					temporaryToken = tempHeadingTwo;
				}
			}

//			printColor(TerminalColors.ANSI_TEXT_CYAN, "Fixed Headings: " + fixedHeadings.toString() + LexerHelper.getDefaultLineDelimiter());
			modifiedTokens.remove(currentTokenPos);
			modifiedTokens.addAll(currentTokenPos, fixedHeadings);
		}

		return modifiedTokens;
	}

	@NotNull
	private static ArrayList<String> joinDirectiveTokens(@NotNull List<String> tokens) {
		// TODO: Detect Directive Marker and Check For Indented Lines in Future Tokens Before Return
		ArrayList<String> modifiedTokens = new ArrayList<>(tokens);

		int currentTokenPos = 0;
		for (String token : tokens) {
			currentTokenPos++;

			if (!Directive.isDirective(token))
				continue;

			printColor(TerminalColors.ANSI_TEXT_YELLOW, "Directive: " + LexerHelper.getDefaultLineDelimiter() + mergeDirectiveTokens(currentTokenPos, modifiedTokens));
			System.out.println();
		}

		return modifiedTokens;
	}

	private static String mergeDirectiveTokens(int startingPos, ArrayList<String> tokens) {
		String DIRECTIVE_ENDING_REGEX = "(^[\\S]+)[\\w\\d\\s]+";
		String DIRECTIVE_ENDING_REGEX_ALT = "^[\\S]+";

		StringBuilder currentDirective = new StringBuilder().append(tokens.get(startingPos-1)).append(LexerHelper.getDefaultLineDelimiter()).append(LexerHelper.getDefaultLineDelimiter());
		for (int currentTokenPos = startingPos; currentTokenPos < tokens.size(); currentTokenPos++) {
//			printColor(TerminalColors.ANSI_TEXT_RED, tokens.get(currentTokenPos));

			if (LexerHelper.getLine(tokens.get(currentTokenPos), 0).matches(DIRECTIVE_ENDING_REGEX) || LexerHelper.getLine(tokens.get(currentTokenPos), 0).matches(DIRECTIVE_ENDING_REGEX_ALT))
				break;

			currentDirective.append(tokens.get(currentTokenPos));
			currentDirective.append(LexerHelper.getDefaultLineDelimiter()).append(LexerHelper.getDefaultLineDelimiter());
		}

		return currentDirective.toString();
	}

	private static void printColor(TerminalColors color, String token) {
		for (int lineNumber = 0; lineNumber < LexerHelper.countLines(token); lineNumber++) {
			System.out.println(color + LexerHelper.getLine(token, lineNumber));
		}
	}
}
