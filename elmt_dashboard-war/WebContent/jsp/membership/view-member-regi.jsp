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
<%@ page import="com.hlcform.util.*" %>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcmrm.util.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!--<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegValidate.js" type="text/javascript" ></script>-->
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script src="javascripts/calendar2.js" type="text/javascript"></script>

<script language="javascript">
function txtBxDisabEnab(txtBxId) {
	if(document.getElementById(txtBxId).disabled == true){
		if(document.getElementById(txtBxId).value == "Member Id"){
			document.getElementById(txtBxId).disabled = false;
			document.getElementById(txtBxId).value = "";
		}
	}else{
		document.getElementById(txtBxId).disabled = true;
		document.getElementById(txtBxId).value = "Member Id"
	}
}

function unhideMailOpts(divId,chkBxId) {
	if(document.getElementById(chkBxId).checked != false){
		document.getElementById(divId).style.display = "none";
		
		var n = document.frmMembRegi.pub_ct.value;
	
		for(i=0;i<n;i++){
		var str="mailOpt1"+i;
		document.getElementById(''+str).checked=false;
	
}
		
	}else {
		document.getElementById(divId).style.display = "block";
	}
}

function myval()
{
	/*if(document.frmMembRegi.uStatusId.value==""){
		alert(" Select any Status ");
		document.frmMembRegi.uStatusId.focus();
		return false;
	}*/
        //alert("inside");
		
        //--------------------------for non hlc  Membership Id  
        
    chbxnum1=document.frmMembRegi.orgCbxCt.value;
   for(i=0;i<chbxnum1;i++)
	{ 
	var chbxname = "orgNameCbx"+i;
	 var txtname="orgNameTxt"+i;
	 // alert(chbxname); 
	  if(document.getElementById(''+chbxname).checked)
	  {  var txtval= document.getElementById(''+txtname).value;
		 if(txtval=="" || document.getElementById(''+txtname).value.indexOf(' ')==0)
		  {alert("Enter the Non HLC Organization Member Id");
		  document.getElementById(''+txtname).focus();
		  return false;}
		  /*alert(document.getElementById(''+txtname).length);
		  if(document.getElementById(''+txtname).length>20)
		  {
			  alert("Non HLC Organization Member Id exceeds the Range");
		  	  document.getElementById(''+txtname).focus();
			  return false;
		  
		  }*/
		 // alert(chbxname);
		
	  }
	  //return false;
	}
  //-----------------------------------
 if(document.frmMembRegi.memTyp.value!="Subscribing Member" && document.frmMembRegi.memTyp.value!="Non-Competing Member")
  {
  if(document.frmMembRegi.amatName.value.length >80){ 
     alert("Amateur Name Range Exceeds 80 characters");
     document.frmMembRegi.amatName.focus();
     return false;
     }
	 
  if(document.frmMembRegi.amatName.value!=""){
  if(document.frmMembRegi.posAmat.checked==false && document.frmMembRegi.decAmat.checked==false){
alert("Check the proper option(s) in the Amateur Rider section");
document.frmMembRegi.amatName.focus();
    return false;
	 }
     }
	 
	if(document.frmMembRegi.posAmat.checked==true || document.frmMembRegi.decAmat.checked==true){
	if(document.frmMembRegi.amatName.value==""){
	alert("Enter Amateur name");
	document.frmMembRegi.amatName.focus();
    return false;
	}
	} 
      
   } 
return true;  
 }
 
 function tempValidate()
{
if(document.frmMembRegi.posAmat.checked==false && document.frmMembRegi.decAmat.checked==false){
	document.frmMembRegi.amatName.value="";
	return false;
	}
	else{
	document.frmMembRegi.amatName.value;
	return false;
	} 

return true;  
}

</script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->

</head>

