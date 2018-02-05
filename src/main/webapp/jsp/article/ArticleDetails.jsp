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
    <title>文章详情页面</title>


    <!-- Set render engine for 360 browser 指定webkit引擎渲染 -->
    <meta name="renderer" content="webkit">   

    <!-- No Baidu Siteapp  禁止百度转码-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>  


    <style type="text/css">
       
       
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

        .box-figure {
            margin-right: 1em;

        }

        .box-body {
            flex: 1;

        }
        .box-right{
            flex: 0 0 60%;
            margin:5px;
        
        }
       


    </style>
 

    </head>

    <%@ include file = "../../jsp/common/commonHead.jsp" %>
    <%@ include file = "../../jsp/common/commonNavigation.jsp" %>

<body>

<div class="col-md-8 col-sm-offset-2 text-center panel panel-default">	
    <div class="panel-body" style="text-align: left;">
      <input id="qa" type="button" style="position: absolute; right: 40px;" value="关注问题" onclick="qa()" >    
     <h2 style="margin-top: -1px;"> 31岁库尔茨任奥地利总理，欧洲最年轻领导人诞生</h2>

    <button type="button" class="btn btn-link glyphicon glyphicon-eye-open" style="text-decoration:none;color:#808080;">22人浏览</button>
    <button type="button" class="btn btn-link glyphicon glyphicon-hand-right" style="text-decoration:none;color:#808080;">14条评论</button>
    <button type="button" class="btn btn-link glyphicon glyphicon-heart" style="text-decoration:none;color:#808080;">点赞</button>
    <!-- <span class="label label-default">22人浏览</span>
     <span class="label label-default glyphicon glyphicon-hand-right">14条评论</span>
     <span class="label label-default glyphicon glyphicon-heart">点赞</span>   -->
    </div>
</div>

<div class="col-md-8 col-sm-offset-2 text-center panel panel-default" style="margin-top: -10px;">  
    <div class="panel-body" style="text-align: left;">
        
         
         <div class="box">
         
            <img src="../../jsp/article/1111.png" class="commentAvatarImage">
            <div class="column" style="text-align:left;">
                 <p class="box-body" style="margin-top: 6px;margin-bottom: -10px;"> <b>cuiping</b> </p>  
                 <p class="box-body"> 五千年的风和雨啊藏了多少梦，圆的是筒竖的是条白板带红中。</p>    
            </div>
            <div class="box-right">
             <input id="btn" type="button" style="float: right;" value="关注我" onclick="change()" >  
            </div>
                                      
        </div>     
 

     <p style="margin-top:-2px;text-align:left;">
      那天找土豪同学借耳机，他递给我的时候我没接稳，差点掉地上，他说了一句，小心点，hifi。当时没听明白这个词，不知道啥意思也不会拼写，回来就查了一下才知道是hifi。把耳机还给他的时候，他问我有啥感觉，是不是听完不想再听自己的耳机了？（我耳机就普通的索尼）。我说没有啊，虽然觉得你的耳机有种说不出来的好，但我觉得我耳机也还行。他白了我一眼，说你差不多是个木耳吧。我猜想这耳机一定很贵，肯定得上千甚至上万。然后我就去某宝搜了一下hifi耳机，按默认按销量排序都没看到他的那款，然后我按价格从高到低排了一下，就看到了他的。。。。。emmmmmm。。。。我觉得你们可以先赞我再去搜，省得待会儿还要回来，你们觉得呢？
     </p>

   
    <button id="abc" type="button" class="btn btn-link glyphicon glyphicon-hand-right" style="text-decoration:none;color:#808080;">22条评论</button>
    <button type="button" class="btn btn-link glyphicon glyphicon-send" style="text-decoration:none;color:#808080;">分享</button>
    <button type="button" class="btn btn-link glyphicon glyphicon-star" style="text-decoration:none;color:#808080;">收藏</button>
    <button type="button" class="btn btn-link glyphicon glyphicon-heart" style="text-decoration:none;color:#808080;">点赞</button>


    </div>
</div>


<div id="comment1" class="col-md-8 col-sm-offset-2 text-center panel panel-default" style="text-align: left;margin-top: -15px;  display: none;">
    <div class="panel-heading"><b>22条评论</b></div>

    <ul class="list-group">
       <div id="app"> 
        <template v-for="user in users">
        <li class="list-group-item">
            <div class="column">
            <img src="../../jsp/article/1111.png" class="commentAvatarImage">
                 <p style="margin-top: 6px;margin-bottom: -10px;"> <b>{{user.name}}</b> </p>   
                 <p style="float: right;">{{user.date}}</p>
                 <p> {{user.content}}</p>   
            <button type="button" class="btn btn-link glyphicon glyphicon-heart" style="text-decoration:none;color:#808080;">赞{{user.heart}}</button>
            <button type="button" class="btn btn-link glyphicon glyphicon-edit" style="text-decoration:none;color:#808080;">回复</button>
            </div>  
        
         
        </li>
        </template>
        </div>

  <li class="list-group-item">
<div style="text-align:left;">
            <form class="bs-example bs-example-form" role="form">
             <div class="row">
            
            <div class="col-lg-6">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="写下你的评论...">
                    <span class="input-group-btn">
                        <button class="btn btn-info" type="button">
                            回复
                        </button>
                    </span>
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
        </div><!-- /.row -->
    </form>
</div>                
  </li>
    </ul>

    
</div>


    <script type="text/javascript">
        function change()
            {
                var btn = document.getElementById("btn");
                if (btn.value=="关注我") {
                    btn.value="已关注";
                }
              else {
                btn.value="关注我";
              }
               
            }
        function qa()
            {
                var btn = document.getElementById("qa");
                if (btn.value=="关注问题") {
                    btn.value="已关注";
                }
              else {
                btn.value="关注问题";
              }
               
            }

        $("#abc").click(function(){
          $("#comment1").toggle();
        });

        new Vue({
            el: '#app',
            data: {
            users: [
                { name: 'cuiping',date:'2017-10-25',heart:'30',content:'媒体的问题,随着文化的进步,人们知识体系的完善和提高,媒体的话语导向能力会逐渐下降的。'},
                { name: 'cuiping',date:'2017-10-30',heart:'50',content:'我觉得一个有志在某一领域真正有所成就的人，并不会因为社会对哗众取宠却乏善可陈的同行报以更多「关注」，而感觉受到屈辱。'},
                { name: 'cuiping',date:'2017-10-25',heart:'250',content:'中国达人秀以前也是各种低能儿，残疾在台上表演，然后就可以获得比别人高的成绩。评判标准不是付出多少努力，而是能力如何。' }
                    ]
                    }
                    })

    </script>  

</body>
</html>
