package com.boroda.data.processor;

import com.boroda.data.bean.Message;

/**
 * Created by dmitrystarchak on 30/09/14.
 */
public interface MessageProcessor {

	public String getDefaultExtension();

	public String processMessage(Message message);
}
