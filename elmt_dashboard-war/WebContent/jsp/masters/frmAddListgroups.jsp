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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<script>


function search()
{
	
			var masterId=document.frmSearchListgroups.masterId.value;
			   strURL = "./viewMaster.do?process=searchList&masterId="+masterId;
               window.location.href = strURL;
			
		
	
	}
	
	function AddGroups()
	{
		
		var masterId=document.frmSearchListgroups.masterId.value;
		   strURL = "./viewMaster.do?process=AddGroups&masterId="+masterId;
           window.location.href = strURL;
	}
	function EditGroups()
	{
	
	var layer_value_id;
	var chkCnt=0;
	var vFlag = false;
	var masterId=document.frmSearchListgroups.masterId.value;
	var chkBoxCnt = document.frmSearchListgroups.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmSearchListgroups.chk[i].checked==true)
			{
				layer_value_id = document.frmSearchListgroups.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.frmSearchListgroups.chk.checked==true)
			{
				layer_value_id = document.frmSearchListgroups.chk.value;
				   chkCnt++;
			}

	}
//Starts for checkbox
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmSearchListgroups.chk[j].checked = false;
                        document.frmSearchListgroups.chkall.checked = false;
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
				
			strURL = "./viewMaster.do?process=EditGroups&layer_value_id="+layer_value_id+"&masterId="+masterId;
		window.location.href = strURL;
}
	
	  function deleteGroups() {
			 var checkId=document.getElementById('chk');

			var chkCnt=0;					
					
						var canDelete = 0;
						var chkBoxCnt = document.frmSearchListgroups.chk.length;
						//alert("chkBoxCnt---"+chkBoxCnt);

						if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
						{
							for(var i=0;i<chkBoxCnt;i++)
							{
								if(document.frmSearchListgroups.chk[i].checked==true)
								{
									canDelete++;
								}

							}
						}
						else
						{
								if(document.frmSearchListgroups.chk.checked==true)
								{
									canDelete++;
								}

						}

						if(canDelete == 0)
						{
							alert("Please check the record(s) for Delete.");
						}

						if(canDelete > 0)
						{
							if(confirm("Only selected records will be deleted.Are you sure you want to delete the same?"))

							{
									//alert("canDelete---"+canDelete);
								document.frmSearchListgroups.process.value ='deleteGroups';
								document.frmSearchListgroups.submit();

							}
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

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">

		  <tr>
			
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer" width="100%">

  
    <tr>
    <td colspan="2" class="tblMainHead">
	Group - Details: <span class="styleBoldTwo">List</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" name="add" class="gradBtn" value="Add" onclick="AddGroups();"/>
	<input type="button" name="edit" class="gradBtn" value="Edit" onclick="EditGroups();"/>
	<input type="button" name="delete" class="gradBtn" value="Delete" onclick="deleteGroups();"/></td>
  </tr>
  
  <tr>
  <td>
  <table>
  <form name="frmSearchListgroups" id="frmSearchListgroups" method="post" action="viewMaster.do">
		<input type="hidden" name="process" value="" />
		<%
    		String groupIdObj=(String)request.getAttribute("groupId");
%>
     
   
										 <tr>
                 
					<td class="tableLeft" colspan="2">Groups:</td>
					<td class="tableRight" colspan="2"><select name="masterId" class="selectboxOne" onchange="search(); ">
                                                <option  selected="selected" value="">Select One</option>

                                                <%
                                                ArrayList viewGroupSrchList = (ArrayList)request.getAttribute("groupList");
           									 if(viewGroupSrchList!=null && viewGroupSrchList.size()!=0){
           							Iterator itGprSrch = viewGroupSrchList.iterator();
           							while(itGprSrch.hasNext()){
           								String[] groupSrchList = (String[])itGprSrch.next();
           								String group_id_srch = groupSrchList[0];
           								String group_name_srch = groupSrchList[1];
           							   if (group_id_srch.equals(groupIdObj)){
                                                                                            
										%>
										  <option  value="<%=group_id_srch%>" selected="selected" ><%=group_name_srch%></option>
										<%
											}
											else{
											%>
											 <option  value="<%=group_id_srch%>" ><%=group_name_srch%></option>
											<%
											}
										}
           									 }									
								%>
								</select>  <span class="asterisk">*</span></td>

				  </tr>
   <tr><th width="5" class="tblRowHeadTwo"><input type="checkbox" name="chkall" value=""></th><th width="40" class="tblRowHeadTwo">Group</th><th width="55" class="tblRowHeadTwo">Details</th></tr>
  <%
  ArrayList viewGroupAllList = (ArrayList)request.getAttribute("viewGroupList");
	 if(viewGroupAllList!=null && viewGroupAllList.size()!=0){
Iterator itViewGrp = viewGroupAllList.iterator();
while(itViewGrp.hasNext()){
String[] viewGroupAllStr = (String[])itViewGrp.next();
String layer_value_id = viewGroupAllStr[0];
String masterIdList = viewGroupAllStr[1];
String layer_value = viewGroupAllStr[2];
String masterNameList = viewGroupAllStr[3];
  %>
  <tr>
  <td width="5"><input type="checkbox" name="chk" value="<%=layer_value_id%>"></input></td>
  <td width="40"><%=masterNameList%></td>
  <td width="55"><%=layer_value%></td>
  
  </tr>
  <%}
}else{ %>

  <td colspan="4" align="center"><br><strong>No Records Found</strong><br />
<%}%>
</form>
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
