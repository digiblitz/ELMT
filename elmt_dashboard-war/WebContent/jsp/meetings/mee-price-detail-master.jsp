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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmGrossAmtMast.js" type="text/javascript" ></script>
<script>
	
	function getMeeActivityName(selMeeCataTypeId){
	//alert(selMemTypeId);
		frmMeePriceDet.meeProcess.value = "getMeeActivity";
		frmMeePriceDet.method="post";
		frmMeePriceDet.action="./meePriceMasterList.do?meeCatagoryTyp="+selMeeCataTypeId;
		frmMeePriceDet.submit();
	}
	function getMeeSpecificationName(selMeeSpecTypeId){
		frmMeePriceDet.meeProcess.value = "getMeeSpeci";
		frmMeePriceDet.method="post";
		frmMeePriceDet.action="./meePriceMasterList.do?meeSpecificationTypID="+selMeeSpecTypeId;
		frmMeePriceDet.submit();
	}
	
	function getMeeSpecificationName(selMeeUserTypeId){
		frmMeePriceDet.meeProcess.value = "getMeeUser";
		frmMeePriceDet.method="post";
		frmMeePriceDet.action="./meePriceMasterList.do?meeMeeUserTypeId="+selMeeUserTypeId;
		frmMeePriceDet.submit();
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
<%
String  MeeCataTypeId = (String)request.getAttribute("MeeCataTypeId");
if(MeeCataTypeId==null || MeeCataTypeId.equals("")){
MeeCataTypeId="";
}
%>

  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Price Detail Master </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	    <img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />
		
	</td>
  </tr>
  <tr>
  	<td>
	
		<form name="frmMeePriceDet" id="myform" method="post" action="./meePriceMasterList.do">
		<input name="meeProcess" type="hidden" value="">
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">Add A Price Detail:</td>
				  </tr>
				   <tr>
				     <td class="tableLeft">Activity Category :</td>
				     <td class="tableRight">
					 	<select name="selMeeCataTypeId" id="selMeeCataTypeId" class="selectboxOne" onChange="getMeeActivityName(this.value)">
							<option selected="selected">Select One</option>
				<% 
					String str1[] = null;
					Vector meeCataTypeVect=new Vector();
					  meeCataTypeVect=(Vector)request.getAttribute("meeCataTypeVect");
  					  System.out.println("vector1:"+meeCataTypeVect);
					  Enumeration enm=meeCataTypeVect.elements();
					  
					  while(enm.hasMoreElements()){
							String[] s = (String[])enm.nextElement();
							for (int i=0;i<s.length; i++) {
								System.out.println("Content : "+s[i]);
							}
							String activityTypeId = s[0];
							String activityTypeName = s[1];
							
							System.out.print(" injsp:  "+activityTypeId);
							
							%>
							 <option value="<%=activityTypeId %>"><%=activityTypeName%></option>
						<%
						}
                       
							%>
							
						</select>
				 	 <span class="asterisk">*</span>					 </td>
		     	  </tr>
				  <tr>
				     <td class="tableLeft">Specification:</td>
				     <td class="tableRight">
					 	<select name="selMeeSpecTypeId" id="selMeeSpecTypeId" class="selectboxOne" onChange="getMeeSpecificationName(this.value)">
							<option selected="selected">Select One</option>
			 <%
		   		//String str1[] = null;
					//Vector memberType=new Vector();
					 Vector specificationType=(Vector)request.getAttribute("dispSpecifcation");
  					  System.out.println("vector2:"+specificationType);
					 if(specificationType!=null && specificationType.size()!=0){ 
					  Enumeration enm1=specificationType.elements();
					  
					  while(enm1.hasMoreElements()){
							String[] s = (String[])enm1.nextElement();
							for (int i=0;i<s.length; i++) {
								System.out.println("Content : "+s[i]);
							}
							String specificationID = s[0];
							String activityTypeId = s[1];
							String specificationName = s[2];
							
							//if (firstName == null) { firstName = "";}
							//if (lastName == null) {lastName = ""; }
							System.out.print(" injsp specificationID:  "+specificationID);
							System.out.print(" specificationName:  "+specificationName);
							
		   %>
							 <option value="<%=specificationID %>"><%=specificationName%></option>
						<%
						}
						}
						%>
							
							
							
						</select>
				 	 <span class="asterisk">*</span>					 </td>
		     	  </tr>

				  <tr>
				     <td class="tableLeft">User Type :</td>
				     <td class="tableRight">
					 	<select name="advType_sel" id="advType_sel" class="selectboxOne">
							<option selected="selected">Select One</option>
							
			<%
		   		//String str1[] = null;
					//Vector memberType=new Vector();
					 Vector userType=(Vector)request.getAttribute("userTypeVec");
  					  System.out.println("vector3:"+userType);
					 if(userType!=null && userType.size()!=0){ 
					  Enumeration enm2=userType.elements();
					  
					  while(enm2.hasMoreElements()){
							String[] s = (String[])enm2.nextElement();
							for (int i=0;i<s.length; i++) {
								System.out.println("Content : "+s[i]);
							}
							String userTypeId = s[0];
							String userTypeName = s[1];
							
							
							//if (firstName == null) { firstName = "";}
							//if (lastName == null) {lastName = ""; }
							System.out.print(" injsp userTypeId:  "+userTypeId);
							System.out.print(" userTypeName:  "+userTypeName);
							
		   %>
							 <option value="<%=userTypeId %>"><%=userTypeName%></option>
						<%
						}
						}
						%>							
							
						</select>
				 	 <span class="asterisk">*</span>					 </td>
		     	  </tr>
				  <tr>
					<td class="tableLeft">Normal Price :</td>
					<td class="tableRight"><strong>$</strong>
                        <input name="text" type="text" class="textboxOne" size="8" />
                    	<span class="asterisk">*</span>
					</td>
				  </tr>
			      <tr>
					<td class="tableLeft">After Due Date Price :</td>
					<td class="tableRight">
					<strong>$</strong> <input type="text" class="textboxOne" size="8" />
				 	 <span class="asterisk">*</span>
					</td>
				  </tr>

			      <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="button" value="Add" class="gradBtn" onclick="window.location.href('#');" />
					&nbsp;
					<input type="button" value="cancel" class="gradBtn" onclick="javascript:history.back(-1);" />					</td>
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
