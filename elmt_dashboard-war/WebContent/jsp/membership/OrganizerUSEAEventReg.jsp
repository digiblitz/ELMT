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
<%@ page import="java.sql.*"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/core-mff.css" type="text/css" rel="stylesheet" />
<script src="javascripts/basic.js" type="text/javascript"></script>

</head>

<body onload="initCondition();">

<div class="cmmnForm">
			
			<div class="colspan">
				<strong>ORGANIZERS: Event Registration Form</strong> 
			</div>
			<div id="commonBG" class="textCommon">
				Organizers, use this Event Registration form to register an event. This form has been
				divided into five parts for it's easy handling. <strong>Click on the tabs seen below to Expand / Contract it</strong> 
				and hence reveal the hidden parts of the form. 
			</div>	
	
<form name="myform" id="myform" class="formcss" methord="post" action="./InsertOrgHLCEventRegAction.do" onsubmit="return validateMyform(this);">

  

<!-- **************************************** Part A of the form starts here *********************************************** -->


<div class="rowExpand" onclick="expandColl('parta');">Event Registration Form: Part A <span class="textOne">(click to expandcontract)</span></div>
<div id="parta" class="formPart">	

     
			<div class="row">
				<span class="label">Event Secretary ID:</span>
				<span class="formX"><input type="text" size="25" name="eventsecid" /></span>
			</div>	
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><input type="text" size="25" name="hlcstaffname" readonly="true"/></span>
			</div>	
			<div class="row">
				<span class="label">Address for entries to be sent: </span>
				<span class="formX"><input type="text" size="30" name="address" readonly="true"/></span>
			</div>
			<div class="row">
				<span class="label">&nbsp;</span>
				<span class="formX"><input type="text" size="30"  name="city" readonly="true"/></span>
			</div>
			<div class="row">
				<span class="label">Telephone:</span>
				<span class="formX"><input type="text" size="20" name="phone" readonly="true"/></span>
			</div>
			<div class="row">
				<span class="label">Fax:</span>
				<span class="formX"><input type="text" size="20" name="fax" readonly="true"/></span>
			</div>
			<div class="row">
				<span class="label">EMail:</span>
				<span class="formX"><input type="text" size="25" name="email" readonly="true"/></span>
			</div>
	<div class="rowHead">
		Divisions: <span class="labelTwo">(Select the division(s) & level(s) that will be offered)</span>
	</div>
			<div class="division">
			<%! String x1; %>
			
			<% 
			
			java.util.Vector allTypesVect=new Vector();
			allTypesVect=(java.util.Vector)session.getAttribute("allTypesVect");
			
			Enumeration itrators= (Enumeration)allTypesVect.elements();
				
			while(itrators.hasMoreElements()){
				String[] s = (String[]) itrators.nextElement();                                    
				String code=s[4];
				String mid=s[0];
			
			%>   
			   <% if(!(s[3].equals(x1))){
			   x1=s[3];
			   %>
			
			<div class="colspan"><%=x1%></div>
				<%}%>
			<div class="formXTwo">
			<span>
			<input type="checkbox" size="10" name="<%=mid%>" value="<%=mid%>" /><%=code%></span>    
			</div>   
			<%  
			} 
			%>
			</div>
	
	<div class="rowHead">
		Officials:
	</div>
	
			<div class="colspan">
				Technical Delegates <span class="labelTwo">(Eventing TD)</span> 
			</div>
			<div class="row">
				<span class="label">Name:</span>
	<span class="formX"><input type="text" size="25" name="techdelegate" id="techdelegate" /></span>
			</div>
			
			
			<div class="colspan">
				President of the Ground Jury <span class="labelTwo">(Eventing Judge)</span> 
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><input name="groundjury" type="text" id="groundjury" size="25"  /></span>
			</div>
			<div class="colspan">
				Judges:<span class="labelTwo">(Eventing Judges or Dressage Judges)</span> 
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><input name="judge1" type="text" id="judge1" size="25" />
				</span>
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><input name="judge2" type="text" id="judge2" size="25" />
				</span>
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><input name="judge3" type="text" id="judge3" size="25" />
				</span>
			</div>
				
			<div class="colspan">
				Show jumping Judge: <span class="labelTwo">(Eventing Judge, TD or Jumper Judge)</span> 
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><input name="jumpjudge" type="text" id="jumpjudge" size="25" />
				</span>
			</div>
			
			
			<div class="colspan">
				X-C Course Designer: <span class="labelTwo">(Must be licensed CD for Preliminary & Above)</span>
			</div>
			<div class="row">
			<span class="label">Name:</span><span class="formX">
			<input name="coursedesign" type="text" id="coursedesign" size="25" />
			</span></div>
			
			
			<div class="colspan">
				Show jumper course designer if different from above
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><input name="jumpcoursedesign" type="text" id="jumpcoursedesign" size="25" />
				</span>
			</div>
			
