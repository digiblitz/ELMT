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

/*************************** class TableAdder ***************************
 * 
 * This class is responsible for creating Text Objects and adding them
 * to your ReportClientDocument.
 *   
 ***********************************************************************/
public class TableAdder
{
	ReportClientDocument oReportClientDocument;

	/******************************************************************
	 * 
	 * Constructor - Save the reportClientDocument to make future 
	 *               mofications on.
	 *               
	 ******************************************************************/
	TableAdder(ReportClientDocument oReportClientDocument)
	{ 
		this.oReportClientDocument = oReportClientDocument;
	}

	/******************************************************************
	 * 
	 * Add all the tables given in table_names, using the given
	 * Datasource to the report.
	 *               
	 ******************************************************************/
	public void add_tables(Datasource new_datasource, String[] table_names) throws ReportSDKException
	{
		Table oTable;
		String table_name;
		for (int i = 0; i < table_names.length; i++) {
			table_name = table_names[i];
			oTable = new Table();
			oTable.setName(table_name);
			oTable.setConnectionInfo(new_datasource.getConnInfo());
			oReportClientDocument.getDatabaseController().addTable(oTable, null);
		}
	}

	/******************************************************************
	 * 
	 * Create a link between two tables on two specific fields.
	 *               
	 ******************************************************************/
	public void add_table_link(String right_table, String left_table, String right_fieldname, String left_fieldname, TableJoinType oTableJoinType) throws ReportSDKException
	{
		IStrings source_field_names = new Strings();
		IStrings target_field_names = new Strings();
		source_field_names.add(right_fieldname);
		target_field_names.add(left_fieldname);
	
		TableLink oTableLink = new TableLink();
		oTableLink.setSourceTableAlias(right_table);
		oTableLink.setTargetTableAlias(left_table);
		oTableLink.setSourceFieldNames(source_field_names);
		oTableLink.setTargetFieldNames(target_field_names);
		oTableLink.setJoinType(oTableJoinType);
		oReportClientDocument.getDatabaseController().addTableLink(oTableLink);
	}
}
%>
