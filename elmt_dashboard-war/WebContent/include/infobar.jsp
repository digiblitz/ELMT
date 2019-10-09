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
<table width="760" border="0" cellpadding="0" cellspacing="0" id="infoBarTab">
			  <tr>
				<td class="infoHead" width="51">My Info</td>
				<td class="infoTopPad" width="670">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="infoTab">
					  <tr>

						<%
						String memberShipId="";
						String userFirstName="";
						String usrEmailId="";
                                                String userLastName="";
						String phNumber="";
                                                String UserRoll="";

						String fstnam=(String)session.getAttribute("firstName");
						String usrmail=(String)session.getAttribute("emailId");
						String memId=(String)session.getAttribute("memberId");
                                                String usrLname=(String)session.getAttribute("lastName");
						String phonNumber=(String)session.getAttribute("phoneNo");
                                                String sessionRole = (String)session.getAttribute("roleName");
						String url="";

						if(memId!=null)
						{
						memberShipId=memId;
						url="./MembershipReg.do?process=view";
						}
						else
						{
						memberShipId="Join Now!";
						url="./MembershipReg.do?process=reg";
						}

						if(fstnam!=null)
						{
						userFirstName=fstnam;
						}
						else
						{
						userFirstName="N/A";
						}

						if(usrmail!=null)
						{
						usrEmailId=usrmail;
						}
						else
						{
						usrEmailId="N/A";
						}

                                                if(usrLname !=null)
                                                {

						userLastName=usrLname;
						}
						else
						{
						userLastName="N/A";
						}
                                               if(phonNumber!=null)
						{
						phNumber=phonNumber;
						}
						else
						{
						phNumber="N/A";
						}
                                                if( sessionRole!=null)
						{
						UserRoll=sessionRole;
						}
						else
						{
						UserRoll="N/A";
						}
						%>

                                                <td width="3%" class="alignCenter"><img alt=""  src="images/icon_ib_name.jpg" title="My Name" width="20" height="22" /></td>
                                                <td width="20%" class="textTwo"><%=userFirstName+" "+userLastName%> </td>
                                                <td width="4%" class="alignCenter"><img alt=""  src="images/icon_ib_quest.JPG" title="My Role" width="17" height="16" /></td>
                                                <td width="12%" class="textTwo"><%=UserRoll%> </td>
                <td width="12%" class="textTwo"><a href="EditUserReg.do?status=edit">My Profile</a>
                 </td>
                 <td width="12%" class="textTwo">
                <a href="logout.do">Logout</a>  
                </td>                              
                    
                                                
                                                <!-- 
                                                <td width="4%" class="alignCenter"><img alt=""  src="images/icon_ib_quest.JPG" title="MemberShip Id" width="17" height="16"  /></td>
                                                <td width="12%" class="textTwo">&nbsp;<a href= "<!--%=url%>" class="linkFive"><!--%=memberShipId%></a></td>
                                                <td width="4%" class="alignCenter"><img alt=""  src="images/icon_ib_quest.JPG" title="My EMail Address" width="17" height="16"  /></td>
						<td width="20%" class="textTwo"><!--%=usrEmailId%></td>
                                                <td width="4%" class="alignCenter"><img alt=""  src="images/icon_ib_quest.JPG" title="My Phone Number" width="17" height="16"  /></td>
						<td width="13%" class="textTwo"><!--%=phNumber%></td>
						
						 -->

					  </tr>
					</table>
				</td>
			  </tr>
	  </table>
               <!--  table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" id="welcomeTab">
				   <tr>
<th colspan="1" rowspan="2"><img align="left"  src="images/logoh.jpg" /></th>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome <!--%=(String)session.getAttribute("firstName")%>  <!--%=(String)session.getAttribute("lastName")%> to Enterprise Landscape Management and Transformation</td>
                                       </tr>

<tr>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Select a module from above to display the corresponding menu options  </td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="Submit" value="My Profile" onclick="location.href='EditUserReg.do?status=edit'" style="max-height: 4" size="7"/>&nbsp;<input type="button" name="Submit" value="Logout"  onclick="javascript:location.href='logout.do'" style="max-height: 4" size="7" /></td>

</tr>

			  </table>-->
