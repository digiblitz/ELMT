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
/*  Program Name    : ActionContract.java
 *  Created Date    : October 5, 2006, 6:04 PM
 *  Author          : Punitha.R
 *  Version         : 1.3
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.sponsor.action;

import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcpayment.*;
import com.sponsor.actionform.*;
import com.sponsor.actionform.FormContract;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.util.MessageResources;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;
import javax.naming.InitialContext;
import javax.naming.Context;

import com.hlcspnr.sponsor.*;
import com.hlcspnr.util.*;
import java.util.Properties;
import java.io.*;
import java.util.*;
import java.text.*;
import com.hlccommon.util.Debug;

/**
 *
 * @author punitha
 * @version
 */

public class ActionContract extends Action {
    
   // private static final String ROOTDIR = "WEB-INF";
     
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         try{
            Properties p =new Properties();
            p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
            p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
            p.setProperty( "java.naming.provider.url", "localhost:11199" );
            Context jndiContext = new InitialContext(p);
            Object obj=jndiContext.lookup("ejb/HLCSponsorSessionBean");
            HLCSponsorSessionRemoteHome home = (HLCSponsorSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCSponsorSessionRemoteHome.class);
            HLCSponsorSessionRemote remote = home.create();
            
            Object objHuman=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
            HLCkaverystatelessRemoteHome humanHome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objHuman,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote humanRemote = humanHome.create();
            HttpSession session = request.getSession(true);
             SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
             String pro = request.getParameter("pro");
             MessageResources mr=getResources(request);
             String filePath=mr.getMessage("file.uploadPath");
                if(pro.equals("salesentryAdd")){
                FormContract  conForm = (FormContract) form;
                 
                // just for the readability 
                String salesPersonId = conForm.getSalesPersonId();
                String finalCost = conForm.getFinalCost();
                String startDate = conForm.getContractStartDate();
                String endDate = conForm.getContractEndDate();
                FormFile fileUpload = conForm.getFileUpload();
                 
            Date contractStartDate = null;
            Date contractEndDate = null;
            String sponsorId = request.getParameter("spoId");
            Debug.print("sponsorId"  + sponsorId);
        
                if(startDate.equals("")){
                    contractStartDate= null;
                }
                else{
                    contractStartDate =(Date)sdf.parse(startDate);
                }
                if(endDate.equals("")){
                    contractEndDate= null;
                }
                else{
                    contractEndDate =(Date)sdf.parse(endDate);
                }
            //logging the inputs coming from the GUI
                 Debug.print("FormContract ="+conForm);
                             
                 //upload the  file                  
                  //  String rootDir = getServlet().getServletContext().getRealPath(File.separator+filePath);                                  
                  //  Debug.print("RootDir="+rootDir);                 
                    String directoryTemp = filePath;                                                   
                    String fileName = fileUpload.getFileName();                   
                  //  String imageFilePath = directoryTemp + java.io.File.separator + fileName; 
                    String imageFilePath = directoryTemp + fileName;
                 
               // String imagePath = File.separator+ROOTDIR+File.separator +"temp"+File.separator+fileName;
                 
                 Debug.print("ImagePath=" + filePath);
                 BufferedOutputStream outFile = null;
                try {                      
                    //creating the temp dir if not exists
                      File fDir = new File(directoryTemp);
                        if (!fDir.exists()) {
                            if (!fDir.mkdir()) {
                              throw new IOException("Cannot create tmpdir for dimension file");
                            }
                        } 
                   File newFile = new File(imageFilePath);                           
                   Debug.print("NewFilePath ="+ newFile.getAbsolutePath());                                                       
                    outFile = new BufferedOutputStream(
                                new FileOutputStream(newFile));
                    outFile.write(fileUpload.getFileData());
 
                } catch (IOException ie) {
                  Debug.print("Error while writing into the file");
                } finally {
                 if(outFile!=null){   
                    outFile.flush();
                    outFile.close();              
                 }
                }
          remote.salesPersonUpdate(sponsorId,contractStartDate,contractEndDate,finalCost,fileName);
          return mapping.findForward("successcontract");
      }
             
             
//=============================================try block end============================================================================== 
  }
//=============================================catch block==============================================================================      
        catch(Exception e){
          Debug.print("While caliing business logic ::::::::::::::::::" + e);
        }
//=============================================Final Forward==============================================================================
        return mapping.findForward("index");
        
    }
}