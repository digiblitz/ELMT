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
<script src="javascripts/userRegistration.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<!--<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
<div class="cmmnForm">
	<div class="colspan">
		Membership:<span class="styleBoldTwo"> User Registration Form </span> 
	</div>
    <div id="commonBG" class="textCommon" style="height:100px;"><strong>User, please take your time and register to avail the privileges that accompanies with it. <br />
	    <br />
	    Note:<span class="textCommon" style="height:100px;">This registration process does not make you a member.</span><br />
	    You may   become an Member by filling up the Membership Application form towards the end of this registration process.</strong>
	</div>

    <form name="frmMembRegi" id="frmMembRegi" method="post" action="InsertUserReg.do" class="formcss" onSubmit="return myvalidate(this);">

            <div id="parta" >
                <!-- **************************************** Part A of the form starts here *********************************************** -->
                                                        <%
														
                                                                com.vo.HLCUsrSignUpVO usrVal=new HLCUsrSignUpVO();
                                                                usrVal=(com.vo.HLCUsrSignUpVO)request.getAttribute("usrVal");

 																	String fstname="";
                                                                    String lstname="";
                                                                    String mm="";
                                                                    String dd="";
                                                                    String yy="";                                                                
 																	String userName="";
																	String email="";
																	String zip="";

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
 																	if(usrVal.getUserName()!=null)
                                                                    {
                                                                        userName=usrVal.getUserName();
                                                                    }
                                                                    else
                                                                    {
                                                                        userName="";
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
                                                        %>                                                  

							<div class="rowHead">
								Basic Information:
							</div>
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
										<span class="asterisk">*</span>
									  </span>
			      </div>
									<div class="row">
										<span class="label">First Name:</span>                                      <span class="formX"><input type="text" name="fname" class="textboxOne" value="<%=fstname%>" size="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly" />
									  </span>
			      </div>
									<div class="row">
										<span class="label">Middle Name:</span>
										<span class="formX"><input type="text" name="mname"class="textboxOne" size="20" /></span>
									</div>
									<div class="row">
										<span class="label">Last Name:</span>									  <span class="formX"><input type="text" name="lname" class="textboxOne" value="<%=lstname%>" size="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly"/>
									  </span>
			      </div>
									<div class="row">
										<span class="label">Suffix:</span>
										<span class="formX"><input type="text" name="sname" class="textboxOne" size="20" /></span>
									</div>
											<%
															String[] mon={"January","February","March","April","May","June","July","August","September","October","November","December"};
                                                                                       String month="";
                                                                                       
                                                                                    if(mm.equals("01"))
                                                                                        month=mon[0];
                                                                                    else if(mm.equals("02"))
                                                                                        month=mon[1];
                                                                                    else if(mm.equals("03"))
                                                                                        month=mon[2];
                                                                                    else if(mm.equals("04"))

                                                                                        month=mon[3];
                                                                                    else if(mm.equals("05"))
                                                                                        month=mon[4];
                                                                                    else if(mm.equals("06"))
                                                                                        month=mon[5];
                                                                                    else if(mm.equals("07"))
                                                                                        month=mon[6];
                                                                                    else if(mm.equals("08"))
                                                                                        month=mon[7];
                                                                                    else if(mm.equals("09"))
                                                                                        month=mon[8];
                                                                                    else if(mm.equals("10"))
                                                                                        month=mon[9];
                                                                                    else if(mm.equals("11"))
                                                                                        month=mon[10];
                                                                                    else if(mm.equals("12"))
                                                                                        month=mon[11];
                                                                                    else
                                                                                           %>
									<div class="row">
										<span class="label">Date of Birth :</span>									  <span class="formX">

													 <%
													int mnth=0;
													System.out.println("mm :"+mm);
													int selmth=Integer.parseInt(mm);
													System.out.println("selmth :"+selmth);

													for(int mth=1;mth<=12;mth++)
													{
														if(selmth==mth)
														{
															mnth=mth;
												 		}}%>

												<input type="text" name="birthmonth" value="<%=mnth%>" size="3" id="select" class="selectboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly">
												
												  /
												  <%
													int date=0;
													int dat=Integer.parseInt(dd);
													for(int d=1;d<=31;d++)
													{													
														if(dat==d)
														{														
															  date=d;
														}
													}%>
												
												<input type="text" name="birthday" value="<%=date%>" size="3" id="select" class="selectboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly">
												/
												
														<%
																				java.util.Calendar c5 = java.util.Calendar.getInstance();
																				//int day = c.get(Calendar.DAY);
																				//int month = c.get(Calendar.MONTH);
																				int year5 = c5.get(java.util.Calendar.YEAR);
																				//String date = day+" / "+month+" / "+year;
																				System.out.println("Current Date = "+year5);
																				int yr=0;

																				for(int f=1900;f<=year5;f++) 
																				{
																					int y=Integer.parseInt(yy);
																					if(f==y)
																					{											
																						yr=f;
																					}}%>
																		<input type="text" name="birthyear" value="<%=yr%>" size="3" id="select" class="selectboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly">  	
												
												(e.g. MM/DD/YYYY)
									  </span>
			      </div>
									<div class="row">
										<span class="label">Gender:</span>
									  <span class="floatLeft">
										<input type="radio" size="10" name="gender" value="male" /> Male
										<input type="radio" size="10" name="gender" value="female" /> Female										</span>
									    <span class="asterisk">*</span></div>
									
									  <div class="row">
										<span class="label">User Name (Login ID):</span>									  <span class="formX"><input type="text" name="usrname" value="<%=userName%>" class="textboxOne" size="25" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly" />
									     </span></div>

									<div class="row">
										<span class="label">EMail :</span>
									  <span class="formX"><input type="text" name="email" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly" value="<%=email%>" class="textboxOne" size="25" />
									     <span class="asterisk">*</span></span></div>
									
									<div class="row">
										<span class="label">Choose Your Password:</span>
									  <span class="formX"><input type="Password" name="pwd" class="textboxOne" size="10" />
									  &nbsp; (Minimum 6 Characters) <span class="asterisk">*</span></span></div>
									<div class="row">
										<span class="label">Re-Type Password:</span>
									  <span class="formX"><input type="Password"name="cpwd" class="textboxOne" size="10" />
									  &nbsp; <span class="asterisk">*</span></span></div>
									<div class="row">
										<span class="label">Choose A Secret Question:</span>
									  <span class="formX">
										<select name="QSelect" id="select" class="selectboxOne">
										  <option selected="selected">Select One</option>
										  <option value="What is your favorite passtime?">What is your favorite passtime?</option>
										  <option value="What is your pet's name?">What is your pet's name?</option>
										  <option value="What was your first car?">What was your first car?</option>
										  <option value="What is your mother's first name?">What is your mother's first name?</option>
										  <option value="Which is your favorite vacation spot?">Which is your favorite vacation spot?</option>
										</select>
									  &nbsp; <span class="asterisk">*</span></span></div>
									<div class="row">
										<span class="label">Your Answer To This Question:</span>
									  <span class="formX"><input type="text"name="ans" class="textboxOne" size="30" />
									  <span class="asterisk">*</span></span>
									</div>
									

							<div id="pAdd">

									<div class="colspan">
										<strong>Primary Address </strong>
									</div>
											<div class="row">
											  <span class="label">Address 1:</span>
											  <span class="formX">
											   <input type="text" name="padd_txt" id="pAdd1_txt" value="" class="textboxOne" size="35" />
											    <span class="asterisk">*</span></span>
											</div>
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <input type="text" name="padd_txt2" id="pAdd2_txt" value="" class="textboxOne" size="35" /></span>
											</div>
											<div class="row">
												<span class="label">City:</span>
											  <span class="formX">
												<input type="text" id="pCity_txt" name="pcity_txt" value="" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
												<div class="row">
												<span class="label">State:</span>
											  <span class="formX">
												<select name="pstate_sel" id="pState_sel" class="selectboxOne">
												  <option selected="selected">Select One</option>
												  <option value="FL">FL</option>
												  <option value="VA">VA</option>
												</select>
											 
											    <span class="asterisk">*</span> </span></div>
												<div class="row">
												<span class="label">Zipcode:</span>
											  <span class="formX">
											  <input type="text" name="pzip_txt" id="pZip_txt" value="<%=zip%>" size="8" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Country:</span>
											  <span class="formX">
												<select name="pcountry_sel" id="pCountry_sel" class="selectboxOne" onChange="FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '');">
												  <option selected="selected">Select One</option>
												  <option value="United States of America">United States of America</option>
												</select>
											 
											    <span class="asterisk">*</span> </span></div>
											<div class="row">
												<span class="label">Phone:</span>
											  <span class="formX"><input type="text" name="pphone_txt"  id="pZip_txt" class="textboxOne" size="15" />
												(e.g. xxx yyy zzzz)<span class="asterisk"> *</span></span></div>
											<div class="row">
												<span class="label">Mobile:</span>
												<span class="formX"><input type="text" name="pmob_txt"  id="pZip_txt" class="textboxOne" size="15" /></span>
											</div>
											<div class="row">
												<span class="label">Fax:</span>
												<span class="formX"><input type="text" name="pfax_txt"  id="pZip_txt" class="textboxOne" size="15" /></span>
											</div>
							</div>
							<div class="row">
								<span class="label">Do you have secondary address ?</span>
								<span class="formX">
									<div class="floatLeft">
<input type="radio" value="Primary" name="addr" size="10" onclick="switchDiv('sAdd');" />Yes
<input type="radio" value="Secondary" name="addr" size="10" checked="checked" onClick="showHideRadio('addr','sAdd','Secondary');" /> No
									</div>
								</span>
							</div>

							<div id="sAdd">

									<div class="colspan">
										<strong>Secondary Address </strong>
									</div>
									
											<div class="row">
												<span class="label">Address 1:</span>
											  <span class="formX"> 
											  <input type="text" value="" name="sadd_txt" id="sAdd1_txt" class="textboxOne" size="35" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <input type="text" value="" name="sadd_txt1" id="sAdd2_txt" class="textboxOne" size="35" /></span>
											</div>
											<div class="row">
												<span class="label">City:</span>
											  <span class="formX"><input type="text" value="" name="scity_txt"  id="sCity_txt" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
												<div class="row">
												<span class="label">State:</span>
											  <span class="formX"><select name="sstate_txt" id="sState_sel" class="selectboxOne">
												    </select><!--<input type="text" value="" name="sstate_txt" id="sState_txt" class="textboxOne" size="25" />-->
											    <span class="asterisk">*</span></span></div>
												<div class="row">
												<span class="label">Zipcode:</span>
											  <span class="formX"><input type="text" value="" name="szip_txt"  id="sZip_txt" class="textboxOne" size="8" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Country:</span>
											  <span class="formX"><select name="scountry_txt" id="sCountry_sel" class="selectboxOne" onChange="FillState(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt,'');" >
                                                    </select><!--<input type="text" value="" name="scountry_txt" id="sCountry_txt" class="textboxOne" size="30" />-->
											    <span class="asterisk">*</span></span></div>
											
											
											
											<div class="row">
												<span class="label">Phone:</span>
											  <span class="formX"><input type="text" name="sphone_txt"  id="sZip_txt" class="textboxOne" size="15" />
												(e.g. xxx yyy zzzz)<span class="asterisk"> *</span></span></div>
											<div class="row">
												<span class="label">Mobile:</span>
												<span class="formX"><input type="text" name="smob_txt"  id="sZip_txt" class="textboxOne" size="15" /></span>
											</div>
											<div class="row">
												<span class="label">Fax:</span>
												<span class="formX"><input type="text" name="sfax_txt"  id="sZip_txt" class="textboxOne" size="15" /></span>
											</div>
											<div class="row">
												<span class="label">preferred Communication Address:</span>
												<span class="formX">
												<select name="myselect" id="comAdd_sel" class="selectboxOne">
												  <option selected="selected" value="Primary">Primary Address</option>
												  <option value="Secondary">Secondary Address</option>
												</select>
												</span>
											</div>

							</div>
							<div class="colspan">
								<strong>Contact Options</strong>
							</div>
									<div id="commonBG" class="textCommon" style="height:40px;">
										We provide mail/email information to other affliates, organizations, sponsors 
										and vendors on a limited basis. You may choose to not receive these mailings by selecting the 
										appropriate options below:
									</div>
									<div id="commonBG" class="textCommon" style="height:40px;">
										<input type="checkbox" name="nonUseaEmail"  id="nonUseaEmail_id" value="true"/> Do not release my email address for specific use. <br />
										<input type="checkbox" name="nonUseaMail"  id="nonUseaAdd_id" value="true"/> Do not release my mailing address for specific use.
									</div>

									
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->


								<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="submit" value="Register Now" class="gradBtn" /></span>
								</div>

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
<script language="javascript">
	FillCountry(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, 'USA' );
	FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '');

	FillCountry(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt, 'USA' );
	FillState(document.frmMembRegi.scountry_txt, document.frmMembRegi.sstate_txt, '');

</script>
