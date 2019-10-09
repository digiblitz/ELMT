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
        <script language="javascript">
            
            function validate(){
                if(document.generateChartByXLS.reportTitle.value=="" || document.generateChartByXLS.reportTitle.value.indexOf(' ')==0){
                    alert("Please enter the Report Title");
                    document.generateChartByXLS.reportTitle.focus();
                    return false;
                }
				 if(document.generateChartByXLS.xlsFile.value=="" || document.generateChartByXLS.xlsFile.value.indexOf(' ')==0){
                    alert("Please upload SpreadSheet");
                    document.generateChartByXLS.xlsFile.focus();
                    return false;
                }
                
                if(document.generateChartByXLS.projectDescription.value.length==0 || document.generateChartByXLS.projectDescription.value.indexOf(' ')==0){
                    alert("Please enter the Project Description");
                    document.createProject.projectDescription.focus();
                    return false; 
                }
                return true;
            }
            function popUp_report(type){
                var val =null;
                var year =document.getElementById("year").value;
                var paymentMode =document.getElementById("paymentMode").value;
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
                if(type=='daily'){
                var month = document.getElementById("month").value;
           val=  window.open("MakeChart.do?process="+type+"&month="+month+"&year="+year+"&paymentMode="+paymentMode+"&Submit=Submit&message=xlsToGraph"+"&view="+view+"&graph="+numArr.join()
             ,'DailySalesGraph',
			  'width=1200,height=700,toolbar=no,location=no,directories=yes,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')
              if(val == null) {
                alert("Plz Enable Site Popup Allowed");
               }
                }
                else if(type='monthly'){
                val=  window.open("MakeChart.do?process="+type+"&year="+year+"&paymentMode="+paymentMode+"&Submit=Submit&message=xlsToGraph"+"&view="+view+"&graph="+numArr.join()
             ,'DailySalesGraph',
			  'width=1200,height=700,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,scrollbars=yes,copyhistory=yes,resizable=yes')
                if(val == null) {
                alert("Plz Enable Site Popup Allowed");
               }
                }
    }
        function UpdateSelect()
{
select_value = document.generateChartByXLS.view.value;
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
                                     
                                            <% if( (request.getParameter("type").equals("daily")) && (request.getParameter("type")!=null) )
                                                { %>
                                                   <b>Daily Sales Report</b>
                                                   <% }
                                            else{ %>
                                               <b> Monthly Sales Report</b>
                                           <% } %>
                                             
                                      </td>
                                       
                                        
                                    </tr>
                                    <tr>
                                        <td>

       <form   name="generateChartByXLS" method="post"   onsubmit="return validate();">

                                             
             <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
                                                    
                                                    <tr>
                                                        <td height="25" >
                                                            
                                                            <table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
                      <% String month[]={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"}; %>
                                                             <% if( (request.getParameter("type").equals("daily")) && (request.getParameter("type")!=null) )
                                                { %>                   <input type="hidden" name="process" value="daily" />
                                                                <tr>
                                                                    <td width="25%" height="25" class="tableLeft1">Select Month : </td>
                                                                    <td width="83%" class="listCellBg1">
                                                                                            <select  name="month" id="month">          <% for(int i=0;i<12;i++) { %>
																	<option value=<%=(i+1)+""%> ><%=month[i]%></option>  <% } %>
          										</select><span class="asterisk">*</span>
                                                                        <div id="statusId"></div>                                                                    </td>
                                                                </tr>
                                                                <% } %>
                                                                <input type="hidden" name="process" value="monthly" id="process" />
                                                                  <tr>
                                                                    <td width="25%" height="25" class="tableLeft1">Select Year : </td>
                                                                    <td width="83%" class="listCellBg1">
                                                                                            <select  name="year" id="year">
																	<option value="2006">2006</option>
																	<option value="2007">2007</option>
																	<option value="2008">2008</option>
																	<option value="2009">2009</option>
                                                                                                                                        
																	
													</select><span class="asterisk">*</span>
                                                                        <div id="statusId"></div>                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td height="25" class="tableLeft1">Payment Mode:</td>
                                                                    <td height="25" class="listCellBg1">
																	<select  name="paymentMode" id="paymentMode">
																	<option value="Visa">Visa</option>
																	<option value="MasterCard">MasterCard</option>
																	<option value="check">check</option>
																	
																	</select>
																	<span class="asterisk">*</span>
                                                                    </td>
                                                                </tr>
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
                                 <% if(request.getParameter("type").equalsIgnoreCase("daily") || request.getParameter("type").equalsIgnoreCase("monthly"))  { %>
                                 <input type="checkbox" name="graph"  value="pie" checked>Pie<br>
								 <input type="checkbox" name="graph" value="Linechart">Line<br>
								 <input type="checkbox" name="graph" value="chart">Bar<br>
								 <input type="checkbox" name="graph" value="PyramidChart">Pyramid<br>
								  

<% } else { %>
<input type="checkbox" name="graph" value="pie">Pie<br>
 
<input type="checkbox" name="graph" value="line">Line<br> 
<input type="checkbox" name="graph" value="PyramidChart">Pyramid<br>
<% } %>
    
        
                             </td>
                            
                            
                        </tr>
                                                                
                                                                <tr>
                                                                    <td colspan="2" class="tblRowHead1">
                                                                        
                                                                        <table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
                                                                            <tr>
                                                                                <td width="11%" align="center">
                                                                                   
                                                                                    <input type="submit" name="Submit" value="Submit" class="gradTwoBtn" 
                                                                                           onclick="javascript:popUp_report('<%=request.getParameter("type")%>')">
                                                                                   
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
