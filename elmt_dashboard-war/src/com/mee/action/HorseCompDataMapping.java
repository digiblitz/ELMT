/*
 * HorseCompDataMapping.java
 *
 * Created on March 27, 2007, 11:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mee.action;

import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.mee.actionform.HorseCompDataMapActionForm;
import com.hlcmsg.util.Debug;
import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.*;
import org.apache.struts.util.MessageResources;
import java.sql.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 *
 * @author karthikeyan
 */

public class HorseCompDataMapping extends Action {
    
    String fwd="";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try {
            HttpSession session = request.getSession();
            
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.icp");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            
            HorseCompDataMapActionForm  horseCompForm = (HorseCompDataMapActionForm) form;
            FormFile fileUpload = horseCompForm.getFileUpload();
            String process = request.getParameter("process");
            Debug.print("process :"+process);
            
            if(process.equalsIgnoreCase("viewUpload")) {
                ArrayList eventDetails = new ArrayList();
                
                eventDetails = remote.getEventTypeDetails();
                Debug.print("eventDetails.size() in servlet :"+eventDetails.size());
                
                request.setAttribute("eventDetails",eventDetails);
                
                fwd = "viewUpload";
                
            }
            
            if(process.equalsIgnoreCase("upload")) {
                //upload the  file
                
                String imagePath=mr.getMessage("horseComp_uploadPath");
                String fullFilePath = "";
                
                String eventId = request.getParameter("eventId");
                String eventName = request.getParameter("eventName");
                String eventTypeId = request.getParameter("eventType");
                
                Debug.print("eventId :"+eventId);
                Debug.print("eventName :"+eventName);
                Debug.print("eventTypeId :"+eventTypeId);
                
                 /*String rootDir = getServlet().getServletContext().getRealPath(File.separator+ROOTDIR);
                 Debug.print("RootDir="+rootDir);
                 String directoryTemp = rootDir+java.io.File.separator +"temp";
                 String fileName = fileUpload.getFileName();
                 String imageFilePath = directoryTemp+File.separator+fileName;
                 String imagePath = File.separator+ROOTDIR+File.separator +"temp"+File.separator+fileName;*/
                
                String fileName = fileUpload.getFileName();
                Debug.print("fileName :"+fileName);
                
                String[] tmpFileName = fileName.split("\\.");
                String finalFileName = "";
                
                Debug.print("tmpFileName.length :"+tmpFileName.length);
                
                for(int i=0;i<tmpFileName.length-1;i++) {
                    Debug.print("splitted file names :"+tmpFileName[i]);
                    
                    if(finalFileName.trim().length() != 0) {
                        finalFileName = finalFileName+"."+tmpFileName[i];
                    } else {
                        finalFileName = tmpFileName[i];
                    }
                }
                
                Debug.print("concatinated file name :"+finalFileName);
                
                java.util.Date curDat = new java.util.Date();
                fileName = finalFileName+" "+curDat.getTime()+"."+tmpFileName[tmpFileName.length-1];
                Debug.print("final Time Stamped fileName :"+fileName);
                
                Debug.print("ImagePath="+imagePath);
                BufferedOutputStream outFile = null;
                String imageFilePath = imagePath+File.separator+fileName;
                Debug.print("imageFilePath :"+imageFilePath);
                
                try {
                    //creating the temp dir if not exists
                    File fDir = new File(imagePath);
                    if (!fDir.exists()) {
                        if (!fDir.mkdir()) {
                            throw new IOException("Cannot create tmpdir for dimension file");
                        }
                    }
                    File newFile = new File(imageFilePath);
                    fullFilePath = newFile.getAbsolutePath();
                    Debug.print("NewFilePath ="+newFile.getAbsolutePath());
                    outFile = new BufferedOutputStream(
                            new FileOutputStream(newFile));
                    outFile.write(fileUpload.getFileData());
                    
                    String uploadId = remote.insertCompResultUploadDetails(eventId,eventName,fullFilePath,eventTypeId);
                    Debug.print("remote.insertCompResultUploadDetails(eventId,eventName,fullFilePath) uploadId :"+uploadId);
                    request.setAttribute("uploadId",uploadId);
                    
                } catch (IOException ie) {
                    Debug.print("Error while writing into the file");
                } finally {
                    if(outFile!=null){
                        outFile.flush();
                        outFile.close();
                    }
                }
                
                request.setAttribute("fileName",fullFilePath);
                fwd="uploadConf";
                
            }
            
            if(process.equalsIgnoreCase("Map")) {
                Vector excel = new Vector();
                Vector excelHead = new Vector();
                String stringValue="";
                
                String fileName = (String)request.getParameter("fileName");
                Debug.print("fileName :"+fileName);
                
                String uploadId = (String)request.getParameter("uploadId");
                Debug.print("uploadId :"+uploadId);
                
                session.setAttribute("uploadId",uploadId);
                
                InputStream input = new FileInputStream(fileName);
                
                try {
                    
                /*String imagePath=mr.getMessage("horseComp_uploadPath");
                String imageFilePath = imagePath+File.separator+fileName;
                Debug.print("full folder path :"+imageFilePath);*/
                    
                    //InputStream input = HorseCompDataMapping.class.getResourceAsStream(fileName);
                    POIFSFileSystem fs = new POIFSFileSystem(input);
                    HSSFWorkbook wb = new HSSFWorkbook(fs);
                    HSSFSheet sheet = wb.getSheetAt(0);
                    
                    // Iterate over each row in the sheet
                    Iterator rows = sheet.rowIterator();
                    int j = 0;
                    
                    while( rows.hasNext() ) {
                        HSSFRow row = (HSSFRow) rows.next();
                        Integer rowNumber = new Integer(row.getRowNum());
                        
                        int i = rowNumber.intValue();
                        
                        // Iterate over each cell in the row and print out the cell's content
                        Iterator cells = row.cellIterator();
                        //Debug.print("               Row Number "+i);
                        if(i!=0){
                            
                            //String[] excelAdd = new String[c];
                            ArrayList excelAdd = new ArrayList();
                            
                            while( cells.hasNext() ) {
                                
                                HSSFCell cell = (HSSFCell) cells.next();
                                Integer cellNumber = new Integer(cell.getCellNum());
                                int c = cellNumber.intValue();
                                //Debug.print("cellNumber.intValue() for rows :"+cellNumber.intValue());
                                
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                        Double numericValue = new Double(cell.getNumericCellValue());
                                        //Debug.print("Numeric Value "+numericValue);
                                        stringValue = String.valueOf(numericValue);
        
                                       /* int position = stringValue.indexOf('.');
                                        Debug.print("DOT position "+position);
                                        
                                        if(position>0){
                                            stringValue = String.valueOf(numericValue);
                                        }
                                        else{
                                            int value = numericValue.intValue();
                                            stringValue = String.valueOf(value);
                                        }*/
                                        //excel.add(numericValue);
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_STRING:
                                        stringValue = (String)cell.getStringCellValue();
                                        //excel.add(stringValue);
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_FORMULA:
                                        stringValue = "";//(String)cell.getCellFormula();
                                        //excel.add(stringValue);
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_BOOLEAN:
                                        stringValue = String.valueOf(cell.getBooleanCellValue());
                                        //excel.add(stringValue);
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_BLANK:
                                        stringValue = "";
                                        //excel.add(strValue);
                                        
                                    default:
                                        //System.out.println("unsuported Cell type in Rows");
                                        break;
                                        
                                        
                                }
                                //Debug.print("j value:"+j);
                                //excelAdd[j] = stringValue;
                                //j++;
                                excelAdd.add(stringValue);
                                
                                Debug.print("               stringValue "+stringValue);
                                //Debug.print("excelAdd.size() :"+excelAdd.size());
                            }
                            
                            excel.add(excelAdd);
                        }
                        if(i==0){
                            while( cells.hasNext() ) {
                                HSSFCell cell = (HSSFCell) cells.next();
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                        Double numericValue = new Double(cell.getNumericCellValue());
                                        
                                        int value = numericValue.intValue();
                                        stringValue = String.valueOf(value);
                                        
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_STRING:
                                        stringValue = (String)cell.getStringCellValue();
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_FORMULA:
                                        stringValue = "";//(String)cell.getCellFormula();
                                        //excel.add(stringValue);
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_BOOLEAN:
                                        stringValue = String.valueOf(cell.getBooleanCellValue());
                                        //excel.add(stringValue);
                                        break;
                                        
                                    case HSSFCell.CELL_TYPE_BLANK:
                                        stringValue = "";
                                        
                                    default:
                                        System.out.println("unsuported Cell type in Column");
                                        break;
                                }
                                
                                excelHead.add(stringValue);
                                Debug.print("head stringValue added :"+stringValue);
                                
                            }
                        }
                    }
                    
                    Debug.print("columns excelHead.size() :"+excelHead.size());
                    Debug.print("rows excel.size() :"+excel.size());
                    
                    ArrayList tblDetails = new ArrayList();
                    tblDetails = remote.getCompResultMappingTableDetails(uploadId);
                    Debug.print("tblDetails.size() in servlet : "+tblDetails.size());
                    
                    request.setAttribute("tblDetails",tblDetails);
                    
                    request.setAttribute("excelHead",excelHead);
                    request.setAttribute("excelRows",excel);
                    
                    fwd = "mapColumns";
                    
                } catch(Exception e) {
                    fwd = "formatInfo";
                    Debug.print("Exception while retrieving data frm Excel :"+e);
                }
                
