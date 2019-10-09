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
<%@ page import="java.util.*,java.util.regex.*"%> 
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
<%! 
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
			retValue = fieldName;	
		}		
	return retValue;
}

%>
<%! 				
				    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
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
		<form name="frmMembRegi" id="myform" method="post" class="formcss" action="UserRegList.do">
<div class="cmmnForm">
	<div class="colspan">
		<strong>Membership: <span class="styleBoldTwo"> Registration Detail</span></strong>	</div>
	

            <div id="parta" >	
                <!-- **************************************** Part A of the form starts here *********************************************** -->
						
							<div class="rowHead">
								Basic Information:
							</div>
							
							<%
								 HLCUserRegVO HLCUserRegVO=new HLCUserRegVO();

								 HLCUserRegVO=(HLCUserRegVO)request.getAttribute("viewMember");
								
								 String selMemTypeId = (String) request.getAttribute("selMemTypeId");								 
																 
																 
                            %>
							
									<div class="row">
										<span class="label">Salutation:</span>
										<span class="formX">
										  <b><%=HLCUserRegVO.getPrefix()%></b>
										</span>
									</div>
									<div class="row">
										<span class="label">First Name:</span>
										<span class="formX"><b><%=HLCUserRegVO.getFirstName()%></b></span>
									</div>

								<%
								String mName="N/A";
								String lName="N/A";
								String sName="N/A";
								String login_name="N/A";
								String emailId="N/A";
								if(HLCUserRegVO.getMiddleName()!=null && HLCUserRegVO.getMiddleName().trim().length()!=0){
									mName=HLCUserRegVO.getMiddleName();
								}
								if(HLCUserRegVO.getLastName()!=null && HLCUserRegVO.getLastName().trim().length()!=0){
									lName=HLCUserRegVO.getLastName();
								}
								if(HLCUserRegVO.getSufix()!=null && HLCUserRegVO.getSufix().trim().length()!=0){
									sName=HLCUserRegVO.getSufix();
								}
								else{
									sName="N/A";
								}
								
								if(HLCUserRegVO.getLoginName()!=null && HLCUserRegVO.getLoginName().trim().length()!=0){
									if(!HLCUserRegVO.getLoginName().equalsIgnoreCase("null"))
									{
									login_name = HLCUserRegVO.getLoginName();
									}
								}
								if(HLCUserRegVO.getEmailId()!=null && HLCUserRegVO.getEmailId().trim().length()!=0){
									if(!HLCUserRegVO.getEmailId().equalsIgnoreCase("null")){
									emailId = HLCUserRegVO.getEmailId();
									}
								}
								%>

									<div class="row">
										<span class="label">Middle Name:</span>
										<span class="formX"><b><%=nullCheck(mName)%></b></span>
									</div>
									<div class="row">
										<span class="label">Last Name:</span>
										<span class="formX"><b><%=nullCheck(lName)%></b></span>
									</div>
									<div class="row">
										<span class="label">Suffix:</span>
										<span class="formX"><b><%=nullCheck(sName)%></b></span>
									</div>
									<div class="row">
									<span class="label">Date of Birth :</span>
										<%
										String[] dob =null;
										System.out.print("DOb  "+HLCUserRegVO.getDob());
										if(HLCUserRegVO.getDob()!=null && HLCUserRegVO.getDob().trim().length()!=0){
											System.out.print("DOb ! null");
											if(!HLCUserRegVO.getDob().equalsIgnoreCase("null")){
												dob=HLCUserRegVO.getDob().split(" ");
										%>
											<span class="formX"><b><%=dateFormat(dob[0])%></b></span>
										<%
											}
										}
										else{
										%>
										<span class="formX"><b>N/A</b></span>
										<% } %>
										
									</div>
									<div class="row">
										<span class="label">Gender:</span>
										<span class="formX">
										<%=HLCUserRegVO.getGender()%>
                                        
										</span>
									</div>
									
									<div class="row">
										<span class="label">EMail :</span>
										<span class="formX"><%=emailId%></span>
									</div>

									<div class="row">
										<span class="label">User Name (Login ID):</span>
										<span class="formX"><%=login_name%></span>
									</div>

									<div class="row">
										                                               
                                         <div class="floatLeft"></div>
                                                                                     
										</span>			  </div>
									
							<div id="pAdd">
									
									<div class="colspan">
										<strong>Primary Address </strong> 
									</div>
											<div class="row">
												<span class="label">Address 1:</span>
												<span class="formX"> <%=HLCUserRegVO.getPrimaryAddress1()%></span>
											</div>

								<%
								String sAdd;

								if(HLCUserRegVO.getPrimaryAddress2()!=null && HLCUserRegVO.getPrimaryAddress2().trim().length()!=0)
								{
									sAdd=HLCUserRegVO.getPrimaryAddress2();
								}
								else
								{
									sAdd="N/A";
								}

								String pMob;

								if(HLCUserRegVO.getPromaryMobileNo()!=null && HLCUserRegVO.getPromaryMobileNo().trim().length()!=0)
								{
									pMob=HLCUserRegVO.getPromaryMobileNo();
								}
								else
								{
									pMob="N/A";
								}

								String pFax;

								if(HLCUserRegVO.getPrimaryFaxNo()!=null && HLCUserRegVO.getPrimaryFaxNo().trim().length()!=0)
								{
									pFax=HLCUserRegVO.getPrimaryFaxNo();
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
												<%=HLCUserRegVO.getPrimaryCity()%>
												</span>
											</div>
												<div class="row">
												<span class="label">State:</span>
												<span class="formX">
												<%=HLCUserRegVO.getPrimaryState()%>
												</span>
											</div>
											<div class="row">
												<span class="label">Zipcode:</span>
												<span class="formX"><%=HLCUserRegVO.getPrmaryZip()%></span>
											</div>
											<div class="row">
												<span class="label">Country:</span>
												<span class="formX">
												<%=HLCUserRegVO.getPrimaryCountry()%>
												 
												</span>
											</div>
	
									<div class="row">
										<span class="label">Phone:</span>
										<span class="formX"><%=HLCUserRegVO.getPrimaryPhoneNo()%></span>
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
								if(HLCUserRegVO.getSecondaryContactTypeName()!=null)
								{
									System.out.println("HLCUserRegVO.getSecondaryContactTypeName() :"+HLCUserRegVO.getSecondaryContactTypeName());
								if(HLCUserRegVO.getSecondaryContactTypeName().equalsIgnoreCase("Secondary"))
								{
									System.out.println("HLCUserRegVO.getSecondaryAddress1() :"+HLCUserRegVO.getSecondaryAddress1());
							%>
									<div>

									<div class="colspan">
										<strong>Secondary Address </strong> 
									</div>
									<div class="row">
							  <span class="label">Address 1:</span>	 <%=HLCUserRegVO.getSecondaryAddress1()%> <span class="formX"> </span>	  </div>
								
			                    <%
								String sAdd2;

								if(HLCUserRegVO.getSecondaryAddress2()!=null && HLCUserRegVO.getSecondaryAddress2().trim().length()!=0)
								{
									sAdd2=HLCUserRegVO.getSecondaryAddress2();
								}
								else
								{
									sAdd2="N/A";
								}

								String sMob;

								if(HLCUserRegVO.getSecondaryMobileNo()!=null && HLCUserRegVO.getSecondaryMobileNo().trim().length()!=0)
								{
									sMob=HLCUserRegVO.getSecondaryMobileNo();
								}
								else
								{
									sMob="N/A";
								}

								String sFax;

								if(HLCUserRegVO.getSecondaryFaxNo()!=null && HLCUserRegVO.getSecondaryFaxNo().trim().length()!=0)
								{
									sFax=HLCUserRegVO.getSecondaryFaxNo();
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
												
												<span class="formX"><%=HLCUserRegVO.getSecondaryCity()%></span>
												
											</div>
											<div class="row">
												<span class="label">State:</span>
												
												<span class="formX"> <%=HLCUserRegVO.getSecondaryState()%></span>											
												
											</div>
											<div class="row">
												<span class="label">Zipcode:</span>
												
												<span class="formX"><%=HLCUserRegVO.getSecondaryZip()%></span>
												
											</div>
											<div class="row">
												<span class="label">Country:</span>
												
												<span class="formX"><%=HLCUserRegVO.getSecondaryCountry()%></span>
												
											</div>
						
									<div class="row">
										<span class="label">Phone:</span>
										<span class="formX"><%=HLCUserRegVO.getSecondaryPhoneNo()%></span>
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
																		   System.out.println("getPreferedCommunication() :"+HLCUserRegVO.getPreferedCommunication());

                                                                           if(HLCUserRegVO.getPreferedCommunication().equalsIgnoreCase("Primary"))
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
									
									
									<div class="colspan">
										<strong>Donation Information </strong> 
									</div>

							 
										<%
										
										ArrayList donSelect=new ArrayList();
										donSelect=(ArrayList)request.getAttribute("donSelect");
										
											if(donSelect!=null)
											{
												System.out.println("donSelect.size() :"+donSelect.size());
												
												HLCDonationDetailVO onjDon = new HLCDonationDetailVO();
												
												if(donSelect.size() == 0)
												{%>
												
											<div class="row">
												<span class="styleBoldOne">No donation details available for this member!!</span>
																						   
											</div>
												
												<%}
																					
												for(int u=0;u<donSelect.size();u++)
												{		
													onjDon=(HLCDonationDetailVO)donSelect.get(u);	
													String[] tmp=onjDon.getMemberDonationDate().split(" ");
												%>
													
													<div class="row">
														<span class="label">Donated On</span>
														<span class="styleBoldOne"><%=dateFormat(tmp[0])%>&nbsp;</span>
													</div>
													
													<div class="row">
														<span class="label">Donation Type: </span>
														<span class="formX"><%=onjDon.getDonationName()%></span>
													</div>
																		 
													<div class="row">
														<span class="label">Donation Amount:($) </span>
														<span class="styleBoldOne"><%=onjDon.getDonationPrice()%>&nbsp;</span>
													</div>	
																				  
													<div class="row">
														<span class="label">Donated By</span>
														<span class="styleBoldOne"><%=onjDon.getDonatedBy()%>&nbsp;</span>
													</div>
													
												
						<%}}else
						{%>
																  
								<div class="row">
										<span class="styleBoldOne">No Donation Details Available for this Member!!</span>
																			
								</div>
																  
						<%}%>
									
									
									<div class="colspan">
										<strong>Contact Options</strong>
									</div>
											
											<div class="row">
											<% if(HLCUserRegVO.isNonUseaEmailStatus()){%>
											    Do not release my email address for non specific use. - <span class="styleBoldOne">Yes</span><br />
												<%}else{%>
												 Do not release my email address for non specific use. - <span class="styleBoldOne">No</span><br />
												 <%}%>
												 </div>
												 
												 <div class="row">
											<% if(HLCUserRegVO.isNonUseaMailingStatus()){%>
											    Do not release my mailing address for non specific use. - <span class="styleBoldOne">Yes</span><br/>
												<%}else{%>
												Do not release my mailing address for non specific use. - <span class="styleBoldOne">No</span><br/>
												<%}%>
											
											</div>
									<div class="row">
										<div class="alignCenter"><input type="submit" value=" Back " class="gradBtn" /></div>
									</div>
									
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->
							
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->
							
						<!-- **************************************** Part A of the form Ends here *********************************************** -->	
            </div>
</div>
		<input type="hidden" name="memProcess" value="uList" />
		<!--<input type="hidden" name="selMemTypeId" value="selMemTypeId%" />-->
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