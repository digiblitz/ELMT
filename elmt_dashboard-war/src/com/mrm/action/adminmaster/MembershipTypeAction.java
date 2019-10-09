/*
 * MembershipTypeAction.java
 *
 * Created on September 7, 2006, 3:26 PM
 */

package com.mrm.action.adminmaster;

import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import com.hlcmember.type.util.HLCMembershipTypeMaster;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
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
 * @author karthikeyan
 * @version
 */

public class MembershipTypeAction extends Action {
    
    private static String membershipTypeId;
    private static String userTypeId;
    private static String membershipAmount;
    private static String userTypeName;
    private static String userid;
    private Date modifyDate;
                                
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try{

				String name = "ejb/HLCKaveryMembershipTypeJNDI";
				Context jndiContext = getInitialContext();
				Object obj=jndiContext.lookup(name);
				HLCKaveryMembershipTypeSessionRemoteHome home = (HLCKaveryMembershipTypeSessionRemoteHome)
				javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);
				HLCKaveryMembershipTypeSessionRemote remote = home.create();

				HLCMembershipTypeMaster objMemberTypeMaster = new HLCMembershipTypeMaster();

				System.out.println("\n after InitialContext Beginning emp Client...\n");
				Collection userTypeColl = remote.displayUserTypeDetails();
				HttpSession session=request.getSession(true);

				Vector userTypeVect = new Vector();
				ArrayList array = new ArrayList(userTypeColl);
				int i=1;

				for (Iterator it=array.iterator(); it.hasNext( ); ) {
				// Object anObject = it.next( );

				String str = String.valueOf(it.next( ));

				String[] result = str.split(":");
				for (int x=1 ; x<result.length; x++){
				//System.out.println(result[x]);
				userTypeId = result[x];
				userTypeVect.addElement(result[x]);


				}
				System.out.println("Vector element:"+userTypeId);

				}

				session.setAttribute("userTypeVect",userTypeVect);

                                } catch (Exception ex) {
                                    
				System.err.println("Caught an exception.");
				ex.printStackTrace();
                                
				}
				return(mapping.findForward("membershipdisplay"));
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
