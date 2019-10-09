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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=(String) session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/validate.js" type="text/javascript" ></script>
        <script src="javascripts/frmCreateMembType.js" type="text/javascript" ></script>
        <script language="javascript">
            /*function isnotInteger(str){
        stringIntCheck="0123456789";
        f2=1;
        for(j=0;j<str.length;j++)
        { if(stringIntCheck.indexOf(str.charAt(j))==-1)
         {f2=0;}}
        if(f2==0)
        {alert('Enter valid priority'); document.frmCreateMembType.periodValue.focus();  return false;} else {   return true;}
        }	*/

            function clearFields(obj){
				//document.frmCreateMembType.memName.value="";
                document.frmCreateMembType.memAmount.value="";
                document.frmCreateMembType.periodValue.value="";
                document.frmCreateMembType.DurationNoDdl.value="";
                document.getElementById('frmCreateMembType').status1.checked=false;
                document.getElementById('frmCreateMembType').status2.checked=false;
                document.frmCreateMembType.transacType.options.selectedIndex = 0;
				document.frmCreateMembType.DurationTypeDdl.options.selectedIndex= 0;
                /*var vOptionsLen = 0;
                if(document.frmCreateMembType.DurationTypeDdl.options[document.frmCreateMembType.DurationTypeDdl.selectedIndex].value!='')
                {
                    document.frmCreateMembType.DurationTypeDdl.options.length = document.frmCreateMembType.DurationTypeDdl.options.length+1;
                    document.frmCreateMembType.DurationTypeDdl.options[document.frmCreateMembType.DurationTypeDdl.options.length-1].value = "";
                    document.frmCreateMembType.DurationTypeDdl.options[document.frmCreateMembType.DurationTypeDdl.options.length-1].text = "Select One";
                    document.frmCreateMembType.DurationTypeDdl.selectedIndex = 	document.frmCreateMembType.DurationTypeDdl.options.length-1;
                }
                for(var i=0;i<(obj.DurationTypeDdl.options.length-1);i++)
                {
                    if(document.frmCreateMembType.DurationTypeDdl.options[i].value=='')
                    {
                        vOptionsLen++;
                        document.frmCreateMembType.DurationTypeDdl.options.length = document.frmCreateMembType.DurationTypeDdl.options.length - vOptionsLen;
                        document.frmCreateMembType.DurationTypeDdl.selectedIndex = 	document.frmCreateMembType.DurationTypeDdl.options.length-1;

                    }
                }*/
                return true;
            }

            function cancelEditMember(){
         if(confirm("Do you want to Cancel and go back to List Page?")){
                strURL = "./memberMaster.do?memProcess=initList";
                window.location.href = strURL;
                }
	  else
	    {
		return;
	    }
            }
        </script>
        <!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
    </head>
    <body><!-- onload="populateEditYear();"-->
        <%!
            String nullCheck(String fieldName) {
                String retValue = "";
                if (fieldName != null && fieldName.trim().length() != 0) {
                    retValue = fieldName;
                }

                return retValue;

            }

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
                    <table width="100%" border="0" ali="center" cellpadding="0" cellspacing="5" id="centerTab">
                        <tr>
                            <!--<td width="230" classtblDescrpmenuTablePad">-->
			<!-- LEFT MENU STARTS HERE -->
                            <%//@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                            <!-- LEFT MENU ENDS HERE -->
                            <!--</td>-->
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
                                <table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
                                    <tr>
                                        <td colspan="2" class="tblMainHead">
		Membership Types - Human: <span class="styleBoldTwo"> Edit</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="tblDescrp">You can <strong>Edit/Update</strong> the <strong>Membership Type - Human</strong> created by you as given below. <br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <form name="frmCreateMembType" id="frmCreateMembType" method="post" action="./memberMaster.do" onsubmit="return myvalidate();">
                                                <input type="hidden" name="memProcess" value="memTyEdit"/>
                                                <%
                                                           String membershipName = "";
                                                            String yr = (String) request.getAttribute("year");
                                                %>
                                                <input type="hidden" name="populYr" value="<%=yr%>" />
                                                <input type="hidden" name="memNme" value="" />
                                                <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                                                    <!--<tr>
                                                        <td colspan="2" class="tblRowHead">
						Edit Membership Type </td>
                                                    </tr>-->
                                                    <!--Debug Starts Here-->
                                                    <%
                                                                // String status = (String) request.getAttribute("status");
                                                                //   if (status != null) {
                                                                //     if (status.equalsIgnoreCase("false")) {
%>
                                                    <!-- <tr>
                                                         <td class="styleError" colspan="4">Membership Type already exists</td>
                                                     </tr>-->
                                                    <%		//}
                                                                //      }
