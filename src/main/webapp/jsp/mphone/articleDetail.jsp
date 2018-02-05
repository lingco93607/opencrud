<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <meta name="description" content="">
    <meta name="keywords" content="">
   	<!-- 初始化移动端浏览显示，网页适应设备宽度，禁止窗口缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">   
    <title>渣渣论坛</title>
    <!-- No Baidu Siteapp  禁止百度转码-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>  
    <!-- css style -->
	  <link rel="stylesheet" type="text/css" href="../../js/ionic-1.3.2/css/ionic.min.css">
    <!-- ionic.js 1.3.2 -->
	  <script type="text/javascript" src="../../js/ionic-1.3.2/js/ionic.bundle.min.js"></script>
    <!-- jquery.js 3.0.0 -->
    <script type="text/javascript" src="../../js/jquery-3.0.0.min.js"></script>


    <style type="text/css">
    .userInfo {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .userInfo p {
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .userInfo button {
      display: flex;
      justify-content: center;
      align-items: center;
      max-height: 30px;
    }

    .articleInfo {
      font-size: 10px;
      color: #AAA;
    }

    .reviewUser {
      font-size: 12px;
      color: #AAA;
    }

    .reviews {
      font-size: 12px;
    }

    .row {
      display: flex;
      align-items: center;
    }
    .review {
      display: flex;
      justify-content: space-between;
    }
    .bar-footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .item-input button {
      max-height: 30px;
    }

    .reviews .voted {
      color: green;
    }

    .reviews .cancelVoted {
      color: #444444;
    }

    .reviews .reviewReviewClass {
      display: none;
    }

    #noReviewMain {   
	  color: #aaa;
	  display: flex;
	  justify-content: space-around;
	  display: none;
	}
    </style>
</head>
<body ng-app="ionicApp">
	<ion-content>
    <div ng-controller="MyArticle" >
    <div class="content ionic-pseudo">

      <div class="list">

        <div class="item item-divider" style="border-top: 0px; border-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
          <div class="userInfo row">
              <div class="row">
                <img src="{{pUserPicPath}}" alt="userIcon" width="30px" height="30px">
                <p class="nickName">&nbsp;&nbsp;{{pUserNickName}}</p>
              </div>
              
              <!-- <button class="button button-small" onClick="javascript:history.back(-1);">返回</button> -->  
              <button class="button button-small button-balanced" id="fouse" ng-click="clickFouse()" >关注</button>
          </div>
        </div>

        <ul class="list">

          <li class="item item-body">
            
            <h3 style="margin: 0px;">{{title}}</h3>
            <div class="articleInfo row">
              <span class="articleTime">{{time}}&nbsp;&nbsp;</span>
              <i class="icon ion-thumbsup"></i>&nbsp;&nbsp;{{voteNum}}
              <i class="icon ion-eye"></i>&nbsp;&nbsp;{{readNum}}
            </div>

            <div class="">
              <!-- <div class="item item-image">  
                <img src="{{articleFirstPicPath}}" class="img-responsive img-rounded"  alt="Third slide">
               
              </div> -->
              <p ng-bind-html="text"></p>
              
            </div>
          </li>
          <li class="item item-divider">
            评论
          </li>
          <li id="noReviewMain" style="font-size: 18px;">
    	    	<center>
    	    		<h4 style="color: gray;">暂没有评论,快来评论吧</h4>
    	    	</center>
	        </li>
          <li class="item reviews" ng-repeat="review in reviews" ng-init="outerIndex = $index" item="item" class="item-remove-animate" style="padding: 10px;">
            <div class="row review">
              <div class="item-avatar" style="min-height: 0px; padding-left: 30px;">
                  <img src="{{review.reviewUserPicPath}}" alt="userIcon" style="max-height: 25px; max-width: 25px; margin-top: 12px;">
                  <div class="reviewUser">
                    <span class="nickName">{{review.reviewUserNickName}}</span>
                    <div>
                      <span> {{outerIndex+1}}楼</span>
                      <span class="articleTime">{{review.reviewTime}}</span>
                    </div>
                  </div>
              </div>
              <div style="">
                <i class="icon ion-chatbox cancelVoted" ng-click="rWrite_click({{outerIndex}})" id="rWrite_{{outerIndex}}"></i>
                &nbsp;&nbsp;
                <i class="icon ion-thumbsup cancelVoted" ng-click="rVote_click({{outerIndex}})" id="rVote_{{outerIndex}}" ng-class="{true:'voted',false:'cancelVoted'}[voteName]"></i>
              </div>
            </div>
            <div class="item-body"  style="padding: 0px;"> 
            	
                <div class="reviewText">{{review.reviewText}}</div>
                <div class="reviewReviewClass" id="rReview_{{outerIndex}}">
                	<div ng-repeat="rReview in review.reviewReview track by $index" ng-init="innerIndex = $index" >
	                  <a href="#" id="rReviewNickName_{{outerIndex}}_{{innerIndex}}">
                      {{rReview.reviewNickName}}
                    </a><input type="input" value="{{rReview.reviewReviewUserId}}" id="rReviewId_{{outerIndex}}_{{innerIndex}}" style="display: none;">
                    :&nbsp;&nbsp;{{rReview.reviewReviewText}}
	                  <div class="reviewReply" style="font-size: 10px; color: #444">
	                    <i class="icon ion-reply cancelVoted" ng-click="rreply_click({{outerIndex}}, {{innerIndex}}, {{review.reviewID}})" id="rreply_{{outerIndex}}_{{innerIndex}}">&nbsp;&nbsp;回复&nbsp;&nbsp;</i>
	                    <i class="icon ion-thumbsup cancelVoted" ng-click="rrVote_click({{outerIndex}}, {{innerIndex}})" id="rrVote_{{outerIndex}}_{{innerIndex}}">&nbsp;&nbsp;赞</i>
	                  </div>
                    <div id="rReviewReplytest_{{outerIndex}}_{{innerIndex}}">
                      
                    </div>
                	</div>
                	<div id="rReviewtest_{{outerIndex}}">
                		
                	</div>
                	<div id="rReviewReplyDiv_{{outerIndex}}" class="item-input item-input-wrapper" style="max-height: 25px; margin-top: 10px;">
            			    <i class="icon ion-chatbox" style="color: #AAA; margin-right: 8px;"></i>
            			    <input type="text" id="rReview_input_{{outerIndex}}" placeholder="@{{review.reviewUserNickName}}" style="font-size: 10px;" >
            			    <button id="rReview_button_{{outerIndex}}" class="button icon-left button-balanced" style="font-size: 10px;" ng-click="rReview_input_click({{outerIndex}}, {{review.reviewID}})">回复</button>
            		  </div>
                </div>
            </div>
          </li>
          <li class="item" style="padding: 0px;">
            <div class="bar-footer">
              <label class="item-input item-input-wrapper" style="max-height: 35px;">
                <i class="icon ion-edit" style="font-size: 20px; color: #AAA; margin-right: 8px;"></i>
                <input type="text" class="" id="input_reviewArticle" placeholder="我要评论" style="font-size: 12px;" >
                
                <!-- <i class="icon ion-thumbsup cancelVoted" id="articleVote" ng-click="articleVote()" style="font-size: 20px; margin-right: 22px; margin-left: 22px;"></i> -->
              </label>
              	<button id="reviewArticleButton" class="button icon-left button-balanced" ng-click="reviewArticle()" style="font-size: 12px;">发表</button>
              	<i class="icon ion-thumbsup " id="articleVote" ng-click="articleVote()" ng-class="{true: 'balanced', false: 'cancelVoted'}[isVote]" style="font-size: 20px; margin-right: 22px; margin-left: 22px;"></i>
            </div>
          </li>
        </ul>

      </div>

    </div>
	
    <script type="text/javascript">

    	angular.module('ionicApp', ['ionic'])

		.controller('SlideController', function($scope) {
			$scope.myActiveSlide = 1;
		})

		.controller('MyArticle', function($scope, $http, $ionicPopup, $q) {  

          $scope.initArticle = function(articleID) {
          	//初始化 文章详情
          	if(articleID != null ) {
          		$http({
		         	method: 'GET',
		         	url: '../../action/article/getArticle?articleID='+ articleID +'&userUid=1'
		        }).then(function successCallback(response) {
		           	if(200 == response.status) {
		           		$scope.isVote = response.data.data.isVote;
		            	var articleDetail = response.data.data.article;
		              	$scope.pUserPicPath = '../../images/common/userIcon.ico';
		              	$scope.pUserNickName = '小米';
		              	$scope.articleID = articleDetail.articleID;
		              	$scope.title = articleDetail.title;
		              	$scope.time = formatDateTime(articleDetail.publishDate);
		              	$scope.text = articleDetail.text;
		              	$scope.readNum = articleDetail.readNum;
		              	$scope.voteNum = articleDetail.voteNum;
		              	$scope.articleFirstPicPath = articleDetail.firstImgUrl;
		              	$scope.reviews = [];

		              	var length = articleDetail.reviews.length;  //文章评论帖子
		              	if(length == 0) {
		              		$("#noReviewMain").show();  //显示未评论
		              	} else {
                      $("#noReviewMain").hide();  //隐藏未评论
		              		for(var i=0; i<length; i++) {
			              		var clength = articleDetail.reviews[i].creviews.length;  //；评论的评论
			              		var reviewReview = [];

			              		for(var j=0; j<clength; j++) {
			              			reviewReview.push({
			              				reviewReviewId: articleDetail.reviews[i].creviews[j].id,
			              				reviewReviewText: articleDetail.reviews[i].creviews[j].text,
                            reviewReviewUserId: 2,
			              				reviewNickName: 'abay',
			              			});
			              		}
			              		

			                	$scope.reviews.push({
                            reviewUserId: 1,
			                  		reviewUserPicPath: '../../images/common/userIcon.ico', 
			                  		reviewUserNickName: '小黄',
			                  		reviewID: articleDetail.reviews[i].reviewID,
			                  		reviewTime: formatDateTimeMonthDay(articleDetail.reviews[i].reviewDate),
			                  		reviewText: articleDetail.reviews[i].text, 
			                  		reviewReview: reviewReview,
			                	})
			              	}
		              	}
		           }
		            
		         }, function errorCallback(response) {
		           // 请求失败执行代码
		        });
          	}
          }


          $scope.initReview= function(articleID) {
          	//初始论坛评论
          	if(articleID != null ) {
          		$http({
		         	method: 'GET',
		         	url: '../../action/article/getReviews?articleID='+ articleID +'&userUid=1'
		        }).then(function successCallback(response) {
		           	if(200 == response.status) {
		            	var reviews = response.data.data.review;
		              	var length = reviews.length;  //文章评论帖子
		              	if(length == 0) {
		              		$("#noReviewMain").show();
		              	} else {
                      $("#noReviewMain").hide();
		              		for(var i=0; i<length; i++) {
			              		var clength = reviews[i].creviews.length;  //；评论的评论
			              		var reviewReview = [];

			              		for(var j=0; j<clength; j++) {
			              			reviewReview.push({
			              				reviewReviewId: reviews[i].creviews[j].id,
			              				reviewReviewText: reviews[i].creviews[j].text,
			              				reviewNickName: 'abay',
			              			});
			              		}
			              		
			                	$scope.reviews.push({
			                  		reviewUserPicPath: '../../images/common/userIcon.ico', 
			                  		reviewUserNickName: '小黄',
			                  		reviewID: reviews[i].reviewID,
			                  		reviewTime: formatDateTime(reviews[i].reviewDate),
			                  		reviewText: reviews[i].text, 
			                  		reviewReview: reviewReview,
			                	})
			              	}
		              	}
		           }
		            
		         }, function errorCallback(response) {
		           // 请求失败执行代码
		        });
          	}

          }
          
          $scope.initArticle(parseInt(GetQueryString("articleID")));
          //$scope.initArticle(1);

          //修改关注状态
          $scope.clickFouse = function() {
            console.log($('#fouse').val());
            var clickFouseId = $('#fouse');
            if(clickFouseId.text() == '关注') {
              clickFouseId.text('取消关注');
              clickFouseId.removeClass('button-balanced');
              clickFouseId.addClass('button-outline button-stable');
            } else {
              clickFouseId.text('关注');
              clickFouseId.removeClass('button-outline button-stable');
              clickFouseId.addClass('button-balanced');
            }
          }

          $scope.rVote_click = function(id) {
            var element = $("#rVote_" + id);
            var className = element.attr("class");
            if(className == "icon ion-thumbsup cancelVoted activated") {
              element.removeClass("cancelVoted");
              element.addClass("voted");
            } else {
              //element.removeClass("voted");
              //element.addClass("cancelVoted");
            }
          }

          $scope.rWrite_click = function(id) {
            //var rrId = $("#rReview_" + id);
            var rwId = $("#rReview_" + id);
            rwId.toggle();
            var className = rwId.attr("class");
            if(className == "icon ion-chatbox cancelVoted activated") {
              rwId.removeClass("cancelVoted");
              rwId.addClass("voted");
            } else {
              rwId.removeClass("voted");
              rwId.addClass("cancelVoted");
            }
            
          }


          //给首次评论点评
          $scope.rReview_input_click = function (outid, reviewID) {

          	var inputReviewText = $("#rReview_input_" + outid).val();
            var rReviewButton = $("#rReview_button_" + outid);
	          var articleID = $scope.articleID;
	          if(inputReviewText == "" || inputReviewText == null) {
	          		$scope.ionicPopupAlert("提示", "你还没任何评论呢,赶紧畅所欲言吧！");
	          } else {
              rReviewButton.removeClass("button-balanced");
              rReviewButton.addClass("cancelVoted");
              rReviewButton.attr("disabled", true);
	          	$scope.doRequestReplyReview(articleID, reviewID, inputReviewText, outid, rReviewButton);
	          	var replyId = $("#rReviewtest_" + outid);
	          	replyId.append('<div><a href="#">' + $scope.pUserNickName + '</a>:<b>&nbsp;&nbsp;' + inputReviewText + '</b> <br><div>');
	          		// var a = $scope.doRequestReplyReview(articleID, reviewID, inputReviewText);  //2017年11月23日20:09:06 bug
	          		// var b = 0;

	          		// if($scope.doRequestReplyReview(articleID, reviewID, inputReviewText)) {

	          		// }
	          		
	          }
          }

          //增加给评论回复的功能
          $scope.rreply_click = function(outid, innerid, reviewID) {
          	var id = outid + '_' + innerid;
            var rreplyId = $("#rreply_" + id);
            var className = rreplyId.attr("class");
            if(className.indexOf('cancelVoted') > 0) {  //是否包含cancelVotehui
              rreplyId.removeClass("cancelVoted");
              rreplyId.addClass("voted");
              $("#rReviewReplyDiv_" + outid).hide();  //隐藏回复帖子内容
              var rReviewUserId = $("#rReviewId_" + id).val();
              var rReviewUserNickName = $.trim($("#rReviewNickName_" + id).text());
              rreplyId.parent().append('<div><div id="reply_divInput_'+ id +'"  class="item-input item-input-wrapper" style="max-height: 25px; margin-top: 5px;"><i class="icon ion-reply" style="color: #AAA; margin-right: 8px;"></i><input type="text" id="reply_input_'+ id +'" placeholder="@'+ rReviewUserNickName +'" style="font-size: 10px;" ><button id="reply_button_' + id + '" class="button icon-left button-balanced " onClick="reply_input__click('+ "'" +id+ "','" + $scope.articleID + "','" + $scope.pUserNickName + "','" + reviewID + "'" +')" style="font-size: 10px; max-height:14px;">回复</button></div></div><br>');
              //var a = $("#rReviewtest_" + outid); articleVote() ng-click="reply_input__click(' + id +')" 
              
            } else {
              rreplyId.removeClass("voted");
              rreplyId.addClass("cancelVoted");
              //rreplyId.parent().remove("div");
              $("#reply_divInput_" + id).remove();  //删除回复帖子内容
              $("#rReviewReplyDiv_" + outid).show();
            }
            
          }

          //评论回复，暂时不用，js写的无法调用函数
          $scope.reply_input__click = function() {
            var id =0;
            var inputReplyReviewText = $("#reply_input_" + id).val();
  	        var articleID = $scope.articleID;
  	        if(inputReplyReviewText == "" || inputReplyReviewText == null) {
  	          	$scope.ionicPopupAlert("提示", "你还没任何回复呢,赶紧畅所欲言吧！");
  	        } else {
	          	var replyId = $("#rReview_" + id);
	          	replyId.parent().append('<a href="#">' + $scope.pUserNickName + '</a>:&nbsp;&nbsp;' + inputReplyReviewText);
	          }
          }

          // 增加给评论的评论点赞
          $scope.rrVote_click = function(outid, innerid) {
          	var id = outid + '_' + innerid;
            var rrVoteId = $("#rrVote_" + id);
            var className = rrVoteId.attr("class");
            if(className.indexOf('cancelVoted') > 0) {
              rrVoteId.removeClass("cancelVoted");
              rrVoteId.addClass("voted");
            } else {
              //rrVoteId.removeClass("voted");
              //rrVoteId.addClass("cancelVoted");
            }
          }

          // 给文章点赞
          $scope.articleVote = function() {
            var articleVoteId = $("#articleVote");
            var className = articleVoteId.attr("class");
            if(className.indexOf('cancelVoted') > 0) {
            	var articleID = $scope.articleID;
            	$scope.doRequestVoteArticle(articleID, articleVoteId); //请求文章点赞成功
            } else {
              //articleVoteId.removeClass("balanced");
              //articleVoteId.addClass("cancelVoted");
            }

          }


          $scope.reviewArticle = function() {
          	var inputReviewText = $("#input_reviewArticle").val();
            var reviewArticleButton = $("#reviewArticleButton");
          	var articleID = $scope.articleID;
          	if(inputReviewText == "" || inputReviewText == null) {
          		$scope.ionicPopupAlert("提示", "你还没发表看法呢,赶紧畅所欲言吧！");
          	} else {
              reviewArticleButton.removeClass("button-balanced");
              reviewArticleButton.addClass("cancelVoted");
              reviewArticleButton.attr("disabled", true);
          		$scope.doRequestReviewArticle(articleID, inputReviewText, reviewArticleButton);        		
          	}
          }

          //帖子点赞的请求
          $scope.doRequestVoteArticle = function(articleID, articleVoteId) {
          	$http({
    		        method: 'POST',
    		        url: '../../action/article/voteArticle?articleID='+ articleID +'&userUid=1'
    		    }).then(function successCallback(response) {
    		        if(200 == response.status) {
    		        	//response.data
    		            // if(true) {
    		            // 	// articleVoteId.removeClass("cancelVoted");
                  // 	// 		articleVoteId.addClass("balanced");
                  			
    		            // }

                    if(response.data) {
                      $scope.ionicPopupAlert("提示", "点赞成功");
                      articleVoteId.removeClass("cancelVoted");
                      articleVoteId.addClass("balanced");
                    } else {
                      $scope.ionicPopupAlert("提示", "点赞失败");
                    }
    		        } else {
                  $scope.ionicPopupAlert("提示", "点赞失败，可能网络比较差");
    		        	$scope.isVote = false;
    		        }
    		         
    		      }, function errorCallback(response) {
    		           // 请求失败执行代码
    		      });

          }


          //评论一个帖子的请求
          $scope.doRequestReviewArticle = function(articleID, commonText, reviewArticleButton) {
          	//var deferred = $q.defer(); // 声明延后执行，表示要去监控后面的执行  
          	$http({
    		        method: 'POST',
    		        url: '../../action/article/reviewArticle?articleID='+ articleID +'&userUid=1&commonText=' + encodeURI(commonText),
    		    }).then(function successCallback(response) {
    		        if(200 == response.status) {
    		            //return response.data.status;
    		            if(response.data.status == true) {
                      $scope.ionicPopupAlert("提示", "评论成功");
                      reviewArticleButton.removeClass("cancelVoted");
                      reviewArticleButton.addClass("button-balanced");
                      reviewArticleButton.attr("disabled", false);
                      $("#input_reviewArticle").val("");
    		            	//$scope.initArticle(parseInt(GetQueryString("articleID")));
                      $scope.initReview($scope.articleID);
    		            } else {
    		            	$scope.ionicPopupAlert("提示", "发表失败");
    		            }
    		        } else {
    		        	$scope.ionicPopupAlert("提示", "发表失败");
    		        }
    		         
    		      }, function errorCallback(response) {
    		           // 请求失败执行代码
    		      });
          }


          //回复一个评论的请求
          $scope.doRequestReplyReview= function(articleID, reviewID ,text, outid, rReviewButton) {
          	$http({
    		        method: 'POST',
    		        url: '../../action/review/writeCReview?articleID='+ 
    		        articleID +'&reviewID='+ reviewID+'&userUid=1&text=' + encodeURI(text)
    		    }).then(function successCallback(response) {
    		        if(200 == response.status) {
    		        	//评论状态：0-成功；1-失败
    		        	if(response.data == 0) {
                    
                    rReviewButton.removeClass("cancelVoted"); 
                    rReviewButton.addClass("button-balanced");
                    rReviewButton.attr("disabled", false);
                    $("#rReview_input_" + outid).val("");
                    $scope.ionicPopupAlert("提示", "回复成功");
    		        		//return true;
    		        	} else if(response.data == 1) {
                    
                    rReviewButton.removeClass("cancelVoted"); 
                    rReviewButton.addClass("button-balanced");
                    rReviewButton.attr("disabled", false);
                    $scope.ionicPopupAlert("提示", "回复失败");
    		        		//return false;
    		        	} else {
    		        		//return false;
                    rReviewButton.removeClass("cancelVoted"); 
                    rReviewButton.addClass("button-balanced");
                    rReviewButton.attr("disabled", false);
                    $scope.ionicPopupAlert("提示", "回复失败");
    		        	}
    		        } else {
                  rReviewButton.removeClass("cancelVoted"); 
                  rReviewButton.addClass("button-balanced");
                  rReviewButton.attr("disabled", false);
                  $scope.ionicPopupAlert("提示", "网络出错");
    		        	return false;

    		        }
    		         
    		      }, function errorCallback(response) {
    		           // 请求失败执行代码
    		      });
          }


        //弹出框提示框
    		$scope.ionicPopupAlert = function(title, text) {
    			var alertPopup = $ionicPopup.alert({
             	title: title ,
             	template: text
          });
          alertPopup.then(function(res) {
             	console.log('This is a popup');
          });
    		}

    	})
		

		//util 工具函数  时间戳格式化
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

    //util 工具函数  时间戳格式化没有年月
    function formatDateTimeMonthDay(timeStamp) {
        var date = new Date(timeStamp);
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
        return m + '-' + d + ' ' + h + ':' + minute + ':' + second;
    }

		//util 工具函数 获取url中参数
		function GetQueryString(name) {
	 		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	 	    var r = window.location.search.substr(1).match(reg);
	 	    if(r!=null)return  decodeURIComponent(r[2]); return null;      
		}

		

    function reply_input__click (id, articleID, pUserNickName, reviewID) {
      // alert(id);
      // var id =0;
      var inputReplyReviewText = $("#reply_input_" + id).val();
      var replyButton = $("#reply_button_" + id);
      var rReviewUserId = $("#rReviewId_" + id).val();
      var rReviewUserNickName = $.trim($("#rReviewNickName_" + id).text());
      //var articleID = $scope.articleID;
      if(inputReplyReviewText == "" || inputReplyReviewText == null) {
          //$scope.ionicPopupAlert("提示", "你还没任何回复呢,赶紧畅所欲言吧！");
          alert("你还没任何回复呢,赶紧畅所欲言吧！")
      } else {
        var replyId = $("#rReview_" + id);
        var rreplyId = $("#reply_divInput_" + id);
        //var rreplyId = $("#rReviewReplytest_" + id);
        replyButton.removeClass("button-balanced");
        replyButton.addClass("cancelVoted");
        replyButton.attr("disabled", true);   //禁止用户多次点击按钮

        rreplyId.parent().prepend('<a href="#">' + pUserNickName + '</a>:<b>&nbsp;&nbsp;@' + rReviewUserNickName + '&nbsp;&nbsp;' + inputReplyReviewText + '</br>');
        //prepend
        var text = "@" + rReviewUserNickName + "  " + inputReplyReviewText;
        var url = "../../action/review/writeCReview?articleID="+ articleID +"&reviewID="+ reviewID+"&userUid=1&text=" + encodeURI(text);
        var type = "POST";
        jqueryAjaxRequest(url, type, id, replyButton)
      }
    }

    //jquery ajax 同步请求 函数 子评论的评论请求
    function jqueryAjaxRequest(url, type, id, replyButton) {
      $.ajax({
        url: url,
        type: type,
        async: false,
        success: function(data, textStatus) {
          if("success" == textStatus) {
            //评论状态：0-成功；1-失败
            if(data == 0) {
              $("#reply_input_" + id).val("");
              replyButton.removeClass("cancelVoted");
              replyButton.addClass("button-balanced");
              replyButton.attr("disabled", false);
              alert("评论成功");
            } else if(data == 1) {
              replyButton.removeClass("cancelVoted");
              replyButton.addClass("button-balanced");
              replyButton.attr("disabled", false);
              alert("评论失败，稍后评论");
            } else {
              replyButton.removeClass("cancelVoted");
              replyButton.addClass("button-balanced");
              replyButton.attr("disabled", false);
              alert("评论失败，稍后评论");
              
            }
          }
        }, 
        fail: function(error) {
          alert("评论失败，稍后评论");
          return false;
        }

      });
    }


    </script>


</body>
</html>