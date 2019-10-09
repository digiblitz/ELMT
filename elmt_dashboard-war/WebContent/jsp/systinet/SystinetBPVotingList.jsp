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
 
  function approved() {
 //alert("approved");
	 
var chkBoxCnt = document.artifactlist.chk.length;
 
		 if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
			   {
			  // alert("approved 2");
					for(var i=0;i<chkBoxCnt;i++)
					{
//alert("approved 3");
						 	if(document.artifactlist.chk[i].checked==true)
						 {
						 //alert("approved 4");
								var StageValue= document.artifactlist.StageValue[i].value;
								var StageName= document.artifactlist.StageName[i].value;
								var ArtifactId= document.artifactlist.ArtifactId[i].value;
								var ProcessId= document.artifactlist.ProcessId[i].value;
								//alert("StageName"+StageName);
								var stageNo= document.artifactlist.StageNo[i].value;
								var ReqquestId= document.artifactlist.ReqquestId[i].value;
								//alert("StageName"+StageName);

								 strURL = "./SysMgmt.do?process=BPapproved&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo+"&RequestID="+ReqquestId;
                                 window.location.href = strURL;

						 }
						
					}
			   }


			   else{
		//	 alert("approved 5");
			   	if(document.artifactlist.chk.checked==true)
			{
//alert("approved 6");
var StageValue= document.artifactlist.StageValue.value;

								var StageName= document.artifactlist.StageName.value;
								var ArtifactId= document.artifactlist.ArtifactId.value;
								var ProcessId= document.artifactlist.ProcessId.value;
								var stageNo= document.artifactlist.StageNo.value;
                            var ReqquestId= document.artifactlist.ReqquestId.value;
								 strURL = "./SysMgmt.do?process=BPapproved&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo+"&RequestID="+ReqquestId;
                                 window.location.href = strURL;



			}
			}




	 
		
	}
 
  function denied() {
		
var chkBoxCnt = document.artifactlist.chk.length;
		 if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
			   {
					for(var i=0;i<chkBoxCnt;i++)
					{

						 	if(document.artifactlist.chk[i].checked==true)
						 {
								
var StageValue= document.artifactlist.StageValue[i].value;
								var StageName= document.artifactlist.StageName[i].value;
								var ArtifactId= document.artifactlist.ArtifactId[i].value;
								var ProcessId= document.artifactlist.ProcessId[i].value;
								var stageNo= document.artifactlist.StageNo[i].value;
								var ReqquestId= document.artifactlist.ReqquestId[i].value;
								 strURL = "./SysMgmt.do?process=BPdenied&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo+"&RequestID="+ReqquestId;
      window.location.href = strURL;


						 }
						
					}
			   }


			   else{
			   
			   	if(document.artifactlist.chk.checked==true)
			{
					
					var StageValue= document.artifactlist.StageValue.value;
								var StageName= document.artifactlist.StageName.value;
								var ArtifactId= document.artifactlist.ArtifactId.value;
								var ProcessId= document.artifactlist.ProcessId.value;
								var stageNo= document.artifactlist.StageNo.value;
								var ReqquestId= document.artifactlist.ReqquestId.value;

 strURL = "./SysMgmt.do?process=BPdenied&StageValue="+StageValue+"&StageName="+StageName+"&ArtifactId="+ArtifactId+"&ProcessId="+ProcessId+"&StageNo="+stageNo+"&RequestID="+ReqquestId;
      window.location.href = strURL;
			}
			   }


	 
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
		
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  
		 
   <tr>
   <td><table width="100%" border="1" align="center" cellpadding="" cellspacing="" id="centerTab">
   <tr>
    
 <form name="artifactlist" action="SysMgmt.do">
 
  
   
 
			<td>   <table  border="1" cellpadding="0" cellspacing="0" width="100%" align="left" > <td colspan="7" class="tblMainHead" >
	</td>
	 <tr>

                           <td width="30" height="27" class="tblRowHeadTwo">Serial Id</td>
                       <td width="90" height="27" class="tblRowHeadTwo">Artifact Name</td>
                       <td width="120" height="27" class="tblRowHeadTwo">Artifact Description</td>
		               <td width="180" height="27" class="tblRowHeadTwo">Date of Request Raised</td>
                       <td width="150" height="27" class="tblRowHeadTwo">Stage Name</td>
					   <td width="200" height="27" class="tblRowHeadTwo">Actions</td>
					   <td width="200" height="27" class="tblRowHeadTwo">Comments</td>
				
		     
            </tr>
              <%
   Map <Integer,String[]> artifactlist=(HashMap)request.getAttribute("BPvotinglist");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (artifactlist != null && artifactlist.size() != 0) {  
                                                            
   Iterator iter = artifactlist.entrySet().iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	Map.Entry mEntry = (Map.Entry) iter.next();
                                                                	
                                                                	String [] artiType=(String[])mEntry.getValue();
                                                                	int id=(Integer)mEntry.getKey();
                                                                	String ArtifactName=artiType[0];
                                                                	String ArtifactDescription=artiType[1];
                                                                	String DORequest=artiType[2]; 
                                                                	String StageName=artiType[3];
                                                                	String Comments=artiType[4];
                                                                	String ProcessId=artiType[5];
                                                                	String ArtifactId=artiType[6];
                                                                	String StageValue=artiType[7];
																	String StageNo=artiType[8];
																	 String reqId=artiType[9];
																	 
																	 if(Comments==null){
																	 Comments="NA";
																	 }

                                                %>
                                                 <tr><td><input type="radio" name="chk" value="<%=id%>"></td><td><%=ArtifactName%></td><td align="left"><%=ArtifactDescription%></td><td align="left"><%=DORequest%></td><td><%=StageName%></td><td><input type="button" value="Approve" onclick="approved()"/><input type="button" value="Deny" onclick="denied()"/>
												 <input type="hidden" value="<%=reqId%>" name="ReqquestId"/>
												 <input type="hidden" value="<%=StageName%>" name="StageName"/>
												 <input type="hidden" value="<%=StageNo%>" name="StageNo"/>
												 <input type="hidden" value="<%=StageValue%>" name="StageValue"/>
												 <input type="hidden" value="<%=ArtifactId%>"  name="ArtifactId"/>
												 <input type="hidden" value="<%=ProcessId%>" name="ProcessId"/></td>
                                                 <td><%=Comments%></td></tr>
                                                 
                                                <%
                                                 
                                               
                                                                   
                                                                }
   }
   else{
	   %>
	   <tr><td colspan="7">No DATA </td></tr>
                                                <%
   }
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
