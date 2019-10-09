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
<%-- 
    Document   : createEditProject
    Created on : Dec 12, 2008, 3:06:51 PM
    Author     : Prateek
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=(String)session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
		<script>
		function popUp_report(){
val=window.open("MakeChart.do?process=areaDet&Submit=Submit&message=xlsToGraph" ,'AreaSalesGraph',
			  'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
              if(val == null) {
                alert("Please Enable Site Popup Allowed");
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
                    
                    <table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                        
                        <tr>
                            <td width="230" class="menuTablePad">
                                <!-- LEFT MENU STARTS HERE -->
                         <!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
                         <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                         <!-- LEFT MENU ENDS HERE -->
                                
                            </td>
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
                                <table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
                                    <!-- <tr>
                                         <td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
                                   </tr> -->
                                    <tr>
                                        
                                        <td colspan="2" class="tblDescrp">
                                            <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
                                            <span class="msgHead"> Members  Report </span>
                                            <br />
                                            <br />
                                            Use the section below to select Members Report Type
                                            
                                        </td>
                                       
                                        
                                    </tr>
                                    <tr>
                                        <td>
                                            
                                                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                                                    
                                                    <tr>
                                                        <td height="25" >
                                                            
                                                            <table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
                                                                
                                                                <!--
                                                                                   <tr class="tblMainHead">
                                                                                         <td height="25" colspan="2" class="textCommon">&nbsp;
                                                                                                 <a href="#" class="linkFour">Insert Address</a> |
                                                                                                 <a href="#" class="linkFour">Add Cc</a> | 
                                                                                                 <a href="#" class="linkFour">Add BCc</a>
                                                                                                 
                                                                                         </td>
                                                                                   </tr>
                                                                -->
                                                               <tr>
                                                                   
                                                                    <td height="25" class="tblMainHead"><a href="membersJoinedReport.do?type=membersJoinedYearWise">Total Number Of Members Joined</a>
                                                                    </td>
                                                                </tr> 
                                                                 <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanMembersAllAreaStatusWise">Human Members - All Areas and Status Wise </a>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                   
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanStatusWise"> Human Members - Status Wise</a>
                                                                    </td>
                                                                </tr> 
                                                                <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanMembersAllMemberhipTypesByArea">Human Members - Membership Type and Area Wise</a>
                                                                    </td>
                                                                </tr>

           
                                                                <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanMembershipStatusWise"> Human Members - Membership Type Wise</a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanSpecificMembershipStatusWise">Human Members - Membership Type and Status Wise</a>
                                                                    </td>
                                                                </tr>
                                                                  
                                                                 <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanMembersAreaWise"> Human Members - Area Wise</a>
                                                                    </td>
                                                                </tr>
                                                                 
                                                                <!-- <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanMembersAllMemberhipTypesByAreaAndStatus">Human Members - Area and Status Wise</a>
                                                                    </td>
                                                                </tr>-->
                                                                <!-- <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanMembersSpecificAreaAndMembershipType">Human Members - Area and Membership Type Wise</a>
                                                                    
                                                                </tr> -->
                                                                <tr>
                                                                    <td height="25" class="tblMainHead"><a href="humanMemberReport.do?type=humanMembersSpecificAreaAndMembershipTypeAndStatus">Human Members - Area, Membership Type and Status Wise</a>
                                                                    </td>
                                                                </tr>
                                                                
                                                                    <tr>

                                                                    <td height="25" class="tblMainHead"><a href="horseMemberReport.do?type=horseStatusWise"> Non-Human Members - Status Wise </a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="25" class="tblMainHead"><a href="horseMemberReport.do?type=horseMembershipStatusWise">Non-Human Members - Membership Type Wise</a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="25" class="tblMainHead"><a href="horseMemberReport.do?type=horseSpecificMembershipAndStatusWise">Non-Human Members - Membership Type and Status wise</a>
                                                                    </td>
                                                                </tr>
                                                                <!-- <tr>
                                                                    <td height="25" class="tblMainHead"><a href="horseMemberReport.do?type=newlyRegHorseMembersMeberShipWise">Total Number of Newly Registered Non-Human Members</a>
                                                                    </td>
                                                                </tr>-->
                                                                 
                                                               
                                                                 <!-- <tr>
                                                                   
                                                                    <td height="25" class="tblMainHead"><a href="createGraphByXLS.do">Generate Chart Report Based on SpreadSheet</a>
                                                                    </td>
                                                                </tr>  -->
                                                               
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
														 
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
