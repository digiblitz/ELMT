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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Integrated Enterprise Dashboard</title></head>

<body>
<h4>Admin Module</h4>
<p><a href="SponsorshipPlan.do?process=newaddSpoPlan">Sponsorship Plan Add</a><br />
    <a href="SponsorshipPlan.do?process=lispoplan">Sponsorship Plan Manage(list/edit/delete) </a><br>
    <a href="Sponsorship.do?pro=p0">Pending Status</a><br /> 
    <!-- <a href="./Sponsorship.do?pro=padm01">Active Status For Admin </a></p> -->
<p><strong>SalesPerson</strong></p>
</p><a href="Sponsorship.do?pro=p01">Pending Status</a> <br />
<a href="Sponsorship.do?pro=p1">Active Status</a> <br />
<a href="Sponsorship.do?pro=p011">Payment Schedule Details</a> 
<h4>Sponsor Home Page </h4>

<a href="Sponsor.do?usrprocess=lstAllPlan">List Plans</a> <br/>
<a href="Sponsorship.do?pro=uv&yid=ff093ecd-e823-4e36-acb9-cef71534dd74">My Plans</a> <br />
<a href="SponsorRequest.do">Sponsor Request</a> 
</body>
</html>
