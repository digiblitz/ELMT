/*
 * ExcelDataExport.java
 *
 * Created on October 13, 2006, 9:32 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.excel.export;

import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BoldStyle;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelDataExport {
    
    /** excel template file */
    private String templatefile = null;
    
    private String path;
    
    /** data to export */
    private List  datasource = null;
    
    /**
     * column names, excel columns should be in
     * the same order (and number) of table fields.
     */
    private String[] columnnames = null;
    
    /** response to write to */
    private HttpServletResponse response = null;
    
    /** output file name */
    private String outputfile;
    
    /** row number begin to write data */
    private int rowno;
    
    public void setColumnnames(String[] columnnames) {
        this.columnnames = columnnames;
    }
    
    public void setOutputfile(String outputfile) {
        this.outputfile = outputfile;
    }
    
      public void setPath(String path) {
        this.path = path;
    }
    
    public void setDatasource(List datasource) {
        this.datasource = datasource;
    }
    
    public void setInputfile(String templatefile) {
        this.templatefile = templatefile;
    }
    
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    
    public void setRowno(int rowno) {
        this.rowno = rowno;
    }
    
    /**
     * get file saved in WEB-INF/classes directory.
     *
     * @param fileName file name to be retrieved
     * @return File or null if the file is not exist
     */
    public File getResource(String fileName) {
        URL url = this.getClass().getResource("/" + fileName);
        return url != null ? new File(url.getFile()): null;
    }
    
     public File getResourceFile(String fileName) {
        return new File(fileName);
    }
    
    /**
     * Export data to excel file
     */
    public void export() {
        WritableWorkbook workbook;
        Label label;
        int currentRow = rowno;
        try {        
            // creates a readable spreadsheet using the excel template
            System.out.println("File Path :"+path+File.separator+templatefile);
            workbook = Workbook.createWorkbook(getResourceFile(path+File.separator+templatefile)); 
            WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
            
            WritableFont font = new WritableFont(WritableFont.createFont("Arial"), 10);
            WritableCellFormat format = new WritableCellFormat(font); 
            format.setAlignment(Alignment.LEFT);
            format.setVerticalAlignment(VerticalAlignment.JUSTIFY);
            format.setBorder(Border.ALL, BorderLineStyle.THIN);
            
            // write data to excel
            Iterator iterator = datasource.iterator();
            Map map = null;
            int cols = columnnames.length;
            int rowCount = 0;
            while (iterator.hasNext()) {
                sheet.insertRow(currentRow);
                map = (Map)iterator.next();
               //Adding column heading
               if(rowCount==0) {
                WritableFont  headFont = new WritableFont(WritableFont.createFont("Arial"), 10,WritableFont.BOLD);
                WritableCellFormat headFormat = new WritableCellFormat(headFont); 
                headFormat.setAlignment(Alignment.LEFT);               
                for (int i = 0; i < cols; i++) {
                   label = new Label(i, currentRow, "" + columnnames[i]);
                   label.setCellFormat(headFormat); 
                   sheet.addCell(label);                      
                 }
                  currentRow++;
               } 
                for (int i = 0; i < cols; i++) {
                    label = new Label(i, currentRow, "" + map.get(columnnames[i]).toString());
                    label.setCellFormat(format);
                    sheet.addCell(label);
                }
                currentRow++;
                rowCount++;
            }
            workbook.write();
            workbook.close();           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

