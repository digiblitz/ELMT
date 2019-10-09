/*
 * AdminMembPaymentAction.java
 *
 * Created on October 16, 2006, 7:23 PM
 */
package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcaccounts.session.*;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCPaymentDetailVO;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import com.hlcmrm.util.Debug;
import java.util.Date;
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
 * @author karthikeyan
 * @version
 */
public class AdminMembNovaPayAction extends Action {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fwd = "";
        HttpSession session = request.getSession();

        MessageResources mr = getResources(request);

        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.usrreg");
        String jndiname2 = mr.getMessage("jndi.kavery");

        HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();

        try {

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);

            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();

            String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
            Object obj = jndiContext.lookup(name1);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(obj, HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();

            String usrStat = (String) session.getAttribute("usrStat");
            String ccStatus = (String) session.getAttribute("ccStatus");

            Object objref2 = jndiContext.lookup(jndiname2);

            HLCKaverySessionBeanStatfulRemoteHome home2 = (HLCKaverySessionBeanStatfulRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref2, HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote2 = home2.create();

            String mahanadhiJndi = mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" + mahanadhiJndi);

            Object maha = jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(maha, HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();

            objPayment = (HLCPaymentDetailVO) session.getAttribute("objPayment");
            String defStat = (String) session.getAttribute("defStat");

            Debug.print("Return from Nova:");

            String sslResult = request.getParameter("ssl_result");
            String sslResultMessage = request.getParameter("ssl_result_message");
            String sslTxnId = request.getParameter("ssl_txn_id");
            String sslApprovalCode = request.getParameter("ssl_approval_code");
            String sslCvv2Response = request.getParameter("ssl_cvv2_response");
            String sslAvsResponse = request.getParameter("ssl_avs_response");
            String sslTransactionType = request.getParameter("ssl_transaction_type");
            String sslInvoiceNo = request.getParameter("ssl_invoice_number");
            String sslEmail = request.getParameter("ssl_email");

            Debug.print(" Defeciency Payment Status :" + defStat);

            Debug.print("Sucessfully return from Nova sslResult:" + sslResult);
            Debug.print("Sucessfully return from Nova sslResultMessage:" + sslResultMessage);
            Debug.print("Sucessfully return from Nova sslTxnId:" + sslTxnId);
            Debug.print("Sucessfully return from Nova sslApprovalCode:" + sslApprovalCode);
            Debug.print("Sucessfully return from Nova sslCvv2Response:" + sslCvv2Response);
            Debug.print("Sucessfully return from Nova sslAvsResponse:" + sslAvsResponse);
            Debug.print("Sucessfully return from Nova sslTransactionType:" + sslTransactionType);
            Debug.print("Sucessfully return from Nova sslInvoiceNo:" + sslInvoiceNo);
            Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);

            Debug.print("Sucessfully Return objPaymentList:" + objPayment.toString());

            Debug.print("paymentId in nova servlet :" + objPayment.getPaymentId());

            if (sslResult.equals("0")) {

                if (objPayment != null) {

                    String amount1 = String.valueOf(objPayment.getAmount());

                    HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();

                    objPayDet.setUserId(objPayment.getUserId());
                    objPayDet.setIpAddress(objPayment.getIpAddress());
                    objPayDet.setParentPaymentId(objPayment.getParentPaymentId());
                    objPayDet.setPaymentId(objPayment.getPaymentId());
                    //objPayDet.setUserId(userId);
                    objPayDet.setCcName(objPayment.getCcName());
                    objPayDet.setCcType(objPayment.getCcType());
                    //objPayDet.setCcNumber(objPayment.getCcNumber());
                    objPayDet.setCcExpMonth(objPayment.getCcExpMonth());
                    objPayDet.setCcExpYear(objPayment.getCcExpYear());
                    objPayDet.setCcCvvid(objPayment.getCcCvvid());
                    objPayDet.setBankName(objPayment.getBankName());
                    objPayDet.setAmount(objPayment.getAmount());
                    objPayDet.setPaymentStatus(objPayment.getPaymentStatus());
                    objPayDet.setCcNumber(objPayment.getCcNumber());
                    objPayDet.setPaymentDate(objPayment.getPaymentDate());
                    objPayDet.setIpAddress(objPayment.getIpAddress());

                    Debug.print("objPayDet.getCcNumber() in payment nova servlet :" + objPayDet.getCcNumber());

                    objPayDet.setSslResult(sslResult);
                    objPayDet.setSslResultMessage(sslResultMessage);
                    objPayDet.setSslTxnId(sslTxnId);
                    objPayDet.setSslApprovalCode(sslApprovalCode);
                    objPayDet.setSslCvv2Response(sslCvv2Response);
                    objPayDet.setSslAvsResponse(sslAvsResponse);
                    objPayDet.setSslTransactionType(sslTransactionType);
                    objPayDet.setSslInvoiceNo(sslInvoiceNo);
                    objPayDet.setSslEmail(sslEmail);
                    objPayDet.setCheckAmount(0);

                    String memberId = (String) session.getAttribute("memberId");
                    Debug.print("memberId :" + memberId);

                    request.setAttribute("amount", String.valueOf(objPayment.getAmount()));

                    boolean status = memberRemote.editMemberStatus("Active", memberId);
                    Debug.print("memberRemote.editMemberStatus(Active,membid) :" + status);
                    String paymentId = objPayment.getPaymentId();
                    String statusId = remote.getStatusIBasedOnStatus("Active");
                    boolean historyUpdateStatus = remote.updateMemberHistoryDetailStatus(memberId, statusId, paymentId);

                    Debug.print("*******************************History Update Status: " + historyUpdateStatus);

//Setting the reconcile & active Status TRUE
                    if (paymentId != null || paymentId.trim().length() != 0) {
                        boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(objPayment.getPaymentId());
                        Debug.print("Update Status " + Update_Status);
                    }

                    boolean updtStatus = remote.updateApprovalDate(memberId, new Date());
                    Debug.print("remote.updateApprovalDate(memberId,new Date()) :" + updtStatus);

                    if (defStat != null) {
                        if (defStat.equalsIgnoreCase("yes")) {

                            Debug.print("inside deficiency payment block :");

                            Debug.print("objPayDet.toString() :" + objPayDet.toString());

                            boolean pStat = remote.insertPayment(objPayDet);
                            Debug.print("remote.insertPayment() :" + pStat);

                            boolean bal = remote2.updateBalanaceAmount(objPayment.getParentPaymentId(), 0);
                            Debug.print("remote1.updateBalanaceAmount(objPayment.getPaymentId(),0) :" + bal);

                        } else {
                            Debug.print("inside NoN-Deficiency payment block :");

                            Debug.print(" usrStat :" + usrStat);

                            if (!usrStat.equalsIgnoreCase("user")) {
                                boolean pStat = remote.insertPayment(objPayDet);
                                Debug.print("remote.insertPayment() :" + pStat);

                                boolean bal = remote2.updateBalanaceAmount(objPayment.getParentPaymentId(), 0);
                                Debug.print("remote1.updateBalanaceAmount(objPayment.getPaymentId(),0) :" + bal);

                            } else {
                                boolean stat = remote.updatePaymentStatus(objPayDet);
                                Debug.print("remote.updatePaymentStatus(objPayment) :" + stat);

                                Debug.print("objPayDet.getPaymentId() :" + objPayDet.getPaymentId());
                                boolean upStat = mahaRemote.updatePaymentStatusAccTxnDetails(objPayDet.getPaymentId());
                                Debug.print("remote.updatePaymentStatusAccTxnDetails(paymentId) :" + upStat);

                            }
                        }

                    }
                }
                fwd = "backtoList";
            } else {

                Debug.print("Not Inserted..:");
                session.setAttribute("objPayment", objPayment);

                fwd = "decline";

            }

        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }

        return mapping.findForward(fwd);

    }
}
