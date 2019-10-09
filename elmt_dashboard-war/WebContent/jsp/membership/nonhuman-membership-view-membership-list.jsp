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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import = "java.math.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=(String) session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/validate.js" type="text/javascript" ></script>
        <script language="javascript">
            function postData1(){

                var typId = document.frmNonMemTypeList.uType.value ;
                var memType = document.frmNonMemTypeList.membTxt.value;
                if(typId!=""){
                    /*frmNonMemTypeList.memProcess.value = "memGetMemDet";
                                      frmNonMemTypeList.method="post";
                                      frmNonMemTypeList.action="nonmemberMaster.do?uTypeId="+uTypeId;
                                      frmNonMemTypeList.submit();*/

                    strURL = "./nonmemberMaster.do?memProcess=memGetMemDet&uTypeId="+typId+"&membTxt="+memType;
                    window.location.href = strURL;
                }
                else
                {
                    strURL = "./nonmemberMaster.do?memProcess=initList" ;
                    window.location.href = strURL;
                }
            }
            <!--Start:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 10-Mar-2011-->
            function valid2()
            {
                var table = document.getElementById("displayNonHumanMem");
                var rowCount = table.rows.length;
                var prompt=0;
                var cntChk=0

                for(var i=3; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];

                    if(null != chkbox && true == chkbox.checked) {
                        document.viewEdit.memId.value=chkbox.value;
                        var candelete=row.cells[0].childNodes[1];

                        if(candelete.value=='No')
                        {
                            prompt=1;
                            chkbox.checked=false;
                        }
                        else
                        {
                            cntChk++;
                        }
                    }
                }
                if(cntChk==0)
                {
                    alert("Selected Records are Invalid");
                    return(false);
                }
                else
                {
                    if(prompt==1)
                    {
                        alert("Only the checked Records Can be deleted");

                    }
                    return(true);
                }
            }

            <!--Start:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 10-Mar-2011-->
            function editRow(tableID)

            {
                if(document.frmNonMemTypeList.uType.value==""){
                    alert ("Please select valid Species" );
                    return false;
                }
                var memId;
                var chkCnt=0;
                var vFlag = false;
                var chk=document.getElementById('chk');
                if(chk==null)
                {
                    alert('No Records Found');
					return false;
                }


                var chkBoxCnt =document.frmNonMemTypeList.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmNonMemTypeList.chk[i].checked==true)
                        {
                            document.frmNonMemTypeList.memId.value = document.frmNonMemTypeList.chk[i].value;
                            chkCnt++;
                        }

                    }
                }
                else
                {

                    if(document.frmNonMemTypeList.chk.checked==true)
                    {
                        document.frmNonMemTypeList.memId.value = document.frmNonMemTypeList.chk.value;
                        chkCnt++;
                    }


                }

                //Starts for checkbox
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmNonMemTypeList.chk[j].checked = false;
                        document.frmNonMemTypeList.chkAll.checked = false;
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
                document.frmNonMemTypeList.memProcess.value='initEdit';
                document.frmNonMemTypeList.submit();

            }

            <!--Start:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 10-Mar-2011-->
            function deleteRow(tableID) {
                if(document.frmNonMemTypeList.uType.value==""){
                    alert ("Please select valid Species" );
                    return false;
                }
                var chkCnt=0;
                var vFlag = 0;
                var cannotDelete=0;
                var canDeleted = 0;
                if(document.frmNonMemTypeList.chk!=undefined && document.frmNonMemTypeList.chk!='undefined')
                    var chkBoxCnt = document.frmNonMemTypeList.chk.length;
                var chk=document.getElementById('chk');

                if(chk==null)
                {
                    alert('No Records Found');
                     return false;
                }
                if(chkBoxCnt!='undefined' && chkBoxCnt > 1)
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmNonMemTypeList.chk[i].checked==true)
                        {
                            if(document.frmNonMemTypeList.candelete[i].value=='No')
                            {
                                cannotDelete++;
                                document.frmNonMemTypeList.chk[i].checked = false;
                                //Debug Starts for check all
                                document.frmNonMemTypeList.chkAll.checked = false;
                                //Debug Ends for check all

                            }

                            if(document.frmNonMemTypeList.candelete[i].value=='Yes')
                            {
                                canDeleted++;
                            }
                        }

                        if(cannotDelete > 0 && canDeleted > 0)
                        {
                            vFlag = 1;
                        }
                        else if(cannotDelete > 0 && canDeleted == 0)
                        {
                            vFlag = 2;
                        }
                        else if(cannotDelete == 0 && canDeleted == 0)
                        {
                            vFlag = 3;
                        }
                        else if(cannotDelete == 0 && canDeleted > 0)
                        {
                            vFlag = 4;

                        }
                    }
                }
                else
                {
                    if(document.frmNonMemTypeList.chk.checked==true)
                    {
                        if(document.frmNonMemTypeList.candelete.value=='No')
                        {
                            cannotDelete++;
                            document.frmNonMemTypeList.chk.checked = false;
                            //Debug Starts for check all
                            document.frmNonMemTypeList.chkAll.checked = false;
                            //Debug Ends for check all
                        }

                        if(document.frmNonMemTypeList.candelete.value=='Yes')
                        {
                            canDeleted++;
                        }
                    }

                    if(cannotDelete > 0 && canDeleted == 0)
                    {
                        vFlag = 2;
                    }
                    else if(cannotDelete == 0 && canDeleted == 0)
                    {
                        vFlag = 3;
                    }
                    else if(cannotDelete == 0 && canDeleted > 0)
                    {
                        vFlag = 4;

                    }
                }

                if(vFlag == 1)
                {
                    confirm("Only checked records will be deleted, Click the Delete button again");
                    return false;
                }

                if(vFlag == 2)
                {
                    alert("Checked records cannot be deleted as it is being referred or mapped.");
                    return false;
                }

                if(vFlag == 3)
                {
                    alert("Please check the record(s) to be deleted");
                    return false;
                }

                if(vFlag == 4)
                {
                    if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
                    {
                        document.frmNonMemTypeList.memProcess.value = 'memTyDelete';
                        document.frmNonMemTypeList.submit();

                    }
                    return false;
                }
            }

            <!--Start:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 10-Mar-2011-->
            /*   function checkAll(value)
            {
                var chkBoxCnt = document.frmNonMemTypeList.chk.length;
                var value;
                if(value){
                    for(var i=0;i<chkBoxCnt;i++){

                        document.frmNonMemTypeList.chk[i].checked = true;
                    }

                }else{

                    for(var i=0;i<chkBoxCnt;i++){

                        document.frmNonMemTypeList.chk[i].checked = false;
                    }

                }
            }*/
            function checkAll()
            {

               if(document.frmNonMemTypeList.chk!=null){
                var chkBoxCnt = document.frmNonMemTypeList.chk.length;

                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {

                        if(document.frmNonMemTypeList.chkAll.checked==true)
                        {

                            document.frmNonMemTypeList.chk[i].checked = true;
                        }
                        else
                        {
                            document.frmNonMemTypeList.chk[i].checked = false;
                        }
                    }
                }
                else
                {
                    if(document.frmNonMemTypeList.chkAll.checked==true)
                    {

                        document.frmNonMemTypeList.chk.checked = true;
                    }
                    else
                    {
                        document.frmNonMemTypeList.chk.checked = false;
                    }
                }
			   }

            }
            <!--Start:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 10-Mar-2011-->
            function addRow()
            {

                var typId = document.frmNonMemTypeList.uType.value ;
                strURL = "./nonmemberMaster.do?memProcess=initCreate&uType="+typId;
                window.location.href = strURL;
            }
            <!--End:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 10-Mar-2011-->
        </script>
        <!--End:[MembershipMgt] For Script function changes dated 10-Mar-2011-->

        <%
                    String uTypeId = (String) request.getAttribute("uTypeId");
                    if (uTypeId == null || uTypeId.trim().length() == 0) {
                        uTypeId = "";
                    }
        %>


        <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />

    </head>
    <% String membTxt = (String) request.getAttribute("membTxt");
                boolean flag = true;
                if (membTxt == null) {
                    membTxt = (String) session.getAttribute("membTxt");
                    flag = false;
                }
                System.out.println("Member Type::>" + membTxt);%>

    <body >

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

                    <table width="750" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab" >
                        <tr>
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->

                                <table border="1" cellpadding="0" cellspacing="0" cellspacing="0" class="tblInnerContainer" >
                                    <tr>
                                        <td colspan="6" class="tblMainHead">
	Membership Types - Non-human:<span class="styleBoldTwo"> Listings</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" class="tblDescrp">
                                            You are viewing the list of <strong>Membership Type - Non-Human</strong> created by you.<br/> To create a Membership Type – Non-Human click <strong>Add</strong> button.<br/>To edit	a Membership Type – Non-Human select the checkbox before each record and then click on the <strong>Edit</strong> button.<br/> To delete Membership Types – Non-Human select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.</td>
                                        </td>
                                    </tr>


                                    <form name="frmNonMemTypeList" id="myform" action="nonmemberMaster.do" >
                                        <input type="hidden" name="memProcess" value="memTyDelete"/><!-- Listing -->
                                        <input type="hidden" name="membTxt" value="<%=membTxt%>"/>
                                        <input type ="hidden" name="memId" value=""/>
                                        <tr>
                                            <td height="30">
                                                <table border="0" cellpadding="0" align="center" cellspacing="0"  width="750">
                                                    <tr>
                                                        <td>
                                                            <strong>Species:</strong>
															<select name="uType" class="selectboxOne" onchange="postData1(); " >
                                                                <option selected="selected" value="">Select One</option>

                                                                <%
                                                                            Vector vUType = (Vector) session.getAttribute("displayUserTypeDetails");
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
                                                            <!--End:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                                        </td>
                                                        <td align="right">
                                                            <input type="button" name="add" value="Add" class="gradBtn" onclick="addRow();" />
                                                            <input name="btnedit" value="Edit" type="button" class="gradBtn" onclick="editRow('dataTable')" />
                                                            <input name="btnsubmit" value="Delete" type="button" class="gradBtn" onclick="deleteRow('dataTable')"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <tr>
                                                <td>
                                                    <table border="0"  id="dataTable" cellpadding="0"  align="center" cellspacing="1" width="750" style='table-layout:fixed'>
                                                        <td width="18" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAll()"/> </td>
                                                        <td width="220" height="27" class="tblRowHeadTwo">Membership type</td>
                                                        <td width="75" class="tblRowHeadTwo">Membership   Cost ($)</td>
                                                        <td width="80" colspan="2" class="tblRowHeadTwo">Duration</td>
                                                        <td width="40" class="tblRowHeadTwo">Active</td>
                                                        <td width="40" class="tblRowHeadTwo">Inactive</td>
                                                        </tr>

                                                        <%
                                                                    Vector memType = (Vector) session.getAttribute("displayMemDetails");
                                                                    if (memType != null && memType.size() != 0) {
                                                                        Enumeration memTypeEnum = memType.elements();
                                                                        int i = 0;
                                                                        while (memTypeEnum.hasMoreElements()) {
                                                                            i++;
                                                                            String memTypeDet[] = (String[]) memTypeEnum.nextElement();
                                                                            String candelete = memTypeDet[0];
                                                                            String membershipTypeId = memTypeDet[1];
                                                                            String membershipTypeName = memTypeDet[2];
                                                                            String membershipAmount = memTypeDet[3];
                                                                            //String periodValue = memTypeDet[3];
                                                                            String durationno = memTypeDet[4];
                                                                            String durationValue = memTypeDet[5];
                                                                            int active_status = Integer.parseInt(memTypeDet[6]);

                                                                            if (durationValue == null) {
                                                                                durationValue = "N/A";
                                                                            }

                                                                            //String memberTypeDet[] = {membershipTypeId,membershipTypeName, membershipAmount,mDate};
                                                        %>

                                                        <tr>
                                                            <td  class="listCellBg" style="text-align:center;"><input type="checkbox" name="chk" id="chk"  value="<%=membershipTypeId%>" /><input type="hidden" name="candelete" value="<%=candelete%>"/>
                                                                <td width="200" height="26" class="listCellBg" style="text-align:center;word-wrap:break-word;"><%=membershipTypeName%>
                                                                    <% if (candelete.equalsIgnoreCase("0")) {%>
                                                                    <strong style="color:red">**</strong>
                                                                    <%}%>
                                                                </td>
                                                                <%
																float price = Float.parseFloat(membershipAmount);
																BigDecimal bdAmount = new BigDecimal(price);
																bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
																%>
																<td width="75" height="26" class="listCellBg" style="text-align:right;word-wrap:break-word"><%=bdAmount%><input type="hidden" name="index" value="<%=i%>"/>
                                                                </td>
                                                                <td width="30" height="26" class="listCellBg" style="text-align:right;"><%=durationno%>
                                                                </td>
                                                                <td width="50" height="26" class="listCellBg" style="text-align:center;"><%=durationValue%>
                                                                </td>
                                                                <%

                                                                                                                                            if (active_status == 1) {
                                                                %>

                                                                <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name=<%="rd" + i%> value="1" checked="true" disabled /></td>
                                                                <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name=<%="rd" + i%> value="0" disabled/></td>
                                                                <%
                                                                                                                                                                                                            } else {
                                                                %>
                                                                <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name=<%="rd" + i%> value="1" disabled/></td>
                                                                <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name=<%="rd" + i%> value="0" checked="true" readonly=true disabled /></td>
                                                                <% }%>
                                                        </tr>
                                                        <%
                                                                                                                                }
                                                        %>

                                                        <%
                                                                                                                            } else {
                                                        %>
                                                        <tr>
                                                            <td height="20" colspan="7"  class="listCellBg"><div class="alignCenter"><strong>No Records Found</strong></div></td>
                                                        </tr>
                                                        <%                                               }
                                                        %>
                                                        <tr>
                                                            <td height="19" colspan="3">&nbsp;</td>
                                                        </tr>
                                                    </table>                 </form>
                                                    </table></td>
                                                </td>
                                            </tr>

                                            </table>
                                            <!-- CONTENTS END HERE -->
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

