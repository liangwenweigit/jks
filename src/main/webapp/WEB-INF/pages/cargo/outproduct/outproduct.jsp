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
<form method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${pageContext.request.contextPath}/api/outproduct/print','_self');">打印</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		出货表月统计
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent">
					<input type="text" style="width:90px;" id="inputDate" name="inputDate" value="2019-02" readonly="readonly"/>
				</td>
	        </tr>
		</table>
	</div>
</div>
 

</form>
<script>
    $(function() {
        $("#inputDate").datepick({dateFormat:"yy-mm"});
    });
</script>
</body>
</html>

