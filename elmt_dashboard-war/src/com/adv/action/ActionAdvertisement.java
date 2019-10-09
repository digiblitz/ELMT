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
/*  Program Name    : ActionAdvertisement.java
 *  Created Date    : September 1, 2006, 10:25 AM
 *  Author          : Punitha.R
 *  Version         : 1.27
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.adv.action;
import com.hlcadv.advertise.HLCAdvertiseSessionRemote;
import com.hlcadv.advertise.HLCAdvertiseSessionRemoteHome;
import com.hlccommon.util.Debug;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import java.io.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;
import java.util.*;

public class ActionAdvertisement extends Action {
    
 /*
  * Media details
  */
    private final static String HOME = "show";
    
    
    
    private final static String NEWMEDIA = "advMediaPlanAddNew";
    private final static String INSERTMEDIA = "advInsertNewMedia";
    private final static String LISTMEDIA = "advMediaLst";
    private final static String EDITDELETEMEDIA ="advManupMedia";
    private final static String EDITMEDIA ="advEditMedia";
    private final static String CONFIRMDELETEMEDIA ="advConfirmDelete";
 /*
  * Dimension details
  */ 
    private final static String NEWDIMENSION = "advDimensionAdd";
    private final static String INSERTDIMENSION ="advDimAdd";
    private final static String LISTDIMENSION = "advLstDimension";
    private final static String EDITDIMENSION ="advDimEdit";
    private final static String EDITDELETEDIMENSION ="advManupDimension";
    private final static String CONFIRMDELETEDIM ="advConfirmDeleteDim";
   /*
    * Issue details
    */ 
    private final static String NEWISSUE ="advIssueAdd";
    private final static String INSERTISSUE ="advIssueInsert";
    private final static String LISTISSUE="advIssueLst";
    private final static String EDITDELETEISSUE ="advManupIssue";
    private final static String EDITISSUE = "advIssueEdit";
    private final static String CONFIRMDELETEISS ="advIssueDeleteIss";
    /*
    * Display Type Details
    */
    private final static String NEWDISPTYPE  = "advDispTypeAdd";
    private final static String LISTDISPTYPE = "advDispTypeLst";
    private final static String INSERTDISPTYPE = "advDispTypeInsert";
    private final static String EDITDELETEDISPTYPE = "advManupDispType";
    private final static String EDITDISPTYPE = "advDispEdit";
    private final static String CONFIRMDELETEDISPTYPE = "confirmDeleteDisp";
     
    /*
     * Display Sub Type Details
     */
    private final static String NEWDISPSUBTYPE  = "advDispSubTypeAdd";
    private final static String LISTDISPSUBTYPE = "advDispSubTypeLst";
    private final static String INSERTDISPSUBTYPE = "advDispSubTypeInsert";
    private final static String GETTYPEID= "advDispTypeShow";
    private final static String EDITDELETEDISPSUBTYPE = "advManupDispSubType";
    private final static String EDITDISPSUBTYPE = "advUpdateDispSubType";
    private final static String CONFIRMDELETEDISPSUBTYPE = "deleteDispSubType";
    private final static String GETLISTTYPEID = "advDispLstShow";
   
        public ActionForward execute(ActionMapping mapping, ActionForm  form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {

            try {
                MessageResources mr=getResources(request);
                String namingfactory=mr.getMessage("ejbclient.namingfactory");
                String contextfactory=mr.getMessage("ejbclient.contextfactory");
                String urlprovider=mr.getMessage("ejbclient.urlprovider");
                String lookupip=mr.getMessage("ejbclient.ip");
               
                String jndiname=mr.getMessage("jndi.advertise");

                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);
                
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname);        

                HLCAdvertiseSessionRemoteHome advHome = (HLCAdvertiseSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCAdvertiseSessionRemoteHome.class);
                HLCAdvertiseSessionRemote advRemote = advHome.create();
                HttpSession session = request.getSession(false);
             
/*
 * redirects to home advertisement home page.............
 */
                String advProcess = request.getParameter("advProcess");
                if(advProcess.equals(HOME)){
                    Debug.print("Home page is caliing.......");
                    return mapping.findForward("frmAdvHome");
                }
 /*
 * redirects to media page frmAdvMediaAdd.jsp..................
 */                
                else if(advProcess.equals(NEWMEDIA)){
                    try{
                        return mapping.findForward("frmAdvMediaAdd");
                    }
                    catch(Exception advNewMedia){
                        Debug.print("Exception in Action Advertisement Media Redirection:::::::"+ advNewMedia);
                    }
                }
/*
 *media values insertion..................
 */
                 else if(advProcess.equals(INSERTMEDIA)){
                    try{
                     
                     String mediaName = request.getParameter("mediaName");
                     String mediaDesc = request.getParameter("mediaDescription");
                      if(mediaName!=null){
                            mediaName = mediaName.trim();
                       }
                     boolean resultInsert = advRemote.createMedia(mediaName,mediaDesc); 
                        if(resultInsert==true){
                            return mapping.findForward("frmAdvMediaConfirmation"); 
                            }
                         else{
                                request.setAttribute("err","status");
                                return mapping.findForward("frmAdvMediaAdd");
                         }
                         
                     }
                    catch(Exception eInsertMedia){
                        Debug.print("Exception in Action Advertisement Media Insertion:::::::" + eInsertMedia);
                        
                    }
                }
/*
 *update the edited media informations..........
 */
            else if(advProcess.equals(EDITMEDIA)){
                String mediaId = request.getParameter("mediaId");
                String mediaName = request.getParameter("mediaName");
                String mediaDesc = request.getParameter("mediaDescription");
               if(mediaName!=null){
                      mediaName = mediaName.trim();
              }
                boolean resultEdit =  advRemote.editMedia(mediaId, mediaName,mediaDesc);
                Debug.print("Result:" + resultEdit);
                        if(resultEdit==true){
                              return mapping.findForward("MediaList"); 
                            }
                        else{
                                request.setAttribute("err","st");
                                return mapping.findForward("frmAdvMediaEdit");
                         }
                
             }       
                
/*
 *getting the media id values  and redirects to frmAdvMediaEdit.jsp......................
 */
            else if(advProcess.equals(EDITDELETEMEDIA)){
             try{
                String advButValue = request.getParameter("advButValue");
                String mediaId = request.getParameter("mediaId");
                Debug.print("mediaId" + mediaId);
               
                
               // HttpSession hsAdv = request.getSession(true);
                 session.setAttribute("objSesAdvEditMedia",null);
                        if(advButValue.equals("Edit")){
                            
                            String[] s = (String[])advRemote.getMediaDetails(mediaId);
                           
                            session.setAttribute("objSesAdvEditMedia",null);                       
                            session.setAttribute("objSesAdvEditMedia",s);
                            return mapping.findForward("frmAdvMediaEdit");
                           
                      }
            
/*
 *getting the media id values  and redirects to frmAdvMediaDelete.jsp......................
 */
               
                     else if(advButValue.equals("Delete")){
                          
                            String[] sDelete = (String[])advRemote.getMediaDetails(mediaId);
                            session.setAttribute("objSesAdvDeleteMedia" ,null);
                            session.setAttribute("objSesAdvDeleteMedia" ,sDelete);
                            return mapping.findForward("frmAdvMediaDelete"); 
                    
                        }
                }
               
                   
               
             catch(Exception eAdvMaupEditDelete){
                 Debug.print("Exception in Action Advertisement Media Edit & Delete:::::::" + eAdvMaupEditDelete);
              }
            }
                
/*
 *getting the media id values frmAdvMediaDelete.jsp for confirmation......................
 */
             else if(advProcess.equals(CONFIRMDELETEMEDIA)){
             try{
                    String advButton = request.getParameter("advButton");
                    String mediaId = request.getParameter("mediaId");
                        
                        if(advButton.equals("Confirm Delete")){
                       
                            boolean resultDelete = advRemote.deleteMedia(mediaId);
                            if(resultDelete == true){
                                 return mapping.findForward("MediaList"); 
                            }
                            else{
                                request.setAttribute("errStat","eConfirmDelete");
                                return mapping.findForward("frmAdvMediaList"); 
                            }
                      }
            
/*
 * cancelling the delete request for media.........................................
 */
                            else if(advButton.equals("Cancel")){
                                return mapping.findForward("frmAdvMediaList"); 
                         }
                  }
             catch(Exception eConfirmDelete){
                   Debug.print("Exception in Action Advertisement Media Edit & Delete:::::::" + eConfirmDelete);
                }
             
             }
 
/*
 * view all media detials and redirects to frmAdvMediaList.jsp.....................
 */
            else if(advProcess.equals(LISTMEDIA)){
              try{
                Vector v = (Vector)advRemote.getAllMediaDetails();
                session.setAttribute("objSesAdvLstMedia" ,null);
                session.setAttribute("objSesAdvLstMedia" ,v);
                return mapping.findForward("frmAdvMediaList"); 
            }
              catch(Exception eLstMedia){
                  Debug.print("Exception in Action Advertisement Media List:::::::" + eLstMedia);
              }
            }
/*
 * redirects to frmAdvDimensionAdd.jsp......................
 */
            else if(advProcess.equals(NEWDIMENSION)){
                    return mapping.findForward("frmAdvDimensionAdd");
                    
            }
/*
 * creates the dimension details.........
 */
                 else if(advProcess.equals(INSERTDIMENSION)){
                    try{
                    String dimensionName = request.getParameter("txtDimensionName");
                     if(dimensionName!=null){
                            dimensionName = dimensionName.trim();
                       }
                     boolean resultInsDim = advRemote.createDimension(dimensionName);
                        if(resultInsDim==true){
                          return mapping.findForward("frmAdvDimensionConfirmation");
                       }
                        else
                        {
                        request.setAttribute("err","st");
                        return mapping.findForward("frmAdvDimensionAdd");
                        }
                    }
                    catch(Exception eDimenInsert){
                        Debug.print("Exception occurs while inserting dimension value" +eDimenInsert);
                    }
                 }
/*
 *update the edited dimension details............................
 */
                else if(advProcess.equals(EDITDIMENSION)){
                try{
                String dimensionId = request.getParameter("dimensionId");
                String dimensionName = request.getParameter("txtDimensionEditName");
                 if(dimensionName!=null){
                            dimensionName = dimensionName.trim();
                  }
                boolean resultEDUP =  advRemote.editDimension(dimensionId, dimensionName);
                Debug.print("resultEDUP:" + resultEDUP);
                     if(resultEDUP==true){
                            Vector v = (Vector)advRemote.getAllDimensionDetails();
                            session.setAttribute("objSesAdvDimLst" ,v);
                            return mapping.findForward("frmAdvDimensionList"); 
                  }
                         else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmAdvDimensionEdit");
                     }
                     
               }
               catch(Exception eEditDim){
                   Debug.print("While editing the dimension :::;" + eEditDim);
               }
           }
