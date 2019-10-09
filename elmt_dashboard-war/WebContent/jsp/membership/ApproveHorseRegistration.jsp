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
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <script src="javascripts/cscombo_new.js" type="text/javascript"></script>
		<script src="javascripts/frmApproveHorseReg.js" type="text/javascript"></script>
   
        <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
    </head>
    <%! 
    DecimalFormat result  = new DecimalFormat("0.00");
    String amtFormat(String amtVal){
        String amountValue = "0.00";
        if(amtVal!=null && amtVal.trim().length()!=0){
            System.out.print(Double.parseDouble(amtVal));
            amountValue = result.format(Double.parseDouble(amtVal));
        }
        return amountValue;
    }
    
    %>
    <%! 
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String  nullCheck(String fieldName){
        String retValue = "NG";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    
    %>
    <%! String dateCheck(Date fieldName){
        String detValue = "NG";
        if(fieldName!=null){
            detValue = sdf.format(fieldName);
        }
        return detValue;
    }
    
    %>
	<%! SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
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
    <%! float floatCheck(float fieldName){
        float floateValue = 0;
        if(fieldName!=0.0){
            floateValue = fieldName;
        }
        return floateValue;
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
                    
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                        
                        <tr>
                            <td width="260" class="menuTablePad">
                                <!-- LEFT MENU STARTS HERE -->
                                <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                                <!-- LEFT MENU ENDS HERE -->

                            </td>
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
                                <div class="cmmnForm">
                                    <div class="colspan">
                                        <strong>Membership:</strong> <span class="styleBoldTwo">Horse Registration Details </span> 
                                    </div>
                                    <div id="commonBG" class="textCommon"> You are viewing the details of registered horse.</div>
                                    <div class="rowHead"> Horse Registration Details:</div>
                                    <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                                        <tr>
                                            <td width="163" height="27" class="tblRowHeadTwo">Horse Username</td>
                                            <td width="154" class="tblRowHeadTwo">Relationship</td>
                                            <td width="179" class="tblRowHeadTwo">Relationship Status</td>
                                        </tr>
                                        
                                        <%
                                        ArrayList HorseDisp1 = (ArrayList) request.getAttribute("ListVO");
                                        String firstName1,last_name1,relationship_status1,relationship_type_id1,relationship_type_name1="";
                                        System.out.print("HorseDisp1" + HorseDisp1);
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
                                        } else {
                                        %>
                                        
                                        <tr>
                                            <td height="25" colspan="7" class="alignCenter"><strong>No Records Found!</strong></td>
                                        </tr>
                                        <% } %>
                                    </table>
                                </div>
                                <div class="rowHead">
                                    Horse Registration Details:
                                </div>
                                
                                
                                <%
                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                HLCHorseRegisterationVO HorseDisp =(HLCHorseRegisterationVO)request.getAttribute("HorseDet");
                                if(HorseDisp!=null){
                                    String horseMemberId = (String) HorseDisp.getHorseMemberId();
                                    String competitionName =(String) HorseDisp.getCompetitionName();
                                    String registeredName = (String) HorseDisp.getRegisteredName();
                                    String baRegisteredName = (String)HorseDisp.getBaRegisteredName();
                                    String baPastName = (String)HorseDisp.getBaPastName();
                                    String ownerId = (String)HorseDisp.getOwnerId();
                                    String riderMemberId = (String)HorseDisp.getRiderMemberId();
                                    String paymentId = (String)HorseDisp.getPaymentId();
                                    String requestStatus = (String)HorseDisp.getRequestStatus();
                                    String firstName = (String)HorseDisp.getFirstName();
                                    String lastName = (String)HorseDisp.getLastName();
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
                                    String dstatusName = (String) HorseDisp.getStatusName();
                                    Date importDate = HorseDisp.getImportDate();
                                    Date actDate = HorseDisp.getActivationDate();
                                    Date upgraDate = HorseDisp.getUpgradationDate();
                                    String foreignGrade = (String)HorseDisp.getForeignGrade();
                                    String foreignPoints ="";
                                    if(HorseDisp.getForeignPoints()!=0.0){
                                        foreignPoints = String.valueOf(HorseDisp.getForeignPoints());
                                    } else{
                                        foreignPoints = "NG";
                                    }
                                    String assignedGrade = (String)HorseDisp.getAssignedGrade();
                                    String assignedPoints ="";
                                    if(HorseDisp.getAssignedPoints()!=0.0){
                                        assignedPoints = String.valueOf(HorseDisp.getAssignedPoints());
                                    } else{
                                        assignedPoints = "NG";
                                    }
                                    String notes = (String)HorseDisp.getNotes();
                                    String splNotes = (String)HorseDisp.getSplNotes();
                                    String regstrBy = (String)HorseDisp.getRegisteredBy();
                                    String membertype=(String)HorseDisp.getMemberTypeName();
                                    String statusName = (String)HorseDisp.getStatusName();
                                    String regByuserId =(String)HorseDisp.getRegisterByUserId();
                                %>
                                <div class="row">
                                    <span class="label">Horse Member ID:</span>
                                    <span class="formX"><span class="styleBoldOne"><%=nullCheck(horseMemberId)%></span></span>
                                </div>
                                <div class="row">
                                    <span class="label">Horse Registration Status:</span>
                                    <span class="formX"><span class="styleBoldOne"><%=nullCheck(statusName)%></span></span>
                                </div>
                                <div class="row">
                                    <span class="label">Horse Registration Type:</span>
                                    <span class="formX"><span class="styleBoldOne"><%=nullCheck(membertype)%></span></span>
                                </div>
                                <div class="row">
                                    <span class="label">Activation Date :</span>
                                    <span class="formX"><span class="styleBoldOne"><%=dateCheck(actDate)%></span></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Upgradation Date:</span>
                                    <span class="formX"><span class="styleBoldOne"><%=dateCheck(upgraDate)%></span></span>
                                    
                                </div>
                                <div class="rowHead">
                                    Horse Information Section:
                                </div>
                                
                                <div class="row">
                                    <span class="label">Horse Name: </span>
                                    <span class="formX"><strong><%=nullCheck(competitionName)%></strong></span>
                                </div>
                                <div class="row">
                                    <span class="label">Registered By:</span>
                                    <span class="formX"><span class="styleBoldOne"><%=nullCheck(regstrBy)%></span></span>
                                </div>								
                                <div class="row">
                                    <span class="label">Registered Name:</span>
                                    <span class="formX"><%=nullCheck(registeredName)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Past Name:</span>
                                    <span class="formX"><%=nullCheck(baPastName)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Breed Assoc. Horse is registered with:</span>
                                    <span class="formX"><%=nullCheck(baRegisteredName)%></span>
                                </div>
                                <div class="rowHead">
                                    Horse Description:
                                </div>
                                <div class="row">
                                    <span class="label">Color:</span>
                                    <span class="formX"><%=nullCheck(color)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Sex:</span>
                                    <span class="formX"><%=nullCheck(gender)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Height</span>
                                    <span class="formX"><%=nullCheck(height)%></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Year foaled:</span>
                                    <span class="formX"><%=nullCheck(yearFoaled)%></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Breed:</span>
                                    <span class="formX"><%=nullCheck(breed)%></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Breed Two:</span>
                                    <span class="formX"><%=nullCheck(breed2) %></span>
                                </div>	
                                
                                <div class="row">
                                    <span class="label">Country of origin:</span>
                                    <span class="formX"><%=nullCheck(country1) %></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Sire Name:</span>
                                    <span class="formX"><%=nullCheck(sire) %></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Sire Breed:</span>
                                    <span class="formX"><%=nullCheck(sireBreed)%></span>
                                </div> 
                                
                                <div class="row">
                                    <span class="label">Sire Breed Two:</span>
                                    <span class="formX"><%=nullCheck(sireBreed2) %></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Dam Name:</span>
                                    <span class="formX"><%=nullCheck(dam)  %></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Dam Breed:</span>
                                    <span class="formX"><%=nullCheck(damBreed) %></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Dam Breed Two:</span>
                                    <span class="formX"><%=nullCheck(damBreed2)  %></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Imported From:</span>
                                    <span class="formX"><%=nullCheck(importedFrom)  %></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Date Imported:</span>
                                    <span class="formX"><%=dateCheck(importDate)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Foreign Grade:</span>
                                    <span class="formX"><%=nullCheck(foreignGrade)%></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Foreign Points:</span>
                                    <span class="formX"><%=foreignPoints %></span>
                                </div>
                                <div class="row">
                                    <span class="label">Assigned Grade:</span>
                                    <span class="formX"><%=nullCheck(assignedGrade)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Assigned Points:</span>
                                    <span class="formX"><%=assignedPoints%></span>
                                </div>
                                
                                <% 
                                String payinfo[] =(String[]) request.getAttribute("paydet");
                                String checkNumber ="";
                                String paymentStatus = "";
                                String checkName ="";
                                String sslTxnId = "";
                                String bankName ="";
                                String checkDate = "";
                                String amount = "";
                                String checkAmount ="";
                                String tempPaymentId ="";
                                String sslResult ="";
                                float payamt = 0.0f;
                                String balAmount = "";
                                boolean show_pay = true;
                                String cardStatus= "";
                                String cardType = "";
                                String cardName = "";	
                                if(payinfo!=null && payinfo.length !=0){
                                checkNumber =  payinfo[0];
                                paymentStatus =  payinfo[1];
                                checkName =  payinfo[2];
                                sslTxnId = payinfo[3];
                                bankName =  payinfo[4];
                                checkDate =  payinfo[5];
                                amount =  payinfo[6];
                                checkAmount =  payinfo[7];
                                sslResult = payinfo[8];
                                tempPaymentId = payinfo[9];
                                balAmount = payinfo[10];
                                cardName = payinfo[11];
                                cardType = payinfo[12];
                                cardStatus = payinfo[13];	
                                }
                                
                                if(paymentStatus!=null && paymentStatus.equalsIgnoreCase("check")){
                                
                                %>
                                <div class="rowHead">Transaction Details:</div>
                                <div class="row">
                                    <span class="label">Check Number:</span>
                                    <span class="formX"><%=checkNumber%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Check Name:</span>
                                    <span class="formX"><%=checkName%></span>
                                </div>
                                <% String chk_dte ="";
                                if(checkDate!=null){
                                chk_dte=checkDate.substring(0,10);
                                }
                                %>
                                <div class="row">
                                    <span class="label">Check Date:</span>
                                    <span class="formX"><%=dateFormat(chk_dte)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Bank Name:</span>
                                    <span class="formX"><%=bankName%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Amount(<strong>$</strong>):</span>
                                    <span class="formX"><%=amtFormat(amount)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Check Enclosed Amount:</span>
                                    <span class="formX"><%=amtFormat(checkAmount)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Pending Amount:</span>
                                    <span class="formX"><%=amtFormat(balAmount)%></span>
                                </div>
                                <%	}
                                else if(paymentStatus!=null && (paymentStatus.equalsIgnoreCase("credit card") || paymentStatus.equalsIgnoreCase("card")))
                                {%>
                                <div class="rowHead">Transaction Details:</div>
                                <div class="row">
                                    <span class="label">Card Name:</span>
                                    <span class="formX"><%=cardName%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Card Type:</span>
                                    <span class="formX"><%=cardType%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Transaction Status:</span>
                                    <span class="formX"><%=cardStatus%></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Transaction Id:</span>
                                    <span class="formX"><%=nullCheck(sslTxnId)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Amount(<strong>$</strong>):</span>
                                    <span class="formX"><%=amtFormat(amount)%></span>
                                </div>
                                
                                <%	}		ArrayList childPayment = (ArrayList)request.getAttribute("childPayment");
				System.out.print("childPayment value in jsp :" + childPayment);
				String checkNumber1 ="";
			    String paymentStatus1 = "";
				String checkName1 ="";
			    String sslTxnId1 = "";
				String bankName1 ="";
			    String checkDate1 = "";
				String amount1 = "";
				String checkAmount1 = "";
				String temAmt1 ="";
				String sslResult1= "";
				String tempPaymentId1 = "";
				String balAmt1 = "";
				String cardStatus1= "";
				String cardType1 = "";
				String cardName1 = "";
				String payId = "";
	
							if(childPayment!=null && childPayment.size()!=0){
							Iterator itr1 = 	childPayment.iterator();
							int i = 1;
							while(itr1.hasNext()) {				   
								String[] val1=(String[])itr1.next();
									checkNumber1 =  val1[0];
									paymentStatus1 =  val1[1];
									checkName1 =  val1[2];
									sslTxnId1 = val1[3];
									bankName1 =  val1[4];
									checkDate1 =  val1[5];
									amount1 =  val1[6];
									checkAmount1 = val1[7];
									sslResult1 = val1[8];
									tempPaymentId1 = val1[9];	
									balAmt1 = val1[10];
									cardName1 = val1[11];
									cardType1 = val1[12];
									cardStatus1 = val1[13];	
									payId = val1[14];
									System.out.print("payId :" + payId);
				if(paymentStatus1!=null && paymentStatus1.equalsIgnoreCase("check")){
				%>
				<div class="rowHead">Next Transaction Details:&nbsp;&nbsp;&nbsp;<span class="styleBoldOne"><%=i%></span></span></div>
				<div class="row">
						<span class="label">Check Number:</span>
						<span class="formX"><%=nullCheck(checkNumber1)%></span>
				</div>
				<div class="row">
						<span class="label">Check Name:</span>
			 
						<span class="formX"><%=nullCheck(checkName1)%></span>
				</div>
				<div class="row">
						<span class="label">Amount(<strong>$</strong>):</span>
						<span class="formX"><%=amtFormat(amount1)%></span>
				</div>
			<!--	<div class="row">
				 
						<span class="label">Check Enclosed Amount(<strong>$</strong>):</span>
						<span class="formX"><%//=amtFormat(checkAmount1)%></span>
				</div>
				<div class="row">
				 
						<span class="label">Pending Amount(<strong>$</strong>):</span>
						<span class="formX"><%//=amtFormat(balAmt1)%></span>
				</div>-->
				<div class="row">
						<span class="label">Check Date:</span>
				<% String chk_dte1 ="";
						if(checkDate1!=null){ chk_dte1=checkDate1.substring(0,10);	}%>
 						<span class="formX"><%=dateFormat(chk_dte1)%></span>
				</div>
				<div class="row">
						<span class="label">Bank Name:</span>
						<span class="formX"><%=nullCheck(bankName1)%></span>
				</div>
						
				<%	}
				else if(paymentStatus1!=null && (paymentStatus1.equalsIgnoreCase("Card") || paymentStatus1.equalsIgnoreCase("credit card")))
					{   %>
					<div class="rowHead">Next Transaction Details:&nbsp;&nbsp;&nbsp;<span class="styleBoldOne"><%=i%></span></div>
					<div class="row">
				 		<span class="label">Card Name:</span>
						<span class="formX"><%=nullCheck(cardName1)%></span>
				</div>
				<div class="row">
						<span class="label">Card Type:</span>
						<span class="formX"><%=nullCheck(cardType1)%></span>
				</div>
				<div class="row">
				 		<span class="label">Card Status:</span>
						<span class="formX"><%=nullCheck(cardStatus1)%></span>
				</div>
				<div class="row">
						<span class="label">Transaction Id:</span>
					 
						<span class="formX"><%=nullCheck(sslTxnId1)%></span>
				</div>
				<div class="row">
						<span class="label">Amount(<strong>$</strong>):</span>
						<span class="formX"><%=amtFormat(amount1)%></span>
				</div>
				
				   
			<%
				}
			i++;
			}	
			} 
			%>
                                
                                
                                <%    
                                if(dstatusName!=null && dstatusName.equalsIgnoreCase("Pending")){
                                if(paymentId!=null && paymentId.trim().length()!=0){
                                if(paymentStatus!=null && paymentStatus.equalsIgnoreCase("check")){
                                if(checkAmount!=null){
                                float tempCheck = Float.parseFloat(checkAmount);
                                float tempAmount = Float.parseFloat(amount);
                                if(tempCheck<tempAmount){
                                
                                %>
                                <div class="rowHead">
                                    Edit Payment Information:
                                </div>
                                <div class="row">
                                    <span class="label"><span class="styleBoldOne">Change Payment For Pending Amount</span> </span>
                                    <span class="formX"><a href='./adminPay.do?process=chngpay'>Click Here To Change Payment</a></span>
                                </div>
                                <%}
                                }
                                }
                                
                                else{
                                if(sslResult!=null && sslResult.trim().length()!=0){                                 			
                                if(sslResult.equalsIgnoreCase("1")){
                                %>
                                
                                <div class="rowHead">
                                    Edit Payment Information:
                                </div>
                                <div class="row">
                                    <span class="label"><span class="styleBoldOne">Change Payment For Pending Amount</span> </span>
                                    <span class="formX"><a href='./adminPay.do?process=chngpay'>Click Here To Change Payment</a></span>
                                </div>
                                <%}}}}} 
                                session.setAttribute("horseMemberId",horseMemberId);
                                session.setAttribute("competitionName",competitionName);
                                session.setAttribute("paymentIdVal",paymentId);
                                session.setAttribute("regByuserId",regByuserId);
                                %>
                                <div class="row">
                                    <form name="frmViewHorseReg" id="frmViewHorseReg" method="post" action="RegHorseListing.do" onsubmit="return myval();" >
                                        <input type="hidden" name="process" value="updatestatus">
                                        <input type="hidden" name="horseMemId" value="<%=horseMemberId%>"/>
                                        <input type="hidden" name="paymentIdVal" value="<%=paymentId%>" />
										 <input type="hidden" name="membertype" value="<%=membertype%>" />
										 <input type="hidden" name="horseName" value="<%=competitionName%>" />
										 
                                        <table width="100%" cellpadding="0" cellspacing="0" align="left" border="0">
                                            <tr>
                                                
                                                <td class="tableLeftTxtArea">Comments:</td>
                                                <td class="tableRight"><textarea name="commentarea" rows="5" value="" class="textAreaOne"/></textarea>&nbsp;&nbsp;</td>
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
                                                if(month<10){
                                                date = year+"-"+"0"+month+"-"+day;
                                                chkDat = "0"+month+"/"+day+"/"+year;
                                                }
                                                else{
                                                date = year+"-"+month+"-"+day;
                                                chkDat = month+"/"+day+"/"+year;
                                                }
                                                if(actDate==null){
                                                finalDate = chkDat;
                                                }
                                                else{
                                                finalDate = sdf.format(actDate);
                                                }
                                                %>
                                                <tr>
                                                    <td class="tableLeft">Activation Date:</td>
                                                    <td class="tableRight">
                                                        <input type="text" name="activeDatVal" id="activeDatVal" class="textboxOne"  size="16" value="<%=finalDate%>" /> 	
                                                        <a href="javascript:cal2.popup();">
                                                        <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
                                                    <span class="asterisk">*</span></td>		
                                                </tr>
                                                <td class="tableLeft">Status:</td>
                                                <td class="tableRight">
                                                    <% 
                                                    ArrayList statusList = (ArrayList)request.getAttribute("statusList");
                                                    %>
                                                    <select name="statSelect" class="selectboxOne" > 								  
                                                        <option value="" selected="selected">Select One</option>		
                                                        <%
                                                         
                                                        String res ="";
                                                        String statusName1 ="";
                                                        String statusIdName="";
                                                        if(statusList!=null&& statusList.size()!=0){
                                                        Iterator itStatus = statusList.iterator();
                                                        while(itStatus.hasNext()){
                                                        String statusListArray[] = (String [])itStatus.next();
                                                        String statusId = statusListArray[0];
                                                        statusName1 = statusListArray[1];
                                                        statusIdName = statusId + "@" + statusName1;
                                                        if(dstatusName.equalsIgnoreCase(statusName1)){
                                                        %>
                                                        <option  value="<%=statusIdName%>" selected="selected"><%=statusName1%></option>
                                                        <%
                                                        }
                                                        else{
                                                        %>
                                                        <option  value="<%=statusIdName%>"><%=statusName1%></option>
                                                        <%
                                                        }
                                                        }
                                                        }
                                                        }
                                                        %>
                                                </select>&nbsp;&nbsp;&nbsp;<span class="asterisk">*</span></td>
                                            </tr>
                                            <tr>
                                                <td class="tableLeft">&nbsp;</td>
                                                <td class="tableRight">
                                                    <input type="submit" name="Submit" value="Submit" class="gradBtn"/>
                                                <input type="button" name="Cancel" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);"/>								</td>
                                            </tr>
                                        </table>
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
    <script language="javascript">
 var cal2 = new calendar2(document.forms['frmViewHorseReg'].elements['activeDatVal']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
    </script>	
</html>