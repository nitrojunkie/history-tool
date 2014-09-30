package com.dataart.data;

import com.dataart.data.bean.Contact;
import com.dataart.data.bean.Message;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitrystarchak on 29/09/14.
 */
public class SkypeDbApiImpl extends BaseConnector implements SkypeDbApi{
	@Override
	public List<Contact> getContacts() throws SQLException {
		//ResultSetHandler<Contact> handler = new BeanHandler<Contact>(Contact.class);

		ResultSetHandler<List<Contact>> handler = new BeanListHandler<Contact>(Contact.class);

		List<Contact> contacts = run.query("SELECT * FROM Contacts WHERE type = 1", handler);

		return contacts;
	}

	@Override
	public List<Message> getMessages(String partner) throws SQLException {
		ResultSetHandler<List<Message>> handler = new BeanListHandler<Message>(Message.class);

		List<Message> messages = run.query("SELECT * FROM Messages WHERE dialog_partner = ?", handler, partner);

		return messages;
	}

}
