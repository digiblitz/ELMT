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
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Integrated Enterprise Dashboard</title>
    </head>
    <body>

    <h1>JSP Page</h1>
    <p><br/>
      <a href="frmEventOrgRenewal.jsp">Event Organiser Endorsed Form</a><br>
      <a href="frmSponsorPlanManagement.jsp">Sponsorship Module </a><br>
	 
	   <a href="./advertisement.do?advProcess=show">Advertisement Module </a><br> </p>
    
    </p>
    <h1>Membership Managment</h1>
    <br/>
  
    <a href="./HorseReg.do">Horse Registration</a> <br>
    <a href="./HorseMemberList.do?process=view">List Horse Registered Under This Member</a> <br>
   
    <a href="./EditUserReg.do?status=register">User Registration</a> <br>
    <a href="./EditUserReg.do?status=edit">Edit User Registration</a> <br>
    <a href="./EditUserReg.do?status=view">View User Registration</a> <br>
    
    <a href="./MembershipReg.do?process=reg">Human Member Membership Registration</a> <br>
     <a href="./MembershipReg.do?process=renew">Renew / Upgrade / Downgrade Human Membership Registration</a> <br>
    <a href="./login.jsp">Login Page</a><br>
    <a href="./adminPage.jsp">Admin Pages</a><br>
 
    <a href="./UsrRefundReqListAction.do">Refund User List</a><br>
    
    <a href="./changePwd.do?process=disp">Change Password</a><br>
    
    <a href="./MembershipReg.do?process=view"> View My Membership Profile </a><br/>
    
    <a href="./EditUserReg.do?status=signup"> user sign up page </a><br/>
    
     <h1>Meetings</h1>
    
    <a href="./IcpUsrList.do?process=list">User Icp Registered Meeting List</a><br>
    <a href="./IcpUsrMeetList.do?process=list">User Icp List of  Meetings</a><br>
    
    <a href="./IcpOrgRegList.do?process=initList">Organizer Icp Meetings List of user based on status </a><br>
    
    <a href="./IcpOrgRegFrm.do?process=disp">Organizer Icp Meetings Registration Page </a><br> 
    
    <a href="./IcpOrgRegFrm.do?process=edit">Organizer Icp Meetings Registration Edit Page </a><br> 
   
    <a href="./listAdminOrg.do?method=initAdmin">Admin Listing Page </a><br>
        
    <a href="./listAdminOrg.do?method=searchOrg">Organizer Listing Page </a><br>
    
    <h4>Event Details</h4>
    <p>

    <a href="EventOrgRenewal.do?eventProcess=eventViewAdmin"> Admin Events Details</a><br/>
    <a href="EventOrgRenewal.do?eventProcess=eventViewOrg"> Organizer Events Details</a><br/>
    <a href="./OrganizerHLCEventReg.do?process=reg">Event Organizer Registration</a> <br>
    
    </p>
    
    <h4>Official</h4>
    <p>
    
     <a href="./AddrDistList.do?process=listDistList"> List Distribution List </a><br/>
     
     <a href="./AddrDistList.do?process=Create">  Create Distribution List  </a><br/>
        
    </p>
     <h4>Messaging</h4>
    <p>
    
     <a href="./AddrDistList.do?process=listDistList"> List Distribution List </a><br/>
     
     <a href="./AddrDistList.do?process=Create">  Create Distribution List  </a><br/>
        
    </p>
    
    </body>
    
</html>

