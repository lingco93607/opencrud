<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html >
<head>
    <meta charset="utf-8">
    <!-- 初始化移动端浏览显示，网页适应设备宽度，禁止窗口缩放 -->
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>内部添加野怪</title>
	
	<%@ include file = "../../jsp/common/commonHead.jsp" %>
	<style type="text/css">
	</style>
</head>
<body ng-app="internet">
	<div ng-controller="showMonster" ng-init="getMonsterRequest()">
		<div class="table-responsive" >
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>英雄名字</th>
						<th>类型</th>
						<th>等级</th>
						<th>境界</th>
						<th>魅力</th>
						<th>攻击力</th>
						<th>防御力</th>
						<th>速度</th>
						<th>五行属性</th>
						<th>技能</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
					</tr>
					<tr>
						<td>Sachin</td>
						<td>Mumbai</td>
						<td>400003</td>
					</tr>
					<tr>
						<td>Uma</td>
						<td>Pune</td>
						<td>411027</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
    
    <script type="text/javascript">
    	var start = 0;

    	angular.module('internet', ['internet'])

		.controller('showMonster', function($scope, $http) {

			$scope.getMonsterRequest = function() {
				$http({
					method: 'GET',
					url: 'http://106.15.193.89:8080/zhazha/action/monster/getmonsters?start=0&end=5'
				}).then(function successCallback(response) {
					if(200 == response.status) {
						console.log(response);
					}
				}, function errorCallback(response) {
					// 请求失败执行代码
				})
			}


			$scope.doRefreshMakeRequest = function() {
				var blob=new Blob(['Hello world'],{type:'text/plain'});
	        	$http({
					method: 'GET',
					url: '../../action/article/getArticles?start='+ start +'&end=5&useruuid=1',
					data: blob
					//url: '../../action/article/getArticles?start=20&end=5&useruuid=1'
				}).then(function successCallback(response) {
						if(200 == response.status) {
							if(!isEmptyObject(response.data.data)) {
								var articleInfo = response.data.data.articleInfo;
								var length = articleInfo.length;
								for (var i=0; i<length; i++) {
									$scope.articles.push({
										pUserId: articleInfo[i].zUserId,
										pUserNickName: articleInfo[i].zNickname,
										pUserPicPath: articleInfo[i].zPicPath,
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
					$timeout(function() {
			          $scope.$broadcast("scroll.infiniteScrollComplete");
			        });
                    //$scope.$broadcast('scroll.infiniteScrollComplete');
            	});
			};

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