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
<%@ page import="com.hlcmro.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><bean:message key="meetings.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmEvenOrgtReg.js" type="text/javascript"></script>
<script src="javascripts/frmEventOrganizerReg.js" type="text/javascript"></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/calendar3.js" type="text/javascript"></script>
<script type="text/javascript" src="javascripts/tiny_mce/tiny_mce_dev.js"></script>
<script type="text/javascript" src="javascripts/tiny_mce/classes/util/UnitTester.js"></script>

<%String indieventID=(String)request.getAttribute("indieventID");%>

<script type="text/javascript">
function focus_login()
{
	document.myform.eventtitle.focus();
}	
function forFocus()
  {
    setTimeout('focus_login()',4000);
  }	
   
</script>


<script type="text/javascript">
// ***************For javascript text editor*******************

var editor;
var unitTester = new tinymce.util.UnitTester('log', {
	debug : true,
	log_skipped : true
});

(function() {
	unitTester.add('commands', {
		html4TextStyles : function() {
			var t = this, c;

			editor.settings.inline_styles = false;
			editor.settings.convert_fonts_to_spans = false;



			editor.settings.inline_styles = true;
		},

		xhtmlTextStyles : function() {
			var t = this, c;


			editor.execCommand('SelectAll');
			editor.execCommand('FontName',false,'Arial');

		},

		alignInline : function() {
			var t = this;

			editor.settings.inline_styles = true;


		},

		blockquote : function() {
			var t = this;

		},

		blocks : function() {
			var t = this;


			try {
				//editor.execCommand('FormatBlock', false, 'div');
			} catch (ex) {
				//t.log('Failed: ' + ex.message);
			}

		},

		styles : function() {
			var t = this;


		},

		link : function() {
			var t = this;


		},

		unlink : function() {
			var t = this;


		},

		subscriptSuperscript : function() {
			var t = this;

		},

		insertHR : function() {
			var t = this, v;


		},

		indentOutdent : function() {
			var t = this;


		},

		removeFormat : function() {
			var t = this;


		},

		customCommands : function() {
			var t = this;


		}
	});

	unitTester.add('internals', {
		forcedBlocks : function() {
			var t = this;

		},

		explorerBugs : function() {
			var t = this;


		}
	});

	unitTester.add('urls', {
		relativeURLs : function() {
			var t = this;


		},

		absoluteURLs : function() {
			var t = this;

		}
	} );

	unitTester.add('editor_methods', {
		getParam : function() {
			var t = this;


		}
	});
})();

tinyMCE.init({


mode : "textareas",

theme : "advanced",

theme_advanced_buttons1 :

"undo,redo,|,bold,italic,underline,|,justifyleft,justifycenter,justifyright,justifyfull,|,bullist,numlist,|,link,unlink,",

theme_advanced_buttons2 : "",

theme_advanced_buttons3 : "",

theme_advanced_toolbar_location : "top", theme_advanced_toolbar_align : "left"

 
});




 function reportLink(){	
new_window=window.open('http://dashboard.useventing.com/resources/scripts/omnibus_preview/index.php?id=<%=indieventID%>') 
	}	

</script>
</head>
<%if(indieventID!=null){%>
<body onload="initCondition();focus_login();forFocus();reportLink();">
<%}else{%>
<body onload="initCondition();focus_login();forFocus();">
<%}%>

<%! 
String nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>

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

			<div class="row">
				<strong>Event Organizer: <span class="styleBoldTwo">Update Omnibus Listing</span></strong>			</div>
				
			<div id="commonBG">
	<p align="justify">Details from the most recent Omnibus listing for this event are shown in sections similar to the printed Omnibus. The text in each section can be edited by typing directly into it, similar to using a word processor. Use the zoom function of your internet browser (hold down Ctrl key and press + or - ) to enlarge the text to assist with viewing and editing.</p>
<p align="justify">To see how changes affect the actual Omnibus listing, click on the &quot;<strong>Update/View Omnibus</strong>&quot; button at the bottom of this page. A new browser window will open up to show the full listing including changes. Close the new window to continue editing. When updating is complete, click &quot;<strong>Save changes/exit</strong>&quot; button to return to the Events Listing page.</p>
			</div>
			<%
			String tempMsg=(String)request.getAttribute("msg");
		    System.out.println("tempMsg"+tempMsg);
		   	
			%>
