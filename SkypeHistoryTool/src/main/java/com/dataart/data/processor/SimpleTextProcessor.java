package com.dataart.data.processor;

import com.dataart.data.bean.Message;

/**
 * Created by dmitrystarchak on 30/09/14.
 */
public class SimpleTextProcessor implements MessageProcessor {
	@Override
	public String processMessage(Message message) {
		String text = " - " + message.getAuthor() + ": " + message.getBodyXml();
		return text;
	}
}
