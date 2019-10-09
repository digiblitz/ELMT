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
<%-- 
    Document   : reportTypes
    Created on : Sep 2, 2009, 12:34:58 PM
    Author     : parteek
--%>

<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Business Service Center</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-sidebar.css" />


<link rel="stylesheet" type="text/css" href="css/EAframe.css" />
<link rel="stylesheet" type="text/css" href="css/Usermenu.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />




<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->


<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-sidebar.css" />
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script>
       function UpdateSelect()
{
select_value = document.salesPage.view.value;
var id = 'hide_this_row';
var obj = '';
obj = (document.getElementById) ? document.getElementById(id) : ((document.all) ? document.all[id] : ((document.layers) ? document.layers[id] : false));

if(select_value =2)
{
obj.style.display = ( obj.style.display != "none" ) ? "none" : "";//Hide Fields
}
else
{
obj.visibility = "show";//Show Fields
}
}
 function goSubmit(type)
 {
     if(type=='purchaseRequisitionPeriodWise')
         {
             document.salesPage.action='purchaseRequisitionReport.do?process=purchaseRequisitionPeriodWise';
           } 
       if(type=='purchaseRequisitionParticularStatus')
           {
           document.salesPage.action='purchaseRequisitionReport.do?process=purchaseRequisitionParticularStatus';    
           }
         
           
     }
	 </script>
<script type="text/javascript" src="js/ddlevelsmenu.js">

/***********************************************
* All Levels Navigational Menu- (c) Dynamic Drive DHTML code library (http://www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/


</script>

</head>


<body>

<!-- Code starts for setting width and border of Web Page -->

<!-- Code Ends -->

<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
<%@ include file = "/include/header.jsp" %> 
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
  <td align="right" bgcolor="#ffffff" class="text">&nbsp;
  </td>
 </tr>
  <tr>
    <td bgcolor="#eeeeee" class="bg" height="25">
	 <span class="text8">Purchase Requisitions Report</span></td>
  </tr>
  
  
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			
			<!-- LEFT MENU STARTS HERE -->
			<!-- <%@ include file = "ADV_MappingRoleLeftBar.html" %> -->

			<!-- LEFT MENU ENDS HERE -->		    
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="1" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                  <tr>
                      <td height="61" colspan="2" bgcolor="#e2eaf2" class="tblDescrp"><strong> <%  if (request.getParameter("type").equalsIgnoreCase("purchaseRequisitionPeriodWise")) { %>
                                            <b>Purchase Requisition Status Wise for Particular Period     </b>
                                             <%  } else if(request.getParameter("type").equalsIgnoreCase("purchaseRequisitionParticularStatus")) { %>
                                             <b>Purchase Requisition For a Particular Status </b>
                                             <% } %></strong><br />
                        <br />
                    </td>
                  </tr>
                  <form name="salesPage" method="post" onsubmit="goSubmit('<%=(String)request.getParameter("type") %>')" >
                  <tr>
                    <td><table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                        <% if(request.getParameter("type").equalsIgnoreCase("purchaseRequisitionPeriodWise")) { %>
                        <tr> 
                                                                    <td class="tableLeft">From Date: </td>
                                                                    <td class="tableRight"><input type="text" name="fromDate" id="fromDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
                      </tr>
                                                                <tr> 
                                                                    <td class="tableLeft">To date: </td>
                                                                    <td class="tableRight"><input type="text" name="toDate" id="toDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
                                                                </tr>

																<tr>
                             <td class="tableLeft"><strong>Report type :</strong></td><td class="tableRight"><select name="view" id="view" onChange="UpdateSelect();" >
                            <option value='1'>Graphical</option>
                            <option value='2'>Tabular</option></select></td>
                            
                            
                        </tr>
																
                          <tr>

                                                                <% } %>
                                                                 <% if(request.getParameter("type").equalsIgnoreCase("purchaseRequisitionParticularStatus")) { %>
                                                                  <tr> 
                                                                    <td class="tableLeft">Status: </td>
                                                                    <td class="tableRight"><select name="status" id="status">
                            <option value='approved'>Approved</option>
                            <option value='rejected'>Rejected</option>
                             <option value='pending'>Pending</option>
                            </select><span class="asterisk">*</span></td>
                                                                </tr>
																
																<tr>
                             <td class="tableLeft"><strong>Report type :</strong></td><td class="tableRight"><select name="view" id="view" onChange="UpdateSelect();">
                            <option value='1'>Graphical</option>
                            <option value='2'>Tabular</option></select></td>
                            
                            
                        </tr>
                          <% } %>
                         <tr id="hide_this_row">
                            <td class="tableLeft"><strong>Graph types :</strong></td>
							 <td class="tableRight">
                                 
                                 <input type="radio" name="graph" value="Cone" checked>Cone<br>
<input type="radio" name="graph" value="Pie">Pie<br>
<input type="radio" name="graph" value="PlotFormat">PlotFormat<br> 
<input type="radio" name="graph" value="Line">Line<br> 
<input type="radio" name="graph" value="Pyramid">Pyramid<br>
    <input type="radio" name="graph" value="Tube">Tube<br>
                             </td>
                            
                            
                        </tr>
                          <tr>                                  

                                                                    <td colspan="2" class="tblRowHead">
                                                                        
                                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                                            <tr>
                                                                                <td width="11%"  >
                                                                                   
                                                                                    <input type="submit" name="Submit" value="Submit" class="gradTwoBtn" />
                                                                                          
                                                                                   
                                                                              </td>
                                                                            </tr>
                                                                        </table>                                                                    </td>
                      </tr>
                  </form>
                      <tr>
                          <td colspan="2" class="alignCenter">&nbsp;
                              <!-- DO NOT DELETE THIS ROW --></td>
                      </tr>
              </table></td>
          </tr>
      </table>
				<!-- CONTENTS END HERE --></td>
		  </tr>
</table>
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "/include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
<!-- Code starts for setting width and border of Web Page -->


<!-- Code Ends -->



</body>
</html>
 <% if(request.getParameter("type").equalsIgnoreCase("purchaseRequisitionPeriodWise")) { %>
<script language="javascript">
    var cal1 = new calendar2(document.forms['salesPage'].elements['fromDate']);
    cal1.year_scroll = true;
    cal1.time_comp = false;
    
    var cal2 = new calendar2(document.forms['salesPage'].elements['toDate']);
    cal2.year_scroll = true;
    cal2.time_comp = false;
    </script> <% } %>
