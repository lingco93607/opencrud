<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <meta name="description" content="">
    <meta name="keywords" content="">
   <!--  初始化移动端浏览显示，网页适应设备宽度，禁止窗口缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">   
    <title>渣渣论坛</title>


    <!-- Set render engine for 360 browser 指定webkit引擎渲染 -->
    <meta name="renderer" content="webkit">   

    <!-- No Baidu Siteapp  禁止百度转码-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/> 

    <%@ include file = "../../jsp/common/commonHead.jsp" %>

    <!-- css style -->
    <link rel="stylesheet" type="text/css" href="../../js/ionic-1.3.2/css/ionic.min.css">
    <!-- ionic.js 1.3.2 -->
    <script type="text/javascript" src="../../js/ionic-1.3.2/js/ionic.bundle.min.js"></script>
    <!-- qeditor 文本插件 -->
    <script type="text/javascript" src="../../js/qeditor.js"></script>

    <link href="../../js/bootstrap-3.3.5/css/font-awesome.min.css" rel="stylesheet">
  	<link rel="stylesheet" href="../../css/jquery.qeditor.css" type="text/css">

    
</head>
    <style type="text/css">
    	*html {
    		margin: 0px;
    		border: 0px;
    	}

    	.textarea {
		    background-color: #ffffff;
		    border: 1px solid #cccccc;
		    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
		    -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
		    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
		    -webkit-transition: border linear .2s, box-shadow linear .2s;
		    -moz-transition: border linear .2s, box-shadow linear .2s;
		    -o-transition: border linear .2s, box-shadow linear .2s;
		    transition: border linear .2s, box-shadow linear .2s;
		    padding: 4px 6px;
		    font-size: 14px;
		    line-height: 20px;
		    color: #555555;
		    -webkit-border-radius: 4px;
		    -moz-border-radius: 4px;
		    border-radius: 4px;
		    vertical-align: middle;
		    outline: none;
		    height: 400px;
		}
		.navigation {
        	min-height: 50px;
        	display: flex;
        	justify-content: space-around;
        }
        .navigation h2 {
        	margin-top: 15px;
        	
        }
        .navigation button {
        	margin-top: 10px;
        	max-height: 30px;

        }
    </style>

    <body ng-app="ionicApp">
    
    <div class="navigation">
    	<!-- <a class="left" >&lsaquo;</a> -->
    	<button class="btn btn-default btn-sm" onClick="javascript:history.back(-1);">返回</button>
    	<span><h3>发文章</h3></span>
    	
    	<button type="submit" id="submit" class="btn btn-success btn-sm" >发表</button> 
    </div>

	<!-- 首行信息 -->
<!-- 	<div class="col-lg-8 col-lg-offset-2 firstRow" style="padding: 0px;">
	</div>
 -->
	<div class="form-horizontal" ng-controller="writeAritcle">
      <ion-item style="padding-right: 0px;">
      <div class="control-group">
        <!-- <label class="control-label" for="inputEmail">标题</label>
        <div class="controls">
          <input type="text" id="title" placeholder="输入标题">
        </div> -->
        <div class="input-group" style="padding-right: 20px; padding-bottom: 10px;">
			     <span class="input-group-addon">标题</span>
			     <input type="text" id="title" class="form-control" placeholder="请输入标题">
		    </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="post_body">内容</label>
        <div class="controls">
           <textarea id="post_body" name="body" class="textarea" placeholder="输入内容"></textarea>
        </div>
      </div>
      <!-- <div class="control-group">
        <div class="controls">
          <button type="submit" id="submit" class="btn btn-success">Submit</button>  
          <button class="btn" onclick="return changeInsertImageIconWithCustomEvent();">Change Insert Image</button>
        </div>
      </div> -->
      </ion-item>
  </div>
</body>
    <script type="text/javascript">
    $("#post_body").qeditor({});
    
    // Custom a toolbar icon
    var toolbar = $("#post_body").parent().find(".qeditor_toolbar");
    var link = $("<a href='#'><span class='icon-smile' title='smile'></span></a>");
    link.click(function(){
      alert("Put you custom toolbar event in here.");
      return false;
    });
    toolbar.append(link);
    
    // Custom Insert Image icon event
    function changeInsertImageIconWithCustomEvent() {
      var link = toolbar.find("a.qe-image");
      link.attr("data-action","");
      link.click(function(e){
        alert("New insert image event");
        return false;
      });
      alert("Image icon event has changed, you can click it to test");
      return false;
    }
    
    

    angular.module('ionicApp', ['ionic'])
    .controller('writeAritcle',function($scope, $http, $ionicPopup, $timeout) {

    	$("#submit").click(function(){
    	  var title = $("#title").val();
    	  var text = $("#post_body").val();
	      if( title != "" || text != "") {
	      	$http({
				method: 'POST',
				url: '../../action/article/writeArticle?userUid=1&title='+ title +'&text=' + text
			}).then(function successCallback(response) {
				if(200 == response.status) {
					//0-发送成功；1-魅力值已大上限；2-体力值已扣完；3-其他异常
					let status = response.data;
					if (status == 0) {
						window.location.href='../../jsp/mphone/forumPhone.jsp';
					} else if (status == 1) {
						$scope.ionicPopupAlert("提示", "温馨提示,你的魅力值今日已经达上限不再增长了。但可以继续发帖");
						window.location.href='../../jsp/mphone/forumPhone.jsp';
					} else if (status == 2) {
						//  alert（警告） 对话框
			         	$scope.ionicPopupAlert("提示", "温馨提示,你体力值不够了,您稍后再发吧！");
					} else {
						$scope.ionicPopupAlert("错误", "服务器异常！稍后再试！");
					}
				}	
			}, function errorCallback(response) {
				// 请求失败执行代码
			});
	      } else {
	      	$scope.ionicPopupAlert("提醒", "标题/内容不能为空！");
	      }
	    });


    	//弹出框提示框
		$scope.ionicPopupAlert = function(title, text) {
			var alertPopup = $ionicPopup.alert({
	       		title: title,
	       		template: text
	     	});
	     	alertPopup.then(function(res) {
	       		console.log('This is a popup');
	     	});
		}
    });




    </script>


</body>
</html>