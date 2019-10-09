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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Material Issue Report</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 
 
 
 <link href="css/core-ie.css" type="text/css" rel="stylesheet" />  
</head>
<%! 

String  nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<body background="LMS_Background_1.jpg">

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
                    <!-- CENTER TABLE STARTS HERE -->	

					<form id="frmIssueReport" name="frmIssueReport" method="post" action="./stock.do" >
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="issueReportProcess">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
					<input type="hidden" name="issueID" value="" />
					  <%
						String status=(String)request.getAttribute("status");
						 if(status==null){
						 %>
						 <%
						 }else if(status!=null){
						 %>
						 <table align="center" width="760">
						   <tr>
					        <td width="200" align="center"><strong><font color="#FF0000"><%=status%></font></strong></td>
					      </tr>
						  </table>
						 
						 <%
						}
						
						%>
					<div><h2 align="center" style="color: black; align:centre">Material Issue Report Maintenance</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						
		 						
		 					<div style="color:red" align="center">* Fields are required</div>
		 						<table align="center" width="760">
		 						
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								
		 								
		 								<td width="100" align="left"  class="textBold">Requested Type</td>
		 								<td width="200">
		 								<select name="requestedType" id="requestedType" style="width: 190px; ">
								 		<option value="Centre Code Wise">Centre Code Wise</option>
								 		<option value="Program Code Wise">Program Code Wise</option>
								 		
								 		<option value="Issued Date Wise">Issued Date Wise</option>								 		
								 		<option selected="" value="<%=nullCheck((String)request.getAttribute("requestedType"))%>"><%=nullCheck((String)request.getAttribute("requestedType"))%></option> 
								 		</select>
		 								 
		 								</td>
		 							<td width="20"  align="left"  class="textBold"></td>
	 								 <td width="100" align="left"  class="textBold">Report Type</td>
									<td>	
										<input type="radio" name="reporttype" id="reporttype" value="Daily" style="width:30" > </input>Daily
										<input type="radio" name="reporttype" id="reporttype" value="Monthly/Yearly" style="width: 30px;"  ></input>Monthly/Yearly
									</td></tr>
			 						
		 							<tr><td height="20"></td></tr>
		 							
					 						<tr>
					 						<td width=1 style="color:red" align="right">*</td>
					 							<td width="100" align="left"  class="textBold">From Date</td>
					 							<td width="200"> <input name="fDate" id="fDate" type="text" width="170" value="<%=nullCheck((String)request.getAttribute("fromDate"))%>" />
					 							<a href="javascript:cal3.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> 
					 							</td>
					 							<td width="20" align="right"><font color="#FF0000">*</font></td>
					 							
					 							<td width="100">To Date</td>
					 							<td width="200"> <input name="tDate" id="tDate" type="text"  width="170" value="<%=nullCheck((String)request.getAttribute("toDate"))%>" />
					 							<a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> 
					 							</td>
					 						
					 							<td width="100" ><input name="issueReport_submit" type="submit" value="Find Report" style="width: 150px;" onclick="return(onValidate());"></td>
					 							<!--td><input name="issueBook_Submit" type="submit" value="Print" style="width: 100px;"/></td-->
					 						</tr>
					 						<tr><td height="20"></td></tr>
		 						</table>
		 						 <br/>
		 						
		 						<table id="tbl_request" align="center" width=700 style=" border-bottom-width: 1px;  border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px; background-color: #FFFFFF">
				          
										          <tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
										             <td width=50 class="textBold" align="center">	Sl.No</td>
										              <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Issued Date</td>
										              <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Centre Code</td>
										              <td width=150 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Centre Name</td>
										               <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Program  Code</td>
										              <td width=150 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Program Name</td>
										             
										              
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Medium</td>
										        
										          <!--   <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Year Of Study</td> -->   
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Academic Year</td>
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Issued Quantity</td>
										            <!--   <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Received Quantity</td>  -->
										              
										             
										             
										          </tr>
										          
										          <%
										              
										          	   String issued_date = null;
											        	String centre_code = null; 
											        	String centre_name = null;
											        	String program_code = null;  
											        	String program_name  = null;  
											        	String medium = null;
											        	String year_of_study = null;
											        	String issued_quantity = null;

													ArrayList issueList = (ArrayList)request.getAttribute("issReport_List");
													if(issueList!=null && issueList.size()!=0)
													{
														Iterator itReqList = issueList.iterator();
														int SI_No = 1;
														
														while(itReqList.hasNext())
														{
															
															String[] reqList = (String [])itReqList.next();	
																				
												          	 issued_date  = reqList[0];	
												        	  centre_code  = reqList[1];	
												        	  centre_name  = reqList[2];	
												        	  program_code  = reqList[3];	  
												        	  program_name   = reqList[4];	
												        	  medium  = reqList[5];	
												        	  year_of_study  = reqList[6];	
												        	  issued_quantity = reqList[7];	
												           	  
												           												           	 
															 					           
														%>
				           									<tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
													             <td width=50 style="border-top-style: solid; border-top-color: #000000; border-top-width: thin; border-top-style: solid; border-top-color: #000000;border-top-width: thin" align="left">
													              	<input name="txt_siNo" type="text" value="<%=SI_No%>" style="width:49px; "/>
													              	</td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="issuedDate" type="text" value="<%=nullCheck(issued_date)%>" style="width:79px; "/>
													              </td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="centreCode" type="text" value="<%=nullCheck(centre_code)%>" style="width:79px; "/>
													              </td>
													            
													              <td width=150 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="centreName" type="text" value="<%=nullCheck(centre_name)%>" style="width:149px;" />
													              </td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              <input name="programCode" type="text" value="<%=nullCheck(program_code)%>" style="width:79px;" /></td>
													              
													              <td width=150 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													             	 <input name="programName" type="text" value="<%=nullCheck(program_name)%>" style="width:149px; "/></td>
													            
													              <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="medium" type="text" value="<%=nullCheck(medium)%>" style="width:69px; "/></td>
													             	
													             <!--  	 <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													             	 <input name="yeas_Of_Study" type="text" value="nullCheck(year_of_study)%>" style="width:79px; "/></td>
													            -->
													              <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="yearOfStudy" type="text" value="<%=nullCheck(year_of_study)%>" style="width:69px; "/></td>
													          
													          <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="issuedQuantity" type="text" value="<%=nullCheck(issued_quantity)%>" style="width:69px; "/></td>
																
															<!-- 	<td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="receivedCopies" type="text"  value="=nullCheck(received_copies)%>" style="width:69px; "/></td>
													             	 
													             --> 	
													          
													          </tr>
				          				 				<%
				          				 					SI_No ++;
													    }
													}
												%>
										          
										          
				               		   		  </table>
				               		   		  <br/>
				               <table align="center">
		 							<tr>
		 							<td><input name="issueReport_submit" type="submit" value="Download" style="width: 100px;"/></td>
		 							</tr>
		 						</table>
		 						<br>
		 						<!-- <input name="mat_count" id="mat_count" type="hidden" value="mat_count%>"/> -->
		 					</div>
		 					
		 					<br>
		 				</div>
		 				
			</form>
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
<script type="text/javascript">



	if(document.getElementById('fDate'))
	{
	var cal3= new calendar2(document.forms['frmIssueReport'].elements['fDate']);
	cal3.year_scroll = true;
	cal3.time_comp = false;
	}
	
	if(document.getElementById('tDate'))
	{
	var cal2= new calendar2(document.forms['frmIssueReport'].elements['tDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
	}
	

	
	
	

function isnotInteger(str){
	stringIntCheck="0123456789";
	f2=1;
	for(j=0;j<str.length;j++){
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{
		return false;
	}
}
	
function onValidate()
{
	if(document.frmIssueReport.requestedType.value==""){
		alert("Requested Type cannot be empty");
		document.frmIssueReport.requestedType.focus();
		return false;
	}
	
	if(document.frmIssueReport.fDate.value==""){
		alert("From Date cannot be empty");
		document.frmIssueReport.fDate.focus();
		return false;
	}
	
	if(document.frmIssueReport.tDate.value==""){
		alert("To Date cannot be empty");
		document.frmIssueReport.tDate.focus();
		return false;
	}
	
	if(document.frmIssueReport.reporttype.value==""){
		alert("To Reporttype cannot be empty");
		document.frmIssueReport.tDate.focus();
		return false;
	}
	
	return true;
}


</script>
</html>
