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
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.hlcform.util.HLCMemberDetails"%>
<%@ page import="com.hlccommon.util.HLCNonUseaOrgVO"%>
<%@ page import="com.hlcform.util.HLCPaymentDetails"%>
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlcform.util.DBHelper" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmMemRegiApprove.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<!--<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>-->
<script language="javascript">
function showComments(divId){

		if(document.getElementById('mainPayNfs').checked == true){
			document.getElementById('admintxtArea').disabled = false;
			document.getElementById('usrtxtArea').disabled = false;		
		}
		else{
			document.getElementById('admintxtArea').disabled = true;
			document.getElementById('usrtxtArea').disabled = true;		
		}
}

function showSubComments(divId,Sz){
	var subpay="subpay"+Sz;
	var subusrtxtArea="subusrtxtArea"+Sz;
	var subadmintxtArea="subadmintxtArea"+Sz;
	var chkItem ="chkItem"+Sz;
	//alert(document.getElementById('subPaySz').value);
		if(document.getElementById(subpay).checked==true){
			document.getElementById(subusrtxtArea).disabled = false;
			document.getElementById(subadmintxtArea).disabled = false;
			document.getElementById(chkItem).value="avail";
		}
		else{
			document.getElementById(subusrtxtArea).disabled = true;
			document.getElementById(subadmintxtArea).disabled = true;
			document.getElementById(chkItem).value="notavail";
		}
}

</script>

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
  <%!SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
					String dateFormat(String fieldName){
						String dispDate = "N/A";
						if(fieldName!=null && fieldName.trim().length()!=0 && fieldName!="N/A"){
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
							
							if(clDate!=null ){
							dispDate = sd1.format(cal.getTime());
							}
						}
						return dispDate;
					}
  %>
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
				

