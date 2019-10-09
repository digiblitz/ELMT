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
<script src="javascripts/frmCreateContactType.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<!--Start:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->

<script>
     function CancelContactType(){
         if(confirm("Do you want to Cancel and go back to List Page?"))
	{
	strURL = "./Demographic.do?horseprocess=list";
	window.location.href = strURL;
         }
	else
	{
		return;
	}
       }
       function myvalidate(obj){
	 vFlag = false;
      /* if(obj.demographic.value == "" || obj.demographic.value.indexOf(' ')==0){
		alert('Please enter valid Demographic');
		obj.demographic.focus();
		return false;
	}*/
        if(obj.demoDesc.value == ""){
		alert('Please enter valid Description');
		obj.demoDesc.focus();
		return false;
	}



	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			if((obj.elements[i].value.indexOf('	') == 0) || (obj.elements[i].value.lastIndexOf('	') == (obj.elements[i].value.length-1)) ||
			(obj.elements[i].value.indexOf(' ') == 0) || (obj.elements[i].value.lastIndexOf(' ') == (obj.elements[i].value.length-1)))
			{
				obj.elements[i].value = obj.elements[i].value.trim();
                                obj.elements[i].focus();
				vFlag = true;
			}
		}
	}

	if(vFlag==true)
	{
		alert("Leading and Trailing spaces will be trimmed. Please submit again.");
		return false;
	}

	if(obj.DemogType.value == ""){
		alert('Please select valid Type');
		obj.DemogType.focus();
		return false;
	}

 if(document.getElementById('frmMemDemoEdit').status1.checked==false && document.getElementById('frmMemDemoEdit').status2.checked==false)
		{
				alert("Please choose the Status");
                                return false;
		}
    return true;
    }

         function clearFields(obj){

           //obj.demographic.value="";
		   obj.demoDesc.value="";

           document.getElementById('frmMemDemoEdit').status1.checked=false;

           document.getElementById('frmMemDemoEdit').status2.checked=false;

            document.frmDemoType.DemogType.selectedIndex=""; 
         /*  var vOptionsLen = 0;


		   if(obj.DemogType.options[obj.DemogType.selectedIndex].value!='')
		   {

			      obj.DemogType.options.length = obj.DemogType.options.length+1;

				  obj.DemogType.options[obj.DemogType.options.length-1].value = "";

				  obj.DemogType.options[obj.DemogType.options.length-1].text = "Select One";

				  obj.DemogType.selectedIndex = obj.DemogType.options.length-1;

		   }

		   for(var i=0;i<(obj.frmMemDemoEdit.options.length-1);i++)
		   {
				if(obj.DemogType.options[i].value=='')
			   {
					vOptionsLen++;
					obj.DemogType.options.length = obj.DemogType.options.length - vOptionsLen;
					obj.DemogType.selectedIndex = obj.DemogType.options.length-1;

			   }
		   }*/


           return true;
}



</script>
<!--End:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->

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
  <!--Start:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->
  <tr>
    <td class="tableCommonBg">

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">

		  <tr>

			<td width="500" class="subDeptTablePad">

<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Demographic Categories - Human: <span class="styleBoldTwo">Edit</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">You can <strong>Edit/Update</strong> the <strong>Demographic Category - Human</strong> created by you as given below.<br />
	</td>
  </tr>

  				<%
                                                                HLCHorseColorVO Vobj1 = (HLCHorseColorVO) request.getAttribute("Vobj");
						                   String demoId = Vobj1.getDemoId();
						                   String demographic = Vobj1.getDemographic();
                                                                   String demoDescription = Vobj1.getDemoDescription();
						                   String demoType = Vobj1.getDemoType();
                                                                   String status = Vobj1.getStatus();

                                                                     System.out.println("horseId ="+ demoId);
                                                                     System.out.println("Demographic"+demographic);
                                                                      System.out.println("DemoDescription ="+ demoDescription);
                                                                     System.out.println("DemoType"+demoType);
                                                                      System.out.println("Status ="+ status);


								%>
							<!--End:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->
<!--Start:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->
<script>


function onSubmitFields(obj){

	var oldDemoDescription='<%=demoDescription%>';
	var oldDemoType='<%=demoType%>';
	var oldStatus='<%=status%>';

	var newDemoDescription=obj.demoDesc.value;
	var newDemoType=obj.DemogType.options[obj.DemogType.selectedIndex].value;
	var newStatus;

	if(document.getElementById('frmMemDemoEdit').status1.checked == true)
		newStatus = document.getElementById('frmMemDemoEdit').status1.value;
	else if(document.getElementById('frmMemDemoEdit').status2.checked == true)
		newStatus = document.getElementById('frmMemDemoEdit').status2.value;

	if(oldDemoDescription== newDemoDescription || oldDemoType==newDemoType || oldStatus==newStatus){
		alert('Old and New values are same. Please change any of the values');
		return false;
	}

	obj.submit();



 }

