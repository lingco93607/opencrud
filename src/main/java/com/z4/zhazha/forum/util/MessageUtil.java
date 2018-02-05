package com.z4.zhazha.forum.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.ValidSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.SMSPayload;

/**
 * @author xintt
 */
public class MessageUtil {
	
	private static SMSClient client = null;
	private static String MASTER_SECRET = "3701ec400b44eee5461d56e0";
	private static String APP_KEY = "42e0236f4649f738b40356ae";
	private static Logger LOG = LoggerFactory.getLogger(MessageUtil.class);
	
	public static String sendSMSCode(String telephone, int temp_id) {
		client = new SMSClient(MASTER_SECRET, APP_KEY);
		SMSPayload payload = SMSPayload.newBuilder()
				.setMobildNumber(telephone)
				.setTempId(temp_id)
				.build();
		
		SendSMSResult res = null;
		try {
			res = client.sendSMSCode(payload);
			LOG.info(res.toString());
		} catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
		return res.getMessageId();
	}
	
	public static boolean isValidCode(String msg_id, String code) {
		ValidSMSResult res = null;
		try {
			res = client.sendValidSMSCode(msg_id, code);
			LOG.info(res.toString());
		} catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Message: " + e.getMessage());
        }
		return res.getIsValid();
	}

}
