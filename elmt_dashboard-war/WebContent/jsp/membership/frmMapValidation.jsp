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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMapValidate.js" type="text/javascript" ></script>
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->
		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						 </td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"> </td>
				  </tr>
				  <form name="frmMapLevel" id="frmMapLevel"  action="mapValidate.do" onsubmit="return mapValidate();" >

				  <tr>
					<td>
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="3" class="tblRowHead"> Map a Qualification Details: </td>
						  </tr>
			
			<input type="hidden" name="process" value="insertMapValidate" />
			<tr>
					<td width="203" class="tableLeft">Select a Event Type:</td>
					<td colspan="2" class="tableRight"><span class="alignLeft">
					<select name="eventTypeId" id="eventTypeId" class="selectboxOne" >
						<option selected="selected" value="">Select One</option>
						<%
							Vector allTypesVect=(Vector)request.getAttribute("allTypesVect");
							if(allTypesVect!=null){
							Enumeration itrators= (Enumeration)allTypesVect.elements();
							while(itrators.hasMoreElements()){
								String[] levels = (String[]) itrators.nextElement();                                    
								String typeid=levels[0];
								String name=levels[1];
								String status = levels[2];
								String add_date = levels[3];
								  
						%>
					<option  value="<%=typeid%>"><%=name%></option>
						<%
							}
							}
						 
						%>
					</select>
					</span><span class="asterisk">*</span></td>
					</tr>
			 
						  
						  
							 
							 
						<tr>
					<td class="tableLeft">Select a Event Level:</td>
					<td colspan="2" class="tableRight"><span class="alignLeft">
					<select name="eventLevelId" id="eventLevelId" class="selectboxOne" >
						<option selected="selected" value="">Select One</option>
						<%
									Vector allLevelsVect=(Vector)request.getAttribute("allLevelsVect");
									if(allLevelsVect!=null){
									Enumeration itrators= (Enumeration)allLevelsVect.elements();
									while(itrators.hasMoreElements()){
											String[] levels = (String[]) itrators.nextElement();                                    
											String levelid=levels[0];
											String name=levels[1];
											String code=levels[2];
								  
						%>
					<option  value="<%=levelid%>"><%=code%></option>
						<%
							}
							}
							 
						%>
					</select>
					</span>	<span class="asterisk">*</span>				</td>
					</tr>
						   
													
													<tr>
													  <td class="tableLeftTxtArea">Rider Age: </td>
													  <td colspan="2" class="tableLeftTxtArea"> 
													    <input name="riderAge" type="text" class="textboxOne" id="riderAge" size="15" maxlength="10" />
													 <span class="asterisk">*</span></td>
						  </tr>
													<tr>
													  <td class="tableLeftTxtArea">Horse Age: </td>
													  <td colspan="2" class="tableLeftTxtArea">
													  <input name="horseAge" type="text" class="textboxOne" id="horseAge" size="15" maxlength="10" />
													  <span class="asterisk">*</span></td>
													  
						  </tr>
						  
						  <tr>
													  <td class="tableLeftTxtArea">Horse Rank: </td>
													  <td colspan="2" class="tableLeftTxtArea"> 
													    <input name="horseRank" type="text" class="textboxOne" id="horseRank" size="15" maxlength="10" />
													 </td>
						  </tr>
													<tr>
													  <td class="tableLeftTxtArea">Amateur Status: </td>
													  <td colspan="2" class="tableLeftTxtArea"><input name="amateurStatus" type="radio" value="Yes" id="amateurStatus" size="15" maxlength="10" />Yes
													  <input name="amateurStatus" type="radio" value="No" checked="checked" id="amateurStatus" size="15" maxlength="10" />No
													  </td>
						  </tr>
						  
													<tr> 
													<td class="tblRowHead">Division</td>
													<td width="156" class="tblRowHead">Age From </td>
													<td width="141"  class="tblRowHead">Age To </td>
													</tr>
										  <%
															Vector divisionList=(Vector)request.getAttribute("allDivisionsVect");
															int vectSize = 0;
															if(divisionList!=null && divisionList.size()!=0){ 
															Enumeration itEvent = divisionList.elements();
															  vectSize = divisionList.size();
															int i=1;
															while(itEvent.hasMoreElements()){
																	
																	String [] eventTypeDetail  = (String[]) itEvent.nextElement();
																	String divisionId = eventTypeDetail[0];
																	String divisionName = eventTypeDetail[1];
																	String divisionStatus= eventTypeDetail[2];
																	String divisionDate = eventTypeDetail[3];
																	String val = "";
																	 
														 
																 							
													%>
											 
													 <tr> 
													<td class="tableLeftTxtArea">&nbsp;
															<input type="checkbox" size="10" name="divisionName<%=i%>" id="divisionName<%=i%>"  value="<%=divisionId%>" onclick="clearFileds();" />&nbsp;<%=divisionName%>															</td>
															<td  class="tableLeft"><input name="ageFrom<%=i%>" id="ageFrom<%=i%>" type="text" size="15" class="textboxOne" /><span class="asterisk">*</span></td>
													        <td  class="tableLeft"> 
													          <input name="ageTo<%=i%>" id="ageTo<%=i%>" type="text" class="textboxOne" size="15" /><span class="asterisk">*</span>											           </td>
												    </tr> 
											 <% 
											 i++; 
											 }
											  %>
											
										<% }%>
										 <input type="hidden" name="divisionCount" id="divisionCount" value="<%=vectSize%>" />
							  </table>
							
					</td>
						    </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">
							
							<input type="submit" value="Submit" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);"/>							</td>
						  </tr>
						   </form>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
						</table>					</td>
				  </tr>
				</table>
			<!-- CONTENTS END HERE -->		
			
	
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