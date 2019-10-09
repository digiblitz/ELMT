/*
 * RefundListAction.java
 *
 * Created on September 11, 2006, 11:06 AM
 */

package com.mrm.action;

import com.hlcmrm.statless.HLCRefundSessionRemote;
import com.hlcmrm.statless.HLCRefundSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import com.hlcmrm.util.*;
/**
 *
 * @author karthikeyan
 * @version
 */

public class RefundListAction extends Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         Vector vObj = new Vector();
         String name = "ejb/HLCRefundSessionJNDI";
         String userId = null;
         
         Vector refundDetail=new Vector();
         Vector usrdet=new Vector();
          
         try {
                Context jndiContext = getInitialContext();
                Object obj=jndiContext.lookup(name);
                HLCRefundSessionRemoteHome home = (HLCRefundSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCRefundSessionRemoteHome.class);
                HLCRefundSessionRemote remote = home.create();
                
                HLCMembershipRefundDetails objRefundDetails = new HLCMembershipRefundDetails();
                HLCMembershipRefundTypeDetails objRefundTypeDetails = new HLCMembershipRefundTypeDetails();
                HLCMembershipRefundTypeMaster objRefundTypeMaster = new HLCMembershipRefundTypeMaster();
                
                  vObj = remote.getRefundDetailList();
                 Enumeration it1 = vObj.elements();
                while(it1.hasMoreElements()){
                    String[] reflist= (String[])it1.nextElement();
                    for(int i=0;i<reflist.length; i++){
                        System.out.print("   "+reflist[i]);
                    System.out.println();
                    
                    }
                   
                    userId = reflist[13];
                    System.out.println("userId in servlet :" +userId);
                    
                 Collection col =remote.memberDetailsOnUserId(userId);
                 System.out.println("user id after coll :" +userId);
                    
                    Vector array = new Vector(col);
                   /// ArrayList array = new ArrayList(col);
                    Object o = array.get(0);
                    Object o1 = array.get(1);
                    HLCUserMaster objUser = null;
                    HLCContactDetails objContact = null;
                    if (o instanceof HLCUserMaster) {
                        objUser = (HLCUserMaster) o;
                    }
                    if (o1 instanceof HLCContactDetails) {
                        objContact = (HLCContactDetails) o1;
                    }
               // System.out.println("Size of the Vector : "+V.size());
               // HLCUserMaster objUser = (HLCUserMaster)vObj.elementAt(0);
               // HLCContactDetails objContact = (HLCContactDetails)vObj.elementAt(1);
                    
                System.out.print("--------------------- from servlet : ---------------------");
                
                String mstatus="Pending";
                
                refundDetail = remote.getRefundBasedOnStatus(mstatus);
                               
                String[] User=new String[10];
                
                User[0]=reflist[3]; // date of req
                User[1]=objUser.getFirstName();
                User[2]=objUser.getLastName();
                User[3]=objUser.getEmailId();
                User[4]=reflist[1];  // memb id
                User[5]=reflist[2];  // claim amt
                User[6]=reflist[11]; // memb comment 
                User[7]=reflist[0]; // Refund Id 
                User[8]=userId; // user Id 
                
                System.out.println("after vector ..........");
                /*System.out.println("Status :" +reflist[12]);*/
                usrdet.add(User);
                System.out.println(User[0]+" "+User[1]);
                }
                 
         System.out.print("--------------------- end of servlet print. ---------------------");        
                 
        request.setAttribute("User",usrdet);
        request.setAttribute("refundDetail",refundDetail);
        
              
        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
         if(request.getParameter("id")==null)
         {
        
              return mapping.findForward("RefundList");
              
         }
         else
         {
                return mapping.findForward("RefundApproveDisp");
         }
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