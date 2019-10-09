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
<script src="javascripts/frmCreateContactType.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<script language="javascript">
<!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
//function myvalidate(){

//-------------------------------------Contact Type name------------------------------------------------------------

/*if(document.frmEditContactType.txtcontactname.value == "" || document.frmEditContactType.txtcontactname.value.indexOf(' ')==0){
		alert('Contact Name cannot be empty');
		document.frmEditContactType.txtcontactname.focus();
		return false;
	}

 if(document.frmEditContactType.txtcontactname.value.length >150){
		alert("Contact Name cannot exceed the maximum of 150 characters");
		obj.txtcontactname.focus();
		return false;
	}
        
     return true;
    }*/

    function myvalidate(obj)
{

	vFlag = false;
	//Debugs Starts to check eixsting name for edit page by Lakshmi 26-05-11
    /*   if(obj.txtcontactname.value=="")
        {
		alert(" Please enter valid Contact Type");
		obj.txtcontactname.focus();
		return false;
	}
        if((obj.txtcontactname.value.length >50 )){
		alert("Contact Type cannot have more than 50 characters");
		obj.txtcontactname.focus();
		return false;
	}
        if((obj.txtcontactname.value.indexOf('	') == 0) || (obj.txtcontactname.value.lastIndexOf('	') == (obj.txtcontactname.value.length-1)) ||
	   (obj.txtcontactname.value.indexOf(' ') == 0) || (obj.txtcontactname.value.lastIndexOf(' ') == (obj.txtcontactname.value.length-1))){
                obj.txtcontactname.value = obj.txtcontactname.value.trim();
                obj.txtcontactname.focus();
                alert("Leading and Trailing spaces will be trimmed. Please Submit again");
	        return false;
	   }*/
         //Debugs Ends to check eixsting name for edit page by Lakshmi 26-05-11
	if(obj.txtcontactdescription.value=="")
        {
		alert(" Please enter valid Description");
		obj.txtcontactdescription.focus();
		return false;
	}
        
	if((obj.txtcontactdescription.value.length >150 )){
		alert("Description cannot have more than 150 characters");
		obj.txtcontactdescription.focus();
		return false;
	}

	if((obj.txtcontactdescription.value.indexOf('	') == 0) || (obj.txtcontactdescription.value.lastIndexOf('	') == (obj.txtcontactdescription.value.length-1)) ||
			(obj.txtcontactdescription.value.indexOf(' ') == 0) || (obj.txtcontactdescription.value.lastIndexOf(' ') == (obj.txtcontactdescription.value.length-1))){
                     obj.txtcontactdescription.value = obj.txtcontactdescription.value.trim();
					 obj.txtcontactdescription.focus();
   					 alert("Leading and Trailing spaces will be trimmed. Please Submit again");
			    	 return false;
	   }

	if(obj.ContactType.value=="")
        {
		alert(" Please select valid Type");
		obj.ContactType.focus();
		return false;
	}
        
	

        if(document.frmEditContactType.ContactType.value == "Select One"){
		alert('Please select valid Type');
		document.frmEditContactType.ContactType.focus();
		return false;
	}


	if(document.getElementById(obj.name).status1.checked==false && document.getElementById(obj.name).status2.checked==false){
		alert("Please choose the Status");
        return false;
	}

return true;

}


   function clearFields(obj){
          // document.frmEditContactType.txtcontactname.value="";
           document.frmEditContactType.txtcontactdescription.value="";
		   document.frmEditContactType.ContactType.value="";
           document.getElementById('frmEditContactType').status1.checked=false;
           document.getElementById('frmEditContactType').status2.checked=false;

          /* var vOptionsLen = 0;

		   if(document.frmEditContactType.ContactType.options[document.frmEditContactType.ContactType.selectedIndex].value!='')
		   {
			   document.frmEditContactType.ContactType.options.length = document.frmEditContactType.ContactType.options.length+1;
				   document.frmEditContactType.ContactType.options[document.frmEditContactType.ContactType.options.length-1].value = "";
				   document.frmEditContactType.ContactType.options[document.frmEditContactType.ContactType.options.length-1].text = "Select One";
				   document.frmEditContactType.ContactType.selectedIndex = document.frmEditContactType.ContactType.options.length-1;
		   }

		   for(var i=0;i<(obj.ContactType.options.length-1);i++)
		   {
				if(document.frmEditContactType.ContactType.options[i].value=='')
			   {
					vOptionsLen++;
					document.frmEditContactType.ContactType.options.length = document.frmEditContactType.ContactType.options.length - vOptionsLen;
					document.frmEditContactType.ContactType.selectedIndex = document.frmEditContactType.ContactType.options.length-1;

			   }
		   }
*/

           return true;
}
<!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
 function CancelContactType(){
     if(confirm("Do you want to Cancel and go back to List Page?")){
	strURL = "./contact.do?conprocess=list";
	window.location.href = strURL;
        }
	  else
	    {
		return;
	    }
       }
	</script>

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
                      <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
			<!--<td width="230" class="menuTablePad">-->
			<!-- LEFT MENU STARTS HERE -->
			<!--<!%@ include file = "../../include/menu-roles-leftmenu.jsp" %>-->
			<!-- LEFT MENU ENDS HERE -->

		   <!--</td>-->
                   <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Contact Type: <span class="styleBoldTwo"> Edit</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"> You can <strong>Edit/Update </strong> the <strong>Contact Type</strong> created by you as given below. <br />
	</td>
  </tr>
  
  			<%

				String contact_type_id= "";
				String contact_type_name= "";
                               //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                String contact_type_desc= "";
                                String contact_type= "";
                               String active_status= "";
                               //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
            String s [] = (String[])request.getAttribute("editcontypedetails");
			System.out.print("contact Details:" + s);
			if (s!=null){
				contact_type_id= s[0];
				contact_type_name= s[1];
                               //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                contact_type_desc= s[2];
                                contact_type= s[3];
                                active_status= s[4];
                               //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
			}
			%>
  
  <tr>
  	<td>
	
 







                <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
		<!--<form name="frmEditContactType" id="frmEditContactType" action="./contact.do" onsubmit="return myvalidate();">-->
                <form name="frmEditContactType" id="frmEditContactType" action="./contact.do" onsubmit="return myvalidate(this);">
                <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
		<input type="hidden" name="conprocess" value="updateContype" />
		<input type="hidden" name="contacttypeId" value="<%=contact_type_id%>" />
		<input type="hidden" name="oldContactName" value="<%=contact_type_name%>"/>
		<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			 <tr>
				  <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
		  	 </tr>
                    <!--Debugs Starts to check eixsting name for edit page by Lakshmi 26-05-11-->
                    <%      
				String status=(String)request.getAttribute("err");
				if(status!=null)
				{
				if(status.equalsIgnoreCase("st"))
					{%>
				  <tr>
                      <td class="styleError" colspan="4"><Strong>Contact Type already exists</Strong></td>
                  </tr>
				<%
                                 contact_type_name=(String)request.getAttribute("ContactName");
                                 System.out.print("name"+contact_type_name);
                                }
					}%>
                                        <!--Debugs Ends to check eixsting name for edit page by Lakshmi 26-05-11-->
				  <!--<tr>
					<td colspan="2" class="tblRowHead">
						Edit Contact Type</td>
				  </tr>-->
				 				  <tr>
                                        <!--<td class="tableLeft">Contact Type Name :</td>-->
					<td class="tableLeft">Contact Type:</td>
                                   <td class="tableRight"  width="200"><input name="txtcontactname" type="text" class="readOnly" value="<%=contact_type_name%>" maxlength="50" readOnly="true"/>
                                              <!--<span class="asterisk">*</span>--></td>
				 </tr>
                                        <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
                                         <tr>
					<td class="tableLeft">Description:</td>
                                        <td class="tableRight"  width="200"><input name="txtcontactdescription" type="text" style="" class="textboxOne" value="<%=contact_type_desc%>" maxlength="150" />
					<span class="asterisk">*</span></td>
				 </tr>

                                    <tr>
				<td class="tableLeft">Type:</td>
				<td width="297" class="tableRight">
								<select name="ContactType" class="selectboxOne">
                                                                    <option value="" selected="selected">Select One</option>
								<%
								ArrayList contType = (ArrayList) request.getAttribute("contType");
                                                                int icon1=0;
                                                                if(contType != null){
                                                                    icon1 = contType.size();
                                                                }
                                                                out.println("type is "+icon1);
                                                                System.out.println("-----------------Type is ------------------"+icon1);
								if(contType!=null){
								Iterator itr = contType.iterator();
								while(itr.hasNext()){
									String conTypeArray[] = (String[]) itr.next();
									String contactTypeId = conTypeArray[0];
									String contactTypeName =conTypeArray[1];
                                     System.out.println("hi"+contactTypeName);
                                    if(contact_type!=null && contact_type.equalsIgnoreCase(contactTypeId)){    
                                    %>
									<option value="<%=contactTypeId%>" selected="selected"><%=contactTypeName%></option>
								<%	
								}
									else{
								%>
								<option value="<%=contactTypeId%>"><%=contactTypeName%></option>
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

                                                                        if(Integer.parseInt(active_status)==1)
                                                                        {
                                                                            %>
                                                                            <td   width="200" class="tableRight" ><input type="radio" id="status1" name="status" value="1" checked="true"  />Active
                                                                            <input type="radio" id="status2" name="status" value="0" />Inactive  <span class="asterisk">*</span></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td  width="200" class="tableRight" ><input type="radio" id="status1" name="status" value="1" />Active
                                                                            <input type="radio" id="status2" name="status" value="0" checked="true"/>Inactive  <span class="asterisk">*</span></td>

                                                               <!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
                                                                              <% } %>
                                                                  </tr>
				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->
					<td colspan = "2" style="text-align:center" height="25">
					<input type="submit" class="gradBtn" value="Update" onClick=""/>&nbsp;&nbsp;
                                        <!--Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
					<input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
					<input type="button" value="Cancel" class="gradBtn" onclick="CancelContactType();" />
					<!--End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011-->
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
