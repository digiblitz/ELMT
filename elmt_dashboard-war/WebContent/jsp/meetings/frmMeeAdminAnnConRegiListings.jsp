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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.*" %>
<%@ page import ="com.hlcmeeting.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript">

function postData(){

	/*frmMeeDispLst.memProcess.value = "adminViewList";
    frmMeeDispLst.method="post";
    frmMeeDispLst.action="AnnualRegList.do";
    frmMeeDispLst.submit();*/
		
	var stat = document.frmMeeDispLst.status.value;
	strURL = "./AnnualRegList.do?memProcess=adminViewList&status="+stat;
	window.location.href = strURL;	
	
}
	function postDataForPagination() {
		var pn = document.frmPaginationTop.pn.value;
		var stat = document.frmMeeDispLst.status.value;
		strURL = "./AnnualRegList.do?&memProcess=adminViewList&pn="+pn+"&status="+stat;
		window.location.href = strURL;	
	
	//var selMemTypeId = document.frmmemRegiList.selMemTypeId.value;
	}
	function postDataForPagination1() {
		var pn = document.frmPaginationBottom.pn.value;
		var stat = document.frmMeeDispLst.status.value;
		strURL = "./AnnualRegList.do?&memProcess=adminViewList&pn="+pn+"&status="+stat;
		window.location.href = strURL;	
	}

</script>

