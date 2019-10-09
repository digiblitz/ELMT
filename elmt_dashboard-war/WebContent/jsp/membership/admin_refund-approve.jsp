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
<script src="javascripts/frmSponsReg.js" type="text/javascript" ></script>
<script src="javascripts/frmAdminRefundApproval.js" type="text/javascript" ></script>
<script src="javascripts/AdvMagval.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript" ></script>
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
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong>Membership</strong>: <span class="styleBoldTwo">Refund Approval Form</span> </td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">&nbsp;</td>
  </tr>
  <tr>
  	<td>
	
		<form name="frmAdvMag" id="myform" method="post" action="./AdminRefundStatusDisp.do?process=update" onsubmit="return myvalidate(this);">
        
		<%
			 Vector refundDetail=new Vector();
                         refundDetail = (Vector) request.getAttribute("refundDetail");
                         
                         Vector refundTyp=new Vector();
                         refundTyp = (Vector) request.getAttribute("refundTyp");
                         
                         String id=(String)request.getAttribute("reid");
                         int ct=Integer.parseInt(id);
                         String[] refund=(String[])refundDetail.elementAt(ct);
                         
                         System.out.println("Size :" +refundDetail.size());
                         String[] reqdate=refund[5].split(" ");
                                 
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

			
        <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
          <tr> 
            <td colspan="2" class="tblRowHead">Approve Refund:</td>
          </tr>
		  <tr> 
            <td class="tableLeft">Date Of Request:</td>
            <th class="tableRight"><%=dateFormat(reqdate[0])%></th>
            <input type="hidden" name="refundid" value="<%=refund[2]%>" />
            <input type="hidden" name="membstat" value="Approved" />
            <input type="hidden" name="memid" value="<%=refund[3]%>" />
            <input type="hidden" name="userId" value="<%=refund[16]%>" />
            
          </tr>
          <tr> 
            <td class="tableLeft">First Name:</td>
            <th class="tableRight"><%=refund[0]%></th>
          </tr>
          <tr> 
            <td class="tableLeft">Last Name:</td>
            <th class="tableRight"><%=refund[1]%></th>
          </tr>
          <tr> 
            <td class="tableLeft">E-Mail ID: </td>
            <th class="tableRight"><%=refund[15]%></th>
          </tr>
          <!--tr> 
            <td class="tableLeft">Date of Application: </td>
            <th class="tableRight">2006-08-29</th>
          </tr-->
          <!--<tr> 
            <td class="tableLeft">Member Id:</td>
            <th class="tableRight"></th>
          </tr>-->
          <!--tr> 
            <td class="tableLeft">Balance Amount: </td>
            <th class="tableRight">$500</th>
          </tr-->   
          
          <%
                String clAmt="0.00";
                
                if(refund[4]!=null)                    
                clAmt=refund[4];
          %>
          
          <tr> 
            <td class="tableLeft">Claim Amount:</td>
            <td class="tableRight"><span class="styleBoldOne"><%=clAmt%></span></td>
          </tr>
          <tr> 
            <td height="27" class="tableLeftTxtArea">Member Comments: </td>
            <td class="tableRight">
			<%=refund[13]%>
			</td>
          </tr>
          
          <% 
          
          String appAmt="0.00";
          
          if(refund[6]!=null)
          appAmt=refund[6];
          
          %>
          
          <tr> 
            <td class="tableLeft">Approved Refund Amount: </td>
            <td class="tableRight">$<%=appAmt%></td>
           
          </tr>
		<%
                Enumeration en=refundTyp.elements();
                while(en.hasMoreElements())
                {
                    String[] refTyp=(String[])en.nextElement();
                    System.out.println(refTyp.length);
                %>

 	<tr> 
            <td class="tableLeft"><%=refTyp[2]%></td>
            <td class="tableRight">$<%=refTyp[3]%></td>
           
          </tr>
                <%}%>
          <tr> 
            <td class="tableLeft">Refund Date: </td>
            <td class="tableRight"><input type="text" name="refunddate" class="textboxOne" size="25" /><a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> 
              </td>
          </tr>
          <tr> 
            <td class="tableLeft">Refunded By: </td>
            <td class="tableRight"><input type="text" name="refundby" class="textboxOne" size="25" /> 
              </td>
          </tr>
          <tr> 
            <td class="tableLeft">Cheque date: </td>
            <td class="tableRight"><input type="text" name="chequedate" class="textboxOne" size="25" /><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> 
              </td>
          </tr>
          <tr> 
            <td class="tableLeft">Cheque number: </td>
            <td class="tableRight"><input type="text" name="chequeno" class="textboxOne" size="25" /> 
              </td>
          </tr>
          <tr> 
            <td class="tableLeft">Bank name: </td>
            <td class="tableRight"><input type="text" name="bankname" class="textboxOne" size="25" /> 
              </td>
          </tr>
          <!--tr> 
            <td class="tableLeftTxtArea">Department Comments: </td>
            <td class="tableRight">
              <textarea name="textarea" class="textAreaOne" rows="5"></textarea>
              <span class="asterisk">*</span></td>
          </tr-->
          <tr id="chrgCrd"> 
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr> 
            <td class="tableLeft">&nbsp;</td>
            <td class="tableRight"><input type="submit" value="Submit" class="gradBtn" /></td>
          </tr>
        </table>
			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmAdvMag'].elements['refunddate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['frmAdvMag'].elements['chequedate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
</script>
