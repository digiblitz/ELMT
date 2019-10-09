/*
 * MessageDBAction.java
 *
 * Created on October 12, 2006, 4:29 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.message.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.Debug;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.message.actionform.MessageDBActionForm;
import com.hlcmsg.util.HLClMsgDBRequestDetailsVO;
import java.text.SimpleDateFormat;
import org.apache.struts.actions.DispatchAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;

import com.hlcform.util.HLCPaymentDetails;
/**
 *
 * @author Shiva Kumar Subbiaha
 */
public class MessageDBAction extends DispatchAction{
    
    public ActionForward reqList(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.reqList() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        String userId = (String)request.getSession().getAttribute("userId");
        Debug.print("RequestLists:"+msgDBForm+",userId="+userId);
        List results = msgRemote.getMsgDownloadReqList(userId);
        Debug.print("RequestLists results :"+((results!=null)?results.size():0));
        msgDBForm.setReqLists(results);
        request.setAttribute(mapping.getName(),msgDBForm);
        return mapping.findForward("reqListView");
    }
    
    public ActionForward adminList(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.adminList() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        List results = msgRemote.getAdminMsgDownloadReqList();
        Debug.print("RequestLists results :"+((results!=null)?results.size():0));
        msgDBForm.setAdminLists(results);
        request.setAttribute(mapping.getName(),msgDBForm);
        return mapping.findForward("adminListView");
    }
    
    public ActionForward myDatabase(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.myDatabase() method calling ..........");
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        initalizeDropDowns(request);
        request.setAttribute(mapping.getName(),msgDBForm);
        return mapping.findForward("myDatabaseView");
    }
    
    public ActionForward viewDbList(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.viewDbList() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        String[] zipCodes = msgDBForm.getZipCode().split("-");
        Debug.print("Search Params : fromZip:"+msgDBForm);
        List results = msgRemote.SearchAndListDBAddress(zipCodes[0],zipCodes[1],msgDBForm.getMemberType(),
                msgDBForm.getStatus());
        Debug.print("viewDbList results :"+((results!=null)?results.size():0));
        msgDBForm.setDbLists(results);
        initalizeDropDowns(request);
        request.setAttribute(mapping.getName(),msgDBForm);
        return mapping.findForward("myDatabaseView");
    }
    
    public ActionForward showPayment(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.saveDb() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        String recordCount = request.getParameter("recordCount");
        String query = request.getParameter("query");
        String totalAmount = "0";
        if(recordCount!=null && recordCount.trim().length()>0) {
            totalAmount = String.valueOf(Integer.parseInt(recordCount)*0.15);
        }
        msgDBForm.setRecordCount(recordCount);
        msgDBForm.setAmount(totalAmount);
        msgDBForm.setQuery(query);
        Debug.print("saveDb Total Amount :"+totalAmount);
        request.setAttribute(mapping.getName(),msgDBForm);
        return mapping.findForward("amountView");
    }
    
    
    public ActionForward requestDB(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.requestDB() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        String reqId = request.getParameter("reqId");
        Debug.print("Request Database:"+reqId);
        HLClMsgDBRequestDetailsVO reqVO = msgRemote.getMsgDownloadReqListDetail(reqId);
        request.setAttribute("REQUEST_DETAILS",reqVO);
        return mapping.findForward("userReqView");
    }
    
    public ActionForward adminReqDB(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.adminReqDB() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        String reqId = request.getParameter("reqId");
        Debug.print("Request Database:"+reqId);
        HLClMsgDBRequestDetailsVO reqVO = msgRemote.getMsgDownloadReqListDetail(reqId);
        request.setAttribute("REQUEST_DETAILS",reqVO);
        return mapping.findForward("adminApproveView");
    }
    
