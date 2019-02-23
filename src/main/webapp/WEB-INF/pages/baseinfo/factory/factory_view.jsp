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
				<td class="columnTitle"><span>厂家名称：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.full_name}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>简称：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.factory_name}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>联系人：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.contacts}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>电话：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.phone}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>手机：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.mobile}</span></td>

			</tr>
			<tr>
				<td class="columnTitle"><span>传真：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.fax}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>验货员：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.inspector}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>排序号：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.order_no}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>创建人：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.order_by}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>创建部门：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${factory.create_dept}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>备注：</span></td>
				<td class="tableContent"><pre style="color: #218c7e">${factory.cnote}</pre></td>
			</tr>
		</table>
	</div>
</div>
</form>
</body>
</html>

