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

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<script>
	/*function getEventRegTypeName(selEventRegTypeId){
	//alert(selMemTypeId);
		frmMemberList.memProcess.value = "getEventRegType";
		frmMemberList.method="post";
		frmMemberList.action="./SetDateMaster.do?eventTypName="+selEventRegTypeId;
		frmMemberList.submit();
	}
	*/
	function validNumber(){
		var myindex=document.frmGrossAmtMast.noOfDays.selectedIndex;
		var eventRegMasterIndex=document.frmGrossAmtMast.eventRegMaster_sel.selectedIndex;
		  
		if (eventRegMasterIndex==0) {
			alert("\nYou must Select Meeting Type.");
			document.frmGrossAmtMast.eventRegMaster_sel.focus();
			return(false);   
		}
		
		if(document.frmGrossAmtMast.regDate.value==""){
			alert("Please select Registration Date");
			return false;
		}
		
		if(!(document.frmGrossAmtMast.regDate.value=="")){
	
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getYear();
	var nowMonth1=todayDate.getMonth();
	var nowMonth=1+nowMonth1;
	
	if(nowDate<10){
		nowDate="0"+nowDate;
	}

	if(nowMonth<10){
		nowMonth="0"+nowMonth;
	}

	if(!(document.frmGrossAmtMast.regDate.value=="")){
		strdate=document.frmGrossAmtMast.regDate.value;
		
		mm = Number(strdate.substring(0,2));
		dd = Number(strdate.substring(3,5));
		yyyy=(strdate.substring(6,document.frmGrossAmtMast.regDate.value.length));
		yyyy1=(Number(yyyy.length));

			if(yyyy<nowYear){
				alert("Enter Valid Registration Date.");
				document.frmGrossAmtMast.regDate.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter Valid Registration Date.");
				document.frmGrossAmtMast.regDate.focus();
				return false;
			}

			if (mm>12){
				alert("Enter Valid Registration Date.");
				document.frmGrossAmtMast.regDate.focus();
				return false;
			}

			if((dd<nowDate)&&(mm==nowMonth)){
				alert("Enter Valid Registration Date.");
				document.frmGrossAmtMast.regDate.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter Valid Registration Date.");
				document.frmGrossAmtMast.regDate.focus();
				return false;
			}
		}
	}

	if(document.frmGrossAmtMast.meeDate.value==""){
			alert("Please select Meeting Date");
			return false;
		}

	if(!(document.frmGrossAmtMast.meeDate.value=="")){
		strdate1=document.frmGrossAmtMast.meeDate.value;
		mm1 = Number(strdate1.substring(0,2));
		dd1 = Number(strdate1.substring(3,5));
		yyy=(strdate1.substring(6,document.frmGrossAmtMast.meeDate.value.length));
		yyy1=(Number(yyy.length));

			if(yyy<nowYear){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}

			if((yyy1==nowYear)&&(mm1<nowMonth)){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}

			if (mm1>12){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}

			if((dd1<nowDate)&&(yyy<nowYear)&&(mm1==nowMonth)){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}

			else if(dd1>31){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}
		}


	if(!(document.frmGrossAmtMast.meeDate.value=="")){
		
		strdate2=document.frmGrossAmtMast.meeDate.value;
		mm2 = Number(strdate2.substring(0,2));
		dd2 = Number(strdate2.substring(3,5));
		yy = Number(strdate2.substring(6,document.frmGrossAmtMast.meeDate.value.length));
	
		strdate3=document.frmGrossAmtMast.regDate.value;
		mm3 = Number(strdate3.substring(0,2));
		dd3 = Number(strdate3.substring(3,5));
		y = Number(strdate3.substring(6,document.frmGrossAmtMast.regDate.value.length));
		
			if(yy<y){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}
			
			if((yy==y)&&(mm2<mm3)){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}
			
			
			if((mm2==mm3)&&(yy<y)&&(dd2<dd3)){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}
			if((mm2==mm3)&&(yy==y)&&(dd2<dd3)){
				alert("Enter Valid Meeting Date.");
				document.frmGrossAmtMast.meeDate.focus();
				return false;
			}
		}

		
		
		
		if (myindex==0) {
			alert("\nYou must Select a number.");
			document.frmGrossAmtMast.noOfDays.focus();
			return(false);   
		}

		return true;
	}
</script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->

