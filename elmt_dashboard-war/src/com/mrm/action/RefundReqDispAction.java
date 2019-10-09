/*
 * RefundReqDispAction.java
 *
 * Created on September 10, 2006, 4:51 PM
 */
package com.mrm.action;

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import java.util.*;
import com.hlccommon.util.Debug;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author karthikeyan
 * @version
 */
public class RefundReqDispAction extends Action {

    String name = "ejb/RefundSessionJNDI";
    String fwd = "";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();

        MessageResources mr = getResources(request);

        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.kaverysless");
        String jndiname2 = mr.getMessage("jndi.usrreg");
        String jndiname3 = mr.getMessage("jndi.kaverymrm");

        Context jndiContext = new InitialContext();


        String userId = (String) session.getAttribute("userId");
        String memberId = (String) session.getAttribute("memberId");

        /* if(memberId!=null)
        {*/
        Object objref = jndiContext.lookup(jndiname);

        HLCkaverySessionBeanStatlessRemoteHome home = (HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref, HLCkaverySessionBeanStatlessRemoteHome.class);
        HLCkaverySessionBeanStatlessRemote remote = home.create();

        ArrayList donSelect = new ArrayList();
        Vector dispMembershipTypeVect = new Vector();

        dispMembershipTypeVect = remote.displayMembershipType("Human");

        Object objref2 = jndiContext.lookup(jndiname2);
        HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref2, HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote1 = home1.create();
  
        String memTypId = remote1.getMembershipTypeIdBasedOnUserId(userId);
        Debug.print(" memTypId in servlet :" + memTypId);

        donSelect = remote.getMemberDonationDetails(userId);
        String[] addonPrice = remote.getFamilyAddOn("family member");

        Object objref3 = jndiContext.lookup(jndiname3);
        HLCkaveryStatelessRemoteHome home2 = (HLCkaveryStatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref3, HLCkaveryStatelessRemoteHome.class);
        HLCkaveryStatelessRemote remote2 = home2.create();

        ArrayList donDet = new ArrayList();
        donDet = remote2.getAllDonationDetails();

        request.setAttribute("dispMembershipTypeVect", dispMembershipTypeVect);
        request.setAttribute("memTypId", memTypId);
        request.setAttribute("addonPrice", addonPrice);
        request.setAttribute("donSelect", donSelect);
        request.setAttribute("donDet", donDet);

        fwd = "RefundApply";
        /* }
        else
        {
        fwd="RefundDenied";
        }*/
        return mapping.findForward(fwd);

    }
    }
                
       
     

