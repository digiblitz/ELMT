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
<script language="javascript">
var adHttpRequest1;

function loginCheck()
{
	
       var loginName=null;
    if(document.getElementById('loginName').value!="" && document.getElementById('loginName').value.indexOf(" ")!=0)
	{
       loginName=document.getElementById('loginName').value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+loginName; 

        if (window.ActiveXObject) 
        { 
            adHttpRequest1 = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            adHttpRequest1 = new XMLHttpRequest(); 
        } 
     
        adHttpRequest1.open("POST", url, true); 
        
        adHttpRequest1.onreadystatechange = function() {loginStatus1(); } ; 
        adHttpRequest1.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function loginStatus1() 
    { 
   
        if (adHttpRequest1.readyState == 4) 
        { 
            if(adHttpRequest1.status == 200) 
            { 
                //get the XML send by the servlet 
                 var adnameXML = adHttpRequest1.responseXML.getElementsByTagName("email")[0]; 				 
                 //var adnameText= adnameXML.childNodes[0].nodeValue; 
                 
                if(adnameXML==null)
                {    
                alert("User Name does not Exists! Choose Other Name"); 
            	document.getElementById('loginName').focus();
				document.getElementById('loginName').value="";
                return false;
                }    
                
            } 
            else 
            { 
                alert("Error loading page\n"+ adHttpRequest1.status +":"+ adHttpRequest1.statusText); 
            } 
        } 
    } 

	
//---------------------

</script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<link href="../../css/core-ie.css" rel="stylesheet" type="text/css" />
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
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<html:form method="post" action="/inviteAction.do">
                <input type="hidden" name="method" value="inviteModerate"/>


				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">Invite Moderator </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
					  Go ahead and <strong>invite</strong> a member to be the moderator of any of your groups. 
					</td>
				  </tr>
				  <tr>
					<td>
				  
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							 <tr>
									<td colspan="2" class="tblRowHead">Group Information</td>
							 </tr>
							  <tr>
								<td class="tableLeft">Group name: </td>
								<td class="tableRight"><!--<span class="styleBoldOne"><bean:write name="inviteGroupForm" property="groupName"/></span>--><html:select property="groupName" styleId="cat_sel_id" styleClass="selectboxOne" onchange="">
											<option selected="selected" value="">Select One</option>
											<html:options collection="GrpName" property="value" labelProperty="label" />
										</html:select></td>
							  </tr>
							 <tr>
									<td colspan="2" class="tblMainHead">Invite A Member To Be Your Group Moderator </td>
							 </tr>
							 <tr>
									<td class="tableLeft">Enter  Login Name: </td>
									<td class="tableRight"><!--<html:text property="emailId" styleClass="textboxOne" size="35" />--><input type="text" name="loginName" id="loginName" class="textboxOne" maxlength="20" onblur="loginCheck();"/></td>
							  </tr>
							  <tr>
									<td class="tableLeft">Subject: </td>
									<td class="tableRight"><html:text property="subject" styleClass="textboxOne" size="35" /></td>
							  </tr>
							  <tr class="tblDescrp">
								<td colspan="2"><p><strong>Enter an introductory message.</strong> <br />
							    Enter a message explaining why you are   inviting this member to be the <strong>'Moderator'</strong> of this Group. </p></td>
							  </tr>
							  <tr>
									<td class="tableLeftTxtArea">Message: </td>
									<td class="tableRight"><textarea name="body" class="textAreaOne" rows="5"></textarea></td>
							  </tr>
							  <tr>
									<td colspan="2" height="30" class="alignCenter">
									<input name="Submit" type="submit" class="gradBtn" value="Invite" /> 
									<input name="Submit2" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1)" />
									</td>
							  </tr>
							  <tr>
									<td height="25" colspan="4" class="alignCenter">&nbsp;</td>
							  </tr>
						</table>
					</td>
					</tr>
				</table>
				</html:form>

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