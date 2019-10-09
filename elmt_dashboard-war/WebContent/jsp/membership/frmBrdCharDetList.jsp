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
<%-- 
    Document   : frmBrdCharDetList
    Created on : Apr 12, 2011, 5:09:59 PM
    Author     : k11
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.hlcmrm.util.HLCBreedVO"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <title><%=(String) session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
<!--<script src="javascripts/validate.js" type="text/javascript" ></script>-->
        <!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->

        <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
        <script language="javascript">
function addRow() {
                var uTypeId = document.frmBreedCharSpecieSelect.uTypeId.value ;
                var charId = document.frmBreedCharSpecieSelect.charId.value ;
                if(uTypeId=="" && charId==""){
                strURL = "./BreedDetails.do?breedProcess=initInsert";
                window.location.href = strURL;
                }
                else{
                   if(uTypeId!="" && charId==""){
                       strURL = "./BreedDetails.do?breedProcess=initInsert&uTypeId="+uTypeId+"&charId="+null;
                       window.location.href = strURL;
                   }
                   else{
                      strURL = "./BreedDetails.do?breedProcess=initInsert&uTypeId="+uTypeId+"&charId="+charId;
                      window.location.href = strURL;
                   }
                }
            }
function checkAll()
{
	       if(document.frmBreedCharDetList.chk!=null){
       var chkBoxCnt = document.frmBreedCharDetList.chk.length;
       if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
       {
            for(var i=0;i<chkBoxCnt;i++)
            {
                  if(document.frmBreedCharDetList.chkAll.checked==true)
                 {
                        document.frmBreedCharDetList.chk[i].checked = true;
                 }
                 else
                 {
                        document.frmBreedCharDetList.chk[i].checked = false;
                 }
            }
       }
       else
       {
             if(document.frmBreedCharDetList.chkAll.checked==true)
             {
                    document.frmBreedCharDetList.chk.checked = true;
             }
             else
             {
                    document.frmBreedCharDetList.chk.checked = false;
             }
        }
	}	
}
function editRow(tableID)

            {
                var characterdetId;
                var uTypeId = document.frmBreedCharDetList.userTypeId.value ;
                var charId = document.frmBreedCharDetList.characId.value ;
                if(uTypeId==""){
                    alert("Please select valid  Species");
					return;
                }
		if(charId=="" && uTypeId!=""){
		    alert("Please select valid  Characterisic");
			return;
		}
                var chkCnt=0;
                var vFlag = false;
                var chk=document.getElementById('chk');
                if(chk==null && uTypeId!="" && charId!=""){
		  alert("No Records Found");
		         return;
	         }
	        else{
                var chkBoxCnt = document.frmBreedCharDetList.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmBreedCharDetList.chk[i].checked==true)
                        {
                            characterdetId = document.frmBreedCharDetList.chk[i].value;
                            chkCnt++;
                        }
                    }
                }
                else
                {
                        if(document.frmBreedCharDetList.chk.checked==true)
                        {
                            characterdetId = document.frmBreedCharDetList.chk.value;
                            chkCnt++;
                        }

                }
				//Starts for checkbox 
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmBreedCharDetList.chk[j].checked = false;
                        document.frmBreedCharDetList.chkAll.checked = false;
                    }
                }
                //Ends for checkbox
                if(chkCnt==0){

                    alert("Please check one record to edit");
                    return;
                }

                if(vFlag == true)
                {
                    alert("Only one record can be edited.Please check one record to edit.");
                    return;
                }
               
			   var retCharId = document.frmBreedCharDetList.characId.value;
                strURL = "./BreedDetails.do?characterdetId="+characterdetId+"&breedProcess=editCharDet&retCharId="+retCharId;
		window.location.href = strURL;
          }
  }
