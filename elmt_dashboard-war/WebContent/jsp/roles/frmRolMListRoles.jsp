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
<%@ page import="java.util.*" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->

<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
<script language="javascript">
 function addRow()
{
	strURL = "./roles.do?roleProcess=initCreateRole";
	window.location.href = strURL;
}

function editRow(tableID)
{
	var roleId;
	var chkCnt=0;
	var vFlag = false;
	var chkBoxCnt = document.frmRoleMgtListRole.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmRoleMgtListRole.chk[i].checked==true)
			{
				   roleId = document.frmRoleMgtListRole.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.frmRoleMgtListRole.chk.checked==true)
			{
				   roleId = document.frmRoleMgtListRole.chk.value;
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

                strURL = "./roles.do?roleId="+roleId+"&roleProcess=initeditRole&Submit2=Edit";
		window.location.href = strURL;
}
    function deleteRow(tableID) {

	var roleId;
	var chkCnt=0;
	var vFlag = 0;
	var cannotDelete=0;
	var canDelete = 0;
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
		if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		{
			 document.frmRoleMgtListRole.roleProcess.value = 'deleteRole';
			 document.frmRoleMgtListRole.submit();
		}
	}

}

/*function multiChkBoxValidation(tableID)
{
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var chkCnt = 0;

            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                var rolename = row.cells[1].childNodes[0];
                var roledesc = row.cells[2].childNodes[0];
                var activeradio = row.cells[3].childNodes[0];
                var inactiveradio = row.cells[4].childNodes[0];

                if(null != chkbox && true == chkbox.checked)
                {

					if(document.frmRoleMgtListRole.chk.checked==true)
					{
						alert("First enter the details for new Role and click Save button");
						chkbox.checked = false;
						return;

					}

                }
				else{

						rolename.disabled =true;
						roledesc.disabled =true;
						activeradio.disabled=true;
						inactiveradio.disabled =true;

				}


            }
}*/

function checkAll()
{

		var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
		if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
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

    </script>
<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
</head>

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
    <td class="tableCommonBg"><table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
      <tr>
        <!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                      <%--<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->

<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>--%>
                    <!--Ends:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
        <td width="500" class="subDeptTablePad"><!-- CONTENTS START HERE -->
              <table  border="1" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				<tr>
                     <td colspan="5" class="tblMainHead"> Maintain Roles:<span class="styleBoldTwo">Listings  </span></td>
                </tr>
	<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                <tr>
                  <td colspan="5" class="tblDescrp">
You are viewing the list of Roles created by you.<br/> To create a Role click <strong>Add</strong> button.<br/>To edit a Role select the checkbox before each record and then click on the  <strong>Edit</strong> button.<br/>To delete Roles select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.</td>
                </tr>
                <tr height="30">
                    <td align = "right">
                        <input type="button" value="Add" onclick="addRow()" class="gradBtn" align="right"/>
                        <input type="button" value="Edit" onclick="editRow('dataTable')" class="gradBtn" align="right"/>
                        <input type="submit" value="Delete" onclick="deleteRow('dataTable')" class="gradBtn" align="right" name="del"/>
                    </td>
                </tr>

	<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                <tr>
	<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                  <!--<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">-->
                  <td><table id="dataTable" width="760" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout" style='table-layout:fixed'>
	<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->

	<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                      <!--<tr>
                        <td width="278" height="27" class="tblRowHead">Name of Role </td>
                        <td width="93" colspan="2" class="tblRowHead">Edit</td>
                      </tr>-->
       			<form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="roles.do" >
                        <input type="hidden" name="roleProcess" value="initeditRole" />

                      <tr>
                          <td width="20" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAll()"> </td>
                        <td width="200" height="27" class="tblRowHeadTwo">Role </td>
                        <td width="350" height="27" class="tblRowHeadTwo">Description </td>
                       <td width="50" height="27" class="tblRowHeadTwo">Active</td>
				<td width="50" height="27" class="tblRowHeadTwo">Inactive</td>
                      </tr>
	<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
					   <%
						 ArrayList roleList = (ArrayList) request.getAttribute("allRoleList");
          					 if(roleList!=null && roleList.size()!=0){
							Iterator it = roleList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String roleId = s[0];
								String roleName = s[1];
							//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                                                                String roledesc=s[2];
                                                                String status=s[3];
                                                                String flag=s[4];
                                                                int chkBoxIndex = 0;
							//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011

		                %>
						<input type="hidden" name="roleId" value="<%=roleId%>" />

						<tr>
							<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<%--<td height="26" class="listCellBg"><%=roleName%></td>
							<td colspan="2" class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="Edit" /></td>--%>
							<!--<td class="listCellBg"><input name="Submit2" type="submit" class="twoBtn" value="Deactivate" /></td> -->
                                                   <td  class="listCellBg"><input type="checkbox" name="chk" value ="<%=roleId%>" /></td>
							<td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;" ><%=roleName%></td>
                                                        <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;" ><%=roledesc%></td>


                                                        <%

                                                                        if(Integer.parseInt(status)==1)
                                                                        {
                                                                            %>
                                                                            <td  width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=roleId%>" value="1" checked="true" disabled="true" /></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=roleId%>" value="0"  disabled="true" /></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=roleId%>" value="1"  disabled="true" /></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=roleId%>" value="0" checked="true" disabled="true"  /></td>
                                                                              <% } %>
                                <input type ="hidden" name ="flag" value ="<%=flag%>"/>
                                <!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
        	              </tr>
						<%
                                                //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                                                chkBoxIndex++;
                                                //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                                                        }
					 }
					  else {%>
							
                                          <tr></tr>
						 <tr><td colspan="5" height="27" align="center" class="tblRowHeadTwo">	No Records are found</td>
						</tr> 
					<% } %>
                                        </form>

                                                  </tr>
						<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->




                      <tr>
					  
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
