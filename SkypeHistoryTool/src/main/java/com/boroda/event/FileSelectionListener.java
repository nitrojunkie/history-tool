package com.boroda.event;

/**
 * Created by dmitrystarchak on 01/10/14.
 */
public interface FileSelectionListener {

	public class FileSelectionEvent {
		private String path;

		public FileSelectionEvent(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}
	}

	public void onFileChosen(FileSelectionEvent event);
}
