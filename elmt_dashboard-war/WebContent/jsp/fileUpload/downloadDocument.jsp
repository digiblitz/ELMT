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
    Document   : openDocument
    Created on : Jun 18, 2009, 6:55:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int i;
            boolean fileexist = false;
            File filename = null;
            FileInputStream fr = null;
            String fileName = request.getAttribute("fileName").toString();
            OutputStream fout = response.getOutputStream();
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            if (request.getAttribute("path").toString() != null) {
                filename = new File(request.getAttribute("path").toString());
                fileexist = (filename.exists() && filename.canRead());
            }

            if (fileexist) {


                response.setContentType("application/text");
                response.setHeader("Content-Disposition", "attachment; filename="+fileName);
                byte[] bytes = new byte[256];
                fr = new FileInputStream(filename);
                while ((i = fr.read(bytes)) > 0) {
                    bout.write(bytes, 0, i);
                }
                fr.close();
                bout.close();


            }
        
        %>
    </body>
</html>