<form name="myform" id="myform" class="formcss" method="post" action="./OrganizerHLCEventReg.do" onsubmit="return check();"  >
<input type="hidden" name="process" value="Update" />
<input type="hidden" name="msg" id="msg" value="<%=tempMsg%>" />

<%
HLCEventDetailsVO objEventDet=new HLCEventDetailsVO();
			ArrayList eventDet = new ArrayList();
			eventDet=(ArrayList)request.getAttribute("eventDetails");
			
			
			
	if(eventDet != null && eventDet.size() != 0)
			{
				System.out.print(" eventDet.size() :" +eventDet.size());
				
				objEventDet=(HLCEventDetailsVO)eventDet.get(0);
										
			}		
		
%>



<!-- **************************************** Part A of the form starts here *********************************************** -->

<!--div class="rowExpand" onclick="expandColl('parta');"> 
  USEA Event Registration Form: Part A 
</div-->
<div id="parta" class="formPart">
		<% 
			String eventId=objEventDet.getEventId();
        //   System.out.println("objEventDet.getEventId() in jsp :"+objEventDet.getEventId());
			
			HLCEventRequestVO objEventReqVO = new HLCEventRequestVO();
			objEventReqVO = (HLCEventRequestVO)request.getAttribute("EVENT_REQUEST_VO");		
				String event_id = "";
				String organizer_id = "";
				String event_title = "";			
				String competition_location = "";
				String competition_city = "";
				String competition_state = "";
				String competition_country = "";
				String competition_zip = "";
				String championship_area = "";
				String status_id = "";
				String issNm = "";
			 ArrayList mappingTypeIds = new ArrayList();
			 
			 String eventSubTypes = "";
			 java.util.Date eventBeginDate=null;
			 java.util.Date eventEndDate=null;
			 
			 if(objEventReqVO!= null ){
			  event_id = objEventReqVO.getEvent_id();
              organizer_id = objEventReqVO.getOrganizer_id();
              event_title = objEventReqVO.getEvent_title();
              eventBeginDate = objEventReqVO.getEveBegDate();		 

			
              eventEndDate = objEventReqVO.getEveEndDate();	
              competition_location = objEventReqVO.getCompetition_location();
              competition_city = objEventReqVO.getCompetition_city();
              competition_state = objEventReqVO.getCompetition_state();
              competition_country = objEventReqVO.getCompetition_country();
              competition_zip = objEventReqVO.getCompetition_zip();
              championship_area = objEventReqVO.getChampionship_area();
              status_id = objEventReqVO.getStatus_id();
			  issNm = objEventReqVO.getIssueName();
			  mappingTypeIds = objEventReqVO.getMaping_type_id();
			  	if(mappingTypeIds!= null && mappingTypeIds.size()!=0){	  
                	Iterator itrMapTypeIds = mappingTypeIds.iterator();
                
	                while(itrMapTypeIds.hasNext()){
                    String mappingTypeId = (String)itrMapTypeIds.next();
                    eventSubTypes = mappingTypeId+"#"+eventSubTypes;
    	            }
				}
			}
			String areaId = "";

			String areaStr = "";
			String[] areaDetails = (String []) request.getAttribute("AREA_DETAILS");
			if(areaDetails != null){
			 areaId = areaDetails[0];
			 areaStr = areaDetails[1];
			 }
			 
		ArrayList omniCommts=(ArrayList) request.getAttribute("omniComments");	 
			
		String secretComt = "";
		String accomCommt= "";
		String directCommt = "";
		String dressCommt= "";
		String tentTimeCommt = "";
		String offCommt= "";
		String entriesCommt = "";
		String awardsCommt= "";
		String stTimeCommt = "";
		String stabVetCommt= "";
		String crsCounCommt = "";
		String othrCommt = "";
		String eveTitCommt = "";
		String eveFeeDCommt = "";
		String eveMgtCommt = "";
		String eveWebSit ="";
			 
		if(omniCommts!=null && omniCommts.size()!=0){
		Iterator itrs = omniCommts.iterator();
                
		while(itrs.hasNext()){
		String[] commts = (String[])itrs.next();
		String orgId=commts[0];
		 secretComt = commts[1];
		 accomCommt=commts[2];
		 directCommt =commts[3];
		 dressCommt=commts[4];
		 tentTimeCommt =commts[5];
		 offCommt=commts[6];
		 entriesCommt = commts[7];
		 awardsCommt=commts[8];
		 stTimeCommt = commts[9];
		 stabVetCommt=commts[10];
		 crsCounCommt =commts[11];
		 othrCommt =commts[12];
		 eveTitCommt =commts[13];
		 eveFeeDCommt =commts[14];
		 eveMgtCommt =commts[15];
		 eveWebSit =commts[16];
		
    	            }
				
		}	 
			 
			 
				%>
             <input type="hidden" name="eventId" value="<%=event_id%>" />		
			
		<div class="row">
				<span>Event Title: </span>
				
	            <input type="text" class="textboxOne" size="40" name="eventtitle" value="<%=objEventReqVO.getEvent_title()%>"/> <span class="asterisk">*</span>	  </div>
				
				
		<div>
								<span class="label">Event Title Comments:</span>
						<span><textarea id="eveTitCommt" class="textboxOne" name="eveTitCommt"><%=nullCheck(eveTitCommt)%></textarea></span>
			    </div>			
	
	<div class="row">
	<span>Event Website:</span>
								
		<span><input type="text" name="txtEveWeb" id="txtEveWeb" class="textboxOne" value="<%=nullCheck(eveWebSit)%>" size="40" /></span> (e.g. www.myeventwebsite.com)
			    </div>			
	
	
			
	<div class="rowHead">
		Event Types and Levels: 
	</div>
	
						<%
							Vector eventTypeDetails = (Vector)request.getAttribute("eventTypeDetails");
							HashMap eventTypeMap = (HashMap)request.getAttribute("eventTypeMap");
							int levelCount =0;
							if(eventTypeDetails!=null && eventTypeDetails.size()!=0){
							Enumeration it = eventTypeDetails.elements();
							while(it.hasMoreElements()){
							String[] eventDet1 =(String[])it.nextElement();
							String eventTypeId = eventDet1[0];
							String eventTypeName= eventDet1[1];
							String tmpVal="";
							ArrayList subLevelMap = (ArrayList)eventTypeMap.get(eventTypeId);
							Iterator itrSub = subLevelMap.iterator();
							while(itrSub.hasNext()){
							String[] subEntry = (String[])itrSub.next();
							String mapId = subEntry[0];
							String levelId = subEntry[1];
							String levelCode = subEntry[2];
							String levelName = subEntry[3]; 
							levelCount = levelCount + 1;
							String cbxName = "eventLevels"+levelCount;
							boolean chkStatus = false;
							if(eventSubTypes.contains(mapId)){
							if(tmpVal==""){
							tmpVal = levelCode;	
							}else{	
							tmpVal = tmpVal + ","+ levelCode;	
							}														
						%>
						
						<%}
						}%>
              			<div>
						<%if(tmpVal!=""){%> 
						<span class="label"><strong><%=eventTypeName%></strong></span>
						
						<span class="formX"><%=tmpVal%></span>
						<%}%> 
						<%}
						}  %>		
	
	<div class="rowHead">
		Secretary:
	</div>		
			
			<div>
								<span class="label">Secretary Comments:</span>
						<span><textarea id="textEditorJS" class="textboxOne" name="textEditorJS"><%=nullCheck(secretComt)%></textarea></span>
						  </div>
			<div class="rowHead">
		Entries:
	</div>						
		<div>
								<span class="label">Entries Comments:</span>
			<span><textarea id="entriesCmmt" class="textboxOne" name="entriesCmmt"><%=nullCheck(entriesCommt)%></textarea></span>
						  </div>						
							
			
	<div class="rowHead">
		Official:
	</div>		
																																				
	<div>Official <span class="label"> Comments:</span>
										<span >
					                    <textarea id="offCmmt" class="textboxOne" name="offCmmt"><%=nullCheck(offCommt)%></textarea></span>
						  </div>						
																			
													

	</div>
	
