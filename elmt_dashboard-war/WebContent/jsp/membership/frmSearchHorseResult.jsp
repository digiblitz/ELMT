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
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlccommon.util.HLCHorseSearchVO" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
 <script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript">
function regBtn(){
	strURL = "HorseReg.do?process=HorseReg";
	window.location.href = strURL;
}


function noBtn(){
	strURL = "SearchHorse.do?searchProcess=initView";
	window.location.href=strURL;
}
</script>
 <script type="text/javascript" >
var radioFlag=false; 
function validateUser(){
	if(document.frmUserSignup.status.value=="exist"){
		chosen="";
		len2 = document.frmUserSignup.quest_rad.length;
		//alert("len2");
		for(j=0;j<len2;j++){
			if(document.frmUserSignup.quest_rad[j].checked){
				chosen= document.frmUserSignup.quest_rad[j].value; 
				if(chosen=='yes'){
					if(!radioFlag){
						alert("Select any of the Non-Human Detail");
						radioFlag=false;
						 return false;
					}
				}
			}
		}
	}
	return true;
}

function radioHorse(){
 radioFlag=true;
 }
  
  
 </script>
 <%! String nullCheck(String fieldName) {
 	String retValue = "NG";
	if(fieldName!=null &&fieldName.trim().length()!=0) {
		retValue = fieldName;
	}
 	return  retValue;
 }
 %>

<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Non-Human Search: <span class="styleBoldTwo">Account Matching Result</span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp">To view Non-Human Registration details, click the ID link.

