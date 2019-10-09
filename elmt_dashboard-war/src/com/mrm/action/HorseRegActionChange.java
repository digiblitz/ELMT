/*
 * HorseRegActionChange.java
 *
 * Created on February 8, 2007, 12:34 PM
 */

package com.mrm.action;

import com.hlccommon.util.HLCHorseUserVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlccommon.util.HLCHorseRegisterationVO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.HttpSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.hlccommon.util.Debug;

/**
 *
 * @author hari
 * @version
 */

public class HorseRegActionChange extends Action {

    private final static String SUCCESS = "success";
    

    public ActionForward execute(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response) 
                                                                                                throws Exception {
        String name="ejb/HLCKaveryJNDI";
            try{
                    MessageResources mr=getResources(request);
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Debug.print("ActionMessage.jndiname:" + name);
                    Object objref = jndiContext.lookup(name);

                    HttpSession session=request.getSession();

                    HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
                    HLCKaverySessionBeanStatfulRemote remote = home.create(); 

                    String process = (String) request.getParameter("process");
                    Debug.print("Process in Servlet is "+process);
            //Display the details
                    if(process.equalsIgnoreCase("dispdet")){
                        HLCHorseRegisterationVO HorseDet = null;
                        String horseMemberId = (String)request.getParameter("horseMemberId");
                        HorseDet = (HLCHorseRegisterationVO)remote.getHorseDetailsByHorseMemberId(horseMemberId);
                        request.setAttribute("HorseDet",HorseDet);
                        return mapping.findForward("ChngRider");
                    }
            //Update the Details        
                    if(process.equalsIgnoreCase("updateDet")){
                        String horseMemberId = (String) request.getParameter("horseMemberId");
                        String horseName = (String) request.getParameter("horseName");
                        String horseRegisterName = (String) request.getParameter("horseRegisterName");
                        String requestBy = (String)session.getAttribute("userId");
                        String riderId = (String) request.getParameter("riderId");
                        String ownerId = (String) request.getParameter("ownerId");
                        String companyStatus = (String) request.getParameter("companyStatus");
                        boolean stat = false;
                        if(companyStatus.equalsIgnoreCase("true")){
                            stat=true;
                        }
                        HLCHorseUserVO objMainOwner = (HLCHorseUserVO) request.getAttribute("objMainOwner");                    
                       // remote.editHorseRiderAndOwnerDetails(horseMemberId,horseName,horseRegisterName,requestBy,riderId,ownerId,objMainOwner, stat);
                    }
            }
            catch(Exception einsert){
                    System.out.println("RegHorseListing Action Error " + einsert.toString());
            }
        return null;
}
}
