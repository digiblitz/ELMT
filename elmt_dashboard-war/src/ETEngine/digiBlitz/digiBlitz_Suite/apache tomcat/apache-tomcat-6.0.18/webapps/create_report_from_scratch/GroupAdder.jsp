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
/*************************** class GroupAdder ***************************
 * 
 * Add Groups to the Report
 *   
 ***********************************************************************/
public class GroupAdder
{
	ReportClientDocument oReportClientDocument;
	public static final int ADD_TO_END = -1;
	
	/************************************************************************
	 * 
	 * Constructor - Save the ReportClientDocument to add groups to.
	 *               
	 ***********************************************************************/	
	GroupAdder(ReportClientDocument oReportClientDocument)
	{ this.oReportClientDocument = oReportClientDocument; }

	/************************************************************************
	 * 
	 * Add a group on the given field to the report.
	 *               
	 ***********************************************************************/	
	public Group add_group(String field_name) throws ReportSDKException
	{
		Group oGroup = new Group();
		IField oField = null;
		Tables oTables = oReportClientDocument.getDatabase().getTables();
		int index;

		for (int i = 0; i < oTables.size(); i++) {
			Table oTable = (Table)oTables.get(i);
			oField =oTable.getDataFields().findField(field_name, FieldDisplayNameType.formulaName, java.util.Locale.ENGLISH);
			if (oField != null) break; // break if we found the field.
		}
		if (oField == null) {
			System.out.println ("*******group not found**********");
			return null;
		} else {
			oGroup.setConditionField(oField);
			oReportClientDocument.getDataDefController().getGroupController().add(ADD_TO_END, oGroup);
			return oGroup;
		}
		
	}
}
%>

