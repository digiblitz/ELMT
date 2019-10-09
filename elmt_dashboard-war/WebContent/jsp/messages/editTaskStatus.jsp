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
    Document   : editTaskStatus
    Created on : Dec 29, 2008, 1:00:25 PM
    Author     : Rupinder
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=(String)session.getAttribute("title")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <script src="javascripts/basic.js" type="text/javascript" ></script>
    <script src="javascripts/calendar2.js" type="text/javascript"></script>
    <!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
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
                         <!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
                         <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                         <!-- LEFT MENU ENDS HERE -->
                                
        </td>
        <td width="500" class="subDeptTablePad">
            <!-- CONTENTS START HERE -->
            <table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                <tr>
                    <td colspan="2" class="tblDescrp">
                        <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
                        <span class="msgHead">Edi Task Status</span>
                        <br /><br />
                        Use the section below to edit the Task Status
                        
                    </td>
                    
                    
                </tr>
                <tr>
                <td>
                <form name="createEditTask" method="post" action="editTaskStatus.do">
                <input type="hidden" name="postEditng" value="true">
                <input type="hidden"  name="taskId" value=<%=(String) request.getParameter("taskId") %> /> 
                <input type="hidden"  name="selectedTaskStatus" value=<%=request.getParameter("taskStatus")%> />        
                       <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                
                <tr>
                    <td height="25" >
                        
                        <table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
      
                            <tr>
                                
                                <th height="25" class="tblMainHead"><span class="fonts">Task Status:</span></th>
                                <td colspan="2" height="142" class="listCellBg">
                                <select  name="taskStatus" class="selectboxOne" >
                                    <option  value="Pending" <%
            // In case if request is for of Editing of Task. 
            if (request.getParameter("taskStatus") != null && request.getParameter("taskStatus").equals("Pending")) {
                out.println("selected");
            }%> 
                                             >Pending</option>
                                    <option value="Current" <%
            // In case if request is for of Editing of Task. 
            if (request.getParameter("taskStatus") != null && request.getParameter("taskStatus").equals("Current")) {
                out.println("selected");
            }%> 
                                            >Current</option>
                                    <option value="Completed" <%
            // In case if request is for of Editing of Task. 
            if (request.getParameter("taskStatus") != null && request.getParameter("taskStatus").equals("Completed")) {
                out.println("selected");
            }%>
                                            >Completed</option>
                                </select>
                            </tr>
                            
                            <tr>
                                <td colspan="3" class="tblRowHead">
                                    
                                    <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                        <tr>
                                            <td >
                                                <input type="submit" name="Update" value="Update" class="gradTwoBtn" />
                                                
                                            </td>
                                            
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
