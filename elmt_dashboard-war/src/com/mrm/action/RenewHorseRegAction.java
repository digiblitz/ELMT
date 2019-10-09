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
package com.mrm.action;
/*
 * RenewHorseRegAction.java
 *
 * Created on August 26, 2006, 2:52 PM
 */

import com.hlchorse.form.util.HLCHorseDisplayVO;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.util.MessageResources;
import com.hlchorse.form.display.*;
/**
 *
 * @author karthikeyan
 * @version
 */

public class RenewHorseRegAction extends Action {
    
   
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         String fwd="";
         HttpSession session=request.getSession();
    
         try {
                    MessageResources mr=getResources(request);
                    
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String kaverysfuljndi=mr.getMessage("jndi.kaverysful");
                    
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(kaverysfuljndi);
                    
                    HLCHorseDisplayVO objHorseDisp = new HLCHorseDisplayVO();
                    
                    HLCKaverySessionStatefulRemoteHome home =
                    (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref, HLCKaverySessionStatefulRemoteHome.class);
           
                    HLCKaverySessionStatefulRemote remote = null;
                   
                    Vector RenewHorseVect = new Vector();
                                        
                            remote = home.create();

                            String memberId=(String)session.getAttribute("memberId");
                            boolean status=remote.getMemberShipStatus(memberId);
                            
                             String memid=request.getParameter("memid");
                             objHorseDisp=(HLCHorseDisplayVO)remote.displayHorseDetails(memid);
                           
                            request.setAttribute("objHorseDisp",objHorseDisp);
                            
//---------------- Disp type details -------------------------
                            
                            String kaveryslessjndi=mr.getMessage("jndi.kaverysless");
                            Context jndiContext1 = new InitialContext();
                            Object objtyperef = jndiContext1.lookup(kaveryslessjndi);
                            
                            HLCkaverySessionBeanStatlessRemoteHome home1 =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objtyperef, HLCkaverySessionBeanStatlessRemoteHome.class);
                            Vector horsememtypVect = new Vector();
                            Vector horsesertypVect = new Vector();
                            
                            HLCkaverySessionBeanStatlessRemote remote1 = home1.create();
                            horsememtypVect = (Vector)remote1.displayMembershipType("Horse");
                            horsesertypVect= (Vector)remote1.displayHorseServiceType("Horse");
                            List selectedlist = (List)objHorseDisp.gethorseServiceTypeName();
                            
                            System.out.println("horse membership typ size :" +horsememtypVect.size());
                            System.out.println("horse ser typ size :" +horsesertypVect.size());
                            System.out.println("horse ser typ selectedlist :" +selectedlist.size());
                                          
                            request.setAttribute("horsememberVect", horsememtypVect);
                            request.setAttribute("horsesertypVect", horsesertypVect);
                            request.setAttribute("selectedlist", selectedlist);
                            
                            String renstatus=request.getParameter("click");
                            System.out.println("renewal status clicked is :"+renstatus);
                          
                            if(renstatus!=null)
                            {
                            if(renstatus.equalsIgnoreCase("Renew") || renstatus.equalsIgnoreCase("Downgrade"))
                            {
                            if(remote.getMemberShipStatus(memid))
                            {
                         
                            System.out.println("inside "+renstatus+" success status......"+status);
                            fwd="RenewHorseReg";
                    
                            }
                            else
                            {
                                System.out.println("inside "+renstatus+" fail status......"+status);
                                fwd="renewalfailed";
                            }
                            
                            }
                            else
                            {
                                 System.out.println("inside "+renstatus+" clicked......");
                                 fwd="RenewHorseReg";
                            }
                     }
                            
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
          if(request.getParameter("process")!=null)
         {         
            return mapping.findForward("ViewHorseReg");
          }
          else
          {
            return mapping.findForward(fwd);
          }
    }
    
}
