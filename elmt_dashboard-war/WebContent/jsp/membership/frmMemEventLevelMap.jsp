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
<script src="javascripts/basic.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>

<script language="javascript">

function checkAll() {
     el = document.forms['myform'].elements;
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
 
function DelAll(){
	var chkValue = "";
	e = document.forms['myform'].elements;
	var count =0;
	for(i=0 ; i< e.length; i++){
		if(e[i].type == "checkbox"){
		 
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 chkValue +=  e[i].value + "#";
			}
		}
	}
		 
		document.myform.entityIds.value = chkValue;
		//alert("Final" + count + " checked Value:  ********" + document.myform.entityIds.value);
}

function entPrivValidate(){
  	if(document.frmMapLevel.membershipTypeId.value==""){
		alert("Select any one Role.");
		document.frmMapLevel.membershipTypeId.focus();
		return false;
	}
	if(document.myform.eventTypeId.value==""){
		alert("Select any Event Type.");
		document.myform.eventTypeId.focus();
		return false;
	}
 	DelAll();
	return true;
}
	
	
function postData(){
 	if(frmMapLevel.membershipTypeId.value!=""){
		frmMapLevel.process.value = "mapProcess";
		frmMapLevel.method="post";
		frmMapLevel.action="map.do";
		frmMapLevel.submit();
	}
	else{
		alert("Select any one Role");
		frmMapLevel.membershipTypeId.focus();
	}
}
 </script>
<body>
 <%
	String memberId = (String)request.getAttribute("memberId");
	System.out.print("memberId:" + memberId);
	if(memberId==null){
		memberId = "";
	}
	//String privId = "";

