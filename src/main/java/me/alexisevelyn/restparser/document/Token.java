package me.alexisevelyn.restparser.document;

public interface Token {
	void initialize(String token);
	boolean isHandled(String token);

	default String getName() {
		return "Generic Token";
	}
}
