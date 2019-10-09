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
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import ="com.hlccommon.util.HLCTaskVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        
        <title><%=(String)session.getAttribute("title")%></title>
    </head>
    
    <body>
        
        <%!
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sd1 = new SimpleDateFormat("MM/dd/yyyy");

    String dateFormat(String fieldName) {
        java.util.Date clDate = null;
        Calendar cal = Calendar.getInstance();
        GregorianCalendar gc = new GregorianCalendar();
        try {
            clDate = sd.parse(fieldName);
        } catch (Exception e) {
            System.out.println("Error While Parsing Date: " + e);
        }

        gc.setTime(clDate);
        cal.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        String dispDate = "N/A";
        if (clDate != null) {
            dispDate = sd1.format(cal.getTime());
        }
        return dispDate;
    }
        %>
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
                                <%
            // If page is displayed for the first time, show only the select box having list of diff. status                    
            if (request.getAttribute("display") != null && request.getAttribute("display").equals("statusFilterOnly")) {
                                    %>
                                <table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <tr>
                                        <td colspan="2" class="tblMainHead"><strong>Task List</strong></td>
                                    </tr>
                                    
                                    <tr>
                                        <td colspan="2" class="tblDescrp">
                                            Select the Status of the tasks you want to see. <br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            
                                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                                                <tr>
                                                    <td class="tblRowHead">
                                                        <form name="frmStatusFilter" action="listTasksAssgnToMe.do">
                                                            <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                                <tr>
                                                                    <td width="13%">
                                                                        <select name="taskStatus">
                                                                            <option value="Select">-- Select Status --</option>
                                                                            <option value="Pending">Pending</option>
                                                                            <option value="Current">Current</option>
                                                                            <option value="Completed">Completed</option>
                                                                        </select>
                                                                        <!--<span class="disclaimer">(select All)</span>-->
                                                                    </td>
                                                                    <td width="38%" class="alignLeft">
                                                                        <input type="Submit" value="Ok" class="gradTwoBtn" />
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </table>
                                    </td></tr>
                                </table>
                                
                                <%
            } else {
                List tasks = null;
                tasks = (ArrayList) request.getAttribute("listOfTasks");

                                %>
                                <table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <tr>
                                        <td colspan="2" class="tblMainHead"><strong>Project List</strong></td>
                                    </tr>
                                    
                                    <tr>
                                        <td colspan="2" class="tblDescrp">
                                            <br />
                                            <span class="msgHead">No. of Tasks (<%= tasks.size() %>)</span>   <!-- - <span class="styleBoldOne">Your Messages </span> --> <br />
                                            <br />
                                            You are viewing the list of Tasks(s) assigned to you.<strong></strong>. <br /><br /> To view details
                                            click on title of task. To edit the task status, Click <strong>'Edit'</strong> corresponding to it in the list.<br />
                                            You can change the list of tasks by selecting the Status from drop down.
                                            <br /><br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            
                                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                                                <tr>
                                                    <td class="tblRowHead">
                                                        
                                                        <form name="frmStatusFilter" action="listTasksAssgnToMe.do">
                                                            <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                                <tr>
                                                                    <td width="13%">
                                                                        <select name="taskStatus" >
                                                                            <option value="Select">-- Select Status --</option>
                                                                            <option value="Pending">Pending</option>
                                                                            <option value="Current">Current</option>
                                                                            <option value="Completed">Completed</option>
                                                                        </select>
                                                                        <!--<span class="disclaimer">(select All)</span>-->
                                                                    </td>
                                                                    <td width="38%" class="alignLeft">
                                                                        <input type="Submit" value="Ok" class="gradTwoBtn" />
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="25" >
                                                        
                                                        <table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
                                                            
                                                            <%
                if (tasks != null && tasks.size() != 0) {
                                                            %>
                                                            <tr class="tblMainHead">
                                                                <td>&nbsp; Task Title
                                                                <td>Project Title
                                                                <td>Priority
                                                                <td>Status
                                                                <td>EDIT
                                                            </tr>
                                                            <%
                                                                Iterator tasksIterator = tasks.iterator();
                                                                while (tasksIterator.hasNext()) {

                                                                    HLCTaskVO taskVO = (HLCTaskVO) tasksIterator.next();
                                                            %>
                                                            
                                                            <tr>
                                                                <td class="listCellBg">&nbsp; <a href="viewTaskDetails.do?taskFilePath=<%=taskVO.getTaskFilePath()%>&taskTitle=<%=taskVO.getTaskTitle()%>&projectTitle=<%= taskVO.getProjectTitle() %>&dueDate=<%=taskVO.getDueDate()%>&taskStatus=<%= taskVO.getTaskStatus() %>&taskPriority=<%= taskVO.getTaskPriority() %>&taskDescription=<%= taskVO.getTaskDescription() %>&selectedProjectStatus=<%=request.getAttribute("selectedProjectStatus")%>"><%= taskVO.getTaskTitle().length() > 25 ? taskVO.getTaskTitle().substring(0, 25) + "..." : taskVO.getTaskTitle() %></a></td>
                                                                <td class="listCellBg"><%=taskVO.getProjectTitle()%></td>
                                                                <td class="listCellBg"> <%=taskVO.getTaskPriority()%> </td>
                                                                <td class="listCellBg"><%=taskVO.getTaskStatus() %></td>
                                                                <td class="listCellBg"><input type="button" class="gradTwoBtn" value="Edit" onclick="location='editTaskStatus.do?taskId=<%=taskVO.getTaskId()%>&taskTitle=<%=taskVO.getTaskTitle()%>&projectTitle=<%= taskVO.getProjectTitle() %>&dueDate=<%=taskVO.getDueDate()%>&taskStatus=<%= taskVO.getTaskStatus() %>&taskPriority=<%= taskVO.getTaskPriority() %>&taskDesc=<%= taskVO.getTaskDescription() %>&selectedTaskStatus=<%=request.getAttribute("selectedTaskStatus")%>'" /></td>
                                                            </tr>
                                                            
                                                            <%

                                                                }
                                                            } else {
                                                            %>
                                                            <tr>
                                                                <td height="25" colspan="6" class="listCellBg">No tasks exists for selected Status</td>
                                                            </tr>
                                                            <%}%>                   
                                                        </table>
                                                        
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tblRowHead">
                                                        
                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                            <tr>
                                                                <td width="13%">&nbsp;</td>
                                                                <td width="38%" class="alignLeft">&nbsp;</td>
                                                            </tr>
                                                        </table>
                                                        
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th height="25" class="alignCenter">&nbsp;</th>
                                                </tr>
                                                <tr>
                                                    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
                                                </tr>
                                            </table>
                                            
                                        </td>
                                    </tr>
                                </table>
                                <% } %>
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
