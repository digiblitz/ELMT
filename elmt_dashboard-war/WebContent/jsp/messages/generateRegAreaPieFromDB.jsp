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
    Document   : generateDonationAmountReport
    Created on : Jul 30, 2009, 11:53:23 AM
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
                              <h2 align="center"><%=(String)request.getAttribute("title") %></h2>
                            </td>
                        </tr>
                         <tr>
                            
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
								<% String graphNew=(String)request.getAttribute("graphsCount");
								           String graph[]=graphNew.split(",");
										   int arrayLen=graph.length;
										   if(arrayLen==4) {
								 %>
                               <table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                                    <tr>

                                        <td rowspan="2" class="tblDescrp">
									<img src="images/chart.png" width="500" height="400" border="2"  ></img>

										</td>
									</tr>
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/PyramidChart.png" width="500" height="400"  border="2" ></img>

									</td>
									</tr>
									</table>
									<table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/Linechart.png" width="500" height="400" border="2"  ></img>
									</td>
									</tr>
									<tr>
									<td rowspan="2" class="tblDescrp">
									<img src="images/pie.png" width="500" height="400" border="2"   ></img>
									</td>
									</tr>

								</table>
								<% } else if(arrayLen==2) { %>
								 <table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
								   <% for(int i=0;i<arrayLen;i++) { %>
                                    <tr>
                                        
                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[i]%>.png" width="500" height="400" border="2"  ></img>
        
										</td>
									</tr>
									<% } %> 
									
									</table>
									<% } else if(arrayLen==3) { %>
									<table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
								   <% for(int i=0;i<arrayLen;i++) { 
									   if(i<=1) { %>
                                    <tr>
                                        
                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[i]%>.png" width="500" height="400" border="2"  ></img>
        
										</td>
									</tr>
									<% }  else { %>
									</table>
									<tr>
                                        
                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[i]%>.png" width="500" height="400" border="2"  ></img>
        
										</td>
									</tr>
									<% } } %>
									</table>
									<% } else if(arrayLen==1) { %>
										<table width="90%" border="0" cellpadding="0" cellspacing="0" class="formLayout" align="center">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                                    <tr>

                                        <td rowspan="2" class="tblDescrp">
									<img src="images/<%=graph[0]%>.png" width="500" height="400" border="2"  ></img>

										</td>
									</tr>
					
									

								</table>
									<% } %>
							</td>
						</tr>
					</table>
					</td>
					</tr>
					<tr>
                <td>
                   
                   <input type="submit" value="close" name="close" onclick="window.close('AreaSalesGraph');return false;" class="gradTwoBtn" />
                </td>
            </tr>
					</table>
									
      
    </body>
</html>
