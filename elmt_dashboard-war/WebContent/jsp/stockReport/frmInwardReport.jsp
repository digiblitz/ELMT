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
<title>Inward Report</title>
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

					<form id="frmInwardReport" name="frmInwardReport" method="post" action="./stock.do" >
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="inwardReportProcess">
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
					<div><h2 align="center" style="color: black; align:centre">Material Inward Report Maintenance</h2></div>
						
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
								 		<option value="Course Code Wise">Course Code Wise</option>
								 		<option value="Press Name Wise">Press Name Wise</option>
								 		<option value="Delivery Date Wise">Delivery Date Wise</option>								 		
								 		<option selected="" value="<%=nullCheck((String)request.getAttribute("requestedType"))%>"><%=nullCheck((String)request.getAttribute("requestedType"))%></option> 
								 		</select>
		 								 
		 								</td>
		 							<td width="20"  align="left"  class="textBold"></td>
	 								 
			 						
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
					 						
					 							<td width="100" ><input name="inwardReport_submit" type="submit" value="Find Report" style="width: 150px;" onclick="return(onValidate());"></td>
					 							<!--td><input name="issueBook_Submit" type="submit" value="Print" style="width: 100px;"/></td-->
					 						</tr>
					 						<tr><td height="20"></td></tr>
		 						</table>
		 						 <br/>
		 						
		 						<table id="tbl_request" align="center" width=700 style=" border-bottom-width: 1px;  border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px; background-color: #FFFFFF">
				          
										          <tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
										             <td width=50 class="textBold" align="center">	Sl.No</td>
										              <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Delivery Date</td>
										              <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">DC Number</td>
										              <td width=150 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Press Name</td>
										               <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Course Code</td>
										              <td width=150 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Course Name</td>
										             
										              
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Medium</td>
										        
										          <!--   <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Year Of Study</td> -->   
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">PO Number</td>
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Ordered Date</td>
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Received Quantity</td>
										              
										             
										             
										          </tr>
										          
										          <%
										              
										          	    String course_code = null;
											        	String course_name =null; 
											        	String medium = null;
											        	String PO_No = null;  
											        	String press_name = null;
											        	String ordered_date = null;
											        	String DC_No = null;
											        	String delivery_date= null;
											        	String received_copies= null;

													ArrayList inwardList = (ArrayList)request.getAttribute("inwardReport_List");
													if(inwardList!=null && inwardList.size()!=0)
													{
														Iterator itReqList = inwardList.iterator();
														int SI_No = 1;
														
														while(itReqList.hasNext())
														{
															
															String[] reqList = (String [])itReqList.next();	
																				
												          	 delivery_date = reqList[0];	
												        	  DC_No = reqList[1];	
												        	  press_name = reqList[2];	
												        	  course_code = reqList[3];	  
												        	  course_name = reqList[4];	
												        	  medium = reqList[5];	
												        	  PO_No = reqList[6];	
												        	  ordered_date= reqList[7];	
												           	  received_copies = reqList[8];
												           												           	 
															 					           
														%>
				           									<tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
													             <td width=50 style="border-top-style: solid; border-top-color: #000000; border-top-width: thin; border-top-style: solid; border-top-color: #000000;border-top-width: thin" align="left">
													              	<input name="txt_siNo" type="text" value="<%=SI_No%>" style="width:49px; "/>
													              	</td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="deliveryDate" type="text" value="<%=nullCheck(delivery_date)%>" style="width:79px; "/>
													              </td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="dcNumber" type="text" value="<%=nullCheck(DC_No)%>" style="width:79px; "/>
													              </td>
													            
													              <td width=150 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="pressName" type="text" value="<%=nullCheck(press_name)%>" style="width:149px;" />
													              </td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              <input name="courseCode" type="text" value="<%=nullCheck(course_code)%>" style="width:79px;" /></td>
													              
													              <td width=150 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													             	 <input name="courseName" type="text" value="<%=nullCheck(course_name)%>" style="width:149px; "/></td>
													            
													              <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="medium" type="text" value="<%=nullCheck(medium)%>" style="width:69px; "/></td>
													             	
													             <!--  	 <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													             	 <input name="yeas_Of_Study" type="text" value="nullCheck(year_of_study)%>" style="width:79px; "/></td>
													            -->
													              <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="PO_NO" type="text" value="<%=nullCheck(PO_No)%>" style="width:69px; "/></td>
													          
													          <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="orderedDate" type="text" value="<%=nullCheck(ordered_date)%>" style="width:69px; "/></td>
																
																<td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="receivedCopies" type="text"  value="<%=nullCheck(received_copies)%>" style="width:69px; "/></td>
													             	 
													             	
													          
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
		 							<td><input name="inwardReport_submit" type="submit" value="Download" style="width: 100px;"/></td>
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
	var cal3= new calendar2(document.forms['frmInwardReport'].elements['fDate']);
	cal3.year_scroll = true;
	cal3.time_comp = false;
	}
	
	if(document.getElementById('tDate'))
	{
	var cal2= new calendar2(document.forms['frmInwardReport'].elements['tDate']);
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
	if(document.frmInwardReport.requestedType.value==""){
		alert("Requested Type cannot be empty");
		document.frmInwardReport.requestedType.focus();
		return false;
	}
	
	if(document.frmInwardReport.fDate.value==""){
		alert("From Date cannot be empty");
		document.frmInwardReport.fDate.focus();
		return false;
	}
	
	if(document.frmInwardReport.tDate.value==""){
		alert("To Date cannot be empty");
		document.frmInwardReport.tDate.focus();
		return false;
	}
	
	return true;
}


</script>
</html>