</div>
			
<!-- **************************************** Part B of the form starts here *********************************************** -->
	
<div class="rowExpand" onclick="expandColl('partb');"> Event Registration Form: Part B </div>
<div id="partb" class="formPart">	

			
	<div class="rowHead">
		Entries:
	</div>
	
			<div class="row">
				<span class="label">Fees:</span>
				<span class="formX"><input type="text" name=entriesfees size="5" /> <strong>$</strong> (Per division)</span>
			</div>
			<div class="row">
				<span class="label">Others:</span>
				<span class="formX"><input type="text" name=entriesother size="10" /> </span>
			</div>
			<div class="colspan">
				Check where applicable:
			</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> Membership required at all levels including BN</div> 
			</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> BNH/BNR competitors who are not members must 
						pay an additional $25 fee with entry.
				</div> 
			</div>
			<div class="row">
				<div style="text-align:left;" class="textBold">Double Entries Accepted:
				</div> 
			</div>
			<div class="row">
				<div class="floatLeft">
				<input type="radio" size="10" name="dblE" value="yes" onClick="switchDiv('dblEntry');" /> Yes
				<input type="radio" size="10" name="dblE" value="no" onClick="showHideRadio('dblE','dblEntry','no');"/> No
				</div> 
			</div>
			<div class="row" id="dblEntry">
				<div class="floatLeft">
				<input type="text" name="dblEntry" size="10" onBlur="threeDigit();" /> <strong>$</strong> (Office Fees)
				</div> 
			</div>
			
			<div class="colspan">
				Payment Check Details: 
			</div>
			<div class="row">
				<span class="label">Draw checks to the order of:</span>
                                    <span class="formX"><input type="text" name="paychkdetails" size="40" />
                                    </span>
			</div>
			<div class="row">
				<div class="floatLeft">
				<input type="radio" size="10" name="r1" value="lpOne" onClick="switchDiv('lpOne'), showHideRadio('r1','lpTwo','lpOne'), showHideRadio('r1','lpOther','lpOne');" /> charge for lost pinny <span class="asterisk">*</span>
				<input type="radio" size="10" name="r1" value="lpTwo" onClick="switchDiv('lpTwo'), showHideRadio('r1','lpOne','lpTwo'), showHideRadio('r1','lpOther','lpTwo');" /> charge for lost pinny <span class="asterisk">**</span>
				<input type="radio" size="10" name="r1" value="lpOther" onClick="switchDiv('lpOther'), showHideRadio('r1','lpTwo','lpOther'), showHideRadio('r1','lpOne','lpOther');" /> Other	
				</div> 
			</div>
			<div class="row" id="lpOne">
				<div class="floatLeft">
				<input type="text" name="lpOne" size="10" /> <strong>$</strong> (<span class="asterisk">*</span> No deposit required)
				</div> 
			</div>
			<div class="row" id="lpTwo">
				<div class="floatLeft">
				<input type="text" name="lpTwo" size="10" /> <strong>$</strong> (<span class="asterisk">**</span> Send deposit with entry)
				</div> 
			</div>
			<div class="row" id="lpOther">
				<div class="floatLeft">
				<input type="text" name="lpOther" size="35" /> <strong>$</strong>
				</div> 
			</div>
			
	<div class="rowHead">
		Refunds:
	</div>	
			
			<div class="colspan">
				<%! String xRule; %>
                            
			   <% 
                              
                              java.util.Vector getAllMapRuleSubRule=new Vector();
                              getAllMapRuleSubRule=(java.util.Vector)session.getAttribute("getAllMapRuleSubRule");
                              //java.util.Vector arenasSizeVect=new Vector();
                              //arenasSizeVect=(java.util.Vector)session.getAttribute("arenaSizeVect");
   
				 Enumeration itratorsRule= (Enumeration)getAllMapRuleSubRule.elements();
