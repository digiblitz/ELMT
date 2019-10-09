/*
 * EndorseRetryCardPayAction.java
 *
 * Created on January 4, 2008, 4:13 PM
 */

package com.mee.action;

import com.mee.actionform.FormEventOrgRenewal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.InitialContext;
import javax.naming.Context;
import com.hlcmro.util.*;
import com.hlcmro.org.*;
import java.util.Properties;
import java.text.*;
import com.hlccommon.util.Debug;
import com.hlcmro.renewal.*;
import com.hlcform.stateless.*;


/**
 *
 * @author Vidhya
 * @version
 */
public class EndorseRetryCardPayAction extends Action {
    
    public ActionForward execute(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)throws Exception {
             try{
                    Properties p =new Properties();
                    p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
                    p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
                    p.setProperty( "java.naming.provider.url", "localhost:11199" );

                    Context jndiContext = new InitialContext(p);
                    Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
                    HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
                    HLCVaigaiSessionRemote remote = home.create(); 
                    HttpSession session = request.getSession(false);
                 
                    FormEventOrgRenewal endForm=(FormEventOrgRenewal)form; 
                    HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
                    HLCPaymentDetailsVO objPayDet = new HLCPaymentDetailsVO();

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
                objPayDet = (HLCPaymentDetailsVO) session.getAttribute("objPaymentList");

                Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
                Debug.print("Sucessfully return from Nova objActDet:" + objRenewalDet);
                Debug.print("Sucessfully return from Nova objPayDet:" + objPayDet);
                
                //if(sslResult.equals("0")){
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
                        Debug.print("Activity Organizer  Sucessfully Getting Started:");
                       
                        boolean updateResult=false;
                        if(objPayDet.getPaymentId()!=null){
                        updateResult=remote.updatePayment(objPayDet);
                        }
                        Debug.print("updateResult :"+updateResult);
                        session.setAttribute("objRenewalDet",null);
                        session.setAttribute("objPaymentList",null);
                        request.setAttribute("eventId",String.valueOf(objRenewalDet.getEventId())); 
                        if(sslResult.equals("0")){
                        request.setAttribute("paymentMode","card"); 
                        request.setAttribute("amount",String.valueOf(objPayDet.getAmount()));
                        return mapping.findForward("forEndorseSuccess");
                        }else if(sslResult.equals("1")){
                        
                        request.setAttribute("paymentId",objPayDet.getPaymentId()); 
                        request.setAttribute("price",String.valueOf(objPayDet.getAmount()));
                        return mapping.findForward("frmMeeRenewalSuccess");    
                        }
                     }
                //}
                Debug.print("Payment Details are not inserted...");
                //request.setAttribute("eventId",String.valueOf(objRenewalDet.getEventId()));
                request.setAttribute("paymentId",objPayDet.getPaymentId());
                request.setAttribute("price",String.valueOf(objPayDet.getAmount()));
                return mapping.findForward("frmMeeRenewalSuccess");   
             }
             catch( Exception e ){
                e.printStackTrace();
            }
           
           return null;
    }
}
