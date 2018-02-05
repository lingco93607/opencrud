package com.z4.zhazha.forum.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import com.alibaba.fastjson.JSONObject;
import com.z4.zhazha.forum.rabbitmq.CommenMessage;

public class FastJsonMessageConverter extends AbstractMessageConverter {
	
	private static Log log = LogFactory.getLog(FastJsonMessageConverter.class);
	public static final String DEFAULT_CHARSET = "UTF-8";
	private volatile String defaultCharset = DEFAULT_CHARSET;
	
	public FastJsonMessageConverter() {
		super();
	}
	
	public void setDefaultCharset(String defaultCharset) {
		this.defaultCharset = (defaultCharset != null) ? defaultCharset:DEFAULT_CHARSET;
	}
	
	public Object fromMessage(Message message) throws MessageConversionException {
		Object o = new CommenMessage();
		try{
			o = fromMessage(message, new CommenMessage());
		}catch(Exception e) {
			log.error(message);
			e.printStackTrace();
		}
		return o;
	}

	@SuppressWarnings("unchecked")
	private <T> T fromMessage(Message message, T t) {
		String json = "";
		try {
			json = new String(message.getBody(), defaultCharset);
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return (T) JSONObject.parseObject(json, t.getClass());
	}

	@Override
	protected Message createMessage(Object object, MessageProperties messageProperties) {
		byte[] bytes= null;
		try {
			String jsonString = JSONObject.toJSONString(object);
			bytes = jsonString.getBytes(this.defaultCharset);
		}catch(UnsupportedEncodingException e) {
			throw new MessageConversionException("Failed to convert Message content", e);
		}
		messageProperties.setContentType(messageProperties.CONTENT_TYPE_JSON);
		messageProperties.setContentEncoding(this.defaultCharset);
		if (bytes != null) {
			messageProperties.setContentLength(bytes.length);
		}
		return new Message(bytes, messageProperties);
	}

}