/*
 * getting the dimension id form frmAdvDimensionLst.jsp for edit .....
 */
            else if(advProcess.equals(EDITDELETEDIMENSION)){
                 try{
                    String advDimButton = request.getParameter("advDimButton");
                    String dimensionId = request.getParameter("dimensionId");
 
                        session.setAttribute("objSesAdvEditDim",null);
                             if(advDimButton.equals("Edit")){
                                String[] s = (String[])advRemote.getDimensionDetails(dimensionId);
                                //session.setAttribute("objSesAdvEditDim",null);                       
                                session.setAttribute("objSesAdvEditDim",s);
                                return mapping.findForward("frmAdvDimensionEdit");
                          }

/*
 * getting the dimension id form frmAdvDimensionLst.jsp for delete .....
 */
                     else if(advDimButton.equals("Delete")){
                           String[] sDimDel = (String[])advRemote.getDimensionDetails(dimensionId);
                           session.setAttribute("objSesAdvDimDelete" ,sDimDel);
                           return mapping.findForward("frmAdvDimensionDelete"); 
                        }
                  }
             catch(Exception eAdvMaupEditDelete){
                 Debug.print("Exception in Action Advertisement Media Edit & Delete:::::::" + eAdvMaupEditDelete);
              }
         }
/*
 * getting dimension id from frmAdvDimensiondelete.jsp for confirmation.....
 */
             else if(advProcess.equals(CONFIRMDELETEDIM)){
             try{
                    String advButtonDim = request.getParameter("advButtonDim");
                     String dimensionId = request.getParameter("dimensionId");
                        
                        if(advButtonDim.equals("Confirm Delete")){
                       
                            boolean resultDelete = advRemote.deleteDimension(dimensionId);
                            if(resultDelete == true){
                            Vector v = (Vector)advRemote.getAllDimensionDetails();
                            session.setAttribute("objSesAdvDimLst" ,v);
                            return mapping.findForward("frmAdvDimensionList"); 
                            }
                            else{
                                request.setAttribute("errStat","eConfirmDelete");
                                return mapping.findForward("frmAdvDimensionList"); 
                            }
                            
                        }
            
/*
 * cancelling the delete request............
 */
                            else if(advButtonDim.equals("Cancel")){
                                return mapping.findForward("frmAdvDimensionList"); 
                         }
                  }
             catch(Exception eConfirmDelete){
                   Debug.print("Exception in Action Advertisement Media Edit & Delete:::::::" + eConfirmDelete);
                }
             
             }
                 
 /*
  * show all dimension details......
  */
            else if(advProcess.equals(LISTDIMENSION)){
                    Vector vDim = (Vector)advRemote.getAllDimensionDetails();
                    session.setAttribute("objSesAdvDimLst",vDim);
                    return mapping.findForward("frmAdvDimensionList");
                    }                    
 