</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
<%
String  eventRegTypId = (String)request.getAttribute("eventRegTypId");
if(eventRegTypId==null || eventRegTypId.equals("")){
eventRegTypId="";
}
%>

  <tr>
    <td class="alignTop"><!-- HEADER STARTS HERE -->
        <%@ include file = "../../include/header.jsp" %>
        <!-- HEADER ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="infoBar"><!-- INFO BAR STARTS HERE -->
        <%@ include file = "../../include/infobar.jsp" %>
        <!-- INFO BAR ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="tableCommonBg"><table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
      <tr>
        <td width="230" class="menuTablePad"><!-- LEFT MENU STARTS HERE -->
              <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
              <!-- LEFT MENU ENDS HERE -->
        </td>
        <td width="500" class="subDeptTablePad">
		<!-- CONTENTS START HERE -->
              <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Set a Date for Meeting </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
						
					</td>
				  </tr>
				  <tr>
					<td>
					
						<form name="frmGrossAmtMast" id="myform" action="./SetDateMaster.do" onsubmit="return validNumber();">
						<input name="adminSetDateProcess" type="hidden" value="setDateForEvent">
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
								  <tr>
									<td colspan="2" class="tblRowHead"><span class="tblMainHead"> <span class="styleBoldTwo">Set a Date for Meeting</span></span> :</td>
								  </tr>
								   <tr>
									 <td class="tableLeft">Meetings Type  :</td>
									 <td class="tableRight">
										<select name="eventRegMaster_sel" id="eventRegMaster_sel" class="selectboxOne">
											<option value="">Select One</option>
											<% 
					String str1[] = null;
					Vector eventRegMasterVect=new Vector();
					  eventRegMasterVect=(Vector)request.getAttribute("eventRegMasterVect");
  					  System.out.println("vector1:"+eventRegMasterVect);
					  Enumeration enm=eventRegMasterVect.elements();
					  
					  while(enm.hasMoreElements()){
							String[] s = (String[])enm.nextElement();
							for (int i=0;i<s.length; i++) {
								System.out.println("Content : "+s[i]);
							}
							String eventRegMasterId = s[0];


							String eventRegMasterName = s[1];
							
							System.out.print(" eventRegMasterId:  "+eventRegMasterId);
							System.out.print(" eventRegMasterName:  "+eventRegMasterName);
							
							%>
							
					  
							 <option value="<%=eventRegMasterId %>"><%=eventRegMasterName%></option>
						<%
						}
                       
							%>
										</select>
									 <span class="asterisk">*</span>					 </td>
								  </tr>
								  <tr>
									 <td class="tableLeft">Registration Date :</td>
									 <td class="tableRight">
										<input type="text" name = "regDate" class="textboxOne" size="18" readonly/>	
										<a href="javascript:cal1.popup();">
											<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">
										</a> <span class="asterisk">*</span>
									</td>
								  </tr>
								  <tr>
									 <td class="tableLeft">Meeting Date :</td>
									 <td class="tableRight">
										<input type="text" name = "meeDate" class="textboxOne" size="18" readonly/>	
										<a href="javascript:cal2.popup();">
											<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">
										</a> <span class="asterisk">*</span>
									</td>
								  </tr>
								  <tr>
									<td class="tableLeft">No. Of Days :</td>
									<td class="tableRight">
													<select name="noOfDays" id="select7" class="selectboxOne">
														<option selected="selected">Nos.</option>
														<option>01</option>
														<option>02</option>
														<option>03</option>
														<option>04</option>
														<option>05</option>
														<option>06</option>
														<option>07</option>
														<option>08</option>
														<option>09</option>
														<option>10</option>
														<option>11</option>
														<option>12</option>
														<option>13</option>
														<option>14</option>
														<option>15</option>
														<option>16</option>
														<option>17</option>
														<option>18</option>
														<option>19</option>
														<option>20</option>
														<option>21</option>
														<option>22</option>
														<option>23</option>
														<option>24</option>
														<option>25</option>
														<option>26</option>
														<option>27</option>
														<option>28</option>
														<option>29</option>
														<option>30</option>
														<option>31</option>
													 </select>
									</td>
								  </tr>
								  
				
								  <tr>
									<td class="tableLeft">&nbsp;</td>
									<td class="tableRight">
									<input type="submit" value="Set Date" class="gradBtn"  />
									&nbsp;
									<input type="button" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);" />					</td>
								  </tr>
							</table>
							
						</form>
					</td>
				  </tr>
				  
				  <tr>
					<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
				  </tr>
				</table>
          <!-- CONTENTS END HERE -->
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="footerBg"><!-- FOOTER STARTS HERE -->
        <%@ include file = "../../include/footer.jsp" %>
        <!-- FOOTER ENDS HERE -->
    </td>
  </tr>
</table>
<script language="javascript">
	

	var cal1 = new calendar2(document.forms['frmGrossAmtMast'].elements['regDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2= new calendar2(document.forms['frmGrossAmtMast'].elements['meeDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;

</script>	
</body>
</html>
