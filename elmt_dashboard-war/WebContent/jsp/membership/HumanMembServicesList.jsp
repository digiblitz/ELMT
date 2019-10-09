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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script language="javascript">
    function postData(){

		var typId = document.viewHrsServiceList.uType.value ;


	if(typId!=""){
                strURL = "./horseServiceMaster.do?horseProcess=getSerTypeDet&uType="+typId+"&userTypeName=Human";
	        window.location.href = strURL;

	}
	else{
                 strURL = "./horseServiceMaster.do?horseProcess=initList&userTypeName=Human";
	        window.location.href = strURL;
	}
}
function checkAll()
{
	if(document.viewHrsServiceList.select11!=null){
	var len = document.viewHrsServiceList.select11.length;
	 if(len!=undefined && len!='undefined')
	{
	    for(var i=0;i<len;i++)
	    {
		if (document.viewHrsServiceList.selectHead.checked==true)
		{
			document.viewHrsServiceList.select11[i].checked=true;
		}
		else
		{
			document.viewHrsServiceList.select11[i].checked=false;
		}
	    }
        }
	else
	{
	   if (document.viewHrsServiceList.selectHead.checked==true)
	   {
			document.viewHrsServiceList.select11.checked=true;
		}
		else
		{
			document.viewHrsServiceList.select11.checked=false;
		}

	 	 }
    }
}

/*function onChangeCheck()
{
	if(document.viewHrsServiceList.selectHead.checked)
	{
		document.viewHrsServiceList.selectHead.checked=false;
	}
}*/

