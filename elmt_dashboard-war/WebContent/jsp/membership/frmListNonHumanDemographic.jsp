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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>

<script language="javascript">
 function addRow()
{
	/*if(document.frmReqForm.selSpecies.value==""){
		alert ("Please select valid Species" );
	}else{*/
	var speciesId = document.frmReqForm.selSpecies.value;
	strURL = "./Demographic.do?horseprocess=initAddNonHum&speciesId="+speciesId;
	window.location.href = strURL;
	//}
}

function editRow(tableID)
{
	var nonHumanDemoId;
	var chkCnt=0;
	var vFlag = false;
	var speciesId = document.frmReqForm.selSpecies.value;
	if(document.frmReqForm.selSpecies.value==""){
		alert ("Please select valid Species" );
	}
	 var chk=document.getElementById('chk');
                if(chk==null && speciesId!="")
                {
                    alert("No Records Found");
                    return;
                }
	if(document.frmMyForm.chk!=null){
	
	var speciesId = document.frmReqForm.selSpecies.value;
	var chkBoxCnt = document.frmMyForm.chk.length;

    if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmMyForm.chk[i].checked==true)
			{
				   nonHumanDemoId = document.frmMyForm.chk[i].value;
				   chkCnt++;
			}

		}
	}
	else
	{
			if(document.frmMyForm.chk.checked==true)
			{
				   nonHumanDemoId = document.frmMyForm.chk.value;
				   chkCnt++;
			}

	}
 //Starts for checkbox 
                if(chkCnt>1)
                {
                    for(var j=0;j<chkBoxCnt;j++)
                    {
                        vFlag = true;
                        document.frmMyForm.chk[j].checked = false;
                        document.frmMyForm.chkAll.checked = false;
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

               strURL = "./Demographic.do?&horseprocess=initEditNonHuman&chk="+nonHumanDemoId+"&speciesId="+speciesId;
		window.location.href = strURL;
		
		}
}
   

function checkAll()
{
         if(document.frmMyForm.chk!=null){
		var chkBoxCnt = document.frmMyForm.chk.length;
		if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
		{
			for(var i=0;i<chkBoxCnt;i++)
			{
				 if(document.frmMyForm.chkAll.checked==true)
				 {
					 document.frmMyForm.chk[i].checked = true;
				 }
				 else
				 {
					document.frmMyForm.chk[i].checked = false;
				 }
			}
		}
		else
		{
				 if(document.frmMyForm.chkAll.checked==true)
				 {
					 document.frmMyForm.chk.checked = true;
				 }
				 else
				 {
					document.frmMyForm.chk.checked = false;
				 }

		}

}

}

function postData(selSpecies) {
	
	
		strURL = "./Demographic.do?horseprocess=reqList&selSpecies="+selSpecies;
		window.location.href = strURL; 	
	
}



 function deleteRow(tableID) {

         
	 var nonHumanDemoId;
	 var chkCnt=0;
	 var vFlag = 0;
	 var cannotDelete=0;
	 var canDelete = 0;
	 var speciesId = document.frmReqForm.selSpecies.value;
	
	 var chk=document.getElementById('chk');
                if(chk==null && speciesId!="")
                {
                    alert("No Records Found");
                    return;
                }
	 if(document.frmReqForm.selSpecies.value==""){
	alert ("Please select valid Species" );
	}else{
	 if(document.frmMyForm.chk!=null){
	 var chkBoxCnt = document.frmMyForm.chk.length;

            if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
            {
                for(var i=0;i<chkBoxCnt;i++)
		{
                   
                	if(document.frmMyForm.chk[i].checked==true)
			{
                           
        
                          if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records?"))
		                {
			 document.frmMyForm.horseprocess.value = 'deleteDemoDets';
                         document.frmMyForm.selSpecies1.value = document.frmReqForm.selSpecies.value;
			 document.frmMyForm.submit();
                        
		                }
                           return;
			}

                }
            }
            else
            {
                	if(document.frmMyForm.chk.checked==true)
			{
                            if(confirm("Only the checked records will be deleted. \nAre you sure you want to delete these records? "))
		                {
			 document.frmMyForm.horseprocess.value = 'deleteDemoDets';
                         document.frmMyForm.selSpecies1.value = document.frmReqForm.selSpecies.value;
			 document.frmMyForm.submit();

		                }
                            return;
			}

            }
         if(chkCnt==0)
               {

                    alert("Please check the record(s) to be deleted");
                    return;
                }
				}
				
				}
  }




