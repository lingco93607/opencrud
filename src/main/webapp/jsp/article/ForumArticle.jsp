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
    <title>论坛文章</title>


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

<div class="col-md-8 col-sm-offset-2 text-center panel panel-default">  
    <div class="panel-body" style="text-align: left;">
     <h2 style="margin-top:8px;"> 在大学，最应该做些什么？</h2>

        <div class="" style="margin-top: 2px;">       
            <img src="../../jsp/article/1111.png" class="commentAvatarImage">
            <div class="box">
                 <p style="margin-top: 6px;"> <b>怀左同学</b> </p>  
                 <input id="rcorners" type="button" style="margin-left:4px;" value="+关注"  >  
                           
            </div>
             <p style="margin-left: -10px;margin-top: -10px;">2017.10.18 09:06    阅读175  评论100 喜欢50</p>  
      <div>
          <p style="text-align:justify;">
              想不想带着手机去图书馆，原来打算好好读书，却发现控制不住想刷手机？一上午过去了，书没看几页。


你想不想早上醒来八九点，觉得六点起床的计划没有实现，心里焦躁，索性盖着被子继续睡过去？


你想不想用大把的时间在宿舍打游戏，想不想在毕业时因为什么也没有而感到茫然失措，想不想在今天我问你大学做了些什么时，你支支吾吾说不出口……


我知道你并不想，但你可能正是这样的状态，夜里辗转反侧，满世界跑着求安慰。


我应该在大学里做什么？我该看些什么书？我要不要考研？要不要去找个兼职呢？


你什么也不知道，当然，这并不怪你。


怪父母，怪他们对你太好，该打钱时打钱，打电话时只知道叮嘱你吃饱穿暖。你感冒了他们心急如焚，你谈恋爱了他们怕你处理不好学习与感情。怪他们，那么多事都做了，就是没告诉你在学校应该干些什么。


怪学校，怪大学的氛围太自由，没有全天紧锣密鼓的课程，没有老师追着你要作业，没有时不时的家长会来了解你的学习情况。怪学校，你把四年托付给了大学，大学却没有给你制定出最负责的计划表。


怪同学，怪他们太爱玩，非要拉着你吃喝玩乐，你本来不想看电视打游戏，不想大中午起床，不想窝在宿舍不出门。怪他们，只知道带着你玩，却不知道和你一起努力。


我知道，你想努力，想变得优秀，只是不知道该做什么。
          </p>
      </div>   
      <button type="button" class="btn btn-default glyphicon glyphicon-heart" style="border-radius: 25px;margin-top: 10px;">喜欢|1111</button>  
                                      
        </div>    

        <div class="page-header">
            <h2>评论</h2>
        </div> 


        <div style="border-bottom:solid 1px gray;margin-bottom: 10px;">
          <img src="../../jsp/article/1111.png" class="commentAvatarImage">         
                    <p style="margin-top: 10px;"> <b>怀左同学</b> </p>         
                    <p style="margin-left: -10px;margin-top: -10px;">2017.10.18 09:06</p> 
                    <p style="margin-left: 20px;margin-top: -5px;margin-bottom: -4px;">
                        这一生我该陪着三种人，一个是把我养大的人，一个是在我跌倒时扶我起来的人，一个是愿意陪我到老的人，我现在大三，正陪着他们一起走我的余生～
                    </p>
            <button type="button" class="btn btn-link glyphicon glyphicon-thumbs-up" style="text-decoration:none;color:#808080;margin-left: 4px;">22</button>
            <button type="button" class="btn btn-link glyphicon glyphicon-edit" style="text-decoration:none;color:#808080;">回复</button>
                    
                    <div style="border-left:solid 1px gray;margin-bottom: 20px;margin-left: 15px;padding-left: 10px;font-size:12px;">
                         <p style="margin-left: 10px;">
                            A倩倩_： @简书卓不凡 啊啊啊啊啊               
                         </p>
                    
                         <div class="box" style="margin-top: -10px;border-bottom: dotted gray;"> 
                             <p style="margin-left: 10px;">
                                2017.10.18 11:17                 
                             </p>
                             <button type="button" class="btn btn-link glyphicon glyphicon-edit" style="text-decoration:none;color:#808080;margin-top: -4px;font-size:12px;">回复</button>
                        </div>

                            <p style="margin-left: 10px;">
                            A倩倩_： @怀左先生 啊啊啊啊啊               
                            </p>
                    
                         <div class="box" style="margin-top: -10px;border-bottom: dotted gray;"> 
                             <p style="margin-left: 10px;">
                                2017.10.20 10:56                
                             </p>
                             <button type="button" class="btn btn-link glyphicon glyphicon-edit" style="text-decoration:none;color:#808080;margin-top: -4px;font-size:12px;">回复</button> 

                        </div>

                   

                    <button type="button" class="btn btn-link glyphicon glyphicon-pencil" style="text-decoration:none;color:#808080;font-size:12px;">添加新评论</button>
                        
                    </div>

        </div>

        <div style="border-bottom:solid 1px gray;margin-bottom: 10px;">
          <img src="../../jsp/article/1111.png" class="commentAvatarImage">         
                    <p style="margin-top: 10px;"> <b>怀左同学</b> </p>         
                    <p style="margin-left: -10px;margin-top: -10px;">2017.10.18 09:06</p> 
                    <p style="margin-left: 20px;margin-top: -5px;margin-bottom: -4px;">
                        这一生我该陪着三种人，一个是把我养大的人，一个是在我跌倒时扶我起来的人，一个是愿意陪我到老的人，我现在大三，正陪着他们一起走我的余生～
                    </p>
            <button type="button" class="btn btn-link glyphicon glyphicon-thumbs-up" style="text-decoration:none;color:#808080;margin-left: 4px;">22</button>
            <button type="button" class="btn btn-link glyphicon glyphicon-edit" style="text-decoration:none;color:#808080;">回复</button>

        </div>



    </div>
</div>




    </body>
</html>