/*
 * MembershipTypeInsertAction.java
 *
 * Created on September 7, 2006, 3:17 PM
 */

package com.mrm.action.adminmaster;

import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import com.hlcmember.type.util.Debug;
import com.hlcmember.type.util.HLCMembershipTypeMaster;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author karthikeyan
 * @version
 */

public class MembershipTypeInsertAction extends Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
       
		try{

			String name = "ejb/HLCKaveryMembershipTypeJNDI";
			Context jndiContext = getInitialContext();
			Object obj=jndiContext.lookup(name);

                      HLCKaveryMembershipTypeSessionRemoteHome home = (HLCKaveryMembershipTypeSessionRemoteHome)
			javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);

                        HLCKaveryMembershipTypeSessionRemote remote = home.create();			

                        HLCMembershipTypeMaster objMemberTypeMaster = new HLCMembershipTypeMaster();

			System.out.println("\n after InitialContext Beginning emp Client...\n");
                        HttpSession session = request.getSession();
                        
                        String memProcess = request.getParameter("memProcess");
//==================================================MemeberShip Master Started here ===========================================                                                    
                        if(memProcess!=null && memProcess.trim().length()!=0){
//==================================================MemeberShip Init Method ===========================================                            
                             if(memProcess.equals("initCreate")){
                                    Debug.print("initCreate Called");
                                    Vector vDisUserType = remote.displayUserTypeDetails();
                                    ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                    //session.setAttribute("displayUserTypeDetails" ,null);
                                    //session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                    Debug.print("The After the list");
                                    session.setAttribute("displayMemDetails" ,null);
                                    request.setAttribute("accDetails",accDetails);
                                    request.setAttribute("status","true");
                                    
                                    return mapping.findForward("membershipCrea");
                             }
                             else if(memProcess.equals("createMemTyMaster")){
                                    Debug.print("createMemTyMaster Called");
                                    String uTypeId = remote.getUserTypeIdOnName("Human");
                                    String memName = request.getParameter("memName");
                                    String memAmount = request.getParameter("memAmount");
                                    String duration=request.getParameter("DurationNoDdl")+"-";
                                    duration=duration+request.getParameter("DurationTypeDdl");
                                    String periodValue = request.getParameter("periodValue");
                                    String QBTranascType = request.getParameter("transacType");
                                    String memYear = request.getParameter("memYear");
                                    String active_Status=request.getParameter("rd1");
                                         int active=1;
                                         if(active_Status.equals("1"))
                                        {
                                            active=1;
                                        }
                                         else
                                        {
                                            active=0;
                                        }
                                    Debug.print("memYear "+memYear);
                                    
                                    if(memYear!=null && memYear.trim().length()!=0){
                                        objMemberTypeMaster.setMembership_year(Integer.parseInt(memYear));
                                    }
                                    else{
                                        objMemberTypeMaster.setMembership_year(0);
                                    }
                                    objMemberTypeMaster.setActive_Status(active);
                                    objMemberTypeMaster.setUserTypeId(uTypeId);
                                    objMemberTypeMaster.setMembershipTypeName(memName);
                                    objMemberTypeMaster.setMembershipAmount(memAmount);
                                    objMemberTypeMaster.setPeriodValue(periodValue);
                                    objMemberTypeMaster.setTransaction_type_id(QBTranascType);
                                    objMemberTypeMaster.setDuration(duration);
                                    boolean stat=remote.addMembershipTypeMaster(objMemberTypeMaster);
                                    Debug.print("Inserted Sucessfully "+stat);
                                   // String status=String.valueOf(stat);
                                    
                                    if(stat==true)
                                    {
                                         Vector vMemDetails = remote.displayHumanMembershipTypeDetail();
                                         ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                        request.setAttribute("accDetails",accDetails);
                                        session.setAttribute("displayMemDetails" ,null);
                                        session.setAttribute("displayMemDetails" ,vMemDetails);
					return mapping.findForward("membershipList");
                                    }
                                    else
                                    {
                                    Vector vDisUserType = remote.displayUserTypeDetails();
                                    ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                    request.setAttribute("accDetails",accDetails);
                                    session.setAttribute("displayUserTypeDetails" ,null);
                                    session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                    session.setAttribute("displayMemDetails" ,null);
                                    request.setAttribute("status",String.valueOf(stat));
                                    
                                    return mapping.findForward("membershipCrea");
                                       
                                    }
                                    
                             }
                              else if(memProcess.equals("initList")){
                                    Debug.print("initList Called");
                                    Vector vDisUserType = remote.displayHumanMembershipTypeDetail();
                                        int i=0;
                                        while(i<vDisUserType.size())
                                        {
                                            String[] val = (String[])vDisUserType.get(i);
                                            String ID = val[1];
                                            String name1 = val[2];
                                            String amount=val[3];
                                            Debug.print(amount);
/*
 Debug.print("Value [0] is "+ID);
Debug.print("Value [1] is "+name1);*/
                                            i++;
                                        }
                                    session.setAttribute("displayMemDetails" ,null);
                                    session.setAttribute("displayMemDetails" ,vDisUserType);
                                    ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                    request.setAttribute("accDetails",accDetails);
                                    return mapping.findForward("membershipList");
                             }
                            else if(memProcess.equals("memGetMemDet")){
                                    Debug.print("memGetMemDet Called");
                                    String uTypeId = request.getParameter("uTypeId");
                                    String yr = request.getParameter("year");
                                    String membTxt = request.getParameter("membTxt");
                                    
                                    int year = 0;
                                    Debug.print("membTxt is  "+membTxt);
                                    if(membTxt!=null && membTxt.trim().length()!=0){
                                        if(!membTxt.equalsIgnoreCase("human")){
                                            year = 0;
                                        }
                                        else{
                                            year = Integer.parseInt(yr);
                                        }
                                    }
                                    
                                    
                                    Debug.print("uTypeId :"+uTypeId);
                                    Debug.print("year :"+year);
                                    Debug.print("membTxt is  "+membTxt);
                                    
                                    if(uTypeId==null)
                                    {
                                        Vector vMemDetails = remote.displayMembershipTypeOnYear(uTypeId,year);
                                        
                                        int i=0;
                                        while(i<vMemDetails.size())
                                        {
                                            String[] val = (String[])vMemDetails.get(i);
                                            i++;
                                        }
                                        i=0;
                                        Vector vDisUserType = remote.displayUserTypeDetails();
                                        while(i<vDisUserType.size())
                                        {
                                            String[] val1 = (String[])vDisUserType.get(i);
                                            String ID = val1[0];
                                            String name1 = val1[1];
                                            Debug.print("Id is "+ID);
                                            Debug.print("name is "+name1);
                                            i++;
                                        }
                                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                    request.setAttribute("accDetails",accDetails);
                                        session.setAttribute("displayUserTypeDetails" ,null);
                                        session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                        session.setAttribute("displayMemDetails" ,null);
                                        session.setAttribute("displayMemDetails" ,vMemDetails);
                                        request.setAttribute("uTypeId",uTypeId);
                                        request.setAttribute("membTxt",membTxt);
                                        session.setAttribute("membTxt",membTxt);
                                        request.setAttribute("year",String.valueOf(year));
                                        session.setAttribute("year",String.valueOf(year));
                                        session.setAttribute("membTxt",membTxt);
                                        
                                        return mapping.findForward("membershipList");
                                    }
                                    if(uTypeId!=null && uTypeId.trim().length()!=0){
                                        //Vector vMemDetails = remote.displayMembershipType(uTypeId);
                                        Vector vMemDetails = remote.displayMembershipTypeOnYear(uTypeId,year);

                                        int i=0;
                                        while(i<vMemDetails.size())
                                        {
                                            String[] val = (String[])vMemDetails.get(i);
                                            i++;
                                        }
                                        i=0;
                                        Vector vDisUserType = remote.displayUserTypeDetails();
                                        while(i<vDisUserType.size())
                                        {
                                            String[] val1 = (String[])vDisUserType.get(i);
                                            String ID = val1[0];
                                            String name1 = val1[1];
                                            Debug.print("Id is "+ID);
                                            Debug.print("name is "+name1);
                                            i++;
                                        }
                                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                    request.setAttribute("accDetails",accDetails);
                                        session.setAttribute("displayUserTypeDetails" ,null);
                                        session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                        session.setAttribute("displayMemDetails" ,null);
                                        session.setAttribute("displayMemDetails" ,vMemDetails);
                                        request.setAttribute("uTypeId",uTypeId);
                                        request.setAttribute("membTxt",membTxt);
                                        session.setAttribute("membTxt",membTxt);
                                        request.setAttribute("year",String.valueOf(year));
                                        session.setAttribute("year",String.valueOf(year));
                                        session.setAttribute("membTxt",membTxt);

                                        return mapping.findForward("membershipList");
                                    }
                                   //return mapping.findForward("admin");
                             }
                             
                             else if(memProcess.equals("initEdit")){
                                    Debug.print("initEdit Called");
                                    String memId = request.getParameter("memId");
                                    if(memId!=null && memId.trim().length()!=0){
                                        String [] vMemDetail = remote.getMembershipTypeDetail(memId);
                                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                       // Vector vDisUserType = remote.displayUserTypeDetails();
                                        int year = remote.getMembershipTypeYearOnId(memId);
                                        request.setAttribute("year",String.valueOf(year));
                                        session.setAttribute("displayEditMemDetails" ,null);
                                        session.setAttribute("displayEditMemDetails" ,vMemDetail);
                                        //session.setAttribute("displayUserTypeDetails" ,null);
                                        //session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                        request.setAttribute("uTypeId",memId);
                                        request.setAttribute("accDetails",accDetails);
                                        request.setAttribute("status","true");
                                        return mapping.findForward("membershipEdit");
                                    }
                                    //return mapping.findForward("admin");
                             }
                             
                             else if(memProcess.equals("memTyEdit")){
                                    Debug.print("memTyEdit Called");
                                    String memId = request.getParameter("memId");
                                    
                                    if(memId!=null && memId.trim().length()!=0){
                                        String memName = request.getParameter("memName");
                                        String memAmount = request.getParameter("memAmount");
                                        String uTypeId = request.getParameter("uType");
                                        String periodValue = request.getParameter("periodValue");
                                        String transacType= request.getParameter("transacType");
                                        //Debugs starts by Lakshmi
                                        String year1 = request.getParameter("memyear");
                                        //Debugs Ends by lakshmi
                                        Debug.print("memyear"+ year1);
                                        String duration=request.getParameter("DurationNoDdl")+"-";
                                        duration=duration+request.getParameter("DurationTypeDdl");
                                         String active_Status=request.getParameter("rd1");
                                         int active=1;
                                          if(active_Status.equals("active"))
                                        {
                                            active=1;
                                        }
                                         else
                                        {
                                            active=0;
                                        }
                                        objMemberTypeMaster.setMembershipTypeId(memId);
                                        objMemberTypeMaster.setUserTypeId(uTypeId);
                                        objMemberTypeMaster.setMembershipTypeName(memName);
                                        objMemberTypeMaster.setMembershipAmount(memAmount);
                                        objMemberTypeMaster.setPeriodValue(periodValue);
                                        objMemberTypeMaster.setTransaction_type_id(transacType);
                                        objMemberTypeMaster.setActive_Status(active);
                                         objMemberTypeMaster.setDuration(duration);
                                        if(year1!=null && !year1.equalsIgnoreCase("0")){
                                            objMemberTypeMaster.setMembership_year(Integer.parseInt(year1));
                                        }
                                        else{
                                            objMemberTypeMaster.setMembership_year(0);
                                        }
                                        
                                        boolean stat = remote.editHumanMembershipTypeMaster(objMemberTypeMaster);
                                        if(stat==true){
                                            Debug.print("memTyEdit Sucessfully Editted:" + memId);
                                            String sesYear = (String)session.getAttribute("year");
                                            int year = 0;
                                            if(sesYear !=null && sesYear.trim().length()!=0){
                                                year = Integer.parseInt(sesYear);
                                            }         
                                            String membTxt = (String)session.getAttribute("membTxt");
                                            //Vector vMemDetails = remote.displayMembershipType(uTypeId);
                                            Vector vMemDetails = remote.displayHumanMembershipTypeDetail();;
                                            //Vector vDisUserType = remote.displayUserTypeDetails();
                                            ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                             request.setAttribute("accDetails",accDetails);
                                           // session.setAttribute("displayUserTypeDetails" ,null);
                                            //session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                            session.setAttribute("displayMemDetails" ,null);
                                            session.setAttribute("displayMemDetails" ,vMemDetails);
                                            request.setAttribute("uTypeId",uTypeId);
                                            request.setAttribute("status",String.valueOf(stat));
                                            request.setAttribute("membTxt",membTxt);
                                            return mapping.findForward("membershipList");
                                        }
                                        else{
                                            String [] vMemDetail = remote.getMembershipTypeDetail(memId);
                                            int year = remote.getMembershipTypeYearOnId(memId);
                                            request.setAttribute("year",String.valueOf(year));                                            
                                            ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                            Vector vDisUserType = remote.displayUserTypeDetails();

                                            session.setAttribute("displayEditMemDetails" ,null);
                                            session.setAttribute("displayEditMemDetails" ,vMemDetail);
                                          //  session.setAttribute("displayUserTypeDetails" ,null);
                                           // session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                            request.setAttribute("uTypeId",memId);
                                            //For Debugs Starts
                                           request.setAttribute("memName", memName);
                                           Debug.print("memName"+memName);
                                           //For Debugs Ends
                                            request.setAttribute("accDetails",accDetails);
                                            request.setAttribute("status","false");
                                            return mapping.findForward("membershipEdit");                                            
                                        }
                                    }
                                   // return mapping.findForward("admin");
                             }
                            else if(memProcess.equals("initDelete")){
                                Debug.print("initDelete Called");
                                String memId = request.getParameter("memId");
                                if(memId!=null && memId.trim().length()!=0){
                                    String [] vMemDetail = remote.getMembershipTypeDetail(memId);
                                    int year = remote.getMembershipTypeYearOnId(memId);
                                    request.setAttribute("year",String.valueOf(year));                                    
                                    session.setAttribute("displayEditMemDetails" ,null);
                                    session.setAttribute("displayEditMemDetails" ,vMemDetail);
                                    request.setAttribute("uTypeId",memId);
                                    return mapping.findForward("membershipDelete");
                                }
                                  // return mapping.findForward("admin");
                            }
                             
                              else if(memProcess.equals("memTyDelete")){
                                Debug.print("memTyDelete Called");
                                String memId[] = request.getParameterValues("select11");
                                String typeid;
                                 System.out.println("You have selected:"+memId.length);

                                 if(memId.length!=0)
                                 {
                                    for(int i=0;i<memId.length;i++)
                                    {
                                        typeid=memId[i];
                                        if(typeid!=null && typeid.trim().length()!=0)
                                        {
											 // Method HLCMembershipType in com.hlcmember.type.stateless
                                             boolean result= remote.deleteMembershipTypeMaster(typeid);

                                             System.out.println("MembershipTypeId: "+typeid);
                                             System.out.println("Delete Operation Result "+result);
                                        }

                                    }

                                 }

                                 Vector vMemDetails = remote.displayHumanMembershipTypeDetail();
                                 ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                    request.setAttribute("accDetails",accDetails);
                                 session.setAttribute("displayMemDetails" ,null);
                                 session.setAttribute("displayMemDetails" ,vMemDetails);
								 return mapping.findForward("membershipList");
                                    }
                          else if(memProcess.equals("initView")){
                                Debug.print("initView Called");
                                String memId = request.getParameter("memId");
                                if(memId!=null && memId.trim().length()!=0){
                                    String [] vMemDetail = remote.getMembershipTypeDetail(memId);
                                    int year = remote.getMembershipTypeYearOnId(memId);
                                    request.setAttribute("year",String.valueOf(year));                                    
                                    session.setAttribute("displayEditMemDetails" ,null);
                                    session.setAttribute("displayEditMemDetails" ,vMemDetail);
                                    request.setAttribute("uTypeId",memId);
                                    return mapping.findForward("membershipViewSingle");
                                }
                               // return mapping.findForward("admin");
                            }
                             
//==================================================MemeberShip Master Ended here ===========================================                            
                          else if(memProcess.equals("Listing"))
                          {
                                 Debug.print("Inside Listing");
                                 String memId = request.getParameter("memId");
                                 String process = request.getParameter("btnsubmit"); 
                                 Debug.print("memId is "+memId);
                                 Debug.print("button Value "+process);

                                     /*This Condition is selected When Delete Button is Clicked In Human Membership Type Page

                                      */
//3/10/2011
                                    if(process.equals("Delete"))
                                     {
                                          Debug.print("memTyDelete Called");

                                             String memID[] = request.getParameterValues("select11");
                                             String typeid;
                                            //System.out.println("You have selected:"+memID.length);

                                             // typeid=memID[0];
                                              if(memID!=null)
                                                 {
                                                  System.out.println("You have selected:"+memID.length);
                                                     for(int i=0;i<memID.length;i++)
                                                     {
                                                         typeid=memID[i];
                                                        if(typeid!=null && typeid.trim().length()!=0)
                                                        {
                                                            boolean result= remote.deleteMembershipTypeMaster(typeid);
                                                             System.out.println("MembershipTypeId: "+typeid);
                                                             System.out.println("Delete Operation Result "+result);

                                                         }
                                                    }
                                                }
                                               Vector vMemDetails = remote.displayHumanMembershipTypeDetail();
                                               ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                    request.setAttribute("accDetails",accDetails);
                                               session.setAttribute("displayMemDetails" ,null);
                                               session.setAttribute("displayMemDetails" ,vMemDetails);
                                               return mapping.findForward("membershipList");

                                     }
                                    else if(process.equals("Update"))
                                    {
                                        String index=request.getParameter("index1");
                                        String memName=request.getParameter("TypeNameText"+index);
                                        Debug.print("Index Value"+index);
                                        String memAmount=request.getParameter("TypeAmtText"+index);
                                        Debug.print("Membership Name,MEmbership Amount"+memName+memAmount);
                                        String active_Status=request.getParameter("rd"+index);
                                        Debug.print("Active Status"+active_Status);
                                        int active;
                                        String duration=request.getParameter("TypeDurationnoDdl"+index)+"-";
                                        duration=duration+request.getParameter("TypeDurationtypeDdl"+index);
                                        Debug.print("Active Status"+request.getParameter("TypeDurationtypeDdl"+index));
                                        Debug.print("Active Status"+duration);
                                        if(active_Status.equals("active"))
                                        {
                                            active=1;
                                        }
                                         else
                                        {
                                            active=0;
                                        }
                                        objMemberTypeMaster.setMembershipTypeId(memId);
                                        objMemberTypeMaster.setMembershipTypeName(memName);
                                        objMemberTypeMaster.setMembershipAmount(memAmount);
                                        objMemberTypeMaster.setActive_Status(active);
                                         objMemberTypeMaster.setDuration(duration);
                                          boolean stat = remote.editHumanMembershipTypeMaster(objMemberTypeMaster);
                                          if(stat==true)
                                          {
                                              Debug.print("Update Sucessful on Membership ID"+memId);
                                          }
                                           Vector vDisUserType = remote.displayHumanMembershipTypeDetail();
                                           ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                                         request.setAttribute("accDetails",accDetails);
                                        session.setAttribute("displayUserTypeDetails" ,null);
                                        session.setAttribute("displayMemDetails" ,vDisUserType);
                                        return mapping.findForward("membershipList");

                                    }
                                      else if(process.equals("Add"))
                                     {
					Debug.print("initCreate Called");
					//String uTypeId = remote.getUserTypeIdOnName("Human");
					ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
					//session.setAttribute("displayUserTypeDetails" ,null);
					 //session.setAttribute("displayUserTypeDetails" ,vDisUserType);
					 Debug.print("The After the list");
					 session.setAttribute("displayMemDetails" ,null);
					  request.setAttribute("accDetails",accDetails);
					 // request.setAttribute("uTypeId",uTypeId);
					  request.setAttribute("status","true");
					 return mapping.findForward("membershipCrea");
                                          
                                     }

                                     else
                                     {
                                          return null;
                                }
                                 }
                          }

//==================================================Try Block Closing here===========================================
		}
		catch (Exception ex){
			System.err.println("Caught an exception.");
			ex.printStackTrace();
		}
		return null;
	}

	public static Context getInitialContext()throws javax.naming.NamingException {
		Properties p =new Properties();
		p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
		p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
		p.setProperty( "java.naming.provider.url", "localhost:11199" );
		return new javax.naming.InitialContext(p);
	}

    }




