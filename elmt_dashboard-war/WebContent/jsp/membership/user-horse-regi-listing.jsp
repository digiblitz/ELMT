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
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.hlccommon.util.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script language="javascript">
function editBtn(hrseId){
	strURL = "./editHorse.do?process=desc&memid="+hrseId;
	window.location.href = strURL;
}
function viewBtn(hrseId){
	strURL = "./RegHorseListing.do?process=chngdesc&memid="+hrseId;
	window.location.href = strURL;
}
function postData(stat)
{
if(stat!=null)
{
	document.frmHorseMemberList.action="RegHorseListing.do?statSelect="+stat;
	document.frmHorseMemberList.submit();
}
else
{
	document.frmHorseMemberList.action="RegHorseListing.do?process=null";
	document.frmHorseMemberList.submit();
}
}
</script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<%
		int start = 1;
		int end = 1;
		int totalPage = 0;
		String rowCnt = (String)request.getAttribute("rCnt");
		String pageNo = (String) request.getAttribute("pNo");

		int rCnt = 0;
		int pNo = 0;
		if(rowCnt==null){
			rCnt = 0;
		}
		else{
			rCnt = Integer.parseInt(rowCnt);
			end = (rCnt/25);
			totalPage = (rCnt%25);
			if(totalPage>0){
				end = end  +1;
			}
		}

		if(pageNo==null){
			pNo = 1;
		}
		else{
			pNo = Integer.parseInt(pageNo);
		}
