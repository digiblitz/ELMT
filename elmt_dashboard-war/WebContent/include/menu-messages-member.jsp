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
<%@ page import="com.hlccommon.util.*"%>

  <script language="javascript">
	function headEntityCheck(){
		if(document.frmLeftMenu.headEntityId.value==""){
			alert("Select Any One Entity");
			document.frmLeftMenu.headEntityId.focus();
			return false;
		}
		return true;
	}
  </script>
<%
	String count = (String) session.getAttribute("count");
	if(count==null) count ="";

%>
				<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" id="fullHeight" class="menuTableBg">
				  <tr>
					<td class="alignTop"><br />
					
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="leftMenuTab">
						  <tr>
							<td class="menuTabLftTopCrnr"></td>
							<td class="menuTab"></td>
							<td class="menuTabRghtTopCrnr"></td>
						  </tr>
						  <tr>
							<td class="menuTabLft"></td>
							<td class="menuTabBg">
								  <%
								  	String headSessionRoleId = (String) session.getAttribute("roleId");
									if(headSessionRoleId==null) headSessionRoleId="";
								  %>
								  <form name="frmLeftMenu" action="roles.do" method="post" onsubmit="return headEntityCheck();">
									<input type="hidden" name="roleProcess" value="selectEntPrivePermDashboard"/>
									<input type="hidden" name="headRoleId" value="<%=headSessionRoleId%>"/>				  
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
                                  <tr>
                                    <td height="20" class="alignLeft"><span class="textBold">&nbsp;&nbsp;&nbsp;Browse Other Sub-Departments</span> </td>
                                  </tr>
                                  <tr>
                                    <td height="25" class="menuLinkbg">&nbsp;
                                      <select name="headEntityId" id="headEntityId" class="selectboxOne">
                                        <option value="">Select One</option>
                                       	<%
										ArrayList entityListLeft = (ArrayList)session.getAttribute("DBEntityList");
										if(entityListLeft!=null && entityListLeft.size()!=0){
											Iterator itEntList = entityListLeft.iterator();
											while(itEntList.hasNext()){
												String strRolelist[]= (String[])itEntList.next();
												String roleIdLeft = strRolelist[1];
												String roleName = strRolelist[3];
												String entityIdVal = strRolelist[2];
												String entityName = strRolelist[4];
												
											 // String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName};
										%>
										<option value="<%=entityIdVal%>"><%=entityName%></option>
									  <%
									  }
									  }
									  %>
                                      </select>
                                    </td>
                                  </tr>
								  
                                  <tr>
                                    <td height="25" class="menuLinkbg">&nbsp;&nbsp;<input type="submit" name="Submit" value="Browse" style="width:55px;" class="gradBtn" /></td>
                                  </tr>
                                </table>
								</form>
							</td>
							<td class="menuTabRght"></td>
						  </tr>
						  <tr>
							<td class="menuTabLftBotCrnr"></td>
							<td class="menuTabBot"></td>
							<td class="menuTabRghtBotCrnr"></td>
						  </tr>
						</table>
						<br />
					
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="leftMenuTab">
						  <tr>
							<td class="menuTabLftTopCrnr"></td>
							<td class="menuTab"></td>
							<td class="menuTabRghtTopCrnr"></td>
						  </tr>
						  <tr>
							<td class="menuTabLft"></td>
							<td class="menuTabTwoBg">
								
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
                                  <tr>
                                    <td height="20" class="alignLeft"><span class="textBold">&nbsp;&nbsp;&nbsp;Related Links</span> </td>
                                  </tr>
								   <tr>
									<td height="20" class="menuLinkbg" align="left"><span class="textBold">&curren; General Links </span></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="messages.do?msgProcess=inbox" class="linkFour">Inbox (<%=count%>)</a></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="messages.do?msgProcess=initCompose" class="linkFour">Compose Message</a></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="contactAction.do?method=search" class="linkFour">Address Book (5)</a></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&nbsp;&nbsp;&nbsp;&raquo; <a href="./contactAction.do?method=initAdd" class="linkThree">Create A Contact</a></td> 
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="#" class="linkFour">Distribution Lists (2)</a></td>
								  </tr>
								   <tr>
									<td height="20" class="menuLinkbg" align="left">&nbsp;&nbsp;&nbsp;&raquo; <a href="./AddrDistList.do?process=Create" class="linkThree">Create A List</a></td> 
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&nbsp;&nbsp;&nbsp;&raquo; <a href="./AddrDistList.do?process=listDistList" class="linkThree">Edit A List</a></td>
								  </tr>
								 <tr>
									<td height="20" class="menuLinkbg" align="left"><span class="textBold">Download Database </span></td>
								  </tr>
								   <tr>
									<td height="20" class="menuLinkbg" align="left">&nbsp;&nbsp;&nbsp;&raquo; <a href="msgDB.do?method=reqList" class="linkThree">Database Request Listings</a></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&nbsp;&nbsp;&nbsp;&raquo; <a href="msgDB.do?method=adminList" class="linkThree">Admin Request Listings</a></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&nbsp;&nbsp;&nbsp;&raquo; <a href="msgDB.do?method=myDatabase" class="linkThree">My Database Request</a></td>
								  </tr>
								  
								  <tr>
									<td height="20" class="menuLinkbg" align="left"><span class="textBold">&curren; HLCGroup </span></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="groupAction.do?method=initAdd" class="linkFour">Create HLCGroup</a></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="groupAction.do?method=initSearch" class="linkFour">View Groups</a></td>
								  </tr>
								   <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="myMsgAction.do?method=message" class="linkFour">My Message</a></td>
								  </tr>

								   <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="myMsgAction.do?method=HLCGroup" class="linkFour">My HLCGroup</a></td>
								  </tr>	
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="myMsgAction.do?method=myGroup" class="linkFour">My HLCGroup Member</a></td>
								  </tr>
								  <tr>
									<td height="20" class="menuLinkbg" align="left">&raquo; <a href="postAction.do?method=initAdd" class="linkFour">Post Messages</a></td>
								  </tr>	
								  </table>
							
							</td>
							<td class="menuTabRght"></td>
						  </tr>
						  <tr>
							<td class="menuTabLftBotCrnr"></td>
							<td class="menuTabBot"></td>
							<td class="menuTabRghtBotCrnr"></td>
						  </tr>
						</table>
					 

					</td>
				  </tr>
				</table>
