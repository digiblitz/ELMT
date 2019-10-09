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
<title>Press Maintenance</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 
 <link href="css/core-ie.css" type="text/css" rel="stylesheet" />  
</head>
<script type="text/javascript"> 
	 function showVal(press_id,  press_name, press_Address, press_City, press_Zip)
     { 
       //alert(roleVal);
	   // alert(rolDes);
	    var pressID = press_id;
	    var pressName = press_name;
	    var pressAddress = press_Address;
	    var pressCity = press_City;
	    var pressZip = press_Zip;
	  
			   
	    document.getElementById('pressID').value = pressID;
	    document.getElementById('pressName').value = pressName;
	    document.getElementById('pressAddress').value = pressAddress;
	    document.getElementById('pressCity').value = pressCity;
	    document.getElementById('pressZip').value = pressZip;
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
			if(document.frmPressMaster.pressName.value==""){
				alert("Press Name cannot be empty");
				document.frmPressMaster.pressName.focus();
				return false;
			}
			
			if(document.frmPressMaster.pressAddress.value==""){
				alert("Press Address cannot be empty");
				document.frmPressMaster.pressAddress.focus();
				return false;
			}
			
					     
			if(document.frmPressMaster.pressCity.value==""){
				alert("Press City cannot be empty");
				document.frmPressMaster.pressCity.focus();
				return false;
			}
			
			if(document.frmPressMaster.pressZip.value==""){
				alert("Zip Code cannot be empty");
				document.frmPressMaster.pressZip.focus();
				return false;
			}
			
			if(document.frmPressMaster.pressZip.value!=""){
			if(isnotInteger(document.frmPressMaster.pressZip.value))
		    { alert("Enter valid Zip Code... Zip Code should be numbers");
		     document.frmPressMaster.pressZip.focus();
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

					<form id="frmPressMaster" name="frmPressMaster" method="post" action="./stock.do"  onsubmit="return(onValidate());">
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="pressProcess">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
						<input type="hidden" name="pressID" id="pressID" value="" />
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
					<div><h2 align="center" style="color: black; align:centre">Press Maintenance</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						
		 						<table align="center" width=600>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter Press Name</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="pressName" id="pressName" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter Address</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="pressAddress" id="pressAddress" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter City Name</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="pressCity" id="pressCity" style="width: 270px; "/>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Zip Code</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="pressZip" id="pressZip" style="width: 270px; "/></td>
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
											ArrayList pressList =(ArrayList)request.getAttribute("pressList");
											
											if(pressList!=null && pressList.size()!=0){
											
											Iterator itr = pressList.iterator();
											 while (itr.hasNext()) {
											 String sr[] = (String[]) itr.next();
											 String press_id = sr[0]; 
											 String press_name = sr[1]; 
											 String press_address = sr[2]; 
											 String press_city = sr[3]; 
											 String press_zip = sr[4]; 
											 %> 
											<tr>
											<td class="tableRight" colspan="5" >
											<a href="#" onclick="showVal('<%=press_id%>','<%=press_name%>','<%=press_address%>','<%=press_city%>','<%=press_zip%>');"><%=press_name%></a></td>		
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
		 							<td><input name="press_Submit" type="submit" value="ADD" style="width: 120px;"/></td>
		 					    	<td><input name="press_Submit" type="submit" value="UPDATE" style="width: 120px;"/></td>
		 							<td><input name="press_Submit" type="submit" value="DELETE" style="width: 120px;"/></td>
		 						 
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
