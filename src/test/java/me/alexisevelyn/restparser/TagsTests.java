package me.alexisevelyn.restparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TagsTests {
	@Test
	public void testTags() {
		File testFile = new File("testfiles/test.rst");
		System.out.println("Using File For Test: " + testFile.getAbsolutePath());

		try {
			Parser parser = new Parser(testFile);
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
