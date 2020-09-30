package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.exceptions.FileReadException;
import me.alexisevelyn.restparser.exceptions.InvalidFileException;
import me.alexisevelyn.restparser.utility.LexerHelper;
import me.alexisevelyn.restparser.utility.TerminalColors;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TokensTests {
	@Test
	public void testTokens() {
//		testTestFile();
		testEdgeCasesFile();
//		testReadMeFile();

//		Assertions.assertEquals(1, 1);
	}

	private static void testTestFile() {
		File testFile = new File("testfiles/test.rst");

		System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Using File For Test: " + testFile.getAbsolutePath());

		try {
			Reader testReader = new Reader(testFile);

			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "                 Test File                ");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			LexerHelper.printTokenCounts(testReader.getDocument());
		} catch (IOException | InvalidFileException | FileReadException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void testEdgeCasesFile() {
		File testFile = new File("testfiles/edgecases.rst");

		System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Using File For Test: " + testFile.getAbsolutePath());

		try {
			Reader testReader = new Reader(testFile);

			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "              Edge Cases File             ");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			LexerHelper.printTokenCounts(testReader.getDocument());
		} catch (IOException | InvalidFileException | FileReadException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void testReadMeFile() {
		File testFile = new File("ReadMe.rst");

		System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Using File For Test: " + testFile.getAbsolutePath());

		try {
			Reader testReader = new Reader(testFile);

			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "                ReadMe File               ");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			LexerHelper.printTokenCounts(testReader.getDocument());
		} catch (IOException | InvalidFileException | FileReadException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
