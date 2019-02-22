<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
	<li id="back"><a href="${pageContext.request.contextPath}/api/factory/find_page">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		浏览生产厂家信息
    </div> 
    </div>
    </div>
<div>
	<div class="modelDiv">
		<table class="modelTable" cellspacing="1">
			<tr>
				<td class="columnTitle"><h6>厂家名称：</h6></td>
				<td class="tableContent"><h6 style="color: #218c7e">${factory.full_name}</h6></td>
			</tr>
			<tr>
				<td class="columnTitle"><h6>简称：</h6></td>
				<td class="tableContent"><h6 style="color: #218c7e">${factory.factory_name}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>联系人：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.contacts}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>电话：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.phone}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>手机：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.mobile}</h6></td>

			</tr>
			<tr>
				<td class="model_intro_left"><h6>传真：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.fax}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>验货员：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.inspector}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>排序号：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.order_no}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>创建人：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.order_by}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>创建部门：</h6></td>
				<td class="model_intro_right"><h6 style="color: #218c7e">${factory.create_dept}</h6></td>
			</tr>
			<tr>
				<td class="model_intro_left"><h6>备注：</h6></td>
				<td class="model_intro_right"><pre><h6 style="color: #218c7e">${factory.cnote}</h6></pre></td>
			</tr>
		</table>
	</div>
</div>
</form>
</body>
</html>

