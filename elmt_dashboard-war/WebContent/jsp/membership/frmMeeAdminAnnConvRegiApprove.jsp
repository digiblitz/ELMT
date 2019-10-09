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
<!-- <script src="javascripts/frmSearchPattern.js" type="text/javascript" ></script>
--> <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<script type="text/javascript">
function isnotAlpha(str){
	stringCheck="!@#$%^&*()_+|<>?/=-~.,`0123456789;:][{}"+"\\"+"\'"+"\"";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true; 
	}
	else {
		return false;
	}
}
function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'";
	f4=1;
	for(j=0;j<str.length;j++){
		if(stringSpecialCheck.indexOf(str.charAt(j))!=-1){
			f4=0;
		}
	}
	if(f4==0){
		return true;
	}
	else{
		return false;
	}
}
function isnotZipcode(str){
	stringZip="0123456789";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(stringZip.indexOf(str.charAt(j))!=-1){
			fzip=0;
		}
	}
	if(fzip==0){
		return false;
	}
	else{
	return true;
	}	
}

 function srchDisp(){
	
	var selObj = document.getElementById('searchType_id');
	var selIndex = selObj.selectedIndex;
	//var selTxt = selObj.options[selIndex].text;
	//alert(selTxt);
	var selVal = selObj.options[selIndex].value;
	
	if (selVal == 0 ){
		//alert('0');
       document.getElementById('memberSrch').style.display="block";
	   document.getElementById('userSrch').style.display="none";
	   document.getElementById('generalSrch').style.display="none";
    }
	if (selVal == 1 ){
		//alert('1');
       document.getElementById('memberSrch').style.display="none";
	   document.getElementById('userSrch').style.display="block";
	   document.getElementById('generalSrch').style.display="none";
    }
	if (selVal == 2 ){
		//alert('2');
       document.getElementById('memberSrch').style.display="none";
	   document.getElementById('userSrch').style.display="none";
	   document.getElementById('generalSrch').style.display="block";
    }
}
 
