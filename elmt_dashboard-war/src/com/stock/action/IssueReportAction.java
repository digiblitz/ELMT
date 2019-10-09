/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.stock.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;



public class IssueReportAction extends Action {
	  private InputStream fileinputstream;

	   /* public String Export_Excel(ArrayList inwardList) {
	        try {

	            SummaryReport sp = new SummaryReport();
	            List result = sp.getSummaryDetail();
	            sp = null;
	            Export_to_Excel(result);
	        } catch (Exception e) {
	            System.out.println("Error on Export_Excel=" + e.getMessage());
	        }
	        return "success";
	    }*/

	    public void Export_to_Excel(ArrayList issReport_List, String Fdate, String Tdate) {
	        try {
	            String path = "C:\\LMS\\Report\\Issue";
	            String currentdate = getDate();//
	            //String filename = "InwardReport(From : " + Fdate + " - To : "+ Tdate+").xls";
	            String filename = "IssueReport("+currentdate+").xls";
	            String filepath = path + "\\" + filename;
	            	//  System.out.println("filepath==" + filepath);
	            File file = new File(path);
	            if (file.exists()) {
	                CreateExcelSheet(filepath, issReport_List, file);
	            } else {
	                file.mkdirs();
	                CreateExcelSheet(filepath, issReport_List, file);
	            }
	        } catch (Exception e) {
	            System.out.println("Error on Export_to_Excel===" + e.getMessage());
	        }
	       
	    }

	   /* private String getFilePath() {
	        String revertback = null;
	        try {
	        	
	            ServletContext context = ActionContext.getServletContext();
	            String path = context.getRealPath("/") + "\\Report\\Summary";
	            context = null;
	            revertback = path;
	        } catch (Exception e) {
	            System.out.println("Error on getFilePath===" + e.getMessage());
	        }
	        return revertback;
	    }*/

	    private void CreateExcelSheet(String path, ArrayList issReport_List, File file) {
	        try {
	           // Vector vect = new Vector();
	            	// System.out.println("CreateExcelSheet");
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            	// System.out.println("Workbook Created");
	            HSSFSheet sheet = workbook.createSheet("IssueReport");
	            	//System.out.println("Create  Sheet");
	            //int row_end = inwardList.size();
	            	//System.out.println("row_end==" + row_end);
	            //vect = getListArrayValues(inwardList);
	            	// System.out.println("vect size==" + vect.size());
	            //int col_end = 10;
	            //int index = 0;
	            //================================Add header========================//

	            HSSFRow head_row = sheet.createRow(0);

	            HSSFCell head_cell = head_row.createCell((short) 0);
	            HSSFCell head_cell1 = head_row.createCell((short) 1);
	            HSSFCell head_cell2 = head_row.createCell((short) 2);
	            HSSFCell head_cell3 = head_row.createCell((short) 3);
	            HSSFCell head_cell4 = head_row.createCell((short) 4);
	            HSSFCell head_cell5 = head_row.createCell((short) 5);
	            HSSFCell head_cell6 = head_row.createCell((short) 6);
	            HSSFCell head_cell7 = head_row.createCell((short) 7);
	            HSSFCell head_cell8 = head_row.createCell((short) 8);
	           
	            
	            
	            head_cell.setCellValue("Sl.NO");
	            head_cell1.setCellValue("Issued Date");
	            head_cell2.setCellValue("Centre Code");
	            head_cell3.setCellValue("Centre Name");
	            head_cell4.setCellValue("Program Code");
	            head_cell5.setCellValue("Program Name");
	            head_cell6.setCellValue("Medium");
	            head_cell7.setCellValue("Academic Year");
	            head_cell8.setCellValue("Issued Quantity");
	          


	            /*
	            for (int i = 1; i <= row_end; i++) {
	                HSSFRow row = sheet.createRow(i);
	                for (short j = 0; j < col_end; j++) {
	                    HSSFCell cell = row.createCell(j);
	                    String val = vect.get(index).toString();
	                    // System.out.println("values=="+val.toString()+"   index="+index);
	                    cell.setCellValue(val);
	                    index++;
	                }

	            }*/
	            

			
			if(issReport_List!=null && issReport_List.size()!=0)
			{
				Iterator itInList = issReport_List.iterator();
				int SI_No = 1;
				
				while(itInList.hasNext())
				{
					HSSFRow row = sheet.createRow(SI_No);
					
					String[] InList = (String [])itInList.next();
					
				
					 for (short j = 0; j <= InList.length; j++) 
					 {
						 HSSFCell cell = row.createCell(j);
						 
						 if(j==0)
						 {
							 cell.setCellValue(SI_No); 
						 }
						 else
						 {
							 cell.setCellValue(InList[j-1]); 
							
						 }			
						 
					 }
		           	 
		           	SI_No++;
				}
			}

	            FileOutputStream fout = new FileOutputStream(new File(path));
	            workbook.write(fout);
	            fout.close();
	            //vect = null;
	            //  System.out.println("Excel written successfully..");
	            //Download_SummaryReportFile(path);

	        } catch (Exception e) {
	            System.out.println("Error on CreateExcelSheet==" + e.getMessage());
	        }

	    }

	    private String getDate() {
	        String revertback = null;
	        try {
	            // System.out.println("getDate");
	            DateFormat date = new SimpleDateFormat("MM-yyyy");
	            Calendar cal = Calendar.getInstance();
	            revertback = date.format(cal.getTime());
	        } catch (Exception e) {
	            System.out.println("ERror on getDate==" + e.getMessage());
	        }
	        return revertback;
	    }
	    /*
	    private Vector getListArrayValues(List result) {
	        Vector revertback = new Vector();

	        try {
	            // System.out.println("getListArrayValues");
	            Iterator itr = result.iterator();
	            while (itr.hasNext()) {
	                Object[] val = (Object[]) itr.next();
	                revertback.add(val[0]);
	                revertback.add(val[1]);
	                revertback.add(val[2]);
	                revertback.add(val[3]);
	                revertback.add(val[4]);
	                revertback.add(val[5]);
	                revertback.add(val[6]);
	                revertback.add(val[7]);
	                revertback.add(val[8]);
	            }
	            result = null;

	        } catch (Exception e) {
	        }
	        return revertback;
	    }*/

	    private void Download_SummaryReportFile(String path) {
	        try {
	            // System.out.println("Download_SummaryReportFile..");
	            fileinputstream = new FileInputStream(new File(path));
	            // System.out.println("Downloaded.....");

	        } catch (Exception e) {
	            System.out.println("Error on Download_SummaryReportFile==" + e.getMessage());
	        }
	    }

	    public InputStream getFileinputstream() {
	        return fileinputstream;
	    }

	    public void setFileinputstream(InputStream fileinputstream) {
	        this.fileinputstream = fileinputstream;
	    }
}
