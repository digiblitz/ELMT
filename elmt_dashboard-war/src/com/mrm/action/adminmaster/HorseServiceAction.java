/*
 * HorseServiceAction.java
 *
 * Created on September 7, 2006, 3:02 PM
 */

package com.mrm.action.adminmaster;

import com.hlchorse.service.util.Debug;
import com.hlchorse.service.util.HLCHorseServiceTypeMaster;
import com.hlchorse.stateless.HLCKaveryHorseStatelessRemote;
import com.hlchorse.stateless.HLCKaveryHorseStatelessRemoteHome;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import java.util.Properties;

/**
 *
 * @author karthikeyan
 * @version
 */

public class HorseServiceAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try{
                   
	 String name = "ejb/HLCKaveryHorseServiceJNDI";
	 Context jndiContext = getInitialContext();
	 Object obj=jndiContext.lookup(name);
	 HLCKaveryHorseStatelessRemoteHome home = (HLCKaveryHorseStatelessRemoteHome)
	 javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryHorseStatelessRemoteHome.class);
	 HLCKaveryHorseStatelessRemote remote = home.create();

	 HLCHorseServiceTypeMaster objHorseServiceType= new HLCHorseServiceTypeMaster();

        String jndiname = "ejb/HLCKaveryMembershipTypeJNDI";
        Object acc_obj=jndiContext.lookup(jndiname);
        HLCKaveryMembershipTypeSessionRemoteHome acc_home = (HLCKaveryMembershipTypeSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(acc_obj,HLCKaveryMembershipTypeSessionRemoteHome.class);

        HLCKaveryMembershipTypeSessionRemote acc_remote = acc_home.create();         
                          
        HttpSession session = request.getSession();

        String horseProcess = request.getParameter("horseProcess");
//==================================================MemeberShip Master Started here ===========================================                                                    
         if(horseProcess!=null && horseProcess.trim().length()!=0){
//==================================================MemeberShip Init Method ===========================================
                  
            if(horseProcess.equals("initHorseMaster")){
                Debug.print("initHorseMaster Called");
                Vector vDisUserType;
                String userTypeId=request.getParameter("uType");
                 String usertypename =request.getParameter("userTypeName");
                ArrayList accDetails = (ArrayList) acc_remote.getAccTxnTypDetails();
                session.setAttribute("userTypeId",userTypeId);
                request.setAttribute("accDetails",null);
                request.setAttribute("accDetails",accDetails);
                 if(usertypename.equals("Human"))
                 {
                     vDisUserType=acc_remote.displayHumanUserTypeDetails();
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                     session.setAttribute("userTypeName", usertypename);
                     return mapping.findForward("humanmembCreatetype");
                 }
                else
                 {
                     vDisUserType=acc_remote.displayUserTypeDetails();
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                     session.setAttribute("userTypeName", usertypename);
                    return mapping.findForward("horsemembCreatetype");
                 }
                
            }
            
            else if(horseProcess.equals("createHorseMaster")){
                Debug.print("createHorseMaster Called");
                String userTypeId=request.getParameter("uType");//12042011
                 String usertypename =request.getParameter("userTypeName");
                String horseServiceTypeName = request.getParameter("horseServiceTypeName");
                String horseServiceTypeAmount = request.getParameter("horseServiceTypeAmount");
                String transactionTypeId = request.getParameter("transacType");
                int active_status=Integer.parseInt(request.getParameter("rd1"));//12042011

                Debug.print("createHorseMaster UserTypeId:"+userTypeId);
                Debug.print("createHorseMaster horseServiceTypeName:" + horseServiceTypeName);
                Debug.print("createHorseMaster horseServiceTypeAmount:" + horseServiceTypeAmount);
                Debug.print("createHorseMaster transactionTypeId:" + transactionTypeId);//12042011
                Debug.print("createHorseMaster Active Status:"+active_status);//12042011

                objHorseServiceType.setUserTypeId(userTypeId);//12042011
                objHorseServiceType.setHorseServiceTypeName(horseServiceTypeName);
                objHorseServiceType.setHorseServiceTypeAmount(horseServiceTypeAmount);
                // Added Code for service type
                objHorseServiceType.setTransaction_type_id(transactionTypeId);
                objHorseServiceType.setActiveStatus(active_status);//12042011
                boolean stat=remote.addHorseServiceTypeMaster(objHorseServiceType);
                Debug.print("Inserted Sucessfully");
                String status=String.valueOf(stat);
                
                if(stat==true)
                {//12042011
                   Vector vDisUserType;
                userTypeId=request.getParameter("uType");//12042011
                 Vector vDisNonHumServiceType=remote.displayNonHumanServiceDetails(userTypeId);//12042011
                 session.setAttribute("userTypeId",userTypeId);//12042011
                 session.setAttribute("displayNonHumServiceDetails" ,null);//12042011
                 session.setAttribute("displayNonHumServiceDetails",vDisNonHumServiceType );//12042011
                 session.setAttribute("displayHrsDetails" ,null);//12042011
                  if(usertypename.equals("Human"))
                 {
                     vDisUserType=acc_remote.displayHumanUserTypeDetails();
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                     session.setAttribute("userTypeName", usertypename);
                     return mapping.findForward("humServiceList");
                 }
                else
                 {
                     vDisUserType=acc_remote.displayUserTypeDetails();
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                     session.setAttribute("userTypeName", usertypename);
                    return mapping.findForward("hrsServiceList");
                 }
                     
                }
                else
                {
                    request.setAttribute("status",status);
                    request.setAttribute("err","st");
                    Vector vDisUserType;//12042011
                    ArrayList accDetails = (ArrayList) acc_remote.getAccTxnTypDetails();//12042011
                    session.setAttribute("userTypeId",userTypeId);//12042011
                    //session.setAttribute("displayUserTypeDetails", null);//12042011
                    //session.setAttribute("displayUserTypeDetails", vDisUserType);//12042011
                    request.setAttribute("accDetails",null);//12042011
                    request.setAttribute("accDetails",accDetails);//12042011
                     if(usertypename.equals("Human"))
                 {
                     vDisUserType=acc_remote.displayHumanUserTypeDetails();
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                     session.setAttribute("userTypeName", usertypename);
                     return mapping.findForward("humanmembCreatetype");
                 }
                else
                 {
                     vDisUserType=acc_remote.displayUserTypeDetails();
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                     session.setAttribute("userTypeName", usertypename);
                    return mapping.findForward("horsemembCreatetype");
                 }
                }
            }
            
            else if(horseProcess.equals("initList")){
                 Vector vDisUserType;
                Debug.print("initList Called");
                String usertypename =request.getParameter("userTypeName");
                session.setAttribute("userTypeName", usertypename);
               
            //    Vector vDisHrsServiceType = remote.displayHorseServiceDetails();
                if(usertypename.equals("Human"))
                {
                    vDisUserType=acc_remote.displayHumanUserTypeDetails();
                     session.setAttribute("userTypeId", null);
                session.setAttribute("displayNonHumServiceDetails" ,null);
                session.setAttribute("displayUserTypeDetails", null);
                session.setAttribute("displayUserTypeDetails", vDisUserType);
              //  session.setAttribute("displayHrsServiceDetails" ,vDisHrsServiceType);
                session.setAttribute("displayNonHumDetails" ,null);
                return mapping.findForward("humServiceList");

                }
                vDisUserType=acc_remote.displayUserTypeDetails();
                session.setAttribute("userTypeId", null);
                session.setAttribute("displayNonHumServiceDetails" ,null);
                session.setAttribute("displayUserTypeDetails", null);
                session.setAttribute("displayUserTypeDetails", vDisUserType);
              //  session.setAttribute("displayHrsServiceDetails" ,vDisHrsServiceType);
                session.setAttribute("displayNonHumDetails" ,null);
                return mapping.findForward("hrsServiceList");
            }
            else if(horseProcess.equals("getSerTypeDet")){
                Debug.print("getSerTypeDet Called");
                 Vector vDisUserType;
                 String userTypeId=request.getParameter("uType");
                  String usertypename =request.getParameter("userTypeName");
                 Vector vDisNonHumServiceType=remote.displayNonHumanServiceDetails(userTypeId);
                 session.setAttribute("userTypeId",userTypeId);
                 session.setAttribute("displayNonHumServiceDetails" ,null);
                 session.setAttribute("displayNonHumServiceDetails",vDisNonHumServiceType );
                 session.setAttribute("displayHrsDetails" ,null);
                 if(usertypename.equals("Human"))
                 {
                     vDisUserType=acc_remote.displayHumanUserTypeDetails();
                      session.setAttribute("userTypeName", usertypename);
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                     return mapping.findForward("humServiceList");
                 }
                else
                 {
                     vDisUserType=acc_remote.displayUserTypeDetails();
                      session.setAttribute("userTypeName", usertypename);
                     session.setAttribute("displayUserTypeDetails", null);
                     session.setAttribute("displayUserTypeDetails", vDisUserType);
                    return mapping.findForward("hrsServiceList");
                 }
             }
            
             else if(horseProcess.equals("initEdit")){
                    Debug.print("initEdit Called");
                    Vector vDisUserType;
                     String usertypename =request.getParameter("userTypeName");
                    String hrsTyId = request.getParameter("hrsTyId");
                    session.setAttribute("HrsetypId",hrsTyId);
                    request.setAttribute("err","");
                    if(hrsTyId!=null && hrsTyId.trim().length()!=0){
                        String [] vMemDetail = remote.displayHorseServiceDetailsById(hrsTyId);
                        session.setAttribute("displayEditHrsDetails" ,null);
                        session.setAttribute("displayEditHrsDetails" ,vMemDetail);
                        request.setAttribute("uTypeId",hrsTyId);
                        ArrayList accDetails = (ArrayList) acc_remote.getAccTxnTypDetails();
                        request.setAttribute("accDetails",accDetails);
                        if(usertypename.equals("Human"))
                        {
                           vDisUserType=acc_remote.displayHumanUserTypeDetails();
                           session.setAttribute("userTypeName", usertypename);
                           session.setAttribute("displayUserTypeDetails", null);
                           session.setAttribute("displayUserTypeDetails", vDisUserType);
                           return mapping.findForward("humServiceEdit");
                        }
                     else
                        {
                            vDisUserType=acc_remote.displayUserTypeDetails();
                            session.setAttribute("userTypeName", usertypename);
                            session.setAttribute("displayUserTypeDetails", null);
                            session.setAttribute("displayUserTypeDetails", vDisUserType);
                            return mapping.findForward("hrsServiceEdit");
                        }
                        
                    }
                  
                    return mapping.findForward("admin");
             }
                             
            else if(horseProcess.equals("hrsSerTyEdit")){
                Debug.print("hrsSerTyEdit Called");
                String hrsTyId = request.getParameter("hrsTyId");

                if(hrsTyId!=null && hrsTyId.trim().length()!=0){
                    String userTypeId=request.getParameter("uType");//12042011
                    String horseServiceTypeName = request.getParameter("horseServiceTypeName");
                    String horseServiceTypeAmount = request.getParameter("horseServiceTypeAmount");
                    String transacType = request.getParameter("transacType");
                     String usertypename =request.getParameter("userTypeName");
                    int active_status=Integer.parseInt(request.getParameter("rd1"));//12042011
                    
                   // String horseServiceTypeId = request.getParameter("hrsTyId");
                    Debug.print("hrsSerTyEdit UserTypeId:"+userTypeId);
                    Debug.print("hrsSerTyEdit hrsTyId:" + hrsTyId);
                    Debug.print("hrsSerTyEdit transacType:" + transacType);
                    Debug.print("hrsSerTyEdit horseServiceTypeName:" + horseServiceTypeName);
                    Debug.print("hrsSerTyEdit horseServiceTypeAmount:" + horseServiceTypeAmount);
                    Debug.print("hrsSerTyEdit Active Status:"+active_status);//12042011

                    objHorseServiceType.setUserTypeId(userTypeId);
                    objHorseServiceType.setHorseServiceTypeId(hrsTyId);
                    objHorseServiceType.setHorseServiceTypeName(horseServiceTypeName);
                    objHorseServiceType.setHorseServiceTypeAmount(horseServiceTypeAmount);
                    objHorseServiceType.setTransaction_type_id(transacType);
                    objHorseServiceType.setActiveStatus(active_status);
                    
                    boolean bol  = remote.editHorseServiceTypeMaster(objHorseServiceType);
                    Debug.print("Result of edit "+bol);
                    if(bol==true)
                    {
                        Debug.print("memTyEdit Sucessfully Editted:" + hrsTyId);
                        Vector vDisUserType;
                        Vector vDisNonHumServiceType=remote.displayNonHumanServiceDetails(userTypeId);
                        session.setAttribute("userTypeId",userTypeId);
                        session.setAttribute("displayNonHumServiceDetails" ,null);
                        session.setAttribute("displayNonHumServiceDetails",vDisNonHumServiceType );
                        session.setAttribute("displayHrsDetails" ,null);
                         if(usertypename.equals("Human"))
                         {
                             vDisUserType=acc_remote.displayHumanUserTypeDetails();
                             session.setAttribute("displayUserTypeDetails", null);
                            session.setAttribute("displayUserTypeDetails", vDisUserType);
                            session.setAttribute("userTypeName", usertypename);
                             return mapping.findForward("humServiceList");
                          }
                         else
                        {
                              vDisUserType=acc_remote.displayUserTypeDetails();
                              session.setAttribute("displayUserTypeDetails", null);
                               session.setAttribute("displayUserTypeDetails", vDisUserType);
                               session.setAttribute("userTypeName", usertypename);
                              return mapping.findForward("hrsServiceList");
                          }
                    }
                    else
                    {
                        request.setAttribute("status",String.valueOf(bol));
//                        hrsTyId = (String)session.getAttribute("HrsetypId");
                        if(hrsTyId!=null && hrsTyId.trim().length()!=0){
                            Vector vDisUserType;
                            String [] vMemDetail = remote.displayHorseServiceDetailsById(hrsTyId);
//                            session.setAttribute("displayEditHrsDetails" ,null);
                            session.setAttribute("displayEditHrsDetails" ,vMemDetail);
                            request.setAttribute("uTypeId",hrsTyId);
                            request.setAttribute("err","st");
                            if(usertypename.equals("Human"))
                            {
                                 vDisUserType=acc_remote.displayHumanUserTypeDetails();
                                session.setAttribute("displayUserTypeDetails", null);
                                session.setAttribute("displayUserTypeDetails", vDisUserType);
                                session.setAttribute("userTypeName", usertypename);
                                //For Debugs Starts
                                request.setAttribute("horseServiceTypeName",horseServiceTypeName);
                                Debug.print("horseServiceTypeName"+horseServiceTypeName);
                                //For Debugs Ends
                                return mapping.findForward("humServiceEdit");
                            }
                            else
                            {
                                  vDisUserType=acc_remote.displayUserTypeDetails();
                                 session.setAttribute("displayUserTypeDetails", null);
                                 session.setAttribute("displayUserTypeDetails", vDisUserType);
                                 ArrayList accDetails = (ArrayList) acc_remote.getAccTxnTypDetails();
                                 request.setAttribute("accDetails",accDetails);
                                session.setAttribute("userTypeName", usertypename);
                                //For Debugs Starts
                                request.setAttribute("horseServiceTypeName",horseServiceTypeName);
                                Debug.print("horseServiceTypeName"+horseServiceTypeName);
                                //For Debugs Ends
                                 return mapping.findForward("hrsServiceEdit");
                            }
                                                       
                        }
                   }
                    
                    
                }
                return mapping.findForward("admin");
            }
                             
              else if(horseProcess.equals("HrsServiceTyDelete")){
                Debug.print("HrsServiceTyDelete Called");
                String hrsTyId[] = request.getParameterValues("select11");
                String userTypeId=request.getParameter("uType");
                String usertypename =request.getParameter("userTypeName");
                if(hrsTyId.length!=0)
                {
                 for(int i=0;i<hrsTyId.length;i++)
                 {
                 if(hrsTyId[i]!=null && hrsTyId[i].trim().length()!=0){
                    boolean result= remote.deleteHorseServiceTypeMaster(hrsTyId[i]);
                    String status=String.valueOf(result);
                    Debug.print("Deletion Completed is"+result);
                    if(result==false)
                    {
                      request.setAttribute("status",status);
                      Debug.print("Delete Un Suceessful");
                       Vector vDisUserType;
                        Vector vDisNonHumServiceType=remote.displayNonHumanServiceDetails(userTypeId);
                        session.setAttribute("userTypeId",userTypeId);
                        session.setAttribute("displayNonHumServiceDetails" ,null);
                        session.setAttribute("displayNonHumServiceDetails",vDisNonHumServiceType );
                        session.setAttribute("displayHrsDetails" ,null);
                         if(usertypename.equals("Human"))
                        {
                           vDisUserType=acc_remote.displayHumanUserTypeDetails();
                           session.setAttribute("userTypeName", usertypename);
                           session.setAttribute("displayUserTypeDetails", null);
                           session.setAttribute("displayUserTypeDetails", vDisUserType);
                           return mapping.findForward("humServiceList");
                        }
                     else
                        {
                            vDisUserType=acc_remote.displayUserTypeDetails();
                            session.setAttribute("userTypeName", usertypename);
                            session.setAttribute("displayUserTypeDetails", null);
                            session.setAttribute("displayUserTypeDetails", vDisUserType);
                            return mapping.findForward("hrsServiceList");
                        }
                        //return mapping.findForward("hrsServiceList");
                    }
                   }
                    }
                       Debug.print("Delete Sucessful");
                       Vector vDisUserType;
                        Vector vDisNonHumServiceType=remote.displayNonHumanServiceDetails(userTypeId);
                        session.setAttribute("userTypeId",userTypeId);
                        session.setAttribute("displayNonHumServiceDetails" ,null);
                        session.setAttribute("displayNonHumServiceDetails",vDisNonHumServiceType );
                        //session.setAttribute("displayUserTypeDetails", null);
                        //session.setAttribute("displayUserTypeDetails", vDisUserType);
                        session.setAttribute("displayHrsDetails" ,null);
                         if(usertypename.equals("Human"))
                        {
                           vDisUserType=acc_remote.displayHumanUserTypeDetails();
                           session.setAttribute("userTypeName", usertypename);
                           session.setAttribute("displayUserTypeDetails", null);
                           session.setAttribute("displayUserTypeDetails", vDisUserType);
                           return mapping.findForward("humServiceList");
                        }
                     else
                        {
                            vDisUserType=acc_remote.displayUserTypeDetails();
                            session.setAttribute("userTypeName", usertypename);
                            session.setAttribute("displayUserTypeDetails", null);
                            session.setAttribute("displayUserTypeDetails", vDisUserType);
                            return mapping.findForward("hrsServiceList");
                        }
                        //return mapping.findForward("hrsServiceList");
                }
                   return mapping.findForward("admin");
            }
          else if(horseProcess.equals("initView")){
                Debug.print("initView Called");
                String hrsTyId = request.getParameter("hrsTyId");
                if(hrsTyId!=null && hrsTyId.trim().length()!=0){
                    String [] vHrsServiceDetail = remote.displayHorseServiceDetailsById(hrsTyId);
                    session.setAttribute("displayViewHrsDetails" ,null);
                    session.setAttribute("displayViewHrsDetails" ,vHrsServiceDetail);
                    request.setAttribute("hrsTyId",hrsTyId);
                    return mapping.findForward("hrsServiceViewSingle");
                }
                return mapping.findForward("admin");
            }
                             
//==================================================MemeberShip Master Ended here ===========================================                            
                        }
//==================================================Try Block Closing here===========================================
		}
		catch (Exception ex){
			System.err.println("Caught an exception.");
			ex.printStackTrace();
		}
		return mapping.findForward("admin");
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
