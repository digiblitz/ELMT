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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script language="javascript">



function popitup(url,sName,sStatus,empId,empCnt,uName,roleName,chkStatus) {
     if(chkStatus=='true')
	{

		newwindow=window.open(url,'name','height=450,width=800,scrollbars=no,resizable=no,top=300,left=500,toolbar=no,menubar=no,dialog=yes');
                        if (window.focus) {newwindow.focus()}
                        return false;

					
			
	}

	else
	{
			 alert(" All the Supervisor(s) should approve the user '"+ empId +"'. Hence you cannot activate this user.");
		   return;
	}
}
function assignBtn(uId,roleId,fname,lname,empId,uName,deptName,roleName){

strURL = "roles.do?roleProcess=dispRoleToUser&fname="+fname+"&lname="+lname+"&empId="+empId+"&uName="+uName+"&deptName="+deptName+"&roleName="+roleName+"&userId="+uId+"&roleId="+roleId;
	window.location.href = strURL;
}
function chgStatus(uId,uEmail,sEmail,uLogin,uRole,roleId,sName,sStatus,empId,empCnt)

{
	
	var count=0;
	
	 var chkEmpStat = document.frmSearchEmpList.chkStatus.length;
	
for(var i=0; i<chkEmpStat;i++)
	{
   
	 if(empId==document.frmSearchEmpList.chkStatus[i].value)
		{
		 
		 if(document.frmSearchEmpList.mapStatus[i].value=='false')
			{
			 
		 alert("All the Supervisor(s) should have approved the user '"+ empId +"'.\n Hence you cannot activate the user.");
		   return;
			}
			if(document.frmSearchEmpList.mapStatus[i].value=='true')
			{
				  
		           /*if(confirm("Do you want to Assign '"+ uRole +"' role to the '"+ uLogin +"' ?"))
		                {
	                   strURL = "roles.do?roleProcess=chgUserStat&uId="+uId+"&uEmail="+uEmail+"&sEmail="+sEmail+"&uRole="+uRole+"&uLogim="+uLogin+"&roleId="+roleId;
                      	//strURL = "roles.do?roleProcess=initUserRoleEnt&uId="+uId+"&uName="+uName+"&depId="+depId+"&deptName="+deptName+"&roleId="+roleId+"&roleName="+roleName;
	                   window.location.href = strURL;
		                }
		         else
	                   {
			           return;
	                    }*/

						count++;
			}
			if(count==empCnt)
			{
				if(confirm("Do you want to Assign '"+ uRole +"' role to the '"+ uLogin +"' ?"))
		                {
	                   strURL = "roles.do?roleProcess=chgUserStat&uId="+uId+"&uEmail="+uEmail+"&sEmail="+sEmail+"&uRole="+uRole+"&uLogin="+uLogin+"&roleId="+roleId;
                      	//strURL = "roles.do?roleProcess=initUserRoleEnt&uId="+uId+"&uName="+uName+"&depId="+depId+"&deptName="+deptName+"&roleId="+roleId+"&roleName="+roleName;
	                   window.location.href = strURL;
		                }
		         else
	                   {
			           return;
	                    }
			}
	     }
     
		
   }
}


function clearText(obj)
{
	 obj.fname.value="";
	 obj.lname.value="";
     obj.empId.value="";
     obj.supName.value="";

}


