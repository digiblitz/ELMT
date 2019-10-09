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
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlccommon.util.HLCHorseSearchVO" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=(String)session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script type="text/javascript" >
var radioFlag=false; 
function validateUser(){
	if(document.frmUserSignup.status.value=="exist"){
		chosen="";
		len2 = document.frmUserSignup.quest_rad.length;
		//alert("len2");
		for(j=0;j<len2;j++){
			if(document.frmUserSignup.quest_rad[j].checked){
				chosen= document.frmUserSignup.quest_rad[j].value; 
				if(chosen=='yes'){
					if(!radioFlag){
						alert("Select any of the Horse Detail");
						radioFlag=false;
						 return false;
					}
				}
			}
		}
	}
	return true;
}

function radioHorse(){
 radioFlag=true;
 }
  
  
  function call(horseid)	{
	//document.forms[0].action = "login?HorseID="+horseid;
	//document.forms[0].submit();
	window.location = "http://reports.useventing.com/Reports/Pages/Report.aspx?ItemPath=%2fPublic%2fHorse_Profile_Public&rs:Command=Render&rs:format=PDF&HORSEID="+horseid;
}
     

</script>
        <%! String  nullCheck(String fieldName){
            String retValue = "NG";
            if(fieldName!=null && fieldName.trim().length()!=0){
                retValue = fieldName;
            }
            return retValue;
        }
        
        %>
        <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
                            <td width="260" class="menuTablePad">
                                <!-- LEFT MENU STARTS HERE -->
                                <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                                <!-- LEFT MENU ENDS HERE -->

                            </td>
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
				
                                <table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
                                    <tr>
                                        <td colspan="2" class="tblMainHead"><strong></strong> Horse Search: <span class="styleBoldTwo">Account Matching Result</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="tblDescrp">To view Horse Registration details, click the ID link.

