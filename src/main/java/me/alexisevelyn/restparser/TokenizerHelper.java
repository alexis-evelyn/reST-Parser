package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.tokens.Directive;
import me.alexisevelyn.restparser.document.tokens.Heading;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
		int fixedTokenSkip = 0;
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
			modifiedTokens.remove(currentTokenPos + fixedTokenSkip - 1);
			modifiedTokens.addAll((currentTokenPos - 1) + fixedTokenSkip, fixedHeadings);
			fixedTokenSkip += fixedHeadings.size() - 1;
		}

		return modifiedTokens;
	}

	@NotNull
	private static ArrayList<String> joinDirectiveTokens(@NotNull List<String> tokens) {
		ArrayList<String> modifiedTokens = new ArrayList<>(tokens);
		ArrayList<Map<int[], String>> preProcessedTokens = new ArrayList<>();

		int currentTokenPos = 0;
		for (String token : tokens) {
			currentTokenPos++;

			if (!Directive.isDirective(token))
				continue;

//			printColor(TerminalColors.ANSI_TEXT_YELLOW, "Directive: " + LexerHelper.getDefaultLineDelimiter() + mergeDirectiveTokens(currentTokenPos, modifiedTokens));
//			System.out.println();

			// 6, 0, 6, 3, 22
			preProcessedTokens.add(mergeDirectiveTokens(currentTokenPos, modifiedTokens));
		}

		// I should probably verify the Map to ensure type safety and null checks
		for (Map<int[], String> preProcessedToken : preProcessedTokens) {
			int[] positions = (int[]) preProcessedToken.keySet().toArray()[0];

			int startPos = positions[0];
			int endingPos = positions[1];
			String directiveToken = (String) preProcessedToken.values().toArray()[0];

//			System.out.println(TerminalColors.ANSI_TEXT_CYAN + "" + startPos + " | " + endingPos);
//			printColor(TerminalColors.ANSI_TEXT_YELLOW, directiveToken);
//
//			printColor(TerminalColors.ANSI_TEXT_CYAN, modifiedTokens.get(startPos));
//			printColor(TerminalColors.ANSI_TEXT_WHITE, modifiedTokens.get(endingPos));

			modifiedTokens.set(startPos, directiveToken);

			// TODO: Remove these tokens and adjust currentPos based on previously removed tokens
			for (int currentPos = startPos + 1; currentPos <= endingPos; currentPos++) {
				printColor(TerminalColors.ANSI_TEXT_YELLOW, modifiedTokens.get(currentPos));
			}
		}

		printColor(TerminalColors.ANSI_TEXT_YELLOW, "------------------------------------------------------------------------------------------");
		printColor(TerminalColors.ANSI_TEXT_RED, TerminalColors.ANSI_TEXT_GREEN, modifiedTokens);
		printColor(TerminalColors.ANSI_TEXT_PURPLE, "------------------------------------------------------------------------------------------");

		return modifiedTokens;
	}

	private static Map<int[], String> mergeDirectiveTokens(int startingPos, ArrayList<String> tokens) {
		String DIRECTIVE_ENDING_REGEX = "(^[\\S]+).*";

		StringBuilder currentDirective = new StringBuilder().append(tokens.get(startingPos-1)).append(LexerHelper.getDefaultLineDelimiter()).append(LexerHelper.getDefaultLineDelimiter());
		int endingPos = tokens.size();
		for (int currentTokenPos = startingPos; currentTokenPos < tokens.size(); currentTokenPos++) {
//			printColor(TerminalColors.ANSI_TEXT_RED, tokens.get(currentTokenPos));

			if (LexerHelper.getLine(tokens.get(currentTokenPos), 0).matches(DIRECTIVE_ENDING_REGEX)) {
				endingPos = currentTokenPos;
				break;
			}

			currentDirective.append(tokens.get(currentTokenPos));
			currentDirective.append(LexerHelper.getDefaultLineDelimiter()).append(LexerHelper.getDefaultLineDelimiter());
		}

		HashMap<int[], String> outputDirective = new HashMap<>();
		int[] positions = new int[2];
		positions[0] = startingPos - 1;
		positions[1] = endingPos - 1;

		outputDirective.put(positions, currentDirective.toString().trim());
		return outputDirective;
	}

	private static void printColor(TerminalColors color, String token) {
		for (int lineNumber = 0; lineNumber < LexerHelper.countLines(token); lineNumber++) {
			System.out.println(color + LexerHelper.getLine(token, lineNumber));
		}
	}

	private static void printColor(TerminalColors color, List<String> tokens) {
		printColor(color, color, tokens);
	}

	private static void printColor(TerminalColors color, TerminalColors color2, List<String> tokens) {
		boolean firstColor = true;
		for (String token : tokens) {
			if (firstColor)
				printColor(color, token);
			else
				printColor(color2, token);

			firstColor = !firstColor;
		}
	}
}
