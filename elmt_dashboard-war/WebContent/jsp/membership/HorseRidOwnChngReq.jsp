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
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
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
	<%!					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
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
                                <div class="rowHead">Horse Information: </div>	
                                
                                
                                
                                <div class="cmmnForm">
                                <div class="colspan">
                                    <strong>Membership:</strong> <span class="styleBoldTwo">Horse Registration Details </span> 
                                </div>
                                <div id="commonBG" class="textCommon">You are viewing the details of registered horse.</div>
                                <div class="rowHead"> Owner / Rider Details </div>
                                <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                                    <tr>
                                        <td width="140" height="22" class="tblRowHeadTwo"> UserName</td>
                                        <td width="151" class="tblRowHeadTwo"> Relationship Type </td>
                                        <td width="143" class="tblRowHeadTwo">Relationship Status</td>
                                        <td width="61" class="tblRowHeadTwo">Status</td>
                                    </tr>
                                    
                                    <%
                                    String competitionName = "";
                                    String registeredName = "";
                                    String baRegisteredName = "";
                                    String baPastName = "";
                                    String ownerId = "";
                                    String riderMemberId = "";
                                    
                                    String paymentId = "";
                                    String requestStatus = "";
                                    String firstName = "";
                                    String lastName = "";
                                    String comments = "";
                                    String color = "";
                                    String gender = "";
                                    String height = "";
                                    String yearFoaled = "";
                                    String breed = "";
                                    String horseMemberId ="";
                                    ArrayList HorseDisp1 = (ArrayList) request.getAttribute("ListVO");
                                    System.out.print("HorseDisp1.toString()" + HorseDisp1.toString());
                                    String firstName1,last_name1,relationship_status1,relationship_type_id1,relationship_type_name1="";
                                    String relaId = "";
                                    
                                    HLCHorseRegisterationVO HorseDisp =(HLCHorseRegisterationVO)request.getAttribute("HorseDet");
                                    System.out.print("HorseDisp.toString()" + HorseDisp.toString());
                                    if(HorseDisp!=null){
                                        horseMemberId = (String) HorseDisp.getHorseMemberId();
                                        
                                        
                                        if(HorseDisp1!=null && HorseDisp1.size()!=0){
                                            Iterator itr = 	HorseDisp1.iterator();
                                            while(itr.hasNext()) {
                                                String[] val=(String[])itr.next();
                                                
                                                firstName1 = val[0];
                                                last_name1 =  val[1];
                                                relationship_type_id1 = val[2];
                                                relationship_type_name1 = val[3];
                                                relationship_status1 =  val[4];
                                                relaId = val[5];
                                    
                                    %>
                                    <form name="frmViewHorseReg" id="frmViewHorseReg" method="post" action="RegHorseListing.do" >
                                        <input type="hidden" name="relationId" value="<%=relaId%>"> 
                                        <input type= "hidden" name="process" value="relationshipListing" />
                                        <input type="hidden" name="horseMemberId" value="<%=horseMemberId%>"/>
                                        <tr>
                                            <td bgcolor="#E3E1D2" class="alignLeft"><%=firstName1%>&nbsp;<%=last_name1%></td>								
                                            <td bgcolor="#E3E1D2" class="alignLeft"><%=relationship_type_name1%></td>
                                            <td bgcolor="#E3E1D2" class="alignLeft"><%=relationship_status1%></td>
                                            <td bgcolor="#E3E1D2" class="alignLeft">
                                                <span class="alignCenter">
                                                    <input type="submit" name="click" value="Change" class="twoBtn"/>
                                            </span></td>
                                        </tr>
                                    </form>
                                    <%
                                            }
                                        } else {
                                    %>
                                    
                                    <tr>
                                        <td height="25" colspan="8" class="alignCenter"><strong>No Records Found!</strong></td>
                                    </tr>
                                    <% } %>
                                </table>
                                
                                <div class="rowHead">Horse Registration Details:</div>
                                
                                <%
                                competitionName =(String) HorseDisp.getCompetitionName();
                                registeredName = (String) HorseDisp.getRegisteredName();
                                baRegisteredName = (String)HorseDisp.getBaRegisteredName();
                                baPastName = (String)HorseDisp.getBaPastName();
                                ownerId = (String)HorseDisp.getOwnerId();
                                riderMemberId = (String)HorseDisp.getRiderMemberId();
                                paymentId = (String)HorseDisp.getPaymentId();
                                requestStatus = (String)HorseDisp.getRequestStatus();
                                firstName = (String)HorseDisp.getFirstName();
                                lastName = (String)HorseDisp.getLastName();
                                comments =(String)HorseDisp.getComments();
                                color = (String)HorseDisp.getColor();
                                gender = (String)HorseDisp.getGender();
                                height = (String)HorseDisp.getHeight();
                                yearFoaled = (String)HorseDisp.getYearFoaled();
                                breed = (String)HorseDisp.getBreed();
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
                                String foreignGrade = (String)HorseDisp.getForeignGrade();
                                float foreignPoints = (float)HorseDisp.getForeignPoints();
                                String assignedGrade = (String)HorseDisp.getAssignedGrade();
                                float assignedPoints =(float)HorseDisp.getAssignedPoints();
                                Date actDate = HorseDisp.getActivationDate();
                                Date upgraDate = HorseDisp.getUpgradationDate();
                                String notes = (String)HorseDisp.getNotes();
                                String splNotes = (String)HorseDisp.getSplNotes();
                                String regstrBy = (String)HorseDisp.getRegisteredBy();
                                String membertype=(String)HorseDisp.getMemberTypeName();
                                String statusName = (String)HorseDisp.getStatusName();
                                %>
                                
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
                                    <span class="label">Horse Member Id: </span>
                                    <span class="formX"><strong><%=nullCheck(horseMemberId)%></strong></span>
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
                                <div class="rowHead">Horse Description:</div>
                                
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
                                    <span class="formX"><%=nullCheck(sireBreed2)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Dam Name:</span>
                                    <span class="formX"><%=nullCheck(dam)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Dam Breed:</span>
                                    <span class="formX"><%=nullCheck(damBreed)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Dam Breed Two:</span>
                                    <span class="formX"><%=nullCheck(damBreed2)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Imported From:</span>
                                    <span class="formX"><%=nullCheck(importedFrom)%></span>
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
                                <div class="row">
                                    <span class="label">Comments:</span>
                                    <span class="formX"><%=nullCheck(comments)%></span>
                                </div>
                                <% }
                                
                                String payinfo[] =(String[]) request.getAttribute("paydet");
                                
                                String checkNumber ="";
                                String paymentStatus = "";
                                String checkName ="";
                                String sslTxnId = "";
                                String bankName ="";
                                String checkDate = "";
                                String amount = "";
                                String checkAmount = "";
                                String temAmt ="";
                                String sslResult= "";
                                String tempPaymentId = "";
                                String balAmt = "";
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
                                checkAmount = payinfo[7];
                                sslResult = payinfo[8];
                                tempPaymentId = payinfo[9];	
                                balAmt = payinfo[10];
                                cardName = payinfo[11];
                                cardType = payinfo[12];
                                cardStatus = payinfo[13];		
                                }				 
                                if(paymentStatus!=null && paymentStatus.equalsIgnoreCase("check"))
                                {
                                %>
                                <div class="rowHead">Transaction Details:</div>
                                <div class="row">
                                    <span class="label">Check Number:</span>
                                    <span class="formX"><%=nullCheck(checkNumber)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Check Name:</span>
                                    <span class="formX"><%=nullCheck(checkName)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Amount(<strong>$</strong>):</span>
                                    <span class="formX"><%=amtFormat(amount)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Check Enclosed Amount(<strong>$</strong>):</span>
                                    <span class="formX"><%=amtFormat(checkAmount)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Pending Amount(<strong>$</strong>):</span>
                                    <span class="formX"><%=amtFormat(balAmt)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Check Date:</span>
                                    <% String chk_dte ="";
                                    if(checkDate!=null){ chk_dte=checkDate.substring(0,10);	}%>
                                    <span class="formX"><%=dateFormat(chk_dte)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Bank Name:</span>
                                    <span class="formX"><%=nullCheck(bankName)%></span>
                                </div>
                                <%	}
                                else if(paymentStatus!=null && (paymentStatus.equalsIgnoreCase("credit card") || paymentStatus.equalsIgnoreCase("Card")))
                                {  %>
                                <div class="rowHead">Transaction Details:</div>
                                <div class="row">
                                    <span class="label">Card Name:</span>
                                    <span class="formX"><%=nullCheck(cardName)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Card Type:</span>
                                    <span class="formX"><%=nullCheck(cardType)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Card Status:</span>
                                    <span class="formX"><%=nullCheck(cardStatus)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Transaction Id:</span>
                                    <span class="formX"><%=nullCheck(sslTxnId)%></span>
                                </div>
                                <div class="row">
                                    <span class="label">Amount(<strong>$</strong>):</span>
                                    <span class="formX"><%=amtFormat(amount)%></span>
                                </div>
                                <%	}	
                                ArrayList childPayment = (ArrayList)request.getAttribute("childPayment");
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
				<!--<div class="row">
				 
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
			 
                                try{
                                
                                String BalAmount = (String)request.getAttribute("BalAmount"); 
                                if (BalAmount!=null || BalAmount.trim().length()!=0){
                                float balanceAmt = Float.parseFloat(BalAmount); 
                                if(balanceAmt>0){
                                if(sslTxnId==null || sslTxnId.trim().length()==0)  {
                                %>
                                <div class="rowHead">Pending Payment Information:</div>
                                
                                <div class="row">
                                    <span class="label"><span class="styleBoldOne">Proceed to Payment For Pending Amount</span> </span>
                                    <span class="formX"><a href='userDuePay.do?process=duepay&totalAmount=<%=balanceAmt%>&paymentIdVal=<%=paymentId%>&horseMemberId=<%=horseMemberId%>&competitionName=<%=competitionName%>&amount=<%=amount%>'>Click Here To Make New Payment</a></span>
                                </div> 						
                                <div class="row">
                                    <span class="label">Check Amount Recieved(<strong>$</strong>):</span>
                                    <span class="formX"><%=amtFormat(checkAmount)%></span>
                                </div>
                                
                                <div class="row">
                                    <span class="label">Amount in Due(<strong>$</strong>) : </span>
                                    <span class="formX"><span class="styleBoldOne"><%=balanceAmt%></span></span>
                                </div>
                                
                                <%
                                }} 
                                } 
                                }
                                
                                catch(Exception e){
                                System.out.print(e);
                                }
                                
                                %>
                                <div class="alignCenter">
                                    <span><input type="button" value="Back" name="back" class="gradBtn" onclick="location.href='RegHorseListing.do?process=requestFrm'"/> 
                                    </span>
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