//				 Enumeration it = (Enumeration)arenasSizeVect.elements();
                                 
					 while(itratorsRule.hasMoreElements()){
					                                           
                                      String[] s = (String[]) itratorsRule.nextElement();                                    
                                    // String type=s[3];
									   String subRuleName=s[4];
										String mid=s[0];
										String txt_status=s[5];
									   
									 			                                    
                                                           
                                    
                                        
                                         
			    %>
                            
			  
                               
                                   <% if(!(s[3].equals(xRule))){
								   xRule=s[3];
                                   %>
                                   
				 <div class="colspan"><%=xRule%></div>
                                <%}%>
                                <div class="floatLeftTwo">
								<span >
                                <input type="radio" size="10" name="rule" value="<%=mid%>" /><%=subRuleName%>
								<%
										if(!txt_status.equals("0")){
										%>
										<input type="text" size="10" name="txt" />
										<%
										}
								%>
								</span>    
                                     </div>   
				
                                
					   <%  } 
			 %>
</div> 
				
				
			
			<div class="colspan">
				Negative Coggins Test 
			</div>
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r5" value="nctRef" onClick="switchDiv('nctReq'), showHideRadio('r5','nctState','nctRef');" />  Required for all horses.. 
				</div>	
					<div class="row" id="nctReq">
						<div class="floatLeft">
						within <input type="text" name="nctReq" size="6" /> months.
						</div> 
					</div>
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r5"  onclick="switchDiv('nctState'), showHideRadio('r5','nctReq','nctRef');" /> Required within..
				</div> 
					<div class="row" id="nctState">
						<div class="floatLeft">
						<input type="text" name="nctInState" size="6" /> months for in-state and <input type="text" name="nctOutState" size="6" /> months for out-of state.
						</div> 
					</div>
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r5" value="nctReqNot" onClick="showHideRadio('r5','nctState','nctReqNot'), showHideRadio('r5','nctReq','nctReqNot');" /> Not Required.
				</div>
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r5" value="nctReqOth" onClick="showHideRadio('r5','nctState','nctReqOth'), showHideRadio('r5','nctReq','nctReqOth');" /> Others.
				</div>  
</div>
			
<!-- **************************************** Part C of the form starts here *********************************************** -->
	
