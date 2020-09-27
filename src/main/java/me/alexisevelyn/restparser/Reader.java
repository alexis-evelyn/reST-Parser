package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.Document;
import me.alexisevelyn.restparser.exceptions.FileReadException;
import me.alexisevelyn.restparser.exceptions.InvalidFileException;
import org.apache.commons.text.TextStringBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Reader {
	Reader(@NotNull File file) throws IOException, InvalidFileException, FileReadException {
		TextStringBuilder fileContents = readFile(file);
		List<String> tokens = tokenizeContents(fileContents.toString());

		Lexer lexer = new Lexer();
		Document document = lexer.initializeDocument(tokens);

		LexerHelper.countTokens(document);
	}

	private static List<String> tokenizeContents(@NotNull String fileContents) {
		return Arrays.asList(fileContents.split("\n\n"));
	}

	private static TextStringBuilder readFile(@NotNull File file) throws IOException, InvalidFileException, FileReadException {
		if (!file.exists())
			throw new FileNotFoundException("File: '" + file.getAbsolutePath() + "' cannot be found!");

		if (!file.canRead())
			throw new FileReadException("File: '" + file.getAbsolutePath() + "' cannot be read!");

		String mimeType = Files.probeContentType(file.toPath());
		if (mimeType == null || !mimeType.equals("text/x-rst"))
			throw new InvalidFileException("File: '" + file.getAbsolutePath() + "' does not match Mime Type of 'text/x-rst'!");

		TextStringBuilder stringBuilder = new TextStringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		bufferedReader.lines().forEach(stringBuilder::appendln);

		return stringBuilder;
	}
}
