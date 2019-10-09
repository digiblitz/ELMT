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
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@ page  import="java.awt.*,java.util.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="java.awt.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="org.apache.poi.hssf.usermodel.HSSFCell,org.apache.poi.hssf.usermodel.HSSFRow,org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook,org.apache.poi.poifs.filesystem.POIFSFileSystem" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.entity.*, org.jfree.chart.renderer.category.*,org.jfree.data.category.*,org.jfree.chart.plot.*" %>
<%@ page  import ="org.jfree.data.general.*"%>
<%
int i=1;

ArrayList dailyPaymentDet=(ArrayList)session.getAttribute("dailyPaymentDet");
String monthly=(String)session.getAttribute("monthValue");
String yearly=(String)session.getAttribute("yearValue");
Iterator itrDailyPaymentDet = dailyPaymentDet.iterator();
final DefaultPieDataset data = new DefaultPieDataset();
       
  while(itrDailyPaymentDet.hasNext())
  {
    			
  Double db= (Double)itrDailyPaymentDet.next();
  double dbValue = db.doubleValue();
  
data.setValue(new Integer(i), db);
i=i+1;
  }
  
  JFreeChart chart = ChartFactory.createPieChart3D
  ("Daily Sales Report For "+yearly, data, true,true,true);
  PiePlot3D p=(PiePlot3D)chart.getPlot();
  p.setForegroundAlpha(0.5f);
  
      

         
String filePath=null;
            try {
                final ChartRenderingInfo info = new 
              ChartRenderingInfo(new StandardEntityCollection()); 
			  filePath=getServletContext().getRealPath("/")+"images\\3DPieChart.png";
                final File file1 = new File(filePath);
                ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
            } catch (Exception e) {
                out.println(e);
            }
%>
<html>
   <head>
        <title><%=(String)session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
         <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <link href="../../css/core-mff.css" type="text/css" rel="stylesheet" />  
		<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />   
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
        </table>
       <img src="images/3DPieChart.png" width="600" height="400" 
         border="0" align="center"></img>
    </body>
</html> 