/*
 * redirects to issue add page.........................
 */
            else if(advProcess.equals(NEWISSUE)){
                    try{
                        Vector vIssue = (Vector)advRemote.getAllMediaDetails();
                        session.setAttribute("objSesAdvIssueLst" ,null);
                        session.setAttribute("objSesAdvAddIssue" ,null);
                        session.setAttribute("objSesAdvAddIssue" ,vIssue);
                        return mapping.findForward("frmAdvIssueAdd");
                    }
                    catch(Exception eIssue){
                        Debug.print("while redirecting new issue page" + eIssue);
                        
                    }
            } 
            
/*
 * show all media details in issue list page......................
 *
 */
             else if(advProcess.equals(LISTISSUE)){
                   try {
                        
                        Vector vIssue = (Vector)advRemote.getAllMediaDetails();
                        session.setAttribute("objSesAdvIssueLst" ,null);
                        session.setAttribute("objSesAdvMediaIssue" ,vIssue);
                        return mapping.findForward("frmAdvIssueList");
                      }
                   
                   catch(Exception eLstIssue){
                       Debug.print("while listing the isuuess...." + eLstIssue);
                       
                   }
                     
            }
  /*
   * based on media id show issue details..................
   *
   */              
            else if(advProcess.equals("advIssueTypeShow")){
                    
                String mid = request.getParameter("mid");
                
                Vector vIssue = (Vector)advRemote.getAllMediaDetails();
                session.setAttribute("objSesAdvMediaIssue" ,vIssue);
                        
                Vector vIs = (Vector)advRemote.getIssueByMedia(mid);
                session.setAttribute("objSesAdvIssueLst",null);
                session.setAttribute("objSesAdvIssueLst",vIs);
                request.setAttribute("mid",mid);
                return mapping.findForward("frmAdvIssueList");
            }   
       
           
