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
</head>

	<style type="text/css">
		.firstRow {
			display: flex;
			justify-content: space-between;
		}
		.firstRowOne {
			max-width: 50%;
		}
		.hotRecommend {
			max-width: 500px;
		}
		.hotRecommend li {
			float: center;
			width: 100%;
			height: 30px;
			line-height: 30px;
			/*隐藏溢出内容*/
			overflow: hidden;
			/*文本溢出时显示为省略号*/
			text-overflow: ellipsis;
			/*禁止换行*/
			white-space: nowrap;
		}

		.hotRecommendName {
			display: flex; 
			justify-content: flex-end;
			font-size: 12px; 
			color: #AAA;
		}

		.articlePublisher {
			display: flex;
			align-items: center;
		}
		.publicTime {
			font-size: 10px;
			color: #AAA;
			margin-left: 20px;
		}
		.nickName {
			margin-left: 20px;
		}
		.article {
			border-top:2px solid #AAA;
			-moz-box-shadow: 1px #AAA; /* 老的 Firefox */
			box-shadow: 1px #AAA;
			margin-top: 20px;
		}

		.articleMain {
			display: flex;
			justify-content: space-between;
		}

		.btn-foot {
			text-decoration:none;
		}

	</style>

	<%@ include file = "../../jsp/common/commonHead.jsp" %>
	<%@ include file = "../../jsp/common/commonNavigation.jsp" %>

	<!-- 首行信息 -->	
	<div class="col-lg-8 col-lg-offset-2 firstRow">
		<!-- 导航消息 -->
		<div class="firstRowOne">
			<div id="myCarousel" class="carousel slide">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>   
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="../../images/common/slide1.png" alt="First slide">
						<div class="carousel-caption">标题 1</div>
					</div>
					<div class="item">
						<img src="../../images/common/slide2.png" alt="Second slide">
						<div class="carousel-caption">标题 2</div>
					</div>
					<div class="item">
						<img src="../../images/common/slide3.png" alt="Third slide">
						<div class="carousel-caption">标题 3</div>
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="carousel-control left" href="#myCarousel" 
				   data-slide="prev">&lsaquo;</a>
				<a class="carousel-control right" href="#myCarousel" 
				   data-slide="next">&rsaquo;</a>
			</div>
		</div>
		<!-- 热帖推荐信息 -->
		<div class="firstRowTwo">
		</div>
		<!-- 热帖推荐信息 -->
		<div class="hotRecommend" id="hotRecommend">
			<h2>最新热帖推荐</h2>
			<li v-for="site in sites">
		      <div style="display: flex; flex-wrap: row;">
		      	<div>{{ site.title }}</div>
		      	<div class="hotRecommendName" style="">{{ site.time }}</div>
		      </div>
			</li>
		</div>
	</div>

	<!-- 加载论坛的文章 -->
	<div class="col-lg-8 col-lg-offset-2" id="article" >
		<div class="article" v-for="article in articles">
		<div class="articlePublisher">
			<img src="../../images/common/userIcon.ico" width="35px" height="35px">
			<p class="nickName">{{article.nickName}}</p>
			<p class="publicTime">{{article.publicTime}}</p>
		</div>

		<div class="articleTitle">
			<a href="#"><h2>{{article.title}}</h2></a>
		</div>
		<div class="articleMain">
			<div class="articleText">
				<!-- {{article.text}} -->
				<div v-html="article.text"></div>
           		<a href="#" onclick="showHideText()">查看更多</a>
			</div>
			<div class="articleImg">
				<!-- {{article.firstImg}} -->
				<!-- <img :src="{{article.firstImg}}" alt="Third slide" width="350px"> -->
				<img :src="article.firstImg" alt="Third slide" width="350px">
			</div>
		</div>
		<div class="articleFoot">
			
			<!-- <button type="button" class="btn btn-default btn-foot" aria-label="Left Align">
			  <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
			  <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
			</button> -->
			<button type="button" class="btn btn-foot btn-link glyphicon glyphicon-eye-open" style="color:#808080;">{{article.eye}}</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <button type="button" class="btn btn-default btn-foot" aria-label="Left Align">
		  		<span class="glyphicon glyphicon-heart" aria-hidden="true">{{article.heart}}</span>  <!-- glyphicon glyphicon-heart-empty 
			</button> --> 
			<button type="button" class="btn btn-foot btn-link glyphicon glyphicon-heart" style="color:#808080;">{{article.heart}}</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-foot btn-link glyphicon glyphicon-comment" style="color:#808080;">{{article.heart}}</button>
			<!-- <button type="button" class="btn btn-default btn-foot" aria-label="Left Align">
			  <span class="glyphicon glyphicon-comment" aria-hidden="true">{{article.comment}}</span>
			</button> -->
		</div>
		</div>
	</div>