%>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
				<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				
					<table width="100%"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="5" class="tblMainHead">
						Membership: <span class="styleBoldTwo">User Non-Human  Registration Listing </span></td>
					  </tr>
					  <tr>
						<td colspan="5" class="tblDescrp">
							The Non-Humans registered  are listed as follows. <br />
							<br />
							To View Individual Non-Humans details, click on the <strong>'Non-Human Member </strong><strong>ID'</strong>.</td>
					  </tr>
						<tr>
						<td height="25" colspan="2" class="tableSpan"><span class="styleBoldTwo">Note:</span><span class="styleBoldOne"> DOR</span> <strong>- Date Of Registration </strong></td>
						</tr> 
					 <tr>
						<td valign="top">
						
							 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<%
							if(rCnt!=0){
							%>
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight">
							<span class="alignRight"><strong>Total Page(s): <%=end%></strong></span> &nbsp;
							<%
								for(int i = 1; i <=end; i++) { 
								String pageStartURL = "RegHorseListing.do?process=UserListing&pn=" + i ;
								if(pNo==i){
									out.println(i);
								}
								else{
								%>
								<a href="<%=pageStartURL%>" class="linkOne"><%=i%></a> 
								<%
								}
								if(i!=end){
									out.print("|");
								}
								}
								%>
							  </td>
						 </tr>
						   <%
						   }
						   %>
						  <tr>
								<td width="58"  height="27" class="tblRowHeadTwo">Horse Id</td>
								
								<td width="51"  class="tblRowHeadTwo">Horse Name</td>
								<td width="87"  class="tblRowHeadTwo">Registration Type</td>
								<!--<td  class="tblRowHeadTwo">Registered By </td>	-->							
								<td width="73"  class="tblRowHeadTwo">DOR </td>
								<td width="48"  class="tblRowHeadTwo">Status </td>
								<td width="50"  class="tblRowHeadTwo">View</td>
								<td width="46"  class="tblRowHeadTwo">Edit</td>
								<td width="78"  class="tblRowHeadTwo">Upgrade </td>								
								<!--
								<td width="112" class="tblRowHeadTwo">DownGrade</td>
								<td width="91" class="tblRowHeadTwo">Renew</td>
								-->
							   </tr>
						      
						<%
							SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
							ArrayList userInfo = (ArrayList) request.getAttribute("userInfo");
							if(userInfo!=null && userInfo.size()!=0){
							Iterator itr = 	userInfo.iterator();
							while(itr.hasNext()) {				   
								HLCHorseRegListVO AppHrListVO = (HLCHorseRegListVO) itr.next();								
								String horseMemberId = AppHrListVO.getHorseMemberId();
								String horseName = AppHrListVO.getHorseName();
								//String riderID = AppHrListVO.getRiderID();
								//String firstName = AppHrListVO.getFirstName();
								//String lastName = AppHrListVO.getLastName();
								String reqStatus = AppHrListVO.getStatusName();
					
								String ownerId = AppHrListVO.getOwnerId();
								Date dte = AppHrListVO.getAddDate();
								//String regBy = AppHrListVO.getRegisteredBy();
								String memberType = AppHrListVO.getMembershipTypeName();
								String date  = "N/G"; 
								if(dte!=null){
									date = sdf.format(dte);
								}
							//if(regBy==null){	regBy="";	}
								%>
						<form name="Approvedfrm" method="post" action="RegHorseListing.do?">
							 <tr>
								<td height="26" class="listCellBg"><a href="./RegHorseListing.do?process=chngdesc&memid=<%=horseMemberId%>"><%=horseMemberId%></a>  </td>
								<td class="listCellBg"> <a href="http://reports.useventing.com/ReportServer?/Public/Horse_Profile_Public&rs:Command=Render&rs:format=PDF&HORSEID=<%=horseMemberId%>" target="_blank"> <%=horseName%> </a></td>
								<td class="listCellBg"><%=memberType%></td>
								<td class="listCellBg"><%=date%></td>
								<td class="listCellBg"><span class="styleBoldOne"><%=reqStatus%></span></td>
								<td class="listCellBg"><input type="button" name="click2" value="View" class="oneBtn" onclick="viewBtn(<%=horseMemberId%>);"/></td>
								<%
								if(memberType.equalsIgnoreCase("Not Registered")){
								 %>
								<td class="listCellBg"><input type="button" name="click2" value="Edit" class="oneBtn" disabled="disabled" onclick="editBtn(<%=horseMemberId%>);"/></td>
								<%
								}
								else{
								%>
								<td class="listCellBg"><input type="button" name="click2" value="Edit" class="oneBtn"  onclick="editBtn(<%=horseMemberId%>);"/></td>
								<%
								}
								%>
								<td class="listCellBg">
								<%
								if(memberType.equalsIgnoreCase("Not Registered")){
								 %>
								 <input type="button" name="click" value="Register" class="twoBtn" onclick="location.href='HorseReg.do?process=newReg&horseMemberId=<%=horseMemberId%>'"/> 	
								 <%
								 }
								
								else if((!memberType.equalsIgnoreCase("Full")&&(!memberType.equalsIgnoreCase("Full status"))) && reqStatus.equalsIgnoreCase("Active"))
								 {
								%>
								<input type="submit" name="upgrade" value="Upgrade" class="twoBtn" />
								<%	} 
								else
								{	%>
								<input type="submit" name="click" value="Upgrade" class="twoBtn2" disabled="disabled" />
								<%	}	
								
							%>
								
								</td>
								<input type="hidden" name="process" value="UpgradeDet"/>
								<input type="hidden" name="memid" value="<%=horseMemberId%>"/>
								<!--<input type="hidden" name="ownerId" value="<%=ownerId%>"/> -->
						  </tr>
						  </form>
								<%	}	%>
								
								<%
								} 
							  if(rCnt!=0){
							%>
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight">
							<span class="alignRight"><strong>Total Page(s): <%=end%></strong></span> &nbsp;
							<%
								for(int i = 1; i <=end; i++) { 
									String pageStartURL = "RegHorseListing.do?process=UserListing&pn=" + i ;
									if(pNo==i){
										out.println(i);
									}
									else{
									%>
										<a href="<%=pageStartURL%>" class="linkOne"><%=i%></a> 
									<%
									}
									if(i!=end){
										out.println("|");
									}
								}
								%>
							  </td>
						 </tr>
						   <%
						   }
						   %>
						  </table>
						  
			</td>
					</tr>  
					</table>
					
					<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	  </table>
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
</body>
</html>