</script>
</head>
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
		
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" cellspacing="0" align="center" class="tblInnerContainer">
  <tr>
    <td colspan="4" class="tblMainHead">
	 User(s): <span class="styleBoldTwo">Search List </span></td>
  </tr>
  <tr>
				<td colspan="2" class="tblDescrp" align="justify"><font size="-1"><strong>
				Please choose the search criteria in order to assign role to user/login into the user's account</strong></font></td>
			  </tr>
				  
	 <%
	 String roleName = (String)session.getAttribute("roleName");
	 String fName = (String)request.getAttribute("fName");
	 String lName = (String)request.getAttribute("lName");
	 String empId = (String)request.getAttribute("empId");
	 String supname = (String)request.getAttribute("supName");
	 
	 String chgStat=(String)request.getAttribute("chgStat");
	 String moduleStat=(String)request.getAttribute("moduleStat");

	 if(fName==null || fName=="")fName="";
	 if(lName==null || lName=="")lName="";
	 if(empId==null || empId=="")empId="";
	 if(chgStat==null || chgStat=="")chgStat="";
	 if(supname==null || supname=="")supname="";
	 if(moduleStat==null || moduleStat=="")moduleStat="";
	 
	 
	 %>
	
		<form name="frmSearchEmp" action="SearchList.do"  method="post" onsubmit="return myValidate();">	
	
<input type="hidden" name="searchProcess" value="searchEmp">
		<!-- SearchList.do?searchProcess=searchEmp-->
			
	  						  
 	
	 <tbody id="showSearchCrite"> 
  <tr>
		<td colspan="4">
		
			
 <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" class="formLayout">
  
 		
			  <tbody id="memShow" class="tableInner">
				
				  
				  
				  
				  
				 </tbody> 
				  <tbody id="commShow"> 	  
				  <tr class="tableInner">
					<td class="tableLeft">First Name:</td>
					<td class="tableRight">
					<input type="text" name="fname" id="fname" class="textboxOne" size="20"  value="<%=fName%>"/></td>
				  </tr>
				 
				 <tr class="tableInner">
					<td class="tableLeft">Last Name:</td>
					<td class="tableRight">
					<input type="text" name="lname" id="lname" class="textboxOne" size="20" value="<%=lName%>"/></td>
				    </tr>
								
				    <tr class="tableInner">
					<td class="tableLeft">Employee Id:</td>
					<td class="tableRight">
					<input type="text" name="empId" id="empId" class="textboxOne" size="20" value="<%=empId%>"/>
						</td>
				  </tr>
				  <%//if(roleName.equalsIgnoreCase("Admin")){%>
       <%--<tr class="tableInner">
     <td class="tableLeft">Supervisior Name:</td>
     <td class="tableRight">
     <input type="text" name="supName" id="supName" class="textboxOne" size="20" value="<%=supname%>"/>
      </td>
      </tr>--%>
      <%//} %>
				  					 
				  </tbody>
				 
				  
					<tr>
						<td colspan="5" class="alignCenter">
						<input align="middle" type="submit" value="Search" class="gradBtn" />

						

						<input align="middle" type="button" value="Clear" class="gradBtn" onclick="clearText(this.form);" /></td>
				   </tr>
				   </table>
				  
				 		 
			  
					</td>
	</tr>
	 </tbody>	
	   </form>

	    
	
<form name="frmSearchEmpList" id="frmSearchEmpList">

 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
 
<tr>
<td colspan="9">
  <%if(moduleStat!="" && roleName.equalsIgnoreCase("Admin")){
		 
		 %>
		 <font color="#FF0000" style="font-size:15px">Note: EMail has been successfully sent to the <%=request.getParameter("uEmail")%> (user) and <%=request.getAttribute("supEmailList")%> (supervisor(s)) with role details</font>
	 <%}%>
