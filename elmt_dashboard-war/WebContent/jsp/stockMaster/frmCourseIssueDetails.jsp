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
<title>Material Issue</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 <script type="text/javascript">
 
 function postData(rProcess){
	if(document.frmMatISsue.courseCode.value!=""){
		//alert(rProcess);
		document.frmMatISsue.stockMaintProcess.value = rProcess;
		//alert(frmRewalList.eventProcess.value);
		document.frmMatISsue.method="post";
		document.frmMatISsue.action="stock.do";
		document.frmMatISsue.submit();
	}
}

function postData_1(rProcess){
	if(document.frmMatISsue.pressName.value!=""){
		//alert(rProcess);
		document.frmMatISsue.stockMaintProcess.value = rProcess;
		//alert(frmRewalList.eventProcess.value);
		document.frmMatISsue.method="post";
		document.frmMatISsue.action="stock.do";
		document.frmMatISsue.submit();
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

					<form id="frmMatISsue" name="frmMatISsue" method="post" action="./stock.do" >
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="courseMatIssue">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
					<input type="hidden" name="printingID" value="" />
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
					<div><h2 align="center" style="color: black; align:centre">Material Issue Maintenance Using Course Code</h2></div>
					
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						<div style="color:red" align="center">* Fields are required</div>
		 						<table align="center" width="760">
		 						
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">Scan Barcode</td>
	 								  <td width="200"><input name="courseBarcode" type="text" style="width: 147px;" value="<%=nullCheck((String)request.getAttribute("courseBarcode"))%>" /><input name="courseIssue_submit" type="submit" value="Find" style="width: 49px; ">  </td>
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left" class="textBold">Course Code</td>
			 							<td width="200"><select name="courseCode" id="courseCode" style="width: 200px;" onchange="postData('SelectCourseNameOnIssue');">
								 		<option value="none">-----------------Select-----------------</option>
								 			<% 
								 				
												ArrayList arrayCourseList = (ArrayList)request.getAttribute("courseList");
												if(arrayCourseList!=null && arrayCourseList.size()!=0)
												{
													Iterator itCourseList = arrayCourseList.iterator();
													while(itCourseList.hasNext())
													{
														String[] courseList = (String [])itCourseList.next();
														String courseID = courseList[0];
														String courseCode = courseList[1];
														String courseName = courseList[2];
														String year_of_course = courseList[3];
				    									String pages = courseList[4];
														
														if(courseCode!=null)
														{
															%>
															<option value="<%=courseCode%>"><%=courseCode%></option>
															<%
														 }
													}
												}
											%>
											<option selected value='<%=nullCheck((String)request.getAttribute("courseCode"))%>'><%=nullCheck((String)request.getAttribute("courseCode"))%></option> 
								 		</select> </td>
		 							</tr>
		 												
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">Course Name</td>
	 								  <td width="200">
	 								  <input name="courseName" type="text" style="width: 200px;" value='<%=nullCheck((String)request.getAttribute("courseName"))%>'>
	 								  </td>
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left" class="textBold">Medium</td>
			 							<td width="200"><select name="medium"  style="width: 200px; ">
								 		<option value="none">-----------------Select-----------------</option>
								 		<option value="Tamil">Tamil</option>
								 		<option value="English">English</option>								 		
								 		<option selected value='<%=nullCheck((String)request.getAttribute("medium"))%>'><%=nullCheck((String)request.getAttribute("medium"))%></option> 
								 		</select></td>
		 							</tr>
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Centre Code</td>
	 								  <td width="200"><select name="centreCode" id="centreCode" style="width: 200px;" onchange="postData('SelectCentreNameOnIssue');">
								 		<option value="none">-----------------Select-----------------</option>
								 			<% 
								 				
												ArrayList arrayCentreList = (ArrayList)request.getAttribute("centreList");
												if(arrayCentreList!=null && arrayCentreList.size()!=0)
												{
													Iterator itCentreList = arrayCentreList.iterator();
													while(itCentreList.hasNext())
													{
														String[] centreList = (String [])itCentreList.next();
														String cenID = centreList[0];
														String cenCode = centreList[1];
														String cenName = centreList[2];
														
														if(cenCode!=null)
														{
															%>
															<option value="<%=cenCode%>"><%=cenCode%></option>
															<%
														 }
													}
												}
											%>
											<option selected value="<%=nullCheck((String)request.getAttribute("centreCode"))%>"><%=nullCheck((String)request.getAttribute("centreCode"))%></option> 
								 		</select></td>
									  
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left"  class="textBold">CentreName</td>
			 							<td width="200">
			 							
								 		<input name="centreName" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("centreName"))%>" /></td>
		 							</tr>
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Year of Study</td>
		 								<td width="200">
		 								<select name="YOS"  style="width: 200px; ">
								 		<option value="none">-----------------Select-----------------</option>
								 		<option value="First">First</option>
								 		<option value="Second">Second</option>
								 		<option value="Third">Third</option>
								 		<option selected value="<%=nullCheck((String)request.getAttribute("YOS"))%>">
										<%=nullCheck((String)request.getAttribute("YOS"))%></option> 
								 		</select>
		 								</td>
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left"  class="textBold">Available Quantity</td>
			 							<td width="200"><input name="availQuantity" type="text" class="textboxOne" style="width: 100px;" value="<%=nullCheck((String)request.getAttribute("availQuantity"))%>" /><input name="courseIssue_submit" type="submit" value="Check" style="width: 98px;" onclick="return(onValidate_1());"></td>
		 							</tr>
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 							<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Requested Quantity</td>
		 								<td width="200">
		 								<input name="reqQuantity" type="text" class="textboxOne" style="width: 200px;" value='<%=nullCheck((String)request.getAttribute("reqQuantity"))%>'>
		 								</td>
		 								<td width="50" ></td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Issued Quantity</td>
		 								<td width="200"><input name="issQuantity" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("issQuantity"))%>" /></td>
			 						</tr>
			 						<tr>
		 							<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Requested Date</td>
		 								<td width="200">
		 								<input name="reqDate" type="text" class="textboxOne" style="width: 165px;" value='<%=nullCheck((String)request.getAttribute("reqDate"))%>'>
		 								<a href="javascript:cal3.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">
		 								</td>
		 								<td width="50" ></td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Issued Date</td>
		 								<td width="200"><input name="issDate" type="text" class="textboxOne" style="width: 165px;" value="<%=nullCheck((String)request.getAttribute("issDate"))%>" /><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></td>
			 						</tr>
		 							<tr><td height="20"></td></tr>
		 						</table>
		 						
		 						<table align="center">
		 							<tr>
		 							<td><input name="courseIssue_submit" type="submit" value="Issue" style="width: 100px;" onclick="return(onValidate());"/></td>
		 							</tr>
		 						</table>
		 						<br>
		 						
				               		   		  <br/>
		 					</div>
		 					<br><br>
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


