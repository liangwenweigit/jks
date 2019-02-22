<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
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
    <li id="new"><a href="${pageContext.request.contextPath}/api/factory/insert_page">新增</a></li>
    <li id="update"><a href="#" onclick="formSubmit('${pageContext.request.contextPath}/api/factory/update_page','_self');this.blur();">修改</a></li>
    <li id="delete"><a href="#" onclick="formSubmitDelete('${pageContext.request.contextPath}/api/factory/delete','_self');this.blur();">删除</a></li>
	<li id="new"><a href="#" onclick="formSubmitStart('${pageContext.request.contextPath}/api/factory/start','_self');this.blur();">启用</a></li>
	<li id="new"><a href="#" onclick="formSubmitStop('${pageContext.request.contextPath}/api/factory/stop','_self');this.blur();">停用</a></li>
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
		<td class="tableHeader">状态</td>
		<td class="tableHeader">其他操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >

	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="factory_id" value="${o.factory_id}"/></td>
		<td>${status.index+1}</td>
		<td><a href="${pageContext.request.contextPath}/api/factory/showview?factory_id=${o.factory_id}">${o.full_name}</a></td>
		<td>${o.factory_name}</td>
		<td>${o.contacts}</td>
		<td>${o.phone}</td>
		<td>${o.mobile}</td>
		<td>${o.fax}</td>
		<td>${o.cnote}</td>
		<td>
				<a href="${pageContext.request.contextPath}/api/factory/update_state?factory_id=${o.factory_id}">
					<c:if test="${o.state eq 1}"><span style="color:green;">启用</span></c:if>
			        <c:if test="${o.state eq 0}"><span>停用</span></c:if>
				</a>
		</td>
        <td><a href='${pageContext.request.contextPath}/api/factory/showview?factory_id=${o.factory_id}'style="text-decoration: underline" ><p style="margin:0 3px;width:70px;height:25px;color:#00554a;padding-left:26px;padding-top:5px; letter-spacing:1.2px;background:url('${pageContext.request.contextPath}/skin/default/images/button/view.gif') no-repeat;">查看</p></a></td>
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
    <a href="${pageContext.request.contextPath}/api/factory/find_page?pageNo=1">首页</a>
    <c:if test="${page.pageNo>1}">
        <a href="${pageContext.request.contextPath}/api/factory/find_page?pageNo=${page.pageNo-1}">上一页</a>
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
                <a style='border:#eee 1px solid;padding:2px 5px;margin:2px;color:#ddd;' href='javascrip:void(0)'>${i}</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/api/factory/find_page?pageNo=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${page.pageNo<page.totalPage}">
        <a href="${pageContext.request.contextPath}/api/factory/find_page?pageNo=${page.pageNo+1}">下一页</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/api/factory/find_page?pageNo=${page.totalPage}">尾页</a>
    </div>
</center>
</body>
</html>