<div class="rowExpand" onclick="expandColl('partc');">Event Registration Form: Part C </div>
<div id="partc" class="formPart">			
			
	<div class="rowHead">
		Tentative Time Schedule:
	</div>
	
			<div class="rowTwo">
				<span class="labelTwo">Day</span>
				<span class="labelTwo">Phase</span>
				<span class="labelTwo">Time</span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" name="day1" size="10" /> </span>
				<span class="labelTwo"><input type="text" name="day2" size="10" /> </span>
				<span class="labelTwo"><input type="text" name="day3" size="10" /> </span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" name="phase1" size="10" /> </span>
				<span class="labelTwo"><input type="text" name="phase2" size="10" /> </span>
				<span class="labelTwo"><input type="text" name="phase3" size="10" /> </span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" name="time1" size="10" /> </span>
				<span class="labelTwo"><input type="text" name="time2" size="10" /> </span>
				<span class="labelTwo"><input type="text" name="time3" size="10" /> </span>
			</div>
			
				
	<div class="rowHead">
		Awards:
	</div>
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r6" value="awardOne" onclick="switchDiv('ribbons'), showHideRadio('r6','awrdOth','awardOne');" /> Trophy & Ribbons
			</div> 
				<div class="row" id="ribbons">
					<div class="floatLeft">
					 <input type="text" name="ribbons" size="10" /> No. of ribbons per division.
				 </div> 
			</div>
			<div class="rowHeightOne">
			<input type="radio" size="10" name="r6" value="awardTwo" onclick="showHideRadio('r6','ribbons','awardTwo'), showHideRadio('r6','awrdOth','awardTwo');" /> Prize money will be paid by check and available at the secretary's office immediately 
						after the <span style="margin-left:25px;">awards ceremony.</span>
			</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r6" value="awardThree" onclick="switchDiv('awrdOth'), showHideRadio('r6','ribbons','awardThree');" /> Other.
			</div> 	
			<div class="row" id="awrdOth">
					<div class="floatLeft">
					<input type="text" name="awrdOth" size="45" />
					</div> 
			</div>
			
	
	<div class="rowHead">
		Starting Times:
	</div>		
			<div class="row">
				<span class="label">Date Available:</span>
				<span class="formX"><input type="text" name="dateavail" size="25" /></span>
			</div>	
			<div class="colspan">
				Available from:
			</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r7" value="dateOne" onclick="showHideRadio('r7','dateDivOth','dateOne');" /> The secretary.
			</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r7" value="dateTwo" onclick="showHideRadio('r7','dateDivOth','dateTwo');" /> Postcards will be sent. 
			</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r7" value="dateThree" onclick="switchDiv('dateDivOth');" /> Other.
			</div> 	
			<div class="row" id="dateDivOth">
					<div class="floatLeft">
					<input type="text" name="dateDivOth" size="50" />
					</div> 
			</div>
			
	
	<div class="rowHead">
		Stabling / Veterinarian:
	</div>
			<div class="colspan">
				Stabling/Stall Info:
			</div>
			
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r8" value="stableOne"/> Limited stabling.
			</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r8" value="stableTwo" /> Unlimited stabling. 
			</div> 
					
					<div class="row" id="unlStbCom">
					
						<div class="floatLeftThree">
						<input type="radio" size="10" name="r81" value="unlStbOne"  /> Charges per stall per night.
						</div> 
						
								<div class="rowNew" id="perStallOne">
									<div class="floatLeft">
									<input type="text" name="perStallOne" size="8" /> <strong>$</strong>
									</div> 
								</div>
								
						<div class="floatLeftThree">
						<input type="radio" size="10" name="r81" value="unlStbTwo" /> Per stall on a given time & date.
						</div>
						
								<div class="rowNew" id="perStalltwo">
									<div class="floatLeft">
									<span class="floatLeftTwo"><input type="text" name="amount" size="8" /> <strong>$</strong></span>,&nbsp;
									<span class="floatLeftTwo"><input type="text" name="time" size="8" /> <strong>am/pm</strong></span>,&nbsp;
									<span class="floatLeftTwo"><input type="text" name="date" size="8" /> <strong>Date</strong></span>
									</div> 
								</div>	
								
					</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r8" value="stableThree"/> No stabling available.
			</div>
			
			
</div>							

<!-- **************************************** Part D of the form starts here *********************************************** -->
	
