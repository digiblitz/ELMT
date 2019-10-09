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
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>

<!--/*  Program Name    : frmAdvHome.jsp
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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
</head>
<h1>::Advertisement Home Page::</h1>
<body>
 
<h4>Media</h4>
<a href="./advertisement.do?advProcess=advMediaPlanAddNew">Create Media </a><br>
<a href="advertisement.do?advProcess=advMediaLst">Show Media(View/Edit/Delete)</a><br> 

<h4>Dimension</h4>
<a href="advertisement.do?advProcess=advDimensionAdd">Create Dimension</a><br />
<a href="advertisement.do?advProcess=advLstDimension">Show Dimension(View/Delete/Edit)</a><br />

<h4>Issue</h4>
<a href="advertisement.do?advProcess=advIssueAdd">Create Issue</a><br />
<a href="advertisement.do?advProcess=advIssueLst">Show Issue(View/Delete/Edit)</a><br /> 

<h4>Advertisement Type</h4>
<a href="advertisement.do?advProcess=advDispTypeAdd">Create Display Type</a><br />
<a href="advertisement.do?advProcess=advDispTypeLst">Show Display Type(View/Delete/Edit)</a><br />

<h4>Advertisement Sub Type</h4>
<a href="advertisement.do?advProcess=advDispSubTypeAdd">Create Display Sub Type</a><br />
<a href="advertisement.do?advProcess=advDispSubTypeLst">Show Display Sub Type(View/Delete/Edit)</a><br />

<h4>Dimensional Details</h4>
<a href="advertise.do?method=newAdd">Create Dimensional Details</a><br />
<a href="advSearch.do?method=initSearch">Show Dimensional Details(View/Delete/Edit)</a><br />

<h4>Admin View Page</h4>
<a href="advertiseAdmin.do?advProcess=AdvAdmin">Main page </a><br />
<a href="advertiseAdmin.do?advProcess=AdvAdminManifest">View Manifest </a><br />

<h4>User Page</h4>
<p><a href="AdvertisePrice.do?advPrice=mediaView">Available Media </a></p>
<a href="AdvertisePrice.do?advPrice=userStatus">Request Status</a>

<h4>Price Matrix</h4>
<a href="AdvertisePrice.do?advPrice=userView">Show Price Matrix(View)</a>
<p><br />
</p>
</body>
</html>


