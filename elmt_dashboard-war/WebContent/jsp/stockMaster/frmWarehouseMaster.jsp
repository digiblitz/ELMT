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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="java.util.*" errorPage="error.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Program And Course Mapping</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
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
<script>

  

    function cancelRlEntMap()
    {
        if(confirm("Do you want to Cancel and go back to index Page?"))
	{
        strURL = "./Login.do?loginProcess=bkInd";
	window.location.href = strURL;
        }
	else
	{
		return;
	}
    }
	
	function onValidate(){

	if(document.frmMpProgCourse.progCode.value==""){
		alert("Program Code cannot be empty");
		document.frmMpProgCourse.progCode.focus();
		return false;
	}
	
	if(document.frmMpProgCourse.courseCode.value==""){
		alert("Course Code cannot be empty");
		document.frmMpProgCourse.courseCode.focus();
		return false;
	}
	
	if(document.frmMpProgCourse.course_year.value==""){
		alert("Year of Course cannot be empty");
		document.frmMpProgCourse.course_year.focus();
		return false;
	}
	
	if(document.frmMpProgCourse.medium.value==""){
		alert("Medium cannot be empty");
		document.frmMpProgCourse.medium.focus();
		return false;
	}
	
return true;
}	
		
</script>


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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
				
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						 Map:<span class="styleBoldTwo">  Create Warehouse Entry </span>					</td>
				  </tr>
				  
				  <%
	String status=(String)request.getAttribute("status");
	 if(status==null){
	 %>
	 <%
	 }else if(status!=null){
	 %>
	   <tr>
        <td width="84" style="height:30" align="center"><strong><font color="#FF0000"><%=status%></font></strong></td>
      </tr>
	 
	 <%
	}
	
	%>
				  <tr>
					<td>
						
						
						 
				<form id="frmMpProgCourse" name="frmMpProgCourse" method="post" action="./stock.do" onsubmit="">
				
				<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="warehouseProcess" />
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>" />
						<input type="hidden" name="mappingID" id="mappingID" value="" />
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<%
						
						String pCode = nullCheck((String)request.getAttribute("pCode"));
						String pName= nullCheck((String)request.getAttribute("pName"));
						String cCode = nullCheck((String)request.getAttribute("cCode"));
						String cName= nullCheck((String)request.getAttribute("cName"));
						
						String pBarcode = nullCheck((String)request.getAttribute("progBarcode"));
						String cBarcode = nullCheck((String)request.getAttribute("courseBarcode"));
						
						 %>
						
						<tr>
		 								
		 								<td class="tableLeft">Scan Program Barcode:</td>
		 								<td class="tableRight">		 								
		 								<span class="alignLeft"><input type="text" name="progBarcode" id="progBarcode" value="<%=pBarcode%>" style="width:150px; "/>
		 								<font color="#FF0000">*</font></span></td>
		 								<td width=100><input type="submit" name="map_submit" Value="Find"/>
		 							</tr>
						
						<tr>  
					 <td class="tableLeft">Select a Program:</td>
						    <td class="tableRight">
							<span class="alignLeft">
						   <select name="progCode" id="progCode" class="selectboxOne">
            					<option  value="">Select One</option>
            						<%
									ArrayList arrayProgList = (ArrayList)request.getAttribute("progList");
									if(arrayProgList!=null && arrayProgList.size()!=0){
										Iterator itProgList = arrayProgList.iterator();
										while(itProgList.hasNext()){
											String[] progList = (String [])itProgList.next();											
											String progId = progList[0];
											String progCode = progList[1];
											String progName= progList[2];
											String progBarcode=progList[3];
											%>
									            <option value="<%=progCode%>"><%=progName%></option>
									           
									            <%
											 }
										}
									
							%>
							 <option selected="selected" value="<%=pCode%>"><%=pName%></option>
         			 </select>
          <font color="#FF0000">*</font></span></td>
          
				       </tr>
				       <tr>
		 								
		 								<td class="tableLeft">Scan Course Barcode:</td>
		 								<td class="tableRight">		 								
		 								<span class="alignLeft"><input type="text" name="courseBarcode" id="courseBarcode" value="<%=cBarcode%>" style="width:150px; "/>
		 								<font color="#FF0000">*</font></span></td>
		 								
		 							</tr>
					   	<tr>  
					 <td class="tableLeft">Select a Course:</td>
						    <td class="tableRight">
							<span class="alignLeft">
						   <select name="courseCode" id="courseCode" class="selectboxOne">
           					 <option  value="">Select One</option>
           						 <%
									ArrayList arrayCourseList = (ArrayList)request.getAttribute("courseList");
									if(arrayCourseList!=null && arrayCourseList.size()!=0){
										Iterator itCourseList = arrayCourseList.iterator();
										while(itCourseList.hasNext()){
											String[] courseList = (String [])itCourseList.next();										
											String courseID = courseList[0];
											String courseCode = courseList[1];
											String courseName = courseList[2];
											String CourseBarcode = courseList[5];
											%>
            <option value="<%=courseCode%>"><%=courseCode%></option>
           
            <%
											
										}
									}
							%>
							 <option selected="selected" value="<%=cCode%>"><%=cCode%></option>
          </select><font color="#FF0000">*</font></span></td>
		 </tr>
		 
		 <tr>  
					 <td class="tableLeft">Select Year Of Course :</td>
						    <td class="tableRight">
							<span class="alignLeft">
						   <select name="course_year" id="course_year" class="selectboxOne">
           					 <option  selected="selected" value="">Select One</option>
           					<option  value="First">First</option>
           					<option  value="Second">Second</option>
           					<option  value="Third">Third</option>	 
         					 </select>
         		 <font color="#FF0000">*</font></span></td>
		 </tr>
		 
		 <tr>  
					 <td class="tableLeft">Select a Medium : </td>
						    <td class="tableRight">
							<span class="alignLeft">
						   <select selected="selected" name="medium" id="medium" class="selectboxOne">
           					 <option  value="">Select One</option>
           					<option  value="Tamil">Tamil</option>
           					<option  value="English">English</option>
           					
         					 </select>
         		 <font color="#FF0000">*</font></span></td>
		 </tr>
		 
					
	<tr align="center">
	<td colspan="10" style="text-align:center" height="30">
	<input name="map_submit" type="submit" class="gradBtn" value="Map" onclick="return(onValidate());" />
          &nbsp;&nbsp;         
         <!--  <input name="button" type="button" class="gradBtn" value="Cancel" onclick="cancelRlEntMap();"/>  -->
		</td>
	  </tr>  	  
						  
						  
</form>

 

						</table>

					</td>
				 </tr>


				</table>
			<!-- CONTENTS END HERE -->
			</td>
		  </tr>
	  </table>

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
 

</body>
</html>
