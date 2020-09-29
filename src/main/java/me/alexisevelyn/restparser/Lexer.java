package me.alexisevelyn.restparser;

import me.alexisevelyn.restparser.document.Document;
import me.alexisevelyn.restparser.document.tokens.*;
import me.alexisevelyn.restparser.document.tokens.Unidentified;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private final ArrayList<Class<? extends Token>> handlers = new ArrayList<>();

	public Lexer() {
		this.addHandler(Heading.class);
		this.addHandler(BulletedList.class);
		this.addHandler(Directive.class);
		this.addHandler(EnumeratedList.class);
	}

	public void addHandler(Class<? extends Token> handler) {
		this.handlers.add(handler);
	}

	public void removeHandler(Class<? extends Token> handler) {
		this.handlers.remove(handler);
	}

	public Document initializeDocument(String fileContents) {
		List<String> tokens = TokenizerHelper.tokenizeContents(fileContents);

		Document document = new Document();

		boolean foundHandler;
		for (String token : tokens) {
			foundHandler = false;

			for (Class<? extends Token> handler : handlers) {
				try {
					// Instantiate Handler Object - Sadly I cannot enforce static methods with an interface. :(
					Token newHandler = handler.getDeclaredConstructor().newInstance();

					// If Handler Handles Token, Add to Document
					if (newHandler.isHandled(token)) {
						newHandler.initialize(token);

						document.add(newHandler);
						foundHandler = true;
					}
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
					System.err.println("Failed To Execute Handler: '" + handler.getName() + "'!!! Removing Handler!!!");
					e.printStackTrace();

					this.removeHandler(handler);
				}
			}

			// If handler not found, add to unidentified handlers!
			if (!foundHandler) {
				Unidentified unidentified = new Unidentified();
				unidentified.initialize(token);
				document.add(unidentified);
			}
		}

		return document;
	}
}
