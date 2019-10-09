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
<%!
/************************* class ReportCreator *************************
 * 
 * This class is responsible for creating Text Objects and adding them
 * to your ReportClientDocument.
 *   
 ***********************************************************************/
private class ReportCreator
{
	ReportClientDocument oReportClientDocument;
	TableAdder myTableAdder;
	ReportObjectAdder myReportObjectAdder;
	ReportObjectFormatter myReportObjectFormatter;
	FieldAdder myFieldAdder;
	GroupAdder myGroupAdder;

	public ReportClientDocument getReportClientDocument() 
	{ return oReportClientDocument; }

	/******************************************************************
	 * 
	 * Constructor - Create a new ReportClientDocument and initialize
	 *               the helper objects.
	 *               
	 ******************************************************************/
	ReportCreator(IEnterpriseSession oEnterpriseSession) throws ReportSDKException, SDKException 
	{
		
		// This is required to create a new report.	
		IReportAppFactory oReportAppFactory = (IReportAppFactory)oEnterpriseSession.getService("", "RASReportService");
		// Create new, empty report as oReportClientDocument.
		this.oReportClientDocument = oReportAppFactory.newDocument(java.util.Locale.ENGLISH);
		myFieldAdder = new FieldAdder(oReportClientDocument);
		myTableAdder = new TableAdder(oReportClientDocument);
		myGroupAdder = new GroupAdder(oReportClientDocument);
		myReportObjectAdder = new ReportObjectAdder(oReportClientDocument);
		myReportObjectFormatter = new ReportObjectFormatter(oReportClientDocument);

	}
	
	/******************************************************************
	 * 
	 * Return the given details section (0 = A, 1 = B, etc)
	 *               
	 ******************************************************************/
	public Section getDetailsSection(int sectionnum) throws ReportSDKException
	{
		return (Section) oReportClientDocument.getReportDefController().getReportDefinition().getDetailArea().getSections().getSection(sectionnum);
	}
	
	/******************************************************************
	 * 
	 * Return the detail section A
	 *               
	 ******************************************************************/
	public Section getDetailsSection() throws ReportSDKException
	{
		return getDetailsSection(0); // Details Section A
	}
	
	/******************************************************************
	 * 
	 * Return the given report header section (0 = A, 1 = B, etc)
	 *               
	 ******************************************************************/
	public Section getReportHeaderSection(int sectionnum) throws ReportSDKException
	{
		return (Section) oReportClientDocument.getReportDefController().getReportDefinition().getReportHeaderArea().getSections().getSection(sectionnum);
	}

	/******************************************************************
	 * 
	 * Return the Report Header section A
	 *               
	 ******************************************************************/
	public Section getReportHeaderSection() throws ReportSDKException
	{
		return getReportHeaderSection(0); // Details Section A
	}

	/******************************************************************
	 * 
	 * Return the given page header section (0 = A, 1 = B, etc)
	 *               
	 ******************************************************************/
	public Section getPageHeaderSection(int sectionnum) throws ReportSDKException
	{
		return (Section) oReportClientDocument.getReportDefController().getReportDefinition().getPageHeaderArea().getSections().getSection(sectionnum);
	}

	/******************************************************************
	 * 
	 * Return the page header section A
	 *               
	 ******************************************************************/
	public Section getPageHeaderSection() throws ReportSDKException
	{
		return getPageHeaderSection(0); // Details Section A
	}

	/******************************************************************
	 * 
	 * Return the given group header section (0 = A, 1 = B, etc)
	 *               
	 ******************************************************************/
	public Section getGroupHeaderSection(int sectionnum) throws ReportSDKException
	{
		return (Section) oReportClientDocument.getReportDefController().getReportDefinition().getGroupHeaderArea(0).getSections().getSection(sectionnum);
	}
	
	/******************************************************************
	 * 
	 * Return the group header section A
	 *               
	 ******************************************************************/
	public Section getGroupHeaderSection() throws ReportSDKException
	{
		return getGroupHeaderSection(0); // Group Header Section A
	}

}

%>
