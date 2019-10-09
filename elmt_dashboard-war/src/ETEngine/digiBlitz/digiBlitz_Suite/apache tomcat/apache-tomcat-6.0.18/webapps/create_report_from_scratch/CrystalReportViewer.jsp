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
<%@ page import = "com.crystaldecisions.sdk.occa.report.application.ReportClientDocument" %>
<%@ page import = "com.crystaldecisions.report.web.viewer.*" %>

<%
/**
===========================================================
INSTANTIATE THE VIEWER AND DISPLAY THE REPORT THROUGH THE REPORT VIEWER
===========================================================
    - Create a Viewer object
    - Set the source for the  viewer to the client documents report source
    - Process the http request to view the report
    - Dispose of the viewer object
*/

// Retrieve the ReportClientDocument object from session
Object reportsource = session.getAttribute("CrystalReportViewer.ReportSource");

// Create a Viewer object
CrystalReportViewer oCrystalReportViewer = new CrystalReportViewer();
// Set the name for the viewer
oCrystalReportViewer.setName("Crystal_Report_Viewer");
// Set the viewer to its own form
oCrystalReportViewer.setOwnForm(true);
// Ste the viewer to own the page
oCrystalReportViewer.setOwnPage(true);
//Sets the print mode for the viewer
oCrystalReportViewer.setPrintMode(CrPrintMode.PDF);

// Set the source for the  viewer to the client documents report source
oCrystalReportViewer.setReportSource(reportsource);
// Process the http request to view the report
oCrystalReportViewer.processHttpRequest(request, response, getServletConfig().getServletContext(), out);
// Dispose of the viewer object
oCrystalReportViewer.dispose();
%>
