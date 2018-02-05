<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html >
<head>
    <meta charset="utf-8">
    <!-- 初始化移动端浏览显示，网页适应设备宽度，禁止窗口缩放 -->
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>渣渣论坛</title>
	<!-- css style -->
	<link rel="stylesheet" type="text/css" href="../../js/ionic-1.3.2/css/ionic.min.css">
    <!-- ionic.js 1.3.2 -->
	<script type="text/javascript" src="../../js/ionic-1.3.2/js/ionic.bundle.min.js"></script>
	<!-- jquery.js 3.0.0 -->
    <script type="text/javascript" src="../../js/jquery-3.0.0.min.js"></script>
    <!-- jquery.ellipsis 隐藏 -->
    <script type="text/javascript" src="../../js/jquery.ellipsis.js"></script> 
    <!-- jquery.ellipsis 隐藏 -->
    <script type="text/javascript" src="../../js/jquery.ellipsis.unobtrusive.js"></script>
    
	
	<style type="text/css">
		.slider {
	      height: 200px;
	    }
	   .slider-slide {
	      color: #000; 
	      background-color: #fff; text-align: center; 
	      font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif; font-weight: 300; }
	    .blue {
	      background-color: #AAA;
	    }
	    .yellow {
	      background-color: yellow;
	    }
	    .pink {
	      background-color: pink;
	    }
		.box{ 
	      height:100%;
	      display: flex;
	      justify-content: center;
	      align-items: center;
	    } 
	    .box h1{
	      position:relative; top:50%; transform:translateY(-50%); 
    	}
    	.full-image {
    		display: flex;
    		justify-content: center;
    		align-items: center;
    	}
    	.articleDate {
    		font-size: 12px;
    		color: #AAA;
    	}

    	#returnOver {
		   color: #aaa;
		   display: flex;
		   justify-content: space-around;
		   display: none;
		}

		.line-limit-length {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap; 
		}
	</style>
