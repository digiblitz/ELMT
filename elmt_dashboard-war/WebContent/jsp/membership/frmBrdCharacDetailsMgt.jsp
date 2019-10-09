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

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.hlcmrm.util.HLCBreedVO"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmBrdCharacDetailsMgt.js" type="text/javascript" ></script>




<%
String retCharId = (String) request.getAttribute("retCharId");
String characteristic = (String) request.getAttribute("Characteristics");
String uTypeId=(String) request.getAttribute("uTypeId");
String radCharId=(String) request.getAttribute("radCharId");
String refcnt=(String) request.getAttribute("refcnt");

System.out.println("retCharId in page"+retCharId);
System.out.println("radCharId in page"+radCharId);
System.out.println("characteristic in page"+characteristic);%>

<script>



</script>





</head>
<style>

.td {
			float: left;
			width: 400px;		
			overflow: hidden;
			white-space: nowrap;			
		}

</style>


<%
if(refcnt!=null && refcnt!="" && refcnt.equalsIgnoreCase("1")){%>
<body>

<%}else{%>

<body onload="breedSec();">

<%}%>

<table width="492" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td width="492" class="alignTop">
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
		
		<table width="782" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
		
			<td width="470" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Membership: <span class="styleBoldTwo">Breed Characteristics Categories and Details management </span></td>
  </tr>

  <tr>
    <td colspan="2" class="tblDescrp"><br />
	    <br />
		You can manage <strong>Breed Characteristics Categories</strong> and <strong>Breed Characteristics Details</strong> by clicking the common radio buttons. <br />
		<br />
		
	</td>
  </tr>
		  <tr>
			<td>
			
			<table width="782" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
			
				  <tr>
					<td height="25" >
					
					
						<table width="769" border="0" cellspacing="0" cellpadding="0">
						  <tr>
						  <form name="myform" id="myform" action="">
						  
						  <input type="hidden" name="characteristicNames" id="characteristicNames" value=<%=characteristic%>>
			    <input type="hidden" name="retnCharIds" id="retnCharIds" value=<%=retCharId%>>
				<input type="hidden" name="radCharIds" id="radCharIds" value=<%=radCharId%>>
							<td width="369" class="tblMainHead">Species  <select name="uType" class="selectboxOne" onchange="postData(); " >
                                                <option selected="selected" value="">Select One</option>

                                                 <%
                                                          Vector vUType = (Vector) request.getAttribute("displayUserTypeDetails");
                                                            if (vUType != null && vUType.size() != 0) {
                                                                Enumeration eUtype = vUType.elements();
                                                                //String [] userType = {ID, name };
                                                                while (eUtype.hasMoreElements()) {
                                                                    String[] strType = (String[]) eUtype.nextElement();
                                                                    String uTypeID = strType[0];
                                                                    String uTypeName = strType[1];
                                                                    if (uTypeID.equals(uTypeId)) {

                                                %>
                                                <option value="<%=uTypeID%>" selected="selected" ><%=uTypeName%></option>
                                                <%
                                                 } else {
                                                %>
                                                <option value="<%=uTypeID%>" ><%=uTypeName%></option>
                                                <%
                                                                    }
                                                                }
                                                            }
                                                %>
                                            </select>
							  <input type="button" value="Add" onclick="addRow()" class="gradBtn" align="right"/>
                                            <input type="button" value="Edit" onclick="editRow('dataTable')" class="gradBtn" align="right"/>
                                            <input type="button" value="Delete" onclick="deleteRow('dataTable')" class="gradBtn" align="right"/>
                            </td>
							</form>
							
							<td width="400" class="tblMainHead">
							
							<div id="showBreedHead">
							<strong>Characteristic:</strong>
							<span class="alignLeft">
							<input name="characteristic" type="text" class="textboxOne" value="<%=characteristic%>"readonly="true" maxlength="120"/>
							</span>  <input type="button" value="Add" onclick="addCharDetRow()" class="gradBtn" align="right"/>
                                            <input type="button" value="Edit" onclick="editCharDetRow('dataTable')" class="gradBtn" align="right"/>
                                            <input type="button" value="Delete" onclick="deleteCharDetRow('dataTable')" class="gradBtn" align="right"/>
							</div>
							</td>
							
						  </tr>			
						
						
			<form name="frmBreedCharacteristicsDetailsMgt" id="frmBreedCharacteristicsDetailsMgt" action="BreedDetails.do" >
						 <input type="hidden" name="breedProcess" value="initbreedCategoryDets" />
						</form> 
						
				
                                            
				 <tr>
                        				    
                                            <td>
											
		<table id="dataTable" border="0" align="left" cellpadding="0" cellspacing="1" style="table-layout:fixed">									
						
		                           <tr> <form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="BreedDetails.do" >
								     <input type="hidden" name="reDirectVal" value="frmMgt"/>	
                                                        <td width="20" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAll()"/></td>
                                                        <td width="278" height="27" class="tblRowHeadTwo">Characteristic </td>
                                                        <td width="100" height="27" class="tblRowHeadTwo">Active</td>
                                                        <td width="100" height="27" class="tblRowHeadTwo">Inactive</td>
														<td width="50" height="27" class="tblRowHeadTwo">&nbsp;</td>

                                                


                                                         <input type="hidden" name="breedProcess" value="delete" />
                                                         <input type="hidden" name="uType" value>
				<input type="hidden" name="characteristicName" id="characteristicName" value=<%=characteristic%>>
			    <input type="hidden" name="retnCharId" id="retnCharId" value=<%=retCharId%>>
				<input type="hidden" name="radCharId" id="radCharId" value=<%=radCharId%>>

                                                  
                                                               <%
                                                               

                                                                    ArrayList lst = (ArrayList) request.getAttribute("list");
                                                                   
                                                                    if (lst!=null && lst.size() != 0) {
                                                                                                                                          Iterator itr = lst.iterator();
                                                                        while (itr.hasNext()) {
                                                                            HLCBreedVO donObj = (HLCBreedVO) itr.next();
                                                                            String charId = donObj.getCharId();
                                                                            String flag      =donObj.getFlag();
                                                                            String status = donObj.getCharStatus();
                                                                            String charDesc = donObj.getCharDesc();
                                                                            System.out.println("" + status);
                                                                         %>
                                                                         
                                                         <input type="hidden" name="charId" value="<%=charId%>" >
                                                         <input type="hidden" name="flag" value="<%=flag%>" >
                                                        <tr>
                                                            
							
                                                           
                                                            <td  class="listCellBg" style="text-align:right"><input type="checkbox" name="chk" id="chk" value="<%=charId%>"/></td>
                                                            <td height="26" class="listCellBg"  style="text-align:center;word-wrap:break-word"><%=charDesc%></td>

                                                            
                                                             <%

                                                                        if(Integer.parseInt(status)==1)
                                                                        {
                                                                            %>
                                                                            <td  width="40" class="listCellBg"style="text-align:center" ><input type="radio" name=<%=charId%> value="1" checked="true" disabled="true" /></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center"><input type="radio" name=<%=charId%> value="0" disabled="true"/></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td width="40" class="listCellBg"style="text-align:center" ><input type="radio" name=<%=charId%> value="1" disabled="true"/></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center"><input type="radio" name=<%=charId%> value="0" checked="true" disabled="true"/></td>
                                                                              <% } %>
																			  
																			  
			<%
			System.out.println("charId in page"+charId);

			if(charId!=null && charId.equalsIgnoreCase(radCharId)){%>			
																			  
								<td width="31" height="27" class="tblRowHeadTwo"><input type="radio" name="radSel" id="radSel" value="<%=charId+"#"+uTypeId%>" checked="checked" onClick="submitform(this.value)"/>		</td>
								
								<%}else{%>
								
								<td width="91" height="27" class="tblRowHeadTwo"><input type="radio" name="radSel" id="radSel" value="<%=charId+"#"+uTypeId%>" onClick="submitform(this.value)"/>		</td>
								
								<%}%>																  
																			  

                                                            
                                                        </tr>

                                                        <% }%>
														
												<input type="hidden" name="radSize" id="radSize" value="<%=lst.size()%>" >	
												
												  
												 		
														
                                                            <%       } else {%>
                                                        <tr>
                                                           
                                                            <td height="20" colspan="5"  class="listCellBg"><div class="alignCenter"><strong>No Records Found!</strong></div></td>
                                                        </tr>
                                                        <% }%>
                                                        </form>
                                                        </tr>
					   
                                                         <% String statuscheck = (String)request.getAttribute("err");
					                    if(statuscheck!=null && statuscheck.equals("st")){
					                  %>

					                 <tr>
					                 <td colspan="2" class="styleError"><strong>Breed Details Can not Delete. Try  Again.</strong></td>
					                </tr>
					                        <%
					                           }
					                       %>
                                                       

                                                   
                                                    
					</table>					 
						 </td>
			
			
					 <td class="td">
						
		<table id="dataTable" border="0" align="center" cellpadding="0" cellspacing="1" style="table-layout:fixed">	
		 <tbody id="showBreed">   
		 	<% 
	
	//String speciesName = (String) request.getAttribute("speciesName");
	%>
      		
		   <form name="frmBreedCharDetList" id="frmBreedCharDetList" method="post" action="BreedDetails.do">
		     <input type="hidden" name="reDirectVal1" value="frmMgt"/>	
			 <input type="hidden" name="characterisName" id="characterisName" value=<%=characteristic%>>
			   
			 
                                                        <tr><td width="20" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkCharDetAll()"/></td>

                                                        <td width="380" height="27" class="tblRowHeadTwo">Detail </td>
                                                        <td width="50" height="27" class="tblRowHeadTwo">Active</td>
                                                        <td width="50" height="27" class="tblRowHeadTwo">Inactive</td></tr>

                                                 <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->



                                                         <input type="hidden" name="breedProcess" value="deleteBrdChar" />
                                                         <input type="hidden" name="userTypeId" value="<%=uTypeId%>">
														 <input type="hidden" name="characId" value="<%=retCharId%>" >
										<input type="hidden" name="radCharacId" id="radCharacId" value=<%=radCharId%>>

                                                    <!--End:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                                               <%
                                         Vector v = (Vector)request.getAttribute("displayBreedCharDetails");
                                                                        if(v!=null && v.size()!=0){
			                                                            Enumeration it = v.elements();
			                                                             while (it.hasMoreElements()) {
				                                                            String[] s = (String[]) it.nextElement();
				                                                                   String characterdetId= s[0];
				                                                                   String details= s[1];
                                                                                   String active_status=s[2];

                                                                                int chkBoxIndex = 0;

				                                                        %>
                                                         <input type="hidden" name="characterdetId" value="<%=characterdetId%>" >

                                                        <tr>

                                                            <td  class="listCellBg" style="text-align:right"><input type="checkbox" name="chk" id="chk" value="<%=characterdetId%>"/></td>
                                                            <td height="26" class="listCellBg"  style="text-align:center;word-wrap:break-word"><%=details%></td>


                                                             <%

                                                                        if(Integer.parseInt(active_status)==1)
                                                                        {
                                                                            %>
                                                                            <td  width="40" class="listCellBg"style="text-align:center" ><input type="radio" name=<%=characterdetId%> value="1" checked="true" disabled="true" /></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center"><input type="radio" name=<%=characterdetId%> value="0" disabled="true"/></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td width="40" class="listCellBg"style="text-align:center" ><input type="radio" name=<%=characterdetId%> value="1" disabled="true"/></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center"><input type="radio" name=<%=characterdetId%> value="0" checked="true" disabled="true"/></td>
                                                                              <% } %>

                                                            <!--Breed For  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                                        </tr>

                                                        <% }

                                                                   } else {%>
                                                        <tr>
                                                           <% System.out.print("user type" + uTypeId);%>
                                                            <td height="20" colspan="5"  class="listCellBg"><div class="alignCenter"><strong>No Records Found!</strong></div></td>
                                                        </tr>
                                                        <% }%>
                                                        </form>
	
					 <tr>
                                                          
                                                    </tr>
							</tbody>			   
	
					</table>					 
						 </td>
						
						 
						
						</tr>
					
				
				  
				   <tr>
					<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
				  </tr>
		</table>
	
	
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
