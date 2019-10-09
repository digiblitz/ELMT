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
<%@page import="com.hlccommon.util.HLCMenuListVO"%>
<%@ page import="java.util.*"%>

<%@ page import="java.util.*"%>

<link rel="stylesheet" type="text/css" href="pro_dropdown_2/pro_dropdown_2.css" />
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<link href="ttip2.css" rel="stylesheet" type="text/css"/>
<script src="pro_dropdown_2/stuHover.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<%
String sessUserId = (String) session.getAttribute("userId");
String sessId = (String) session.getAttribute("sessionId");
//System.out.println("sessId=="+sessId);
if(session.isNew() || sessId == null){


	response.sendRedirect("login.jsp");

}
String sessionRoleName = (String)session.getAttribute("roleName");
String sessionRoleId = (String)session.getAttribute("roleId");
String userName=(String)session.getAttribute("userName");
String userPassword=(String)session.getAttribute("userPassword");

session.setAttribute("userName",userName);
session.setAttribute("userPassword",userPassword);

if(sessionRoleId==null)
	sessionRoleId = "";
if(sessionRoleName==null)
	sessionRoleName = "General";
%>
<script language="javascript">
	function headRoleCheck(){
		if(document.frmHead.headRoleId.value==""){
			alert("Select Any One Role");
			document.frmHead.headRoleId.focus();
			return false;
		}
		return true;
	}
	  function noBack() {  
		 
		window.history.forward(); }

    noBack();
    window.onload = noBack;
    window.onpageshow = function (evt) { if (evt.persisted) noBack(); }
    window.onunload = function () { void (0); }
  </script>

