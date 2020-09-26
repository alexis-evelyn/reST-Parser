package me.alexisevelyn.restparser;

import org.apache.commons.text.TextStringBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Parser {
	Parser(@NotNull File file) throws Exception {
		parseFile(file);
	}

	private static void parseFile(@NotNull File file) throws Exception {
		String fileContents = readFile(file).toString();

		countTags(Arrays.asList(fileContents.split("\n\n")));
	}

	private static TextStringBuilder readFile(@NotNull File file) throws Exception {
		if (!file.exists() || !file.canRead())
			throw new Exception("File cannot be read/found!");

		TextStringBuilder stringBuilder = new TextStringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		bufferedReader.lines().forEach(stringBuilder::appendln);

		return stringBuilder;
	}

	// Replace with Implementation In Tests
	private static void countTags(List<String> tags) {
		int Heading = 0;

		for (String tag : tags) {
			if (ParserHelper.isHeading(tag)) {
				Heading++;
			}
		}

		System.out.println("Heading: " + Heading);
	}
}
