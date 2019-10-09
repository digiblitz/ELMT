/*
 * MeeOrgAnnConRegiListings.java
 *
 * Created on September 25, 2006, 3:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mee.action;
import com.hlchorse.form.util.Debug;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import java.util.*;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.regex.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
/**
 *
 * @author harmohan
 */
public class MeeOrgAnnConRegiListings extends Action {
    
   Vector vObj = new Vector();
    
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
            
            Vector vObj = new Vector();
            Vector nobj = new Vector();
            String memProcess = request.getParameter("memProcess");
            
            Debug.print("Annula Meeting Details In Servlet:" + memProcess);
            
            String userId = (String)request.getSession().getAttribute("userId");
              nobj = remote.displayAnnualDetailBasedOnUserID(userId);
              request.setAttribute("annConRegList",nobj);
              
           if(memProcess != null && memProcess.equals("dispDetail")){
                    String annualMeeId = request.getParameter("annualMeetingId");
                    System.out.println("Annual Meeting Id In Servlet : "+annualMeeId);
                    
                    vObj = (Vector)remote.displayAnnaulApplicationDetail(annualMeeId);
                    Debug.print("Vector element for user detail in Servlet : "+vObj);
                    request.setAttribute("viewMember",vObj);
                    nobj = null;
                    nobj = (Vector)remote.displayAnnaulSpecificationDetail(annualMeeId);
                    Debug.print("Vector element for specification detail in Servlet : "+nobj);
                    request.setAttribute("viewSpecification",nobj);
                    
                 return mapping.findForward("MeeOrgAnnConRegiDetail");
                
           }
                                          
        }
        catch(Exception e){
                
          System.out.print("Client Exception :" + e.getMessage());
            e.printStackTrace();
        }
        return(mapping.findForward("MeeOrgAnnConRegiListings"));
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
