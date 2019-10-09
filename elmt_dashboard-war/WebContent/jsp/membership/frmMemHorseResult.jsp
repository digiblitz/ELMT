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
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlccommon.util.HLCHorseSearchVO" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
 <script type="text/javascript" >
var radioFlag=false; 
function validateUser(){
	if(document.frmUserSignup.status.value=="exist"){
		chosen="";
		len2 = document.frmUserSignup.quest_rad.length;
		//alert("len2");
		for(j=0;j<len2;j++){
			if(document.frmUserSignup.quest_rad[j].checked){
				chosen= document.frmUserSignup.quest_rad[j].value; 
				if(chosen=='yes'){
					if(!radioFlag){
						alert("Select any of the Horse Detail");
						radioFlag=false;
						 return false;
					}
				}
			}
		}
	}
	return true;
}

function radioHorse(){
 radioFlag=true;
 }
  
function radioClear(){
	len1 = document.frmUserSignup.info_rad.length ;
	document.frmUserSignup.info_rad.checked=false
	for(i=0;i<len1;i++){
		document.frmUserSignup.info_rad[i].checked=false;
	}
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
		<%@ include file = "../../include/infobar.jsp" %>
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
						<td colspan="2" class="tblMainHead"><strong></strong> Horse Registration: <span class="styleBoldTwo">Account Matching Result</span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp">&nbsp;
							
							</td>
					  </tr>
							  <tr>
								<td>
								
								<form name="frmUserSignup" id="frmUserSignup" action="SearchHorse.do" method="post"  onsubmit="return validateUser();">		  
								<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
									  <tr>
										<td colspan="2" class="tblRowHead">&nbsp;Matching Results:</td>
									  </tr>
									  <%
									  
							 String horseName =(String)request.getAttribute("horseName");
                             String regName = (String)request.getAttribute("regName");
                             String baRegName = (String)request.getAttribute("baRegName");
                             String baPastName = (String)request.getAttribute("baPastName"); 
                              
							System.out.print("jsp horseName:" + horseName);
							System.out.print("jsp regName:" + regName);
							System.out.print("jsp baRegName:" +baRegName);
							System.out.print("jsp baPastName:" +baPastName);
					 
						 	  
									  %>
									  <input type="hidden" name="hName" value="<%=horseName%>" />
									  <input type="hidden" name="rName" value="<%=regName%>" />
									  <input type="hidden" name="bAsso" value="<%=baRegName%>" />
									  <input type="hidden" name="pName" value="<%=baPastName%>" />
									  
									  <input type="hidden" name="searchProcess" value="suggestionPage" />
									  
									  
									  <tr>
										<td height="25" colspan="2" class="tableSpan"><span class="styleBoldTwo">Note:</span><span class="styleBoldOne"> PR</span> <strong>- Primary Rider, <span class="styleBoldOne">NG </span>- Not Given</strong></td>
								  	 </tr> 

									 
											<tr>
											  <td colspan="2" height="25" class="alignCenter">
											  
											  
											  <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                                <tr>
                                                  <td width="5%" height="25" class="tblMainHead">&nbsp;</td>
                                                  <td width="17%" height="25" class="tblMainHead">Horse Name </td>
                                                  <td width="11%" height="25" class="tblMainHead">PR Name </td>
                                                 
                                                  <td width="11%" height="25" class="tblMainHead">Gender</td>
                                               
                                                  <td width="11%" height="25" class="tblMainHead">Year Foaled</td>
                                                  <td width="11%" height="25" class="tblMainHead">Sire Name </td>
                                                  <td width="12%" height="25" class="tblMainHead">Dam Name </td>
                                                </tr>
												 
					
					 
												
			<%
					String compName = "";
                    String registeredName = "";
                    String riderMemId = "";
                    String firstName = "";
                    String lastName = "";
                    String gender = "";
                    String breedName = "";
                    String yearFoaled = "";
                    String sireName = "";
                    String damName = "";
					String horseMemberId="";
					Hashtable al = (Hashtable)request.getAttribute("DisplayResultDetails");
					if(al!=null && al.size()!=0){
					
					
					
					Enumeration el = al.keys();
                    while (el.hasMoreElements()) {
					  String key = (String) el.nextElement();
					  HLCHorseSearchVO objSearchHorse =(HLCHorseSearchVO)al.get(key);
					  compName = objSearchHorse.getCompetitionName();
                      registeredName = objSearchHorse.getRegisteredName();
                      riderMemId = objSearchHorse.getRiderMemberId();
                      firstName = objSearchHorse.getFirstName();
                      lastName = objSearchHorse.getLastName();
                      gender = objSearchHorse.getGender();
					  horseMemberId = objSearchHorse.getHorseMemberId();
					/*  if(objSearchHorse.getBreed()!=null) {
                     	 breedName = objSearchHorse.getBreed();
					  }
					  else{
					  breedName="NG";
					  }*/
					   if(objSearchHorse.getYearFoaled()!=null) {
                     	 yearFoaled = objSearchHorse.getYearFoaled();
					  }
					  else{
					   yearFoaled = "NG";
					  }
					   if(objSearchHorse.getSire()!=null) {
                     	sireName = objSearchHorse.getSire();
					  }
					  else{
					   sireName = "NG";
					  }
					   if(objSearchHorse.getDam()!=null) {
                     	  damName = objSearchHorse.getDam();
					  }
					  else{
					    damName = "NG";
					  }
					%>
                                                <tr>
                                                  <td height="25" class="listCellBg">
												  <input type="radio" name="info_rad" value="ok"  onclick="radioHorse();"/></td>
                                                  <td height="25" class="listCellBg"><a href="./RegHorseListing.do?process=userViewDet&memid=<%=horseMemberId%>"><%=compName%></a> </td>
                                                  <td height="25" class="listCellBg"><%=firstName%> <%=lastName%></td>
                                                 
                                                  <td height="25" class="listCellBg"><%=gender%></td>
                                                  
                                                  <td height="25" class="listCellBg"><%=yearFoaled%></td>
                                                  <td height="25" class="listCellBg"><%=sireName%></td>
                                                  <td height="25" class="listCellBg"><%=damName%></td>
                                                </tr>
												<%
												}
                 								}
								           else{
												%>
											<tr>
											<th colspan="9" height="22" class="alignCenter">No records are available</th>
											</tr> 
												
												<%
												}
												%>
                                              </table>											  </td>
								  </tr>
											
									
										<%
										if(al!=null && al.size()!=0){
										%>
									 
													<tr>
														<td colspan="7" class="tableSpan"> 
														<strong>Similar account(s) already exists. They are as such:</strong> <br/>
														Select the result that matches your profile to proceed with the sign up process.
														</td>
													</tr>
											 
										<tr>
										<td class="tableLeft">Are you sure the selection matches your horse? </td>
										<td class="tableRight">
										<input type="radio" name="quest_rad" value="yes" />
										Yes, this is my horse.<br />
										<input type="radio" name="quest_rad" value="no" checked="checked" onclick="radioClear();" />
										No, its not.
										</td>
								  </tr>
								  <input type="hidden" name="status" value="exist">
										<%}else{%>
										 <input type="hidden" name="status" value="notexist">
										<%}%>
									<tr>
										<td colspan="2" class="alignCenter" id="secretQuest">
										   
										   <table width="100%" border="0" cellpadding="0" align="center" cellspacing="0" >
											</table>										</td>
								   </tr>
									<tr>
										<td colspan="2" class="alignCenter"><input type="Submit" value="Continue" class="gradBtn" />&nbsp;&nbsp;<input type="button" value="Back to Search" class="gradBtn"  onclick="javascript:location.href('SearchHorse.do?searchProcess=initView');"/></td>
								   </tr>
								</table>
							</form>
						</td>
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
