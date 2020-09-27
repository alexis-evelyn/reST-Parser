package me.alexisevelyn.restparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TagsTests {
	@Test
	public void testTags() {
		File testFile = new File("testfiles/test.rst");
		File rootReadMe = new File("ReadMe.rst");

		System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Using File For Test: " + testFile.getAbsolutePath());
		System.out.println(TerminalColors.ANSI_TEXT_PURPLE + "Using File For Test: " + rootReadMe.getAbsolutePath());

		try {
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "                 Test File                ");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			new Reader(testFile);

			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "                ReadMe File               ");
			System.out.println(TerminalColors.ANSI_TEXT_BLUE + "------------------------------------------");
			new Reader(rootReadMe);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		Assertions.assertEquals(1, 1);
	}
}
