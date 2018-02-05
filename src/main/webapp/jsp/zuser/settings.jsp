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
<title>设置</title>


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
}

.Main {
    width:980px;        
    padding-top:30px;        
    margin:0 auto;  
    font-family: "微软雅黑";
}  
.Main .Box {
    width:50px;
    margin-left: -20px;

}
.Main .Box ul{
    list-style-type: none;
}
.Main .Box ul li{
    margin: 5px;
    padding: 5px;
    text-align: left;
}


.Main .Box ul li button{
    width: 150px;
    height: 50px;
    text-decoration: none;
    border: none;

}


.Main .Box ul li button:hover {
    background-color: #0099FF;
}



.Detail{
    position: relative;
    top: -250px;
    left: 200px;
}
.Detail ul{
    list-style-type: none;
}
.Detail ul li{
    margin-top:30px;
    padding-bottom:15px;
    border-bottom:1px solid #efefef;
}       
.submit_but {
    width:82px;
    height:32px;
    border:1px solid #0492d6;
    background:#13a5ec;
    display:block;
    color:#fff;
    font-size:16px;
    font-family:"Microsoft YaHei UI";
    line-height:30px;
    border-radius:5px;
    cursor:pointer;
    margin-top:40px;
    margin-left: 28px;
}
.submit_but:hover {
    background:#1EB0F6;
}      
.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    margin-left: 15px;
    margin-top: 5px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}

.pstyle{
    margin: 10px;
}

</style>
 

</head>

<%@ include file = "../../jsp/common/commonHead.jsp" %>
<%@ include file = "../../jsp/common/commonNavigation.jsp" %>

<body>
    <div class="Main">
        <div class="Box">
            <ul>
                <li>
                   <button id="PartA" type="button" class="glyphicon glyphicon-list" style="background-color:#0099FF">&nbsp;基本设置</button>
                </li>
                <li>
                   <button id="PartB" type="button" class="glyphicon glyphicon-user">&nbsp;个人资料</button>
                </li>
                <li>
                   <button id="PartC" type="button" class="glyphicon glyphicon-tree-conifer">&nbsp;积分&nbsp;&nbsp;</button>
                </li>
                <li>
                   <button id="PartD" type="button" class="glyphicon glyphicon-apple">&nbsp;等级&nbsp;&nbsp;</button>
                </li>
            </ul>            
        </div>

        <div id="basic" class="Detail">
            <ul>                
                <img src="../../jsp/article/1111.png" class="commentAvatarImage">
                <a href="" class="file">更换头像
                    <input type="file" name="" id="">
                </a>           
                <li>
                    <label for="name">用户名：</label>
                    <input style="margin-left: 60px;" type="text"  placeholder="崔萍萍" required />                    
                </li>
                <li>
                    <label for="phone">手机：</label>
                    <input class="align" style="margin-left: 70px;" type="text"  name="phone" placeholder="13823456789" required />                    
                </li>
                <li>
                    <label>语言设置：</label>
                    <label style="margin-left: 38px;"><input type="radio"  name="language" value="1">中文简体</label>
                    <label style="margin-left: 10px;"><input type="radio"  name="language" value="2">中文繁体</label>
                </li>
                <li>
                    <label>接收谁的消息：</label>
                    <label style="margin-left: 10px;"><input type="radio"  name="message" value="1">所有人</label>
                    <label style="margin-left: 10px;"><input type="radio"  name="message" value="2">我关注的人、我发过消息的人</label>
                </li>               
            </ul>
            <input class="submit_but" type="button" name="" value="保存" style="">            
        </div>

        <div id="profile" class="Detail" style="display: none;">
            <ul>
                <li>
                    <label for="name">用户名：</label>
                    <input style="margin-left: 50px;" type="text"  placeholder="崔萍萍" required /> 
                </li>
                <li>
                    <label for="name">性别：</label>
                    <label style="margin-left: 65px;"><input type="radio"  name="sex" value="1">男</label>
                    <label style="margin-left: 10px;"><input type="radio"  name="sex" value="2">女</label>
                    <label style="margin-left: 10px;"><input type="radio"  name="sex" value="3">保密</label>
                </li>
                <li>
                    <label for="name">个人简介：</label>
                    <textarea style="margin-left: 30px;" cols="50" rows="2"></textarea>
                </li>
                <li>
                    <label for="name">个人网站：</label>
                    <input style="margin-left: 30px;width: 300px;" type="text"  placeholder="www.facebook.com/cuipingping" required />
                </li>
            </ul>
            <input class="submit_but" type="button" name="" value="保存" style=""> 
        </div>
    </div>
        <div id="integral" style="position: relative;top: -250px;left: 700px;display: none;">
            <table class="table table-striped" style="width: 500px;height: 300px;">
                <thead>    
                <tr>
                    <th>类别</th>
                    <th>我的主用户组-LV1</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>用户级别</td>
                        <td>1级</td>
                    </tr>
                    <tr>
                        <td>访问论坛</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>阅读权限</td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <td>自动搜索</td>
                        <td>允许搜索帖子内容</td>
                    </tr>
                    <tr>
                        <td>自定义头衔</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>允许加好友</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>查看统计数据报表</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>允许使用应用</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>发新话题</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>发表回复</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>发表活动</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>发表交易</td>
                        <td><input type="checkbox"></td>
                    </tr>
                    <tr>
                        <td>发表评论</td>
                        <td><input type="checkbox"></td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div id="grade" style="position: relative;top: -250px;left: 700px; display: none;">
            <div>
                <h3>
                   我的积分 
                </h3>
                <p class="pstyle">金币:  4</p>                                 
                <p class="pstyle">威望： 0 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贡献： 0</p>                               
                <p class="pstyle" style="border-bottom-style: dotted;padding-bottom: 20px; width: 400px;">积分： 4</p>                                    
                <p class="pstyle">积分记录</p>               
            </div>
        </div>

<script type="text/javascript">               
    $(function(){
        $("#PartA").click(function(){
            $("#basic").css("display","inherit");
            $("#profile,#integral,#grade").css("display","none");
            $(this).css("background","#0099FF");
            $("#PartB,#PartC,#PartD").css("background","");            
        })
    })
    $(function(){
        $("#PartB").click(function(){
            $("#profile").css("display","inherit");
            $("#basic,#integral,#grade").css("display","none");
            $(this).css("background","#0099FF");
            $("#PartA,#PartC,#PartD").css("background","");
        })
    })
    $(function(){
        $("#PartC").click(function(){
            $("#integral").css("display","inherit");
            $("#basic,#profile,#grade").css("display","none");
            $(this).css("background","#0099FF");
            $("#PartA,#PartB,#PartD").css("background","");
        })
    })
    $(function(){
        $("#PartD").click(function(){
            $("#grade").css("display","inherit");
            $("#basic,#profile,#integral").css("display","none");
            $(this).css("background","#0099FF");
            $("#PartA,#PartB,#PartC").css("background","");
        })
    })
       
</script> 


</body>
</html>