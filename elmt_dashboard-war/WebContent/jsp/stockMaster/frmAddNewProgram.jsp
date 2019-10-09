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
<title>Program Maintenance</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
 
 <link href="css/core-ie.css" type="text/css" rel="stylesheet" />  
</head>
<script type="text/javascript"> 
	 function showVal(prog_id, prog_code, prog_name,prog_Barcode)
     { 
       //alert(roleVal);
	   // alert(rolDes);
	   var progID = prog_id;
	    var progCode = prog_code;
		var progName = prog_name;
		var progBarcode = prog_Barcode;
		
	    document.getElementById('progID').value = progID;
	    document.getElementById('progCode').value = progCode;
	    document.getElementById('progName').value = progName;
	    document.getElementById('progBarcode').value = progBarcode;
	  
	 
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
		if(document.frmProgramMaster.progCode.value==""){
			alert("Program code cannot be empty");
			document.frmProgramMaster.progCode.focus();
			return false;
		}
		
		if(document.frmProgramMaster.progName.value==""){
			alert("Program name cannot be empty");
			document.frmProgramMaster.progName.focus();
			return false;
		}
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

					<form id="frmProgramMaster" name="frmProgramMaster" method="post" action="./stock.do" onsubmit="return(onValidate());">
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="progProcess">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
					<input type="hidden" name="progID" id="progID" value="" />
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
					<div><h2 align="center" style="color: black; align:centre">Program Maintenance</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						
		 						
		 						<table align="center" width=760>
		 							<tr>
		 								<td width=100 />
		 								<td Width=130 align="left">Enter Program Code</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="progCode" id="progCode" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=130 align="left">Enter Program Name</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="progName" id="progName" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=130 align="left">Scan Barcode</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="progBarcode" id="progBarcode" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 						</table>
		 						<br>
		 						<table width=760 align="center">
		 							<tr>
		 								
		 								<td width=400 align="left"><b>Click and Update / Delete the below program Name </b></td>
		 								<td width=200></td>
		 							</tr>
		 							<%
											ArrayList progList =(ArrayList)request.getAttribute("progList");
											
											if(progList!=null && progList.size()!=0){
											
											Iterator itr = progList.iterator();
											 while (itr.hasNext()) {
											 String sr[] = (String[]) itr.next();
											 String prog_id = sr[0]; 
											 String prog_code = sr[1];  
											 String prog_Name = sr[2]; 
											 String prog_Barcode = sr[3]; 
											   
											
											%> 
											<tr>
											
											<td class="tableRight" colspan="5" >
											<a href="#" onclick="showVal('<%=prog_id%>','<%=prog_code%>','<%=prog_Name%>','<%=prog_Barcode%>');"><%=prog_code%> - <%=prog_Name%></a></td>		
										   	</tr> 
										
											<%
											}
											}else{%>
											
											<tr>
											<td colspan="2" align="center">
											No records found
											</td>
											
											</tr>
										<%}%>
		 						</table>
		 						<br>
		 						<table align="center">
		 							<tr>
		 							<td><input name="prog_Submit" type="submit" value="ADD" style="width: 120px;"/></td>
		 						    <td><input name="prog_Submit" type="submit" value="UPDATE" style="width: 120px;"/></td>
		 							<td><input name="prog_Submit" type="submit" value="DELETE" style="width: 120px;"/></td>
		 						
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
