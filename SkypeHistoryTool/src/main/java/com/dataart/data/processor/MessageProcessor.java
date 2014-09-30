package com.dataart.data.processor;

import com.dataart.data.bean.Message;

/**
 * Created by dmitrystarchak on 30/09/14.
 */
public interface MessageProcessor {

	public String processMessage(Message message);
}
