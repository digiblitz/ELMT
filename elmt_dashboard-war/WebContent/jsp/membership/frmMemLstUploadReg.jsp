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
<%@ page import = "java.text.*" %>

<%@ page import = "com.hlcmeeting.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=(String)session.getAttribute("title")%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/validate.js" type="text/javascript" ></script>
        
         <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
			<%!	SimpleDateFormat sdfsql = new SimpleDateFormat("MM/dd/yyyy");
           		String sqldateFormat(java.sql.Date sqlDate){     	
            
                String detValue = "NG";
                if(sqlDate!=null){
					java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                    detValue = sdfsql.format(utilDate);
                }
                return detValue;
                }
			%>
            <tr>
                <td class="tableCommonBg">
                    
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
                        
                        <tr>
                            <td width="230" class="menuTablePad">
                                <!-- LEFT MENU STARTS HERE -->
                                <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                                <!-- LEFT MENU ENDS HERE -->

                            </td>
                            <td width="500" class="subDeptTablePad">
                                <!-- CONTENTS START HERE -->
                                
                                <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
                                    
                                    <tr>
                                        <td colspan="5" class="tblMainHead">
                                        Membership: <span class="styleBoldTwo">Uploaded Registration Files Listing </span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="5" class="tblDescrp">
                                            All Uploaded Registration Files are listed as follows. <br />
                                            <br />
                                            To map a competition Registration Details,
                                            click on the <strong>'Proceed
                                            Mapping'</strong> button beside it. <br />
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td>
                                            
                                            
                                            <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
                                                
                                                <tr>
                                                    <td width="66" height="27" class="tblRowHeadTwo">Event ID</td>
                                                   
                                                    <td width="101" class="tblRowHeadTwo">File Name </td>
                                                    <td width="59" class="tblRowHeadTwo">Upload Date</td>
                                                    <td width="42" class="tblRowHeadTwo">Map</td>
                                                    <td width="76" class="tblRowHeadTwo">Validate</td>
                                                    <td width="70" class="tblRowHeadTwo">Delete</td>
                                                </tr>
                                                
                                                <%
                                                try
                                                {
                                                HLCUploadHorseRegVO uploadDetailsVO = new HLCUploadHorseRegVO();
                                                ArrayList uploadDetails = new ArrayList();
                                             	uploadDetails = (ArrayList)request.getAttribute("compFileDet");
                                                
                                                if(uploadDetails.size()!=0){
                                                Iterator it = uploadDetails.iterator();
												while(it.hasNext()){
												uploadDetailsVO = new HLCUploadHorseRegVO();
												uploadDetailsVO = (HLCUploadHorseRegVO)it.next();
                                                String event_id = uploadDetailsVO.getEvent_id();
												String uploadId = uploadDetailsVO.getReg_upload_id();
												//System.out.print("uploadId" + uploadId); 
                                                String event_name = uploadDetailsVO.getEvent_name();
                                                String result_file_path = "N/A";
                                                java.sql.Date upload_date = uploadDetailsVO.getUpload_date();
                                                String url_file_path = "";
                                                
	if(uploadDetailsVO.getRegistration_file_path()!=null && uploadDetailsVO.getRegistration_file_path().trim().length()!=0){
	result_file_path = uploadDetailsVO.getRegistration_file_path();//tmp[val];
	url_file_path = result_file_path;
	StringTokenizer strTkns = new StringTokenizer(uploadDetailsVO.getRegistration_file_path().toString(),"\\");
			while(strTkns.hasMoreTokens()){
				try{
					String phNo = (String)strTkns.nextToken();
					
						if(phNo!=null && phNo.trim().length()!=0){
						//System.out.print("ph no Added from Stokenizer:" + phNo);
						result_file_path = phNo;
						}
					}
					catch(Exception e){
					System.out.print("ph no tokanizing exception:" + e);
					}
			}
	}
                                               
                                                String enableStat="";
                                                //System.out.print(" uploadDetailsVO.isActive_status() :"+uploadDetailsVO.isActive_status());
                                                boolean mapStatus = uploadDetailsVO.isActive_status();
												System.out.print("mapStatus:::" + mapStatus);
                                                if(uploadDetailsVO.isActive_status()){
                                                enableStat = "disabled";
                                                }
                                                
                                                %>
                                                <form action="horseRegExcelData.do" method="post" />
                                                    
                                                    <input type="hidden" name="process" value="dispDataMapping" />
                                                    <input type="hidden" name="fileName" value="<%=url_file_path%>" />
                                                    <input type="hidden" name="uploadId" value="<%=uploadId%>" />
													<input type="hidden" name="eventId" value="<%=event_id%>" />
                                                    <%
														String enableStaus = "";
														if(uploadDetailsVO.isValidation_status() || mapStatus ==false){
															enableStaus = "disabled";
														}
														String enaStaus = "";
														if(uploadDetailsVO.isValidation_status()){
															enaStaus = "disabled";
														}
														%>
                                                    <tr>
                                                        <td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=event_id%></td> 
                                                        
                                                        <td bgcolor="#E3E1D2" class="alignCenter"><%=result_file_path%></td>		    
                                                        <td bgcolor="#E3E1D2" class="alignCenter"><%=sqldateFormat(upload_date)%></td>
                                                        <td bgcolor="#E3E1D2" class="alignCenter">
                                                        <input type="submit" <%=enableStat%> value="Map" name="butValue" class="oneBtn" />                                                </td>
                                                        <td bgcolor="#E3E1D2" class="alignCenter">
														
										<input name="btnValidate" type="button" class="oneBtn" <%=enableStaus%> value="Validate" onclick="location.href='competitionReg.do?process=exceptionReg&eventId=<%=event_id%>&upId=<%=uploadId%>'"/>														</td>
                                                        <td bgcolor="#E3E1D2" class="alignCenter"><label>
                                                          <input type="submit" name="butValue" value="Delete" class="twoBtn" />
                                                        </label></td>
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
