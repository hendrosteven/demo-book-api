package com.brainmatics.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage {
	private List<String> messages = new ArrayList<>();

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	
}
