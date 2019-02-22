<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form id="insertForm" method="post" action="${pageContext.request.contextPath}/api/contract/insert">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="commint();">确定</a></li>
<li id="back"><a href="${pageContext.request.contextPath}/api/contract/find_page">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		新增合同
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
            <tr>
                <td class="columnTitle_mustbe">厂家名称：</td>
                <td class="tableContent"><input type="text" name="full_name"/></td>
                <td class="columnTitle_mustbe">简称：</td>
                <td class="tableContent"><input type="text" name="contract_name"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">联系人：</td>
                <td class="tableContent"><input type="text" name="contacts"/></td>
                <td class="columnTitle_mustbe">电话：</td>
                <td class="tableContent"><input type="text" name="phone"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">手机：</td>
                <td class="tableContent"><input type="text" name="mobile"/></td>
                <td class="columnTitle_mustbe">传真：</td>
                <td class="tableContent"><input type="text" name="fax"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">验货员：</td>
                <td class="tableContent"><input type="text" name="inspector"/></td>
                <td class="columnTitle_mustbe">排序号：</td>
                <td class="tableContent"><input type="text" maxlength="9" onkeyup="value=value.replace(/[^\d]/g,'')" name="order_no"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">备注：</td>
                <td class="tableContent"><textarea name="cnote" style="height:120px;"></textarea></td>
            </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

