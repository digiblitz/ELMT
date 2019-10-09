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
<%@ page import="com.hlcmro.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>

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
					<td width="381" colspan="5" class="tblMainHead">
					Meetings: <span class="styleBoldTwo">Area Chair - Area Map Listing </span></td>
				  </tr>
				 
				 
				 <tr>
					<td>
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
				
						 
						 <!-- <tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							  <a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			 </td>
						   </tr>-->
						  <tr>
							<td height="27" colspan="2" class="tblRowHeadTwo">Area Chair ID </td>
							<td width="137" class="tblRowHeadTwo">Area Chair Name </td>
							<td width="40" class="tblRowHeadTwo"> Area</td>
							<td width="96" class="tblRowHeadTwo">Edit</td>
<!--							<td width="89" class="tblRowHeadTwo">Deactivate</td> -->
						  </tr>
						  
						  <%
						    ArrayList areaChairList = (ArrayList)request.getAttribute("allAreaChairList");
							if(areaChairList!=null && areaChairList.size()!=0){
								Iterator e = areaChairList.iterator();
								 while(e.hasNext()){
								 	HLCAreaChairsVO objACVO = (HLCAreaChairsVO)e.next();
									if(objACVO!=null){
										String mapAreaId = objACVO.getMapAreaId();
										String areaId = objACVO.getAreaId();
										String areaChairId = objACVO.getAreaChairId();
										String areaCode = objACVO.getAreaCode();
										String areaName = objACVO.getAreaName();
										String userId = objACVO.getUserId();
										String userCode = objACVO.getUserCode();
										String firstName = objACVO.getFirstName();
										String middleName = objACVO.getMiddleName();
										String lastName = objACVO.getLastName();
										String emailId = objACVO.getEmailId();
										if(areaName==null) areaName = "";
										if(firstName==null) firstName = "";
										if(middleName==null) middleName = "";
										if(lastName==null) lastName = "";
										String nameDet = firstName + " " + middleName + " " + 	lastName;																			
									  %>
									  <form name="frmAreaMapListing" method="post" action="EventOrgRenewal.do" >
									  <input type="hidden" name="eventProcess" value="initEditAreaChairArea"/>
									  <input type="hidden" name="mapAreaId" value="<%=mapAreaId%>"/>
									  <tr>
										<td height="30" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><a href="EventOrgRenewal.do?mapAreaId=<%=mapAreaId%>&eventProcess=showAreaChairDet" class="linkFive"><%=userCode%></a></td>
										<td bgcolor="#E3E1D2" class="alignCenter"><%=nameDet%></td>
										<td bgcolor="#E3E1D2" class="alignCenter"><%=areaName%></td>
										<td bgcolor="#E3E1D2" class="alignCenter"><input name="Submit2" type="submit" class="oneBtn" value="Edit" /></td>
<!--										<td bgcolor="#E3E1D2" class="alignCenter"><input name="Submit" type="submit" class="twoBtn" value="Deactivate" /></td> -->
									  </tr>
									  </form>
									  <%
									  }
						  			}
								}
								else{
								%>
								  <tr>
									<td height="26" colspan="6" bgcolor="#E3E1D2" class="alignCenter">No Records are Available.</td>
								  </tr>
								<%
								}
						  %>
						  

						  <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
						   </tr>-->
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