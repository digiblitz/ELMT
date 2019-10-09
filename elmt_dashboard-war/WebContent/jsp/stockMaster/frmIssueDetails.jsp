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

					<form id="frmMatIssue" name="frmMatIssue" method="post" action="./stock.do" >
					<input type="hidden" name="stockMaintProcess" id="stockMaintProcess" value="matIssueProcess">
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
					<div><h2 align="center" style="color: black; align:centre">Material Issue Maintenance</h2></div>
						
					<div>
		 							
		 					<div style="border-bottom-width: thin; border-top-style: solid; border-right-style: solid; border-bottom-color: #000000; border-top-width: thin; border-bottom-style: solid; border-left-color: #000000; border-top-color: #000000; border-left-style: solid; border-right-color: #000000; border-right-width: thin; border-left-width: thin; background-color: #E6E6E6">
		 						
		 						
		 						<div style="color:red" align="center">* Fields are required</div>
		 						<table align="center" width="760">
		 						
		 							<tr><td height="20" ></td></tr>
		 							<tr>
		 								<td width=1 style="color:red" align="right">*</td>
		 								
		 								
		 								<td width="100" align="left"  class="textBold">Requested date</td>
		 								<td width="300">
		 								<input name="requestedDate" id="requestedDate" type="text" class="textboxOne" style="width: 250px;" value="<%=nullCheck((String)request.getAttribute("requestedDate"))%>" >
		 								 <a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
		 								</td>
		 							<td width="50"  align="left"  class="textBold"></td>
	 								  <td width="200"><input name="matIssue_submit" type="submit" value="Search" style="width: 150px;" onclick="return(onValidate());" /> </td>
			 						
		 							<tr><td height="20"></td></tr>
		 						</table>
		 						
		 						
		 						<table id="tbl_request" align="center" width=700 style=" border-bottom-width: 1px;  border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px; background-color: #FFFFFF">
				          
										          <tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
										             <td width=50 class="textBold" align="center">	Sl.No</td>
										               <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Centre Code</td>
										              <td width=150 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Centre Name</td>
										             
										              <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Program Code</td>
										              <td width=150 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Program Name</td>
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Medium</td>
										        
										              <td width=80 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Year Of Study</td>
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Available Quantity</td>
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Requested Quantity</td>
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Issue Quantity</td>
										              
										              <td width=70 class="textBold" style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px" align="center">Select For Issue</td>
										             
										          </tr>
										          
										          <%
										               int mat_count = 0;
										          	   String centre_code = null;
													   String centre_name = null; 
													   String program_code = null;
													   String program_name = null;  
													   String year_of_study = null;
													   String medium = null;
													   String quantity = null;
													   String requested_date= null;
													   String available_Stock = null;

													ArrayList requestList = (ArrayList)request.getAttribute("requestList");
													if(requestList!=null && requestList.size()!=0)
													{
														Iterator itReqList = requestList.iterator();
														int SI_No = 1;
														
														while(itReqList.hasNext())
														{
															mat_count++;
															String[] reqList = (String [])itReqList.next();											
														
																						
												          	 centre_code = reqList[0];	
												        	  centre_name = reqList[1];	
												        	  program_code = reqList[2];	
												        	  program_name = reqList[3];	  
												        	  year_of_study = reqList[4];	
												        	  medium = reqList[5];	
												        	  quantity = reqList[6];	
												        	  requested_date= reqList[7];	
												           	 available_Stock = reqList[8];
												           	
												           	  
												           	  String centreCode = "centreCode_" + mat_count;	
															  String centreName = "centreName_"+mat_count;	
															  String progCode = "progCode_"+mat_count;
															  String progName = "progName_"+mat_count;
															  String mediumType = "medium_"+mat_count;
															  
															  String yeas_Of_Study = "year_"+mat_count;
															  String available_Quantity = "aQuantity_"+mat_count;
															  String request_Quantity = "rQuantity_"+mat_count;
															  String Issue_Quantity = "iQuantity_"+mat_count;
															  String chk_Select = "select_"+mat_count;
															 					           
														%>
				           									<tr style="border-bottom-width: 1px; border-bottom-style: solid; border-top-style: solid; border-left-color: #000000; border-top-color: #000000; border-right-color: #000000; border-left-style: solid; border-right-width: 1px; border-right-style: solid; border-left-width: 1px; border-bottom-color: #000000; border-top-width: 1px">
													             <td width=50 style="border-top-style: solid; border-top-color: #000000; border-top-width: thin; border-top-style: solid; border-top-color: #000000;border-top-width: thin" align="left">
													              	<input name="txt_siNo" type="text" value="<%=SI_No%>" style="width:49px; "/>
													              	</td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="<%=centreCode%>" type="text" value="<%=nullCheck(centre_code)%>" style="width:79px; "/>
													              </td>
													            
													              <td width=150 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              	<input name="<%=centreName%>" type="text" value="<%=nullCheck(centre_name)%>" style="width:149px;" />
													              </td>
													             
													              <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													              <input name="<%=progCode%>" type="text" value="<%=nullCheck(program_code)%>" style="width:79px;" /></td>
													              
													              <td width=150 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													             	 <input name="<%=progName%>" type="text" value="<%=nullCheck(program_name)%>" style="width:149px; "/></td>
													            
													              <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="<%=mediumType%>" type="text" value="<%=nullCheck(medium)%>" style="width:69px; "/></td>
													             	
													             	 <td width=80 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; border-top-width: thin" align="left">
													             	 <input name="<%=yeas_Of_Study%>" type="text" value="<%=nullCheck(year_of_study)%>" style="width:79px; "/></td>
													            
													              <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="<%=available_Quantity%>" type="text" value="<%=nullCheck(available_Stock)%>" style="width:69px; "/></td>
													          
													          <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="<%=request_Quantity%>" type="text" value="<%=nullCheck(quantity)%>" style="width:69px; "/></td>
																
																<td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="<%=Issue_Quantity%>" type="text"  style="width:69px; "/></td>
													             	 
													             	 <td width=70 style="border-left-color: #000000;  border-left-style: solid;  border-left-width: 1px; border-top-style: solid; border-top-color: #000000; 
													             	 border-top-width: thin" align="left"><input name="<%=chk_Select%>" type="checkbox" value="<%=quantity%>" style="width:69px; "/></td>													          
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
		 							<td><input name="matIssue_submit" type="submit" value="Issue" style="width: 100px;"/></td>
		 							</tr>
		 						</table>
		 						<br>
		 						<input name="mat_count" id="mat_count" type="hidden" value="<%=mat_count%>"/>
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
	var cal2= new calendar2(document.forms['frmMatIssue'].elements['requestedDate']);
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
	if(document.frmMatIssue.requestedDate.value==""){
		alert("Requested Date cannot be empty");
		document.frmMatIssue.requestedDate.focus();
		return false;
	}
	
	return true;
}


</script>
</html>
