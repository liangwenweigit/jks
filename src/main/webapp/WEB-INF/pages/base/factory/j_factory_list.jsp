<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="new"><a href="${pageContext.request.contextPath}/api/factory/insert_page">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('${pageContext.request.contextPath}/api/factory/update_page','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmitDelete('${pageContext.request.contextPath}/api/factory/delete','_self');this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<!-- 页面主体部分（列表等） -->  
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    生产厂家列表
  </div> 
  </div>
  </div>
<div>
<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('factory_id',this)"></td>
		<td class="tableHeader">编号</td>
		<td class="tableHeader">厂家全称</td>
		<td class="tableHeader">简称</td>
		<td class="tableHeader">联系人</td>
		<td class="tableHeader">电话</td>
		<td class="tableHeader">手机</td>
		<td class="tableHeader">传真</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">其他操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >

	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="factory_id" value="${o.factory_id}"/></td>
		<td>${status.index+1}</td>
		<td><a href="toview.action?id=${o.factory_id}">${o.full_name}</a></td>
		<td>${o.factory_name}</td>
		<td>${o.contacts}</td>
		<td>${o.phone}</td>
		<td>${o.mobile}</td>
		<td>${o.fax}</td>
		<td>${o.cnote}</td>
		<td><a href='${pageContext.request.contextPath}/api/factory/showview?factory_id=${o.factory_id}'><li style="margin:0 3px;width:70px;height:25px;color:#00554a;padding-left:7px;padding-top:5px; letter-spacing:1.2px;background:url('${pageContext.request.contextPath}/skin/default/images/button/view.gif') no-repeat;">查看</li></a></td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
</form>
</body>
</html>

