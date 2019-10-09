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
<script src="javascripts/frmMessageEditDistriList.js" type="text/javascript" ></script>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" rel="stylesheet" type="text/css" /> -->

<link href="css/core-ie.css" rel="stylesheet" type="text/css" />
</head>
<%@ page import="java.util.*" %>
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
 <%! String  noCheck(String fieldName){
        String retValue = "";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }%>     

<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Distribution Lists  - Edit/Update  List </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	    <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
	    <span class="msgHead">Distribution List  </span>  - <span class="styleBoldOne">Edit/Update  List </span> <br />
	    <br />
		You can <strong>Edit/Update</strong> this distribution list by adding new members or removing members added to your list from your Address Book. <br />
		<br />
		<br />
		<br />
	</td>
  </tr>
		  <tr>
			<td>
			
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
				  <tr>
					<td class="tblRowHead">Edit/Update This Distribution List </td>
				  </tr>
				  <tr>
				    <form name="frmMessageEditDistriList" id="frmMessageEditDistriList" method="post" action="./AddrDistList.do" onsubmit="return myvalidate();" >

						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
															 <%
                                                               Vector dListname=new Vector();
                                                               dListname=(Vector)request.getAttribute("dList");
                                                               String[] dname=null;                                                               
                                                               
                                                               if(dListname !=null && dListname.size()!=0)
                                                               {
								dname=(String[])dListname.elementAt(0);
																%>
							<th width="28%" class="alignRight">Distribution List Name:&nbsp; </th>
							<td width="72%" class="alignLeft"><input name="lstname" id="txtDisList" value="<%=dname[1]%>" type="text" class="textboxOne" size="35" /></td>
							<%}%>
						  </tr>
						</table>
					
				  <tr>
					<td height="25" >
					
					
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td class="tblMainHead">Members in your Address Book</td>
							<td class="tblMainHead">&nbsp;</td>
							<td class="tblMainHead">Members Added to your List </td>
						  </tr>
						  <tr>
							<th rowspan="2">
							<select name="addsel" id="selSource" size="6" multiple="multiple" class="textAreaOne">
							<%
							Vector addVect=new Vector();
							addVect=(Vector)request.getAttribute("addrList");
                                                        Enumeration en=addVect.elements(); 
                                                        if(addVect !=null && addVect.size()!=0 )
                                                        {
                                                        while(en.hasMoreElements())
                                                        {
							String[] addrList=(String[])en.nextElement();
							 Enumeration enu=dListname.elements();
                              boolean flag = true;                               
							   while(enu.hasMoreElements())
							   {
								   String [] dInfo=(String[])enu.nextElement();								   
								   if(dInfo[2]!=null && dInfo[2].equalsIgnoreCase(addrList[0])){
								    flag = false;
								   }
								}
								if(flag){  
							%>
							
														
								<option value="<%=addrList[0]%>"><%=noCheck(addrList[1])+" "+noCheck(addrList[3])%></option>
							
                                                          		
                                                        <%}}}else{%>
                                                            <option>No Address Available</option>
							
                                                        <%}%>
						  </select>
							</th>
							<td height="28"><input type="button" name="add" value="&raquo;&raquo;" class="gradTwoBtn" onClick="addSrcToDestList();"/></td>



							<td rowspan="2">
								  
								<select name="distsel" id="selDest"size="6" multiple="multiple"  class="textAreaOne">
								
                                                           <%	HashMap hm = new HashMap();
                                                               Vector dList=new Vector();
                                                               dList=(Vector)request.getAttribute("dList");
                                                               String[] dInfo=null;                                                                if(dList !=null && dList.size()!=0)
                                                               {
                                                               Enumeration enu=dList.elements();
                                                              
                                                                   while(enu.hasMoreElements())
                                                                   {
                                                                       dInfo=(String[])enu.nextElement();
																	   
																	   hm.put(dInfo[2],noCheck(dInfo[4])+" "+noCheck(dInfo[5]));
                                                                    }
																 }
																 if(!hm.isEmpty()){
																 Set set = hm.entrySet();
																 Iterator i = set.iterator();
																 while(i.hasNext()){
																 Map.Entry me = (Map.Entry)i.next();
																 %>
																  <option value="<%=me.getKey()%>"><%=me.getValue()%></option>
																	<%}}else{%>
				                                                            <option>No Address Available</option>
									
                                    				                    <%}%>
                                                                       
								</select>
							</td>
						  </tr>
						  <tr>
							<td valign="top"><input type="button" name="remove" value="&laquo;&laquo;" class="gradTwoBtn" onclick="deleteFromDestList();"/></td>
                                                        <input type="hidden" name="listname" value="<%=dInfo[1]%>" />
						  </tr>
						</table>
					
					</td>
				  </tr>
				  <tr>
					<td height="30" class="alignCenter">
						<input type="submit" name="process" value="Update" class="gradBtn" />
						
						<input type="button" name="update" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);" />
					</form>
					
				   <tr>
					<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
				  </tr>
		</table>
	</td>
				  </tr>
	
</table>
			<!-- CONTENTS END HERE -->		
		
	
	
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