%>
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
					<td colspan="2" class="tblMainHead">
						Roles &amp; Privileges: <span class="styleBoldTwo"> Entity Role Mapping </span>					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
 				You can map an <strong>Event Level</strong> or Multiple <strong>Event Level</strong> for
 				a given Membership Type Name as given below. <br />
				 
					<br />					</td>
				  </tr>
				  <tr>
					<td>
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Map An Event Level For A Member: </td>
						  </tr>
						  <form name="frmMapLevel" id="frmMapLevel" >
					    	<input type="hidden" name="process" value="" />
										  <tr>
											<td class="tableLeft">Select a Membership:</td>
											<td class="tableRight"><span class="alignLeft">
											<select name="membershipTypeId" id="membershipTypeId" class="selectboxOne" onchange="postData();">
											<option selected="selected" value="">Select One</option>
											<%
													ArrayList horsememberVect=(ArrayList)request.getAttribute("horsememberVect");
													if(horsememberVect!= null && horsememberVect.size()!=0){
													Iterator itr = horsememberVect.iterator();
														while(itr.hasNext()){
															String[] sarray = (String[]) itr.next();
															String memberTypeId = sarray[0];
															String membershipName = sarray[1];
															String membershipAmount =sarray[2];
															if(memberTypeId.equals(memberId)){
															%>
															<option  value="<%=memberTypeId%>" selected="selected"><%=membershipName%></option>
															 <%
															 }
															 else{
															 %>
															<option  value="<%=memberTypeId%>"><%=membershipName%></option>
															 <%
															 }
														}
													}
											%>
											</select>
											</span>											</td>
										  </tr>
						  </form>
						
						  <form name="myform" id="myform" method="post" action="map.do" onsubmit="return entPrivValidate();">
						  <input type="hidden" name="process" value="mappingMemLevel" />
							<input type="hidden" name="entityIds" value="puni">
							<input type="hidden" name="memberId" value="<%=memberId%>"> 
							
						 
							
							 
						  <tr>
						    <td colspan="2">
							
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								
							
										<tr>
					<td class="tableLeft">Select a Event Type:</td>
					<td class="tableRight"><span class="alignLeft">
					<select name="eventTypeId" id="eventTypeId" class="selectboxOne" >
						<option selected="selected" value="">Select One</option>
						<%
						String eventTypeId1 = "";
											String eventLevelId1 = "";
							Vector allTypesVect=(Vector)request.getAttribute("allTypesVect");
							if(allTypesVect!=null){
							Enumeration itrators1= (Enumeration)allTypesVect.elements();
							while(itrators1.hasMoreElements()){
								String[] level = (String[]) itrators1.nextElement();                                    
								String typeid=level[0];
								String typename=level[1];
								String typestatus = level[2];
								String typeadd_date = level[3];
										ArrayList mapMemLevelDetails1 = (ArrayList)request.getAttribute("mapMemLevelDetails");
																if(mapMemLevelDetails1!=null && mapMemLevelDetails1.size()!=0){
																	Iterator itMemEventLevelList1 = mapMemLevelDetails1.iterator();
																	
																	while(itMemEventLevelList1.hasNext()){
																		String[] mapEntList1 = (String [])itMemEventLevelList1.next();
																		  eventTypeId1 = mapEntList1[2];
																		  eventLevelId1 = mapEntList1[3];
																		  }		
																    }		  					
								
								  if(typeid.equals(eventTypeId1)){
						%>
					 <option  value="<%=typeid%>" selected="selected"><%=typename%></option> 
						<%
							 }
							 else{
						%>
					<option  value="<%=typeid%>"><%=typename%></option>
						<%
							 }
							}
							}
						%>
					</select>
					</span>
					
					</td>
					</tr>	
											
									
							 <tr> 
							 
							<td colspan="2" class="tableLeftTxtArea"> Accessible Event Level(s):</td>
							</tr>					
							<tr>
									<td width="477" colspan="2" class="tableLeftTxtArea">&nbsp;
								  <input  type="checkbox" name="chkAll" value="ChkAll" alt="Select or Deselect All" onClick="checkAll();" > Select All									</td>
								</tr>						
											
											
											<%
									 
											
											String eventTypeId = "";
											String eventLevelId = "";
											Vector allLevelsVect=(Vector)request.getAttribute("allLevelsVect");
											if(allLevelsVect!=null){
											Enumeration itrators= (Enumeration)allLevelsVect.elements();
													while(itrators.hasMoreElements()){
															String[] levels = (String[]) itrators.nextElement();                                    
															String levelid=levels[0];
															String name=levels[1];
															String code=levels[2];
															boolean entIdStatus = false;
																ArrayList mapMemLevelDetails = (ArrayList)request.getAttribute("mapMemLevelDetails");
																if(mapMemLevelDetails!=null && mapMemLevelDetails.size()!=0){
																	Iterator itMemEventLevelList = mapMemLevelDetails.iterator();
																	
																	while(itMemEventLevelList.hasNext()){
																		String[] mapEntList = (String [])itMemEventLevelList.next();
																		  eventTypeId = mapEntList[2];
																		  eventLevelId = mapEntList[3];
																		if(levelid.equals(eventLevelId)){
																			entIdStatus = true;
																			break;
																		}
																	}
											}
											 
											
											
											
											
											
											if(entIdStatus==true){
											
											%>
											
					
				
													<tr> 
													<td  colspan="2" class="tableLeftTxtArea">&nbsp;
															<input type="checkbox" size="10" name="<%=name%>" checked="checked" value="<%=levelid%>" />&nbsp;<%=code%>		
													</td>
													</tr>
											<%
											}
											else{
											%>
													<tr> 
													<td colspan="2" class="tableLeftTxtArea">&nbsp;
															<input type="checkbox" size="10" name="<%=name%>" value="<%=levelid%>" />&nbsp;<%=code%>		
													</td>
													</tr>
											 <%
											 }
										}
									 }
									%>
								</table>
							
							</td>
						    </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">
							
							<input type="submit" value="Submit" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);"/>							</td>
						  </tr>
						  </form>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
						</table>					</td>
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
