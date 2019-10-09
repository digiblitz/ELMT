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
<%@ page import="java.text.*" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
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
  <%!SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
					String dateFormat(String fieldName){					
						Date clDate = null;
						Calendar cal = Calendar.getInstance();
						GregorianCalendar gc = new GregorianCalendar();
						try{
							clDate = sd.parse(fieldName);
						}catch(Exception e){
							System.out.println("Error While Parsing Date: "+e);
						}
						
						gc.setTime(clDate);
						cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						String dispDate = "N/A";
						if(clDate!=null ){
						dispDate = sd1.format(cal.getTime());
						}
						return dispDate;
					}
  %>
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
				
				
					<table width="545"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="5" class="tblMainHead">
						Membership: <span class="styleBoldTwo">Horse Registration Listing </span></td>
					  </tr>
					  <tr>
						<td colspan="5" class="tblDescrp">
							The Horses registered with are listed as follows. <br />
							<br />
							To Upgrade a horse's account, click on the <strong>'Upgrade'</strong> button beside it.</td>
					  </tr>
					 
					 <tr>
						<td>
							 <table width="523" border="0" align="center" cellpadding="3" cellspacing="1" class="formLayout">
							  <!--
							  <tr>
								<td colspan="6">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									  <tr>
										<th height="20" width="125" valign="top" class="alignRight" style="border-bottom:1px solid #999;">&nbsp;Color Legends:&nbsp; </th>
										<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
											<span class="legendOne">&nbsp;</span> &nbsp;Renew					</td>
										<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
											<span class="legendTwo">&nbsp;</span> &nbsp;Upgrade/Downgrade					</td>
									  </tr>
									</table>			</td>
							  </tr>
							  
							  <tr>
								<td height="25" colspan="6"  bgcolor="#ffffff" class="alignLeft">&nbsp;&nbsp;<strong>Status:</strong> 
								  <select name="select" class="selectboxOne">
								    <option>Select One</option>
								    <option>Pending</option>
								    <option>Approved</option>
							      </select>
							    </td>
							   </tr>
							   -->
							  <tr>
								<td width="176" height="27" class="tblRowHeadTwo">Horse Name  </td>
								<td width="82" class="tblRowHeadTwo">Rider ID </td>
								<td width="129" class="tblRowHeadTwo">Date Of Registration </td>
								<td width="84" class="tblRowHeadTwo">Upgrade </td>
								<!--
								<td width="112" class="tblRowHeadTwo">DownGrade</td>
								<td width="91" class="tblRowHeadTwo">Renew</td>
								-->
							   </tr>
								<%
											   Vector HorseList=new Vector();
											   HorseList=(Vector)request.getAttribute("HorseList");
					
											  if(HorseList!=null && HorseList.size()!=0){  
											   Enumeration en=HorseList.elements();
												
												while(en.hasMoreElements())
													{
														String[] horseDetail=(String[])en.nextElement();
										String dat=horseDetail[4];
														String[] regdate= dat.split(" ");
											   %>
					
							 
									   <form name="frHorseMemberList" id="myform" method="post" action="RenewHorseReg.do">
									   <tr>
								<td height="26" bgcolor="#E3E1D2" class="alignCenter"><a href="./RenewHorseReg.do?process=desc&memid=<%=horseDetail[1]%>" class="linkFive"><%=horseDetail[2]%></a> </td>
								<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=horseDetail[1]%></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=dateFormat(regdate[0])%></td>
								<input type="hidden" name="memid" value="<%=horseDetail[1]%>" />
								<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="click" value="Upgrade" class="oneBtn" /></td>
								<!--
								<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="click" value="Downgrade" class="oneBtn" /></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="click" value="Renew" class="twoBtn" /></td>
								-->
								</tr>
										</form>
							   
							  <%}}else{%>
							  <tr>
								<td height="25" colspan="6" class="alignCenter"><strong>No Records Found!</strong></td>
							   </tr>
							<%}%>
							  <tr>
								<td height="19" colspan="6">&nbsp;</td>
							   </tr>
						  </table>
						  
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