/*function getDemoList(){
			
		if(document.getElementById('selSpecies').selectedIndex==0)
		{
			alert("Select any Species");
			document.getElementById('selSpecies').options.selectedIndex=0;
			document.getElementById('selSpecies').focus();
			return false;
		}
		
		showData();
		
	}

function showData() {
	var speciesId = document.frmReqForm.selSpecies.value;
	
	strURL = "./Demographic.do?horseprocess=demoListForPagin&selSpecies="+speciesId;
	window.location.href = strURL;
}	


function postDataForPagination() {
		var pn = document.frmPagination.pn.value;
		var specId = document.frmReqForm.selSpecies.value;
		strURL = "./Demographic.do?&horseprocess=demoListForPagin&pn="+pn+"&selSpecies="+specId;
		window.location.href = strURL;	
	}

*/

    </script>
</head>

    <body>

<table width="780" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab" >
  <tr>
    <td class="alignTop"><!-- HEADER STARTS HERE -->
      		<%@ include file = "../../include/header.jsp" %>
        <!-- HEADER ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="infoBar"><!-- INFO BAR STARTS HERE -->
        <%@ include file = "../../include/infobar.jsp" %>
        <!-- INFO BAR ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="tableCommonBg">

		<table width="758" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab" style='table-layout:fixed'>
		  <tr>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="1" cellpadding="0" cellspacing="0" cellspacing="0" class="tblInnerContainer" >
     <tr>
       <td colspan="6" class="tblMainHead">
	 Demographic Categories - Non-human: <span class="styleBoldTwo"> Listings  </span></td>
                </tr>
	<%
