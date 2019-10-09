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
<%@ page import="java.util.*,java.util.regex.*,java.text.*"%> 
<%@ page import="com.hlchorse.form.util.*" %>
<%@ page import="com.hlcform.util.HLCMemberDetails" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.hlcform.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
 
</head>
<%! String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}
%>
<%!					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
					String dateFormat(String fieldName){					
						Date clDate = null;
						Calendar cal = Calendar.getInstance();
						GregorianCalendar gc = new GregorianCalendar();
						try{
							clDate = sd.parse(fieldName);
						}catch(Exception e){
							System.out.println("Error While Parsing Date: "+e);
						}
						
						gc.setTime(clDate);
						cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						String dispDate = "N/A";
						if(clDate!=null ){
						dispDate = sd1.format(cal.getTime());
						}
						return dispDate;
					}
%>
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
		<form name="frmMembRegi" id="myform" method="post" class="formcss" action="InsertUserReg.do">
		
<div class="cmmnForm">
            <div id="parta" >	
							<div class="rowHead">
								Basic Information:
							</div>
	<%
	HLCUserRegVO HLCUserRegVO=new HLCUserRegVO();
	HLCUserRegVO=(HLCUserRegVO)request.getAttribute("EditusrVect");
	String prefix="N/A";
	if(HLCUserRegVO.getPrefix()!=null && HLCUserRegVO.getPrefix().trim().length()!=0){
	prefix=HLCUserRegVO.getPrefix();
	}
	%>
							
									<div class="row">
										<span class="label">Salutation:</span>
										<span class="formX">
										  <b><%=prefix%></b>
										</span>
									</div>
									<div class="row">
										<span class="label">First Name:</span>
										<span class="formX"><b><%=nullCheck(HLCUserRegVO.getFirstName())%></b></span>
									</div>
									<div class="row">
										<span class="label">Middle Name:</span>
										<span class="formX"><b><%=nullCheck(HLCUserRegVO.getMiddleName())%></b></span>
									</div>
									<div class="row">
										<span class="label">Last Name:</span>
										<span class="formX"><b><%=nullCheck(HLCUserRegVO.getLastName())%></b></span>
									</div>
									<div class="row">
										<span class="label">Suffix:</span>
										<span class="formX"><b><%=nullCheck(HLCUserRegVO.getSufix())%></b></span>
									</div>
					<%
						String finalDob="N/A";
							if(HLCUserRegVO.getDob()!=null){
							String[] dob=HLCUserRegVO.getDob().split(" ");
							finalDob=dob[0];
							}
					%>
			  						<div class="row">
										<span class="label">Date of Birth :</span>
										 <span class="formX"><b><%=dateFormat(finalDob)%></b></span>
									</div>
									<div class="row">
										<span class="label">Gender:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getGender())%></span>
									</div>
									<div class="row">
										<span class="label">EMail :</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getEmailId())%></span>
									</div>
				 
									<div class="row">
										<span class="label">User Name (Login ID):</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getLoginName())%></span>
									</div>
							<div id="pAdd">
									<div class="rowHead">
										<strong>Primary Address </strong> 
									</div>
									<div class="row">
										<span class="label">Address 1:</span>
										<span class="formX"> <%=nullCheck(HLCUserRegVO.getPrimaryAddress1())%></span>
									</div>
									<div class="row">
										<span class="label">Address 2:</span>
										<span class="formX"> <%=nullCheck(HLCUserRegVO.getPrimaryAddress2())%></span>
									</div>
									<div class="row">
										<span class="label">City:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getPrimaryCity())%></span>
									</div>
									<div class="row">
										<span class="label">State:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getPrimaryState())%></span>
									</div>
									<div class="row">
										<span class="label">Zipcode:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getPrmaryZip())%></span>
									</div>
									<div class="row">
										<span class="label">Country:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getPrimaryCountry())%></span>
									</div>
									<div class="row">
										<span class="label">Phone:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getPrimaryPhoneNo())%></span>
									</div>
									<div class="row">
										<span class="label">Mobile:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getPromaryMobileNo())%></span>
									</div>
									<div class="row">
										<span class="label">Fax:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getPrimaryFaxNo())%></span>
									</div>
							</div>
							<%
								if(HLCUserRegVO.getSecondaryContactTypeName()!=null){
									if(HLCUserRegVO.getSecondaryContactTypeName().equalsIgnoreCase("Secondary")){
									 
							%>
									<div>
									<div class="rowHead">
										<strong>Secondary Address </strong> 
									</div>
									<div class="row">
										<span class="label">Address 1:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getSecondaryAddress1())%> </span>	
									</div>
									<div class="row">
										<span class="label">Address 2:</span>
										<span class="formX"> <%=nullCheck(HLCUserRegVO.getSecondaryAddress2())%></span>
									</div>
									<div class="row">
										<span class="label">City:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getSecondaryCity())%></span>
									</div>
									<div class="row">
										<span class="label">State:</span>
										<span class="formX"> <%=nullCheck(HLCUserRegVO.getSecondaryState())%></span>
									</div>
									<div class="row">
										<span class="label">Zipcode:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getSecondaryZip())%></span>
									</div>
									<div class="row">
										<span class="label">Country:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getSecondaryCountry())%></span>
									</div>
									<div class="row">
										<span class="label">Phone:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getSecondaryPhoneNo())%></span>
									</div>
									<div class="row">
										<span class="label">Mobile:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getSecondaryMobileNo())%></span>
									</div>
									<div class="row">
										<span class="label">Fax:</span>
										<span class="formX"><%=nullCheck(HLCUserRegVO.getSecondaryFaxNo())%></span>
									</div>												
								</div>
								<%}} 
								String prefcom="";
								if(HLCUserRegVO.getPreferedCommunication().equalsIgnoreCase("Primary")){
								prefcom="Primary Address";
								}
								else{
								prefcom="Secondary Address"; 
								}
								%>						
									<div class="row">
										<span class="label">preferred Communication Address:</span>
										<span class="formX">
										<%=prefcom%>
										</span>
									</div>
									<div class="row">
										<div class="alignCenter"><input type="button" value=" Back " class="gradBtn" onclick="javascript:history.back(-1);" /></div>
									</div>									
            </div>
</div>
	</form>
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