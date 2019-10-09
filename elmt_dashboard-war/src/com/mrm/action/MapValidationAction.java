/*
 * Program Name     :   MapValidationAction.java
 * Created Date     :   Aprile 22, 2007, 10:55 AM
 * Author           :   punitha
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.15
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */

package com.mrm.action;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcmeeting.util.HLCEventCompQualDetVO;
import com.hlcmro.display.*;
import com.hlcmro.org.*;
import com.hlccommon.util.Debug;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

public class MapValidationAction extends Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try {
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            
            Context jndiContext = new InitialContext();
            
            HttpSession session=request.getSession();
            
            
            
            String jndiname1=mr.getMessage("jndi.kaverysless");
            Object objref1 = jndiContext.lookup(jndiname1);
            HLCkaverySessionBeanStatlessRemoteHome horsehome =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote horseremote = horsehome.create();
            
            String jndiname=mr.getMessage("jndi.vaigaisless");
            Object objref = jndiContext.lookup(jndiname);
            HLCVaigaiStatelessRemoteHome vaigaiHome =(HLCVaigaiStatelessRemoteHome)
            PortableRemoteObject.narrow(objref, HLCVaigaiStatelessRemoteHome.class);
            HLCVaigaiStatelessRemote vaigaiRemote = vaigaiHome.create();
            
            Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            
            String meejndiname=mr.getMessage("jndi.icp");
           
            Object meeobjref = jndiContext.lookup(meejndiname);
            com.hlcmeeting.session.HLCVaigaiSessionRemoteHome meehome = (com.hlcmeeting.session.HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(meeobjref,com.hlcmeeting.session.HLCVaigaiSessionRemoteHome.class);
            com.hlcmeeting.session.HLCVaigaiSessionRemote meeremote = meehome.create();

            
            String process = request.getParameter("process");
            Debug.print("Process in Servlet " + process);
            
            if(process!=null && process.equalsIgnoreCase("newMapValidate")) {
                Debug.print("MemEventLevelMapAction of :" +process+ "calling................. ");
                Vector allDivisionsVect = (Vector)remote.listAllEventDivision(true);
                request.setAttribute("allDivisionsVect", allDivisionsVect);
                Vector allLevelsVect = (Vector)vaigaiRemote.getAllLevels();
                request.setAttribute("allLevelsVect", allLevelsVect);
                Vector allTypesVect = (Vector)vaigaiRemote.getAllTypes();
                request.setAttribute("allTypesVect", allTypesVect);
                Debug.print("Sucessfully comes from Admin List");
                return mapping.findForward("newMapValidate");
            }
            if(process!=null && process.equalsIgnoreCase("insertMapValidate")) {
                Debug.print("MemEventLevelMapAction of :" +process+ "calling................. ");
                String eventTypeId = request.getParameter("eventTypeId");
                String evnetLevelId = request.getParameter("eventLevelId");
                String horseAge = request.getParameter("riderAge");
                String riderAge = request.getParameter("horseAge");
                String divisionCount = request.getParameter("divisionCount");
                
                String horseRank = request.getParameter("horseRank");
                String amateurStatus = request.getParameter("amateurStatus");
                
                boolean amatStat = false;
                
                if(amateurStatus.equalsIgnoreCase("Yes"))                    
                {
                    amatStat = true;
                }
                
                Debug.print("eventTypeId in MapValidation Action:::::::::::" + eventTypeId);
                Debug.print("evnetLevelId in MapValidation Action:::::::::::" + evnetLevelId);
                Debug.print("horseAge in MapValidation Action:::::::::::" + horseAge);
                Debug.print("riderAge in MapValidation Action:::::::::::" + riderAge);
                Debug.print("divisionCount in MapValidation Action:::::::::::" + divisionCount);
                Debug.print("horseRank in MapValidation Action:::::::::::" + horseRank);
                Debug.print("amateurStatus in MapValidation Action:::::::::::" + amateurStatus);
                
                String divisionId = "";
                String ageFrom ="";
                String ageTo ="";
                int cnt = 0;
                HLCEventCompQualDetVO CompQualDetail = new HLCEventCompQualDetVO();
                if(divisionCount!=null && divisionCount.trim().length()!=0){
                    cnt = Integer.parseInt(divisionCount);
                }
                for(int i=1; i<=cnt; i++){
                    divisionId = request.getParameter("divisionName" + i);
                    ageFrom = request.getParameter("ageFrom" + i);
                    ageTo = request.getParameter("ageTo" + i);
  
                    Debug.print("DivisionId in MapValidation Action::::::::" + divisionId);
                    Debug.print("ageFrom in MapValidation Action:::::::::::" + ageFrom);
                    Debug.print("ageTo in MapValidation Action:::::::::::::" + ageTo);
                    
                    if(divisionId!=null && divisionId.trim().length()!=0 &&
                            ageFrom!=null && ageFrom.trim().length()!=0 &&
                            ageTo!=null && ageTo.trim().length()!=0 ){
                        CompQualDetail.setEvent_type(eventTypeId);
                        CompQualDetail.setEvent_level(evnetLevelId);
                        CompQualDetail.setHorse_age(horseAge);
                        CompQualDetail.setRider_age(riderAge);
                        CompQualDetail.setEvent_division(divisionId);
                        CompQualDetail.setAge_from(ageFrom);
                        CompQualDetail.setAge_to(ageTo);
                        
                        CompQualDetail.setHorse_rank(horseRank);
                        CompQualDetail.setAmateur_status(amatStat);
                        
                        boolean status = meeremote.insertEventCompQualDetails(CompQualDetail);
                        Debug.print("status" + status);
                    }
                }
                Debug.print("cnt" + cnt);
                return mapping.findForward("successMapValidate");
            }
            
            
            
        } catch(Exception e){
            System.out.println("Exception in MemEventLevelMapAction Action: " + e.toString());
        }
        return null;
    }
}