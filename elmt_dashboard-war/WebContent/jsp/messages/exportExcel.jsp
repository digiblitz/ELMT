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
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" 
%><%@ page import="java.util.List"
%><jsp:useBean id="exp" class="com.excel.export.DataExport"/><%
List sExport = (List)request.getAttribute("EXPORT_EXCEL")
if (sExport != null) {
   exp.setTemplatefile("producttpl.xls");
   exp.setOutputfile("HLCMemberDetails.xls");
   exp.setColumnnames(new String[]{"pid", "pname", "amount", "price"});
   exp.setRowno(1);
   exp.setDatasource(sExport);
   exp.setResponse(response);
   exp.export();
   return;
}
%>