</script>
<!--End:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->


<!--Start:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->
  <tr>
  	<td>

                <form name="frmDemoType" id="frmMemDemoEdit" action="Demographic.do" onsubmit="return myvalidate(this);">

		<input type="hidden" name="horseprocess" value="update" />
		<input type="hidden" name="demoId" value="<%=demoId%>" />
		<input type="hidden" name="olddemographic" value="<%=demographic%>"/>
                <input type="hidden" name="olddemodescr" value="<%=demoDescription%>"/>
                <input type="hidden" name="olddemotype" value="<%=demoType%>"/>
                <input type="hidden" name="olddemostatus" value="<%=status%>"/>

		<table width="740" border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			 <tr>
				  <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
		  	 </tr>
                    <!--Debugs Starts by Lakshmi-->
			 <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					<tr>
					<td colspan="2" class="styleError"><strong>Demographic already exists</strong></td>
					</tr>
					<%
						demographic=(String)request.getAttribute("demographic");
					}
					%>
                                        <!--Debugs Ends by Lakshmi-->

				 				  <tr>

					<td class="tableLeft">Demographic:</td>
                                        <td class="tableRight"  width="200"><input name="demographic" type="text" class="readOnly" value="<%=demographic%>" maxlength="50" readOnly="true" />
										<!--<span class="asterisk">*</span>--></td>
				 </tr>

                                         <tr>
					<td class="tableLeft">Description:</td>
                                        <td class="tableRight"  width="200"><input name="demoDesc" type="text" style="" class="textboxOne" value="<%=demoDescription%>" maxlength="150" />
					<span class="asterisk">*</span></td>
				 </tr>

                                    <tr>
				<td class="tableLeft">Type:</td>
				<td width="297" class="tableRight">
								<select name="DemogType" class="selectboxOne">
                                                                   	 <option value="" selected="selected">Select One</option>

								<%
								ArrayList DemoTypes = (ArrayList) request.getAttribute("DemoType");

                                                               int icon1=0;
                                                                if(DemoTypes != null){
                                                                    icon1 = DemoTypes.size();
                                                                }
                                                                out.println("type is "+icon1);
                                                                System.out.println("-----------------Type is ------------------"+icon1);
								if(DemoTypes!=null){
								Iterator itr = DemoTypes.iterator();
								while(itr.hasNext()){
									String DemoTypeArray[] = (String[]) itr.next();
									String demoTypeId = DemoTypeArray[0];
									String demoTypeName =DemoTypeArray[1];
                                                                       System.out.println("hi"+demoTypeName);
																	   System.out.println("demoType "+demoType+"=="+demoTypeName);
									if(demoType!=null && demoType.equalsIgnoreCase(demoTypeName))
									//if(demoType.equals(demoTypeName))
									{
									%>
										<option value="<%=demoTypeId%>" selected="selected"><%=demoTypeName%></option>
								<%   }
									else
									{
										%>
											<option value="<%=demoTypeId%>"><%=demoTypeName%></option>
										<%
									}
								  }
								}
								%>
								</select>  <span class="asterisk">*</span>
							</td>
				</tr>
     <tr>
                                               	<td class="tableLeft"> Status:</td>
							 <%

                                                                        if(Integer.parseInt(status)==1)
                                                                        {
                                                                            %>
                                                                            <td   width="200" class="tableRight" ><input type="radio" id="status1" name="status" value="true" checked="true"  />Active
                                                                            <input type="radio" id="status2" name="status" value="false" />Inactive  <span class="asterisk">*</span></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td  width="200" class="tableRight" ><input type="radio" id="status1" name="status" value="true" />Active
                                                                            <input type="radio" id="status2" name="status" value="false" checked="true"/>Inactive   <span class="asterisk">*</span></td>


                                                                              <% } %>
                                                                  </tr>
				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->
					<td  colspan = "2" style="text-align:center" height="25">
					<input type="Submit" class="gradBtn" value="Update"  />&nbsp;&nbsp;

                                        <input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;

					<input type="button" value="Cancel" class="gradBtn" onclick="CancelContactType();" />
					</td>
				  </tr>
			</table>

		</form>
	</td>
  </tr>
<!--End:[DemographicDetails] For Demographic Type Edit changes dated 11-Apr-2011-->

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
