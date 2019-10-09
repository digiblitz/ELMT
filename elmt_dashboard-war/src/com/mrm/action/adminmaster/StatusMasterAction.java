/*
 * StatusMasterAction.java
 *
 * Created on September 7, 2006, 3:41 PM
 */

package com.mrm.action.adminmaster;

import com.hlchorse.member.util.HLCMembershipStatusMaster;
import com.hlcmembership.stateless.HLCKaveryStatelessMembershipServiceRemote;
import com.hlcmembership.stateless.HLCKaveryStatelessMembershipServiceRemoteHome;
import java.util.*;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author karthikeyan
 * @version
 */

public class StatusMasterAction extends Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try{

                Context jndiContext = getInitialContext();
                String name = "ejb/HLCKaveryMemberJNDI";
                Object obj=jndiContext.lookup(name);
                HLCKaveryStatelessMembershipServiceRemoteHome home = (HLCKaveryStatelessMembershipServiceRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryStatelessMembershipServiceRemoteHome.class);
                HLCKaveryStatelessMembershipServiceRemote remote = home.create();

                HLCMembershipStatusMaster objMembershipstatus = new HLCMembershipStatusMaster();

                System.out.println("\n after InitialContext Beginning emp Client...\n");
                String textfield1 = request.getParameter("stName");
		String textfield2 = request.getParameter("desArea");

		System.out.println("Name:"+textfield1);
        	System.out.println("Textarea:"+textfield2);

                objMembershipstatus.setStatusName(textfield1);
                objMembershipstatus.setDescription(textfield2);
                remote.addMembershipStatusMaster(objMembershipstatus);
                
                
        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        return(mapping.findForward("statusMaster"));
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
