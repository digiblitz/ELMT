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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Integrated Enterprise Dashboard</title>
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
    <td class="infoBar">
	    <!-- INFO BAR STARTS HERE -->
        <table width="760" border="0" cellpadding="0" cellspacing="0" id="infoBarTab">

			  <tr>
				
				<td class="infoTopPad" width="2%">				</td>
				<td class="infoTopPad" width="90%" height="25"><span class="styleBoldTwo">Admin &gt; User Management &gt; Delete Roles </span></td>
				<td class="infoTopPad" width="8%" align="center">
	</td>
				
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
							<td class="menuTabTwoBg">
								
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">

                                  <tr>
                                    <td height="20" class="alignLeft"><span class="textBold">&nbsp;</span> </td>
                                  </tr>
								  
									<tr>
										<td height="25">
										
										<!-- code for side bar starts here --><!-- code for side bar starts here -->									  </td>
								  </tr>
									 
									  															  								  
                                </table>

							</td>
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
              <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
                <tr>
                  <td colspan="5" class="tblMainHead">  Roles : <span class="styleBoldTwo"> Delete Roles </span></td>
                </tr>
                <tr>
                  <td colspan="5" class="tblDescrp"><br />
                  You are viewing the list of <strong>Roles</strong> created by you. To <strong> Delete </strong> a	role click on the <strong>Delete</strong> button beside each record.  </td>
                </tr>
                <tr>
                  <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                      
                      <tr>
                        <td width="278" height="27" class="tblRowHead">Name of Role </td>
                        <td width="93" colspan="2" class="tblRowHead">Delete</td>
                      </tr>
                      
					   <%  
						 ArrayList roleList = (ArrayList) request.getAttribute("allRoleList");
      					 if(roleList!=null && roleList.size()!=0){
							Iterator it = roleList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String roleId = s[0];
								String roleName = s[1];
		                %>
						<form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="roles.do" >
						<input type="hidden" name="roleId" value="<%=roleId%>" />
						<input type="hidden" name="roleProcess" value="initeditRole" />
						<tr>
							<td height="26" class="listCellBg"><%=roleName%></td>
							<td colspan="2" class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="Apply" /></td>
							<!--<td class="listCellBg"><input name="Submit2" type="submit" class="twoBtn" value="Deactivate" /></td> -->
        	              </tr>
				    </form>
						<% }
					 }
					  else {%>
							 <tr>
							<td colspan="2" class="tblRowHead"><center>No Records are found</center></td>
							</tr>
					<% } %>
                      
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