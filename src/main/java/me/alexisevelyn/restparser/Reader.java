package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.Document;
import me.alexisevelyn.restparser.exceptions.FileReadException;
import me.alexisevelyn.restparser.exceptions.InvalidFileException;
import me.alexisevelyn.restparser.utility.LexerHelper;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;

public class Reader {
	private Document document;

	Reader(@NotNull File file) throws IOException, InvalidFileException, FileReadException {
		this.initializeReader(readFile(file).toString());
	}

	private void initializeReader(String fileContents) {
		// Initialize Lexer
		Lexer lexer = this.createLexer();

		// Parse Document For Tokens
		this.document = lexer.initializeDocument(fileContents);
	}

	/**
	 * Override this method to customize Lexer
	 *
	 * @return Instance of {@link Lexer}
	 */
	public Lexer createLexer() {
		return new Lexer();
	}

	public Document getDocument() {
		return this.document;
	}

	private static StringBuilder readFile(@NotNull File file) throws IOException, InvalidFileException, FileReadException {
		if (!file.exists())
			throw new FileNotFoundException("File: '" + file.getAbsolutePath() + "' cannot be found!");

		if (!file.canRead())
			throw new FileReadException("File: '" + file.getAbsolutePath() + "' cannot be read!");

		String mimeType = Files.probeContentType(file.toPath());
		if (mimeType == null || !mimeType.equals("text/x-rst"))
			throw new InvalidFileException("File: '" + file.getAbsolutePath() + "' does not match Mime Type of 'text/x-rst'!");

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		bufferedReader.lines().forEach(line -> stringBuilder.append(line).append(LexerHelper.getDefaultLineDelimiter()));

		return stringBuilder;
	}
}
