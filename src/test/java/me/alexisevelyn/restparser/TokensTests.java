package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.exceptions.FileReadException;
import me.alexisevelyn.restparser.exceptions.InvalidFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TokensTests {
	@Test
	public void testTokens() {
		File testFile = new File("testfiles/test.rst");
		File rootReadMe = new File("ReadMe.rst");

		System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Using File For Test: " + testFile.getAbsolutePath());
		System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Using File For Test: " + rootReadMe.getAbsolutePath());

		try {
			Reader testReader = new Reader(testFile);
			Reader readMeReader = new Reader(rootReadMe);

			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "                 Test File                ");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			LexerHelper.printTokenCounts(testReader.getDocument());

			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "                ReadMe File               ");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			LexerHelper.printTokenCounts(readMeReader.getDocument());
		} catch (IOException | InvalidFileException | FileReadException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

//		Assertions.assertEquals(1, 1);
	}
}
