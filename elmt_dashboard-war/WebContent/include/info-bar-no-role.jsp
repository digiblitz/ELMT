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
				<td class="infoTopPad" width="450">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" id="infoTab">
					  <tr>

						<%
						String memberShipId="";
						String userFirstName="";						
						String usrEmailId="";
                                                String userLastName="";						
						String phNumber="";
                                                
						String fstnam=(String)session.getAttribute("firstName");
						String usrmail=(String)session.getAttribute("emailId");
						String memId=(String)session.getAttribute("memberId");
                                                String usrLname=(String)session.getAttribute("lastName");
						String phonNumber=(String)session.getAttribute("phoneNo");
						String url="";
						
		
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

						%>

						<td width="4%" class="alignCenter"><img src="images/icon_ib_name.jpg" alt="My Name" width="20" height="22" /></td>
						<td width="24%" class="textTwo"><%=userFirstName+" "+userLastName%></td>
						<td width="6%" class="alignCenter"><img src="images/icon_ib_mail.jpg" alt="My EMail Address" width="23" height="22" /></td>
						<td width="24%" class="textTwo"><%=usrEmailId%></td>
						<td width="4%" class="alignCenter"><img src="images/icon_ib_phone.jpg" alt="My Phone Number" width="20" height="22" /></td>
						<td width="13%" class="textTwo"><%=phNumber%></td>
						<td width="13%" class="textTwo"><div align="center"><strong><span class="style1">MBR #</span></strong></div></td>
						<!-- <td width="12%" class="textTwo">&nbsp;<a href="<%=url%>" class="linkFive"><%=memberShipId%></a></td>-->
						
					  </tr>
					</table>
				</td>
				<td class="infoTopPad" width="121"><input type="button"  name="Submit" value="Edit Profile" onclick="location.href='EditUserReg.do?status=edit'" style="width:75px;" class="gradTwoBtn" /></td>
				<td class="infoTopPad" width="61">
	<input type="button" name="Submit" value="Logout" class="gradTwoBtn" onclick="javascript:location.href='logout.do'" /></td>
				<td class="infoTopPadTwo" width="77">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<%
						String msgCount  = (String)session.getAttribute("msgCount");
						if(msgCount==null) msgCount="0";
					%>
					  <tr>
						<td width="76%" class="alignCenter"><span class="styleBoldOne"><%=msgCount%></span> <strong><span class="style1">Msg</span></strong></td>
						<td width="24%"><img src="images/icon_msg.gif" alt="Messages" width="16" height="12" /></td>
					  </tr>
					</table>
				</td>
			  </tr>
	  </table>