/*
 * create issue details..................
 */
                 else if(advProcess.equals(INSERTISSUE)){
                    try{
                     String mediaId = request.getParameter("mediaId");
                     String issueName = request.getParameter("txtIsssueName");
                      if(issueName!=null){
                            issueName = issueName.trim();
                       }
                     boolean resultIssue = advRemote.createIssue(issueName,mediaId);
                         if(resultIssue==true){
                             return mapping.findForward("frmAdvIssueConfirmation");
                            }
                         else{
                             request.setAttribute("err","st");
                             return mapping.findForward("frmAdvIssueAdd");
                         }
                         
                     }
                    catch(Exception eInsertIssue){
                        Debug.print("Exception in Action Advertisement Issue Insertion:::::::" + eInsertIssue);
                        
                    }
                }
/*
 * update the edited issue details.....................
 */
             else if(advProcess.equals(EDITDELETEISSUE)){
                 try{
                    String advIssueButton = request.getParameter("advIssueButton");
                    String issue_id = request.getParameter("issue_id");
                    Debug.print("Servlet IssueId in issue section"  + issue_id);

                    HttpSession hsEdit = request.getSession(true);
                    hsEdit.setAttribute("objSesAdvEditIssue",null);
                        if(advIssueButton.equals("Edit")){
                                Vector vIssue = (Vector)advRemote.getAllMediaDetails();
                                session.setAttribute("objSesAdvDelIssueMedia",null);
                                session.setAttribute("objSesAdvDelIssue",null);
                                session.setAttribute("objSesAdvEditIssueMedia" ,vIssue);
                                String[] s = advRemote.getIssueDetails(issue_id); 
                                session.setAttribute("objSesAdvEditIssue",s);
                                return mapping.findForward("frmAdvIssueEdit");
                          }

/*
 * getting issue id for delete issue details.....
 */
                         else if(advIssueButton.equals("Delete")){
                                    Vector vIssue = (Vector)advRemote.getAllMediaDetails();
                                    session.setAttribute("objSesAdvDelIssueMedia",null);
                                    session.setAttribute("objSesAdvDelIssue",null);
                                    session.setAttribute("objSesAdvDelIssueMedia" ,vIssue);
                                    String[] s = advRemote.getIssueDetails(issue_id); 
                                    session.setAttribute("objSesAdvDelIssue",s);
                                    return mapping.findForward("frmAdvIssueDelete"); 
                           }
                   }
             catch(Exception eAdvIssueEditDelete){
                 Debug.print("Exception in Action Advertisement Issue Edit & Delete:::::::" + eAdvIssueEditDelete);
              }
         }
                
 /*
  * for issue details confirmation........
  */
             else if(advProcess.equals(CONFIRMDELETEISS)){
             try{
                     String advIssueButton = request.getParameter("advIssueButton");
                     String issueId = request.getParameter("issueId");
                     String mid = request.getParameter("mediaNewid");   
           
                     if(advIssueButton.equals("Confirm Delete")){
                       
                            boolean resultDelete = advRemote.deleteIssue(issueId);
                            if(resultDelete == true){
                                Vector v = (Vector)advRemote.getIssueByMedia(mid);
                                session.setAttribute("objSesAdvIssueLst",null);
                                session.setAttribute("objSesAdvIssueLst",v);
                                request.setAttribute("mid",mid);
                                Debug.print("mid true part servlet" + mid);
                                return mapping.findForward("IssueList");
                            }
                            else{
                            
                                Vector v = (Vector)advRemote.getIssueByMedia(mid);
                                HttpSession hsIssue = request.getSession(true);
                                hsIssue.setAttribute("objSesAdvIssueLst",v);
                                request.setAttribute("mid",mid);
                                Debug.print("mid false part servlet" + mid);
                                request.setAttribute("errStat","eConfirmDelete");
                                return mapping.findForward("IssueList");
                            }
                            
                        }
 /*
  * cancelling the delete request....
  */
                         else if(advIssueButton.equals("Cancel")){
                                Vector v = (Vector)advRemote.getIssueByMedia(mid);
                                HttpSession hsIssue = request.getSession(true);
                                hsIssue.setAttribute("objSesAdvIssueLst",v);
                                return mapping.findForward("frmAdvIssueList");
                             }
                  }
             catch(Exception eConfirmDelete){
                   Debug.print("Exception in Action Advertisement Issue Edit & Delete:::::::" + eConfirmDelete);
                }
             
             }
 