<div class="rowHead">
		Event Management:
	</div>			
	
	<div>Event Management <span class="label"> Comments:</span>
		<span>
		<textarea id="eveMgtCmmt" class="textboxOne" name="eveMgtCmmt"><%=nullCheck(eveMgtCommt)%></textarea></span>
		</div>						
																														
	</div>
	
	
	
<!-- **************************************** Part B of the form starts here *********************************************** -->

<!--div class="rowExpand" onclick="expandColl('partb');"> USEA Event Registration Form: Part B </div-->
<div class="rowHead">
		Entry Fee:
	</div>		
<div id="partb" class="formPart">

		<div>
			<span class="label">Event Fees Directory Comments:</span>
<span><textarea id="eveFeesDirtCommt" class="textboxOne" name="eveFeesDirtCommt"><%=nullCheck(eveFeeDCommt)%></textarea></span>
			    </div>			
			
	
<!-- **************************************** Part C of the form starts here *********************************************** -->

<!--div class="rowExpand" onclick="expandColl('partc');"> USEA Event Registration Form: Part C </div-->
<div id="partc" class="formPart">

	<div class="rowHead">
		Tentative Time Schedule: </div>

		
			
			<div >
				<span class="label">Tentative Time Schedule Comments:</span>
					<span><textarea id="timeComment" class="textboxOne" name="timeComment" ><%=nullCheck(tentTimeCommt)%></textarea></span>
			    </div>
			
	<div class="rowHead">
		Awards: </div>
		
