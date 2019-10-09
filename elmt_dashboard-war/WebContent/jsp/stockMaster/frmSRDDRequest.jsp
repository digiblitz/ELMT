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
<title>Material Request</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 <script type="text/javascript">
 
 function postData(rProcess){
	if(document.frmIndentRequest.centreCode.value!=""){
		//alert(rProcess);
		document.frmIndentRequest.stockMaintProcess.value = rProcess;
		//alert(frmRewalList.eventProcess.value);
		document.frmIndentRequest.method="post";
		document.frmIndentRequest.action="stock.do";
		document.frmIndentRequest.submit();
	}
}

function postData_1(rProcess){
	if(document.frmIndentRequest.programCode.value!=""){
		//alert(rProcess);
		document.frmIndentRequest.stockMaintProcess.value = rProcess;
		//alert(frmRewalList.eventProcess.value);
		document.frmIndentRequest.method="post";
		document.frmIndentRequest.action="stock.do";
		document.frmIndentRequest.submit();
	}
}
</script>
 
 
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

					<form id="frmIndentRequest" name="frmIndentRequest" method="post" action="./stock.do" onSubmit="return(onValidate());">
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="srddRequestProcess">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
					<input type="hidden" name="matID" value="" />
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
					<div><h2 align="center" style="color: black; align:centre">SR &amp; DD Material Request to Warehouse</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						
		 						
		 						<div style="color:red" align="center">* Fields are required</div>
		 						<table align="center" width="760">
		 						<tr><td height="10"></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Centre Code</td>
	 								  <td width="200"><select name="centreCode" id="centreCode" style="width: 192px;" onchange="postData('SelectCentreName_SRDD');">
								 		<option value="none">-----------------Select-----------------</option>
								 			<% 
								 				
												ArrayList arrayCentreList = (ArrayList)request.getAttribute("centreList");
												if(arrayCentreList!=null && arrayCentreList.size()!=0)
												{
													Iterator itCentreList = arrayCentreList.iterator();
													while(itCentreList.hasNext())
													{
														String[] centreList = (String [])itCentreList.next();
														String centreID = centreList[0];
														String centreCode = centreList[1];
														String centreName = centreList[2];
														
														
														if(centreCode!=null)
														{
															%>
															<option value="<%=centreCode%>"><%=centreCode%></option>
															<%
														 }
													}
												}
											%>
											<option selected value="<%=nullCheck((String)request.getAttribute("centreCode"))%>"><%=nullCheck((String)request.getAttribute("centreCode"))%></option> 
								 		</select></td>
									  
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left"  class="textBold">Centre Name</td>
			 							<td width="200">
			 							
								 		<input name="centreName" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("centreName"))%>" /></td>
		 							</tr>
		 							
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Program Code</td>
	 								  <td width="200"><select name="programCode" id="programCode" style="width: 192px;" onchange="postData_1('SelectProgramName_SRDD');">
								 		
								 			<% 
								 				
												ArrayList arrayProgList = (ArrayList)request.getAttribute("progList");
												if(arrayProgList!=null && arrayProgList.size()!=0)
												{
													Iterator itProgList = arrayProgList.iterator();
													while(itProgList.hasNext())
													{
														String[] progList = (String [])itProgList.next();
														String progID = progList[0];
														String progCode = progList[1];
														String progName = progList[2];
														
														
														if(progCode!=null)
														{
															%>
															<option value="<%=progCode%>"><%=progCode%></option>
															<%
														 }
													}
												}
											%>
											<option selected value="<%=nullCheck((String)request.getAttribute("programCode"))%>"><%=nullCheck((String)request.getAttribute("programCode"))%></option> 
								 		</select></td>
									  
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left"  class="textBold">Program Name</td>
			 							<td width="200">
			 							
								 		<input name="programName" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("progName"))%>" /></td>
		 							</tr>
		 							
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 							<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Medium</td>
		 								<td width="200">
		 								<select name="medium" id="medium" style="width: 192px; ">
								 		<option value="none">-----------------Select-----------------</option>
								 		<option value="Tamil">Tamil</option>
								 		<option value="English">English</option>
								 		<option selected="" value="<%=nullCheck((String)request.getAttribute("medium"))%>"><%=nullCheck((String)request.getAttribute("medium"))%></option> 
								 		</select>
		 								</td>
		 								<td width="50" ></td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Year Of Study</td>
		 								<td width="200">
		 								<select name="year_of_study" id="year_of_study" style="width: 192px; ">
								 		<option value="First">First</option>
								 		<option value="Second">Second</option>	
								 		<option value="Third">Third</option>									 		
								 		<option selected="" value="<%=nullCheck((String)request.getAttribute("year_of_study"))%>"><%=nullCheck((String)request.getAttribute("medium"))%></option> 
								 		</select>
		 								
		 								</td>
			 						</tr>
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">Quantity</td>
	 								  <td width="200"><input name="quantity" type="text" class="textboxOne" style="width: 192px;" value="<%=nullCheck((String)request.getAttribute("quantity"))%>"/> </td>
			 							<td width="50" ></td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Requested date</td>
		 								<td width="200">
		 								<input name="requestedDate" id="requestedDate" type="text" class="textboxOne" style="width: 160px;" value="<%=nullCheck((String)request.getAttribute("requestedDate"))%>" >
		 								 <a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
		 								</td>
		 							
		 							<tr><td height="20"></td></tr>
		 						</table>
		 						
		 						<table align="center">
		 							<tr>
		 							<td><input name="SRDD_submit" type="submit" value="ADD" style="width: 100px;"/></td>
		 							</tr>
		 						</table>
		 						<br>
		 						<table id="tbl_request" align="center" width=700 style=" border-bottom-width: 1px;  border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px; background-color: #FFFFFF">
				          
										          <tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
										             <td width=80 class="textBold" align="center">	Sl.No</td>
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Program Code</td>
										              <td width=200 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Program Name</td>
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Medium</td>
										        
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Year Of Study</td>
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Quantity</td>
										             
										          </tr>
										          
										          <%
										          	String Coll = "";
										          	String Issue_ID = "";
										          	String due_days = "";
										          	String dueDate = "";
													String Book_ID = "";

													ArrayList requestList = (ArrayList)request.getAttribute("requestList");
													if(requestList!=null && requestList.size()!=0)
													{
														Iterator itReqList = requestList.iterator();
														int SI_No = 1;
														while(itReqList.hasNext())
														{
															String[] reqList = (String [])itReqList.next();											
														
															String requestID = reqList[0];											
												          	String centre_code = reqList[1];
												          	String centre_name = reqList[2];
												          	String program_code = reqList[3];
												           	String program_name = reqList[4];
												            String year_of_study = reqList[5];
												           	String medium = reqList[6];
												           	String quantity = reqList[7];
												           	String requested_date = reqList[8];	
												           	 
												           	 
												           	 System.out.println("requestID : "+requestID);
												           	 System.out.println("centre_code : "+centre_code);
												           	 System.out.println("centre_name : "+centre_name);
												           	 System.out.println("program_code : "+program_code);
												           	 
												           	 System.out.println("program_name : "+program_name);
												           	 System.out.println("year_of_study : "+year_of_study);
												           	 System.out.println("medium : "+medium);
												           	 
												           	 System.out.println("quantity : "+quantity);
												           	 System.out.println("requested_date : "+requested_date);
															 					           
														%>
				           									<tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
													             <td width=80 style="border-top-style: solid; border-top-color: #000000; border-top-width: thin; border-top-style: solid; border-top-color: #000000;border-top-width: thin" align="left">
													              	<input name="txt_siNo" type="text" value="<%=SI_No%>" style="width:79px; "/>
													              	</td>
													             
													              <td width=90 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="progCode" type="text" value="<%=program_code%>" style="width:89px; "/>
													              </td>
													            
													              <td width=200 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="progName" type="text" value="<%=program_name%>" style="width:199px;" />
													              </td>
													             
													              <td width=90 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              <input name="medium" type="text" value="<%=medium%>" style="width:89px;" />
													              
													              	
													              </td>
													             
													       
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													             	 <input name="yeas_Of_Study" type="text" value="<%=year_of_study%>" style="width:79px; "/></td>
													            
													              <td width=100 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="quantity" type="text" value="<%=quantity%>" style="width:99px; "/></td>
													             	
													          </tr>
				          				 				<%
				          				 					SI_No ++;
													    }
													}
												%>
										          
										          
				               		   		  </table>
				               		   		  <br/>
				               		   		 
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



	
	if(document.getElementById('requestedDate'))
	{
	var cal2= new calendar2(document.forms['frmIndentRequest'].elements['requestedDate']);
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
	if(document.frmIndentRequest.centreCode.value==""){
		alert("Centre Code cannot be empty");
		document.frmIndentRequest.centreCode.focus();
		return false;
	}
	
	if(document.frmIndentRequest.centreName.value==""){
		alert("Centre Name cannot be empty");
		document.frmIndentRequest.centreName.focus();
		return false;
	}
	
	if(document.frmIndentRequest.programCode.value==""){
		alert("Program Code cannot be empty");
		document.frmIndentRequest.programCode.focus();
		return false;
	}
	
		if(document.frmIndentRequest.programName.value==""){
		alert("Program Name cannot be empty");
		document.frmIndentRequest.programName.focus();
		return false;
	}
	
	if(document.frmIndentRequest.medium.value==""){
		alert("Medium cannot be empty");
		document.frmIndentRequest.medium.focus();
		return false;
	}
	
	if(document.frmIndentRequest.year_of_study.value==""){
		alert("Year of study cannot be empty");
		document.frmIndentRequest.year_of_study.focus();
		return false;
	}
	
	if(document.frmIndentRequest.quantity.value==""){
		alert("Quantity cannot be empty");
		document.frmIndentRequest.quantity.focus();
		return false;
	}
	
	if(document.frmIndentRequest.quantity.value!=""){

if(isnotInteger(document.frmIndentRequest.quantity.value))
   { alert("Enter valid pages... Pages should be numbers");
     document.frmIndentRequest.quantity.focus();
   return false; }
}	

	if(document.frmIndentRequest.requestedDate.value==""){
		alert("Requested Date cannot be empty");
		document.frmIndentRequest.requestedDate.focus();
		return false;
	}
	
	
	
	
	return true;
}


</script>
</html>
