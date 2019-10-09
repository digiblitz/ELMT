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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmCreateServType.js" type="text/javascript" >
</script>
<script language="javascript">

     function clearFields(obj){

            document.frmCreateServType.uType.value = "";
			document.frmCreateServType.horseServiceTypeName.value="";
	        document.frmCreateServType.horseServiceTypeAmount.value="";
            document.getElementById('frmCreateServType').rd1[0].checked=false;
			document.getElementById('frmCreateServType').rd1[1].checked=false;
            //document.getElementById('frmCreateServType').status2.checked=false;
           return true;
       }

     function cancelServiceType(){
		  var utypeId=document.frmCreateServType.uTypeId.value
   if(confirm("Do you want to Cancel and go back to List Page?"))
		{
			  if(utypeId!=""){
   strURL="./horseServiceMaster.do?horseProcess=getSerTypeDet&uType="+utypeId+"&userTypeName=Human";
   window.location.href = strURL;
		  }  else
			  {
	strURL="./horseServiceMaster.do?horseProcess=initList&userTypeName=Human";
   window.location.href = strURL;
		  }
                  }
		else
		{
			return;
		}
    }
</script>


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
	 Membership Services - Human: <span class="styleBoldTwo"> Create</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">You can <strong>Create</strong> a <strong>New Membership Service - Human </strong> for all members and non-members Online Services Dashboard, right here!<br />
    </td>
  </tr>


  <tr>
  	 <td>

		<form name="frmCreateServType" id="frmCreateServType" method="get" action="horseServiceMaster.do" onSubmit="return myvalidate();">
	        <input type="hidden" name="horseProcess" value="createHorseMaster" />
                <% String userTypeName=(String)session.getAttribute("userTypeName");
				 String uTypeId=(String)session.getAttribute("userTypeId");%>
                                   <input type="hidden" name="userTypeName" value="<%=userTypeName%>"/>
								     <input type="hidden" name="uTypeId" value="<%=uTypeId%>"/>
        	<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				 <%-- <tr>
					<td colspan="2" class="tblRowHead">Create membership service: Human  </td>
				  </tr> --%>
				  <tr>
					  <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
                                  </tr>
								  <%
					String status=(String)request.getAttribute("status");

					 if(status!=null)
					{
					if(status.equalsIgnoreCase("false"))
					{%>
				  <tr>
                            <td class="styleError" colspan="2"><strong>Service already exists</strong></td>
                  </tr>
					<%}}%>
                           <tr>
                                      <td height="25" class="tableLeft">User Type: </td>
				      <td class="tableRight"><select name="uType" class="selectboxOne">
					 <option value="" selected="selected" >Select One</option>
								  <%
									Vector vUType = (Vector)session.getAttribute("displayUserTypeDetails");
                                                                  //      String uTypeId=(String)session.getAttribute("userTypeId");
									if(vUType!=null && vUType.size()!=0){
										Enumeration eUtype = vUType.elements();
										 //String [] userType = {ID, name };
										while(eUtype.hasMoreElements()){
											String[] strType = (String [])eUtype.nextElement();
											String uTypeID = strType[0];
											String uTypeName = strType[1];
											if(uTypeID.equals(uTypeId)){
										%>
										  <option value="<%=uTypeID%>" selected="selected" ><%=uTypeName%></option>
										<%
											}
											else{
											%>
											 <option value="<%=uTypeID%>" ><%=uTypeName%></option>
											<%
											}
										}
										}
								%>
                                                                </select>  <span class="asterisk">*</span></td>
                                  </tr>
				  <tr>
					<td class="tableLeft">Service:</td>
					<td class="tableRight">
					<input name="horseServiceTypeName" type="text" class="textboxOne" maxlength="80"/>  <span class="asterisk">*</span>
					   </td>
									  </tr>
				  <tr>
					<td class="tableLeft"> Cost $:</td>
					<td class="tableRight">
					<input name="horseServiceTypeAmount" type="text" class="textboxOne" maxlength="16" />
					<span class="asterisk">*</span></td>
				  </tr>
                                   <tr>
					<td class="tableLeft"> Status:</td>
                                        <td class="tableRight"><input type="radio" name="rd1" value="1"  />Active
                                   <input type="radio" name="rd1" value="0" />Inactive  <span class="asterisk">*</span></td>
				  </tr>
				<!---  /*<%
						  //ArrayList accDetails = (ArrayList) request.getAttribute("accDetails");
						  //if(accDetails!=null && accDetails.size()!=0){
						//	Iterator itr = accDetails.iterator();
						//	while(itr.hasNext()){
							//	String txnDet[] = (String[]) itr.next();

							//		String txnId = txnDet[0];
							//		String txnName = txnDet[1];
						  %>
							<option value="<%//=txnId%>"><%//=txnName%></option>
						  //<%//	 }
						 // }
						  //%>
							</select><span class="asterisk">*</span></td>*/
				  </tr>-->
				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->
					<td colspan = "2" style="text-align:center" height="25"><input name="Submit" type="submit" class="gradBtn" value="Create" />&nbsp;&nbsp;
					<input type="button" value="Clear" class="gradBtn" onClick ="clearFields()" />&nbsp;&nbsp;
					<input  type="button" class="gradBtn" value="Cancel" onclick="cancelServiceType()"/>
					</td>

				  </tr>
			</table>

		</form>
	</td>
  </tr>

 <%-- <tr>
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