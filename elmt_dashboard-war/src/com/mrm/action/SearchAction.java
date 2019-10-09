/*
 * SearchAction.java
 *
 * Created on November 16, 2006, 6:06 PM
 */

package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCUserSearchResultVO;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.*;
import com.hlcrole.management.*;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author punitha
 * @version
 */

public class SearchAction extends Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");         
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            
            Context jndiContext = new InitialContext();                 
            String jndiname1=mr.getMessage("jndi.usrreg");
            Object objref1 = jndiContext.lookup(jndiname1);

            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref1,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();

String jndiname=mr.getMessage("jndi.rolemanagement");
Object objref = jndiContext.lookup(jndiname);

            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCBrahmaputraSessionRemoteHome.class);
                HLCBrahmaputraSessionRemote roleRemote = roleHome.create();
                
            HttpSession session = request.getSession();
            String searchProcess = request.getParameter("searchProcess");
            
            if(searchProcess==null){
                return mapping.findForward("frmMemberSearchList");
            } else if(searchProcess.equals("initViewDet")){
                ArrayList roleList = new ArrayList();
                    roleList = (ArrayList)roleRemote.getAllRoles();

                 request.setAttribute("roleList",roleList);

                return mapping.findForward("frmMemberSearchList");
            }
//============================================================================================
            else if(searchProcess.equals("humanSearch")){
                Debug.print("SearchList Action humanSearch executing....");
                ArrayList memberDetails = null;
              
      //=========Dhivya Here:Assign Role to Users========================
                 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                String radMem=request.getParameter("radMem");
                    String  firstName = request.getParameter("fname");
                    String lastName = request.getParameter("lname");
                    String emailVal = request.getParameter("email");
                    String zipCode = request.getParameter("zip");
                    String logName = request.getParameter("loginName");
                    String frmDate = request.getParameter("frmDate");
                    String tDate = request.getParameter("toDate");
                    String rolId = request.getParameter("roleId");
        Debug.print("radMem in servlet&&&&&"+radMem);
         session.setAttribute("radMem",radMem);
                    String  fName = firstName.trim();
                    String lName = lastName.trim();
                    String email = emailVal.trim();
                    String zip = zipCode.trim();
                    String loginName = logName.trim();

                     Date fromDate = null;
        if (frmDate != null && frmDate.trim().length() != 0) {
            fromDate = (Date) sdf.parse(frmDate);
            Debug.print("fromDate in servlet" + fromDate);
        }

                     Date toDate = null;
        if (tDate != null && tDate.trim().length() != 0) {
            toDate = (Date) sdf.parse(tDate);
            Debug.print("toDate in servlet" + toDate);
        }
                    
                
                    String roleId= rolId.trim();

                if(radMem!=null && radMem.equalsIgnoreCase("members")){
                String memID=request.getParameter("memberId");
                String  memberId =memID.trim();             
                session.setAttribute("searchMemId",memberId); 
                if(memberId!=null && memberId.trim().length()!=0){
                    Debug.print("SearchList Action Found MemberSearch");
                    memberDetails = remote.searchUserByMemberId(memberId);
                    
                   
                } else{
                                  
                    session.setAttribute("fname",fName);
                    session.setAttribute("lname",lName);
                    session.setAttribute("email",email);
                    session.setAttribute("zip",zip);
                    session.setAttribute("login_Name",loginName);
                    session.setAttribute("fromDate",fromDate);
                    session.setAttribute("toDate",toDate);
                    session.setAttribute("roleIds",roleId);
                    
                    if((fName!=null && fName.trim().length()!=0) || (lName!=null && lName.trim().length()!=0)
                    || (email!=null && email.trim().length()!=0) || (zip!=null && zip.trim().length()!=0) 
                    || (fromDate!=null) || (toDate!=null)
                    || (roleId!=null && roleId.trim().length()!=0)){
                        memberDetails = (ArrayList)remote.searchUserByGeneral(fName, lName, email, zip, fromDate,toDate,roleId,radMem);
                    } else if(loginName!=null && loginName.trim().length()!=0) {
                        Debug.print("SearchList Action Found LoginSearch");
                        memberDetails = (ArrayList)remote.searchUserByLoginName(loginName,radMem);
                    }
                    
                }

                }else if(radMem!=null && radMem.equalsIgnoreCase("nonMembers")){

                    session.setAttribute("fname",fName);
                    session.setAttribute("lname",lName);
                    session.setAttribute("email",email);
                    session.setAttribute("zip",zip);
                    session.setAttribute("login_Name",loginName);
                    session.setAttribute("fromDate",fromDate);
                    session.setAttribute("toDate",toDate);
                    session.setAttribute("roleId",roleId);

                    if((fName!=null && fName.trim().length()!=0) || (lName!=null && lName.trim().length()!=0)
                    || (email!=null && email.trim().length()!=0) || (zip!=null && zip.trim().length()!=0)
                    || (fromDate!=null) || (toDate!=null) || (roleId!=null && roleId.trim().length()!=0)){
                       memberDetails = (ArrayList)remote.searchUserByGeneral(fName, lName, email, zip, fromDate,toDate,roleId,radMem);                    
                    } else if(loginName!=null && loginName.trim().length()!=0) {
                        Debug.print("SearchList Action Found LoginSearch");
                        memberDetails = (ArrayList)remote.searchUserByLoginName(loginName,radMem);
                        
                       
                    }
                }

//============Ends Here====================================================
                  request.setAttribute("memberDetails",memberDetails);

                return mapping.findForward("frmMemberSearchResultList");
            }

    else if(searchProcess.equals("bckToSearchList")){
                Debug.print("SearchList Action bckToSearchList executing....");
                ArrayList memberDetails = null;
 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String memberId = (String) session.getAttribute("searchMemId");
            String fName = (String) session.getAttribute("fname");
            String lName = (String) session.getAttribute("lname");
            String email = (String) session.getAttribute("email");
            String zip = (String) session.getAttribute("zip");
            String loginName = (String) session.getAttribute("login_Name");
            String frmDate = (String) session.getAttribute("fromDate");
            String tDate = (String) session.getAttribute("toDate");
            String rolId = (String) session.getAttribute("roleIds");
            String radMem = (String) session.getAttribute("radMem");


        /*   Debug.print("memberId in SearchAction"+memberId);
           Debug.print("fName in SearchAction"+fName);
           Debug.print("lName in SearchAction"+lName);
           Debug.print("email in SearchAction"+email);
           Debug.print("zip in SearchAction"+zip);
           Debug.print("loginName in SearchAction"+loginName);
           Debug.print("frmDate in SearchAction"+frmDate);
           Debug.print("tDate in SearchAction"+tDate);
           Debug.print("rolId in SearchAction"+rolId);
           Debug.print("radMem in SearchAction"+radMem);*/

             Date fromDate = null;
        if (frmDate != null && frmDate.trim().length() != 0) {
            fromDate = (Date) sdf.parse(frmDate);
            Debug.print("fromDate in servlet" + fromDate);
        }
              Date toDate = null;
        if (tDate != null && tDate.trim().length() != 0) {
            toDate = (Date) sdf.parse(tDate);
            Debug.print("toDate in servlet" + toDate);
        }

                   if(memberId!=null && memberId.trim().length()!=0){
                        Debug.print("SearchList Action Found bckToSearchList");
                        memberDetails = remote.searchUserByMemberId(memberId);
                        
                    } else{

                        if((fName!=null && fName.trim().length()!=0) || (lName!=null && lName.trim().length()!=0)
                        || (email!=null && email.trim().length()!=0) || (zip!=null && zip.trim().length()!=0) || (fromDate!=null) || (toDate!=null) || (rolId!=null && rolId.trim().length()!=0)){
                            memberDetails = (ArrayList)remote.searchUserByGeneral(fName, lName, email, zip,fromDate,toDate,rolId,radMem);
                            
                        } else if(loginName!=null && loginName.trim().length()!=0) {
                            Debug.print("SearchList Action Found bckToSearchList");
                            memberDetails = (ArrayList)remote.searchUserByLoginName(loginName,radMem);
                        }
                    }
                    //Debug.print("memberDetails in searchAction servlet" + memberDetails.size());
//============Ends Here====================================================
                request.setAttribute("memberDetails",memberDetails);
                 /* request.getAttribute("dispStr",vObj);
                    request.getAttribute("userTypeId",userTypeId);
                    request.getAttribute("rCnt", String.valueOf(rCnt));
                    request.getAttribute("pNo", String.valueOf(pNo));*/

                //For Debug Starts
                //return mapping.findForward("frmMemberSearchResultList");
                if(memberDetails!=null && !memberDetails.equals(""))
                    return mapping.findForward("frmMemberSearchResultList");
                else
                    return mapping.findForward("displayMemberUserList");
                //For Debug Ends
            }




            //============================================================================================
            else if(searchProcess.equals("loginProcess")){
                Debug.print("Search loginProcess executing........");
                String name3 = "ejb/HLCKaveryMembershipTypeJNDI";
                String jndiname3=mr.getMessage("jndi.kaverymrm");
                Object obj3=jndiContext.lookup(name3);
                
                HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj3,HLCKaveryMembershipTypeSessionRemoteHome.class);
                HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
                
                String staff_user_id = (String) session.getAttribute("userId");
                Debug.print("staff_user_id is "+staff_user_id);
                session.setAttribute("staff_user_id",staff_user_id);
                
                String  userId =   request.getParameter("userId");
                String mailAddressStatus = request.getParameter("mailAddressStatus");
                String splNote="";
                if(request.getParameter("splNote")!=null && request.getParameter("splNote").trim().length() != 0){
                    splNote = request.getParameter("splNote");
                    System.out.println("splNote is assigned");
                }
                boolean AddressStatus = false;
                if(mailAddressStatus!=null ){
                    System.out.println("inside mailAddressStatus!=null");
                    if(mailAddressStatus.equals("true")){
                        AddressStatus = true;
                        System.out.println("AddressStatus = true");
                    }
                }
                
                System.out.println("Checking mailAddressStatus:"+mailAddressStatus);
                System.out.println("Checking userID:"+userId);
                System.out.println("Checking splNote:"+splNote);
                boolean updateStatus = memberRemote.updateUserMemberFlag(userId,splNote,AddressStatus);
                if(updateStatus){
                    System.out.println("Inside servlet view Action updateUserMemberFlag Successfull"+updateStatus);
                } else {
                    System.out.println("Inside Servlet Action updateUserMemberFlag failed");
                }
                
                if(userId!=null && userId.trim().length()!=0) {
                    request.setAttribute("userId" , userId);
                    String backButton="";
                    if(request.getParameter("Back")!=null && request.getParameter("Back").trim().length()!=0){
                        backButton = request.getParameter("Back");
                    }
                    if(backButton.equalsIgnoreCase("Update")){
                        return mapping.findForward("frmMemberSearchList");
                    }else{
                        request.setAttribute("loginProcess", "adminByAdmin");
                        return mapping.findForward("redirLogin");
                    }
                }
            } else if(searchProcess.equalsIgnoreCase("view")) {
                String memberId = request.getParameter("memberId");
                String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
                //Context jndiContext1 = getInitialContext();
                Object obj1=jndiContext.lookup(name1);
                HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj1,HLCKaveryMembershipTypeSessionRemoteHome.class);
                HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
                
                Vector userVect3=memberRemote.selectDetailsForMember(memberId);
                request.setAttribute("userTypeVect3",userVect3);
                
                return mapping.findForward("membView");
            }
            else if(searchProcess.equalsIgnoreCase("empSearchList")) {
                      
                return mapping.findForward("empSearchList");
            }
            else if(searchProcess.equalsIgnoreCase("searchEmp")) {
            	String roleName = (String)session.getAttribute("roleName");
            	System.out.println("roleName=="+roleName);
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String empId = request.getParameter("empId");
                String supName = "";
                if(roleName.equalsIgnoreCase("Admin"))
                {
                	System.out.println("roleName if=="+roleName);
                	supName = request.getParameter("supName");
                }
                if(!roleName.equalsIgnoreCase("Admin"))
                {
                	System.out.println("roleName else=="+roleName);
                	supName=(String)session.getAttribute("loginName");
                }
                ArrayList empSearchList = remote.searchEmpBySearchCriteria(fname, lname, empId,supName);
                ArrayList activeEmpIdList = remote.getActiveUserEmpIds();
                request.setAttribute("fName", fname);
                request.setAttribute("lName", lname);
                request.setAttribute("empId", empId);    
                request.setAttribute("supName", supName);  
                request.setAttribute("empSearchList",empSearchList);
                request.setAttribute("activeEmpIdList",activeEmpIdList);
                
                
                return mapping.findForward("empSearchList");
            }
//============================================================================================
        } catch(Exception e){
            //Debug.print("Exception occurs while calling memeber details::::" + e.getMessage());
            e.printStackTrace();
        }
        return null;
        
    }
}