function myValidate(){
       if(document.frmUserSignup.memberId.value==""){
			alert("Member Id cant be Empty");
			document.frmUserSignup.memberId.focus();
			return false;
		}
		if(isnotSpecial(document.frmUserSignup.memberId.value)){
			alert("Member Id is not valid");
			document.frmUserSignup.memberId.focus();
			return false;
		}
		if((document.frmUserSignup.memberId.value.indexOf('  ')!=-1)||(document.frmUserSignup.memberId.value.indexOf(' ')==0)){
			alert("Member Id is not valid");
			document.frmUserSignup.memberId.focus();
			return false;
		}
		if(document.frmUserSignup.memberId.value.length > 80){
			alert("Member Id is not valid");
			document.frmUserSignup.memberId.focus();
			return false;
		}
	return true;
	}

  function userValidate(){
       if(document.frmloginName.loginName.value==""){
			alert("Login Name is Empty");
			document.frmloginName.loginName.focus();
			return false;
		}
		if(isnotSpecial(document.frmloginName.loginName.value)){
			alert("Login Name is not valid");
			document.frmloginName.loginName.focus();
			return false;
		}
		if((document.frmloginName.loginName.value.indexOf('  ')!=-1)||(document.frmloginName.loginName.value.indexOf(' ')==0)){
			alert("Login Name is not valid");
			document.frmloginName.loginName.focus();
			return false;
		}
		if(document.frmloginName.loginName.value.length > 80){
			alert("Login Name is not valid");
			document.frmloginName.loginName.focus();
			return false;
		}
		return true;
}	
function memberTypeValidate(){
	if (document.frmMemberType.memberType.selectedIndex == 0 ){
		alert ( "Please Select Membership Category" );
		document.frmMemberType.memberType.focus();
		return false;
		}
	  if(document.frmMemberType.fname.value=="" && document.frmMemberType.lname.value=="" &&
	  	   document.frmMemberType.email.value=="" && document.frmMemberType.zip.value==""){
			alert("Please enter atleast one info along with membership category");
			document.frmMemberType.fname.focus();
			return false;
	  }
 	  if((document.frmMemberType.fname.value.indexOf('  ')!=-1)||(document.frmMemberType.fname.value.indexOf(' ')==0)){
			alert("First Name is not valid");
			document.frmMemberType.fname.focus();
			return false;
		}	
		if(document.frmMemberType.fname.value.length > 80){
			alert("First Name is not valid");
			document.frmMemberType.fname.focus();
			return false;
		}
 		 
		if(isnotAlpha(document.frmMemberType.lname.value)){
			alert("Last Name is not valid");
			document.frmMemberType.lname.focus();
			return false;
		}
		if((document.frmMemberType.lname.value.indexOf('  ')!=-1)||(document.frmMemberType.lname.value.indexOf(' ')==0)){
			alert("Last Name is not valid");
			document.frmMemberType.lname.focus();
			return false;
		}	
		if(document.frmMemberType.lname.value.length > 80){
			alert("Last Name is not valid");
			document.frmMemberType.lname.focus();
			return false;
		}
		 
		if(!(document.frmMemberType.email.value== "")){
			strmail=document.frmMemberType.email.value;
			firstat = strmail.indexOf("@");
			lastat = strmail.lastIndexOf("@");
			strmain=strmail.substring(0,firstat);
			strsub=strmail.substring(firstat,document.frmMemberType.email.value.length);
			if(strmail.length>120){
				alert("Email is out of range");
				document.frmMemberType.email.focus();
				return false;
			}
			if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
				alert("Enter valid Email ");
				document.frmMemberType.email.focus();
				return false;
			}
			if(isnotSpecial(strmain) || isnotSpecial(strsub)){
				alert("Enter valid Email ");
				document.frmMemberType.email.focus();
				return false;
			}
			k=0;
			strlen=strsub.length;
			for(i=0;i<strlen-1;i++){
				if(strsub.charAt(i)=='.'){
					k=k+1;
				}
			}
			if(k>2){
				alert("Enter valid Email ");
				document.frmMemberType.email.focus();
				return false;
			}
			if(firstat==-1 || lastat==-1){
				alert("Enter valid Email" );
				document.frmMemberType.email.focus();
				return false;
			}
			if(Number(strmain)){
				alert("Enter valid Email ");
				document.frmMemberType.email.focus();
				return false;
			}
			if(firstat != lastat ){
				alert("Enter valid Email ");
				document.frmMemberType.email.focus();
				return false;
			}
			firstdot=strmail.indexOf(".",firstat);
			lastdot=strmail.lastIndexOf(".");
			if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
				alert("Enter valid Email ");
				document.frmMemberType.email.focus();
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
	Membership: <span class="styleBoldTwo">Search List</span> 
	</td>
  </tr>
 <!-- <tr>
	<td colspan="2" height="30" class="tblRowHead">
		<select name="searchType" id="searchType_id" class="selectboxSearch"  onchange="srchDisp();">
			<option value="0" selected="selected" >Member Search</option>
			<option value="1" >loginName Search</option>
			<option value="2" >General Search</option>											
		</select>
	</td>
  </tr>-->
  <tr>
	<td colspan="2" height="30" class="tblRowHead">
		<select name="searchType" id="searchType_id" class="selectboxSearch"  onchange="srchDisp();">
			<option value="0" selected="selected" >Member Search</option>
			<option value="1" >loginName Search</option>
			<option value="2" >General Search</option>											
		</select>
	</td>
  </tr>
  <tr>
		<td colspan="5" id="memberSrch">
			<form name="frmUserSignup" id="frmUserSignup" action="SearchList.do" method="post" onsubmit="return myValidate();">	
			<input type="hidden" name="searchProcess" value="memberSearch">	  
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">  
			 <%
			 ArrayList displayMemberDetails =(ArrayList)session.getAttribute("HLCMemberDetails");
			 if(displayMemberDetails!=null && displayMemberDetails.size()!=0){
                    Iterator it = displayMemberDetails.iterator();
                    while(it.hasNext()){
					//HLCAnnualPriceDetailVO objPriceDet = (HLCAnnualPriceDetailVO)it.next();
					//String activityName = objPriceDet.getSpecificationName();
                        String Prefix = (String)it.next();
					 
                    }
			  
			 }
			 
			 
			 %>
				  <tr>
					<td class="tableLeft">Member ID:</td>
					<td class="tableRight"><input type="text" name="memberId" id="memberId" class="textboxOne" size="20" /> 
					  <span class="asterisk">*</span> </td>
				  </tr>
					   
					<tr>
						<td colspan="5" class="alignCenter">
						<input align="middle" type="submit" value="Search" class="gradBtn" onclick="postData();" /></td>
				   </tr>				 
			  </table>
			  </form>
		</td>
	</tr>
	<tr>
		<td colspan="5" id="userSrch">
			<form name="frmloginName" id="frmloginName" method="post" action="SearchList.do" onsubmit="return userValidate();">
			<input type="hidden" name="searchProcess" value="userSearch">			  
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">  
				  
				  <tr>
					<td class="tableLeft">User Login Name:</td>
					<td class="tableRight"><input type="text" name="loginName" id="loginName" class="textboxOne" size="20" />
                      <span class="asterisk">*</span> </td>
				  </tr>
					   
					<tr>
						<td colspan="5" class="alignCenter"><input align="middle" type="submit" value="Search" class="gradBtn" /></td>
				   </tr>				 
			  </table>
			  </form>
		</td>
	</tr>
	<tr>
		<td colspan="5" id="generalSrch">
		<form name="frmMemberType" id="frmMemberType" method="post" action="" onsubmit="return memberTypeValidate();">
		<input type="hidden" name="searchProcess" value="generalSearch">	  	  
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">  
				  <tr>
					<td colspan="2" class="tableSpan"><strong>Please enter atleast one info along with membership category</span>. </strong></td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Membership Category: </td>
					<td class="tableRight"> 
					<select name="memberType" class="selectboxOne" >
						<option value="" selected="selected" >Select One</option>
						<option value="" >Full Member</option>
						<option value="" >Human Member</option>
						<option value="" >Junior Member</option>
						<option value="" >Horse Member</option>											
					</select> 
					<span class="asterisk">*</span> </td>
				  </tr>
				  <input type="hidden" name="process" value="sign" />
	
				  <tr class="tableInner">
					<td class="tableLeft">First Name:</td>
					<td class="tableRight">
					<input type="text" name="fname" id="fname" class="textboxOne" size="20" /></td>
				  </tr>
				 
				 <tr class="tableInner">
					<td class="tableLeft">Last Name:</td>
					<td class="tableRight">
					<input type="text" name="lname" id="lname" class="textboxOne" size="20" /></td>
				    </tr>
								 <tr class="tableInner">
				
									<td class="tableLeft">e-Mail:</td>
									<td class="tableRight">
									<input type="text" name="email" id="email" class="textboxOne" size="20" /></td>
				    </tr>
								  <tr class="tableInner">
									<td class="tableLeft">Zipcode:</td>
									<td class="tableRight">
									<input type="text" name="zip" id="zip" class="textboxOne" size="20" /></td>
								  </tr>
								  	 
								<tr>
									<td colspan="5" class="alignCenter"><input align="middle" type="submit" value="Search" class="gradBtn" /></td>
							   </tr>				 
				  </table>
				  </form>
		</td>
	</tr>
<!-- Based on id -->
<%
//SearchDetailStore searchList = (SearchDetailStore)session.getAttribute("searchList");
	//	if(searchList!=null && searchList.getSize()!=0){

%>
<tr>
 	<td>
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		  <tr>
			<td colspan="5">
			
			
			</td>
		  </tr>
 			
		  <tr>
			<td width="62" height="27" class="tblRowHeadTwo">Login Name </td>
			<td width="62" class="tblRowHeadTwo">Name</td>
			<td width="119" class="tblRowHeadTwo">MemberId </td>
			<td width="127" class="tblRowHeadTwo">Email</td>
			<td width="100" class="tblRowHeadTwo">Login</td>
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
						%>
					<form name="viewHrsServiceList" action="horseServiceMaster.do" method="post" >
					<input type="hidden" name="horseProcess" value="initEdit" />
					<input type="hidden" name="hrsTyId" value="<%=userId%>" />
					
						<tr>
							<td height="26" bgcolor="#E3E1D2" class="alignCenter"><a href="horseServiceMaster.do?horseProcess=initView&hrsTyId=<%=userId%>" ><%=loginName%></a></td>
							<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=firstName%>&nbsp;<%=lastName%></td>
							<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=memberId%></th>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=emailId%></td>
							<td bgcolor="#E3E1D2" class="alignCenter">
								<input type="button" name="Submit5" value="Click Me" class="twoBtn"  />							</td>
						</tr>
					</form>
		   
				<%
						}
				   }
					else{
				   %>
					 
						<td height="26" colspan="5" bgcolor="#E3E1D2" class="alignCenter"><strong>No Records were Found !</strong></td>
 
					</tr>
				   <%
				   }
				   %>
  
		  <tr>
			<td height="19" colspan="5">&nbsp;</td>
           </tr>
	  </table>
	</td>
</tr>  
<%
//}
//else{
//}
%>
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
