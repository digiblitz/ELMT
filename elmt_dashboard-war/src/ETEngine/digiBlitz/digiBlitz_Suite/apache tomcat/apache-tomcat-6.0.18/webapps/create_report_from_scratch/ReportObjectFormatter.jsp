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
/********************* class ReportObjectFormatter *********************
 * 
 * This class is responsible for changing the formatting of existing
 * ReportObjects.
 * 
 ***********************************************************************/
public class ReportObjectFormatter
{
	private ReportClientDocument oReportClientDocument;

	/******************************************************************
	 * 
	 * Contructor - Save the ReportClientDocument for making
	 * modifications with.
	 *               
	 ******************************************************************/	
	ReportObjectFormatter(ReportClientDocument oReportClientDocument)
	{ this.oReportClientDocument = oReportClientDocument; }

	/******************************************************************
	 * 
	 * Set the bold value of the ReportObject.
	 * The Report Object must be a TextObject or Field Object 
	 * for this to have any effect.
	 *               
	 ******************************************************************/	
	private ReportObject setBold(ReportObject oReportObject, boolean value) throws ReportSDKException
	{
		// make a copy of the ReportObject and make our changes to the copy
		// as per the model/view/controller archetecture.
 		ReportObject oReportObjectCopy = (ReportObject)oReportObject.clone(true);
		if (oReportObject instanceof TextObject) {
			((TextObject)oReportObjectCopy).getFontColor().getIFont().setBold(value);
		} else if (oReportObject instanceof FieldObject) {
			((FieldObject)oReportObjectCopy).getFontColor().getIFont().setBold(value);
		} else {
			return null;
		}
		
		oReportClientDocument.getReportDefController().getReportObjectController().modify(oReportObject, oReportObjectCopy);
		return oReportObjectCopy;

	}

	/******************************************************************
	 * 
	 * Set the italics value of the ReportObject.
	 * The Report Object must be a TextObject or Field Object 
	 * for this to have any effect.
	 *               
	 ******************************************************************/	
	private ReportObject setItalic(ReportObject oReportObject, boolean value) throws ReportSDKException
	{
		// make a copy of the ReportObject and make our changes to the copy
		// as per the model/view/controller archetecture.
 		ReportObject oReportObjectCopy = (ReportObject)oReportObject.clone(true);
		if (oReportObject instanceof TextObject) {
			((TextObject)oReportObjectCopy).getFontColor().getIFont().setItalic(value);
		} else if (oReportObject instanceof FieldObject) {
			((FieldObject)oReportObjectCopy).getFontColor().getIFont().setItalic(value);
		} else {
			return null;
		}
		
		oReportClientDocument.getReportDefController().getReportObjectController().modify(oReportObject, oReportObjectCopy);
		return oReportObjectCopy;

	}
	
	/******************************************************************
	 * 
	 * Set the underline value a valid ReportObject.
	 * The Report Object must be a TextObject or Field Object 
	 * for this to have any effect.
	 *               
	 ******************************************************************/	
	private ReportObject setUnderline(ReportObject oReportObject, boolean value) throws ReportSDKException
	{
		// make a copy of the ReportObject and make our changes to the copy
		// as per the model/view/controller archetecture.
 		ReportObject oReportObjectCopy = (ReportObject)oReportObject.clone(true);
		if (oReportObject instanceof TextObject) {
			((TextObject)oReportObjectCopy).getFontColor().getIFont().setUnderline(value);
		} else if (oReportObject instanceof FieldObject) {
			((FieldObject)oReportObjectCopy).getFontColor().getIFont().setUnderline(value);
		} else {
			return null;
		}
		
		oReportClientDocument.getReportDefController().getReportObjectController().modify(oReportObject, oReportObjectCopy);
		return oReportObjectCopy;

	}

