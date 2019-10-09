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
<%@ page import="java.util.*" %> 
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
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
						Meetings: <span class="styleBoldTwo">List of ICP Meetings</span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
					ICP Meetings placed by Organizers are listed below. To view the details,
					click on the <strong>'View'</strong> button beside each record and to apply for the meeting click on the <strong>'Apply'</strong> button.					</td>
				  </tr>
				 
				 <tr>
					<td>
						<table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
						 <!--<tr>
							<td colspan="5" class="tableSpan" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th height="20" width="130" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
									 
									<td valign="middle" class="alignLeft">
										<span class="legendOne">&nbsp;</span> &nbsp;Apply					</td>
									
									<td valign="middle" class="alignLeft">
										<span class="legendTwo">&nbsp;</span> &nbsp;View					</td>
								  </tr>
								</table>			</td>
						  </tr>-->
						  <tr>
							<td width="60" height="27" class="tblRowHead">Meeting ID </td>
							<td width="80" class="tblRowHead">Assessment Level  </td>
							<td width="80" class="tblRowHead">Assessment Date  </td>
							<td width="65" class="tblRowHead">View</td>
							<td width="65" class="tblRowHead">Apply</td>
						  </tr>
                                                  <%
							Vector icpMeeList=new Vector(); 
                                                        icpMeeList=(Vector)request.getAttribute("meeDet");
                                                        System.out.println("size :"+icpMeeList.size());
                                                        
							if(icpMeeList!=null && icpMeeList.size()!=0)
							{
                                                          Enumeration en=icpMeeList.elements();    
                                                            
							while(en.hasMoreElements())
							{
                                                                  String[] icpVal = (String[])en.nextElement();
                                                                  String dat1=icpVal[2];
								  String[] dat=dat1.split(" ");
                                                           %>
                                           <form name="frmMembRegi" id="myform" method="post"  action="IcpUsrMeetList.do">
										   <input type="hidden" name="mid" value="<%=icpVal[0]%>" />
										   <input type="hidden" name="process" value="view" />
						  <tr>
              
                             <td height="26" class="listCellBg"><%=icpVal[0]%></td>
							<td class="listCellBg"><%=icpVal[1]%></td>
							<td class="listCellBg"><%=dat[0]%></td>
							<td class="listCellBg"><input name="butName" type="submit" class="oneBtn" value="View" /></td>
							<td class="listCellBg"><input name="butName" type="button" class="twoBtn" value="Apply" onclick="window.location.href('IcpUsrMeetList.do?process=applyDisp&mid=<%=icpVal[0]%>')"  /></td>
						  </tr>
                                           </form>	
  
                                           
							<%}}else{%>
                          <td  colspan="6" class="alignCenter" >
                                                             No Records Found !
                          </td>
                                                       <% }%>
                                                       
						  <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
						   </tr>-->
						  <tr>
							<td height="19" colspan="6">&nbsp;</td>
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
