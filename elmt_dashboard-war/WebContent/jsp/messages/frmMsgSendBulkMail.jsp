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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<style type="text/css">
#myToolTip {
	position:absolute;
	left:480px;
	top:260px;
	width:214px;
	height:184px;
	z-index:1;
	visibility:hidden;
}
#myToolTip2 {
	position:absolute;
	left:670px;
	top:260px;
	width:214px;
	height:184px;
	z-index:1;
	visibility:hidden;
}
</style>

<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmBulkMessageCompose.js" type="text/javascript"></script>
<!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
<script language="javascript">
/*
*To validate user input Datas on form Submit
*/

function callS(param){
	if(document.frmMsgCompose.txtEmail.value==""){
		document.frmMsgCompose.txtEmail.value= param;
	}
	else{
		document.frmMsgCompose.txtEmail.value+=";" + param;
	}
}
</script>

<script language="javascript">
/*
*To Dynamically display Address Book details and Distribution List Details
*/
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
   if (obj.style) { obj=obj.style; v=(v=='show')?'visible':'hidden'; }
    obj.visibility=v; }
}
//-->
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
<%! String  noCheck(String fieldName){
        String retValue = "";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }%>
<% 
	Vector HLCContactDetails = (Vector) request.getAttribute("ContactListDetails");
   	ArrayList distDetails = (ArrayList) request.getAttribute("DistributionListDetails");
     %>
		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Compose a Message </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
						<span class="msgHead">Compose Message</span>
						<br /><br />
						Use the section below to compose and send  messages to your friends and contacts.
						
						</td>
				  		</tr>
<tr><td>
<div id="myToolTip" onMouseOver="MM_showHideLayers('myToolTip','','show');" onMouseOut="MM_showHideLayers('myToolTip','','hide')" >
  <table width="180" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td  width="3" height="3" ></td>
      <td bgcolor="#fff3c2"></td>
      <td  width="3" height="3" ></td>
    </tr>
    <tr>
      <td bgcolor="#fff3c2"></td>
      <td height="100%" align="center" valign="middle" bgcolor="#fff3c2"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          
          <tr>
             
            <td height="100%" align="left" valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                          
                <%-- ArrayList containing Contact Details--%>
                <%  if(HLCContactDetails!=null && HLCContactDetails.size()!=0){
                    Enumeration enumContacts = HLCContactDetails.elements();
       
                       while(enumContacts.hasMoreElements()){
                       String [] contact = (String[])enumContacts.nextElement();
                       String contactlistId = contact[0];
                       String firstName = contact[1];
                       String middleName= contact[2];
                       String lastName= contact[3];
                       String nickName= contact[4];
                       String emailId= contact[5];
                %>
                <tr>
                    <td class="tipText"><a class="linkMail" href="javascript:callS('<%=emailId%>');" ><%=noCheck(firstName)%> <%=noCheck(middleName)%> <%=noCheck(lastName)%></a></td>
                </tr>
                <%    }
                    }else{                                
                %>
		 <tr>
                    <td class="tipText">No Contacts in Address Book</td>
                </tr>
                <%}%>
                
            </table></td>
            
          </tr>
          
      </table></td>
      <td bgcolor="#fff3c2"></td>
    </tr>
    <tr>
      <td width="3" height="3" ></td>
      <td bgcolor="#fff3c2"></td>
      <td width="3" height="3" ></td>
    </tr>
  </table>
