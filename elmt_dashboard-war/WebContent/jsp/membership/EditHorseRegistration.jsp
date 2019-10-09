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
<%@ page import="java.util.*" %>
<%@ page import="com.breed.DAO.*"%>
<%@ page import="com.color.DAO.*"%>
<%@ page import="com.hlcmrm.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlccommon.util.HLCHorseRegisterationVO"%>
<%@ page import="com.hlchorse.form.util.*" %>
<%@ page import="java.text.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=(String)session.getAttribute("title")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <script src="javascripts/basic.js" type="text/javascript" ></script>
    <script src="javascripts/EditHorseRegValidate.js" type="text/javascript" ></script>
    <script src="javascripts/frmHorseRegisterUpdate.js" type="text/javascript" ></script>
    <script src="javascripts/calendar2.js" type="text/javascript"></script>
    <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
    <script language="javascript" />
function addiRider(){
	 
		var domR = document.getElementById;
		var fomR = document.myform;
		if(fomR.arhlcNo.value != ""){
			domR('addRider').style.display = "block";
		}else{
			domR('addRider').style.display = "none";
		}
} 

    </script>
    
</head>
 <%! 
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String  nullCheck(String fieldName){
        String retValue = "NG";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    %>
    <%! String dateCheck(Date fieldName){
        String detValue = "NG";
        if(fieldName!=null){
            detValue = sdf.format(fieldName);
        }
        return detValue;
    }
    %>
    <%! float floatCheck(float fieldName){
        float floateValue = 0;
        if(fieldName!=0.0){
            floateValue = fieldName;
        }
        return floateValue;
    }
    %>
    <%! 
    DecimalFormat result  = new DecimalFormat("0.00");
    String amtFormat(String amtVal){
        String amountValue = "0.00";
        if(amtVal!=null && amtVal.trim().length()!=0){
            System.out.print(Double.parseDouble(amtVal));
            amountValue = result.format(Double.parseDouble(amtVal));
        }
        return amountValue;
    }
    %>
	<%! 
   
    String  emptyCheck(String fieldName){
        String retValue = "";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    %>

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
<td width="260" class="menuTablePad">
    <!-- LEFT MENU STARTS HERE -->
    <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
<!-- LEFT MENU ENDS HERE -->		    </td>
<td width="500" class="subDeptTablePad">
<!-- CONTENTS START HERE -->
<div class="cmmnForm">
    <div class="colspan">
    <strong>Membership:</strong> <span class="styleBoldTwo">Horse Registration Details </span>						</div>
    <div id="commonBG" class="textCommon"> You	are	viewing	the	details	of the horse registered by you. </div>
    
    <form name="myform" id="myform" method="post" action="horseupdate.do" onsubmit="return myValidate();" >
        <input type="hidden" name="process" value="horseUpdate">
<input type="hidden" value="<bean:message key='FEH.minage'/>" name="FEHMin"/>
<input type="hidden" value="<bean:message key='FEH.maxage'/>" name="FEHMax"/>
<input type="hidden" value="<bean:message key='YEH.minage'/>" name="YEHMin"/>
<input type="hidden" value="<bean:message key='YEH.maxage'/>" name="YEHMax"/>
        <div class="rowExpand"> Horse Registration Details:</div>
        <%
        
 
        HLCHorseRegisterationVO HorseDisp =(HLCHorseRegisterationVO) request.getAttribute("HorseDet");
        if(HorseDisp==null)	out.print("HorseDisp is null");
        System.out.print(HorseDisp.getPrevRiderMemberId());
        String horseMemberId = (String) HorseDisp.getHorseMemberId();
        String competitionName = (String) HorseDisp.getCompetitionName();
        String registeredName = (String) HorseDisp.getRegisteredName();
        String baRegisteredName = (String)HorseDisp.getBaRegisteredName();
        String baPastName = (String)HorseDisp.getBaPastName();
        String riderMemberId = (String)HorseDisp.getRiderMemberId();
        String prevRiderMemberId = (String)HorseDisp.getPrevRiderMemberId();
        String addRiderMemberId = (String)HorseDisp.getAddRiderMemberId();
        String ownerId = (String)HorseDisp.getOwnerId();
		String trainerId = (String) HorseDisp.getTrainerId();
        String prevOwnerId = (String)HorseDisp.getPrevOwnerId();
        String addOwnerId = (String)HorseDisp.getAddOwnerId();
        String prevOwnerName = (String)HorseDisp.getPrevOwnerName();
        String addOwnerName = (String)HorseDisp.getAddOwnerName();
        String paymentId = (String)HorseDisp.getPaymentId();
        String requestStatus = (String)HorseDisp.getRequestStatus();
        String firstName = (String)HorseDisp.getFirstName();
        String lastName = (String)HorseDisp.getLastName();
        String comments = (String)HorseDisp.getComments();
        String color = (String)HorseDisp.getColor();
        String gender = (String)HorseDisp.getGender();
        String height = (String)HorseDisp.getHeight();
        String yearFoaled = (String)HorseDisp.getYearFoaled();
        String country1 = (String)HorseDisp.getCountry();
        String sire = (String)HorseDisp.getSire();
        String sireBreed = (String)HorseDisp.getSireBreed();
        String dam = (String)HorseDisp.getDam();
        String damBreed = (String)HorseDisp.getDamBreed();
        
        String sireBreed2 = (String)HorseDisp.getSireBreed2();
        String damBreed2 = (String)HorseDisp.getDamBreed2();
        String importedFrom = (String)HorseDisp.getImportedFrom();
        Date importDate = HorseDisp.getImportDate();
		String imp_dte ="";
         if(importDate!=null){
            imp_dte = sdf.format(importDate);
        } else{
            imp_dte = "";
        }
        String foreignPoints ="";
        if(HorseDisp.getForeignPoints()!=0.0){
            foreignPoints = String.valueOf(HorseDisp.getForeignPoints());
        } else{
            foreignPoints = "";
        }
        String assignedGrade = (String)HorseDisp.getAssignedGrade();
        float assignedPoints = (float)HorseDisp.getAssignedPoints();
        String notes = (String)HorseDisp.getNotes();
        String splNotes = (String)HorseDisp.getSplNotes();
        String checkNumber = (String)HorseDisp.getCheckNumber();
        String paymentStatus = (String)HorseDisp.getPaymentStatus();
        String checkName = (String)HorseDisp.getCheckName();
        String sslTxnId = (String)HorseDisp.getSslTxnId();
        String bankName = (String)HorseDisp.getBankName();
        String amount = (String)HorseDisp.getAmount();
        String regstrBy = (String)HorseDisp.getRegisteredBy();
        String membertype= (String)HorseDisp.getMemberTypeName();
        Date actDate = HorseDisp.getActivationDate();
        Date upgraDate = HorseDisp.getUpgradationDate();
        String statusName = (String)HorseDisp.getStatusName();
        String checkDate = "";
        if(HorseDisp.getCheckDate()!=null){
            checkDate = sdf.format(HorseDisp.getCheckDate());
        } else{
            checkDate = "";
        }
        
        
        %>
        
        <div class="row">
            <span class="label">Horse Registration Status:</span>
            <span class="formX"><%=nullCheck(statusName)%></span></span>
        </div>
        
		<div class="row">
			<span class="label">Horse Registration Type:</span>
			<span class="formX"><span class="styleBoldOne"><%=nullCheck(membertype)%></span></span>
			
		</div>
        <div class="row">
            <span class="label">Activation Date :</span>
            <span class="formX"><span class="styleBoldOne"><%=dateCheck(actDate)%></span></span>
        </div>
        <div class="row">
            <span class="label">Upgradation Date:</span>
            <span class="formX"><span class="styleBoldOne"><%=dateCheck(upgraDate)%></span></span>
        </div>
        <div class="rowHead">Horse Information Section:</div>
        <div class="row">
            <span class="label">Horse Name: </span>
            <span class="formX"><strong><%=nullCheck(competitionName)%></strong></span>
        </div>
        <div class="row">
            <span class="label">Registered By:</span>
            <span class="formX"><span class="styleBoldOne"><%=nullCheck(regstrBy)%></span></span>
        </div>								
        <div class="row">
            <span class="label">Registered Name:</span>
            <span class="formX"><%=nullCheck(registeredName)%></span>	
        </div>
        <div class="row">
            <span class="label">Past Name:</span>
            <span class="formX"><%=nullCheck(baPastName)%></span>	
        </div>
        <div class="row">
            <span class="label">Breed Assoc. Horse is registered with:</span>
            <span class="formX"><%=nullCheck(HorseDisp.getBaRegisteredName())%></span>	
        </div>
        <%
        ArrayList riderInfoDetails = (ArrayList) request.getAttribute("riderInfoDetails");
        String rid_prefix1="";
        String rid_first_name = "";
        String rid_middle_name = "";
        String rid_last_name = "";
        String rid_sufix = "";
        String rid_email_id = "";
        String rid_suite = "";
        String rid_address1 = "";
        String rid_address2 = "";
        String rid_city = "";
        String rid_state = "";
        String rid_country = "";
        String rid_zip = "";
        String rid_phone_no = "";
        String rid_mobile_no = "";
        String rid_fax_no = "";
        if(riderInfoDetails !=null && riderInfoDetails.size()!=0){
            Iterator it = riderInfoDetails.iterator();
            while(it.hasNext()){
                rid_prefix1 = (String)it.next();
                rid_first_name  = (String)it.next();
                rid_middle_name  = (String)it.next();
                rid_last_name = (String)it.next();
                rid_sufix =  (String)it.next();
                rid_email_id  = (String)it.next();
                rid_suite =  (String)it.next();
                rid_address1 =  (String)it.next();
                rid_address2 = (String)it.next();
                rid_city = (String)it.next();
                rid_state =  (String)it.next();
                rid_country = (String)it.next();
                rid_zip = (String)it.next();
                rid_phone_no = (String)it.next();
                rid_mobile_no = (String)it.next();
                rid_fax_no = (String)it.next();
               
            }
        }						
        %>
        <div class="rowHead">
            <strong>Rider Information Section:</strong>	
        </div>
        <div class="row">
            <span class="label">No.:</span>
            <span class="formX">
                <input type="text" class="readOnly" readonly="true" name="hlcNo" id="riderUseaNo_id"  value="<%=nullCheck(riderMemberId)%>" size="20" />
            </span>
        </div>
        <div class="row">
            <span class="label">Salutation:</span>
            <span class="formX">
                <input type="text" name="rprefix" readonly="true" class="readOnly" size="10" value="<%=nullCheck(rid_prefix1)%>" />
        </span>			</div>
        <div class="row">
            <span class="label">First Name:</span>
            <span class="formX"><input type="text" class="readOnly" readonly="true"  name="rName" id="riderFname_id" size="20" value="<%=nullCheck(rid_first_name)%>" /></span>
        </div>
        <div class="row">
            <span class="label">Last Name:</span>
            <span class="formX"><input type="text" class="readOnly" readonly="true"  name="lName" id="riderLname_id" size="20" value="<%=nullCheck(rid_last_name)%>"/></span>
        </div>
        <div class="row">
            <span class="label">Street:</span>
            <span class="formX"><input type="text"  class="readOnly"  readonly="true" name="rStreet" id="rStreet" size="20" value="<%=nullCheck(rid_address1)%>" /></span>
        </div>
        <div class="row">
            <span class="label">City:</span>
            <span class="formX"><input type="text" class="readOnly" readonly="true" name="rCity" id="rCity" size="15" value="<%=nullCheck(rid_city)%>" /></span>
        </div>
        <div class="row">
            <span class="label">State:</span>
            <span class="formX"><input type="text" class="readOnly" readonly="true" name="rState" id="rState" size="20" value="<%=nullCheck(rid_state)%>" /></span>
        </div>
        <div class="row">
            <span class="label">Zipcode:</span>
            <span class="formX"><input type="text" class="readOnly" readonly="true" name="rZipcode" id="rZipcode" size="8"  value="<%=nullCheck(rid_zip)%>"/></span>
        </div>
        <div class="row">
            <span class="label">Phone:</span>
            <span class="formX"><input type="text" class="readOnly" readonly="true" name="riderPhone_id" id="riderPhone_id" size="15" value="<%=nullCheck(rid_phone_no)%>" /></span>	
        </div>
        <div class="row">
            <span class="label">Cell:</span>
            <span class="formX"><input type="text" class="readOnly" readonly="true" name="rCell"  id="rCell" size="15"  value="<%=nullCheck(rid_mobile_no) %>"/></span>
        </div>
        <div class="row">
            <span class="label">Email:</span>
            <span class="formX">
			<input type="text" class="readOnly" readonly="true" name="rEmail" id="rEmail" size="20" value="<%=nullCheck(rid_email_id)%>" />
			</span>		
        </div>
        
        
        <%
        //Owner Details
        ArrayList listContact = (ArrayList) request.getAttribute("ownerDetails");
        String prefix1="";
        String first_name = "";
        String middle_name = "";
        String last_name = "";
        String sufix = "";
        String email_id = "";
        String suite = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String country = "";
        String zip = "";
        String phone_no = "";
        String mobile_no = "";
        String fax_no = "";
        if(listContact !=null && listContact.size()!=0){
            Iterator it = listContact.iterator();
            while(it.hasNext()){
                prefix1 = (String)it.next();
                first_name  = (String)it.next();
                middle_name  = (String)it.next();
                last_name = (String)it.next();
                sufix =  (String)it.next();
                email_id  = (String)it.next();
                suite =  (String)it.next();
                address1 =  (String)it.next();
                address2 = (String)it.next();
                city = (String)it.next();
                state =  (String)it.next();
                country = (String)it.next();
                zip = (String)it.next();
                phone_no = (String)it.next();
                mobile_no = (String)it.next();
                fax_no = (String)it.next();
            }
        }
        %>			
		<div id="partb" class="formPart">   
            <div class="rowHead">
            Owner Information :				 </div>	  		
            
            <div class="row">
                <span class="label">Name:</span>
                <span class="formX"><%=first_name+" "+last_name%></span>
            </div>
            <div class="row">
                <span class="label">Address 1:</span>
                <span class="formX"><%=nullCheck(address1)%></span>	
            </div>
            <div class="row">
                <span class="label">Address 2:</span>
                <span class="formX"><%=nullCheck(address2)%></span>	
            </div>
            <div class="row">
                <span class="label">City:</span>
                <span class="formX"><%=nullCheck(city)%></span>
            </div>
            <div class="row">
                <span class="label">State:</span>
                <span class="formX"><%=nullCheck(state)%></span>
            </div>
            
            <div class="row">
                <span class="label">Zipcode:</span>
                <span class="formX"><%=nullCheck(zip)%></span>	
            </div>
			<div class="row">
                <span class="label">Country:</span>
                <span class="formX"><%=nullCheck(country)%></span>	
            </div>
            <div class="row">
                <span class="label">Phone Number:</span>
                <span class="formX"><%=nullCheck(phone_no)%></span>	
            </div>
            <div class="row">
                <span class="label">Mobile Number:</span>
                <span class="formX"><%=nullCheck(mobile_no)%></span>	
            </div>
            <div class="row">
                <span class="label">Email:</span>
                <span class="formX"><%=nullCheck(email_id)%></span>
            </div>
            <div class="row">
                <span class="label">Fax</span>
                <span class="formX"><%=nullCheck(fax_no)%></span>
            </div>
        </div>
        <%
        //Owner Details
        ArrayList TrinrContact = (ArrayList) request.getAttribute("TrainerDetails");
        String prefix12="";
        String first_name1 = "";
        String middle_name1 = "";
        String last_name1 = "";
        String sufix1 = "";
        String email_id1 = "";
        String suite1 = "";
        String address11 = "";
        String address22 = "";
        String city1 = "";
        String state1 = "";
        String country11 = "";
        String zip1 = "";
        String phone_no1 = "";
        String mobile_no1 = "";
        String fax_no1 = "";
        if(TrinrContact !=null && TrinrContact.size()!=0){
            Iterator it = TrinrContact.iterator();
            while(it.hasNext()){
                prefix12 = (String)it.next();
                first_name1  = (String)it.next();
                middle_name1  = (String)it.next();
                last_name1 = (String)it.next();
                sufix1 =  (String)it.next();
                email_id1  = (String)it.next();
                suite1 =  (String)it.next();
                address11 =  (String)it.next();
                address22 = (String)it.next();
                city1 = (String)it.next();
                state1 =  (String)it.next();
                country11 = (String)it.next();
                zip1 = (String)it.next();
                phone_no1 = (String)it.next();
                mobile_no1 = (String)it.next();
                fax_no1 = (String)it.next();
            }
        }
        %>		          
	<div id="partb" class="formPart">
	<div class="rowHead">
           <span>Trainer Information: <span>
<!--			<%// if(trainerId!=null && trainerId.trim().length()!=0){ %>
            <input type="button" name="click2" value="Edit" class="twoBtn" style="width:50px;" onclick="location.href='editHorse.do?process=initPage&avail=avail&trainerId=<%=trainerId%>&horseId=<%=horseMemberId%>';"/></span></span>
			<%// }else{
			%>
            <input type="button" name="click2" value="Add" class="twoBtn" style="width:50px;" onclick="location.href='editHorse.do?process=initPage&avail=notavail&trainerId=<%=trainerId%>&horseId=<%=horseMemberId%>';"/></span></span>			
			<%//}%>
-->			</div>
			<% if(TrinrContact !=null && TrinrContact.size()!=0){
			%>
            <div class="row">
                <span class="label">Name:</span>
                <span class="formX"><%=first_name1+" "+last_name1%></span>
            </div>
            <div class="row">
                <span class="label">Address 1:</span>
                <span class="formX"><%=nullCheck(address11)%></span>	
            </div>
            <div class="row">
                <span class="label">Address 2:</span>
                <span class="formX"><%=nullCheck(address22)%></span>	
            </div>
            <div class="row">
                <span class="label">City:</span>
                <span class="formX"><%=nullCheck(city1)%></span>
            </div>
            <div class="row">
                <span class="label">State:</span>
                <span class="formX"><%=nullCheck(state1)%></span>
            </div>
          
            <div class="row">
                <span class="label">Zipcode:</span>
                <span class="formX"><%=nullCheck(zip1)%></span>	
            </div>
			  <div class="row">
                <span class="label">Country:</span>
                <span class="formX"><%=nullCheck(country11)%></span>	
            </div>
            <div class="row">
                <span class="label">Phone Number:</span>
                <span class="formX"><%=nullCheck(phone_no1)%></span>	
            </div>
            <div class="row">
                <span class="label">Mobile Number:</span>
                <span class="formX"><%=nullCheck(mobile_no1)%></span>	
            </div>
            <div class="row">
                <span class="label">Email:</span>
                <span class="formX"><%=nullCheck(email_id1)%></span>
            </div>
            <div class="row">
                <span class="label">Fax</span>
                <span class="formX"><%=nullCheck(fax_no1)%></span>
            </div>
        </div>				
	<% } %>		
		
        <div class="rowHead"> Horse Description:</div>
        
        <div class="row">
            <span class="label">Color:</span>
            <span class="formX">
                <select name="colorselect" id="colorSelectId" class="selectboxOne">
                    <option selected="selected" value="">Select One</option>
                    <%
                    String colorExistId = HorseDisp.getColorId();
                    ArrayList lst = (ArrayList) request.getAttribute("DisplayColorDetails");
                    if(lst.size()!=0){
                        Iterator itr = lst.iterator();
                        while(itr.hasNext()){
                            HLCHorseColorVO objColorVO = (HLCHorseColorVO)itr.next();
                            String colorId = objColorVO.getColorId();
                            String colorCode = objColorVO.getColorCode();
                            String colorDesc = objColorVO.getColorDesc();
                            if(colorId.equals(colorExistId)){
                    %>
                    <option value="<%=colorId%>" selected="selected"><%=colorDesc%></option>
                    <%}else{
                    %>
                    <option value="<%=colorId%>"> <%=colorDesc%></option>
                    <%
                    }
                            
                        }
                    }
                    %>   
                </select>
                
        <span class="asterisk">*</span>				</span> </div>
        
        <div class="row">
            <span class="label">Sex:</span>
            <span class="formX">
                <select name="genderselect" id="genderSelectId" class="selectboxOne">
                    
                    <%if(gender.equals("Gelding")){
                    %>
                    <option selected="selected" value="Gelding"><%=gender%></option>
                    <%
                    } else{
                    %>
                    <option value="Gelding">Gelding</option>
                    <%}%>
                    
                    <%if(gender.equals("Stallion")){%>
                    <option selected="selected" value="Stallion"><%=gender%></option>
                    <%
                    } else{
                    %>
                    <option value="Stallion">Stallion</option>
                    <%}%>
                    

                    <%if(gender.equals("Mare")){%>
                    <option selected="selected" value="Mare"><%=gender%></option>
                    <%
                    } else{
                    %>
                    <option value="Mare">Mare</option>
                    <%}%>
                    <%if(gender.equals("")){%>
                    <option selected="selected" value="">Select One</option>
                    <%
                    } else{
                    %>
                    <option value="">Select One</option>
                    <%}%>
                </select>
                <span class="asterisk">*</span>	
            </span>			
        </div>
        
        
        <div class="row">
            <span class="label">Height</span>
        <span class="formX"><input type="text" class="textboxOne" value="<%=emptyCheck(HorseDisp.getHeight())%>"  name="height" id="height_id" size="10" /></span>			</div>
        <div class="row">
            <span class="label">Year foaled:</span>
            <span class="formX"><input type="text" class="textboxOne" value="<%=emptyCheck(yearFoaled)%>"  name="foaled" id="foaled_id" size="10" />
        </span>			</div>
        <div class="row">
            <span class="label">Breed:</span>
            <span class="formX">
                <select name="breed" id="breed" class="selectboxOne">
                    <option selected="selected" value="">Select One</option>
                    <%
                    String existingBreed = HorseDisp.getBreedId();
                    ArrayList breed = (ArrayList) request.getAttribute("DisplayBreedDetails");
                    if(breed.size()!=0){
                        Iterator itr = breed.iterator();
                        while(itr.hasNext()){
                            HLCBreedVO objBreedVO = (HLCBreedVO) itr.next();
                            String breedId = objBreedVO.getBreedId();
                            String breedCode = objBreedVO.getBreedCode();
                            String breedDesc =  objBreedVO.getBreedDesc();
                            if(breedId.equals(existingBreed)){
                    %>
                    <option value="<%=breedId%>" selected="selected"><%=breedDesc%></option>
                    <%
                            } else{
                    %>
                    <option value="<%=breedId%>"> <%=breedDesc%></option>
                    <%
                            }
                        }
                    }
                    %>   
        </select></span>			</div>
        <div class="row">
            <span class="label">Breed Two:</span>
            <span class="formX">
                <select name="breedTwo" id="breedTwo" class="selectboxOne">
                    <option selected="selected" value="">None</option>
                    <%
                    String existingBreed2 = HorseDisp.getBreed2Id();
                    ArrayList breed1 = (ArrayList) request.getAttribute("DisplayBreedDetails");
                    if(breed1.size()!=0){
                        Iterator itr1 = breed1.iterator();
                        while(itr1.hasNext()){
                            HLCBreedVO objBreedVO1 = (HLCBreedVO) itr1.next();
                            String breedId1 = objBreedVO1.getBreedId();
                            String breedCode1 = objBreedVO1.getBreedCode();
                            String breedDesc1 =  objBreedVO1.getBreedDesc();
                            if(breedId1.equals(existingBreed2)){
                    %>
                    <option value="<%=breedId1%>" selected="selected"><%=breedDesc1%></option>
                    <%
                            } else{
                    %>
                    <option value="<%=breedId1%>"> <%=breedDesc1%></option>
                    <%
                            }
                        }
                    }
                    %>
                </select>
        </span>			</div>
        
        <div class="row">
            <span class="label">Country of origin:</span>
        <span class="formX"><input type="text" class="textboxOne" value="<%=emptyCheck(HorseDisp.getCountry())%>" name="countryOrigin" id="countryOrigin" size="15" /> (If not USA)</span>			</div>
        
        <div class="row">
            <span class="label">Sire Name:</span>
        <span class="formX"><input type="text" class="textboxOne" name="sireName" id="sireName" value="<%=emptyCheck(HorseDisp.getSire())%>" size="15" /></span>			</div>
        <div class="row">
            <span class="label">Sire Breed:</span>
            <span class="formX">
                <select name="sireBreed" id="sireBreed" class="selectboxOne">
                    <option selected="selected" value="">Select One</option>
                    <%
                    String existingBreed3 = HorseDisp.getSireBreedId();
                    ArrayList breed3 = (ArrayList) request.getAttribute("DisplayBreedDetails");
                    if(breed3.size()!=0){
                        Iterator itr3 = breed3.iterator();
                        while(itr3.hasNext()){
                            HLCBreedVO objBreedVO3 = (HLCBreedVO) itr3.next();
                            String breedId3 = objBreedVO3.getBreedId();
                            String breedCode3 = objBreedVO3.getBreedCode();
                            String breedDesc3 =  objBreedVO3.getBreedDesc();
                            if(breedId3.equals(existingBreed3)){
                    %>
                    <option value="<%=breedId3%>" selected="selected"><%=breedDesc3%></option>
                    <%
                            } else{
                    %>
                    <option value="<%=breedId3%>"> <%=breedDesc3%></option>
                    <%
                            }
                        }
                    }
                    %>
        </select></span>			</div>
        <div class="row">
            <span class="label">Sire Breed Two:</span><span class="formX">
                <select name="sireBreedTwo" id="sireBreedTwo" class="selectboxOne">
                    <option selected="selected" value="">None</option>
                    <%
                    String existingBreed4 = HorseDisp.getSireBreed2Id();
                    System.out.print("existingBreed4"+ existingBreed4);
                    ArrayList breed2 = (ArrayList) request.getAttribute("DisplayBreedDetails");
                    if(breed2.size()!=0){
                        Iterator itr2 = breed2.iterator();
                        while(itr2.hasNext()){
                            HLCBreedVO objBreedVO2 = (HLCBreedVO) itr2.next();
                            String breedId2 = objBreedVO2.getBreedId();
                            String breedCode2 = objBreedVO2.getBreedCode();
                            String breedDesc2 =  objBreedVO2.getBreedDesc();
                            if(breedId2.equals(existingBreed4)){
                    %>
                    <option value="<%=breedId2%>" selected="selected"><%=breedDesc2%></option>
                    <%
                            } else{
                    %>
                    <option value="<%=breedId2%>"> <%=breedDesc2%></option>
                    <%

                            }
                        }
                    }
                    %>
                </select>
        </span></div>
        
        <div class="row">
            <span class="label">Dam Name:</span>
        <span class="formX"><input type="text" class="textboxOne" name="damName" value="<%=emptyCheck(HorseDisp.getDam())%>" id="damName" size="15" /></span>			</div>
        <div class="row">
            <span class="label">Dam Breed:</span>
            <span class="formX">
                <select name="damBreed" id="damBreed" class="selectboxOne">
                    <option selected="selected" value="">Select One</option>
                    <%
                    String exisDamBreed = HorseDisp.getDamBreedId();
                    ArrayList breed4 = (ArrayList) request.getAttribute("DisplayBreedDetails");
                    if(breed4.size()!=0){
                        Iterator itr4 = breed4.iterator();
                        while(itr4.hasNext()){
                            HLCBreedVO objBreedVO4 = (HLCBreedVO) itr4.next();
                            String breedId4 = objBreedVO4.getBreedId();
                            
                            String breedCode4 = objBreedVO4.getBreedCode();
                            String breedDesc4 =  objBreedVO4.getBreedDesc();
                            if(breedId4.equals(exisDamBreed)){
                    %>
                    <option value="<%=breedId4%>" selected="selected"><%=breedDesc4%></option>
                    <%
                            } else{
                    %>
                    <option value="<%=breedId4%>"> <%=breedDesc4%></option>
                    <%
                            }
                        }
                    }
                    %>
        </select></span>			</div>
        <div class="row">
            <span class="label">Dam Breed Two:</span>
            <span class="formX">
                <select name="damBreedTwo" id="damBreedTwo" class="selectboxOne">
                    <option selected="selected" value="">None</option>
                    <%
                    String exisDamBreed1 = HorseDisp.getDamBreed2Id();
                    ArrayList breed5 = (ArrayList) request.getAttribute("DisplayBreedDetails");
                    if(breed5.size()!=0){
                        Iterator itr5 = breed5.iterator();
                        while(itr5.hasNext()){
                            HLCBreedVO objBreedVO5 = (HLCBreedVO) itr5.next();
                            String breedId5 = objBreedVO5.getBreedId();
                            String breedCode5 = objBreedVO5.getBreedCode();
                            
                            String breedDesc5 =  objBreedVO5.getBreedDesc();
                            if(breedId5.equals(exisDamBreed1)){
                    %>
                    <option value="<%=breedId5%>" selected="selected"><%=breedDesc5%></option>
                    <%
                            } else{
                    %>
                    <option value="<%=breedId5%>"> <%=breedDesc5%></option>
                    <%
                            }
                        }
                    }
                    %>
        </select></span>			</div>
        
        <div class="row">
            <span class="label">Imported From:</span>
            <span class="formX"><input type="text" class="textboxOne" value="<%=emptyCheck(HorseDisp.getImportedFrom())%>"  name="importedFrm" id="impFrm_id" size="25" />
        </span>			</div>
        
        
        <div class="row">
            <span class="label">Date Imported:</span>
            <span class="formX"><input type="text"  value="<%=imp_dte%>" name="importedDate" id="impDate_id" size="20" class="readOnly" readonly />
                                                                                                                                              <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> 	</span>			</div>
        <div class="row">
            <span class="label">Foreign Grade:</span>
            <span class="formX">
                <input type="text"  value="<%=emptyCheck(HorseDisp.getForeignGrade())%>"  name="foreignGrade" id="foreignGrd_id" size="15" class="readOnly" readonly="true"/>
        </span>			</div>
        <div class="row">
            
            <span class="label">Foreign Points:</span>
        <span class="formX"><input type="text"  value="<%=foreignPoints%>" name="foreignPoints" class="readOnly" readonly="true" id="foreignPnt_id" size="10" /></span>			</div>
        <input type="hidden" value="<%=horseMemberId%>" name="horseMemberId"/>
        <input type="hidden" value="<%=competitionName%>" name="competitionName">
        <input type="hidden" value="<%=membertype%>" name="membertype">
        
        
        
        
        <div class="row"><div class="alignCenter">
                <input type="submit" value="Update" class="gradBtn" />&nbsp;
                <input type="button" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);" />
            </div> 	
            
            <div id="spacer">&nbsp;</div>
        </div>
    </form>
</div>
<!-- CONTENTS END HERE -->		 </td>
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['myform'].elements['impDate_id']);
	 cal1.year_scroll = true;
	 cal1.time_comp = false;
</script>
</html>
