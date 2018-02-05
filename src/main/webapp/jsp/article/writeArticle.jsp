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
<title>发帖</title>


<!-- Set render engine for 360 browser 指定webkit引擎渲染 -->
<meta name="renderer" content="webkit">   

<!-- No Baidu Siteapp  禁止百度转码-->
<meta http-equiv="Cache-Control" content="no-siteapp"/>  

<%@ include file = "../../jsp/common/commonHead.jsp" %>
<%@ include file = "../../jsp/common/commonNavigation.jsp" %>

<!-- css style -->
<link rel="stylesheet" type="text/css" href="../../js/ionic-1.3.2/css/ionic.min.css">
<!-- ionic.js 1.3.2 -->
<script type="text/javascript" src="../../js/ionic-1.3.2/js/ionic.bundle.min.js"></script>
<!-- qeditor 文本插件 -->
<script type="text/javascript" src="../../js/qeditor.js"></script>

<link href="../../js/bootstrap-3.3.5/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="../../css/jquery.qeditor.css" type="text/css">

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
        .image{
          margin: 10px auto;
          margin-top: -30px;
          width: 400px;
          height: 200px;
          background-color: #C0C0C0;          
        }

        .item {
          border-style:none;
        }
        .file {
          position: relative;
          display: inline-block;
          background: #D0EEFF;
          border: 1px solid #99D3F5;
          border-radius: 4px;
          padding: 4px 12px;
          overflow: hidden;
          color: #1E88C7;
          text-decoration: none;
          text-indent: 0;
          line-height: 20px;
          margin-left: 15px;
          margin-top: 5px;

        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }

</style>
 

</head>



<body>
  <div style="margin: 0 auto;width: 48%;">
    <div class="navigation">
      <button class="btn btn-default btn-sm" onClick="javascript:history.back(-1);">返回</button>
      <span><h3>发帖</h3></span>       
      <button type="submit" id="submit" class="btn btn-success btn-sm" >发表</button> 
    </div>
    
    <form name="form">
    <p class="file">上传图像
      <input type="file" name="myfiles" id="myfiles">
    </p>
    </form>
    <button style="margin-left: 15px;" class="btn btn-default btn-sm" onClick="clear()">清除图像</button>

    <div id="databox" class="image">
    </div>
    



    <div class="form-horizontal" type="POST" action="" ng-controller="writeAritcle">
        <ion-item style="padding-right: 0px;">
          <div class="control-group">
            <div class="input-group" style="padding-right: 10px; padding-bottom: 10px;">
              <input type="text" id="title" class="form-control" style="width: 600px;" placeholder="请输入标题">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="post_body" ng-click="showAlert()"></label>
            <div class="controls">
              <textarea id="post_body" name="body" class="textarea" placeholder="请输入正文"></textarea>
            </div>
          </div>
        </ion-item>
    </div>
  </div> 



<script type="text/javascript"> 
function initiate(){
   databox=document.getElementById('databox');
   var myfiles=document.getElementById('myfiles');
   myfiles.addEventListener('change',process,false);
 }
 function process(e){
   var files=e.target.files;
   databox.innerHTML='';
   var file=files[0];
   if(!file.type.match(/image.*/i)){
     alert('请插入一个图片');     
   }
   else{  
     var reader=new FileReader();
     reader.onload=show;
     reader.readAsDataURL(file);
   }
 }
 function show(e){
   var result=e.target.result;
   databox.innerHTML+='<img src="'+result+'" width="100%" height="100%">';
 }
 window.addEventListener('load',initiate,false);


 function clear()
    {
      document.getElementById('databox').innerHTML='none';
    }


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
    
    $("#submit").click(function(){
      alert($("#post_body").val());

      if($("#title").val() != "" || $("#post_body").val() != "") {

      } 
    });

    angular.module('ionicApp', ['ionic'])
    .controller('writeAritcle',function($scope, $ionicPopup, $timeout) {

       //  alert（警告） 对话框
           $scope.showAlert = function() {
             var alertPopup = $ionicPopup.alert({
               title: 'Don\'t eat that!',
               template: 'It might taste good'
             });
             alertPopup.then(function(res) {
               console.log('Thank you for not eating my delicious ice cream cone');
             });
           };
        });   



</script> 


</body>
</html>