/*
 * RefundReqApproveAction.java
 *
 * Created on September 11, 2006, 8:42 PM
 */

package com.mrm.action;


import com.hlcmrm.statless.HLCRefundSessionRemote;
import com.hlcmrm.statless.HLCRefundSessionRemoteHome;
import java.util.*;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.hlcmrm.util.*;
/**
 *
 * @author karthikeyan
 * @version
 */

public class RefundReqApproveAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Vector vObj = new Vector();
         String name = "ejb/HLCRefundSessionJNDI";
         String memberId = null;
         try {
                Context jndiContext = getInitialContext();
                Object obj=jndiContext.lookup(name);
                HLCRefundSessionRemoteHome home = (HLCRefundSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCRefundSessionRemoteHome.class);
                HLCRefundSessionRemote remote = home.create();
                
                HLCMembershipRefundDetails objRefundDetails = new HLCMembershipRefundDetails();
                HLCMembershipRefundTypeDetails objRefundTypeDetails = new HLCMembershipRefundTypeDetails();
                HLCMembershipRefundTypeMaster objRefundTypeMaster = new HLCMembershipRefundTypeMaster();
        
                HttpSession session=request.getSession();
                /*String[] details;
                details=(String[])session.getAttribute("usrdet");*/
                String membid=request.getParameter("membid");
                String refid=request.getParameter("refid");
                String userId=request.getParameter("userId");
                
                System.out.println("rid from servlet :" +refid);
                System.out.println("mid from servlet :" +membid);
                System.out.println("userId from servlet :" +userId);
                
                String ststus = request.getParameter("radiobutton");
                
                objRefundDetails.setRefundId(refid);
                objRefundDetails.setUserId(userId);
                objRefundDetails.setRefundAmount(Double.parseDouble(request.getParameter("appamt")));
                objRefundDetails.setBalanceAmount(Double.parseDouble(request.getParameter("appamt")));
                objRefundDetails.setRefundDate(new Date());
                objRefundDetails.setRefundStatus(request.getParameter("radiobutton"));
               
                 System.out.println("from Refund approv action....");

                 System.out.println("ref id : "+refid);
                 System.out.println("ref amt :" +Double.parseDouble(request.getParameter("appamt"))); 
                 System.out.println("ref date :" +new Date());
                 System.out.println("status :" +request.getParameter("radiobutton"));
                 
                 System.out.println(objRefundDetails.getRefundStatus());
                 
                 remote.editRefundDetails(objRefundDetails);
                    
                 System.out.println("inserted succesfully........from approv action....");
    
           } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
          return mapping.findForward("RefundListAction");
         
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