function deleteRow(tableID) {


	 var characterdetId;
         var uTypeId = document.frmBreedCharDetList.userTypeId.value ;
                var charId = document.frmBreedCharDetList.characId.value;
				
                if(uTypeId==""){
                    alert("Please select valid Species");
					return;
                }
		if(charId=="" && uTypeId!=""){
		    alert("Please select valid Characterisic");
			return;
		}
	 var chkCnt=0;
	 var vFlag = 0;
	 var cannotDelete=0;
	 var canDelete = 0;
         var chk=document.getElementById('chk');
         if(chk==null && uTypeId!="" && charId!=""){
		  alert("No records found");
		  return;
	  }
	  else{
	 
	 var chkBoxCnt = document.frmBreedCharDetList.chk.length;

            if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
            {
                for(var i=0;i<chkBoxCnt;i++)
		{

                	if(document.frmBreedCharDetList.chk[i].checked==true)
			{


                          if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		                {
			 document.frmBreedCharDetList.breedProcess.value = 'deleteBrdChar';
                         document.frmBreedCharDetList.userTypeId.value = document.frmBreedCharDetList.userTypeId.value;
                          document.frmBreedCharDetList.characId.value = document.frmBreedCharDetList.characId.value;
			 document.frmBreedCharDetList.submit();

		                }
                           return;
			}

                }
            }
            else
            {
                	if(document.frmBreedCharDetList.chk.checked==true)
			{
                            if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		                {
			 document.frmBreedCharDetList.breedProcess.value = 'deleteBrdChar';
                         document.frmBreedCharDetList.userTypeId.value = document.frmBreedCharDetList.userTypeId.value;
                          document.frmBreedCharDetList.characId.value = document.frmBreedCharDetList.characId.value;
			 document.frmBreedCharDetList.submit();

		                }
                            return;
			}

            }
         if(chkCnt==0)
               {

                    alert("Please check the record(s) to be deleted.");
                    return;
                }
          }
  }



