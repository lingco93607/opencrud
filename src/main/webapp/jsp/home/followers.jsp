<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <title>粉丝</title>
    <link rel='stylesheet prefetch' href='css/reset.css'>
	<link rel="stylesheet" type="text/css" href="css/default.css">
	<link rel="stylesheet" type="text/css" href="css/styles.css">  
	<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.0.0/jquery.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>  

    	html,body{
                height:100%;
                width: 100%;
                margin: 0;
                padding: 0;
            }

            div{
            	margin: 0;
                padding: 0;
            }
   
     
        .my_container{  
                width: 100%;
                height: 100%;
                margin: 0;
                padding: 0; 
                background-color: #000000;  
            }  
        .box{
            width: 100%;
            height: 15%;
            float: left;
            background-color: #ffff8f;
            padding: 15px 30px;

        }
        .box img{
            width: 100px;
            height: 100px;
            border-radius: 100%;
            padding: 10px;
            float: left;
            overflow: hidden;
        }
        .vertical_line{
            width:1px;
            border:1px solid red;
            float:left;
            height:100%;
        }
        .follow_column{
            padding: 5px 15px;

            text-align: left;
            float: left;
        }
        .header{  
            width: 100%;  
            height: 10%;  
            background-color: #0000FF;  
        }  
        .content_left{  
            height: 80%;  
            width: 25%;  
            background-color: #0E2D5F;  
            float: left;  
        }  
        .content_body{  
            width: 50%;  
            height: 80%;  
            float: left;  
            background-color: #ffffff;  
        }  
        .content_right{  
            width: 25%;  
            height: 80%;  
            float: left;  
            background-color: #005256;  
        }  
        .footer{  
            width: 100%;  
            height: 10%;  
            background-color: #575765;  
            clear: both;/**清除浮动*/  
        } 

  

    </style>  
</head>  
<body> 
    <div class="my_container">  
        <div class="header">头部</div>  
        <div class="content_left"></div>  
        <div class="content_body">
            <div class="box">
            
                <img src="../../images/home/eg_tulip.png" alt="个人头像"  />
                <div class="vertical_line"><!--这个div模拟一条红色的垂直分割线--></div>
                <div class="follow_column">
                    <p><a href="">女神时尚穿搭</a></p>
                    <em><a href="">关注</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">粉丝</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">文章</a></em>
                    <em><a href="">(0)</a></em>
                    <div style="height: 100%; width: 20%; float: right; display: inline;">
                        <input type="button" name="follow" value="+关注">
                    </div>
                    
                </div>
            </div>
            <div class="box">
            
                <img src="../../images/home/eg_tulip.png" alt="个人头像"  />
                <div class="vertical_line"><!--这个div模拟一条红色的垂直分割线--></div>
                <div class="follow_column">
                    <p><a href="">女神时尚穿搭</a></p>
                    <em><a href="">关注</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">粉丝</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">文章</a></em>
                    <em><a href="">(0)</a></em>
                    <div style="height: 100%; width: 20%; float: right; display: inline;">
                        <input type="button" name="follow" value="+关注">
                    </div>
                </div>
            </div>
            <div class="box">
            
                <img src="../../images/home/eg_tulip.png" alt="个人头像"  />
                <div class="vertical_line"><!--这个div模拟一条红色的垂直分割线--></div>
                <div class="follow_column">
                    <p><a href="">女神时尚穿搭</a></p>
                    <em><a href="">关注</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">粉丝</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">文章</a></em>
                    <em><a href="">(0)</a></em>
                    <div style="height: 100%; width: 20%; float: right; display: inline;">
                        <input type="button" name="follow" value="+关注">
                    </div>
                </div>
            </div>
            <div class="box">
                <img src="../../images/home/eg_tulip.png" alt="个人头像"  />
                <div class="vertical_line"><!--这个div模拟一条红色的垂直分割线--></div>
                <div class="follow_column">
                    <p><a href="">女神时尚穿搭</a></p>
                    <em><a href="">关注</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">粉丝</a></em>
                    <em><a href="">(0)</a></em>
                    <hr style="width:4px;height:100%; display:inline"></hr>
                    <em><a href="">文章</a></em>
                    <em><a href="">(0)</a></em>
                    <div style="height: 100%; width: 20%; float: right; display: inline;">
                        <input type="button" name="follow" value="+关注">
                    </div>
                </div>
            </div>
		</div>
		<div class="content_right"></div> 
		<div class="footer">底部</div>
	</div>
</body>  