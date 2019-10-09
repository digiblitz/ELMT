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
<%@ page import="com.vo.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>

<script language="javascript" type="text/javascript">

//-----------------------------------is not alpha1----------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*|<>?/=~`;:"+"\\"+"\'"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
{ f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

//------------------------DOSPACE--------------------------------------------------------------------------
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}

function isnotInteger(str){
stringCheck="0123456789.";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
{ f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}

function myValidation(){
		
	if(document.frmlifecycleproc.frmDate.value==""){
	alert("Select Date");
	document.frmlifecycleproc.frmDate.focus();
	return false;
	}
	
		
	if(document.frmlifecycleproc.SelArtifacttype.value==""){
	alert("Select Artifact Type");
	document.frmlifecycleproc.SelArtifacttype.focus();
	return false;
	}
	if(document.frmlifecycleproc.txtLifeProcees.value==""){
	alert("Lifecycle Process Name can not be empty");
	document.frmlifecycleproc.txtLifeProcees.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtLifeProcees.value.indexOf(" ")==0)
 {alert("Please enter a Lifecycle Process Name");
  document.frmlifecycleproc.txtLifeProcees.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtLifeProcees.value))
{ alert("Please enter a valid Lifecycle Process Name. Numbers and Symbols are not allowed");
  document.frmlifecycleproc.txtLifeProcees.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtLifeProcees.value)){
			alert("Please enter a valid Lifecycle Process Name");
	     	document.frmlifecycleproc.txtLifeProcees.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtLifeProcees.value)){
			alert("Enter Valid Lifecycle Process Name");
	     	document.frmlifecycleproc.txtLifeProcees.focus();
	  		return false;
			}
	if(document.frmlifecycleproc.txtLifeProcees.value.length>30)
  {alert("Lifecycle Process Name too long");
  document.frmlifecycleproc.txtLifeProcees.focus();
  return false;}
	
	if(document.frmlifecycleproc.selNolifeStage.value==""){
	alert("Select number of lifecycle stages");
	document.frmlifecycleproc.selNolifeStage.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.selNolifeStage.value=="1"){
	alert("Select more than 1 number of lifecycle stages");
	document.frmlifecycleproc.selNolifeStage.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.selStages.value==""){
	alert("Select the lifecycle stage");
	document.frmlifecycleproc.selStages.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.selStages.value!=""){
	var totstagesss=document.frmlifecycleproc.selNolifeStage.value;
	if(document.frmlifecycleproc.selStages.value>totstagesss){
	alert("Stage exceeded the number of lifecycle stages.");
	document.frmlifecycleproc.selStages.focus();
	return false;
	}
	}
	
	
	if(document.frmlifecycleproc.txtstagename.value==""){
	alert("Enter Stage Name");
	document.frmlifecycleproc.txtstagename.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtstagename.value.indexOf(" ")==0)
 {alert("Enter a Stage Name");
  document.frmlifecycleproc.txtstagename.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtstagename.value))
{ alert("Enter Valid Stage Name");
  document.frmlifecycleproc.txtstagename.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtstagename.value)){
			alert("Enter Valid Stage Name");
	     	document.frmlifecycleproc.txtstagename.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtstagename.value)){
			alert("Enter Valid Stage Name");
	     	document.frmlifecycleproc.txtstagename.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtstagename.value.length>30)
			  {alert("Enter Stage Name within 30");
			  document.frmlifecycleproc.txtstagename.focus();
			  return false;}
	
	if(document.frmlifecycleproc.txtInput.value==""){
	alert("Enter Input");
	document.frmlifecycleproc.txtInput.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtInput.value.indexOf(" ")==0)
 {alert("Enter a Input");
  document.frmlifecycleproc.txtInput.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtInput.value))
{ alert("Enter Valid Input");
  document.frmlifecycleproc.txtInput.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtInput.value)){
			alert("Enter Valid Input");
	     	document.frmlifecycleproc.txtInput.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtInput.value)){
			alert("Enter Valid Input");
	     	document.frmlifecycleproc.txtInput.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtInput.value.length>30)
			  {alert("Enter Input within 30");
			  document.frmlifecycleproc.txtInput.focus();
			  return false;}
	
	
	if(document.frmlifecycleproc.txtprocess.value==""){
	alert("Enter process");
	document.frmlifecycleproc.txtprocess.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtprocess.value.indexOf(" ")==0)
 {alert("Enter a process");
  document.frmlifecycleproc.txtprocess.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtprocess.value))
{ alert("Enter Valid process");
  document.frmlifecycleproc.txtprocess.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtprocess.value)){
			alert("Enter Valid process");
	     	document.frmlifecycleproc.txtprocess.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtprocess.value)){
			alert("Enter Valid process");
	     	document.frmlifecycleproc.txtprocess.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtprocess.value.length>30)
			  {alert("Enter Process within 30");
			  document.frmlifecycleproc.txtprocess.focus();
			  return false;}
		
			
	if(document.frmlifecycleproc.txtDesc.value==""){
	alert("Enter Description");
	document.frmlifecycleproc.txtDesc.focus();
	return false;
	}
	if(document.frmlifecycleproc.selControls.value==""){
	alert("Select number of controls/votes");
	document.frmlifecycleproc.selControls.focus();
	return false;
	}
	if(document.frmlifecycleproc.selVotes.value==""){
	alert("Select number of votes required by");
	document.frmlifecycleproc.selVotes.focus();
	return false;
	}
	
	if(document.frmlifecycleproc.txtOutput.value==""){
	alert("Enter output");
	document.frmlifecycleproc.txtOutput.focus();
	return false;
	}
	if(document.frmlifecycleproc.txtOutput.value.indexOf(" ")==0)
 {alert("Enter a output");
  document.frmlifecycleproc.txtOutput.focus();
 return false;}
 
 if(isnotAlpha1(document.frmlifecycleproc.txtOutput.value))
{ alert("Enter Valid output");
  document.frmlifecycleproc.txtOutput.focus();
   return false; }
   
    if(Dospace(document.frmlifecycleproc.txtOutput.value)){
			alert("Enter Valid output");
	     	document.frmlifecycleproc.txtOutput.focus();
	  		return false;
			}
			
			 if(isnotInteger(document.frmlifecycleproc.txtOutput.value)){
			alert("Enter Valid output");
	     	document.frmlifecycleproc.txtOutput.focus();
	  		return false;
			}
			 if(document.frmlifecycleproc.txtOutput.value.length>30)
			  {alert("Enter Output within 30");
			  document.frmlifecycleproc.txtOutput.focus();
			  return false;}
	
	
	return true;
}


