package me.alexisevelyn.restparser.document.tokens;

// https://docutils.sourceforge.io/docs/ref/rst/directives.html

public class Directive implements Token {
	private String token;

	@Override
	public void initialize(String token) {
		this.token = token;
	}

	@Override
	public boolean isHandled(String token) {
		// TODO: Figure out how to tokenize this first.
		// TODO: Figure out how to pull tokens out of this.
		return false;
	}

	@Override
	public String getRawToken() {
		return this.token;
	}

	@Override
	public String getName() {
		return "Directive";
	}

	@Override
	public String toString() {
		return this.getRawToken();
	}
}
