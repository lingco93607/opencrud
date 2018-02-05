    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- navigation -->
    <nav class="navbar navbar-default">
    <div class="container-fluid" style="margin-left: 20%;">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">首页</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">渣渣</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="../home/HomePage.jsp">首页 <span class="sr-only">(current)</span></a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">提问 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">Separated link</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#">One more separated link</a></li>
            </ul>
          </li>
          <li><a href="#">答题</a></li>
          <li><a href="../article/writeArticle.jsp">发帖</a></li>
        </ul>
        <form class="navbar-form navbar-left">
          <div class="form-group">
            <div class="input-group"> 
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span> 
            </div>
          </div>
          <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <!-- 消息提示功能 -->
          <li>
            <a href="#"> 
              <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
            </a>
          </li>
          
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">梦与我123 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="../home/HomePage.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>我的主页</a></li>
              <li><a href="../zuser/settings.jsp"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>设置</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="#"><span class="glyphicon glyphicon-off" aria-hidden="true">退出</a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
  <!-- navigation end-->