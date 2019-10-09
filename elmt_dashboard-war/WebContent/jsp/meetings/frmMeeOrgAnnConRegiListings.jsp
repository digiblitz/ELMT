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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>
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
    <td class="alignTop"><!-- HEADER STARTS HERE -->
        <%@ include file = "../../include/header.jsp" %>
        <!-- HEADER ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="infoBar"><!-- INFO BAR STARTS HERE -->
        <%@ include file = "../../include/infobar.jsp" %>
        <!-- INFO BAR ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="tableCommonBg"><table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
      <tr>
        <td width="230" class="menuTablePad"><!-- LEFT MENU STARTS HERE -->
              <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
              <!-- LEFT MENU ENDS HERE -->
        </td>
        <td width="500" class="subDeptTablePad"><!-- CONTENTS START HERE -->
              <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
                <tr>
                  <td colspan="5" class="tblMainHead">Meetings: <span class="styleBoldTwo">Organizer Annual Convention Registration Listing </span></td>
                </tr>
                <tr>
                  <td colspan="5" class="tblDescrp"><img src="images/usea_logo.jpg" class="imgFloatLeft" /> <br />
				  The <strong>Annual Convention Meeting registrations</strong> posted by you is listed below. 
				  Click on the '<strong>View</strong>' 
				  button to view the details of the registration along with it's approval status.
				  </td>
                </tr>
                <tr>
                  <td><table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
                      <tr>
                        <td colspan="6" style="border-bottom:1px solid #999;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <th width="125" height="20" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
                              <td colspan="3" valign="middle" class="alignLeft"><span class="legendTwo">&nbsp;</span> &nbsp;View. </td>
                              <!--
									<td colspan="3" valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
									<span class="legendOne">&nbsp;</span> &nbsp;Delete.
									</td>
									-->
                            </tr>
                        </table></td>
                      </tr>
                      <tr>
                        <td width="107" height="27" class="tblRowHead">Annual Meeting Id </td>
                        <td width="96" class="tblRowHead">Name of Badge  </td>
                        <td width="92" class="tblRowHead">Date Of Registration </td>
                        <td class="tblRowHead">Status </td>
                        <td width="53" class="tblRowHead">View</td>
                      </tr>

						<%
		   		
						Vector annConRegList=(Vector)request.getAttribute("annConRegList");
						System.out.println("Inside the JSP vector :"+ annConRegList);
						if(annConRegList!=null && annConRegList.size()!=0){ 
						Enumeration enm1=annConRegList.elements();
					  
					  while(enm1.hasMoreElements()){
							String[] s = (String[])enm1.nextElement();
							for (int i=0;i<s.length; i++) {
								System.out.println("Content : "+s[i]);
							}
							
							String annualMeetingId = s[0];
							String badgeInfo = s[1];
							String requestStatus = s[5];
							String dateOfReg1 = s[6];
							String dateOfReg[] = dateOfReg1.split(" ");
														
							System.out.print(" injsp badgeInfo:  "+badgeInfo);
							System.out.print(" requestStatus:  "+ requestStatus);
							System.out.print(" dateOfReg:  "+dateOfReg[0]);

		   %>

					<form name="frmDisplay" method="post" action="./AnnualConRegList.do">		
						<input name="memProcess" type="hidden" value="dispDetail"> 
						<input name="annualMeetingId" type="hidden" value="<%=annualMeetingId%>">
						
						<tr>
							<td height="26" class="listCellBg"><%=annualMeetingId  %>  </td>
							<td class="listCellBg"><%=badgeInfo  %>  </td>
							<td class="listCellBg"><%=dateOfReg[0]%></td>
							<td class="listCellBg"><span class="styleBoldTwo"><%=requestStatus%></span></td>
							<td class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="View" /></td>
						</tr>
					</form>
					  <%		}
						}else {
					%>
					<tr>
					  <th height="25" colspan="5">There are no records available. </th>
					</tr>
					<%}%>
                      
                      <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
						   </tr>-->
                      <tr>
                        <td height="19" colspan="7">&nbsp;</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
          <!-- CONTENTS END HERE -->
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="footerBg"><!-- FOOTER STARTS HERE -->
        <%@ include file = "../../include/footer.jsp" %>
        <!-- FOOTER ENDS HERE -->
    </td>
  </tr>
</table>
</body>
</html>
