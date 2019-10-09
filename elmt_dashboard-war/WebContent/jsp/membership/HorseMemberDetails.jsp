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
<%@ page import="java.text.*" %>

<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlcform.util.DBHelper" %>
<%@ page import="com.hlchorse.form.util.HLCUserRegVO" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=(String)session.getAttribute("title")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <script src="javascripts/basic.js" type="text/javascript" ></script>
     <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
    <script src="javascripts/calendar2.js" type="text/javascript"></script>
    <script language="javascript">
function myval()
{
	if(document.frmMembRegi.uStatusId.value==""){
		alert(" Select any Status ");
		document.frmMembRegi.uStatusId.focus();
		return false;
	}
}
function currentDate()
{	
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowMonth1=todayDate.getMonth();
	var nowYear=todayDate.getYear();
	var nowMonth=1+nowMonth1;
	if(nowDate<10){nowDate="0"+nowDate;}
	if(nowMonth<10){nowMonth="0"+nowMonth;}
	//document.myform.approvalDate.value = nowMonth+"/"+nowDate+"/"+nowYear;
}
    </script>
    
    <%! String  nullCheck(String fieldName){
        String retValue = "N/A";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    
    %>
    <%!SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
					String dateFormat(String fieldName){					
						Date clDate = null;
						Calendar cal = Calendar.getInstance();
						GregorianCalendar gc = new GregorianCalendar();
						try{
							clDate = sd.parse(fieldName);
						}catch(Exception e){
							System.out.println("Error While Parsing Date: "+e);
						}
						
						gc.setTime(clDate);
						cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						String dispDate = "N/A";
						if(clDate!=null ){
						dispDate = sd1.format(cal.getTime());
						}
						return dispDate;
					}
	%>
</head>
<body>
<!-- <body onload="famAddOnInit(), initLife();">-->
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

<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">

<tr>
<td width="230" class="menuTablePad">
    <!-- LEFT MENU STARTS HERE -->
    <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
    <!-- LEFT MENU ENDS HERE -->

</td>
<td width="500" class="subDeptTablePad">
<!-- CONTENTS START HERE -->
				
<div class="cmmnForm">
<div class="colspan"><strong>USEA Membership:</strong> <span class="styleBoldTwo">Membership Information</span> </div>
<div id="commonBG" class="textCommon" >You are viewing your Membership <span class="rowHead">Information.</span></div>
 
<form name="frmMembRegi" id="myform" method="post" class="formcss" action="./MembershipReg.do" onsubmit="return myval();">
<input type="hidden" name="process" value="update"/> 
 
<%   
HLCMemberDetails objMember = new HLCMemberDetails();
objMember=(com.hlcform.util.HLCMemberDetails)request.getAttribute("objMember");
String memTypsel=null;


ArrayList memPreference = (ArrayList)request.getAttribute("mailPreference");
ArrayList familyAddOnId = (ArrayList)request.getAttribute("familyAddOnId");
session.setAttribute("familyAddOnId",familyAddOnId);

Vector memberTypeVect3=new Vector();
memberTypeVect3=(Vector)request.getAttribute("userTypeVect3");
System.out.println("memberTypeVect3:"+memberTypeVect3);
System.out.println("memberTypeVect3: SIZE  "+memberTypeVect3.size());
String str1 [] = new String [15];
String str2[] = new String [15];
String str3[] = new String [15];
if (memberTypeVect3.elementAt(0) != null && memberTypeVect3.size() !=0) {
    str1   = (String[]) memberTypeVect3.elementAt(0);
}
if (memberTypeVect3.elementAt(1) != null && memberTypeVect3.size() !=0) {
    str2   = (String[]) memberTypeVect3.elementAt(1);
}
if (memberTypeVect3.elementAt(2) != null && memberTypeVect3.size() !=0) {
    str3   = (String[]) memberTypeVect3.elementAt(2);
}


String parentId="N/A";
if(str1[3]!=null){
    parentId =str1[3];
}

String joinDate = "N/A";																																	  if (str1[6] != null) {
    joinDate = str1[6];
    String jD[] = joinDate.split(" ");
    joinDate = jD[0];
}
String expDate = "N/A";
if (str1[7] != null){
    expDate = str1[7];
    String jD[] = expDate.split(" ");
    expDate = jD[0];
}
String dob = "N/A";
if (str2[6] != null) {
    dob = str2[6];
    String jD[] = dob.split(" ");
    dob = jD[0];																																			}
String gendar = "N/A";
if (str2[7] != null) {
    gendar = str2[7];
}

//contactTypeName, suite, add1, add2, city, state, country, zip
String add1 = "";
String add2 = "";
String city = "";
String state = "";
String country = "";
String zip = "";
String areaName = "N/A";
if(str3[2] != null) {
    add1=str3[2];
}
if (str3[3] != null) {
    add2 = str3[3];
}
if (str3[4] != null) {
    city = str3[4];
}
if (str3[5] != null){
    state = str3[5];
}
if (str3[6] != null) {
    country = str3[6];
}
if (str3[7] != null) {
    zip = str3[7];
}
if (str3[11] != null) {
    areaName = str3[11];
}


%>	
<div class="rowHead">
    USEA Member Type :
</div>				

<%
String mtyp="";

if(objMember.getMembershipTypeName()!=null && objMember.getMembershipTypeName().trim().length()!=0) {
    mtyp=objMember.getMembershipTypeName();
} else {
    mtyp="N/A";
}
%>

<div class="row">
    <span class="label">Membership Type:</span>
    <span class="formX">
        <%=mtyp%>
        
    </span>
</div>

<%
String tmpMemId="";
tmpMemId=objMember.getMemberId();

String status="";

if(objMember.getStatusName()!=null) {
    status=objMember.getStatusName();
} else {
    status="N/A";
}
%>

<div class="row">
    <span class="label">Membership Status :</span>
    <span class="formX">
        <span class="styleBoldOne"><%=status%></span>																		
    </span>
</div>	
<div class="row">
    <span class="label">Member ID:</span>		
    <span class="label">
        <%=objMember.getMemberId()%>																		
    </span>	
    
</div>

<%
if(!mtyp.equalsIgnoreCase("Life Member")) {%>

<div class="row">
    <span class="label">Expiration Date:</span>		
    <span class="label">
        <%=dateFormat(expDate)%>																		
    </span>
    
    <%}%>
    
</div>

<div class="row">
    <span class="label">Join Date:</span>		
    <span class="label">
        <%=dateFormat(joinDate) %>																		
    </span>		
    
</div>

<div class="row">
    <span class="label">Date of Birth:</span>		
    <span class="label">
        <%=dateFormat(dob)%>																		
    </span>		
    
</div>

<div class="row">
    <span class="label">Gender:</span>		
    <span class="label">
        <%=gendar%>																		
    </span>	
</div>	
<div class="row">
    <span class="label">Area:</span>		
    <span class="label">
        <%=areaName%>																		
    </span>	
</div>

<%
try{
    HLCUserRegVO userregvo=new HLCUserRegVO();
    
    userregvo=(HLCUserRegVO)request.getAttribute("userregvo");

%>

<div id="pAdd">

    
    <div class="rowHead">

        <strong>Primary Address </strong> 
    </div>
    <div class="row">
        <span class="label">Address 1:</span>
        <span class="formX"> <%=nullCheck(userregvo.getPrimaryAddress1())%></span>
    </div>
    
    
    
    <div class="row">
        <span class="label">Address 2:</span>
        <span class="formX"> <%=nullCheck(userregvo.getPrimaryAddress2())%></span>
    </div>
     <div class="row">
        <span class="label">City:</span>
        <span class="formX">
            <%=nullCheck(userregvo.getPrimaryCity())%>
        </span>
    </div>
	<div class="row">
        <span class="label">State:</span>
        <span class="formX">
            <%=nullCheck(userregvo.getPrimaryState())%>
        </span>
    </div>
	<div class="row">
        <span class="label">Zipcode:</span>
        <span class="formX"><%=nullCheck(userregvo.getPrmaryZip())%></span>
    </div>
    <div class="row">
        <span class="label">Country:</span>
        <span class="formX">
            <%=nullCheck(userregvo.getPrimaryCountry())%>
        </span>
    </div>
    <div class="row">
        <span class="label">Phone:</span>
        <span class="formX"><%=nullCheck(userregvo.getPrimaryPhoneNo())%></span>
    </div>
    <div class="row">
        <span class="label">Mobile:</span>
        <span class="formX"><%=nullCheck(userregvo.getPromaryMobileNo())%></span>
    </div>
    <div class="row">
        <span class="label">Fax:</span>
        <span class="formX"><%=nullCheck(userregvo.getPrimaryFaxNo())%></span>
    </div>
    
</div>


<%
if(userregvo.getSecondaryContactTypeName()!=null) {
        System.out.println("userregvo.getSecondaryContactTypeName() :"+userregvo.getSecondaryContactTypeName());
        if(userregvo.getSecondaryContactTypeName().equalsIgnoreCase("Secondary")) {
            System.out.println("userregvo.getSecondaryAddress1() :"+userregvo.getSecondaryAddress1());
%>
<div>
    
    <div class="rowHead">
        <strong>Secondary Address </strong> 
    </div>
    <div class="row">
    <span class="label">Address 1:</span>	 <%=nullCheck(userregvo.getSecondaryAddress1())%> <span class="formX"> </span>	  </div>
    <div class="row">
        <span class="label">Address 2:</span>
        <span class="formX"><%=nullCheck(userregvo.getSecondaryAddress2())%></span>
    </div>
     <div class="row">
        <span class="label">City:</span>
        
        <span class="formX"><%=nullCheck(userregvo.getSecondaryCity())%></span>
        
    </div>
	 <div class="row">
        <span class="label">State:</span>        
        <span class="formX"> <%=nullCheck(userregvo.getSecondaryState())%></span>    
    </div>
	<div class="row">
        <span class="label">Zipcode:</span>        
        <span class="formX"><%=nullCheck(userregvo.getSecondaryZip())%></span>        
    </div>
    <div class="row">
        <span class="label">Country:</span>      
        <span class="formX"><%=nullCheck(userregvo.getSecondaryCountry())%></span>       
    </div>
    <div class="row">
        <span class="label">Phone:</span>
        <span class="formX"><%=nullCheck(userregvo.getSecondaryPhoneNo())%></span>
    </div>
    <div class="row">
        <span class="label">Mobile:</span>
        <span class="formX"><%=nullCheck(userregvo.getSecondaryMobileNo())%></span>
    </div>
    <div class="row">
        <span class="label">Fax:</span>
        <span class="formX"><%=nullCheck(userregvo.getSecondaryFaxNo())%></span>
    </div>												
    
</div>
<%}}%>	                                      <%
String prefcom="";
System.out.println("getPreferedCommunication() :"+userregvo.getPreferedCommunication());

if(userregvo.getPreferedCommunication().equalsIgnoreCase("Primary")) {
    prefcom="Primary Address";
} else {
    prefcom="Secondary Address";
}
%>						
<div class="row">
    <span class="label">preferred Communication Address:</span>
    <span class="formX">
        <%=prefcom%>
    </span>
</div>
<%
} catch(Exception e) {
    System.out.print(" error in jsp :"+e);
}
%>

<div class="rowHead">
    <strong>Mailing Preferences:</strong>
</div>	
<% String mailPreference = "";
if(memPreference !=null && memPreference.size()!=0){
    Iterator it = memPreference.iterator();
    while(it.hasNext()){
        mailPreference = (String)it.next();
        if(mailPreference==null)
            mailPreference = "";  %>
<div class="row">
    <span class="label"></span>		
    <span class="label">
        <%=mailPreference%>					
    </span>		
    
</div>
<% } } else {%>
<div class="row">
    <span class="styleBoldOne">No Mailing Preferences details available for this Member!!</span>
</div>
<%}
%>


<div class="rowHead">
    <strong>Current Full / Life Member Info:</strong>
</div>		
<div class="row">
    <span class="label">Parent Member ID :</span>
    <%
    String parId=objMember.getParentMemberId();
    
    if(parId!=null && parId.trim().length()!=0) {
        parId=objMember.getParentMemberId();
    } else {
        parId="N/A";
    }
    %>     
    <span class="formX"><%=parentId%></span>
    
</div>





<%
int addon=0;
if(objMember.getFamilyAddOn()!=null) {
    List lst=(List)objMember.getFamilyAddOn();
    addon=lst.size();
} else {
    addon=0;
}
%>
<div class="row">
    <span class="label">Number Of Family members :</span>
    <span class="formX">
        <%=addon%>
        &nbsp; 
    </span>
</div>															  
<% 
String[] familyAddOnId1 = new String[2];
String famMid="";
String famUid="";

if(familyAddOnId !=null && familyAddOnId.size()!=0){
%>

<div class="row">
    <span class="label">Family members ID :</span>
    
</div>
<%
Iterator it = familyAddOnId.iterator();
while(it.hasNext()){
    familyAddOnId1 = (String[])it.next();
    famMid=familyAddOnId1[0];
    famUid=familyAddOnId1[1];
    System.out.print("famMid :"+famMid);
    System.out.print("famUid :"+famUid);

%>
<div class="row">
    <span class="label">Member Id:</span>	
    
    
    <span class="label"><a href="MembershipReg.do?process=view&familyAddOnId1=<%=famMid%>&userId=<%=famUid%>" target="_blank"><%=famMid%> </a>					
    </span>	
    
    
</div>
<% } }%>
<div>
<table width="100%" cellpadding="0" cellspacing="1" border="0">
<tr>
    <td colspan="2" class="tblRowHead">Non-USEA Membership Information </td>
</tr>

<%
ArrayList nonusea=new ArrayList();
ArrayList donSelect=new ArrayList();

nonusea=(ArrayList)request.getAttribute("nonusea");

donSelect=(ArrayList)request.getAttribute("donSelect");

if(nonusea!=null) {
    System.out.println("nonusea.size() :"+nonusea.size());
    HLCNonUseaOrgVO onjNonUsea = new HLCNonUseaOrgVO();
    
    if(nonusea.size() == 0) {%>

<tr>
    <td class="tableSpan" colspan="2"><span class="styleBoldOne">No Non-USEA Organization details available for this Member!!</span></td>
    
</tr>

<%}

for(int t=0;t<nonusea.size();t++)
{		
onjNonUsea=(HLCNonUseaOrgVO)nonusea.get(t);
%>

<tr>
    <td class="tableLeft">Non USEA Organization Name: </td>
    <th class="tableRight"><%=onjNonUsea.getNonuseaOrgName()%></th>
</tr>

<%
String nonMemId="";

if(onjNonUsea.getNonuseaMemberId()!=null) {
    nonMemId=onjNonUsea.getNonuseaMemberId();
} else {
    nonMemId="NA";
}		

%>

<tr>
    <td class="tableLeft">Non USEA MemberID: </td>
    <th class="tableRight"><%=nonMemId%></th>
</tr>


<%}}else
{%>

<tr>
    <td class="tableSpan" colspan="2"><span class="styleBoldOne">No Non-USEA Organization details available !! </span></td>
    
</tr>

<%}

%>
<tr>
    <td colspan="2" class="tblRowHead">Donation Information </td>
</tr>		
<%
if(donSelect!=null) {
    System.out.println("donSelect.size() :"+donSelect.size());
    
    HLCDonationDetailVO onjDon = new HLCDonationDetailVO();
    
    if(donSelect.size() == 0) {%>

<tr>
    <td class="tableSpan" colspan="2"><span class="styleBoldOne">No donation details available for this member!!</span></td>
    

</tr>

<%}


for(int u=0;u<donSelect.size();u++)
{		
onjDon=(HLCDonationDetailVO)donSelect.get(u);	
String[] tmp=onjDon.getMemberDonationDate().split(" ");
%>	

<tr>
    <td class="tableLeft">Donated On: </td>
    <th class="tableRight"><span class="styleBoldOne"><%=dateFormat(tmp[0])%>&nbsp;</span></th>
</tr>		

<tr>
    <td class="tableLeft">Donation Type: </td>
    <th class="tableRight"><%=onjDon.getDonationName()%></th>
</tr>				


<tr>
    <td class="tableLeft">Donation Amount:($) </td>
    <th class="tableRight"><span class="styleBoldOne"><%=onjDon.getDonationPrice()%>&nbsp;</span></th>
</tr>

<%
String donBy="N/A";

if(onjDon.getDonatedBy()!=null) {
    donBy=onjDon.getDonatedBy();
}

%>


<tr>
<td class="tableLeft">Donated By</td>
<th class="tableRight"><%=donBy%>&nbsp;</span></th>
</tr>

<%}}else
{%>

<tr>
    <td class="tableSpan" colspan="2"><span class="styleBoldOne">No Donation Details Available for this Member!!</span></td>
    
</tr>

<%}

%>


<tr>
    <td colspan="2" class="tblRowHead">Amateur Rider Information </td>
</tr>		
<%
String AmateurName="";
String AmateurDec1="";
String AmateurDec2="";

boolean isAmateurDec2;
boolean isAmateurDec1;



isAmateurDec1=objMember.isAmateurDec1();

if(isAmateurDec1 == true) {
    AmateurDec1="Yes";
} else {
    AmateurDec1="No";
}



isAmateurDec2=objMember.isAmateurDec2();

if(isAmateurDec2 == true) {
    AmateurDec2="Yes";
} else {
    AmateurDec2="No";
}

System.out.println("AmateurName :"+AmateurName);
System.out.println("AmateurDec1 :"+isAmateurDec1);
System.out.println("AmateurDec2 :"+isAmateurDec1);  


%>

<tr>
    <td class="tableLeft">Name: </td>
    <th class="tableRight"><%=nullCheck(objMember.getAmateurName())%>&nbsp;</th>
</tr>																 
<tr>
    <td class="tableLeft">Possession of a current USEF Amateur Card ?</td>
    <th class="tableRight"><%=AmateurDec1%>&nbsp;</th>
</tr>	                                                                                                                                  
<tr>
    <td class="tableLeft">Declaration serves as my affidavit for Amateur Status Eligibility ? </td>
    <th class="tableRight"><%=AmateurDec2%>&nbsp;</th>
</tr>




</table>							
<!-- SearchList.do -->
<div class="row">
    <div class="alignCenter"><input type="button" value=" Back " class="gradBtn" onclick="javascript: history.back(-1);"/></div>              
</div>


</div>

</form>

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
