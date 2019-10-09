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
<%@ page import="java.text.*"%>
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlcform.util.DBHelper" %>
<%@ page import="com.hlchorse.form.util.HLCUserRegVO" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMembSearchApprove.js" type="text/javascript" ></script>

<!--<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegValidate.js" type="text/javascript" ></script>-->
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script language="javascript">

function currentDate()
{	
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowMonth1=todayDate.getMonth();
	var nowYear=todayDate.getYear();
	var nowMonth=1+nowMonth1;
	if(nowDate<10){nowDate="0"+nowDate;}
	if(nowMonth<10){nowMonth="0"+nowMonth;}
	//document.myform.approvalDate.value = nowMonth+"/"+nowDate+"/"+nowYear;
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
						<div class="colspan"><strong>USEA Membership:</strong> <span class="styleBoldTwo">Membership Information</span> </div>
						
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
						<form name="frmMembRegi" id="myform" method="post" class="formcss" action="./MembershipReg.do" onsubmit="return myval();">
						<input type="hidden" name="process" value="update"/> 
								<div id="part1" class="holderDivOne" >
					<%   
																 HLCMemberDetails objMember = new HLCMemberDetails();
																 objMember=(com.hlcform.util.HLCMemberDetails)request.getAttribute("objMember");
																 String memTypsel=null; 
                                                                                                                                 
                                   
																ArrayList memPreference = (ArrayList)request.getAttribute("mailPreference");
																ArrayList familyAddOnId = (ArrayList)request.getAttribute("familyAddOnId");
                                                                                                                                session.setAttribute("familyAddOnId",familyAddOnId);
                                                                                                                                                                  
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
																																			String zip = "";
																																			String areaName = "N/A";
																																			String prefix1 = "";
																																			String firstName = "";
																																			String middleName ="";
																																			String lastName = "";																																			String emailId = "";
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
	                                                                                                                          
		%>		
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
						<div id="commonBG" class="textCommon" >You are viewing Membership Information for:<span class="rowHead"><span class="styleBoldOne"><%=prefix1%>. <%=firstName%> <%=middleName%> <%=lastName%> <%=suffix_nme%></span></span></div>
													 <div class="colspan">
																USEA Member Details :													  </div>				

																<%
	
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
													java.util.Calendar toDay = java.util.Calendar.getInstance();
													int newyear = toDay.get(Calendar.YEAR);
													int new_month = toDay.get(Calendar.MONTH);
													if(new_month>=9 && new_month<=10){
													ArrayList histValues = (ArrayList) request.getAttribute("histValues");
													if(histValues!=null && histValues.size()!=0){
													Iterator itr = histValues.iterator();
													while(itr.hasNext()){
													String[] histval = (String[]) itr.next();
													String membTypName = histval[0];
													String membYr = histval[1];
													String statName = histval[2];
													String ExpDte = histval[3];
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
													<div class="row">
														<span class="label">Expiration Date:</span>
														<%
															if(membTypName.equalsIgnoreCase("Life Member")){
																ExpDte = "No Expiry Date For Life Members";
															}
														if(ExpDte==null || ExpDte.equalsIgnoreCase("null") || ExpDte.trim().length()==0)  ExpDte = "N/A";	
														%>
														<span class="label"><%=ExpDte%></span>													
						  							</div>													
													<%
													}
													%>
													
													<%
													}
													}
													else{
													%>
													 <div class="colspan">USEA Member Information:</div>
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
															String tmpMemId1="";
															tmpMemId1=objMember.getMemberId(); 
															
															String status1="";
															
															if(objMember.getStatusName()!=null)
															{
																status1=objMember.getStatusName();
															}
															else
															{
																status1="N/A";
															}
													%>
															 
															<div class="row">
																<span class="label">Membership Status :</span>
																<span class="label">
																	   <span class="styleBoldOne"><%=status1%></span>
																</span>
															</div>
														<%
														
														//new_month
														%>														
													<%
													if(!mtyp.equalsIgnoreCase("Life Member"))
	  												{%>
	 
													<div class="row">
														<span class="label">Expiration Date:</span>		
													<span class="label">
														<%=dateFormat(expDate)%>																		
												    </span>
															
													<%}}%>
													
													</div>
													<div class="colspan">
															<strong>Personal / Contact Details:</strong>
													</div>													
													<div class="row">
														<span class="label">Join Date:</span>		
													<span class="label">
														<%=dateFormat(joinDate)%>																		
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
													try{
														 HLCUserRegVO userregvo=new HLCUserRegVO();

                                                         userregvo=(HLCUserRegVO)request.getAttribute("userregvo");
													
													%>
													
													<div id="pAdd">
									
									<div class="colspan">
										<strong>Primary Address </strong> 
									</div>
											<div class="row">
												<span class="label">Address 1:</span>
												<span class="formX"> <%=userregvo.getPrimaryAddress1()%></span>
											</div>

								<%
								String sAdd;

								if(userregvo.getPrimaryAddress2()!=null && userregvo.getPrimaryAddress2().trim().length()!=0)
								{
									sAdd=userregvo.getPrimaryAddress2();
								}
								else
								{
									sAdd="N/A";
								}

								String pMob;

								if(userregvo.getPromaryMobileNo()!=null && userregvo.getPromaryMobileNo().trim().length()!=0)
								{
									pMob=userregvo.getPromaryMobileNo();
								}
								else
								{
									pMob="N/A";
								}

								String pFax;

								if(userregvo.getPrimaryFaxNo()!=null && userregvo.getPrimaryFaxNo().trim().length()!=0)
								{
									pFax=userregvo.getPrimaryFaxNo();
								}
								else
								{
									pFax="N/A";
								}

								%>
											
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <%=sAdd%></span>
											</div>
											<div class="row">
												<span class="label">City:</span>
												<span class="formX">
												<%=userregvo.getPrimaryCity()%>
												</span>
											</div>
											<div class="row">
												<span class="label">State:</span>
												<span class="formX">
												<%=userregvo.getPrimaryState()%>
												</span>
											</div>
											<div class="row">
												<span class="label">Zipcode:</span>
												<span class="formX"><%=userregvo.getPrmaryZip()%></span>
											</div>
											<div class="row">
												<span class="label">Country:</span>
												<span class="formX">
												<%=userregvo.getPrimaryCountry()%>
												 
												</span>
											</div>								

									<div class="row">
										<span class="label">Phone:</span>
										<span class="formX"><%=userregvo.getPrimaryPhoneNo()%></span>
									</div>
									<div class="row">
										<span class="label">Mobile:</span>
										<span class="formX"><%=pMob%></span>
									</div>
									<div class="row">
										<span class="label">Fax:</span>
										<span class="formX"><%=pFax%></span>
									</div>

							</div>
							
							
							<%
								if(userregvo.getSecondaryContactTypeName()!=null)
								{
									System.out.println("userregvo.getSecondaryContactTypeName() :"+userregvo.getSecondaryContactTypeName());
								if(userregvo.getSecondaryContactTypeName().equalsIgnoreCase("Secondary"))
								{
									System.out.println("userregvo.getSecondaryAddress1() :"+userregvo.getSecondaryAddress1());
							%>
									<div>

									<div class="colspan">
										<strong>Secondary Address </strong> 
									</div>
									<div class="row">
							  <span class="label">Address 1:</span>	 <%=userregvo.getSecondaryAddress1()%> <span class="formX"> </span>	  </div>
								
			                    <%
								String sAdd2;

								if(userregvo.getSecondaryAddress2()!=null && userregvo.getSecondaryAddress2().trim().length()!=0)
								{
									sAdd2=userregvo.getSecondaryAddress2();
								}
								else
								{
									sAdd2="N/A";
								}

								String sMob;

								if(userregvo.getSecondaryMobileNo()!=null && userregvo.getSecondaryMobileNo().trim().length()!=0)
								{
									sMob=userregvo.getSecondaryMobileNo();
								}
								else
								{
									sMob="N/A";
								}

								String sFax;

								if(userregvo.getSecondaryFaxNo()!=null && userregvo.getSecondaryFaxNo().trim().length()!=0)
								{
									sFax=userregvo.getSecondaryFaxNo();
								}
								else
								{
									sFax="N/A";
								}


								%>

											<div class="row">
												<span class="label">Address 2:</span>	 <span class="formX"> <%=sAdd2%></span>
												
												<span class="formX"> 
												</span>
												
											</div>
											<div class="row">
												<span class="label">City:</span>
												
												<span class="formX"><%=userregvo.getSecondaryCity()%></span>
												
											</div>
											<div class="row">
												<span class="label">State:</span>
												
												<span class="formX"> <%=userregvo.getSecondaryState()%></span>
												
												
											</div>
											<div class="row">
												<span class="label">Zipcode:</span>
												
												<span class="formX"><%=userregvo.getSecondaryZip()%></span>
												
											</div>
											<div class="row">
												<span class="label">Country:</span>
												
												<span class="formX"><%=userregvo.getSecondaryCountry()%></span>
												
											</div>	
									<div class="row">
										<span class="label">Phone:</span>
										<span class="formX"><%=userregvo.getSecondaryPhoneNo()%></span>
									</div>
									<div class="row">
										<span class="label">Mobile:</span>
										<span class="formX"><%=sMob%></span>
									</div>
									<div class="row">
										<span class="label">Fax:</span>
										<span class="formX"><%=sFax%></span>
									</div>												
	
								</div>
                                  <%}}%>	                                      <%
                                                                           String prefcom="";
																		   System.out.println("getPreferedCommunication() :"+userregvo.getPreferedCommunication());

                                                                           if(userregvo.getPreferedCommunication().equalsIgnoreCase("Primary"))
                                                                           {
                                                                                prefcom="Primary Address";
                                                                           }
                                                                           else
                                                                           {
                                                                                prefcom="Secondary Address"; 
                                                                           }
                                                                        %>						
									<div class="row">
										<span class="label">preferred Communication Address:</span>
										<span class="formX">
										<%=prefcom%>
										</span>
									</div>
													<%
													}
													catch(Exception e)
													{
														System.out.print(" error in jsp :"+e);
													}
													%>
													
													<div class="colspan">
															<strong>Mailing Preferences:</strong>
													</div>	
													<% String mailPreference = "";
													if(memPreference !=null && memPreference.size()!=0){
															Iterator it = memPreference.iterator();
															while(it.hasNext()){
																mailPreference = (String)it.next();
																if(mailPreference==null)
																      mailPreference = "";  %>
													<div class="row">
														<span class="label"></span>		
													<span class="label">
														<%=mailPreference%>					
												    </span>		
														
													</div>
													<% } }
													else
													{%>
														<div class="row">
															<span class="styleBoldOne">No Mailing Preferences details available for this Member!!</span>
						</div>
													<%}
													%>
													
													
													<div class="colspan">
															<strong>Current Full / Life Member Info:</strong>
													</div>		
															<div class="row">
																<span class="label">Parent Member ID :</span>
																											<%
																											   String parId=objMember.getParentMemberId();
																											   
																														if(parId!=null && parId.trim().length()!=0)
																														{
																													  		 parId=objMember.getParentMemberId();
																														}
																														else
																														{
																															parId="N/A";
																														}
																												%>     
																				  <span class="formX"><%=parentId%></span>
																													 
															</div>
																						
														
														
														
															 
															  <%
																			int addon=0;
																			if(objMember.getFamilyAddOn()!=null)
																		{
																			List lst=(List)objMember.getFamilyAddOn();
																			addon=lst.size();
																		}
																		else
																		{
																			addon=0;
																		}
																%>
															 <div class="row">
																<span class="label">Number Of Family members :</span>
																<span class="formX">
																<%=addon%>
																&nbsp; 
																</span>
								  </div>															  
															  <% 
															  String[] familyAddOnId1 = new String[2];
															  String famMid="";
															  String famUid="";
															  
													if(familyAddOnId !=null && familyAddOnId.size()!=0){
													%>
													
													<div class="row">
																<span class="label">Family members ID :</span>
																
						  </div>
													<%
															Iterator it = familyAddOnId.iterator();
															while(it.hasNext()){
																familyAddOnId1 = (String[])it.next();
																famMid=familyAddOnId1[0];
																famUid=familyAddOnId1[1];
																
																System.out.print("famMid :"+famMid);
																System.out.print("famUid :"+famUid);
																																
													 %>
													<div class="row">
														<span class="label"></span>	
												
															
													<span class="label"><a href="MembershipReg.do?process=familyView&familyAddOnId1=<%=famMid%>&userId=<%=famUid%>" target="_blank"><%=famMid%> </a>					
												    </span>	
													
														
													</div>
													<% } }%>
															  <div>
															  	<table width="100%" cellpadding="0" cellspacing="1" border="0">
																	<tr>
																		<td colspan="2" class="tblRowHead">Non-USEA Membership Information </td>
																	</tr>
																	
												          <%
															 ArrayList nonusea=new ArrayList();
															 ArrayList donSelect=new ArrayList();
																	 
															nonusea=(ArrayList)request.getAttribute("nonusea");

															donSelect=(ArrayList)request.getAttribute("donSelect");
															
															if(nonusea!=null)
															{
																System.out.println("nonusea.size() :"+nonusea.size());
																HLCNonUseaOrgVO onjNonUsea = new HLCNonUseaOrgVO();
																
																if(nonusea.size() == 0)
																{%>
																
																<tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Non-USEA Organization details available for this Member!!</span></td>
																   
																 </tr>
																
																<%}
																
																for(int t=0;t<nonusea.size();t++)
																{		
																		onjNonUsea=(HLCNonUseaOrgVO)nonusea.get(t);
															%>
																	
																  <tr>
																	<td class="tableLeft">Non USEA Organization Name: </td>
																	<th class="tableRight"><%=onjNonUsea.getNonuseaOrgName()%></th>
																  </tr>
																  
																  <%
																  	String nonMemId="";
																	
																	if(onjNonUsea.getNonuseaMemberId()!=null)
																	{
																		nonMemId=onjNonUsea.getNonuseaMemberId();
																	}
																	else
																	{
																		nonMemId="NA";
																	}		
																														  
																  %>
																  
																  <tr>
																	<td class="tableLeft">Non USEA MemberID: </td>
																	<th class="tableRight"><%=nonMemId%></th>
																  </tr>

																  <%}}else
																  {%>
																  
																  <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Non-USEA Organization details available !! </span></td>
																	
																  </tr>
																  
																  <%}
																  
																  %>
																	<tr>
																		<td colspan="2" class="tblRowHead">Donation Information </td>
																  </tr>		
																			<%
																				if(donSelect!=null)
																				{
																					System.out.println("donSelect.size() :"+donSelect.size());
																					
																					HLCDonationDetailVO onjDon = new HLCDonationDetailVO();
																					
																					if(donSelect.size() == 0)
																					{%>
																					
																					<tr>
																						<td class="tableSpan" colspan="2"><span class="styleBoldOne">No donation details available for this member!!</span></td>
																					   
																					</tr>
																					
																					<%}
																					
																					
																					for(int u=0;u<donSelect.size();u++)
																					{		
																						onjDon=(HLCDonationDetailVO)donSelect.get(u);	
																					String[] tmp=onjDon.getMemberDonationDate().split(" ");
																				%>	
																				
																  <tr>
																	<td class="tableLeft">Donated On: </td>
																	<th class="tableRight"><span class="styleBoldOne"><%=dateFormat(tmp[0])%>&nbsp;</span></th>
																  </tr>		
																				
																  <tr>
																	<td class="tableLeft">Donation Type: </td>
																	<th class="tableRight"><%=onjDon.getDonationName()%></th>
																  </tr>				
																				
																 
																 <tr>
																	<td class="tableLeft">Donation Amount:($) </td>
																	<th class="tableRight"><span class="styleBoldOne"><%=onjDon.getDonationPrice()%>&nbsp;</span></th>
																  </tr>
																 
																 <%
																 String donBy="N/A";
																 
																 if(onjDon.getDonatedBy()!=null)
																 {
																 	donBy=onjDon.getDonatedBy();
																 }
																 
																%>
																 
																 
																 <tr>
																	<td class="tableLeft">Donated By</td>
																	<th class="tableRight"><%=donBy%>&nbsp;</span></th>
																  </tr>
																  
																 <%}}else
																  {%>
																  
																  <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Donation Details Available for this Member!!</span></td>
																	
																  </tr>
																  
																  <%}
																  
																  %>
																 
																 
																 <tr>
																		<td colspan="2" class="tblRowHead">Amateur Rider Information </td>
																  </tr>		
																 <%
																	String AmateurName="";
																	String AmateurDec1="";
																	String AmateurDec2="";
																	
                                                                    boolean isAmateurDec2;
                                                                    boolean isAmateurDec1;  
																	
																	if(objMember.getAmateurName() !=null)
																	{
																		AmateurName=objMember.getAmateurName();
																	}
																	else
																	{
																		AmateurName="NA";
																	}
                                                                                                                                        
                                                                                                                                        isAmateurDec1=objMember.isAmateurDec1();
                                                                                                                                        
                                                                                                                                        if(isAmateurDec1 == true)
                                                                                                                                        {
                                                                                                                                            AmateurDec1="Yes";
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                        {
                                                                                                                                             AmateurDec1="No";   
                                                                                                                                        }
                                                                                                                                        
                                                                                                                                        
                                                                                                                                        
                                                                                                                                       isAmateurDec2=objMember.isAmateurDec2();
                                                                                                                                        
                                                                                                                                        if(isAmateurDec2 == true)
                                                                                                                                        {
                                                                                                                                            AmateurDec2="Yes";
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                        {
                                                                                                                                             AmateurDec2="No";   
                                                                                                                                        }
                                                                                                                                     
																																	 System.out.println("AmateurName :"+AmateurName); 
																																	 System.out.println("AmateurDec1 :"+isAmateurDec1);  
                                                                                                                                     System.out.println("AmateurDec2 :"+isAmateurDec1);  
                                                                                                                                        
                                                                                                                                                
																 %>
																 
																 <tr>
																	<td class="tableLeft">Name: </td>
																	<th class="tableRight"><%=AmateurName%></th>
																  </tr>																 
                                                                                                                                  <tr>
																	<td class="tableLeft">Possession of a current USEF Amateur Card ?</td>
																	<th class="tableRight"><%=AmateurDec1%></th>
																  </tr>	                                                                                                                                  
                                                                                                                                  <tr>
																	<td class="tableLeft">Declaration serves as my affidavit for Amateur Status Eligibility ? </td>
																	<th class="tableRight"><%=AmateurDec2%></th>
																  </tr>	
																 
																 
																  <tr>
																	<td colspan="2" class="tblRowHead">Payment Information: </td>
																  </tr>
																 
																 <%
																 HLCPaymentDetails objPayment = new HLCPaymentDetails();
																 
																 objPayment=(HLCPaymentDetails)request.getAttribute("objPayment");
																 System.out.println("objPayment jsp :"+objPayment.toString());
																 String chkAmt="";
                                                                                                                                  
																 if(objPayment != null)
																 {
																 %>
																 <input type="hidden" name="payId" value="<%=objPayment.getPaymentId()%>" />
																 <%
																if(objPayment.getPaymentStatus()!=null)
																{
																if(objPayment.getPaymentStatus().equalsIgnoreCase("card"))
																{
                                                                                                                                    
                                                                                                                                     String ccType="N/A";
																																   
                                                                                                                                    if(objPayment.getCcType()!=null)
                                                                                                                                    { 
                                                                                                                                        ccType=objPayment.getCcType();
                                                                                                                                    }
                                                                                                                                     
                                                                                                                                    String namCard="N/A";
  
                                                                                                                                   if(objPayment.getCcName()!=null)
                                                                                                                                   {                                                                                                                                      
                                                                                                                                        namCard=objPayment.getCcName();
                                                                                                                                   }
                                                                                                                                    
                                                                                                                                    String cardNo="N/A";

                                                                                                                                    if(objPayment.getCcNumber()!=null)
                                                                                                                                    {
                                                                                                                                        cardNo=objPayment.getCcNumber();
                                                                                                                                    }
                                                                                                                                    
                                                                                                                                    String txId="N/A";
                                                                                                                                    
                                                                                                                                    if(objPayment.getSslTxnId()!=null)
                                                                                                                                    {	
                                                                                                                                        txId=objPayment.getSslTxnId();
                                                                                                                                    }
                                                                                                                                    
																%>
																
																  <tr>
																	<td class="tableLeft">Payment Mode: </td>
																	<th class="tableRight"><%=objPayment.getPaymentStatus()%></th>
																  </tr>	
																
																  <tr>
																	<td class="tableLeft">Card Type: </td>
																	<th class="tableRight"><%=ccType%></th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Name on Card: </td>
																	<th class="tableRight"><%=namCard%></th>
																  </tr>	
																  
																 <%-- <tr>
																	<td class="tableLeft">Card No: </td>
																	<th class="tableRight"><%=cardNo%></th>
																  </tr>	--%>
																  
                                                                                                                                   <tr>
																	<td class="tableLeft">Transaction Id : </td>
																	<th class="tableRight"><%=txId%></th>
																   </tr>
                                                                                                                                  
																  <tr>
																	<td class="tableLeft">Payment Date: </td>
																	<th class="tableRight"><%=dateFormat(objPayment.getPaymentDate().toString())%></th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Amount: </td>
																	<th class="tableRight"><%=objPayment.getAmount()%></th>
																  </tr>
																  
																  <tr>
																	<td class="tableLeft">Payment Status: </td>
																	<th class="tableRight"><%=objPayment.getSslResultMessage()%></th>
																  </tr>
																  
																 <%}else{
                                                                                                                                    
                                                                                                                                    chkAmt=String.valueOf(objPayment.getCheckAmount());
                                                                                                                                    
                                                                                                                                    %>
																 
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
																	<th class="tableRight"><%=dateFormat(objPayment.getCheckDate().toString())%></th>
																  </tr>
																 
                                                                                                                                   <tr>
																	<td class="tableLeft">Check Amount: </td>
																	<th class="tableRight"><%=chkAmt%></th>
																  </tr>
                                                                                                                                  
																 <tr>
																	<td class="tableLeft">Payment Date: </td>
																	<th class="tableRight"><%=dateFormat(objPayment.getPaymentDate().toString())%></th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Amount: </td>
																	<th class="tableRight"><%=objPayment.getAmount()%></th>
																  </tr>
																 
																 <%}}else{%>
																 
																 <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">Payment Failed for this Member !</span></td>        
																  </tr>
																  
																<%}}else{%>
																 
																 <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">Payment Failed for this Member !</span></td>        
																  </tr>
																  
																<%}%>
																</table>
															  </div>													
										<div>					  
                                        <table width="100%" cellpadding="0" cellspacing="1" border="0">
											<tr>
												<td class="tableLeft">Assign Status: </td>
												<th class="tableRight">
												<span class="formX">		 
												
														<select name="uStatusId" id="uStatusId" class="selectboxOne" >
<% String[] dropVal = new String[]{ "Active","Inactive","Expired","Duplicate","Suspended","Pending","Deceased","Merged"};
   String[] dropText = new String[]{"Activate","Deactivate","Expired","Duplicate","Suspended","Pending","Deceased","Merged"};

					   if(status!= null && status.trim().length()!=0){
								    for(int i=0;i<8;i++){
										if(status.equalsIgnoreCase(dropVal[i])){%>
										<option value="<%=dropVal[i]%>" selected="selected"><%=dropText[i]%></option> 
										<%} else {%>
										<option value="<%=dropVal[i]%>"><%=dropText[i]%></option> 
									   <%}
									}									
						} else { %>
								
														  <option selected="selected">Select One</option>
														  <option value="Active">Activate</option>
														  <option value="Inactive">Deactivate</option>
														  <option value="Pending">Pending</option>
														  <option value="Expired">Expired</option>
														  <option value="Suspended">Suspended</option>
														  <option value="Duplicate">Duplicate</option>
														  <option value="Deceased">Deceased</option>	
														  <option value="Merged">Merged</option>	
					  	<%}%>			                                 
														</select>
												</span>
												</th>
										  </tr>
	<tr>
		  <% 
		     String finalDate="";
		 	 
			 
		 				java.util.Calendar c5 = java.util.Calendar.getInstance();
    					int day = c5.get(Calendar.DATE);
						int month1 = c5.get(Calendar.MONTH);
						
						int month=month1+1;
						int year = c5.get(Calendar.YEAR);
						String date="";
						String chkDat="";
						
						if(month<10)
						{
							date = year+"-"+"0"+month+"-"+day;
							System.out.print("inside if(month1<10) ");
							chkDat = "0"+month+"/"+day+"/"+year;
						}
						else
						{
						date = year+"-"+month+"-"+day;
						chkDat = month+"/"+day+"/"+year;
						}
		 
		    Date appDate = new Date();
		    appDate = (Date) request.getAttribute("approvalDate");
			if(appDate==null){
			finalDate = chkDat;
			System.out.println("Final Date : "+finalDate);
			}
			else{
			java.sql.Date dt=DBHelper.toSQLDate(appDate);
			String tmpDat="";
			tmpDat=dt.toString();
			String ttDay[]=tmpDat.split("-");
			finalDate = ttDay[1]+"/"+ttDay[2]+"/"+ttDay[0];
			System.out.print("finalDate :"+finalDate);
			
			}
			
		%>
		  <td class="tableLeft">
		<span class="label">Approval  Date:</span>
		  <th class="tableRight">
		<span class="formX"><input name="approvalDate" type="text" id="approvalDate" class="textboxOne"  value="<%=finalDate%>" size="10" />
		&nbsp;<a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></span>	
			</th>				
			</tr>
											  <tr>
												<th class="tableRight" colspan="2"><span class="formX">
												
												<%
                                                                                                   String usrId="";
                                                                                                   
                                                                                                    if(request.getAttribute("userId")!=null)
                                                                                                    {
                                                                                                        usrId=(String)request.getAttribute("userId");
                                                                                                    }
                                                                                                   
                                                                                                   String mailStatus = (String)request.getAttribute("mailStatus");
												   String splNotes = (String)request.getAttribute("splNotes");
												   if(splNotes==null){
												   splNotes ="";
												   } 
												if(mailStatus.equalsIgnoreCase("true")){%>
										<input type="checkbox" name="mailAddressStatus"  id="mailAddressStatus" value="true" checked/>&nbsp;Verify Mailing address  <br />	
										<%}else {%>
										<input type="checkbox" name="mailAddressStatus"  id="mailAddressStatus" value="true"/>&nbsp;Verify Mailing address  <br />	
										<%}%></span>
												</th>
											  </tr>
											  <tr>
												<td colspan="2" class="tblRowHead">Special Notes: </td>
								 		  </tr>
											<tr>
												<td class="tableLeft" colspan="2">
												<span class="label"><textarea name="splNote" rows="5"/><%=splNotes%></textarea></span>
												</td>
											</tr>	
                                                                                        
                                                                                        <input type="hidden" name="userId" value="<%=usrId%>" />
                                                                                        
										</table>							
											  <!-- SearchList.do -->
															<div class="row">
																<div class="alignCenter">
																<input name="Submit" type="submit" class="gradBtn" value="Submit" /> 
																<input type="button" value=" Back " class="gradBtn" onclick="javascript: history.go(-1);" />
											</div>              
										
											</div>
											
                                                                                          	
							</div>
							<input type="hidden" value="<%=objMember.getMemberId()%>" name="memberId"/>

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
<script language="javascript">
	var cal1 = new calendar2(document.forms['myform'].elements['approvalDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	</script>

</html>