<%if(sessId != null){
	%>
<table width="1024" border="0" align="center" cellpadding="0" cellspacing="0" id="headerTab">

		  <tr>
		    <td class="logoRow"  bgcolor="#E1F6FB" >
				<table width="758" border="0" align="center" cellpadding="0" cellspacing="0" id="logoTimeHolderTab">
				  <tr>
					<td>
                                            <!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                            <%--<img src="<%=request.getContextPath()%>/images/title.jpg"  />--%>
                                            <img src="<%=request.getContextPath()%>/images/banner_final_landscape.jpg"  />
					<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<ul>
							<!-- CSS Tabs -->
								<%
								ArrayList headerRoleList = (ArrayList)session.getAttribute("DBInitialBoard");
								int tabId = 0;
								int tabSize = 0;
								String newRoleName ="";
								if(headerRoleList!=null && headerRoleList.size()!=0){
									tabSize = headerRoleList.size();
									Iterator itRoleList = headerRoleList.iterator();
									String sessHeadRoleId = (String)session.getAttribute("roleId");
									if(sessHeadRoleId==null) sessHeadRoleId="";
									while(itRoleList.hasNext()){
										String strRolelist[]= (String[])itRoleList.next();
										String heFirstRoleId = strRolelist[2];
										String heFirstRoleName = strRolelist[3];
										tabId++;
										if(heFirstRoleId.equals(sessHeadRoleId)){
											 newRoleName = heFirstRoleName;

									%>

									<!--li id="current"><a href="<%=request.getContextPath()%>/roles.do?roleProcess=selectEntityDashboard&headRoleId=<%=heFirstRoleId%>"><span><%=heFirstRoleName%></span></a></li-->

									<%
									}
									else{
									%>
									<!--li><a href="<%=request.getContextPath()%>/roles.do?roleProcess=selectEntityDashboard&headRoleId=<%=heFirstRoleId%>"><span><%=heFirstRoleName%></span></a></li-->
									<%
									}
									}
								}

								session.setAttribute("roleName", newRoleName);
								%>
							</ul>
						</div>
					</td>
				  </tr>
				</table>
			</td>
	      </tr>
		  <tr>
		    <td height="23" bgcolor="#B2C7DA" valign="bottom">

				<!--TABS START HERE-->
				<div id="tabsF">
					<ul>
						<%
						ArrayList headEntityList = (ArrayList)session.getAttribute("DBEntityList");
						if(headEntityList!=null && headEntityList.size()!=0){
							Iterator itEntList = headEntityList.iterator();
							String sessHeadEntityId = (String)session.getAttribute("entityId");

if(sessHeadEntityId==null) sessHeadEntityId="";
							while(itEntList.hasNext()){
								String strRolelist[]= (String[])itEntList.next();
								String heRoleId = strRolelist[1];
								String heRoleName = strRolelist[3];
								String heEntityId = strRolelist[2];
								String heEntityName = strRolelist[4];
								String heEntityUrl = strRolelist[5];
							if(heEntityUrl==null){
								
								
						  	if(heEntityId.equals(sessHeadEntityId)){
						  						  			
						%>

						<li id="current2"><a href="<%=request.getContextPath()%>/roles.do?roleProcess=selectEntPrivePermDashboard&headEntityId=<%=heEntityId%>&headRoleId=<%=heRoleId%>&headEntityName=<%=heEntityName%>&headRoleName=<%=heRoleName%>"><span><%=heEntityName%></span></a></li>
							<%
							}
							else{
							%>
							<li><a href="<%=request.getContextPath()%>/roles.do?roleProcess=selectEntPrivePermDashboard&headEntityId=<%=heEntityId%>&headRoleId=<%=heRoleId%>&headEntityName=<%=heEntityName%>&headRoleName=<%=heRoleName%>"><span><%=heEntityName%></span></a></li>
							<%
							}
						  	}else{%>
						  		
						  		<li><a href="<%=request.getContextPath()+heEntityUrl%>"><span><%=heEntityName%></span></a></li>
						  	<%}
							
							}
						}
						%>

					</ul>
				</div>

				<!--TABS END HERE-->

			</td>
	      </tr>

		  <tr><td style="border-bottom:1px solid #999;"><!-- DO NOT DELETE THIS ROW --></td></tr>
		</table>



<%

ArrayList leftMenuList1 = (ArrayList)session.getAttribute("DBLeftPrivlegeList");
 java.util.HashMap leftSubMenuList = new HashMap();
leftSubMenuList=(java.util.HashMap)session.getAttribute("DBLeftSubPrivlegeList");
String templeftPrivilegeName="";
int ileftSubmenulistcount=0;
String leftAccessNameHead ="";
String leftAccessURLHead = "";
String strShowPrevillege[]=new String[50];
String strShowAccessName[][]=new String[50][50];
String strShowAccessURL[][]=new String[50][50];
String strShowSubAccessId[][][]=new String[50][50][50];
String strShowSubAccessURL[][][]=new String[50][50][50];
int strForJsetfromI[]=new int[50];
int iWhileloopCount=0;
int initPrevillegeCount=0;
int increPrevillegeCount=0;
int iAccessnamecount=0;
String leftPrivilegeNameHead ="";
String leftPrivilegeIdHead ="";
String leftgetPermissionIdHead="";
String leftSubAccessName = "";
String leftSubPermissionId="";
String leftgetPermissionNameHead="";
String leftSubQueryPermissionId ="";
String leftSubAccessURL ="";
int tempiAccessnamecount=0;
int subPermCnt = 0;

if(leftSubMenuList != null){//if21 block
	ileftSubmenulistcount=leftSubMenuList.size();
}//if21 block end

//////System.out.println("DBLeftSubPrivlegeList..."+ileftSubmenulistcount);
ArrayList arrLeftSubMenuList = new ArrayList();
 int iRealincrePrevillegeCount=0;
 int iRealiAccessnamecount=0;

 String leftgetRoleId="";


	  /*=================================
	  */
if(leftMenuList1!=null && leftMenuList1.size()!=0){//if0 block
  Iterator itMenu1 = leftMenuList1.iterator();


	while(itMenu1.hasNext()){//while0 block
		iWhileloopCount++;

		HLCMenuListVO leftMenuVO = (HLCMenuListVO) itMenu1.next();
		if(leftMenuVO.getRoleId()!=null && leftMenuVO.getEntityId()!=null){//if1 block
			 leftPrivilegeNameHead=leftMenuVO.getPrivilegeName();
			 leftPrivilegeIdHead=leftMenuVO.getPrivilegeId();
			 leftgetRoleId=leftMenuVO.getRoleId();
			  leftgetPermissionIdHead=leftMenuVO.getPermissionId();
			  leftgetPermissionNameHead=leftMenuVO.getPermissionName();
		}//if1 block end
		arrLeftSubMenuList=(ArrayList)leftSubMenuList.get(leftgetRoleId);
		int tempsize=0;
		if(arrLeftSubMenuList!=null){
			tempsize=arrLeftSubMenuList.size();
		}

		if(initPrevillegeCount==0){//if2 block
			templeftPrivilegeName=leftPrivilegeNameHead;
			strShowPrevillege[increPrevillegeCount]=leftPrivilegeNameHead;
			//////System.out.println("strShowPrevillege["+ increPrevillegeCount+"]====================>"+strShowPrevillege[increPrevillegeCount]);
		}//if2 block end
		leftAccessNameHead= leftMenuVO.getAccessName();
		leftAccessURLHead=leftMenuVO.getAccessUrl();
		iAccessnamecount++;
		if(templeftPrivilegeName.equals(leftPrivilegeNameHead)){//if0 block

		}else{//else0 block

			templeftPrivilegeName=leftPrivilegeNameHead;
			increPrevillegeCount++;
			strShowPrevillege[increPrevillegeCount]=leftPrivilegeNameHead;
			initPrevillegeCount=0;
			iAccessnamecount=0;
		}//else0 end


		strShowAccessName[increPrevillegeCount][iAccessnamecount]=leftAccessNameHead;
		iRealincrePrevillegeCount=increPrevillegeCount;
		iRealiAccessnamecount=iAccessnamecount;
		strShowAccessURL[increPrevillegeCount][iAccessnamecount]=leftAccessURLHead;
		/*====================*/
		////System.out.println("strShowAccessName["+increPrevillegeCount+"]["+iAccessnamecount+"]====================>"+strShowAccessName[increPrevillegeCount][iAccessnamecount]);
		////System.out.println("strShowAccessURL["+increPrevillegeCount+"]["+iAccessnamecount+"]====================>"+strShowAccessURL[increPrevillegeCount][iAccessnamecount]);

		if(arrLeftSubMenuList!=null && arrLeftSubMenuList.size() >0){//if15 block
			Iterator itSubMenu = arrLeftSubMenuList.iterator();
			int incresubpermissionid=0;
			while(itSubMenu.hasNext()){//while2 block
				//////System.out.println("inside calling  while2.");
				HLCMenuListVO leftSubMenuVO = (HLCMenuListVO) itSubMenu.next();
				leftSubQueryPermissionId= leftSubMenuVO.getPermissionId();
				leftSubPermissionId= leftSubMenuVO.getSubPermissionId();
				leftSubAccessName=leftSubMenuVO.getAccessName();
				leftSubAccessURL=leftSubMenuVO.getAccessUrl();

				if(leftSubQueryPermissionId.equals(leftgetPermissionIdHead)){//if16 block start
					strShowSubAccessId[increPrevillegeCount][iAccessnamecount][incresubpermissionid]=leftSubPermissionId;
					strShowSubAccessURL[increPrevillegeCount][iAccessnamecount][incresubpermissionid]=leftSubAccessURL;
					//////System.out.println("strShowSubAccessId["+increPrevillegeCount+"]["+iAccessnamecount+"]["+incresubpermissionid+"]====================>"+strShowSubAccessId[increPrevillegeCount][iAccessnamecount][incresubpermissionid]);

					//////System.out.println("strShowSubAccessURL["+increPrevillegeCount+"]["+iAccessnamecount+"]["+incresubpermissionid+"]====================>"+strShowSubAccessURL[increPrevillegeCount][iAccessnamecount][incresubpermissionid]);
					//////System.out.println("while2 ======iRealiAccessnamecount==="+iAccessnamecount);
					incresubpermissionid++;
				}//if16 block end
			 }//while2 block end
			 incresubpermissionid=0;
		}//if15 block end
		tempiAccessnamecount=iAccessnamecount;
		strForJsetfromI[increPrevillegeCount]=tempiAccessnamecount;
			/*======================*/
		if(initPrevillegeCount==0){//if11 block
			initPrevillegeCount++;
		}//if11 block end
	}//while0 block end
}//if0 block end

%>

<ul id="nav">
<%
	for(int i=0; i<=iRealincrePrevillegeCount;i++){//for0 start
		if(strShowPrevillege[i]!=null){
				tempiAccessnamecount=strForJsetfromI[i];
				if(iRealincrePrevillegeCount==0 && tempiAccessnamecount==1){
					//System.out.println("strShowAccessURL[i][0]----------"+strShowAccessURL[i][1]);
%>		
<li class="top">
		<a href="javascript:void(0);" onclick="javascript:location.href='<%=strShowAccessURL[i][1]%>'" class="top_link"><span class="down1"><%=strShowPrevillege[i]%></span></a>
		</li>
			<%}else{
	       System.out.println("else block");%>
<li class="top">
		<a href="javascript:void(0)" class="top_link"><span class="down"><%=strShowPrevillege[i]%></span></a>
			<ul class="sub">
<%
			tempiAccessnamecount=strForJsetfromI[i];
			for(int j=0;j<=tempiAccessnamecount;j++){//for1 block
				////System.out.println("===================["+i+"]["+j+"]====="+strShowAccessName[i][j]);
				if(strShowAccessURL[i][j]!= null && strShowAccessName[i][j] != null){
					if(strShowAccessName[i][j].equals("")){//if12 block start
						////System.out.println("if block empty ===================");
					}else{//else12 blcok
					////System.out.println("if block ===================["+i+"]["+j+"]====="+strShowAccessName[i][j]);
%>
				  <li><a href="javascript:void(0);" onclick="javascript:location.href='<%=strShowAccessURL[i][j]%>'"><%=strShowAccessName[i][j]%></a></li>

<%					}//if else12 block end
				}//if5 block end
                else{//else5 block
					if(strShowAccessName[i][j] != null && strShowAccessURL[i][j]==null && (strShowSubAccessId[i][j][0]!=null || strShowSubAccessId[i][j][1]!=null)){//if11 block
						////System.out.println("else if ===================["+i+"]["+j+"]====="+strShowAccessName[i][j]);
						String oldStrAccessname="";
						String newStrAccessname="";
%>
						<li> <a href="javascript:void(0)" class="fly"><%=strShowAccessName[i][j]%></a>
					        <ul>
							<%
								for(subPermCnt=0;subPermCnt<3;subPermCnt++){//for10 block start
									newStrAccessname=strShowSubAccessId[i][j][subPermCnt];
									if(newStrAccessname!=null)
	{
									if(subPermCnt==0){//if23 block
										oldStrAccessname=newStrAccessname;
%>

					              <li><a href="<%=strShowSubAccessURL[i][j][subPermCnt]%>"><%=newStrAccessname%></a></li>

<%
	                         ////System.out.println("else if ===================["+i+"]["+j+"]["+subPermCnt+"]====="+strShowSubAccessURL[i][j][subPermCnt]+newStrAccessname);
									}//if23 block end
									if(!newStrAccessname.equalsIgnoreCase(oldStrAccessname)){
											oldStrAccessname=newStrAccessname;
											%>

					              <li><a href="<%=strShowSubAccessURL[i][j][subPermCnt]%>"><%=newStrAccessname%></a></li>
<%
									   ////System.out.println("else if 1===================["+i+"]["+j+"]["+subPermCnt+"]====="+strShowSubAccessURL[i][j][subPermCnt]+newStrAccessname);
									}
	}


%>

					              <!--li><a href="<%=strShowSubAccessURL[i][j][subPermCnt]%>"><%=newStrAccessname%></a></li-->
								 <%}//for10 block end%>
							</ul>
					  </li>
<%
					}//if11 block end
				}//else5 block end
			}//for1 block end
}//else
%>			</ul>

<%           
			}
		}//for0 block end
%>
	</li>
</ul><%}%>
