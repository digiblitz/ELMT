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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>

<!--<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />-->
</head>
<script language="javascript">

  

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
			<%--<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>--%>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="0" cellpadding="0" cellspacing="0" align="center" class="tblInnerContainer">

			  <tr>




		<form name="frmBpReq" action="SysMgmt.do"  method="post" onsubmit="return myvalidate();">
			<input type="hidden" name="process" value="callBPReq"/>



  <tr>
		<td colspan="8">


 <table width="700%" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">





				    <tr class="tableInner">

					 <td class="tableLeft" nowrap>Date Submitted:</td>
          <td class="tableRight"><input  type="text" name="frmDate" id="frmDate" readonly="readonly" class="textboxOne" value="" size="20" maxlength="20" />
              <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> </td>

                                         

					<td class="tableLeft" nowrap>Type of Request:</td>
					<td class="tableRight">
                                            <select name="req" id="req" class="selectboxOne">
											<option selected="selected" value="">Select One</option>
							<option  value="BP">Business Process</option>
							<option  value="WS">Web Service</option>
							<option  value="">Artifact</option>
							<option  value="">others</option>
							



							</select></td>


















</tr>
  <tr class="tableInner">





                        <td class="tableLeft" nowrap>Request Id:</td>
          <td class="tableRight"><input type="text" name="reqId" id="reqId" value="" class="textboxOne" size="20"/></td>


       
	<td class="tableLeft" nowrap>
Request Priority:</td>
					<td class="tableRight">
                                            <select name="reqPri" id="reqPri" class="selectboxOne">
											<option selected="selected" value="">Select One</option>
							<option  value="high">High</option>
							<option  value="medium">Medium</option>
							<option  value="low">Low</option>
							

							



							</select></td>
						</tr>
						
						<tr align="center">
						<td> <input type="submit" value="Search" class="grdBtn"/></td>
						</tr>
						








	   </form>

	    <table id="dataTable" width="100%">
                

               <tr>

                     

                       <td width="90" height="27" class="tblRowHeadTwo">Serial Number</td>
                       <td width="90" height="27" class="tblRowHeadTwo">Request Date</td>
                       <td width="90" height="27" class="tblRowHeadTwo">Request Id</td>
		               <td width="100" height="27" class="tblRowHeadTwo">Request Title</td>
                       <td width="170" height="27" class="tblRowHeadTwo">Request Description</td>
		               <td width="100" height="27" class="tblRowHeadTwo">Priority</td>
                                 <td width="150" height="27" class="tblRowHeadTwo">Status</td>
                               
            </tr>
                <%
   ArrayList list=(ArrayList)request.getAttribute("list");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (list != null && list.size() != 0) {  
                                                            
   Iterator iter = list.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                                                                               	
                                                                	String [] artiType=(String[])iter.next();
                                                                	String sno=artiType[0];
                                                                	String date=artiType[1]; 
                                                                	String reqid=artiType[2];
                                                                	String reqtit=artiType[3];
                                                                	String reqdesc=artiType[4];
                                                                	String priority=artiType[5];
                                                                	String status=artiType[6];
                                                                	
                                                                	

                                                %>
                                                 <tr><td align="center"><%=sno%></td><td align="center"><%=date%></td><td align="center"><%=reqid%></td><td align="center"><%=reqtit%></td>
                                                 <td align="center"><%=reqdesc%></td>  <td align="center"><%=priority%></td>  <td align="center"><%=status%></td></tr>
                                                <%
                                                 
                                               
                                                                   
                                                                }
   }
   else{
	   %>
	   <tr><td colspan="8">No DATA </td></tr>
                                                <%
   }
                                                %>


                


		   </table>


</table>

		</td>
	</tr>
<!-- Based on id -->

<tr>
 	<td>

	</td>
</tr>

<!--end of the file-->
</table>
			<!-- CONTENTS END HERE -->

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
<script language="javascript">
    var cal1 = new calendar2(document.forms['frmBpReq'].elements['frmDate']);
	 cal1.year_scroll = true;
	 cal1.time_comp = false;

    




</script>

</body>
</html>
