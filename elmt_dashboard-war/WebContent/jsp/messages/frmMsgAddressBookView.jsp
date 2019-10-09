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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/pagination.tld" prefix="pg"%>

<bean:define id="HLCContactDetails" name="contactForm" scope="request"/>
<bean:define id="start" name="contactForm" property="start" scope="request"/>
<bean:define id="range" name="contactForm" property="range" scope="request"/>
<bean:define id="resultSize" name="contactForm" property="resultSize" scope="request"/>
<bean:define id="sortName" name="contactForm" property="sortName" scope="request"/>




<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script language="javascript">
function checkAll() {
     el = document.forms['contactForm'].elements;
     for (i=0; i < el.length; i++) {
       if(el[i].type == "checkbox") {
          if(el[i].checked== true && el[i].value== "ChkAll") {
            //alert("ok");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=1);
               }
            }
            break;
          }
          if(el[i].checked== false && el[i].value== "ChkAll") {
            //alert("uncheck");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=0);
               }
            }
          }   
       }
     }
 }
 function fireCommand(obj) {
 alert("name"+obj);
   if(obj.value=="Edit"){
	document.forms[0].method.value="initEdit"; 
   } else {
    document.forms[0].method.value="deleteAll";
   }
   document.forms[0].submit();
 }
 