/*
 * update edited issue information details.........
 */
            else if(advProcess.equals(EDITISSUE)){
                try{
                String issueId = request.getParameter("issueId");
                Debug.print("ActionissueId" + issueId );
                String issueName = request.getParameter("txtIssueName");
                 Debug.print("ActionissueName" + issueName );
                String mediaId = request.getParameter("mediaNewid");
                Debug.print("ActionmediaId" + mediaId );
                 if(issueName!=null){
                            issueName = issueName.trim();
                 }
                boolean result =  advRemote.editIssue(issueId,issueName,mediaId);
                Debug.print("resultEDUP:" + result);
                     if(result==true){
                            Vector v = (Vector)advRemote.getIssueByMedia(mediaId);
                            HttpSession hsIssue = request.getSession(true);
                            hsIssue.setAttribute("objSesAdvIssueLst",null);
                            hsIssue.setAttribute("objSesAdvIssueLst",v);
                            request.setAttribute("mid",mediaId);
                            return mapping.findForward("frmAdvIssueList");
                        }
                     else{
                        request.setAttribute("err","st");
                        return mapping.findForward("frmAdvIssueEdit");
                     }
               }
               catch(Exception eEditIssue){
                   Debug.print("While editing the issue :::;" + eEditIssue);
               }
           }  
/*
 * redirects to new display type page..........
 */
            else if(advProcess.equals( NEWDISPTYPE)){
                    try{
                        Vector vIssue = (Vector)advRemote.getAllMediaDetails();
                        HttpSession hs = request.getSession(true);
                        hs.setAttribute("objSesAdvAddDisp" ,null);
                        hs.setAttribute("objSesAdvAddDisp" ,vIssue);
                        return mapping.findForward("frmAdvDisplayTypeAdd");
                    }
                    catch(Exception eDisp){
                        Debug.print("while redirecting new display type page" + eDisp);
                        
                    }
            } 
/*
 * creates the display type details.....
 */
                 else if(advProcess.equals(INSERTDISPTYPE)){
                    try{
                        String mediaDispId = request.getParameter("mediaDispId");
                        String txtDisplayType = request.getParameter("txtDisplayType");
                        if(txtDisplayType!=null){
                            txtDisplayType = txtDisplayType.trim();
                       }
                        String txtareaDisplayTypeDesc = request.getParameter("txtareaDisplayTypeDesc");
                        boolean resultDisp = advRemote.createDisplayType(txtDisplayType,mediaDispId,txtareaDisplayTypeDesc);
                        if(resultDisp==true){
                             return mapping.findForward("frmAdvDispTypeConfirmation");
                            }
                         else{
                            request.setAttribute("err","st");
                             return mapping.findForward("frmAdvDisplayTypeAdd");
                            
                         }
                         
                     }
                    catch(Exception eInsertDispType){
                        Debug.print("Exception in Action Advertisement Disp Type Insertion:::::::" + eInsertDispType);
                        
                    }
                }
 /*
  * initially load all media detials in display list page............
  */
            else if(advProcess.equals(LISTDISPTYPE)){
                   try {
                        session.setAttribute("objSesAdvDispType",null);
                        session.setAttribute("objSesAdvDispTypeMedia" ,null);
                        Vector vMedia = (Vector)advRemote.getAllMediaDetails();                   
                        session.setAttribute("objSesAdvDispTypeMedia" ,vMedia);
                        return mapping.findForward("frmAdvDisplayTypeList");
                   }
                   catch(Exception eLstDisp){
                       Debug.print("while listing the isuuess...." + eLstDisp);
                      }
            }
