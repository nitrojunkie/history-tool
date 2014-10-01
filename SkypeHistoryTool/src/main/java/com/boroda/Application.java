package com.boroda;

import com.boroda.data.SkypeDbApi;
import com.boroda.data.SkypeDbApiImpl;
import com.boroda.data.bean.Contact;
import com.boroda.data.bean.Message;
import com.boroda.data.processor.MessageProcessor;
import com.boroda.data.processor.SimpleTextProcessor;
import com.boroda.event.ContactSelectionListener;
import com.boroda.event.FileSelectionListener;
import com.boroda.ui.ContactSelector;
import com.boroda.ui.OutputFileChooser;

import java.awt.EventQueue;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitrystarchak on 29/09/14.
 */
public class Application implements Runnable {
	ContactSelector contactSelector;
	OutputFileChooser outputFileChooser;
	SkypeDbApi api;
	MessageProcessor processor;
	List<Contact> contacts;
	Contact selectedContact;
	String selectedPath;

	public static void main(String[] args) throws ClassNotFoundException {
		Runnable r = new Application();

		EventQueue.invokeLater(r);
	}

	@Override
	public void run() {
		api = new SkypeDbApiImpl();

		contactSelector = new ContactSelector();
		outputFileChooser = new OutputFileChooser();

		processor = new SimpleTextProcessor();

		contactSelector.setContactSelectionListener(new ContactSelectionListener() {
			@Override
			public void onContactSelected(ContactSelectionEvent event) {
				Application.this.onContactSelected(event.getContact());
			}
		});

		outputFileChooser.setFileSelectionListener(new FileSelectionListener() {
			@Override
			public void onFileChosen(FileSelectionEvent event) {
				Application.this.onFileSelected(event.getPath());
			}
		});

		onStart();

		System.exit(0);
	}

	public void onStart() {
		try {
			contacts = api.getContacts();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		contactSelector.setData(contacts.toArray(new Contact[contacts.size()]));
		contactSelector.pack();
		contactSelector.setVisible(true);
	}

	public void onExit() {

	}

	public void onContactSelected(Contact contact) {
		selectedContact = contact;
		contactSelector.dispose();

		outputFileChooser.pack();
		outputFileChooser.setVisible(true);
	}

	public void onFileSelected(String path) {
		outputFileChooser.dispose();

		List<Message> messages = null;

		try {
			messages = api.getMessages(selectedContact.getSkypename());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(messages != null && !messages.isEmpty()) {
			saveMessagesAsText(messages, path, processor);
		}

	}


	public void saveMessagesAsText(List<Message> messages, String path, MessageProcessor processor) {
		File file = new File(path);

		try {
			if(file.createNewFile()) {
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
				for(Message message : messages) {
					String s = processor.processMessage(message);
					outputStream.write(s.getBytes(Charset.defaultCharset()));
				}

				outputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
