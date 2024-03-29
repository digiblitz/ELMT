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
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<script src="javascripts/basic.js" type="text/javascript" /></script>
<script src="javascripts/frmMeeICPValid.js" type="text/javascript" /></script>
 
<script type="text/javascript">
 function MM_openBrWindow(theURL,winName,features) {  
  window.open(theURL,winName,features);
}
function dis_able(){
	document.frmMeeUserICPAssessInsure.membno.disabled=true;
	document.frmMeeUserICPAssessInsure.membno.value="";
}
function call(obj){
	if((document.frmMeeUserICPAssessInsure.membno.value=="")||(document.frmMeeUserICPAssessInsure.membno.value.indexOf(' ')==0)){
		document.frmMeeUserICPAssessInsure.hlcmemb.focus();
	}
	else{
		HLCMemberDetails(obj);
	}
}
</script>


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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"> Meetings: <span class="styleBoldTwo">Insurance Release - ICP Assessment</span></td>
				  </tr>
				 
				  <tr>
					<td>
					
						<form name="frmMeeUserICPAssessInsure" id="frmMeeUserICPAssessInsure" method="post" action="IcpUsrMeetList.do" onsubmit="return myvalidate();">
						<input type="hidden" name="process" value="apply">
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Activity Details: </td>
						  </tr>
						  <tr> 
							<td colspan=2>&nbsp; </td>
						  </tr>
						  <tr> 
                                                      <%
                                                        Vector icpMeeList=new Vector();
                                                        icpMeeList=(Vector)request.getAttribute("meeDetapp");
                                                        System.out.println("size :"+icpMeeList.size());
                                                        
							if(icpMeeList!=null && icpMeeList.size()!=0)
							{
                                                          Enumeration en=icpMeeList.elements();    
                                                            
							while(en.hasMoreElements())
							{
                                                                  String[] icpVal = (String[])en.nextElement();
                                                                  String dat1=icpVal[2];
								  String[] dat=dat1.split(" ");
                                                           %>
                                                         
                                                           <input type="hidden" name="mid" value="<%=icpVal[0]%>" />
                                                           
							<td class="tableLeft"> Name of Activity</td>
							<td colspan="-1" class="tableRight"><input type="text" readonly="true" class="textboxOne" value="<%=icpVal[1]%>" name="textfield" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Area</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=icpVal[26]%>" name="textfield2" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Date(s)to be held</td>
							<td colspan="-1" class="tableRight"> <input type="text" class="textboxOne" readonly="true" value="<%=dat[0]%>" name="textfield4" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Location</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=icpVal[5]%>" name="textfield3" /> 
							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> State</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=icpVal[32]%>" name="textfield32" /></td>
						  </tr>
						  <tr> 
							<td colspan="2" class="tableSpan">
							<a href="javascript:MM_openBrWindow('terms-conditions.html','terms','width=520,height=565')" class="linkFour">View Terms &amp; Conditions</a> 
							</td>
						  </tr>
						  <tr> 
							<td colspan="2" class="tableSpan"> 
							<input type="checkbox" name="chkAccept" value="checkbox" />  
							I Agree to the terms and conditions. </td>
						  </tr>
						  <tr> 
							<td colspan="2" class="tblRowHead"> Participant&#8217;s Details: </td>
						  </tr>
						  <% 
							ArrayList listContact = (ArrayList) request.getAttribute("userInfo");
							String prefix1="";
							String first_name = "";
							String middle_name = "";
							String last_name = "";
							String sufix = "";
							String email_id = "";
							String suite = "";
							String address1 = "";
							String address2 = "";
							String city = "";
							String state = "";
							String country = "";
							String zip = "";
							String phone_no = "";
							String mobile_no = "";
							String fax_no = "";
							
							if(listContact !=null && listContact.size()!=0){
								Iterator it = listContact.iterator();
								while(it.hasNext()){			
									prefix1 = (String)it.next();
									if(prefix1==null)
									prefix1 = "- N.A -";
									first_name  = (String)it.next();
									if(first_name==null)
									first_name = "- N.A -";
									middle_name  = (String)it.next();
									if(middle_name==null)
									middle_name = "- N.A -";
									last_name = (String)it.next();
									if(last_name==null)
									last_name = "- N.A -";
									sufix =  (String)it.next();
									if(sufix==null)
									sufix = "- N.A -";
									email_id  = (String)it.next();
									if(email_id==null)
									email_id = "- N.A -";
									suite =  (String)it.next();
									if(suite==null)
									suite = "- N.A -";
									address1 =  (String)it.next();
									if(address1==null)
									address1 = "- N.A -";
									address2 = (String)it.next();
									if(address2==null)
									address2 = "- N.A -";
									city = (String)it.next();
									if(city==null)
									city = "- N.A -";
									state =  (String)it.next();
									if(state==null)
									state = "- N.A -";
									country = (String)it.next();
									if(country==null)
									country = "- N.A -";
									zip = (String)it.next();
									if(zip==null)
									zip = "- N.A -";
									phone_no = (String)it.next();
									if(phone_no==null)
									phone_no = "- N.A -";
									mobile_no = (String)it.next();	
									if(mobile_no==null)
									mobile_no = "- N.A -";
									fax_no = (String)it.next();
									if(fax_no==null)
									fax_no = "- N.A -";
									}
								}
						  %>
						  <tr> 
							<td class="tableLeft">Email Id</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=email_id%>" name="textfield34" /></td>
						  </tr>
						  <tr> 
							<td height="27" class="tableLeft">First Name</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=first_name%>" name="textfield35" /></td>
						  </tr>
						   <tr> 
							<td height="27" class="tableLeft">Last Name</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=last_name%>" name="textfield35" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Address</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=address1%>&nbsp;<%=address2%>" name="textfield36" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Country</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" readonly="true" value="<%=country%>" name="textfield37" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> State</td>
							<td colspan="-1" class="tableRight"> <span class="row"><span class="formX"> 
							  <input type="text" class="textboxOne" readonly="true" value="<%=state%>" name="textfield3723" />
							  </span></span></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> City</td>
							<td colspan="-1" class="tableRight"><input type="text" value="<%=city%>" readonly="true" class="textboxOne" name="textfield372" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Zip Code</td>
							<td colspan="-1" class="tableRight"><input type="text" value="<%=zip%>" readonly="true" class="textboxOne" name="textfield3722" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Phone</td>
							<td colspan="-1" class="tableRight"><input type="text" readonly="true" value="<%=phone_no%>" class="textboxOne" name="textfield373" /> 
							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Fax</td>
							<td colspan="-1" class="tableRight"><input type="text" class="textboxOne" value="<%=fax_no%>" readonly="true" name="textfield373" /> 
							</td>
						  </tr>
						  <tr>
		 
							<td colspan="2" class="tblRowHead"> Other Information: </td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Are You a member?</td>
							<td colspan="-1" class="tableRight"> <input type="radio" name="hlcmemb" readonly="true" value="yes" onclick="isRadio();" />
							  Yes 
							  <input type="radio" name="hlcmemb" value="no" checked="checked" onclick="dis_able();" />
							  No</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">If yes, Your Membership Number</td>
							<td colspan="-1" class="tableRight"> <input type="text" class="textboxOne" name="membno" onblur="call(this);" disabled="disabled"/></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">&nbsp; </td>
							<td class="tableLeft"><span class="row">
							  <input name="sub" type="submit" class="gradBtn" value="Apply" />
							  </span></td>
							
					
						  </tr>

						<%}}else{%>
						 
                                <% }%>

						</table>
							
						</form>
					</td>
				  </tr>
				  
				  <tr>
				  
					<td height="20">&nbsp; 
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


 

</body>
</html>
