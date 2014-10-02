package com.boroda;

import com.boroda.data.bean.Contact;
import com.boroda.event.ContactSelectionListener;
import com.boroda.event.FileSelectionListener;
import com.boroda.ui.ContactSelector;
import com.boroda.ui.OutputFileChooser;

import javax.swing.JOptionPane;

import java.util.ResourceBundle;

/**
 * Created by dmitrystarchak on 01/10/14.
 */
public class View implements Runnable {
	public static String OVERWRITE_DIALOG_TITLE_KEY = "fileOverwriteDialogTitle";
	public static String OVERWRITE_DIALOG_MESSAGE_KEY = "fileOverwriteDialogMessage";

	private ResourceBundle labels = ResourceBundle.getBundle("labels");
	private ContactSelector contactSelector;
	private OutputFileChooser outputFileChooser;
	private Application model;

	public View(Application model) {
		this.model = model;
	}

	@Override
	public void run() {
		contactSelector = new ContactSelector();
		outputFileChooser = new OutputFileChooser();

		contactSelector.setContactSelectionListener(new ContactSelectionListener() {
			@Override
			public void onContactSelected(ContactSelectionEvent event) {
				View.this.onContactSelected(event.getContact());
			}
		});

		outputFileChooser.setFileSelectionListener(new FileSelectionListener() {
			@Override
			public void onFileChosen(FileSelectionEvent event) {
				View.this.onFileSelected(event.getPath());
			}
		});

		outputFileChooser.setOutputPath(model.getSelectedPath());
		onStart();
	}

	public void onStart() {
		contactSelector.setData(model.getContacts());
		contactSelector.pack();
		contactSelector.setLocationRelativeTo(null);
		contactSelector.setVisible(true);
	}

	public void onExit() {

	}

	public void onContactSelected(Contact contact) {
		model.setSelectedContact(contact);
		contactSelector.dispose();
		showFileChooser();
	}

	public void showFileChooser() {
		outputFileChooser.pack();
		outputFileChooser.setLocationRelativeTo(null);
		outputFileChooser.setVisible(true);
	}

	public void onFileSelected(String path) {
		model.setSelectedPath(path);

		if (model.isFileExist(path)) {
			if (JOptionPane.showConfirmDialog(null, labels.getString(OVERWRITE_DIALOG_MESSAGE_KEY),
					labels.getString(OVERWRITE_DIALOG_TITLE_KEY), JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
				showFileChooser();
			}
		}

		outputFileChooser.enableWorkInProgress();

		model.saveMessages();
	}

	public void onFileSaved() {
		outputFileChooser.disableProgressBar();
		outputFileChooser.dispose();

		onStart();
	}
}