To create a PDF version of the Non-Human Profile report, active members can click the Non-Human Name link. PDF software like Adobe Acrobat is required.
							</td>
					  </tr>
							  <tr>
								<td>
								
								<form name="frmUserSignup" id="frmUserSignup" action="SearchHorse.do" method="post"  onsubmit="return validateUser();">		  
								<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
									  <tr>
										<td colspan="2" class="tblRowHead">&nbsp;Matching Results:</td>
									  </tr>
									  
									  
									  
									  
									  
									  <tr>
										<td height="25" colspan="2" class="tableSpan"><span class="styleBoldTwo">Note:</span><span class="styleBoldOne"> Reg </span> <strong>-Registration, <span class="styleBoldOne">NG </span>- Not Given</strong></td>
								  	 </tr> 

									 
											<tr>
											  <td colspan="2" height="25" class="alignCenter">
											  
											  
											  <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                                <tr>
                                           <td width="13%" height="25" class="tblMainHead">ID </td>
                                                  <td width="18%" height="25" class="tblMainHead">Non-Human Name </td>
                                                  
                                                  <td width="15%" height="25" class="tblMainHead">Rider Name (primary)</td>
                                                  <td width="16%" height="25" class="tblMainHead">Owner Name (primary) </td>
                                                  <td width="15%" height="25" class="tblMainHead">Reg Level </td>
                                               
                                                  <td width="15%" height="25" class="tblMainHead">Reg Status </td>
                                                </tr>
	
							<%
							HLCHorseSearch2VO usrVal1=new HLCHorseSearch2VO();
							int j=0;
							int count1=0;
							ArrayList usrDet1=new ArrayList();
							usrDet1=(java.util.ArrayList)request.getAttribute("horseResultDetails");
							if(usrDet1!=null){
								for(j=0;j<usrDet1.size();j++){
								usrVal1=(HLCHorseSearch2VO)usrDet1.get(j);
								String statusName1 =usrVal1.getStatusName() ; 
									if((statusName1!=null && statusName1.equalsIgnoreCase("Duplicate"))||(statusName1!=null && statusName1.equalsIgnoreCase("Merged"))){
									count1=count1+1;
									}
								}
							}
							System.out.println("usrDet1 :"+usrDet1.size());
							System.out.println("count1 :"+count1);
							if(session.getAttribute("loggedBy")== null){
							if(usrDet1.size()!=count1){ 
								String memStatus=(String) request.getAttribute("memberStatus");
								String riderFirstName = ""; 
								String riderLastName = ""; 
								String ownerFirstName = ""; 
								String ownerlastName = ""; 
								String horseMemberId = ""; 
								String horseName = ""; 
								String membershipTypeId = ""; 
								String membershipTypeName  = ""; 
								String statusName  = ""; 
								String prname ="";
								String poname ="";
								String colorDesc = "" ;
								String gender = "";
								String height = "";
								String yearFoaled = "";
								String breedDesc = "";
				
					ArrayList horseVal = (ArrayList)request.getAttribute("horseResultDetails");
					if(horseVal!=null && horseVal.size()!=0){
					Iterator itr = horseVal.iterator();
					while(itr.hasNext()) {
					 
						HLCHorseSearch2VO objSearchHorse =(HLCHorseSearch2VO)itr.next();
						riderFirstName = objSearchHorse.getRiderFirstName() ; 
						riderLastName = objSearchHorse.getRiderLastName(); 
						ownerFirstName = objSearchHorse.getOwnerFirstName(); 
						ownerlastName = objSearchHorse.getOwnerlastName()  ; 
						horseMemberId = objSearchHorse.getHorseMemberId() ; 
						horseName = objSearchHorse.getHorseName(); 
						membershipTypeId = objSearchHorse.getMembershipTypeId(); 
						membershipTypeName = objSearchHorse.getMembershipTypeName(); 
						statusName =objSearchHorse.getStatusName() ; 
						colorDesc = objSearchHorse.getColorDesc();
						gender = objSearchHorse.getGender();
						height = objSearchHorse.getHeight();
						yearFoaled = objSearchHorse.getYearFoaled();
						breedDesc = objSearchHorse.getBreedDesc();
					  	prname = riderFirstName +" "+ riderLastName;
						poname = ownerFirstName +" "+ ownerlastName;
				  
					  if(objSearchHorse.getHorseMemberId()!=null) {
                     	 horseMemberId = objSearchHorse.getHorseMemberId();
					  }
					  else{
					   horseMemberId = "NG";
					  }
					   if(objSearchHorse.getMembershipTypeId()!=null) {
                     	membershipTypeId = objSearchHorse.getMembershipTypeId();
					  }
					  else{
					   membershipTypeId = "NG";
					  }
					   if(objSearchHorse.getMembershipTypeName()!=null) {
                     	  membershipTypeName = objSearchHorse.getMembershipTypeName();
					  }
					  else{
					    membershipTypeName = "NG";
					  }
					   
					   		if((statusName!=null) && (!(statusName.equalsIgnoreCase("Duplicate")))){	
											  System.out.println("1");
											  if((statusName!=null) && (!(statusName.equalsIgnoreCase("Merged")))){
											  
					%>
                                                <tr>
												   <td height="25" class="listCellBg">
												   <a href="RegHorseListing.do?process=horseDet&amp;memid=<%=horseMemberId%>"><%=horseMemberId%></a></td>
												  <% if(memStatus!=null && memStatus.equalsIgnoreCase("Active")){%>
                                                  <td height="25" class="listCellBg">
												
												  
												 <a href="http://reports.useventing.com/ReportServer?/Public/Horse_Profile_Public&rs:Command=Render&rs:format=PDF&HORSEID=<%=horseMemberId%>" target="_blank"> <%=horseName%> </a></td>
												  <%}else{%>
												  <td height="25" class="listCellBg">
												
												  
												  <%=horseName%> </td>
												  <%}%>
												 
												  
												 
                                                 <td height="25" class="listCellBg"><%=prname%></td>
                                                  <td height="25" class="listCellBg"><%=poname%></td> 
                                                 
                                                  <td height="25" class="listCellBg"><%=membershipTypeName%></td>
                                                  
                                                  <td height="25" class="listCellBg"><%=statusName%></td>
                                                </tr>
												 <tr>
														<td height="25" colspan="7" class="listCellBg1">
														<span class="textGrey">
														<%=nullCheck(yearFoaled)%> ,&nbsp;<%=nullCheck(colorDesc)%>,&nbsp; <%=nullCheck(breedDesc)%>,&nbsp; <%=nullCheck(gender)%>,&nbsp; <%=nullCheck(height)%>													
														</span>
														</td>
												</tr>
												<%
												}
												}
												}
                 								}
												}
								           else{
												%>
											<tr>
											<th colspan="9" height="20" class="listCellBg"><span class="alignCenter">No records are available</span></th>
											</tr> 
												
												<%
												}}else{
												System.out.println("testing");
												
												String memStatus=(String) request.getAttribute("memberStatus");
				 
                String riderFirstName = ""; 
                String riderLastName = ""; 
                String ownerFirstName = ""; 
                String ownerlastName = ""; 
                String horseMemberId = ""; 
                String horseName = ""; 
                String membershipTypeId = ""; 
                String membershipTypeName  = ""; 
                String statusName  = ""; 
				String prname ="";
				String poname ="";
				String colorDesc = "" ;
                String gender = "";
                String height = "";
                String yearFoaled = "";
                String breedDesc = "";
				
					ArrayList horseVal = (ArrayList)request.getAttribute("horseResultDetails");
					if(horseVal!=null && horseVal.size()!=0){
					Iterator itr = horseVal.iterator();
					while(itr.hasNext()) {
					 
						HLCHorseSearch2VO objSearchHorse =(HLCHorseSearch2VO)itr.next();
						riderFirstName = objSearchHorse.getRiderFirstName() ; 
						riderLastName = objSearchHorse.getRiderLastName(); 
						ownerFirstName = objSearchHorse.getOwnerFirstName(); 
						ownerlastName = objSearchHorse.getOwnerlastName()  ; 
						horseMemberId = objSearchHorse.getHorseMemberId() ; 
						horseName = objSearchHorse.getHorseName(); 
						membershipTypeId = objSearchHorse.getMembershipTypeId(); 
						membershipTypeName = objSearchHorse.getMembershipTypeName(); 
						statusName =objSearchHorse.getStatusName() ; 
						colorDesc = objSearchHorse.getColorDesc();
						gender = objSearchHorse.getGender();
						height = objSearchHorse.getHeight();
						yearFoaled = objSearchHorse.getYearFoaled();
						breedDesc = objSearchHorse.getBreedDesc();
					  	prname = riderFirstName +" "+ riderLastName;
						poname = ownerFirstName +" "+ ownerlastName;
						
					 
					  
					  if(objSearchHorse.getHorseMemberId()!=null) {
                     	 horseMemberId = objSearchHorse.getHorseMemberId();
					  }
					  else{
					   horseMemberId = "NG";
					  }
					   if(objSearchHorse.getMembershipTypeId()!=null) {
                     	membershipTypeId = objSearchHorse.getMembershipTypeId();
					  }
					  else{
					   membershipTypeId = "NG";
					  }
					   if(objSearchHorse.getMembershipTypeName()!=null) {
                     	  membershipTypeName = objSearchHorse.getMembershipTypeName();
					  }
					  else{
					    membershipTypeName = "NG";
					  }
					  
					%>
                                                <tr>
												   <td height="25" class="listCellBg">
												   <a href="RegHorseListing.do?process=horseDet&memid=<%=horseMemberId%>"><%=horseMemberId%></a></td>
												  <% if(memStatus!=null && memStatus.equalsIgnoreCase("Active")){%>
                                                  <td height="25" class="listCellBg">
												
												  
												 <a href="http://reports.useventing.com/ReportServer?/Public/Horse_Profile_Public&rs:Command=Render&rs:format=PDF&HORSEID=<%=horseMemberId%>" target="_blank"> <%=horseName%> </a></td>
												  <%}else{%>
												  <td height="25" class="listCellBg">
												
												  
												  <%=horseName%> </td>
												  <%}%>
                                                 <td height="25" class="listCellBg"><%=prname%></td>
                                                  <td height="25" class="listCellBg"><%=poname%></td> 
                                                 
                                                  <td height="25" class="listCellBg"><%=membershipTypeName%></td>
                                                  
                                                  <td height="25" class="listCellBg"><%=statusName%></td>
                                                </tr>
												 <tr>
														<td height="25" colspan="7" class="listCellBg1">
														<span class="textGrey">
														<%=nullCheck(yearFoaled)%> ,&nbsp;<%=nullCheck(colorDesc)%>,&nbsp; <%=nullCheck(breedDesc)%>,&nbsp; <%=nullCheck(gender)%>,&nbsp; <%=nullCheck(height)%>													
														</span>
														</td>
												</tr>
												<%
												}
                 								}
								           else{
												%>
											<tr>
											<th colspan="9" height="20" class="listCellBg"><span class="alignCenter">No records are available</span></th>
											</tr> 
												
												<%
												}
												%>
												<%}
												%>
                                              </table>											  </td>
								  </tr>
											
									
										 
									<tr>
										<td colspan="2" class="alignCenter">
										
 
<!--  /*  <input name="button" type="button" class="gradBtn" value="Yes, Register Me" onclick="regBtn();" />
    &nbsp;
    <input name="button" type="button" class="gradBtn" value="No, Try Again" onclick="noBtn();" />*/-->

										
<input type="button" value="Try Again" class="gradBtn" onclick="noBtn();"  />&nbsp;&nbsp;
<input type="button" value="Register New Non-Human" class="gradBtn" onclick="regBtn();"/> 
</td>
								   </tr>
								</table>
							</form>
						</td>
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
