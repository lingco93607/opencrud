<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html >  
<head>  
    <meta charset="UTF-8">  
    <title>个人资料</title>


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

        .user_pic{
        	width: 100px;
        	height: 100px;
        	padding: 6px;
        	float: left;  
        	background-color: #ffffff;
        	overflow: hidden;
        } 
        .user_pic img{
        	width:80%;
        	height: 80%;
        	border-radius: 100%;
        	background-color: #ffffff;
        	overflow: hidden;
        }

        .user_basic_info{
        	width: 85%;
        	height: 15%;
        	float: left;
        	
        }
        .user_name{
        	height: 45%;
        }
        .user_like{
        	height: 45%;
        }
		
		.hero_Attributes{
			width: 10%;
			height: 50%;
			float: left;
		}
ul li{
	width: 15%;
	text-align: center;
}
.content_style{
	padding: 20px 25px;
}


    </style>  
    <%@ include file = "../../jsp/common/commonHead.jsp" %>
</head>  
<body> 


   
    <div class="my_container">  
        <div class="header">头部</div>  
        <div class="content_left"></div>  
        <div class="content_body">
			<div class="user_pic">
				<img src="../../images/home/eg_tulip.png" alt="个人头像"  />
			</div>
			<div class="user_basic_info">
				<div class="user_name">
					<em ><b> 小白兔</b></em>
					<em ><font style="size:'9';color: red" style="width: 40%"> lv1 </font>  </em>
					<em text-align:center style="width: 50%">积分: </em>
					<em>2000</em>
				</div >
				<div class="user_like">
					<em>关注</em>
					<em>(0)  </em>
					<hr style="width:4px;height:100%; display:inline"></hr>
					<em>粉丝</em>
					<em>(0)  </em>
					<hr style="width:4px;height:100%; display:inline"></hr>
					<em>喜欢</em>
					<em>(0)</em>
				</div>
        	</div>

			<!--五行 动态 回答 提问 文章-->
			<ul id="myTab" class="nav nav-tabs">
				<li class="active"><a href="#1" data-toggle="tab">五行属性</a></li>
				<li ><a href="#2" data-toggle="tab">动态</a></li>
				<li><a href="#3" data-toggle="tab">回答</a></li>
				<li><a href="#4" data-toggle="tab">提问</a></li>
				<li><a href="#5" data-toggle="tab">文章</a></li>
				<li><a href="#6" data-toggle="tab">喜欢的文章</a></li>
			</ul>
			<div id="myTabContent" class="tab-content content_style">
			   <div class="tab-pane fade in active" id="1">
					<div >
						<div style="width: 10%; height: 100px; float: left;">
							
							<div style="text-align: center;" >
							<a>英雄属性: </a>
							    
							</div>
						</div>
						<div style="width: 90%; height: 10%; float: left; ">
							<div style="width: 10%; float: left; text-align: center;">
								<span style="text-align: center;">体力: </span>
							</div>
							
							<div class="progress" style="width: 40%;  float: left;">
							    <div class="progress-bar" role="progressbar" aria-valuenow="60" 
							        aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
							        <span class="sr-only">40% 完成</span>
							    </div>
							</div>
						</div>
						<div style="width: 90%; height: 10%; float: left; ">
							<div style="width: 10%; float: left; text-align: center;">
								<span style="text-align: center;">智力: </span>
							</div>
							
							<div class="progress" style="width: 40%;  float: left;">
							    <div class="progress-bar" role="progressbar" aria-valuenow="60" 
							        aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
							        <span class="sr-only">40% 完成</span>
							    </div>
							</div>
						</div>
						<div style="width: 90%; height: 10%; float: left; ">
							<div style="width: 10%; float: left; text-align: center;">
								<span style="text-align: center;">魅力: </span>
							</div>
							
							<div class="progress" style="width: 40%;  float: left;">
							    <div class="progress-bar" role="progressbar" aria-valuenow="60" 
							        aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
							        <span class="sr-only">40% 完成</span>
							    </div>
							</div>
						</div>
					</div>
					<hr style="width:100%; height:2px;"></hr>
					<div>
						<div style="width: 10%; height: 100px; float: left;">
							<div style="text-align: center;" >
								<a>五行属性: </a>   
							</div>
						</div>
						<div style="width: 90%; height: 10%; float: left; ">
							<div style="width: 10%; float: left; text-align: center;">
								<span style="text-align: center;">体力: </span>
							</div>
							
							<div class="progress" style="width: 40%;  float: left;">
							    <div class="progress-bar" role="progressbar" aria-valuenow="60" 
							        aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
							        <span class="sr-only">40% 完成</span>
							    </div>
							</div>
						</div>
					</div>
			   </div>
			   <div class="tab-pane fade content_style" id="2">
			    	<h4><b>我的状态</b></h4>
					<hr style="width:100%; height:2px;"></hr>
					<span style="width: %50; text-align: left; "> <a href="">关注了问题</a> </span>
					<span style="width: %50; text-align: right; "><a href="">20小时前</a> </span>
					<br>
					<span>呜呜呜呜无无无无无无无无无无无无无无无无无无无</span>
					<hr style="width:100%; height:2px;"></hr>
			
					<span style="width: %50; text-align: left; "> <a href="">回答了问题</a> </span>
					<span style="width: %50; text-align: right; "><a href="">20小时前</a> </span>
					<br>
					<span>wwwwwwww ?</span>
					<div >
						<img src="../../images/home/eg_tulip.jpg" alt="个人头像" style="height: 20px; width: 20px; border-radius:50%;" />
					</div>
					<p><a href="">阅读全文</a></p>
					<hr style="width:100%; height:2px; "></hr>
					<hr style="width:100%; height:2px;"></hr>
					<hr style="width:100%; height:2px;"></hr>
					<hr style="width:100%; height:2px;"></hr>
			   </div>
			   <div class="tab-pane fade content_style" id="3">
					<h4><b>我的回答</b></h4>
					<hr style="width:100%; height:2px;"></hr>
					<br>
					<span style="width: %50; text-align: left; "> <a href="">回答了问题</a> </span>
					<span style="width: %50; text-align: right; "><a href="">20小时前</a> </span>
					<br>
					<span>wwwwwwww ?</span>
					<div >
						<img src="../../images/home/eg_tulip.jpg" alt="个人头像" style="height: 20px; width: 20px; border-radius:50%;" />
					</div>
					<p><a href="">阅读全文</a></p>
					<hr style="width:100%; height:2px; "></hr>
					<hr style="width:100%; height:2px;"></hr>
					<hr style="width:100%; height:2px;"></hr>
					<hr style="width:100%; height:2px;"></hr>
			   </div>
			   <div class="tab-pane fade content_style" id="4">
		      			<h4><b>我的提问</b></h4>
						<hr style="width:100%; height:2px;"></hr>
						<br>
						<span style="width: %50; text-align: left; "> <a href="">回答了问题</a> </span>
						<span style="width: %50; text-align: right; "><a href="">20小时前</a> </span>
						<br>
						<span>wwwwwwww ?</span>
						<div >
							<img src="../../images/home/eg_tulip.jpg" alt="个人头像" style="height: 20px; width: 20px; border-radius:50%;" />
						</div>
						<p><a href="">阅读全文</a></p>
						<hr style="width:100%; height:2px; "></hr>
						<hr style="width:100%; height:2px;"></hr>
						<hr style="width:100%; height:2px;"></hr>
						<hr style="width:100%; height:2px;"></hr>
			   </div>
			   <div class="tab-pane fade content_style" id="5">
					<h4><b>我的文章</b></h4>
					<hr style="width:100%; height:2px;"></hr>
			   </div>

			   <div class="tab-pane fade content_style" id="6">
					<div >
						<img src="../../images/home/eg_tulip.jpg" alt="个人头像" style="height: 20px; width: 20px; border-radius:50%;" />
						<span>怀左同学</span>
						<span>3小时前</span>
						<h4><b>在大学我们应该做些什么</b></h4>
						<p>前段时间天气晴朗又闷热，大家怀疑是不是秋老虎来个回马枪又秋老虎一回。可天气说变就变，一夜之间就变得阴阴沉沉，风呼呼地刮，吹得你感到透心的凉。 忽然想起阿文在学校里住校，垫棉还...
						</p>
						<hr style="width:100%; height:2px;"></hr>
					</div>
					<div >
						<img src="../../images/home/eg_tulip.jpg" alt="个人头像" style="height: 20px; width: 20px; border-radius:50%;" />
						<span>怀左同学</span>
						<span>3小时前</span>
						<h4><b>在大学我们应该做些什么</b></h4>
						<p>前段时间天气晴朗又闷热，大家怀疑是不是秋老虎来个回马枪又秋老虎一回。可天气说变就变，一夜之间就变得阴阴沉沉，风呼呼地刮，吹得你感到透心的凉。 忽然想起阿文在学校里住校，垫棉还...
						</p>
						<hr style="width:100%; height:2px;"></hr>
					</div>
					<div >
						<img src="../../images/home/eg_tulip.jpg" alt="个人头像" style="height: 20px; width: 20px; border-radius:50%;" />
						<span>怀左同学</span>
						<span>3小时前</span>
						<h4><b>在大学我们应该做些什么</b></h4>
						<p>前段时间天气晴朗又闷热，大家怀疑是不是秋老虎来个回马枪又秋老虎一回。可天气说变就变，一夜之间就变得阴阴沉沉，风呼呼地刮，吹得你感到透心的凉。 忽然想起阿文在学校里住校，垫棉还...
						</p>
						<hr style="width:100%; height:2px;"></hr>
					</div>

			   </div>
			</div>
		</div>
		<div class="content_right"></div> 
		<div class="footer">底部</div>
	</div>

   





<script>
   $(function () {
      $('#myTab li:eq(0) a').tab('show');
   });
</script>
</body>  