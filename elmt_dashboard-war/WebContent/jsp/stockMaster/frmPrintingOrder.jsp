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
<title>Printing Order</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 <script type="text/javascript">
 
 function postData(rProcess){
	if(document.frmPrinitingOrder.courseCode.value!=""){
		//alert(rProcess);
		document.frmPrinitingOrder.stockMaintProcess.value = rProcess;
		//alert(frmRewalList.eventProcess.value);
		document.frmPrinitingOrder.method="post";
		document.frmPrinitingOrder.action="stock.do";
		document.frmPrinitingOrder.submit();
	}
}

function postData_1(rProcess){
	if(document.frmPrinitingOrder.pressName.value!=""){
		//alert(rProcess);
		document.frmPrinitingOrder.stockMaintProcess.value = rProcess;
		//alert(frmRewalList.eventProcess.value);
		document.frmPrinitingOrder.method="post";
		document.frmPrinitingOrder.action="stock.do";
		document.frmPrinitingOrder.submit();
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

					<form id="frmPrinitingOrder" name="frmPrinitingOrder" method="post" action="./stock.do" >
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="printingProcess">
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
					<div><h2 align="center" style="color: black; align:centre">Material Production & Distribution Division</h2></div>
					<div><h3 align="center" style="color: black; align:centre">Printing Order To Press</h3></div>	
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						<div style="color:red" align="center">* Fields are required</div>
		 						<table align="center" width="760">
		 						
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">PO - No:</td>
	 								  <td width="200"><input name="po_no" type="text" class="textboxOne" style="width: 100px;" value="<%=nullCheck((String)request.getAttribute("po_no"))%>" /> <input name="print_submit" type="submit" value="Generater" style="width: 90px; "/> </td>
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left" class="textBold">Order Date</td>
			 							<td width="200"><input name="orderDate" id="orderDate" type="text" class="textboxOne" style="width: 160px;" value="<%=nullCheck((String)request.getAttribute("orderDate"))%>"/> <a href="javascript:cal3.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></td>
		 							</tr>
		 												
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100"  align="left"  class="textBold">Press Name</td>
	 								  <td width="200">
	 								  <select name="pressName" id="pressName" style="width: 200px;" onchange="postData_1('SelectPressAddress');">
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
			 							<td width="100" align="left" class="textBold">Press Address</td>
			 							<td width="200"><input name="pressAddress" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("pressAddress"))%>"/></td>
		 							</tr>
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Course Code</td>
	 								  <td width="200"><select name="courseCode" id="courseCode" style="width: 200px;" onchange="postData('SelectCourseName');">
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
		 								<td width="100" align="left"  class="textBold">Nature of Print</td>
		 								<td width="200">
		 								<select name="printNature" id="printNature" style="width: 200px; ">
								 		<option value="none">-----------------Select-----------------</option>
								 		<option value="Fresh">Fresh</option>
								 		<option value="RePrint">RePrint</option>
								 		<option selected value="<%=nullCheck((String)request.getAttribute("printNature"))%>"><%=nullCheck((String)request.getAttribute("printNature"))%></option> 
								 		</select>
		 								</td>
			 							<td width="50" ></td>
			 							<td width=1 style="color:red" align="right">*</td>
			 							<td width="100" align="left"  class="textBold">No of Pages</td>
			 							<td width="200"><input name="pages" type="text" class="textboxOne" style="width: 200px;" value="<%=nullCheck((String)request.getAttribute("pages"))%>" /></td>
		 							</tr>
		 							<tr><td height="10"></td></tr>
		 							<tr>
		 							<td width=1 style="color:red" align="right">*</td>
		 								<td width="100" align="left"  class="textBold">Medium</td>
		 								<td width="200">
		 								<select name="medium" id="medium" style="width: 200px; ">
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
		 							<td><input name="print_submit" type="submit" value="ADD" style="width: 100px;" onclick="return(onValidate());"/></td>
		 							</tr>
		 						</table>
		 						<br>
		 						<table id="tbl_printOrder" align="center" width=700 style=" border-bottom-width: 1px;  border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px; background-color: #FFFFFF">
				          
										          <tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
										             <td width=80 class="textBold" align="center">	Sl.No</td>
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Course Code</td>
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Course Title</td>
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Medium</td>
										        
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Nature of Print</td>
										              <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">No of Pages</td>
										               <td width=100 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">No of Copies</td>
										              
										          </tr>
										          
										          <%
										          	String Coll = "";
										          	String Issue_ID = "";
										          	String due_days = "";
										          	String dueDate = "";
													String Book_ID = "";

													ArrayList orderList = (ArrayList)request.getAttribute("orderList");
													if(orderList!=null && orderList.size()!=0)
													{
														Iterator itOrderList = orderList.iterator();
														int SI_No = 1;
														while(itOrderList.hasNext())
														{
															String[] ordList = (String [])itOrderList.next();											
														
															String PO_No = ordList[0];											
												          	String ordered_date = ordList[1];
												          	String pressName_1 = ordList[2];
												          	String pressAddress = ordList[3];
												           	String course_code = ordList[4];
												            String course_name = ordList[5];
												           	String medium = ordList[6];
												           	String nature_of_print = ordList[7];
												           	String pages = ordList[8];	
												           	String no_of_copies = ordList[9];
												           						           	
												           	 
												           	 System.out.println("PO_No : "+PO_No);
												           	 System.out.println("ordered_date : "+ordered_date);
												           	 System.out.println("pressName_1 : "+pressName_1);
												           	 System.out.println("pressAddress : "+pressAddress);
												           	 
												           	 System.out.println("course_code : "+course_code);
												           	 System.out.println("course_name : "+course_name);
												           	 System.out.println("medium : "+medium);
												           	 
												           	 System.out.println("nature_of_print : "+nature_of_print);
												           	 System.out.println("pages : "+pages);
															 System.out.println("no_of_copies : "+no_of_copies);					           
														%>
				           									<tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
													             <td width=80 style="border-top-style: solid; border-top-color: #000000; border-top-width: thin; border-top-style: solid; border-top-color: #000000; 
													              	border-top-width: thin" align="left"><input name="txt_siNo" type="text" value="<%=SI_No%>" style="width:79px; "/></td>
													             
													              <td width=90 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="courseCode" type="text" value="<%=course_code%>" style="width:89px; "/>
													              </td>
													            
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="courseName" type="text" value="<%=course_name%>" style="width:89px; "/>
													              </td>
													             
													              <td width=90 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left"/><input name="medium" type="text" value="<%=medium%>" style="width:89px; ">
													              	
													              </td>
													             
													       
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="nature_of_print" type="text" value="<%=nature_of_print%>" style="width:79px; "/></td>
													            
													              <td width=100 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="pages" type="text" value="<%=pages%>" style="width:99px; "/></td>
													             	 
													             	 <td width=100 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="no_of_copies" type="text" value="<%=no_of_copies%>" style="width:99px; "/></td>
													            
													              
													          </tr>
				          				 				<%
				          				 					SI_No ++;
													    }
													}
												%>
										          
										          
				               		   		  </table>
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



	if(document.getElementById('orderDate'))
	{
	var cal3= new calendar2(document.forms['frmPrinitingOrder'].elements['orderDate']);
	cal3.year_scroll = true;
	cal3.time_comp = false;
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
	if(document.frmPrinitingOrder.po_no.value==""){
		alert("PO Number cannot be empty");
		document.frmPrinitingOrder.po_no.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.orderDate.value==""){
		alert("Order Date cannot be empty");
		document.frmPrinitingOrder.orderDate.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.pressName.value==""){
		alert("Press Name cannot be empty");
		document.frmPrinitingOrder.pressName.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.pressAddress.value==""){
		alert("Press Address cannot be empty");
		document.frmPrinitingOrder.pressAddress.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.courseCode.value==""){
		alert("Course Code cannot be empty");
		document.frmPrinitingOrder.courseCode.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.courseName.value==""){
		alert("Course Name cannot be empty");
		document.frmPrinitingOrder.courseName.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.printNature.value==""){
		alert("Print Nature cannot be empty");
		document.frmPrinitingOrder.printNature.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.pages.value==""){
		alert("Pages cannot be empty");
		document.frmPrinitingOrder.pages.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.medium.value==""){
		alert("Medium cannot be empty");
		document.frmPrinitingOrder.medium.focus();
		return false;
	}
	
	if(document.frmPrinitingOrder.copies.value==""){
		alert("Copies cannot be empty");
		document.frmPrinitingOrder.copies.focus();
		return false;
	}
	
if(document.frmPrinitingOrder.copies.value!=""){
	if(isnotInteger(document.frmPrinitingOrder.copies.value))
   { alert("Enter valid No of Copies... No of Copies should be numbers");
     document.frmPrinitingOrder.copies.focus();
   return false; }
}	
	
return true;
}


</script>
</html>
