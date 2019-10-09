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
<title>Add New Course</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 
 <link href="css/core-ie.css" type="text/css" rel="stylesheet" />  
</head>
<script type="text/javascript"> 
	 function showVal(course_id, course_code, course_name, year_Of_Course, No_Of_Pages,course_barcode)
     { 
       //alert(roleVal);
	   // alert(rolDes);
	    var courseID = course_id;
	    var courseCode = course_code;
	    var courseName = course_name;
	    var yearOfCourse = year_Of_Course;
	    var Pages = No_Of_Pages;
		var coursebarcode = course_barcode;
		
	    document.getElementById('courseID').value = courseID;
	    document.getElementById('courseCode').value = courseCode;
	    document.getElementById('courseName').value = courseName;
	    document.getElementById('course_year').value = yearOfCourse;
	    document.getElementById('pages').value = Pages;
	    document.getElementById('courseBarcode').value = coursebarcode;
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
			if(document.frmCourseMaster.courseCode.value==""){
				alert("Course Code cannot be empty");
				document.frmCourseMaster.courseCode.focus();
				return false;
			}
			
			if(document.frmCourseMaster.courseName.value==""){
				alert("Course Name cannot be empty");
				document.frmCourseMaster.courseName.focus();
				return false;
			}
			
			if(document.frmCourseMaster.course_year.value==""){
				alert("Year Of Course cannot be empty... Please select the Year");
				document.frmCourseMaster.course_year.focus();
				return false;
			}
		     
			if(document.frmCourseMaster.pages.value==""){
				alert("No of pages cannot be empty");
				document.frmCourseMaster.pages.focus();
				return false;
			}
			
			if(document.frmCourseMaster.pages.value!=""){
			if(isnotInteger(document.frmCourseMaster.pages.value))
		    { alert("Enter valid No of pages... No of pages should be numbers");
		     document.frmCourseMaster.pages.focus();
		     return false;
		     }
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
                    <!-- CENTER TABLE STARTS HERE -->	

					<form id="frmCourseMaster" name="frmCourseMaster" method="post" action="./stock.do"  onsubmit="return(onValidate());">
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="courseProcess">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
						<input type="hidden" name="courseID" id="courseID" value="" />
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
					<div><h2 align="center" style="color: black; align:centre">Course Maintenance</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						
		 						<table align="center" width=600>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter Course Code</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="courseCode" id="courseCode" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter Course Name</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="courseName" id="courseName" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Scan Barcode</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="courseBarcode" id="courseBarcode" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter Year of Course</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=150 align="left">
		 								<select name="course_year" id="course_year" style="width: 150px; ">
								 		<option value="">-----------Select------------</option>
								 		<option value="First">First</option>
								 		<option value="Second">Second</option>
								 		<option value="Third">Third</option>
		 								</select>
		 								</td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">No of Pages</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="pages" id="pages" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							
		 						</table>
		 						<br>
		 						<table width=760 align="center">
		 							<tr>
		 								
		 								<td width=400 align="left"><b>Click and Update the below Details</b></td>
		 								<td width=200></td>
		 							</tr>
		 								<%
											ArrayList courseList =(ArrayList)request.getAttribute("courseList");
											
											if(courseList!=null && courseList.size()!=0){
											
											Iterator itr = courseList.iterator();
											 while (itr.hasNext()) {
											 String sr[] = (String[]) itr.next();
											 String course_id = sr[0]; 
											 String course_code = sr[1]; 
											 String course_name = sr[2]; 
											 String year_of_course = sr[3]; 
											 String pages = sr[4]; 
											 String course_barcode=sr[5];
											 %> 
											<tr>
											<td class="tableRight" colspan="5" >
											<a href="#" onclick="showVal('<%=course_id%>','<%=course_code%>','<%=course_name%>','<%=year_of_course%>','<%=pages%>','<%=course_barcode%>');"><%=course_code%>    -   <%=course_name%></a></td>		
										   	</tr> 
											<%
											}
											}else{
											%>
											<tr>
											<td colspan="2" align="center">
											No records found
											</td>
											
											</tr>
											<%
											}
											%>
		 						</table>
		 						<br>
		 						<table align="center">
		 							<tr>
		 							<td><input name="course_Submit" type="submit" value="ADD" style="width: 120px;"/></td>
		 						<td><input name="course_Submit" type="submit" value="UPDATE" style="width: 120px;"/></td>
		 							<td><input name="course_Submit" type="submit" value="DELETE" style="width: 120px;"/></td>
		 						
		 						 	</tr>
		 						</table>
		 						<br>
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

</html>
