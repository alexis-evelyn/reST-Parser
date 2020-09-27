package me.alexisevelyn.restparser.document.tokens;

public interface Token {
	void initialize(String token);
	boolean isHandled(String token);
	String getRawToken();

	default String getName() {
		return "Generic Token";
	}
}
