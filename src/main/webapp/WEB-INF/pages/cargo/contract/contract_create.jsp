<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/clock/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/clock/jquery.datepick.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/clock/jquery.datepick.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/clock/jquery.datepick-zh-CN.js" charset="UTF-8"></script>
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
                <td class="columnTitle_mustbe">收购方：</td>
                <td class="tableContent"><input type="text" name="offeror" value="杰信商贸有限公司"/></td>
                <td class="columnTitle_mustbe">客户名称：</td>
                <td class="tableContent"><input type="text" name="customer_name"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">合同号：</td>
                <td class="tableContent"><input type="text" name="contract_no"/></td>
                <td class="columnTitle_mustbe">打印版式：</td>
                <td class="tableContentAuto">
                    <input type="radio" name="print_style" value="2" class="input" checked>两款
                    <input type="radio" name="print_style" value="1" class="input">一款
                </td>
            </tr>
            <tr>
                <%--<td class="columnTitle_mustbe">签单日期：</td>
                <td class="tableContent">
                    <input type="text" style="width:90px;" name="signing_date"
                           onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                </td>--%>
                <td class="columnTitle_mustbe">签单日期：</td>
                <td class="tableContent">
                    <input type="text" style="width:90px;" name="signing_date" id="signing_date" readonly="readonly"/>
                </td>
                <td class="columnTitle_mustbe">重要程度：</td>
                <td class="tableContentAuto">
                    <input type="radio" name="import_num" value="3" class="input" checked>★★★
                    <input type="radio" name="import_num" value="2" class="input">★★
                    <input type="radio" name="import_num" value="1" class="input">★
                </td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">交货期限：</td>
                <td class="tableContent">
                    <input type="text" style="width:90px;" name="delivery_date" id="delivery_date" readonly="readonly"/>
                </td>
                <td class="columnTitle_mustbe">船期：</td>
                <td class="tableContent">
                    <input type="text" style="width:90px;" name="ship_date" id="ship_date" readonly="readonly" />
                </td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">贸易条款：</td>
                <td class="tableContent"><input type="text" name="trade_clause"/></td>
                <td class="columnTitle_mustbe">验货员：</td>
                <td class="tableContent"><input type="text" name="inspector"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">制单人：</td>
                <td class="tableContent"><input type="text" name="input_by"/></td>
                <td class="columnTitle_mustbe">审单人：</td>
                <td class="tableContent"><input type="text" name="check_by"/></td>
            </tr>
            <tr>
                <td class="columnTitle_mustbe">要求：</td>
                <td class="tableContent"><textarea name="crequest" style="height:120px;"></textarea></td>
                <td class="columnTitle_mustbe">说明：</td>
                <td class="tableContent"><textarea name="instructions" style="height:120px;"></textarea></td>
            </tr>
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

