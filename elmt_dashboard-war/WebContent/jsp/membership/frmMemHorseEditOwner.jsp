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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlcmrm.util.HLCBreedVO"%>
<%@ page import="com.common.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=(String) session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/validate.js" type="text/javascript" ></script>
        <!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
        <script language="javascript">
            function myvalidate(obj){
                if(document.frmBreedEdit.relationDesc.value==""){
                    alert('Please enter valid Relationship ');
                    document.frmBreedEdit.relationDesc.focus();
                    return false;
                }
				
                
                vFlag = false;

                vFlag = false;

                for(var i=0;i<obj.elements.length;i++)
                {
                    if(obj.elements[i].type=='text')
                    {
                        if((obj.elements[i].value.indexOf('	') == 0) || (obj.elements[i].value.lastIndexOf('	') == (obj.elements[i].value.length-1)) ||
                            (obj.elements[i].value.indexOf(' ') == 0) || (obj.elements[i].value.lastIndexOf(' ') == (obj.elements[i].value.length-1)))
                        {
                            obj.elements[i].value = obj.elements[i].value.trim();
                            vFlag = true;
                        }
                    }
                }

                if(vFlag==true)
                {
                    alert("Leading and Trailing spaces will be trimmed. Please Submit again");
                    return false;
                }

                /*if(document.frmBreedEdit.relationDesc.value.length>=50){

		           alert('Relationship cannot have more than 50 characters ');
		           document.frmBreedEdit.relationDesc.focus();
		              return false;
                   }*/
                      

                if(document.frmBreedEdit.relationstatus[0].checked==false && document.frmBreedEdit.relationstatus[1].checked==false)
                {
                    alert("Please choose the Status");
                    return false;
                }
            }
            <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
            function clearFields(obj)
            {

                for(var i=0;i<obj.elements.length;i++)
                {
                    if(obj.elements[i].type=='text')
                    {
                        obj.elements[i].value = "";
                    }

                    if(obj.elements[i].type=='radio')
                    {
                        obj.elements[i].checked = false;
                    }
                }
            }
            <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
            function cancelEditBreed()
            {

               var typId = document.frmBreedEdit.uTypeId.value ;
          if(confirm("Do you want to Cancel and go back to List Page?"))
	    {
                strURL = "./RegHorseListing.do?process=reqFrmList&uTypeId="+typId;
                window.location.href = strURL;
                   }
	  else
	    {
		return;
	    }
            }
        </script>
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

                    <table width="100%" border="1" align="center" cellpadding="0" cellspacing="5" id="centerTab">

                        <tr>
                            <!--<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
                            <!--<%//@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                            </td>
                            <!-- LEFT MENU ENDS HERE -->
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->

                                <table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
                                    <tr>
                                        <td colspan="2" class="tblMainHead">
	Human/Non-Human Relationships: <span class="styleBoldTwo">Edit</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="tblDescrp">You can <strong>Edit/Update</strong> the <strong>Human/Non-Human Relationship</strong> created by you as given below.<br />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>


                                            <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">

                                                <tr>
                                                    <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
                                                </tr>
                                                <%  String relate=""; 
												String statuscheck = (String) request.getAttribute("err");
                                                            if (statuscheck != null && statuscheck.equals("st")) {
                                                %>
                                                <tr>
                                                    <td colspan="2" class="styleError"><strong>Relationship already exists</strong></td>
                                                </tr>
                                                <%   relate=(String)request.getAttribute("relation");                                    
													}
                                                         String relationId = "";
                                                            String relationStatus = "";
                                                            String relationDesc = "";
                                                            String SpecieId = "";
                                                            String uTypeId = "";

                                                            ArrayList arrayPermList = (ArrayList) request.getAttribute("Vobj");
                                                            Iterator itPermList = arrayPermList.iterator();
                                                            while (itPermList.hasNext()) {
                                                                String[] perList = (String[]) itPermList.next();
                                                                SpecieId = perList[0];
                                                                relationId = perList[1];
                                                                relationDesc = perList[2];
                                                                relationStatus = perList[3];
                                                                uTypeId = perList[4];
                                                            }
                                                %>

                                                <tr>
                                                    <!--<td class="tableLeft">Breed  Code:</td>
					<td class="tableRight"><input name="breedCode" type="text" class="textboxOne" value="< %=breedCode%>"/><span class="asterisk">*</span></td>-->
                                                </tr>
                                                <tr>
                                                    <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                    <td class="tableLeft">Species:</td>
                                                    <td class="tableRight"><input name=" SpecieId" type="text" class="readOnly" value="<%= SpecieId%>"readonly="true" maxlength="120"/></td>

                                                    <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                </tr>
                                                <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                <form name="frmBreedEdit" id="frmBreedEdit" method="post" action="RegHorseListing.do" onSubmit="return myvalidate(this);">
                                                    <input type="hidden" name="process" value="update" />
                                                    <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                    <tr>
                                                        <td class="tableLeft">Relationship:</td>
														<% if(relate==""){  %>
                                                        <td class="tableRight"><input name="relationDesc" type="text" class="textboxOne" value="<%=relationDesc%>" maxlength="49"/>  <span class="asterisk">*</span></td>
														<% }
														else {  %>
														<td class="tableRight"><input name="relationDesc" type="text" class="textboxOne" value="<%=relate%>" maxlength="49"/>  <span class="asterisk">*</span></td>
														<%  }  %>
                                                        <td ><input name=" uTypeId" type="hidden" class="textboxOne" value="<%= uTypeId%>" maxlength="51"/>
                                                        </td>
                                                    </tr>
                                                    <tr><!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                        <td class="tableLeft">Status:</td>

                                                        <%       if (Integer.parseInt(relationStatus) == 1) {
                                                        %>
                                                        <td class="tableRight" ><input type="radio" name="relationstatus" value="1" checked="true"  />Active
                                                            <input type="radio" name="relationstatus" value="0" />Inactive  <span class="asterisk">*</span></td>
                                                            <%                                                                                      } else {
                                                            %>
                                                        <td  class="tableRight" ><input type="radio" name="relationstatus" value="1" />Active
                                                            <input type="radio" name="relationstatus" value="0" checked="true"/>Inactive  <span class="asterisk">*</span></td>
                                                            <% }%>
                                                    </tr><!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->

                                                    <input type="hidden" value="<%=relationId%>" name="relationId"/>
                                                    <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                    <input type="hidden" value="<%=uTypeId%>" name="uTypeId"/>
                                                    <tr>
                                                        <!--<td class="tableLeft">&nbsp;</td>-->
                                                        <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                        <td colspan = "2" style="text-align:center" height="25"><input type="submit" class="gradBtn" value="Update" />&nbsp;&nbsp;
                                                            <input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/> &nbsp;&nbsp;
                                                            <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelEditBreed()" /></td>
                                                        <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                                    </tr></form>
                                            </table>


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


                    
