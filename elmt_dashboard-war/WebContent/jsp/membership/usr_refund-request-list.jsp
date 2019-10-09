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
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
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
<script>
    function submitForm() {
       location.href = "refund.do?method=refundRequestViewStatus"
    }
</script>
  <%! 
    DecimalFormat result  = new DecimalFormat("0.00");
    String amtFormat(String amtVal){
        String amountValue = "0.00";
        if(amtVal!=null && amtVal.trim().length()!=0){
            System.out.print(Double.parseDouble(amtVal));
            amountValue = result.format(Double.parseDouble(amtVal));
        }
        return amountValue;
    }
    
    %>
	
	<%! 				
				    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
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
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				  <div class="cmmnForm">
				    <div class="rowHead"> Membership Refund Application Listings:</div>
		<table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">  <tr>
		  <tr>
		  	<td width="123" height="25" class="tblRowHeadTwo">Claim Date</td>
			<td width="86" class="tblRowHeadTwo">Claim amount</td>
			<td width="87" class="tblRowHeadTwo">Approved amount</td>
			<td width="79" class="tblRowHeadTwo">Balance Amount</td>
			<td width="64" class="tblRowHeadTwo">Status</td>
			<td width="56" class="tblRowHeadTwo">View</td>
		   </tr>
                   <%
                      Vector refundDetail=new Vector();
                      refundDetail = (Vector) request.getAttribute("refundDetail");
       		      request.setAttribute("refundDetail",refundDetail);
                      Enumeration en=refundDetail.elements();
                      
		       int i=0;
                      
                       String clamt;
                       String appamt;
                       String balamt;
                       String stat;
                       
					  if(refundDetail!=null && refundDetail.size()!=0)
					  {
					  
					  System.out.print("refundDetail.size() in jsp :"+refundDetail.size());
					  
                      while(en.hasMoreElements())
                      {
					  String[] usrrefdet=(String[])en.nextElement();
					 
					  String[] dat=usrrefdet[3].split(" ");
					  
					  if(usrrefdet[2]!=null)
					  {
					  	clamt=usrrefdet[2];
					  }else{
					  	clamt="0.00";
					  }
					  if(usrrefdet[4]!=null)
					  {
					  	appamt=usrrefdet[4];
                                                
                                          }else{
					  	appamt="0.00";
					  }
					  
					  if(usrrefdet[10]!=null)
					  {
					  	balamt=usrrefdet[10];
                                          }
					  else{
					  	balamt="0.00";
					  }
					  
					  if(usrrefdet[12]!=null)
					  {
					  	stat=usrrefdet[12];
					  }
					  else{
					  	stat="";
					  }
					  					   
						%> 
			 <tr>   
			<form name="frmCntryMailList" id="myform" method="post" action="./UsrRefundReqListAction.do">	    
            <td bgcolor="#E3E1D2" class="alignLeft"><%=dateFormat(dat[0])%></td>
			<td bgcolor="#E3E1D2" class="alignLeft"><%=amtFormat(clamt)%></td>
			<td bgcolor="#E3E1D2" class="alignLeft"><%=amtFormat(appamt)%></td>
			<td bgcolor="#E3E1D2" class="alignLeft"><%=amtFormat(balamt)%></td>
			<td bgcolor="#E3E1D2" class="alignLeft"><%=stat%></td>
                        <input type=hidden name="id" value="<%=i%>">
			<input type=hidden name="rId" value="<%=usrrefdet[0]%>">
			<td bgcolor="#E3E1D2" class="alignLeft"><input type="submit" name="Submit2" value="View" class="oneBtn" /></td>
			 </form>
           </tr>
                  <%   i++; }}else{%>
				<tr>
					<td height="19" colspan="6" align="center"> <strong>No Records Found !!<font/></strong></a></td>
					</tr>
				 <% }
                 %>    

		  <tr>
			<td height="19" colspan="6" align="center"><a href="./RefundReqDisp.do" ><strong>Apply For Refund Request</strong></a></td>
			
           </tr>
	  </table>
	  </div>
	  
	  
	</td>
</tr>  
</table>
			<!-- CONTENTS END HERE -->		
		 
	
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