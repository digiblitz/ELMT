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
<td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="255" valign="bottom"><img src="images/logo.jpg" width="138" height="61" /></td>
            <td align="right" valign="bottom">
                <table align="right" cellpadding="0" cellspacing="0">
                    <td>
                        <div id="Usermenu-topbar" class="Usermenu">
                            <ul>
                                <li><a href="#">User</a></li>
                                
                            </ul>
                    </div></td>
                    
                </table>
            </td> 
        </tr>     
        <tr>
            <td height="10">
            </td>
        </tr>
    </table>
</td>




<tr>
    <td width="100%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="100%" height="19" colspan="8">
                    
                    <div id="ddtopmenubar" class="mattblackmenu">
                        <ul>
                            <li><a href="#">About XYZ-IED Program</a></li>
                            <li><a href="#" rel="ddsubmenu1">XYZ-IED Management Plan</a></li>
                            <li><a href="#" rel="ddsubmenu2">Future XYZ-IED Views</a></li>
                            <li><a href="#" rel="ddsubmenu3">XYZ-IED Tutorial</a></li>
                            <li><a href="#" rel="ddsubmenu4">XYZ-IED Standards</a></li>
                            <li><a href="#" rel="ddsubmenu5">Site Map</a></li>
                        </ul>
                    </div>
                    
                </td>
            </tr>
            
            <tr height="10">
                &nbsp;
            </tr>
            
            
        </table>
    </td>
</tr>

<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="100%" height="19" colspan="4">
                
                
                <div id="EAframe" class="EAmenu">
                    <ul>
                        <%

            List entityList = (List) session.getAttribute("entityList");
            Iterator entityIterator = entityList.iterator();
            while (entityIterator.hasNext()) { 
                String entityValues[] = (String[]) entityIterator.next();
                String entityName = entityValues[4]; %>
                        
                        <li><a href=<% if (entityName.equals("Strategic View")) {
                                %>"/ied_mfg-war/StrategicViewServlet?process=orgLevel"<%
                            }%> > <%=entityName%></a></li>
                        <%
            }
                        %>
                        
                                           
                        <table align="right" width="50" cellpadding="0" cellspacing="0">
                            <td><li><a href="XYZ-IED.jsp">Logout</a></li></td>
                        </table>
                        
                        
                    </ul>
                </div>
                
            </td>
        </tr>
        
        
    </table>
</td>
