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
    <div>
		<h3>
			<table class="commonTable" cellspacing="1">
			<tr>
				<td class="columnTitle">厂家名称：</td>
				<td class="tableContent">${factory.full_name}</td>
			</tr>
			<tr>
				<td class="columnTitle">简称：</td>
				<td class="tableContent">${factory.factory_name}</td>
			</tr>
			<tr>
				<td class="columnTitle">联系人：</td>
				<td class="tableContent">${factory.contacts}</td>

			</tr>
			<tr>
				<td class="columnTitle">电话：</td>
				<td class="tableContent">${factory.phone}</td>
			</tr>
			<tr>
				<td class="columnTitle">手机：</td>
				<td class="tableContent">${factory.mobile}</td>

			</tr>
			<tr>
				<td class="columnTitle">传真：</td>
				<td class="tableContent">${factory.fax}</td>
			</tr>
			<tr>
				<td class="columnTitle">验货员：</td>
				<td class="tableContent">${factory.inspector}</td>
			</tr>
				<tr>
					<td class="columnTitle">排序号：</td>
					<td class="tableContent">${factory.order_no}</td>
				</tr>
			<tr>
				<td class="columnTitle">创建人：</td>
				<td class="tableContent">${factory.order_by}</td>
			</tr>
				<tr>
					<td class="columnTitle">创建部门：</td>
					<td class="tableContent">${factory.create_dept}</td>
				</tr>
			<tr>
				<td class="columnTitle">备注：</td>
				<td class="tableContent"><pre>${factory.cnote}</pre></td>
			</tr>
		    </table>
		</h3>
	</div>
</div>
 
 
</form>
</body>
</html>

