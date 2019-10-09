/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msg.action;

import com.hlccommon.util.*;

import com.mrm.actionform.ReportActionForm;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author parteek
 */
public class ActionReport extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String msg = request.getParameter("message");
        System.out.println("Message cmg ============="+msg);
         HttpSession session = request.getSession(true);
        if (msg.equalsIgnoreCase("xlsToGraph") && msg != null) {
            String fileName = null;
            ReportActionForm fbean = (ReportActionForm) form;
            String reportTitle = fbean.getReportTitle();
            FormFile uploadFile = (FormFile) fbean.getXlsFile();

            String chartType = fbean.getChartType();
            if (uploadFile != null) {
                String directoryTemp = "C:\\upload\\";
                fileName = uploadFile.getFileName();
                String uploadFilePath = directoryTemp + fileName;
                Debug.print("ImagePath=" + uploadFile);
                BufferedOutputStream outFile = null;

                try {
                    //creating the temp dir if not exists
                    File fDir = new File(directoryTemp);
                    if (!fDir.exists()) {
                        if (!fDir.mkdir()) {
                            throw new IOException("Cannot create tmpdir for dimension file");
                        }
                    }
                    File newFile = new File(uploadFilePath);
                    Debug.print("NewFilePath =" + newFile.getAbsolutePath());
                    outFile = new BufferedOutputStream(
                            new FileOutputStream(newFile));
                    outFile.write(uploadFile.getFileData());

                } catch (IOException ie) {
                    Debug.print("Error while writing into the file");
                } finally {
                    if (outFile != null) {
                        outFile.flush();
                        outFile.close();
                    }
                     }
                
                Vector dataHolder=xlsDataExtractor(uploadFilePath);
                                 
                //Print the data read
                System.out.println("Data Holder"+dataHolder.size());
    	        Vector patientNumber=printCellDataToConsole(dataHolder);
                 System.out.println("Number of patients"+patientNumber);
                session.setAttribute("patient",patientNumber);
                System.out.println("Size of patient"+patientNumber.size());
                session.setAttribute("title",reportTitle);
                System.out.println("Chart Type---------"+chartType);
               if (chartType.equalsIgnoreCase("Bar"))
               {
                    System.out.println("Inside Bar");
                   return mapping.findForward("xlsToBarGraph");
               }
                else if(chartType.equalsIgnoreCase("Pie"))
                {
                     System.out.println("Inside Pie");
                     return mapping.findForward("xlsToPieGraph");
                }
                else if(chartType.equalsIgnoreCase("3D Pie")) {
                    System.out.println("Inside 3D");
                     return mapping.findForward("xlsTo3DGraph"); }
                else if(chartType.equalsIgnoreCase("WaterFall"))
                {
                    ArrayList dataHolderNew= (ArrayList)xlsDataExtractorForLine(uploadFilePath);
                     session.setAttribute("dataholder",dataHolderNew);
                    return mapping.findForward("xlsToWaterFall");
                }
                else 
                {
                     
                      ArrayList dataHolderNew= (ArrayList)xlsDataExtractorForLine(uploadFilePath);
                      ArrayList arr=(ArrayList)fetchingForXYLineFromXLS(dataHolderNew);
                      session.setAttribute("dataholder",dataHolderNew);
                      
                     
                     return mapping.findForward("xlsToLineGraph");
                }
                
            }
            
        }
 return null;
    }
    
         public Vector xlsDataExtractor(String filePath)
         {
              /** --Define a Vector
    	 	--Holds Vectors Of Cells
    	 */
        System.out.println("FilePath is:In action:"+filePath);   
             
    	Vector cellVectorHolder = new Vector();

    	try{
    	/** Creating Input Stream**/
    	
    	FileInputStream myInput = new FileInputStream(filePath);

    	/** Create a POIFSFileSystem object**/
    	POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

    	/** Create a workbook using the File System**/
         HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

         /** Get the first sheet from workbook**/
        HSSFSheet mySheet = myWorkBook.getSheetAt(0);

        /** We now need something to iterate through the cells.**/
          Iterator rowIter = mySheet.rowIterator(); 

          while(rowIter.hasNext()){
        	  HSSFRow myRow = (HSSFRow) rowIter.next();
        	  Iterator cellIter = myRow.cellIterator();
        	  Vector cellStoreVector=new Vector();
        	  while(cellIter.hasNext()){
        		  HSSFCell myCell = (HSSFCell) cellIter.next();
        		  cellStoreVector.addElement(myCell);
        	  }
        	  cellVectorHolder.addElement(cellStoreVector);
          }
    	}catch (Exception e){e.printStackTrace(); }
    	return cellVectorHolder;
              
             
         }
         private static Vector printCellDataToConsole(Vector dataHolder) {

		Vector patients=new Vector();
             for (int i=0;i<dataHolder.size(); i++){
                   Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
			for (int j=0; j < cellStoreVector.size();j++){
                            System.out.println("CellStorevectro"+cellStoreVector.size());
				HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
				String stringCellValue = myCell.toString();
                                System.out.println(stringCellValue+"\t");
                                patients.add(cellStoreVector);
				
			}
                   System.out.println();
			
		}
                return patients;
         }
         
              private static ArrayList fetchingForXYLineFromXLS(ArrayList dataHolder) {

		ArrayList patients=new ArrayList();
             for (int i=0;i<dataHolder.size(); i++){
                   ArrayList cellStoreVector=(ArrayList)dataHolder.get(i);
			for (int j=0; j < cellStoreVector.size();j++){
                            System.out.println("CellStorevectro"+cellStoreVector.size());
				HSSFCell myCell = (HSSFCell)cellStoreVector.get(j);
				String stringCellValue = myCell.toString();
                                System.out.println(stringCellValue+"\t");
                                patients.add(cellStoreVector);
				
			}
                   System.out.println();
			
		}
                return patients;
         }
               public ArrayList xlsDataExtractorForLine(String filePath)
         {
              /** --Define a Vector
    	 	--Holds Vectors Of Cells
    	 */
        System.out.println("FilePath is:In action:"+filePath);   
             
    	ArrayList cellVectorHolder = new ArrayList();

    	try{
    	/** Creating Input Stream**/
    	
    	FileInputStream myInput = new FileInputStream(filePath);

    	/** Create a POIFSFileSystem object**/
    	POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

    	/** Create a workbook using the File System**/
         HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

         /** Get the first sheet from workbook**/
        HSSFSheet mySheet = myWorkBook.getSheetAt(0);

        /** We now need something to iterate through the cells.**/
          Iterator rowIter = mySheet.rowIterator(); 

          while(rowIter.hasNext()){
        	  HSSFRow myRow = (HSSFRow) rowIter.next();
        	  Iterator cellIter = myRow.cellIterator();
        	  ArrayList cellStoreVector=new ArrayList();
        	  while(cellIter.hasNext()){
        		  HSSFCell myCell = (HSSFCell) cellIter.next();
        		  cellStoreVector.add(myCell);
        	  }
        	  cellVectorHolder.add(cellStoreVector);
          }
    	}catch (Exception e){e.printStackTrace(); }
    	return cellVectorHolder;
              
             
         }
}
         
         
         
   


