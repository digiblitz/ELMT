/*
 * EventRegListing.java
 *
 * Created on November 2, 2007, 6:37 PM
 */

package com.mee.action;


import com.hlcmro.display.HLCVaigaiStatelessRemote;
import com.hlcmro.display.HLCVaigaiStatelessRemoteHome;
import com.hlcmro.util.HLCEventRequestVO;
import com.hlcutil.Debug;
import java.text.SimpleDateFormat;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import com.hlcmro.org.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author Ganapathy
 * @version
 */

public class EventRegListing extends Action {
    
    /* forward name="success" path="" */
    //private final static String SUCCESS = "errorPage";
    
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
        HttpSession session = request.getSession();
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        String jndiname=mr.getMessage("jndi.vaigaisession");
        Object objref = jndiContext.lookup(jndiname);
        
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();
        
        String jndiname2=mr.getMessage("jndi.vaigaisless");
        Object objref2 = jndiContext.lookup(jndiname2);
        
        HLCVaigaiStatelessRemoteHome home2 =(HLCVaigaiStatelessRemoteHome) PortableRemoteObject.narrow(objref2, HLCVaigaiStatelessRemoteHome.class);
        HLCVaigaiStatelessRemote remote2 = home2.create();
        
        String cmd = request.getParameter("cmd");
        if(cmd!=null && cmd.trim().length()!=0){
            if(cmd.equalsIgnoreCase("eventReg")){
                return mapping.findForward("toEventRegList");
            } else if(cmd.equalsIgnoreCase("eventList")){
                String liststatus = request.getParameter("selStatus");
                String year = request.getParameter("year");
                int calYear =0 ;
                if(year != null && year.trim().length()!=0){
                    calYear = Integer.parseInt(year);
                }
                String memberId = (String)session.getAttribute("memberId");
                ArrayList eventRegList = new ArrayList();
                if(liststatus!=null && liststatus.trim().length()!=0 && calYear!=0 && memberId !=null && memberId.trim().length()!=0){
                    eventRegList = remote.getAreaChairDetails(liststatus,calYear,memberId);
                }
                request.setAttribute("EVENT_REG_LIST",eventRegList);
                request.setAttribute("status",liststatus);
                request.setAttribute("year",year);
                return mapping.findForward("toEventRegList");
            } else if(cmd.equalsIgnoreCase("singleEventDetail")){
                String eventId = request.getParameter("eventId");
                Debug.print("eventId in servlet singleEventDetail:"+eventId);
                String type = request.getParameter("type");
                HLCEventRequestVO eventReqVO = new HLCEventRequestVO();
                ArrayList areaDetails = new ArrayList();
                if(eventId!= null && eventId.trim().length()!=0){
                    eventReqVO = remote.getEventRequestDetails(eventId);
                    areaDetails = remote.getZipcodeFromToOnStateId(eventReqVO.getCompetition_state(),eventReqVO.getCompetition_zip());
                }
                Debug.print("EventReqVO returned:"+eventReqVO.toString());
                
                ArrayList stateDetails = new ArrayList();
                ArrayList issueDetails = new ArrayList();
                Vector allTypesVect = new Vector();
                
                
                stateDetails = remote.getAllStates();
                allTypesVect = remote2.getAllMapTyLe();
                issueDetails = remote.getAllIssues();
                Debug.print("ArrayList Returned:"+issueDetails.toString());
                //Debug.print("Vector Returned:"+allTypesVect.toString());
                //Debug.print("ArrayList Returned:"+stateDetails.toString());
                Vector eventTypeDetails = new Vector();
                HashMap hm = new HashMap();
                HashMap eventTypeMap = new HashMap();
                ArrayList subLevel = new ArrayList();
                
                if(allTypesVect!=null ){
                    Enumeration e = allTypesVect.elements();
                    while(e.hasMoreElements()){
                        String[] typeDetails = (String[])e.nextElement();
                        String mapId = typeDetails[0];
                        String typeId = typeDetails[1];
                        String levelId = typeDetails[2];
                        String typeName = typeDetails[3];
                        String levelCode = typeDetails[4];
                        String levelName = typeDetails[5];
                        if(hm.containsKey(typeId)){
                            String[] subEntry = {mapId,levelId,levelCode,levelName};
                            subLevel.add(subEntry);
                            eventTypeMap.put(typeId,subLevel);
                        }else{
                            subLevel = new ArrayList();
                            String[] subEntry = {mapId,levelId,levelCode,levelName};
                            subLevel.add(subEntry);
                        }
                        hm.put(typeId,typeName);
                        
                    }
                    Set set = hm.keySet();
                    Iterator itr = set.iterator();
                    while(itr.hasNext()){
                        String keyId = (String)itr.next();
                        String valueName = (String)hm.get(keyId);
                        String[] eventTypeDet = {keyId,valueName};
                        eventTypeDetails.add(eventTypeDet);
                    }
                }
                Iterator arItr = areaDetails.iterator();
                if(arItr.hasNext()){
                    String[] areaDet = (String[])arItr.next();
                    request.setAttribute("AREA_DETAILS",areaDet);
                }
                request.setAttribute("EVENT_REQUEST_VO",eventReqVO);
                request.setAttribute("issueDetails",issueDetails);
                request.setAttribute("stateDetails",stateDetails);
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("eventTypeMap",eventTypeMap);
                request.setAttribute("eventId",eventId);
                if(type!=null && type.equalsIgnoreCase("Edit")){
                    return mapping.findForward("toRequestRegEdit");
                }else if(type!=null && type.equalsIgnoreCase("Approve")){
                    return mapping.findForward("toRequestApprove");
                }
               
            } else if(cmd.equalsIgnoreCase("updateEventRequestReg")){
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                HLCEventRequestVO eventReqVO = new HLCEventRequestVO();
                String eventId = request.getParameter("eventId");
                Debug.print("Event Id: in updateEventRequestReg: "+eventId);
                eventReqVO.setEvent_id(eventId);
                String eventTitle = request.getParameter("eventTitle");
                Debug.print("Event Title:"+eventTitle);
                if(eventTitle!=null && eventTitle.trim().length()!=0){
                    eventReqVO.setEvent_title(eventTitle);
                }
                
                eventReqVO.setCompetition_country("USA");
                
                String state = request.getParameter("stateId");
                Debug.print("State Id:"+state);
                if(state!=null && state.trim().length()!=0){
                    eventReqVO.setCompetition_state(state);
                }else{
                    eventReqVO.setCompetition_state(null);
                }
                
                String zipCode = request.getParameter("zipCode");
                Debug.print("Zip Code:"+zipCode);
                if(zipCode!=null && zipCode.trim().length()!=0){
                    eventReqVO.setCompetition_zip(zipCode);
                }else{
                    eventReqVO.setCompetition_zip(null);
                }
                
                String beginDate = request.getParameter("beginDate");
                Date startDate=null;
                
                if(beginDate!=null && beginDate.trim().length()!=0){
                    startDate=(Date)sdf.parse(beginDate);
                    Debug.print("startDate in servlet"+startDate);
                }
                String endDate = request.getParameter("endDate");
                Debug.print("End Date :"+endDate);
                Date evendDate=null;
                if(endDate!=null && endDate.trim().length()!=0){
                    evendDate=(Date)sdf.parse(endDate);
                }
                
                if(startDate!=null ){
                    eventReqVO.setEveBegDate(startDate);
                }else{
                    eventReqVO.setEveBegDate(null);
                }
                if(evendDate!=null ){
                    eventReqVO.setEveEndDate(evendDate);
                }else{
                    eventReqVO.setEveEndDate(null);
                }
                String organizerId = request.getParameter("orgnaizerId");
                Debug.print("Orgnaizer Id:"+organizerId);
                if(organizerId!=null && organizerId.trim().length()!=0){
                    eventReqVO.setOrganizer_id(organizerId);
                }else{
                    eventReqVO.setOrganizer_id(null);
                }
                
                String location = request.getParameter("txtLocation");
                Debug.print("Location :"+location);
                if(location!=null && location.trim().length()!=0){
                    eventReqVO.setCompetition_location(location);
                }else{
                    eventReqVO.setCompetition_location(null);
                }
                
                String city = request.getParameter("txtCity");
                Debug.print("City :"+city);
                if(city!= null && city.trim().length()!=0){
                    eventReqVO.setCompetition_city(city);
                }else{
                    eventReqVO.setCompetition_city(null);
                }
                String issName = request.getParameter("selIssue");
                Debug.print("issName :"+issName);
                if(issName!= null && issName.trim().length()!=0){
                eventReqVO.setIssueId(issName);
                }else{
                eventReqVO.setIssueId(null);
                }               
                String area = request.getParameter("areaId");
                Debug.print("Area :"+area);
                if(area!=null && area.trim().length()!=0){
                    eventReqVO.setChampionship_area(area);
                }else{
                    eventReqVO.setChampionship_area(null);
                }
                eventReqVO.setStatus_id("Pending");
                String levelCount = request.getParameter("levelCount");
                int count = 0;
                if(levelCount != null && levelCount.trim().length()!=0){
                    count = Integer.parseInt(levelCount);
                }
                ArrayList mappingTypeIds = new ArrayList();
                for(int i = 1 ; i <=count ;i++){
                    String eventLevelMapId = request.getParameter("eventLevels"+i);
                    if(eventLevelMapId != null && eventLevelMapId.trim().length()!=0 ){
                        Debug.print("Selected Event Sub Level Id:"+eventLevelMapId);
                        mappingTypeIds.add(eventLevelMapId);
                    }
                }
                if(mappingTypeIds!=null && mappingTypeIds.size()!=0){
                    eventReqVO.setMaping_type_id(mappingTypeIds);
                }else{
                    eventReqVO.setMaping_type_id(null);
                }
                
                String eveId = remote.updateEventRequest(eventReqVO);
                boolean updateResult=false;
                if(eveId!=null && eveId.trim().length()!=0){
                    HLCEventRequestVO eventVO = new HLCEventRequestVO();
                        eventVO = remote.getEventRequestDetails(eveId);
                        Debug.print("eveId in updateEventHistoryDets condition:"+eveId);
                        String eveId1=eventVO.getEvent_id();
                        String eveTitle=eventVO.getEvent_title();
                        String orgId=eventVO.getOrganizer_id();
                        Date begDate=eventVO.getEveBegDate();                       
                        String tempDt =(begDate.toString());
                        String tempYr = tempDt.substring(0,4);
                        Debug.print("tempYr in EventRegListing Servlet"+tempYr);
                        int compYr = Integer.parseInt(tempYr); 
                        Debug.print("compYr in EventRegListing Servlet"+compYr);
                        updateResult = remote.updateEventHistoryDets(eventVO,compYr); 
                } 
                //if(updateResult){
                    //Redirects to Event Request Registration List page on Success
                    request.setAttribute("updateStatus","updateSuccess");
                    return mapping.findForward("EventRegEditSuccess");
                //}else{
                    //request.setAttribute("updateStatus","updateFailed");
                    //return mapping.findForward("EventRegEditSuccess");
                //}
           
            }else if(cmd.equalsIgnoreCase("viewNewEventReg")){
                String eventId = request.getParameter("eventId");
                String type = request.getParameter("type");
                HLCEventRequestVO eventReqVO = new HLCEventRequestVO();
                ArrayList areaDetails = new ArrayList();
                if(eventId!= null && eventId.trim().length()!=0){
                    eventReqVO = remote.getEventRequestDetails(eventId);
                    areaDetails = remote.getZipcodeFromToOnStateId(eventReqVO.getCompetition_state(),eventReqVO.getCompetition_zip());
                }
                Debug.print("EventReqVO returned:"+eventReqVO.toString());
                ArrayList stateDetails = new ArrayList();
                Vector allTypesVect = new Vector();
                stateDetails = remote.getAllStates();
                allTypesVect = remote2.getAllMapTyLe();
                Vector eventTypeDetails = new Vector();
                HashMap hm = new HashMap();
                HashMap eventTypeMap = new HashMap();
                ArrayList subLevel = new ArrayList();
                if(allTypesVect!=null ){
                    Enumeration e = allTypesVect.elements();
                    while(e.hasMoreElements()){
                        String[] typeDetails = (String[])e.nextElement();
                        String mapId = typeDetails[0];
                        String typeId = typeDetails[1];
                        String levelId = typeDetails[2];
                        String typeName = typeDetails[3];
                        String levelCode = typeDetails[4];
                        String levelName = typeDetails[5];
                        if(hm.containsKey(typeId)){
                            String[] subEntry = {mapId,levelId,levelCode,levelName};
                            subLevel.add(subEntry);
                            eventTypeMap.put(typeId,subLevel);
                        }else{
                            subLevel = new ArrayList();
                            String[] subEntry = {mapId,levelId,levelCode,levelName};
                            subLevel.add(subEntry);
                        }
                        hm.put(typeId,typeName);
                    }
                    Set set = hm.keySet();
                    Iterator itr = set.iterator();
                    while(itr.hasNext()){
                        String keyId = (String)itr.next();
                        String valueName = (String)hm.get(keyId);
                        String[] eventTypeDet = {keyId,valueName};
                        eventTypeDetails.add(eventTypeDet);
                    }
                }
                Iterator arItr = areaDetails.iterator();
                if(arItr.hasNext()){
                    String[] areaDet = (String[])arItr.next();
                    request.setAttribute("AREA_DETAILS",areaDet);
                }
                request.setAttribute("EVENT_REQUEST_VO",eventReqVO);
                request.setAttribute("stateDetails",stateDetails);
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("eventTypeMap",eventTypeMap);
                return mapping.findForward("frmViewNewEventReg");
                
            }
            
            else if(cmd.equalsIgnoreCase("approveEventRequestReg")){
                String eventId = request.getParameter("eventId");
                Debug.print("eventId in approveEventRequestReg :"+eventId);
                String appStatus = request.getParameter("selStatus");
                Debug.print("status in approveEventRequestReg :"+appStatus);
                boolean approveResult = false;
                if(eventId!= null && eventId.trim().length()!=0 && appStatus!=null && appStatus.trim().length()!=0){
                    approveResult = remote.changeEventRequestStatus(eventId,appStatus);
                }
                Debug.print("Approval Status:"+approveResult);
                //Redirects to Success page
                if(approveResult){
                    request.setAttribute("approveStatus","approveSuccess");
                    return mapping.findForward("EventRegApproveSuccess");
                }else{
                    request.setAttribute("approveStatus","approveFailed");
                    return mapping.findForward("EventRegApproveSuccess");
                }                
            }
           else if(cmd.equalsIgnoreCase("initUserList")){
                Debug.print("cmd in EventRegListing servlet "+cmd);
                return mapping.findForward("frmMeeUserNewEveRegListing");
            }
            else if(cmd.equalsIgnoreCase("userListDetails")){
               String status=request.getParameter("selStatus");
                Debug.print("status in userListDetails" +status); 
               String tempUserId = (String) session.getAttribute("userId");
               Debug.print("tempUserId in reqInsert" +tempUserId);
               ArrayList userRegList= new ArrayList();
            if(tempUserId!=null && tempUserId.trim().length()!=0 && status!=null && status.trim().length()!=0){
                  userRegList=remote.getUserEveRegDetails(tempUserId,status);                                                    
            }
               request.setAttribute("status",status);
               request.setAttribute("userRegList",userRegList);
               return mapping.findForward("frmMeeUserNewEveRegListing");
            } 
            
        }
    }
        catch( Exception e ){
            e.printStackTrace();
        }
        return mapping.findForward("errorPage");
         
    }
}
