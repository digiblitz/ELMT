/*
 * AdminAnnualRegListAction.java
 *
 * Created on September 26, 2006, 5:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mee.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.HLCAnnualRegisterVO;
import com.hlcmeeting.util.HLCAnnualRegistrationDetailVO;
import com.hlcmeeting.util.Debug;
import java.util.*;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

public class AdminAnnualRegListAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    String name = "ejb/HLCVaigaiSessionJNDI";
    Vector vObj = new Vector();
      try {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup(name);
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            HttpSession session = request.getSession(true);

/// Transaction Entry
            MessageResources mr=getResources(request);
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();            
// End Transaction Entry            
            String memProcess = request.getParameter("memProcess");
            Debug.print("MemeberShip Name In Servlet:" + memProcess);
            
            String statusName = request.getParameter("memTypName");
            System.out.println(" Status in servlet : "+statusName);
            if(statusName!=null && statusName.trim().length()>0) {     
               // request.setAttribute("memTypId",memberName);
                //String memberTypeName = request.getParameter("memTypId");
                Debug.print("statusName inside the condition : " + statusName);
                vObj = (Vector) remote.displayAnnualDetailBasedOnStatus(statusName);
                request.setAttribute("dispStr",vObj);
            } 
            else if(memProcess != null && memProcess.equals("dispDetail")){
                String annualMeeId = request.getParameter("annualMeetingId");
                System.out.println("Annual Meeting Id In Servlet : "+annualMeeId);

                vObj = (Vector)remote.displayAnnaulApplicationDetail(annualMeeId);
                Debug.print("Vector element for user detail in Servlet : "+vObj);
                request.setAttribute("viewMember",vObj);
                Vector nobj = new Vector();
                nobj = (Vector)remote.displayAnnaulSpecificationDetail(annualMeeId);
                Debug.print("Vector element for specification detail in Servlet : "+nobj);
                request.setAttribute("viewSpecification",nobj);

                //String activeStat = "False";
                //public boolean editActiveStatus(String activeStat, String userId) throws RemoteException
               // boolean bol1 = remote.editActiveStatus(activeStat, userId);
               // System.out.println("Status is : "+bol1);

                 return mapping.findForward("displayAnnualConDetail");
               }
            
             else if(memProcess != null && memProcess.equals("dispViewDetail")){
                String annualMeetingId = request.getParameter("annualMeetingId");
                Debug.print("Annual Meeting Id In Servlet : "+annualMeetingId);

                ArrayList  allUserList= (ArrayList)remote.getAnnualDetailsByMeetingId(annualMeetingId);
                request.setAttribute("viewMemberDetails",allUserList);
                return mapping.findForward("frmMeeAdminAnnConRegiDetailtedList");
               }
 //================================================================show particular user details=============================           
             else if(memProcess != null && memProcess.equals("statusChange")){
                String annualMeetingId = request.getParameter("annualMeetingId");
                String ardId = request.getParameter("ardId");
                Debug.print("Annual Meeting Id In Servlet annualMeetingId: "+annualMeetingId);
                HLCAnnualRegisterVO allRegList = (HLCAnnualRegisterVO)remote.getAnnualRequestStatus(annualMeetingId);
                HLCAnnualRegistrationDetailVO  allUserList= (HLCAnnualRegistrationDetailVO)remote.getAnnualRegistrationDetails(ardId);
                ArrayList priceList = (ArrayList)remote.getAnnualPriceDetailsByAnnualId(ardId);
                request.setAttribute("viewRegUserDetails",allUserList);
                request.setAttribute("priceDetails",priceList);
                //HLCPaymentDetailVO  objPayment = (HLCPaymentDetailVO)remote.getPaymentDetails(paymentId, userId)
                request.setAttribute("viewMemberDetails",allRegList);
                //request.setAttribute("priceDetails",priceList);
                  return mapping.findForward("frmMeeAdminAnnConvRegiStatusApprove"); 
               }
            //================================================================show particular user details=============================           
             else if(memProcess != null && memProcess.equals("statusShow")){
                String annualMeetingId = request.getParameter("annualMeetingId");
                Debug.print("Annual Meeting Id In Servlet annualMeetingId: "+annualMeetingId);
                HLCAnnualRegisterVO allUserList = (HLCAnnualRegisterVO)remote.getAnnualRequestStatus(annualMeetingId);
                ArrayList  viewAllUserDetails= (ArrayList)remote.getAnnualDetailsByMeetingId(annualMeetingId);
                request.setAttribute("viewAllUserDetails",viewAllUserDetails);
                //HLCPaymentDetailVO  objPayment = (HLCPaymentDetailVO)remote.getPaymentDetails(paymentId, userId)
                request.setAttribute("viewMemberDetails",allUserList);
                //request.setAttribute("priceDetails",priceList);
                  return mapping.findForward("frmMeeAdminAnnConvRegiStatusDetails"); 
               }
            //================================================================show particular user details=============================           
             else if(memProcess != null && memProcess.equals("detailedUserDetails")){
               // String butName = request.getParameter("butName");
               // Debug.print("Button Value is:" + butName);
                String ardId = request.getParameter("ardId");
                Debug.print("Annual Meeting Id In Servlet : "+ardId);
                HLCAnnualRegistrationDetailVO  allUserList= (HLCAnnualRegistrationDetailVO)remote.getAnnualRegistrationDetails(ardId);
                ArrayList priceList = (ArrayList)remote.getAnnualPriceDetailsByAnnualId(ardId);
                request.setAttribute("viewMemberDetails",allUserList);
                request.setAttribute("priceDetails",priceList);
              //  if(butName.equals("View")){
                    return mapping.findForward("frmMeeAdminAnnConvRegiDetail");
                //}
                // else{
                 // return mapping.findForward("frmMeeAdminAnnConvRegiStatusApprove"); 
                //}
               }
  
//===============================================================================================================================            
            else if(memProcess != null && memProcess.equals("updateStatus")){
                        Debug.print("updateStatus method is calling ");
                        String ardId = request.getParameter("ardId");
                        String requestStatus = request.getParameter("requestStatus");
                        String comments = request.getParameter("comments");
                        Debug.print("Annual Meeting Id In Servlet ardId: "+ardId);
                        Debug.print("requestStatus  In Servlet : "+requestStatus);
                        Debug.print("comments In Servlet : "+comments);
                        boolean res=remote.updateRequestStatus(ardId,requestStatus,comments);
                        Debug.print("Update result is calling...." + res);
                        if(res==true){
                           //request.setAttribute("status",requestStatus);
                           return mapping.findForward("displayAnnualConList");
                        }
                        else{
                             return null;
                        }
                }
            
                else if(memProcess != null && memProcess.equals("massUpdateStatus")){
                        Debug.print("updateStatus method is calling ");
                        String annualMeetingId = request.getParameter("annualMeetingId");
                        String requestStatus = request.getParameter("requestStatus");
                        String paymentId = request.getParameter("paymentId");
                        String comments = request.getParameter("comments");
                ///  Update Transaction Type Details
                        Debug.print("paymentId is "+paymentId);
                        Debug.print("requestStatus "+requestStatus);
                        if(paymentId!=null && paymentId.trim().length()!=0){
                            if(requestStatus.equalsIgnoreCase("Registered")){
                                boolean update_payStat = mahaRemote.updatePaymentStatusAccTxnDetails(paymentId);
                                Debug.print("Update Status "+update_payStat);
                            }
                        }
               ///  End of update Transaction Type Details
                        Debug.print("Annual Meeting Id In Servlet annualMeetingId: "+annualMeetingId);
                        Debug.print("requestStatus  In Servlet : "+requestStatus);
                        Debug.print("comments In Servlet : "+comments);
                        boolean res=remote.updateAnnualStatus(annualMeetingId,requestStatus,comments);
                        Debug.print("Update result is calling...." + res);
                        if(res==true){
                           //request.setAttribute("status",requestStatus);
                           return mapping.findForward("displayAnnualConList");
                        }
                        else{
                             return null;
                        }
                }
            
                else  if(memProcess.equals("adminViewList")){
                     Debug.print("AdminAnnualRegListAction.adminViewList()");
                     String status = request.getParameter("status");
                     ArrayList annualList = new ArrayList();
                     int rCnt =0;
                int pNo =1;
                String pageNo = request.getParameter("pn");

                if(pageNo!=null){
                    pNo = Integer.parseInt(pageNo);
                }
                     if(status!=null && status.trim().length()!=0){
                         annualList = remote.getAllAnnualDetailsForAdminByRequestId(status.trim(),pNo);
                         rCnt = remote.selectAnnualRegCount(status.trim());
                     }
                    request.setAttribute("AnnualList",annualList);
                    request.setAttribute("status",status);
                    request.setAttribute("rCnt", String.valueOf(rCnt));
                    request.setAttribute("pNo", String.valueOf(pNo));
                    return mapping.findForward("displayAnnualConList");
                }
            
              else  if(memProcess.equals("initAdminView")){
                     Debug.print("AdminAnnualRegListAction.initAdminView()");
                    return mapping.findForward("displayAnnualConList");
                }
          
              else  if(memProcess.equals("myListView")){
                   Debug.print("AdminAnnualRegListAction.myListView()");
                   String sessionUserId = (String) session.getAttribute("userId");
                   ArrayList myAnnualList = new ArrayList();
                   if(sessionUserId!=null) {
                        myAnnualList = remote.getMyAnnualDetails(sessionUserId);
                   }
                   request.setAttribute("MyAnnualList",myAnnualList);
                  return mapping.findForward("frmMeeUserAnnConRegiListings");
                }  
            
              else if(memProcess.equals("dispUserView")){
                    ArrayList myAnnualList = new ArrayList();
                    String meetingId = request.getParameter("annualMeetingId");
                   if(meetingId!=null) {
                        myAnnualList = remote.getAnnualDetailsByMeetingId(meetingId);
                   }
                   request.setAttribute("DetailsView",myAnnualList);
                   
                  Debug.print("AdminAnnualRegListAction.dispUserView()");
                  return mapping.findForward("frmMeeUserAnnConvRegiDetail");
              }
            }
        catch(Exception e){
                /* this will most likely be because the customer already exists in the database. */
       // System.out.print("Client Exception :" + e);
            e.printStackTrace();
        }
        return null;
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
