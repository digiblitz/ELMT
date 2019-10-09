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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmHorseReg.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
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
	if(horseMemId ==null || horseMemId.trim().length()==0)
		horseMemId  = "NG";
	if(competitionName  ==null || competitionName.trim().length()==0)
		competitionName   = "NG";
	if(registeredName  ==null || registeredName.trim().length()==0)
		registeredName   = "NG";
		 								
	if(foreignGrade   ==null || foreignGrade.trim().length()==0)
		foreignGrade    = "NG";
	if(assignedGrade   ==null || assignedGrade.trim().length()==0)
		assignedGrade    = "NG";
	
	if(notes   ==null || notes.trim().length()==0)
		notes    = "NG";												
	if(splNotes   ==null || splNotes.trim().length()==0)
		splNotes    = "NG";
	if(regstrBy   ==null || regstrBy.trim().length()==0)
		regstrBy    = "NG";
	if(membertype  ==null || membertype.trim().length()==0)
		membertype   = "NG";%>
				
			 		
					<div class="cmmnForm">
						<div class="colspan">
							<strong>Membership:</strong> <span class="styleBoldTwo">Horse Registration Details </span> 
						</div>
						
					<%
					
					System.out.print("statusName value:" + statusName);
					if(statusName!=null && statusName.equalsIgnoreCase("Active")){
					System.out.print("Inside the condition statusName value:" + statusName);%>	
					   	<div class="alignLeft">
						<span><input type="button" value="Add New Rider/Owner"  class="gradBtn" onclick="location.href='horserRidOwnAdd.do?process=addRidOwn&horseMemberId=<%=horseMemberId%>'"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="Horse/Rider/Owner Change"  class="gradBtn" onclick="location.href='changeOwnRidPay.do?process=dispdet&horseMemberId=<%=horseMemberId%>'"/>
						</span>
						</div>
				<%	}%>				 
			<form name="frmViewHorseReg" id="frmViewHorseReg" method="post" class="formcss" action="RegHorseListing.do" >
