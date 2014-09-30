package com.dataart;

import com.dataart.data.SkypeDbApi;
import com.dataart.data.SkypeDbApiImpl;
import com.dataart.data.bean.Contact;
import com.dataart.ui.ContactSelector;

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
