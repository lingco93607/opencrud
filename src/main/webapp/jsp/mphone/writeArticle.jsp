<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>渣渣论坛</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <!-- 引入jQuery -->
    <script src="../../js/jquery-3.0.0.min.js"></script>

    <script src="../../js/webuploader.min.js"></script>
    <!-- 插件核心 -->
    <script src="../../js/Eleditor.js"></script>


  <style>
    *{
      margin: 0;
      padding: 0;
    }
    body {
        -webkit-touch-callout: none;
        background-color: #fff;
        line-height: inherit;
        padding-top: 30px;
    }
    #contentEditor{
      width: 100%;
      min-height: 300px;
      box-sizing: border-box;
      padding: 10px;
        color: #444;
    }
    #contentEditor p{
        letter-spacing: 0.25px;
        font: 16px/25px Tahoma, Verdana, 宋体;
        margin: 20px 0px;
    }
    #contentEditor h4 {
        font-weight: bold;
        line-height: 1.333em;
        margin: 10px 0 20px;
        padding: 25px 0 0;
    }
    #contentEditor img{
      width: 100%;
      height: auto;
      box-sizing: border-box;
    }
    .dempTip{
        border-left: 2px solid #00BCD4;
        padding-left: 5px;
        margin: 10px;
        font-size: 16px;
    }
    .viewTit{
        color: #FF5722;
        position: fixed;
        top: 0;
        left: 0;
        height: 30px;
        line-height: 30px;
        font-size: 14px;
        text-align: center;
        display: block;
        width: 100%;
        background: #FFEB3B;
        box-shadow: 0 0 5px;
    }

    #title {
        border: 1px solid #CCC;
        color: #888;
        height: 20px;
        line-height:15px;
        margin-bottom: 2px;
        margin-right: 6px;
        margin-top: 12px;
        outline: 0 none;
        padding: 5px 0px 5px 5px;
        width: 80%;
        border-radius: 4px;
        -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        font-size:18px;
    }
  </style>
</head>
<body>

<font class="viewTit">（此编辑器仅适用移动端，chrome请按F12模拟手机设备进行浏览）</font>
<script>
  var ua = navigator.userAgent.toLowerCase(); ;

  if( ua.indexOf('android') >= 0 || ua.indexOf('iphone') >= 0 || ua.indexOf('ipad') >= 0 || $(window).width() <= 500 ){
    $('.viewTit').hide();
    $('body').css('padding-top', 0);
  }
</script>

<p style="text-align: center;"><input type="text" id="title" placeholder="请输入标题"></p>
<div id="contentEditor">

</div>

  <div class="Eleditor-method">              
    <button class="Eleditor-commit" id="submit" >发表</button>             
    <button class="Eleditor-cancel" onClick="javascript:history.back(-1);">取消</button>           
  </div>

<script>
    var contentEditor = new Eleditor({
        el: '#contentEditor',
        upload:{
          server: '../../action/article/saveArticleImg',
          compress: false,
          fileSizeLimit: 2
        },
        toolbars: [
                'insertText',
                'editText',
                'insertImage',
                'insertLink',                 
                'insertHr',
                'deleteThis',

                //自定义一个按钮 'deleteBefore', 'deleteAfter',
                {
                    id: 'changeIndent',
                    tag: 'p,img', //指定P标签操作，可不填
                    name: '缩进',
                    handle: function(select, controll){//回调返回选择的dom对象和控制按钮对象
                      var _$ele = $(select),
                        _$controll = $(controll);
                      _$controll.html(_$ele.css('text-indent') != '0px' ? '缩进' : '还原缩进');
                      _$ele.css('text-indent', _$ele.css('text-indent') == '0px' ? '5em' : '0px');
                    }
                },

                //自定义按钮，该按钮只有在编辑IMG标签时才会显示
                {
                    id: 'rotateImage',
                    tag: 'img', //指定IMG标签操作，可不填
                    name: '反转图片',
                    handle: function(select, controll){
                      var _$ele = $(select),
                        _$controll = $(controll);
                      if( _$ele.attr('transform-rotate') != '180deg' ){
                        _$controll.html('还原图片');
                          _$ele.attr('transform-rotate', '180deg').css('transform', 'rotate(180deg)');
                      }else{
                        _$controll.html('反转图片');
                        _$ele.removeAttr('transform-rotate').css('transform', 'rotate(0)');
                      }
                    }
                  },

                  'cancel'
        ]
        //placeHolder: 'placeHolder设置占位符'
      });

    $("#submit").click(function(){
        var title = $.trim($("#title").val());
        var text = $.trim($("#contentEditor").html());
        if( title != "" && text != "" && text != '<p class="Eleditor-placeholder Eleditor-active">点击此处编辑内容</p>' ) {
            if (text.indexOf('点击此处编辑内容') > 0) {
              alert("需要编辑一下内容");
            } else {
              $.ajax({
                url:'../../action/article/writeArticle?userUid=1&title='+ title +'&text=' + text,
                async:false,
                type:'POST',
                success:function(data, XMLHttpRequest, textStatus){
                  //0-发送成功；1-魅力值已大上限；2-体力值已扣完；3-其他异常
                    if(200 == textStatus.status) {
                      if (data == 0) {
                        window.location.href='../../jsp/mphone/forumPhone.jsp';
                      } else if (data == 1) {
                        alert("温馨提示,你的魅力值今日已经达上限不再增长了。但可以继续发帖");
                        window.location.href='../../jsp/mphone/forumPhone.jsp';
                      } else if (data == 2) {
                        //  alert（警告） 对话框
                        //    $scope.ionicPopupAlert("提示", "温馨提示,你体力值不够了,您稍后再发吧！");
                        alert("温馨提示,你体力值不够了,您稍后再发吧！");
                      } else {
                        alert("服务器异常！稍后再试！");
                        //$scope.ionicPopupAlert("错误", "服务器异常！稍后再试！");
                      }
                    }
                },
                error:function(xhr,textStatus){
                   console.log('错误');
                   console.log(xhr);
                   console.log(textStatus);
                }
            });
          }
            
      } else {
        alert("标题/内容不能为空！")
      }
    })

            // $http({
            //   method: 'POST',
            //   url: '../../action/article/writeArticle?userUid=1&title='+ title +'&text=' + text
            // }).then(function successCallback(response) {
            //   if(200 == response.status) {
            //     //0-发送成功；1-魅力值已大上限；2-体力值已扣完；3-其他异常
            //     let status = response.data;
            //     if (status == 0) {
            //       window.location.href='../../jsp/mphone/forumPhone.jsp';
            //     } else if (status == 1) {
            //       $scope.ionicPopupAlert("提示", "温馨提示,你的魅力值今日已经达上限不再增长了。但可以继续发帖");
            //       window.location.href='../../jsp/mphone/forumPhone.jsp';
            //     } else if (status == 2) {
            //       //  alert（警告） 对话框
            //           $scope.ionicPopupAlert("提示", "温馨提示,你体力值不够了,您稍后再发吧！");
            //     } else {
            //       $scope.ionicPopupAlert("错误", "服务器异常！稍后再试！");
            //     }
            //   } 
            // }, function errorCallback(response) {
            //   // 请求失败执行代码
            // });
            //   } else {
            //     $scope.ionicPopupAlert("提醒", "标题/内容不能为空！");
            //   }
            // });


</script>

</body>
</html>