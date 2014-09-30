package com.dataart.data.bean;

/**
 * Created by dmitrystarchak on 30/09/14.
 */
public class Message {
	private int id;
	private int convoId;
	private String chatname;
	private String author;
	private String dialogPartner;
	private long timestamp;
	private String bodyXml;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConvoId() {
		return convoId;
	}

	public void setConvoId(int convoId) {
		this.convoId = convoId;
	}

	public String getChatname() {
		return chatname;
	}

	public void setChatname(String chatname) {
		this.chatname = chatname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDialogPartner() {
		return dialogPartner;
	}

	public void setDialogPartner(String dialogPartner) {
		this.dialogPartner = dialogPartner;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBodyXml() {
		return bodyXml;
	}

	public void setBodyXml(String bodyXml) {
		this.bodyXml = bodyXml;
	}
}
