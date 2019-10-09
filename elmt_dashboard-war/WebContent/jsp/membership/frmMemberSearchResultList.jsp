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
<%@ page import="com.hlcform.util.HLCUserSearchResultVO" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript">
function assignBtn(uId){
	strURL = "roles.do?roleProcess=initUserRole&userId="+uId;
	window.location.href = strURL;
}
</script>
<!-- <script src="javascripts/frmSearchPattern.js" type="text/javascript" ></script>
--> <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/search_header.jsp" %>
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
		
		<%
			System.out.print("session.getAttribute(sessionId) in search page :"+session.getAttribute("sessionId")); 		
		%>
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Search Result List</span>	</td>
  </tr>

<tr>
 	<td>
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		  <tr>
			<td colspan="5">			</td>
		  </tr>
 		<%
		String radName=(String)session.getAttribute("radMem");
		
		if(radName!=null && radName.equalsIgnoreCase("members")){
		
		%>
		
			
		  <tr>
			<td width="93" height="27" class="tblRowHeadTwo">Member ID </td>
			<td width="103" class="tblRowHeadTwo">Name</td>
			<td width="83" class="tblRowHeadTwo">State </td>
			<td width="106" class="tblRowHeadTwo">Member Type</td>
			<td width="79" class="tblRowHeadTwo">Status</td>
                         <td width="94" class="tblRowHeadTwo">Role</td>
			<td width="79" colspan="2"class="tblRowHeadTwo">Login</td>
		   </tr>
   				<%
						ArrayList  memberList = (ArrayList) request.getAttribute("memberDetails");
						
						if(memberList!=null && memberList.size()!=0){
							Iterator itUserList = memberList.iterator();
							while(itUserList.hasNext()){
								HLCUserSearchResultVO objUserSearch = (HLCUserSearchResultVO)itUserList.next();
								String firstName =  objUserSearch.getFirstName();
								String lastName = objUserSearch.getLastName();
								String loginName = objUserSearch.getLoginName();
								String Password = objUserSearch.getPassword();
								String memberId = objUserSearch.getMemberId();
								String emailId = objUserSearch.getEmailId();
								String userId = objUserSearch.getUserId();
								String state = objUserSearch.getState();
								String memberType = objUserSearch.getMembershipTypeName();
								String memberStatus = objUserSearch.getStatusName();
								//out.println("Login Name:" + loginName);
								String lastFirstName = firstName+", "+lastName;
							
								if(loginName==null || loginName.trim().length()==0)  loginName = "N/A";
								if(memberId==null || memberId.trim().length()==0)  memberId = "N/A";
								if(memberType==null || memberType.trim().length()==0)  memberType = "N/A";
								else {
								        String memtyp[] = memberType.split(" ");
										memberType = memtyp[0];
								}
								if(memberStatus==null || memberStatus.trim().length()==0)  memberStatus = "N/A";
						%>
					<form name="viewHrsServiceList" action="SearchList.do" method="post" />
					<input type="hidden" name="searchProcess" value="loginProcess" />
					<input type="hidden" name="userId" value="<%=userId%>"/>
					<input type="hidden" name="memberId" value="<%=memberId%>"/>
						<tr>
						<% if (memberId.equals("N/A")) {%>
							<td height="26" bgcolor="#E3E1D2" class="alignCenter"><a href="meeting.do?meeProcess=userDetailsViewLogin&uid=<%=userId%>"><%=memberId%></a></td>	
					    <% } else { %>
							<td height="26" bgcolor="#E3E1D2" class="alignCenter"><a href="MembershipReg.do?process=familyView&status=approve&memberId=<%=memberId%>&userId=<%=userId%>"><%=memberId%> </a></td>	
						<% }%>
							<td height="26" bgcolor="#E3E1D2" class="alignLeft"><%=lastFirstName%></td>
							<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=state%></th>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=memberType%></td>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=memberStatus%></td>
                                                       <td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="Assign" class="oneBtn" onclick="assignBtn('<%=userId%>')" />
                                                        </td>
						  <td bgcolor="#E3E1D2" class="alignCenter">
								<input type="submit" name="Submit5" value="Login" class="twoBtn" />
						</tr>
					</form>
		   
				<%
						}
				   }
					else{
				   %>
					 
						<td height="26" colspan="8" bgcolor="#E3E1D2" class="alignCenter"><strong>No Records were Found !</strong></td>
 
					</tr>
				   <%
				   }
				   %>
				   
		<%}else if(radName!=null && radName.equalsIgnoreCase("nonMembers")){%>	
			
			  <tr>
			
			<td width="103" colspan="2" class="tblRowHeadTwo">Name</td>
			<td width="83" colspan="2" class="tblRowHeadTwo">State </td>
			<td width="94" colspan="2" class="tblRowHeadTwo">Role</td>
			<td width="79" colspan="2" class="tblRowHeadTwo">Login</td>
		   </tr>
   				<%
				
				
						ArrayList  NonMemberList = (ArrayList) request.getAttribute("memberDetails");
						
						if(NonMemberList!=null && NonMemberList.size()!=0){
							Iterator itDUserList = NonMemberList.iterator();
							while(itDUserList.hasNext()){
								HLCUserSearchResultVO objNUserSearch = (HLCUserSearchResultVO)itDUserList.next();
								String firstNameN =  objNUserSearch.getFirstName();
								String lastNameN = objNUserSearch.getLastName();
								String loginNameN = objNUserSearch.getLoginName();
								String PasswordN = objNUserSearch.getPassword();
								String userIdN = objNUserSearch.getUserId();
								String emailIdN = objNUserSearch.getEmailId();
								String stateN = objNUserSearch.getState();
								
								String lastFirstNameN = firstNameN+", "+lastNameN;
						
															
						%>
						
					
						
					<form name="viewHrsServiceList" action="SearchList.do" method="post" />
					<input type="hidden" name="searchProcess" value="loginProcess" />
					<input type="hidden" name="userId" value="<%=userIdN%>"/>
					
						<tr>
							<td height="26" bgcolor="#E3E1D2" class="alignLeft"colspan="2"><a href="meeting.do?meeProcess=userDetailsViewLogin&uid=<%=userIdN%>"><%=lastFirstNameN%></a></td>
							<th height="26" bgcolor="#E3E1D2" class="alignCenter" colspan="2"><%=stateN%></th>
							<td bgcolor="#E3E1D2" class="alignCenter" colspan="2"><input type="button"  value="Assign" class="oneBtn" onclick="assignBtn('<%=userIdN%>')" />
                                                        </td>
						  <td bgcolor="#E3E1D2" class="alignCenter" colspan="2">
								<input type="submit" name="Submit5" value="Login" class="twoBtn" />
						</tr>
					</form>
		   
				<%
						}
				   }
					else{
				   %>
					 
						<td height="26" colspan="8" bgcolor="#E3E1D2" class="alignCenter"><strong>No Records were Found !</strong></td>
 
					</tr>
				   <%
				   }
				   %>
				   			   
		<%}%>		   
		  <tr>
			<td height="19" colspan="8" align="center">
			<input align="middle" type="submit" value="Search Again" class="gradBtn" onclick="javascript:location.href='SearchList.do?searchProcess=initViewDet'" />
		<input align="alignLeft" type="submit" value="Add New User" class="gradBtn" onclick="window.open('EditUserReg.do?status=searchSignup','mywindow',
			'width=1100,height=700,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')" /></td>
           </tr>
	  </table>	</td>
</tr>  

<!--end of the file-->
</table>
			<!-- CONTENTS END HERE -->		
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