To create a PDF version of the Horse Profile report, active members can click the Horse Name link. PDF software like Adobe Acrobat is required.
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            
                                            
                                            <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
                                                <tr>
                                                    <td colspan="2" class="tblRowHead">&nbsp;Matching Results:</td>
                                                </tr>
                                                
                                                
                                                
                                                
                                                
                                                <tr>
                                                    <td height="25" colspan="2" class="tableSpan"><span class="styleBoldTwo">Note:</span><span class="styleBoldOne"> Reg </span> <strong>-Registration, <span class="styleBoldOne">NG </span>- Not Given</strong></td>
                                                </tr> 
                                                
                                                
                                                <tr>
                                                    <td colspan="2" height="25" class="alignCenter">
                                                        
                                                        
                                                        <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                                            <tr>
                                                                <td width="13%" height="25" class="tblMainHead"> ID </td>
                                                                <td width="18%" height="25" class="tblMainHead">Horse Name </td>
                                                                
                                                                <td width="15%" height="25" class="tblMainHead">Rider Name (primary)</td>
                                                                <td width="16%" height="25" class="tblMainHead">Owner Name (primary) </td>
                                                                <td width="15%" height="25" class="tblMainHead">Edit</td>
                                                                
                                                                <td width="15%" height="25" class="tblMainHead">Activate </td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                            
                                                            <%
                                                            
                                                            
                                                            
                                                            String riderFirstName = ""; 
                                                            String riderLastName = ""; 
                                                            String ownerFirstName = ""; 
                                                            String ownerlastName = ""; 
                                                            String horseMemberId = ""; 
                                                            String horseName = ""; 
                                                            String membershipTypeId = ""; 
                                                            String membershipTypeName  = ""; 
                                                            String statusName  = ""; 
                                                            String prname ="";
                                                            String poname ="";
                                                            String colorDesc = "" ;
                                                            String gender = "";
                                                            String height = "";
                                                            String yearFoaled = "";
															String secondBreed = "";
                                                            String breedDesc = "";
                                                            String ownerId = "";
                                                            String riderId = "";
                                                            
                                                            ArrayList horseVal = (ArrayList)request.getAttribute("horseResultDetails");
                                                            if(horseVal!=null && horseVal.size()!=0){
                                                            Iterator itr = horseVal.iterator();
                                                            while(itr.hasNext()) {
                                                            
                                                            HLCHorseSearch2VO objSearchHorse =(HLCHorseSearch2VO)itr.next();
                                                            riderFirstName = objSearchHorse.getRiderFirstName() ; 
                                                            riderLastName = objSearchHorse.getRiderLastName(); 
                                                            ownerFirstName = objSearchHorse.getOwnerFirstName(); 
                                                            ownerlastName = objSearchHorse.getOwnerlastName()  ; 
                                                            horseMemberId = objSearchHorse.getHorseMemberId() ; 
                                                            horseName = objSearchHorse.getHorseName(); 
                                                            membershipTypeId = objSearchHorse.getMembershipTypeId(); 
                                                            membershipTypeName = objSearchHorse.getMembershipTypeName(); 
                                                            statusName =objSearchHorse.getStatusName() ; 
                                                            colorDesc = objSearchHorse.getColorDesc();
                                                            gender = objSearchHorse.getGender();
                                                            height = objSearchHorse.getHeight();
                                                            yearFoaled = objSearchHorse.getYearFoaled();
															secondBreed = objSearchHorse.getSecondBreed();
                                                            breedDesc = objSearchHorse.getBreedDesc();
                                                            ownerId = objSearchHorse.getOwnerId();
                                                            riderId = objSearchHorse.getRiderMemberId();
                                                            prname = riderFirstName +" "+ riderLastName;
                                                            poname = ownerFirstName +" "+ ownerlastName;
                                                            
                                                            if(objSearchHorse.getMembershipTypeName()!=null) {
                                                            membershipTypeName = objSearchHorse.getMembershipTypeName();
                                                            }
                                                            else{
                                                            membershipTypeName = "NG";
                                                            }
                                                            %>
                                                            <form name="testfrm" action="RegHorseListing.do" method="post">	  
                                                                <input type="hidden" name="process" value="Approve"/>	
                                                                <input type="hidden" name="memid" value="<%=horseMemberId%>"/>	
                                                                
                                                                <tr>
                                                                    <td height="25" class="listCellBg"><a href="RegHorseListing.do?process=adminHorseDet&memid=<%=horseMemberId%>"><%=nullCheck(horseMemberId)%></a></td>
																	
									 
									 								
                                                                    <td height="25" class="listCellBg" ><a href="http://reports.useventing.com/ReportServer?/Public/Horse_Profile_Public&rs:Command=Render&rs:format=PDF&HORSEID=<%=horseMemberId%>" target="_blank"> <%=horseName%> </a>
																	</td>
                                                                    <td height="25" class="listCellBg"><a href="ownRidDet.do?process=view&memberId=<%=riderId%>"><%=prname%></a></td>
                                                                    <td height="25" class="listCellBg"> <a href="ownRidDet.do?process=view&ownerInfo=ownerValue&userId=<%=ownerId%>"><%=poname%></a></td> 
                                                                    
                                                                    <%
                                                                    if(membershipTypeName.equalsIgnoreCase("Not Registered")){
                                                                    %>
                                                                    <td class="listCellBg"><input type="button" name="btnOwner" value="Update" class="twoBtn" onclick="location.href='AdminHorseEdit.do?process=desc&memid=<%=horseMemberId%>';"  /></td>
                                                                    <%
                                                                    }
                                                                    else{
                                                                    %>
                                                                    <td class="listCellBg"><input type="button" name="btnOwner" value="Update" class="twoBtn" onclick="location.href='AdminHorseEdit.do?process=desc&memid=<%=horseMemberId%>';"/></td>
                                                                    <%
                                                                    }
                                                                    %>
                                                                    <%
                                                                    if(membershipTypeName.equalsIgnoreCase("Not Registered")){
                                                                    %>
                                                                    <td class="listCellBg"> 
                                                                    <input type="submit" name="click" value="Approve" class="twoBtn" disabled="disabled"/></td>
                                                                    <%
                                                                    }
                                                                    else{
                                                                    %>
                                                                    <td class="listCellBg"><input type="submit" name="click" value="Approve" class="twoBtn"/></td>
                                                                    <%
                                                                    }
                                                                    %>
                                                                </tr>
                                                                <tr>
                                                                <tr>

															
                                                                   <td height="25" colspan="7" class="listCellBg1">
																		 <span class="textGrey">
																		  <%=nullCheck(membershipTypeName)%>,&nbsp; 
																		 <%=nullCheck(statusName)%>, &nbsp;
																		 <%=nullCheck(yearFoaled)%> ,&nbsp;
																		 <%=nullCheck(colorDesc)%>, &nbsp;
																		 <%=nullCheck(breedDesc)%>, &nbsp;
																		 <%=nullCheck(secondBreed)%>, &nbsp;																						 																          <%=nullCheck(gender)%>, &nbsp;
																		 <%=nullCheck(height)%>
																		 </span>                                                        
																	</td>
                                                                </tr>
                                                                
                                                            </form>            
                                                            <%
                                                            }
                                                            }
                                                            else{
                                                            %>
                                                            <tr>
                                                                <th colspan="9" height="22" class="listCellBg"><span class="alignCenter">No records are available</span></th>
                                                            </tr> 
                                                            
                                                            <%
                                                            }
                                                            %>
                                                    </table>											  </td>
                                                </tr>
                                                
                                                <%
                                                session.setAttribute("adminSearch","adminSearch");
                                                
                                                %>	
                                                
                                                <tr>
                                                    <td colspan="2" class="alignCenter">
                                                        <input type="button" value="Back To Search" class="gradBtn"  onclick="location.href='SearchHorse.do?searchProcess=initAdminPage';"  />&nbsp;&nbsp;
                                                        
                                                    </td>
                                                </tr>
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
