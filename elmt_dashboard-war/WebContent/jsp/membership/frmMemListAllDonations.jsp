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
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlcmrm.util.*"%>
<%@ page import="com.hlcform.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
 
</head>
<%! String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
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
		<form name="frmDonationDet" id="frmDonationDet" method="post" class="formcss">
		
			<div class="cmmnForm">

					<div class="rowHead">Donation Information:</div>
	<%
	String donBy="N/A";
	String dispDonatedBy = "N/A";
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
	
	Date donDate = null;
    Calendar cal = Calendar.getInstance();
    GregorianCalendar gc = new GregorianCalendar();
	
	ArrayList alldonations = (ArrayList) request.getAttribute("allDonations");
	HLCDonationDetailVO onjDon = new HLCDonationDetailVO();
	
	if(alldonations==null){
		System.out.print("Null");
	}
	if(alldonations.size()!=0){
		for(int u=0;u<alldonations.size();u++)
		{		
		onjDon=(HLCDonationDetailVO)alldonations.get(u);	
		String[] tmp=onjDon.getMemberDonationDate().split(" ");
		
		try{
            donDate = sd.parse(tmp[0]);
        }catch(Exception e){
            System.out.println("Error While Parsing Date: "+e);
        }
		
		gc.setTime(donDate);
		cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
		dispDonatedBy = sd1.format(cal.getTime());
		
		if(onjDon.getDonatedBy()!=null){
			donBy=onjDon.getDonatedBy();
		}
	%>							
									<div class="row">
										<span class="label">Donated On:</span>
										<span class="formX"><%=nullCheck(dispDonatedBy)%>&nbsp;</span>
									</div>
									<div class="row">
										<span class="label"><strong>Donation Type:</strong></span>
										<span class="formX"><b><%=nullCheck(onjDon.getDonationName())%></b></span>
									</div>
									<div class="row">
										<span class="label"><strong>Donation Amount ($):</strong></span>
										<span class="formX"><span class="styleBoldOne"><b><%=nullCheck(onjDon.getDonationPrice())%></b></span></span>
									</div>
									<div class="row">
										<span class="label"><strong>In Name Of:</strong></span>
										<span class="formX"><b><%=nullCheck(donBy)%>&nbsp;</b></span>
									</div>
	<%}
	}%>
									<div class="row">
										<div class="alignCenter"><input type="button" value=" Back " class="gradBtn" onclick="javascript:history.back(-1);" /></div>
									</div>									
			</div>
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
