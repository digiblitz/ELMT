/*
 * AdminSetDateMasterAction.java
 *
 * Created on September 23, 2006, 11:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mee.action;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.Debug;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import java.lang.Double;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.regex.*;
import java.lang.String;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author harmohan
 */
public class AdminSetDateMasterAction extends Action {
    
   public ActionForward execute(ActionMapping mapping, ActionForm  form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
       
       
      try {

            Context jndiContext = getInitialContext();
            String name = "ejb/HLCVaigaiSessionJNDI";
            System.out.println("\n after InitialContext Beginning emp Client...\n");
            Object objref = jndiContext.lookup(name);
            HLCVaigaiSessionRemoteHome home =
            (HLCVaigaiSessionRemoteHome) PortableRemoteObject.narrow(objref, HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            
           
            Vector nobj = new Vector();
            String adminSetDateProcess = request.getParameter("adminSetDateProcess");
            if (adminSetDateProcess != null){
                Debug.print("MemeberShip Name In Servlet:" + adminSetDateProcess);
            }

             nobj = remote.displayEventRegistrationTypeMaster();
             request.setAttribute("eventRegMasterVect",nobj);
             
            String eventRegMasterID = null;
            Date regDate = null;
            Date meeDate = null;
            int noOfDays = 0;

              if(adminSetDateProcess != null && adminSetDateProcess.equals("setDateForEvent")){
                
                eventRegMasterID = request.getParameter("eventRegMaster_sel");
                Debug.print("eventRegMasterID : "+eventRegMasterID);
                String regMeeName = request.getParameter("");
                String regDate1 = request.getParameter("regDate"); 
                String meeDate1 = request.getParameter("meeDate"); 
                Debug.print(" Registartion Date : "+regDate1);
                Debug.print(" Meeting Date : "+meeDate1);
                noOfDays = Integer.parseInt(request.getParameter("noOfDays"));
                Debug.print("No of Days : "+noOfDays);
                
                try {
                    boolean bol = remote.updateEventRegistrationTypeMaster(eventRegMasterID, null,
                            regDate1,meeDate1,noOfDays);
                    Debug.print("Result for add in Servlet : "+bol);

                    if (bol == true){
                        return mapping.findForward("conformUpdate");
                    }else {
                        request.setAttribute("err","st");
                        return mapping.findForward("frmMeeAdminSetDateMaster");
                    }
                }catch (Exception e){
                    Debug.print(" Error : "+e.getMessage());
                }
            }
            
            
                    
            }
        catch(Exception e){
                /* this will most likely be because the customer already exists in the database. */
       // System.out.print("Client Exception :" + e);
            e.printStackTrace();
        }
        return(mapping.findForward("frmMeeAdminSetDateMaster"));
   }
   
   public static Context getInitialContext()
        throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
    
}
