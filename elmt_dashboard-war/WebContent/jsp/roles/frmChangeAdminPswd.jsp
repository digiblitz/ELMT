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
<title>Business Service Center</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
      <table width="760" border="0" cellpadding="0" cellspacing="0" id="infoBarTab">
        <tr>
          <td class="infoTopPad" width="2%"></td>
          <td class="infoTopPad" width="90%" height="25"><span class="tabHead">Admin &gt;Change Password </span></td>
          <td class="infoTopPad" width="8%" align="center"></td>
        </tr>
      </table>
      <!-- INFO BAR ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="tableCommonBg"><table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
        <tr>
          <td width="230" class="menuTablePad"><!-- LEFT MENU STARTS HERE -->
            <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" id="fullHeight" class="menuTableBg">
              <tr>
                <td class="alignTop"><br />
                  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="leftMenuTab">
                    <tr>
                      <td class="menuTabLft"></td>
                      <td class="menuTabTwoBg"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
                          <tr>
                            <td height="20" class="alignLeft"><span class="textBold">&nbsp;</span> </td>
                          </tr>
                          <tr>
                            <td height="25"><!-- code for side bar starts here -->
                              <!-- code for side bar starts here -->
                            </td>
                          </tr>
                        </table></td>
                      <td class="menuTabRght"></td>
                    </tr>
                    <tr>
                      <td class="menuTabLftBotCrnr"></td>
                      <td class="menuTabBot"></td>
                      <td class="menuTabRghtBotCrnr"></td>
                    </tr>
                  </table>
                  <br />
                </td>
              </tr>
            </table>
            <!-- LEFT MENU ENDS HERE -->
          </td>
          <td width="500" class="subDeptTablePad"><!-- CONTENTS START HERE -->
            <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
              <tr>
                <td colspan="2" class="tblMainHead"><span class="styleBoldOne"> Change Password </span> </td>
              </tr>
              <tr>
                <td colspan="2" class="tblDescrp"> You can create<strong> New Password </strong>through Replacing the Old! <br />
                  <br />
                  <br />
                </td>
              </tr>
              <tr>
                <td><form name="frmRoleMgtRolePrev" id="frmRoleMgtRolePrev" action="roles.do" >
                    <input type="hidden" name="roleProcess" value="insertChangedAdminPswd"/>
                    <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                      <tr>
                        <td colspan="2" class="tblRowHead"> Change Password: </td>
                      </tr>
                      <%
            
            if (request.getAttribute("result") != null && request.getAttribute("result").toString().equals("false")) {
                                                    %>
                      <tr>
                          <td colspan="2" class="text2"><font color="red">Incorrect Old Password !</font></td>
                      </tr>
                      <%
            }
                                                    %>
                      <tr>
                        <td class="textBold"> Old Password:</td>
                        <td class="text2"><input name="oldPassword" id="oldPassword" type="password" class="textboxOne" size="25" /></td>
                      </tr>
                      <tr>
                        <td class="textBold"> New Password:</td>
                        <td class="text2"><input name="newPassword" id="newPassword" type="password" class="textboxOne" size="25" /></td>
                      </tr>
                      <tr>
                        <td class="textBold"> Confirm Password:</td>
                        <td class="text2"><input name="confirmPassword" id="confirmPassword" type="password" class="textboxOne" size="25" /></td>
                      </tr>
                      <tr>
                        <td colspan="2" class="alignCenter"><input type="submit" value="Submit" class="gradBtn" />
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" class="alignCenter">&nbsp;
                          <!-- DO NOT DELETE THIS ROW --></td>
                      </tr>
                    </table>
                  </form></td>
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
