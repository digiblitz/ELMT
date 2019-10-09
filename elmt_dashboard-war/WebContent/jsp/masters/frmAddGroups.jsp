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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlcmrm.util.HLCBreedVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<script>

function validate()
{
	
	var vFlag = false;
	var obj=document.frmAddListgroups;
	
	if(obj.masterId.selectedIndex==0 )
    {
		alert("Please select any one of the Group");
		return false;
    }
	if(obj.groupDet.value=="" )
    {
		alert("Details cannot be empty");
		return false;
    }

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			if((obj.elements[i].value.indexOf('	') == 0) || (obj.elements[i].value.lastIndexOf('	') == (obj.elements[i].value.length-1)) ||
				(obj.elements[i].value.indexOf(' ') == 0) || (obj.elements[i].value.lastIndexOf(' ') == (obj.elements[i].value.length-1)))
			{
				obj.elements[i].value = obj.elements[i].value.trim();
				//obj.elements[i].focus();
				vFlag = true;
			}
		}
	}

	if(vFlag==true)
	{
		alert("Leading and Trailing spaces will be trimmed. Please Submit again");
		return false;
	}
	
}

function cancelGroup()
{
	var masterId=document.frmAddListgroups.groupId.value;
	   strURL = "./viewMaster.do?process=searchList&masterId="+masterId;
    window.location.href = strURL;	
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

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">

		  <tr>
			
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer" width="100%">
  <tr>
    <td colspan="4" class="tblMainHead">
	Group - Details: <span class="styleBoldTwo">Create</span></td>
  </tr>
  <tr>
    <td colspan="4" class="tblDescrp">You can <strong>Create</strong> a <strong>New Group - Detail</strong> for all users.<br />
	</td>
  </tr>



  <tr>
  	<td>
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">

		<form name="frmAddListgroups" id="frmAddListgroups" method="post" action="viewMaster.do" onSubmit="return validate();">
		<input type="hidden" name="process" value="createGroups" />
		<input type="hidden" name="layerId" value="" />
	
<%
    		String groupIdObj=(String)request.getAttribute("groupId");
%>
           <input type="hidden" name="groupId" value="<%=groupIdObj%>" />
				 <tr>
					  <td class="tblDescrp" colspan="4">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
					  </tr>

				       <% String statuscheck = (String)request.getAttribute("status");
					if(statuscheck!=null && statuscheck.equals("error")){
					%>

					<tr>
					<td colspan="4" class="styleError"><strong> Detail already exist </strong></td>
					</tr>
					<%
					}
					%>
				  
										 <tr>
                 
					<td class="tableLeft" colspan="2">Groups:</td>
					<td class="tableRight" colspan="2"><select name="masterId" class="selectboxOne"  >
                                                <option  selected="selected" value="">Select One</option>

                                                <%
                                                ArrayList viewGroupList = (ArrayList)request.getAttribute("groupList");
           									 if(viewGroupList!=null && viewGroupList.size()!=0){
           							Iterator it = viewGroupList.iterator();
           							while(it.hasNext()){
           								String[] groupList = (String[])it.next();
           								String group_id = groupList[0];
           								String group_name = groupList[1];
           							   if (group_id.equals(groupIdObj)){
                                                                                            
										%>
										  <option  value="<%=group_id%>" selected="selected" ><%=group_name%></option>
										<%
											}
											else{
											%>
											 <option  value="<%=group_id%>" ><%=group_name%></option>
											<%
											}
										}
           									 }									
								%>
								</select>  <span class="asterisk">*</span></td>

				  </tr>
				  <tr>
					<td class="tableLeft" colspan="2">Detail:</td>

					<td class="tableRight" colspan="2"><input name="groupDet" type="text" class="textboxOne" />  <span class="asterisk">*</span></td>

				  </tr>
				   
				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->

					<td colspan="4" style="text-align:center" height="25"><input type="submit" name="submit" class="gradBtn" value="Create" />&nbsp;&nbsp;
					<input name="Clear"  type = "button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
					<input name="Cancel"  type = "button" class="gradBtn" value="Cancel" onClick = "cancelGroup();"/>&nbsp;&nbsp;
                    </td>

				  </tr></form>
			</table>


	</td>
	
	<tr>
	
	
	</tr>
  </tr>
  
    


  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
