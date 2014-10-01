package com.boroda;

import com.boroda.data.SkypeDbApi;
import com.boroda.data.SkypeDbApiImpl;
import com.boroda.data.bean.Contact;
import com.boroda.data.bean.Message;
import com.boroda.data.processor.MessageProcessor;
import com.boroda.data.processor.SimpleTextProcessor;

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
public class Application {
	SkypeDbApi api;
	MessageProcessor processor;
	List<Contact> contacts;
	Contact selectedContact;
	String selectedPath;

	View view;

	public static void main(String[] args) throws ClassNotFoundException {
		Application model = new Application();

		javax.swing.SwingUtilities.invokeLater(model.view);
	}

	public Application() {
		api = new SkypeDbApiImpl();
		processor = new SimpleTextProcessor();
		view = new View(this);
	}

	public Contact[] getContacts() {
		try {
			contacts = api.getContacts();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contacts.toArray(new Contact[contacts.size()]); //TODO: make api return array
	}

	public void saveMessagesAsText(List<Message> messages, String path, MessageProcessor processor) {
		File file = new File(path);

		try {
			file.createNewFile();

			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
			for (Message message : messages) {
				String s = processor.processMessage(message);
				outputStream.write(s.getBytes(Charset.defaultCharset()));
			}
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}

	public boolean isFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	public void saveMessages() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				List<Message> messages = null;
				try {
					messages = api.getMessages(selectedContact.getSkypename());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (messages != null && !messages.isEmpty()) {
					saveMessagesAsText(messages, selectedPath, processor);
				}
				view.onFileSaved();
			}
		};

		Thread t = new Thread(r);
		t.start();
	}

	public void setSelectedPath(String selectedPath) {
		this.selectedPath = selectedPath;
	}
}