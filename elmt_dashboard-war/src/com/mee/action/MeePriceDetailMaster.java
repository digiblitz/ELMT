/*
 * MeePriceDetailMaster.java
 *
 * Created on September 22, 2006, 8:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mee.action;

import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.Debug;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.*;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.*;
/**   
 *
 * @author harmohan
 */
public class MeePriceDetailMaster extends Action {
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         
         try
        {
            /*
             * Service Locator for ejb/HLCVaigaiSessionJNDI
 JNDI
             */
            
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

            System.out.println("\n after InitialContext inside icp user Meeting list action...\n");


            Vector vObj = new Vector();
            Vector nobj = new Vector();
            String meeProcess = request.getParameter("meeProcess");

            Debug.print("Activity Category Name In Servlet:" + meeProcess);

            nobj = remote.displayActivityCategory();
            request.setAttribute("meeCataTypeVect",nobj);
            
            vObj = (Vector) remote.displayUserType();
            request.setAttribute("userTypeVec",vObj);
            //meeCatagoryTyp
            String meeActivityTypId = request.getParameter("meeCatagoryTyp");
           if(meeActivityTypId !=null && meeActivityTypId.trim().length()>0) {     

                Debug.print("meeActivityTypId in Servlet : " + meeActivityTypId);
                vObj = (Vector) remote.displaySpecification(meeActivityTypId);
                request.setAttribute("dispSpecifcation",vObj);
           }              
                  }
      
                      catch(Exception ex)
                        {
                            System.err.println("Caught an exception."+ex.getMessage());
                            ex.printStackTrace();
                        }
         return(mapping.findForward("priceDetailMaster"));
    }
    
}
