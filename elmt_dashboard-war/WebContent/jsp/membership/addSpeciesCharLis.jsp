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
                var typId = document.frmAddBreedDet.uType.value ;
                strURL = "./BreedDetails.do?breedProcess=initCharView&uTypeId="+typId;
                window.location.href = strURL;

            }

function editRow(tableID)

            {
				 var typId = document.frmAddBreedDet.uType.value ;
				if(typId==""){
					alert("Please select valid Species");
					return;
				}
                var charId;
                var chkCnt=0;
                var vFlag = false;
                var chk=document.getElementById('chk');
                if(chk==null && typId!="")
                {
                    alert("No Records Found");
                    return;
                }
                var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmRoleMgtListRole.chk[i].checked==true)
                        {
                            charId = document.frmRoleMgtListRole.chk[i].value;
                            chkCnt++;
                        }

                       
                    }
                }
                else
                {

                        if(document.frmRoleMgtListRole.chk.checked==true)
                        {
                            charId = document.frmRoleMgtListRole.chk.value;
                            chkCnt++;
                        }

                      
                }
				//Starts for checkbox 
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmRoleMgtListRole.chk[j].checked = false;
                        document.frmRoleMgtListRole.chkAll.checked = false;
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

                strURL = "./BreedDetails.do?charId="+charId+"&breedProcess=SpeciesEdit&Submit2=Edit";
                window.location.href = strURL;


            }
       
 function deleteRow(tableID) {


      var typId = document.frmAddBreedDet.uType.value ;
				if(typId==""){
					alert("Please select valid Species");
					return;
				}
	 var breedId;
	var chkCnt=0;
	var vFlag = 0;
	var cannotDelete=0;
	var canDelete = 0;
        var chk=document.getElementById('chk');
                if(chk==null && typId!="")
                {
                    alert("No Records Found");
                    return;
                }
	var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
	if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmRoleMgtListRole.chk[i].checked==true)
			{
				   if(document.frmRoleMgtListRole.flag[i].value==0)
				   {
					cannotDelete++;
					document.frmRoleMgtListRole.chk[i].checked = false;
					//For Debug Starts
                                          document.frmRoleMgtListRole.chkAll.checked = false;
                                         //For Debug Ends
				   }

				   if(document.frmRoleMgtListRole.flag[i].value==1)
				   {
					canDelete++;
				   }
			}

			if(cannotDelete > 0 && canDelete > 0)
			{
							vFlag = 1;
			}
			else if(cannotDelete > 0 && canDelete == 0)
			{
							vFlag = 2;
			}
			else if(cannotDelete == 0 && canDelete == 0)
			{
							vFlag = 3;
			}
			else if(cannotDelete == 0 && canDelete > 0)
			{
							vFlag = 4;

			}
		}
	}
	else
	{
			if(document.frmRoleMgtListRole.chk.checked==true)
			{
				   if(document.frmRoleMgtListRole.flag.value==0)
				   {
					cannotDelete++;
					document.frmRoleMgtListRole.chk.checked = false;
					//For Debug Starts
                                          document.frmRoleMgtListRole.chkAll.checked = false;
                                         //For Debug Ends
				   }

				   if(document.frmRoleMgtListRole.flag.value==1)
				   {
					canDelete++;
				   }
			}

			if(cannotDelete > 0 && canDelete == 0)
			{
							vFlag = 2;
			}
			else if(cannotDelete == 0 && canDelete == 0)
			{
							vFlag = 3;
			}
			else if(cannotDelete == 0 && canDelete > 0)
			{
							vFlag = 4;

			}

	}

	if(vFlag == 1)
	{
		confirm("Only the checked records will be deleted. Click the Delete button again");
	}

	if(vFlag == 2)
	{
	   alert("Checked records cannot be deleted as it is being referred or mapped.");

	}

	if(vFlag == 3)
	{
		alert("Please check the record(s) to be deleted");
	}

	if(vFlag == 4)
	{
		if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these Breed Characteristic?"))
		{
			document.frmRoleMgtListRole.breedProcess.value = 'deleteChar';
                         document.frmRoleMgtListRole.uType.value = document.frmAddBreedDet.uType.value;
			 document.frmRoleMgtListRole.submit();
		}
	}

}

