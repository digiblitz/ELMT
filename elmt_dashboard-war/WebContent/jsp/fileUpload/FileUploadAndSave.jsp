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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
    <head>
        <title></title>
        <html:base/>
    </head>
    <body bgcolor="white">
        <html:form action="/FileUploadAndSave" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td align="center" colspan="2">
                    <font size="4">File Upload on Server</font>
                </tr>
                
                <tr>
                    <td align="left" colspan="2">
                    <font color="red"><html:errors/></font>
                </tr>
                
                
                <tr>
                    <td align="right">
                        File Name
                    </td>
                    <td align="left">
                        <html:file property="theFile"/> 
                    </td>
                </tr>
                
                
                <tr>
                    <td align="center" colspan="2">
                        <html:submit>Upload File</html:submit>
                    </td>
                </tr>
            </table>
            
            
        </html:form>
    </body>
</html:html>
