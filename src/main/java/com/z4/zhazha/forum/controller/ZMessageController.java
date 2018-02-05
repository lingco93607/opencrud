package com.z4.zhazha.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.z4.zhazha.forum.pojo.ZMessage;
import com.z4.zhazha.forum.service.IService;

/**
 * 处理消息相关的控制器
 * @author xintt
 * @date 2017.11.06
 */

@Controller
@RequestMapping("/message")
public class ZMessageController {
	@Autowired
	@Qualifier("zmessageservice")
	private IService<ZMessage> zmsgService;
	
	

}
