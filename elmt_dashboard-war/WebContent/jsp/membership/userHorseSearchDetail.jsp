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
<%@ page import="java.text.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlccommon.util.HLCHorseRegisterationVO"%>
<%@ page import="com.hlchorse.form.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title<%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmHorseReg.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
  <%! 
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String  nullCheck(String fieldName){
        String retValue = "NG";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    %>
    <%! String dateCheck(Date fieldName){
        String detValue = "NG";
        if(fieldName!=null){
            detValue = sdf.format(fieldName);
        }
        return detValue;
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
				<div class="rowHead">Owner Information:</div>	
				<%
				String horseMemberId = (String)request.getAttribute("memId");
				System.out.print("horseMemberId:" +horseMemberId);
				 
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 	HLCHorseRegisterationVO HorseDisp =(HLCHorseRegisterationVO) request.getAttribute("HorseDet");
	String horseMemId = (String)HorseDisp.getHorseMemberId();
    String competitionName =(String) HorseDisp.getCompetitionName();
    String registeredName = (String) HorseDisp.getRegisteredName();
    String baRegisteredName = (String)HorseDisp.getBaRegisteredName();
    String baPastName = (String)HorseDisp.getBaPastName();
	String ownerId = (String)HorseDisp.getOwnerId();	
    String riderMemberId = (String)HorseDisp.getRiderMemberId();
    String prevRiderMemberId =""; 
    String addRiderMemberId = ""; 
    
    String prevOwnerId = ""; 
    String addOwnerId = ""; 
    String prevOwnerName =""; 
    String addOwnerName =""; 
	String paymentId = (String)HorseDisp.getPaymentId();
	String requestStatus = (String)HorseDisp.getRequestStatus();
	System.out.print("requestStatus1 :" + HorseDisp.getRequestStatus());
    String firstName = (String)HorseDisp.getFirstName();
    String lastName = (String)HorseDisp.getLastName();
    String comments =(String)HorseDisp.getComments();
    String color = (String)HorseDisp.getColor();
    String gender = (String)HorseDisp.getGender();

    String height = (String)HorseDisp.getHeight();
    String yearFoaled = (String)HorseDisp.getYearFoaled();
    String breed = (String)HorseDisp.getBreed();
    String country1 = (String)HorseDisp.getCountry();
    String sire = (String)HorseDisp.getSire();
	String sireBreed = (String)HorseDisp.getSireBreed();
    String dam = (String)HorseDisp.getDam();
    String damBreed = (String)HorseDisp.getDamBreed();
    String breed2 = (String)HorseDisp.getBreed2();
    String sireBreed2 = (String)HorseDisp.getSireBreed2();
    String damBreed2 = (String)HorseDisp.getDamBreed2();
    String importedFrom = (String)HorseDisp.getImportedFrom();
    Date importDate = HorseDisp.getImportDate();
	String imp_dte ="";
	 
	if(importDate!=null){
		imp_dte = sdf.format(HorseDisp.getImportDate());
	}
	else{
		imp_dte="NG";
	}
    String foreignGrade = (String)HorseDisp.getForeignGrade();
    float foreignPoints = (float)HorseDisp.getForeignPoints();
	String assignedGrade = (String)HorseDisp.getAssignedGrade();
    float assignedPoints = (float)HorseDisp.getAssignedPoints();
    String notes = (String)HorseDisp.getNotes();
    String splNotes = (String)HorseDisp.getSplNotes();
	
	String regstrBy = (String)HorseDisp.getRegisteredBy();
	String membertype=(String)HorseDisp.getMemberTypeName();
	String statusName = (String)HorseDisp.getStatusName();
	 
 %>
<div class="colspan"><strong> Membership:</strong><span class="styleBoldTwo">Non-Human Registration Details:</span></div>
					<%
					String primaryOwner = (String)request.getAttribute("primaryOwner");
				    System.out.print("primaryOwner value:" + primaryOwner);
					System.out.print("statusName value:" + statusName);
					if(statusName!=null && statusName.equalsIgnoreCase("Active")){
					System.out.print("Inside the condition statusName value:" + statusName);%>	
						 <div class="alignLeft">
                                    <span>	
                                        <input type="button" class="gradBtn" name="Change Horse" value="Non-Human/Rider/Owner Change" onclick="location.href='changeOwnRidPay.do?process=dispdet&horseMemberId=<%=horseMemberId%>'"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </span>
									<%
									if(primaryOwner!=null && primaryOwner.equalsIgnoreCase("primaryOwner")){
									%>
                                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Add New Rider/Owner"  class="gradBtn" onclick="location.href='horserRidOwnAdd.do?process=addRid&horseMemberId=<%=horseMemberId%>&cardStatus=check'"/>
                                    </span>
									<%
									}else{
									%>
									 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Add New Rider/Owner"  class="gradBtn" onclick="location.href='horserRidOwnAdd.do?process=addRidOwn&horseMemberId=<%=horseMemberId%>&membertype=<%=membertype%>&competitionName=<%=competitionName%>'"/>
                                    </span>
									
									<input type="hidden" value="<%=competitionName%>" name="competitionName"/>
									<%}
									%>
                                </div>
						
				<%	}%>				 
			<form name="frmViewHorseReg" id="frmViewHorseReg" method="post" class="formcss" action="RegHorseListing.do" >
<div class="rowExpand" >  Horse Registration Details:</div>
					 
					<table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
						  <tr>
								<td width="163" height="27" class="tblRowHeadTwo"> Username</td>
								<td width="154" class="tblRowHeadTwo">Relationship Type </td>
								<td width="179" class="tblRowHeadTwo">Relationship Status</td>
					      </tr>
						<% String statuscheck = (String)request.getAttribute("err");
							if(statuscheck!=null && statuscheck.equals("st")){
							%>
							<tr>
							<td colspan="3" height="23" class="styleError"><strong>You are not authorized to update the demographic informations of this Horse.</strong></td>
							</tr>
							<%
							}
							ArrayList HorseDisp1 = (ArrayList) request.getAttribute("ListVO");
							String firstName1,last_name1,relationship_status1,relationship_type_id1,relationship_type_name1="";

							if(HorseDisp1!=null && HorseDisp1.size()!=0){
							Iterator itr = 	HorseDisp1.iterator();
							while(itr.hasNext()) {				   
								String[] val=(String[])itr.next();
								firstName1 = val[0];
								last_name1 =  val[1];	
								relationship_type_id1 = val[2];
								relationship_type_name1 = val[3];
								relationship_status1 =  val[4];
								%>
								 <tr>
									<td bgcolor="#E3E1D2" class="alignLeft"><%=firstName1%>&nbsp;<%=last_name1%></td>								
									<td bgcolor="#E3E1D2" class="alignLeft"><%=relationship_type_name1%></td>
									<td bgcolor="#E3E1D2" class="alignLeft"><%=relationship_status1%></td>
									</tr>
									<input type="hidden" value="<%=firstName1%>" name="firstName1"/>
							<input type="hidden" value="<%=relationship_type_name1%>" name="relationship_type_name1"/>
							<input type="hidden" value="<%=relationship_status1%>" name="relationship_status1"/>
							<%
							}
							} else
							{
						   %>
					
							  <tr>
								<td height="25" colspan="7" class="alignCenter"><strong>No Records Found!</strong></td>
							   </tr>
							<% } %>
							
						  </table>
						<div class="rowHead">
							Non-Human Registration Details:
						</div>
						

				
				<div class="row">
								<span class="label">Non-Human Registration Status:</span>
								<span class="formX"><span class="styleBoldOne"><%=nullCheck(statusName)%></span></span>
							</div>

						<div class="row">
								<span class="label">Non-Human Registration Type:</span>
								<span class="formX"><span class="styleBoldOne"><%=nullCheck(membertype)%></span></span>
							</div>
						<div class="rowHead">
							Non-Human Information Section:
						</div>
								<div class="row">
									<span class="label">Non-Human Name: </span>
									<span class="formX"><strong><%=nullCheck(competitionName)%></strong></span>
								</div>
						<div class="row">
								<span class="label">Registered By:</span>
								<span class="formX"><span class="styleBoldOne"><%=nullCheck(regstrBy)%></span></span>
							</div>								
								 
								<div class="row">
									<span class="label">Registered Name:</span>
									<span class="formX"><%=nullCheck(registeredName)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Past Name:</span>
									<span class="formX"><%=nullCheck(baPastName)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Breed Assoc. Horse is registered with:</span>
									<span class="formX"><%=nullCheck(baRegisteredName)%></span>
								</div>
						<div class="rowHead">
							Non-Human Description:
						</div>
								 
								<div class="row">
									<span class="label">Color:</span>
									<span class="formX"><%=nullCheck(color)%></span>
								</div>
								 							 
								<div class="row">
									<span class="label">Sex:</span>
									<span class="formX"><%=nullCheck(gender)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Height</span>
									<span class="formX"><%=nullCheck(height)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Year foaled:</span>
									<span class="formX"><%=nullCheck(yearFoaled)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Breed:</span>
									<span class="formX"><%=nullCheck(breed)%></span>
								</div>
								 					   
								<div class="row">
									<span class="label">Breed Two:</span>
									<span class="formX"><%=nullCheck(breed2)%></span>
								</div>	
								 
								<div class="row">
									<span class="label">Country of origin:</span>
									<span class="formX"><%=nullCheck(country1) %></span>
								</div>
								 
								<div class="row">
									<span class="label">Sire Name:</span>
									<span class="formX"><%=nullCheck(sire)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Sire Breed:</span>
									<span class="formX"><%=nullCheck(sireBreed)%></span>
								</div> 
								 							   	
								<div class="row">
									<span class="label">Sire Breed Two:</span>
									<span class="formX"><%=nullCheck(sireBreed2)%></span>
								</div>
							 
								<div class="row">
									<span class="label">Dam Name:</span>
									<span class="formX"><%=nullCheck(dam)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Dam Breed:</span>
									<span class="formX"><%=nullCheck(damBreed)%></span>
								</div>
								 							
								<div class="row">
									<span class="label">Dam Breed Two:</span>
									<span class="formX"><%=nullCheck(damBreed2)%></span>
								</div>
								 
								<div class="row">
									<span class="label">Imported From:</span>
									<span class="formX"><%=nullCheck(importedFrom)%></span>
								</div>
							 
								<div class="row">
									<span class="label">Date Imported:</span>
									<span class="formX"><%=imp_dte%></span>
								</div>
								 	
								<div class="row">
									<span class="label">Foreign Grade:</span>
									<span class="formX"><%=nullCheck(foreignGrade)%></span>
								</div>
							
								<div class="row">
									<span class="label">Foreign Points:</span>
									<span class="formX"><%=foreignPoints %></span>
								</div>
								  
								<div class="row">
									<span class="label">Assigned Grade:</span>
									<span class="formX"><%=nullCheck(assignedGrade)%></span>
								</div>
								<div class="row">
									<span class="label">Assigned Points:</span>
									<span class="formX"><%=assignedPoints%></span>
								</div>
								<div class="row">
									<span class="label">Comments:</span>
								 
									<span class="formX"><%=nullCheck(comments)%></span>
								</div>
						 				

							 
								
						<div class="alignCenter">
							<span><input type="button" value="Back" name="back" class="gradBtn" onclick="javascript:history.back(-1);"/>
						</span>
						</div>
<input type="hidden" value="<%=ownerId%>" name="ownerId"/>

</form>
			<!-- CONTENTS END HERE -->		 
			</div>
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