package com.z4.zhazha.forum.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushUtil {
		protected static final Logger LOG = LoggerFactory.getLogger(PushUtil.class);

		// demo App defined in resources/jpush-api.conf
		/*private static final String appKey = "2230dd05c6d9681a04618ebe";
		private static final String masterSecret = "da7bb77dfb620c7d4a0764f2";*/
		
		private static final String appKey = "42e0236f4649f738b40356ae";
		private static final String masterSecret = "3701ec400b44eee5461d56e0";

		public static final String MSG_CONTENT = "";
		public static final String REGISTRATION_ID = "";
		public static final String TAG = "siji";

		public static void main(String[] args) {
			pushToUser("18511111111","test");
			pushToUser2("15889610183","test",1,1);
			System.out.println("done");
		}

		public static boolean pushToUser(String telephone, String title) {
			JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
			PushPayload payload = buildPushObject_all_alias_alert(telephone,title);
			try {
				PushResult result = jpushClient.sendPush(payload);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		/*public static PushResult push2User(String alert) {
			JPushClient jpushClient = new JPushClient(masterSecret, appKey);
			PushPayload payload = buildPushObject_all_all_alert(alert);
			try {
				return jpushClient.sendPush(payload);
			} catch (APIConnectionException e) {
				//log.error("connection err:" + e);
				e.printStackTrace();
				return null;
			} catch (APIRequestException e) {
				e.printStackTrace();
				return null;
			}
		}*/
		
		public static PushPayload buildPushObject_all_all_alert(String alert) {
			return PushPayload.alertAll(alert);
		}
		
		public static PushPayload buildPushObject_all_alias_alert(String telephone,String title) {
		        return PushPayload.newBuilder()
		                .setPlatform(Platform.all())
		                .setAudience(Audience.tag(telephone))
		                .setNotification(Notification.alert(title))
		                .build();
		}
		public static boolean pushToUser2(String telephone, String title,int message_id,int type) {
			JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
			PushPayload payload = buildPushObject_all_alias_alert2(telephone,title,message_id,type);
			try {
				PushResult result = jpushClient.sendPush(payload);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		public static PushPayload buildPushObject_all_alias_alert23(String telephone,String title,int message_id,int type) {
			return PushPayload
					.newBuilder()
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.tag(telephone))
					.setNotification(
							Notification
									.newBuilder()
									.setAlert(title)
									.addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtra("message_id", message_id).build())
									.addPlatformNotification(
											IosNotification.newBuilder().incrBadge(1).addExtra("type", type)
													.build()).build())
					.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
		}
		public static PushPayload buildPushObject_all_alias_alert2(String telephone,String title,int message_id,int type) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.tag(telephone))
	                .setNotification(Notification.alert(title))
	                .build();
		}

	}
