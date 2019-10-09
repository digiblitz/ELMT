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
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<script language="javascript">
function myValidate(){
	if((document.frmViewHorseRelation.statSelect.value=="")||(document.frmViewHorseRelation.statSelect.selectedIndex==0)){
	alert("Select any of the Options in Horse Relationship Status");
	document.frmViewHorseRelation.statSelect.focus();
	return false;
	}
	if((document.frmViewHorseRelation.relstatSelect.value=="")||(document.frmViewHorseRelation.relstatSelect.selectedIndex==0)){
	alert("Select any of the Options in Horse Status");
	document.frmViewHorseRelation.relstatSelect.focus();
	return false;
	}
	return true;
}
</script>
 <%! 
    DecimalFormat result  = new DecimalFormat("0.00");
    String amtFormat(String amtVal){
        String amountValue = "0.00";
        if(amtVal!=null && amtVal.trim().length()!=0){
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
<%! float floatCheck(float fieldName){
	float floateValue = 0;
		if(fieldName!=0.0){
		 floateValue = fieldName;
		}
	return floateValue;
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
				<form name="frmViewHorseRelation" id="frmViewHorseRelation" method="post" action="RegHorseListing.do" onsubmit ="return myValidate();">
				<input type="hidden" name="process" value="relationChange"/>
				 <div class="cmmnForm">
                                    <div class="colspan">
                                        <strong>Membership:</strong> <span class="styleBoldTwo">Horse
                                        Registration Owner/Rider Activation Details </span> 
                                    </div>
                                    
                                    <div class="rowHead">Owner/Rider Approve Status Information:</div>
                                    <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
											<tr>
											<td width="169" height="18" class="tblRowHeadTwo"> UserName</td>
											<td width="192" class="tblRowHeadTwo"> Relationship Type </td>
											<td width="135" class="tblRowHeadTwo">Relationship Status</td>
											
											</tr>
                                        
                                        <%
												ArrayList relationshipDetails= (ArrayList) request.getAttribute("relationshipDetails");
												if(relationshipDetails!=null && relationshipDetails.size()!=0){
													String firstName = "";
													String last_name = "";
													String relationship_type_id = "";
													String relationship_type_name = "";
													String relationship_status ="";
													String relation_id = "";
													String memberId = "";
													Iterator itr = 	relationshipDetails.iterator();
														while(itr.hasNext()) {				   
														String[] val=(String[])itr.next();
															 
																firstName = val[0];
																last_name = val[1];
																relationship_type_id = val[2];
																relationship_type_name = val[3];
																relationship_status =val[4];
																relation_id = val[5];
																memberId = val[6];	
                                        %>
                                       <tr>
									<td bgcolor="#E3E1D2" class="alignLeft"><%=firstName%>&nbsp;<%=last_name%></td>								
									<td bgcolor="#E3E1D2" class="alignLeft"><%=relationship_type_name%></td>
									<td bgcolor="#E3E1D2" class="alignLeft"><%=relationship_status%></td>
									
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
 			
						
						<div class="rowHead">Horse Registration Owner/Rider Activation Details</div>
						
			
						<% 
						    String horseUserName = "";
							String horseRelationship ="";
						    String horseRelationStatus ="";
						    String horseMembId = "";
						    String horseName= "";
						String relationId = (String)request.getAttribute("relationId");
						String [] horseRelationDetails = (String [] )request.getAttribute("horseRelationDetails");
						if(horseRelationDetails !=null && horseRelationDetails.length!=0){
							  horseUserName = horseRelationDetails[0]+","+horseRelationDetails[1];
							  horseRelationship =horseRelationDetails[3] ;
						      horseRelationStatus =horseRelationDetails[4];
						      horseMembId = horseRelationDetails[6];
						      horseName= horseRelationDetails[7];
						   
						   %>
			
				<div class="row">
								<span class="label">Horse MemberId:</span>
								<span class="formX"><span class="styleBoldOne"><%=nullCheck(horseMembId)%></span></span>	
				  </div>

			    <div class="row">
					<span class="label">Horse Name :</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(horseName)%></span></span>
		  </div>
			  
			    <div class="row">
				<span class="label"> Username:</span>
				<span class="formX"><span class="styleBoldOne"><%=nullCheck(horseUserName)%></span></span>
			  </div>

			    <div class="row">
				<span class="label">Relationship Type:</span>
				<span class="formX"><span class="styleBoldOne"><%=nullCheck(horseRelationship)%></span></span>			  </div>
			  
			    <div class="row">
				<span class="label">Relationship Status:</span>
				<span class="formX"><span class="styleBoldOne"><%=nullCheck(horseRelationStatus)%></span></span>
				</div>
				
			    <div class="row">
					<span class="label">Assign Relationship Type:</span>
					<span class="formX"><span class="styleBoldOne">
					<select name="statSelect" class="selectboxOne" > 
								  <option value="" selected="selected">Select One</option>		
                                    <%
									ArrayList horseRelation = (ArrayList)request.getAttribute("horseRelation");
																	
									if(horseRelation!=null&& horseRelation.size()!=0){
										Iterator itStatus = horseRelation.iterator();
										while(itStatus.hasNext()){
											String statusListArray[] = (String [])itStatus.next();
											if(statusListArray!=null && statusListArray.length!=0) {
												String statusId = statusListArray[0];
												String statusName = statusListArray[1];
												if(statusName!=null && statusName.equals(horseRelationship)){%>
												<option  value="<%=statusId%>" selected="selected"><%=statusName%></option>
												<%
												}else{%>
												<option  value="<%=statusId%>"><%=statusName%></option>
												<%   }
											}
										 }
									 }  %>
                    </select>
				  </span></span>
				</div>
				<div class="row">
					<span class="label">Approval    Status:</span>
					<span class="formX"><span class="styleBoldOne">
					<select name="relstatSelect" class="selectboxOne" > 
					<option value="" selected="selected">Select One</option>
					<%
					  String [] resultStatSelect =new String[]{"Active","Pending","Reject"};
					  if(horseRelationStatus!=null && horseRelationStatus.trim().length()!=0){
					  	for(int i = 0;i<resultStatSelect.length;i++){
						  if(resultStatSelect[i].equals(horseRelationStatus)){%>
						  <option value="<%=resultStatSelect[i]%>" selected="selected"><%=resultStatSelect[i]%></option>
						  <%
						  }
						  else {%>
						  <option value="<%=resultStatSelect[i]%>"><%=resultStatSelect[i]%></option>
						  <%}
					    } //End of For					  
					   }else{
					   %>
						<option value="" selected="selected">Select One</option>		
						<option value="Active" >Active</option>
						<option value="Pending">Pending</option>
						<option value="Reject">Reject</option>
						<%}%>		  
                    </select>
					<%}%>
				  </span></span>
			  </div>


			  <div class="row">
				<%
				String regByuserId ="";
				 String [] strHorseDet = (String []) request.getAttribute("strHorseDet");
				 if(strHorseDet!=null && strHorseDet.length!=0){
				 String hrMemberId = strHorseDet[0];	
				 String paymentId = strHorseDet[1];	
				 String reqAmount = strHorseDet[2];	
				 String temp=strHorseDet[3];
				 String[] reqDate= temp.split(" ");	
				 String comName = strHorseDet[4];			 
				 String reqBy = strHorseDet[5]; 
				   regByuserId = strHorseDet[6];
				 
				%>
					<span class="label">Requested Date :</span>
					<span class="formX"><span class="styleBoldOne"><%=dateFormat(reqDate[0])%></span></span>
		  </div>
		  <div class="row">
					<span class="label">Requested By:</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(reqBy)%></span></span>
		  </div>
		  <% } 
				String payinfo[] =(String[]) request.getAttribute("payDetails");
				String checkNumber ="";
			    String paymentStatus = "";
				String checkName ="";
			    String sslTxnId = "";
				String bankName ="";
			    String checkDate = "";
				String amount = "";
				String checkAmount = "";
				String balAmt ="";
				float payamt = 0.0f;
				boolean show_pay = true;
				String sslResult ="";
				String tempPaymentId="";
				if(payinfo!=null && payinfo.length !=0){%>
					
					<%
					 checkNumber =  payinfo[0];
					 paymentStatus =  payinfo[1];
					 checkName =  payinfo[2];
					 sslTxnId = payinfo[3];
					 bankName =  payinfo[4];
					 checkDate =  payinfo[5];
					 amount =  payinfo[6];
					 checkAmount =  payinfo[7];
					 sslResult =  payinfo[8];
					 tempPaymentId =  payinfo[9];
					 balAmt = payinfo[10];
					if(paymentStatus!=null &&paymentStatus.equalsIgnoreCase("check")) {
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
					<% 
							String chk_dte ="";
							if(checkDate!=null) chk_dte=checkDate.substring(0,10);	
						%>
					
					<div class="row">
						<span class="label">Check Date:</span>
						
						<span class="formX"><%=dateFormat(chk_dte)%></span>
					</div>
					<div class="row">
							<span class="label">Bank Name:</span>
							<span class="formX"><%=nullCheck(bankName)%></span>
					</div>
						
				<%
				}
				else if(paymentStatus!=null && (paymentStatus.equalsIgnoreCase("Card") || paymentStatus.equalsIgnoreCase("credit card")))
					{   %>
				<div class="row">
					<span class="label">Transaction Id:</span>
					<span class="formX"><%=nullCheck(sslTxnId)%></span>
				</div>
				<div class="row">
						<span class="label">Amount(<strong>$</strong>):</span>
						<span class="formX"><%=amtFormat(amount)%></span>
				</div>
				<%	}	 
		 				 
			 	}	
				
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
				<div class="rowHead">Next Transaction Details:&nbsp;&nbsp;&nbsp;<span class="styleBoldOne"><%=i%></span></div>
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
 						<span class="formX"><%=chk_dte1%></span>
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
						
							 if(paymentStatus!=null){
								 if(horseRelationStatus!=null && horseRelationStatus.equals("Pending")){
									 if(payinfo!=null && payinfo.length !=0){
									 %>
									 <div class="rowHead">
										Change Payment Information:
									</div>
									 
									<div class="row">
										<span class="label"><span class="styleBoldOne">Change Payment For Pending Amount</span> </span>
										<span class="formX"><a href='chngHorseName.do?process=chngpay'>Click Here To Change Payment</a></span>
									</div>
									 
									 <%
									 }
								 }
							 }
						 }
						 catch(Exception e){
						 System.out.print("Exception is e:" + e.getMessage());
						 }
											
							session.setAttribute("horseMemberId",horseMembId);
							session.setAttribute("competitionName",horseName);
							session.setAttribute("paymentIdVal",tempPaymentId);
							session.setAttribute("regByuserId",regByuserId);
	
						%> 
				<div class="alignCenter">
							<span>
							<input type="hidden" name="relationId" value="<%=relationId%>"><br />
							<% String horseMemberId = (String) request.getAttribute("horseMemberId"); 
							 %>
							 <input type="hidden" name="horseMemberId" value="<%=horseMemberId%>"/>
							 								
															
							<input type="submit" value="Submit" name="submit" class="gradBtn" />&nbsp;&nbsp;
							<input type="button" value="Back" name="back" class="gradBtn" onclick="javascript:history.back(-1);"/>
						</span>
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