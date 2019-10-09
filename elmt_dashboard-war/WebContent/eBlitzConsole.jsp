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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 
<style>

.sub_h{
                font-family:Georgia;
                border-top:#0099FF solid 1px;
                border-bottom:#0099FF solid 1px;
                line-height:25px;
                font-style:normal;	
                font-weight:normal;
				font-size:20px;
                color: 	#006699;			
            }
			</style>
<html lang="en,us"><head>

 
    <title>digiBlitz Suite Console </title>
    <meta content="text/html; charset=windows-1252" http-equiv="Content-Type">
    <link rel="stylesheet" href="">
   
   
  
    </head>
	

	<body text="#000000" vlink="#996633" alink="#ff6600" bgcolor="#ffffff" link="#0000000">


<h2><font color="#396EA2 "><span >Welcome !</span></font></h2>    
<table summary="" width="100%" border="0" cellpadding="4" cellspacing="0">
         
          <tbody><tr valign="top" align="">
            </tr>
</tbody></table>
<!--  <p align="left" font color="#">eBlitz Suite is a complete set of service
  infrastructure components for creating, deploying, and
  managing <span class="OraTextInline">Service Oriented Architectures</span>.</p> -->
<p align="left">digiBlitz Suite
  enables services to be created, managed, and orchestrated
  into composite applications and business processes.</p>
<p align="left"> digiBlitz Suite 1.0 consists of:</p>
<ul>
  <li><strong>digiBlitz BPEL Engine </strong></li>
  <li><strong>digiBlitz Enterprise Service Bus (ESB) </strong></li>
  <li><strong>digiBlitz Governance </strong></li>
   <!--  li><strong>digiBlitz BPM Engine </strong></li-->
</ul>
<p></p></font>
</div>
<div >
            <table summary="" width="99%" align="center" border="0" cellpadding="6" cellspacing="0">
			<tr>
                <td height="37"><h3 class ="sub_h"align="left"><font color="#396EA2 " >MANAGEMENT TOOLS </h3></td>
              </tr>
              <tr>
                <td height="100"><ul>
                    <li><strong> digiBlitz BPEL Engine</strong> </li>
                    <FORM method="post"  ACTION ="EngineMgt.do">
                     <input type="hidden" name="process" id="process" value="startEngines">
                    <input type="hidden" name="proc" id="proc" value="bpel">
                      <input name="submit4" type="submit" value="Start">    </FORM>
					  <FORM method="post"  ACTION ="EngineMgt.do">
					   <input type="hidden" name="process" id="process" value="stopEngines">
                   <input type="hidden" name="proc" id="proc" value="bpelStop">
                      <input name="submit42" type="submit" value="Stop">
                    </FORM>
                      
                   
          <li><strong> digiBlitz ESB
                  <FORM method="post"  ACTION ="EngineMgt.do">
                   <input type="hidden" name="process" id="process" value="startEngines">
                   <input type="hidden" name="proc" id="proc" value="esb">
				   
                    <input name="submitesb" type="submit" value="Start"/>
					
				
                    </FORM>
                    <FORM method="post"  ACTION ="EngineMgt.do">
                     <input type="hidden" name="process" id="process" value="stopEngines">
                    <input type="hidden" name="proc" id="proc" value="esbStop">
                          <input name="submitesb2" type="submit" value="Stop">
                          </FORM>
                    </strong></li> 
                    
                    
                    
                  <li><strong> digiBlitz Governance
                  <FORM method="post"  ACTION ="EngineMgt.do">
                   <input type="hidden" name="process" id="process" value="startSOABin">
                    <input name="submit3" type="submit" value="Start">
                  </FORM>
                     <FORM method="post"  ACTION ="EngineMgt.do">
                      <input type="hidden" name="process" id="process" value="stopSOABin">
                          <input name="submit22" type="submit" value="Stop">
                  </FORM>
                    </strong></li>
                    
    <!-- li><strong> digiBlitz BPM Engine
                  <FORM method="post"  ACTION ="EngineMgt.do">
                   <input type="hidden" name="process" id="process" value="startEngines">
                   <input type="hidden" name="proc" id="proc" value="bpm">
                     <input name="submitbpm" type="submit" value="Start">
                  </FORM>
                     <FORM method="post"  ACTION ="EngineMgt.do">
                      <input type="hidden" name="process" id="process" value="stopEngines">
                     <input type="hidden" name="proc" id="proc" value="Stopbpm">
                          <input name="submitbpm2" type="submit" value="Stop">
                  </FORM>
                    </strong></li>   
                    
  <li><strong> Smartlehren Engine
                  <FORM method="post"  ACTION ="EngineMgt.do">
                   <input type="hidden" name="process" id="process" value="startSLEngines">
                   <input type="hidden" name="proc" id="proc" value="slhn">
                     <input name="submitslhn" type="submit" value="Start">
                  </FORM>
                     <FORM method="post"  ACTION ="EngineMgt.do">
                      <input type="hidden" name="process" id="process" value="stopSLEngines">
                     <input type="hidden" name="proc" id="proc" value="StopSlhn">
                          <input name="submitslhn2" type="submit" value="Stop">
                  </FORM>
                    </strong></li-->                                  
                    
                    
                    
                    
                </ul>
                    <p>&nbsp;</p>
            <tr>
                <td height="37"><h3 class="sub_h"align="left"><font color="#396EA2 " >MANAGEMENT CONSOLES </h3>
              <tr>
                <td height =100 ><ul><font color="#000000 " >
                    <li><a href="http://localhost:8085/ode/">digiBlitz BPEL Engine Console</a></li>
                  <li><a href="http://localhost:8181/system/console/bundles">digiBlitz ESB Console</a> </li>
                  <li><a href="http://localhost:8084/repository/">digiBlitz Governance Console</a></li>
                </ul>
              <tr>
                <td height="37"><h3 class="sub_h"align="left"><font color="#396EA2 " >LEARNING digiBlitz SUITE </font></h3>
              <tr>
                <td height =100 ><ul>
                  <li><em><a href="digiBlitz BPEL Engine Console.html">digiBlitz BPEL Engine Manual</a></em></li>
                  <li> <em><a href="digiBlitzESBmanual.html">digiBlitz ESB Manual</a></em></li>
                  <li> <em><a href="digiBlitzGovmanual.html">digiBlitz Governance Manual</a></em> </li>
                </ul>
              <tbody title="Overview" align="left">
                <tr>
                 
                             
              
  </table>
          </td>
          
          <!-- Right Management Consoles -->
  <td valign="top" width="240" align="left">&nbsp;</td>
        </tr>
        </tbody></table>
</div>
<div align="left"></div>


    </body></html>
