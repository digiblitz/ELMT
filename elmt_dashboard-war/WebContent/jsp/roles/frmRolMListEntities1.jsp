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

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
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
                  <td colspan="5" class="tblMainHead"> IED View <span class="styleBoldTwo">Listings  </span></td>
                </tr>
                <tr>
                  <td colspan="5" class="tblDescrp"> <br />
                  You are viewing the list of <strong>Views</strong> created by you. To	edit	an	entity click on the <strong>Edit</strong> button beside each record. To deactivate an Entity click on the <strong>'Deactivate'</strong> button beside the corresponding record. </td>
                </tr>
                <tr>
                  <td><table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
                       
                      <tr>
                        <td width="278" height="27" class="tblRowHead">Name of View </td>
                        <td width="93" class="tblRowHead">Edit</td>
                        <td width="107" class="tblRowHead">Deactivate</td>
                      </tr>
                      
					   <%  
					   ArrayList entityList = (ArrayList) request.getAttribute("allEntityList");
      					 if(entityList!=null && entityList.size()!=0){
							Iterator it = entityList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String entityId = s[0];
								String entityName = s[1];
							%>
						  <tr>
	  					<form name="frmRoleEntityList" id="frmRoleEntityList" method="post" action="roles.do">
						<input name="roleProcess" type="hidden" value="initEditEntity">
						<input type="hidden" value="<%=entityId%>" name="entityId">

                        <td height="26" class="listCellBg"><%=entityName%></td>
                        <td class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value=" Edit " /></td>
                        <td class="listCellBg"><input name="Submit2" type="submit" class="twoBtn" value=" Deactivate" /></td>
						</form>
                      </tr>
                      <% }
					  }
					else {
					%>
					<tr>
					  <th height="25" colspan="4">No Records are found. </th>
					</tr>
					<%}%>
				<tr>

                      	<!--
					  		<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
						   </tr>
						 -->
                      <tr>
                        <td height="19" colspan="5">&nbsp;</td>
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