function postData(bProcess){
	if(document.frmBreedCharSpecieSelect.uTypeId.value!=null){
                
		document.frmBreedCharSpecieSelect.breedProcess.value = bProcess;
		
		document.frmBreedCharSpecieSelect.method="post";
		document.frmBreedCharSpecieSelect.action="BreedDetails.do";
		document.frmBreedCharSpecieSelect.submit();
	}
	/*else{
		if(bProcess=="initSelectSpecies"){
			alert("Select any one Species");
			document.frmBreedCharSpecieSelect.uTypeId.focus();
		}
		else{
			alert("Select any one Species");
			document.frmBreedCharSpecieSelect.uTypeId.focus();
		}
	}*/
}
        </script>

    </head>
    <%

                String uTypeId = (String) request.getAttribute("uTypeId");

                if (uTypeId == null) {

                    uTypeId = "";
				}
				String charId = (String) request.getAttribute("retCharId");
	          if(charId==null){
		                charId = "";
	             }


    %>

    <body>

        <table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
            <tr>
                <td class="alignTop"><!-- HEADER STARTS HERE -->
                    <%@ include file = "../../include/header.jsp" %>
                    <!-- HEADER ENDS HERE -->
                </td>
            </tr>
            <tr>
                <td class="infoBar"><!-- INFO BAR STARTS HERE -->
                    <%@ include file = "../../include/infobar.jsp" %>
                    <!-- INFO BAR ENDS HERE -->
                </td>
            </tr>
            <tr>
                <td class="tableCommonBg"><table width="760px" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                        <tr>
                           <!--<td width="230" class="menuTablePad">-->
			<!-- LEFT MENU STARTS HERE -->
			<!--<%//@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                        </td>-->
			<!-- LEFT MENU ENDS HERE -->
                            <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                <td width="700" class="subDeptTablePad"><!-- CONTENTS START HERE -->
                                    <table  border="1" cellpadding="0" cellspacing="0" width="100%" align="center" class="formLayout">
                                        <form name="frmBreedCharSpecieSelect" id="myform" method="post">
										<input type="hidden" name="breedProcess" value="" />
                      <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->                         <tr>
    <td colspan="2" class="tblMainHead">
	Breed Characteristics - Details: <span class="styleBoldTwo">Listings</span></td>
  </tr>   <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                            <tr> <td colspan="4" class="tblDescrp">
                                                    <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                  You are viewing the list of Breed Characteristics - Details created by you.<br/>To create a Breed Characteristic - Detail click <strong>Add</strong> button.<br/>To edit a Breed Characteristic - Detail select the checkbox before each record and then click on the <strong>Edit</strong> button.<br/>To delete Breed Characteristics - Details select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.
                                                </td></tr>
                                        <tr><td height="25" colspan="5" bgcolor="#ffffff" class="alignLeft">&nbsp;&nbsp;<strong>Species:</strong>
                                            <select name="uTypeId" class="selectboxOne" onchange="postData('listBreedCharDetail'); " >
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
                                            </select></td></tr>

											<tr>
							<td height="25" colspan="5" bgcolor="#ffffff" class="alignLeft">&nbsp;&nbsp;<strong>Characteristic:</strong>
							<span class="alignLeft">
							<select name="charId" id="charId" class="selectboxOne" onchange="postData('listBreedCharDetail');">
                                <option selected="selected" value="">Select One</option>
								<%
									Vector brdCharList = (Vector)request.getAttribute("displayBreedCharacters");
									if(brdCharList!=null && brdCharList.size()!=0){
                                                                            System.out.println("size"+brdCharList.size());
										Iterator itBrdCharList = brdCharList.iterator();
										while(itBrdCharList.hasNext()){
											String[] charList = (String [])itBrdCharList.next();
											String charID = charList[0];
											String character = charList[1];
											if(charId.equals(charID)){
												%>
													<option value="<%=charID%>" selected="selected"><%=character%></option>
												<%
												}
												else{
												%>
													<option value="<%=charID%>"><%=character%></option>
												<%
											}
										}
									}
								%>
								</select>
							</span></td>
						  </tr>
                                            <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->

                                            </form>
                                            <%-- <td colspan="5" class="tblMainHead">  Roles &amp; Privileges: <span class="styleBoldTwo">Role Listings  </span></td>--%>

                                            <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                            <tr height="30">
                                            <!--  <td colspan="5" class="tblDescrp"><br />
                                              You are viewing the list of <strong>Roles</strong> created by you. To	edit	a	role	click on the <strong>Edit</strong> button beside each record. To deactivate a role click on the <strong>'Deactivate'</strong> button beside the corresponding record. </td>
                                            </tr>-->
                                            <!--StartBreed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                            <td align = "right">
                                            <input type="button" value="Add" onclick="addRow()" class="gradBtn" align="right"/>
                                            <input type="button" value="Edit" onclick="editRow('dataTable')" class="gradBtn" align="right"/>
                                            <input type="button" value="Delete" onclick="deleteRow('dataTable')" class="gradBtn" align="right"/>
                                            </td>
                                        </tr>

                                        <!--End:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                        <tr>
                                            <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                            <!--<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">-->

                                            <td><table id="dataTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout" style="table-layout:fixed">
                                                    <!--End:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->

                                                    <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                                    <!--<tr>
                                                      <td width="278" height="27" class="tblRowHead">Name of Role </td>
                                                      <td width="93" colspan="2" class="tblRowHead">Edit</td>
                                                    </tr>-->
                                                  <form name="frmBreedCharDetList" id="frmBreedCharDetList" method="post" onsubmit="return entPrivValidate();" >
                                                        <tr><td width="20" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAll()"/></td>
                                                        <td width="430" height="27" class="tblRowHeadTwo">Detail </td>
                                                        <td width="50" height="27" class="tblRowHeadTwo">Active</td>
                                                        <td width="50" height="27" class="tblRowHeadTwo">Inactive</td></tr>

                                                 <!--Start:Breed  Addition, Editing and Deletion changes dated 10-Mar-2011-->



                                                         <input type="hidden" name="breedProcess" value="deleteBrdChar" />
                                                         <input type="hidden" name="userTypeId" value="<%=uTypeId%>"/>
														 <input type="hidden" name="characId" value="<%=charId%>" />

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
                                                         <input type="hidden" name="characterdetId" value="<%=characterdetId%>" />

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
                                                            <td height="20" colspan="5"  class="listCellBg"><div class="alignCenter"><strong>No Records Found</strong></div></td>
                                                        </tr>
                                                        <% }%>
                                                        </form>
                                                        <tr>
                                                           <td height="19" colspan="5">&nbsp;</td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                    </table>
                                    <!-- CONTENTS END HERE -->
                                </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td class="footerBg"><!-- FOOTER STARTS HERE -->
                    <%@ include file = "../../include/footer.jsp" %>
                    <!-- FOOTER ENDS HERE -->
                </td>
            </tr>
        </table>
    </body>
</html>
