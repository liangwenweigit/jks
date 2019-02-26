<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/api/extcproduct/update">
	<input type="hidden" name="contract_product_id" value="${cxtCproduct.contract_product_id}">
	<input type="hidden" name="ext_cproduct_id" value="${cxtCproduct.ext_cproduct_id}">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="updateSubmit();">确定</a></li>
	<li id="back"><a href="${pageContext.request.contextPath}/api/extcproduct/list?contract_product_id=${cxtCproduct.contract_product_id}">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改货物的附件信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
			<tr>
				<td class="columnTitle_mustbe">厂家名称：</td>
				<td class="tableContent">
					<select name="factory_id">
						<%--这里直接把厂家id传过去，然后在里面再查一次厂家，把厂家名称 UUID封装到 货物实体--%>
						<c:forEach items="${factoryList}" var="f">
							<option value="${f.factory_id}" <c:if test="${f.factory_id eq cxtCproduct.factory_id}">selected</c:if>>${f.factory_name}</option>
						</c:forEach>
					</select>
				</td>
				<td class="columnTitle_mustbe">货物名称：</td>
				<td class="tableContent"><input type="text" name="product_name" value="${cxtCproduct.product_name}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">货物编号：</td>
				<td class="tableContent"><input type="text" name="product_num" value="${cxtCproduct.product_num}"/></td>
				<td class="columnTitle_mustbe">货物照片：</td>
				<td class="tableContent"><input type="text" name="product_image" value="${cxtCproduct.product_image}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">货物数量：</td>
				<td class="tableContent"><input maxlength="9" onkeyup="value=value.replace(/[^\d]/g,'')" type="text" name="cnumber" value="${cxtCproduct.cnumber}"/></td>
				<td class="columnTitle_mustbe">包装单位：</td>
				<td class="tableContent">
					<select name="packing_unit">
						<option value="PCS" <c:if test="${'PCS' eq cxtCproduct.packing_unit}">selected</c:if>>--PCS--</option>
						<option value="SETS" <c:if test="${'SETS' eq cxtCproduct.packing_unit}">selected</c:if> >--SETS--</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">装率：</td>
				<td class="tableContent"><input type="text" name="loading_rate" value="${cxtCproduct.loading_rate}"/></td>
				<td class="columnTitle_mustbe">件数：</td>
				<td class="tableContent"><input maxlength="9" onkeyup="value=value.replace(/[^\d]/g,'')" type="text" name="box_num" value="${cxtCproduct.box_num}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">单价：</td>
				<td class="tableContent"><input type = "text" name= "price" value="${cxtCproduct.price}" maxlength="9" onkeyup="if( ! /^\d{0,8}(\.\d{0,2})?$/g.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='';}" /></td>
				<td class="columnTitle_mustbe">排序号：</td>
				<td class="tableContent"><input type="text" maxlength="9" onkeyup="value=value.replace(/[^\d]/g,'')" name="order_no" value="${cxtCproduct.order_no}"/></td>
			</tr>
			<tr>
				<td class="columnTitle_mustbe">货物描述：</td>
				<td class="tableContent"><textarea name="product_desc" style="height:120px;">${cxtCproduct.product_desc}</textarea></td>
			</tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

