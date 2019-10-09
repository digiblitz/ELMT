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
<%@ page import="com.hlcmrm.util.HLCBreedVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script>
function listviews(lobid,view_point_id)
{
	strURL = "./roles.do?roleProcess=listSubViews&lobId="+lobid+"&view_point_id="+view_point_id;
	window.location.href = strURL;
	}
	function listArtifact(lobid,view_point_id,view_id)
	{
		alert("view_id---"+view_id);
		strURL = "./roles.do?roleProcess=listSubViews&lobId="+lobid+"&view_point_id="+view_point_id+"&viewId="+view_id;
		window.location.href = strURL;
		
	}
	
function toggle(id, link)
{
	//alert("1"+id);
	var e = document.getElementById(id);
	
	if (e.style.display == '') {
		e.style.display = 'none';
		link.innerHTML = 'Expand';
	}
	else {
		e.style.display = '';
		link.innerHTML = 'Collapse';
	}
}

</script>
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
			
			<td width="500" class="subDeptTablePad">
<%ArrayList viewPointArtifactList=(ArrayList)request.getAttribute("viewPointArtifactList");

ArrayList viewPointDetailsList=(ArrayList)session.getAttribute("viewPointDetailsList");
if(viewPointDetailsList!=null && viewPointDetailsList.size()!=0){
%>
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="">
				  <tr>
					<td valign="bottom">
					<%
					String view_point_name="";
					String view_point_id="";
					
					if(viewPointDetailsList!=null && viewPointDetailsList.size()!=0)
					{
						Iterator it=viewPointDetailsList.iterator();
						while(it.hasNext())
						{
							String[] viewpointDetails=(String[])it.next();
							
							view_point_id=viewpointDetails[0];
							view_point_name=viewpointDetails[1];
							String lob_value=viewpointDetails[2];
						}
						
						
					}
					%><div id="tabsE">
							<ul>
							<!-- CSS Tabs -->
										
								<li><a href="<%=request.getContextPath()%>" onClick="window.open('<%=request.getContextPath()%>/undrConstruct.jsp', 'width=300,height=200')"><span><%=view_point_name%></span></a></li>
									
							</ul>
					  </div>
					</td>
				  </tr>

				</table>
				
				<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="">
				 <tr>
		    <td valign="bottom">
				
				<!--TABS START HERE--> 	
				<div id="tabsF">
							<ul>
							<!-- CSS Tabs -->
								<%
								HashMap hm=new HashMap();
								ArrayList subViewList=(ArrayList)session.getAttribute("subViewList");
					ArrayList viewPointDetailsListofLob=(ArrayList)session.getAttribute("viewPointDetailsList");
					if(viewPointDetailsListofLob!=null && viewPointDetailsListofLob.size()!=0)
					{
						Iterator itLob=viewPointDetailsListofLob.iterator();
						while(itLob.hasNext())
						{
							String[] viewpointDetailsLOB=(String[])itLob.next();
							
							String view_id_lob=viewpointDetailsLOB[0];
							String view_name_lob=viewpointDetailsLOB[1];
							String lob_value=viewpointDetailsLOB[2];
							String lob_value_id=viewpointDetailsLOB[3];
							hm.put(lob_value_id,lob_value);
					
						
					%>
										
									<li><a href="#" onClick="listviews('<%=lob_value_id%>','<%=view_id_lob%>')"><span><%=lob_value%></span></a></li>								
								<%		
										}
									
						
						
					}
					%>	
														
							</ul>
				</div>
							
							
							</td>
	      </tr>	
	     <%
	     String objLobId=(String)request.getAttribute("LOBId");
	     String objViewPointId=(String)request.getAttribute("objViewPointId");
	     if(subViewList!=null && subViewList.size()!=0)
			{ %>
			 <tr>
		 
				
				
							<!-- CSS Tabs -->
								<%
								
					
						Iterator itsub=subViewList.iterator();
						while(itsub.hasNext())
						{
							String[] subViewListDet=(String[])itsub.next();
							
							String lob_sub_viewname=subViewListDet[0];
							String lob_sub_viewid=subViewListDet[1];
							
						if(hm.get(objLobId)!=null) {
					%>
							<td><input type="checkbox" name="subView" value="<%=lob_sub_viewid%>" onclick="listArtifact('<%=objLobId%>','<%=objViewPointId%>','<%=lob_sub_viewid%>')"><%=hm.get(objLobId)%> <%=lob_sub_viewname%></td>			
																	
					<%
						}	
					}	%>														
							
			
							
							
							
	      </tr>	
			<%} %>
		  </table>
		  
		  
		  
		  <%}%>
			</td>
		  </tr>
	  </table>
	  

	</td>
  </tr>
  <tr>
  <td>
  <table >
  <tr><td>
  <table align="left">
  <%
  
  String view_point_name="";
  String lob_name="";
  String tempGroupName="";
  
  int k=0;
  int l=0;
   ArrayList treeDetails=(ArrayList)request.getAttribute("treeDetailsList");
   //System.out.println("------------");
 //System.out.println("------------"+treeDetails.size());

   String[] gropuNameArray=new String[100];