var version = navigator.appVersion; 

function showKeyCode(e) { 
var keycode = (window.event) ? event.keyCode : e.keyCode; 

if ((version.indexOf('MSIE') != -1)) { 
if (keycode == 116) { 
event.keyCode = 0; 
event.returnValue = false; 
return false; 
} 
} 
else { 
if (keycode == 116) { 
return false; 
} 
} 
} 

var message="Sorry, right-click has been disabled"; 
/////////////////////////////////// 
function clickIE() {if (document.all) {(message);return false;}} 
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) { 
if (e.which==2||e.which==3) {(message);return false;}}} 
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;} 
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;} 
document.oncontextmenu=new Function("return false") 

window.onbeforeunload = function() {
  return "Data will be lost if you leave the page, are you sure?";
};

</script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>

 <%
 
            String fstname=(String)session.getAttribute("firstName");
			  String userLname=(String)session.getAttribute("lastName");
			  
			  
			  String fullName=fstname+" "+userLname;
                String lifecycleProNameinputType = (String) request.getAttribute("lifecycleProName");
                String numStage = (String) request.getAttribute("numofStage");
                String dateofCreat = (String) request.getAttribute("dateofCreation");
                String addArtitype = (String) request.getAttribute("addArtifacttype");
                if (addArtitype == null) {

                	addArtitype = "";
				}
                
                else{
                	
                	
                }
				
                if (lifecycleProNameinputType == null) {

                	lifecycleProNameinputType = "";
				}
				
                
                if (numStage == null) {

                	numStage = "";
				}
                if (dateofCreat == null) {

                	dateofCreat = "";
				}
               
    %>
