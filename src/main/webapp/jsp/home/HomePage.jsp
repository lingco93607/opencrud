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
<title>渣渣主页</title>


<!-- Set render engine for 360 browser 指定webkit引擎渲染 -->
<meta name="renderer" content="webkit">   

<!-- No Baidu Siteapp  禁止百度转码-->
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<script src="../../js/angular.js"></script>
<script src="../../js/angular.min.js"></script>


<%@ include file = "../../jsp/common/commonHead.jsp" %>
<%@ include file = "../../jsp/common/commonNavigation.jsp" %>


<style type="text/css">
.main{
	
	margin: 0 auto;
	width: 900px;

}
.hotnews{
	float: left;
	width: 400px;
	height: 300px;
	margin-left: 10px;
	border-style:solid; 				
	border-width:3px;
	border-radius:10px;
	border-color: #4abefa;
	padding:10px 20px;
}

.hotnews ol li{
	margin-top: 10px;
}
.splicline{
	width: 800px;
	
	position: relative;
	float: left;	
}
.data11{
	position: relative;
	float: left;
	width: 850px;


}

.commentAvatarDiv{   
    width: 40px;
    height: 40px;
    padding:2px;
    background: #ececec;
    border-radius:32px;
    box-shadow: 0px 0px 1px rgba(0,0,0,0.4);
    -moz-border-radius: 32px;
    -webkit-border-radius: 32px;
}
.commentAvatarImage{   
    width:20px;
    height:20px;
    line-height: 0;      /* remove line-height */
    display: inline-block; /* circle wraps image */
    border-radius: 50%;  /* relative value */   
    -moz-border-radius: 50%;   
    -webkit-border-radius: 50%;   
    transition: linear 0.25s;
    float:left;
    margin: 10px;
}

</style>
 

</head>


<body>
<div class="main">
	<div data-am-widget="slider" class="am-slider am-slider-default col-md-6" data-am-slider='{}'>
	    <ul class="am-slides">
	        <li>
	            <img src="../../jsp/article/2222.jpg" alt="slide1">	           
	        </li>
	        <li>
	            <img src="../../jsp/article/2222.jpg" alt="slide2">	           
	        </li>	        
	    </ul>
  	</div>
  	<div class="hotnews">
  		<h3 style="text-align: center;">2017最热门的渣渣帖子</h3>
  		<ol>
  			<li>
  				【问拳头第四十四期】重获荣誉与天梯
  			</li>
  			<li>
  				【汇总】收集传奇卡片 解锁精美皮肤汇总贴
  			</li>
  			<li>
  				【汇总】收集传奇卡片 解锁精美皮肤汇总贴
  			</li>
  			<li>
  				【汇总】收集传奇卡片 解锁精美皮肤汇总贴
  			</li>
  			<li>
  				【汇总】收集传奇卡片 解锁精美皮肤汇总贴
  			</li>
  			<li>
  				【汇总】收集传奇卡片 解锁精美皮肤汇总贴
  			</li>
  			<li>
  				【汇总】收集传奇卡片 解锁精美皮肤汇总贴
  			</li>
  					 			
  		</ol>
  		
  	</div>

  	<div class="splicline">
  		<p style="display: inline-block;margin:10px 0 0 20px;">帖子</p>
		<p style="float: right;margin-top: 8px;">更多推荐</p>
  	</div>

  	<div class="data11">
	  	<!-- <ul class="list-group">
	       <div id="app"> 
	        <template v-for="user in users">
	        <li class="list-group-item">	            	            			            	
	            <img src="../../jsp/article/1111.png" class="commentAvatarImage">
	            <p style="float: left; margin-top: 10px;">{{user.name}}</p>
	            <p style="float: right;">{{user.date}}</p>
	            <br>	            
            	<p style="font-size: 15px;font-weight: bold;margin-top: 30px;">{{user.title}}</p>
                <p> {{user.content}}</p>                             	                	             
	            <button type="button" class="btn btn-link glyphicon glyphicon-eye-open" style="text-decoration:none;color:#808080;">{{user.readNum}}</button>
	            <button type="button" class="btn btn-link glyphicon glyphicon glyphicon-edit" style="text-decoration:none;color:#808080;">{{user.reviewsNum}}</button>   	               	                         	                   
	            <button type="button" class="btn btn-link glyphicon glyphicon-heart" style="text-decoration:none;color:#808080;">{{user.heart}}</button>
	            
	                      
	        </li>
	        </template>
	        </div>
	    </ul>   -->  
	        <div ng-app="myApp" ng-controller="siteCtrl"> 

				<ul class="list-group">
				  <li ng-repeat="x in articles" class="list-group-item">
				    <img src="../../jsp/article/1111.png" class="commentAvatarImage">
		            <p style="float: left; margin-top: 10px;">{{x.pUserNickName}}</p>
		            <p style="float: right;">{{x.publishDate}}</p>
		            <br>	            
	            	<p ng-click="onclickArticleDetail({{x.articleID}}, {{x.isVote}})" style="font-size: 15px;font-weight: bold;margin-top: 30px;">{{x.title}}</p>
	                <p ng-click="onclickArticleDetail({{x.articleID}}, {{x.isVote}})"> {{x.text}}</p>                             	                	             
		            <button type="button" class="btn btn-link glyphicon glyphicon-eye-open" style="text-decoration:none;color:#808080;">{{x.readNum}}</button>
		            <button type="button" class="btn btn-link glyphicon glyphicon glyphicon-edit" style="text-decoration:none;color:#808080;">{{x.reviewsNum}}</button>   	               	                         	                   
		            <button type="button" class="btn btn-link glyphicon glyphicon-heart" style="text-decoration:none;color:#808080;">{{x.voteNum}}</button>
				  </li>
				</ul>
			</div>
    </div>
	
	
