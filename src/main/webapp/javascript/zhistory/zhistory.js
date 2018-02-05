var zhistory = function() {
	$("#searchzhistorys").jqxMenu({
		theme : "base",
		width : "100%",
		height : 35
	});
	_ids = new Array();
	_labels = new Array();
};
zhistory.prototype = {
	/**
	 * 初始化数据源的列属性
	 */
	sourceDatafields : [{name : 'zUserId',type : 'int'},{name : 'zLocation',type : 'string'}],
	/**
	 * 初始化Grid的列属性
	 */
	gridColumns : [ {text : 'zhistory zUserId',datafield : 'zUserId',width : '20%'},{text : 'zhistory zLocation',datafield : 'zLocation',width : '20%'} ],
	/**
	 * 初始化数据源
	 * 
	 * @param userName
	 * @param datafields
	 * @param url
	 * @returns {$.jqx.dataAdapter}
	 */
	initSource : function(username, datafields, url) {

		var source = {
			type : "GET",
			datatype : "json",
			data : {
				
			},
			datafields : datafields,
			root : "list",
			// record: "Product",
			id : 'id',
			url : url,
			async : false,
			pager : function(pagenum, pagesize, oldpagenum) {

			},
			beforeprocessing : function(data) {
				source.totalrecords = data.totalCount;
			}
		};
		var dataAdapter = new $.jqx.dataAdapter(source,{
			processData: function (data) {            
                   $.extend(data,$("#searchform").serializeObject());        
            }
		});
		return dataAdapter;
	},
	/**
	 * 初始化Grid
	 * 
	 * @param id
	 * @param dataAdapter
	 * @param columns
	 */
	initGrid : function(id, dataAdapter, columns) {
		$("#" + id).jqxGrid({
			width : '100%',
			height : '88%',
			pagesize : 10,
			pagesizeoptions : [ '5', '10', '20', '50' ],
			source : dataAdapter,
			selectionmode : 'checkbox',
			altrows : true,
			sortable : false,
			pageable : true,
			columnsresize : true,
			virtualmode : true,
			rendergridrows : function() {
				return dataAdapter.records;
			},
			showtoolbar : true,
			rendertoolbar : function(toolbar) {
				zhistory.prototype.render(toolbar);
			},
			columns : columns
		});
	},
	/**
	 * 清空表单里的值
	 */
	emptyForm:function(i){
		var v = document.forms[i].elements;
		for ( var i = 0; i < v.length; i++) {
			if (v[i].type == "text") {
				v[i].value = "";
			}
		}
	},
	/**
	 * 渲染增删改查按钮
	 * 
	 * @param toolbar
	 */
	render : function(toolbar) {
		var container = $("<div style='overflow: hidden; position: relative; margin: 5px; color:#000000'></div>");
		var addButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px; color:#000000; width:16px; height:16px;' src='./images/zz-add.png'/><span style='margin-left: 4px; position: relative; top: -3px; color:#000000'>新增</span></div>");
		var editButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px; color:#000000; width:16px; height:16px;' src='./images/zz-edit.png'/><span style='margin-left: 4px; position: relative; top: -3px; color:#000000'>修改</span></div>");
		var deleteButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px; color:#000000; width:16px; height:16px;' src='./images/zz-delete.png'/><span style='margin-left: 4px; position: relative; top: -3px; color:#000000'>删除</span></div>");
		container.append(addButton);
		container.append(editButton);
		container.append(deleteButton);

		toolbar.append(container);
		addButton.jqxButton({
			width : 60,
			height : 20
		});
		editButton.jqxButton({
			width : 60,
			height : 20
		});
		deleteButton.jqxButton({
			width : 65,
			height : 20
		});

		addButton.click(function(event) {
			zhistory.prototype.emptyForm(1);
			zhistory.prototype.openWindows("当前位置：用户管理&gt&gt新增用户");
		});
		editButton.click(function() {
			var selectedRowIndex = $("#zhistoryManagerGrid").jqxGrid('getselectedrowindexes');
			if (selectedRowIndex.length == 1) {
				var id = $("#zhistoryManagerGrid").jqxGrid('getrowdata',selectedRowIndex[0]);
				$.get("./action/zhistory/getzhistory/"+id.id, 
					function(data) {
					
						$.each(data, function(name, val){
						    var $el = $('#zhistory_info_form input[id="'+name+'"]'),
						        type = $el.attr('type');	
						    switch(type){
						        case 'checkbox':
						            $el.attr('checked', 'checked');
						            break;
						        case 'radio':
						            $el.filter('[value="'+val+'"]').attr('checked', 'checked');
						            break;
						        default:
						            $el.val(val);
						    }
						});
					zhistory.prototype.initInputButtonAndText('#zhistory_info_form');
					zhistory.prototype.validator("#zhistory_info_form");
					zhistory.prototype.openWindows("当前位置：用户管理&gt&gt修改用户信息");
				});
			} else {
				alert("请选择一条记录");
			}
		});
		deleteButton.click(function(event) {
			var selectionObj = new Array();
			var selectedRowIndex = $("#zhistoryManagerGrid").jqxGrid('getselectedrowindexes');
			if (selectedRowIndex.length < 1) {
				alert("请选择一条记录删除");
				return;
			}
			if (confirm("确认要删除吗？")) {
				for ( var i = 0; i < selectedRowIndex.length; i++) {
					var id = $("#zhistoryManagerGrid").jqxGrid('getrowdata',selectedRowIndex[i]);
					selectionObj.push(id.id);
				}
				zhistory.prototype.delReports(selectionObj);
			}
		});
	},
	/**
	 * 调用ajax异步请求，避免重复代码块
	 * 
	 * @param type
	 * @param dataType
	 * @param data
	 * @param url
	 * @param success
	 */
	ajax : function(type, dataType, data, url, success) {
		$.ajax({
			type : type,
			cache : false,
			dataType : dataType,
			data : data,
			url : url,
			error : function(jqXHR, textStatus, errorThrown) {
				if (jqXHR.status == 401 || jqXHR.status == 403
						|| jqXHR.status == 302) {
					window.location.href = "./index.html";
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
	 * 按用户名称查询
	 */
	usersSearch : function() {
		$("#searchbutton").on('click', function(data) {
		    $("zhistoryManagerGrid").jqxGrid('updatebounddata');
	    });
	},
	/**
	 * 删除记录
	 * 
	 * @param usersId
	 */
	delReports : function(usersId) {
		var url = "./action/zhistory/delzhistorys";
		var success = function(data) {
			$('#zhistoryManagerGrid').jqxGrid('updatebounddata');
			$('#zhistoryManagerGrid').jqxGrid('clearselection');
		};
		zhistory.prototype.ajax("POST", "json", {
			"zhistorysId" : usersId
		}, url, success);
	},


	/**
	 * 新增修改的弹出窗
	 * 
	 * @param str
	 */
	openWindows : function(str) {
		$("#addUpdateCaptureContainer")[0].innerHTML = str;
		// 初始化添加/修改窗口
		$('#addUpdateManagerWindow').jqxWindow({
			width : 500,
			height : 300,
			resizable : true,
			autoOpen : false,
			isModal : true,
			modalOpacity : 0.3,
			cancelButton : $('#closebutton'),
			initContent : function() {

			}
		});
		$('#savebutton').on('click', function(event) {
			// var result = $('#zhistory_info_form').jqxValidator('validate');
			// if (result) {
			zhistory.prototype.saveORUpdate();
			// }
		});

		$('#addUpdateManagerWindowContent').find("#closebutton").on('click',
				function(event) {
					$(".jqx-validator-hint").remove();
					$('#addUpdateManagerWindow').jqxWindow('close');
				});
		$('#addUpdateManagerWindow').jqxWindow('open');
	},
	/**
	 * 保存记录
	 */
	saveORUpdate : function() {
		var data = $("#addUpdateManagerWindowContent").find("form")
				.serializeObject();
		var url = "./action/zhistory/savezhistory";
		var success = function(data) {
			if (confirm(data.message)) {
				window.location = "./action/zhistory/index";
			}
		};
		zhistory.prototype.ajax("POST", "json", data, url, success);
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
	},
	gameOn : function(id) {
		var url = "./action/zhistory/getzhistorys?start=0&end=10";
		zhistory.prototype.initGrid("zhistoryManagerGrid",
				zhistory.prototype.initSource("",zhistory.prototype.sourceDatafields, url),
				zhistory.prototype.gridColumns);
	}
};

$(function() {
	var users = new zhistory();
	try {
		users.initInputButtonAndText("#searchzhistorys,#addUpdateManagerWindowContent");
		users.validator("#zhistory_info_form");
		users.gameOn('');
		users.usersSearch();
	} catch (e) {
		alert("Exception name:" + e.name + "Exception msg:" + e.message);
	}
});
