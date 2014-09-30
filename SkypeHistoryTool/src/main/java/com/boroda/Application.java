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
	public static void main(String[] args) throws ClassNotFoundException {
		Runnable r = new Application();

		EventQueue.invokeLater(r);
	}

	@Override
	public void run() {
		SkypeDbApi api = new SkypeDbApiImpl();
		try {
			ContactSelector dialog = new ContactSelector();
			dialog.pack();
			List<Contact> list = api.getContacts();
			dialog.setData(list.toArray(new Contact[list.size()]));

			dialog.setVisible(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