function Add_Submit()
{
//	alert('add');
    document.viewHrsServiceList.horseProcess.value='initHorseMaster';
	//alert('add1');
	document.viewHrsServiceList.submit();

}
function Edit_Submit()
{
	var species=document.viewHrsServiceList.uType.value;
	if(species==""){
				alert ("Please select valid User Type" );
				return;
	}
	var checkId=document.getElementById('select11');
	 if(checkId==null&&species!=null)
                {
                    alert("No Records Found");
                    return;
                }
	var i,cnt,len = document.viewHrsServiceList.select11.length;
	cnt=0;
	if(len==undefined)
	{len=1;
            if(document.viewHrsServiceList.select11.checked)
	   {
               document.viewHrsServiceList.hrsTyId.value=document.viewHrsServiceList.select11.value;
		//Debug for Checkboxes purpose
		//alert(document.viewHrsServiceList.hrsTyId.value);
		//document.viewHrsServiceList.select11.checked=false;
		//document.viewHrsServiceList.selectHead.checked=false;
                cnt=cnt+1
					//Debug for Checkboxes purpose
           }
        }
        else
        {
	   for(i=0;i<len;i++)
	   {
		if(document.viewHrsServiceList.select11[i].checked)
		{
			document.viewHrsServiceList.hrsTyId.value=document.viewHrsServiceList.select11[i].value;
			//alert("enter edit");
			/*if(cnt==0)
			{
			//	alert("Enter the function");
				document.viewHrsServiceList.hrsTyId.value=document.viewHrsServiceList.select11[i].value;
				//alert(document.viewHrsServiceList.hrsTyId.value);
				document.viewHrsServiceList.select11[i].checked=false;
				document.viewHrsServiceList.selectHead.checked=false;
			}
			else
			{
				document.viewHrsServiceList.select11[i].checked=false;
			}*/
			cnt=cnt+1;
		}

	  }
        }
		//Debug for Checkboxes purpose
        if(cnt>1)
		{
			for(j=0;j<len;j++)
			{
				document.viewHrsServiceList.select11[j].checked=false;
				document.viewHrsServiceList.selectHead.checked=false;
			}
		}
		//Debug for Checkboxes purpose
	if(cnt==1)
	{
		//alert("Inside if condition");
		document.viewHrsServiceList.horseProcess.value='initEdit';
		document.viewHrsServiceList.submit();
	}
	else if(cnt>1)
	{
	       alert("Only one record can be edited.Please check one record to edit.");
		return;
	}
	else if(cnt==0)
	{
		alert("Please check one record to edit");
		return;
	}

}
function Delete_Submit()
{
var species=document.viewHrsServiceList.uType.value;
	if(species==""){
				alert ("Please select valid User Type" );
				return;
	}
	var checkId=document.getElementById('select11');
	 if(checkId==null&&species!=null)
                {
                    alert("No Records Found");
                    return;
                }
	var i,cnt,len = document.viewHrsServiceList.select11.length;
	cnt=0;
	if(len==undefined)
	{len=1;
            if(document.viewHrsServiceList.select11.checked)
                {
                    if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		   {
                    document.viewHrsServiceList.horseProcess.value='HrsServiceTyDelete';
                    document.viewHrsServiceList.submit();
		}
                else
		 {return;
		 }
                }
                else
                    {alert("Please select one record to Delete")
					return;}
        }
	for(i=0;i<len;i++)
	{
		if(document.viewHrsServiceList.select11[i].checked)
		{
			cnt=cnt+1;
		}

	}

	if(cnt==0)
	{
		alert("Please check the record(s) to be deleted.");
		return;
	}
        else
            {
                if(document.viewHrsServiceList.selectHead.checked)
                 {
					if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
					{
                    document.viewHrsServiceList.horseProcess.value='HrsServiceTyDelete';
                    document.viewHrsServiceList.submit();
					}
					else
					 {return;
					 }
                 }
				 else
				{
					 if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
					{
						document.viewHrsServiceList.horseProcess.value='HrsServiceTyDelete';
                    document.viewHrsServiceList.submit();
					}
                                        else
                                            {
                                                return;
                                            }
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

				<table width="758" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab" >

		<tr>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="1" cellpadding="0" cellspacing="0" cellspacing="0" class="tblInnerContainer" align="center">
     <tr>
       <td colspan="6" class="tblMainHead">
	Membership Services - Human:<span class="styleBoldTwo"> Listings</span></td>
  </tr>

        <tr>
                  <td colspan="5" class="tblDescrp">
                You are viewing the list of Membership Services - Human created by you.<br/>To create a Membership Service - Human click <strong>Add</strong> button.<br/>To edit a Membership Service - Human select the checkbox before each record and then click on the <strong>Edit</strong> button.<br/>
                To delete Membership Services - Human select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.</td>
                </tr>
 <form name="viewHrsServiceList" action="horseServiceMaster.do" method="post" >
  <input type="hidden" name="horseProcess" value="initEdit" />
  <% String userTypeName=(String)session.getAttribute("userTypeName");%>
  <input type="hidden" name="userTypeName" value="<%=userTypeName%>"/>
  <input type="hidden" name="hrsTyId" value=""/>
 <%                  String uTypeName=null;
			 String status = (String)request.getAttribute("status");

				if(status!=null)
				{
					if(status.equalsIgnoreCase("false"))
					{
				%>
				<!--<tr>
				<td class="styleError" colspan="4">Cannot delete this record. its already in use</td>
				</tr>-->
				<%
			}}
			%>

 <tr>
	  <td height="30">
	 <table border="0" cellpadding="0" align="center" cellspacing="0" width="600">
	 <tr>
	   <td>

	<strong>User Type: </strong>
<select name="uType" class="selectboxOne" onchange="postData();">
                                                                    <option value="" selected="selected" >Select One</option>
								  <%
									Vector vUType = (Vector)session.getAttribute("displayUserTypeDetails");
                                                                        String uTypeId=(String)session.getAttribute("userTypeId");
									if(vUType!=null && vUType.size()!=0){
										Enumeration eUtype = vUType.elements();
										 //String [] userType = {ID, name };
										while(eUtype.hasMoreElements()){
											String[] strType = (String [])eUtype.nextElement();
											String uTypeID = strType[0];
										 uTypeName = strType[1];
											if(uTypeID.equals(uTypeId)){
										%>
										  <option value="<%=uTypeID%>" selected="selected" ><%=uTypeName%></option>
										<%
											}
											else{
											%>
											 <option value="<%=uTypeID%>" ><%=uTypeName%></option>
											<%
											}
										}
										}
								%>
								</select>
                       </td><td align="right" colspan="3" style="text-align:right">
            <input name="Add" value="Add" type="button" class="gradBtn" onclick="Add_Submit();"  />
            <input name="Edit" value="Edit" type="button" class="gradBtn"  onclick="Edit_Submit();"  />
            <input name="Delete" value="Delete" type="button" class="gradBtn" onclick ="Delete_Submit();" />
	   </td>
	   </tr></table>
	   </td></tr>
                   <tr>
                         <tr><td>		<table border="0"  id="dataTable" cellpadding="0"  align="center" cellspacing="1" width="600" style='table-layout:fixed'>
			<td width="18"  class="tblRowHeadTwo"><input type="checkbox" name="selectHead" id="selectHead"  value="membershipTypeId" onclick="checkAll();"/></td>
			<td width="250" height="27" class="tblRowHeadTwo">Service  </td>
			<td width="80" height="27" class="tblRowHeadTwo">Cost $</td>
			<td width="50"  height="27" class="tblRowHeadTwo">Active </td>
			<td width="50"  height="27" class="tblRowHeadTwo">Inactive</td>
		   </tr>
		     <input type="hidden" name="UserTypeName" value="<%=uTypeName%>" />
		   <%
				Vector vDisHrsServiceType =(Vector)session.getAttribute("displayNonHumServiceDetails");
				if(vDisHrsServiceType!=null && vDisHrsServiceType.size()!=0){
					Enumeration hrsServiceTypeEnum = vDisHrsServiceType.elements();
                                        int i=0;
					while(hrsServiceTypeEnum.hasMoreElements()){
						String hrsServiceTypeDet[] = (String[])hrsServiceTypeEnum.nextElement();
						String hrsTyId = hrsServiceTypeDet[0];
						String horseServiceTypeName = hrsServiceTypeDet[1];
						String horseServiceTypeAmount = hrsServiceTypeDet[2];
                                                 int active_status=Integer.parseInt(hrsServiceTypeDet[3]);
						//String memberTypeDet[] = {membershipTypeId,membershipTypeName, membershipAmount,mDate};
						%>



						<tr>
						<td  class="listCellBg" style="text-align:center;"><input type="checkbox" name="select11" id="select11"  value="<%=hrsTyId%>"/>
							<td height="26" class="listCellBg" class="alignCenter" style = "text-align: center;word-wrap: break-word;"><%=horseServiceTypeName%></td>
				<%
				float price = Float.parseFloat(horseServiceTypeAmount);
				BigDecimal bdAmount = new BigDecimal(price);
				bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
				%>
							<td height="26" class="listCellBg"  style = "text-align: right;word-wrap: break-word;"><%=bdAmount%></td>
                                                        <%
							if(active_status==1){
                                                                            %>

									<td   class="listCellBg" style = "text-align: center;word-wrap: break-word;"><input type="radio" name=<%="rd"+ i%> value="active" checked="true" disabled /></td>
                                                                            <td class="listCellBg" style = "text-align: center;word-wrap: break-word;" ><input type="radio" name=<%="rd"+ i%> value="inactive" disabled/></td>
                                                                            <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td class="listCellBg" style = "text-align: center;word-wrap: break-word;" ><input type="radio" name=<%="rd"+ i%> value="active" disabled/></td>
                                                                            <td class="listCellBg" style = "text-align: center;word-wrap: break-word;"><input type="radio" name=<%="rd"+ i%> value="inactive" checked="true" readonly=true disabled /></td>
                                                                            <% } %>
						</tr>


				<%
					i=i+1;	}
				   }
					else{
				   %>
					<tr>
                                            <td height="26" colspan="5" class="listCellBg"  style="text-align:center;"><b>No Records  Found  </b></td>
					</tr>
				   <%
				   }
				   %>
				   <tr>
			<td height="19" colspan="4">&nbsp;</td>
           </tr>

                   </table>                 </form>
	  </table>
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