<div >
								<span class="label">Award Comments:</span>
										<span ><textarea id="awardComment" class="textboxOne" name="awardComment"><%=nullCheck(awardsCommt)%></textarea></span>
			    </div>

	<div class="rowHead">
		Starting Times:
	</div>
		
<div >
								<span class="label">Start Time Comments:</span>
										<span ><textarea id="stComment" class="textboxOne" name="stComment" ><%=nullCheck(stTimeCommt)%></textarea></span>
			    </div>

	<div class="rowHead">
		Stabling / Veterinarian:
	</div>
			
<!-- **************************************** Part D of the form starts here *********************************************** -->

<!--div class="rowExpand" onclick="expandColl('partd');"> USEA Event Registration Form: Part D </div-->
<div id="partd" class="formPart">



		
	<div >
								<span class="label">Stabling / Veterinarian Comments:</span>
										<span >
					                    <textarea id="stbVetComment" class="textboxOne" name="stbVetComment" ><%=nullCheck(stabVetCommt)%></textarea></span>
				  </div>				
					
					
					

	<div class="rowHead">
		Accomodations:
	</div>
	
	<div class="rowTxtArea">Accomodation comments:<span class="label">
			  <textarea id="accomodation" class="textboxOne" name="accomodation"><%=nullCheck(accomCommt)%></textarea>
			</span></div>


		
	<div class="rowHead">
		Direction:
	</div>
			<div class="rowTxtArea">Direction Comments:<span class="label">
			  <textarea name="textarea" rows="5" cols="25" id="directions"><%=nullCheck(directCommt)%></textarea>
			</span></div>

</div>

<!-- **************************************** Part E of the form starts here *********************************************** -->

<!--div class="rowExpand" onclick="expandColl('parte');"> USEA Event Registration Form: Part E </div-->
<div id="parte" class="formPart">

	
	
	<div class="spacer">&nbsp;</div>
	
	<div >
								<span class="label">Dressage Comments:</span>
										<span><textarea id="dressageComment" class="textboxOne" name="dressageComment"><%=nullCheck(dressCommt)%></textarea></span>
				  </div>
    	
			
			<div class="rowHead">
				Cross-Country information:
			</div>
			<div>
				<span class="label">Cross Country Comments:</span>
				<span>
				<textarea id="crossContCommts" name="crossContCommts" class="textboxOne"><%=nullCheck(crsCounCommt)%></textarea>
				</span>
			</div>

	<div class="rowHead">
		Other Information: 
	</div>
			
			<div>		
				<span class="label">Other Comments:</span>
				<span>
				<textarea id="othrCommts" name="othrCommts" class="textboxOne"><%=nullCheck(othrCommt)%></textarea>
				</span>
			</div>

	<div class="row" align="center">
		<span>&nbsp;</span>
		<span><input type="submit" value="Update/View Omnibus Listing" name="buttName" id="buttName" class="gradBtn"/>
		<input type="submit" value="Save changes/exit" name="buttName" id="buttName" class="gradBtn"/>	
		</span>

	</div>
	

</div>
 <input type="hidden" name="eventBeginDate" value="<%=eventBeginDate%>" />
</form>
</div>


 <script language="javascript">
	

	/*var cal5= new calendar3(document.forms['myform'].elements['date1']);
	cal5.year_scroll = true;
	cal5.time_comp = false;
	
	var cal6= new calendar3(document.forms['myform'].elements['todate']);
	cal6.year_scroll = true;
	cal6.time_comp = false;*/

	
</script>
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