if(document.getElementById('reqDate'))
	{
	var cal3= new calendar2(document.forms['frmMatISsue'].elements['reqDate']);
	cal3.year_scroll = true;
	cal3.time_comp = false;
	}
	
	if(document.getElementById('issDate'))
	{
	var cal2= new calendar2(document.forms['frmMatISsue'].elements['issDate']);
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

function onValidate_1()
{
	if(document.frmMatISsue.courseCode.value==""){
		alert("courseCode cannot be empty");
		document.frmMatISsue.courseCode.focus();
		return false;
	}

	if(document.frmMatISsue.medium.value==""){
		alert("Medium cannot be empty");
		document.frmMatISsue.medium.focus();
		return false;
	}
	if(document.frmMatISsue.YOS.value==""){
		alert("Year of study cannot be empty");
		document.frmMatISsue.YOS.focus();
		return false;
	}
	return true;
}	
function onValidate()
{
	if(document.frmMatISsue.courseCode.value==""){
		alert("courseCode cannot be empty");
		document.frmMatISsue.courseCode.focus();
		return false;
	}
	
	if(document.frmMatISsue.courseName.value==""){
		alert("courseName cannot be empty");
		document.frmMatISsue.courseName.focus();
		return false;
	}
	
	if(document.frmMatISsue.medium.value==""){
		alert("Medium cannot be empty");
		document.frmMatISsue.medium.focus();
		return false;
	}
	
	if(document.frmMatISsue.centreCode.value==""){
		alert("centreCode cannot be empty");
		document.frmMatISsue.centreCode.focus();
		return false;
	}
	
	if(document.frmMatISsue.centreName.value==""){
		alert("centreName cannot be empty");
		document.frmMatISsue.centreName.focus();
		return false;
	}
	
	if(document.frmMatISsue.YOS.value==""){
		alert("Year of study cannot be empty");
		document.frmMatISsue.YOS.focus();
		return false;
	}
	
	if(document.frmMatISsue.availQuantity.value==""){
		alert("Available Quantity cannot be empty");
		document.frmMatISsue.availQuantity.focus();
		return false;
	}
	
	if(document.frmMatISsue.reqQuantity.value==""){
		alert("Requested Quantity be empty");
		document.frmMatISsue.reqQuantity.focus();
		return false;
	}
	
	if(document.frmMatISsue.issQuantity.value==""){
		alert("Issued Quantity cannot be empty");
		document.frmMatISsue.issQuantity.focus();
		return false;
	}
	
	if(document.frmMatISsue.reqDate.value==""){
		alert("Requested Date cannot be empty");
		document.frmMatISsue.reqDate.focus();
		return false;
	}
	
	if(document.frmMatISsue.issDate.value==""){
		alert("Issued Date cannot be empty");
		document.frmMatISsue.issDate.focus();
		return false;
	}
	
	if(document.frmMatISsue.reqQuantity.value!=""){
	if(isnotInteger(document.frmMatISsue.reqQuantity.value))
   { alert("Enter valid No of Copies... Requested Quantity should be numbers");
     document.frmMatISsue.reqQuantity.focus();
   return false; }
}	
	
if(document.frmMatISsue.issQuantity.value!=""){
	if(isnotInteger(document.frmMatISsue.issQuantity.value))
   { alert("Enter valid No of Copies... Issued Quantity should be numbers");
     document.frmMatISsue.issQuantity.focus();
   return false; }
}	
	
return true;
}


</script>
</html>
