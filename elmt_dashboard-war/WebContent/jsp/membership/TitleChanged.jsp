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
    Document   : changeTitle
    Created on : Aug 5, 2009, 4:12:28 PM
    Author     : Ravi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Integrated Enterprise Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->

    </head>
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
                                            <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />--><br />
                                            <br />
                                         
                                               Change Title
                                           
                                            
                                        </td>
                                        
                                        
                                    </tr>
                                    <tr>
                                        <td>
                                                  <form   name="newtitle" method="post"  action="newTitleAction.do" onsubmit="">
                                                <input type="hidden" name="process" value="area_yearly" />
                                                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                                                    
                                                    <tr>
                                                        <td height="25" >
                                                            
                                                            <table width="100%" border="0" cellpadding="0" cellspacing="8" id="inboxTbl">
                                                                
                                                             
                                                                
                                                              <!--  <tr>
                                                                    <th height="25" class="tblMainHead"><span class="fonts">Chart Type :</span></th>
                                                                    <td height="25" class="listCellBg">
                                                                        <select  name="chartType">
                                                                            <option value="Pie">Pie</option>
                                                                            <option value="Bar">Bar</option>
                                                                            <option value="Area">Area</option>
                                                                            <option value="3D Pie">3D Pie</option>
                                                                        </select>
                                                                        <span class="asterisk">*</span>                                                                    </td>
                                                                </tr> -->
                                                            
                                                                    
                                                                
                                                                <tr>
                                                                    <td colspan="2" class="tblRowHead">
                                                                        
                                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">

                                                            
                                                                <input type="hidden" name="process" value="yearly" id="process" id="process" />
                                                                  <tr>
                                                                    <th width="25%" height="25" class="tblMainHead">Title Changed Successfully</th>
                                                                    <td width="83%" class="listCellBg">
                                                                                 To Reflect the Title changed Please Re-Login! <div id="statusId"></div>                                                                    </td>
                                                                </tr>



                                                                        <tr>
                                                                                <td width="11%" align="center"  > &nbsp;
                                                                                    
                                                                                                                                           </td>
                                                                            </tr>
                                                                           
                                                                    </table>                                                                    </td>
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
</html>