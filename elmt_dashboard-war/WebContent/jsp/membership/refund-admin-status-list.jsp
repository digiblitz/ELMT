#-------------------------------------------------------------------------------
# * * Copyright: 2019 digiBlitz Foundation
#  * * 
#  * * License: digiBlitz Public License 1.0 (DPL) 
#  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
#  * * 
#  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
#  * * 
#  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
#  * * 
#  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
#-------------------------------------------------------------------------------
<%@page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<script>
    function submitForm() {
       document.forms[0].submit();
    }
</script>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	 Membership: <span class="styleBoldTwo">Refund Application Listings </span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The Refund Applications are listed as follows. <br />
		<br />
		To 'Finalize' an application, click on the <strong>'Finalize'</strong> button beside it. <br />
	</td>
  </tr>
 
 <tr>
 	<td>
	
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		 <!-- <tr>
			<td colspan="4">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<th height="20" width="125" valign="top" class="alignRight" style="border-bottom:1px solid #999;">&nbsp;Color Legends:&nbsp; </th>
					<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendTwo">&nbsp;</span> &nbsp;Finalize.					</td>
				  </tr>
				</table>			</td>
		  </tr>-->
		  <!--
		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>
		  -->
		  <tr>
			<td width="123" height="27" class="tblRowHeadTwo">Firstname</td>
			<td width="123" class="tblRowHeadTwo">Lastname</td>
			<td width="155" class="tblRowHeadTwo">Status</td>
			<td width="63" class="tblRowHeadTwo">View</td>
		   </tr>
		  <tr>
		  
                   <%
                        int i=0;
						Vector refundDetail=new Vector();
						refundDetail = (Vector) request.getAttribute("refundDetail");
                                                session.setAttribute("refundDetail",refundDetail);
                        
                        Enumeration en=refundDetail.elements();
                        
                        while(en.hasMoreElements())
                        {
                            String refdet[]=(String[])en.nextElement();
                            
                        if(refdet[1]!=null)
                        {%>
                        <form name="frmCntryMailList" id="myform" method="post" action="./AdminRefundStatusDisp.do">
                        <tr>
                        <th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=refdet[0]%></th>
			<th bgcolor="#E3E1D2" class="alignCenter"><%=refdet[1]%></th>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=refdet[14]%></td>
                        <input type=hidden name="id" value="<%=i%>">
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="Submit2" value="View Details" class="oneBtn" onClick="javascript:submitForm();"/></td>
                        </tr>
                        </form>
                  <%   }  else {%>
			
                            <th bgcolor="#E3E1D2" class="alignCenter">No Records Found</th>
				                 
                <% } i++;   
                }%>    
		   
                   	  
		   <!--
		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
		   -->
		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
           </tr>
	  </table>
	  
	</td>
</tr>  
</table>
			<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	  </table>
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>

</body>
</html>
