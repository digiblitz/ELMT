/*
 * MemberRegiListAction.java
 *
 * Created on September 12, 2006, 12:58 PM
 */

package com.mrm.action;

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlchorse.form.util.Debug;
import com.hlchorse.form.util.HLCHorseDisplayVO;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCPaymentDetailVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.regex.*;
import java.text.*;
import com.hlcform.util.HLCMemberDetails;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlchorse.form.display.*;


/**
 *
 * @author henry
 * @version
 */

public class MemberRegiListAction extends Action {
    /* forward name="success" path="" */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Debug.print("MemberRegiListAction is calling......");
        MessageResources mr=getResources(request);
        
        try {
            
            Context jndiContext = getInitialContext();
            String name = "ejb/HLCKaverySessionStatefulBean";
            System.out.println("\n after InitialContext Beginning emp Client...\n");
            Object objref = jndiContext.lookup(name);
            
            HLCKaverySessionStatefulRemoteHome home =
            (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref, HLCKaverySessionStatefulRemoteHome.class);
            HLCKaverySessionStatefulRemote remote = home.create();
                                  
            String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
            //Context jndiContext1 = getInitialContext();
            Object obj=jndiContext.lookup(name1);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
            
            //String statusID="b9f3f76a-1aa7-4e19-b457-43104413828e";
            
            String name2="ejb/HLCMemberRegistrationJNDI";
            
            Object ob=jndiContext.lookup(name2);
            HLCkaverystatelessRemoteHome home2 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(ob,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote2 = home2.create();
            
            String memRegiListProcess = request.getParameter("memRegiListProcess");
            String memTypId1 = request.getParameter("memTypId");
            String memdispTypId = request.getParameter("dispId");
            
/// Transaction
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
           
                                 
            Debug.print("MemeberShip Name In Servlet:" + memRegiListProcess);
            HttpSession session = request.getSession();
            
            String staff_user_id= (String) session.getAttribute("staff_user_id");
            //   System.out.println("MemberDisplayID...................."+memdispTypId);
            
            
            /*====================Henu.. Disply Drop Down List ============================*/
            
 /*
         Loading all MemberShip details..... at FirstTime
  */
            
            if(memRegiListProcess.equalsIgnoreCase("initFind")) {
                try{
                    Debug.print("initial search is loading......." );
                    
                             /* String statusName=request.getParameter("status");
                              Debug.print("status name from servlet :" +statusName);
                              
                              String statusID=remote2.getStatusIBasedOnStatus(statusName);
                              Debug.print("statusID from servlet :"+statusID);
                              session.setAttribute("statusID",statusID);*/
                    
                    Vector  userVect =(Vector)memberRemote.displayUserTypeDetails();
                    session.setAttribute("userTypeVect",userVect);
                    //System.out.println("servletvector:"+userVect);
                    session.setAttribute("userTypeVect2",null);
                    
                    Vector userVect1=(Vector)memberRemote.displayMembershipTypeDetails1(2007);
                    session.setAttribute("userTypeVect1",userVect1);
                    //System.out.println("servletvector1:"+userVect1);
                    request.setAttribute("memTypId",memTypId1);
                    session.setAttribute("userTypeVect2",null);
                    
                    return mapping.findForward("displayMemberRegiList");
                } catch(Exception emember) {
                    Debug.print("while Loading User Type ID page" + emember);
                    
                }
            } else if(memRegiListProcess.equalsIgnoreCase("getMemberTypes")){
                String year = request.getParameter("year");
                int yearVal = 0;
                if(year!=null &&year.trim().length()!=0){
                    yearVal  =Integer.parseInt(year);
                }
                Vector userVect1=(Vector)memberRemote.displayMembershipTypeDetails1(yearVal);
                Debug.print("Vector Returned:"+userVect1);
                request.setAttribute("year",year);
                session.setAttribute("userTypeVect1",userVect1);
            } else if(memRegiListProcess.equalsIgnoreCase("memRegiListSubUserType")) {
                if(memTypId1.length()!=0 && memTypId1!=null) {
                    try {
                        String usrtypname=request.getParameter("usrtyp");
                        String year = request.getParameter("year");
                        int yearVal = Integer.parseInt(year);
                        Debug.print("memtypname from servlet MemberDetails block :" + usrtypname);
                        request.setAttribute("usrtypname",usrtypname);
                        
                        //Vector userVect1=(Vector)memberRemote.displayMembershipType(memTypId1);
                        Vector userVect1=(Vector)memberRemote.displayMembershipTypeDetails1(yearVal);
                        session.setAttribute("userTypeVect1",userVect1);
                        System.out.println("servletvector1:"+userVect1);
                        request.setAttribute("memTypId",memTypId1);
                        session.setAttribute("userTypeVect2",null);
                        request.setAttribute("year",year);
                        return mapping.findForward("displayMemberRegiList");
                    } catch(Exception emember) {
                        Debug.print("while Loading User Type ID page" + emember);
                        
                    }
                }
            }
 /*
         Populate Member Type Dropdown List...
  */
            else if(memRegiListProcess.equalsIgnoreCase("memRegiListMemberType")) {
                Debug.print("memRegiListMemberType Method Called");
                String year = request.getParameter("year");
                request.setAttribute("year",year);
                if(memdispTypId.length()!=0 && memdispTypId!=null)  {
                    try{
                        int rCnt =0;
                        int pNo =1;
                        String statusName=request.getParameter("status");
                        Debug.print("status name from servlet :" +statusName);
                        String pageNo = request.getParameter("pn");
                        
                        if(pageNo!=null){
                            pNo = Integer.parseInt(pageNo);
                        }
                        Debug.print("Page NO :"+pNo);
                        String statid=remote2.getStatusIBasedOnStatus(statusName);
                        Debug.print("statusID from servlet :"+statid);
                        //session.setAttribute("statusID",statusID);
                        
                        System.out.println("MemberDisplayID...................."+memdispTypId);
                        // String statid=(String)session.getAttribute("statusID");
                        Debug.print("statusID from session inside servlet MemberDetails block :" + statid);
                        String usrtypname=request.getParameter("usrtyp");
                        Debug.print("memtypname from servlet MemberDetails block :" + usrtypname);
                        request.setAttribute("memdispTypId",memdispTypId);
                        request.setAttribute("memTypId",memTypId1);
                        request.setAttribute("status",statusName);
                        
                        if(usrtypname.equalsIgnoreCase("Human")) {
                            Debug.print("Inside Human Details Block ......");
                            if(memdispTypId!=null && memdispTypId.trim().length()!=0 && statid!=null && statid.trim().length()!=0){
                                rCnt = memberRemote.memberRowCount(memdispTypId,statid);
                                Vector userVect2=(Vector)memberRemote.displayMembershipDetailByMemberIDe(memdispTypId,statid,pNo);
                                session.setAttribute("userTypeVect2",userVect2);
                                request.setAttribute("usrtypname",usrtypname);
                            }
                            request.setAttribute("rCnt", String.valueOf(rCnt));
                            request.setAttribute("pNo", String.valueOf(pNo));
                        }
                        
                        if(usrtypname.equalsIgnoreCase("Horse")) {
                            Debug.print("Inside Horse Details Block ......");
                            
                            String usertypid=request.getParameter("userType_sel");
                            String memtypid=request.getParameter("memType_Sel");
                            
                            System.out.println("usertypid :"+usertypid);
                            System.out.println("memtypid :"+memtypid);
                            System.out.println("statid :"+statid);
                            
                            ArrayList horsedet=new ArrayList();
                            horsedet=memberRemote.displayMembershipDetailForHorse(usertypid,memtypid,statid);
                            System.out.println("horsedet size :"+horsedet.size());
                            request.setAttribute("horsedet",horsedet);
                            
                            request.setAttribute("usrtypname",usrtypname);
                            
                        }
                        
                        return mapping.findForward("displayMemberRegiList");
                    } catch(Exception emember) {
                        Debug.print("while Loading User Type ID page" + emember);
                        
                    }
                }
                
                int yearVal = 0;
                if(year!=null &&year.trim().length()!=0){
                    yearVal  =Integer.parseInt(year);
                }
                Vector userVect1=(Vector)memberRemote.displayMembershipTypeDetails1(yearVal);
                session.setAttribute("userTypeVect1",userVect1);
                
            }
            
              /*
         To View the Member Information.....
               */
            else if(memRegiListProcess.equalsIgnoreCase("memRegiListView")) {
                
                try {
                    String usrtypname=request.getParameter("usrtypname");
                    String memberId=request.getParameter("memberId");
                    String userId=request.getParameter("userId");
                    
                    Debug.print(" userId :"+ userId);
                    System.out.println("MemberID...................."+memberId);
                    Vector userVect3=(Vector)memberRemote.selectDetailsForMember(memberId);
                    request.setAttribute("userTypeVect3",userVect3);
                    System.out.println("servletvector3:"+userVect3);
                    request.setAttribute("usrtypname",usrtypname);
                    
                    String jndiname2=mr.getMessage("jndi.usrreg");
                    Object objref2 = jndiContext.lookup(jndiname2);
                    HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote1 = home1.create();
                    String status=remote1.getStatusByMemberId(memberId);
                    
                    ArrayList familyAddOnId = new ArrayList();
                    familyAddOnId = memberRemote.getFamilyAddOnId(memberId);
                    request.setAttribute("familyAddOnId",familyAddOnId);
                    
                    Object objref1 = jndiContext.lookup("ejb/HLCkaverySessionBeanStatlessBean");
                    HLCkaverySessionBeanStatlessRemoteHome home3 =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
                    HLCkaverySessionBeanStatlessRemote remote3 = home3.create();
                    
                    // request.setAttribute("memberId",memberId);
                    Debug.print("The Size.....................................:"+ userVect3.size());
                    
                    HLCMemberDetails objMember = new HLCMemberDetails();
                    objMember=remote1.displayMemberDetail(memberId);
                    Debug.print("remote1.displayMemberDetail(memberId) :" + objMember);
                    
                    int siz=0;
                    
                    if(objMember.getFamilyAddOn()!=null) {
                        siz=objMember.getFamilyAddOn().size();
                    }
                    
                    request.setAttribute("objMember",objMember);
                    System.out.println("objMember.getFamilyAddOn() : "+siz);
                    
                    ArrayList nonhlc=new ArrayList();
                    ArrayList donSelect=new ArrayList();
                    
                    nonhlc=remote3.getMemberNonUseaDetails(memberId);
                    donSelect=remote3.getMemberDonationDetails(userId);
                    
                    request.setAttribute("nonhlc",nonhlc);
                    request.setAttribute("donSelect",donSelect);
                    
                    HLCPaymentDetails objPayment = new HLCPaymentDetails();
                    objPayment=remote1.getPaymentDetails(objMember.getPaymentId());
                    request.setAttribute("objPayment",objPayment);
                    Debug.print("objMember.getPaymentId() returned by getPaymentDetails() :"+objMember.getPaymentId());
                    System.out.println("objPayment returned by getPaymentDetails() :"+objPayment.toString());
                    
                    ArrayList childPayment=new ArrayList();
                    
                    childPayment = remote1.getAdminPaymentDetails(objMember.getPaymentId());
                    request.setAttribute("childPayment",childPayment);
                    Debug.print("ArrayList returned by getAdminPaymentDetails() childPayment.size() :"+childPayment.size());
                    
                    Date approvalDate =(Date) remote1.getApprovedDate(memberId);
                    Debug.print("memberId: "+memberId);
                    Debug.print(" approvalDate:"+approvalDate);
                    request.setAttribute("approvalDate",approvalDate);
                    request.setAttribute("userId",userId);
                    String[] serviceType = (String[]) remote1.loadServiceType();
                    request.setAttribute("serviceType",serviceType);
                    
                    
                    ArrayList NSFcharge = (ArrayList) remote1.getNsfPaymentDetails(objMember.getPaymentId());
                    Debug.print("NSFcharge Size"+NSFcharge.size());
                    
                    request.setAttribute("NSFcharge",NSFcharge);
                    
                    return mapping.findForward("displayMemberInfo");
                    
                    
                } catch(Exception emember) {
                    Debug.print("while Viewing the Member information page" + emember);
                    
                    
                }
            }
            
      /*
       * To Assign Satus of Member
       */
            else if(memRegiListProcess.equalsIgnoreCase("memRegiApprove")) {
                
                try {
                    String jndiname2=mr.getMessage("jndi.usrreg");
                    Object objref2 = jndiContext.lookup(jndiname2);
                    HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote1 = home1.create();
                    
                    String uStatusId=request.getParameter("uStatusId");
                    String memberId=request.getParameter("memberId");
                    System.out.println("MemberID...................."+memberId);
                    
                           /*  String uStatusId=request.getParameter("uStatusId");
                             if(usrtypname.equalsIgnoreCase("Human"))
                             {
                             }*/
                    if(memberId.length()!=0 && memberId!=null) {
                        String usrtypname=request.getParameter("usrtypname");
                        
                        if(usrtypname.equalsIgnoreCase("Horse")) {
                            boolean result=memberRemote.editHorseMemberStatus(uStatusId,memberId);
                            //request.setAttribute("userTypeVect4",userVect4);
                            System.out.println("result:"+result);
                            
                        } else {
                            boolean result=memberRemote.editMemberStatus(uStatusId,memberId);
                            //request.setAttribute("userTypeVect4",userVect4);
                            System.out.println("result:"+result);
                            
                            ArrayList familyAddOnId=new ArrayList();
                            familyAddOnId=(ArrayList)session.getAttribute("familyAddOnId");
                            
                            if(uStatusId.equalsIgnoreCase("Inactive")) {
                                if(familyAddOnId!=null) {
                                    Debug.print("Inside familyAddOn Inactiveate block :" +familyAddOnId.size());
                                    
                                    Debug.print("familyAddOnId.size() :" +familyAddOnId.size());
                                    
                                    for(int i=0;i<familyAddOnId.size();i++) {
                                        String memId[]=(String[])familyAddOnId.get(i);
                                        
                                        Debug.print("uStatusId is "+uStatusId);
                                        Debug.print("memberId of familyAddOn is "+memId[0]);
                                        boolean stat=memberRemote.editMemberStatus(uStatusId,memId[0]);
                                        Debug.print("memberRemote2.editMemberStatus(uStatusId,memId[0]) :"+stat);
                                        
                                    }
                                    
                                    session.setAttribute("familyAddOnId",null);
                                }
                            }
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
                            Debug.print("memberId:"+memberId);
                            Debug.print("request.getParameter(approvalDate) :"+request.getParameter("approvalDate"));
                            
                            Date approvalDate=null;
                            
                            if(request.getParameter("approvalDate")!=null && request.getParameter("approvalDate").trim().length()!=0) {
                                approvalDate = (Date) sdf.parse(request.getParameter("approvalDate"));
                                Debug.print("approvalDate :"+approvalDate.toString());
                                boolean appDateStatus = remote1.updateApprovalDate(memberId,approvalDate);
                                Debug.print("approvalDate :"+appDateStatus);
                            }
                            
                            
                            /// Update status in the History Details
                            String paymentId= request.getParameter("parentpayId");
                            Debug.print("parent Pay Id "+paymentId);
                            String statid=remote1.getStatusIBasedOnStatus(uStatusId);
                            Debug.print("Status Id on query for "+uStatusId+" is "+statid);
                            String memberId1 = request.getParameter("memberId");
                            boolean histStatusUpdate = remote1.updateMemberHistoryDetailStatus(memberId1,statid,paymentId);
                            Debug.print("histStatusUpdate is "+histStatusUpdate);
                            /// End update status in the History Details
                            
                            
                            // request.setAttribute("memberId",memberId);
                            // Debug.print("The Size.....................................:"+ userVect4.size());
                            
                            String mainStat = request.getParameter("mainStat");
                            String subStat = request.getParameter("subStat");
                            
                            Debug.print("mainStat"+mainStat);
                            Debug.print("subStat"+subStat);
                            
                            String mainPayNfs=null;
                            String firstEnbAmt = null;
                            boolean firstRec = false;
                            if(mainStat.equalsIgnoreCase("avail")){
                                mainPayNfs = request.getParameter("mainPayNfs");
                                Debug.print("Request Parameter    "+mainPayNfs);
                                if(mainPayNfs!=null){
                                    String[] payId_amount = mainPayNfs.split("#");
                                    mainPayNfs = payId_amount[0];
                                    String amount = payId_amount[1];
                                    String subpayamount = payId_amount[2];
                                    
                                    String usrtxtArea = request.getParameter("usrtxtArea");
                                    String admintxtArea = request.getParameter("admintxtArea");
                                    Debug.print("payId_amount "+payId_amount);
                                    Debug.print("mainPayNfs" +mainPayNfs);
                                    Debug.print("amount "+amount);
                                    Debug.print("subpayamount   "+subpayamount);
                                    
                                    HLCPaymentDetailVO payDetVO = new HLCPaymentDetailVO();
                                    // Get client's IP address
                                    String addr = request.getRemoteAddr(); // 123.123.123.123
                                    Debug.print("Port Value:" + addr);
                                    
                                    String newPaymentId = remote2.getNextId();
                                    payDetVO.setNsfStatus(true);
                                    //payDetVO.setNsf_charge_status(true);
                                    payDetVO.setCheckAmount(0);
                                    payDetVO.setPendingAmount(Float.parseFloat(amount));
                                    payDetVO.setParentPaymentId(mainPayNfs);
                                    payDetVO.setAmount(Float.parseFloat(amount));
                                    payDetVO.setPaymentId(newPaymentId);
                                    String userId = (String) session.getAttribute("userId");
                                    payDetVO.setUserId(userId);
                                    payDetVO.setUser_comments(usrtxtArea);
                                    payDetVO.setStaff_comments(admintxtArea);
                                    
                                    ///payDetVO.setPaymentStatus("check");
                                    
                                    payDetVO.setIpAddress(addr);
                                    remote1.insertPayment(payDetVO);
                                    remote1.updateNSFChargeStatus(mainPayNfs);
                                    if(firstRec!=true){
                                        firstRec = true;
                                        firstEnbAmt = subpayamount;
                                        remote1.updateNSFPendingAmt(mainPayNfs,Float.parseFloat(firstEnbAmt));
                                    }
                                    String parentpayId = request.getParameter("parentpayId");
                                    
                                    if(parentpayId!=null && parentpayId.trim().length()!=0){
                                        boolean update_NSF_Sebsequent = remote1.updateNSFChargeStatusForSubSequentPayments(parentpayId,Float.parseFloat(subpayamount));
                                        Debug.print("update_NSF_Sebsequent Result "+update_NSF_Sebsequent);
                                    }
                                }
                            }
                            //Sub payment Update
                            if(subStat.equalsIgnoreCase("avail")){
                                String subPaySz = request.getParameter("subPaySz");
                                String parentPayId = (String) request.getParameter("parentpayId");
                                Debug.print("Parent Pay Id "+parentPayId);
                                int subSz = Integer.parseInt(subPaySz);
                                for(int j=0;j<subSz;j++){
                                    String subpay = (String) request.getParameter("subpay"+j);
                                    Debug.print("sub Pay is "+subpay);
                                    Debug.print("sub Pay length is "+subpay.length());
                                    String checkedSub = (String) request.getParameter("chkItem"+j);
                                    Debug.print("checkedSub is :"+checkedSub);
                                    if(checkedSub!=null && checkedSub.trim().length()!=0){
                                        if(checkedSub.trim().equalsIgnoreCase("avail")){
                                            Debug.print("sub Pay is "+subpay);
                                            String[] payId_amount1 = subpay.split("#");
                                            String subPayNfs = payId_amount1[0];
                                            String nsfamount = payId_amount1[1];
                                            String subpayamount = payId_amount1[2];
                                            
                                            String subusrtxtArea = request.getParameter("subusrtxtArea"+j);
                                            String subadmintxtArea = request.getParameter("subadmintxtArea"+j);
                                            
                                            Debug.print("subPayNfs "+subPayNfs);
                                            Debug.print("nsfamount "+nsfamount);
                                            Debug.print("subPaySz  "+subPaySz);
                                            Debug.print("subpayamount "+subpayamount);
                                            
                                            if(subPayNfs!=null || subPayNfs.trim().length()!=0){
                                                HLCPaymentDetailVO payDetVO = new HLCPaymentDetailVO();
                                                
                                                // Get client's IP address
                                                String addr = request.getRemoteAddr(); // 123.123.123.123
                                                Debug.print("Port Value:" + addr);
                                                String newPaymentId = remote2.getNextId();
                                                payDetVO.setNsfStatus(true);
                                                payDetVO.setCheckAmount(0);
                                                payDetVO.setStaff_comments(subadmintxtArea);
                                                payDetVO.setUser_comments(subusrtxtArea);
                                                payDetVO.setPendingAmount(Float.parseFloat(nsfamount));
                                                payDetVO.setParentPaymentId(parentPayId);
                                                payDetVO.setPaymentId(newPaymentId);
                                                payDetVO.setAmount(Float.parseFloat(nsfamount));
                                                String userId = (String) session.getAttribute("userId");
                                                payDetVO.setUserId(userId);
                                                ///payDetVO.setPaymentStatus("check");
                                                payDetVO.setUser_comments(subusrtxtArea);
                                                payDetVO.setStaff_comments(subadmintxtArea);
                                                //payDetVO.setNsf_charge_status(true);
                                                payDetVO.setIpAddress(addr);
                                                
                                                remote1.insertPayment(payDetVO);
                                                remote1.updateNSFChargeStatus(subPayNfs);
                                                if(firstRec!=true){
                                                    firstRec = true;
                                                    firstEnbAmt = subpayamount;
                                                    remote1.updateNSFPendingAmt(parentPayId,Float.parseFloat(firstEnbAmt));
                                                }
                                                
                                                String parentpayId = request.getParameter("parentpayId");
                                                
                                                if(parentpayId!=null && parentpayId.trim().length()!=0){
                                                    boolean update_NSF_Sebsequent = remote1.updateNSFChargeStatusForSubSequentPayments(parentpayId,Float.parseFloat(subpayamount));
                                                    Debug.print("update_NSF_Sebsequent Result "+update_NSF_Sebsequent);
                                                }
                                                
                                            }
                                        }
                                    }
                                }
                            }
                            // }
                        }
                        
                        return mapping.findForward("displayMemberRegiList");
                    } else {
                        
                        
                        // String memberId=request.getParameter("memberId");
                        //    System.out.println("MemberID...................."+memberId);
                        //  Vector userVect3=(Vector)memberRemote.selectDetailsForMember(memberId);
                        // request.setAttribute("userTypeVect3",userVect3);
                        // System.out.println("servletvector3:"+userVect3);
                        // request.setAttribute("memberId",memberId);
                        // Debug.print("The Size.....................................:"+ userVect3.size());
                        return mapping.findForward("displayMemberRegiList");
                    }
                }
                
                catch(Exception emember) {
                    emember.printStackTrace();
                    Debug.print("while changing the status of Member " + emember);
                    
                }
            }
            
            else if(memRegiListProcess.equalsIgnoreCase("horseDet")) {
                try {
                    
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String kaverysfuljndi=mr.getMessage("jndi.kaverysful");
                    
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    
                    Object objref3 = jndiContext.lookup(kaverysfuljndi);
                    
                    HLCHorseDisplayVO objHorseDisp = new HLCHorseDisplayVO();
                    
                    HLCKaverySessionStatefulRemoteHome home3 =
                            (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref3, HLCKaverySessionStatefulRemoteHome.class);
                    
                    HLCKaverySessionStatefulRemote remote3 = null;
                    
                    Vector RenewHorseVect = new Vector();
                    
                    remote3 = home3.create();
                    
                    String memid=request.getParameter("memid");
                    objHorseDisp=(HLCHorseDisplayVO)remote3.displayHorseDetails(memid);
                    
                    request.setAttribute("objHorseDisp",objHorseDisp);
                    
                    //---------------- Disp type details -------------------------
                    
                    String kaveryslessjndi=mr.getMessage("jndi.kaverysless");
                    Context jndiContext1 = new InitialContext();
                    Object objtyperef = jndiContext1.lookup(kaveryslessjndi);
                    
                    HLCkaverySessionBeanStatlessRemoteHome home4 =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objtyperef, HLCkaverySessionBeanStatlessRemoteHome.class);
                    Vector horsememtypVect = new Vector();
                    Vector horsesertypVect = new Vector();
                    
                    HLCkaverySessionBeanStatlessRemote remote4 = home4.create();
                                   /*  horsememtypVect = (Vector)remote1.displayMembershipType("Horse");
                                     horsesertypVect= (Vector)remote1.displayHorseServiceType("Horse");*/
                    List selectedlist = (List)objHorseDisp.gethorseServiceTypeName();
                    
                                    /* System.out.println("horse membership typ size :" +horsememtypVect.size());
                                     System.out.println("horse ser typ size :" +horsesertypVect.size());*/
                    System.out.println("horse ser typ selectedlist :" +selectedlist.size());
                    
                                    /* request.setAttribute("horsememberVect", horsememtypVect);
                                     request.setAttribute("horsesertypVect", horsesertypVect);*/
                    request.setAttribute("selectedlist", selectedlist);
                    request.setAttribute("memid",memid);
                    String usrtypname=request.getParameter("usrtypname");
                    request.setAttribute("usrtypname",usrtypname);
                    
                    return mapping.findForward("horseDetApprove");
                    
                }
                
                catch(Exception emember) {
                    Debug.print("while changing the status of Member" + emember);
                    
                }
                
                
            }
            
            
            if(memRegiListProcess.equalsIgnoreCase("payment")) {
                return mapping.findForward("memberPayment");
            }
//Admin Transaction Entry Direction
            if(memRegiListProcess.equalsIgnoreCase("adminTransDet")){
                
///  Payment Details
                String jndiname2=mr.getMessage("jndi.usrreg");
                Object objref2 = jndiContext.lookup(jndiname2);
                HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
                HLCkaverystatelessRemote remote1 = home1.create();
                
                ArrayList transacEntries = null;
                ArrayList subTransac = null;
                ArrayList nxtTransac = null;
                HLCPaymentDetails objPayment = null;
                HLCPaymentDetailVO bounceDet = null;
                String payId = request.getParameter("Id");
                String firstNme = request.getParameter("fnme");
                String lastNme = request.getParameter("lnme");
                String memberId = request.getParameter("mid");
                if(firstNme==null)    {
                    firstNme= "";
                }
                if(lastNme==null){
                    lastNme= "";
                }
                if(memberId==null){
                    memberId = "";
                }
                if(payId!=null || payId.trim().length()!=0){
                    transacEntries = mahaRemote.getAccTxnDetailsOnPaymentId(payId);
                    objPayment = remote1.getPaymentDetails(payId);
                    nxtTransac = remote1.getAdminPaymentDetails(payId);
                    bounceDet = remote1.getNSFCheckDetails(payId);
                }
                request.setAttribute("fnme",firstNme);
                request.setAttribute("lnme",lastNme);
                request.setAttribute("mid",memberId);
                request.setAttribute("objPayment",objPayment);
                request.setAttribute("transacEntries",transacEntries);
                request.setAttribute("bounceDet",bounceDet);
                request.setAttribute("subTransac",nxtTransac);
                request.setAttribute("memTypId1",memTypId1);
                return mapping.findForward("adminTransac");
            }
            
            if(memRegiListProcess.equalsIgnoreCase("adminEntry")){
                
                boolean transacConf =false;
                String listSz =  request.getParameter("listSz");
                Debug.print("listSz is "+listSz);
                String requestVal = request.getParameter("submit");
                Debug.print("Button Name "+requestVal);
                
                String staff_userId = (String) session.getAttribute("userId");
                String reqIp=request.getRemoteAddr();
                Debug.print(" Request IP :"+reqIp);
///////
//Start Reverse
                if(requestVal!=null){
                    if(requestVal.equalsIgnoreCase("NSFPay")){
                        HLCAccTransactionVO axnVO1 = new HLCAccTransactionVO();
                        
                        if(listSz!=null || listSz.trim().length()!=0){
                            int Sz1 = Integer.parseInt(listSz);
                            Debug.print("Size is "+Sz1);
                            for(int i=0;i<Sz1;i++){
                                String revBox = request.getParameter("revBox"+i);
                                if(revBox!=null && revBox.trim().length()!=0){
                                    String[] rev_val = revBox.split("#");
                                    Debug.print("rev_val is \n"+rev_val.toString());
                                    String accType = rev_val[0];
                                    String accNo = rev_val[1];
                                    String subaccNo = rev_val[2];
                                    String accDesc = rev_val[3];
                                    String accClassType = rev_val[4];
                                    String accQty = rev_val[5];
                                    String accAmt = rev_val[6];
                                    String accUserId = rev_val[7];
                                    String accItemNo = rev_val[8];
                                    String accPayMode = rev_val[9];
                                    String accReconcileAmt = rev_val[10];
                                    String accTransMode = rev_val[11];
                                    String accTransId = rev_val[12];
                                    String payId = rev_val[13];
                                    String userId = rev_val[14];
                                    
                                    axnVO1.setAccount_no(accNo);
                                    axnVO1.setAccount_type(accType);
                                    axnVO1.setActive_status(true);
                                    axnVO1.setAmount(Float.parseFloat(accAmt));
                                    axnVO1.setClass_Typ(accClassType);
                                    axnVO1.setDescription(accDesc);
                                    axnVO1.setItem_no(accItemNo);
                                    axnVO1.setPayment_id(payId);
                                    axnVO1.setPayment_mode(accPayMode);
                                    
                                    axnVO1.setReconcile_status(true);
                                    axnVO1.setSub_account_no(subaccNo);
                                    axnVO1.setSync_status(false);
                                    axnVO1.setTransaction_id(accTransId);
                                    axnVO1.setTransaction_mode(accTransMode);
                                    //User_id omitted for reverse entries
                                    //axnVO1.setUser_id(userId);
                                    
                                    if(accQty!=null && accQty.trim().length()!=0){
                                        axnVO1.setQuantity(Integer.parseInt(accQty));
                                    }
                                    if(accReconcileAmt!=null && accReconcileAmt.trim().length()!=0){
                                        axnVO1.setReconcile_amount(Float.parseFloat(accReconcileAmt));
                                    }
                                    
                                    if(axnVO1.getReconcile_amount()<=0){
                                        if(accAmt!=null || accAmt.trim().length()!=0){
                                            accAmt ="-"+accAmt;
                                            axnVO1.setAmount(Float.parseFloat(accAmt));
                                        }
                                        axnVO1.setReconcile_amount(0);
                                        
                                        // Setting Staff UserId, Staff IpAddress
                                        axnVO1.setStaff_user_id(staff_userId);
                                        axnVO1.setStaff_ip_address(reqIp);
                                        
                                        boolean insrtStatus = mahaRemote.insertAccountTxnDetails(axnVO1);
                                        Debug.print("Sub Payment Isert Status is "+insrtStatus);
                                    } else{
                                        if(accReconcileAmt!=null || accReconcileAmt.trim().length()!=0){
                                            accReconcileAmt ="-"+accReconcileAmt;
                                            axnVO1.setReconcile_amount(Float.parseFloat(accReconcileAmt));
                                        }
                                        axnVO1.setAmount(0);
                                        
                                        // Setting Staff UserId, Staff IpAddress
                                        axnVO1.setStaff_user_id(staff_userId);
                                        axnVO1.setStaff_ip_address(reqIp);
                                        
                                        boolean insrtStatus = mahaRemote.insertAccountTxnDetails(axnVO1);
                                        Debug.print("Sub Payment Isert Status is "+insrtStatus);
                                        
                                        String NSFPayId = request.getParameter("NSFPayId");
                                        Debug.print("NSF PayID "+NSFPayId);
                                        
                                        if(NSFPayId!=null && NSFPayId.trim().length()>0){
                                            boolean revEntry = remote2.updateReverseEntryStatus(NSFPayId);
                                            Debug.print("Reverse Entry update Status "+revEntry);
                                        }
                                        
                                        
                                    }
                                }
                            }
                            
                        }
                        String year = request.getParameter("year");
                        int yearVal = 0;
                        if(year!=null &&year.trim().length()!=0){
                            yearVal  =Integer.parseInt(year);
                        }
                        Vector userVect1=(Vector)memberRemote.displayMembershipTypeDetails1(yearVal);
                        session.setAttribute("userTypeVect1",userVect1);
                        request.setAttribute("year",year);
                        return mapping.findForward("displayMemberRegiList");
                    } else{
//End Reverse
////
                        if(listSz!=null || listSz.trim().length()!=0){
                            int Sz = Integer.parseInt(listSz);
                            Debug.print("Size is "+Sz);
                            boolean status = true;
                            boolean voidStatus = false;
                            
///Setting Active status TRUE / FALSE
                            for(int i=0;i<Sz;i++){
                                String payType = request.getParameter("payType"+i);
                                Debug.print("Pay Type is "+payType);
                                
                                if(payType==null || payType.trim().length()==0){
                                    System.out.println("Null Made False");
                                    status = false;
                                    break;
                                }
                                
                                else{
                                    if(payType!=null || payType.trim().length()!=0){
                                        if(payType.equalsIgnoreCase("Partial Payment") || payType==null){
                                            status = false;
                                            Debug.print("Status set as false ");
                                            break;
                                        } else{
                                            Debug.print("Status set as true");
                                            status = true;
                                        }
                                    }
                                }
                            }
                            
                            
///Update the entries to table
                            for(int i=0;i<Sz;i++){
                                
                                String amtName = request.getParameter("amtName"+i);
                                String payType = request.getParameter("payType"+i);
                                String ccType = request.getParameter("ccType"+i);
                                String transId = request.getParameter("transId"+i);
                                String actualAmount = request.getParameter("actualAmount"+i);
                                
                                Debug.print("Amount is "+i+" is "+amtName);
                                Debug.print("payType is "+i+" is "+payType);
                                Debug.print("ccType is "+i+" is "+ccType);
                                Debug.print("transId is "+i+" is "+transId);
                                Debug.print("actualAmount is "+i+" is "+actualAmount);
                                
                                HLCAccTransactionVO axnVO = new HLCAccTransactionVO();
                                
                                //Reconclie Amount
                                if(amtName!=null){
                                    if(amtName.trim().length()!=0){
                                        axnVO.setReconcile_amount(Float.parseFloat(amtName));
                                    }
                                } else{
                                    axnVO.setReconcile_amount(0);
                                }
                                
                                //Set Transaction Mode
                                if(payType!=null || payType.trim().length()!=0){
                                    axnVO.setTransaction_mode(payType);
                                }
                                
                                if(ccType!=null || ccType.trim().length()!=0){
                                    axnVO.setPayment_mode(ccType);
                                }
                                
                                // Set Active Status False if PAYMENT VOID
                                if(status==true){
                                    boolean updateDateResult = mahaRemote.updateRecouncilTxnDateOnTxnId(transId);
                                    Debug.print("Update on Date Result is "+updateDateResult);
                                    if(payType.equalsIgnoreCase("void")){
                                        axnVO.setReconcile_status(false);
                                    } else{
                                        axnVO.setReconcile_status(true);
                                    }
                                } else{
                                    axnVO.setReconcile_status(false);
                                }
                                
                                // Set Reconile Status True if FULL PAYMENT
                                axnVO.setActive_status(status);
                                
                                
                                // Set Transaction ID
                                axnVO.setTransaction_id(transId);
                                
                                // Setting Staff UserId, Staff IpAddress
                                axnVO.setStaff_user_id(staff_userId);
                                axnVO.setStaff_ip_address(reqIp);
                                
                                //Update to database
                                if(axnVO.getReconcile_amount()>0){
                                    transacConf = mahaRemote.updateRecouncilAccTxnDetails(axnVO);
                                    Debug.print("Transaction Confirmation  "+transacConf);
                                }
                                
                                
                                if(actualAmount!=null || actualAmount.trim().length()!=0){
                                    Debug.print("actVal is  "+actualAmount);
                                    double actVal = Double.parseDouble(actualAmount);
                                    if(actVal<0){
                                        axnVO.setReconcile_amount(Float.parseFloat(actualAmount));
                                        Debug.print("Inisde update"+actualAmount);
                                        transacConf = mahaRemote.updateRecouncilAccTxnDetails(axnVO);
                                        Debug.print("Transaction Confirmation  "+transacConf);
                                    }
                                }
                            }
                            
///Setting Active status TRUE / FALSE
                            
                            boolean subPayStatus =false;
                            boolean subVoidstatus = false;
                            for(int i=0;i<Sz;i++){
                                
                                String payType = request.getParameter("divTransMode"+i);
                                Debug.print("Pay Type is "+payType);
                                
                                String divX = request.getParameter("div"+i);
                                
                                if(divX!=null){
                                    if(payType==null || payType.trim().length()==0){
                                        System.out.println("Null Made False in sub payment");
                                        status = false;
                                        break;
                                    } else{
                                        if(payType!=null || payType.trim().length()!=0){
                                            if(payType.equalsIgnoreCase("Partial Payment") || payType==null){
                                                subPayStatus = false;
                                                Debug.print("Status set as false ");
                                                break;
                                            } else{
                                                Debug.print("Status set as true");
                                                subPayStatus = true;
                                            }
                                        }
                                    }
                                }
                            }
                            if(status==false){
                                subPayStatus = false;
                            } else{
                                subPayStatus = true;
                            }
                            //Sub Payment Entries
                            for(int j=1;j<=Sz;j++){
                                Debug.print("Inside Sub Payment"+j);
                                String divX = request.getParameter("div"+j);
                                Debug.print("Inside Sub divX "+divX + " : j =" + j);
                                if(divX!=null){
                                    Debug.print("Inside Sub Payment divX is "+divX);
                                    HLCAccTransactionVO axnVO = new HLCAccTransactionVO();
                                    HLCAccTransactionVO dbVO = new HLCAccTransactionVO();
                                    String accPayId = null;
                                    
                                    String itemNme = request.getParameter("divNewItem"+j);
                                    String newAmt = request.getParameter("newAmt"+j);
                                    String transMode = request.getParameter("divTransMode"+j);
                                    String payMode = request.getParameter("payMode"+j);
                                    String divtransId = request.getParameter("divTransId"+j);
                                    
                                    Debug.print("Amount is "+j+" is "+newAmt);
                                    Debug.print("transMode is "+j+" is "+transMode);
                                    Debug.print("payMode is "+j+" is "+payMode);
                                    Debug.print("divtransId is "+j+" is "+divtransId);
                                    
                                    //Set Transaction Mode
                                    if(transMode!=null || transMode.trim().length()!=0){
                                        axnVO.setTransaction_mode(transMode);
                                    }
                                    
                                    if(payMode!=null || payMode.trim().length()!=0){
                                        axnVO.setPayment_mode(payMode);
                                    }
                                    
                                    //Reconclie Amount
                                    if(newAmt!=null){
                                        if(newAmt.trim().length()!=0){
                                            axnVO.setReconcile_amount(Float.parseFloat(newAmt));
                                        }
                                    } else{
                                        axnVO.setReconcile_amount(0);
                                    }
                                    
                                    if(divtransId!=null || divtransId.trim().length()!=0){
                                        dbVO = mahaRemote.getAccTransctionDetails(divtransId);
                                        
                                        String accNo = dbVO.getAccount_no();
                                        String accTyp = dbVO.getAccount_type();
                                        String accDesc = dbVO.getDescription();
                                        String accItemNo = dbVO.getItem_no();
                                        String subAccNo = dbVO.getSub_account_no();
                                        String classType = dbVO.getClass_Typ();
                                        accPayId = dbVO.getPayment_id();
                                        
                                        axnVO.setAccount_no(accNo);
                                        axnVO.setAccount_type(accTyp);
                                        axnVO.setDescription(accDesc);
                                        axnVO.setPayment_id(accPayId);
                                        axnVO.setSub_account_no(subAccNo);
                                        axnVO.setItem_no(accItemNo);
                                        axnVO.setClass_Typ(classType);
                                        
                                    }
                                    
                                    // Set Sub Active Status False if PAYMENT VOID
                                    if(subPayStatus==true){
                                        if(transMode.equalsIgnoreCase("void")){
                                            axnVO.setReconcile_status(false);
                                        } else{
                                            axnVO.setReconcile_status(true);
                                            boolean updateDateResult = mahaRemote.updateRecouncilTxnDateOnTxnId(divtransId);
                                            Debug.print("Update on Date Result is "+updateDateResult);
                                        }
                                    } else{
                                        axnVO.setReconcile_status(false);
                                    }
                                    // Set Sub Reconile Status True if FULL PAYMENT
                                    axnVO.setActive_status(subPayStatus);
                                    
                                    // Set Transaction ID
                                    axnVO.setParentTransactionId(divtransId);
                                    
                                    // Setting Staff UserId, Staff IpAddress
                                    axnVO.setStaff_user_id(staff_userId);
                                    axnVO.setStaff_ip_address(reqIp);
                                    
                                    if(axnVO.getReconcile_amount()>0){
                                        boolean insrtStatus = mahaRemote.insertAccountTxnDetails(axnVO);
                                        Debug.print("Sub Payment Isert Status is "+insrtStatus);
                                    }
                                }
                            }
                            if(transacConf==true){
                                String year = request.getParameter("year");
                                int yearVal = 0;
                                if(year!=null &&year.trim().length()!=0){
                                    yearVal  =Integer.parseInt(year);
                                }
                                Vector userVect1=(Vector)memberRemote.displayMembershipTypeDetails1(yearVal);
                                session.setAttribute("userTypeVect1",userVect1);
                                request.setAttribute("year",year);
                                return mapping.findForward("displayMemberRegiList");
                            } else{
                                return mapping.findForward("index");
                            }
                        }
                    }
                }
            }
//Admin Entry Ending
        } catch(Exception e){
            Debug.print("Exception occurs while calling EJB method()......." + e);
        }
        return mapping.findForward("displayMemberRegiList");
        
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

