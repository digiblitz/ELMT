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
    Document   : generateEventsAreaWiseForEventType
    Created on : Aug 6, 2009, 1:58:45 PM
    Author     : parteek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
     <head>
        <title>Integrated Enterprise Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
         <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <link href="../../css/core-mff.css" type="text/css" rel="stylesheet" />
		<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
                               <table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                                    <tr>

                                        <td rowspan="2" class="tblDescrp">
									<img src="images/Linechart.png" width="500" height="400" border="2"  ></img>

										</td>
									</tr>
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/plotformat.png" width="500" height="400"  border="2" ></img>

									</td>
									</tr>
									</table>
									<table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/tube.png" width="500" height="400" border="2"  ></img>
									</td>
									</tr>
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/pie.png" width="500" height="400" border="2"   ></img>
									</td>
									</tr>

								</table>
							</td>
						</tr>
					</table>
					</td>
					</tr>
					<tr>
                <td>

                   <input type="submit" value="close" name="close" onclick="window.close('EventReports');return false;"/>
                </td>
            </tr>
					</table>


    </body>
</html>

