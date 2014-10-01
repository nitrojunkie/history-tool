package com.boroda;

import com.boroda.data.bean.Contact;
import com.boroda.event.ContactSelectionListener;
import com.boroda.event.FileSelectionListener;
import com.boroda.ui.ContactSelector;
import com.boroda.ui.OutputFileChooser;

import javax.swing.JOptionPane;

/**
 * Created by dmitrystarchak on 01/10/14.
 */
public class Controller implements Runnable {
	private ContactSelector contactSelector;
	private OutputFileChooser outputFileChooser;
	private Application model;

	public Controller(Application model) {
		this.model = model;
	}

	@Override
	public void run() {
		contactSelector = new ContactSelector();
		outputFileChooser = new OutputFileChooser();

		contactSelector.setContactSelectionListener(new ContactSelectionListener() {
			@Override
			public void onContactSelected(ContactSelectionEvent event) {
				Controller.this.onContactSelected(event.getContact());
			}
		});

		outputFileChooser.setFileSelectionListener(new FileSelectionListener() {
			@Override
			public void onFileChosen(FileSelectionEvent event) {
				Controller.this.onFileSelected(event.getPath());
			}
		});

		onStart();

		System.exit(0);
	}

	public void onStart() {
		contactSelector.setData(model.getContacts());
		contactSelector.pack();
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
		outputFileChooser.setVisible(true);
	}

	public void onFileSelected(String path) {
		if (model.isFileExist(path)) {
			if (JOptionPane.showConfirmDialog(null, "Would You Like to Overwrite Selected File?", "Warning", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
				showFileChooser();
			}
		}

		model.setSelectedPath(path);

		outputFileChooser.enableProgressBar();

		model.saveMessages();

		outputFileChooser.disableProgressBar();
		outputFileChooser.dispose();
	}
}
