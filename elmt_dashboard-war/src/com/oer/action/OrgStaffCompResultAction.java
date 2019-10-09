/*
 * orgStaffCompResultAction.java
 *
 * Created on December 3, 2007, 2:25 PM
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
 * @author Vidhya
 * @version
 */
public class OrgStaffCompResultAction extends Action {
    

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
            HLCMembershipQualificationMatrixRemote qremote = qhome.create();
            String cmd = request.getParameter("cmd");
            Debug.print("cmd function:" + cmd);
            HLCCompResultVO compVO= new HLCCompResultVO();
            if(cmd!=null){
                
            if(cmd.equals("initOrgResultList")){
                    Debug.print("cmd in orgStaffCompResultAction :"+cmd);
                    return mapping.findForward("frmOEROrgnazierCompResList");         
            }
            else if(cmd.equals("initEventTitle")){
                    Debug.print("cmd in orgStaffCompResultAction :"+cmd);
                    String year=request.getParameter("year");
                    Debug.print("year :"+year);
                    String organizerId=(String)session.getAttribute("memberId");
                    ArrayList eventTitles=new ArrayList();
                    eventTitles=qremote.getEventTiles(year,organizerId);
                    request.setAttribute("eventTitles",eventTitles);
                    request.setAttribute("year",year);
                    Debug.print("cmd in AddPriceAction :"+cmd);
                    return mapping.findForward("frmOEROrgnazierCompResList");
            }
            else if(cmd.equals("initStaffResultList")){
                    Debug.print("cmd in orgStaffCompResultAction :"+cmd);
                    return mapping.findForward("frmOERStaffCompResList");         
            }
            else if(cmd.equals("initAllEventTitle")){
                    Debug.print("cmd in orgStaffCompResultAction :"+cmd);
                    String year=request.getParameter("year");
                    int tempYear=0;
                    if(year!=null && year.trim().length()!=0){
                    tempYear=Integer.parseInt(year);    
                    }
                    Debug.print("year :"+year);
                    ArrayList eventTitles=new ArrayList();
                    eventTitles=qremote.getAllEventTiles(tempYear);
                    request.setAttribute("eventTitles",eventTitles);
                    request.setAttribute("year",year);
                    Debug.print("cmd in AddPriceAction :"+cmd);
                    return mapping.findForward("frmOERStaffCompResList");
            }
            else if(cmd.equals("orgResultList")){
                    Debug.print("cmd in orgStaffCompResultAction :"+cmd);
                    String year = request.getParameter("year");
                    int tempYear=Integer.parseInt(year);
                    String eventId=request.getParameter("selTitle"); 
                    String organizerId=(String)session.getAttribute("memberId");
                                 
                    ArrayList eventTitles=new ArrayList();
                    ArrayList eventCompResList=new ArrayList();  
                    
                    eventTitles=qremote.getEventTiles(year,organizerId);                                                       
                    eventCompResList=qremote.getCompResultList(eventId,tempYear);
                    
                    request.setAttribute("eventTitles",eventTitles);
                    request.setAttribute("year",year);
                    request.setAttribute("tempeventId",eventId);                  
                    request.setAttribute("eventCompResList",eventCompResList);
                    return mapping.findForward("frmOEROrgnazierCompResList");
                 
            }
            else if(cmd.equals("staffResultList")){
                    Debug.print("cmd in orgStaffCompResultAction :"+cmd);
                    String year = request.getParameter("year");                    
                    String eventId=request.getParameter("selTitle"); 
                    int tempYear=0;
                    if(year!=null && year.trim().length()!=0){
                    tempYear=Integer.parseInt(year);    
                    }         
                    ArrayList eventTitles=new ArrayList();
                    ArrayList eventCompResList=new ArrayList();  
                    
                    eventTitles=qremote.getAllEventTiles(tempYear);                                                       
                    eventCompResList=qremote.getCompResultList(eventId,tempYear);
                    
                    request.setAttribute("eventTitles",eventTitles);
                    request.setAttribute("year",year);
                    request.setAttribute("tempeventId",eventId);                  
                    request.setAttribute("eventCompResList",eventCompResList);
                    return mapping.findForward("frmOERStaffCompResList");
                 
            }
            
            }
   }catch(Exception e){
   e.printStackTrace();    
   }
   return null;         
  }       
}    
