package com.z4.zhazha.forum.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z4.zhazha.forum.dao.Dao;
import com.z4.zhazha.forum.pojo.Token;
import com.z4.zhazha.forum.pojo.ZUser;
import com.z4.zhazha.forum.util.MessageUtil;
import com.z4.zhazha.forum.util.Storage;
import com.z4.zhazha.forum.util.StringUtil;

import cn.jpush.api.JPushClient;
//import cn.jpush.api.examples.PushExample;

/**
 * 
 * @author xintt on 2017.05.04
 */
@Controller
@RequestMapping("/login")
public class Login {
	
	@Autowired
	@Qualifier("zuserjpa")
	private Dao<ZUser> zuserDao;
	
	/**
	 * 接口001A,接口001B:获取验证码—验证手机号码合法性，合法则发送验证码短信
	 * @param request
	 * @param phoneNo 手机号码
	 * @return json:{message:"获取验证码成功！", status = 0}
	 */
	@RequestMapping(value="/getCode",produces="application/json;charset=UTF-8",method={RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getCode(@RequestBody Map<String, String> map) {
		
		int status = 0;
		String message = "获取验证码成功！";
		
		ZUser zuser = new ZUser();
		zuser.setzUserName("xintt");
		
		Map<String, Object> mapResult = new HashMap();
		mapResult.put("status", Integer.valueOf(status));
		mapResult.put("message", message);
		mapResult.put("zuser", zuser);
		return mapResult;
	}
	/*public Map<String, String> getCode(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "获取验证码成功！";
		
		String phoneNo = (String) map.get("phoneNo");
		
		boolean push = PushExample.pushToUser("test1@server109", "您好，这是一条推送消息");
		if(push) {
			System.out.println("push succeed!");
		}
		
		boolean isLegal = StringUtil.isLegalPhone(phoneNo);
		if (!isLegal) {
			status = 1;
			message = "请输入正确的手机号！";
		} else {
			String msg_id = MessageUtil.sendSMSCode(phoneNo, 36866);
			if (msg_id.equals("") || msg_id.equals("***")) {
				status = 2;
				message = "获取验证码失败";
			} else
				Storage.put(phoneNo, msg_id);
		}
		 
		map.put("message", message);
		map.put("status", String.valueOf(status));
		return map;
	}*/
	
	/**
	 * 接口002A,接口002B：登录—验证验证码与手机号码，验证是否为新用户，新用户则随机生成密码并存储，旧用户则返回密码
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/phoneValidate", produces="application/json;charset=UTF-8", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, String> phoneValidate(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "验证成功";
		boolean newUserflag = false;
		Map<String, String> mapResult = new HashMap();
		
		String phoneNo = (String) map.get("phoneNo");
		String code = (String) map.get("code");
		
		String msg_id = Storage.get(phoneNo);
		boolean isLegalCode = MessageUtil.isValidCode(msg_id, code);
		
		boolean isLegalPhone = StringUtil.isLegalPhone(phoneNo);
		if (isLegalCode && isLegalPhone) {
			String scope = "zusername='" + phoneNo + "'";
			ZUser zuser = zuserDao.get(ZUser.class, scope);
			String zPassword = null;
			if (zuser == null) {
				newUserflag = true;
				zPassword = StringUtil.randomCode(6);
				Storage.put(phoneNo + "_password", zPassword);
			} else {
				//zPassword = zuser.getzPassword();
			}
			mapResult.put("phoneNo", phoneNo);
			mapResult.put("password", zPassword);
		} else if (!isLegalCode) {
			status = 1;
			message = "验证码有误！";
		} else if (!isLegalCode) {
			status = 1;
			message = "手机号码不合法！";
		}
		
		mapResult.put("message", message);
		mapResult.put("status", String.valueOf(status));
		mapResult.put("newUserflag", String.valueOf(newUserflag));
		return mapResult;
	}
	
	/**
	 * 接口003A,接口003B:新用户注册-openfire处理成功后，该用户注册到zuser
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/registerInBackend", produces="application/json;charset=UTF-8", method = {RequestMethod.POST}) 
	@ResponseBody
	public Map<String, String> registerInBackend(@RequestBody Map<String, String> map ) {
		int status = 0;
		String message = "新用户注册成功！";
		Map<String, String> mapResult = new HashMap<String, String>();
		
		String account = map.get("account");
		//String pwd = map.get("pwd");
		String pwd = Storage.get(account + "_password");
		ZUser zuser = new ZUser();
		zuser.setzUserName(account);
		//zuser.setzPassword(pwd);
		boolean save = zuserDao.save(zuser);
		if(!save) {
			status = 1;
			message = "新用户注册失败";
		}
		
		mapResult.put("message", message);
		mapResult.put("status", String.valueOf(status));
		return mapResult;
	}
	
	/**
	 * 接口004A，接口004B：第三方登录
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/QQ", produces = "application/json;charset=UTF-8", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, String> loginByQQ(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "第三方授权登录成功！";
		Map<String, String> mapResult = new HashMap<String, String>();
		
		String openID = map.get("openID");
		boolean newUserflag = false;
		String zPassword = null;
		String scope = "zusername='" + openID + "'";
		ZUser zuser = zuserDao.get(ZUser.class, scope);
		if (zuser == null) {
			newUserflag = true;
			zPassword = StringUtil.randomCode(6);
			Storage.put(openID + "_password", zPassword);
		} else {
			//zPassword = zuser.getzPassword();
		}
		
		mapResult.put("phoneNo", openID);
		mapResult.put("password", zPassword);
		mapResult.put("message", message);
		mapResult.put("status", String.valueOf(status));
		mapResult.put("newUserflag", String.valueOf(newUserflag));
		return mapResult;
	}
	
	@RequestMapping(value="/loginToken", produces = "application/json;charset=UTF-8", method={RequestMethod.POST})
	@ResponseBody
	public Map<String, String> loginToken(@RequestBody Map<String, String> map) {
		int status = 0;
		String message = "创建token成功！";
		Map<String, String> mapResult = new HashMap<String, String>();
		String userName = map.get("userName");		
		String tokenStr = StringUtil.sign(userName);
		
		mapResult.put("token", tokenStr);
		mapResult.put("message", message);
		mapResult.put("status", String.valueOf(status));
		
		return mapResult;
	}
}
