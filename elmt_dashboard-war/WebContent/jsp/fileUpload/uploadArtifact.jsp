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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Business Service Center</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="../../javascripts/basic.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
 <style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
 </style>
 
 <style type="text/css">
#progressBar {position:static; 
              width:400px; 
              height:35px; 
              visibility:hidden;
              background-color:#99ccff; 
              padding:20px;
              border-width:2px;
              border-left-color:#9999ff; 
              border-top-color:#9999ff;
              border-right-color:#666666; 
              border-bottom-color:#666666; 
              border-style:solid;
             }
#progressBarMsg {position:static;
                 left:10px; 
                 top:10px; 
                 font:18px Verdana, Helvetica, sans-serif bold
                }
#sliderWrapper {position:static; 
                left:10px; 
                top:40px; 
                width:417px; 
                height:15px;
                background-color:#ffffff; 
                border:1px solid #000000; 
                text-align:center;
                font-size:12px
               }
#slider{position:static; 
        left:0px; 
        top:0px; 
        width:0px; 
        height:px;
        clip:rect(0px 0px 15px 0px);
        background-color:#666699; 
        text-align:center; 
        color:#ffffff; 
        font-size:0px
       }
</style>
<script type="text/javascript">
var isCSS, isW3C, isIE4, isNN4, isIE6CSS;
// Initialize upon load to let all browsers establish content objects
function initDHTMLAPI() {
    if (document.images) {
        isCSS = (document.body && document.body.style) ? true : false;
        isW3C = (isCSS && document.getElementById) ? true : false;
        isIE4 = (isCSS && document.all) ? true : false;
        isNN4 = (document.layers) ? true : false;
        isIE6CSS = (document.compatMode && document.compatMode.indexOf("CSS1") >= 0) ? true : false;
    }
}
// Set event handler to initialize API
window.onload = initDHTMLAPI;

// Seek nested NN4 layer from string name
function seekLayer(doc, name) {
    var theObj;
    for (var i = 0; i < doc.layers.length; i++) {
        if (doc.layers[i].name == name) {
            theObj = doc.layers[i];
            break;
        }
        // dive into nested layers if necessary
        if (doc.layers[i].document.layers.length > 0) {
            theObj = seekLayer(document.layers[i].document, name);
        }
    }
    return theObj;
}

// Convert object name string or object reference
// into a valid element object reference
function getRawObject(obj) {
    var theObj;
    if (typeof obj == "string") {
        if (isW3C) {
            theObj = document.getElementById(obj);
        } else if (isIE4) {
            theObj = document.all(obj);
        } else if (isNN4) {
            theObj = seekLayer(document, obj);
        }
    } else {
        // pass through object reference
        theObj = obj;
    }
    return theObj;
}

// Convert object name string or object reference
// into a valid style (or NN4 layer) reference
function getObject(obj) {
    var theObj = getRawObject(obj);
    if (theObj && isCSS) {
        theObj = theObj.style;
    }
    return theObj;
}

// Position an object at a specific pixel coordinate
function shiftTo(obj, x, y) {
    var theObj = getObject(obj);
    if (theObj) {
        if (isCSS) {
            // equalize incorrect numeric value type
            var units = (typeof theObj.left == "string") ? "px" : 0 
            theObj.left = x + units;
            theObj.top = y + units;
        } else if (isNN4) {
            theObj.moveTo(x,y)
        }
    }
}

// Move an object by x and/or y pixels
function shiftBy(obj, deltaX, deltaY) {
    var theObj = getObject(obj);
    if (theObj) {
        if (isCSS) {
            // equalize incorrect numeric value type
            var units = (typeof theObj.left == "string") ? "px" : 0 
            theObj.left = getObjectLeft(obj) + deltaX + units;
            theObj.top = getObjectTop(obj) + deltaY + units;
        } else if (isNN4) {
            theObj.moveBy(deltaX, deltaY);
        }
    }
}

// Set the z-order of an object
function setZIndex(obj, zOrder) {
    var theObj = getObject(obj);
    if (theObj) {
        theObj.zIndex = zOrder;
    }
}

// Set the background color of an object
function setBGColor(obj, color) {
    var theObj = getObject(obj);
    if (theObj) {
        if (isNN4) {
            theObj.bgColor = color;
        } else if (isCSS) {
            theObj.backgroundColor = color;
        }
    }
}

// Set the visibility of an object to visible
function show(obj) {
    var theObj = getObject(obj);
    if (theObj) {
        theObj.visibility = "visible";
    }
}

