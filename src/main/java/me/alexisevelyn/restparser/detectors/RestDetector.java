package me.alexisevelyn.restparser.detectors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import java.util.Optional;

// https://stackoverflow.com/a/32863198/6828099

public class RestDetector extends FileTypeDetector {
	/**
	 * Detect if file is a reStructuredText document.
	 * <br><br>
	 *
	 * {@inheritDoc}
	 * <br><br>
	 *
	 * @see Files#probeContentType
	 */
	@Override
	public String probeContentType(Path path) {
		Optional<String> extension = getExtension(path.getFileName().toString());

		if (extension.isPresent() && extension.get().equals("rst"))
			return "text/x-rst";

		return null;
	}

	public Optional<String> getExtension(String filename) {
		return Optional.ofNullable(filename)
				.filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
}
