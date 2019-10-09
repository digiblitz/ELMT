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
    Document   : createXYLineByXLS
    Created on : Mar 28, 2009, 3:26:50 PM
    Author     : parteek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.jfree.chart.*,org.jfree.chart.plot.PlotOrientation,
 org.jfree.data.category.DefaultCategoryDataset,
 org.jfree.data.general.DefaultPieDataset,
 org.jfree.data.xy.*,
 org.jfree.data.*" %>
 <%@ page  import="java.awt.*,java.util.*" %>
<%@ page  import="java.io.*" %>
 <%@ page  import="org.apache.poi.hssf.usermodel.HSSFCell,org.apache.poi.hssf.usermodel.HSSFRow,org.apache.poi.hssf.usermodel.HSSFSheet" %>
 <%@ page  import="org.jfree.chart.entity.*, org.jfree.chart.renderer.category.*,org.jfree.data.category.*,org.jfree.chart.plot.*" %>
<%@ page  import ="org.jfree.data.general.*" %>
<%! double db; String db1; %>
<html>
     <head>
        <title><%=(String)session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
         <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <link href="../../css/core-mff.css" type="text/css" rel="stylesheet" />  
		<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />   
    </head>
    <% 

	ArrayList patient=(ArrayList)session.getAttribute("dataholder"); 
    String reportTitle=(String)session.getAttribute("title");

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (int i=1;i<patient.size(); i++){
                   ArrayList cellStoreVector=(ArrayList)patient.get(i);
			for (int j=0; j < cellStoreVector.size();j++){
                            System.out.println("CellStorevectro"+cellStoreVector.size());
                            
				HSSFCell myCell = (HSSFCell)cellStoreVector.get(j);
				     if(j==0)
							  db=Double.parseDouble(myCell.toString());
					  else
							 db1=myCell.toString();
			}
			 dataset.addValue(db, "Cases",db1);
			 
				 
			}

			JFreeChart chart = ChartFactory.createWaterfallChart
  (reportTitle,"Surgeries", "Cases", 
  dataset, PlotOrientation.VERTICAL, true,true, false);
  chart.getTitle().setPaint(Color.blue); 
  CategoryPlot p = chart.getCategoryPlot(); 
  p.setRangeGridlinePaint(Color.red); 
  p.setDomainGridlinesVisible(true);
  p.setDomainGridlinePaint(Color.black);
  
  String filePath=null;
            try {
                
                final ChartRenderingInfo info = new 
              ChartRenderingInfo(new StandardEntityCollection()); 
			  filePath=getServletContext().getRealPath("/")+"images\\waterfall.png";
                
                          final File file1 = new File(filePath);
                ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
                
            } catch (Exception e) {
                out.println(e);
            }
		
  
  
  %>
    
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
					<img src="images/waterfall.png" width="600" height="400" 
         border="0" align="center"></img> 
        
										</td>
									</tr>
								</table>
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
