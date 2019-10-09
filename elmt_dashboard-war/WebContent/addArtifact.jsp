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
<head>
<%@taglib prefix="s" uri="http://jakarta.apache.org/taglibs/"%>

<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
</head>

<body>
    

    
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%-- <%@ include file = "../../include/infobar.jsp" %> --%>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
<%@ include file = "include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<% if(request.getAttribute("added") != null && request.getAttribute("added").equals("true") ){
          out.print("Artifact Added");   
         }
         %>
		 </td></tr>
					
   <tr>
     <td>
         
         <form action="artifact.do" name="upform" enctype="multipart/form-data">
           <input type="hidden" name="process" value="addOrgLevel" />
           <table bgcolor="#FFFFFF" width="330" height="198" valign="top">
         <tr>
           <td class="tblRowHead" colspan="3" align="center" bgcolor="#e2eaf2">Create Artifact</td>
         </tr>
         <tr>
           <td width="102" height="30" class="tableLeft" > Artifact Name: </td>
           <td width="266"><input type="text" name="name" size="20" class="textboxOne" /></td>
           <td width="59">&nbsp;</td>
         </tr>
		 <tr> 
							<td class="tableLeft">Artifact Level:</td>
								<td width="266" class="tableRight" id="subItemList"><select name="level" id="subItemNo" class="textboxOne">
                                  <option value="tblMfgStrategic">StrOrg</option>
                                  <option value="tblMfgStrategicBSC">StrLOB</option>
                                  <option value="tblMfgStrategicOrder">StrOrder</option>
                                  <option value="tblMfgStrategicOTC">StrOrderToCash</option>
                                  <option value="tblMfgStrategicOE">StrOrderEntry</option>
                                </select></td>	</tr>
								 <tr> 
							<td class="tableLeft">Artifact Type:</td>
								<td width="266" class="tableRight" id="subItemList"><select name="type" id="subItemNo" class="textboxOne">
                                  <option value="NGovern">Non-Govern</option>
                                  <option value="Govern">Govern</option>
                             </select></td>	</tr>
							  <tr>
           <td width="102" height="30" class="tableLeft" > Artifact Description: </td>
           <td width="266"><TEXTAREA Name="desc" rows="4" cols="20" class="textboxOne"></TEXTAREA> </td>
           <td width="59">&nbsp;</td>
         </tr>
         <tr>
           <td class="text9"> Location </td>
           <td colspan="2"><input type="file" name="filename" size="50" class="textboxOne"></td>
         </tr>
         <tr>         </tr>

         <tr>
           <td height="30" ><input type="submit" value="Submit" name="Submit" class="text1"/>           </td>
           <td colspan="2"><input type="reset" value="Cancel" class="text1"name="undo"/>           </td>
         </tr>
         <tr>
           <td class="text4" colspan="3">&nbsp;</td>
         </tr>
     </table>
 </form>
     </td>
   </tr>
 </table></td>
				  </tr>
				  
				  
				</table>
			  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>CONTENTS END HERE -->		
			
	  </table>
	
	</td>
  </tr>



</body>
</html>