<%
try{
String  status = (String)request.getAttribute("status");
if(status!=null && status.trim().length()!=0){
	status=status;
}else{
	status = "";
}
%>

</script>
 
</head>

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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
					<span class="styleBoldTwo">Annual Meeting &amp; Convention List Page</span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">&nbsp;</td>
				  </tr>
				 <tr>
					<td valign="top">
					
						<table border="0" cellpadding="0" align="center" cellspacing="1" >
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<!--<th width="125" height="20" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
									<td colspan="3" valign="middle" class="alignLeft">
									<span class="legendTwo">&nbsp;</span> &nbsp;View.									</td>-->
									<!--
									<td colspan="3" valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
									<span class="legendOne">&nbsp;</span> &nbsp;Delete.
									</td>
									-->
							      </tr>
								</table>
								
							</td>
						 </tr>
						 		  <tr>
		   	<td colspan="5" class="tableSpan">
			<form name="frmMeeDispLst" >
			<input name="memProcess" type="hidden" value="">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				   <tr>
					  <th width="48" height="20" valign="top" class="alignLeft"> Status </th>
					  <td width="206" colspan="3" valign="middle" class="alignLeft">
					  <select name="status" class="selectboxOne" onchange="postData();">
                        <option selected="selected" value="">Select One</option>
						<%
							if(status!=null && status.trim().length()!=0 && status.equals("Pending")){
						%>
                        <option value="Pending" selected="selected" >Pending</option>
						<%
							}
							else{
						%>
						 	<option value="Pending" >Pending</option>
						<%
							}
							if(status!=null && status.trim().length()!=0 && status.equals("Registered")){
						%>
						<option value="Registered" selected="selected" >Registered</option>
						
						<%
						}
						else{
						%>
							<option value="Registered" >Registered</option>
						<%
						}
						if(status!=null && status.trim().length()!=0 && status.equals("Rejected")){
						%>
						
						<option value="Rejected" selected="selected" >Rejected</option>
						<%
						}
						else{
						%>
						<option value="Rejected" >Rejected</option>
						<%
						}
						%>
                      </select></td>
				  </tr>
				</table>
			  </form>
			</td>
		   </tr>
		   <form name="frmPaginationTop" id="frmPaginationTop" method="post" action="AnnualRegList.do">


		  	 <%
							int start = 1;
							int end = 1;
							int totalPage = 0;
							String rowCnt = (String)request.getAttribute("rCnt");
							String pageNo = (String) request.getAttribute("pNo");
							
							int rCnt = 0;
							int pNo = 0;

							if(rowCnt!=null && rowCnt.trim().length()!=0){
								rCnt = Integer.parseInt(rowCnt);
								end = (rCnt/50);
								totalPage = (rCnt%50);
								if(totalPage>0){end = end  +1;}
							}else{
								rCnt = 0;
							}

							if(pageNo!=null && pageNo.trim().length()!=0){
								pNo = Integer.parseInt(pageNo);
							}else{
								pNo = 0;
							}
							
							if(rCnt!=0){
							%>
							<tr>
							<td  colspan="7"  bgcolor="#ffffff" class="alignRight">
							<span class="alignRight"><strong>View Page(s): </strong></span> &nbsp;
							<select name="pn" id="pn" class="selectboxOne" onchange="postDataForPagination();">
							<%
								for(int i = 1; i <=end; i++) { 
									if(pNo==i){
									%>
										<option selected="selected" value="<%=i%>"><%=i%></option>
									<%
									}
									else{
									%>
										<option value="<%=i%>"><%=i%></option>
									<%
									}
								}
								%>
							  </select>
							  <strong> of <%=end%> </strong>
							  </td> 
						 </tr>
						   <%
						   }
						   %>
		  		  </form>
		   
						  <tr>
							<td width="96" class="tblRowHead">First Name </td>
							<td width="92" class="tblRowHead">Last Name </td>
							<td width="116" class="tblRowHead">Request Date </td>
							<td width="116" class="tblRowHead">Request Status </td>
							<td width="53" class="tblRowHead">View</td>
						   </tr>
						  

					<%
		   		
					 ArrayList memberDetail=(ArrayList)request.getAttribute("AnnualList");
					 SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					 if(memberDetail!=null && memberDetail.size()!=0){
						Iterator  enm1 = memberDetail.iterator();
						while(enm1.hasNext()){
							HLCAnnualRegistrationDetailVO objRegDet = (HLCAnnualRegistrationDetailVO) enm1.next();
							String annualMeetingId = objRegDet.getAnnualMeetingId();
							String firstName = objRegDet.getFirstName();
							String lastName = objRegDet.getLastName();
							String reqStatus = objRegDet.getRequestStatus();
							Date today = objRegDet.getAddDate();
		   %>
		   <!-- {annualMeetingId,badgeInfo,firstName,lastName,areaName,requestStatus,addDate,email};  -->

						<form name="frmDisplayAdminList" method="post" action="AnnualRegList.do">		
						<input name="memProcess" type="hidden" value="dispViewDetail">
						<input name="annualMeetingId" type="hidden" value="<%=annualMeetingId%>">
						
						  <tr>
							<td class="listCellBg"><a href="AnnualRegList.do?memProcess=statusShow&annualMeetingId=<%=annualMeetingId%>"><%=firstName%></a> </td>
							<td class="listCellBg"><%=lastName%> </td>
							
							<td class="listCellBg"><%=sdf.format(today)%></td>
							<td class="listCellBg">
				<%if(!reqStatus.equals("Registered")){%>
				<input name="Submit2" type="button" class="oneBtn" value="<%=reqStatus%>" onclick="javascript:location.href='AnnualRegList.do?memProcess=statusChange&annualMeetingId=<%=annualMeetingId%>'"/>
				<%}
				else{
				out.println(reqStatus);
				}
				%>
			</td>
							<td class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="View" /></td>
						   </tr>
						</form>
		   
					      <%	
									}
									}else {
								%>
								
								<tr>
								  <th height="25" colspan="5">No Records are available. </th>
								</tr>
								<%}
							if(rCnt!=0){
							%>
							<form name="frmPaginationBottom" id="frmPaginationBottom" action="AnnualRegList.do" >
							<tr>
							<td  colspan="7"  bgcolor="#ffffff" class="alignRight">
							<span class="alignRight"><strong>View Page(s): </strong></span> &nbsp;
							<select name="pn" id="pn" class="selectboxOne" onchange="postDataForPagination1(this.value);">
							<%
								for(int i = 1; i <=end; i++) { 
									if(pNo==i){
									%>
										<option selected="selected" value="<%=i%>"><%=i%></option>
									<%
									}
									else{
									%>
										<option value="<%=i%>"><%=i%></option>
									<%
									}
								}
								%>
							  </select>
							  <strong> of <%=end%> </strong>
							  </td> 
						 </tr>
						 </form>
						   <%
						   }
						   }catch(Exception e){
						   		e.printStackTrace();
						   }
								%>

						  <tr>
							<td height="19" colspan="7">&nbsp;</td>
						   </tr>
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
