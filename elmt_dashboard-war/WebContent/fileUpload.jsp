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
    Document   : fileUpload
    Created on : Jun 25, 2009, 11:08:08 AM
    Author     : vikram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<form action="NewUpload.jsp" name="upform" enctype="multipart/form-data">
<table width="60%" border="0" cellspacing="1" cellpadding="1" align="center" class="style1">
<tr>
<td align="left"><b>Select a file to upload :</b></td>
</tr>
<tr>
<td align="left">
<input type="file" name="filename" size="50">
</td>
</tr>
<tr>
<td align="left">
<input type="hidden" name="todo" value="upload">
<input type="submit" name="Submit" value="Upload">
<input type="reset" name="Reset" value="Cancel">
</td>
</tr>
</table>
</form>

</html>
