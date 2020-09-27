package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.Document;
import me.alexisevelyn.restparser.document.Heading;
import me.alexisevelyn.restparser.document.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private final ArrayList<Class<? extends Token>> handlers = new ArrayList<>();

	public Lexer() {
		this.addHandler(Heading.class);
	}

	public void addHandler(Class<? extends Token> handler) {
		this.handlers.add(handler);
	}

	public Document initializeDocument(List<String> tokens) {
		Document document = new Document();

		for (String token : tokens) {
			for (Class<? extends Token> handler : handlers) {
				try {
					// Instantiate Handler Object - Sadly I cannot enforce static methods with an interface. :(
					Token newHandler = handler.getDeclaredConstructor().newInstance();

					// If Handler Handles Token, Add to Document
					if (newHandler.isHandled(token)) {
						newHandler.initialize(token);

						document.add(newHandler);
					}
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
					System.err.println("Failed To Execute Handler: '" + handler.getName() + "'!!!");
					e.printStackTrace();
				}
			}
		}

		return document;
	}
}
