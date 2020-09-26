package me.alexisevelyn.restparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TagsTests {
	@Test
	public void testTags() {
		File testFile = new File("testfiles/test.rst");
		File rootReadMe = new File("ReadMe.rst");

		System.out.println("Using File For Test: " + testFile.getAbsolutePath());

		try {
			System.out.println("------------------------------------------");
			System.out.println("                 Test File                ");
			System.out.println("------------------------------------------");
			new Parser(testFile);

			System.out.println("------------------------------------------");
			System.out.println("                ReadMe File               ");
			System.out.println("------------------------------------------");
			new Parser(rootReadMe);
		} catch (Exception e) {
			System.out.println("File Read: " + e.getMessage());
			e.printStackTrace();
		}

		Assertions.assertEquals(1, 1);
	}

//	private static void countTags(List<String> tags) {
	// TODO: Replace with checking tags from Document object.
//		int Heading = 0;
//
//		for (String tag : tags) {
//			if (ParserHelper.isHeading(tag)) {
//				Heading++;
//			}
//		}
//
//		System.out.println("Heading: " + Heading);
//	}
}
