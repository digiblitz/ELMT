/*
 * MerchantAction.java
 *
 * Created on November 10, 2006, 11:29 AM
 */
package com.mee.action;

import com.hlccommon.util.Debug;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author hari
 * @version
 */
public class MerchantAction extends Action {

    private final static String process = "add";

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /*try
        {*/
        MessageResources mr = getResources(request);
        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.icp");
        System.setProperty(namingfactory, contextfactory);
        System.setProperty(urlprovider, lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);

        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();

        HttpSession session = request.getSession(true);

        String process = request.getParameter("process");
        System.out.println("Process is " + process);

        if (process.equals("add")) {
            try {
                String merchantId;
                String merchantLoginId;
                String merchantUserId;
                String merchantPin;
                String[] db_data = remote.gettMerchantDetails();
                session.setAttribute("value", db_data);
                merchantId = db_data[0];
                merchantLoginId = db_data[1];
                merchantUserId = db_data[2];
                merchantPin = db_data[3];
                Debug.print("Merchant Id " + merchantId);
                Debug.print("Login Id" + merchantUserId);
                Debug.print("User Id" + merchantUserId);
                Debug.print("Mechant Pin" + merchantPin);
                System.out.println("Add Page");
            } catch (Exception e) {
                Debug.print(" Error : " + e.getMessage());
            }
            return mapping.findForward("AddMerchantDet");
        }
        if (process.equals("update")) {
            try {
                String merchId = request.getParameter("mId");
                String merchLogId = request.getParameter("merchId");
                String userId = request.getParameter("userId");
                String mercPinNo = request.getParameter("mercPinNo");
                Debug.print("MerchId" + merchId);
                Debug.print("Merchant Id " + merchLogId);
                Debug.print("User Id " + userId);
                Debug.print("Merchant Pin No/: ", mercPinNo);
                boolean bol = remote.updateMerchantDetails(merchId, merchLogId, userId, mercPinNo);
                if (bol == true) {
                    Debug.print("Result in Update is" + bol);
                    System.out.println("Update True Page");
                    return mapping.findForward("UpdateMerchantDet");
                }
            } catch (Exception e) {
                Debug.print(" Error : " + e.getMessage());
            }
        }
        return mapping.findForward("AddMerchantDet");
    }
}