</head>
<body ng-app="ionicApp">
	<ion-content>
	
	<div animation="slide-left-right-ios7" ng-controller="SlideController">
		<ion-slide-box active-slide="myActiveSlide">
	    	<ion-slide>
	      		<div class="box blue"><h3>Slide 1</h3></div>
	    	</ion-slide>
	    	<ion-slide>
	      		<div class="box yellow"><h3>Slide 2</h3></div>
	    	</ion-slide>
	   		<ion-slide>
	      		<div class="box pink"><h3>Slide 3</h3></div>
	    	</ion-slide>
	  	</ion-slide-box>
	</div>

  	
  	<div ng-controller="MyList" ng-init="doRefreshMakeRequest()">
  		<div>
    	<!-- <button class="button button-balanced" style="width: 95%; margin: 10px 10px;" onclick="window.location.href='../../jsp/mphone/writeArticle.jsp';">发帖</button> -->
    	<button class="button button-balanced" style="width: 95%; margin: 10px 10px;" ng-click="writeArticleRequest()">发帖</button>
    	</div>
  		<ion-list can-swipe="true">
  			<ion-item>
	  		<div ng-repeat="article in articles" item="item"  class="item-remove-animate"><!--  href="../../jsp/mphone/articleDetail.jsp" -->
		        <div class="item item-avatar" style="padding-bottom: 0px; border-bottom: none;">
		          <img src="http://www.runoob.com/try/demo_source/mcfly.jpg">
		          <h2>{{article.pUserNickName}}</h2>
		          <span class="articleDate">{{article.publishDate}}</span>
		        </div>
		        <div class="item item-body" style="padding-top: 0px; border-top: none;">
		          <center>
		          <!-- <img class="full-image" style="height:10%; width:90%" src="../../images/common/slide1.png"> -->
		          
		          </center>
		          <b ng-click="onclickArticleDetail({{article.articleID}}, {{article.isVote}})">{{article.title}}</b>
		          <!-- <p >
		            {{article.text}}
		          </p> -->
		    <!--       <p class="line-limit-length" data-ellipsis="true" data-ellipsis-max-width="200" data-ellipsis-max-line="2">中文支持不支持(adf)asd,中文支持不支持(adf)asasdfasdf中文支持不支持(adf)asd,中文支持不支持(adf)as...中文支持不支持(adf)asd,中文支持不支持(adf)as...中文支持不支持(adf)asd,中文支持不支持(adf)as...中文支持不支持(adf)asd,中文支持不支持(adf)as...中文支持不支持(adf)asd,中文支持不支持(adf)as...中文支持不支持(adf)asd,中文支持不支持(adf)as...中文支持不支持(adf)asd,中文支持不支持(adf)as...</p>
 -->
		          <p class="line-limit-length" data-ellipsis="true" data-ellipsis-max-width="500" data-ellipsis-max-line="2" ng-click="onclickArticleDetail({{article.articleID}}, {{article.isVote}})" ng-bind-html="article.text" ></p>
		          <p>
		             <i class="icon ion-eye"></i>&nbsp;&nbsp; {{article.readNum}} &nbsp;&nbsp;
		             <i class="icon ion-thumbsup"></i>&nbsp;&nbsp; {{article.voteNum}} &nbsp;&nbsp;
		             <i class="icon ion-chatbox"></i>&nbsp;&nbsp;{{article.reviewsNum}}
		          </p>
		        </div>
		    </div>
		    <div id="returnOver" style="font-size: 18px;">
		    	<center>
		    		我是有底线的
		    	</center>
		    </div>
		    <ion-infinite-scroll  ng-if="isLoadMore" distance="5%" on-infinite="doRefreshMakeRequest()" immediate-check="false"></ion-infinite-scroll>
	  		</ion-item>
		</ion-list>

  	</div>
	</ion-content>	
    
    <script type="text/javascript">
    	var start = 0;

    	$(".article_text").ellipsis({maxWidth:500,maxLine:2});

		angular.module('ionicApp', ['ionic'])

		.controller('SlideController', function($scope) {
			$scope.myActiveSlide = 1;
		})

		.controller('MyList', function($scope, $http, $ionicPopup) {
			$scope.articles = [];
			$scope.isLoadMore = true;

			
			//$scope.doRefreshMakeRequest()
			// $http({
			// 	method: 'GET',
			// 	url: '../../action/article/getArticles?start=0&end=5&useruuid=1'
			// }).then(function successCallback(response) {
			// 		if(200 == response.status) {
			// 			var articleInfo = response.data.data.articleInfo;
			// 			let length = articleInfo.length;
			// 			for (let i=0; i<length; i++) {
			// 				$scope.articles.push({
			// 					pUserId: articleInfo[i].zUserId,
			// 					pUserNickName: articleInfo[i].zNickname,
			// 					pUserPicPath: articleInfo[i].zPicPath,
			// 					articleID: articleInfo[i].article.articleID,
			// 					title: articleInfo[i].article.title,
			// 					text: articleInfo[i].article.text,
			// 					publishDate: articleInfo[i].article.publishDate,
			// 					readNum: articleInfo[i].article.readNum,
			// 					voteNum: articleInfo[i].article.voteNum,
			// 					reviewsNum: articleInfo[i].article.reviews.length,
			// 					articleFirstPicPath: articleInfo[i].article.firstImgUrl,
			// 				})
			// 			}
						

			// 		}
					
			// 	}, function errorCallback(response) {
			// 		// 请求失败执行代码
			// });

			//页面跳转
			$scope.onclickArticleDetail = function(articleID, isVote) {
				window.location.href='../../jsp/mphone/articleDetail.jsp?articleID=' + articleID + '&isVote=' + isVote;
			}

			$scope.writeArticleRequest = function() {
				$http({
					method: 'GET',
					url: '../../action/article/isWriteArticle?userUid=1'
				}).then(function successCallback(response) {
					if(200 == response.status) {
						//status 0-可以发帖，1-体力不足，2-其他异常
						let status = response.data;
						if (status == 0) {
							window.location.href='../../jsp/mphone/writeArticle.jsp';
						} else if (status == 1) {
							$scope.ionicPopupAlert("提示", "温馨提示,你体力值不够了,您稍后再发吧！");
							window.location.href='../../jsp/mphone/writeArticle.jsp';
						} else {
							//  alert（警告） 对话框
				         	$scope.ionicPopupAlert("错误", "服务器异常！稍后再试！");
						}
					}	
				}, function errorCallback(response) {
					// 请求失败执行代码
				});
			}
			

			$scope.doRefreshMakeRequest = function() {

	        	$http({
					method: 'GET',
					url: '../../action/article/getArticles?start='+ start +'&end=5&useruuid=1'
					//url: '../../action/article/getArticles?start=20&end=5&useruuid=1'
				}).then(function successCallback(response) {
						if(200 == response.status) {
							if(!isEmptyObject(response.data.data)) {
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
							} else {
								$("#returnOver").show();
								$scope.isLoadMore = false;
							}	
						}
						
				}, function errorCallback(response) {
					// 请求失败执行代码
				}).finally(function() {
					start += 5;
					/*
						使用了一个定时器。如果不使用这个定时器，虽然数据请求回来了，
						但是内容还没有充满整个屏幕，这时已经广播$broadcast加载动作已经结束，
						它就会再自动执行一次或者多次加载，造成数据错误 
					*/
					$timeout(() => {
			          $scope.$broadcast("scroll.infiniteScrollComplete");
			        });
                    //$scope.$broadcast('scroll.infiniteScrollComplete');
            	});
			};
			/**immediate-check属性判断是否在页面载入后立即检查滚动边界 
			默认为true(这将导致页面载入后立即调用刷新)，如果不设置为false时，
			可能我们在进入页面后直接调用了多次的加载更多loadMore函数
			（这里是query函数），设置FALSE后需要我们在进入页面后在stateChangeSuccess中
			调用加载更多的loadMore函数。
			**/
			$scope.$on('stateChangeSuccess', function() {  
      			$scope.doRefreshMakeRequest();
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

		})


		
		// $scope.articles = [
				// 	{ pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.jpg', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
	   //               readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
	   //              { pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.jpg', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
	   //               readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
	   //              { pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.jpg', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
	   //               readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
	   //              { pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.jpg', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
	   //               readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
	   //              { pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.jpg', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
	   //               readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
	   //              { pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.jpg', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
	   //               readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
	   //              { pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.jpg', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
	   //               readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
				// ];


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


		//判断是否为空对象
		function isEmptyObject(obj) {   
		　　for (var key in obj){
		　　　　return false;//返回false，不为空对象
		　　}　　
		　　return true;//返回true，为空对象
		}	

	</script>




 

</body>
</html>