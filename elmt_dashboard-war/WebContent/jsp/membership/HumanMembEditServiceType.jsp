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
<%@ page import="java.sql.*"%>
<%@ page import = "java.math.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmCreateServType.js" type="text/javascript" ></script>

    <script language="javascript">
  function clearFields(){

//document.frmCreateServType.horseServiceTypeName.value="";
                 document.frmCreateServType.horseServiceTypeAmount.value="";
           document.getElementById('frmCreateServTypeId').status1.checked=false;
           document.getElementById('frmCreateServTypeId').status2.checked=false;
           return true;
}
 function cancelServiceType(){
		  var utypeId=document.frmCreateServType.uType.value
			   if(confirm("Do you want to Cancel and go back to List Page?")){
   strURL="./horseServiceMaster.do?horseProcess=getSerTypeDet&uType="+utypeId+"&userTypeName=Human";
   window.location.href = strURL;
   }
		else
		{
			return;
		}
    }
</script>
<%
String[] hrsServiceDet = (String[])session.getAttribute("displayEditHrsDetails");

String hrsTyId = "";
String horseServiceTypeName = "";
String horseServiceTypeAmount = "";
String transactionId = "";
String userTypeId="";
int active_status=0;
String UserTypeName="";

if(hrsServiceDet!=null){
       
	hrsTyId = hrsServiceDet[0];
	horseServiceTypeName = hrsServiceDet[1];
	horseServiceTypeAmount = hrsServiceDet[2];
	transactionId = hrsServiceDet[3];
        userTypeId=hrsServiceDet[4];
        active_status=Integer.parseInt(hrsServiceDet[5]);
         UserTypeName=hrsServiceDet[6];

	if(transactionId == null){
		transactionId = "";
	}
}
%>

<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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

<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	 Membership Services - Human: <span class="styleBoldTwo"> Edit</span></td>
  </tr>

    <tr>
					<td colspan="2" class="tblDescrp">You can <strong>Edit/Update</strong> the <strong>Membership Service - Human</strong> created by you as given below. </td>
				  </tr>
   <tr>
  	<td>
			<form name="frmCreateServType" action="horseServiceMaster.do" method="post" id="frmCreateServTypeId"  onSubmit="return myvalidate();" >
				<input type="hidden" name="horseProcess" value="hrsSerTyEdit" />
				<input type="hidden" name="hrsTyId" value="<%=hrsTyId%>" />
                                <% String userTypeName=(String)session.getAttribute("userTypeName");%>
                                   <input type="hidden" name="userTypeName" value="<%=userTypeName%>"/>
                   	<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">

				  <tr>
					  <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>)are required.</td>
		                   </tr>
				   <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					<tr>
					<td colspan="2" class="styleError"><strong>Service already exists</strong></td>
					</tr>
					<%
						horseServiceTypeName=(String)request.getAttribute("horseServiceTypeName");
					}
					%>
                                  <tr>
                                    <td height="25" class="tableLeft">User Type: </td>
                                   <td class="tableRight"><input name="UserTypeName" type="text" class="readOnly" readonly="true" value="<%=UserTypeName%>" /></td>

					 <td ><input name="uType" type="hidden" class="textboxOne" value="<%=userTypeId%>" maxlength="120"/>
					</td>
                                         </tr>
				  <tr>
                                        <td class="tableLeft">Service:</td>
                                        <td class="tableRight"><input name="horseServiceTypeName"  type="text" class="readOnly" readOnly="true" value="<%=horseServiceTypeName%>" /></td>
				  </tr>
				  <tr>
					<td class="tableLeft"> Cost $:</td>
					<td class="tableRight">
				<%
				float price = Float.parseFloat(horseServiceTypeAmount);
				BigDecimal bdAmount = new BigDecimal(price);
				bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
				%>
					<input name="horseServiceTypeAmount" type="text" class="textboxOne" value="<%=bdAmount%>" maxlength="16"/>  <span class="asterisk">*</span></td>
				  </tr>
				   <tr>
					<td class="tableLeft">  Status:</td>
					<%if(active_status==1){%>
										<td class="tableRight"><input type="radio" name="rd1" value="1" checked="true" id="status1" />
                                   Active<input type="radio" name="rd1" value="0" id="status2" />Inactive  <span class="asterisk">*</span></td>
								   <%}
								   else{%>
								   <td class="tableRight"><input type="radio" name="rd1" value="1" id="status1" />
                                  Active<input type="radio" name="rd1" value="0" checked="true" id="status2" />Inactive  <span class="asterisk">*</span></td>
								   <%}%>

				  </tr>
												
				  				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->
					<td colspan = "2" style="text-align:center" height="25">
					<input name="Submit" type="submit" class="gradBtn" value="Update" onClick=""/>&nbsp;&nbsp;
					<input name="Reset" type="button" value="Clear" class="gradBtn" onclick="clearFields();"/>&nbsp;&nbsp;
					<input  type="button" class="gradBtn" value="Cancel" onclick="cancelServiceType()"/></td>
				  </tr>
			</table>

		</form>
	</td>
  </tr>

  <%--<tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
  </tr> --%>
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
