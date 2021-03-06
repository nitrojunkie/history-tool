package com.boroda.ui;

import com.boroda.data.bean.Contact;
import com.boroda.event.ContactSelectionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ContactSelector extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JList contactsList;

	private ContactSelectionListener contactSelectionListener;

	public ContactSelector() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});

// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK() {
		Contact selected = (Contact) contactsList.getSelectedValue();
		contactSelectionListener.onContactSelected(new ContactSelectionListener.ContactSelectionEvent(selected));
	}

	private void onCancel() {
// add your code here if necessary
		dispose();
	}

	public void setData(Contact[] data) {
		contactsList.setListData(data);
	}

	public void setContactSelectionListener(ContactSelectionListener contactSelectionListener) {
		this.contactSelectionListener = contactSelectionListener;
	}
}
