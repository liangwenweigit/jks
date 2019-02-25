<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body>
<form id="insertForm" method="post" action="${pageContext.request.contextPath}/api/product/insert">
    <input type="hidden" name="contract_id" value="${contract_id}" />
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
		新增货物
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
                        <option value="">--请选择--</option>
                        <%--这里直接把厂家id传过去，然后在里面再查一次厂家，把厂家名称 UUID封装到 货物实体--%>
                        <c:forEach items="${factoryList}" var="f">
                            <option value="${f.factory_id}">${f.factory_name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td class="columnTitle_mustbe">货物名称：</td>
                <td class="tableContent"><input type="text" name="product_name"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">货物编号：</td>
                <td class="tableContent"><input type="text" name="product_num"/></td>
                <td class="columnTitle_mustbe">货物照片：</td>
                <td class="tableContent"><input type="text" name="product_image"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">货物数量：</td>
                <td class="tableContent"><input maxlength="9" onkeyup="value=value.replace(/[^\d]/g,'')" type="text" name="cnumber"/></td>
                <td class="columnTitle_mustbe">包装单位：</td>
                <td class="tableContent">
                    <select name="packing_unit">
                        <option value="PCS">--PCS--</option>
                        <option value="SETS">--SETS--</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">装率：</td>
                <td class="tableContent"><input type="text" name="loading_rate"/></td>
                <td class="columnTitle_mustbe">件数：</td>
                <td class="tableContent"><input maxlength="9" onkeyup="value=value.replace(/[^\d]/g,'')" type="text" name="box_num"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">单价：</td>
                <td class="tableContent"><input type = "text" name= "price" maxlength="9" onkeyup="if( ! /^\d{0,8}(\.\d{0,2})?$/g.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='';}" /></td>
                <td class="columnTitle_mustbe">排序号：</td>
                <td class="tableContent"><input type="text" maxlength="9" onkeyup="value=value.replace(/[^\d]/g,'')" name="order_no"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">货物描述：</td>
                <td class="tableContent"><textarea name="product_desc" style="height:120px;"></textarea></td>
            </tr>
        </table>
	</div>
</div>
</form>
<span>操作信息：</span><span style="color: red">${msg}</span>

<div class="textbox" id="centerTextbox">
    <div class="textbox-header">
        <div class="textbox-inner-header">
            <div class="textbox-title">
                货物列表
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
                    <td><a href='${pageContext.request.contextPath}/api/product/update_page?contract_product_id=${o.contract_product_id}&contract_id=${contract_id}'style="text-decoration: underline;color:green;">修改</a></td>
                    <td><a href='javascript:;' onclick="isdelete('${o.contract_product_id}','${contract_id}');" style="text-decoration: underline;color:green;">删除</a></td>
                    <td><a onclick="return confirm('确定删除吗?')" href="${pageContext.request.contextPath}/api/product/delete?contract_product_id=${o.contract_product_id}&contract_id=${contract_id}">删除2</a><td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%--分页--%>
<center>
    <div class="digg">
        第${page.pageNo}页/共${page.totalPage}页
        <a href="${pageContext.request.contextPath}/api/product/insert_page?pageNo=1&contract_id=${contract_id}">首页</a>
        <c:if test="${page.pageNo>1}">
            <a href="${pageContext.request.contextPath}/api/product/insert_page?pageNo=${page.pageNo-1}&contract_id=${contract_id}">上一页</a>
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
                    <a href="${pageContext.request.contextPath}/api/product/insert_page?pageNo=${i}&contract_id=${contract_id}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${page.pageNo<page.totalPage}">
            <a href="${pageContext.request.contextPath}/api/product/insert_page?pageNo=${page.pageNo+1}&contract_id=${contract_id}">下一页</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/api/product/insert_page?pageNo=${page.totalPage}&contract_id=${contract_id}">尾页</a>
    </div>
</center>
<script>
    function isdelete(contract_product_id,contract_id){
        var sure = window.confirm("确定删除吗？");
        if(sure){
            window.location.href="${pageContext.request.contextPath}/api/product/delete?contract_product_id="+contract_product_id+"&contract_id="+contract_id;
        }
    }
</script>
</body>
</html>

