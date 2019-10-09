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
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script type="text/javascript">
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
        if(document.frmHorseMember.horseName.value==""){
			alert("Horse Name Cannot be Empty");
			document.frmHorseMember.horseName.focus();
			return false;
		}
		if(isnotSpecial(document.frmHorseMember.horseName.value)){
			alert("Horse Name is not valid");
			document.frmHorseMember.horseName.focus();
			return false;
		}
		if((document.frmHorseMember.horseName.value.indexOf('  ')!=-1)||(document.frmHorseMember.horseName.value.indexOf(' ')==0)){
			alert("Horse Name is not valid");
			document.frmHorseMember.horseName.focus();
			return false;
		}
		if(document.frmHorseMember.horseName.value.length > 80){
			alert("Horse Name is not valid");
			document.frmHorseMember.horseName.focus();
			return false;
		}



</script>

<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
		<%//@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Horse Member SignUp: <span class="styleBoldTwo"></span></td>
				  </tr>
				  
						  <tr>
							<td>
							
							<form name="frmHorseMember" id="frmHorseMember" method="post" action="SearchHorse.do" onsubmit="return myvalidate();">		
							<input type="hidden" name="searchProcess" value="searchResult">  
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								  <tr>
									<td colspan="2" class="tblRowHead">&nbsp;Horse Member  Detail </td>
								  </tr>
								 
								   <tr>
											<td colspan="2" class="tableSpan">&nbsp;Fields marked with an asterisk (<span class="asterisk">*</span>)  are required.</td>
									  </tr>
								  <tr>
									<td class="tableLeft">Horse Name :</td>
									<td class="tableRight"> <input type="text" name="horseName" id="horseName" class="textboxOne"  size="20" onblur="HLCMemberDetails();" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  <input type="hidden" name="process" value="sign" />
					
								  <!--<tr class="tableInner">
									<td class="tableLeft">Registered Name:</td>
									<td class="tableRight"><input type="text" name="regName" id="regName" class="textboxOne" size="20" /></td>
								  </tr>
								 
								 <tr class="tableInner">
									<td class="tableLeft">Breed Assoc. Horse is registered with :</td>
									<td class="tableRight"><input type="text" name="baRegName" id="baRegName" class="textboxOne" size="20" /></td>
								  </tr>
								 <tr class="tableInner">
				
									<td class="tableLeft">Past Name :</td>
									<td class="tableRight"><input type="text" name="baPastName" id="baPastName" class="textboxOne" size="20" /></td>
								  </tr>-->
								  
								  	 
								<tr>
									<td colspan="2" class="alignCenter">
									<input type="submit" value="Continue" class="gradBtn" />
									</td>
							   </tr>				 
								<tr>
									<td colspan="2" height="25" class="alignCenter">&nbsp;</td>
							   </tr>
							</table>
						</form>					</td>
				  </tr>
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
