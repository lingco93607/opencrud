var add_zhistory = function() {
	$("#closebutton").on('click',function(){
		window.close();
	});
};
add_zhistory.prototype = {

	/**
	 * 调用ajax异步请求，避免重复代码块
	 * 
	 * @param type
	 * @param dataType
	 * @param data
	 * @param url
	 * @param success
	 */
	ajax : function(type,dataType,contentType,data, url, success) {
		$.ajax({
			type : type,
			cache : false,
			dataType : dataType,
			contentType: contentType,
			data : data,
			url : url,
			error : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status == 401 || jqXHR.status == 403|| jqXHR.status == 302) {
					window.location.href = "./welcome.jsp";
				} else if (jqXHR.status == 400) {
					ShowObjProperty(jqXHR);
					alert(jqXHR.responseText);
				} else {
					alert(jqXHR.responseText);
				}
				$('#priButton').jqxButton({
					disabled : false
				});
				$('#cancelButton').jqxButton({
					disabled : false
				});
				return false;
			},
			success : success
		});
	},
	
	/**
	 * 保存记录
	 */
	saveORUpdate : function() {
		 $("#savebutton").on('click',function(){
			 var data = $("#zhistory_info_form").serializeObject();
			 var url = "./action/zhistory/savezhistory";
			 var success = function(data) {
				if (confirm(data.message)) {
					window.opener.location=window.opener.location;
					window.close();
				}
			};
			add_zhistory.prototype.ajax("POST", "json","application/x-www-form-urlencoded;charset=utf-8",data,url,success);
		 });
	},
	
	/**
	 * 将div中的button和text添加jqwigent
	 * 
	 * @param formId
	 */
	initInputButtonAndText : function(formId) {
		$(formId).find("input[type=button]").each(function() {
			$(this).jqxButton({
				theme : "base",
				width : 80,
				height : 24
			});
		});
		$(formId).find("input[type=text]").each(function() {
			$(this).jqxInput({
				theme : "base",
				width : 150,
				height : 20
			});
		});
		$(formId).find("input[type=reset]").each(function() {
			$(this).jqxButton({
				theme : "base",
				width : 72,
				height : 16
			});
		});
	},
	
	/**
	 * 添加验证
	 * 
	 * @param id
	 */
	validator : function(id) {
		$(id).jqxValidator({
			rules : [ {input : '#zUserId',message : 'zUserId不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#zLocation',message : 'zLocation不能为空!',action : 'keyup, blur',rule : 'required'} ]
		});
	}
};

$(function() {
	var zhistory = new add_zhistory();
	try {
		zhistory.saveORUpdate();
	} catch (e) {
		alert("Exception name:" + e.name + "Exception msg:" + e.message);
	}
});
