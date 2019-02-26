<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages.css" />
</head>
<body>

<div id="menubar">
    <div id="middleMenubar">
        <div id="innerMenubar">
            <div id="navMenubar">
                <ul>
                    <li id="new"><a href="${pageContext.request.contextPath}/api/extcproduct/insert_page?contract_product_id=${contract_product_id}">新增</a></li>
                    <li id="new"><a href="${pageContext.request.contextPath}/api/contract/find_page">返回</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="textbox" id="centerTextbox">
    <div class="textbox-header">
        <div class="textbox-inner-header">
            <div class="textbox-title">
                货物附件列表
            </div>
        </div>
    </div>
</div>
<div>
    <div class="eXtremeTable" >
        <table id="ec_table" class="tableRegion" width="98%" >
            <thead>
            <tr>
                <td class="tableHeader">序号</td>
                <td class="tableHeader">厂家名称</td>
                <td class="tableHeader">货物编号</td>
                <td class="tableHeader">货物名称</td>
                <td class="tableHeader">货物数量</td>
                <td class="tableHeader">包装单位</td>
                <td class="tableHeader">装率</td>
                <td class="tableHeader">件数</td>
                <td class="tableHeader">单价</td>
                <td class="tableHeader">总金额</td>
                <td class="tableHeader">修改</td>
                <td class="tableHeader">删除</td>
                <td class="tableHeader">删除2</td>
            </tr>
            </thead>
            <tbody class="tableBody" >
            <c:forEach items="${dataList}" var="o" varStatus="status">
                <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                    <td>${status.index+1}</td>
                    <td>${o.factory_name}</td>
                    <td>${o.product_num}</td>
                    <td>${o.product_name}</td>
                    <td>${o.cnumber}</td>
                    <td>${o.packing_unit}</td>
                    <td>${o.loading_rate}</td>
                    <td>${o.box_num}</td>
                    <td>${o.price}</td>
                    <td>${o.amount}</td>
                    <td><a href='${pageContext.request.contextPath}/api/extcproduct/update_page?ext_cproduct_id=${o.ext_cproduct_id}'style="text-decoration: underline;color:green;">修改</a></td>
                    <td><a href='javascript:;' onclick="isdelete('${o.ext_cproduct_id}','${o.contract_product_id}');" style="text-decoration: underline;color:green;">删除</a></td>
                    <td><a onclick="return confirm('确定删除吗?')"style="text-decoration: underline;color:green;" href="${pageContext.request.contextPath}/api/extcproduct/delete?ext_cproduct_id=${o.ext_cproduct_id}&contract_product_id=${o.contract_product_id}">删除2</a><td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<span style="color: red">${msg}</span>
<%--分页--%>
<center>
    <div class="digg">
        第${page.pageNo}页/共${page.totalPage}页
        <a href="${pageContext.request.contextPath}/api/extcproduct/list?pageNo=1&contract_product_id=${contract_product_id}">首页</a>
        <c:if test="${page.pageNo>1}">
            <a href="${pageContext.request.contextPath}/api/extcproduct/list?pageNo=${page.pageNo-1}&contract_product_id=${contract_product_id}">上一页</a>
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
                    <a href="${pageContext.request.contextPath}/api/extcproduct/list?pageNo=${i}&contract_product_id=${contract_product_id}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${page.pageNo<page.totalPage}">
            <a href="${pageContext.request.contextPath}/api/extcproduct/list?pageNo=${page.pageNo+1}&contract_product_id=${contract_product_id}">下一页</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/api/extcproduct/list?pageNo=${page.totalPage}&contract_product_id=${contract_product_id}">尾页</a>
    </div>
</center>
<script>
    function isdelete(id1,id2){
        var sure = window.confirm("确定删除吗？");
        if(sure){
            window.location.href="${pageContext.request.contextPath}/api/extcproduct/delete?ext_cproduct_id="+id1+"&contract_product_id="+id2;
        }
    }
</script>
</body>
</html>

