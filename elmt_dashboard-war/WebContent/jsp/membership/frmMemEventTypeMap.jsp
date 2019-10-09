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
  	if(document.myform.eventTypeId.value==""){
		alert("Select any Event Type.");
		document.myform.eventTypeId.focus();
//		document.myform.eventTypeId.value = document.myform.eventTypeId.value;
		return false;
	}
 	DelAll();
	return true;
}
	
	
function postData(){
 	if(myform.eventTypeId.value!=""){
		myform.process.value = "MapEvntLevel";
		myform.method="post";
		myform.action="EvntLvlTypeMap.do";
		myform.submit();
	}
	else{
		alert("Select any one Role");
		myform.eventTypeId.focus();
	}
}
 </script>
<body>
 <%
/*	String memberId = (String)request.getAttribute("memberId");
	System.out.print("memberId:" + memberId);
	if(memberId==null){
		memberId = "";
	}
	//String privId = "";*/

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
 				You can map an <strong>Event Type </strong> for  Multiple <strong>Event Level</strong> for
 				a selected  Event Type Name as given below. <br />
				 
					<br />					</td>
				  </tr>
				  <tr>
					<td>
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Map An Event Level For A Member: </td>
						  </tr>
						  <form name="myform" id="myform" action="EvntLvlTypeMap.do" method="post" onsubmit="return entPrivValidate();">
					    	<input type="hidden" name="process" value="insertEvntLevel" />
										  <tr>
											<td width="209" class="tableLeft">Select a Event Type:</td>
											<td width="291" class="tableRight"><span class="alignLeft">
											<%
											String typeid = "";
											if(request.getAttribute("eventTypeId")!=null){
												typeid = (String) request.getAttribute("eventTypeId");
											}
											%>
											<select name="eventTypeId" id="eventTypeId" class="selectboxOne" onchange="postData();">
											<option selected="selected" value="">Select One</option>
											<%
												Vector allTypesVect=(Vector)request.getAttribute("allTypesVect");
												if(allTypesVect!=null){
													Enumeration itrators= (Enumeration)allTypesVect.elements();
													while(itrators.hasMoreElements()){
														String[] levels1 = (String[]) itrators.nextElement();                                    
														String id = levels1[0];
														String name = levels1[1];
														String status = levels1[2];
														String add_date = levels1[3];
														 if(typeid.equals(id)){
												%>
												<option  value="<%=id%>" selected="selected"><%=name%></option>
													<%
														}
														else{
													%>
												<option  value="<%=id%>"><%=name%></option>
												<%
														}
													}
												}
											%>
											</select>
											</span>											</td>
										  </tr>

							<input type="hidden" name="entityIds" value="puni">
												
				<tr>
			<td colspan="2" class="tableLeftTxtArea">Existing Event Level(s):</td>		
			</tr>
			<%
				String eventLevelCode="";
				ArrayList eventLevelList = (ArrayList)request.getAttribute("eventLevelList");
					if(eventLevelList!=null && eventLevelList.size()!=0){
						Iterator itMemEventLevelList = eventLevelList.iterator();
						
						while(itMemEventLevelList.hasNext()){
							String[] mapEntList = (String [])itMemEventLevelList.next();
							//{mapEntityId, roleIdVal, entityId, roleName, entityName};
							String mapTypeid =mapEntList[0];
							String tempEveTypeId = mapEntList[1];
							String eveLevelId = mapEntList[2];
							String eveTypName = mapEntList[3];
							String eventLevelName = mapEntList[4];
							 eventLevelCode = mapEntList[5];
				%>				
				<tr>
			<td class="alignCenter"><%=eventLevelCode%></td>				
				</tr>	
				
			<%
			}
			}			
			%>
			
			<%
			if(eventLevelCode==null){
			%>
							
						  <tr> 							 
							<td colspan="2" class="tableLeftTxtArea"> Accessible Event Level(s):</td>
							</tr>
						  <tr>
						    <td colspan="2">
							
								<tr>
									<td width="477" colspan="2" class="tableLeftTxtArea">&nbsp;
								  <input  type="checkbox" name="chkAll" value="ChkAll" alt="Select or Deselect All" onClick="checkAll();" > Select All									</td>
								</tr>
							<%
											Vector allLvlsVect=(Vector)request.getAttribute("allLvlsVect");
											if(allLvlsVect!=null){
												Enumeration itrators= (Enumeration)allLvlsVect.elements();
													while(itrators.hasMoreElements()){
															String[] levels = (String[]) itrators.nextElement();  
															String levelid =levels[0];
															String levelName = levels[1];
															String code = levels[2];
															String jumping_efforts = levels[3];								
											%>
													<tr> 
													<td colspan="2" class="tableLeftTxtArea">&nbsp;
															<input type="checkbox" size="10" name="<%=levelName%>" value="<%=levelid%>" />&nbsp;<%=code%>		
													</td>
													</tr>
											 <%
											 }
										}
									}else{
									%>
									
			  <tr> 							 
							<td colspan="2" class="tableLeftTxtArea">Additional Event Level(s):</td>
							</tr>
						  <tr>
						    <td colspan="2">
							
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td width="477" colspan="2" class="tableLeftTxtArea">&nbsp;
								  <input  type="checkbox" name="chkAll" value="ChkAll" alt="Select or Deselect All" onClick="checkAll();" > Select All									</td>
								</tr>
							<%
											Vector allLvlsVect=(Vector)request.getAttribute("allLvlsVect");
											if(allLvlsVect!=null){
												Enumeration itrators= (Enumeration)allLvlsVect.elements();
													while(itrators.hasMoreElements()){
															String[] levels = (String[]) itrators.nextElement();  
															String levelid =levels[0];
															String name = levels[1];
															String code = levels[2];
															String jumping_efforts = levels[3];

															boolean entIdStatus = false;
												ArrayList eventLevelList1 = (ArrayList)request.getAttribute("eventLevelList");
																if(eventLevelList1!=null && eventLevelList1.size()!=0){
																	Iterator itMemEventLevelList = eventLevelList1.iterator();
																	
																	while(itMemEventLevelList.hasNext()){
																		String[] mapEntList = (String [])itMemEventLevelList.next();
																		//{mapEntityId, roleIdVal, entityId, roleName, entityName};
																		String eventLevelCode1 = mapEntList[5];
																		if(code.equals(eventLevelCode1)){
																			entIdStatus = true;
																			break;
																		}
																	}
											}
											if(entIdStatus==true){
											
											%>
													
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
									 }
									%>						
	
								</table>
							
							</td>
						    </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">
							
							<input type="submit" value="Submit" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Cancel" onclick="location.href='index.jsp'"/>							</td>
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
