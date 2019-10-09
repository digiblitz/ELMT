/*
 * EventRegPendingCardPayAction.java
 *
 * Created on January 21, 2008, 2:35 PM
 */

package com.mee.action;

import com.hlcmeeting.util.HLCEventRegPendingVO;
import com.hlcmeeting.util.HLCPaymentDetails;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import com.hlcutil.Debug;
import java.text.SimpleDateFormat;
import javax.naming.Context;
import javax.naming.InitialContext;
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
 * @author Vidhya
 * @version
 */

public class EventRegPendingCardPayAction extends Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
        
       try{
                    
                HttpSession session = request.getSession(false);    
                String cmd=request.getParameter("cmd");
                HLCVaigaiSessionRemote vaiRemote  = initializeVaigaiEJB(request);
                HLCPaymentDetails objPayDet = new HLCPaymentDetails();
                HLCEventRegPendingVO objPendingVO= new HLCEventRegPendingVO();
                HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Debug.print("Return from Nova");
                
                String sslResult = request.getParameter("ssl_result");
                String sslResultMessage =  request.getParameter("ssl_result_message");
                String sslTxnId =  request.getParameter("ssl_txn_id");
                String sslApprovalCode = request.getParameter("ssl_approval_code");
                String sslCvv2Response = request.getParameter("ssl_cvv2_response");
                String sslAvsResponse = request.getParameter("ssl_avs_response");
                String sslTransactionType =  request.getParameter("ssl_transaction_type");
                String sslInvoiceNo = request.getParameter("ssl_invoice_number");
                String sslEmail = request.getParameter("ssl_email");
                
                Debug.print("Sucessfully return from Nova sslResult:" + sslResult);
                Debug.print("Sucessfully return from Nova sslResultMessage:" + sslResultMessage);
                Debug.print("Sucessfully return from Nova sslTxnId:" + sslTxnId);
                Debug.print("Sucessfully return from Nova sslApprovalCode:" + sslApprovalCode);
                Debug.print("Sucessfully return from Nova sslCvv2Response:" + sslCvv2Response);
                Debug.print("Sucessfully return from Nova sslAvsResponse:" + sslAvsResponse);
                Debug.print("Sucessfully return from Nova sslTransactionType:" + sslTransactionType);
                Debug.print("Sucessfully return from Nova sslInvoiceNo:" + sslInvoiceNo);
                Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);
               
                
                objRenewalDet = (HLCRenewalOrganizerDetails) session.getAttribute("objRenewalDet");
                objPayDet = (HLCPaymentDetails) session.getAttribute("objPaymentList");

                Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
                Debug.print("Sucessfully return from Nova objActDet:" + objRenewalDet);
                Debug.print("Sucessfully return from Nova objPayDet:" + objPayDet);
                
                
                    if(objRenewalDet!=null && objPayDet!=null){
                        objPayDet.setSslResult(sslResult);
                        objPayDet.setSslResultMessage(sslResultMessage);
                        objPayDet.setSslTxnId(sslTxnId);
                        objPayDet.setSslApprovalCode(sslApprovalCode);
                        objPayDet.setSslCvv2Response(sslCvv2Response);
                        objPayDet.setSslAvsResponse(sslAvsResponse);
                        objPayDet.setSslTransactionType(sslTransactionType);
                        objPayDet.setSslInvoiceNo(sslInvoiceNo);
                        objPayDet.setSslEmail(sslEmail);
                        Debug.print("Activity User Sucessfully Getting Started:");
                       
                        boolean updateResult=false;
                        if(objPayDet.getPaymentId()!=null){
                        updateResult=vaiRemote.updatePendingPayment(objPayDet);
                        }
                        Debug.print("updateResult :"+updateResult);
                        session.setAttribute("objRenewalDet",null);
                        session.setAttribute("objPaymentList",null);
                        request.setAttribute("eventId",String.valueOf(objRenewalDet.getEventId())); 
                        if(sslResult.equals("0")){
                        request.setAttribute("paymentMode","card"); 
                        request.setAttribute("amount",String.valueOf(objPayDet.getAmount()));
                        return mapping.findForward("frmEventRegPendingCardSuccess");
                        }else if(sslResult.equals("1")){
                        
                        request.setAttribute("paymentId",objPayDet.getPaymentId()); 
                        request.setAttribute("price",String.valueOf(objPayDet.getAmount()));
                        return mapping.findForward("cardDecline");    
                        }
                     }
               
                Debug.print("Payment Details are not inserted...");
                //request.setAttribute("eventId",String.valueOf(objRenewalDet.getEventId()));
                request.setAttribute("paymentId",objPayDet.getPaymentId());
                request.setAttribute("price",String.valueOf(objPayDet.getAmount()));
                return mapping.findForward("cardDecline");   
             }
             catch( Exception e ){
                e.printStackTrace();
            }
        return null;
    }
    private HLCVaigaiSessionRemote initializeVaigaiEJB(HttpServletRequest request) throws Exception{
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
        return remote;
    }
}
