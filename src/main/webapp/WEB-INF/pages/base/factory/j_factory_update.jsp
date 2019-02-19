<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/api/factory/update">
	<input type="hidden" name="factory_id" value="${factory.factory_id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="updateSubmit();">确定</a></li>
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
		修改生成厂家信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
			<tr>
				<td class="columnTitle_mustbe">厂家名称：</td>
				<td class="tableContent"><input type="text" name="full_name" value="${factory.full_name}"/></td>
				<td class="columnTitle_mustbe">简称：</td>
				<td class="tableContent"><input type="text" name="factory_name" value="${factory.full_name}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">联系人：</td>
				<td class="tableContent"><input type="text" name="contacts" value="${factory.contacts}"/></td>
				<td class="columnTitle_mustbe">电话：</td>
				<td class="tableContent"><input type="text" name="phone" value="${factory.phone}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">手机：</td>
				<td class="tableContent"><input type="text" name="mobile" value="${factory.mobile}"/></td>
				<td class="columnTitle_mustbe">传真：</td>
				<td class="tableContent"><input type="text" name="fax" value="${factory.fax}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">验货员：</td>
				<td class="tableContent"><input type="text" name="inspector" value="${factory.inspector}"/></td>
				<td class="columnTitle_mustbe">排序号：</td>
				<td class="tableContent"><input type="text" name="order_no" value="${factory.order_no}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">备注：</td>
				<td class="tableContent"><textarea name="cnote" style="height:120px;">${factory.cnote}</textarea></td>
			</tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