function checkAll()
{

       if(document.frmRoleMgtListRole.chk!=null){
       var chkBoxCnt = document.frmRoleMgtListRole.chk.length;

       if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
       {
            for(var i=0;i<chkBoxCnt;i++)
            {

                 if(document.frmRoleMgtListRole.chkAll.checked==true)
                 {

                         document.frmRoleMgtListRole.chk[i].checked = true;
                 }
                 else
                 {
                        document.frmRoleMgtListRole.chk[i].checked = false;
                 }
            }
       }
       else
       {
             if(document.frmRoleMgtListRole.chkAll.checked==true)
             {

                     document.frmRoleMgtListRole.chk.checked = true;
             }
             else
             {
                    document.frmRoleMgtListRole.chk.checked = false;
             }
        }
	}
}


function postData()
{

                var typId = document.frmAddBreedDet.uType.value ;
                //var memType = document.frmAddBreedDet.membTxt.value;


                strURL = "./BreedDetails.do?breedProcess=charList&uTypeId="+typId;
                window.location.href = strURL;


 }
        </script>

    </head>
    <%

                String uTypeId1 = (String) request.getAttribute("uTypeId");

                if (uTypeId1 != null && !uTypeId1.equals("") && uTypeId1.trim().length() > 0) {

                }
                else
                    {
                         uTypeId1 = "";

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


                                <td width="700" class="subDeptTablePad"><!-- CONTENTS START HERE -->
                                    <table  border="1" cellpadding="0" cellspacing="0" width="100%" align="center" class="formLayout">
                                        <form name="frmAddBreedDet" id="myform" action="">
                                           <tr>
    <td colspan="2" class="tblMainHead">
   Breed Characteristics - Category: <span class="styleBoldTwo"> Listings</span></td>
  </tr>
                                            <tr> <td colspan="4" class="tblDescrp">
                   You are viewing the list of Breed Characteristics - Category created by you.<br/> To create a Breed Characteristic - Category click <strong>Add</strong> button.<br/>To edit a Breed Characteristic - Category select the checkbox before each record and then click on the<strong>Edit</strong> button.<br/>To delete Breed Characteristics - Category select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.</td></tr>

				    <tr>
	  <td height="30">
	 <table border="0" cellpadding="0" align="center" cellspacing="0"  width="620">

                                        <tr><td><strong>Species:</strong>
                                            <select name="uType" class="selectboxOne" onchange="postData(); " >
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
                                                                    if (uTypeID.equals(uTypeId1)) {

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

											</td>


                                            </form>




                                            <td align = "right">
                                            <input type="button" value="Add" onclick="addRow()" class="gradBtn" align="right"/>
                                            <input type="button" value="Edit" onclick="editRow('dataTable')" class="gradBtn" align="right"/>
                                            <input type="button" value="Delete" onclick="deleteRow('dataTable')" class="gradBtn" align="right"/>
                                            </td>
                                        </tr>
										</table>


                                        <tr>


                                            <td><table id="dataTable" width="620" border="0" align="center" cellpadding="0" cellspacing="1">

                                                    <tr> <form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="BreedDetails.do" >
                                                        <td width="18" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAll()"/></td>
                                                        <td width="400" height="27" class="tblRowHeadTwo">Characteristic </td>
                                                        <td width="50" height="27" class="tblRowHeadTwo">Active</td>
                                                        <td width="50" height="27" class="tblRowHeadTwo">Inactive</td>




                                                         <input type="hidden" name="breedProcess" value="delete" />
                                                         <input type="hidden" name="uType" value/>


                                                               <%


                                                                    ArrayList lst = (ArrayList) request.getAttribute("list");
                                                                    request.setAttribute("list", null);
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

                                                         <input type="hidden" name="charId" value="<%=charId%>" />
                                                         <input type="hidden" name="flag" value="<%=flag%>" />
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


                                                        </tr>

                                                        <% }
                                                                   } else {%>
                                                        <tr>

                                                            <td height="20" colspan="5"  class="listCellBg"><div class="alignCenter"><strong>No Records Found</strong></div></td>
                                                        </tr>
                                                        <% }%>
                                                        </form>
                                                        </tr>

                                                    <tr>
                                                         <% String statuscheck = (String)request.getAttribute("err");
					                    if(statuscheck!=null && statuscheck.equals("st")){
					                  %>

					                 <tr>
					                 <td colspan="2" class="styleError"><strong>Breed Details Can not Delete. Try  Again.</strong></td>
					                </tr>
					                        <%
					                           }
					                       %>
                                                        <!--<tr>-->

                                                        <!--</tr>-->

                                                      <td height="19" colspan="5">&nbsp;</td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                    </table>
                                    <!-- CONTENTS END HERE -->
                                </td>
                        </tr>
                    </table></td>
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
