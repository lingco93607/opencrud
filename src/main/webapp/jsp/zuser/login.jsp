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
    <title>登录</title>


    <!-- Set render engine for 360 browser 指定webkit引擎渲染 -->
    <meta name="renderer" content="webkit">   

    <!-- No Baidu Siteapp  禁止百度转码-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>  


    <style type="text/css">
       
        .commentAvatarImage{   
             width:40px;
             height:40px;
             line-height: 0;      /* remove line-height */
             display: inline-block; /* circle wraps image */
             border-radius: 50%;  /* relative value */   
             -moz-border-radius: 50%;   
             -webkit-border-radius: 50%;   
             transition: linear 0.25s;
             float:left;
             margin: 10px;
         }

        .box {
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: flex-start;
            align-content: flex-start;

        }

        #rcorners {
            border-radius: 10px;
            background: #8AC007;
            width: 60px;
            height: 25px;    
}


    </style>
 

    </head>

    <%@ include file = "../../jsp/common/commonHead.jsp" %>
    <%@ include file = "../../jsp/common/commonNavigation.jsp" %>

<body>

<div class="col-md-4 col-sm-offset-4 text-center panel panel-default" style="margin-top: 100px;">  
    <div class="panel-body" style="text-align: center;width: 600px;height: 450px;">
        <div id="setin" style="">

        <h1>
            渣渣
        </h1>
        <p>
            与世界分享你的知识、经验和见解
        </p>
         <ul class="pager">
            <li><a href="#" onclick="setup()">注册</a></li>
            <li><a href="#" onclick="setin()">登录</a></li>
        </ul>
         
        <input type="text" class="form-control" style="width: 50%;position: relative;left: 25%;" placeholder="手机号" autofocus>    
        <input type="text" class="form-control" style="width: 50%;position: relative;left: 25%;margin-top: 10px;" placeholder="密码">
        <button type="button" class="btn btn-primary" style="margin-top: 10px;width: 50%;height: 35px;" >登录</button>
        
        <div>
            
            <button type="button" class="btn btn-link" style="position: absolute;left: 25%; text-decoration:none;color:#808080;">手机验证码登录</button>
            <button type="button" class="btn btn-link" style="position: absolute;right: 26%; text-decoration:none;color:#808080;">无法登录?</button>
            <br>
            <button type="button" class="btn btn-link" style="position: absolute;left: 25%; text-decoration:none;color:#808080;">二维码登录</button>
            <button type="button" class="btn btn-link" style="text-decoration:none;color:#808080;">社交账号登录</button>

        </div>
         <button type="button" class="btn btn-default" style="margin-top: 10px;width: 50%;height: 35px;" >下载渣渣</button>

         </div>



         <div id="setup" style="display: none;">

        <h1>
            渣渣
        </h1>
        <p>
            与世界分享你的知识、经验和见解
        </p>
         <ul class="pager">
            <li><a href="#" onclick="setup()">注册</a></li>
            <li><a href="#" onclick="setin()">登录</a></li>
        </ul>
         
        <input type="text" class="form-control" style="width: 50%;position: relative;left: 25%;" placeholder="姓名" autofocus> 
        <input type="text" class="form-control" style="width: 50%;position: relative;left: 25%;margin-top: 10px;" placeholder="手机号">    
        <input type="text" class="form-control" style="width: 50%;position: relative;left: 25%;margin-top: 10px;" placeholder="密码">
        <button type="button" class="btn btn-primary" style="margin-top: 10px;width: 50%;height: 35px;" >注册渣渣</button>
        
        <p>
            点击「注册」按钮，即代表你同意《渣渣协议》
        </p>
         <button type="button" class="btn btn-default" style="margin-top: 10px;width: 50%;height: 35px;" >下载渣渣</button>

        </div>





    



    </div>
</div>



 <script type="text/javascript">
        function setup()
            {
                document.getElementById("setup").style.display="inherit";
                document.getElementById("setin").style.display="none";

              }
               
            
        function setin()
            {
              document.getElementById("setin").style.display="inherit";
              document.getElementById("setup").style.display="none";


              }
               
        

    </script> 



    </body>
</html>