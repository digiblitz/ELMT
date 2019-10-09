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
package com.view.action;



import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

import com.hlccommon.util.HLCMenuListVO;
import com.hlccountry.mail.util.Debug;

import com.hlcrole.management.HLCBrahmaputraSessionRemote;
import com.hlcrole.management.HLCBrahmaputraSessionRemoteHome;


public class ViewAction extends Action {
	
	 public ActionForward execute(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	        try{
	        	 MessageResources mr=getResources(request);
	                String namingfactory=mr.getMessage("ejbclient.namingfactory");
	                String contextfactory=mr.getMessage("ejbclient.contextfactory");
	                String urlprovider=mr.getMessage("ejbclient.urlprovider");
	                String lookupip=mr.getMessage("ejbclient.ip");
	                String jndiname=mr.getMessage("jndi.rolemanagement");

	                System.setProperty(namingfactory,contextfactory);
	                System.setProperty(urlprovider,lookupip);
	                Context jndiContext = new InitialContext();
	                Object objref = jndiContext.lookup(jndiname);
	                Debug.print("ViewAction.jndiname:" + jndiname);
	                HLCMenuListVO objMap = new HLCMenuListVO();
	                
	                HLCMenuListVO objArti = new HLCMenuListVO();

	                HttpSession session = request.getSession(true);
	               
	                HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCBrahmaputraSessionRemoteHome.class);
	                HLCBrahmaputraSessionRemote roleRemote = roleHome.create();

	   
	                String artiProcess = request.getParameter("artiMapProcess");
	                Debug.print("ViewAction.artiProcess:" + artiProcess);
	                
	              //==================================================initArtifact Artifact Management =====================================================================================
	                if(artiProcess.equals("initArtifactMap")){
	                	
	                	ArrayList viewList = (ArrayList)roleRemote.getAllViews();
	                	ArrayList grpList = (ArrayList)roleRemote.getAllGroup();
	                	ArrayList grpDetList = (ArrayList)roleRemote.getGrpDetails();
	                	ArrayList cntList = (ArrayList)roleRemote.getCount();
	
	                
	                	
	                     request.setAttribute("viewList",viewList);	 
	                     request.setAttribute("grpDetList",grpDetList);	
	                     request.setAttribute("grpList",grpList);	
	                     request.setAttribute("cntList",cntList);	
	                     request.setAttribute("viewPointId",null);	
	                    
	                     return mapping.findForward("frmViewPointMapList");
	                 }
	    //==================================================create Artifact Management =====================================================================================
	                 else if(artiProcess.equals("insertArtifactMap")){
	                     Debug.print("ViewAction.insertArtifactMap()");
	                     String view_point_Id = request.getParameter("viewId");
	    		
	                      String grpCnt = request.getParameter("grpSize");
	                      String artiCnt = request.getParameter("artiCnt");
	                      
	                      boolean result=false;
	                      int grpSize = 0;
	                      if(grpCnt!=null && grpCnt.trim().length()!=0){
	                    	  grpSize = Integer.parseInt(grpCnt);
	                      }    
	                      
	                      int artiSize = 0;
	                      if(artiCnt!=null && artiCnt.trim().length()!=0){
	                    	  artiSize = Integer.parseInt(artiCnt);
	                      }    
	                 
	                      int tmpSize=0;
	                      int tempSize=0;
	                      
	                      String lobMasterId="";
	                      String viewMasterId="";
	                      String groupsId ="";
	                      String processMasterId ="";
	                      String  domainMasterId = ""; 
	                      
	                      for(int i=0; i<grpSize; i++){
	                    	if(request.getParameter("grpsDet"+i)!=null && request.getParameter("grpsDet"+i)!=""){  
	               String grpDet = request.getParameter("grpsDet"+i);
	               String tempGrpDet="";
	                              
                 
                   // tmpSize=0;
                    if(grpDet!=null && lobMasterId==""){
                	 String cntValue[] = grpDet.split("#");
                      lobMasterId = cntValue[0];
                     String lobLayerId = cntValue[1];
                    // String grpSequence = cntValue[2]; 
                 	
                    // objMap.setMasterId(lobMasterId);
                     objMap.setLobLayerId(lobLayerId);
                    // objMap.setSequence(Integer.parseInt(grpSequence));
                     
                   }
                    else if(grpDet!=null && viewMasterId == "" && lobMasterId != ""){
                     	 String cntValue[] = grpDet.split("#");
                     	viewMasterId = cntValue[0];
                       String viewLayerId = cntValue[1];
                     // String orgAsstSequence = cntValue[2]; 
            		
                      // objMap.setMasterId(orgAsstMasterId);
                       objMap.setViewPtId(viewLayerId);
                      // objMap.setSequence(Integer.parseInt(orgAsstSequence));   
                         
                        }
                    else if(grpDet!=null && groupsId == "" && viewMasterId!="" && lobMasterId != ""){
                   	 String cntValue[] = grpDet.split("#");
                      groupsId = cntValue[0];
                     String groupsIdLayerId = cntValue[1];
                   // String orgAsstSequence = cntValue[2]; 
          		
                    // objMap.setMasterId(orgAsstMasterId);
                     objMap.setGroupsId(groupsIdLayerId);
                    // objMap.setSequence(Integer.parseInt(orgAsstSequence));   
                       
                      }
                   
                    else if(grpDet!=null && processMasterId == "" && groupsId != "" && lobMasterId != ""){
             		   
             		   String cntValue[] = grpDet.split("#");
                         processMasterId = cntValue[0];
                        String processLayerId = cntValue[1];
                     //   String processSequence = cntValue[2]; 
             		
                     //   objMap.setMasterId(processMasterId);
                        objMap.setProcessDomainId(processLayerId);
                      //  objMap.setSequence(Integer.parseInt(processSequence));       		   
                    }
                   /* else if(grpDet!=null && domainMasterId == "" && processMasterId != "" && orgAsstMasterId != "" && lobMasterId != ""){
             		   
             		   String cntValue[] = grpDet.split("#");
                         domainMasterId = cntValue[0];
                        String domainLayerId = cntValue[1];
                      // String domainSequence = cntValue[2]; 
             		
                       // objMap.setMasterId(domainMasterId);
                        objMap.setDomainId(domainLayerId);
                      //  objMap.setSequence(Integer.parseInt(domainSequence));   
             		   
             	   }*/
	      
	                      }
	                      }
	                      result = roleRemote.insertFrameworkMapDetails(view_point_Id,objMap,objArti); 	           
	                      
	                            
	        
						        if(result){       
						        	ArrayList viewList = (ArrayList)roleRemote.getAllViews();
				                	ArrayList grpList = (ArrayList)roleRemote.getAllGroup();
				                	ArrayList grpDetList = (ArrayList)roleRemote.getGrpDetails();
				                	ArrayList cntList = (ArrayList)roleRemote.getCount();
				
				                
				                	
				                     request.setAttribute("viewList",viewList);	 
				                     request.setAttribute("grpDetList",grpDetList);	
				                     request.setAttribute("grpList",grpList);	
				                     request.setAttribute("cntList",cntList);	
				                     request.setAttribute("viewPointId",null);	
				                     request.setAttribute("success","success");
				                    
				                     return mapping.findForward("frmViewPointMapList");
					  	//return mapping.findForward("frmArtifactMapSucess");           
						        }else{
						        	return mapping.findForward("frmRedArtifactMapList");    	
						        }
	                 }
	                 else if(artiProcess.equals("listViewPointMap")){
	                		 ArrayList viewList = (ArrayList)roleRemote.getAllViews();
	                		
	                		 String viewPointId=request.getParameter("viewPoint");
	                		/*ArrayList userMapPointList=(ArrayList)roleRemote.getAllViewMapList();
	                		 request.setAttribute("userMapPointList",userMapPointList);*/	
	                		 request.setAttribute("viewList",viewList);	 
	                		 request.setAttribute("viewPointId",viewPointId);	 
	                		 return mapping.findForward("frmMappedViewPointList");
	                 }
	                   else if(artiProcess.equals("searchMappedGroup")){
	                	   ArrayList viewList = (ArrayList)roleRemote.getAllViews();
	                	 String viewPointId=request.getParameter("viewPoint");
	                		ArrayList userMapPointList=(ArrayList)roleRemote.getAllViewMapList(viewPointId);
	                		 request.setAttribute("userMapPointList",userMapPointList);	
	                		 request.setAttribute("viewPointId",viewPointId);	
	                		 request.setAttribute("viewList",viewList);	 
	                		 return mapping.findForward("frmMappedViewPointList");
	                 }
	                   else if(artiProcess.equals("listViewBasedOnViewPoint")){
		                	 
		                	 String viewPointId=request.getParameter("selVPointId");
		                	 ArrayList viewList = (ArrayList)roleRemote.getAllViews();
			                	ArrayList grpList = (ArrayList)roleRemote.getAllGroup();
			                	ArrayList grpDetList = (ArrayList)roleRemote.getGrpDetails();
			                	ArrayList cntList = (ArrayList)roleRemote.getCount();
			                ArrayList viewPointList=(ArrayList)roleRemote.getAllViewsBasedOnViewPoint(viewPointId);
		                		 request.setAttribute("viewPointList",viewPointList);	
		                		 request.setAttribute("viewPointId",viewPointId);	
		                		   request.setAttribute("viewList",viewList);	 
				                     request.setAttribute("grpDetList",grpDetList);	
				                     request.setAttribute("grpList",grpList);	
				                     request.setAttribute("cntList",cntList);
		                		 return mapping.findForward("frmViewPointMapList");
		                 }
	                
	//===============Dhivya Here:Artifact Mapping===============================        
	                
 if(artiProcess.equals("loadArtifact")){
	                	
	                	ArrayList viewPntList = (ArrayList)roleRemote.getAllViews();                	
	                	ArrayList cntList = (ArrayList)roleRemote.getCount();
	
	                	if(request.getAttribute("errorMsg")!=null){
	                	String errorMsg=(String) request.getAttribute("errorMsg");
	                	request.setAttribute("errorMsg",errorMsg);
	                	}
	                                	
	                     request.setAttribute("viewPntList",viewPntList);	 	                    
	                     request.setAttribute("cntList",cntList);
	                     

	                     return mapping.findForward("frmArtifactMapList");
	                 }  
 
 else if(artiProcess.equals("mapArtiDets")){
	 
	 String viewPntId=request.getParameter("viewPntId");
	 String lobId=request.getParameter("lobId");
	 String viewId=request.getParameter("viewId");
	 String grpId=request.getParameter("grpId");
	 String domProcId=request.getParameter("domProcId");
	 
	 
	 viewActionForm uploadForm=(viewActionForm)form;
	 System.out.println("1");
	 List myFiles =(List) uploadForm.getUploads();
	 System.out.println("2");
	 String filePath ="C:\\download";
	 
	 for(int i=0;i<myFiles.size();i++){
		 System.out.println("3");
		 if(myFiles.get(i)!=null){
			 System.out.println("4");
	       FormFile myFile =(FormFile)myFiles.get(i) ;
	       System.out.println("5");
	       System.out.println("file---------------"+myFile.getFileName());
	       
	       
	      //write file
	       String uploadFileName = myFile.getFileName();
           String contentType = myFile.getContentType();     
           String tempFileName = myFile.getFileName();           
           int mid = uploadFileName.lastIndexOf(".");          
           String ext = uploadFileName.substring(mid + 1, uploadFileName.length());         
              
               int fileSize = myFile.getFileSize();
               byte[] fileData = myFile.getFileData();
             
               
               new File(filePath).mkdirs();
               File destDirectory = new File(filePath);
               boolean exists = (destDirectory.exists());
               if (!exists) {
                  
                   destDirectory.mkdirs();
               }

              System.out.println("after dir creation");
               
               if (!uploadFileName.equals("")) {
                  
                   File fileToCreate = new File(filePath, uploadFileName);
                  
                   System.out.println("after fileToCreate creation");
                   System.out.println("after fileToCreate creation"+fileToCreate.getName());
                   if (!fileToCreate.exists()) {
                       try {
                           FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                           fileOutStream.write(myFile.getFileData());
                           fileOutStream.flush();
                           fileOutStream.close();
                          
                           System.out.println("after full write ");
                           
                          
                                                     
                       }    catch (Exception e) {
                       }
                
	   
                   }
               }
		 }
	 } 
	 
	 
	 Debug.print("ViewAction.grpId:" + grpId);
	 Debug.print("ViewAction.domProcId:" + domProcId);
	 boolean result =false;
	 String errorMsg=null;
	 objArti.setViewPtId(viewPntId);
	 if(lobId.equals("")){
	 objArti.setLobLayerId(null);
	 }else{
		 objArti.setLobLayerId(lobId); 
	 }
	 if(viewId.equals("")){
	 objArti.setViewId(null);
	 }
	 else{
		 objArti.setViewId(viewId);
	 }
	 if(grpId.equals("")){
		 objArti.setGroupsId(null);
		 }
		 else{
			 objArti.setGroupsId(grpId);
		 }
	 if(domProcId.equals("")){
		 objArti.setProcessDomainId(null);
		 }
		 else{
			 objArti.setProcessDomainId(domProcId);
		 }
	 
	 
 
	 String artiCnt = request.getParameter("artiCnt"); 
     
     int artiSize = 0;
     if(artiCnt!=null && artiCnt.trim().length()!=0){
   	  artiSize = Integer.parseInt(artiCnt);
     }  
     
     for(int i=0; i<artiSize; i++){
     	if(request.getParameter("artiFact"+i)!=null && request.getParameter("artiFact"+i)!=""){  
     		 FormFile myFile =(FormFile)myFiles.get(i) ;
    String artifactId=request.getParameter("artiFact"+i);
    String txtArtiDesc=request.getParameter("txtArtiDesc"+i);
    String filepath1=filePath+"\\"+myFile.getFileName();
    System.out.println("file path------------------"+filepath1);
    objArti.setArtifactId(artifactId);
    objArti.setArtifactDesc(filepath1);
    
     result = roleRemote.insertArtifactMapDetails(objArti);           
    
     		
     	}
     }
     
     if(result==true){
     
     return mapping.findForward("frmArtifactMapSucess"); 
     }else{
    	  errorMsg="yes";
    	 request.setAttribute("errorMsg",errorMsg);
    	 return mapping.findForward("frmRedArtifactMapList"); 	 
     }
	 
 }
	               
	                
	               
	        }
	        catch(Exception exp){
               exp.printStackTrace();
            }
	        return null;
	 }
}
	
	           