/*
 * based on media id show advertisement type details......
 */
            else if(advProcess.equals("advDispTypeLstShow")){
               try{
                String mid = request.getParameter("mid");
               
                Vector vMedia = (Vector)advRemote.getAllMediaDetails();  
                session.setAttribute("objSesAdvDispTypeMedia" ,vMedia);
                
                Vector displayType = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                session.setAttribute("objSesAdvDispType",null);
                session.setAttribute("objSesAdvDispType" ,displayType);
                request.setAttribute("mid",mid);
                return mapping.findForward("frmAdvDisplayTypeList");
               }
               catch(Exception showDisp){
                   Debug.print("Exception in display page" + showDisp);
               }
             }    
 /*
  * getting display id for editing the ad type details....
  */
             else if(advProcess.equals(EDITDELETEDISPTYPE)){
                 try{
                    String advButtonDisp = request.getParameter("advButtonDisp");
                    String display_id = request.getParameter("display_id");
                    
                    Debug.print("display_id Servlet in display type edit" + display_id);
                  
                    session.setAttribute("objSesAdvEditDisp",null);
                        if(advButtonDisp.equals("Edit")){
                        
                                Vector vIssue = (Vector)advRemote.getAllMediaDetails();
                                
                                session.setAttribute("objSesAdvEditDispMedia" ,null);
                                session.setAttribute("objSesAdvEditDispMedia" ,vIssue);
                                
                                String[] sDisp = advRemote.getDisplayTypeDetails(display_id);
                                session.setAttribute("objSesAdvEditDisp",null);                       
                                session.setAttribute("objSesAdvEditDisp",sDisp);
                                return mapping.findForward("frmAdvDisplayTypeEdit");
                          }
/*
  * getting display id for deleting the ad type details....
  */               
                     else if(advButtonDisp.equals("Delete")){
                                                        
                                Vector vMedia =(Vector)advRemote.getAllMediaDetails();
                                session.setAttribute("objSesAdvMediaDispType",vMedia);
                                String[] sDisp = advRemote.getDisplayTypeDetails(display_id);
                                session.setAttribute("objSesAdvDispTypeDelete",null);
                                session.setAttribute("objSesAdvDispTypeDelete",sDisp);
                                return mapping.findForward("frmAdvDisplayTypeDelete");
                        }
                                }
             catch(Exception eAdvDispEditDelete){
                 Debug.print("Exception in Action Advertisement Display type Edit & Delete:::::::" + eAdvDispEditDelete);
              }
         }
/*
  * update edited  display details.......
  */
            else if(advProcess.equals(EDITDISPTYPE)){
                try{
                String displayId = request.getParameter("displayId");
                String txtDisplayType = request.getParameter("txtDisplayType");
                String mediaDispId = request.getParameter("mediaDispId");
                String txtareaDisplayTypeDesc = request.getParameter("txtareaDisplayTypeDesc");
                if(txtDisplayType!=null){
                            txtDisplayType = txtDisplayType.trim();
                }
                boolean result =  advRemote.editDisplayType(displayId,txtDisplayType,mediaDispId,txtareaDisplayTypeDesc);
                Debug.print("resultEDUP:" + result);
                     if(result==true){
                                Vector vDisp = (Vector)advRemote.getMediaDisplayTypeDetails(mediaDispId);
                                return mapping.findForward("dispList");
                      }
                     else{
                            request.setAttribute("err","st");
                            return mapping.findForward("frmAdvDisplayTypeEdit");
                          }
                 }
               catch(Exception eEditDisp){
                   Debug.print("While editing the eEditDispType :::;" + eEditDisp);
               }
           } 
/*
 * confirmation delete page for display type....
 */
             else if(advProcess.equals(CONFIRMDELETEDISPTYPE)){
             try{
                     String advButtonDisp = request.getParameter("advButtonDisp");
                     String displayId = request.getParameter("displayId");
                     String mid = request.getParameter("mediaId");   
                     Debug.print("mid value from servlet in display type is.......", mid);
                     if(advButtonDisp.equals("Confirm Delete")){
                       
                           boolean resultDelete = advRemote.deleteDisplayType(displayId);
                           
                            if(resultDelete == true){
                               // Vector vDisp = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                               // session.setAttribute("objSesAdvDispType",null);
                              //  session.setAttribute("objSesAdvDispType",vDisp);
                              //  Debug.print("mid true part servlet" + mid);
                                return mapping.findForward("dispList");
                            }
                            else{
                            
                                //Vector vDisp = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                               // session.setAttribute("objSesAdvDispType",null);
                               // session.setAttribute("objSesAdvDispType",vDisp);
                               // Debug.print("mid false part servlet" + mid);
                                request.setAttribute("errStat","eConfirmDelete");
                                return mapping.findForward("dispList");
                            }
                            
                        }
 /*
  * cancelling the delete request for display type details....
  */
                         else if(advButtonDisp.equals("Cancel")){
                                Vector vDisp = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                                session.setAttribute("objSesAdvDispType",null);
                                session.setAttribute("objSesAdvDispType",vDisp);
                                return mapping.findForward("dispList");
                             }
                  }
             catch(Exception eConfirmDelete){
                   Debug.print("Exception in Action Advertisement Issue Edit & Delete:::::::" + eConfirmDelete);
                }
             
             }
  /*
   * initialliy load all media details into display sub type page.......
   */
          else if(advProcess.equals(NEWDISPSUBTYPE)){
                    try{
                     
                                Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                                 
                                session.setAttribute("objSesAdvMediaType",null);
                                session.setAttribute("objSesAdvDispType",null);
                                session.setAttribute("objSesAdvMediaType" ,dispMediaType);  
                                return mapping.findForward("frmAdvDisplaySubTypeAdd");
                    } 
                    catch(Exception eDispSubType){
                        Debug.print("while redirecting new display sub type page" + eDispSubType);
                        
                    }
            }
