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
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcmrm.util.HLCDonationVO"%>
<%@ page import="com.hlcmrm.util.*"%>
<%@ page import="com.hlccommon.util.*"%>
<%@ page import="com.hlcform.util.*" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript" ></script>

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
		<%
		String  eventId = (String)request.getAttribute("eventId");
		System.out.print("eventId in jsp:::::" + eventId);
		
		
		%>
		<form name="horseCompMap" action="horseRegExcelData.do" method="post" />
		<input type="hidden" name="process" value="insertMapDatas" />
		<input type="hidden" name="eventId" value="<%=eventId%>" />
		
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
				<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad"><table width="500" border="0" cellpadding="0" cellspacing="0">
			<tr>
			<td colspan="2" class="tblMainHead">
							Membership: <span class="styleBoldTwo">Competition Registration Details Mapping.	</span></td>
			  </tr>
			  
			  <tr>
    		<td colspan="5" class="tblDescrp">
				All available column names in excel sheet are listed in the left side.Available column names on the destination table is provided on the right side.<br />
				<br />
				To map a particular excel column to a particular table column select the appropriate available in the dropdown available appropriate straight to the table column, click on the <strong>' Map Data'</strong> button beside to Map selected datas. <br />
			</td>
  		</tr>
		<%
			ArrayList excelColumns = new ArrayList();
			ArrayList excelRow = new ArrayList();
			
			ArrayList tblDetails = new ArrayList();
					
			tblDetails = (ArrayList)request.getAttribute("tblDetails");
			
			System.out.print("tblDetails.size() :"+tblDetails.size());
		
		%>		  
              <tr>
                <td width="250">
				<table width="250" border="0" cellpadding="0" cellspacing="0">
                  <tr>
				  <td height="24" align="center"><div class="rownew"></div></td>
                  </tr>
                  <tr>
                    <td height="24" align="center">
					
					<%		                
						String dropName1="";
						
						if(tblDetails != null)
						{	 
						for(int j=2;j<tblDetails.size()-4;j++)
						{
							dropName1 = "colSel"+String.valueOf(j);
					%>
					
					<div class="rownew">
					<span class="labelmap"><strong>Column #<%=j+1-2%> :</strong></span>
                      <%
							String[] head1 = null;
						
							head1 = (String[])tblDetails.get(j);
							
							%>
					<span class="formXmap"><%=head1[0]%></span>
					  </div>
			<%}}%> 
					  </td>
					  </tr>
					  </table>
                   </td>
                <td width="250">
				<table width="250" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                     <td height="24" align="center"><div class="rownew"></div>
					<input type="hidden" name="dropCt" value="<%=tblDetails.size()-6%>" />
					
					<%
					
					Vector excelRows = new Vector();
					
					excelColumns = (ArrayList)request.getAttribute("excelHead");
					excelRows = (Vector)request.getAttribute("excelRows");
							
							session.setAttribute("excelRows",excelRows);
							
					System.out.print("excelColumns.size() :"+excelColumns.size());
					System.out.print("excelRows.size() :"+excelRows.size());
							
					String dropName="";
					
					if(excelColumns != null)
					{	 
					for(int i=0;i<tblDetails.size()-6;i++)
					{
						dropName = "colSel"+String.valueOf(i);
				%>
				
																	<div class="rownew">
																			<span class="labelmap"><strong>Excel Column #<%=i+1%>:</strong></span>
																			<span class="formXmap">
																			<select name="<%=dropName%>" id="<%=dropName%>" class="selectboxOne">
				<option value="" selected="selected">Select One</option>
				<%
					String head="";
					
					for(int j=0;j<excelColumns.size();j++)
					{
						head = (String)excelColumns.get(j);
					   // System.out.print("head in jsp" + j);	
						%>
						<option value="<%=j%>"><%=head%></option>
				  <%}
				%>
				</select>
																			</span>
																		</div>
														
										  
			<%}}%>		  
					  </td>
                  </tr></table></td>
                <!--</table></td>-->
                  </tr>
				 </table>
				 <br />
				 <br />
			<div class="alignCenter">
			 <input type="submit" class="gradBtn" value="Map Data" />	&nbsp;
			 <input type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" />
			</form> 
			</div>
                </td>
              </tr>
			  
            </table>
			
				<!-- CONTENTS START HERE -->
			 
				<!-- CONTENTS END HERE -->			</td>
		  </tr>

	
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