<body onload="JavaScript:document.body.focus();" onkeydown="return showKeyCode(event)">
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
 
 
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
	  
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
		<%@ include file = "../../include/infobar.jsp"  %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
   
   <tr>
   <td><table width="100%" border="1" align="center" cellpadding="" cellspacing="" id="centerTab">
   <tr>
   
  
 <% String msg=(String)request.getAttribute("Success");
%>

			<td>   
	<div class="cmmnForm">
	<div class="colspan">
		Lifecycle Process:<span class="styleBoldTwo"> Create</span>	
		<%if(msg!=null) {%>
		<script language="javascript">
		
 function popitup(url) {
	 if(confirm(msg))
	 {
		newwindow=window.open(msg,'name','height=450,width=800,scrollbars=no,resizable=no,top=300,left=500,toolbar=no,menubar=no,dialog=yes');
                        if (window.focus) {newwindow.focus()}
                        return false;
	 }
	 else
	 {
		 return;
	 }

}
</script>
 <%}%>
	</div>
   	

    <form name="frmlifecycleproc" id="frmlifecycleproc" method="post" action="SysMgmt.do?process=callArtadd" class="formcss" onsubmit="return myValidation();">

                                                                                           							
								<div class="row">
										<span class="label">Date of Lifecycle</span>
									  <span class="formX"><input name="frmDate" type="text" id="frmDate"  class="textboxOne" value="<%=dateofCreat%>" size="10" readonly="true"/>

             <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> 
									     <span class="asterisk">*</span></span></div>
									     
									      <div class="row">
										<span class="label">Artifact Type:</span>									  <span class="formX">
										<select name="SelArtifacttype" class="selectboxOne" id="SelArtifacttype">
										
									
                                                <option selected="selected" value="">Select One</option>
 <%
                                                       
                                                            Map<String,String> artiType = (HashMap) session.getAttribute("artifactType");
                                                            if (artiType != null && artiType.size() != 0) {
                                                           Iterator iter = artiType.entrySet().iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	Map.Entry me = (Map.Entry)iter.next();
                                                                    String artifactType =(String) me.getValue();
                                                                    String artifactId =(String) me.getKey(); 
                                                                    if (artifactId.equals(addArtitype)) {

                                                %>
                                                <option value="<%=artifactId%>" selected="selected" ><%=artifactType%></option>
                                                <%
                                                 } else {
                                                %>
                                                <option value="<%=artifactId%>" ><%=artifactType%></option>
                                                <%
                                                                    }
                                                                }
                                                            }
                                                %>
                                               
                                            </select>&nbsp;<span class="asterisk">*</span></span></div>
									     
									     <div class="row">
										<span class="label">Lifecycle Process Name:</span>
									  <span class="formX">
									  <% if(lifecycleProNameinputType.equalsIgnoreCase("")){ %><input type="text" name="txtLifeProcees" id="txtLifeProcees" class="textboxOne" size="20"/><%} else { %><input type="text" name="txtLifeProcees" value="<%=lifecycleProNameinputType %>" readonly="readonly"  class="textboxOne" size="20"/><% } %>
									     <span class="asterisk">*</span></span></div>
									   
									     
								  <div class="row">
										<span class="label">Number of Lifecycle Stages:</span>									  
										
										<% if(numStage=="") {%>
										<span class="formX">
																
										<select name="selNolifeStage" class="selectboxOne" id="selNolifeStage">
                                                <option selected="selected" value="">Select One</option>
                                                 
                                              <%
                                                       
                                                            ArrayList numlifeCycle = (ArrayList) session.getAttribute("lifeCycle");
                                                            if (numlifeCycle != null && numlifeCycle.size() != 0) {
                                                            	Iterator  lifeCycle = numlifeCycle.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (lifeCycle.hasNext()) {
                                                                    String strType = (String) lifeCycle.next();
                                                                    String numlifeStage = strType;
                                                                 
                                                                   

                                                %>
                                               
                                               
                                               
                                                <option value="<%=numlifeStage%>"><%=numlifeStage%></option>
                                                <%
                                                                    
                                                                }
                                                            }
                                                %>
                                               
                                            </select>&nbsp;<span class="asterisk">*</span>
											
											</span>
											<%}else{%>
											
								 <span class="formX">
									<input type="text" name="selNolifeStage" id="selNolifeStage" value="<%=numStage%>" readonly="readonly"  class="textboxOne" size="20"/>
									     <span class="asterisk">*</span></span>			
											
											
											
											<%}%>
											
											
											
											
											</div>

											<div class="row">
										<span class="label">Stage</span>									  <span class="formX"><select name="selStages" class="selectboxOne" id="selStages"><option selected="selected" value="">Select One</option>
                                                  <%
                                                       
                                                            ArrayList StageCycle = (ArrayList) session.getAttribute("StageCycle");
                                                            if (StageCycle != null && StageCycle.size() != 0) {
                                                            	Iterator  StaCycle = StageCycle.iterator();
                                                              
                                                                while (StaCycle.hasNext()) {
                                                                    String strType = (String) StaCycle.next();
                                                                    String Stage = strType;
                                                                 
                                                                    if (Stage.equals("")) {

                                                %>
                                                <option value="<%=Stage%>" selected="selected" ><%=Stage%></option>
                                                <%
                                                 } else {
                                                %>
                                                <option value="<%=Stage%>" ><%=Stage%></option>
                                                <%
                                                                    }
                                                                }
                                                            }
                                                %>

                                               
                                            </select>&nbsp;<span class="asterisk">*</span></span></div>

	<div class="row">
										<span class="label">Stage Name:</span>
									  <span class="formX"><input type="text" name="txtstagename" class="textboxOne" size="20" id="txtstagename"/>
									     <span class="asterisk">*</span></span></div>
									     
								<div class="row">
										<span class="label">Input:</span>
									  <span class="formX"><input type="text" name="txtInput" class="textboxOne" size="20" id="txtInput"/>
									     <span class="asterisk">*</span></span></div>
									     <div class="row">
										
								<div class="row">
										<span class="label">Process:</span>
									  <span class="formX"><input type="text" name="txtprocess" class="textboxOne" size="20" id="txtprocess"/>
									     <span class="asterisk">*</span></span></div>
									     <div class="row">
									     <span class="label">Description</span>
									  <span >
									  <textarea rows="4" cols="2" name="txtDesc" id="txtDesc"></textarea>
									     <span class="asterisk">*</span></span></div>
									   
									   
									       <div class="row">
										<span class="label">Numbers of Controls/Votes</span>
									   <span class="formX"><select name="selControls" class="selectboxOne" id="selControls"><option selected="selected" value="">Select One</option>
                                                <%
                                                       
                                                            ArrayList numberVoters = (ArrayList) session.getAttribute("numberVoters");
                                                            if (numberVoters != null && numberVoters.size() != 0) {
                                                            	Iterator  numberContrVoters = numberVoters.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (numberContrVoters.hasNext()) {
                                                                    String strType = (String) numberContrVoters.next();
                                                                    String ContrVoters = strType;
                                                                 
                                                                    if (ContrVoters.equals("")) {

                                                %>
                                                <option value="<%=ContrVoters%>" selected="selected" ><%=ContrVoters%></option>
                                                <%
                                                 } else {
                                                %>
                                                <option value="<%=ContrVoters%>" ><%=ContrVoters%></option>
                                                <%
                                                                    }
                                                                }
                                                            }
                                                %>


                                               
                                            </select>&nbsp;<span class="asterisk">*</span></span></div>
									   <div class="row">
										<span class="label">Votes Required By</span>
									   <span class="formX">
									   <select name="selVotes" class="selectboxOne" id="selVotes"><option selected="selected" value="">Select One</option>
                                                 <%
                                                       
                                                            Map<String,String> votesReq = (HashMap) session.getAttribute("votesReq");
                                                            if (votesReq != null && votesReq.size() != 0) {
                                                            	 Iterator iter = votesReq.entrySet().iterator();
                                                                while (iter.hasNext()) {
                                                                	Map.Entry me = (Map.Entry) iter.next();
                                                                    String Voters =(String)me.getValue() ;
                                                                    String votersId=(String)me.getKey();
                                                                    if (Voters.equals("")) {

                                                %>
                                                <option value="<%=votersId%>" selected="selected" ><%=Voters%></option>
                                                <%
                                                 } else {
                                                %>
                                                <option value="<%=votersId%>" ><%=Voters%></option>
                                                <%
                                                                    }
                                                                }
                                                            }
                                                %>



                                                
                                            </select>&nbsp;<span class="asterisk">*</span></span></div>
									  <div class="row">
										<span class="label">Output</span>
									  <span class="formX"><input type="text"name="txtOutput" class="textboxOne" size="10" id="txtOutput"/>
									  &nbsp; <span class="asterisk">*</span></span></div>
									
									
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->


								<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="submit" value="ADD/SAVE STAGE" class="gradBtn" name="method" /></span>								</div>

									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->
									<s:text name="Please fill in the form below:" />


						<!-- **************************************** Part A of the form Ends here *********************************************** -->
            </div>
            </form>
