package com.boroda.event;

import com.boroda.data.bean.Contact;

/**
 * Created by dmitrystarchak on 01/10/14.
 */
public interface ContactSelectionListener {

	public class ContactSelectionEvent {
		private Contact contact;

		public ContactSelectionEvent(Contact contact) {
			this.contact = contact;
		}

		public Contact getContact() {
			return contact;
		}
	}

	public void onContactSelected(ContactSelectionEvent event);
}
