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
    Document   : createHorseMemberReports
    Created on : Jul 21, 2009, 11:40:05 AM
    Author     : Remya
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=(String)session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/calendar2.js" type="text/javascript"></script>
        <!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
        <script language="javascript">

function populateCurrYear(){
 var currfromYear=null;
 var currtoYear=null;
 var yearNow=null;
 currfromYear=document.getElementById("fromDate");
 currtoYear=document.getElementById("toDate");
 var currDate= new Date();
 yearNow=currDate.getFullYear();
  try{
    currfromYear.add(new Option(yearNow, yearNow));//in IE
        }catch(e){
    currfromYear.add(new Option(yearNow, yearNow),null);//in FireFox
        }
        try{
    currtoYear.add(new Option(yearNow, yearNow));//in IE
        }catch(e){
    currtoYear.add(new Option(yearNow, yearNow),null);//in FireFox
        }
}




function getfromdays(){
    var noLeapfrom=true;
    var sel =document.getElementById("frommonth").value;
    var selDay= document.getElementById("fromday");
    var currentfromYear=document.getElementById("toyear").value;//leap year check
    var currFYVal=parseInt(currentfromYear);
    if ( ((currFYVal % 4 == 0) && !(currFYVal % 100 == 0))|| (currFYVal % 400 == 0) ){

    noLeapfrom=false;
    if(selDay.length==28){
try{
    selDay.add(new Option("30", "30"));//in IE
    selDay.add(new Option("31", "31"));
    }
    catch(e){
    selDay.add(new Option("30", "30"),null);//in FireFox
    selDay.add(new Option("31", "31"),null);
    }
}
 
    }



    if(selDay.length==27){

    try{
    selDay.add(new Option("29", "29"));
    selDay.add(new Option("30", "30"));//in IE
    selDay.add(new Option("31", "31"));
    }
    catch(e){
    selDay.add(new Option("29", "29"),null);
    selDay.add(new Option("30", "30"),null);//in FireFox
    selDay.add(new Option("31", "31"),null);
    }
   
    }
    if(selDay.length==29){
        try{
       selDay.add(new Option("31", "31"));//in IE

        }catch(e){
       selDay.add(new Option("31", "31"),null);

        }
    }
    if ((sel==2)&&(noLeapfrom)){
     selDay.remove(selDay.length-1);
     selDay.remove(selDay.length-1);
     selDay.remove(selDay.length-1);
    }
   if((sel==2)&&(!noLeapfrom)){
     selDay.remove(selDay.length-1);
     selDay.remove(selDay.length-1);
    }
         
     if((sel==11) ||(sel==9)|| (sel==6 )||(sel==4)){
     selDay.remove(selDay.length-1);
}
}


function gettodays(){
    var noLeapto=true;
    var tosel =document.getElementById("tomonth").value;
    var toselDay= document.getElementById("today");
    var currenttoYear=document.getElementById("toyear").value;//leap year check
    var currTYVal=parseInt(currenttoYear);
 if ( ((currTYVal % 4 == 0) && !(currTYVal % 100 == 0))|| (currTYVal % 400 == 0) ){
     noLeapto=false;
    if(toselDay.length==28){
try{
    toselDay.add(new Option("30", "30"));//in IE
    toselDay.add(new Option("31", "31"));
    }
    catch(e){
    toselDay.add(new Option("30", "30"),null);//in FireFox
    toselDay.add(new Option("31", "31"),null);
    }
}
 
    }


    if(toselDay.length==27){

    try{
    toselDay.add(new Option("29", "29"));
    toselDay.add(new Option("30", "30"));//in IE
    toselDay.add(new Option("31", "31"));
    }
    catch(e){
    toselDay.add(new Option("29", "29"),null);
    toselDay.add(new Option("30", "30"),null);//in FireFox
    toselDay.add(new Option("31", "31"),null);
    }

    }
    if(toselDay.length==29){
        try{
       toselDay.add(new Option("31", "31"));//in IE
  
        }catch(e){
       toselDay.add(new Option("31", "31"),null);//in FireFox
 
        }
    }
    
    if ((tosel==2)&&(noLeapto)){
     toselDay.remove(toselDay.length-1);
     toselDay.remove(toselDay.length-1);
     toselDay.remove(toselDay.length-1);
    }
    if ((tosel==2)&&(!noLeapto)){
     toselDay.remove(toselDay.length-1);
     toselDay.remove(toselDay.length-1);
    }

     if((tosel==11) ||(tosel==9)|| (tosel==6 )||(tosel==4)){
     toselDay.remove(toselDay.length-1);
}
}

