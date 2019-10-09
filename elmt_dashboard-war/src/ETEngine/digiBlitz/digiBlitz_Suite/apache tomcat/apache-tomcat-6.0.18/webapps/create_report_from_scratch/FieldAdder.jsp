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
/************************** class FieldAdder ***************************
 * 
 * Add various types of fields to the report.
 * 
 ***********************************************************************/
public class FieldAdder
{
	ReportClientDocument oReportClientDocument;
	ReportObjectAdder myReportObjectAdder;

	/******************************************************************
	 * 
	 * Constructor - initialize variables
	 *               
	 ******************************************************************/
	FieldAdder(ReportClientDocument oReportClientDocument) 
	{
		this.oReportClientDocument = oReportClientDocument;
		this.myReportObjectAdder = new ReportObjectAdder(oReportClientDocument);
	}

	/******************************************************************
	 * 
	 * Add the database field with the given field_name into the given
	 * section at the given coordinates.  Field names should be of the 
	 * format: "table.field".  
	 *               
	 ******************************************************************/
	public ReportObject add_db_field(Section oSection, String field_name, 
					 FieldValueType oFieldValueType,
				         double x, double y, double w, double h)
					 throws ReportSDKException
	{
		return place_field(oSection, db_field(field_name, oFieldValueType),x,y,w,h);
	}

	/******************************************************************
	 * 
	 * Used internally to create a database field object.
	 *               
	 ******************************************************************/
	private Field db_field(String field_name, FieldValueType oFieldValueType)
	{
		DBField oDBField = new DBField();
		oDBField.setName(field_name);
		oDBField.setType(oFieldValueType);
		return oDBField;
	}

	/******************************************************************
	 * 
	 * Add the Formula with the given field_name and formula_text 
	 * into the given section at the given coordinates.
	 *               
	 ******************************************************************/
	public ReportObject add_formula_field(Section oSection, String field_name, String formula_text,
					 FieldValueType oFieldValueType,
				         double x, double y, double w, double h)
					 throws ReportSDKException
	{
		// Add the formula to the report
		FormulaField oFormulaField = formula_field(field_name, formula_text, oFieldValueType);
		// Place the formula field on the report's canvas.
		oReportClientDocument.getDataDefController().getFormulaFieldController().add(oFormulaField);
		return place_field(oSection, oFormulaField,x,y,w,h);
	}

	/******************************************************************
	 * 
	 * Used by place_field to create a database field object.
	 *               
	 ******************************************************************/
	private FormulaField formula_field(String field_name, String formula_text, FieldValueType oFieldValueType)
	{
		FormulaField oFormulaField = new FormulaField();
		oFormulaField.setName(field_name);
		oFormulaField.setText(formula_text);
		oFormulaField.setType(oFieldValueType);
		oFormulaField.setLength(formula_text.length());
		oFormulaField.setSyntax(FormulaSyntax.crystal);
		
		return oFormulaField;
	}

	
	/******************************************************************
	 * 
	 * Used to place a field object on the report.
	 *               
	 ******************************************************************/	
	private ReportObject place_field(Section oSection, Field oField, 
				      double x, double y, double w, double h)
				      throws ReportSDKException
	{

		FieldObject oFieldObject = new FieldObject();
		oFieldObject.setDataSource(oField.getFormulaForm());
		oFieldObject.setFieldValueType(oField.getType());
		return myReportObjectAdder.place_report_object(oSection, oFieldObject, x, y, w, h);
	}

	
}
%>


