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
<%
   /* 
    * Applies To Version:	XI
    * Date:			Febuary 28 2005
    * Description:
    * 				This is a sample to demonstrate creating our 
    * 				standard simplepreviewreport sample from scratch
    * 				using RAS Report Creation and Modification APIs.
    * Author:			BH
    */
%>

<%@ page import="com.crystaldecisions.sdk.occa.report.application.*,
  com.crystaldecisions.sdk.occa.report.data.*,
  com.crystaldecisions.sdk.occa.report.lib.*,
  com.crystaldecisions.sdk.framework.*,
  com.crystaldecisions.sdk.exception.*,
  com.crystaldecisions.sdk.occa.managedreports.*,
  com.crystaldecisions.sdk.occa.infostore.*,
  com.crystaldecisions.sdk.occa.report.document.*,
  com.crystaldecisions.sdk.occa.report.definition.*"
%>

<%@ include file = "logonform.jsp" %>
<%@ include file = "Generic.jsp" %>

<%@ include file="ReportCreator.jsp" %>

<%@ include file="Datasource.jsp" %>
<%@ include file="ODBCDatasource.jsp" %>

<%@ include file="TableAdder.jsp" %>
<%@ include file="GroupAdder.jsp" %>
<%@ include file="ReportObjectAdder.jsp" %>
<%@ include file="ReportObjectFormatter.jsp" %>
<%@ include file="FieldAdder.jsp" %>



<%

	// If logon information is required, display the logonForm and exit.
	// (code for LogonForm class is below)
	LogonForm logonform = new LogonForm(response, request);
	if (logonform.display_if_needed()) return;

	// Log in to Enterprise and get the report to modify
	// (code for Generic class in Generic.jsp)
	Generic myGeneric = new Generic(logonform.cmsname, logonform.username, logonform.password, logonform.authType);
	IEnterpriseSession oEnterpriseSession = myGeneric.logon();

	ReportClientDocument oReportClientDocument = create_this_report(oEnterpriseSession);

	
	// View the result
	session.setAttribute("CrystalReportViewer.ReportSource", oReportClientDocument.getReportSource());
	response.sendRedirect("CrystalReportViewer.jsp");

	
%>



<%! 
/******************************************************************
 * 
 * This function creates a SimplePreviewReport using RAS.
 *               
 ******************************************************************/
public ReportClientDocument create_this_report(IEnterpriseSession oEnterpriseSession)    throws ReportSDKException, SDKException 
{
	ReportCreator myReportCreator = new ReportCreator(oEnterpriseSession);

	// Add table and linking information
	Datasource myDatasource = new ODBCDatasource("","","Xtreme Sample Database 11", "");
	myReportCreator.myTableAdder.add_tables(myDatasource, new String[] {"Customer", "Orders"});
	myReportCreator.myTableAdder.add_table_link("Customer", "Orders", "Customer ID", "Customer ID", TableJoinType.equalJoin);
	
	// Add a group on the Customer Name.
	myReportCreator.myGroupAdder.add_group("{Customer.Customer Name}");
	
	// Add Report Header
	ReportObject oReportObject;
	
	oReportObject = myReportCreator.myReportObjectAdder.add_text_object(
		myReportCreator.getReportHeaderSection(),
		"Simple Preview Report",
		0.760, 0.130, 4.880, 0.430);
	oReportObject = myReportCreator.myReportObjectFormatter.setFont(oReportObject, "Times New Roman");
	oReportObject = myReportCreator.myReportObjectFormatter.setFontSize(oReportObject, 20);
	oReportObject = myReportCreator.myReportObjectFormatter.setAlignment(oReportObject, Alignment.horizontalCenter);
	oReportObject = myReportCreator.myReportObjectFormatter.setBorder(oReportObject, LineStyle.singleLine, null, true);
		
	// Add Page Header
	oReportObject = myReportCreator.myReportObjectAdder.add_text_object(
		myReportCreator.getPageHeaderSection(),
		"Customer Name",
		1.580, 0.250, 1.300, 0.160);
	oReportObject = myReportCreator.myReportObjectFormatter.setUnderline(oReportObject, true);
	
	oReportObject = myReportCreator.myReportObjectAdder.add_text_object(
		myReportCreator.getPageHeaderSection(),
		"Order Amount",
		3.000, 0.250, 1.300, 0.160);
	oReportObject = myReportCreator.myReportObjectFormatter.setAlignment(oReportObject, Alignment.right);
	oReportObject = myReportCreator.myReportObjectFormatter.setUnderline(oReportObject, true);
	
	// Add database fields for details section 
	oReportObject = myReportCreator.myFieldAdder.add_db_field(
		myReportCreator.getGroupHeaderSection(), 
		"Customer.Customer Name", 
		FieldValueType.stringField,
		1.580, 0.000, 1.300, 0.160);

	
	myReportCreator.myFieldAdder.add_db_field(
		myReportCreator.getDetailsSection(), 
		"Orders.Order Amount", 
		FieldValueType.currencyField,
		3.000, 0.000, 1.300, 0.160);

	

	return myReportCreator.getReportClientDocument();
} %>