<div class="rowExpand" onclick="expandColl('partd');"> Event Registration Form: Part D </div>
<div id="partd" class="formPart">		
			
			<div class="colspan">
				Type of stabling:
			</div> 
			
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r9" value="typStableOne"/> Permanent stalls on ground.
				</div>
								<div class="rowNew" id="qtyOne">
									<div class="floatLeft">
									<input type="text" name="perStallOne" size="8" /> <strong>(Qty)</strong>
									</div> 
								</div>	
								
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r9" value="typStableTwo"/> Temporary stalls on ground.
				</div>
								<div class="rowNew" id="qtyTwo">
									<div class="floatLeft">
										<span class="floatLeftTwo"><input type="text" name="qtyTwo" size="8" /> <strong>(Qty)</strong></span>,&nbsp;
									</div> 
								</div>
								
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r9" value="typStableThree"/> Permanent/temporary stalls with approximate distance from event.
				</div>
								<div class="rowNew" id="qtyThree">
									<div class="floatLeft">
										<span class="floatLeftTwo"><input type="text" name="amount" size="8" /> <strong>(Qty)</strong></span>,&nbsp;
										<span class="floatLeftTwo"><input type="text" name="time" size="8" /> <strong>Miles from event</strong></span>&nbsp;
									</div> 
								</div>
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r9" value="typStableFour"/> Stall doors & initial bedding only provided.
				</div>
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r9" value="typStableFive"/> Feed/Bedding/Hay available for purchase, further info from the secretary.
				</div>
				<div class="floatLeftTwo">
				<input type="radio" size="10" name="r9" value="typStableSix"/> Other type.
				</div>
								<div class="rowNew" id="qtyTwo">
									<div class="floatLeft">
										<span class="floatLeftTwo"><input type="text" name="qtyTwo" size="25" /></span>
									</div> 
								</div>
			
			<div class="colspan">
				Veterinarian Info:
			</div>
					<div class="row">
						<span class="label">Name:</span>
						<span class="formX"><input type="text" name="vetname" size="25" /></span>
					</div>
					<div class="row">
						<span class="label">Veterinarian phone no. at event:</span>
						<span class="formX"><input type="text" name="vetphone" size="25" /></span>
					</div>	
					<div class="row">
						<span class="label">Where Vet. no. will be posted at event:</span>
						<span class="formX"><input type="text" name="vetpost" size="25" /></span>
					</div>
					
	<div class="rowHead">
		Accomodations:
	</div>
	
			<div class="rowTwo">
				<span class="labelTwo">Hotel</span>
				<span class="labelTwo">Phone No.</span>
				<span class="labelTwo">Miles From Event</span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" name="acchotel" size="20" /> </span>
				<span class="labelTwo"><input type="text" name="accphno"size="10" /> </span>
				<span class="labelTwo"><input type="text" name="mileevent" size="10" /> </span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" size="20" /> 
				</span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" size="20" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
			</div>		
			
			
	<div class="rowHead">
		Directions:
	</div>
			<div class="rowTxtArea"><span class="label">
			  <textarea name="textarea" rows="5" cols="25" name="directions" id="directions"></textarea>
			</span></div>
			
</div>

<!-- **************************************** Part E of the form starts here *********************************************** -->
	
<div class="rowExpand" onclick="expandColl('parte');">Event Registration Form: Part E </div>
<div id="parte" class="formPart"> 		
							
	<div class="rowHead">
		Dressage test for horse trials, two-day events & Combined tests:
	</div>
	
			<div class="rowTwo">
				<span class="labelTwo">Advanced Level</span>
				<span class="labelThree">Arena Size</span>
			</div>
			<div class="division">
    	
			<%! String x; %>
                            
			   <% 
                              
                              java.util.Vector dressageHorseTrialsVect=new Vector();
                              dressageHorseTrialsVect=(java.util.Vector)session.getAttribute("dressageHorseTrialsVect");
                              java.util.Vector arenasSizeVect=new Vector();
                              arenasSizeVect=(java.util.Vector)session.getAttribute("arenaSizeVect");
   
				 Enumeration itr= (Enumeration)dressageHorseTrialsVect.elements();
				 Enumeration it = (Enumeration)arenasSizeVect.elements();
                                 
					 while(itr.hasMoreElements() && it.hasMoreElements() ){
					                                           
                                      String[] s = (String[]) itr.nextElement();                                    
                                     String level=s[1];			                                    
                                                           
                                      String arena=it.nextElement().toString();
                                        
                                         
			    %>
                            
			   
                               
                                   <% if(!(s[0].equals(x))){
                                   x=s[0];
                                   System.out.println(x);
                                   %>
                                   
				<div class="colspan"><%=x%></div>
                                <%}%>
								 <div class="rowTwo">
                                <span >
                                <input type="checkbox" size="10" name="subLevelOne" /><%=level%>
								</span>					
                                <span class="labelThree"><%=arena%></span>   
								</div>                        
				
                                
					   <%  } 
			 %>
			 </div>
			 