     public ActionForward exportExcel(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.exportExcel() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);       
        String reqId = request.getParameter("reqId");
        Debug.print("Request Id:"+reqId);
        //lMsgDBRequestDetailsVO reqVO = msgRemote.getMsgDownloadReqListDetail(reqId);
        request.setAttribute("EXPORT_EXCEL",getList());
        return mapping.findForward("excelView");
    }
    
    public ActionForward updateAdminReqDB(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.updateAdminReqDB() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        String reqId = request.getParameter("reqId");
        Debug.print("Request Database:"+reqId);
        Debug.print("Request Database I/P:"+msgDBForm);
        boolean updateFlag =msgRemote.updateRequestStatus(reqId, msgDBForm.getStatus(), msgDBForm.getComment());
        Debug.print("Admin Req Details Updated:"+updateFlag);
        return mapping.findForward("adminListView");
    }
    
    public ActionForward saveDbAmount(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("MessageDBAction.saveDbAmount() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        MessageDBActionForm msgDBForm = (MessageDBActionForm)form;
        String userId = (String)request.getSession().getAttribute("userId");
        String paymentType = request.getParameter("paymentType");
        Debug.print("saveDbAmount Values:"+msgDBForm+",userId:"+userId);
        
        HLClMsgDBRequestDetailsVO  dbReq = new HLClMsgDBRequestDetailsVO();
        dbReq.setUserId(userId);
        dbReq.setTotalAmount(msgDBForm.getAmount());
        dbReq.setNoOfRecords(msgDBForm.getRecordCount());
        
        HLCPaymentDetails objPayment = new HLCPaymentDetails();
        
        if(paymentType.equalsIgnoreCase("check")) {
            if (isNotNull(msgDBForm.getCheckNo())){
                objPayment.setCheckNumber(msgDBForm.getCheckNo());
            }
            if (msgDBForm.getCheckDate()!=null && msgDBForm.getCheckDate().trim().length()>0){
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date dt =(Date) formatter.parse(msgDBForm.getCheckDate());
                objPayment.setCheckDate(dt);
            }else {
                objPayment.setCheckDate(new Date());
            }
            if (isNotNull(msgDBForm.getFavourOf())){
                objPayment.setFavourOf(msgDBForm.getFavourOf());
            }
        } else {
            
            if (isNotNull(msgDBForm.getCardNo())){
                objPayment.setCcNumber(msgDBForm.getCardNo().trim());
            }
            
            if (isNotNull(msgDBForm.getCardCvvNo())){
                objPayment.setCcCvvid(Integer.parseInt(msgDBForm.getCardCvvNo()));
            }
            
            objPayment.setCcName(msgDBForm.getCcName());
            objPayment.setCcType(msgDBForm.getCcType());
            if (isNotNull(msgDBForm.getCcExpMonth())){
                objPayment.setCcExpMonth(Integer.parseInt(msgDBForm.getCcExpMonth()));
            }
            if (isNotNull(msgDBForm.getCcExpYear())){
                objPayment.setCcExpYear(Integer.parseInt(msgDBForm.getCcExpYear()));
            }
            
        }
        
        if (isNotNull(msgDBForm.getAmount())){
            objPayment.setAmount(Double.parseDouble(msgDBForm.getAmount()));
        }
        objPayment.setPaymentDate(new Date());
        objPayment.setPaymentStatus("yes");
        objPayment.setUserId(userId);
        boolean reqDbFlag = msgRemote.createMsgDBRequestDetails(dbReq);
        Debug.print("Request Detail Created Flag:"+reqDbFlag);
        HLCkaverystatelessRemote kavRemote = paymentEJB(request);
        
        boolean payFlag =  kavRemote.addPaymentDetails(objPayment);
        Debug.print("Payment Detail Created Flag:"+payFlag);
        
        
        request.setAttribute(mapping.getName(),msgDBForm);
        return mapping.findForward("confirmView");
    }
    
    private ArrayList getDropDown(List mediaLists){
        
        ArrayList dropDwonLists = new ArrayList();
        for (Iterator it = mediaLists.iterator(); it.hasNext();) {
            String[] keyValue = (String[]) it.next();
            dropDwonLists.add(new LabelValueBean(keyValue[1],keyValue[0]));
        }
        return dropDwonLists;
    }
    
    private ArrayList getDropDown(Vector mediaLists){
        ArrayList dropDwonLists = new ArrayList();
        for (Iterator it = mediaLists.iterator(); it.hasNext();) {
            String[] keyValue = (String[]) it.next();
            dropDwonLists.add(new LabelValueBean(keyValue[2],keyValue[0]));
        }
        return dropDwonLists;
    }
    
    private void initalizeDropDowns(HttpServletRequest request) throws Exception{
        HLCVaigaiSessionRemote icpVaigai =    getIcPVaigaiEJB(request);
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        Vector hlcArea = icpVaigai.displayUseaArea();
        request.setAttribute("HLC_AREA",getDropDown(hlcArea));
        List memberShipTypes = msgRemote.getMembershipTypes();
        request.setAttribute("MEMBER_SHIP",getDropDown(memberShipTypes));
        List membershipStatus = msgRemote.getMembershipStatus();
        request.setAttribute("MEMBER_SHIP_STATUS",getDropDown(membershipStatus));
        Debug.print("MyDatabase HLC_AREA :"+hlcArea.size()+",MEMBER_SHIP :"
                +memberShipTypes.size()+",MEMBER_SHIP_STATUS :"+membershipStatus.size());
        
    }
    
    private HLCMessageSessionRemote initializeEJB(HttpServletRequest request) throws Exception{
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.message");
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
        HLCMessageSessionRemote remote = home.create();
        return remote;
    }
    
    
    private HLCkaverystatelessRemote paymentEJB(HttpServletRequest request) throws Exception{
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.usrreg");
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object obj=jndiContext.lookup(jndiname);
        HLCkaverystatelessRemoteHome home =
                (HLCkaverystatelessRemoteHome) PortableRemoteObject.narrow(obj, HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
        return remote;
    }
    
    
    
    
    private HLCVaigaiSessionRemote getIcPVaigaiEJB(HttpServletRequest request) throws Exception{
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
    
    private boolean isNotNull(String data) {
        return (data!=null && data.trim().length()>0) ? true :false;
    }
    
    private List getList(){
    
        String[] columnNames = {"First Name","Last Name","Address"};
         String[] values1 = {"Shiva","Kumar","Chennai"};
         String[] values2 = {"Rajesh","Kumar","Velur"};
          String[] values3 = {"Sharma","Kumar","Orissa"};
          String[] values4 = {"100.00","22/09/2006","Velacherry"};
          
         
         List list = new ArrayList();
         HashMap hash = new HashMap();
         for (int i = 0; i < columnNames.length; i++) {
             hash.put(columnNames[i],values1[i]);          
         }
         list.add(hash);
         hash = new HashMap();
         for (int i = 0; i < columnNames.length; i++) {
             hash.put(columnNames[i],values2[i]);          
         }         
         list.add(hash);
         
         hash = new HashMap();
         for (int i = 0; i < columnNames.length; i++) {
             hash.put(columnNames[i],values3[i]);          
         }
         
         list.add(hash);
         
           hash = new HashMap();
         for (int i = 0; i < columnNames.length; i++) {
             hash.put(columnNames[i],values4[i]);          
         }
         
         list.add(hash);
      return list;
    }
}
