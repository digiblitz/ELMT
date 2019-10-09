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
/*********************** class ReportObjectAdder ***********************
 * 
 * This class is responsible for creating Text Objects and adding them
 * to your ReportClientDocument.
 *   
 ***********************************************************************/
public class ReportObjectAdder
{
	private final int INCHES = 0;
	private final int CENTIMETRES = 1;

	private final int ADD_TO_END = -1;

	private ReportClientDocument oReportClientDocument;

	/******************************************************************
	 * 
	 * Constructor - Save the ReportClientDocument for modification.
	 *               
	 ******************************************************************/	
	ReportObjectAdder(ReportClientDocument oReportClientDocument)
	{ this.oReportClientDocument = oReportClientDocument; }

	private ReportObject add_text_object(Section section, String text, double x, double y, double w, double h) throws ReportSDKException
	{
		return place_report_object(section, text_object(text), x,y,w,h);
	}

	/******************************************************************
	 * 
	 * Create a TextObject using the given text.
	 *               
	 ******************************************************************/
	private ReportObject text_object(String text) 
	{
		TextObject oTextObject = new TextObject();
		Paragraphs oParagraphs = new Paragraphs();
		Paragraph oParagraph = new Paragraph();
		ParagraphElements oParagraphElements = new ParagraphElements();
		ParagraphTextElement oParagraphTextElement = new ParagraphTextElement();
		
		oParagraphTextElement.setText(text);
		oParagraphTextElement.setKind(ParagraphElementKind.text);

		// fix some wierd defaults and behavior by explicitly setting default font
		FontColor newFontColor = new FontColor();
		Font newfont = new Font();
		newfont.setBold(false);
		newfont.setName("Arial");
		newFontColor.setIFont(newfont);
		oParagraphTextElement.setFontColor(newFontColor);
		
		oParagraphElements.add(oParagraphTextElement);
		oParagraph.setParagraphElements(oParagraphElements);
		oParagraphs.add(oParagraph);

		oTextObject.setParagraphs(oParagraphs);

		return oTextObject;
	}

	/******************************************************************
	 * 
	 * Place a ReportObject on the section at the given coordinates
	 * Coordinates are in inches.
	 *               
	 ******************************************************************/
	public ReportObject place_report_object(Section oSection, ReportObject oReportObject, 
				        double x, double y, double w, double h) throws ReportSDKException	
	{
		oReportObject.setLeft(getTwips(x, INCHES));
		oReportObject.setTop(getTwips(y, INCHES));
		oReportObject.setWidth(getTwips(w, INCHES));
		oReportObject.setHeight(getTwips(h, INCHES));

		oReportClientDocument.getReportDefController().getReportObjectController().add(oReportObject, oSection, ADD_TO_END);
		
		// return the object for possible later modifications,
		// formating, etc.
		return oReportObject;
	}

	/******************************************************************
	 * 
	 * Remove the report object with the given name from the report.
	 *               
	 ******************************************************************/
	public void remove_report_object(String report_object_name) throws ReportSDKException
	{
		// Get the Report Object that we want to remove.
		ReportObject oReportObject = (ReportObject)oReportClientDocument.getReportDefController().getReportDefinition().findObjectByName(report_object_name);

		oReportClientDocument.getReportDefController().getReportObjectController().remove(oReportObject);
	}
	
	
	/******************************************************************
	 * 
	 * Return the number of twips for n INCHES or n CENTIMETRES.
	 *               
	 ******************************************************************/
	private int getTwips(double n, int measurement)
	{
		switch (measurement) {
			case INCHES:
				return (int) (n * 1440); // 1440 twips/inch
			case CENTIMETRES:
				return (int) (n * 567); // 567 twips/centimeter
		}
		return -1;
	}
}

%>
