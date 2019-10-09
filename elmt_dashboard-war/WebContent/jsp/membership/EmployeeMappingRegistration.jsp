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
<%@ page import="com.vo.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/employeeMappingRegistration.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/login_header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%//@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
  
		  <tr>
			<!--td width="230" class="menuTablePad"-->
			<!-- LEFT MENU STARTS HERE -->
			<!--%@ include file = "../../include/menu-roles-leftmenu.jsp" %-->
			<!-- LEFT MENU ENDS HERE -->		    <!--/td-->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
<div class="cmmnForm">
	<div class="colspan">
		 Employee:<span class="styleBoldTwo"> Mapping Registration Form </span>	</div>
  

    <form name="frmEmpRegi" id="frmEmpRegi" method="post" action="InsertEmpReg.do" class="formcss" onSubmit="return myvalidate(this);">
    <input type="hidden" name="process" value="insertEmpDet"/>
	
            <div id="parta" >
                <!-- **************************************** Part A of the form starts here *********************************************** -->
                                                       
														
                                                                                              

							<div class="rowHead">
								Basic Information:							</div>
									<div class="row">
										<span class="label">Salutation:</span>
									  <span class="formX">
										<select name="USelect" id="select" class="selectboxOne">
                                                                                    <option value="" selected="selected">Select One</option>
										  <option value="Mr">Mr.</option>
										  <option value="Mrs">Mrs.</option>
										  <option value="Miss">Miss.</option>
										  <option value="Ms">Ms.</option>
										</select>
										</span>			      </div>
									<div class="row">
										<span class="label">First Name:</span>                                      <span class="formX"><input type="text" name="fname" class="textboxOne" size="20" />&nbsp;<span class="asterisk">*</span></span>			      </div>
									<div class="row">
										<span class="label">Middle Name:</span>
										<span class="formX"><input type="text" name="mname"class="textboxOne" size="20" /></span>									</div>
									<div class="row">
										<span class="label">Last Name:</span>									  <span class="formX"><input type="text" name="lname" class="textboxOne" size="20" />&nbsp;<span class="asterisk">*</span></span>			      </div>
									<div class="row">
										<span class="label">Suffix:</span>
										<span class="formX"><input type="text" name="sname" class="textboxOne" size="20" /></span>									</div>
						      <div class="row">
										<span class="label">Employee Id:</span>									  <span class="formX"><input type="text" name="empId" class="textboxOne" size="20" onblur="empStat();" />&nbsp;<span class="asterisk">*</span></span></div>
						
									
									  <div class="row">
										<span class="label">User Name (Login ID):</span>									  <span class="formX"><input type="text" name="usrname" class="textboxOne" size="20" readonly="readonly"/>&nbsp;<span class="asterisk">*</span></span></div>

									<div class="row">
										<span class="label">EMail :</span>
									  <span class="formX"><input type="text" name="email" class="textboxOne" size="20" readonly="readonly"/>
									     <span class="asterisk">*</span></span></div>
									
									
									
									<input type="hidden" name="password" class="textboxOne" size="10" />
									 
								

							<div id="pAdd">

									<div class="colspan">
										<strong>Organization Position Mapping(OPM)</strong>									</div>
											<div class="row">
											  <span class="label">Country of Base Location:</span>
											  <span class="formX">
											  	<select name="counBaseLoc" id="counBaseLoc" class="selectboxOne" onchange="retrieveBaseLoc('baseLocList',this);">
							<option selected="selected" value="">Select One</option>
							<%
									 ArrayList counBaseLocList = (ArrayList) request.getAttribute("counBaseLocList");
          					 if(counBaseLocList!=null && counBaseLocList.size()!=0){
							Iterator itCBL = counBaseLocList.iterator();
							while(itCBL.hasNext()){
								String[] sCBL = (String[])itCBL.next();
								String sCurBaseId = sCBL[0];
								String sCurBaseName = sCBL[1];		
								
											

											 %>
											 <option value="<%=sCurBaseId%>"><%=sCurBaseName%></option>
											 <%
											 }
										}
									


							%>
							</select>
											    <span class="asterisk">*</span></span>											</div>
											<div class="row">
												<span class="label">Base Location:</span>
												<span class="formX">	<select name="baseLoc" id="baseLoc" class="selectboxOne" >
							<option selected="selected" value="">Select One</option>
							
							</select> <span class="asterisk">*</span></span>											</div>
												<div class="row">
												<span class="label">Country of Current Location:</span>
											  <span class="formX">
													<select name="curCoun" id="curCoun" class="selectboxOne" onchange="retrieveCurLoc('baseLocList',this);">
							<option selected="selected" value="">Select One</option>
							<%
									 ArrayList curCounList = (ArrayList) request.getAttribute("counBaseLocList");
          					 if(curCounList!=null && curCounList.size()!=0){
							Iterator itCurCou = curCounList.iterator();
							while(itCurCou.hasNext()){
								String[] sCurCoun = (String[])itCurCou.next();
								String curCounId = sCurCoun[0];
								String curCounName = sCurCoun[1];		
								
											

											 %>
											 <option value="<%=curCounId%>"><%=curCounName%></option>
											 <%
											 }
										}
									


							%>
							</select>
											    <span class="asterisk">*</span></span></div>
												
												<div class="row">
												<span class="label">Current Location:</span>
											  <span class="formX">
													<select name="curLoc" id="curLoc" class="selectboxOne" onchange="retrieveLobList()">
							<option selected="selected" value="">Select One</option>
							
							</select>
											 
											    <span class="asterisk">*</span> </span></div>										
											
											<!-- div class="row">
												<span class="label">Designation:</span>
											  <span class="formX">
											  <input type="text" name="desig" id="desig" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div-->
										
										
											<div class="row">
												<span class="label">Role:</span>
											  <span class="formX">
											 	<select name="role" id="role" class="selectboxOne">
							<option selected="selected" value="">Select One</option>
							<%
									 ArrayList roleList = (ArrayList) request.getAttribute("roleList");
          					 if(roleList!=null && roleList.size()!=0){
							Iterator itRol = roleList.iterator();
							while(itRol.hasNext()){
								String[] sRol = (String[])itRol.next();
								String sCurRoleId = sRol[0];
								String sCurRoleName = sRol[1];	
								String sCurRoleGrade = sRol[5];
								
											

											 %>
											 <option value="<%=sCurRoleId%>#<%=sCurRoleGrade%>"><%=sCurRoleName%></option>
											 <%
											 }
										}
									


							%>
							</select>
											    <span class="asterisk">*</span></span>
											    <input type="hidden" name="roleName" value="" id="roleName"/>
											    </div>
											    
												<div class="row" style="height:80px">
												<span class="label">LOB:</span>
											  <span class="formX">
													<select multiple name="lob" size="4" id ="lob" onchange="retrieveDept('depList','');">
														
													</select>
											 
											    <span class="asterisk">*</span> </span></div>
											
											<div class="row" style="height:80px">
												<span class="label">Department:</span>
											  <span class="formX">	
											  <select multiple name="dept"  id ="dept" onchange="retrieveDeptSup('depList','');">
														
													</select><span class="asterisk"> *</span></span>
													<input type="hidden" name="deptName" value="" id="deptName"/>
													</div>
													
													
											<div class="row" style="height:80px">
												<span class="label">Reporting Supervisor:</span>
												<span class="formX">  <select multiple name="repSup" id="repSup">
														
													</select><span class="asterisk">*</span></span>											</div>
											<div class="row" style="height:80px">
												<span class="label">Email Id of Reporting Supervisor:</span>
												<span class="formX"><select multiple name="supEmail" id="supEmail">
													


						
													</select><span class="asterisk">*</span></span>											</div>
														<!--div class="row">
												<span class="label">Email Id of Reporting Supervisor:</span>
												<span class="formX">
												<textarea rows="5" cols="10" id="sEmail" name="sEmail">

												</textarea>
												</span>											</div-->
							</div>
							

									
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->


								<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="submit" value="Submit" class="gradBtn" /></span>								</div>

									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->

						<!-- **************************************** Part A of the form Ends here *********************************************** -->
            </div>
	</form>
</div>

			<!-- CONTENTS END HERE -->			</td>
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
