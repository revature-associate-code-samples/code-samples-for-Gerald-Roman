package com.revature.pojos;

public class AccountType {

	private int typeID;
	private String type;

	public AccountType() {
	}

	public AccountType(int typeID, String type) {
		super();
		this.typeID = typeID;
		this.type = type;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account Type [TypeId =" + typeID + ", type=" + type + "]";
	}

}