</td>
</tr>
</table> 


 <table  border="1" cellpadding="0" cellspacing="0" width="100%" align="left" style="table-layout:fixed" > 

 <td colspan="11" class="tblMainHead" >
	Displaying results 1-5 </td><tr align="center">
	<th class="tblMainHead1" >Lifecycle</th>
    <th class="tblMainHead1" >Total Stages</th>
    <th class="tblMainHead1">Date of Creation</th>
	 <th class="tblMainHead1">Created By</th>
	  <th class="tblMainHead1">Stage</th>
	  <th class="tblMainHead1">Stage Name</th>
	  <th class="tblMainHead1">Input</th>
	  <th class="tblMainHead1">Process</th>
	  <th class="tblMainHead1">Control</th>
	  <th class="tblMainHead1">Output</th>
	  <th class="tblMainHead1">Description</th>
  </tr>
  <form name="frmlifecycle" id="frmlifecycle" method="post" action="SysMgmt.do?process=systinetLifecycle" class="formcss" onSubmit="">
     <%
     
    
     
                                                 ArrayList artifactName=(ArrayList)session.getAttribute("artifactData");
     
     if (artifactName != null && artifactName.size() != 0) { 
    	 Iterator evotesartifactName = artifactName.iterator();
    	 //String [] userType = {ID, name };
    	 while (evotesartifactName.hasNext()) {
             String strType[] = (String[]) evotesartifactName.next();
         	
			String lifecycleProName=strType[0];
    	String numofStage=strType[1];
    	String dateofCreation=strType[2];
    	String createdBy=strType[3];
    	String stage=strType[4];
    	String stageName=strType[5];
    	String input=strType[6];
    	String process=strType[7];
    	String voter=strType[8];
    	String output=strType[9];
    	String descripition=strType[10];
        String addArtifacttype=strType[11]; 
        String votes=strType[12]; 		
       

%>
     
   <tr><td align="left"><%=lifecycleProName%></td><td align="left"><%=numofStage%></td><td align="left"><%=dateofCreation%></td><td align="left"><%=fullName%></td><td align="left"> <%=stage%></td><td align="left"> <%=stageName%></td><td align="left"><%=input%> </td><td align="left"> <%=process%></td><td align="left"> <%=voter%></td><td align="left"><%=output%> </td><td align="left" style="word-wrap: break-word;"> <%=descripition%></td></tr>
  <%
                                                 
                                                                                                                                                                       }
   }
   else{
	   %>
	   <tr><td colspan="11">No DATA </td></tr>
                                                <%
   }
                                               
                                                
                                                 if (artifactName != null && artifactName.size() != 0) { %> <tr><td colspan="11" align="center">    <input type="submit" value="SUBMIT" class="gradBtn" align="right"/> </td></tr>    <%
   } %>
   
   </form>
</table></td>
  </tr>
  
				<!-- CONTENTS START HERE -->
</table>
</td>
</tr>
</table>
			<!-- CONTENTS END HERE -->			</td>
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
<script language="javascript">
    var cal1 = new calendar2(document.forms['frmlifecycleproc'].elements['frmDate']);
     cal1.year_scroll = true;
     cal1.time_comp = false;

   
</script>
</body>
</html>
