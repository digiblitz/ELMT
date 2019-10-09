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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.hlcmeeting.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>

<script type="text/javascript">

function confirmation(uploadId,fileName) {
	var answer = confirm("All Corresponding Event Related Datas Will Be Deleted !! \r\n\n                         Are You Sure ?");
	//alert("horseCompDataMap.do?process=Delete&uploadId="+uploadId+"&fileName="+fileName);
	
	if (answer){
		window.location = "horseCompDataMap.do?process=Delete&uploadId="+uploadId+"&fileName="+fileName;
	}	
}

</script>

<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<%!SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
					String dateFormat(String fieldName){					
						Date clDate = null;
						Calendar cal = Calendar.getInstance();
						GregorianCalendar gc = new GregorianCalendar();
						try{
							clDate = sd.parse(fieldName);
						}catch(Exception e){
							System.out.println("Error While Parsing Date: "+e);
						}
						
						gc.setTime(clDate);
						cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						String dispDate = "N/A";
						if(clDate!=null ){
						dispDate = sd1.format(cal.getTime());
						}
						return dispDate;
					}
			%>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">

  <tr>
    <td colspan="5" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Uploaded Competition Files Listing </span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		
		  <%
                    try
		    {
			  java.util.Calendar c7 = java.util.Calendar.getInstance();
			  int calYear = c7.get(Calendar.YEAR);
			  System.out.print("calYear in JSP :"+calYear);
                                                    
			  String loadYear = (String)request.getAttribute("year");
                          System.out.println("loadYear in JSP :"+loadYear);
                          
                          int selYr = 0;
                          
                          if(loadYear!=null)
                          {
                             selYr = Integer.parseInt(loadYear);
                          }
                          else
                          {
                            selYr = calYear;
                          }
		  %>
		  <form action="horseCompDataMap.do" method="post" />  
		  <input type="hidden" name="process" value="listUploadDetails" />
		                                        
		    <br />	
			To View All Details Select a Year and Click Show all files :	  
		    <select name="year" id="year" class="selectboxOne"/>
		      <%
			  	for(int i=1980;i<=calYear;i++)
				{
					if(i==selYr)
					{
				%>
					<option value="<%=i%>" selected="selected"><%=i%></option>
				<%
					}
					else
					{
					%>
					<option value="<%=i%>"><%=i%></option>
					<%}}
			  %>
		    </select>
			&nbsp;
			<input type="submit" name="dispStat" value="Show all files" class="gradBtn" />
			</form>
			
			<p>All Uploaded Competition Result Files are listed as follows. <br />
			<br />
		      </p></td>
  </tr>
 
 <tr>
 	<td>
	
	
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		  
		  <tr>
			<td width="100" height="27" class="tblRowHeadTwo">Event ID</td>
			<td width="100" class="tblRowHeadTwo">Event Type</td>
			<td width="123" class="tblRowHeadTwo">File Name </td>
			<td width="100" class="tblRowHeadTwo">Upload Date</td>
			<td width="63" class="tblRowHeadTwo">Mapping</td>
			<td width="63" class="tblRowHeadTwo">Validate</td>
			<td width="63" class="tblRowHeadTwo">Delete</td>
		   </tr>
			
			<%
				HLCCompUploadFileDetails uploadDetailsVO = new HLCCompUploadFileDetails();
				ArrayList uploadDetails = new ArrayList();
				
				uploadDetails = (ArrayList)request.getAttribute("compFileDet");
                                

                                
				if(uploadDetails.size()!=0)
				{
				for(int i=0;i<uploadDetails.size();i++)
				{
			   		uploadDetailsVO = new HLCCompUploadFileDetails();
					uploadDetailsVO = (HLCCompUploadFileDetails)uploadDetails.get(i);
					
					//System.out.print("uploadDetailsVO.toString() :"+uploadDetailsVO.toString());
					
					String event_id = "N/A";
                	String event_name = "N/A";
                	String result_file_path = "N/A";
                	String upload_date = "N/A";
					
					if(uploadDetailsVO.getEvent_id()!=null && uploadDetailsVO.getEvent_id().trim().length()!=0)
					{
						event_id = uploadDetailsVO.getEvent_id();
					}
					
					if(uploadDetailsVO.getEvent_name()!=null && uploadDetailsVO.getEvent_name().trim().length()!=0)
					{
						event_name = uploadDetailsVO.getEvent_name();
					}
					
					String url_file_path = "";
					
					if(uploadDetailsVO.getResult_file_path()!=null && uploadDetailsVO.getResult_file_path().trim().length()!=0)
					{						
						//String[] tmp = uploadDetailsVO.getResult_file_path().split("\\");
						//int val = tmp.length-1;
						result_file_path = uploadDetailsVO.getResult_file_path();//tmp[val];
						url_file_path = result_file_path;
						 StringTokenizer strTkns = new StringTokenizer(uploadDetailsVO.getResult_file_path().toString(),"\\");
                                               
                        while(strTkns.hasMoreTokens()){
                            try{
                                String phNo = (String)strTkns.nextToken();
                                
                                if(phNo!=null && phNo.trim().length()!=0){
                                    System.out.print("ph no Added from Stokenizer:" + phNo);
                                    result_file_path = phNo;
                                }
                            }
                                 catch(Exception e){
                                    System.out.print("ph no tokanizing exception:" + e);
                                }
                            }
						
					}
					
					if(uploadDetailsVO.getUpload_date()!=null)
					{
						//System.out.print("uploadDetailsVO.getUpload_date().toString() :"+uploadDetailsVO.getUpload_date().toString());
						//java.sql.Date temp_date= uploadDetailsVO.getUpload_date().toString();
						//String pat = "\\";
						//String[] temp_dt = uploadDetailsVO.getUpload_date().toString().split(pat);
						//System.out.print("temp_dt[0] :"+temp_dt[0]);
										
						upload_date = uploadDetailsVO.getUpload_date().toString();//temp_dt[0];
					}
					
					String enableStat="";
					String uploadId= uploadDetailsVO.getUpload_id();
					System.out.print(" uploadDetailsVO.isActive_status() :"+uploadDetailsVO.isActive_status());
									boolean mapStatus = uploadDetailsVO.isActive_status();
				/*out.print("mapStatus "+mapStatus );
				out.print("Validation Status "+uploadDetailsVO.isValidation_status());*/
					if(uploadDetailsVO.isActive_status())
					{
						enableStat = "disabled";
					}
                                        String enableStaus = "";
                                        if(uploadDetailsVO.isValidation_status() || mapStatus ==false){
                                                enableStaus = "disabled";
                                        }
					%>    
                                            <form action="horseCompDataMap.do" method="post" />                                        
		                                    <input type="hidden" name="fileName" value="<%=url_file_path%>" />
                                            <input type="hidden" name="uploadId" value="<%=uploadDetailsVO.getUpload_id()%>" />
											
                                            <tr>
                                                <td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=event_id%></td> 
                                                <td bgcolor="#E3E1D2" class="alignCenter"><%=event_name%></td>
                                                <td bgcolor="#E3E1D2" class="alignCenter"><%=result_file_path%></td>		    
                                                <td bgcolor="#E3E1D2" class="alignCenter"><%=dateFormat(upload_date)%></td>
                                              <td bgcolor="#E3E1D2" class="alignCenter">
                                                    <input type="submit" <%=enableStat%> name="process" value="Map" class="oneBtn" />
                                                </td>
												<td bgcolor="#E3E1D2" class="alignCenter">
                                                <input type="button" name="validatebtn"  <%=enableStaus%>  value="Validate" class="oneBtn" onclick="location.href='competitionReg.do?process=exceptionResult&eventId=<%=event_id%>&upId=<%=uploadId%>'"/>
                                                </td>
												<td bgcolor="#E3E1D2" class="alignCenter">
												<%
												String disStatus = "";
												if(newRoleName!=null && !(newRoleName.equalsIgnoreCase("Admin"))){
													disStatus = "disabled";
												}
												%>
																									
                                                    <input type="button" name="process" value="Delete" class="oneBtn" onclick="confirmation('<%=uploadId%>','<%=url_file_path%>');" />

                                                </td>
												
                                            </tr>
                                        </form>
		   <%}}
			else {
			%>
				<tr>
				  <th height="25" colspan="7"><center>There are no records available.</center> </th>
				</tr>
			<%}}
			catch(Exception e)
			{
				System.out.println(" error in jsp :"+e);
			} 
			%>
	   
		  <tr>
			<td height="19" colspan="7">&nbsp;</td>
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