                finally {
                    input.close();
                }
            }
            
            if(process.equalsIgnoreCase("listUploadDetails")) {
                Debug.print("inside listUploadDetails block :");
                
                String dispStat = request.getParameter("dispStat");
                Debug.print("dispStat :"+dispStat);
                ArrayList compFileDetails = new ArrayList();
                String year = request.getParameter("year");
                
                if(dispStat == null) {
                    compFileDetails = remote.listCompResultUploadDetails();
                } else {
                    compFileDetails = remote.listCompResultUploadDetailsOnYear(year);
                }
                
                Debug.print("compFileDetails.size() in Servlet : "+compFileDetails.size());
                request.setAttribute("compFileDet",compFileDetails);
                request.setAttribute("year",year);
                
                fwd = "listUploadDetails";
            }
            
            if(process.equalsIgnoreCase("insertMapDatas")) {
                Debug.print("inside insertMapDatas block :");
                
                String uploadId = (String)session.getAttribute("uploadId");
                Debug.print("session.getAttribute(uploadId) :"+uploadId);
                
                try {
                    
                    int dropCt = Integer.parseInt(request.getParameter("dropCt"));
                    Debug.print("drop down count is :"+dropCt);
                    
                    String[] dropValue = new String[dropCt];
                    String[] dBValue = new String[dropCt];
                    String dBNameValue ="";
                    
                    for(int i=0;i<dropCt;i++) {
                        String dropName = "colSel"+String.valueOf(i);
                        Debug.print("dropName :"+dropName);
                        
                        dropValue[i] = request.getParameter(dropName);
                        Debug.print("dropValue :"+dropValue[i]);
                        
                        dBNameValue = "dbVal"+String.valueOf(i);
                        Debug.print("dBName :"+dBNameValue);
                        
                        dBValue[i] = request.getParameter(dBNameValue);
                        Debug.print("dBValue :"+dBValue[i]);
                    }
                    
                    Vector excelRows = new Vector();
                    ArrayList excelRow = new ArrayList();
                    
                    excelRows = (Vector)session.getAttribute("excelRows");
                    Debug.print("excelRows.size() in Servlet : "+excelRows.size());
                    
                    for(int k=0;k<excelRows.size();k++) {
                        excelRow = (ArrayList)excelRows.get(k);
                        Debug.print("excel Row "+k+" contents size :"+excelRow.size());
                        
                        StringBuffer query = new StringBuffer();
                        
                        if(dBValue!=null && dBValue.length!=0){
                            
                            query.append("insert into tblMeeTempCompResults (upload_id, "+dBValue[0]);
                            
                            for(int j=1;j<dBValue.length;j++) {
                                query.append(", "+dBValue[j]);
                            }
                            query.append(") values (\'"+uploadId+"\',");
                            
                        }
                        
                        if(dropValue!=null &&dropValue.length!=0 && excelRows.size()!=0) {
                            if(dropValue[0]!=null && dropValue[0].trim().length()!=0) {
                                //ArrayList firstRow = (ArrayList)excelRows.get(k);
                                
                                int pos = Integer.parseInt(dropValue[0]);
                                Debug.print("Postion is "+pos);
                                String tmpValue = (String)excelRow.get(pos);
                                Debug.print("                       First Value To Query   "+tmpValue);
                                query.append("\'"+tmpValue.replaceAll("'","''")+"\'");
                            } else {
                                query.append("\' \'");
                            }
                        }
                        
                        for(int x=1;x<dropValue.length;x++) {
                            
                            String positionValue ="";
                            
                            if(dropValue[x] != null && dropValue[x].trim().length()!=0) {
                                int pos = Integer.parseInt(dropValue[x]);
                                positionValue = (String)excelRow.get(pos);
                                
                                query.append(",\'"+positionValue.replaceAll("'","''")+"\'");
                            } else {
                                query.append(",\' \'");
                                //query.append(""+null+"");
                            }
                        }
                        query.append(")");
                        
                        String finalQuery = query.toString();
                        // Debug.print(" Generated Query in servlet :"+finalQuery);
                        
                        boolean stat1 = remote.insertCompResultDetailsByQuery(finalQuery);
                        Debug.print("insertCompResultDetailsByQuery(finalQuery) statue in servlet for record no -"+k+" :"+stat1);
                        
                    }
                    
                    
                    
                /*for(int k=0;k<excelRows.size();k++){
                 
                    HLCCompResultVO = new HLCCompResultVO();
                    status = false;
                 
                    excelRow = (ArrayList)excelRows.get(k);
                    Debug.print("excel Row "+k+" contents size :"+excelRow.size());
                 
                 
                        if(dropValue[k] != null && dropValue[k].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[k]);
                            String positionValue = (String)excelRow.get(pos);
                 
                            if(dBNameValue.trim().equalsIgnoreCase("final_place")){
                                HLCCompResultVO.setFinal_place(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("phase_D_inspection")){
                                HLCCompResultVO.setPhase_D_inspection(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("dressage_place")){
                                HLCCompResultVO.setDressage_place(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("xc_phaseD_elapsed_time")){
                                HLCCompResultVO.setXc_phaseD_elapsed_time(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("show_jump_jump_penalties")){
                                HLCCompResultVO.setShow_jump_jump_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("road_and_track_C")){
                                HLCCompResultVO.setRoad_and_track_C(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("road_and_track_A")){
                                HLCCompResultVO.setRoad_and_track_A(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("dangerous_riding_penalties")){
                                HLCCompResultVO.setDangerous_riding_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("final_points")){
                                HLCCompResultVO.setFinal_points(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("pinney_number")){
                                HLCCompResultVO.setPinney_number(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("steeplechase_time_penalties")){
                                HLCCompResultVO.setSteeplechase_time_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("xc_phaseD_jump_penalties")){
                                HLCCompResultVO.setXc_phaseD_time_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("to_date_points")){
                                HLCCompResultVO.setTo_date_points(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("show_jump_time_penalties")){
                                HLCCompResultVO.setShow_jump_time_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("hlc_points")){
                                HLCCompResultVO.setUsea_points(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("event_id")){
                                HLCCompResultVO.setEvent_id(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("steeplechase_jump_penalties")){
                                HLCCompResultVO.setSteeplechase_jump_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("xc_phaseD_time_penalties")){
                                HLCCompResultVO.setXc_phaseD_time_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("last_inspection")){
                                HLCCompResultVO.setLast_inspection(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("dressage_penalties")){
                                HLCCompResultVO.setDressage_penalties(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("to_date_place")){
                                HLCCompResultVO.setTo_date_place(positionValue);
                            }
                            if(dBNameValue.trim().equalsIgnoreCase("first_inspection")){
                                HLCCompResultVO.setFirst_inspection(positionValue);
                            }
                       }*/
                    
                    
                       /* if(dropValue[1] != null && dropValue[1].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[1]);
                            String event_type = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setEvent_type(event_type);
                        }
                        
                        if(dropValue[2] != null && dropValue[2].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[2]);
                            String event_level = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setEvent_level(event_level);
                        }
                        
                        if(dropValue[3] != null && dropValue[3].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[3]);
                            String event_division = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setEvent_division(event_division);
                        }
                        
                        if(dropValue[4] != null && dropValue[4].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[4]);
                            String starters = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setStarters(starters);
                        }
                        
                        if(dropValue[5] != null && dropValue[5].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[5]);
                            String horse_name = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setHorse_name(horse_name);
                        }
                        
                        if(dropValue[6] != null && dropValue[6].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[6]);
                            String horse_member_id = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setHorse_member_id(horse_member_id);
                        }
                        
                        if(dropValue[7] != null && dropValue[7].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[7]);
                            String rider_name = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setRider_name(rider_name);
                        }
                        
                        if(dropValue[8] != null && dropValue[8].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[8]);
                            String rider_member_id = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setRider_member_id(rider_member_id);
                        }
                        
                        if(dropValue[9] != null && dropValue[9].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[9]);
                            String pinney_number = (String)excelRow.get(pos);
                        
                           HLCCompResultVO.setPinney_number(pinney_number);
                        }
                        
                        if(dropValue[10] != null && dropValue[10].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[10]);
                            String dressage_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setDressage_penalties(dressage_penalties);
                        }
                        
                        if(dropValue[11] != null && dropValue[11].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[11]);
                            String dressage_place = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setDressage_place(dressage_place);
                        }
                        
                        if(dropValue[12] != null && dropValue[12].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[12]);
                            String xc_phaseD_jump_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setXc_phaseD_jump_penalties(xc_phaseD_jump_penalties);
                        }
                        
                         if(dropValue[13] != null && dropValue[13].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[13]);
                            String xc_phaseD_elapsed_time = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setXc_phaseD_elapsed_time(xc_phaseD_elapsed_time);
                        }
                        
                        if(dropValue[14] != null && dropValue[14].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[14]);
                            String xc_phaseD_time_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setXc_phaseD_time_penalties(xc_phaseD_time_penalties);
                        }
                        
                        if(dropValue[15] != null && dropValue[15].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[15]);
                            String show_jump_time_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setShow_jump_time_penalties(show_jump_time_penalties);
                        }
                        
                        if(dropValue[16] != null && dropValue[16].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[16]);
                            String show_jump_jump_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setShow_jump_jump_penalties(show_jump_jump_penalties);
                        }
                        
                        if(dropValue[17] != null && dropValue[17].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[17]);
                            String to_date_points = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setTo_date_points(to_date_points);
                        }
                        
                        if(dropValue[18] != null && dropValue[18].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[18]);
                            String to_date_place = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setTo_date_place(to_date_place);
                        }
                        
                        if(dropValue[19] != null && dropValue[19].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[19]);
                            String dangerous_riding_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setDangerous_riding_penalties(dangerous_riding_penalties);
                        }
                        
                        if(dropValue[20] != null && dropValue[20].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[20]);
                            String final_points = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setFinal_points(final_points);
                        }
                        
                        if(dropValue[21] != null && dropValue[21].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[21]);
                            String final_place = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setFinal_place(final_place);
                        }
                        
                        if(dropValue[22] != null && dropValue[22].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[22]);
                            String first_inspection = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setFirst_inspection(first_inspection);
                        }
                        
                        if(dropValue[23] != null && dropValue[23].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[23]);
                            String last_inspection = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setLast_inspection(last_inspection);
                        }
                        
                        if(dropValue[24] != null && dropValue[24].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[24]);
                            String phase_D_inspection = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setPhase_D_inspection(phase_D_inspection);
                        }
                        
                        if(dropValue[25] != null && dropValue[25].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[25]);
                            String road_and_track_A = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setRoad_and_track_A(road_and_track_A);
                        }
                        
                        if(dropValue[26] != null && dropValue[26].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[26]);
                            String road_and_track_C = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setRoad_and_track_C(road_and_track_C);
                        }
                        
                        if(dropValue[27] != null && dropValue[27].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[27]);
                            String steeplechase_jump_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setSteeplechase_jump_penalties(steeplechase_jump_penalties);
                        }
                        
                        if(dropValue[28] != null && dropValue[28].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[28]);
                            String steeplechase_time_penalties = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setSteeplechase_time_penalties(steeplechase_time_penalties);
                        }
                        
                        if(dropValue[29] != null && dropValue[29].trim().length()!=0)
                        {
                            int pos = Integer.parseInt(dropValue[29]);
                            String hlc_points = (String)excelRow.get(pos);
                        
                            HLCCompResultVO.setUsea_points(hlc_points);
                        }*/
                    
                    boolean status = remote.updateCompResultEventDet(uploadId);
                    Debug.print("updateCompResultEventDet(uploadId) in servlet:"+status);
                    
                    boolean stat = remote.changeEventMapStatus(uploadId);
                    Debug.print("changeEventMapStatus(uploadId) :"+stat);
                    
                    fwd = "mappingConf";
                } catch(Exception e) {
                    Debug.print("generel exception in servlet insertMapDatas block :");
                    e.printStackTrace();
                    
                    boolean stat2 = remote.deleteCompResultMappedDatas(uploadId);
                    Debug.print("deleteCompResultMappedDatas(uploadId) status in servlet while exception caught :"+stat2);
                    
                    fwd = "mappingError";
                    
                }
            }
            
            if(process.equalsIgnoreCase("Delete")) {
                Debug.print("inside process Delete block :");
                
                try {
                    String fileName = (String)request.getParameter("fileName");
                    Debug.print("fileName :"+fileName);
                    
                    String uploadId = (String)request.getParameter("uploadId");
                    Debug.print("uploadId :"+uploadId);
                    
                    File fd = new File(fileName);
                    boolean delStat = fd.delete();
                    Debug.print("file delete status :"+delStat);
                                       
                    boolean stat1 = remote.deleteCompResultDetails(uploadId);
                    Debug.print("deleteCompResultFileDetails(uploadId) status in servlet :"+stat1);
                    
                    boolean grpStat = remote.deleteGroupResultDetails(uploadId);
                    Debug.print("deleteGroupResultDetails(uploadId) status in servlet :"+grpStat);
                    
                    boolean stat2 = remote.deleteCompResultMappedDatas(uploadId);
                    Debug.print("deleteCompResultMappedDatas(uploadId) status in servlet :"+stat2);
                    
                    boolean stat = remote.deleteCompResultFileDetails(uploadId);
                    Debug.print("deleteCompResultFileDetails(uploadId) status in servlet :"+stat);
                    
                } catch(Exception e) {
                    Debug.print("caught an exception in servlet : "+e);
                }
                
                fwd = "delConf";
            }
            
        } catch(Exception e) {
            Debug.print("caught an exception in servlet : "+e);
            
        }
        
        return mapping.findForward(fwd);
    }
    
}
