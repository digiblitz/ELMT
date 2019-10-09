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
<script src="javascripts/frmSpeciesBreedMgt.js" type="text/javascript" ></script>


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
String refcnt=(String) request.getAttribute("refcnt");
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
    <td colspan="2" class="tblMainHead"><strong></strong> Membership: <span class="styleBoldTwo">Species and Breed Management </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"><br />
	    <br />
		You can manage <strong>Species</strong> and <strong>Breeds</strong> by clicking the common radio buttons. <br />
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
							<td width="369" class="tblMainHead">Species &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <input type="button" value="Add" onclick="addSpeciesRow()" class="gradBtn" align="right"/>
                                            <input type="button" value="Edit" onclick="editSpeciesRow('dataTable')" class="gradBtn" align="right"/>
                                            <input type="button" value="Delete" onclick="deleteSpeciesRow('dataTable')" class="gradBtn" align="right"/>
                            </td>
							
							<td width="400" class="tblMainHead">
							
							<div id="showBreedHead">
							Breeds 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Add" onclick="addBreedRow()" class="gradBtn" align="right"/>
                                            <input type="button" value="Edit" onclick="editBreedRow('dataTable')" class="gradBtn" align="right"/>
                                            <input type="button" value="Delete" onclick="deleteBreedRow('dataTable')" class="gradBtn" align="right"/>
							</div>
							</td>
							
						  </tr>			
						
						
						<form name="frmSpeciesBreedMgt" id="frmSpeciesBreedMgt" action="BreedDetails.do" >
						 <input type="hidden" name="breedProcess" value="speciesBreedsMgt" />
						</form> 
						
				<%String retSpeciesId = (String) request.getAttribute("speciesId");%>
                                            
				 <tr>
                        				    
                                            <td>
											
		<table id="dataTable" border="0" align="left" cellpadding="0" cellspacing="1" style="table-layout:fixed">									
						
		  <tr> <form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="BreedDetails.do" >				
			 <input type="hidden" name="breedProcess" value="speciesView" />
			  <input type="hidden" name="reDirectVal1" value="frmMgt"/>	
			  
			   
         <td width="20" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAllSpecies()"/></td>
                                                         <td width="278" height="27" class="tblRowHeadTwo">Species </td>
                                                        <td width="100" height="27" class="tblRowHeadTwo">Active</td>
                                                        <td width="100" height="27" class="tblRowHeadTwo">Inactive</td>	
														<td width="50" height="27" class="tblRowHeadTwo">&nbsp;</td>			
					
		  <%
                                                                
                                                   ArrayList lst1 = (ArrayList) request.getAttribute("specieslist");
                                                                    
                                                                    if (lst1!=null && lst1.size() != 0) {
                                                                       
                                                                        Iterator itr1 = lst1.iterator();
                                                                        while (itr1.hasNext()) {
                                                                            HLCBreedVO donObj1 = (HLCBreedVO) itr1.next();
                                                                             String flag = donObj1.getFlag();
                                                                            String speciesId = donObj1.getUserTypeId();
                                                                            String SpeciesName = donObj1.getUserTybeName();
                                                                            String speciesStatus = donObj1.getUserTypeStatus();
                                                                                                        
                                                                         %>
                                                                        
                                                         <input type="hidden" name="speciesId" value="<%=speciesId%>" />
                                                         <input type="hidden" name="flag" value="<%=flag%>" />			
					
					
				
                                                        <tr>
														
                                                           
                                                            <td  class="listCellBg" style="text-align:left"><input type="checkbox" name="chk"   value="<%=speciesId%>"/></td>
                                                            <td height="26" class="listCellBg"  style="text-align:center;word-wrap:break-word"><%=SpeciesName%></td>

                                                           
                                                             <%

                                                                        if(Integer.parseInt(speciesStatus)==1)
                                                                        {
                                                                            %>
                                                          <td  width="51" class="listCellBg"style="text-align:center" ><input type="radio" name=<%=speciesId%> value="1" checked="true" disabled="true" /></td>
                                                          <td width="47" class="listCellBg" style="text-align:center"><input type="radio" name=<%=speciesId%> value="0" disabled="true"/></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                               <td width="20" class="listCellBg"style="text-align:center" ><span class="listCellBg" style="text-align:center">
                                                                                 <input type="radio" name=<%=speciesId%> value="1" disabled="true"/>
                                                          </span></td>
                                                                               <td width="22" class="listCellBg" style="text-align:center"><input type="radio" name=<%=speciesId%> value="0" checked="true" disabled="true"/></td>
                                                                              <% } %>
																			  
							
				<%if(speciesId!=null && speciesId.equalsIgnoreCase(retSpeciesId)){%>			
																			  
								<td width="31" height="27" class="tblRowHeadTwo"><input type="radio" name="radSel" id="radSel" value="<%=speciesId%>" checked="checked" onClick="submitform(this.value)"/>		</td>
								
								<%}else{%>
								
								<td width="91" height="27" class="tblRowHeadTwo"><input type="radio" name="radSel" id="radSel" value="<%=speciesId%>" onClick="submitform(this.value)"/>		</td>
								
								<%}%>
										  
                                                        </tr>  
		
                                                        <% }%>
														
										<input type="hidden" name="radSize" id="radSize" value="<%=lst1.size()%>" />					
														
														<%
                                                                   } else {%>
                                                        <tr>
                                                           
                                                            <td height="20" colspan="8"  class="listCellBg"><div class="alignCenter"><strong>No Records Found!</strong></div></td>
                                                        </tr>
                                                        <% }%>	
						
					
		
					</form>
					</tr>	
					   <tr>
                                                         <% String statuscheck1 = (String)request.getAttribute("err");
					                    if(statuscheck1!=null && statuscheck1.equals("st")){
					                  %>
			                    <tr>
					                 <td colspan="8" class="styleError"><strong>species Details Can not Delete. Try  Again.</strong></td>
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
	
	String speciesName = (String) request.getAttribute("speciesName");
	%>
      		
		   <tr> <form name="frmMyForm" id="frmMyForm" action="BreedDetails.do" >
		    <input type="hidden" name="reDirectVal" value="frmMgt"/>
			 <input type="hidden" name="uTypeId" value="<%=retSpeciesId%>" />	
			  <input type="hidden" name="retSpeciesName" value="<%=speciesName%>" />	
	
				<tr>
							<td class="tableLeft" colspan="1"><strong>Species:</strong>
							
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font color="#CC0066"><%=speciesName%></font></strong>
					 </td>									
				</tr>
				  
				
		
		  
		 
            <td width="63" class="tblRowHead"><input type="checkbox" name="chkAll" onClick="checkAllBreed()"/></td>
                                                         <td width="128" height="27" class="tblRowHeadTwo">Breed Description </td>
                                                        <td width="50" height="27" class="tblRowHeadTwo">Active</td>
                                                        <td width="82" height="27" class="tblRowHeadTwo">Inactive</td>	
														
														    <input type="hidden" name="breedProcess" value="delete" />
                                                         <input type="hidden" name="uType" value/>	
														
															
														
		   <%
                                                              

                                                                    ArrayList lst = (ArrayList) request.getAttribute("list");
                                                                    //request.setAttribute("list", null);                                                               
                                                                    if (lst!=null && lst.size() != 0) {
                                                                     
                                                                        Iterator itr = lst.iterator();
                                                                        while (itr.hasNext()) {
                                                                            HLCBreedVO donObj = (HLCBreedVO) itr.next();
                                                                            String breedId = donObj.getBreedId();
                                                                            String status = donObj.getBreedStatus();
                                                                            String breedDesc = donObj.getBreedDesc();
                                                                            System.out.println("" + status);                                                    
                                                                         %>
                                                         <input type="hidden" name="breedId" value="<%=breedId%>" />
					
				
                                                        <tr>
                                                           
                 <td class="listCellBg" style="text-align:left"><input type="checkbox" id="chk" name="chk" value="<%=breedId%>"/></td>
                                                            <td height="26" class="listCellBg" style="text-align:left;word-wrap:break-word;">

															<%=breedDesc%>

															</td>

                                                        
                                                             <%

                                                                        if(Integer.parseInt(status)==1)
                                                                        {
                                                                            %>
                                       <td  width="50" class="listCellBg" style="text-align:center"><input type="radio" name=<%=breedId%> value="1" checked="true" disabled="true" /></td>
                                                          <td width="82" class="listCellBg" style="text-align:center"><input type="radio" name=<%=breedId%> value="0" disabled="true"/></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                          <td width="20" class="listCellBg"  style="text-align:center"><input type="radio" name=<%=breedId%> value="1" disabled="true"/></td>
                                                          <td width="50" class="listCellBg"  style="text-align:center"><input type="radio" name=<%=breedId%> value="0" checked="true" disabled="true"/></td>
                                                                              <% } %>
		  
                                                          
                                                         </tr>  



                                                        <% }
                                                                   } else {%>
                                                        <tr>
                                                         
                                                            <td height="20" colspan="8"  class="listCellBg"><div class="alignCenter"><strong>No Records Found!</strong></div></td>
                                                        </tr>
                                                        <% }%>
														
															
	
															
                                                        </form>
                                </tr>
	
					
                                                         <% String statuscheck = (String)request.getAttribute("err");
					                    if(statuscheck!=null && statuscheck.equals("st")){
					                  %>

			                    <tr>
					                 <td colspan="8" class="styleError"><strong>Breed Details Can not Delete. Try  Again.</strong></td>
				                </tr>
					                        <%
					                           }
					                       %>
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
