package com.coface.corp.translationView;

public class UserData {

	private String accountId;

	private String displayedName;
	
	public UserData(String accountId, String displayedName) {
		this.accountId = accountId;
		this.displayedName = displayedName;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getDisplayedName() {
		return displayedName;
	}
	
}

