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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
</head>

<body>
<center>
<strong><h1>Admin Page</h1><strong></center>

<strong>
<p><br>
  <strong>Non Usea Members : </strong></p>
<ul>
  <li>
  <a href="./nonhlc.do?process=Add">Non Usea Add</a>  </li>
  <li>      <a href="./nonhlc.do?process=List">Non Usea List</a><br>
      <br>
          
    <br>
          
Membership Create Contact Type:</li>
  <li><a href="./contact.do?conprocess=contacttype">Contact details</a></li>
  <li>      <a href="./contact.do?conprocess=list">Contact Type Listing</a><br>
    <br>
    <br>
              
    <strong>Membership Create Member Type:</strong></li>
  <li><a href="memberMaster.do?memProcess=initCreate">MemberShip Type Master Creation</a></li>
  <li><a href="memberMaster.do?memProcess=initList">MemberShip Type Master Management (Edit/Delete) </a></li>
</ul>
</p>
<p><strong>Horse Service Type Master:</strong></p>
<ul>
  <li><a href="horseServiceMaster.do?horseProcess=initHorseMaster">Horse Service  Type Master Creation</a></li>
  
    <a href="horseServiceMaster.do?horseProcess=initList"> Type Master Management (Edit/Delete) </a></li>
</ul>
<p><strong>Country Mail Type Master:</strong></p>
<ul>
  <li><a href="./countrymail.do?countryProcess=addCountrymail">Add Country mail List</a></li>
  <li>    <a href="./countrymail.do?countryProcess=lstCountrymail">Manage Country mail List</a><br>
         
  </li>
</ul>
<p><strong>Admin User Listing :</strong></p>
	<ul>
	  <li><a href="./UserRegList.do">All User Listing</a><br>           
    
  </li>
<p><strong>Admin Member Listing Based On Status :</strong></p>

     <li><a href="./MemberRegiList.do?memRegiListProcess=initFind&status=Pending">Member Registration Pending List</a><br>
     </li>
         
         <li><a href="./MemberRegiList.do?memRegiListProcess=initFind&status=Active">Member Registration Active List</a><br>
     </li>
             <li><a href="./MemberRegiList.do?memRegiListProcess=initFind&status=Inactive">Member Registration Inactive List</a><br>
     </li>
                 <li><a href="./MemberRegiList.do?memRegiListProcess=initFind&status=Deceased">Member Registration Deceased List</a><br>
     </li>
               <li><a href="./MemberRegiList.do?memRegiListProcess=initFind&status=Suspended">Member Registration Suspended List</a><br>
     </li>
             <li><a href="./MemberRegiList.do?memRegiListProcess=initFind&status=Expired">Member Registration Expired List</a><br>
     </li>
                 <li><a href="./MemberRegiList.do?memRegiListProcess=initFind&status=Retired">Member Registration Retired List</a><br>
     </li>
                     
   </ul>

<p><strong>Admin Refund Listing :</strong></p>

 <ul>
   <li><a href="./RefundList.do">Refund Admin Pending List</a></li></br>
   <li><a href="./AdminRefundStatusDisp.do?membstat=Approved">Admin Refund Approved List</a><br><br>
        
   </li>
 </ul>
</body>
</html>
