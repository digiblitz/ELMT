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
package com.StrgViewMgmnt;

import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcrole.management.HLCBrahmaputraSessionRemote;
import com.hlcrole.management.HLCBrahmaputraSessionRemoteHome;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemote;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemoteHome;
import com.svm.session.SatlujRemote;
import com.svm.session.SatlujRemoteHome;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;

public class StrategicViewManagement extends Action
{

    public StrategicViewManagement()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        java.util.List artifactsList = null;
        SatlujRemote satlujRemote;
       
        Context context = new InitialContext();
        Object object = context.lookup("ejb/HLCSatlujBean");
        SatlujRemoteHome satlujRemoteHome = (SatlujRemoteHome)PortableRemoteObject.narrow(object, SatlujRemoteHome.class);
        satlujRemote = satlujRemoteHome.create();
        javax.servlet.http.HttpSession session = request.getSession(true);
        String process = request.getParameter("process");
        Debug.print("StrategicViewManagement.process:" + process);
        if(process.equals("orgLevel"))
        {
            artifactsList = satlujRemote.displayOrgLevelArtifacts();
            request.setAttribute("artifactsList", artifactsList);
            Debug.print("StrategicViewManagement.displayOrgLevelArtifacts() sucessfully comes from servlet.");
            return mapping.findForward("strategicHome");
        }
        if(process.equals("bscLevel"))
        {
            artifactsList = satlujRemote.displayBSCArtifacts();
            request.setAttribute("artifactsList", artifactsList);
            Debug.print("StrategicViewManagement.displayBSCArtifacts() sucessfully comes from servlet.");
            return mapping.findForward("strategicBSC");
        }
        if(process.equals("orderLevel"))
        {
            artifactsList = satlujRemote.displayOrderArtifacts();
            request.setAttribute("artifactsList", artifactsList);
            Debug.print("StrategicViewManagement.displayOrderArtifacts() sucessfully comes from servlet.");
            return mapping.findForward("strategicOrder");
        }
        if(process.equals("orderToCashLevel"))
        {
            artifactsList = satlujRemote.displayOrderToCashArtifacts();
            request.setAttribute("artifactsList", artifactsList);
            Debug.print("StrategicViewManagement.displayOrderToCashArtifacts() sucessfully comes from servlet.");
            return mapping.findForward("strategicOrderToCash");
        }
        if(process.equals("orderEntryLevel"))
        {
            artifactsList = satlujRemote.displayOrderEntryArtifacts();
            request.setAttribute("artifactsList", artifactsList);
            Debug.print("StrategicViewManagement.displayOrderEntryArtifacts() sucessfully comes from servlet.");
            return mapping.findForward("strategicOrderEntry");
        }
        if(process.equals("initAddArtifact"))
            return mapping.findForward("initAddArtifact");
        try
        {
            if(process.equals("addOrgLevel"))
            {
                String name = request.getParameter("name");
                System.out.print((new StringBuilder()).append("name ################").append(name).toString());
                String path = request.getParameter("filename");
                String level = request.getParameter("level");
                String type = request.getParameter("type");
                String desc = request.getParameter("desc");
                String newPath = "";
                int count = 0;
                (new File("c:/cvsArtifacts/artifacts")).mkdirs();
                if(path != null)
                {
                    ArrayList arr = new ArrayList();
                    for(StringTokenizer st = new StringTokenizer(path, "\\"); st.hasMoreTokens();)
                    {
                        arr.add(count, st.nextToken());
                        count++;
                    }

                    newPath = (new StringBuilder()).append("c:/cvsArtifacts/artifacts/").append(arr.get(count - 1)).toString();
                    FileInputStream fis = new FileInputStream(path);
                    FileOutputStream fos = new FileOutputStream(newPath);
                    int c;
                    while((c = fis.read()) != -1) 
                        fos.write((char)c);
                }
                satlujRemote.addStrategicOrgLevelAtrifact(name, newPath, type, desc, level);
                request.setAttribute("added", "true");
                Debug.print("StrategicViewManagement.addStrategicOrgLevelAtrifact() sucessfully comes from servlet.");
                return mapping.findForward("addArtifact");
            }
            
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


            String userId = (String) session.getAttribute("userId");
            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote roleRemote = roleHome.create();

            String jndiName=mr.getMessage("jndi.mqm");
            Object qualObj = jndiContext.lookup(jndiName);
            HLCMembershipQualificationMatrixRemoteHome qhome = (HLCMembershipQualificationMatrixRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(qualObj,HLCMembershipQualificationMatrixRemoteHome.class);
            HLCMembershipQualificationMatrixRemote qremote = qhome.create();

            String jndiname1=mr.getMessage("jndi.usrreg");
            Object objref1 = jndiContext.lookup(jndiname1);

        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref1,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();

        String jndinameKrish=mr.getMessage("jndi.pc");
        Object krishnaObj = jndiContext.lookup(jndinameKrish);
            HLCKrishnaStatelessRemoteHome krishnaHome = (HLCKrishnaStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(krishnaObj,HLCKrishnaStatelessRemoteHome.class);
            HLCKrishnaStatelessRemote eveCalRemote = krishnaHome.create();
           
          //=============Dhivya Starts Here===================================  
          //==================================================initArtifact Artifact Management =====================================================================================
            if(process.equals("initCreateArtifact")){
            	String mainArtifactName=request.getParameter("mainArtifactName");
            	String mainArtiId=request.getParameter("mainArtiId");
                 Debug.print("StrategicViewManagement.initCreateArtifact() sucessfully comes from servlet.");
                 request.setAttribute("mainArtifactName",mainArtifactName);
                 request.setAttribute("mainArtiId",mainArtiId);
                 return mapping.findForward("frmAddArtifact");
             }
//==================================================create Artifact Management =====================================================================================
             else if(process.equals("createArtifact")){
                 Debug.print("StrategicViewManagement.createArtifact()");
                 String mainArtiname = request.getParameter("mainArtiname");
		
                  String subArtiName = request.getParameter("subArtiName");
                  String mainArtiId = request.getParameter("mainArtiId");
                 
                 Debug.print("StrategicViewManagement.createSubArtifact() subArtiName:" + subArtiName);
                 
                 if(mainArtiname!=null && subArtiName!=null && mainArtiId!=null){
		
                     boolean result = roleRemote.createSubArtifact(mainArtiname,subArtiName,mainArtiId);
		
                     Debug.print("StrategicViewManagement.createSubArtifact()");
                     if(result){
		
                    	 request.setAttribute("mainArtiId",mainArtiId);
                    	
                         return mapping.findForward("frmRedrListArtifacts");
		
                     }
                     else{
                    	 request.setAttribute("mainArtifactName",mainArtiname);
                    	 request.setAttribute("mainArtiId",mainArtiId);
                         return mapping.findForward("errRedirArtifact");
                     }
                 }
                 Debug.print("StrategicViewManagement.createArtifact() sucessfully comes from servlet.");
             }
            
             else if(process.equals("artifactList")){
                 Debug.print("StrategicViewManagement.artifactList()");
                 ArrayList artifactList = new ArrayList();
                 String mapPerId=null;
                 String retPrivViewId=null;
                 ArrayList viewDrop = new ArrayList();
                 
                 
                 if(request.getParameter("viewId")!=null){
                String viewId= request.getParameter("viewId");
                String viewName=roleRemote.getViewName(viewId);
                 session.setAttribute("viewVal", viewName);
                 request.setAttribute("viewId", viewId);
                 }        
                 
                 String sessionEntityId = (String)session.getAttribute("entityId");
                 String sessionRoleId = (String)session.getAttribute("roleId");
                 
                 viewDrop = roleRemote.getMyPrivilegesListForHeader(sessionRoleId, sessionEntityId);
                 
                 

                  if(request.getParameter("mapId")!=null){
                	  mapPerId=request.getParameter("mapId");
                  }else{
                	  mapPerId=(String) request.getAttribute("mainArtiId");  
                  }
                  Debug.print("StrategicViewManagement.artifactList mapPerId:" + mapPerId);
                 
                 artifactList = (ArrayList)roleRemote.getAllSubArtifacts(mapPerId);
                 String artifactName=roleRemote.getArtifactName(mapPerId);

                 request.setAttribute("allartifactList",null);
                 request.setAttribute("allartifactList",artifactList);
                 request.setAttribute("artifactName",artifactName);
                 request.setAttribute("mapPerId",mapPerId);
                 request.setAttribute("viewDrop",viewDrop);
                 request.setAttribute("retPrivViewId",retPrivViewId);

                 Debug.print("StrategicViewManagement.artifactList() sucessfully comes from servlet.");
                 return mapping.findForward("frmListArtifacts");
             }
            
             else if(process.equals("initeditArtifact")){
                 Debug.print("StrategicViewManagement.initeditArtifact()");
                 String artifactId = request.getParameter("artifactId");
                 String mainArtifactName=request.getParameter("mainArtifactName");
             	String mainArtiId=request.getParameter("mainArtiId");
 
                 if(artifactId!=null){
                     String  subArti = roleRemote.getSubArtifactName(artifactId);
                     request.setAttribute("subArtifact",subArti);
                 }
                 request.setAttribute("mainArtifactName",mainArtifactName);
                 request.setAttribute("mainArtiId",mainArtiId);
                 request.setAttribute("artifactId",artifactId);
                 
                 Debug.print("StrategicViewManagement.initeditArtifact() sucessfully comes from servlet.");
                 return mapping.findForward("frmEditArtifact");
             } else if(process.equals("editArtifact")){
                 Debug.print("StrategicViewManagement.editArtifact()");
                
                 String artifactId = request.getParameter("artifactId");
                 String mainArtifactName=request.getParameter("mainArtifact");
             	String mainArtiId=request.getParameter("mainArtiId");
             	
             	String subArtifact=request.getParameter("subArtifact");
		
                 boolean result = false;
               
                 if(artifactId!=null && artifactId.trim().length()!=0 && subArtifact!=null){
		
                     result = roleRemote.editSubArtifact(artifactId,subArtifact);
		
                     Debug.print("StrategicViewManagement.editArtifact() result:" + result);
                     if(result==true){
                         Debug.print("StrategicViewManagement.editArtifact() result:" + result);
                         if(artifactId!=null){
                             String  subArti = roleRemote.getSubArtifactName(artifactId);
                             request.setAttribute("subArtifact",subArti);
                         }
                         request.setAttribute("mainArtifactName",mainArtifactName);
                         request.setAttribute("mainArtiId",mainArtiId);
                         request.setAttribute("artifactId",artifactId);
                         return mapping.findForward("frmRedrListArtifacts");
                       
                     }
                     else{
                         Debug.print("StrategicViewManagement.editArtifact() result:" + result);
                         if(artifactId!=null){
                             String  subArti = roleRemote.getSubArtifactName(artifactId);
                             request.setAttribute("subArtifact",subArti);
                         }
                        
                         request.setAttribute("subArtifact", subArtifact);
                         request.setAttribute("mainArtifactName",mainArtifactName);
                         request.setAttribute("mainArtiId",mainArtiId);
                         request.setAttribute("artifactId",artifactId);
                         return mapping.findForward("errRedirEditArtifact");
                     }
                 }
                 Debug.print("StrategicViewManagement.editArtifact() sucessfully comes from servlet.");
             }
            
            
            
             else if(process.equals("deleteArtifact")){
                 Debug.print("StrategicViewManagement.deleteArtifact()");
                 String roleId = request.getParameter("roleId");
                 String rolename = request.getParameter("rolename");
                 String roledesc =request.getParameter("roledesc");
                 
                 String status = request.getParameter(roleId);
                 String chkRoleIdArr[] = request.getParameterValues("chk");

                 for(int i=0;i<chkRoleIdArr.length;i++)
                 Debug.print("StrategicViewManagement.deleteArtifact() checked records: "+chkRoleIdArr[i]);

                 boolean result = false;
                 Debug.print("StrategicViewManagement.deleteArtifact() values:" + roleId+"==="+rolename+"==="+roledesc+status);

                 if(chkRoleIdArr!=null){
                   
                     result = roleRemote.deleteRole(chkRoleIdArr);
                     Debug.print("StrategicViewManagement.deleteArtifact() result:" + result);
                     if(result==true){
                         Debug.print("StrategicViewManagement.deleteArtifact() result:" + result);
                       
                         if(chkRoleIdArr!=null){
                             String[]  resultString = roleRemote.getRole(chkRoleIdArr[0]);
                             request.setAttribute("roleDetails",resultString);
                         }
                         return mapping.findForward("frmRedrListArtifacts");
                     }
                     else{
                         Debug.print("StrategicViewManagement.deleteArtifact() result:" + result);
                       
                         if(chkRoleIdArr!=null){
                             String[]  resultString = roleRemote.getRole(chkRoleIdArr[0]);
                             request.setAttribute("roleDetails",resultString);
                         }

                         return mapping.findForward("errRedirEditArtifact");
                     }
                 }
                 Debug.print("StrategicViewManagement.deleteArtifact() sucessfully comes from servlet");
             } 
            
            
            
            
            
            
            //=======================Dhivya Ends Here==========================================
            
        }
        catch(Exception exp)
        {
            Debug.print((new StringBuilder()).append("In StrategicViewManagement :").append(exp.getMessage()).toString());
        }
        return mapping.findForward("LoginSuccess");
    }
}
