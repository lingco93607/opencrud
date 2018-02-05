package com.z4.zhazha.forum.rabbitmq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class RabbitMessage implements Serializable {
	private static final long serialVersionUID = -6487839157908352120L;

	private Class<?>[] paramTypes;// 参数类型
	private String exchange;// 交换器

	private Map<String,Object> params;

	private String routeKey;// 路由key

	public RabbitMessage() {
	}

	/*public RabbitMessage(String exchange, String routeKey, Map<String, Object> params) {
		this.params = params;
		this.exchange = exchange;
		this.routeKey = routeKey;
	}*/

	@SuppressWarnings("rawtypes")
	public RabbitMessage(String exchange, String routeKey, Map<String, Object> params) {
		this.params = params;
		this.exchange = exchange;
		this.routeKey = routeKey;
		int len = params.size();
		Class[] clazzArray = new Class[len];
		Iterator itr = params.entrySet().iterator();
		int i=0;
		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) itr.next();
			clazzArray[i] = entry.getValue().getClass();
			i++;
		}
		/*for (int i = 0; i < len; i++) {
			clazzArray[i] = params[i].getClass();
		}*/
		this.paramTypes = clazzArray;
	}

	public byte[] getSerialBytes() {
		byte[] res = new byte[0];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.close();
			res = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getRouteKey() {
		return routeKey;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public void setRouteKey(String routeKey) {
		this.routeKey = routeKey;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