function DelAll(){
	var chkValue = "";
	e = document.forms['contactForm'].elements;
	var count =0;
	for(i=0 ; i< e.length; i++){
		if(e[i].type == "checkbox"){
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 chkValue +=  e[i].value + "#";
			}
		}
	}
	if(count == 0 ){
		alert("Check any one of Contact Below");
		return false;
	}
	else{
		document.contactForm.method = "post";
		document.contactForm.action = "contactAction.do";
		document.contactForm.submit();
	}
} 
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
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<html:form method="post" action="/contactAction.do" method="post" onsubmit="return DelAll();"> <!--onSubmit="DelAll();"-->
                <html:hidden property="method" value="deleteAll"/>

				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Address Book - View Contacts</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><br />
						<span class="msgHead">Address Book (<bean:write name="contactForm" property="resultSize"/>)</span>  - <span class="styleBoldOne">View Contacts</span> <br />
						<br />
						You are viewing the list of contacts you had added to your <strong>Address Book</strong>. <br /><br /> To Edit an existing contact
						in your address book, click on the <strong>'Edit'</strong> button beside each record. To delete a contact from your 
						address book <strong>'Check'</strong> the relevant record in your list and click on the <strong>'Delete'</strong> button.
						<br /><br />
					</td>
				  </tr>
						  <tr>
							<td>
							
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								  <tr>
									<td class="tblRowHead">									
										<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
										  <tr>
											<td width="20%"><span class="disclaimer">(select All)</span></td>
											<td width="14%" class="alignRight"><input type="button" class="gradTwoBtn" name="addContact" value="Add New" onclick="location.href='contactAction.do?method=initAdd'" /></td>
											<td width="15%" class="alignCenter"><html:submit value="Delete" styleClass="gradTwoBtn"></html:submit>
											<!--<input type="submit" name="delete" value="Delete" class="gradTwoBtn"/>--></td>
											<% 
											String pageCount = "0";
											String totalPagesCount="0";
											if(Integer.parseInt((String)resultSize) > 0) {
											   pageCount = String.valueOf((Integer.parseInt((String)start)+Integer.parseInt((String)range))/10);
											
												int	totalPages = (Integer.parseInt((String)resultSize) / Integer.parseInt((String)range));
		                                          totalPages =(Integer.parseInt((String)resultSize) % 
													Integer.parseInt((String)range) == 0) ? totalPages : totalPages + 1;
											    totalPagesCount = String.valueOf(totalPages);
											  }
											%>
											<td width="23%" class="textCommon">
												<strong>Contacts</strong> 
												<span class="styleBoldTwo"><%=pageCount%> - <%=totalPagesCount%></span> 
											</td>
											<td width="22%">
												<pg:pager title="yes" start='<%=(String)start%>'
												range='<%=(String)range%>' results='<%=(String)resultSize%>' action="<%="contactAction.do?method=search&sortName="+sortName%>" styleClass="page" />
											</td>
										  </tr>
										</table>
									
									</td>
								  </tr>
								  <tr>
									<td height="25" >
										
										<table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
										  <tr class="tblMainHead">
											<td width="5%" height="25" ><input  type="checkbox" name="chkAll" value="ChkAll" alt="Select or Deselect All" onClick="checkAll();" ></td>
											<td width="21%" height="25">Nickname</td>
											<td width="26%" height="25">First Name</td>
											<td width="29%" height="25">Email</td>
											<td width="19%">Edit</td> 
											</tr>
									<logic:present name="contactForm" property="results">
									 <bean:size id="size" name="contactForm" property="results"/>
									 <logic:equal name="size" value="0">
											<tr>
											   <td colspan="4"  align="center"><B>No Records Found</B></td>
											</tr>
									 </logic:equal>										
									 <logic:greaterThan name="size" value="0">
										<logic:iterate id="searchList" name="contactForm" property="results" > 					
												  <input type="hidden" name="contactId" value='<bean:write name="searchList" property="contactlistId"/>'/>
												  <tr>
													<td height="25" class="listCellBg">
														<html:multibox  property="userCheckBox" ><bean:write name="searchList" property="contactlistId"/></html:multibox>	
												</td>
													<td class="listCellBg"><a href='contactAction.do?method=initView&contactId=<bean:write name="searchList" property="contactlistId"/>'><bean:write name="searchList" property="nickName"/></a></td>
													<td class="listCellBg"><bean:write name="searchList" property="firstName"/></td>
													<td class="listCellBg"><bean:write name="searchList" property="emailId"/></td>
													<td class="listCellBg">
													<input name="editName" type="button" 
													onClick="location.href='contactAction.do?method=initEdit&contactId=<bean:write name="searchList" property="contactlistId"/>'" class="oneBtn" value="Edit" />	
													</td>
												  </tr>											   
											</logic:iterate>                    
									   </logic:greaterThan>     
									</logic:present>
											</table>
									
									</td>
								  </tr>
								  <tr>
									<td class="tblRowHead">
									
										<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
										  <tr>
											<td width="20%">&nbsp;</td>
											<td width="14%" class="alignRight">&nbsp;</td>
											<td width="15%" class="alignCenter">&nbsp;</td>
											<td width="23%" class="textCommon">
												<strong>Contacts</strong> 
												<span class="styleBoldTwo"><%=pageCount%> - <%=totalPagesCount%></span> 
											</td>
											<td width="22%">
												<pg:pager title="yes" start='<%=(String)start%>'
												range='<%=(String)range%>' results='<%=(String)resultSize%>' action="<%="contactAction.do?method=search&sortName="+sortName%>" styleClass="page" />
											</td>
										  </tr>
										</table>
									
									</td>
								  </tr>
								  <tr>
									<th height="25" class="alignCenter">Browse:&nbsp; 	
									<% if (!sortName.equals("A")){ %>
										   <a href="contactAction.do?method=search&sortName=A" class="linkOne">A</a> 
									<%} else {%>
										  <strong>A</strong>
									<%}%>
									   <span class="divider">|</span>
									<% if (!sortName.equals("B")){ %>
										   <a href="contactAction.do?method=search&sortName=B" class="linkOne">B</a> 
									<%} else {%>
										  <strong>B</strong>
									<%}%>
									   <span class="divider">|</span>
									<% if (!sortName.equals("C")){ %>
										   <a href="contactAction.do?method=search&sortName=C" class="linkOne">C</a> 
									<%} else {%>
										  <strong>C</strong>
									<%}%>
									   <span class="divider">|</span>
									<% if (!sortName.equals("D")){ %>
										   <a href="contactAction.do?method=search&sortName=D" class="linkOne">D</a> 
									<%} else {%>
										  <strong>D</strong>
									<%}%>
									  <span class="divider">|</span>
									<% if (!sortName.equals("E")){ %>
										   <a href="contactAction.do?method=search&sortName=E" class="linkOne">E</a> 
									<%} else {%>
										  <strong>E</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("F")){ %>
										   <a href="contactAction.do?method=search&sortName=F" class="linkOne">F</a> 
									<%} else {%>
										  <strong>F</strong>
									<%}%>
									  <span class="divider">|</span>
									
									<% if (!sortName.equals("G")){ %>
										   <a href="contactAction.do?method=search&sortName=G" class="linkOne">G</a> 
									<%} else {%>
										  <strong>G</strong>
									<%}%>
									  <span class="divider">|</span>
									        <% if (!sortName.equals("H")){ %>
										   <a href="contactAction.do?method=search&sortName=H" class="linkOne">H</a> 
									<%} else {%>
										  <strong>H</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("I")){ %>
										   <a href="contactAction.do?method=search&sortName=I" class="linkOne">I</a> 
									<%} else {%>
										  <strong>I</strong>
									<%}%>
									  <span class="divider">|</span>
									  <% if (!sortName.equals("J")){ %>
										   <a href="contactAction.do?method=search&sortName=J" class="linkOne">J</a> 
									<%} else {%>
										  <strong>J</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("K")){ %>
										   <a href="contactAction.do?method=search&sortName=K" class="linkOne">K</a> 
									<%} else {%>
										  <strong>K</strong>
									<%}%>
									  <span class="divider">|</span>
									<% if (!sortName.equals("L")){ %>
										   <a href="contactAction.do?method=search&sortName=L" class="linkOne">L</a> 
									<%} else {%>
										  <strong>L</strong>
									<%}%>
									  <span class="divider">|</span>
									        <% if (!sortName.equals("M")){ %>
										   <a href="contactAction.do?method=search&sortName=M" class="linkOne">M</a> 
									<%} else {%>
										  <strong>M</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("N")){ %>
										   <a href="contactAction.do?method=search&sortName=N" class="linkOne">N</a> 
									<%} else {%>
										  <strong>N</strong>
									<%}%>
									  <span class="divider">|</span>
									  <% if (!sortName.equals("O")){ %>
										   <a href="contactAction.do?method=search&sortName=O" class="linkOne">O</a> 
									<%} else {%>
										  <strong>O</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("P")){ %>
										   <a href="contactAction.do?method=search&sortName=P" class="linkOne">P</a> 
									<%} else {%>
										  <strong>P</strong>
									<%}%>
									  <span class="divider">|</span>
                                   <% if (!sortName.equals("Q")){ %>
										   <a href="contactAction.do?method=search&sortName=Q" class="linkOne">Q</a> 
									<%} else {%>
										  <strong>Q</strong>
									<%}%>
									  <span class="divider">|</span>
									        <% if (!sortName.equals("R")){ %>
										   <a href="contactAction.do?method=search&sortName=R" class="linkOne">R</a> 
									<%} else {%>
										  <strong>R</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("S")){ %>
										   <a href="contactAction.do?method=search&sortName=S" class="linkOne">S</a> 
									<%} else {%>
										  <strong>S</strong>
									<%}%>
									  <span class="divider">|</span>
									  <% if (!sortName.equals("T")){ %>
										   <a href="contactAction.do?method=search&sortName=T" class="linkOne">T</a> 
									<%} else {%>
										  <strong>T</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("U")){ %>
										   <a href="contactAction.do?method=search&sortName=U" class="linkOne">U</a> 
									<%} else {%>
										  <strong>U</strong>
									<%}%>
									  <span class="divider">|</span>

									<% if (!sortName.equals("V")){ %>
										   <a href="contactAction.do?method=search&sortName=V" class="linkOne">V</a> 
									<%} else {%>
										  <strong>V</strong>
									<%}%>
									  <span class="divider">|</span>

									<% if (!sortName.equals("W")){ %>
										   <a href="contactAction.do?method=search&sortName=W" class="linkOne">W</a> 
									<%} else {%>
										  <strong>W</strong>
									<%}%>
									
									  <span class="divider">|</span>
									        <% if (!sortName.equals("X")){ %>
										   <a href="contactAction.do?method=search&sortName=X" class="linkOne">X</a> 
									<%} else {%>
										  <strong>X</strong>
									<%}%>
									  <span class="divider">|</span>
                                    <% if (!sortName.equals("Y")){ %>
										   <a href="contactAction.do?method=search&sortName=Y" class="linkOne">Y</a> 
									<%} else {%>
										  <strong>Y</strong>
									<%}%>
									  <span class="divider">|</span>
									  <% if (!sortName.equals("Z")){ %>
										   <a href="contactAction.do?method=search&sortName=Z" class="linkOne">Z</a> 
									<%} else {%>
										  <strong>Z</strong>
									<%}%>							

									</th>
								  </tr>
								   <tr>
									<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
								  </tr>
						</table>
					
					</td>
				  </tr>
				</table>
				 </html:form>
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
