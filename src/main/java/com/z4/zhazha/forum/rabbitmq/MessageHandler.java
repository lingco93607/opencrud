package com.z4.zhazha.forum.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageHandler {
	private Log log = LogFactory.getLog(getClass());
	
	public void handleMessage(CommenMessage msg) {
		try{
			System.out.println("..." + msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
