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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
 <script language="javascript">

//window.onload=call();
 
  function popitup(url) {
	
		newwindow=window.open(url,'name','height=170,width=350,scrollbars=no,resizable=no,top=300,left=500,toolbar=yes,menubar=no,dialog=yes');
                        if (window.focus) {newwindow.focus()}
                        return false;
	}


  function callAdd(lcExist,id,name,version,govStat){
   // boolean val;
	// val= validate(lcExist);
	//  if (val == true){
		strURL = "./SysMgmt.do?process=busineessProce&id="+id+"&bpName="+name+"&bpVersion="+version+"&txtName=Add BPEL"+"&govStat="+govStat;
			window.location.href = strURL;
	//  }else
  //           return false;					
	 }

	 
  function callEndGov(fwd,artiId,govStat){
	  //alert("df");

		 
		strURL = "./SysMgmt.do?process=callEndGov&fwd="+fwd+"&name=bpm"+"&artifactId="+artiId+"&govstatus="+govStat;
				window.location.href = strURL;
					
		       }
  function validate(lcExist)
  {
  	if(lcExist.equals("false")){
  		alert("There is no lifecycle associated with Business Process artifact. Please create the lifecycle first and then start the Governance");  		
  		return false;
  		}
  	return true;
  }


	 </script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
 
 
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
	  
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
		<%@ include file = "../../include/infobar.jsp"  %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
    <tr>
    <td>
		<!-- INFO BAR STARTS HERE -->
		<h3>View Business Process Artifact List </h3>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  
		  <%
		  
		  
		  String gov=(String)request.getAttribute("stopGov");
		  String id=(String)request.getAttribute("id");
		  String ErrorMsg=(String)request.getAttribute("ErrorMsg");
		  
		  if(ErrorMsg!=null){%>
		  
		  <td><font color="#FF0000"><strong><%=ErrorMsg%></strong></font></td>
	  
		  <%
		  }else{	  
		  ErrorMsg="";
		  }
		  if(id==null)id="";
		  if(gov==null)gov="";
		  System.out.println("gov==="+gov+"==id=="+id);
		  %>
   <tr>
   <td><table width="100%" border="1" align="center" cellpadding="" cellspacing="" id="centerTab">
   <tr>
    
 <form name="frmArti" action="SysMgmt.do">
 <input type="hidden" name="process" value="callArtifactBPList"/>
 
   
   <input type="hidden" name="reqname" value=""/>
 
			<td>   <table  border="1" cellpadding="0" cellspacing="0" width="100%" align="left" > <td colspan="7" class="tblMainHead" >
	</td>
	 <tr>

     
                       <td width="120" height="27" class="tblRowHeadTwo">Name</td>
                       <td width="30" height="27" class="tblRowHeadTwo">Version</td>
		               <td width="30" height="27" class="tblRowHeadTwo">Consumable</td>
                       <td width="50" height="27" class="tblRowHeadTwo">Owner</td>
					   <td width="50" height="27" class="tblRowHeadTwo">Domain</td>
					   <td width="150" height="27" class="tblRowHeadTwo">Governance</td>
					 
		     
            </tr>
            
              <%
              Boolean lcExist=(Boolean)request.getAttribute("cycleExist");
              ArrayList list=(ArrayList)request.getAttribute("list");
    //System.out.println  ("artifactData"+artifactData.size());   
    
                                                 
   if (list != null && list.size() != 0) {  
                                                            
   Iterator iter = list.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	 
                                                                	
                                                                	String [] artiType=(String[])iter.next();
                                                                	//String id=artiType[0];
                                                                	String artId=artiType[0];
                                                                	String name=artiType[1]; 
                                                              System.out.println("Inside jsp BP name"+name);
                                                                	String version=artiType[2];
																	String govStat=artiType[3];
																	
                                                                	if(version==null)version="";
                                                                	

                                                %>
                                                 <tr><td align="left"><%=name%></td><td align="left"><%=version%></td><td align="left"></td><td></td><td></td>
												<td> 
												<%if(govStat.equalsIgnoreCase("Start")){ %>
												 <input type="button" value="Start Governance" onclick="callAdd('<%=lcExist%>','<%=artId%>','<%=name%>','<%=version%>','<%=govStat%>')"/>
												 <%}else if(govStat.equalsIgnoreCase("End")){ %>
												 <input type="button" value="End Governance" onclick="callEndGov('callArtiList','<%=artId%>','<%=govStat%>')"/>
												 <%}%>
												 </td>
												
                                                </tr>
                                                <%
                                                 
                                               
                                                                   
                                                                }
   }
   else{
	   %>
	   <tr><td colspan="6">No DATA </td></tr>
 <%   }
    
                                                %>

				<!-- CONTENTS START HERE -->
				</form>
</table>
</td>
</tr>
</table>
			<!-- CONTENTS END HERE -->			</td>
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
