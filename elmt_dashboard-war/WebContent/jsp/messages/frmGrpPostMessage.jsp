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
<script language="javascript1.1">
function myvalidate(){
	if(document.postGroupForm.groupName.options.selectedIndex==0 || document.postGroupForm.groupName.value==""){
		alert('Select Any one of the Group name');
		document.postGroupForm.groupName.focus();
		return false;
	}
/*	if(document.postGroupForm.groupName.value==null || document.postGroupForm.groupName.value==""){
			alert('Recipients mail address cannot be empty');
			document.postGroupForm.groupName.focus();
			return false;
	}*/
	if(document.postGroupForm.msg.value!=""){
		split(document.postGroupForm.msg.value,'\n');
		if (splitIndex > 80) {
			alert('Mail Message Exceeds its allowed limit');
			return false;
		}
		for (var i=0;i<splitIndex;i++) {
			if (splitArray[i].length > 80) {
				alert('Mail Message Exceeds its allowed limit of columns');
				return false;
			}
		}
	}
	if(document.postGroupForm.msg.value=="" || document.postGroupForm.msg.value.indexOf(' ')==0){
		alert('Mail message cannot be empty');
		document.postGroupForm.msg.focus();
		return false;	
	}
}
//Check for the blank spaces in the TextArea even with the Enter spaces
var splitIndex = 0;
var splitArray = new Array();

function split(string,text) {
    splitIndex = 0;
    splits(string,text);
}

function splits(string,text) {
	
	strLength = string.length, txtLength = text.length;
	
    if ((strLength == 0) || (txtLength == 0)) return;

    var i = string.indexOf(text);
    if ((!i) && (text != string.substring(0,txtLength))) return;
    if (i == -1) {
        splitArray[splitIndex++] = string;
        return;
    }

    splitArray[splitIndex++] = string.substring(0,i);
     
    if (i+txtLength < strLength)
        splits(string.substring(i+txtLength,strLength),text);

    return;
}

function postData(val){
	if(document.postGroupForm.groupName.options.selectedIndex!=0 || document.postGroupForm.groupName.value!=""){
		document.postGroupForm.to.value=val;
	}
}
</script>
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<html:form method="post" action="/postAction.do" enctype="multipart/form-data" onsubmit="return myvalidate();" >
				<input type="hidden" name="method" value="postMail"/>
				<html:hidden property="parentMsgId"/>

				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				   <tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">Post A Message</span></td>
				  </tr>
				  <tr>
						<td colspan="2" class="tblDescrp">

							<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
						  
						</td>
				  </tr>
				  <tr>
						 <td colspan="4">
							 <table width="100%" border="0" cellspacing="0" cellpadding="0">
							 <% 
							 String validgroup = (String) request.getAttribute("validgroup");
							 if(validgroup.equalsIgnoreCase("no")){
							 %>				 
							   	  <tr>
										<td class="styleError" colspan="4">Group Message  posting failed as the Group does not <strong>'Exist'</strong> or You may be an <strong>'Invalid Group Member'</strong></td>
								  </tr>	
							 <%
							 }
							 %>
						   
								   <tr>
										<td class="tblRowHead" colspan="4">Post A Message In This Group</td>
								  </tr>
								   <tr>
										<td class="tableLeft">Group Name:</td>
										<td class="tableRight"><!--<html:text property="groupName" styleClass="textboxOne" size="20"/>-->
										<%
										String names_collection = "";
										ArrayList avail  =  (ArrayList) request.getAttribute("AvailGroups");
										if(avail!=null && avail.size()!=0){
											Iterator itr = avail.iterator();
											
										%>
											<select name="groupName" class="selectboxOne" id="groupNameSelect" onchange="postData(this.value);">
												<option value="">Select One</option>
											<%	while(itr.hasNext()){
													String [] groupInfo = (String[]) itr.next();
													String group_name =groupInfo[0];
													String group_id = groupInfo[1];
													String category_id = groupInfo[2];
													String group_type = groupInfo[3];
													String group_desc = groupInfo[4];
											%>
												<option value="<%=group_name%>"><%=group_name%></option>
										<%	}	
										%>
											</select>
										<%	
										}
										%>
										</td>
									</tr>

								   <tr>
										<td class="tableLeft">From:</td>
										 <td class="tableRight"><html:text property="from" readonly="true" styleClass="textboxOne" size="20" /></td>
								   </tr>
								   <tr>
										<td class="tableLeft">Subject:</td>
										<td class="tableRight"> <html:text property="subject" styleClass="textboxOne" size="20" />
								   </tr>
									<tr>
										<td class="tableLeftTxtArea">Message:</td>
										<td class="tableRight"><textarea name="msg" class="textAreaOne" rows="5"></textarea>&nbsp;<span class="asterisk">*</span></td>
									</tr>
									<tr>
									  <td class="tableLeft">Attachments:</td>
									  <td class="tableRight"><html:file property="file"/><!--<input type="file" name="file" class="textboxOne" />--></td>
							   		</tr>
									<tr>
										<td class="tableLeft">&nbsp;</td>
										<td class="tableRight">
										<input type="hidden" name="to" />
										<input name="Submit" type="submit" class="gradBtn" value="Send" /> 
										<input name="Submit2" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);"/>										</td>
									</tr>
									<tr>
										<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
