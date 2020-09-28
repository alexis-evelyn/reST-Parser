package me.alexisevelyn.restparser;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class TokenizerHelper {
	// TODO: Create better tokenizer to handle edge cases
	public static List<String> tokenizeContents(@NotNull String fileContents) {
		return Arrays.asList(fileContents.split("\n\n"));
	}

	// TODO: Detect Directive Marker and Check For Indented Lines in Future Tokens Before Return
	// TODO: Check For Mashed Headers Specifically Here Too Before Return
}
