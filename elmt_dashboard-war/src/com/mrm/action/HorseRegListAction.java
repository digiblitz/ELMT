/*
 * HorseRegListAction.java
 *
 * Created on September 10, 2006, 6:46 PM
 */

package com.mrm.action;

import java.util.Vector;
import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlchorse.form.display.*;
/**
 *
 * @author karthikeyan
 * @version
 */

public class HorseRegListAction extends Action {
    
  
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         try {

             /*
              * Initializing service locator for ejb/HLCKaverySessionStatefulBean
              */
             
          /*  Properties p =new Properties();
            p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
            p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
            p.setProperty( "java.naming.provider.url", "localhost:11199" );
            Context jndiContext = new InitialContext(p);
            
                    String name = "ejb/HLCKaverySessionStatefulBean";
                    System.out.println("\n after InitialContext Beginning emp Client...\n");
                    Object objref = jndiContext.lookup(name);*/
                    
                    MessageResources mr=getResources(request);
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.kaverysful");
                                      
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname);
                   
                    HLCKaverySessionStatefulRemoteHome home =
                    (HLCKaverySessionStatefulRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCKaverySessionStatefulRemoteHome.class);

                    HLCKaverySessionStatefulRemote remote = home.create();
                    System.out.println("After Create...");

                    Vector vObj = (Vector)remote.displayHorseMember();
                    request.setAttribute("vObj",vObj);
          }
          
                    catch(Exception e){
                        System.out.println("Exception might be due to improper insertion of data"+ e);
                        
                    }
             /*
              * Redirect to Horse Reg List page
              */
                            return mapping.findForward("HorseRegList");
                    
    }
}
