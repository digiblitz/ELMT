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
<title>Inward Entry</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 <script type="text/javascript">
 
 function postData(rProcess){
	if(document.frmInward.courseCode.value!=""){
		//alert(rProcess);
		document.frmInward.stockMaintProcess.value = rProcess;
		//alert(frmRewalList.eventProcess.value);
		document.frmInward.method="post";
		document.frmInward.action="stock.do";
		document.frmInward.submit();
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

					<form id="frmInward" name="frmInward" method="post" action="./stock.do" >
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="inwardProcess">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
					<input type="hidden" name="inwardID" value="" />
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
					<div><h2 align="center" style="color: black; align:centre">Inward Entry</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						<div style="color:red" align="center">* Fields are required</div>
		 						<table align="center" width="760">
		 						
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">PO - No:</td>
	 								  <td width="200"><input name="po_no" type="text" class="textboxOne" style="width: 192px;" value="<%=nullCheck((String)request.getAttribute("po_no"))%>" /> </td>
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left" class="textBold">Order Date</td>
			 							<td width="200"><input name="orderDate" id="orderDate" type="text" class="textboxOne" style="width: 160px;" value="<%=nullCheck((String)request.getAttribute("orderDate"))%>" /> 
			 							<a href="javascript:cal3.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></td>
		 							</tr>
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">DC - No:</td>
	 								  <td width="200"><input name="dc_no" type="text" class="textboxOne" style="width: 192px;" value="<%=nullCheck((String)request.getAttribute("dc_no"))%>" />   </td>
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left" class="textBold">Delivery Date</td>
			 							<td width="200"><input name="deliveryDate" id="deliveryDate" type="text" class="textboxOne" style="width: 160px;" value="<%=nullCheck((String)request.getAttribute("deliveryDate"))%>" />
			 							<a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></td>
		 							</tr>
		 												
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">Press Name</td>
	 								  <td width="200">
	 								  <select name="pressName" id="pressName" style="width: 193px;" >
								 		<option value="none">-----------------Select-----------------</option>
								 			<jsp:scriptlet> 
								 				
												ArrayList arrayPressList = (ArrayList)request.getAttribute("pressList");
												if(arrayPressList!=null && arrayPressList.size()!=0)
												{
													Iterator itPressList = arrayPressList.iterator();
													while(itPressList.hasNext())
													{
														String[] pressList = (String [])itPressList.next();
														String pressID = pressList[0];
														String pressName = pressList[1];
														String pressAddress = pressList[2];
														String pressCity = pressList[3];
				    									
														
														if(pressName!=null)
														{
															</jsp:scriptlet>
															<option value="<%=pressName%>"><%=pressName %></option>
															<jsp:scriptlet>
														 }
													}
												}
											</jsp:scriptlet>
											<option selected value="<%=nullCheck((String)request.getAttribute("pressName"))%>"><%=nullCheck((String)request.getAttribute("pressName"))%></option> 
								 		</select>
	 								  </td>
			 						<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left"  class="textBold">Scan Course Barcode</td>
			 							<td width="200"><input name="courseBarcode" type="text" style="width: 149px;" value="<%=nullCheck((String)request.getAttribute("courseBarcode"))%>"/><input type="submit" name="inward_submit" value="Find" style="width: 49px;">
			 							</td>
			 							
		 							</tr>
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Course Code</td>
	 								  <td width="200"><select name="courseCode" id="courseCode" style="width: 192px;" onchange="postData('SelectNameOfCourse');">
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
											<option selected value="<%=nullCheck((String)request.getAttribute("courseCode"))%>"><%=nullCheck((String)request.getAttribute("courseCode"))%></option> 
								 		</select></td>
									  
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left"  class="textBold">Course Name</td>
			 							<td width="200">
			 							
								 		<input name="courseName" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("courseName"))%>" /></td>
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
								 		<option selected="" value="<%=nullCheck((String)request.getAttribute("medium"))%>"><jsp:expression>nullCheck((String)request.getAttribute("medium"))</jsp:expression></option> 
								 		</select>
		 								</td>
		 								<td width="50" ></td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">No of Copies</td>
		 								<td width="200"><input name="copies" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("copies"))%>" /></td>
			 						</tr>
		 							<tr><td height="20"></td></tr>
		 						</table>
		 						
		 						<table align="center">
		 							<tr>
		 							<td><input name="inward_submit" type="submit" value="ADD" style="width: 100px;" onclick="return(onValidate());"/></td>
		 							</tr>
		 						</table>
		 						<br>
		 						
				               		   		 
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



	if(document.getElementById('orderDate'))
	{
	var cal3= new calendar2(document.forms['frmInward'].elements['orderDate']);
	cal3.year_scroll = true;
	cal3.time_comp = false;
	}
	
	if(document.getElementById('deliveryDate'))
	{
	var cal2= new calendar2(document.forms['frmInward'].elements['deliveryDate']);
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
	if(document.frmInward.po_no.value==""){
		alert("PO Number cannot be empty");
		document.frmInward.po_no.focus();
		return false;
	}
	
	if(document.frmInward.orderDate.value==""){
		alert("Order date cannot be empty");
		document.frmInward.orderDate.focus();
		return false;
	}
	
	if(document.frmInward.dc_no.value==""){
		alert("DC Number cannot be empty");
		document.frmInward.dc_no.focus();
		return false;
	}
	
	if(document.frmInward.deliveryDate.value==""){
		alert("Delivery Date cannot be empty");
		document.frmInward.deliveryDate.focus();
		return false;
	}
	
	if(document.frmInward.pressName.value==""){
		alert("Press Name cannot be empty");
		document.frmInward.pressName.focus();
		return false;
	}
	
	if(document.frmInward.courseCode.value==""){
		alert("Course Code cannot be empty");
		document.frmInward.courseCode.focus();
		return false;
	}
	
	if(document.frmInward.courseName.value==""){
		alert("Course Name cannot be empty");
		document.frmInward.courseName.focus();
		return false;
	}
	
	if(document.frmInward.medium.value==""){
		alert("Medium cannot be empty");
		document.frmInward.medium.focus();
		return false;
	}
	
	if(document.frmInward.copies.value==""){
		alert("No of Copies cannot be empty");
		document.frmInward.copies.focus();
		return false;
	}
	
if(document.frmInward.copies.value!=""){
	if(isnotInteger(document.frmInward.copies.value))
   { alert("Enter valid No of Copies... No of Copies should be numbers");
     document.frmInward.copies.focus();
   return false; }
}	
	
return true;
}


</script>
</html>