</div>


<script type="text/javascript"> 
	/*new Vue({
        el: '#app',
        data: {
        users: [
        { name: '怀左同学',date:'2017-10-25',title:'在大学主要做什么',heart:'30',readNum:'100',reviewsNum:'30',content:'媒体的问题,随着文化的进步,人们知识体系的完善和提高,媒体的话语导向能力会逐渐下降的。'},
        { name: '怀左同学',date:'2017-10-30',title:'在大学主要做什么',heart:'50',readNum:'200',reviewsNum:'30',content:'我觉得一个有志在某一领域真正有所成就的人，并不会因为社会对哗众取宠却乏善可陈的同行报以更多「关注」，而感觉受到屈辱。'},
        { name: '怀左同学',date:'2017-10-30',title:'在大学主要做什么',heart:'50',readNum:'200',reviewsNum:'30',content:'我觉得一个有志在某一领域真正有所成就的人，并不会因为社会对哗众取宠却乏善可陈的同行报以更多「关注」，而感觉受到屈辱。'},
        { name: '怀左同学',date:'2017-10-30',title:'在大学主要做什么',heart:'50',readNum:'200',reviewsNum:'30',content:'我觉得一个有志在某一领域真正有所成就的人，并不会因为社会对哗众取宠却乏善可陈的同行报以更多「关注」，而感觉受到屈辱。'},
        { name: '怀左同学',date:'2017-10-25',title:'在大学主要做什么',heart:'200',readNum:'220',reviewsNum:'30',content:'中国达人秀以前也是各种低能儿，残疾在台上表演，然后就可以获得比别人高的成绩。评判标准不是付出多少努力，而是能力如何。' }
                ]
                }
                })*/

   /* $(document).ready(function(){
    $.ajax({
	  type: 'get',
	  url: '../../action/article/getHotAritcal?num=10',
	  success: function(data) {
	  	let data = data;
	  }
	})
	})*/

	var app = angular.module('myApp', []);
		app.controller('siteCtrl', function($scope, $http) {
		$scope.articles = [];	
		  $http.get("../../action/article/getArticles?start=0&end=5&useruuid=1")
		  .then(function (response) {
		  	/*$scope.names = response.data.data.articleInfo.article;*/

		  	if(200 == response.status) {
				
				var articleInfo = response.data.data.articleInfo;
				let length = articleInfo.length;
				for (let i=0; i<length; i++) {
					$scope.articles.push({
						pUserId: articleInfo[i].zUserId,
						pUserNickName: articleInfo[i].zNickname,
						pUserPicPath: articleInfo[i].zPicPath,
						isVote: articleInfo[i].vote,
						articleID: articleInfo[i].article.articleID,
						title: articleInfo[i].article.title,
						text: articleInfo[i].article.text,
						publishDate: formatDateTime(articleInfo[i].article.publishDate),
						readNum: articleInfo[i].article.readNum,
						voteNum: articleInfo[i].article.voteNum,
						reviewsNum: articleInfo[i].article.reviews.length,
						articleFirstPicPath: articleInfo[i].article.firstImgUrl,
					})
				}
				
		  	}
		});  
			
		$scope.onclickArticleDetail = function(articleID, isVote) {
			window.location.href='../../jsp/mphone/articleDetail.jsp?articleID=' + articleID + '&isVote=' + isVote;
			}
		});

		



	function formatDateTime(timeStamp) {
			var date = new Date(timeStamp);
		    var y = date.getFullYear();
		    var m = date.getMonth() + 1;
		    m = m < 10 ? ('0' + m) : m;
		    var d = date.getDate();
		    d = d < 10 ? ('0' + d) : d;
		    var h = date.getHours();
		    h = h < 10 ? ('0' + h) : h;
		    var minute = date.getMinutes();
		    var second = date.getSeconds();
		    minute = minute < 10 ? ('0' + minute) : minute;
		    second = second < 10 ? ('0' + second) : second;
		    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
		}	


</script> 


</body>
</html>	