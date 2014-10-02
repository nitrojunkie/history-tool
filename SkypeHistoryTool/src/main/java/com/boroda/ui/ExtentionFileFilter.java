package com.boroda.ui;

import javax.swing.filechooser.FileFilter;

import java.io.File;

/**
 * Created by dmitrystarchak on 02/10/14.
 */
public class ExtentionFileFilter extends FileFilter {
	private String[] ext;
	private String description;

	public ExtentionFileFilter(String description, String... ext) {
		this.ext = ext;
		this.description = description;
	}

	@Override
	public boolean accept(File file) {
		if (file != null) {
			if (file.isDirectory()) {
				return false;
			}
			String extension = getExtension(file);

			for(String e : ext) {
				if(e.equals(extension)) {
					return true;
				}
			}
		}
		return false;
	}

	public String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return null;
	}

	@Override
	public String getDescription() {
		StringBuilder builder = new StringBuilder(description + ": ");

		for(String e : ext) {
			builder.append("." + e + " ");
		}
		return builder.toString();
	}
}