// Set the visibility of an object to hidden
function hide(obj) {
    var theObj = getObject(obj);
    if (theObj) {
        theObj.visibility = "hidden";
    }
}

// Retrieve the x coordinate of a positionable object
function getObjectLeft(obj)  {
    var elem = getRawObject(obj);
    var result = 0;
    if (document.defaultView) {
        var style = document.defaultView;
        var cssDecl = style.getComputedStyle(elem, "");
        result = cssDecl.getPropertyValue("left");
    } else if (elem.currentStyle) {
        result = elem.currentStyle.left;
    } else if (elem.style) {
        result = elem.style.left;
    } else if (isNN4) {
        result = elem.left;
    }
    return parseInt(result);
}

// Retrieve the y coordinate of a positionable object
function getObjectTop(obj)  {
    var elem = getRawObject(obj);
    var result = 0;
    if (document.defaultView) {
        var style = document.defaultView;
        var cssDecl = style.getComputedStyle(elem, "");
        result = cssDecl.getPropertyValue("top");
    } else if (elem.currentStyle) {
        result = elem.currentStyle.top;
    } else if (elem.style) {
        result = elem.style.top;
    } else if (isNN4) {
        result = elem.top;
    }
    return parseInt(result);
}

// Retrieve the rendered width of an element
function getObjectWidth(obj)  {
    var elem = getRawObject(obj);
    var result = 0;
    if (elem.offsetWidth) {
        result = elem.offsetWidth;
    } else if (elem.clip && elem.clip.width) {
        result = elem.clip.width;
    } else if (elem.style && elem.style.pixelWidth) {
        result = elem.style.pixelWidth;
    }
    return parseInt(result);
}

// Retrieve the rendered height of an element
function getObjectHeight(obj)  {
    var elem = getRawObject(obj);
    var result = 0;
    if (elem.offsetHeight) {
        result = elem.offsetHeight;
    } else if (elem.clip && elem.clip.height) {
        result = elem.clip.height;
    } else if (elem.style && elem.style.pixelHeight) {
        result = elem.style.pixelHeight;
    }
    return parseInt(result);
}

// Return the available content width space in browser window
function getInsideWindowWidth() {
    if (window.innerWidth) {
        return window.innerWidth;
    } else if (isIE6CSS) {
        // measure the html element's clientWidth
        return document.body.parentElement.clientWidth
    } else if (document.body && document.body.clientWidth) {
        return document.body.clientWidth;
    }
    return 0;
}

// Return the available content height space in browser window
function getInsideWindowHeight() {
    if (window.innerHeight) {
        return window.innerHeight;
    } else if (isIE6CSS) {
        // measure the html element's clientHeight
        return document.body.parentElement.clientHeight
    } else if (document.body && document.body.clientHeight) {
        return document.body.clientHeight;
    }
    return 0;
}


</script>
<script type="text/javascript">
// Center a positionable element whose name is passed as 
// a parameter in the current window/frame, and show it
function centerOnWindow(theFile) {
    // 'obj' is the positionable object
    var obj = getRawObject(theFile);
    // window scroll factors
    var scrollX = 0, scrollY = 0;
    if (document.body && typeof document.body.scrollTop != "undefined") {
        scrollX += document.body.scrollLeft;
        scrollY += document.body.scrollTop;
        if (document.body.parentNode && 
            typeof document.body.parentNode.scrollTop != "undefined") {
            scrollX += document.body.parentNode.scrollLeft;
            scrollY += document.body.parentNode.scrollTop
        }
    } else if (typeof window.pageXOffset != "undefined") {
        scrollX += window.pageXOffset;
        scrollY += window.pageYOffset;
    }
    var x = Math.round((getInsideWindowWidth()/2) - (getObjectWidth(obj)/2)) + scrollX;
    var y = Math.round((getInsideWindowHeight()/2) -  (getObjectHeight(obj)/2)) + scrollY;
    shiftTo(obj, x, y);
    show(obj);
}

function initProgressBar() {
  // create quirks object whose default (CSS-compatible) values
    // are zero; pertinent values for quirks mode filled in later  
  if (navigator.appName == "Microsoft Internet Explorer" && 
        navigator.userAgent.indexOf("Win") != -1 && 
        (typeof document.compatMode == "undefined" || 
        document.compatMode == "BackCompat")) {
        document.getElementById("progressBar").style.height = "81px";
        document.getElementById("progressBar").style.width = "444px";
        document.getElementById("sliderWrapper").style.fontSize = "xx-small";
        document.getElementById("slider").style.fontSize = "xx-small";
        document.getElementById("slider").style.height = "13px";
        document.getElementById("slider").style.width = "415px";
    }
}

