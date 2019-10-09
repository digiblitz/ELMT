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
<script src="javascripts/frmUserReg.js" type="text/javascript" ></script>
<script src="javascripts/frmUserSignup.js" type="text/javascript" ></script>
<script src="javascripts/frmMemberDetail.js" type="text/javascript" ></script>

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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Member SignUp: <span class="styleBoldTwo">Member Detail  </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">&nbsp;</td>
				  </tr>
						  <tr>
							<td>
							
							<form name="frmUserSignup" id="myform" method="post" action="MemberSignupDetails.do" onsubmit="return myvalidate();">		
							<input type="hidden" name="process" value="regMembInsert" />

																<%
                                                               	  //String[] usrVal=(String[])request.getAttribute("usrVal");
																  
																  com.vo.HLCUsrSignUpVO usrVal=new HLCUsrSignUpVO();
                                            					  usrVal=(com.vo.HLCUsrSignUpVO)request.getAttribute("usrVal");
																
                                            					   

 																	String fstname="";
                                                                    String lstname="";
                                                                    String mm="";
                                                                    String dd="";
                                                                    String yy="";                                                                
 																	String membid="";
																	String email="";
                                                                    String zip="";
																	String userid="";
																																																		
																	if(usrVal!=null)
                                                                {
                                                                    Debug.print("usrVal.length in jsp :"+usrVal.toString());
                                                                    
                                                                    if(usrVal.getFname()!=null)
                                                                    {
                                                                       fstname=usrVal.getFname();;
                                                                    }
                                                                    else
                                                                    {
                                                                        fstname="";
                                                                    }
                                                                    
                                                                    if(usrVal.getLname()!=null)
                                                                    {
                                                                        lstname=usrVal.getLname();
                                                                    }
                                                                    else
                                                                    {
                                                                        lstname="";
                                                                    }
                                                                    
                                                                    if(usrVal.getBirthmonth()!=null)
                                                                    {
                                                                        mm=usrVal.getBirthmonth();
                                                                    }
                                                                    else
                                                                    {
                                                                        mm="";
                                                                    }
                                                                    
                                                                    if(usrVal.getBirthday()!=null)
                                                                    {
                                                                        dd=usrVal.getBirthday();
                                                                    }
                                                                    else
                                                                    {
                                                                        dd="";
                                                                    }
                                                                    
                                                                    if(usrVal.getBirthyear()!=null)
                                                                    {
                                                                        yy=usrVal.getBirthyear();
                                                                    }
                                                                    else
                                                                    {
                                                                        yy="";
                                                                    }   
 																	if(usrVal.getMembid()!=null)
                                                                    {
                                                                        membid=usrVal.getMembid();
                                                                    }
                                                                    else
                                                                    {
                                                                        membid="";
                                                                    }    

																	if(usrVal.getEmail()!=null)
                                                                    {
                                                                        email=usrVal.getEmail();
                                                                    }
                                                                    else
                                                                    {
                                                                        email="";
                                                                    }    
                                                                
																	if(usrVal.getZip()!=null)
                                                                    {
                                                                        zip=usrVal.getZip();
                                                                    }
                                                                    else
                                                                    {
                                                                        zip="";
                                                                    }    
                                                                   
                                                                }
																	

                                                                /*if(usrVal!=null)
                                                                {
                                                                    System.out.println("usrVal in jsp :"+usrVal.toString());
                                                                    
                                                                    if(usrVal[0]!=null)
                                                                    {
                                                                       fstname=usrVal[0];
                                                                    }
                                                                    else
                                                                    {
                                                                        fstname="";
                                                                    }
                                                                    
                                                                    if(usrVal[1]!=null)
                                                                    {
                                                                        lstname=usrVal[1];
                                                                    }
                                                                    else
                                                                    {
                                                                        lstname="";
                                                                    }
                                                                    
                                                                    if(usrVal[2]!=null)
                                                                    {
                                                                        mm=usrVal[2];
                                                                    }
                                                                    else
                                                                    {
                                                                        mm="";
                                                                    }
                                                                    
                                                                    if(usrVal[3]!=null)
                                                                    {
                                                                        dd=usrVal[3];
                                                                    }
                                                                    else
                                                                    {
                                                                        dd="";
                                                                    }
                                                                    
                                                                    if(usrVal[4]!=null)
                                                                    {
                                                                        yy=usrVal[4];
                                                                    }
                                                                    else

                                                                    {
                                                                        yy="";
                                                                    }   
 																	if(usrVal[5]!=null)
                                                                    {
                                                                        membid=usrVal[5];
                                                                    }
                                                                    else
                                                                    {
                                                                        membid="";
                                                                    }    
                                                                
																	if(usrVal[6]!=null)
                                                                    {
                                                                       email=usrVal[6];
                                                                    }
                                                                    else
                                                                    {
                                                                        email="";
                                                                    }

																	if(usrVal[7]!=null)
                                                                    {
                                                                       zip=usrVal[7];
                                                                    }
                                                                    else
                                                                    {
                                                                        zip="";
                                                                    }

                                                                  userid =(String)request.getAttribute("userid");
																	System.out.println("userid :"+userid);
                                                                }*/
																
																 userid =(String)request.getAttribute("userid");
																	System.out.println("userid :"+userid);
																
                                                        %>
									 
								<input type="hidden" name="usrid" value="<%=userid%>" />
  
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								  <tr>
									<td colspan="2" class="tblRowHead">&nbsp;Member  Detail </td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft">Member ID:</td>
									<td class="tableRight"> 
									<input name="membID" id="secques" class="textboxOne" value="<%=membid%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="25" readonly="readonly" />									  </td>
								  </tr>
								  <tr class="tableInner">
									<td class="tableLeft">First Name:</td>
									<td class="tableRight">
									<input name="memfname" id="secques" class="textboxOne" value="<%=fstname%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="25" readonly="readonly" />										</td>
								  </tr>
								 
								 <tr class="tableInner">
									<td class="tableLeft">Last Name:</td>
								   <td class="tableRight">
									<input name="memlname" id="secques" class="textboxOne" value="<%=lstname%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="25" readonly="readonly" />									   </td>
								  </tr>
								  <tr>
										<td class="tableLeft"> Date of Birth:</td>
									<td class="tableRight">
											<input name="memdob" id="secques" class="textboxOne" value="<%=mm+"-"+dd+"-"+yy%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="25" readonly="readonly" />									  </td>
								</tr>
								 <tr class="tableInner">
				
									<td class="tableLeft">e-Mail:</td>
								   <td class="tableRight">
									<input name="mememail" id="secques" class="textboxOne" value="<%=email%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="25" readonly="readonly" />									   </td>
								  </tr>
								  <tr class="tableInner">
									<td class="tableLeft">Zipcode:</td>
									<td class="tableRight">
									<input name="memzip" id="secques" class="textboxOne" value="<%=zip%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="10" readonly="readonly" />									    </td>
								  </tr>
								  
								  <!-- SHOW IF USERNAME ALREADY EXISTS -->
								  <tr>
									<td colspan="2" height="25" class="alignLeft" id="chrgCrd">
									&nbsp;&nbsp;Your <strong>Login detail</strong> would be sent to the e-Mail ID you had specified
									during  registration. </td>
								  </tr>	
								  <!-- SHOW IF USERNAME ALREADY EXISTS -->
								  
								  
								  <!-- SHOW IF USERNAME DOES NOT EXISTS -->
								  <tr>
								  	<td colspan="2">
										<table width="100%" cellpadding="0" cellspacing="1" border="0">
											  <tr>
												<td colspan="2" class="tblRowHead">&nbsp;Create Login  </td>
											  </tr>
                                                                                          
												  <%
													String statuscheck = (String)request.getAttribute("stat");
													if(statuscheck!=null && statuscheck.equals("exists")){
													%>
													<tr>
													<td height="25" colspan="2" class="styleError">User Name Already Exists. Check for availability before submitting.</td>
													</tr>
													<%
													}
													
													String userName=(String)request.getAttribute("userName");
													
													%>
                                                                                          
											   <tr>
												<td class="tableLeft">Choose a Username:</td>
												<%if(userName!=null)
												{%>
													<td class="tableRight"> <input type="text" name="chsUserName" id="chsUserName" class="textboxOne" value="<%=userName%>" size="20" onclick="displayButton();" onkeyup="displayButton();" /><!--checkAvailability();-->
												<%}
												else
												{%>
													<td class="tableRight"> <input type="text" name="chsUserName" id="chsUserName" class="textboxOne"  size="20" onclick="displayButton();" onkeyup="displayButton();" /><!--checkAvailability();-->
												<%}%>
												  <span class="asterisk">* </span>(Min 4 chars., max 25 chars.)</td>
											  </tr>
											  
											  <tr>
												<td class="tableLeft">&nbsp;</td>
												<td class="tableRight">
												<input name="Submit" id="chkAvail_id" type="button" class="gradBtn" style="width:138px;" onclick="usrStat();" disabled="disabled" value="Check For Availability" /></td>
											  </tr>	
											  <tr class="tableInner">
												<td class="tableLeft">Choose a Password:</td>
												<td class="tableRight"><input type="Password" name="pwd" id="passwrd" class="textboxOne" size="20" />
													<span class="asterisk">*</span> <span class="asterisk"> </span>(Min 6 chars., max 12 chars.)</td>
											  </tr>
											  <tr class="tableInner">
												<td class="tableLeft">Retype Password:</td>
												<td class="tableRight"><input type="Password" name="cpwd" id="repasswrd" class="textboxOne" size="20" />
													<span class="asterisk">*</span> </td>
											  </tr>	
									  </table>
									</td>
								</tr>
								 <!-- SHOW IF USERNAME DOES NOT EXISTS --> 	
									
									
									
									 
								<tr>
									<td colspan="2" class="alignCenter"><input type="submit" value="Done" class="gradBtn" /></td>
							   </tr>				 
								<tr>
									<td colspan="2" height="25" class="alignCenter">&nbsp;</td>
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
