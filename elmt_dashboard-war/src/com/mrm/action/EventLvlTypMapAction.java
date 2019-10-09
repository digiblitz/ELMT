/*
 * EventLvlTypMapAction.java
 *
 * Created on April 10, 2007, 1:10 PM
 */

package com.mrm.action;

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcmro.display.*;
import com.hlcmro.org.*; 
import com.hlccommon.util.Debug;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author Hari
 * @version
 */

public class EventLvlTypMapAction extends Action {

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

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            String process = request.getParameter("process");
            Debug.print("Process in Servlet " + process);
            
            /*
             *Mapping event Level to codes
             */
            if(process.equalsIgnoreCase("ListEvntLevel")){
                Debug.print("Listing the Mapping Event to Level Codes");
                Vector allTypesVect = (Vector)vaigaiRemote.getAllTypes();
                Vector allLvlsVect = (Vector)vaigaiRemote.getAllLevels();
                request.setAttribute("allTypesVect",allTypesVect);
                request.setAttribute("allLvlsVect",allLvlsVect);
                request.setAttribute("eventTypeId",null);
                return mapping.findForward("lvlmap");
            }
            
            /*
             * Selecting the appropriate Mapping Information to particular event Level
             */
            if(process.equalsIgnoreCase("MapEvntLevel")){
                Debug.print("Mapping Event to Level Codes");
                try{
                    String eventTypeId = request.getParameter("eventTypeId");
                    Debug.print("Event Type ID "+eventTypeId);
                    if(eventTypeId!=null || eventTypeId.trim().length()!=0){
                        ArrayList eventLevelList = horseremote.getMappingDetailsForEventTypeAndEventLevels(eventTypeId);
                        Vector allTypesVect = (Vector)vaigaiRemote.getAllTypes();
                        Vector allLvlsVect = (Vector)vaigaiRemote.getAllLevels();
                        request.setAttribute("allTypesVect",allTypesVect);
                        request.setAttribute("allLvlsVect",allLvlsVect);                
                        request.setAttribute("eventLevelList",eventLevelList);
                        request.setAttribute("eventTypeId",eventTypeId);
                        return mapping.findForward("lvlmap");                                    
                    }
                }
                catch(Exception eDisp){
                        Debug.print("while getting roleEntSelect:" + eDisp);
                }
            }
            
             /*
             * Update Mapping Information mapping to particular event Level
             */
            if(process.equalsIgnoreCase("insertEvntLevel")){
                Debug.print("Updating the Mapping Event Type to Level Codes");
                ArrayList eventLevelList = new ArrayList();
                String eventTypeId = request.getParameter("eventTypeId");
                Debug.print("Event Type ID "+eventTypeId);

                String entityIds = request.getParameter("entityIds");
                Debug.print("entityIds:" + entityIds);
                StringTokenizer strTkns = new StringTokenizer(entityIds,"#");
                boolean result = false;
                
                while(strTkns.hasMoreTokens()){
                    try{
                        String entityId = (String)strTkns.nextToken();
                        if(entityId!=null && entityId.trim().length()!=0){
                            Debug.print("ActionRoleMangement.mappingRoleEnt() Added from Stokenizer:" + entityId);
                            eventLevelList.add(entityId);
                        }
                    }
                    catch(Exception e){
                        Debug.print("Exception while spliting privilegeIds ActionRoleMangement.mappingRoleEnt() :" + e);
                    }
                }
                if(eventTypeId!=null || eventTypeId.trim().length()!=0){
                    boolean insert_map = horseremote.generateMappingEventTypeWithEventLevels(eventTypeId,eventLevelList);
                    Debug.print("Mapping Insert Result "+insert_map);                   
                    if(insert_map==true){
                        Debug.print("ActionRoleMangement.mappingRoleEnt() sucessfully comes from servlet.");
                        Vector allTypesVect = (Vector)vaigaiRemote.getAllTypes();
                        Vector allLvlsVect = (Vector)vaigaiRemote.getAllLevels();
                        eventLevelList = horseremote.getMappingDetailsForEventTypeAndEventLevels(eventTypeId);
                        request.setAttribute("allTypesVect",allTypesVect);
                        request.setAttribute("allLvlsVect",allLvlsVect); 
                        request.setAttribute("eventTypeId",eventTypeId);
                        request.setAttribute("eventLevelList",eventLevelList);
                        return mapping.findForward("lvlmap");
                    }else{
                       Debug.print("ActionRoleMangement.mappingRoleEnt() sucessfully comes from servlet.");
                        Vector allTypesVect = (Vector)vaigaiRemote.getAllTypes();
                        Vector allLvlsVect = (Vector)vaigaiRemote.getAllLevels();
                        eventLevelList = horseremote.getMappingDetailsForEventTypeAndEventLevels(eventTypeId);
                        request.setAttribute("allTypesVect",allTypesVect);
                        request.setAttribute("allLvlsVect",allLvlsVect); 
                        request.setAttribute("eventTypeId",eventTypeId);
                        request.setAttribute("eventLevelList",eventLevelList);
                        return mapping.findForward("lvlmap"); 
                    }
                    
                }
            }
          } 
         catch(Exception e){
            System.out.println("Exception in MemEventLevelMapAction Action: " + e.toString());
        }
        return null;
      
    }
}


