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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmCreateContactType.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
 <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
 <script language="javascript">
  function CancelContactType(){
      if(confirm("Do you want to Cancel and go back to List Page?")){
	strURL = "./contact.do?conprocess=list";
	window.location.href = strURL;
        }
	  else
	    {
		return;
	    }
       }
       </script>
 <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
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
                        <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
			<!--<td width="230" class="menuTablePad">-->
			<!-- LEFT MENU STARTS HERE -->
			<!--<!%@ include file = "../../include/menu-roles-leftmenu.jsp" %>-->
			<!-- LEFT MENU ENDS HERE -->

		    <!--</td>-->
                    <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Contact Type: <span class="styleBoldTwo">Create</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	  You can <strong>Create</strong> a <strong>New Contact Type</strong> for all members and non-members Online Services Dashboard, right here!</td>
  </tr>
  <tr>
  	<td>
                 <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
		<form method="post" name="frmCreateContactType" id="frmCreateContactType" action="./contact.do" onsubmit="return myvalidate(this);" >
                    <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
                        <input type="hidden" name="conprocess" value="create" />
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			<tr>
                            <td colspan="2" class="tblDescrp" height="15">&nbsp;Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
		  	</tr>
				<%
					String status=(String)request.getAttribute("err");
					if(status!=null)
					{
					if(status.equalsIgnoreCase("st"))
					{%>
				  <tr>
                            <td class="styleError" colspan="2"><strong>Contact Type already exists</strong></td>
                  </tr>
					<%}}%>
			
				<!--<tr>
					<td colspan="2" class="tblRowHead">Create Contact Type</td>
				</tr>-->
				  <tr>
                                        <!--<td class="tableLeft">Contact Type Name:</td>-->
					<td class="tableLeft">Contact Type:</td>
                                        <td class="tableRight"><input name="txtcontactname" type="text" class="textboxOne" maxlength="50" />
					<span class="asterisk">*</span></td>
				 </tr>
                                        <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
                                         <tr>
					<td class="tableLeft">Description:</td>
					<td class="tableRight"><input name="txtcontactdescription" type="text" class="textboxOne" maxlength="150" />
					<span class="asterisk">*</span></td>
				 </tr>

                                    <tr>
				<td class="tableLeft">Type:</td>
				<td width="297" class="tableRight">
								<select type="select" name="ContactType" class="selectboxOne">
								<option value="" selected="selected">Select One</option>
								<%
								ArrayList contType = (ArrayList) request.getAttribute("contType");
                                                                int icon1=0;
                                                                if(contType != null){
                                                                    icon1 = contType.size();
                                                                }
                                                                out.println("type is "+icon1);
                                                                System.out.println("-----------------Type is ------------------"+icon1);
								if(contType!=null){
								Iterator itr = contType.iterator();
								while(itr.hasNext()){
									String conTypeArray[] = (String[]) itr.next();
									String contactTypeId = conTypeArray[0];
									String contactTypeName =conTypeArray[1];
                                                                        System.out.println("hi"+contactTypeName);
									%>
									<option value="<%=contactTypeId%>"><%=contactTypeName%></option>
								<%	}
								}
								%>
								</select>   <span class="asterisk">*</span>
							</td>
				</tr>

                                              <tr>
							<td class="tableLeft"> Status:</td>
							<td width="40" class="tableRight" >
                                                            <input type="radio" id="status1" name="status" value="1" />Active <input type="radio" id="status2" name="status" value="0"/>Inactive
                                                           <span class="asterisk">*</span></td>
                                                        
						  </tr>
                                      <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->
					<td colspan = "2" style="text-align:center" height="25">
					<input type="submit" value="Create" class="gradBtn"/>&nbsp;&nbsp;
                                        <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
                                        <input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields();"/>&nbsp;&nbsp;
					<input type="button" value="Cancel" class="gradBtn" onclick="CancelContactType();"/>
                                        <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
					</td>
				  </tr>
			</table>
		</form>
	</td>
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
