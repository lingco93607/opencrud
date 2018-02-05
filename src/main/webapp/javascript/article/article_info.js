var add_article = function() {
	$("#closebutton").on('click',function(){
		window.close();
	});
};
add_article.prototype = {

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
			 var data = $("#article_info_form").serializeObject();
			 var url = "./action/article/savearticle";
			 var success = function(data) {
				if (confirm(data.message)) {
					window.opener.location=window.opener.location;
					window.close();
				}
			};
			add_article.prototype.ajax("POST", "json","application/x-www-form-urlencoded;charset=utf-8",data,url,success);
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
			rules : [ {input : '#path',message : 'path不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#readNum',message : 'readNum不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#voteNum',message : 'voteNum不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#articleID',message : 'articleID不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#publishDate',message : 'publishDate不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#text',message : 'text不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#authorID',message : 'authorID不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#type',message : 'type不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#title',message : 'title不能为空!',action : 'keyup, blur',rule : 'required'},{input : '#status',message : 'status不能为空!',action : 'keyup, blur',rule : 'required'} ]
		});
	}
};

$(function() {
	var article = new add_article();
	try {
		article.saveORUpdate();
	} catch (e) {
		alert("Exception name:" + e.name + "Exception msg:" + e.message);
	}
});
