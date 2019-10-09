/*
 * OfficialsDetAction.java
 *
 * Created on December 20, 2006, 1:01 PM
 */

package com.mee.action.master;

import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hlcmro.org.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlccommon.util.Debug;
import javax.naming.Context;
import javax.naming.InitialContext;
/**
 *
 * @author hari
 * @version
 */

public class OfficialsDetAction extends Action {
    
   private final static String SUCCESS = "success";
    
   
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");

            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();

            Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();   

            String offProcess= (String) request.getParameter("offProcess");
            Debug.print("Process in Servlet "+offProcess);
        
            if(offProcess.equals("add")){
                Debug.print("Inside Add Of Official Details");
                return mapping.findForward("createOfficial");
            }
            if(offProcess.equals("create")){   
                Debug.print("Inside Create Process.");
                try{
                    String judgeTypeName = request.getParameter("judgeName");
                    Debug.print("Judge Name:"+judgeTypeName);
                
                    boolean result = remote.insertOficial(judgeTypeName);
                    if(result==true){
                        return mapping.findForward("addOfficial"); 
                    }
                    else {   
                        request.setAttribute("err","st");
                        return mapping.findForward("createOfficial");
                    }
                }
                catch(Exception e){
                    System.out.println("Exception while adding Judge Name" + e);
                }
            }
            if(offProcess.equals("list")){
                Debug.print("Inside List Of Divisions Details");
                boolean result = false;
                String status = (String)request.getParameter("status");
                Debug.print("status:"+status);
                if(status.equals("activate")){
                    result =true;
                Vector allTypesVect = new Vector();
                allTypesVect = remote.displayAllJudgetype(result);
                request.setAttribute("status",status);
                request.setAttribute("DispOfficialDet",allTypesVect);
                return mapping.findForward("dispOfficial");
                }
                if(status.equals("deactivate")){
                    result =false;
                Vector allTypesVect = new Vector();
                allTypesVect = remote.displayAllJudgetype(result);
                request.setAttribute("status",status);
                request.setAttribute("DispOfficialDet",allTypesVect);
                return mapping.findForward("dispOfficial");
                }
                else{
                    request.setAttribute("err","st");
                    return mapping.findForward("dispOfficial");
                }
            }
            if(offProcess.equals("Edit")){
                Debug.print("\n Inside Edit Divisions Details\n");
                String judgetypeId = request.getParameter("judgetypeId");
                String judgeName = request.getParameter("judgeTypeName");
                request.setAttribute("judgeType",judgetypeId);
                request.setAttribute("judgeName",judgeName);
                Debug.print("judge Id:"+judgetypeId);
                Debug.print("judge Name:"+judgeName);
                return mapping.findForward("editOfficial");
            }
            if(offProcess.equals("confirmEdit")){
                Debug.print("\n Inside Confirmation Edit Divisions Details\n");
                String judgetypeId = request.getParameter("judgetypeId");
                Debug.print("judge Id:"+judgetypeId);
                String judgeName = request.getParameter("judgeTypeName");
                Debug.print("judge Name:"+judgeName);
                boolean result = remote.updateOfficial(judgeName,judgetypeId);
                Debug.print("after result");
                if(result==true){
                    Debug.print("inside result");
                    return mapping.findForward("conEditOfficial");
                }
                else {
                    request.setAttribute("err","st");
                    request.setAttribute("judgeType",judgetypeId);
                    request.setAttribute("judgeName",judgeName);
                    return mapping.findForward("editOfficial");
                }
            }
            if(offProcess.equals("Deactivate")){
                Debug.print("\n Inside Edit Divisions Details\n");
                String judgetypeId = request.getParameter("judgetypeId");
                String judgeName = request.getParameter("judgeTypeName");
                request.setAttribute("judgeType",judgetypeId);
                request.setAttribute("judgeName",judgeName);
                Debug.print("judge Id:"+judgetypeId);
                Debug.print("judge Name:"+judgeName);
                return mapping.findForward("deactivateOfficial");
            }
            if(offProcess.equals("Activate")){
                Debug.print("\n Inside Edit Divisions Details\n");
                String judgetypeId = request.getParameter("judgetypeId");
                String judgeName = request.getParameter("judgeTypeName");
                request.setAttribute("judgeType",judgetypeId);
                request.setAttribute("judgeName",judgeName);
                Debug.print("judge Id:"+judgetypeId);
                Debug.print("judge Name:"+judgeName);
                return mapping.findForward("activateOfficial");
            }
            if(offProcess.equals("conDeactivateOfficial")){
                String judgetypeId = request.getParameter("judgetypeId");
                String judgeName = request.getParameter("judgeTypeName");
                boolean result = remote.deleteOfficial(judgetypeId);
                if(result==true){
                    return mapping.findForward("conDeactivateOfficial");
                }
            }
            if(offProcess.equals("conActivateOfficial")){
                String judgetypeId = request.getParameter("judgetypeId");
                String judgeName = request.getParameter("judgeTypeName");
                boolean result = remote.activateJudgeType(judgetypeId);
                if(result==true){
                    return mapping.findForward("conActivateOfficial");
                }       
            }
            return null;
        }

    private Context getInitialContext() {
        return null;
    }
}
