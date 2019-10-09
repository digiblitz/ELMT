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

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
<script language="javascript">
       function addContactType(){
	strURL = "./contact.do?conprocess=contacttype";
	window.location.href = strURL;
       }
function checkAll()
{
       var chkBoxCnt = document.loopfrm.chk.length;
       if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
       {
            for(var i=0;i<chkBoxCnt;i++)
            {
                  if(document.loopfrm.chkAll.checked==true)
                 {
                        document.loopfrm.chk[i].checked = true;
                 }
                 else
                 {
                        document.loopfrm.chk[i].checked = false;
                 }
            }
       }
       else
       {
             if(document.loopfrm.chkAll.checked==true)
             {
                    document.loopfrm.chk.checked = true;
             }
             else
             {
                    document.loopfrm.chk.checked = false;
             }
        }

}

function editRow(tableID)

            {
				 var checkId=document.getElementById('chk');
	         if(checkId==null)
                {
                    alert("No Records Found");
                    return;
                }
                var contacttypeid;
                var chkCnt=0;
                var vFlag = false;
                var chkBoxCnt = document.loopfrm.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.loopfrm.chk[i].checked==true)
                        {
                            contacttypeid = document.loopfrm.chk[i].value;
                            chkCnt++;
                        }

                       
                    }
                }
                else
                {
                        if(document.loopfrm.chk.checked==true)
                        {
                            contacttypeid = document.loopfrm.chk.value;
                            chkCnt++;
                        }

                       
                }
				 //Starts for checkbox 
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.loopfrm.chk[j].checked = false;
                        document.loopfrm.chkAll.checked = false;
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
                strURL = "./contact.do?conprocess=editcontype&contacttypeid="+contacttypeid+"&butValue=Edit";
		window.location.href = strURL;
          }

 function deleteRow(tableID) {
	  var checkId=document.getElementById('chk');
	         if(checkId==null)
                {
                    alert("No Records Found");
                    return;
                }

	var contacttypeid;
	var chkCnt=0;
	var vFlag = 0;
	var cannotDelete=0;
	var canDelete = 0;
	var chkBoxCnt = document.loopfrm.chk.length;

	if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.loopfrm.chk[i].checked==true)
			{
				   if(document.loopfrm.flag[i].value==0)
				   {
					cannotDelete++;
					document.loopfrm.chk[i].checked = false;
					//Debug Starts for check all
                                document.loopfrm.chkAll.checked = false;
								//Debug Ends for check all
				   }

				   if(document.loopfrm.flag[i].value==1)
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
			if(document.loopfrm.chk.checked==true)
			{
				   if(document.loopfrm.flag.value==0)
				   {
					cannotDelete++;
					document.loopfrm.chk.checked = false;
					//Debug Starts for check all
                                document.loopfrm.chkAll.checked = false;
								//Debug Ends for check all
				   }

				   if(document.loopfrm.flag.value==1)
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
		alert("Only checked records will be deleted, Click the Delete button again.");
	}

	if(vFlag == 2)
	{
	   document.loopfrm.chkAll.checked=false;
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
			document.loopfrm.conprocess.value = 'confirmDelete';
                        document.loopfrm.submit();

		}
	}

}

 </script>