%>
                                                    <!--Debug Ends Here-->
                                                    <tr>
                                                        <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
                                                    </tr>
                                                    <%
                                                                String status = (String) request.getAttribute("status");
                                                                if (status != null) {
                                                                    if (status.equalsIgnoreCase("false")) {
                                                    %>
                                                    <tr>
                                                        <td class="styleError" colspan="4"><strong>Membership Type already exists</strong></td>
                                                    </tr>
                                                    <%		membershipName=(String)request.getAttribute("memName");
                                                                    }
                                                                }
                                                    %>

                                                    <%
                                                                String[] subType = (String[]) session.getAttribute("displayEditMemDetails");
                                                                String membershipTypeId = "";
                                                                String membershipTypeName = "";
                                                                String membershipAmount = "";
                                                                String userTypeId = "";
                                                                String periodValue = "";
                                                                String mDate = "";
                                                                String transacTypeId = "";
                                                                String DurationNo = "";
                                                                String DurationType = "";
                                                                int active_status = 0;
                                                                int membership_year = 0;

                                                                if (subType != null) {
                                                                    membershipTypeId = subType[0];
                                                                    membershipTypeName = subType[1];
                                                                    userTypeId = subType[2];
                                                                    membershipAmount = subType[3];
                                                                    mDate = subType[4];
                                                                    periodValue = subType[5];
                                                                    transacTypeId = subType[6];
                                                                    DurationNo = subType[7];
                                                                    DurationType = subType[8];
                                                                    active_status = Integer.parseInt(subType[9]);
                                                                    membership_year = Integer.parseInt(subType[10]);

                                                                    if (periodValue == null) {
                                                                        periodValue = "";
                                                                    }
                                                                }

                                                                String uTypeId = (String) request.getAttribute("uTypeId");
                                                    %>
                                                    <input type="hidden" id="memId" name="memId" value="<%=membershipTypeId%>"/>
                                                    <input type="hidden" id="memyear" name="memyear" value="<%=membership_year%>"/>
                                                    <tr>
                                                        <td class="tableLeft">Membership Type:</td>
														<% 
														System.out.print("Name" +membershipName);
														if(membershipName=="")
														{     %>
                                                        <td class="tableRight"><input name="memName" id="memName" type="text" class="readOnly" maxlength="80" value="<%=membershipTypeName%>" readOnly="true"/> <!--<span class="asterisk">*</span>--></td>
														<%}
														else{
															%>
															 <td class="tableRight"><input name="memName" id="memName" type="text" class="readOnly" maxlength="80" value="<%=membershipName%>" readOnly="true"/> </td>
															 <% } %>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Membership cost $: </td>
                                                        <td class="tableRight"><input name="memAmount" id="memAmount" type="text" maxlength="16" value="<%=membershipAmount%>" class="textboxOne" />
                                                            <span class="asterisk">*</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Duration: </td>
                                                        <td class="tableRight">
                                                            <input type="text" value="<%=DurationNo%>" name="DurationNoDdl" id="DurationNoDdl" maxLength="3" class="textboxOne" style="width:30px;" maxlength="3"/>

                                                            <select name="DurationTypeDdl" id="DurationTypeDdl" class="selectboxOne">
															<option selected="selected" value="">Select One</option>

                                                                <%
                                                                            if (DurationType.equals("year")) {
                                                                %>

                                                                <option selected="selected" value="year">Year</option>
                                                                <option value="month">Month</option>

                                                                <%                                                                                                                                                 } else {
                                                                %>
                                                                <option selected="selected" value="month">Month</option>
                                                                <option value="year">Year</option>
                                                                <%}%>

                                                            </select>  <span class="asterisk">*</span>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Status: </td>
                                                        <%

                                                                    if (active_status == 1) {
                                                        %>
                                                        <td width="10" class="tableRight" ><input type="radio" name="rd1" id="status1" value="active" checked="true"  />Active
                                                            <input type="radio" name="rd1" id="status2"  value="inactive" />Inactive  <span class="asterisk">*</span></td>
                                                            <%                                                                    } else {
                                                            %>
                                                        <td width="10" class="tableRight" ><input type="radio" name="rd1" id="status1"  value="active" />Active
                                                            <input type="radio" name="rd1" id="status2"  value="inactive" checked="true"/>Inactive  <span class="asterisk">*</span></td>
                                                            <% }%>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Membership Prority: </td>
                                                        <td class="tableRight"><input style="width:30px;" name="periodValue" id="periodValue" type="text" maxLength="3"  value="<%=periodValue%>" class="textboxOne" />
                                                            <!--<span class="asterisk">*</span>--></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">QB Transaction Type: </td>
                                                        <td class="tableRight">
                                                            <select name="transacType" id="transacType" class="selectboxOne" >
                                                                <option selected="selected" value="Select One">Select One</option>
                                                                <%
                                                                            ArrayList accDetails = (ArrayList) request.getAttribute("accDetails");
                                                                            if (accDetails != null && accDetails.size() != 0) {
                                                                                Iterator itr = accDetails.iterator();
                                                                                while (itr.hasNext()) {
                                                                                    String txnDet[] = (String[]) itr.next();

                                                                                    String txnId = txnDet[0];
                                                                                    String txnName = txnDet[1];
                                                                                    if (transacTypeId != null) {
                                                                                        if (transacTypeId.equalsIgnoreCase(txnId)) {
                                                                %>
                                                                <option value="<%=txnId%>" selected="selected"><%=txnName%></option>
                                                                <%	} else {%>
                                                                <option value="<%=txnId%>"><%=txnName%></option>
                                                                <%	}
                                                                                                                                                    } else {%>
                                                                <option value="<%=txnId%>"><%=txnName%></option>
                                                                <%}
                                                                                }
                                                                            }
                                                                %>
                                                            </select><span class="asterisk">*</span></td>
                                                    </tr>
                                                    <input type="hidden" value="<%=membershipTypeId%>" name="memId" />
                                                    <tr>
                                                        <!--<td class="tableLeft">&nbsp;</td>-->
                                                        <td colspan = "2" style="text-align:center" height="25">
                                                            <input name="Button" type="submit" class="gradBtn" value="Update"  />&nbsp;&nbsp;
                                                            <input name="Clear" type="button" class="gradBtn" value="Clear"  onClick = "clearFields(this.form)" />&nbsp;&nbsp;
                                                            <input type="button" value="Cancel" class="gradBtn" onclick="cancelEditMember()"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </form>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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