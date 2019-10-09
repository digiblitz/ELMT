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
<%@ page import="com.hlcmrm.util.HLCHorseColorVO"%>
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
<!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011-->

<script language="javascript">
     function addDemoType(){
	strURL = "./Demographic.do?horseprocess=add";
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
                var demoid;
                var chkCnt=0;
                var vFlag = false;
                var chkBoxCnt = document.loopfrm.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.loopfrm.chk[i].checked==true)
                        {
                            demoid = document.loopfrm.chk[i].value;
                            chkCnt++;
                        }
                    }
                }
                else
                {
                        if(document.loopfrm.chk.checked==true)
                        {
                            demoid = document.loopfrm.chk.value;
                            chkCnt++;
                        }

                }
                if(chkCnt==0){

                    alert("Please check one record to edit");
                    return;
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
                if(vFlag == true)
                {
                    alert("Only one record can be edited.Please check one record to edit.");
                    return;
                }
                strURL = "./Demographic.do?horseprocess=edit&demoid="+demoid;
		        window.location.href = strURL;
          }

 function deleteRow(tableID) {
	 var checkId=document.getElementById('chk');
	         if(checkId==null)
                {
                    alert("No Records Found");
                    return;
                }
	var demoid;
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
				canDelete++;
			}

		}
	}
	else
	{
			if(document.loopfrm.chk.checked==true)
			{
				canDelete++;
			}

	}

	if(canDelete == 0)
	{
		alert("Please check the record(s) to be deleted.");
	}

	if(canDelete > 0)
	{
		if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		{
			document.loopfrm.horseprocess.value = 'delete';
            document.loopfrm.submit();

		}
	}

}
    </script>
	<!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011-->

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
  <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011-->
  <tr>
    <td class="tableCommonBg">

		<table width="753" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">

		  <tr>

			<td width="500" class="subDeptTablePad">


<table border="1" cellpadding="0" cellspacing="0" cellspacing="5" align="center" class="tblInnerContainer">
     <tr>
    <td colspan="5" class="tblMainHead">
	Demographic Categories - Human: <span class="styleBoldTwo"> Listings</span></td>
  </tr>

        <tr>
                  <td colspan="5" class="tblDescrp">
                  You are viewing the list of Demographic Categories - Human created by you.<br/>To create a Demographic Category - Human click <strong>Add</strong> button.<br/>To edit a Demographic Category - Human select the checkbox before each record and then click on the <strong>Edit</strong> button.<br/>
                  To delete Demographic Categories - Human select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.</td>
                </tr>
         <tr>
			<td align="right" height="30">
        <input type="button" value="Add" onclick="addDemoType();" class="gradBtn" align="right"/>
        <input type="button" value="Edit" name="butValue" onclick="editRow('dataTable')" class="gradBtn" align="right"/>
        <input type="submit" value="Delete" name="delcontype" onclick="deleteRow('dataTable')" class="gradBtn" align="right"/>
            </td></tr>


               <tr>
 	<td>

            <table border="0"  id="dataTable" cellpadding="0"  align="center" cellspacing="1" class="formLayout" style='table-layout:fixed'>
                                 <form action="Demographic.do" name="loopfrm">
	                             <input type="hidden" name="horseprocess" value="edit1"/>

		     <tr>

                       <td width="18" height="27" class="tblRowHeadTwo"><input type="checkbox" name="chkAll" onClick = "checkAll()"></input></td>
                       <td width="150" height="27" class="tblRowHeadTwo">Demographic</td>
                       <td width="270" height="27" class="tblRowHeadTwo">Description</td>
		               <td width="140" height="27" class="tblRowHeadTwo">Type</td>
                       <td width="50" height="27" class="tblRowHeadTwo">Active</td>
		               <td width="50" height="27" class="tblRowHeadTwo">Inactive</td>
            </tr>

		  <%
						 ArrayList DemoList = (ArrayList) request.getAttribute("list");

          					 if(DemoList!=null && DemoList.size()!=0){
							Iterator it = DemoList.iterator();

							int iwhileloppcount=0;
							while(it.hasNext()){

								 HLCHorseColorVO hlchorsevo = (HLCHorseColorVO) it.next();
								if(hlchorsevo.getDemoId()!=null){


                                                                 String DemoId=hlchorsevo.getDemoId();
								 String Demographic=hlchorsevo.getDemographic();
								  String Demodesc=hlchorsevo.getDemoDescription();
								   String Type=hlchorsevo.getDemoType();
								    String status=hlchorsevo.getStatus();


                                                               //int chkBoxIndex = 0;


		                %>



	   	<input type="hidden" name="DemoId" value="<%=DemoId%>" />


		<tr>
                 <td  class="listCellBg" style="text-align:center;"> <input type="checkbox" name="chk" value="<%=DemoId%>" id="chk"/></td>
                 <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;"><%=Demographic%></td>
                 <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;"><%=Demodesc%></td>
		       <td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;"><%=Type%></td>
			 <%

                                                                         if(Integer.parseInt(status)==1)
                                                                        {
                                                                            %>
                                                                            <td  width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=DemoId%>" value="1" checked="true" disabled="true"/></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=DemoId%>" value="0" disabled="true"/></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=DemoId%>" value="1" disabled="true"/></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=DemoId%>" value="0" checked="true" disabled="true"/></td>
                                                                              <% } %>


		</tr>
			<%
		}
							}
							}

		else {
		%>
		<tr>
		  <td height="20" colspan="6"  class="listCellBg"><div class="alignCenter"><strong>No Records Found</strong></div></td>
		</tr>
		<%	}	%>

<!--		  <tr>
		  	<td height="25" colspan="3"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
 -->		  <tr>
			<td height="19" colspan="3">&nbsp;</td>
           </tr>
 		</form>
	  </table>
	</td>
</tr>
<!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011-->
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