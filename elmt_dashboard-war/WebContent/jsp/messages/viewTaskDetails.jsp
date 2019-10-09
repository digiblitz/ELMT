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
    Document   : viewTaskDetails
    Created on : Dec 23, 2008, 1:10:02 PM
    Author     : Prateek
--%>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import ="com.hlccommon.util.HLCProjectVO"%>
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
                             
                                <table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <tr>
                                        <td colspan="2" class="tblMainHead"><strong>Task Details</strong> </td>
                                    </tr>
                                    
                                    <tr>
                                        <td colspan="2" class="tblDescrp">
                                            <br />
                                            <span class="msgHead">Details of Task </span>   <!-- - <span class="styleBoldOne">Your Messages </span> --> <br />
                                            <br />
                                            You are viewing the details of Task.
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            
                                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                                                <tr>
                                                    <td class="tblRowHead">
                                                        
                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                            <tr>
                                                                <td width="13%"><span class="disclaimer"></span></td>
                                                                <td width="38%" class="alignLeft">
                                                                    
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="25" >
                                                        
                                                        <table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
                                                            <tr class="tblMainHead">
                                                                <td width="30%">&nbsp; Task Title</td><td><%=(String)request.getParameter("taskTitle")%></td>
                                                            </tr>
                                                             <tr class="tblMainHead">
                                                                <td width="30%">&nbsp; Project Title</td><td><%=(request.getParameter("projectTitle"))%></td>
                                                            </tr>
                                                            <% if(request.getParameter("firstName")!=null && request.getParameter("lastName")!=null)  { %>
                                                            <tr class="tblMainHead">
                                                                <td width="30%">&nbsp; Assigned To</td><td><%=request.getParameter("firstName")%><%=request.getParameter("lastName")%></td>
                                                            </tr> <%  } %>
                                                            <tr class="tblMainHead">
                                                                <td>&nbsp; Task Description</td><td><%=request.getParameter("taskDescription")%></td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                            
                                                            <tr class="tblMainHead">
                                                                <td>&nbsp; Due Date</td><td><%=request.getParameter("dueDate")%></td>
                                                            </tr>
                                                            <% if(request.getParameter("taskFilePath")!=null && request.getParameter("taskFilePath").length()>0) { %>
                                                           
                                                            <tr class="tblMainHead">
                                                                <td>&nbsp; Attached File</td><td> <a  href="#" onclick="location='downLoad.do?fileName=<%=(String)request.getParameter("taskFilePath")%>'" > <%=(String)request.getParameter("taskFilePath") %></a>  </td>
                                                            </tr>
                                                            <% } %>
                                                            <tr class="tblMainHead">
                                                                <td>&nbsp; Status</td><td><%=request.getParameter("taskStatus")%></td>
                                                             </tr>    
                                                            <tr class="tblMainHead">
                                                                <td>&nbsp; Task Priority</td><td><%=request.getParameter("taskPriority")%></td>
                                                            </tr> 
                                                               
                                                        </table>
                                                        
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tblRowHead">
                                                        
                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                            <tr>
                                                            <td width="13%">
                                                                <% if (request.getParameter("AssgnToName")!=null) { %>
                                                                <input type="button" onclick="location='listTasksAssgnByMe.do?AssgnToName=<%=request.getParameter("AssgnToName")%>&taskStatus=<%=request.getParameter("taskStatus")%>&tasksChoice=taskStatus' " value="Go Back" class="gradTwoBtn" />
                                                                
                                                                <% } else { %>
                                                                
                                                                    <input type="button" onclick="location='listTasksAssgnToMe.do?taskStatus=<%=request.getParameter("taskStatus")%> ' "  value="Go Back" class="gradTwoBtn" />
                                                                    <% } %>
                                                                </td>
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
