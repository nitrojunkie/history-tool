package com.boroda.data;

import com.boroda.data.bean.Contact;
import com.boroda.data.bean.Message;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitrystarchak on 29/09/14.
 */
public interface SkypeDbApi {

	/**
	 * Retrieves list of all user's contacts
	 */
	public List<Contact> getContacts() throws SQLException;

	/**
	 * Retrieves all stored messages from conversation with specified partner
	 * @param parner
	 * @return
	 * @throws SQLException
	 */
	public List<Message> getMessages(String parner) throws SQLException;
}
