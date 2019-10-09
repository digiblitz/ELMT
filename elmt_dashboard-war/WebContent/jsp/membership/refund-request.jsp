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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="java.util.*"%>
<%@ page import="com.hlcform.util.*" %>
<%@ page import="java.text.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcmrm.util.*"%>
<%@ page import="com.hlccommon.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>

<script src="javascripts/refund-request.js" type="text/javascript" ></script>

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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong> Membership</strong>: <span class="styleBoldTwo">Refund Request Form</span> </td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	Members can request a refund for products and services purchased within the last 30 days from HLC. 
	Your refund request will be sent for approval, and you will be notified of the status. <br /><br />
	
	</td>
  </tr>
  <tr>
  	<td valign="top">
	
		
		<form name="frmRefundReq" id="frmRefundReq" method="post" action="./RefundReq.do" onSubmit="return frmRefundReqval();" >
	
			
        <table border="0" cellpadding="0" align="left" cellspacing="0" class="formLayout">
          <tr> 
            <td colspan="2" class="tblRowHead">Refund Request:</td>
          </tr>
		  
		  <%
		  	String balamt=(String)request.getAttribute("rfbalamt");
		  %>
		 
		   <div class="rowHead">
		    	A list of items and amounts available for refund are displayed below:
		   </div>
		   <div class="row">
																					
																	 <%
																	 
																	 ArrayList donDet=new ArrayList();
																 donDet=(ArrayList)request.getAttribute("donDet");	
																  ArrayList donSelect=new ArrayList();
																  donSelect=(ArrayList)request.getAttribute("donSelect");
																  
															 if(donDet!=null)
															 {
															 	String doncbname;
																String dontbname;
																int donsiz=donDet.size();
																HLCDonationDetailVO onjDon = new HLCDonationDetailVO();
																
															 	for(int j=0;j<donDet.size();j++)
															 	{
																	
                                                                                                                                        //doncbname="donCb"+j;
																	//dontbname="donTb"+j;
																	
																	 HLCDonationVO donvo=new HLCDonationVO();
																	 donvo=(HLCDonationVO)donDet.get(j);
																	 
																	 String donId=donvo.getDonationId();
																	 String donName=donvo.getDonationName();
																	 String donAmt=donvo.getDonationPrice();
																	 String doncnct=donId+"#"+donName+"#"+donAmt;
																	 System.out.println("doncnct :"+doncnct);
																	 
																		String donval="0";
																		String donChked="";
																																		
																		for(int u=0;u<donSelect.size();u++)
																		{		
																		
																		doncbname="donCb"+u;
																	    dontbname="donTb"+u;
																	
																			onjDon=(HLCDonationDetailVO)donSelect.get(u);										
																		if(donSelect!=null && donName.equalsIgnoreCase(onjDon.getDonationName()))
																		{
																			donval=onjDon.getDonationPrice();
																		%>
																			<span class="formXThree">
																				<input type="checkbox" name="<%=doncbname%>" id="<%=doncbname%>" value="<%=doncnct%>" onclick="Sumup();"/>	<%=donName%>
																			</span>
																			<span class="formXThree">&nbsp;<strong>$</strong> <input name="<%=dontbname%>" id="<%=dontbname%>" readonly class="readOnly" value="<%=donval%>" size="10" /></span> <br /><br />
																			
																			<%
																			System.out.println("inside checked......"+onjDon.getDonationPrice());
																			//t=nonhlcct-1;
																		}}}%>																
																		
																			<input type="hidden" name="donCbxCt" value="<%=donSelect.size()%>" />
																		<%}
																		
																		/*HLCMemberDetails objMember = new HLCMemberDetails();
					 													objMember=(com.hlcform.util.HLCMemberDetails)request.getAttribute("objMember");*/
                                                                                                                                                         
                                                                                                                                                   String memTypsel=(String)request.getAttribute("memTypId");
      
	  																	   //String memTypsel="";
     																	           String memberTypeId1 ="";
																		   String membershipName1 = "";
																		   String membershipAmount1 = "";
																		   String cnct1=memberTypeId1+"#"+membershipName1+"#"+membershipAmount1;
																		   String stat="no";
																		 
																		   Vector dispMembershipTypeVect=new Vector();
																 			dispMembershipTypeVect=(Vector)request.getAttribute("dispMembershipTypeVect");
																			 Enumeration itrators= (Enumeration)dispMembershipTypeVect.elements();
																 
																		   while(itrators.hasMoreElements()){
																		   String[] sarray = (String[]) itrators.nextElement();
					
																		   String memberTypeId = sarray[0];
																		   String membershipName = sarray[1];
																		   String membershipAmount = sarray[2];
																		   String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;
																		   
																		   //memTypsel=objMember.getMembershipTypeName();
																		   
																		   if(memTypsel!=null)
																		   {
																		   if(memTypsel.equalsIgnoreCase(memberTypeId))
																		   {
																	             stat="yes";
																				 
																				 memberTypeId1 = memberTypeId;
																		   		 membershipName1 = membershipName;
																		   		 membershipAmount1 = membershipAmount;
																		         cnct1=memberTypeId1+"#"+membershipName1+"#"+membershipAmount1;
																           %>
					 
					 
																		<span class="formXThree">
																		
																		<span class="formXThree">
																				<input type="checkbox" name="membTypFee" value="<%=membershipAmount1%>" onclick="Sumup();"/>   <%=membershipName1%>
																			</span>
																																							
																		</span>
																			<span class="formXThree">&nbsp;<strong>$</strong> <input name="mem_amt" id="mem_amt" readonly="readonly" class="readOnly" value="<%=membershipAmount1%>" size="10" /></span> <br /><br />
																		
																	
																<%}}}%>
																
								<input type="hidden" name="selected_mem" value="<%=cnct1%>" />																
								<input type="hidden" name="memb_avail" value="<%=stat%>" />
																					
															</div> 
          <%--<tr>
            <td class="tableLeft">Balance Amount:</td>
            <td class="tableRight"><input type="text" readonly="true" value="<%=balamt%>"  name="txtAmount" class="textboxOne" size="25" /> 
              <span class="asterisk">*</span></td>
          </tr>--%>
		  
          <tr> 
            <td class="tableLeft">Claim Amount: </td>
            <td class="tableRight"><strong>$</strong> <input type="text" name="claimAmount" readonly="readonly" value="0" class="readOnly" size="10" /></td>
          </tr>
          <tr> 
            <td class="tableLeftTxtArea">Comments: </td>
            <td class="tableRight"><textarea name="txtarea" class="textAreaOne" rows="5"></textarea> 
			<span class="asterisk">*</span></td>
          </tr>

          <tr> 
            <td class="tableLeft">&nbsp;</td>
            <td class="tableRight"><input name="subsub" type="submit" class="gradBtn" value="Apply Refund" /></td>
          </tr>
        </table>
			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
