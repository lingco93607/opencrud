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
    	*html {
    		margin: 0px;
    		border: 0px;
    	}
        .articlePublisher {
        	display: flex;
        	align-items: center;
        }
        .articleMain {
			display: flex;
			justify-content: space-around;
		}

		.articleText {
			font-size: 12px;
		}

		.articleImg {
			/*max-width: 80%;
			display: flex;
			flex-direction: column;
			*/
			display:flex;
  			align-items:center;/*垂直居中*/
  			justify-content: center;/*水平居中*/
		}

		.article {
			border-bottom:2px solid #AAA;
			-moz-box-shadow: 2px #AAA; /* 老的 Firefox */
			box-shadow: 1px #AAA;
			margin-bottom: 20px;
		}

		.publicTime {
			margin-left: 20px;
			color: #AAA;
			font-size: 10px;
		}
		.nickName {
			margin-left: 20px;
			font-weight: bold;
			font-size: 14px;
		}

		.swiper-container {
	        width: 100%;
	        height: 200px;
	    }
	    .swiper-slide {
	        text-align: center;
	        font-size: 12px;
	        background: #AAA;

	        /* Center slide text vertically */
	        display: -webkit-box;
	        display: -ms-flexbox;
	        display: -webkit-flex;
	        display: flex;
	        -webkit-box-pack: center;
	        -ms-flex-pack: center;
	        -webkit-justify-content: center;
	        justify-content: center;
	        -webkit-box-align: center;
	        -ms-flex-align: center;
	        -webkit-align-items: center;
	        align-items: center;
	    }  

    </style>

    <%@ include file = "../../jsp/common/commonHead.jsp" %>
	<!-- 首行信息 -->
    <div class="col-lg-8 col-lg-offset-2 firstRow" style="padding: 0px;">
        <!-- 导航消息 -->
        <div class="firstRowOne">
       		<!-- Swiper -->
		    <div class="swiper-container">
		        <div class="swiper-wrapper">
		            <div class="swiper-slide">Slide 1</div>
		            <div class="swiper-slide">Slide 2</div>
		            <div class="swiper-slide">Slide 3</div>
		            <div class="swiper-slide">Slide 4</div>
		            <div class="swiper-slide">Slide 5</div>
		            <div class="swiper-slide">Slide 6</div>
		            <div class="swiper-slide">Slide 7</div>
		            <div class="swiper-slide">Slide 8</div>
		            <div class="swiper-slide">Slide 9</div>
		            <div class="swiper-slide">Slide 10</div>
		        </div>
		        <!-- Add Pagination -->
		        <div class="swiper-pagination"></div>
		    </div>
        </div>
    </div>

    <div>
    	<button class="btn btn-success" style="width: 95%; margin: 10px 10px;" onclick="window.location.href='../../jsp/mphone/writeArticle.jsp';">发帖</button>
    </div>

    <!-- 加载论坛的文章 -->
    <div class="col-lg-8 col-lg-offset-2" id="article" >
        <div class="article" v-for="article in articles">
        <div class="articlePublisher">
            <img :src="article.pUserPicPath" alt="userIcon" width="30px" height="30px">
            <p class="nickName">{{article.pUserNickName}}</p>
            <p class="publicTime">{{article.publishDate}}</p>
        </div>

        <div class="articleTitle" onclick="window.location.href='../../jsp/mphone/articleDetail.jsp';">
            <a href="#"><h3>{{article.title}}</h3></a>
        </div>
        <div class="articleMain" onclick="window.location.href='../../jsp/mphone/articleDetail.jsp';">
            <div class="articleText">
                <!-- {{article.text}} -->
                
                <div v-html="article.text"></div>
                
            </div>
            <div class="articleImg">
                <!-- {{article.firstImg}} -->
                <!-- <img :src="{{article.firstImg}}" alt="Third slide" width="350px"> -->
                <img :src="article.articleFirstPicPath" class="img-responsive img-rounded"  alt="Third slide">
            </div>
        </div>
        <div class="articleFoot">
            
            <!-- <button type="button" class="btn btn-default btn-foot" aria-label="Left Align">
              <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
              <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
            </button> -->
            <button type="button" class="btn btn-foot btn-link glyphicon glyphicon-eye-open" style="color:#808080;">{{article.readNum}}</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <!-- <button type="button" class="btn btn-default btn-foot" aria-label="Left Align">
                <span class="glyphicon glyphicon-heart" aria-hidden="true">{{article.heart}}</span>  <!-- glyphicon glyphicon-heart-empty 
            </button> --> 
            <button type="button" class="btn btn-foot btn-link glyphicon glyphicon-heart" style="color:#808080;">{{article.voteNum}}</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-foot btn-link glyphicon glyphicon-comment" style="color:#808080;">{{article.reviewsNum}}</button>
            <!-- <button type="button" class="btn btn-default btn-foot" aria-label="Left Align">
              <span class="glyphicon glyphicon-comment" aria-hidden="true">{{article.comment}}</span>
            </button> -->
        </div>
        </div>
    </div>
</body>

    <script type="text/javascript">
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true
    });

    // var article = new Vue({
    //  el: '#article',
    //  data: {
    //      articles: []
    //  }
    // })

    var article = new Vue({
        el: '#article',
        data: {
            articles: [
                {pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.ico', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
                 readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
                {pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.ico', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
                 readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
                {pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.ico', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
                 readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
                {pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.ico', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
                 readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
                {pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.ico', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
                 readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
                {pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.ico', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
                 readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
                {pUserId: 1, pUserNickName: '小明同学', pUserPicPath: '../../images/common/userIcon.ico', articleID: 11, title: '有哪些发人深省、引人思考的好书', text: '忘记是在西雅图还是旧金山了，一飞机中国人排队过关，前一个女生抱了一个很大只的毛绒熊。海关除了柜台里面坐着的，外面还有一些维持秩序的工作人员，有一个小胖墩儿工作人员看见了，过来问：“这是一个毛绒玩具？你的？”女生很紧张地说是啊，工作人员说你在这儿等着，我很快回来。', publishDate: '2017年10月30日09:06:01',
                 readNum: 88, voteNum: 77, reviewsNum: 88, articleFirstPicPath: '../../images/common/slide1.png' },
            ]
        }
    })


    //init(0);
    //initHotArticle();
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
    </script>
</html>