<!--Ends:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->

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

		<table width="753" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">

		  <tr>
                      <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
			<!--<td width="230" class="menuTablePad">-->
			<!-- LEFT MENU STARTS HERE -->
			<!--<!%@ include file = "../../include/menu-roles-leftmenu.jsp" %>-->
			<!-- LEFT MENU ENDS HERE -->

		    <!--</td>-->
                    <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="1" cellpadding="0" cellspacing="0" cellspacing="5" align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Contact Type:<span class="styleBoldTwo"> Listings</span></td>
  </tr>
    <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
  <!--<tr>
    <td colspan="5" class="tblDescrp">
		The  Contact Types are listed below: <br />
		<br />
		To edit a contact type master, click on the <strong>'Edit'</strong> button beside it. To detele a record click on the <strong>'Delete'</strong> button. <br />
	</td>
  </tr>-->
        <tr>
                  <td colspan="5" class="tblDescrp">
                 You are viewing the list of Contact Types created by you.<br/>To create a Contact Type click <strong>Add</strong> button.<br/>To edit a Contact Type select the checkbox before each record and then click on the <strong>Edit</strong> button.<br/>
                  To delete Contact Types select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.</td>
                </tr>
         <tr><td align="right" height="30">
        <input type="button" value="Add" onclick="addContactType();" class="gradBtn" align="right"/>
        <input type="button" value="Edit" name="butValue" onclick="editRow('dataTable')" class="gradBtn" align="right"/>
        <input type="submit" value="Delete" name="delcontype" onclick="deleteRow('dataTable')" class="gradBtn" align="right"/>
            </td></tr>

             <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
                  <tr>
 	<td>
             <!--<table border="0"  cellpadding="0"  align="center" cellspacing="1" class="formLayout">-->
              <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
            <table border="0"  id="dataTable" cellpadding="0"  align="center" cellspacing="1" class="formLayout" style='table-layout:fixed'>
                                 <form action="contact.do" name="loopfrm">
	    <input type="hidden" name="conprocess" value="editcontype"/>
            <!--		<tr>
		  	<td height="25" colspan="3"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr> -->


		<!--<tr>
			<td width="207" height="27" class="tblRowHeadTwo">Contact Type </td>
			<td width="45" class="tblRowHeadTwo">Edit </td>
			<td width="100" class="tblRowHeadTwo">Delete</td>
		   </tr>-->

		   <tr>
                       <td width="18" height="27" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAll()"></input> </td>
                       <td width="150" height="27" class="tblRowHeadTwo">Contact Type</td>
                       <td width="250" height="27" class="tblRowHeadTwo">Description </td>
		       <td width="140" height="27" class="tblRowHeadTwo">Type</td>
                       <td width="60" height="27" class="tblRowHeadTwo">Active</td>
		       <td width="60" height="27" class="tblRowHeadTwo">Inactive</td>
                   </tr>
                   <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
		<%
			Vector v = (Vector)request.getAttribute("vlistObj");
			if(v!=null && v.size()!=0){
			Enumeration it = v.elements();
			while (it.hasMoreElements()) {
				String[] s = (String[]) it.nextElement();
				String contact_type_id= s[0];
				String contact_type_name= s[1];
                                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
				String contact_type_desc= s[2];
				String contact_type= s[3];
				String active_status=s[4];
                                String flag=s[5];
                                  int chkBoxIndex = 0;
                                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
				%>
             <!--<form action="contact.do" name="loopfrm">-->
	    <!--<input type="hidden" name="conprocess" value="editcontype"/>-->
	    <input type="hidden" name="contacttypeid" value="<%=contact_type_id%>"/>
		<!--<tr>-->
                <%--<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=contact_type_name%></th>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="butValue" value="Edit" class="oneBtn" /></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="butValue" value="Delete" class="twoBtn" /></td>--%>
		<!--</tr>-->
                <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
		<tr>
                 <td  class="listCellBg" style="text-align:center;"> <input type="checkbox" name="chk" value="<%=contact_type_id%>" id="chk"/></td>
                 <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;"><%=contact_type_name%></td>
		 <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;"><%=contact_type_desc%></td>
		 <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;"><%=contact_type%></td>
			 <%

                                                                         if(Integer.parseInt(active_status)==1)
                                                                        {
                                                                            %>
                                                                            <td  width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=contact_type_id%>" value="1" checked="true" disabled="true"/></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=contact_type_id%>" value="0" disabled="true"/></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=contact_type_id%>" value="1" disabled="true"/></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=contact_type_id%>" value="0" checked="true" disabled="true"/></td>
                                                                              <% } %>
                                 <input type ="hidden" name ="flag" value ="<%=flag%>"/>
                                 <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
		</tr>
			<%
                        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                         chkBoxIndex++;
                         //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                        }
			}
		else {
		%>
		<tr>
		  <td align="center" colspan="3"><strong>No Records are Available </strong></td>
		</tr>
		<%	}	%>

<!--		  <tr>
		  	<td height="25" colspan="3"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
 -->		 <tr>
			<td height="19" colspan="6">&nbsp;</td>
           </tr>
 		</form>
	  </table>
	</td>
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