ArrayList speciesList = (ArrayList)request.getAttribute("speciesList");
ArrayList typeList = (ArrayList)request.getAttribute("typeList");
%>
			
        <tr>
                  <td colspan="6" class="tblDescrp">
                   You are viewing the list of Demographic Categories - Non-human created by you.<br/>
                   To create a Demographic Category - Non-human click <strong>Add</strong> button.<br/>
                   To edit a Demographic Category - Non-human select the checkbox before each record and then click on the  <strong>Edit</strong> button.<br/>
                   To delete Demographic Categories - Non-human select the checkbox(s) before each record and then click on the <strong>Delete</strong> button.
				   </td>
				</tr>
				 <%
								 String speciesId = (String) request.getAttribute("speciesId");
								 if(speciesId==null) speciesId = "";
							 %>
	 <form name="frmReqForm">

 <tr>
	  <td height="30">
	 <table border="0" cellpadding="0" align="center" cellspacing="0"  width="750">

	 <tr>
	 <td>
					   		<input type="hidden" name="horseprocess" value="reqList"/>		
					Species:
						&nbsp;&nbsp;&nbsp;<select name="selSpecies" id="selSpecies" class="selectboxOne" onchange="postData(this.value);">
										<option selected="selected" value="">Select One</option>
							<%
				if(speciesList!=null && speciesList.size()!=0){
					Iterator it = speciesList.iterator();
					while(it.hasNext()){
						String [] dDet = (String[])it.next();
						String userTypId = dDet[0];
						String userTypName = dDet[1];
				
				if(speciesId!=null && speciesId.equalsIgnoreCase(userTypId)){
			%>
			<option value="<%=userTypId%>" selected="selected"><%=userTypName%></option>
			
			<%} else{%>
			
			<option value="<%=userTypId%>"><%=userTypName%></option>
		
			<%}}}%>					
																												
	</td>
	<td align="right">
                        <input type="button" value="Add" onclick="addRow()" class="gradBtn" align="right"/>
                        <input type="button" value="Edit" onclick="editRow('dataTable')" class="gradBtn" align="right"/>
                        <input type="button" value="Delete" onclick="deleteRow('dataTable')" class="gradBtn" align="right" name="del"/>                   		</form>	 </td>
                </tr>
	   </form>
	 </table>
	 </td>
	    <tr>
			<td>
		<table border="0"  id="dataTable" cellpadding="0"  align="center" cellspacing="1" width="750" style='table-layout:fixed'>
			<form name="frmMyForm" id="frmMyForm" action="Demographic.do" >
                        <input type="hidden" name="horseprocess" id="horseprocess" value="initEditNonHuman" />
						   <input type="hidden" name="selSpecies1" id="selSpecies1" value/>


			<td width="18" height="27" class="tblRowHeadTwo">
			<input type="checkbox" name="chkAll" onClick = "checkAll()"> </td></td>
                       <td width="150" height="27" class="tblRowHeadTwo">Demographic</td>
            <td width="270" height="27" class="tblRowHeadTwo">Description</td>
		    <td width="140" height="27" class="tblRowHeadTwo">Type</td>
            <td width="50" height="27" class="tblRowHeadTwo">Active</td>
		    <td width="50" height="27" class="tblRowHeadTwo">Inactive</td>
                      </tr>
	
					   <%
						 ArrayList demoList = (ArrayList) request.getAttribute("demoList");
          					 if(demoList!=null && demoList.size()!=0){
							Iterator it1 = demoList.iterator();
							while(it1.hasNext()){
								String[] s = (String[])it1.next();
								 String nonHumanDemoId = s[0];
								String demographicName = s[1];
							
                                                                String demographicDesc=s[2];
                                                                String listTypeName=s[3];
                                                                String activeStatus=s[4];
                                                                int chkBoxIndex = 0;
																int stat=0;
																if(activeStatus.equalsIgnoreCase("true")){
																stat=1;
																}else{
																stat=0;
																}
																
																
						

		                %>
					 <input type="hidden" name="nonHumanDemoId" value="<%=nonHumanDemoId%>" />	

						<tr>

						<td  class="listCellBg" style="text-align:center;"> <input type="checkbox" name="chk" id="chk" value="<%=nonHumanDemoId%>" />

							<td height="26" class="listCellBg" class="alignCenter" style = "text-align: center;word-wrap: break-word;"><%=demographicName%></td>

						<td height="26" class="listCellBg" class="alignCenter" style = "text-align: center;word-wrap: break-word;"><%=demographicDesc%></td>
						<td height="26" class="listCellBg" style = "text-align: center;word-wrap: break-word;" ><%=listTypeName%></td>

                                                         <%

                                                                        if(stat==1)
                                                                        {
                                                                            %>
									<td   class="listCellBg"  style="text-align:center;"><input type="radio" name="<%=nonHumanDemoId%>" value="1" checked="true" disabled="true" /></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=nonHumanDemoId%>" value="0"  disabled="true" /></td>
                                                                            <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td   class="listCellBg" style="text-align:center;"><input type="radio" name="<%=nonHumanDemoId%>" value="1"  disabled="true" /></td>
                                                                            <td width="40" class="listCellBg" style="text-align:center;"><input type="radio" name="<%=nonHumanDemoId%>" value="0" checked="true" disabled="true"  /></td>
                                                                            <% } %>
						</tr>
       	              
						<%
                                           
                                                chkBoxIndex++;
                                              
                                                        }
					 }
					else{
				   %>
					<tr>
		  <td height="20" colspan="6"  class="listCellBg"><div class="alignCenter"><strong>No Records Found</strong></div></td>
		</tr>
				   <%
				   }
				   %>
 <tr>
			<td height="19" colspan="3">&nbsp;</td>
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