/*
 * based on media id show all display details. and getting display details also....
 */
              else if(advProcess.equals(GETTYPEID)){
                    try{
                            String mid = request.getParameter("mid");
                            Debug.print("Servlet mid" + mid);
                                Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                                session.setAttribute("objSesAdvMediaType",null);
                                session.setAttribute("objSesAdvMediaType" ,dispMediaType);  
  
                                Vector displayType = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                                session.setAttribute("objSesAdvDispType",null);
                                session.setAttribute("objSesAdvDispType" ,displayType);
                                request.setAttribute("mid",mid);
                                return mapping.findForward("frmAdvDisplaySubTypeAdd");
                    } 
                    catch(Exception eDispSubType){
                        Debug.print("while redirecting new display sub type page" + eDispSubType);
                    }
            }
 /*
  * create display sub types details......
  */
         else if(advProcess.equals(INSERTDISPSUBTYPE)){
                    try{
                        String mediaId = request.getParameter("mediaId");
                        String DispId = request.getParameter("DispId");
                        String subTypeName = request.getParameter("txtDisplaySubType");
                        String subTypeDesc = request.getParameter("txtareaDesc");
                        
                        
                        Debug.print("Action mediaId" + mediaId);
                        Debug.print("Action DispIdDispId" +DispId);
                        Debug.print("Action subTypeName" + subTypeName);
                        Debug.print("Action subTypeDesc "+subTypeDesc);
                        
                        if(subTypeName!=null){
                            subTypeName = subTypeName.trim();
                       }
                        boolean resultDispSub = advRemote.createDisplaySubType(subTypeName,DispId,subTypeDesc);
                        if(resultDispSub==true){
                             return mapping.findForward("frmAdvDispSubTypeConfirmation");
                        }
                         else{
                             request.setAttribute("err","st");
                             return mapping.findForward("frmAdvDisplaySubTypeAdd");
                         }
                    } 
                    catch(Exception eInsertDispSubType){
                        Debug.print("Exception in Action Advertisement Disp Type Insertion:::::::" + eInsertDispSubType);
                    }
                }   
/*
 * intially loading all media details.....
 */
             else if(advProcess.equals(LISTDISPSUBTYPE)){    
                try{
                                session.setAttribute("objSesAdvDispType",null);
                                session.setAttribute("objSesdispSubType", null);
                                Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                                session.setAttribute("objSesAdvMediaType",null);
                               
                                session.setAttribute("objSesAdvMediaType" ,dispMediaType);  
                                return mapping.findForward("frmAdvDisplaySubTypeList");
                    } 
                    catch(Exception eDispSubType){
                        Debug.print("while Listing new display sub type page" + eDispSubType);
                        
                    }  
             }
/*
 *based on media id load display details...
 */
              else if(advProcess.equals(GETLISTTYPEID)){
                    try{
                            String mid = request.getParameter("mid");
                            Debug.print("Servlet mid" + mid);
                             
                                session.setAttribute("objSesdispSubType", null);
                                Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                                session.setAttribute("objSesAdvMediaType",null);
                                session.setAttribute("objSesAdvMediaType" ,dispMediaType);  
                              
                               
                                Vector displayType = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                                session.setAttribute("objSesAdvDispType",null);
                                session.setAttribute("objSesAdvDispType" ,displayType);
                                
                                request.setAttribute("mid",mid);
                                return mapping.findForward("frmAdvDisplaySubTypeList");
                    } 
                    catch(Exception eDispSubType){
                        Debug.print("while Listing new display sub type page" + eDispSubType);
                    }
            }   