</td>
</tr>
      <tr>
			
			<td width="120" class="tblRowHeadTwo">Empl Id</td>
			<td width="120" class="tblRowHeadTwo">Login Name</td>
			<td width="120" class="tblRowHeadTwo">Emp Name</td>
			<td width="350" class="tblRowHeadTwo">Department</td>
			<td width="150" class="tblRowHeadTwo">Email Id</td>
			<td width="350" class="tblRowHeadTwo">Role</td>
			
			<td width="150" class="tblRowHeadTwo">Supervisior Name</td>
			<td width="150" class="tblRowHeadTwo">Supervisior Email</td>
		
			<td width="150" class="tblRowHeadTwo">Assign Role</td>
			
		
			
		   </tr>
		   <%
		   ArrayList empSearchList = (ArrayList)request.getAttribute("empSearchList");
		 
		   String tempId="";
		   String actEnpId="";
		   int count=1;
		    int count1=0;
           									 if(empSearchList!=null && empSearchList.size()!=0){
												 int arraySize=empSearchList.size();
												 int chkSuper=0;
           							Iterator itEmpSear = empSearchList.iterator();
           							while(itEmpSear.hasNext()){

           								String[] empSchList = (String[])itEmpSear.next();
           								String emplId = empSchList[0];
           								String fname = empSchList[1];
           								String lname = empSchList[2];
           								String deptName = empSchList[3];
           								String emailId = empSchList[4];
           								String userId = empSchList[5];
										String status = empSchList[6];
										String rolName = empSchList[7];
										String supEmail = empSchList[8];
										String supName = empSchList[9];
										String mapStatus = empSchList[10];
										String userName = empSchList[11];
										String depId = empSchList[12];
										String roleId = empSchList[13];
										String rank = empSchList[14];
										String empCnt = empSchList[15];
										//int chkEmpCnt = Integer.parseInt(empCnt);
           								String empName =fname+" "+lname;
											boolean entIdStatus = false;
           								if(rolName==null || rolName=="")rolName="";
											ArrayList activeEmpIdList = (ArrayList)request.getAttribute("activeEmpIdList");
											if(activeEmpIdList!=null && activeEmpIdList.size()!=0){
												Iterator itEmpl = activeEmpIdList.iterator();
												while(itEmpl.hasNext()){
													String[] mapEmpId = (String [])itEmpl.next();
													//{mapEntityId, roleIdVal, entityId, roleName, entityName};
													 actEnpId = mapEmpId[0];
													 if(tempId=="")tempId=emplId;

													if(emplId.equals(actEnpId)){
															System.out.println("If123 "+emplId+"=="+tempId+"=="+count1);
														entIdStatus = true;
														if(emplId.equals(tempId)){
															count1++;
															if(count1>=1 )
																count1=0;
															//System.out.println("If "+emplId+"=="+tempId+"=="+count1);
														}
														else {
															
															tempId=emplId;
															count1=1;
															//System.out.println("Else "+emplId+"=="+tempId+"=="+count1);
														}
														//count1++;
														break;
													}
												}
											}
										
										//if(tempId=="")tempId=emplId;
										
           								
		   %>
		   
		   <tr>
		   
		   <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=emplId%></td>

		     <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=userName%></td>
			 
		    <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=empName%></td>

			 <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=deptName%></td>
			  <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=emailId%></td>
			  <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=rolName%></td>
			  
			  
			
			<td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=supName%></td>
			<td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=supEmail%></td>
			  
		
			
			
		  <%if(roleName.equalsIgnoreCase("Admin")){
				   
			   if(status.equalsIgnoreCase("true")&& mapStatus.equalsIgnoreCase("true")){%>
			   <td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="Active" class="oneBtn" disabled="disabled"/>
			   <input type="hidden" name="chkStatus" id="chkStatus" value="<%=emplId%>"/>
			   <input type="hidden" name="mapStatus" id="mapStatus" value="<%=mapStatus%>"/>
                                                        </td>
                  <%}else if(status.equalsIgnoreCase("false")&& mapStatus.equalsIgnoreCase("true") && entIdStatus==true && count1 == 1){
						//System.out.println(count+"=="+emplId+"=="+empCnt);			 

					 //if(Integer.parseInt(empCnt)>1 && count == 1)
					//	 count=2;

				     %>
				   <td bgcolor="#E3E1D2" class="alignCenter"  rowspan="<%=empCnt%>"><input type="button"  value="Activate" class="twoBtn" onclick="return popitup('roles.do?roleProcess=confirmProcess&uId=<%=userId%>&uEmail=<%=emailId%>&sEmail=<%=supEmail%>&uRole=<%=rolName%>&uLogin=<%=userName%>&roleId=<%=roleId%>','<%=supName%>','<%=mapStatus%>','<%=emplId%>','<%=empCnt%>','<%=userName%>','<%=rolName%>','<%=entIdStatus%>')" />  
				  
				    <input type="hidden" name="chkStatus" id="chkStatus" value="<%=emplId%>"/>
					<input type="hidden" name="mapStatus" id="mapStatus" value="<%=mapStatus%>"/>                                 
				  <%count++;
					 }else if(status.equalsIgnoreCase("false")&& mapStatus.equalsIgnoreCase("true") && entIdStatus==false){

				     %>
				   <td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="Activate" class="twoBtn" onclick="return popitup('roles.do?roleProcess=confirmProcess&uId=<%=userId%>&uEmail=<%=emailId%>&sEmail=<%=supEmail%>&uRole=<%=rolName%>&uLogin=<%=userName%>&roleId=<%=roleId%>','<%=supName%>','<%=mapStatus%>','<%=emplId%>','<%=empCnt%>','<%=userName%>','<%=rolName%>','<%=entIdStatus%>')" />
				    <input type="hidden" name="chkStatus" id="chkStatus" value="<%=emplId%>"/>
					<input type="hidden" name="mapStatus" id="mapStatus" value="<%=mapStatus%>"/>                                 
				  <%}else if(status.equalsIgnoreCase("false")&& mapStatus.equalsIgnoreCase("false")){%>
				   <td bgcolor="#E3E1D2" class="alignCenter" id="supPen">Supervisor Pending 
				     <input type="hidden" name="chkStatus" id="chkStatus" value="<%=emplId%>"/>
					 <input type="hidden" name="mapStatus" id="mapStatus" value="<%=mapStatus%>"/>
                   </td>
				 
                    <%}
			        else if(status.equalsIgnoreCase("true")&& mapStatus.equalsIgnoreCase("false")){%>                                      
				   <td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="Activate" class="twoBtn" onclick="return popitup('roles.do?roleProcess=confirmProcess&uId=<%=userId%>&uEmail=<%=emailId%>&sEmail=<%=supEmail%>&uRole=<%=rolName%>&uLogin=<%=userName%>&roleId=<%=roleId%>','<%=supName%>','<%=mapStatus%>','<%=emplId%>','<%=empCnt%>','<%=userName%>','<%=rolName%>')" />
				    <input type="hidden" name="chkStatus" id="chkStatus" value="<%=emplId%>"/>
					<input type="hidden" name="mapStatus" id="mapStatus" value="<%=mapStatus%>"/>
                                                        </td>
				  <%}}else if(status.equalsIgnoreCase("false")) {%>	
                  <td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="Assign" class="twoBtn" onclick="assignBtn('<%=userId%>','<%=roleId%>','<%=fname%>','<%=lname%>','<%=emplId%>','<%=userName%>','<%=deptName%>','<%=rolName%>')" />
                                                        </td>
                  <%}else if(status.equalsIgnoreCase("true")&& mapStatus.equalsIgnoreCase("false")) {%>	
                  <td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="Assign" class="twoBtn" onclick="assignBtn('<%=userId%>','<%=roleId%>','<%=fname%>','<%=lname%>','<%=emplId%>','<%=userName%>','<%=deptName%>','<%=rolName%>')" />
                                                        </td>
                  <%}else{%>
                  <td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="Assigned" class="oneBtn" disabled="disabled"></td>
                		<%} %>
                  	  
									
		   </tr>
   			<%
										chkSuper++;
									}
           							}else{ %>	
           							<td height="26" colspan="9" bgcolor="#E3E1D2" class="alignCenter"><strong>No Records were Found !</strong></td>
 
           							<%} %>

		 
 </table>

</form>
			  </table>
				 
		</td>
	</tr>
<!-- Based on id -->

<tr>
 	<td>
		 
	</td>
</tr>  

<!--end of the file-->
</table>
			<!-- CONTENTS END HERE -->		
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp"%>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>

</body>
</html>