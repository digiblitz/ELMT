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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
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
              <!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			  <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
              <!-- LEFT MENU ENDS HERE -->
        </td>
        <td width="500" class="subDeptTablePad"><!-- CONTENTS START HERE -->
              <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
                <tr>
                  <td colspan="5" class="tblMainHead"> Messages: <span class="styleBoldTwo">My Database Download Request Listings </span></td>
                </tr>
                <tr>
                  <td colspan="5" class="tblDescrp">You are viewing the list of  <strong>Database Download Requests </strong> sent by you. <br /></td>
                </tr>
                <tr>
                  <td><table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
                      <tr>
                        <td colspan="5" style="border-bottom:1px solid #999;">
						
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
                        </table>						</td>
                      </tr>
					    <tr>
                        <td width="128" height="27" class="tblRowHead">No. Of Addresses</td>
                        <td width="125" class="tblRowHead">Total Cost ($) </td>
                        <td width="125" class="tblRowHead">Date Of Request </td>
                        <td width="63" class="tblRowHead">View</td>
                      </tr>

					   <logic:present name="messageDBForm" property="reqLists">
							 <bean:size id="size" name="messageDBForm" property="reqLists"/>
							 <logic:equal name="size" value="0">
									<tr>
									   <td colspan="5"  align="center"><B>No Records Found</B></td>
									</tr>
							 </logic:equal>                 
							 <logic:greaterThan name="size" value="0">
								<logic:iterate id="reqList" name="messageDBForm" property="reqLists">								  
											<tr>
											<td class="listCellBg"><bean:write name="reqList" property="noOfRecords"/></td>
											<td class="listCellBg"><bean:write name="reqList" property="totalAmount"/></td>
											<td class="listCellBg"><bean:write name="reqList" property="addDate"/></td>
											<td class="listCellBg"><input name="editName" type="button" class="oneBtn" value="Edit"
													onClick="location.href='msgDB.do?method=requestDB&reqId=<bean:write name="reqList" property="dbRequestId"/>'" /></td>												
											</tr>										
									</logic:iterate>                    
							   </logic:greaterThan>     
						</logic:present>			
                      <tr>
                        <td height="19" colspan="6">&nbsp;</td>
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
