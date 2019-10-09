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
/************************** class DataRetriever *************************
 * 
 * Used to retrieve the data from the ReportClientDocument object.
 * If the document has saved data.
 *   
 ***********************************************************************/
public class DataRetriever
{
	private ReportClientDocument oReportClientDocument;

	/***********************************************************************
	 * 
	 * Constructor - Save the ReportClientDocument
	 *               
	 **********************************************************************/
	DataRetriever(ReportClientDocument oReportClientDocument)
	{
		this.oReportClientDocument = oReportClientDocument;
	}

	/***********************************************************************
	 * 
	 * Constructor - Return the saved data from a report as a Rowset object.
	 *               
	 **********************************************************************/
	public Rowset getSavedData() throws ReportSDKException
	{
		// Retrieve a Rowset of the saved data in the instance. 
		RowsetMetaData oRowsetMetaData = new RowsetMetaData();
		oRowsetMetaData.setDataFields(oReportClientDocument.getDataDefinition().getResultFields());

		RowsetCursor oRowsetCursor = oReportClientDocument.getRowsetController().createCursor(null, oRowsetMetaData);

		oReportClientDocument.getRowsetController().setRowsetBatchSize(oRowsetCursor.getRowset().getTotalRecordCount());

		oRowsetCursor.moveTo(0);
		
		return oRowsetCursor.getRowset();
	}
	
}
%>