/*
 * display ad sub type details based on media id and display id....
 */
              else if(advProcess.equals("advDispSubTypeShow")){
                    try{
                            String mid = request.getParameter("mid");
                            Debug.print("Servlet mid" + mid);
                                Vector dispMediaType = (Vector)advRemote.getAllMediaDetails();
                                 
                                session.setAttribute("objSesAdvMediaType",null);
                                session.setAttribute("objSesAdvMediaType" ,dispMediaType);  
                               
                                Vector displayType = (Vector)advRemote.getMediaDisplayTypeDetails(mid);
                                
                                session.setAttribute("objSesAdvDispType",null);
                                session.setAttribute("objSesAdvDispType" ,displayType);
                                request.setAttribute("mid",mid);
                        
                        String dispId = request.getParameter("dispId");
                        Debug.print("Servlet dispId" + dispId);
                        Vector dispSubType = (Vector)advRemote.getDisplayTypeFromSubType(dispId);
                       
                        session.setAttribute("objSesdispSubType", null);
                        session.setAttribute("objSesdispSubType", dispSubType);
                        request.setAttribute("dispId",dispId);
                        return mapping.findForward("frmAdvDisplaySubTypeList");
                    }
                    catch(Exception eDispSubType){
                        Debug.print("eDispSubType::::::::::" + eDispSubType);
                   }
              }
  

  
 /*
  * getting media id and display type id for editing......
  */
             else if(advProcess.equals(EDITDELETEDISPSUBTYPE)){
               try {
                   Debug.print("edit method is calling.........");
                    String advButtonDispSub = request.getParameter("advButtonDispSub");
                    String subTypeId = request.getParameter("subTypeId");
                    Debug.print("subTypeId" + subTypeId );
                   
                    String mediaId = request.getParameter("mediaId");
                    
                    Debug.print("mediaId" + mediaId );
                   
                           if(advButtonDispSub.equals("Edit")){
                                String[] displaySubTypeDetails = advRemote.getDisplaySubTypeDetails(subTypeId);
                              
                                session.setAttribute("DisplaySubTypeDetails", displaySubTypeDetails);
                                Debug.print("Display Sub Type Details" + displaySubTypeDetails);
                                
                                Vector mediaDetails = (Vector)advRemote.getAllMediaDetails();
                                
                                session.setAttribute("DisplayMediaDetails",mediaDetails);
                                Debug.print("media Details vector:" + mediaDetails);
                                Vector dispalyDetails = (Vector)advRemote.getMediaDisplayTypeDetails(mediaId);
                                session.setAttribute("DisplayTypeDetails",dispalyDetails);
                                Debug.print("Display details vector:" + dispalyDetails);
                                                            
                                request.setAttribute("mid",mediaId);
                                return mapping.findForward("frmAdvDisplaySubTypeEdit");
                        }
 

 /*
  * based on media id and display id redirects to delete page...
  */
                     else if(advButtonDispSub.equals("Delete")){
                                
                                String[] deleteSubTypeDetails = advRemote.getDisplaySubTypeDetails(subTypeId);
                                session.setAttribute("deleteSubTypeDetails", deleteSubTypeDetails);
                                                               
                                Vector dispalyDetails = (Vector)advRemote.getMediaDisplayTypeDetails(mediaId);
                                session.setAttribute("DisplayTypeDetails",dispalyDetails);
                                Debug.print("Display details vector:" + dispalyDetails);
                                
                                Vector mediaDetails = (Vector)advRemote.getAllMediaDetails();
                                session.setAttribute("DisplayMediaDetails",mediaDetails);
                                Debug.print("media Details vector:" + mediaDetails);
                                
                                request.setAttribute("mid",mediaId);
                                return mapping.findForward("frmAdvDisplaySubTypeDelete");
                        }
                                }
             catch(Exception eAdvDispSubTypeEditDelete){
                 Debug.print("Exception in Action Advertisement Display type Edit & Delete:::::::" + eAdvDispSubTypeEditDelete);
              }
         }

  /*
   * update the edited display details....
   *
   */                     
            else if(advProcess.equals(EDITDISPSUBTYPE)){
                try{
                String mediaType = request.getParameter("mediaType");
                String displayType = request.getParameter("displayType");
                String dispSubTypeId = request.getParameter("dispSubTypeId");
                String txtDisplaySubType = request.getParameter("txtDisplaySubType");
                String txtAreaDesc = request.getParameter("txtAreaDesc");
                 if(txtDisplaySubType!=null){
                            txtDisplaySubType = txtDisplaySubType.trim();
                 }
                if(dispSubTypeId!=null){
                boolean resultEdit =  advRemote.editDisplaySubType(dispSubTypeId,txtDisplaySubType,displayType,txtAreaDesc);
                Debug.print("resultEDUP:" + resultEdit);
                     if(resultEdit==true){
                             return mapping.findForward("list");
                      }
                     else{
                            request.setAttribute("err","st");
                            return mapping.findForward("frmAdvDisplaySubTypeEdit");
                            
                         }
                 }
                }
               catch(Exception eEditDispSubType){
                   Debug.print("While editing the eEditDispSubType :::;" + eEditDispSubType);
               }
           } 

 /*
  * confirmation of delete details..
  */    
     else if(advProcess.equals(CONFIRMDELETEDISPSUBTYPE)){
             try{
                     String buttonSubType = request.getParameter("buttonSubType");
                     String dispalySubTypeId = request.getParameter("dispalySubTypeId");
                    
                     if(buttonSubType.equals("Confirm Delete")){
                       
                           boolean resultDelete = advRemote.deleteDisplaySubType(dispalySubTypeId);
                           
                            if(resultDelete == true){
                               return mapping.findForward("list");
                            }
                            else{
                                request.setAttribute("errStat","eConfirmDelete");
                                return mapping.findForward("list");
                            }
                            
                        }
 /*
  *cancelling the display type delete request.
  */            
                         else if(buttonSubType.equals("Cancel")){
                                return mapping.findForward("list");
                             }
                  }
             catch(Exception DeleteSubType){
                   Debug.print("Display sub type Edit & Delete:::::::" + DeleteSubType);
                }
             
             }

//============================================GLOBAL TRY BLOCK END===================================================================
            }
          
//===============================================GLOBAL CATCH BLOCK================================================================            
            catch(Exception eAdvertise){
                Debug.print("In ActionAdvertise while calling advertise session bean::::::::" + eAdvertise );
            }
//============================================GLOBAL FORWARD METHOD===================================================================            
              return null;
        }       

    }
//=========================================THE END========================================================================================
       
