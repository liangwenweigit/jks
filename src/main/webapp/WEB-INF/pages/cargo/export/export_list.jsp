<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages.css" />
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
    <li id="new"><a href="${pageContext.request.contextPath}/api/contract/find_page?state=1">新增</a></li>
    <li id="update"><a href="#" onclick="formSubmit('${pageContext.request.contextPath}/api/export/update_page','_self');this.blur();">修改</a></li>
    <li id="delete"><a href="#" onclick="formSubmitDelete('${pageContext.request.contextPath}/api/export/delete','_self');this.blur();">删除</a></li>
	<li id="new"><a href="#" onclick="formSubmitexportStartState('${pageContext.request.contextPath}/api/export/start','_self');this.blur();">取消</a></li>
	<li id="new"><a href="#" onclick="formSubmitexportStopState('${pageContext.request.contextPath}/api/export/stop','_self');this.blur();">上报</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<!-- 页面主体部分（列表等）-->
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    出口报运列表
  </div> 
  </div>
  </div>
<div>
<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('export_id',this)"></td>
        <td class="tableHeader">序号</td>
        <td class="tableHeader">合同或确认书号</td>
        <td class="tableHeader">信用证号</td>
        <td class="tableHeader">收货人及地址</td>
        <td class="tableHeader">装运港</td>
        <td class="tableHeader">目的港</td>
        <td class="tableHeader">运输方式</td>
        <td class="tableHeader">价格条件</td>
        <td class="tableHeader">制单日期</td>
        <td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >

	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="export_id" value="${o.export_id}"/></td>
        <td>${status.index+1}</td>
		<td><a href="${pageContext.request.contextPath}/api/export/showview?export_id=${o.export_id}">${o.customer_contract}</a></td>
		<td>${o.lcno}</td>
		<td>${o.consignee}</td>
		<td>${o.shipment_port}</td>
        <td>${o.destination_port}</td>
        <td>${o.transport_mode}</td>
        <td>${o.price_condition}</td>
        <td><fmt:formatDate value="${o.input_date}" pattern="yyyy-MM-dd"/></td>
		<td>
			<c:if test="${o.state eq 0}"><span style="color:green;">上报</span></c:if>
			<c:if test="${o.state eq 1}"><span style="color:green;">已上报</span></c:if>
            <c:if test="${o.state eq 2}"><span style="color:green;">装箱</span></c:if>
            <c:if test="${o.state eq 3}"><span style="color:green;">委托</span></c:if>
            <c:if test="${o.state eq 4}"><span style="color:green;">发票</span></c:if>
            <c:if test="${o.state eq 5}"><span style="color:green;">财务</span></c:if>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>

</div>
</div>
</form>
<center>
    <div class="digg">
    第${page.pageNo}页/共${page.totalPage}页
    <a href="${pageContext.request.contextPath}/api/export/find_page?pageNo=1">首页</a>
    <c:if test="${page.pageNo>1}">
        <a href="${pageContext.request.contextPath}/api/export/find_page?pageNo=${page.pageNo-1}">上一页</a>
    </c:if>
    <c:choose>
        <%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
        <c:when test="${page.totalPage<=10}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${page.totalPage}"/>
        </c:when>
        <%-- 当总页数>10时，通过公式计算出begin和end --%>
        <c:otherwise>
            <c:set var="begin" value="${page.pageNo-5}"/>
            <c:set var="end" value="${page.pageNo+4}"/>

            <c:if test="${begin<1}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="10"/>
            </c:if>
            <c:if test="${end>page.totalPage}">
                <c:set var="begin" value="${page.totalPage-9}"/>
                <c:set var="end" value="${page.totalPage}"/>
            </c:if>
        </c:otherwise>
    </c:choose>
    <!-- 循环显示 -->
    <c:forEach var="i" begin="${begin}" end="${end}">
        <c:choose>
            <c:when test="${i eq page.pageNo}">
                <a style='border:#eee 1px solid;padding:2px 5px;margin:2px;color:#ddd;' href='javascrip:void(0);'>${i}</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/api/export/find_page?pageNo=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${page.pageNo<page.totalPage}">
        <a href="${pageContext.request.contextPath}/api/export/find_page?pageNo=${page.pageNo+1}">下一页</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/api/export/find_page?pageNo=${page.totalPage}">尾页</a>
    </div>
</center>
</body>
</html>

