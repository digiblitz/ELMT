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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<!--/*  Program Name    : frmAdvTypeAdd.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.2
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
--> 
 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="./javascripts/basic.js" type="text/javascript" ></script>
<script src="./javascripts/frmAdvType.js" type="text/javascript" ></script>
<link href="./css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>

<body>
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">Create Advertisement Type </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
    <strong>
	  Please create the required Advertisement Type for all the advertisements that are to be placed for all the event. </strong><br />
		<br />
		Select a Media Type given below and fill-in the relevant Advertisement Type details. <br />
	<br /></td>
  </tr>
  <tr>
  	<td>
	
		<form name="frmCreateAdvType" method="post" action="">
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">Create A Dimension Detail:</td>
				  </tr>
				   <tr>
				     <td class="tableLeft">Select A Media Type:</td>
				     <td class="tableRight">
					 	<select name="mediaType_sel" id="mediaType_sel" class="selectboxOne">
							<option selected="selected">Select One</option>
							<option value="">Omnibus</option>
							<option value="">Magazine</option>
						</select>
				 	 <span class="asterisk">*</span>					 </td>
		     	  </tr>
				   
				  <tr>
					<td class="tableLeft">Advertisement Type Name:</td>
					<td class="tableRight">
					<input type="text" name="txtAdvName" class="textboxOne" size="25" />
				 	 <span class="asterisk">*</span>					</td>
				  </tr>
				  
				  <tr>
					<td class="tableTopAlign">Description:</td>
					<td class="tableRight"><textarea name="textarea" cols="25" rows="5" class="textAreaOne"></textarea>
				    <span class="asterisk">*</span>					</td>
				  </tr>
				  
			      <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="button" value="Create" class="gradBtn" onclick="return advTypeCheck(); (window.location.href('adv-confirmation-page.html');" />&nbsp;
					<input type="button" value="cancel" class="gradBtn" onclick="javascript:history.back(-1);" />					</td>
				  </tr>
			</table>
			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
  </tr>
</table>

</body>
</html>