</body>

	<script type="text/javascript">

	window.current = 0 //定义全局变量
    var pullRefreshss = true;
    $(window).scroll(
            function () {
                /*当前滚动条到顶部的距离*/
                var top = $(document).scrollTop();
                /*当前浏览器的可是高度*/
                var height = document.body.clientHeight;
                /*当前网页（body）的高度*/
                var z_height = $(document).height();
                /*判断（网页的body高度减去当前浏览器的可视高度是否等于滚动条到顶部的距离）
                 * 相等：则判定当前页面在底部
                 * 不相等：判定当前页面不在底部
                 * */
                var stats = ( z_height - height-top < 5) ;
                    $("#show_s").html("top:"+top+",z_height:"+z_height+",height:"+height+",是否底部:"+stats);

                if (stats) {
                    /*当前网页在最底部，执行该函数*/
                    upPullfreshFunction(current);
                }else {
                    if (!pullRefreshss) {
                        $("#show_view").html("没有更多的数据");
                    }
                }
            }
    );


    var hotR = new Vue({
		el: '#hotRecommend',
		data: {
	    	sites: [
	    	]
	  }
	})


	var article = new Vue({
		el: '#article',
		data: {
			articles: []
		}
	})

	/**
	查看更多
	xiaoy
	2017年10月24日10:57:14
	*/	
    function showHideText() {
    	$(".showdiv").toggle();
    }

    init(0);
    initHotArticle();
    /**
	初始化页面，ajax同步请求
    */
    function init(current) {

    	$.ajax({
			headers : {
				Accept : "application/json; charset=utf-8"
			},
			url : "../../action/article/getArticleInfo?start="+current+"&end=5&useruuid=1",  //用户Id未设置
			async: false,
			type : "get",
			success : function(data) {
				var data = data.data.articleInfo;
				if(data != null && data.length !=0) {
					console.log(data);
					let length = data.length;
					for(let i=0; i<length; i++) {
						article.articles.push({
							nickName: data[i].zNickname,
							publicTime: data[i].article.publishDate,
							title: data[i].article.title,
							text: data[i].article.text,
							firstImg: '../../images/common/slide1.png',
							eye: data[i].article.readNum,
							heart: data[i].article.voteNum,
							comment: 88
						})
					}

				}
			},
			error: function(e) {
				console.log(e);
			}
		});
		current = current;
    }

    function initHotArticle() {
    	$.ajax({
			headers : {
				Accept : "application/json; charset=utf-8"
			},
			url : "../../action/article/getHotArticleInfo?num=10",  
			type : "get",
			success : function(data) {
				if(data != null && data.length !=0) {
					for(let i=0; i<data.length; i++) {
						hotR.sites.push({
							title: data[i].title,
							time: data[i].publishDate
						})
					}

				}
			},
			error: function(e) {
				console.log(e);
			}
		});
    }

    function upPullfreshFunction(current) {
        if (!pullRefreshss) {
            return;
        }
        /*获取当前page值*/
        var page = current
        /*page+1以便来获取下一页的数据*/
        page=Number(current)+Number(5);
        //var keyWord= $("#key").val();
        // var orders=$("#orders").val();
        // if(orders=="up"){
        //     orders="down";
        // }else{
        //     orders="up";
        // }
        //init(page);
    }

    /*function upload(){
        if (pullRefreshss) {
            upPullfreshFunction();
        } else {
            $("#show_view").html("没有更多的数据");
        }

        if(mediatorList.length>0){
            }else{
                pullRefreshss = false;
            }
    }*/
	</script>
</html>