/*
 * MemberUserListAction.java
 *
 * Created on September 11, 2006, 6:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mrm.action;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlchorse.form.util.Debug;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import java.util.*;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.regex.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.hlchorse.form.util.HLCUserRegVO;
import com.hlchorse.form.display.*;
/**
 *
 * @author harmohan
 */
public class MemberUserListAction extends Action {
    
    Vector vObj = new Vector();
    
   public ActionForward execute(ActionMapping mapping, ActionForm  form,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {
       
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
            HLCUserRegVO userregvo=new HLCUserRegVO();
            Vector vObj = new Vector();
            Vector nobj = new Vector();
            String memProcess = request.getParameter("memProcess");
          //  String memProcess1 = request.getParameter("memProcess1");
            Debug.print("MemeberShip Name In Servlet:" + memProcess);
             nobj = memberRemote.displayUserTypeDetails();
            Debug.print("Size:" + nobj.size());
            request.setAttribute("userTypeVect",nobj);
              
             // Debug.print("userTypeVect" + userTypeVect);
              if(memProcess==null){
                Debug.print(" memProcess is null ");
                                
                String userTypeId = remote.getHumanUserTypeId(); //request.getParameter("selMemTypeId");
                
                int rCnt =0;
                int pNo =1;
                String pageNo = request.getParameter("pn");

                if(pageNo!=null){
                    pNo = Integer.parseInt(pageNo);
                }
                Debug.print("Page NO :"+pNo);
                if(userTypeId!=null && userTypeId.trim().length()>0) {
                    Debug.print("memberId:" + userTypeId);
                    rCnt = remote.userRowCount(userTypeId);
                    vObj = (Vector) remote.displayMemberUserList(userTypeId,pNo);
                    request.setAttribute("dispStr",vObj);
                    request.setAttribute("userTypeId",userTypeId);
                    request.setAttribute("rCnt", String.valueOf(rCnt));
                    request.setAttribute("pNo", String.valueOf(pNo));
                }
                
                return mapping.findForward("displayMemberUserList");
              }
              else if(memProcess!=null && memProcess.trim().equalsIgnoreCase("uList")){
                String userTypeId = remote.getHumanUserTypeId();//request.getParameter("selMemTypeId");
                int rCnt =0;
                int pNo =1;
                String pageNo = request.getParameter("pn");

                if(pageNo!=null){
                    pNo = Integer.parseInt(pageNo);
                }
                Debug.print("Page NO :"+pNo);
                if(userTypeId!=null && userTypeId.trim().length()>0) {
                    Debug.print("memberId:" + userTypeId);
                    rCnt = remote.userRowCount(userTypeId);
                    vObj = (Vector) remote.displayMemberUserList(userTypeId,pNo);
                    request.setAttribute("dispStr",vObj);
                    request.setAttribute("userTypeId",userTypeId);
                    request.setAttribute("rCnt", String.valueOf(rCnt));
                    request.setAttribute("pNo", String.valueOf(pNo));
                } 
                return mapping.findForward("displayMemberUserList");
              }
              else if(memProcess!=null && memProcess.equals("updateStatus")){
                    String userId = request.getParameter("userId");
                    System.out.println("USER ID : "+userId);
                    //For Debug Starts
                    //String activeStat = "False";
                    String activeStat = request.getParameter("activeStatus");
                    //For Debug Ends
		    //public boolean editActiveStatus(String activeStat, String userId) throws RemoteException
                    boolean bol1 = remote.editActiveStatus(activeStat, userId);
                    System.out.println("Status is : "+bol1);

                    String userTypeId = remote.getHumanUserTypeId(); //request.getParameter("selMemTypeId");
                    
                    int rCnt =0;
                    int pNo =1;
                    String pageNo = request.getParameter("pn");
                    
                    if(pageNo!=null){
                        pNo = Integer.parseInt(pageNo);
                    }
                    Debug.print("Page NO :"+pNo);
                    if(userTypeId!=null && userTypeId.trim().length()>0) {
                        Debug.print("memberId:" + userTypeId);
                        rCnt = remote.userRowCount(userTypeId);
                        vObj = (Vector) remote.displayMemberUserList(userTypeId,pNo);
                        request.setAttribute("dispStr",vObj);
                        request.setAttribute("userTypeId",userTypeId);
                        request.setAttribute("rCnt", String.valueOf(rCnt));
                        request.setAttribute("pNo", String.valueOf(pNo));
                    }
                    
                    return mapping.findForward("displayMemberUserList");
             }
             else if(memProcess!=null && memProcess.equals("viewMember")){
                String userId = request.getParameter("userId");
                System.out.println("userId In Servlet : "+userId);

                userregvo = (HLCUserRegVO)remote.displayUserRegistrationFormOnUserId(userId);
                Debug.print("Vector element in Servlet : "+vObj);
                request.setAttribute("viewMember",userregvo);
                //String activeStat = "False";
                //public boolean editActiveStatus(String activeStat, String userId) throws RemoteException
               // boolean bol1 = remote.editActiveStatus(activeStat, userId);
               // System.out.println("Status is : "+bol1);

                Object objref1 = jndiContext.lookup("ejb/HLCkaverySessionBeanStatlessBean");
                HLCkaverySessionBeanStatlessRemoteHome home3 =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
                HLCkaverySessionBeanStatlessRemote remote3 = home3.create();

                Debug.print("userregvo.getUserId() :"+userregvo.getUserId());

                ArrayList donSelect=new ArrayList();
                donSelect=remote3.getMemberDonationDetails(userregvo.getUserId());
                request.setAttribute("donSelect",donSelect);

                 return mapping.findForward("displayMemberDetail");
            }
            }
        catch(Exception e){
                /* this will most likely be because the customer already exists in the database. */
       // System.out.print("Client Exception :" + e);
            e.printStackTrace();
        }
        return(mapping.findForward("displayMemberUserList"));
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
