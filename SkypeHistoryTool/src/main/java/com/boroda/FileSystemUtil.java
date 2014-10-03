package com.boroda;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Locale;

/**
 * Class dedicated to file system related jobs:
 * - Discovering paths to Skype DB
 */
public class FileSystemUtil {
	public static final String USERHOME = System.getProperty("user.home");
	public static final String SKYPE_SUBFOLDER_ON_MAC = "/Library/Application Support/Skype/";
	public static final String SKYPE_SUBFOLDER_ON_WINDOWS = "/AppData/Roaming/Skype/";
	public static final String DB_FILE = "main.db";

	public static File[] findDBs() {
		File skypeDirectory;

		String osname = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
		if ((osname.indexOf("mac") >= 0) || (osname.indexOf("darwin") >= 0)) {
			skypeDirectory = new File(USERHOME + SKYPE_SUBFOLDER_ON_MAC);
		} else if (osname.indexOf("win") >= 0) {
			skypeDirectory = new File(USERHOME + SKYPE_SUBFOLDER_ON_WINDOWS);
		} else if (osname.indexOf("nux") >= 0) {
			//TODO
			return null;
		} else {
			//TODO
			return null;
		}

		return skypeDirectory.listFiles(new FileFilter() {
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return file.listFiles(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							return name.equals(DB_FILE);
						}
					}).length == 1;
				}
				return false;
			}
		});
	}

	public static void main(String args[]) {
		System.out.println(findDBs().length);
		System.out.println(findDBs()[0]);
	}
}