if(treeDetails!=null && treeDetails.size()!=0)
   {
	   Iterator iterTreeLen=treeDetails.iterator();
	   while(iterTreeLen.hasNext())
	   {
		   String treearrayLen[]=(String[])iterTreeLen.next();
		   gropuNameArray[l]=treearrayLen[4];
		   //System.out.println("---------------"+gropuNameArray[l]);
		   l++;
	   }
	   //System.out.println("---------------"+gropuNameArray);
   }




   if(treeDetails!=null && treeDetails.size()!=0)
   {
	   Iterator iterTree=treeDetails.iterator();
	   while(iterTree.hasNext())
	   {
		   String treearray[]=(String[])iterTree.next();
		   view_point_name=treearray[0];
		    lob_name=treearray[1];
		    String view_name=treearray[2];
		    String  groupId=treearray[3];
		    String groupName=treearray[4];
		    String processId=treearray[5];
		    String processName=treearray[6];
		    
			System.out.println("1"+groupName+"=="+gropuNameArray[k]+"=="+processName);

			if(groupName!=null && processName!=null && !groupName.equalsIgnoreCase(tempGroupName)){
			
				%>		    
				<ul><a href="#" onclick="toggle('node<%=k%>')"><input type="hidden" name="groups" value="<%=groupId%>"><%=groupName%></input></a>
				<ul id="node<%=k%>" style="display:none">
		    	<li><input type="hidden" name="process" value="<%=processId%>"><%=processName%></input></li>
				<%	    	
			tempGroupName = groupName;
			k++;
			}
			else {
				if(groupName!=null && processName!=null && gropuNameArray[k+1]!=null && !gropuNameArray[k+1].equalsIgnoreCase(tempGroupName)) {
					%>
					<li><input type="hidden" name="process" value="<%=processId%>"><%=processName%></input></li>
					</ul></ul>
					<%
					}else {
					
						if(groupName!=null && processName!=null) {						
							%>
							<li><input type="hidden" name="process" value="<%=processId%>"><%=processName%></input></li>
							<%
						}
					}
					k++;
				}
			}
		}
	   			 				 
 
  %>



</table>
</td><td></td>
<td>
 
<table align="right">

 <%
  int cnt=0;
  if(viewPointArtifactList!=null && viewPointArtifactList.size()!=0)
  {
	   Iterator iterArtifact=viewPointArtifactList.iterator();
	   while(iterArtifact.hasNext())
	   {
		   String artifactArray[]=(String[])iterArtifact.next();
		   String view_point_art_id=artifactArray[0];
		   String artifact_name=artifactArray[1];
		   String artifact_desc=artifactArray[2];
		  %>
		<tr> <td><%=artifact_name %><br>
		  <%=artifact_desc %></td></tr>
		   <%
		   }
		   cnt++;
	   }
  
  %>
  
  </table>
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
