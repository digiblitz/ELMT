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
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPEditAssess.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong>Meetings: </strong> <span class="styleBoldTwo">Application for ICP Assessment</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">   
					</td>
				  </tr>
				  <tr>
					<td>
	
					<form name="frmMeeICPAssessment" id="myform" method="post" action="ICPOrgListApp.do" onsubmit="return myvalidate();">
					
							
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<!--<tr>
							<td colspan="2">
								<!-- TABS START HERE  									
								<table cellpadding="0" cellspacing="0" border="0" class="container">
									<tr>
										<td id="tabData1" class="tabHighlight" onclick="naviTab('1')">
											<a id="link1" href="javascript:void(0);" class="active"><span class="tabHead">Part A</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData2" class="tabLowlight" onclick="naviTab('2')">
											<a id="link2" href="javascript:void(0);" class="inactive"><span class="tabHead">Part B</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData3" class="tabLowlight" onclick="naviTab('3')">
											<a id="link3" href="javascript:void(0);" class="inactive"><span class="tabHead">Part C</span></A>
										</td>
									</tr>	
								</table>
								<!-- TABS END HERE  
							</td>
						</tr>-->
						 
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<!--++++++++++++++++++++ Part 1 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								 <tr> 
									<td colspan="2" class="tblRowHead">ICP Assessment Details:</td>
								  </tr>
								   
								  <tr>
                                                                             <%
																				Vector dispReg=new Vector();
                                                                                dispReg=(Vector)request.getAttribute("dispReg");
                                                                                System.out.println("disp size :"+dispReg.size());
                                                                                String[] icpVal =null;
                                                                                if(dispReg!=null && dispReg.size()!=0) {
                                                                                    Enumeration en=dispReg.elements();
                                                                                    
                                                                                    while(en.hasMoreElements()) {
                                                                                       icpVal = (String[])en.nextElement();
                                                                                        String[] dat=icpVal[2].split("-");
																						String datOrd=dat[0]+"/"+dat[1]+"/"+dat[2];
                                                                                        %>
                                                                      <input type="hidden" name="process" value="update" />
                                                                      <input type="hidden" name="mid" value="<%=icpVal[0]%>" />
                                                                      
                                                                      <td class="tableLeft">Assesmant Level:</td>
                                                                      <td class="tableRight"><input type="text" value="<%=icpVal[1]%>" name="assesLevel" id="assesmant" class="textboxOne" size="25" />
                                                                      <span class="asterisk">*</span></td>
                                                                  </tr>
                                                                  <tr>
                                                                      <td class="tableLeft">Date:</td>
                                                                      <td class="tableRight"><input name="dat" type="text" value="<%=datOrd%>" readonly="true" class="textboxOne" id="date" size="16" />
                                                                          <span class="asterisk">*</span><a href="javascript:cal1.popup();"> <img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> </td>
                                                                          </tr>
                                                                                       <tr> 
									<td class="tableLeft">No. of Days:</td>
									<td class="tableRight">
									<select name="days" id="select71" class="selectboxOne">
									<% for(int i=1;i<31;i++){
										if(i==Integer.parseInt(icpVal[3])){	%>
										<option value="<%=icpVal[3]%>" selected="selected"><%=icpVal[3]%></option>
										<% }else{%>
										<option value="<%=i%>"><%=i%></option>
										<% } }%>
									 </select> 
									  <span class="asterisk">*</span></td>
								  </tr>
								   <tr>
                                     <td class="tableLeft">Area:</td>

                                     <td class="tableRight">
                                       <select name="hlcArea" id="select72" class="selectboxOne">
									   
                                        <!-- <option value="<%=icpVal[4]%>" selected="selected"><%=icpVal[26]%></option>-->
                                         <%
										Vector area=new Vector(); 
                                        area=(Vector)request.getAttribute("area");
										if(area!=null && area.size()!=0){
                                              Enumeration enu=area.elements();    
												while(enu.hasMoreElements()){
													   String[] icpArea = (String[])enu.nextElement();
														if(icpVal[4].equals(icpArea[2]))
														{	%>
													 <option value="<%=icpArea[0]%>" selected="selected"> <%=icpArea[2]%></option>												
													 <% } else	{	%>
                                         <option value="<%=icpArea[0]%>"><%=icpArea[2]%></option>
                                         <%}}}%>
                                       </select>
                                       <span class="asterisk">*</span></td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">Location:</td>
                                     <td class="tableRight"><input type="text" value="<%=icpVal[5]%>" name="location" id="location" class="textboxOne" size="25" />
                                         <span class="asterisk">*</span></td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">Host ID:</td>
                                     <td class="tableRight"><input name="hostid" id="hostid" value="<%=icpVal[7]%>" type="text" class="textboxOne" size="25"  onblur="riderDetails();" />
                                         <span class="asterisk">*</span></td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">First Name:</td>
                                     <td class="tableRight"><input type="text" name="fname" value="<%=icpVal[27]%>" id="firstName" readonly="true" class="textboxOne" size="25" />
                                         <span class="asterisk">*</span></td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">Last Name:</td>
                                     <td class="tableRight"><input type="text" name="lname" value="<%=icpVal[28]%>" id="lastName" readonly="true" class="textboxOne" size="25" />
                                         <span class="asterisk">*</span></td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">Address:</td>
                                     <td class="tableRight"><input name="addr" type="text" value="<%=icpVal[30]%>" id="address1" readonly="true" class="textboxOne" size="25" />
                                         <span class="asterisk">*</span></td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">Country:</td>
                                     <td class="tableRight"><input type="text" name="ctry" value="<%=icpVal[31]%>" id="country" readonly="true" class="textboxOne" size="25" />
                                         <span class="asterisk">*</span> </td>
						      </tr>
								   <tr>
                                     <td height="24" class="tableLeft">State:</td>
                                     <td class="tableRight"><input type="text" name="state" value="<%=icpVal[32]%>" id="state" readonly="true" class="textboxOne" size="25" />
                                         <span class="asterisk">*</span> </td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">City:</td>
                                     <td class="tableRight"><input type="text" name="city" id="city" value="<%=icpVal[33]%>" readonly="true" class="textboxOne" size="20" />
                                         <span class="asterisk">*</span></td>
						      </tr>
								   <tr>
                                     <td class="tableLeft">Zip:</td>
                                     <td class="tableRight"><input name="zip" type="text" readonly="true" value="<%=icpVal[34]%>" class="textboxOne" id="zip" size="25" />
                                         <span class="asterisk">*</span></td>
						      </tr>

								  
							 </table>
							<!--++++++++++++++++++++ Part 1 of the form ends here ++++++++++++++++++++++++++++++ -->
							</td>
						 </tr>
						 
						 <tr id="part2" class="holderDivTwo">
						 	<td colspan="2">
							
							<!--++++++++++++++++++++ Part 2 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								  <tr>
                                    <td class="tableLeft">Phone:</td>
                                    <td class="tableRight"><input type="text" name="phone" id="phone" value="<%=icpVal[35]%>" readonly="true" class="textboxOne" size="25" />
                                        <span class="asterisk">*</span></td>
						      </tr>
								  <tr>
                                    <td class="tableLeft">Fax:</td>
                                    <td class="tableRight"><input type="text" name="fax" id="faxNo" readonly="true" value="<%=icpVal[36]%>" class="textboxOne" size="25" />
                                        <span class="asterisk">*</span></td>
						      </tr>
								  <tr>
                                    <td class="tableLeft">EMail:</td>
                                    <td class="tableRight"><input type="text" name="email" value="<%=icpVal[29]%>" id="emailId" readonly="true"  class="textboxOne" size="25" />
                                        <span class="asterisk">*</span></td>
						      </tr>
								  <tr>
                                    <td class="tableLeftTxtArea">Assessor Detail:</td>
                                    <td class="tableRight"><span class="asterisk">
                                      <textarea name="acessdet" id="txtarea" class="textAreaOne" rows="5"><%=icpVal[9]%></textarea>
    *</span></td>
						      </tr>
								  <tr>
                                    <td class="tableLeftTxtArea"> Facilities To Be Used Specifically: </td>
                                    <td class="tableRight">
	<%
		String[] value=null;
		if(icpVal[10]!=null)
		{
			value=icpVal[10].split("#");
			 System.out.println("facility :"+icpVal[10].length());
			 System.out.println("facility siz:"+value.length);

			if(value[0]!=null)
			{
				if(!value[0].equalsIgnoreCase("null")){%>
			<input type="checkbox" checked name="ctry" value="Cross-Country Schooling" />
			<%}else{%>
			<input type="checkbox" name="ctry" value="Cross-Country Schooling" />
			<%}}%>
    Cross-Country Schooling
			<%if(value[1]!=null)
			{
			if(!value[1].equalsIgnoreCase("null")){%>
		    <input type="checkbox" checked name="rclinic" value="Riding Clinic" />
			<%}else{%>
			<input type="checkbox" name="rclinic" value="Riding Clinic" />
			<%}}%>
    Riding Clinic
			<%if(value[2]!=null)
			{
			if(!value[2].equalsIgnoreCase("null")){%>
		    <input type="checkbox" checked name="stab" value="Stablity" />
			<%}else{%>
			<input type="checkbox" name="stab" value="Stablity" />
			<%}}%>
    Stablity
			<%if(value[3]!=null)
			{
			if(!value[3].equalsIgnoreCase("null")){%>
    			<input type="checkbox" checked name="cbx_other" id="Otherchckbx" value="others" onchange="isOtherscheck();" />
			<%}else{%>
				<input type="checkbox" name="cbx_other" id="Otherchckbx" value="others" onchange="isOtherscheck();" />
			<%}}}%>
    Other </td>
						      </tr>
								  <tr>
                                    <td class="tableLeft"> If Other Specify:</td>
									<%if(value[3]!=null)
									{
										if(!value[3].equalsIgnoreCase("null")){%>
                                    <td class="tableRight"><input type="text" value="<%=value[3]%>" class="textboxOne" disabled="disabled" id="txtOther" name="others" /></td>
									<%}else{%>
									<td class="tableRight"><input type="text" class="textboxOne" disabled="disabled" id="txtOther" name="others" /></td>
									<%}}%>
						      </tr>

							</table>
							<!--++++++++++++++++++++ Part 2 of the form ends here ++++++++++++++++++++++++++++++ -->		
							
							</td>
						 </tr>
						  
					  	 <tr id="part3" class="holderDivTwo">
						<td colspan="2">
						
						<!--++++++++++++++++++++ Part 3 of the form starts here ++++++++++++++++++++++++++++++ -->	
						<table cellpadding="0" cellspacing="0" border="0" class="formLayout">

						  <tr>
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Land Owner Details : </span></td>
						  </tr>
						  <tr>
                            <td class="tableLeft">Land Owner Name :</td>
                            <td class="tableRight"><input type="text" value="<%=icpVal[11]%>" class="textboxOne" name="lownernam" id="name1" /> <span class="asterisk">*</span></td>
						    </tr>
						  <tr>
                            <td height="27" class="tableLeft">Business Name : </td>
                            <td class="tableRight"><input type="text" value="<%=icpVal[12]%>" class="textboxOne" name="busname" id="bizname"/> <span class="asterisk">*</span></td>
						    </tr>
						  <tr>
                            <td class="tableLeft"> Address:</td>
                            <td class="tableRight"><input type="text" value="<%=icpVal[13]%>" class="textboxOne" name="laddr" id="address" /> <span class="asterisk">*</span></td>
						    </tr>
						  <tr>
                            <td height="27" class="tableLeft">Country: </td>
                            <td class="tableRight">
                              <select name="ctry_sel" id="select11" class="selectboxOne" onchange="FillState(document.frmMeeICPAssessment.select11, document.frmMeeICPAssessment.select21,' ');">
                                <option value="<%=icpVal[16]%>" selected="selected"><%=icpVal[16]%></option>
                              
                              </select> <span class="asterisk">*</span>
                            </td>
						    </tr>
						  <tr>
                            <td class="tableLeft"> State:</td>
                            <td class="tableRight">
                              <select name="stat_sel" id="select21" class="selectboxOne" >                              
                                <option value="<%=icpVal[15]%>" selected="selected"><%=icpVal[15]%></option>
                               
                              </select> <span class="asterisk">*</span>
                            </td>
						    </tr>
						  <tr>
                            <td class="tableLeft">City: </td>
                            <td class="tableRight"><input type="text" value="<%=icpVal[14]%>" class="textboxOne" name="lcity" id="city2" /> <span class="asterisk">*</span></td>
						    </tr>
							 <tr>
							<td class="tableLeft">Zip: </td>
							<td class="tableRight"><input type="text" value="<%=icpVal[17]%>" class="textboxOne" name="lzip" id="zip2"/> <span class="asterisk">*</span></td>
						  </tr>
						     <tr>
                               <td class="tableLeft">Phone: </td>
                               <td class="tableRight"><input type="text" value="<%=icpVal[18]%>" class="textboxOne" name="lph" id="phone2"/> <span class="asterisk">*</span></td>
					        </tr>
						     <tr>
                               <td class="tableLeft style1">Fax: </td>
                               <td class="tableRight"><input type="text" value="<%=icpVal[19]%>" class="textboxOne" name="lfax" id="fax2" /> <span class="asterisk">*</span></td>
					        </tr>

						  <tr>
						  
							<td colspan="2" > 
						 
							  <table width="100%" border="0">
								
								  <tr>
									<td class="alignCenter"><input name="Submit" type="submit" class="gradBtn" value="Update" /></td>
								  </tr>
								  <%}}%>
							  </table>
							 </td>
						</tr>
						</table>
						<!--++++++++++++++++++++ Part 3 of the form ends here ++++++++++++++++++++++++++++++ -->		
						
						</td>

					  </table>
					  </form>
					  
					</td>
				  </tr>
				  <tr>
						<td>&nbsp; 
					   <!-- DO NOT DELETE THIS ROW -->
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmMeeICPAssessment'].elements['dat']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	//var cal2 = new calendar2(document.forms['frmMeeICPAssessment'].elements['checkDate']);
	//cal1.year_scroll = true;
	//cal1.time_comp = false;
	</script>
<script>
	FillCountry(document.frmMeeICPAssessment.select11, document.frmMeeICPAssessment.select21, '<%=icpVal[16]%>');
	FillState(document.frmMeeICPAssessment.select11, document.frmMeeICPAssessment.select21, '<%=icpVal[15]%>');
	
</script>

 

</body>
</html>
