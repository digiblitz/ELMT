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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE><%=(String)session.getAttribute("title")%></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1" />
<SCRIPT SRC="javascripts/basic.js" TYPE="text/javascript" ></SCRIPT>
<SCRIPT SRC="javascripts/validate.js" TYPE="text/javascript" ></SCRIPT>
<SCRIPT SRC="javascripts/frmUserReg.js" TYPE="text/javascript" ></SCRIPT>
<SCRIPT SRC="javascripts/MemberUserSignup.js" TYPE="text/javascript" ></SCRIPT>
<script language="javascript">
function callEmpReg()
{
	strURL = "./MemberUsrSignUp.do?process=usrReg";
	window.location.href = strURL;
}
</script>
<LINK HREF="../../css/core-ie.css" TYPE="text/css" REL="stylesheet" /> 
</HEAD>
<BODY>
<TABLE WIDTH="760" BORDER="0" ALIGN="center" CELLPADDING="0" CELLSPACING="0" id="mainTab">
  <TR>
    <TD CLASS="alignTop">
		<!-- HEADER STARTS HERE -->
	 <%@ include file = "../../include/login_header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</TD>
  </TR>
  <TR>
    <TD CLASS="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%//@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</TD>
  </TR>
  <TR>
    <TD CLASS="tableCommonBg">
		
		<TABLE WIDTH="100%" BORDER="0" ALIGN="center" CELLPADDING="0" CELLSPACING="5" id="centerTab">
		  
		  <TR>
			<!--TD WIDTH="260" CLASS="menuTablePad"-->
			<!-- LEFT MENU STARTS HERE -->
			<!--%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    <!--/TD-->
			<TD WIDTH="500" CLASS="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<TABLE WIDTH="70%" BORDER="0" CELLPADDING="0" CELLSPACING="0" CLASS="tblInnerContainer" align="center">
				  <TR>
					<!-- TD COLSPAN="2" CLASS="tblMainHead"><strong>Create/Search for Dashboard Login</TD-->
					<TD COLSPAN="2" CLASS="tblMainHead"><strong>Create/Search for Login</TD>
				  </TR>
				  <TR>
					<!--TD COLSPAN="2" CLASS="tblDescrp">Existing Members can create their Dashboard login details here.	<BR /><BR/-->
					<TD COLSPAN="2" CLASS="tblDescrp">Existing Users can create their login details here.	<BR /><BR/>
					<!-- <B>Steps for Login </B><BR/>
1. Search to see if you are in our system (including non-members)<BR/> 2. Match your information (information may be outdated, you can update in Step 3) <BR/> 3. Update your Profile details<BR/> 4. Login <BR /><BR /-->
<B>Steps for Login </B><BR/>
1. Search to see if you are in our system <BR/> 2. Create a new Account <BR/> 3. Update your Profile details <BR/> 4. <a href="javascript:location.href='logout.do'">Login </a><BR /><BR />
<!-- <B>Steps for New Account </B><BR />
1. Search to see if you are in our system (including non-members) <BR />2. No match of your information (select "doesn't match my profile") <BR />3. Try Again or Create New Account <BR />4. Login-->
</TD>
				  </TR>
				  <%
				  String loginName = (String) request.getAttribute("loginName");
				  String source = (String)request.getAttribute("source");
				  String eventTypeId = (String)request.getAttribute("eventTypeId");
				  String compYear = (String)request.getAttribute("compYear");
				  if(loginName!= null && loginName.equalsIgnoreCase("notFound")){
				  %>
			     <TR>
						<TD HEIGHT="25" COLSPAN="2" CLASS="styleError">Please setup a username & Password in our new system.</TD>
				 </TR>
				  <%}   %>


						  <!--TR>
							<TD>
							
							<FORM name="frmUserSignup" id="myform" METHOD="post" ACTION="MemberUsrSignUp.do" onSubmit="return myvalidate();">		  
							<TABLE BORDER="0" CELLPADDING="0" ALIGN="center" CELLSPACING="0" CLASS="formLayout">  
								  <TR>
									<TD COLSPAN="2" CLASS="tblRowHead">&nbsp;<STRONG>Search By </STRONG>(enter information in any of the fields below) </TD>
								  </TR>
									<INPUT TYPE="hidden" name="eventTypeId" id="eventTypeId" VALUE="<!--%=eventTypeId%>" --/>
									<INPUT TYPE="hidden" name="compYear" id="compYear" VALUE="<!--%=compYear%>" --/>
									 <TR>								  
								  <TD CLASS="tableLeft">
									 Member ID:</TD>
																		
<TD CLASS="tableRight"> <INPUT TYPE="text" name="membid" id="chsUserName" CLASS="textboxOne"  size="20" onBlur="HLCMemberDetails();" />
									  </TD>
								  </TR>
								  
								  <INPUT TYPE="hidden" name="process" VALUE="sign" />
								  <INPUT TYPE="hidden" name="source" VALUE="<!--%=source%>" --/>
					
									<!-- tr>
									<td colspan="2" class="tableRight">
									&nbsp;											
									Last and/or First Name (partial names allowed)
									</td>	
									</tr>		
					
	  <tr class="tableInner">
		<td class="tableLeft"><span >First Name:</span></td>
		<td class="tableRight"><input type="text" name="fname" id="fname" class="textboxOne" size="20" />										 </TD>
	  </tr>
								 
 <TR CLASS="tableInner">
	<TD CLASS="tableLeft"><SPAN >Last Name:</SPAN></TD>
	<TD CLASS="tableRight"><INPUT TYPE="text" name="lname" id="lname" CLASS="textboxOne" SIZE="20" />
		</TD>
  </TR>
							  	 
								<TR>
									<TD COLSPAN="2" CLASS="alignCenter"><INPUT TYPE="submit" VALUE="Continue" CLASS="gradBtn" /></TD>
							   </TR>				 
								<TR>
									<TD COLSPAN="2" HEIGHT="25" CLASS="alignCenter">&nbsp;</TD>
							   </TR>
							</TABLE>
						</FORM>
					</TD>
				  </TR-->
				  <TR>
									<TD COLSPAN="2" CLASS="alignCenter"><INPUT TYPE="button" VALUE="Register" CLASS="gradBtn" onclick="callEmpReg()"/></TD>
				</TR>
				</TABLE>

			<!-- CONTENTS END HERE -->		
			</TD>
		  </TR>
	  </TABLE>
	
	</TD>
  </TR>
  <TR>
    <TD CLASS="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</TD>
  </TR>
</TABLE>

</BODY>
</HTML>
