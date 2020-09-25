package me.alexisevelyn;

// It appears pre-formatted blocks are the only ones that don't follow the new tag after empty line rule.
// Pull Example Document From: https://github.com/edx/edx-documentation/blob/master/en_us/edx_style_guide/source/ExampleRSTFile.rst
// reST format: https://docutils.readthedocs.io/en/sphinx-docs/user/rst/quickstart.html
// Test Editor: https://livesphinx.herokuapp.com/

import java.io.File;

public class Main {
	public static void main(String[] args) {
		File file;

		if (args.length == 0)
			file = new File("ExampleRSTFile.rst");
		else
			file = new File(args[0]);

		System.out.println("Using File: " + file.getAbsolutePath());
	}
}
