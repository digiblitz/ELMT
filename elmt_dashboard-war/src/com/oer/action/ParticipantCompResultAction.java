/*
 * ParticipantCompResultAction.java
 *
 * Created on November 29, 2007, 11:35 AM
 */

package com.oer.action;

import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemote;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemoteHome;
import com.hlcutil.HLCCompResultVO;
import com.hlcutil.Debug;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.*;
import java.util.regex.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author Dhivya
 * @version
 */

public class ParticipantCompResultAction extends Action {
    
  
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
       try{
            HttpSession session=request.getSession();
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiName=mr.getMessage("jndi.mqm");
            String jndiname=mr.getMessage("jndi.pc");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
           
            Object qualObj = jndiContext.lookup(jndiName);
            HLCMembershipQualificationMatrixRemoteHome qhome = (HLCMembershipQualificationMatrixRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(qualObj,HLCMembershipQualificationMatrixRemoteHome.class);
            HLCMembershipQualificationMatrixRemote partiResRemote = qhome.create();
            String cmd = request.getParameter("cmd");
            Debug.print("cmd function:" + cmd);
            //String filePath=mr.getMessage("horseComp_uploadPath");
            HLCCompResultVO compVO= new HLCCompResultVO();
            if(cmd!=null){
                
           if(cmd.equals("initResultList")){
                Debug.print("cmd in ParticipantCompResultAction :"+cmd);
                return mapping.findForward("frmOERParticipantCompResList");         
           }
           else if(cmd.equals("eveTitleList")){               
                    Debug.print("cmd in ParticipantCompResultAction :"+cmd);
                    ArrayList myCompRegDetails=new ArrayList();
                    String year = request.getParameter("year");
                    Debug.print("year in Servlet:"+year);
                    int tempYear=Integer.parseInt(year);
                    Debug.print("tempYear in ParticipantCompRegListAction:"+tempYear);
                    Debug.print("year in ParticipantCompRegListAction.participantList :"+year);
                    String riderUserId=(String)session.getAttribute("userId");
                    myCompRegDetails=partiResRemote.getMyEventTiles(tempYear,riderUserId);
                     String tempEveTypName="";
                     String tempeventId="";
                       
                    request.setAttribute("year",year);
                    request.setAttribute("tempeventId",tempeventId);
                    request.setAttribute("myCompRegDetails",myCompRegDetails);
                    return mapping.findForward("frmOERParticipantCompResList");               
           }
           else if(cmd.equals("resultList")){
               Debug.print("cmd in ParticipantCompResultAction :"+cmd);
                    String year = request.getParameter("year");
                    Debug.print("year in Servlet:"+year);
                    int tempYear=Integer.parseInt(year);
                    Debug.print("tempYear in ParticipantCompRegListAction:"+tempYear);
                    Debug.print("year in ParticipantCompRegListAction.resultList :"+year);
                    String tempeventId=request.getParameter("selTitle"); 
                    Debug.print("tempeventId in ParticipantCompRegListAction.resultList :"+tempeventId);
                    String riderUserId=(String)session.getAttribute("userId");   
                                     
                    ArrayList myCompRegDetails=new ArrayList();
                    ArrayList eventcompResList=new ArrayList();                                
                    myCompRegDetails=partiResRemote.getMyEventTiles(tempYear,riderUserId);                                                       
                    eventcompResList=partiResRemote.getMyCompResultList(riderUserId,tempeventId,tempYear);
                    Debug.print("list in ParticipantCompResultAction.resultList :"+eventcompResList);
                    
                    request.setAttribute("myCompRegDetails",myCompRegDetails);
                    request.setAttribute("year",year);
                    request.setAttribute("tempeventId",tempeventId);                  
                    request.setAttribute("eventcompResList",eventcompResList);
                    return mapping.findForward("frmOERParticipantCompResList");   
           } 
           
           else if(cmd!=null && cmd.equalsIgnoreCase("myCompResultView")){
                    Debug.print("ParticipantCompRegListAction calling Process:myCompRegView...."); 
                     HLCCompResultVO objRegVO= new HLCCompResultVO();
                    String compResultId = request.getParameter("compResultId");
                    Debug.print("compResultId in ParticipantCompResultAction :"+compResultId);
                    
                    if(compResultId!=null && compResultId.trim().length()!=0){
                        objRegVO = partiResRemote.getMyCompResultView(compResultId);
                        Debug.print("objRegVO in ParticipantCompResultAction :"+objRegVO);
                    }
                    request.setAttribute("objRegVO",objRegVO);
                    return mapping.findForward("frmOERParticipantCompResView");
                }
     
            }
       }catch(Exception e){           
           Debug.print("Exception in ParticipantCompResultAction: "+e);           
       }
       return null;
    }
}
