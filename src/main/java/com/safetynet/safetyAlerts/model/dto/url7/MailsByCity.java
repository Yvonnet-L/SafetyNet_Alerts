package com.safetynet.safetyAlerts.model.dto.url7;

import java.util.Set;

public class MailsByCity {

	private String city;
	private Set<String> mails;

	public MailsByCity() {
		super();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<String> getMails() {
		return mails;
	}

	public void setMails(Set<String> mails) {
		this.mails = mails;
	}
}