function showProgressBar() {
    centerOnWindow("progressBar");
}

function calcProgress(current, total) {
    if (current <= total) {
        var factor = current/total;
        var pct = Math.ceil(factor * 100);
        document.getElementById("sliderWrapper").firstChild.nodeValue = pct + "%";
        document.getElementById("slider").firstChild.nodeValue = pct + "%";
        document.getElementById("slider").style.clip = "rect(0px " + parseInt(factor * 417) + "px 16px 0px)";
    }
}

function hideProgressBar() {
    hide("progressBar");
    calcProgress(0, 0);
}

// Test bench to see progress bar in action at random intervals
var loopObject = {start:0, end:10, current:0, interval:null};

function runit() {
    if (loopObject.current <= loopObject.end) {
        calcProgress(loopObject.current, loopObject.end);
        loopObject.current += Math.random();
        loopObject.interval = setTimeout("runit()", 700);
    } else {
        calcProgress(loopObject.end, loopObject.end);
        loopObject.current = 0;
        loopObject.interval = null;
        setTimeout("hideProgressBar()", 500);
    }
}

function test() {
    showProgressBar();
    runit();
	location.reload();
}

</script>
</head>

<body onload="initDHTMLAPI(); initProgressBar()">
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
		<table width="760" border="0" cellpadding="0" cellspacing="0" id="infoBarTab">

			  <tr>
				
				<td class="infoTopPad" width="2%">				</td>
				<td class="infoTopPad" width="90%" height="25"><span class="styleBoldOne">Strategic View &gt; Artifact Management  </span></td>
				<td class="infoTopPad" width="8%" align="center">
	</td>
				
			  </tr>
	  </table>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" id="fullHeight" class="menuTableBg">
				  <tr>
					<td class="alignTop"><br />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="leftMenuTab">
						  <tr>
							<td class="menuTabLft"></td>
							<td class="menuTabTwoBg">
								
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">

                                  <tr>
                                    <td height="20" class="alignLeft"><span class="textBold">&nbsp;</span> </td>
                                  </tr>
								  
									<tr>
										<td height="25">
										
										<!-- code for side bar starts here -->
	                                    <%--  <%@ include file = "include/#" --%>
	                                    <!-- code for side bar starts here -->
		 	
									  </td>
								  </tr>
									 
									  															  								  
                                </table>

							</td>
							<td class="menuTabRght"></td>
						  </tr>
						  <tr>
							<td class="menuTabLftBotCrnr"></td>
							<td class="menuTabBot"></td>
							<td class="menuTabRghtBotCrnr"></td>
						  </tr>
						</table>

						<br />
					

					</td>
				  </tr>
				</table>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						Artifact Management : 
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					
					 <span class="tabHead">Upload Artifact</span> 
					
					<br />					</td>
				  </tr>
				  <tr>
					<td>
					
						<form method="post" action="../../uploadArifactInCVS.do" enctype="multipart/form-data">
														  
                                                                                                          
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="3" class="tblRowHead"> Please Click here to Upload the Edited Artifact : </td>
						  </tr>
                                                    <tr> 
							<td class="tableLeft"> CVS User Name</td>
							<td class="tableRight">
							
							<input name="userName" id="theFile" type="input" class="textboxOne"/></td>
			<td>
						    </td>
						  </tr>
                                                    <tr> 
							<td class="tableLeft">CVS Password</td>
							<td class="tableRight">
							
							<input name="password" id="theFile" type="password" class="textboxOne"/></td>
			<td>
						    </td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Select Artifact:</td>
							<td class="tableRight">
							
							<input name="theFile" id="theFile" type="file" class="textboxOne"/></td>
			<td>
						    </td>
						  </tr>
						  <tr> 
							<td colspan="3" class="alignCenter"><button onclick="test()"  class="gradBtn" >Upload</button>
<div align="center" id="progressBar">
<div id="progressBarMsg">Commiting...</div>
<div id="sliderWrapper">0%
  <div id="slider">0%</div>
</div>
</div>		
						    </td>
						  </tr>
						  <tr> 
							<td colspan="3" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
						</table>
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
			<%@ include file ="../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>


 

</body>
</html>