<body>
<!-- <body onload="famAddOnInit(), initLife();">-->
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
   <%! 				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
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
				
					<div class="cmmnForm">
						<div class="colspan"><strong> Membership:</strong> <span class="styleBoldTwo">Membership Information</span></div>

					<!--
						<table cellpadding="0" cellspacing="0" border="0" class="container">
						<tr>
							<td id="tabData1" class="tabHighlight" onclick="naviTab('1')">
								<a id="link1" href="javascript:void(0);" class="active"><span id="1" class="tabHead"></span></A>
							</td>
							<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
							<td id="tabData2" class="tabLowlight" onclick="naviTab('2')">
								<a id="link2" href="javascript:void(0);" class="inactive"><span id="2" class="tabHead"></span></A>
							</td>
							<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
							<td id="tabData3" class="tabLowlight" onclick="naviTab('3')">
								<a id="link3" href="javascript:void(0);" class="inactive"><span id="3" class="tabHead"></span></A>
							</td>
							<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
							<td id="tabData4" class="tabLowlight" onclick="naviTab('4')">
								<a id="link4" href="javascript:void(0);" class="inactive"><span id="4" class="tabHead"></span></A>
							</td>
							<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
							<td id="tabData5" class="tabLowlight" onclick="naviTab('5')">
								<a id="link5" href="javascript:void(0);" class="inactive"><span id="5" class="tabHead"></span></A>
							</td>
							<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
							<td id="tabData6" class="tabLowlight" onclick="naviTab('6')">
								<a id="link6" href="javascript:void(0);" class="inactive"><span id="6" class="tabHead"></span></A>
							</td>
						</tr>
						</table>
					-->
						<form name="frmMembRegi" id="myform" method="post" class="formcss" action="./MembershipReg.do" onsubmit="return myval(this);">
								<div id="part1" class="holderDivOne" >
					<%   
																 HLCMemberDetails objMember = new HLCMemberDetails();
																 objMember=(com.hlcform.util.HLCMemberDetails)request.getAttribute("objMember");
																 String memTypsel=null; 
                                                                                                                                 
                                   
																ArrayList memPreference = (ArrayList)request.getAttribute("mailPreference");
																ArrayList familyAddOnId = (ArrayList)request.getAttribute("familyAddOnId");
																Vector areUAMemberVect=new Vector();
																areUAMemberVect=(Vector)request.getAttribute("areUAMemberVect");
																
																
								                                                                                                  Vector memberTypeVect3=new Vector();
                                                                                                                                    memberTypeVect3=(Vector)request.getAttribute("userTypeVect3");
                                                                                                                                    System.out.println("memberTypeVect3:"+memberTypeVect3);
                                                                                                                                    System.out.println("memberTypeVect3: SIZE  "+memberTypeVect3.size());
                                                                                                                                     String str1 [] = new String [15];
																																	   String str2[] = new String [15];
																																	   String str3[] = new String [15];
                                                                                                                                      if (  memberTypeVect3.elementAt(0) != null && memberTypeVect3.size() !=0)
                                                                                                                                     {
                                                                                                                                               str1   = (String[]) memberTypeVect3.elementAt(0);
                                                                                                                                     }	
																																	 if (  memberTypeVect3.elementAt(1) != null && memberTypeVect3.size() !=0)
                                                                                                                                     {
                                                                                                                                               str2   = (String[]) memberTypeVect3.elementAt(1);
                                                                                                                                     }	
																																	 if (  memberTypeVect3.elementAt(2) != null && memberTypeVect3.size() !=0)
                                                                                                                                     {
                                                                                                                                               str3   = (String[]) memberTypeVect3.elementAt(2);
                                                                                                                                     }	
  

                                                                                                                                     String parentId="N/A";
                                                                                                                                     if(str1[3]!=null){
                                                                                                                                      parentId =str1[3];
                                                                                                                                      }
		
																																		String joinDate = "N/A";																																	  if (str1[6] != null) {		
																																	  joinDate = str1[6];
																																	  String jD[] = joinDate.split(" ");
																																	  joinDate = jD[0];
																																	  }
																																	  String expDate = "N/A";
																																	  if (str1[7] != null){
																																	      expDate = str1[7];
																																		  String jD[] = expDate.split(" ");
																																		  expDate = jD[0];
																																		  }
																																		 																																		  String dob = "N/A";
																																		  if (str2[6] != null) {
																																		    dob = str2[6];
																																			String jD[] = dob.split(" ");																																			
																																			 dob = jD[0];																																			}
																																			String gendar = "N/A";
																																		  if (str2[7] != null) {
																																		    gendar = str2[7];
																																			}
																																			
																																			//contactTypeName, suite, add1, add2, city, state, country, zip
																																			String add1 = "";
																																			String add2 = "";
																																			String city = "";
																																			String state = "";
																																			String country = "";
																																			String areaName = "N/A";																																			
																																			String prefix1 = "";
																																			String firstName = "";
																																			String middleName ="";
																																			String lastName = "";
																																			String zip = "";
																																			String emailId = "";
																																			String suffix_nme = "";

																																			if(str3[2] != null) {
																																			add1=str3[2];
																																			}
																																			if (str3[3] != null) {
																																			add2 = str3[3];
																																			}
																																			if (str3[4] != null) {
																																			city = str3[4];
																																			}
																																			if (str3[5] != null){
																																			state = str3[5];
																																			}
																																			if (str3[6] != null) {
																																			country = str3[6];
																																			}
																																			if (str3[7] != null) {
																																			zip = str3[7];
																																			}
																																			if (str3[11] != null) {
																																			areaName = str3[11];
																																			}
																																			
																																			if(str2[1]!=null){
																																				prefix1  =str2[1];
																																			}
																																			if(str2[2]!=null){
																																				firstName  = str2[2];
																																			}
																																			if(str2[4]!=null){
																																				lastName  =str2[4];
																																			}
																																			if(str2[3]!=null){
																																				middleName = str2[3];
																																			}
																																			if(str2[5]!=null){
																																				suffix_nme = str2[5];
																																			}
																																			if(str2[8]!=null){
																																				emailId = str2[8];
																																			}


		%>							<div id="commonBG" class="textCommon" >You are viewing Membership Information for:<span class="rowHead">. <span class="styleBoldOne"><%=prefix1%>. <%=firstName%> <%=middleName%> <%=lastName%> <%=suffix_nme%></span></span></div>
													 <div class="colspan">
																 Member Details :													  </div>				

																<%
																String mtyp="";
	
																if(objMember.getMembershipTypeName()!=null && objMember.getMembershipTypeName().trim().length()!=0)
																{
																	mtyp=objMember.getMembershipTypeName();
																}
																else
																{
																	mtyp="N/A";
																}
																%>
													<div class="row">
														<span class="label">Member ID:</span>		
													<span class="label">
														<%=objMember.getMemberId()%>																		
												    </span>	
													
													</div>
													<%
													boolean curr_link = false;
													ArrayList histValues = (ArrayList) request.getAttribute("histValues");
													if(histValues!=null && histValues.size()!=0){
													Iterator histItr = histValues.iterator();
													while(histItr.hasNext()){
													String[] histval = (String[]) histItr.next();
													String membTypName = histval[0];
													String membYr = histval[1];
													String statName = histval[2];
													String ExpDte = histval[3];		
													java.util.Calendar toDay = java.util.Calendar.getInstance();
													int newyear = toDay.get(Calendar.YEAR);
													int new_month = toDay.get(Calendar.MONTH);	
													int dbYear =  Integer.parseInt(membYr);

													if(curr_link==false){
														curr_link =true;
													%>
														<div class="colspan"><strong>Current Year:</strong></div>
													<%
													}
													else{%>
														<div class="colspan"><strong>Upcoming Year:</strong></div>
													<% } %>
													<div class="row">
														<span class="label">Membership Type:</span>
														<span class="label"><%=membTypName%> </span>													
													</div>
													<div class="row">
														<span class="label">Membership Status:</span>
														<span class="label"><%=statName%></span>
													</div>
														<%
														if(curr_link==false){
														if(ExpDte!=null){
														if(statName!=null){
														if(statName.equalsIgnoreCase("Active")){
															String[] tmpDte = ExpDte.split("-");
															if(Integer.parseInt(tmpDte[0])<=newyear){
																if(new_month>=9 && new_month<11){
														%>
															<div class="row">
																<span class="label">Renewal Status :</span>
																<span class="label">														
																	<strong>Not Renewed!&nbsp;&nbsp;&nbsp;<a href="./MembershipReg.do?process=renew">Renew Now!</a></strong>
																</span>
															</div>
														<%
																}															
															}
														}
														else{
														  if(!(statName.equalsIgnoreCase("Pending"))){
														%>
															<div class="row">
																<span class="label">Renewal Status :</span>
																<span class="label">														
																<strong>&nbsp;<a href="./MembershipReg.do?process=renew">Renew Now!</a></strong>
																</span>
															</div>
														<%
															}
															}
														}
														}
														}
														%>
														
													<%if(session.getAttribute("loggedBy")!=null){%>
													<div class="row">
														<span class="label">Expiration Date:</span>
														<%
															if(membTypName.equalsIgnoreCase("Life Member")){
																ExpDte = "No Expiry Date For Life Members";
															}
														if(ExpDte==null || ExpDte.equalsIgnoreCase("null") || ExpDte.trim().length()==0)  ExpDte = "N/A";	
														if(ExpDte!=null && !(ExpDte.equalsIgnoreCase("No Expiry Date For Life Members"))){
															String[] eDte = ExpDte.split("-");
														%>
														<span class="label">
															<%=eDte[1]%>-<%=eDte[2]%>-<%=eDte[0]%>
													  </span>
														<%
														}else{
														%>
														<span class="label">No Expiry Date For Life Members</span>
														<%}%>
												  </div>
												  	
													<%}else{
													if(!(statName.equalsIgnoreCase("Pending"))){%>
													<div class="row">
														<span class="label">Expiration Date:</span>
														<%
															if(membTypName.equalsIgnoreCase("Life Member")){
																ExpDte = "No Expiry Date For Life Members";
															}
														if(ExpDte==null || ExpDte.equalsIgnoreCase("null") || ExpDte.trim().length()==0)  ExpDte = "N/A";	
														if(ExpDte!=null && !(ExpDte.equalsIgnoreCase("No Expiry Date For Life Members"))){
															String[] eDte = ExpDte.split("-");
														%>
														<span class="label">
															<%=eDte[1]%>-<%=eDte[2]%>-<%=eDte[0]%>
													  </span>
														<%
														}else{
														%>
														<span class="label">No Expiry Date For Life Members</span>
														<%}%>
												  </div>													
													<%
													}
													}
													}
													}
													else{
													%>
													 <div class="colspan">
																Member Information :
								  </div>				
														<%
														if(objMember.getMembershipTypeName()!=null && objMember.getMembershipTypeName().trim().length()!=0){
															mtyp=objMember.getMembershipTypeName();
														}
														else{
															mtyp="N/A";
														}
														%>
															<div class="row">
																<span class="label">Membership Type:</span>
																<span class="label"><%=mtyp%></span>
															</div>
													<%
																String tmpMemId="";
																tmpMemId=objMember.getMemberId(); 
																
																String status="";
																
																if(objMember.getStatusName()!=null)
																{
																status=objMember.getStatusName();
																}
																else
																{
																status="N/A";
																}
															 %>
															 
															<div class="row">
																<span class="label">Membership Status :</span>
																<span class="label">
																	   <span class="styleBoldOne"><%=status%></span>
																</span>
															</div>
														<%
														java.util.Calendar toDay = java.util.Calendar.getInstance();
														int newyear = toDay.get(Calendar.YEAR);
														int new_month = toDay.get(Calendar.MONTH);
														System.out.print("expDate														"+expDate);
														if(expDate!=null){
														if(status!=null){
														if(status.equalsIgnoreCase("Active")){
															String[] tmpDte = expDate.split("-");
															if(Integer.parseInt(tmpDte[0])<=newyear){
																if(new_month>=9 && new_month<11){
														%>
															<div class="row">
																<span class="label">Membership Status :</span>
																<span class="label">														
																	<strong>Not Renewed!&nbsp;&nbsp;&nbsp;<a href="./MembershipReg.do?process=renew">Renew Now!</a></strong>
																</span>
															</div>
														<%
																}															
															}
														}
														else{
														  if(!(status.equalsIgnoreCase("Pending"))){
														%>
															<div class="row">
																<span class="label">Renewal Status :</span>
																<span class="label">														
																<strong>&nbsp;<a href="./MembershipReg.do?process=renew">Renew Now!</a></strong>
																</span>
															</div>
														<%
															}
															}
														}
														}
														//}
														//new_month
														%>	
																</span>
						  </div>					<%if(session.getAttribute("loggedBy")!=null){%>
													<%if(!mtyp.equalsIgnoreCase("Life Member")){%>
															<div class="row">
																<span class="label">Expiration Date:</span>		
															<span class="label">
			 												<%
															if(expDate!=null){
															String[] eDte = expDate.split("-");
															%>
															<%=eDte[1]%>-<%=eDte[2]%>-<%=eDte[0]%>
															<%
															}
															%>
															</span>	
															</div>													
														<%}else{%>
															<div class="row">
															<span class="label">Expiration Date:</span>
															<span class="label">No Expiry Date For Life Members</span>
															</div>
														<%}}else{
														if(!(status.equalsIgnoreCase("Pending"))){
														%>
														<%if(!mtyp.equalsIgnoreCase("Life Member")){%>
															<div class="row">
																<span class="label">Expiration Date:</span>		
															<span class="label">
			 												<%
															if(expDate!=null){
															String[] eDte = expDate.split("-");
															%>
															<%=eDte[1]%>-<%=eDte[2]%>-<%=eDte[0]%>
															<%
															}
															%>
															</span>	
															</div>													
														<%}else{%>
															<div class="row">
															<span class="label">Expiration Date:</span>
															<span class="label">No Expiry Date For Life Members</span>
															</div>
														<%}}}}%>
													<!--<div class="row">
																<span class="label">Membership Type:</span>
																<span class="label">
																			   < %=mtyp%>
																		
																</span>
													</div>-->
													
													<%
															/*	String tmpMemId="";
																tmpMemId=objMember.getMemberId(); 
																
																String status="";
																
																if(objMember.getStatusName()!=null)
																{
																status=objMember.getStatusName();
																}
																else
																{
																status="N/A";
																}/*
															 %>
															 
															<div class="row">
																<span class="label">Membership Status :</span>
																<span class="label">
																	   <span class="styleBoldOne">< %=status%></span>																		
																</span>
															</div>	

													
													<%*/
													
													if(!mtyp.equalsIgnoreCase("Life Member"))
	  												{%>
	 
<!--													<div class="row">
														<span class="label">Expiration Date:</span>		
													<span class="label">
														< %=dateFormat(expDate)%>																		
												    </span>-->
															
													<%}%>
													
													
													<div class="colspan">
															<strong>Personal / Contact Details:</strong>
													</div>													
													<div class="row">
														<span class="label">Join Date:</span>		
													<span class="label">
														<%=dateFormat(joinDate) %>																		
												    </span>		
													
													</div>
													
													<div class="row">
														<span class="label">Date of Birth:</span>		
													<span class="label">
														<%=dateFormat(dob)%>																		
												    </span>		
													
													</div>
													
													<div class="row">
														<span class="label">Gender:</span>		
													<span class="label">
														<%=gendar%>																		
												    </span>	
													</div>	
													<div class="row">
														<span class="label">Area:</span>		
													<span class="label">
														<%=areaName%>																		
												    </span>	
													</div>
													<div class="row">
														<span class="label">Email:</span>		
													<span class="label">
														<strong><A HREF="mailto:<%=emailId%>"><%=emailId%></A></strong>
												    </span>	
													</div>													
													
													<%
														if(!mtyp.equalsIgnoreCase("Family Member"))
																	{
													%>
													
													<div class="colspan">
															<strong>Mailing Preferences:</strong>
													</div>	
																<div class="row">
																<span class="labelFull"><input type="checkbox" name="mailOptQuesti" id="mailOptQuesti" value="" onclick="unhideMailOpts('hideMail','mailOptQuesti');"/></span>
																		Do not send publications.
																
																</div>										
													<%
																		int chkInd=0;
																		String chk_id="";
																																				
																		ArrayList DispPublication= (ArrayList)request.getAttribute("DispPubDetails");
																		//out.print("DispPublication" + DispPublication);
																		if(DispPublication.size()!=0 && DispPublication!=null)
																		{%>
																		
																		<div class="row" id="hideMail">	
																		
																		<%
																		Iterator itr = DispPublication.iterator();
																		
																		while(itr.hasNext()){
																		HLCPublicationVO donObj = (HLCPublicationVO) itr.next();
																		String publicationId = donObj.getPublicationId();
																		String publicationName = donObj.getPublicationName();
																		chk_id="mailOpt1"+chkInd;
																		
																		String chk_st="";
																		String mailPreference1 = "";
																		if(memPreference !=null && memPreference.size()!=0){
																																		
																		Iterator it = memPreference.iterator();
																		while(it.hasNext()){
																		mailPreference1 = (String)it.next();
																		System.out.print("memPreference.size() :"+memPreference.size());	
																		System.out.print("publicationName :"+publicationName);
																		System.out.print("mailPreference1 :"+mailPreference1);
																																				
																		if(publicationName.equalsIgnoreCase(mailPreference1))
																		{
																      		chk_st = "checked";
																		}
																		}}
																		%>

																<div class="row">

																	<%
																	if(!mtyp.trim().equalsIgnoreCase("Subscribing Member")){
																	%>
																	<span class="labelFull">
																		<input type="checkbox" name="mailOpt1" <%=chk_st%> id="<%=chk_id%>" value="<%=publicationId%>" />
																		<%=publicationName%>.
																	</span>
																	<%
																	}
																	else{
																	if(publicationName.trim().equalsIgnoreCase("Send Me Eventing USA magazines")){
																	%>
																	<span class="labelFull">
																		<input type="checkbox" name="mailOpt1" <%=chk_st%> id="<%=chk_id%>" value="<%=publicationId%>" />
																		<%=publicationName%>.
																	</span>
																<% }
																} %>
																</div>
																<%chkInd++;}%>
																	</div>
																	
																	<input type="hidden" value="<%=DispPublication.size()%>" name="pub_ct" />
																	
																<%}}%>
													
                                                                                                                                <br /><br /><br />
															  	<table width="100%" cellpadding="0" cellspacing="1" border="0">
																
																	
																  <tr>
																	<td colspan="2" class="tblRowHead">Payment Information: </td>
																  </tr>
																 
																 <%
																 HLCPaymentDetails objPayment = new HLCPaymentDetails();
																 
																 objPayment=(HLCPaymentDetails)request.getAttribute("objPayment");
																 System.out.println("objPayment jsp :"+objPayment.toString());
																 
																 if(objPayment != null)
																 {
																 
																if(objPayment.getPaymentStatus()!=null)
																{
																if(objPayment.getPaymentStatus().equalsIgnoreCase("card"))
																{
																%>
																
																  <tr>
																	<td class="tableLeft">Payment Mode: </td>
																	<th class="tableRight"><%=objPayment.getPaymentStatus()%></th>
																  </tr>	
																<%
 
                                                                                                                                   String ccType="N/A";
																																   
 if(objPayment.getCcType()!=null){                                                                                                                                    ccType=objPayment.getCcType();
                                                                                                                                    }
                                                                                                                                    String namCard="N/A";
  
   if(objPayment.getCcName()!=null)
   {                                                                                                                                      namCard=objPayment.getCcName();
   }                                                                                                                    
                                                                                                                                    String cardNo="N/A";

if(objPayment.getCcNumber()!=null)
   {	                                                                                                                            cardNo=objPayment.getCcNumber();
   
   } 
                                                                                                                                    String txId="N/A";
                                                                                                                                    
                                                                                                                                    if(objPayment.getSslTxnId()!=null)
                                                                                                                                    {	
                                                                                                                                        txId=objPayment.getSslTxnId();
                                                                                                                                    }
                                                                                                                                    
                                                                                                                                    %>
  																  <tr>
																	<td class="tableLeft">Card Type: </td>
																	<th class="tableRight"><%=ccType%></th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Name on Card: </td>
																	<th class="tableRight"><%=namCard%></th>
																  </tr>	
																  
																  <%--<tr>
																	<td class="tableLeft">Card No: </td>
																	<th class="tableRight"><%=cardNo%></th>
																  </tr>	--%>
																  
                                                                                                                                   <tr>
																	<td class="tableLeft">Transaction Id : </td>
																	<th class="tableRight"><%=txId%></th>
																   </tr>	
                                                                                                                                  
																  <tr>
																	<td class="tableLeft">Payment Date: </td>
																	<th class="tableRight"><%=sd1.format(objPayment.getPaymentDate())%></th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Amount: </td>
																	<th class="tableRight"><%=objPayment.getAmount()%></th>
																  </tr>
																  
																  <tr>
																	<td class="tableLeft">Payment Status: </td>
																	<th class="tableRight"><%=objPayment.getSslResultMessage()%></th>
																  </tr>
																  
																  <%
                                                                                                                                  if(objPayment.getSslResult()!=null && objPayment.getSslResult().trim().length()!=0 )
                                                                                                                                      {
                                                                                                                                if(objPayment.getSslResult().equalsIgnoreCase("1"))
                                                                                                                                    {
                                                                                                                                        session.setAttribute("amt",String.valueOf(objPayment.getAmount()));
                                                                                                                                        
                                                                                                                                        %>
      <tr>
                                                                                                                                            <td class="tableLeft"><span class="styleBoldOne">Payment Declined for You </span> </td>
                                                                                                                                            <th class="tableRight"><span class="styleBoldOne"><a href="./AdminMembPayment.do?process=disp&usr=user&cardStatus=null&pid=<%=objPayment.getPaymentId()%>">Retry Payment</a></span></th>
                                                                  </tr>
                                                                                                                                    
                                                                                                                                    <%}}															  
																 }else{%>
																 
																 <tr>
																	<td class="tableLeft">Payment Mode: </td>
																	<th class="tableRight"><%=objPayment.getPaymentStatus()%></th>
																  </tr>	
																 
																  <tr>
																	<td class="tableLeft">Check No: </td>
																	<th class="tableRight"><%=objPayment.getCheckNumber()%></th>
																  </tr>
																  
																  <tr>
																	<td class="tableLeft">Bank Name: </td>
																	<th class="tableRight"><%=objPayment.getBankName()%></th>
																  </tr>
																 
																 <tr>
																	<td class="tableLeft">Check Date: </td>
																	<th class="tableRight"><%=sd1.format(objPayment.getCheckDate())%></th>
																  </tr>
																 
																 <tr>
																	<td class="tableLeft">Payment Date: </td>
																	<th class="tableRight"><%=sd1.format(objPayment.getPaymentDate())%></th>
																  </tr>	
																  

																  <tr>
																	<td class="tableLeft">Amount: </td>
																	<th class="tableRight"><%=objPayment.getAmount()%></th>
																  </tr>
																 
																 <%
																 
																 try
																 {
													 
	    														 	String memId1=(String)session.getAttribute("memberId");
																	 session.setAttribute("status_membId",memId1);
																 
																 	String chkAmt=String.valueOf(objPayment.getCheckAmount());
																	System.out.println("objPayment.getCheckAmount() :"+chkAmt);
																	
																	String totAmt1=String.valueOf(objPayment.getAmount());
																	System.out.println("objPayment.getAmount() :"+totAmt1);
																	
																	double at=Double.parseDouble(totAmt1) ;
    																 double ct=Double.parseDouble(chkAmt) ;
																	
																	/*if(!chkAmt.equalsIgnoreCase("0.0"))
																	{
																	
																	if(objPayment.getCheckAmount()<objPayment.getAmount())
																	{                                                                                                                                       
																			 double at=Double.parseDouble(totAmt1) ;
																			 double ct=Double.parseDouble(chkAmt) ;
																			 
																			 System.out.println("totAmt1 :"+at);
																			 System.out.println("chkAmt :"+ct);
																			 
																			 double finalamt=at-ct;
																			 System.out.println("finalamt :"+finalamt);
																			
																			session.setAttribute("amt",String.valueOf(finalamt));*/
                                                                                                                                                        
                                                                                                                                                        float pendAmt = objPayment.getPendingAmount();
                                                                                                                                                        
                                                                                                                                                        System.out.println("Pending Amt in JSP :"+pendAmt);
                                                                                                                                                        if(objPayment.getPendingAmount()>0)
                                                                                                                                                                {
                                                                                                                                                              session.setAttribute("amt",String.valueOf(pendAmt));
                                                                                                                                                        
																	%>
																	
                                                                                                                                 <tr>
																	<td colspan="2" class="tblRowHead">Pending Payment Information: </td>
																 </tr>
                                                                                                                                       
    															         <tr>
																	<td class="tableLeft">Check Amount Recieved: </td>
																	<th class="tableRight"><%=ct%></th>
																  </tr>
																	
																  <tr>
																	<td class="tableLeft">Amount in Due : </td>
																	<th class="tableRight"><%=pendAmt%> &nbsp;<span class="styleBoldOne"><a href="./AdminMembPayment.do?process=disp&usr=user&cardStatus=null&defStat=yes&pid=<%=objPayment.getPaymentId()%>">Complete Payment</a></span></th>
																  </tr>
																  																																	
																	<%}}
																	catch(Exception e)
																	{
																		System.out.println(" error in jsp :"+e);
																	}
																	%>
																	
																
																 
																 <%}}else{%>
																 
																 <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Payment Details Available for this Member !</span></td>        
																  </tr>
																  

																<%}}else{%>
																 
																 <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Payment Details Available for this Member !</span></td>        
																  </tr>                                                                                                                                   <%}%>
																</table>
						  													
                                                                                                                
                                                                                                                                <input type="hidden" name="process" value="editMemb" />
<input type="hidden" name="memTyp" value="<%=mtyp%>" />																																
                                                      
											<div class="alignCenter">
<input type="button" value=" Back " class="gradBtn" onclick="location.href='index.jsp'" />
                                                                                                                                <input class="gradBtn" type="submit" value="Update" name="update"/>
										</div>	
											
                                                                                          	
							 
							<input type="hidden" value="<%=objMember.getMemberId()%>" name="memberId"/>
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
