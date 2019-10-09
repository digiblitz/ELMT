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
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcmro.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmEvntOrgRenewal.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script language="JavaScript" src="javascripts/calendar2.js" type="text/javascript"></script>


<body>
<%! 
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%
ArrayList listContact = (ArrayList)session.getAttribute("dynamicOrgDetails");
	
		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				if(prefix1==null)
				prefix1 = "";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "";
				middle_name  = (String)it.next();
				if(middle_name==null)
				middle_name = "";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "";
				suite =  (String)it.next();
				if(suite==null)
				suite = "";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "";
				address2 = (String)it.next();
				if(address2==null)
				address2 = "";
				city = (String)it.next();
				if(city==null)
				city = "";
				state =  (String)it.next();
				if(state==null)
				state = "";
				country = (String)it.next();
				if(country==null)
				country = "";
				zip = (String)it.next();
				if(zip==null)
				zip = "";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "";
				mobile_no = (String)it.next();
				if(mobile_no==null)
				mobile_no = "";
				fax_no = (String)it.next();
				if(fax_no==null)
				fax_no = "";
			}
		}

%>

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
				<div class="cmmnForm">
	<div class="colspan">
		<strong>Event Registration USEF Endorsed Competition/Management Application </strong>
	</div>
  
<form name="frmEvntOrgRenewal" method="post" action="EventOrgRenewal.do" >
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		HLCEndorsedFormVO	objEndorseDet = (HLCEndorsedFormVO)request.getAttribute("DisplayEndorsedDetails");
	if(objEndorseDet==null){
		out.println("No records are available");
	}
	else{
	
	
	%>
	
	<div class="rowHead">
		USEF Endorsed Competition Details:
	</div>
			<div class="row">
			<span class="label"><strong>Status:</strong></span>
			<span class="asterisk">
			<span class="formX">
			<strong><%=nullCheck(objEndorseDet.getReqStatus())%></strong>
			</span>
			</span>
			</div>
			<div class="row">
				<span class="label">Name of Competition:</span>
				<span class="formX">
				<%=nullCheck(objEndorseDet.getCompName())%>
				</span>
			</div>
			<div class="row">
				<span class="label">Location of Competition:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCompLocation())%></span>
			</div>

			<div class="row">
				<span class="label">Country</span>
				<span class="formX">
				<%=nullCheck(objEndorseDet.getCompCountry())%></span>
			</div>

			<div class="row">
				<span class="label">State</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getStatName())%></span>

			</div>


			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCompCity())%></span>
			</div>
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCompZip())%></span>
			</div>
			<%
			String compDate ="";
			if(objEndorseDet.getCompDate()!=null){
			compDate =sdf.format(objEndorseDet.getCompDate());
			}
			
			%>
			<div class="row">
				<span class="label">Date of Competition:</span>
				<span class="formX"><%=compDate%></span>
		  </div>
						
		  
			<div class="row">
			<span class="label">Area Championship:</span>
			<span class="formX"><%=nullCheck(objEndorseDet.getAreaName())%></span>
			</div>
			
			<div class="row"> <span class="label">NO:</span>
			 <span class="formX"><%=nullCheck(objEndorseDet.getOrgId())%></span>
			</div>
			<div class="row"> <span class="label">Event Organizer & Contact:</span>
			 <span class="formX"> <%=first_name%></span>
			</div>
			<div class="row">
				<span class="label">Address:</span>
				<span class="formX"><%=address1%></span>
			</div>
			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><%=city%></span>
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX"><%=state%></span>
			</div>
			<div class="row">
				<span class="label">Country:</span>
				<span class="formX"><%=country%></span>
			</div>
			<div class="row">
				<span class="label">Telephone Number:</span>
				<span class="formX"><%=phone_no%></span>
			</div>
			<div class="row">
				<span class="label">EMail:</span>
				<span class="formX"><%=email_id%></span>
			</div>
	
	
			<div class="rowHead">
				USEF Competition Management Details: 
			</div>

			<div class="row">
				<span class="label">Name of Competition Management:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_Name())%></span>
			</div>
			<div class="row">
				<span class="label">Address of principal place of business:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_Addrs())%></span>
			</div>

			<div class="row">
				<span class="label">Country:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_Country())%></span>
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_State())%></span>
			</div>

			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_City())%></span>
			</div>

			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_Zip())%></span>
			</div>

			<div class="row">
				<span class="label">Telephone Number:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_Phone())%></span>
			</div>
			<div class="row">
				<span class="label">Fax Number:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getCom_Mgt_Fax())%></span>
			</div>
			<div class="colspan">
				<strong>Competition Manager/Organizer Information</strong>
			</div>
			<div class="row">
				<span class="label">Competition Manager/Organizer Name:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getMgr_Name())%></span>
			</div>
			<%
			String memUsefNo =objEndorseDet.getManager_usef_memberId();
			if(memUsefNo==null){
			memUsefNo="Not Given";
			}
	
			%>
			<div class="row">
				<span class="label">USEF Membership No.:</span>
				<span class="formX"><%=memUsefNo%></span>
			</div>
			<%
			String memUseaNo =objEndorseDet.getManager_hlc_memberId();
			if(memUseaNo==null){
			memUseaNo="Not Given";
			}
	
			%>
			<div class="row">
				<span class="label">Membership No.:</span>
				<span class="formX"><%=memUseaNo%></span>
			</div>
			<div class="colspan">
				<strong>Member Secretary Information **</strong>
			</div>
			<div id="commonBG" class="textCommon">
				<strong>**</strong> A Secretary's information is only required if neither the Manager/Organizer nor Secretary is a USEF Senior Member.
			</div>
			<div class="row">
				<span class="label">Member Secretary Name:</span>
				<span class="formX"><%=nullCheck(objEndorseDet.getSectryName())%></span>
			</div>
			<%
			String memNo =objEndorseDet.getSecretary_usef_memberId();
			if(memNo==null){
			memNo="Not Given";
			}
	
			%>
			<div class="row">
				<span class="label">USEF Membership No.:</span>
				<span class="formX"><%=memNo%></span>
			</div>
		<%
			String paymentMode=objEndorseDet.getPaymentStatus();
			String transId=objEndorseDet.getSslTxnId();
			float CardAmount=objEndorseDet.getAmt();
			String ccNo=objEndorseDet.getCcNumber();
			int checkNo=objEndorseDet.getChkNumber();		
			//float checkAmt=objEndorseDet.getChkAmount();
			String bkName=objEndorseDet.getBankName();
			String ccN=objEndorseDet.getCcName();
			String ccType=objEndorseDet.getCcType();
			String ccNum=objEndorseDet.getCcNumber();
			int expMon=objEndorseDet.getCc_exp_Month();
			int expYr=objEndorseDet.getCc_exp_Year();
		
			String tempDt ="";
			if(objEndorseDet.getChkDt()!=null){
			tempDt =sdf.format(objEndorseDet.getChkDt());
			}

		if(paymentMode!=null && paymentMode.equalsIgnoreCase("card")){
						%>					 
				
				<div class="rowHead">
				Credit Card Payment Details:
				</div>
				<div class="row">
				
				<div class="row">
				<span class="label">Amount ($) :</span>
				<span class="formX"><%=CardAmount%></span>
				</div>
				<div class="row">
				<span class="label">CC Name:</span>
				<span class="formX"><%=nullCheck(ccN)%></span>
				</div>
				<div class="row">
				<span class="label">CC Type:</span>
				<span class="formX"><%=nullCheck(ccType)%></span>
				</div>				
		
				<%}
			else if(paymentMode!=null && paymentMode.equalsIgnoreCase("Check"))
								{ 
								%>
					<div class="rowHead">
					Check Payment Details:
					</div>

                <div class="row">
				<span class="label">Check Amount:</span>
				<span class="formX"><%=CardAmount%></span>
				</div>
				<div class="row">
				<span class="label">Check Number:</span>
				<span class="formX"><%=checkNo%></span>
				</div>
				
				<div class="row">
				<span class="label">Check Date:</span>
				<span class="formX"><%=tempDt%></span>
				</div>
	
				<div class="row">
				<span class="label">Bank Name:</span>
				<span class="formX"><%=nullCheck(bkName)%></span>
				</div>
				
				<%}%>
<div class="row">
		<span class="label">&nbsp;</span>
		<span class="formX"><input type="button" name="submit" value="Back" class="gradBtn" onclick="javascript:history.back(-1);"/></span>
		</div>
		<%
		}
		%>
	
	<div id="spacer">&nbsp;</div>

</form>
</div>
				
			<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	  </table>
	
	</td>  </tr>
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
