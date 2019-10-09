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
<%@ page import="java.util.*" errorPage="error.jsp"%>

<%
String sessUserId = (String) session.getAttribute("userId");
String sessId = (String) session.getAttribute("sessionId");
String sessReqst=(String)session.getAttribute("requestStatus");
if(sessId != null){
	response.sendRedirect("index.jsp");
}
else if(sessReqst=="false") {
    response.sendRedirect("index-no-role.jsp");
}
%>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="headerTab">
		  <tr>
			<td bgcolor="#EDEDED" class="alignTop">
				<!-- top navibar Starts Here -->
				<table width="740" border="0" align="center" cellpadding="0" cellspacing="0" id="topNavibarTab">
				  <tr>
					
					<td width="245" class="alignTop">&nbsp;</td>
					<td width="230" class="alignTop">
						<div class="alignRight"> 
						<%
							
							String sessFname=(String)session.getAttribute("firstName");
							if(sessFname==null)
							{
							//session.invalidate();
							%>
								
							<!-- span class="welcome">Welcome Guest !</span> 
							<span class="divider">|</span--> 
							<span class="welcome">Welcome </span> 
							<span class="divider">|</span> 
							
							<%}
							
							else
							                                                        
							{%>
                                                        
                                                            
								
							<span class="welcome">Welcome <%=sessFname%>!</span> 
							<span class="divider">|</span> 
							<a href="./logout.do" class="linkOne">LOGOUT</a> 
							<span class="divider">|</span>
							
							<%}%>
							
						</div>
					</td>
					<td width="110" class="alignLeft">
						<span class="date">&nbsp;
							<SCRIPT LANGUAGE="JavaScript">
							if(navigator.appName == "Netscape") {									
									//document.write(isnDays[today.getDay()+1]+", "+isnMonths[today.getMonth()+1]+" "+today.getDate()+", "+ (today.getFullYear()+1900));
									document.write(isnDays[today.getDay()+1]+", "+isnMonths[today.getMonth()+1]+" "+today.getDate()+", "+ (today.getFullYear()));
								}else{
								document.write(isnDays[today.getDay()+1]+", "+isnMonths[today.getMonth()+1]+" "+today.getDate()+", "+today.getFullYear());
								}  
							</SCRIPT>
						</span>
					</td>
				  </tr>
				</table>
				<!-- top navibar Ends Here -->
			</td>
		  </tr>
		  <tr>
		    <td><table width="758" border="0" align="center" cellpadding="0" cellspacing="0" id="logoTimeHolderTab">
              <tr>
                <td>
                    <!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                            <%--<img src="/images/title.jpg"  />--%>
                                           <%-->img src="images/banner_final_hlc.jpg" />--%>
                                           <a href="logout.do"><img src="images/banner_final_landscape.jpg"/></a>
					<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
				
				</td>
              </tr>
            </table></td>
	      </tr>
		  
		  <%if(sessFname!=null){%>
		  
		  <tr>
		    <td class="mainNavibar">
			
				<!-- Main Navibar Ends Here -->
				<table width="750" border="0" align="center" cellpadding="0" cellspacing="1" id="mainNaviBar">
				  <tr>
					<td class="mainTabOne"><a href="index.jsp" class="linkTwo"><div class="tabPad">Dashboard Home</div></a></td>
					<td class="mainTabOne"><a href="#" class="linkTwo"><div class="tabPad">Join Now</div></a></td>
					<td class="mainTabOne"><a href="#" class="linkTwo"><div class="tabPad">Messages</div></a></td>
				    <td class="mainTabOne"><a href="#" class="linkTwo"><div class="tabPad">Downloads</div></a></td>
				    <td class="mainTabOne"><a href="#" class="linkTwo"><div class="tabPad">Webstore</div></a></td>
				    <td class="mainTabOne"><a href="#" class="linkTwo"><div class="tabPad">Reports</div></a></td>
				  </tr>
				</table>
				<!-- Main Navibar Ends Here -->			</td>
	      </tr>
		  <tr>
		    <td class="subNaviBar">
				<!-- Sub Navibar Ends Here -->
				<table width="750" border="0" align="center" cellpadding="0" cellspacing="0" id="subNaviBar">
				  <tr>
					<td class="mainTabTwo"><a href="#" class="linkThree"><div class="tabTwoPad">Memberships</div></a></td>
					<td class="mainTabTwo"><a href="#" class="linkThree"><div class="tabTwoPad">Inventory</div></a></td>
					<td class="mainTabTwo"><a href="#" class="linkThree"><div class="tabTwoPad">Accounts</div></a></td>
				    <td class="mainTabTwo"><a href="#" class="linkThree"><div class="tabTwoPad">Events</div></a></td>
				    <td class="mainTabTwo"><a href="#" class="linkThree"><div class="tabTwoPad">Marketing</div></a></td>
				    <td class="mainTabTwo"><a href="#" class="linkThree"><div class="tabTwoPad">Communication</div></a></td>
				  </tr>
				</table>
				<!-- Sub Navibar Ends Here -->			</td> 
	      </tr>
		  <%}%>
</table>

<map name="Map" id="Map">
<area shape="rect" coords="2,0,77,15" href="index.jsp" alt="HLC Home" />
</map>

<map name="Map2" id="Map2">
<area shape="rect" coords="2,0,77,15" href="#" alt="About HLC" />
</map>