function UpdateSelect()
{
select_value = document.generateHorseMemberReport.view.value;
var id = 'hide_this_row';
var obj = '';
obj = (document.getElementById) ? document.getElementById(id) : ((document.all) ? document.all[id] : ((document.layers) ? document.layers[id] : false));

if(select_value =2)
{
obj.style.display = ( obj.style.display != "none" ) ? "none" : "";//Hide Fields
}
else
{
obj.visibility = "show";//Show Fields
}
}
            




    function validate(type){
       
        var flag=true;
                       if(document.generateHorseMemberReport.fromDate.value==""){
                           alert("Select From Date");
                           document.generateHorseMemberReport.fromDate.focus();
                           flag=false;
                           return flag;
                       }

                       if(document.generateHorseMemberReport.toDate.value==""){
                           alert("Select To Date");
                           document.generateHorseMemberReport.toDate.focus();
                           flag=false;
                           return flag;
                       }
                     
            var toDay =new Date();
            var currentYear = toDay.getFullYear();
            var toDate=document.generateHorseMemberReport.toDate.value;
            var fromDate=document.generateHorseMemberReport.fromDate.value;
            var todateVal= toDate.split("/");
            var toselectedYear = todateVal[2];
            if(toselectedYear>currentYear){
                alert("Please select ToDate less than or equal to current year");
                document.generateHorseMemberReport.toDate.focus();
                flag=false;
                return flag;

            }
            if (Date.parse(fromDate) > Date.parse(toDate))
            {
                alert("FromDate should not be greater than Todate");
                flag=false;
                return flag;
            }
                          
                                             if(flag)
                       {
                           popUp_report(type);
                       }
                   
    }

                   function popUp_report(type){

                       
                           var val =null; var status=null;var membershipid=null;
                           var fromDate =document.getElementById("fromDate").value;
                           var toDate =document.getElementById("toDate").value;
                           var view=document.getElementById("view").value;
				 var numArr = new Array ();
				j=0;
				var graph=document.getElementsByName("graph");
				
				for (i = 0; i < graph.length; i++)
				{
	                        if(graph[i].checked)
					{
								
								numArr[j]=graph[i].value;
								j++;
								
					}
					          
					               
                          }
                           if(type=='horseStatusWise')
                           {
                               val=  window.open("HorseMemberReports.do?process=HorseMembersStatusWise&fromDate="+fromDate+"&toDate="+toDate+"&view="+view+"&graph="+numArr.join()
                               ,'HorseMemberReports',
                               'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
                           }
                           if(type=='horseMembershipStatusWise')
                           {
                               status=document.getElementById("status").value;
                               val=  window.open("HorseMemberReports.do?process=HorseMembersMembershipAndStatusWise&fromDate="+fromDate+"&toDate="+toDate+"&status="+status+"&view="+view+"&graph="+numArr.join()
                               ,'HorseMemberReports',
                               'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
                           }
                           if(type=='horseSpecificMembershipAndStatusWise')
                           {
                               membershipid=document.getElementById("membershipid").value;
                               status=document.getElementById("status").value;
                               val=  window.open("HorseMemberReports.do?process=HorseSpecificMembershipAndStatusWise&fromDate="+fromDate+"&toDate="+toDate+"&status="+status+"&membershipid="+membershipid+"&view="+view+"&graph="+numArr.join()
                               ,'HorseMemberReports',
                               'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
                           }
                            if(type=='HorseMembersEventCompetitionsWise')
                           {
                               val=  window.open("HorseMemberReports.do?process=HorseMembersEventCompetitionsWise&fromDate="+fromDate+"&toDate="+toDate+"&view="+view+"&graph="+numArr.join()
                               ,'HorseMemberReports',
                               'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
                           }
                            if(type=='HorseMembersEventCompetitionsAndMembershipTypeWise')
                           {
                               val=  window.open("HorseMemberReports.do?process=HorseMembersEventCompetitionsAndMembershipTypeWise&fromDate="+fromDate+"&toDate="+toDate+"&view="+view+"&graph="+numArr.join()
                               ,'HorseMemberReports',
                               'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
                           }

							    if(type=='newlyRegHorseMembersMeberShipWise')
                           {
                               val=  window.open("HorseMemberReports.do?process=newlyRegHorseMembersMeberShipWise&fromDate="+fromDate+"&toDate="+toDate+"&view="+view+"&graph="+numArr.join()
                               ,'HorseMemberReports',
                               'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
                           }

                       }
                   
        </script>
    </head>

    <body onload="populateCurrYear();">
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
<%  if (request.getParameter("type").equalsIgnoreCase("horseStatusWise")) { %>
                                             <b>Non-Human Members - Status Wise</b>
                                             <%  } else if (request.getParameter("type").equalsIgnoreCase("horseMembershipStatusWise")) { %>
                                             <b>Non-Human Members - Membership Type Wise</b>
                                             <%  } else if(request.getParameter("type").equalsIgnoreCase("horseSpecificMembershipAndStatusWise")) { %>
                                             <b>Non-Human Members - Membership Type and Status wise</b>
                                             <%  } else if(request.getParameter("type").equalsIgnoreCase("newlyRegHorseMembersMeberShipWise")) { %>
                                             <b>Total Number of Newly Registered Non-Human Members</b>
                                             <% } else if(request.getParameter("type").equalsIgnoreCase("HorseMembersEventCompetitionsWise")) { %>
                                             <b>Non-Humans Participated in Events</b>
                                             <% } else if(request.getParameter("type").equalsIgnoreCase("HorseMembersEventCompetitionsAndMembershipTypeWise")) { %>
                                             <b>Non-Humans Participated in Events - Membership Type Wise</b>
                                           <%} %>
                                      </td>


                                    </tr>
                                    <tr>
                                        <td>

       <form   name="generateHorseMemberReport" method="post"  onsubmit="return validate('<%=(String)request.getParameter("type") %>');">
 <% if(request.getParameter("type").equalsIgnoreCase("horseStatusWise")) { %>
                <input type="hidden" name="process" id="process" value="horseMembersStatusWise" />
                <% } else if (request.getParameter("type").equalsIgnoreCase("horseMembershipStatusWise")) { %>
                <input type="hidden" name="process" id ="process" value="horseMembershipStatusWise" />
                <%  } else if (request.getParameter("type").equalsIgnoreCase("horseSpecificMembershipAndStatusWise")){%>
                <input type="hidden" name="process" id="process" value="HorseSpecificMembershipAndStatusWise" />
                <% }else if (request.getParameter("type").equalsIgnoreCase("newlyRegHorseMembersMeberShipWise")){%>
                <input type="hidden" name="process" id ="process" value="newlyRegHorseMembersMeberShipWise" />
                <% } else if (request.getParameter("type").equalsIgnoreCase("HorseMembersEventCompetitionsWise")){%>
                <input type="hidden" name="process" id ="process" value="HorseMembersEventCompetitionsWise" />
                <% } else if (request.getParameter("type").equalsIgnoreCase("HorseMembersEventCompetitionsAndMembershipTypeWise")){%>
                <input type="hidden" name="process" id ="process" value="HorseMembersEventCompetitionsAndMembershipTypeWise" />
                <% } %>

               
             <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">

                                                    <tr>
                                                        <td height="25" >

                                                            <table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
                   <% if(request.getParameter("type").equalsIgnoreCase("newlyRegHorseMembersMeberShipWise")) { %>
                   <tr>
                       <td class="tableLeft1">From Date: </td>
                   <td class="tableRight1"><input type="text" name="fromDate" id="fromDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>    


                   </tr>
                   <tr>
                       <td class="tableLeft1">To Date: </td>
                       
                    <td class="tableRight1"><input type="text" name="toDate" id="toDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>

                   </tr>
                    <% }else { %>
				
                   <tr>
            <td class="tableLeft1">From Date: </td>
            <td class="tableRight1"><input type="text" name="fromDate" id="fromDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
          		</tr>
				<tr>
            <td class="tableLeft1">To date: </td>
            <td class="tableRight1"><input type="text" name="toDate" id="toDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
          		</tr>

                  <% if (request.getParameter("type").equalsIgnoreCase("horseMembershipStatusWise")||(request.getParameter("type").equalsIgnoreCase("horseSpecificMembershipAndStatusWise"))) { %>
                         <tr>
            <td class="tableLeft1">Status: </td>
            <td class="tableRight1"><select  name="status" id="status">
                                                                            <option value="Duplicate">Duplicate</option>
                                                                            <option value="Merged">Merged</option>
                                                                            <option value="Expired">Expired</option>
                                                                            <option value="Active">Active</option>
                                                                            <option value="Pending">Pending</option>
                                                                            <option value="Inactive">Inactive</option>
                                                                            <option value="Suspended">Suspended</option>
                                                                            <option value="Deceased">Deceased</option>
                                                                            <option value="TBD">TBD</option>
                                                                            <option value="Retired">Retired</option>
                                                                        </select>
                         <span class="asterisk">*</span> <span class="asterisk">*</span></td></tr><%  } %>



                <% if (request.getParameter("type").equalsIgnoreCase("horseSpecificMembershipAndStatusWise")) { %>
                         <tr>
                        <td class="tableLeft1">Membership Type: </td>
             <td class="tableRight1"><select  name="membershipid" id="membershipid">
                                                                            <option value="A75F797E-5279-4F2C-BC2B-048E88FD9020">Young Event </option>
                                                                            <option value="9057F93F-D265-46B3-B5E5-352B275BF191">LIMITED</option>
                                                                            <option value="01A46E72-1FBF-412F-8626-3C7CFE48EB57">FUTURE EVENT</option>
                                                                            <option value="23967F37-D688-4B20-8662-793AB8F56354">DUPLICATE</option>
                                                                            <option value="C3D04478-20DB-434D-A6E9-876FD04609A3">NOT REGISTERED</option>
                                                                            <option value="3A1B7858-99B1-456C-AFB9-F220B15654DA">FULL </option>

                                                                        </select>
                         <span class="asterisk">*</span> <span class="asterisk">*</span></td>  </tr><%  } %>
                                         <% }  %>

                                                                <!-- <tr>
                                                   <th height="25" class="tblMainHead"><span class="fonts">Chart Type :</span></th>
                                                                  <td height="25" class="listCellBg">
																	<select  name="chartType">
																	<option value="Pie">Pie</option>
																	<option value="Bar">Bar</option>
                                                                                                                                        <option value="Line">Line</option>
                                                                                                                                        <option value="Pyramid">Pyramid</option>
																	<option value="Area">Area</option>


																	</select>
																	<span class="asterisk">*</span>
																</td>
                                                               </tr> -->
                                                               

                                                                 <tr>
                             <td class="tableLeft1">Report type :</td><td class="tableRight1"><select name="view" id="view" onChange="UpdateSelect();">
                            <option value='1'>Graphical</option>
                            <option value='2'>Tabular</option></select></td>
                            
                            
                        </tr>
                        <tr id="hide_this_row">
                            <td class="tableLeft1">Graph types :</td>
							 <td class="tableRight1">
                                 <% if(request.getParameter("type").equalsIgnoreCase("horseStatusWise") || request.getParameter("type").equalsIgnoreCase("horseMembershipStatusWise") || request.getParameter("type").equalsIgnoreCase("newlyRegHorseMembersMeberShipWise") || request.getParameter("type").equalsIgnoreCase("HorseMembersEventCompetitionsWise") || request.getParameter("type").equalsIgnoreCase("HorseMembersEventCompetitionsAndMembershipTypeWise"))  { %>
                                 <input type="checkbox" name="graph"  value="pie" checked>Pie<br>
								 <input type="checkbox" name="graph" value="PyramidChart">Pyramid<br>
								 <input type="checkbox" name="graph" value="chart">Bar<br>
								 <input type="checkbox" name="graph" value="Linechart">Line<br>
								 <% } else if(request.getParameter("type").equalsIgnoreCase("horseSpecificMembershipAndStatusWise")){%>
                                                                  <input type="checkbox" name="graph" value="plotformat">PlotFormat<br>
                                                                    <input type="checkbox" name="graph" value="chart">Bar<br> 
                                                                    <input type="checkbox" name="graph" value="cone">Cone<br>
                                                                        <input type="checkbox" name="graph" value="tube">Tube<br>
                                                                  <%} %>
                                                                <tr>
                                                                    <td colspan="2" class="tableRight1">

                                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                                            <tr>
                                                                                <td width="11%" align="center">

                                                                                    <input type="submit" name="Submit" value="Submit" class="gradTwoBtn" />


                                                                                                                                                                   </td>
                                                                            </tr>
                                                                        </table>                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
                                                    </tr>
                                                </table>
                                                <input type="hidden" name="message" value="xlsToGraph" />
                                                

                                            </form>

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
<script language="javascript">

	var cal1 = new calendar2(document.forms['generateHorseMemberReport'].elements['fromDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;

	var cal2 = new calendar2(document.forms['generateHorseMemberReport'].elements['toDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;

</script>
