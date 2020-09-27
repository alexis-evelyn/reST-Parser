package me.alexisevelyn.restparser.detectors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import java.util.Optional;

// https://stackoverflow.com/a/32863198/6828099

public class RestDetector extends FileTypeDetector {
	/**
	 * Probes the given file to guess its content type.
	 *
	 * <p> The means by which this method determines the file type is highly
	 * implementation specific. It may simply examine the file name, it may use
	 * a file <a href="../attribute/package-summary.html">attribute</a>,
	 * or it may examines bytes in the file.
	 *
	 * <p> The probe result is the string form of the value of a
	 * Multipurpose Internet Mail Extension (MIME) content type as
	 * defined by <a href="http://www.ietf.org/rfc/rfc2045.txt"><i>RFC&nbsp;2045:
	 * Multipurpose Internet Mail Extensions (MIME) Part One: Format of Internet
	 * Message Bodies</i></a>. The string must be parsable according to the
	 * grammar in the RFC 2045.
	 *
	 * @param path the path to the file to probe
	 * @return The content type or {@code null} if the file type is not
	 * recognized
	 * @throws SecurityException If the implementation requires to access the file, and a
	 *                           security manager is installed, and it denies an unspecified
	 *                           permission required by a file system provider implementation.
	 *                           If the file reference is associated with the default file system
	 *                           provider then the {@link SecurityManager#checkRead(String)} method
	 *                           is invoked to check read access to the file.
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
