<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String urlPath = request.getContextPath();
	String urlBasePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ urlPath;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>users manager</title>
<%@include file="../common/include.jsp"%>
<script type="text/javascript"
	src="javascript/book/book.js"></script>
<script type="text/javascript" 
    src="javascript/common/comBox.js"></script>
<script type="text/javascript">
var path = "<%=urlBasePath%>";
</script>
</head>
<body>
<div id="searchbooks">
	<form id="searchform" style="margin-left:0%;">
        <a>用户名称:</a>
		<input type="text" id="searchBookName" class="searchplay" name="userName"/>
		<input type="button" id="searchbutton" value="查询"/>
        <input type="reset" id="searchreset" value="重置"/>
	</form>
</div>
    <!--加载Grid的DIV-->
	<div id="bookManagerGrid" class="yahei"></div>
	<!--end-->
	<!--新增修改页面的弹窗-->
	<div id="addUpdateWindowContainer" style="display: none;" class="yahei">
		<div id="addUpdateManagerWindow">
			<div id="addUpdateManagerWindowHeader">
				<span class="panel_head" id="addUpdateCaptureContainer" style="float: left"></span>
			</div>
			<div id="addUpdateManagerWindowContent" style="overflow: hidden;">
			   <form id="book_info_form" action="action/book/savebook"
			enctype='multipart/form-data' method="post" style="font-family:microsoft yahei">
			<table>
				<tr>
<td align='right'><b style='color:red;'></b>name:</td>
<td align='right'><input id="name" name="name" class="rolestext" type="text" /></td></tr><tr>
<td align='right'><b style='color:red;'></b>id:</td>
<td align='right'><input id="id" name="id" class="rolestext" type="text" /></td></tr>
				
				</table>
				<div align="center">
					   <input type='button' value='保存' 	id='savebutton'/>
					   <input type='reset' 	value='重置' id='resetbutton' style='margin-left:12px;' /> 
					   <input type='button' value='关闭' id='closebutton' style='margin-left:12px;' />
			    </div>
		</form>
			</div>
		</div>
	</div>

	
	<!--绑定业务子系统-->
<!-- 	<div id="bindSubSysWindowContainer" style="display: none;" class="yahei"> -->
<!-- 		<div id="bindSubSysManagerWindow"> -->
<!-- 			<div id="bindSubSysManagerWindowHeader"> -->
<!-- 				<span id="bindSubSysCaptureContainer" style="float: left"></span>  -->
<!-- 			</div> -->
<!-- 			<div id="bindSubSysJqxWidget" style="overflow: hidden;overflow:scroll;" > -->
			
<!-- 					<table id="headSubSystem"> -->
<!-- 					      <tr> -->
<!-- 					        <td>用户名称:</td> -->
<!-- 							<td> -->
<%-- 							  <input type="text" id="systemUserName" name="userName" value="${params.name}" style="border:0px;color:grey;" readonly="readonly" /> --%>
<%-- 							  <input type="hidden" id="systemUserId" name="userId" value="${params.id}"/> --%>
<!-- 						   </td> -->
<!-- 					    </tr> -->
<!-- 						<tr id="addUserMapping"> -->
<!-- 						    <td>添加用户</td> -->
<!-- 						    <td> -->
<!-- 						       <img src="./images/add.png"/> -->
<!-- 						    </td> -->
<!-- 					    </tr> -->
<!-- 					</table> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<!--end-->
</body>
</html>
