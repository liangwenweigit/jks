<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/clock/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/clock/jquery.datepick.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/clock/jquery.datepick.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/clock/jquery.datepick-zh-CN.js" charset="UTF-8"></script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/api/contract/update">
	<input type="hidden" name="contract_id" value="${contract.contract_id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="updateSubmit();">确定</a></li>
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
		修改合同信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
			<table class="commonTable" cellspacing="1">
				<tr>
					<td class="columnTitle_mustbe">收购方：</td>
					<td class="tableContent"><input type="text" name="offeror" value="${contract.offeror}"/></td>
					<td class="columnTitle_mustbe">客户名称：</td>
					<td class="tableContent"><input type="text" name="customer_name" value="${contract.customer_name}"/></td>
				</tr>
				<tr>
					<td class="columnTitle_mustbe">合同号：</td>
					<td class="tableContent"><input type="text" name="contract_no" value="${contract.contract_no}"/></td>
					<td class="columnTitle_mustbe">打印版式：</td>
					<td class="tableContentAuto">
						<input type="radio" name="print_style" value="2" class="input" <c:if test="${contract.print_style=='2'}">checked</c:if>>两款
						<input type="radio" name="print_style" value="1" class="input" <c:if test="${contract.print_style=='1'}">checked</c:if>>一款
					</td>
				</tr>
				<tr>
					<td class="columnTitle_mustbe">签单日期：</td>
					<td class="tableContent">
						<input type="text" style="width:90px;" name="signing_date" id="signing_date" readonly="readonly" value="<fmt:formatDate value="${contract.signing_date}" pattern="yyyy-MM-dd"/>"/>
					</td>
					<td class="columnTitle_mustbe">重要程度：</td>
					<td class="tableContentAuto">
						<input type="radio" name="import_num" value="3" class="input" <c:if test="${contract.import_num==3}">checked</c:if>>★★★
						<input type="radio" name="import_num" value="2" class="input" <c:if test="${contract.import_num==2}">checked</c:if>>★★
						<input type="radio" name="import_num" value="1" class="input" <c:if test="${contract.import_num==1}">checked</c:if>>★
					</td>
				</tr>
				<tr>
					<td class="columnTitle_mustbe">交货期限：</td>
					<td class="tableContent">
						<input type="text" style="width:90px;" name="delivery_date" id="delivery_date" readonly="readonly" value="<fmt:formatDate value="${contract.delivery_date}" pattern="yyyy-MM-dd"/>"/>
					</td>
					<td class="columnTitle_mustbe">船期：</td>
					<td class="tableContent">
						<input type="text" style="width:90px;" name="ship_date" id="ship_date" readonly="readonly" value="<fmt:formatDate value="${contract.ship_date}" pattern="yyyy-MM-dd"/>"/>
					</td>
				</tr>
				<tr>
					<td class="columnTitle_mustbe">贸易条款：</td>
					<td class="tableContent"><input type="text" name="trade_clause" value="${contract.trade_clause}"/></td>
					<td class="columnTitle_mustbe">验货员：</td>
					<td class="tableContent"><input type="text" name="inspector" value="${contract.inspector}"/></td>
				</tr>
				<tr>
					<td class="columnTitle_mustbe">制单人：</td>
					<td class="tableContent"><input type="text" name="input_by" value="${contract.input_by}"/></td>
					<td class="columnTitle_mustbe">审单人：</td>
					<td class="tableContent"><input type="text" name="check_by" value="${contract.check_by}"/></td>
				</tr>
				<tr>
					<td class="columnTitle_mustbe">要求：</td>
					<td class="tableContent"><textarea name="crequest" style="height:120px;">${contract.crequest}</textarea></td>
					<td class="columnTitle_mustbe">说明：</td>
					<td class="tableContent"><textarea name="instructions" style="height:120px;">${contract.instructions}</textarea></td>
				</tr>
			</table>
		</table>
	</div>
</div>
 
 
</form>
<script>
    $(function() {
        $("#signing_date").datepick({dateFormat:"yy-mm-dd"});
        $("#delivery_date").datepick({dateFormat:"yy-mm-dd"});
        $("#ship_date").datepick({dateFormat:"yy-mm-dd"});
    });
</script>
</body>
</html>

