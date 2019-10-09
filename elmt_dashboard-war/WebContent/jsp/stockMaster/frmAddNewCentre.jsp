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
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlccommon.util.HLCHorseRegisterationVO"%>
<%@ page import="com.hlchorse.form.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Centre Maintenance</title>
 <script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
    </head>
    <script type="text/javascript"> 
	 function showVal(centre_id,centre_code,  centre_name, centre_Address, centre_City, centre_Zip)
     { 
       //alert(roleVal);
	   // alert(rolDes);
	    var centreID = centre_id;
	    var centreCode = centre_code;
	    var centreName = centre_name;
	    var centreAddress = centre_Address;
	    var centreCity = centre_City;
	    var centreZip = centre_Zip;
	  
			   
	    document.getElementById('centreID').value = centreID;
	    document.getElementById('centreCode').value = centreCode;
	    document.getElementById('centreName').value = centreName;
	    document.getElementById('centreAddress').value = centreAddress;
	    document.getElementById('centreCity').value = centreCity;
	    document.getElementById('centreZip').value = centreZip;
	    
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
			if(document.frmCentreMaster.centreCode.value==""){
				alert("Centre Code cannot be empty");
				document.frmCentreMaster.centreCode.focus();
				return false;
			}
			
		     
			if(document.frmCentreMaster.centreName.value==""){
				alert("Centre Name cannot be empty");
				document.frmCentreMaster.centreName.focus();
				return false;
			}
			
			if(document.frmCentreMaster.centreAddress.value==""){
				alert("Centre Address cannot be empty");
				document.frmCentreMaster.centreAddress.focus();
				return false;
			}
			
			if(document.frmCentreMaster.centreCity.value==""){
				alert("Centre City cannot be empty");
				document.frmCentreMaster.centreCity.focus();
				return false;
			}
			
			if(document.frmCentreMaster.centreZip.value==""){
				alert("Centre Zip cannot be empty");
				document.frmCentreMaster.centreZip.focus();
				return false;
			}
			
			if(document.frmCentreMaster.centreZip.value!=""){
			if(isnotInteger(document.frmCentreMaster.centreZip.value))
		    { alert("Enter valid Zip Code... Zip Code should be numbers");
		     document.frmCentreMaster.centreZip.focus();
		     return false;
		     }
			}
			return true;
		
		}
 </script>
    <body>
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

					<form id="frmCentreMaster" name="frmCentreMaster" method="post" action="./stock.do"  onsubmit="return(onValidate());">
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="centreProcess">
					<input type="hidden" name="pName" id="pName" value="<%=(String)session.getAttribute("pName")%>"/>
						<input type="hidden" name="centreID" id="centreID" value="" />
					  <%
	String status=(String)request.getAttribute("status");
	 if(status==null){
	 %>
	 <%
	 }else if(status!=null){
	 %>
	 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
	   <tr>
        <td width="200" align="center"><strong><font color="#FF0000"><%=status%></font></strong></td>
      </tr>
	 
	 
	 <%
	}
	
	%>
					<div><h2 align="center" style="color: black; align:centre">Centre Maintenance</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						
		 						<table align="center" width=600>
		 						<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter Centre Code</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="centreCode" id="centreCode" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Enter Centre Name</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="centreName" id="centreName" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left"> Centre Address</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="centreAddress" id="centreAddress" style="width: 270px; "/></td>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Centre City Name</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="centreCity" id="centreCity" style="width: 270px; "/>
		 								<td width=100 />
		 							</tr>
		 							<tr>
		 								<td width=100 />
		 								<td Width=120 align="left">Zip Code</td>
		 								<td width=1 style="color:red" align="right">*</td>
		 								<td width=270 align="left"><input type="text" name="centreZip" id="centreZip" style="width: 270px; "/></td>
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
											ArrayList centreList =(ArrayList)request.getAttribute("centreList");
											
											if(centreList!=null && centreList.size()!=0){
											
											Iterator itr = centreList.iterator();
											 while (itr.hasNext()) {
											 String sr[] = (String[]) itr.next();
											 String centre_id = sr[0]; 
											 String centre_code = sr[1]; 
											 String centre_name = sr[2]; 
											 String centre_address = sr[3]; 
											 String centre_city = sr[4]; 
											 String centre_zip = sr[5]; 
											 %> 
											<tr>
											<td class="tableRight" colspan="5" >
											<a href="#" onclick="showVal('<%=centre_id%>','<%=centre_code%>','<%=centre_name%>','<%=centre_address%>','<%=centre_city%>','<%=centre_zip%>');"><%=centre_name%></a></td>		
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
		 							<td><input name="centre_Submit" type="submit" value="ADD" style="width: 120px;"/></td>
		 						 	<td><input name="centre_Submit" type="submit" value="UPDATE" style="width: 120px;"/></td>
		 							<td><input name="centre_Submit" type="submit" value="DELETE" style="width: 120px;"/></td>
		 						
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
