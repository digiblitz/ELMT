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
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="com.hlchorse.form.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmHorseReg.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->

<script language="javascript" />

function myval()
{
if(document.frmHorseReg.uStatusId.value=="")
{alert(" Select any Status ");
 document.frmHorseReg.uStatusId.focus();
 return false;}
}

</script>

</head>

<body onload="initConditionHorse();">
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
					<div class="cmmnForm">
						<div class="colspan">
							<strong> Membership:</strong> <span class="styleBoldTwo">Horse Life Registration Details </span> 
						</div>
						<div id="commonBG" class="textCommon"><strong>Use one form for each horse.</strong> <br />
						<br />
						All horses competing Registered Events at the Novice level and above MUST be registered . (Beginner Novice level exempt). </div>
						<form name="frmHorseReg" id="myform" method="post" class="formcss" action="MemberRegiList.do" onsubmit="return myval();" >
					<!-- **************************************** Part A of the form starts here *********************************************** -->
					
					<div class="rowExpand" onclick="expandColl('parta');"> Horse Life Registration Details: Part A <span class="textOne">(click to expand/contract)</span></div>
					
					<div id="parta" class="formPart">
					
						<div class="rowHead">
							Horse Registration Details:
						</div>
							   
							  <div class="row">
							  
												<% 
														 String impdate1="N/A";
														 
														 HLCHorseDisplayVO objHorseDisp = new HLCHorseDisplayVO();
														 objHorseDisp =(HLCHorseDisplayVO)request.getAttribute("objHorseDisp");
					
														 String impdate=objHorseDisp.getHorseImportDate();
																	 if(impdate!=null && impdate.trim().length()>0)
																	 {
																		String[] spimpdate=impdate.split(" ");
																		String[] chimpdate=spimpdate[0].split("-");
																		impdate1=chimpdate[1]+"/"+chimpdate[2]+"/"+chimpdate[0];
																	  }else
																	   {
																		impdate1="N/A";
																	  }					
													   
													
													%>
					
												  
																 
									
												
												<span class="label">Horse Registration Type:</span>
												<span class="formX">
												
												<%=objHorseDisp.getMembershipTypeName()%>
									</span>
								</div>
						  
								
								<div class="colspan">
									<span class="floatLeftRadio">
									  <strong>Miscellaneous Charges</strong>
								   </span>
								</div>               
													   
					
													 <%   
													List selectedlist=(List)request.getAttribute("selectedlist");
													String[] serviceTyp=new String[500];
													
					
													if(selectedlist.size()!=0)
													{
					
													for (int n=0; n<selectedlist.size(); n++)
													{
													String sellist= (String)selectedlist.get(n);
													serviceTyp[n]=sellist;
													System.out.println(serviceTyp[n]);
					
													%>
								<div id="commonBG">
									<div class="row">
									  <span class="colspan" style="padding-left:5px;">
										  <span class="styleBoldWel"><%=serviceTyp[n]%></span>
									  </span>
									</div>
								</div>		
											  <%}}%>
									
								<!-- spacer starts-->
								<div class="spacer">&nbsp;</div>
								<!-- spacer ends-->
								
								<div class="colspan">
									<strong>Horse Information Section:</strong>
								</div>
								<div class="row">
									<span class="label">Horse Name: </span>
									<span class="formX"><%=objHorseDisp.getCompetitionName()%></span>
								</div>
								<%
								String regname="";
								if(objHorseDisp.getRegisteredName()!=null && objHorseDisp.getRegisteredName().trim().length()!=0){
								regname = objHorseDisp.getRegisteredName();
								}
								else{
								regname ="NA";
								}
								%>
								<div class="row">
									<span class="label">Registered Name:</span>
									<span class="formX"><%=regname%></span>
								</div>
								<%
								String breename="";
								if(objHorseDisp.getBaRegisteredName()!=null && objHorseDisp.getBaRegisteredName().trim().length()!=0){
								breename = objHorseDisp.getBaRegisteredName();
								}
								else{
								breename ="NA";
								}
								
								%>
								<div class="row">
									<span class="label">Breed Assoc. Horse is registered with:</span>
									<span class="formX"><%=breename%></span>
								</div>
								<%
								String pastname="";
								if(objHorseDisp.getBaPastName()!=null && objHorseDisp.getBaPastName().trim().length()!=0){
								pastname = objHorseDisp.getBaPastName();
								}
								else{
								pastname ="NA";
								}
								
								%>
								<div class="row">
									<span class="label">Past Name:</span>
									<span class="formX"><%=pastname%></span>
								</div>
					
								<div class="colspan">
									<strong>Rider Information Section:</strong>
								</div>
								
								<div class="row">
									<span class="label">Name:</span>
									<span class="formX"><%=objHorseDisp.getAddRiderFirstName()%>&nbsp; <strong> No.:</strong> <%=objHorseDisp.getRiderMemberId()%></span>
								</div>
								<div class="row">
									<span class="label">Street:</span>
									<span class="formX"><%=objHorseDisp.getRiderAddress1()%></span>
								</div>
								<div class="row">
									<span class="label">City:</span>
									<span class="formX"><%=objHorseDisp.getRiderCity()%></span>
								</div>
								<div class="row">
									<span class="label">State:</span>
									<span class="formX"><%=objHorseDisp.getRiderState()%></span>
								</div>
								<div class="row">
									<span class="label">Zipcode:</span>
									<span class="formX"><%=objHorseDisp.getRiderZip()%></span>
								</div>
									<div class="row">
									<span class="label">Phone:</span>
									<span class="formX"><%=objHorseDisp.getRiderPhoneNo()%></span>
								</div>

								<%
								String cell="";
								if(objHorseDisp.getRiderMobileNo()!=null && objHorseDisp.getRiderMobileNo().trim().length()!=0){
								cell = objHorseDisp.getRiderMobileNo();
								}
								else{
								cell ="NA";
								}
								%>

								<div class="row">
									<span class="label">Mobile:</span>
									<span class="formX"><%=cell%></span>
								</div>
								<div class="row">
									<span class="label">Email:</span>
									<span class="formX"><%=objHorseDisp.getRiderEmailId()%></span>
								</div>

								<%
								String prname="";
								if(objHorseDisp.getPrevRiderFirstName()!=null && objHorseDisp.getPrevRiderFirstName().trim().length()!=0){
								prname = objHorseDisp.getPrevRiderFirstName();
								}
								else{
								prname ="NA";
								}

								String prid="";
								if(objHorseDisp.getPrevRiderMemberId()!=null && objHorseDisp.getPrevRiderMemberId().trim().length()!=0){
								prid = objHorseDisp.getPrevRiderMemberId();
								}
								else{
								prid ="NA";
								}

								String arid="";
								if(objHorseDisp.getAddRiderMemberId()!=null && objHorseDisp.getAddRiderMemberId().trim().length()!=0){
								arid = objHorseDisp.getAddRiderMemberId();
								}
								else{
								arid ="NA";
								}

								String arname="";
								if(objHorseDisp.getAddRiderFirstName()!=null && objHorseDisp.getAddRiderFirstName().trim().length()!=0){
								arname = objHorseDisp.getAddRiderFirstName();
								}
								else{
								arname ="NA";
								}

								%>

								<div class="row">
									<span class="label">Previous Rider:</span>
									<span class="formX"><%=prname%>&nbsp; <strong>No.:</strong> <%=prid%></span>
								</div>
								<div class="row">
									<span class="label">Additional Rider:</span>
									<span class="formX"><%=arname%>&nbsp; <strong> No.:</strong> <%=arid%></span>
								</div>
					</div>
					<!-- **************************************** Part B of the form starts here *********************************************** -->
					
					<div class="rowExpand" onclick="expandColl('partb');">  Horse Life Registration Details: Part B <span class="textOne">(click to expand/contract)</span></div>
					<div id="partb" class="formPart">
					
						<div class="colspan">
									<strong>Owner Information Section:</strong>
						  </div>
								
								<div class="row">
									<span class="label">Name:</span>
									<span class="formX"><%=objHorseDisp.getOwnerFirstName()%></span>
								</div>
								<div class="row">
									<span class="label">Street:</span>
									<span class="formX"><%=objHorseDisp.getOwnerAddress1()%></span>
								</div>
								<div class="row">
									<span class="label">City:</span>
									<span class="formX"><%=objHorseDisp.getOwnerCity()%></span>
								</div>
								<div class="row">
									<span class="label">State:</span>
									<span class="formX"><%=objHorseDisp.getOwnerState()%></span>
								</div>
								<div class="row">
									<span class="label">Zipcode:</span>
									<span class="formX"><%=objHorseDisp.getOwnerZip()%></span>
								</div>
									<div class="row">
									<span class="label">Phone:</span>
									<span class="formX"><%=objHorseDisp.getOwnerPhoneNo()%></span>
								</div>

								<%
								String omob="";
								if(objHorseDisp.getOwnerMobileNo()!=null && objHorseDisp.getOwnerMobileNo().trim().length()!=0){
								omob = objHorseDisp.getOwnerMobileNo();
								}
								else{
								omob ="NA";
								}
								%>

								<div class="row">
									<span class="label">Cell:</span>
									<span class="formX"><%=omob%></span>
								</div>
								<div class="row">
									<span class="label">Email:</span>
									<span class="formX"><%=objHorseDisp.getOwnerEmailId()%></span>
								</div>
								<%
								String addOwner="";
								if(objHorseDisp.getAddOwnerName()!=null && objHorseDisp.getAddOwnerName().trim().length()!=0){
								addOwner = objHorseDisp.getAddOwnerName();
								}
								else{
								addOwner ="NA";
								}
								
								%>
								<div class="row">
									<span class="label">Additional Owner:</span>
									<span class="formX"><%=addOwner%></span>
								</div>
								<%
								String prevOwner="";
								if(objHorseDisp.getPrevOwnerName()!=null && objHorseDisp.getPrevOwnerName().trim().length()!=0){
								prevOwner = objHorseDisp.getPrevOwnerName();
								}
								else{
								prevOwner ="NA";
								}
								
								%>
								<div class="row">
									<span class="label">Previous Owner:</span>
									<span class="formX"><%=prevOwner%></span>
								</div>
					
					
					
						<div class="rowHead">
							Horse Description:
						</div>
					           <%
							   String horseCol="";
							   if(objHorseDisp.getHorseColor()!=null && objHorseDisp.getHorseColor().trim().length()!=0){
							   horseCol = objHorseDisp.getHorseColor();
							   }
							   else{ 
							   horseCol = "NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Color:</span>
									<span class="formX"><%=horseCol%></span>
								</div>
								 
								<div class="row">
									<span class="label">Sex:</span>
									<span class="formX"><%=objHorseDisp.getHorseGender()%></span>
								</div>
								 <%
							   String hgt="";
							   if(objHorseDisp.getHorseHeight()!=null && objHorseDisp.getHorseHeight().trim().length()!=0){
							    hgt = objHorseDisp.getHorseHeight();
							   }
							   else{
							   hgt ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Height</span>
									<span class="formX"><%=hgt%></span>
								</div>
								 <%
							   String yrFoale="";
							   if(objHorseDisp.getHorseYearFoaled()!=null && objHorseDisp.getHorseYearFoaled().trim().length()!=0){
							   yrFoale = objHorseDisp.getHorseYearFoaled();
							   }
							   else{
							   yrFoale ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Year foaled:</span>
									<span class="formX"><%=yrFoale%></span>
								</div>
								 <%
							   String breed="";
							   if(objHorseDisp.getHorseBreed()!=null && objHorseDisp.getHorseBreed().trim().length()!=0){
							    breed =objHorseDisp.getHorseBreed();
							   }
							   else{
							   breed ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Breed:</span>
									<span class="formX"><%=breed%></span>
								</div>
								 <%
							   String countryOrg="";
							   if(objHorseDisp.getHorseCountry()!=null && objHorseDisp.getHorseCountry().trim().length()!=0){
							    countryOrg = objHorseDisp.getHorseCountry();
							   }
							   else{
							   countryOrg = "NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Country of origin:</span>
									<span class="formX"><%=countryOrg%></span>
								</div>
								 <%
							   String sireName="";
							   if(objHorseDisp.getHorseSire()!=null && objHorseDisp.getHorseSire().trim().length()!=0){
							     sireName = objHorseDisp.getHorseSire();
							   }
							   else{
							   sireName ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Sire Name:</span>
									<span class="formX"><%=sireName%></span>
								</div>
								 <%
							   String sireBreed="";
							   if(objHorseDisp.getHorseSireBreed()!=null && objHorseDisp.getHorseSireBreed().trim().length()!=0){
							    sireBreed =objHorseDisp.getHorseSireBreed();
							   }
							   else{
							    sireBreed ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Sire Breed:</span>
									<span class="formX"><%=sireBreed%></span>
								</div>
								 <%
							   String damName="";
							   if(objHorseDisp.getHorseDam()!=null && objHorseDisp.getHorseDam().trim().length()!=0){
							     damName = objHorseDisp.getHorseDam();
							   }
							   else{
							   damName ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Dam Name:</span>
									<span class="formX"><%=damName%></span>
								</div>
								 <%
							   String damBreed="";
							   if(objHorseDisp.getHorseDamBreed()!=null && objHorseDisp.getHorseDamBreed().trim().length()!=0){
							    damBreed = objHorseDisp.getHorseDamBreed();
							   }
							   else{
							   damBreed ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Dam Breed:</span>
									<span class="formX"><%=damBreed%></span>
								</div>
								 <%
							   String importForm ="";
							   if(objHorseDisp.getHorseImportedFrom()!=null && objHorseDisp.getHorseImportedFrom().trim().length()!=0){
							     importForm = objHorseDisp.getHorseImportedFrom();
							   }
							   else{
							   importForm ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Imported From:</span>
									<span class="formX"><%=importForm%></span>
								</div>
								 
								<div class="row">
									<span class="label">Date Imported:</span>
									<span class="formX"><%=impdate1%></span>
								</div>
								 <%
							   String forGrade="";
							   if(objHorseDisp.getHorseForeignGrade()!=null && objHorseDisp.getHorseForeignGrade().trim().length()!=0){
							   		forGrade = objHorseDisp.getHorseForeignGrade();
							   }
							   else{
							   		forGrade="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Foreign Grade:</span>
									<span class="formX"><%=forGrade%></span>
								</div>
								 <%
							   String forPoints="";
							   if(objHorseDisp.getHorseForeignPoints()!=null && objHorseDisp.getHorseForeignPoints().trim().length()!=0){
							   forPoints = objHorseDisp.getHorseForeignPoints();
							   }
							   else{
							     forPoints ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Foreign Points:</span>
									<span class="formX"><%=forPoints%></span>
								</div>
								 <%
							   String assGrade="";
							   if(objHorseDisp.getHorseAssignedGrade()!=null && objHorseDisp.getHorseAssignedGrade().trim().length()!=0){
							   	assGrade=objHorseDisp.getHorseAssignedGrade();
							   }
							   else{
							   	assGrade ="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Assigned Grade:</span>
									<span class="formX"><%=assGrade%></span>
								</div>
								 <%
							   String assPoints="";
							   if(objHorseDisp.getHorseAssignedPoints()!=null && objHorseDisp.getHorseAssignedPoints().trim().length()!=0){
							   		assPoints=objHorseDisp.getHorseAssignedPoints();
							   }
							   else{
							      	assPoints="NA";
							   }
							   
							   %>
								<div class="row">
									<span class="label">Assigned Points:</span>
									<span class="formX"><%=assPoints%></span>
							</div>
							  
				<div class="row">
					<span class="label">Assign Status: </span>
					<span class="formX">
					<input type="hidden" name="memRegiListProcess" value="memRegiApprove"/>
					<input type="hidden" name="memberId" value="<%=request.getAttribute("memid")%>"/>
					 <input type="hidden" name="memRegiListProcess" value="memRegiApprove"/>
					 <input type="hidden" name="usrtypname" value="<%=request.getAttribute("usrtypname")%>"/>

						<select name="uStatusId" id="uStatusId" class="selectboxOne" >
						  <option selected="selected">Select One</option>
									  <option value="Active">Activate</option>
									  <option value="Inactive">Deactivate</option>
						  <option value="Expired">Expired</option>						  
						<option value="Deceased">Deceased</option>
						<option value="Retired">Retired</option>
																		 
						</select>
					</span>
							
				  </div>
				<div class="row">
					<span class="alignCenter">		
					<input type="submit" value="Approve" />
					</span>
				</div>	
					
</div>
</form>
</div>
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
