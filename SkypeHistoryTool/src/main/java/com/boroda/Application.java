package com.boroda;

import com.boroda.data.SkypeDbApi;
import com.boroda.data.SkypeDbApiImpl;
import com.boroda.data.bean.Contact;
import com.boroda.ui.ContactSelector;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitrystarchak on 29/09/14.
 */
public class Application implements Runnable {
	ContactSelector contactSelector;
	SkypeDbApi api;
	List<Contact> contacts;

	public static void main(String[] args) throws ClassNotFoundException {
		Runnable r = new Application();

		EventQueue.invokeLater(r);
	}

	@Override
	public void run() {
		api = new SkypeDbApiImpl();
		contactSelector = new ContactSelector();
		contactSelector.pack();
		try {
			contacts = api.getContacts();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		contactSelector.setData(contacts.toArray(new Contact[contacts.size()]));

		contactSelector.setVisible(true);
		System.exit(0);
	}
}