</div>
</td></tr>
<!-- Second Dynamic Div-->
<tr><td>
<div id="myToolTip2" onMouseOver="MM_showHideLayers('myToolTip2','','show')" onMouseOut="MM_showHideLayers('myToolTip2','','hide');">
  <table width="180" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td  width="3" height="3" ></td>
      <td bgcolor="#fff3c2"></td>
      <td  width="3" height="3" ></td>
    </tr>
    <tr>
      <td bgcolor="#fff3c2"></td>
      <td height="100%" align="center" valign="middle" bgcolor="#fff3c2"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          
          <tr>
             
            <td height="100%" align="left" valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                                
                <%--ArrayList containing Distribution List Details --%>
				 <%  if(distDetails!=null && distDetails.size()!=0 ){
                    Iterator itrDistribution  = distDetails.iterator();
       				  String[] listNames = new String[distDetails.size()];
					  String[] emailIds = new String[distDetails.size()];
					   int i =0;
					   TreeMap listDetails = new TreeMap();
                       while(itrDistribution.hasNext()){
                       String [] distribution = (String[])itrDistribution.next();
                       
                       String listName = distribution[0];
                       String emailId= distribution[1];
                       String noOfUser= distribution[2];
					   listNames[i] = listName;
					   emailIds[i]  = emailId;
					   i++;
					   }
					   for(int k =0;k<i;k++){
					   		if(listDetails.containsKey(listNames[k])){
							 String emailId = ((String)listDetails.get(listNames[k]))+";"+emailIds[k];
							 listDetails.put(listNames[k],emailId);
							}else{
							 listDetails.put(listNames[k],emailIds[k]);
					   		}
					   }
                       Set keySet = listDetails.keySet();
					   Iterator itr = keySet.iterator();
					   while(itr.hasNext()){
					   String listName = (String)itr.next();
					   String emailId = (String)listDetails.get(listName);
                %>
                <tr>
                    <td class="tipText"><a class="linkMail" href="javascript:callS('<%=emailId%>');" ><%=listName%></a></td>
                </tr>
                <%    }
                    }else{                                
                %>
		 <tr>
                    <td class="tipText">No Contacts in Distribution List </td>
                </tr>
                <%}%>
                
            </table></td>
             
          </tr>
         
      </table></td>
      <td bgcolor="#fff3c2"></td>
    </tr>
    <tr>
      <td width="3" height="3" ></td>
      <td bgcolor="#fff3c2"></td>
      <td width="3" height="3" ></td>
    </tr>
  </table>
</div>
</td></tr>
<!-- End of Dynamic Div-->

						  <tr>
							<td>
							<form id="frmMsgCompose" name="frmMsgCompose" method="post" action="bulkmail.do" onsubmit="return composeOnCheck();">
							<input type="hidden" value="composeMsg" name="cmd"/>
							<input type="hidden" value="" name="userId"/>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								  <tr>
									<td class="tblRowHead">
									
										<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
										  <tr>
											<td width="11%" class="alignRight">
											<input type="submit" name="Send" value="Send" class="gradTwoBtn" /></td>
											<td width="17%" class="alignCenter">
											<input type="reset" name="cancel" value="Reset" class="gradTwoBtn" /></td>
											<td width="25%" align="center" class="textCommon"><span class="redlabel">
										    <label onMouseOver="javascript:MM_showHideLayers('myToolTip','','show')" onMouseOut="MM_showHideLayers('myToolTip','','hide')"><strong>Address Book</strong></label>
											</span> </td>
										    <td width="49%" align="left" class="textCommon"><span class="redlabel">
									        <label  onMouseOver="javascript:MM_showHideLayers('myToolTip2','','show')" onMouseOut="MM_showHideLayers('myToolTip2','','hide')"> <strong>Distribution List</strong></label>
										    </span></td>
										  </tr>
										</table>					</td>
								  </tr>
								  <tr>
									<td height="25" >
										
										<table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
										 
										 <!--
										  <tr class="tblMainHead">
											<td height="25" colspan="2" class="textCommon">&nbsp;
												<a href="#" class="linkFour">Insert Address</a> |
												<a href="#" class="linkFour">Add Cc</a> | 
												<a href="#" class="linkFour">Add BCc</a>
												
											</td>
										  </tr>
										  -->
										  <tr>
											<th width="19%" height="25" class="listCellBg">To:</th>
											<td width="81%" class="listCellBg">
											<input type="text" class="textboxOne" name="toEmail" id="txtEmail" size="45" />
											<div id="statusId"></div>
											</td>
										  </tr>
										  <tr>
											<th height="25" class="listCellBg">Subject:</th>
											<td height="25" class="listCellBg">
											<input type="text" class="textboxOne" name="subject" id="txtSubject" size="55" /></td>
										  </tr>
										  <tr>
											<td colspan="2" height="25" class="tblMainHead"> &nbsp;Message:</td>
										  </tr>
										  <tr>
											<td colspan="2" height="142" class="listCellBg">
											<textarea name="mesgDesc" rows="8" cols="58" id="txtMessage"></textarea></td>
										  </tr>
										  <tr>
											<td colspan="2" class="tblRowHead">
											
												<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
												  <tr>
													<td width="11%" class="alignRight">
													<input type="submit" name="Send" value="Send" class="gradTwoBtn" /></td>
													<td width="17%" class="alignCenter">
													<input type="reset" name="cancel" value="Reset" class="gradTwoBtn" /></td>
													
													<td width="42%" class="textCommon">&nbsp;</td>
													<td width="30%">&nbsp;</td>
												  </tr>
												</table>
											</td>
										  </tr>
									</table>
									</td>
								  </tr>
								   <tr>
									<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
								  </tr>
						</table>
						</form>
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