	/******************************************************************
	 * 
	 * Set the font of the ReportObject if it is a TextObject.
	 *               
	 ******************************************************************/	
	private ReportObject setFont (ReportObject oReportObject, String fontname) throws ReportSDKException
	{

		// make a copy of the ReportObject and make our changes to the copy
		// as per the model/view/controller archetecture.
 		ReportObject oReportObjectCopy = (ReportObject)oReportObject.clone(true);
		if (oReportObject instanceof TextObject) {
			((TextObject)oReportObjectCopy).getFontColor().getIFont().setName(fontname);
		} else if (oReportObject instanceof FieldObject) {
			((FieldObject)oReportObjectCopy).getFontColor().getIFont().setName(fontname);
		} else {
			return null;
		}
		
		oReportClientDocument.getReportDefController().getReportObjectController().modify(oReportObject, oReportObjectCopy);
		return oReportObjectCopy;
		
		
		
	}

	/******************************************************************
	 * 
	 * Set the font size of the ReportObject if it is a TextObject.
	 *               
	 ******************************************************************/	
	private ReportObject setFontSize (ReportObject oReportObject, int size) throws ReportSDKException
	{
		// make a copy of the ReportObject and make our changes to the copy
		// as per the model/view/controller archetecture.
 		ReportObject oReportObjectCopy = (ReportObject)oReportObject.clone(true);
		if (oReportObject instanceof TextObject) {
			((TextObject)oReportObjectCopy).getFontColor().getIFont().setSize(size);
		} else if (oReportObject instanceof FieldObject) {
			((FieldObject)oReportObjectCopy).getFontColor().getIFont().setSize(size);
		} else {
			return null;
		}

		oReportClientDocument.getReportDefController().getReportObjectController().modify(oReportObject, oReportObjectCopy);
		return oReportObjectCopy;

	}

	/******************************************************************
	 * 
	 * Set alignment value of the ReportObject if it is a TextObject.
	 *               
	 ******************************************************************/	
	private ReportObject setAlignment (ReportObject oReportObject, Alignment alignment) throws ReportSDKException
	{
		// make a copy of the ReportObject and make our changes to the copy
		// as per the model/view/controller archetecture.
		if (oReportObject instanceof TextObject) {
			TextObject oReportObjectCopy = (TextObject)oReportObject.clone(true);
			Paragraphs oParagraphs = oReportObjectCopy.getParagraphs();
			IParagraph oParagraph;
			for (int i = 0; i < oParagraphs.size(); i++) {
				oParagraph = oParagraphs.getParagraph(i);
				oParagraph.setAlignment(alignment);
			}
			oReportClientDocument.getReportDefController().getReportObjectController().modify(oReportObject, oReportObjectCopy);
			return oReportObjectCopy;
		} else {
			return null;
		}
	}


	// I figure it's popular enough that people use a constance border
	// to have a convience method.
	public ReportObject setBorder(ReportObject oReportObject, 
				      LineStyle linestyle,
				      java.awt.Color color,
				      boolean drop_shadow) throws ReportSDKException
	{
		return setBorder(oReportObject, linestyle, linestyle, linestyle, linestyle, color, drop_shadow);
	}

	/******************************************************************
	 * 
	 * Set the border of the ReportObject.
	 *               
	 ******************************************************************/	
	public ReportObject setBorder(ReportObject oReportObject, 
				      LineStyle left, LineStyle right, 
				      LineStyle top, LineStyle bottom,
				      java.awt.Color color,
				      boolean drop_shadow) throws ReportSDKException
	{
		IBorder oBorder = (IBorder) new Border();
		oBorder.setLeftLineStyle(left);
		oBorder.setRightLineStyle(right);
		oBorder.setTopLineStyle(top);
		oBorder.setBottomLineStyle(bottom);
		if (color != null) {
			oBorder.setBorderColor(color);
		}
		oBorder.setHasDropShadow(drop_shadow);
		
		ReportObject oReportObjectCopy = (ReportObject)oReportObject.clone(true);
		oReportObjectCopy.setBorder(oBorder);
		oReportClientDocument.getReportDefController().getReportObjectController().modify(oReportObject, oReportObjectCopy);
		
		return oReportObjectCopy;
	}
	
}	
%>
