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
        <script src="javascripts/frmCreateNonMembType.js" type="text/javascript" ></script>
        <script language="javascript">

            <!-- Start:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
            function clearFields(obj){
				//document.frmCreateMembType.memName.value="";
                document.frmCreateMembType.memAmount.value="";
                document.frmCreateMembType.DurationNoDdl.value="";
                document.getElementById('frmCreateMembType').status1.checked=false;
                document.getElementById('frmCreateMembType').status2.checked=false;
                document.frmCreateMembType.periodValue.value="";
                document.frmCreateMembType.transacType.options.selectedIndex = 0;
				document.frmCreateMembType.DurationTypeDdl.options.selectedIndex ="";
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
                document.frmCreateMembType.memProcess.value = 'memGetMemDet';
                document.frmCreateMembType.submit();
                 }
	  else
	    {
		return;
	    }
            }

            /*function isnotInteger(str){
        stringIntCheck="0123456789";
        f2=1;
        for(j=0;j<str.length;j++)
        { if(stringIntCheck.indexOf(str.charAt(j))==-1)
         {f2=0;}}
        if(f2==0)
        {alert('Enter valid priority'); document.frmCreateMembType.periodValue.focus();  return false;} else {   return true;}
        }	*/
            <!-- End:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
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
                    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                        <tr>
                            <td width="750" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
                                <table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
                                    <tr>
                                        <td colspan="2" class="tblMainHead">
		Membership Types - Non-human: <span class="styleBoldTwo"> Edit</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="tblDescrp">You can <strong>Edit/Update</strong> the <strong>Membership Type - Non-human</strong> created by you as given below <br/></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <form name="frmCreateMembType" id="frmCreateMembType" method="post" action="./nonmemberMaster.do" onsubmit="return myvalidate();">
                                                <input type="hidden" name="memProcess" value="memTyEdit"/>
                                                <%

                                                            String utypeID = (String) request.getAttribute("uTypeId");
                                                %>
                                                <input type ="hidden" name="uTypeId" value="<%=utypeID%>"/>

                                                <input type="hidden" name="memNme" value="" />
                                                <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" >
                                                    <!--<tr>
                                                        <td colspan="2" class="tblRowHead">Edit Membership Type </td>
                                                    </tr>-->
                                                    <!-- Start:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->

                                                    <!-- End:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
                                                    <tr>
                                                        <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
                                                    </tr>
                                                    <%          String membershipName = "";
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
                                                    <!-- Start:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
                                                    <tr>
                                                        <td class="tableLeft">Species:</td>
                                                        <td class="tableRight" >

                                                            <%
                                                                        String[] subType = (String[]) session.getAttribute("displayEditMemDetails");
                                                                        String membershipTypeId = "";
                                                                        String membershipTypeName = "";
                                                                        String membershipAmount = "";
                                                                        String duration = "";
                                                                        String durationType = "";
                                                                        String userTypeId = "";
                                                                        String periodValue = "";
                                                                        String mDate = "";
                                                                        String transacTypeId = "";
                                                                        int active_status = 0;
                                                                        int membership_year = 0;
                                                                        if (subType != null) {
                                                                            membershipTypeId = subType[0];
                                                                            membershipTypeName = subType[1];
                                                                            userTypeId = subType[2];
                                                                            System.out.println(userTypeId);
                                                                            membershipAmount = subType[3];
                                                                            mDate = subType[4];
                                                                            periodValue = subType[5];
                                                                            transacTypeId = subType[6];
                                                                            duration = subType[7];
                                                                            durationType = subType[8];
                                                                            active_status = Integer.parseInt(subType[9]);
                                                                            membership_year = Integer.parseInt(subType[10]);
                                                                            if (periodValue == null) {
                                                                                periodValue = "";
                                                                            }
                                                                        }

                                                                        String uTypeId = (String) request.getAttribute("uTypeId");
                                                                        System.out.println("uTypeId" + uTypeId);
                                                                        Vector vUType = (Vector) session.getAttribute("displayUserTypeDetails");
                                                                        if (vUType != null && vUType.size() != 0) {
                                                                            Enumeration eUtype = vUType.elements();
                                                                            //String [] userType = {ID, name };
                                                                            while (eUtype.hasMoreElements()) {
                                                                                String[] strType = (String[]) eUtype.nextElement();
                                                                                String uTypeID = strType[0];
                                                                                String uTypeName = strType[1];
                                                                                System.out.println("uTypeId=>>>uTypeName===>>>" + uTypeId + "==" + uTypeID + "==" + uTypeName);
                                                                                if (uTypeID.equals(userTypeId)) {
                                                            %>
                                                            <input type="hidden" name="uType" class="textboxOne"  value="<%=uTypeID%>" readOnly="true"/>
                                                            <input type="hidden" id="memyear" name="memyear" value="<%=membership_year%>"/>
                                                            <input type="text" name="uTypeName" class="readOnly" value="<%=uTypeName%>" readOnly="true"/>

                                                            <%
                                                                                    break;
                                                                                }
//								out.print("In If"+uTypeName);
                                                                                //break;



                                                                            }
                                                                        }
                                                            %>

                                                            <!-- End:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Membership Type:</td>
														<% if(membershipName==""){
															%>
                                                        <td class="tableRight"><input name="memName" id="memName" type="text" class="readOnly" value="<%=membershipTypeName%>" maxlength="80" readOnly="true"/>  <!--<span class="asterisk">*</span>--></td>
														<% }
															else { %>
															<td class="tableRight"><input name="memName" id="memName" type="text" class="readOnly" value="<%=membershipName%>" maxlength="80" readOnly="true"/> </td>
															<% } %>
                                                    </tr>
                                                    <tr>
                                                        <td class="tableLeft">Membership Cost $: </td>
                                                        <td class="tableRight"><input name="memAmount" id="memAmount" type="text" value="<%=membershipAmount%>" class="textboxOne" maxlength="16" />
                                                            <span class="asterisk">*</span></td>
                                                    </tr>
                                                    <!-- Start:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
                                                    <tr>
                                                        <td class="tableLeft">Duration: </td>
                                                        <td class="tableRight">
                                                            <input style="width:30px" type="text" value="<%=duration%>" name="DurationNoDdl" class="textboxOne" id="DurationNoDdl" maxlength="3"/>
                                                            <select name="DurationTypeDdl" id="DurationTypeDdl" class="selectboxOne">
															<option  selected="selected" value="">Select One</option>
                                                                <!-- <option  value="">Select One</option>-->
                                                                <% if (durationType.equals("year")) {%>
                                                                <option  selected="selected" value="year">Year</option>
                                                                <option value="month">Month</option>
                                                                <%} else {%>
                                                                <option  value="year">Year</option>
                                                                <option selected="selected" value="month">Month</option>
                                                                <%}%>
                                                            </select>  <span class="asterisk">*</span>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <!-- Start:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
                                                        <td class="tableLeft">Status: </td>
                                                        <%

                                                                    if (active_status == 1) {
                                                        %>
                                                        <td width="10" class="tableRight"><input type="radio" name="rd1" id="status1" value="1" checked="true"  />Active
                                                            <input type="radio" name="rd1" id="status2" value="0" /> Inactive  <span class="asterisk">*</span></td>
                                                            <%                                                                    } else {
                                                            %>
                                                        <td width="10" class="tableRight"><input type="radio" name="rd1" id="status1" value="1" />
                                                            Active <input type="radio" name="rd1" id="status2" value="0" checked="true"/> Inactive  <span class="asterisk">*</span></td>
                                                            <% }%>
                                                    </tr>
                                                    <!-- End:Membership Mgt For member  Editing changes dated 12-Mar-2011 -->
                                                    <tr>
                                                        <td class="tableLeft">Membership Prority: </td>
                                                        <td class="tableRight"><input style="width:30px" name="periodValue" id="periodValue" type="text" value="<%=periodValue%>" class="textboxOne" maxlength="3"/>
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
                                                            <input name="Button" type="submit" class="gradBtn" value="Update"/>&nbsp;&nbsp;
                                                            <input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
                                                            <input name="button" type="button" value="Cancel" class="gradBtn" onclick="cancelEditMember()"/>
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