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
            function postData(tableID){
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;

                for(var i=3; i<rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    // var strmembershipId=new Array();
                    //  var i=0;
                    if(null != chkbox && true == chkbox.checked)
                    {
                        var memID=chkbox.value;
                        strURL = "./memberMaster.do?memProcess=memTyDelete";
                        window.location.href = strURL;

                    }
                }
            }

            function valid2() {
                var chkCnt=0;
                var vFlag = 0;
                var cannotDelete=0;
                var canDeleted = 0;
                var chkBoxCnt = document.viewEdit.select11.length;
                if(chkBoxCnt!='undefined' && chkBoxCnt > 1)
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.viewEdit.select11[i].checked==true)
                        {
                            if(document.viewEdit.candelete[i].value=='No')
                            {
                                cannotDelete++;
                                document.viewEdit.select11[i].checked = false;
								//For Debug Starts
                                document.viewEdit.selectHead.checked = false;
                                //For Debug Ends
                            }

                            if(document.viewEdit.candelete[i].value=='Yes')
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
                    if(document.viewEdit.select11.checked==true)
                    {
                        if(document.viewEdit.candelete.value=='No')
                        {
                            cannotDelete++;
                            document.viewEdit.select11.checked = false;
						//For Debug Starts
                                document.viewEdit.selectHead.checked = false;
                                //For Debug Ends
                        }

                        if(document.viewEdit.candelete.value=='Yes')
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
                    alert("Please select the record(s) to be deleted");
                    return false;
                }

                if(vFlag == 4)
                {
                    if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
                    {
                        document.viewEdit.memProcess.value = 'memTyDelete';
                        document.viewEdit.submit();
                    }
                }
            }

            function edit()
            {
                var memId;
                var chkCnt=0;
                var vFlag = false;
                var select11=document.getElementById('select11');
                if(select11==null)
                {
                    alert("No Records Found");
                    return;
                }
                var chkBoxCnt =document.viewEdit.select11.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.viewEdit.select11[i].checked==true)
                        {
                            document.viewEdit.memId.value = document.viewEdit.select11[i].value;
                            chkCnt++;
                        }
                    }
                }
                else
                {
                    if(document.viewEdit.select11.checked==true)
                    {
                        document.viewEdit.memId.value = document.viewEdit.select11.value;
                        chkCnt++;
                    }
                }
				 //Starts for checkbox
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.viewEdit.selectHead.checked = false;
                        document.viewEdit.select11[j].checked = false;
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
                document.viewEdit.memProcess.value='initEdit';
                document.viewEdit.submit();
            }

            function checkAll(checkbox1)
            {
                var i,len = document.viewEdit.select11.length;

                if (checkbox1.checked)
                {
                    for(i=0;i<len;i++)
                    {
                        document.viewEdit.select11[i].checked=true;
                    }
                }
                else
                {
                    for(i=0;i<len;i++)
                    {
                        document.viewEdit.select11[i].checked=false;
                    }

                }
            }

            function onChangeCheck()
            {
                if(document.viewEdit.selectHead.checked)
                {
                    document.viewEdit.selectHead.checked=false;
                }
            }

            function onsubmitvalidate()
            {
                if(gtAct=='Delete')
                {
                    return valid2();
                }
                else
                {
                    return true;
                }
            }
        </script>

        <!--SCRIPTING BLOCK STARTS HERE -->
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
    %>

    <body>
        <table width="770" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab" >
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
	                           Membership Types Human:<span class="styleBoldTwo"> Listings</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" class="tblDescrp">
                                            You are viewing the list of Membership Types - Human created by you.<br/>To create a Membership Type - Human click <strong>Add</strong> button.<br/>To edit a Membership Type - Human select the checkbox before each record and then click on the <strong>Edit</strong> button.<br/>To delete Membership Types - Human select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.</td>
                                        </td>
                                    </tr>
                                    <form name="viewEdit" action="./memberMaster.do" method="post" onsubmit="return onsubmitvalidate();" >
                                        <input type="hidden" name="memProcess" value="Listing"/>
                                        <input type="hidden" name="memId" />
                                        <input type="hidden" name="index1" value=""/>
                                        <tr>
                                            <td height="30">
                                                <table border="0" cellpadding="0" align="center" cellspacing="0"  width="750" >
                                                    <tr>
                                                        <td align = "right" colspan="7" style="text-align:right">
                                                            <input type="submit" name="btnsubmit" value="Add" class="gradBtn" onclick="gtAct=this.value;"/>
                                                            <input name="btnEdit" value="Edit" type="button" class="gradBtn" onclick="edit();" />
                                                            <input name="btnsubmit" value="Delete" type="submit" class="gradBtn" onclick="gtAct=this.value;"/></td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <tr>
                                                <td>
                                                    <table border="0"  id="dataTable" cellpadding="0"  align="center" cellspacing="1" width="750" style='table-layout:fixed'>
                                                        <tr>
                                                            <td width="18" class="tblRowHeadTwo"><input type="checkbox" name="selectHead" id="selectHead"  value="membershipTypeId" onclick="checkAll(this);"/></td>
                                                            <td width="220" height="27" class="tblRowHeadTwo">Membership type</td>
                                                            <td width="75" class="tblRowHeadTwo">Membership Cost $ </td>
                                                            <td width="80" colspan="2" class="tblRowHeadTwo">Duration</td>
                                                            <td width="40" class="tblRowHeadTwo">Active</td>
                                                            <td width="40" class="tblRowHeadTwo">InActive</td>
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
                                                            <td width="30" height="26" class="listCellBg" style="text-align:center;"><input type="checkbox" name="select11" id="select11"  value="<%=membershipTypeId%>" onclick="onChangeCheck();" /><input type="hidden" name="candelete" value="<%=candelete%>"/>
                                                            </td>
                                                            <td width="200" height="26" class="listCellBg" style="text-align:center;word-wrap:break-word"><%=membershipTypeName%>
                                                            </td>
                                                            <td width="75" height="26" class="listCellBg" style="text-align:right;word-wrap:break-word"><%=membershipAmount%><input type="hidden" name="index" value="<%=i%>"/>
                                                            </td>
                                                            <td width="30" height="26" class="listCellBg" style="text-align:right;"><%=durationno%> </td>
                                                            <td width="50" height="26" class="listCellBg" style="text-align:center;"><%=durationValue%>
                                                            </td>
                                                            <%

                                                                                                                                        if (active_status == 1) {
                                                            %>

                                                            <td width="40" class="listCellBg" style="text-align:center;" ><input type="radio" name=<%="rd" + i%> value="active" checked="true" disabled /></td>
                                                            <td width="40" class="listCellBg" style="text-align:center;" ><input type="radio" name=<%="rd" + i%> value="inactive" disabled/></td>
                                                            <%
                                                                                                                                                                                                    } else {
                                                            %>
                                                            <td width="40" class="listCellBg" style="text-align:center;" ><input type="radio" name=<%="rd" + i%> value="active" disabled/></td>
                                                            <td width="40" class="listCellBg" style="text-align:center;" ><input type="radio" name=<%="rd" + i%> value="inactive" checked="true" readonly=true disabled /></td>
                                                            <% }%>
                                                        </tr>
                                                        <%

                                                        %>
                                                        <%
                                                                                                                                }
                                                                                                                            } else {
                                                        %>
                                                        <tr>
                                                            <td height="20" colspan="5"  class="listCellBg"><div class="alignCenter"><strong>No Records Found!</strong></div></td>
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