<div class="spacer">&nbsp;</div>
			
			<div class="row">
				<span class="label">Briefly describe footing:</span>
				<span class="formX"><input type="text" size="45" /></span>
			</div>				

			
	<div class="rowHead">
		Cross-Country:
	</div>
	
			<div class="rowTwo">
				<span class="labelTwo">Division</span>
				<span class="labelTwo">Length</span>
				<span class="labelTwo">Speed</span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" size="20" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" size="20" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
			</div>
			<div class="row">
				<span class="labelTwo"><input type="text" size="20" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
				<span class="labelTwo"><input type="text" size="10" /> </span>
			</div>
			
			<div class="colspan">
				Describe your course(s). Choose from the list below or use your own description. 
			</div>
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r10" value="dateOne" /> Easy, good for first time at this level.
			</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r10" value="dateTwo" /> Average, for horses with some experience at this level. 
			</div> 
			<div class="floatLeftTwo">
			<input type="radio" size="10" name="r10" value="dateThree" /> Difficult, championship caliber course.
			</div> 
			
			<div class="colspan">
				Additional information. 
			</div>
			<div class="rowTxtArea">
				<span class="label">
				<textarea id="additionalInfo" rows="5" cols="25"></textarea>
				</span>			
			</div>
			
	<div class="rowHead">
		Other Information: <span class="labelTwo">(Check applicable boxes. The course-closed date must be stated)</span> 
	</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> Cross-country course officially closed on..</div> 
			</div>
						<div class="row">
							<span id="ccCourse"><input type="text" size="15" /> </span> 
						</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> Entry limit</div> 
			</div>
						<div class="row">
							<span id="ccCourse"><input type="text" size="15" /> </span> 
						</div>
			<div class="row">
			  <div class="floatLeft"><input type="checkbox" size="10" /> 
				Riders limited to horse/level <span class="asterisk">*</span> <strong>(or)</strong> horse/entire event.<span class="asterisk">**</span></div> 
			</div>
						<div class="rowTxtArea">
							<span id="ccCourse"><input type="text" size="15" /> <span class="asterisk">*</span></span>
							<span id="ccCourse"><input type="text" size="15" /> <span class="asterisk">**</span></span> 
						</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> Divisions further divided if entries warrant; state birth date on entry. </div> 
			</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> Dogs must be leashed (and/or)</div> 
			</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> Team of three of four, best three scores to count, all team members at same level. </div> 
			</div>
						<div class="row">
							<span id="ccCourse"><input type="text" size="15" /> <strong>$</strong> per team member. <strong>(or)</strong> </span> 
						</div>
						<div class="row">
							<span id="ccCourse"><input type="text" size="50" /> </span> 
						</div>
			<div class="row">
				<div class="floatLeft"><input type="checkbox" size="10" /> Competitors/Volunteers/Officials Party</div> 
			</div>
						<div class="row">
							<span id="ccCourse"><input type="text" size="35" /> </span> 
						</div>
			<div class="colspan">
				Additional information. 
			</div>
			<div class="rowTxtArea">
				<span class="label">
				<textarea id="additionalInfo" rows="5" cols="25"></textarea>
				</span>			
			</div>
			
	<div class="row">
		<span class="label">&nbsp;</span>
		<span class="formX"><input type="submit" value="Register Now" class="gradBtn" /></span>
	</div>
	
</div>

<form>
</div>
    
<script type="text/javascript">
 
/* ======================= Myform validation methords ==================== */



/* ======================= user defined Myform validation methords ==================== */

function validateMyform(val)
{
//============= Event secaratry id ======

 var eventsecid=document.myform.eventsecid.value;
    
    if(eventsecid=="")
     {    
        alert("Enter the event secretary Id");
        document.myform.eventsecid.focus();
        return false;
     }
     
//========== double Entry ==========

     var dblEntrycal=document.myform.dblEntry.value;
    
     if(!Number(dblEntrycal))
     {    
        alert("Enter a Numeric value");
        document.myform.dbEntry.focus();
        return false;
     }
     if(dblEntrycal>999.99) 
     {
        alert("The Range Should Not Exceed 999.99");
        document.myform.dbEntry.focus();
        return false;   
     }
}

</script>

</body>
</html>
