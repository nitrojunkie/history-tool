package com.boroda.data.processor;

import com.boroda.data.bean.Message;

/**
 * Created by dmitrystarchak on 30/09/14.
 */
public class SimpleTextProcessor implements MessageProcessor {
	@Override
	public String processMessage(Message message) {
		String text = "\t- " + message.getAuthor() + ": \n" + message.getBody_xml() + "\n";
		return text;
	}
}
