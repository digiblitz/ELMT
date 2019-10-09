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
    Document   : createEditProject
    Created on : Dec 12, 2008, 3:06:51 PM
    Author     : Prateek
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=(String)session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
        <script language="javascript">
            
            function validate(){
                if(document.createProject.projectTitle.value=="" || document.createProject.projectTitle.value.indexOf(' ')==0){
                    alert("Please enter the Project Title");
                    document.createProject.projectTitle.focus();
                    return false;
                }
                
                if(document.createProject.projectDescription.value.length==0 || document.createProject.projectDescription.value.indexOf(' ')==0){
                    alert("Please enter the Project Description");
                    document.createProject.projectDescription.focus();
                    return false;
                }
                
                if(document.createProject.startDate.value==""){
                    alert("Please select the Start Date");
                    document.createProject.startDate.focus();
                    return false;
                }
                
                
                if(document.createProject.endDate.value==""){
                    alert("Please select the End Date");
                    document.createProject.endDate.focus();
                    return false;
                }
                
                var startDate = new Date(document.createProject.startDate.value);
                var endDate = new Date(document.createProject.endDate.value);
                
                if (startDate >= endDate){
                    
                    alert("End date should be greater than Start Date");
                    return false;
                }
                return true;
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
                                        <% if (request.getParameter("projectId") != null) {%>
                                        <td colspan="2" class="tblDescrp">
                                            <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
                                            <span class="msgHead">Edit A Project </span>
                                            <br /><br />
                                            Use the section below to edit a  project
                                            
                                        </td>
                                        <% } else {%>
                                        <td colspan="2" class="tblDescrp">
                                            <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
                                            <span class="msgHead">Create A Project </span>
                                            <br /><br />
                                            Use the section below to create a new project
                                            
                                        </td>
                                        <% }%>
                                        
                                    </tr>
                                    <tr>
                                        <td>
                                            <form   name="createProject" method="post" enctype="multipart/form-data" action="project.do" onsubmit="return validate();">
                                                <% if (request.getParameter("projectId") != null) {%>
                                                <input type="hidden" name="Project" value="EditProject" />
                                                <input type="hidden"  name="projectId" 
                                                       value=<%=(String) request.getParameter("projectId") %> /> <% } else {%>
                                                       <input type="hidden" value="CreateProject" name="Project"/>
                                                <% }%>
                                                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                                                    
                                                    <tr>
                                                        <td height="25" >
                                                            
                                                            <table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
                                                                
                                                                <!--
                                                                                   <tr class="tblMainHead">
                                                                                         <td height="25" colspan="2" class="textCommon">&nbsp;
                                                                                                 <a href="#" class="linkFour">Insert Address</a> |
                                                                                                 <a href="#" class="linkFour">Add Cc</a> | 
                                                                                                 <a href="#" class="linkFour">Add BCc</a>
                                                                                                 
                                                                                         </td>
                                                                                   </tr>
                                                                -->
                                                                <tr>
                                                                    <th width="25%" height="25" class="tblMainHead">Project Title:</th>
                                                                    <td width="81%" class="listCellBg">
                                                                        <input type="text" id="projectTitle" name="projectTitle" value='<%=request.getParameter("projectTitle") != null ? (String) request.getParameter("projectTitle") : "" %>' class="textboxOne"  size="45"/><span class="asterisk">*</span>
                                                                        
                                                                        <div id="statusId"></div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th height="25" class="tblMainHead">Project Description:</th>
                                                                    <td height="25" class="tblMainHead">
                                                                    <textarea  name="projectDescription" id="projectDescription"  rows="5"  cols="28" ><%=request.getParameter("projectDesc") != null ? (String) request.getParameter("projectDesc") : "" %></textarea><span class="asterisk">*</span> </td>
                                                                </tr>
                                                                <tr>
                                                                    <th height="25" class="tblMainHead"><span class="fonts">Project Start Date:</span></th><td height="25" class="listCellBg">
                                                                    <input type="text"  name="startDate"  value='<%=request.getParameter("projectStartDate") != null ? (String) request.getParameter("projectStartDate") : "" %>' class="textboxOne" size="25" readonly="true" /><a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a><span class="asterisk">*</span>
                                                                </tr>
                                                                <tr>
                                                                    <th height="25" class="tblMainHead"><span class="fonts">Project End Date:</span></th>
                                                                    <td  height="25" class="listCellBg"> 
                                                                    <input type="text" name="endDate" value='<%=request.getParameter("projectEndDate") != null ? (String) request.getParameter("projectEndDate") : "" %>' id="endDate" class="textboxOne" size="25" readonly="true" /><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a><span class="asterisk">*</span>
                                                                </tr>
                                                                <tr>
                                                                    <th height="25" class="tblMainHead"><span class="fonts">Project Status:</span></th>
                                                                    <td colspan="2" height="142" class="listCellBg">
                                                                    <select  name="projectStatus" id="projectStatus"  class="selectboxOne" >
                                                                        <option  value="Pending" <%if (request.getParameter("projectStatus") != null && request.getParameter("projectStatus").equals("Pending")) {
                out.println("selected");
            } else {
                out.println("selected");
            }%> >Pending</option>
                                                                        <option value="Current" <%if (request.getParameter("projectStatus") != null && request.getParameter("projectStatus").equals("Current")) {
                out.println("selected");
            }%> >Current</option>
                                                                        <option value="Completed" <%if (request.getParameter("projectStatus") != null && request.getParameter("projectStatus").equals("Completed")) {
                out.println("selected");
            }%> >Completed</option>
                                                                    </select><span class="asterisk">*</span>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td colspan="2" class="tblRowHead">
                                                                        
                                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                                            <tr>
                                                                                <td width="11%"  >
                                                                                    <% if (request.getParameter("projectId") != null) {%>
                                                                                    <input type="submit" name="Update" value="Update" class="gradTwoBtn" />
                                                                                    <% } else {%>
                                                                                    <input type="submit" name="Submit" value="Send" class="gradTwoBtn" />
                                                                                    <% } %>
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
<script language="javascript">
    
    var cal1 = new calendar2(document.forms['createProject'].elements['startDate']);
    cal1.year_scroll = true;
    cal1.time_comp = false;
    
    var cal2 = new calendar2(document.forms['createProject'].elements['endDate']);
    cal2.year_scroll = true;
    cal2.time_comp = false;
    </script>
