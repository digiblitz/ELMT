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
        <script src="javascripts/frmCreateNonMembType.js" type="text/javascript" ></script>
        <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
        <script language="javascript">
            function isnotInteger(str){
                stringIntCheck="0123456789";
                f2=1;
                for(j=0;j<str.length;j++)
                { if(stringIntCheck.indexOf(str.charAt(j))==-1)
                    {f2=0;}}
                if(f2==0)
                {alert('Enter valid priority'); document.frmCreateMembType.periodValue.focus();  return false;} else {   return true;}
            }
            <!--Start:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 12-Mar-2011-->
            function cancelAddRole()
            {
		if(confirm("Do you want to Cancel and go back to List Page?")){
                if(document.frmCreateMembType.uTypeIdold.value!=""){
                    var uTypeId = document.frmCreateMembType.uTypeIdold.value;
                    strURL = "./nonmemberMaster.do?memProcess=memGetMemDet&uTypeId="+uTypeId;
                    window.location.href = strURL;
				}
				else{
					strURL = "./nonmemberMaster.do?memProcess=initList";
					window.location.href = strURL;
				}
                                 }
	       else
	      {
		   return;
	      }

            }

            function clearFields(obj){
				document.frmCreateMembType.uType.options.selectedIndex = 0;
                document.frmCreateMembType.memName.value="";
                document.frmCreateMembType.memAmount.value="";
                document.frmCreateMembType.DurationNoDdl.value="";
                document.frmCreateMembType.DurationTypeDdl.options.selectedIndex = 0;
                document.getElementById('frmCreateMembType').status1.checked=false;
                document.getElementById('frmCreateMembType').status2.checked=false;
                document.frmCreateMembType.periodValue.value="";
                document.frmCreateMembType.transacType.options.selectedIndex = 0;
                var vOptionsLen = 0;

                return true;
            }
            <!--End:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 12-Mar-2011-->
        </script>
        <%String uTypeId = (String) request.getAttribute("uTypeId");
                    String uTypeIdold = (String) request.getAttribute("uTypeId");
                    System.out.println("utypename" + uTypeId);
                    if (uTypeId == null || uTypeId.trim().length() == 0) {
                        System.out.println("nnn1" + uTypeId);
                    }
        %>
        <!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
    </head>

    <body>
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

                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                        <tr>
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->

                                <table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
                                    <tr>
                                        <td colspan="2" class="tblMainHead">
		Membership Types - Non-human: <span class="styleBoldTwo"> Create</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="tblDescrp">You can <strong>Create</strong> a <strong>New Membership Type - Non-human</strong> for all members and non-members Online Services Dashboard, right here!<br />
                                        </td>
                                    </tr>
                                    <!--Debug Starts Here-->
                                    <%
                                                //String status = (String) request.getAttribute("status");
                                                // if (status != null) {
                                                //   if (status.equalsIgnoreCase("false")) {%>
                                    <!--<tr>
                                        <td class="styleError" colspan="4">Membership Type already exists</td>
                                    </tr>-->

                                    <%  //}
                                                //   }%>
                                    <!--Debug Ends Here-->
                                    <tr>
                                        <td>

                                            <form name="frmCreateMembType" id="frmCreateMembType" method="post" action="./nonmemberMaster.do" onsubmit="return myvalidate(); ">
                                                <input type ="hidden" name="uTypeIdold" value="<%=uTypeIdold%>"/>
                                                <input type="hidden" name="memProcess" value="createMemTyMaster"/>
                                                <input type="hidden" name="populYr" value="0" />
                                                <input type="hidden" name="memNme" value="" />
                                                <input type ="hidden" name="uTypeId" value=""/>
                                                <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                                                    <!--<tr>
                                                        <td colspan="2" class="tblRowHead">
						Create Membership Type </td>
                                                    </tr>-->
                                                    <tr>
                                                        <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
                                                    </tr>
                                                    <!--Debug Starts Here-->
                                                    <%
                                                                String status = (String) request.getAttribute("status");
                                                                if (status != null) {
                                                                    if (status.equalsIgnoreCase("false")) {%>
                                                    <tr>
                                                        <td class="styleError" colspan="4"><strong>Membership Type already exists</strong></td>
                                                    </tr>

                                                    <%}
                                                                }%>
                                                    <!--Debug Ends Here-->
                                                    <tr>
                                                        <td class="tableLeft">Species:</td>

                                                        <td class="tableRight">
                                                            <select name="uType" id="uType" class="selectboxOne" >
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
                                                                                    ///									String uTypePrority = strType[2];
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

                                                            </select>  <span class="asterisk">*</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Membership Type:</td>
                                                        <td class="tableRight"><input name="memName" id="memName" type="text" class="textboxOne"  maxlength="80"/>  <span class="asterisk">*</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Membership Cost $: </td>
                                                        <td class="tableRight"><input name="memAmount" id="memAmount" type="text" class="textboxOne"  maxlength="16"/>
                                                            <span class="asterisk">*</span></td>
                                                    </tr>
                                                    <!--Start:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 12-Mar-2011-->
                                                    <tr>
                                                        <td class="tableLeft">Duration: </td>
                                                        <td class="tableRight"><input style="width:30px" type="text" class="textboxOne" name="DurationNoDdl" id="DurationNoDdl" maxLength="3"/>
                                                            <select name="DurationTypeDdl" id="DurationTypeDdl" class="selectboxOne">
                                                                <option value="Select One">Select One</option>
                                                                <option  value="year">Year</option>
                                                                <option  value="month">Month</option>
                                                            </select>  <span class="asterisk">*</span>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Status: </td>
                                                        <td class="tableRight" ><input type="radio" name="rd1" id="status1" value="1"/>Active
                                                            <input type="radio" name="rd1" id="status2" value="0" />Inactive  <span class="asterisk">*</span></td>
                                                    </tr>
                                                    <!--End:[MembershipMgt] For Member Addition, Editing and Deletion changes dated 12-Mar-2011-->
                                                    <tr>
                                                        <td class="tableLeft">Membership Priority: </td>
                                                        <td class="tableRight"><input name="periodValue" id="periodValue" type="text" class="textboxOne" maxlength="3" style="width:30px"/>
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
                                                                %>
                                                                <option value="<%=txnId%>"><%=txnName%></option>
                                                                <%	 }
                                                                            }
                                                                %>
                                                            </select><span class="asterisk">*</span></td>
                                                    </tr>
                                                    <tr>
                                                        <!--                                                        <td class="tableLeft">&nbsp;</td>-->
                                                        <td colspan = "2" style="text-align:center" height="25">
                                                            <input name="Button" type="submit" class="gradBtn" value="Create"  onSubmit= "" />&nbsp;&nbsp;
                                                            <input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
                                                            <input type="button" value="Cancel" class="gradBtn" onclick="cancelAddRole();" />
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