<form name="frmmemRegiApprove" id="frmmemRegiApprove" action="MemberRegiList.do" onsubmit="return myval();">
			
			<!-- henu -->	
			<input type="hidden" name="memRegiListProcess" value="memRegiApprove"/> 
			
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td class="tblMainHead">Membership: <span class="styleBoldTwo">Membership Approval Form </span></td>
  </tr>
  <tr>
    <td class="tblDescrp">
	<!--<img src="images/usea_logo.jpg" width="100" height="99" class="imgFloatLeft" />--><br />
	
	<strong>You are viewing the Membership Details of a selected member.</strong> <br /><br/> To <strong>Approve</strong>
	a membership and assign a <strong>status</strong> select one from the drop-down menu and submit it.
	
	</td>
  </tr>

	
	<% 
								//Henu..
                String usrtypname=request.getParameter("usrtypname");
                                 
				String str1 [] = new String [15];
				String str2 []  = new String [15];
				String str3 [] = new String [15];
				String[] regdat=new String [10];
				String[] expdat=new String [10];

						Vector memberTypeVect3=new Vector();
						memberTypeVect3=(Vector)request.getAttribute("userTypeVect3");
						System.out.println("memberTypeVect3:"+memberTypeVect3);
						System.out.println("memberTypeVect3: SIZE  "+memberTypeVect3.size());


						ArrayList familyAddOnId = (ArrayList)request.getAttribute("familyAddOnId");
																																																																															                        session.setAttribute("familyAddOnId",familyAddOnId);

				 if (  memberTypeVect3.elementAt(0) != null && memberTypeVect3.size() !=0)
				  {
						   str1   = (String[]) memberTypeVect3.elementAt(0);
				  }	   
				  if (memberTypeVect3.size() == 2 ) 
				  {
					 if (memberTypeVect3.elementAt(1) != null) 
					 {
							   str2   = (String[]) memberTypeVect3.elementAt(1);
					 }
				  }
				  if (memberTypeVect3.size() == 3) 
				  {
					  if (memberTypeVect3.elementAt(2) != null) 
					  {
							str2   = (String[]) memberTypeVect3.elementAt(1);
							str3  = (String[]) memberTypeVect3.elementAt(2);

							regdat=str1[6].split(" ");
							expdat=str1[7].split(" ");
					  }
				  }

	
		     			String finalDate="";
		 	            String dispFinalDate="N/A";
			 
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
		    appDate = (Date)request.getAttribute("approvalDate");
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
			dispFinalDate = tmpDat;
			
			}
						
	%>
        
        <input type="hidden" name="usrtypname" value="<%=usrtypname%>" />
        
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formLayout">
      <tr>
        <td colspan="2" class="tblRowHead">Basic Information: </td>
       </tr>
      <tr>
        <td class="tableLeft">First Name: </td>
        <th class="tableRight"><%=str2[2]%></th>
      </tr>
      <tr>
        <td class="tableLeft">Last Name: </td>
        <th class="tableRight"><%=str2[4]%></th>
      </tr>
      <tr>
        <td class="tableLeft">Email:</td>
        <th class="tableRight"><%=str2[8]%></th>
      </tr>
      <tr>
        <td class="tableLeft">User Type: </td>
        <th class="tableRight">Human</th>
      </tr>
								<%
								String pmob;

								if(str3[9]!=null && str3[9].trim().length()!=0)
								{
									pmob=str3[9];
								}
								else
								{
									pmob="N/A";
								}

								String pph;

								if(str3[8]!=null && str3[8].trim().length()!=0)
								{
									pph=str3[8];
								}
								else
								{
									pph="N/A";
								}

								%>

	   <tr>
        <td class="tableLeft">Mobile No.: </td>
        <th class="tableRight"><%=pmob%></th>
      </tr>
	   <tr>
        <td class="tableLeft">Phone: </td>
        <th class="tableRight"><%=pph%></th>
      </tr>
      <tr>
        <td colspan="2" class="tblRowHead">Membership Information </td>
       </tr>
	   <tr>
        <td class="tableLeft">Membership ID: </td>
        <th class="tableRight"><%=str1[0]%></th>
		<input name="memberId" id="memberId" type="hidden" value="<%=str1[0]%>">
                <%
                    session.setAttribute("status_membId",str1[0]);
                %>
      </tr>
      <tr>
        <td class="tableLeft">Membership Type: </td>
        <th class="tableRight"><span class="styleBoldOne"><%=str1[12]%></span></th>
      </tr>
	   <tr>
        <td class="tableLeft">Current Membership Status: </td>
        <th class="tableRight"><span class="styleBoldOne"><%=str1[11]%></span></th>
      </tr>
	  	 
	 <tr>
        <td class="tableLeft">Membership Registration Date: </td>
        <th class="tableRight"><%=dateFormat(regdat[0])%></th>
      </tr>
	  
	  <tr>
        <td class="tableLeft">Membership Activation Date: </td>
        <th class="tableRight"><%=dateFormat(dispFinalDate)%></th>
      </tr>
	  
	  <%
	  if(!str1[12].equalsIgnoreCase("Life Member"))
	  {%>
	 
	   <tr>
        <td class="tableLeft">Membership Expiry Date: </td>
        <th class="tableRight"><%=dateFormat(expdat[0])%></th>
      </tr>
	<%}
	
	  String mailaddr="";
	  if(str1[10]!=null){
	  mailaddr =str1[10];
	  }
	  else{
	  mailaddr="NA";
	  }
	  %>

	  <%
	   HLCMemberDetails objMember = new HLCMemberDetails();
	   objMember=(HLCMemberDetails)request.getAttribute("objMember");
	  
	  if(str1[12].equalsIgnoreCase("Life Member") || str1[12].equalsIgnoreCase("Full Member"))
	  {
	 
	  int addon=0;
	  if(objMember.getFamilyAddOn()!=null)
      {
	  	 addon =objMember.getFamilyAddOn().size();
		 %>
	  <tr>
        <td class="tableLeft">Family Add on: </td>
        <th class="tableRight"><%=addon%></th>
      </tr>
		 
		  <% 
															  String[] familyAddOnId1 = new String[2];
															  String famMid="";
															  String famUid="";
															  
													if(familyAddOnId !=null && familyAddOnId.size()!=0){
													
															Iterator it = familyAddOnId.iterator();
															while(it.hasNext()){
																familyAddOnId1 = (String[])it.next();
																famMid=familyAddOnId1[0];
																famUid=familyAddOnId1[1];
																
																System.out.print("famMid :"+famMid);
																System.out.print("famUid :"+famUid);
																																
													 %>
													<tr>
													<td class="tableLeft">Family member ID :</td>		
													<th class="tableRight"><a href="MembershipReg.do?process=familyView&familyAddOnId1=<%=famMid%>&userId=<%=famUid%>" target="_blank"><%=famMid%> </a>					
												   </th>
													
														
													</tr>
													<%}}}}%>
		 
	
     
	   <%
	   if(str1[12].equalsIgnoreCase("Family Member"))
	   {
	  String parentId="";
	  if(str1[3]!=null){
	  parentId =str1[3];
	  }
	  else
	  {
	  	parentId="NA";
	  }%>
      <tr>
        <td class="tableLeft">Parent MemberID: </td>
        <th class="tableRight"><%=parentId%></th>
      </tr>
	  <%}
	  %>
	   	<table width="100%" cellpadding="0" cellspacing="1" border="0">
																	<tr>
																		<td colspan="2" class="tblRowHead">Non Membership Information </td>
																	</tr>
																	
												          <%
															 ArrayList nonhlc=new ArrayList();
															 ArrayList donSelect=new ArrayList();
																	 
															nonhlc=(ArrayList)request.getAttribute("nonhlc");
															donSelect=(ArrayList)request.getAttribute("donSelect");
															
															if(nonhlc!=null)
															{
																System.out.println("nonhlc.size() :"+nonhlc.size());
																HLCNonUseaOrgVO onjNonUsea = new HLCNonUseaOrgVO();
																
																if(nonhlc.size() == 0)
																{%>
																
																<tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Non Organization details available for this Member!!</span></td>
																   
																 </tr>
																
																<%}
																
																for(int t=0;t<nonhlc.size();t++)
																{		
																		onjNonUsea=(HLCNonUseaOrgVO)nonhlc.get(t);
															%>
																	
																  <tr>
																	<td class="tableLeft">Non HLC Organization Name: </td>
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
																	<td class="tableLeft">Non HLC MemberID: </td>
																	<th class="tableRight"><%=nonMemId%></th>
																  </tr>
																  <%}}else
																  {%>
																  
																  <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Non Organization details available !! </span></td>
																	
																  </tr>
																  
																  <%}
																  
																  %>
  	
	 
																 <tr>
																		<td colspan="2" class="tblRowHead">Donation Information </td>
																  </tr>		
																			<%
                                                                                                                                                                String userId="";
                                                                                                                                                                userId=(String)request.getAttribute("userId");
                                                                                                                                                                System.out.println("userId in jsp :"+userId);
                                                                                                                                                                
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
																	<th class="tableRight">&nbsp;<%=AmateurName%></th>
																  </tr>																 
                                                                                                                                  <tr>
																	<td class="tableLeft">Possession of a current USEF Amateur Card ?</td>
																	<th class="tableRight">&nbsp;<%=AmateurDec1%></th>
																  </tr>	                                                                                                                                  
                                                                                                                                  <tr>
																	<td class="tableLeft">Declaration serves as my affidavit for Amateur Status Eligibility ? </td>
																	<th class="tableRight">&nbsp;<%=AmateurDec2%></th>
																  </tr>	
                                                                                                                                  
                                                                                                                                  <input type="hidden" name="userId" value="<%=userId%>">
                                                                                                                                  
	 																<tr>
																		<td colspan="2" class="tblRowHead">Main Payment Information: </td>
																	</tr>
																 
																 <%
																 HLCPaymentDetails objPayment = new HLCPaymentDetails();
																 
																 objPayment=(HLCPaymentDetails)request.getAttribute("objPayment");
																 System.out.println("objPayment jsp :"+objPayment.toString());
																 
																 String[] serviceType = (String[])request.getAttribute("serviceType");
																 
																 String chkAmt="";
                                                                 String pendingAmt="";   
													 		     String mainStat = "notavail";                                                             
																 if(objPayment != null)
																 {
																 
																if(objPayment.getPaymentStatus()!=null)
																{
																if(objPayment.getPaymentStatus().equalsIgnoreCase("card"))
																{
                                                                                                                                    String ccTyp="N/A";
                                                                                                                                    String ccNam="N/A";
                                                                                                                                    String ccNo="N/A";
                                                                                                                                    
                                                                                                                                    if(objPayment.getCcType()!=null)
                                                                                                                                   {
                                                                                                                                      ccTyp=objPayment.getCcType();
                                                                                                                                   }
                                                                                                                                    
                                                                                                                                     if(objPayment.getCcName()!=null)
                                                                                                                                   {
                                                                                                                                      ccNam=objPayment.getCcName();
                                                                                                                                   }
                                                                                                                                    
                                                                                                                                      if(objPayment.getCcNumber()!=null)
                                                                                                                                   {
                                                                                                                                      ccNo=objPayment.getCcNumber();
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
																	<th class="tableRight"><%=ccTyp%>&nbsp;</th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Name on Card: </td>
																	<th class="tableRight"><%=ccNam%></th>
																  </tr>	
																  
																  <%--<tr>
																	<td class="tableLeft">Card No: </td>
																	<th class="tableRight"><%=ccNo%></th>
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
																  
																 <%}else{
                                                                                                                                    
                                                                                                                                    chkAmt=String.valueOf(objPayment.getCheckAmount());
                                                                                                                                    pendingAmt = String.valueOf(objPayment.getPendingAmount());
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
																	<th class="tableRight"><%=objPayment.getBankName()%>&nbsp;</th>
																  </tr>
																 
																 <tr>
																	<td class="tableLeft">Check Date: </td>
																	<th class="tableRight"><%= sd1.format(objPayment.getCheckDate())%></th>
																  </tr>
																 
                                                                                                                                   																	                                                                  <tr>
																	<td class="tableLeft">Check Amount: </td>
																	<th class="tableRight"><%=chkAmt%></th>
																  </tr>
                                                                        
																 <tr>
																	<td class="tableLeft">Pending Amount: </td>
																	<th class="tableRight"><%=pendingAmt%></th>
																  </tr>		
																		                                                          
																 <tr>
																	<td class="tableLeft">Payment Date: </td>
																	<th class="tableRight"><%=sd1.format(objPayment.getPaymentDate())%></th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Amount: </td>
																	<th class="tableRight"><%=objPayment.getAmount()%></th>
																  </tr>
																  <input type="hidden" value="<%=objPayment.getPaymentId()%>" name="parentpayId" />

																 <%}}else{%>
																 
																 <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Payment Details Available for this Member !</span></td>        
																  </tr>
																  
																<%}}else{%>
																 
																 <tr>
																	<td class="tableSpan" colspan="2"><span class="styleBoldOne">No Payment Details Available for this Member !</span></td>        
																  </tr>
																  
																<%}%>
	<%
        if(str1[11].equalsIgnoreCase("Pending"))
        {
           if(objPayment.getPaymentId()!=null)
               {
               
			   if(objPayment.getPaymentStatus().equalsIgnoreCase("check"))
               {
			   
			   session.setAttribute("amt",String.valueOf(objPayment.getAmount()));
                           session.setAttribute("pendingAmt",String.valueOf(objPayment.getPendingAmount()));
			   
			   float checkAmt=objPayment.getCheckAmount();
			   double totAmt=objPayment.getAmount();
			   
			   System.out.print(" objPayment.getPendingAmount() :"+checkAmt);
			   System.out.print(" objPayment.getAmount() :"+totAmt);

			   if(checkAmt<totAmt)
			   {
        	%>
          <tr>
            <td class="tableLeft">Edit Payment Details: </td>
            <th class="tableRight"><span class="styleBoldOne"><a href="./AdminMembPayment.do?process=disp&userId=<%=userId%>&pid=<%=objPayment.getPaymentId()%>">Edit Payment</a></span></th>
          </tr>
       <%      }
        }
		else
		{
		
			System.out.print("objPayment.getSslResult() :"+objPayment.getSslResult());
			
			if(objPayment.getSslResult()!=null && objPayment.getSslResult().trim().length()!=0 )
                                                                                                                                      			{
                 if(objPayment.getSslResult().equalsIgnoreCase("1"))
				 {

				 
				 session.setAttribute("amt",String.valueOf(objPayment.getAmount()));
                 				                 
				 %>
		
		<tr>
            <td class="tableLeft">Edit Payment Details: </td>
            <th class="tableRight"><span class="styleBoldOne"><a href="./AdminMembPayment.do?process=disp&usr=user&cardStatus=card&userId=<%=userId%>&pid=<%=objPayment.getPaymentId()%>">Edit Payment</a></span></th>
          </tr>
		
		<%}}}}}
		else{}

           if(objPayment.getPaymentId()!=null)
               {
               
			   if(objPayment.getPaymentStatus().equalsIgnoreCase("check"))
               {
			  String serviceTypeId=" ";
			  String memberType=" ";
			  String memberAmount=" ";
			  String payId_amount=" ";
			  mainStat = "avail";
			  if(serviceType.length!=0 || serviceType!=null){
					serviceTypeId =serviceType[0];
					memberType = serviceType[1];
					memberAmount = serviceType[2];
			  }
			  payId_amount = objPayment.getPaymentId()+"#"+memberAmount+"#"+objPayment.getAmount();
			  boolean nsfCharge_Status =  objPayment.isNsf_charge_status();
			  if(nsfCharge_Status==false){
		  %>
																  
		  <tr>
			<td class="tableLeft"><input type="checkbox" id="mainPayNfs" name="mainPayNfs" value="<%=payId_amount%>" onclick="showComments('commentArea');"/>NSF Charge</td>
			<td class="tableRight">&nbsp;<span class="styleBoldOne"><%=memberAmount%></span></td>
		  </tr>
			<tr>	
				<td colspan="2" id="commentArea">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td class="tabHead">&nbsp;&nbsp;Admin Comments:</td>
						<td class="tabHead">&nbsp;&nbsp;User Comments:</td>
					</tr>
					<tr>
						<td class="tableRight"><textarea disabled="disabled" id="admintxtArea" name="admintxtArea" rows="2" class="textAreaOne"/></textarea>&nbsp;&nbsp;</td>		
						<td class="tableRight"><textarea name="usrtxtArea" id="usrtxtArea" disabled="disabled" rows="2" class="textAreaOne"/></textarea>&nbsp;&nbsp;</td>					
					</tr>
				</table>
				</td>
			</tr>		  
		    <% } 
		    }
		    }
	    %>		
		
	   <%
	   
	   ArrayList childPayment=new ArrayList();
	   
	   childPayment=(ArrayList)request.getAttribute("childPayment");
	   System.out.println("childPayment.size() in jsp :"+childPayment.size());
	   
	   int x=0;
	   String subpay="";
	   String subStat ="notavail";
	   int subPaySz = 0;
	   if(childPayment != null)
	   {
	   for(x=0; x<childPayment.size(); x++)
	   {
	   		objPayment = new HLCPaymentDetails();
			objPayment = (HLCPaymentDetails)childPayment.get(x);
			subPaySz = childPayment.size();
			System.out.println("objPayment in jsp :"+objPayment.toString());		
			%>
			<input type="hidden" name="subPaySz" id="subPaySz" value="<%=childPayment.size()%>" />
			<tr>
						<td colspan="2" class="tblRowHead">Sub Payment Information <%=x+1%>: </td>
			</tr>
			
			<%
						
			if(objPayment.getPaymentStatus()!=null)
			{
			if(objPayment.getPaymentStatus().equalsIgnoreCase("card"))
			{
                        //out.print("setSslResult " + objPayment.getSslResult());                                                   
																			                                                        String ccTyp1="N/A";
                                                                                                                                    String ccNam1="N/A";
                                                                                                                                    String ccNo1="N/A";
                                                                                                                                    
                                                                                                                                    if(objPayment.getCcType()!=null)
                                                                                                                                   {
                                                                                                                                      ccTyp1=objPayment.getCcType();
                                                                                                                                   }
                                                                                                                                    
                                                                                                                                     if(objPayment.getCcName()!=null)
                                                                                                                                   {
                                                                                                                                      ccNam1=objPayment.getCcName();
                                                                                                                                   }
                                                                                                                                    
                                                                                                                                      if(objPayment.getCcNumber()!=null)
                                                                                                                                   {
                                                                                                                                      ccNo1=objPayment.getCcNumber();
                                                                                                                                   }                                                                                                                               String txId1="N/A";
                                                                                                                                    
                                                                                                                                    if(objPayment.getSslTxnId()!=null)
                                                                                                                                    {	
                                                                                                                                        txId1=objPayment.getSslTxnId();
                                                                                                                                    }
                                                                                                                                    
																%>
																
																  <tr>
																	<td class="tableLeft">Payment Mode: </td>
																	<th class="tableRight"><%=objPayment.getPaymentStatus()%></th>
																  </tr>	
																
																  <tr>
																	<td class="tableLeft">Card Type: </td>
																	<th class="tableRight"><%=ccTyp1%>&nbsp;</th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Name on Card: </td>
																	<th class="tableRight"><%=ccNam1%></th>
																  </tr>	
																  
																  <%--<tr>
																	<td class="tableLeft">Card No: </td>
																	<th class="tableRight"><%=ccNo1%></th>
																  </tr>	--%>
																  
                                                                                                                                  <tr>
																	<td class="tableLeft">Transaction Id : </td>
																	<th class="tableRight"><%=txId1%></th>
																   </tr>
                                                                                                                                  
																  <tr>
																	<td class="tableLeft">Payment Date: </td>
																	<th class="tableRight"><%=sd1.format(objPayment.getPaymentDate())%></th>
																  </tr>	
																  
																  <tr>
																	<td class="tableLeft">Amount: </td>
																	<th class="tableRight"><%=objPayment.getAmount()%></th>
																  </tr>
																  
																 <%}else{                                                                                                                                   %>   															 <tr>
																	<td class="tableLeft">Payment Mode: </td>
																	<th class="tableRight"><%=objPayment.getPaymentStatus()%></th>
																  </tr>	
																 
																  <tr>
																	<td class="tableLeft">Check No: </td>
																	<th class="tableRight"><%=objPayment.getCheckNumber()%></th>
																  </tr>
																  
																  <tr>
																	<td class="tableLeft">Bank Name: </td>
																	<th class="tableRight"><%=objPayment.getBankName()%>&nbsp;</th>
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
																	  String serviceTypeId=" ";
																	  String memberType=" ";
																	  String memberAmount=" ";
																	  String payId_amount1=" ";
																	  subStat="avail";
																	  if(serviceType.length!=0 || serviceType!=null){
																			serviceTypeId =serviceType[0];
																			memberType = serviceType[1];
																			memberAmount = serviceType[2];
																	  }
																	  payId_amount1 = objPayment.getPaymentId()+"#"+memberAmount+"#"+objPayment.getAmount();
																	  
																	  boolean nsfCharge_Status =  objPayment.isNsf_charge_status();
		  																if(nsfCharge_Status==false){
																	  %>																  
																  <tr>
																	<td class="tableInner"><input type="checkbox" id="subpay<%=x%>" name="subpay<%=x%>" value="<%=payId_amount1%>"  onclick="showSubComments('subcommentArea<%=x%>',<%=x%>)"/>NSF Charge</td>
																	<input type="hidden" name="chkItem<%=x%>" value="" />
																	<td class="tableRight">&nbsp;<span class="styleBoldOne"><%=memberAmount%></span></td>
																  </tr>
																  <tr>	
																	<td colspan="2" id="subcommentArea<%=x%>">
																	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
																		<tr>
																			<td class="tabHead">&nbsp;&nbsp;Admin Comments:</td>
																			<td class="tabHead">&nbsp;&nbsp;User Comments:</td>
																		</tr>
																		<tr>
																			<td class="tableRight"><textarea disabled="disabled" name="subusrtxtArea<%=x%>" id="subusrtxtArea<%=x%>" rows="2" class="textAreaOne"/></textarea>&nbsp;&nbsp;</td>		
																			<td class="tableRight"><textarea disabled="disabled" name="subadmintxtArea<%=x%>" id="subusrtxtArea<%=x%>" rows="2" class="textAreaOne"/></textarea>&nbsp;&nbsp;</td>					
																		</tr>
																	</table>
																	</td>
																  </tr>		
																  <% } %>																  															  
																 <%}}%>
																 <input type="hidden" name="chkItem<%=x%>" value="" />
																 <input type="hidden" name="subpay<%=x%>" value="viewalldona" />
<%
			
	   }
	   }
	   
	   ArrayList NSFcharge=new ArrayList();
	   
	   NSFcharge=(ArrayList)request.getAttribute("NSFcharge");
	   System.out.println("childPayment.size() in jsp :"+NSFcharge.size());
	   int paymentNo = 0;
	   if(NSFcharge != null)
	   {
	   for(int x1=0; x1<NSFcharge.size(); x1++)
	   {
	   		HLCPaymentDetails objPayDet = new HLCPaymentDetails();
			objPayDet = (HLCPaymentDetails)NSFcharge.get(x1);
			System.out.println("objPayment in jsp :"+objPayDet.toString());		
		%>   

			<%
			if(objPayDet.getPaymentStatus()!=null)
			{
			paymentNo++;
			if(objPayDet.getPaymentStatus().equalsIgnoreCase("card"))
			{
					//out.print("setSslResult " + objPayDet.getSslResult()); 
				  	String ccTyp1="N/A";
					String ccNam1="N/A";
					String ccNo1="N/A";
					String txId1="N/A";
					
					if(objPayDet.getCcType()!=null){
					  ccTyp1=objPayDet.getCcType();
				   }
				   if(objPayDet.getCcName()!=null){
					  ccNam1=objPayDet.getCcName();
				   }
 				   if(objPayDet.getCcNumber()!=null){
					  ccNo1=objPayDet.getCcNumber();
				   }                               
				   if(objPayDet.getSslTxnId()!=null){	
						txId1=objPayDet.getSslTxnId();
				   }                                                                                                                
				%>
				  <tr>
					<td colspan="2" class="tblRowHead">NSF Payment Information <%=paymentNo%>: </td>
				  </tr>																
				  <tr>
					<td class="tableLeft">Payment Mode: </td>
					<th class="tableRight"><%=objPayDet.getPaymentStatus()%></th>
				  </tr>	
				
				  <tr>
					<td class="tableLeft">Card Type: </td>
					<th class="tableRight"><%=ccTyp1%>&nbsp;</th>
				  </tr>	
				  
				  <tr>
					<td class="tableLeft">Name on Card: </td>
					<th class="tableRight"><%=ccNam1%></th>
				  </tr>	
				  
				  <tr>
					<td class="tableLeft">Transaction Id : </td>
					<th class="tableRight"><%=txId1%></th>
				   </tr>
																				  
				  <tr>
					<td class="tableLeft">Payment Date: </td>
					<th class="tableRight"><%=sd1.format(objPayDet.getPaymentDate())%></th>
				  </tr>	
				  
				  <tr>
					<td class="tableLeft">Amount: </td>
					<th class="tableRight"><%=objPayDet.getAmount()%></th>
				  </tr>
				  
				 <%}else{      %>
				<tr>
					<td colspan="2" class="tblRowHead">NSF Payment Information <%=paymentNo%>: </td>
				</tr>				    															
				  <tr>
					<td class="tableLeft">Payment Mode: </td>
					<th class="tableRight"><%=objPayDet.getPaymentStatus()%></th>
				  </tr>	
				 
				  <tr>
					<td class="tableLeft">Check No: </td>
					<th class="tableRight"><%=objPayDet.getCheckNumber()%></th>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Bank Name: </td>
					<th class="tableRight"><%=objPayDet.getBankName()%>&nbsp;</th>
				  </tr>
				 
				 <tr>
					<td class="tableLeft">Check Date: </td>
					<th class="tableRight"><%=sd1.format(objPayDet.getCheckDate())%></th>
				  </tr>
																			  
				 <tr>
					<td class="tableLeft">Payment Date: </td>
					<th class="tableRight"><%=sd1.format(objPayDet.getPaymentDate())%></th>
				  </tr>	
				  
				  <tr>
					<td class="tableLeft">Amount: </td>
					<th class="tableRight"><%=objPayDet.getAmount()%></th>
				  </tr>
		 <%   }  
		 }
		 }	
		 }%>
	   
        <td class="tableLeft">Assign Status: </td>
        <th class="tableRight">
			<select name="uStatusId" id="uStatusId" class="selectboxOne" >
				<%
					if(str1[11].equalsIgnoreCase("Pending")){
				%>
					<option value="Pending" selected="selected">Pending</option>
					<option value="Active" >Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased">Deceased</option>
					<option value="Merged">Merged</option>
				<% }
					else if(str1[11].equalsIgnoreCase("Active")){	
				%>
					<option value="Pending">Pending</option>
					<option value="Active" selected="selected">Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased">Deceased</option>	
					<option value="Merged">Merged</option>				
				<% } 
					else if(str1[11].equalsIgnoreCase("Inactive")){	
				%>
					<option value="Pending">Pending</option>
					<option value="Active">Activate</option>
					<option value="Inactive" selected="selected">Inactive</option>
					<option value="Expired">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased">Deceased</option>
					<option value="Merged">Merged</option>					
				<% } 
					else if(str1[11].equalsIgnoreCase("Expired")){	
				%>
					<option value="Pending">Pending</option>
					<option value="Active">Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired" selected="selected">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased">Deceased</option>
					<option value="Merged">Merged</option>					
				<% } 
					else if(str1[11].equalsIgnoreCase("Suspended")){	
				%>
					<option value="Pending">Pending</option>
					<option value="Active">Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired">Expired</option>
					<option value="Suspended" selected="selected">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased">Deceased</option>
					<option value="Merged">Merged</option>
				<% } 
					else if(str1[11].equalsIgnoreCase("Duplicate")){	
				%>
					<option value="Pending">Pending</option>
					<option value="Active">Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate" selected="selected">Duplicate</option>
					<option value="Deceased">Deceased</option>
					<option value="Merged">Merged</option>					
				<% } 
					else if(str1[11].equalsIgnoreCase("Deceased")){	
				%>
					<option value="Pending">Pending</option>
					<option value="Active">Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased" selected="selected">Deceased</option>
					<option value="Merged">Merged</option>					
				<% } 							
				    else if(str1[11].equalsIgnoreCase("Merged")){	
				%>
					<option value="Pending">Pending</option>
					<option value="Active">Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased">Deceased</option>
					<option value="Merged" selected="selected">Merged</option>					
				<% } else {
				%>
					<option value="Pending">Pending</option>
					<option value="Active">Activate</option>
					<option value="Inactive">Deactivate</option>
					<option value="Expired">Expired</option>
					<option value="Suspended">Suspended</option>
					<option value="Duplicate">Duplicate</option>
					<option value="Deceased">Deceased</option>	
					<option value="Merged">Merged</option>			
				<%	} %>
			  

			                                 
			</select>
		</th>
      </tr>
	  <tr>
		  
		  <td class="tableLeft">
		<span class="label">Approval  Date:</span>
		  <th class="tableRight">
		<span class="formX"><input name="approvalDate" type="text" id="approvalDate" class="textboxOne" size="10" />
		&nbsp;<a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><!--<span class="asterisk">*</span>--></span>	
			</th>				
			</tr>
      <tr>
        <td class="tableLeft">&nbsp;</td>
        <td class="tableRight"><input name="Submit" type="submit" class="gradBtn" value="Submit" /> 
        <input name="Submit2" type="button" class="gradBtn" value="Back" onclick="javascript:history.back(-1);"/></td>
		<input type="hidden" value="<%=mainStat%>" name="mainStat" />
		<input type="hidden" value="<%=subStat%>" name="subStat" />
      </tr>
       <tr>
        <td class="tableLeft">&nbsp;</td>
        <td class="tableRight">&nbsp;</td>
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
	var cal1 = new calendar2(document.forms['frmmemRegiApprove'].elements['approvalDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	</script>

</html>
