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
    Document   : showSalesReports
    Created on : Sep 2, 2009, 4:52:02 PM
    Author     : parteek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Page</title>
		<script src="javascripts/basic.js" type="text/javascript" ></script>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
    </head>
     <body>
            <table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">


            <tr>
                <td class="tableCommonBg">

                    <table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                         <tr>
                            <td height="64" width="500" style="background-color: white;">
                              <h2 align="center"><%=(String)request.getAttribute("title")%></h2>
                            </td>
                        </tr>
                         <tr>

                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
                               <table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout" align="center">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                                    <tr>

                                        <td rowspan="2" class="tblDescrp">
									<img src="images/Chart.png" width="500" height="400" border="2"  ></img>

										</td>
									</tr>
					
									

								</table>
							</td>
						</tr>
							<tr>
                <td>

                   <input type="submit" class="gradTwoBtn" value="Back" name="Back" onclick="location.href='showReportTypes.do'"/>
                </td>
            </tr>
					</table>
					</td>
					</tr>
					<tr>
                <td>
					</table>
    </body>
</html>
