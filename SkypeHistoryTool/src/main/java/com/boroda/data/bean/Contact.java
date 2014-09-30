package com.boroda.data.bean;

/**
 * Created by dmitrystarchak on 29/09/14.
 */
public class Contact {
	private int id;
	private String skypename;
	private String pstnnumber;
	private String fullname;
	private String displayName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkypename() {
		return skypename;
	}

	public void setSkypename(String skypename) {
		this.skypename = skypename;
	}

	public String getPstnnumber() {
		return pstnnumber;
	}

	public void setPstnnumber(String pstnnumber) {
		this.pstnnumber = pstnnumber;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return displayName != null ? displayName : skypename;
	}
}
