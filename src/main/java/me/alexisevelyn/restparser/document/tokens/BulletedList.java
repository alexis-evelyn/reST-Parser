package me.alexisevelyn.restparser.document.tokens;

import me.alexisevelyn.restparser.LexerHelper;
import me.alexisevelyn.restparser.document.Token;

public class BulletedList implements Token {
	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	@Override
	public boolean isHandled(String token) {
		String line;
		for (int lineNumber = 0; lineNumber < LexerHelper.countLines(token); lineNumber++) {
			line = LexerHelper.getLine(token, lineNumber);

			if (!line.startsWith("* ")) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String getName() {
		return "Bulleted List";
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
