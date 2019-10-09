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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->

</head>
 
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
						Meetings: <span class="styleBoldTwo">Insurance Release  ICP Registration Listing</span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
				 
					Insurance Release ICP Registerations placed by you are listed below. To view the details,
					click on the <strong>'View'</strong> button beside each record.
					</td>
				  </tr>
				 
				 <tr>
				   <td>
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
				
						 <!--<tr>
							<td colspan="4" class="tableSpan">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th height="20" width="130" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
									<!-- 
									<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
										<span class="legendOne">&nbsp;</span> &nbsp;Delete					</td>
									 
									<td valign="middle" class="alignLeft">
										<span class="legendTwo">&nbsp;</span> &nbsp;View					</td>
								  </tr>
								</table>			</td>
						  </tr>-->
						  <tr>
							<td height="27" class="tblRowHead">Meeting ID  </td>
							<td class="tblRowHead">Date of Registration </td>
							<td class="tblRowHead">Approval Status</td>
							<td width="65" class="tblRowHead">View</td>
						  </tr>
							<%
							Vector icpList=new Vector(); 
                                                        icpList=(Vector)request.getAttribute("usrDetail");
                                                        System.out.println("size :"+icpList.size());
                                                        
							if(icpList!=null && icpList.size()!=0)
							{
                                                          Enumeration en=icpList.elements();    
                                                            
							while(en.hasMoreElements())
							{
                                                                  String[] icpVal = (String[])en.nextElement();
                                                                  String dat1=icpVal[4];
								  String[] dat=dat1.split(" ");
                                                           %>
						  <form name="frmMeeUserICPRegiList" method="post" action="IcpUsrList.do?process=view">
						  <tr>
							<input type="hidden" value="<%=icpVal[7]%>" name="releaseId" />
							<td height="26" class="listCellBg"><%=icpVal[0]%></td>
							<td class="listCellBg"><%=dat[0]%></td>
							<td class="listCellBg"><span class="styleBoldTwo"><%=icpVal[6]%></span></td>
							<td class="listCellBg"><input name="Submit" type="submit" class="oneBtn" value="View" /></td>
						  </tr>
							</form>
							<%}}
                                                        else{%>
                                                              <td class="alignCenter" colspan="5"> 
                                                              No Records Found !
                                                                      </td>
                                                       <% }%>
						 <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
						   </tr>-->
						  <tr>
							<td height="19" colspan="5">&nbsp;</td>
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
