<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		浏览合同信息
    </div> 
    </div>
    </div>
<div>
	<div class="modelDiv">
		<table class="commonTable" cellspacing="1">
			<tr>
				<td class="columnTitle"><span>客户名称：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${contract.customer_name}</span></td>
				<td class="columnTitle"><span>收购方：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${contract.offeror}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>合同号：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${contract.contract_no}</span></td>
				<td class="columnTitle"><span>打印版式：</span></td>
				<td class="tableContentAuto">
					<span style="color: #218c7e">
					<c:if test="${contract.print_style=='2'}">两款</c:if>
					<c:if test="${contract.print_style=='1'}">一款</c:if>
					</span>
				</td>
			</tr>
			<tr>
				<td class="columnTitle"><span>签单日期：</span></td>
				<td class="tableContent"><span style="color: #218c7e"><fmt:formatDate value="${contract.signing_date}" pattern="yyyy-MM-dd"/></span></td>
				<td class="columnTitle"><span>重要程度：</span></td>
				<td class="tableContentAuto">
					<span style="color: #218c7e">
					<c:if test="${contract.import_num==3}">★★★</c:if>
					<c:if test="${contract.import_num==2}">★★</c:if>
					<c:if test="${contract.import_num==1}">★</c:if>
					</span>
				</td>
			</tr>
			<tr>
				<td class="columnTitle"><span>交货期限：</span></td>
				<td class="tableContent"><span style="color: #218c7e"><fmt:formatDate value="${contract.delivery_date}" pattern="yyyy-MM-dd"/></span></td>
				<td class="columnTitle"><span>船期：</span></td>
				<td class="tableContent"><span style="color: #218c7e"><fmt:formatDate value="${contract.ship_date}" pattern="yyyy-MM-dd"/></span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>贸易条款：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${contract.trade_clause}</span></td>
				<td class="columnTitle"><span>验货员：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${contract.inspector}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>制单人：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${contract.input_by}</span></td>
				<td class="columnTitle"><span>审单人：</span></td>
				<td class="tableContent"><span style="color: #218c7e">${contract.check_by}</span></td>
			</tr>
			<tr>
				<td class="columnTitle"><span>要求：</span></td>
				<td class="tableContent"><pre style="color: #218c7e">${contract.crequest}</pre></td>
				<td class="columnTitle"><span>说明：</span></td>
				<td class="tableContent"><pre style="color: #218c7e">${contract.instructions}</pre></td>
			</tr>
		</table>
	</div>
</div>
</form>
</body>
</html>