<div class="rowExpand" onclick="expandColl('parta');">Horse Registration Details:</div>
					 
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
							Horse Registration Details:
						</div>
						

				
				<div class="row">
								<span class="label">Horse Registration Status:</span>
								<span class="formX"><span class="styleBoldOne"><%=statusName%></span></span>
							</div>

						<div class="row">
								<span class="label">Horse Registration Type:</span>
								<span class="formX"><span class="styleBoldOne"><%=membertype%></span></span>
							</div>
						<div class="rowHead">
							Horse Information Section:
						</div>
								<div class="row">
									<span class="label">Horse Name: </span>
									<span class="formX"><strong><%=competitionName%></strong></span>
								</div>
						<div class="row">
								<span class="label">Registered By:</span>
								<span class="formX"><span class="styleBoldOne"><%=regstrBy%></span></span>
							</div>								
								<%
								if(registeredName==null || registeredName.trim().length()==0){
									registeredName="NG";
								} 
								%>
								<div class="row">
									<span class="label">Registered Name:</span>
									<span class="formX"><%=registeredName%></span>
								</div>
								<%
								if(baPastName==null || baPastName.trim().length()==0){
									baPastName="NG";
								} 
								%>
								<div class="row">
									<span class="label">Past Name:</span>
									<span class="formX"><%=baPastName%></span>
								</div>
								<%
								if(baRegisteredName==null || baRegisteredName.trim().length()==0){
									baRegisteredName="NG";
								} 
								%>
								<div class="row">
									<span class="label">Breed Assoc. Horse is registered with:</span>
									<span class="formX"><%=baRegisteredName%></span>
								</div>
						<div class="rowHead">
							Horse Description:
						</div>
								<%
								if(color==null || color.trim().length()==0){
									color="NG";
								} 
								%>
								<div class="row">
									<span class="label">Color:</span>
									<span class="formX"><%=color%></span>
								</div>
								<%
								if(gender==null || gender.trim().length()==0){
									gender="NG";
								} 
								%>								 
								<div class="row">
									<span class="label">Sex:</span>
									<span class="formX"><%=gender%></span>
								</div>
								<%
								if(height==null || height.trim().length()==0){
									height="NG";
								} 
								%>
								<div class="row">
									<span class="label">Height</span>
									<span class="formX"><%=height%></span>
								</div>
								<%
								if(yearFoaled==null || yearFoaled.trim().length()==0){
									yearFoaled="NG";
								} 
								%>	
								<div class="row">
									<span class="label">Year foaled:</span>
									<span class="formX"><%=yearFoaled%></span>
								</div>
								<%
								if(breed==null || breed.equals("")){
									breed="NG";
								} 
								%>	
								<div class="row">
									<span class="label">Breed:</span>
									<span class="formX"><%=breed%></span>
								</div>
								<%
								if(breed2 ==null || breed2.equals("")){
									breed2 ="NG";
								} 
								%>						   
								<div class="row">
									<span class="label">Breed Two:</span>
									<span class="formX"><%=breed2 %></span>
								</div>	
								<%
								if(country1 ==null || country1.equals("")){ 
									country1 ="NG";
								} 
								%>	
								<div class="row">
									<span class="label">Country of origin:</span>
									<span class="formX"><%=country1 %></span>
								</div>
								<%
								if(sire ==null || sire.equals("")){ 
									sire ="NG";
								} 
								%>	
								<div class="row">
									<span class="label">Sire Name:</span>
									<span class="formX"><%=sire %></span>
								</div>
								<%
								if(sireBreed ==null || sireBreed.equals("")){ 
									sireBreed ="NG";

								} 
								%>	
								<div class="row">
									<span class="label">Sire Breed:</span>
									<span class="formX"><%=sireBreed%></span>
								</div> 
								<%
								if(sireBreed2 ==null || sireBreed2.equals("")){ 
									sireBreed2 ="NG";
								} 
								%>								   	
								<div class="row">
									<span class="label">Sire Breed Two:</span>
									<span class="formX"><%=sireBreed2 %></span>
								</div>
								<%
								if(dam ==null || dam.equals("")){ 
									dam ="NG";
								} 
								%>	
								<div class="row">
									<span class="label">Dam Name:</span>
									<span class="formX"><%=dam  %></span>
								</div>
								<%
								if(damBreed ==null || damBreed.equals("")){ 
									damBreed ="NG";
								} 
								%>	
								<div class="row">
									<span class="label">Dam Breed:</span>
									<span class="formX"><%=damBreed %></span>
								</div>
								<%
								if(damBreed2 ==null || damBreed2.equals("")){ 
									damBreed2 ="NG";
								} 
								%>								
								<div class="row">
									<span class="label">Dam Breed Two:</span>
									<span class="formX"><%=damBreed2  %></span>
								</div>
								<%
								if(importedFrom ==null || importedFrom.equals("")){ 
									importedFrom ="NG";
								} 
								%>	
								<div class="row">
									<span class="label">Imported From:</span>
									<span class="formX"><%=importedFrom  %></span>
								</div>
							 
								<div class="row">
									<span class="label">Date Imported:</span>
									<span class="formX"><%=imp_dte%></span>
								</div>
								 	
								<div class="row">
									<span class="label">Foreign Grade:</span>
									<span class="formX"><%=foreignGrade%></span>
								</div>
							
								<div class="row">
									<span class="label">Foreign Points:</span>
									<span class="formX"><%=foreignPoints %></span>
								</div>
								  
								<div class="row">
									<span class="label">Assigned Grade:</span>
									<span class="formX"><%=assignedGrade%></span>
								</div>
								<div class="row">
									<span class="label">Assigned Points:</span>
									<span class="formX"><%=assignedPoints%></span>
								</div>
								<div class="row">
									<span class="label">Comments:</span>
									<%	if(comments==null || comments.trim().length()==0)
									{	comments="NG";	}%>
									<span class="formX"><%=comments%></span>
								</div>
				 
				
				<div class="rowHead">Transaction Details:</div>
				<% 
				String payinfo[] =(String[]) request.getAttribute("paydet");
							String checkNumber ="";
							String paymentStatus = "";
							String checkName ="";
							String sslTxnId = "";
							String bankName ="";
							String checkDate = "";
							String amount = "";
							String checkAmount  ="";
							String sslResult= "";
							String tempPaymentId = "";
							 
							String temAmt ="";
							String output2= "";
							if(payinfo!=null && payinfo.length !=0){
								checkNumber =  payinfo[0];
								paymentStatus =  payinfo[1];
								checkName =  payinfo[2];
								sslTxnId = payinfo[3];
								bankName =  payinfo[4];
								checkDate =  payinfo[5];
								amount =  payinfo[6];
								checkAmount = payinfo[7];
								sslResult = payinfo[8];
								tempPaymentId = payinfo[9];	
				}
				if(paymentStatus.equalsIgnoreCase("check"))
				{
				%>
				<div class="row">
						<span class="label">Check Number:</span>
						<span class="formX"><%=checkNumber%></span>
				</div>
				<div class="row">
						<span class="label">Check Name:</span>
				<%		if(checkName==null || checkName.trim().length()==0 )
						{
							checkName="NG";
						}	%>
						<span class="formX"><%=checkName%></span>
				</div>
				<div class="row">
						<span class="label">Amount:</span>
						<span class="formX"><%=amount%></span>
				</div>
				
				<div class="row">
				<%		if(checkAmount==null)
						{
							temAmt="N/A";
						}	%>
						<span class="label">Check Enclosed Amount:</span>
						<span class="formX"><%=checkAmount%></span>
				</div>
				
				
				<div class="row">
						<span class="label">Check Date:</span>
				<% String chk_dte ="";
						if(checkDate!=null){ chk_dte=checkDate.substring(0,10);	}%>
 						<span class="formX"><%=dateFormat(chk_dte)%></span>
				</div>
				<div class="row">
						<span class="label">Bank Name:</span>
						<span class="formX"><%=bankName%></span>
				</div>
				<%	}
				 
					else
					{  
					if(sslResult!=null && sslResult.equalsIgnoreCase("1")){%>
					<div class="row">
						<span class="label"><span class="styleBoldOne">Transaction Failed In Previous So</span> </span>
						<span class="formX"><a href='./Horserrepay.do?act=repay&paymentId=<%=tempPaymentId%>&totalAmount=<%=amount%>&registrationLevel=<%=statusName%>&horseMemberId=<%=horseMemberId%>&horseName=<%=competitionName%>'>Click Here To Make New Payment</a></span>
					</div>	
				<% } %>
				<div class="row">
				
				<%
				
				System.out.print("sslResult" + sslResult);
				 if(sslTxnId==null){	sslTxnId=""; } %>
						<span class="label">Transaction Id:</span>
						<span class="formX"><%=sslTxnId%></span>
				</div>
				<div class="row">
						<span class="label">Amount:</span>
						<span class="formX"><%=amount%></span>
				</div>
			<%	}	try {	 
							if (checkAmount!=null && (!(checkAmount.equals("0.0")))) {
							  		if(sslTxnId==null || sslTxnId.trim().length()==0)  {
							%>
							
								<div class="rowHead">
							Pending Payment Information:
						</div>

							
							<%
							DecimalFormat myformat = new DecimalFormat("#####.00");
							double at=Double.parseDouble(amount) ;
							double ct=Double.parseDouble(checkAmount) ;
							if(ct<at)
								{
									 System.out.println("amount :"+at);
									 System.out.println("chkAmt :"+ct);
									 
									 double finalamt=at-ct;
									 System.out.println("finalamt :"+finalamt);
									
									session.setAttribute("amt",String.valueOf(finalamt));
							%>
						<div class="row">
							<span class="label"><span class="styleBoldOne">Proceed to Payment For Pending Amount</span> </span>
							<span class="formX"><a href='userDuePay.do?process=duepay&totalAmount=<%=myformat.format(finalamt)%>&paymentIdVal=<%=paymentId%>&horseMemberId=<%=horseMemberId%>&competitionName=<%=competitionName%>&amount=<%=amount%>'>Click Here To Make New Payment</a></span>
						</div> 						
							<div class="row">
						<span class="label">Check Amount Recieved <strong>$</strong>:</span>
						<span class="formX"><%=myformat.format(ct)%></span>
						</div>
						
							<div class="row">
						<span class="label">Amount in Due(<strong>$</strong>) : </span>
						<span class="formX"><span class="styleBoldOne"><%=myformat.format(finalamt)%></span></span>
						</div>
						 
							<%
							}}} 
							} 
							catch(Exception e)
							{
								System.out.println(" error in jsp :"+e);
							}
							%>	
						<div class="alignCenter">
							<span><input type="button" value="Back" name="back" class="gradBtn" onclick="javascript:history.back(-1);"/>
						</span>
						</div>
<input type="hidden" value="<%=ownerId%>" name="ownerId"/>

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