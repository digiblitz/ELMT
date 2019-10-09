/*
 * OEEValidationAction.java
 *
 * Created on November 6, 2007, 2:36 PM
 */

package com.oec.action;

import com.hlccommon.util.Debug;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;
import java.util.ArrayList;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hlcutil.HLCValidationVO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author Ellamaran
 * @version
 */

public class OEEValidationAction extends Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.pc");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object obj = jndiContext.lookup(jndiname);
            
            Object krishnaObj = jndiContext.lookup(jndiname);
            HLCKrishnaStatelessRemoteHome krishnaHome = (HLCKrishnaStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(krishnaObj,HLCKrishnaStatelessRemoteHome.class);
            HLCKrishnaStatelessRemote calRemote = krishnaHome.create();
            
            String cmd = request.getParameter("cmd");
            HttpSession session = request.getSession(true);
            HLCValidationVO validVO = new HLCValidationVO();
            
            if(cmd!=null && cmd.equals("initValidate")){
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                ArrayList divisionTypeDetails = new ArrayList();
                
                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                areaDetails = calRemote.getAllAreaMasters();
                divisionTypeDetails = calRemote.getDivisions();
                
                String chmpStat="";
                String areaId="";
                String divisionId="";
                
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("areaDetails",areaDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("recordExists","yes");
                request.setAttribute("chmpStat",chmpStat);
                request.setAttribute("areaId",areaId);
                request.setAttribute("divisionId",divisionId);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                
                return mapping.findForward("initInsert");
            }
            
            else if(cmd!=null && cmd.equalsIgnoreCase("selEventLevels")){
                String eventTypeId = request.getParameter("eventType");
                String userTypeId = request.getParameter("userType");
                String chmpStat=request.getParameter("chmpStatus");
                String areaId=request.getParameter("selArea");
                String divisionId=request.getParameter("division");
                String chmpStat1="";
                if(chmpStat.equalsIgnoreCase("yes")) chmpStat1="True";
                else chmpStat1="False";
                
                ArrayList divisionTypeDetails = new ArrayList();
                ArrayList membershipTypeDetails = new ArrayList();
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList eventLevelDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                
                boolean exResult = calRemote.ValidationDetailsExist(eventTypeId,userTypeId,divisionId,areaId,chmpStat1);
                if(!exResult){
                    if((eventTypeId!=null && areaId=="")||(eventTypeId!=null && areaId!=null && chmpStat.equalsIgnoreCase("no"))){
                        eventLevelDetails = calRemote.getEventLevelsBasedOnEventId(eventTypeId);
                        request.setAttribute("recordExists","yes");
                    } else if(areaId!=null && chmpStat.equalsIgnoreCase("yes")){
                        eventLevelDetails = calRemote.getEventLevelsForArea(areaId);
                        request.setAttribute("recordExists","yes");
                    }
                }else{
                    request.setAttribute("recordExists","no");
                }
                
                divisionTypeDetails = calRemote.getDivisions();
                membershipTypeDetails = calRemote.getMembershipTypes(userTypeId);
                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                areaDetails = calRemote.getAllAreaMasters();
                
                request.setAttribute("areaDetails",areaDetails);
                request.setAttribute("eventLevelDetails",eventLevelDetails);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                request.setAttribute("membershipTypeDetails",membershipTypeDetails);
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("eventTypeId",eventTypeId);
                request.setAttribute("userTypeId",userTypeId);
                request.setAttribute("chmpStat",chmpStat);
                request.setAttribute("areaId",areaId);
                request.setAttribute("divisionId",divisionId);
                return mapping.findForward("initInsert");
            }
            
            else if(cmd.equals("regLevel")){
                
                ArrayList membershipTypeDetails = new ArrayList();
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList divisionTypeDetails = new ArrayList();
                ArrayList eventLevelDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                
                String userTypeId = (String)request.getParameter("usrTyp");
                membershipTypeDetails = calRemote.getMembershipTypes(userTypeId);
                divisionTypeDetails = calRemote.getDivisions();
                String eventTypeId = request.getParameter("eventType");
                String areaId=request.getParameter("selArea");
                String divisionId=request.getParameter("division");
                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                String chmpStat=request.getParameter("chmpStatus");
                
                String chmpStat1="";
                if(chmpStat.equalsIgnoreCase("yes")){
                    chmpStat1="True";
                } else {
                    chmpStat1="False";
                }
                boolean exResult = calRemote.ValidationDetailsExist(eventTypeId,userTypeId,divisionId,areaId,chmpStat1);
                if(!exResult){
                    if((eventTypeId!=null && areaId=="")||(eventTypeId!=null && areaId!=null && chmpStat.equalsIgnoreCase("no"))){
                        eventLevelDetails = calRemote.getEventLevelsBasedOnEventId(eventTypeId);
                        request.setAttribute("recordExists","yes");
                    } else if(areaId!=null && chmpStat.equalsIgnoreCase("yes")){
                        eventLevelDetails = calRemote.getEventLevelsForArea(areaId);
                        request.setAttribute("recordExists","yes");
                    }
                }else{
                    request.setAttribute("recordExists","no");
                }
                
                areaDetails = calRemote.getAllAreaMasters();
                request.setAttribute("eventLevelDetails",eventLevelDetails);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                request.setAttribute("membershipTypeDetails",membershipTypeDetails);
                request.setAttribute("userTypeId",userTypeId);
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("eventTypeId",eventTypeId);
                request.setAttribute("areaDetails",areaDetails);
                request.setAttribute("chmpStat",chmpStat);
                request.setAttribute("areaId",areaId);
                request.setAttribute("divisionId",divisionId);
                return mapping.findForward("initInsert");
            }
            
            else if(cmd!=null && cmd.equalsIgnoreCase("insertDetails")){
                String eventType = request.getParameter("inEventId");
                String userType = request.getParameter("inUserId");
                String areaId = request.getParameter("inAreaId");
                String divisionId = request.getParameter("indivisionId");
                String chmpStat = request.getParameter("inChmp");
                
                String stEventSize = request.getParameter("eventSize");
                int eventSize = 0;
                if(stEventSize!=null && stEventSize.trim().length()!=0){
                    eventSize = Integer.parseInt(stEventSize);
                }
                boolean flag = true;
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                ArrayList divisionTypeDetails = new ArrayList();
                
                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                divisionTypeDetails = calRemote.getDivisions();
                areaDetails = calRemote.getAllAreaMasters();
                
                for(int i=0; i<eventSize; i++){
                    String eventLevel = request.getParameter("eventLevel"+i);
                    if(eventLevel!=null && eventLevel.trim().length()!=0){
                        String levelRank = request.getParameter("levelRank"+i);
                        String minAge = request.getParameter("minAge"+i);
                        String maxAge = request.getParameter("maxAge"+i);
                        String qPeriod = request.getParameter("qPeriod"+i);
                        String minRank = request.getParameter("minRank"+i);
                        String minCount = request.getParameter("minCount"+i);
                        String maxRank = request.getParameter("maxRank"+i);
                        String jumpPenalties = request.getParameter("jumpPenalties"+i);
                        String timePenalties = request.getParameter("timePenalties"+i);
                        String membershipLevel = request.getParameter("membershipLevel"+i);
                        String menbLevelId = "";
                        String membLevelName = "";
                        if(membershipLevel!=null && membershipLevel.trim().length()!=0){
                            menbLevelId = membershipLevel.substring(0,membershipLevel.indexOf("#"));
                            membLevelName = membershipLevel.substring((1+membershipLevel.indexOf("#")),membershipLevel.length());
                        }
                        String amateurStatus = request.getParameter("amateurStatus"+i);
                        
                        if(eventType!=null && eventType.trim().length()!=0) validVO.setEventTypeId(eventType);
                        else validVO.setEventTypeId(null);
                        
                        if(userType!=null && userType.trim().length()!=0) validVO.setUserTypeId(userType);
                        else validVO.setUserTypeId(null);
                        
                        if(eventLevel!=null && eventLevel.trim().length()!=0) validVO.setEventLevelId(eventLevel);
                        else validVO.setEventLevelId(null);
                        
                        if(divisionId!=null && divisionId.trim().length()!=0) validVO.setEventDivisionId(divisionId);
                        else validVO.setEventDivisionId(null);
                        
                        if(levelRank!=null && levelRank.trim().length()!=0) validVO.setEveLevelRank(levelRank);
                        else validVO.setEveLevelRank(null);
                        
                        if(minAge!=null && minAge.trim().length()!=0) validVO.setMinAge(Integer.parseInt(minAge));
                        else validVO.setMinAge(0);
                        
                        if(maxAge!=null && maxAge.trim().length()!=0) validVO.setMaxAge(Integer.parseInt(maxAge));
                        else validVO.setMaxAge(0);
                        
                        if(qPeriod!=null && qPeriod.trim().length()!=0) validVO.setQualicPeriod(qPeriod);
                        else validVO.setQualicPeriod(null);
                        
                        if(minRank!=null && minRank.trim().length()!=0) validVO.setMinRank(minRank);
                        else validVO.setMinRank(null);
                        
                        if(minCount!=null && minCount.trim().length()!=0) validVO.setMinCount(Integer.parseInt(minCount));
                        else validVO.setMinCount(0);
                        
                        if(maxRank!=null && maxRank.trim().length()!=0) validVO.setMaxRank(maxRank);
                        else validVO.setMaxRank(null);
                        
                        if(jumpPenalties!=null && jumpPenalties.trim().length()!=0) validVO.setMaxXcJmpenal(jumpPenalties);
                        else validVO.setMaxXcJmpenal(null);
                        
                        if(timePenalties!=null && timePenalties.trim().length()!=0) validVO.setMaxXcTimepenal(timePenalties);
                        else validVO.setMaxXcTimepenal(null);
                        
                        if(membLevelName!=null && membLevelName.trim().length()!=0) validVO.setMembTypeName(membLevelName);
                        else validVO.setMembTypeName(null);
                        
                        if(menbLevelId!=null && menbLevelId.trim().length()!=0) validVO.setMemTypId(menbLevelId);
                        else validVO.setMemTypId(null);
                        
                        if(amateurStatus!=null && amateurStatus.trim().length()!=0) validVO.setAmateurStatus(true);
                        else validVO.setAmateurStatus(false);
                        
                        if(chmpStat!=null && chmpStat.trim().length()!=0 && chmpStat.equalsIgnoreCase("yes")) validVO.setChampStatus(true);
                        else validVO.setChampStatus(false);
                        
                        if(areaId!=null && areaId.trim().length()!=0) validVO.setAreaId(areaId);
                        else validVO.setAreaId(null);
                        
                        boolean existReswithChamp=false;
                        boolean result=false;
                        String chStatus1="True";
                        if(existReswithChamp==true) flag = false;
                        else result = calRemote.insertValidationDetails(validVO);

                        if(result) flag = true;
                        else flag = false;
                    }
                }

                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("eventTypeId",eventType);
                request.setAttribute("userTypeId",userType);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                request.setAttribute("areaDetails",areaDetails);
                request.setAttribute("areaId",areaId);
                request.setAttribute("divisionId",divisionId);
                request.setAttribute("chmpStat",chmpStat);
                
                if(flag){
                    request.setAttribute("insertStatus","success");
                    return mapping.findForward("insertSuccess");
                }else{
                    request.setAttribute("insertStatus","failed");
                    return mapping.findForward("insertSuccess");
                }
            }
            
            else if(cmd!=null && cmd.equalsIgnoreCase("initUpdate")){
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList viewValidationDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                ArrayList divisionTypeDetails = new ArrayList();
                
                divisionTypeDetails = calRemote.getDivisions();
                areaDetails = calRemote.getAllAreaMasters();
                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                
                String eventTypeId="";
                String userTypeId="";
                String areaId="";
                String divisionId="";

                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("areaDetails",areaDetails);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                request.setAttribute("userTypeId",userTypeId);
                request.setAttribute("divisionId",divisionId);
                request.setAttribute("areaId",areaId);
                request.setAttribute("viewValidationDetails",viewValidationDetails);
                
                return mapping.findForward("frmEditQualificationDetails");
            }
            
            else if(cmd!=null && cmd.equalsIgnoreCase("validDetails")){
                
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList viewValidationDetails = new ArrayList();
                ArrayList divisionTypeDetails = new ArrayList();
                ArrayList membershipTypeDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                
                String eventTypeId = request.getParameter("eventType");
                String userTypeId = request.getParameter("userType");
                String areaId = request.getParameter("selArea");
                String divisionId = request.getParameter("division");
                String chmpStat1=request.getParameter("chmpStatus");
                
                String chmpStat="";
                if(chmpStat1.equalsIgnoreCase("yes")) chmpStat="True";
                else chmpStat="False";

                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                divisionTypeDetails = calRemote.getDivisions();
                areaDetails = calRemote.getAllAreaMasters();
                viewValidationDetails=calRemote.getValidationDetsForEdit(eventTypeId,userTypeId,divisionId,areaId,chmpStat);
                membershipTypeDetails = calRemote.getMembershipTypes(userTypeId);
                
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("viewValidationDetails",viewValidationDetails);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                request.setAttribute("divisionId",divisionId);
                request.setAttribute("membershipTypeDetails",membershipTypeDetails);
                request.setAttribute("eventTypeId",eventTypeId);
                request.setAttribute("userTypeId",userTypeId);
                request.setAttribute("chmpStat1",chmpStat1);
                request.setAttribute("areaId",areaId);
                request.setAttribute("areaDetails",areaDetails);
                return mapping.findForward("frmEditQualificationDetails");
            }
            
            else if(cmd.equals("editRegLevel")){
                
                ArrayList membershipTypeDetails = new ArrayList();
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList divisionTypeDetails = new ArrayList();
                ArrayList eventLevelDetails = new ArrayList();
                ArrayList viewValidationDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                
                String userTypeId = (String)request.getParameter("usrTyp");
                membershipTypeDetails = calRemote.getMembershipTypes(userTypeId);
                divisionTypeDetails = calRemote.getDivisions();
                String eventTypeId = request.getParameter("eventType");
                String areaId = request.getParameter("selArea");
                String divisionId = request.getParameter("division");
                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                areaDetails = calRemote.getAllAreaMasters();
                String chmpStat1=request.getParameter("chmpStatus");
                
                String chmpStat="";
                if(chmpStat1.equalsIgnoreCase("yes")) chmpStat="True";
                else chmpStat="False";
                
                viewValidationDetails=calRemote.getValidationDetsForEdit(eventTypeId,userTypeId,divisionId,areaId,chmpStat);
                request.setAttribute("viewValidationDetails",viewValidationDetails);
                request.setAttribute("eventLevelDetails",eventLevelDetails);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                request.setAttribute("divisionId",divisionId);
                request.setAttribute("membershipTypeDetails",membershipTypeDetails);
                request.setAttribute("userTypeId",userTypeId);
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("eventTypeId",eventTypeId);
                request.setAttribute("areaId",areaId);
                request.setAttribute("areaDetails",areaDetails);
                request.setAttribute("chmpStat1",chmpStat1);
                return mapping.findForward("frmEditQualificationDetails");
            }
            
            else if(cmd!=null && cmd.equalsIgnoreCase("update")){
                String eventType = request.getParameter("inEventId");
                String userType = request.getParameter("inUserId");
                String divisionId = request.getParameter("indivisionId");
                String areaId = request.getParameter("inAreaId");
                String chmpStat1 = request.getParameter("inChmp");
                
                String stQualiSize = request.getParameter("qualiSize");
                int qualiSize = 0;
                if(stQualiSize!=null && stQualiSize.trim().length()!=0){
                    qualiSize = Integer.parseInt(stQualiSize);
                }
                
                boolean flag = true;
                Vector eventTypeDetails = null;
                ArrayList userTypeDetails = new ArrayList();
                ArrayList divisionTypeDetails = new ArrayList();
                ArrayList areaDetails = new ArrayList();
                eventTypeDetails = calRemote.getAllEventTypes();
                userTypeDetails = calRemote.getUserTypes();
                divisionTypeDetails = calRemote.getDivisions();
                areaDetails = calRemote.getAllAreaMasters();
                
                for(int i=0; i<qualiSize; i++){
                    String qualificationId = request.getParameter("qualificationId"+i);
                    String eventLevel = request.getParameter("eventLevelId"+i);
                    String levelRank = request.getParameter("levelRank"+i);
                    String minAge = request.getParameter("minAge"+i);
                    String maxAge = request.getParameter("maxAge"+i);
                    String qPeriod = request.getParameter("qPeriod"+i);
                    String minRank = request.getParameter("minRank"+i);
                    String minCount = request.getParameter("minCount"+i);
                    String maxRank = request.getParameter("maxRank"+i);
                    String jumpPenalties = request.getParameter("jumpPenalties"+i);
                    String timePenalties = request.getParameter("timePenalties"+i);
                    String membershipLevel = request.getParameter("membershipLevel"+i);
                    
                    String menbLevelId = "";
                    String membLevelName = "";
                    if(membershipLevel!=null && membershipLevel.trim().length()!=0){
                        menbLevelId = membershipLevel.substring(0,membershipLevel.indexOf("#"));
                        membLevelName = membershipLevel.substring((1+membershipLevel.indexOf("#")),membershipLevel.length());
                    }
                    String amateurStatus = request.getParameter("amateurStatus"+i);
                    
                    if(qualificationId!=null && qualificationId.trim().length()!=0) validVO.setQualificationId(qualificationId);
                    else validVO.setQualificationId(null);
                    
                    if(eventType!=null && eventType.trim().length()!=0) validVO.setEventTypeId(eventType);
                    else validVO.setEventTypeId(null);
                    
                    if(userType!=null && userType.trim().length()!=0) validVO.setUserTypeId(userType);
                    else validVO.setUserTypeId(null);
                    
                    if(eventLevel!=null && eventLevel.trim().length()!=0) validVO.setEventLevelId(eventLevel);
                    else validVO.setEventLevelId(null);
                    
                    if(divisionId!=null && divisionId.trim().length()!=0) validVO.setEventDivisionId(divisionId);
                    else validVO.setEventDivisionId(null);
                    
                    if(levelRank!=null && levelRank.trim().length()!=0) validVO.setEveLevelRank(levelRank);
                    else validVO.setEveLevelRank(null);
                    
                    if(minAge!=null && minAge.trim().length()!=0) validVO.setMinAge(Integer.parseInt(minAge));
                    else validVO.setMinAge(0);
                    
                    if(maxAge!=null && maxAge.trim().length()!=0) validVO.setMaxAge(Integer.parseInt(maxAge));
                    else validVO.setMaxAge(0);
                    
                    if(qPeriod!=null && qPeriod.trim().length()!=0) validVO.setQualicPeriod(qPeriod);
                    else validVO.setQualicPeriod(qPeriod);
                    
                    if(minRank!=null && minRank.trim().length()!=0) validVO.setMinRank(minRank);
                    else validVO.setMinRank(null);
                    
                    if(minCount!=null && minCount.trim().length()!=0) validVO.setMinCount(Integer.parseInt(minCount));
                    else validVO.setMinCount(0);
                    
                    if(maxRank!=null && maxRank.trim().length()!=0) validVO.setMaxRank(maxRank);
                    else validVO.setMaxRank(null);
                    
                    if(jumpPenalties!=null && jumpPenalties.trim().length()!=0) validVO.setMaxXcJmpenal(jumpPenalties);
                    else validVO.setMaxXcJmpenal(null);
                    
                    if(timePenalties!=null && timePenalties.trim().length()!=0) validVO.setMaxXcTimepenal(timePenalties);
                    else validVO.setMaxXcTimepenal(null);
                    
                    if(membLevelName!=null && membLevelName.trim().length()!=0) validVO.setMembTypeName(membLevelName);
                    else validVO.setMembTypeName(null);
                    
                    if(menbLevelId!=null && menbLevelId.trim().length()!=0) validVO.setMemTypId(menbLevelId);
                    else validVO.setMemTypId(null);
                    
                    if(amateurStatus!=null && amateurStatus.trim().length()!=0) validVO.setAmateurStatus(true);
                    else validVO.setAmateurStatus(false);
                    
                    if(chmpStat1!=null && chmpStat1.trim().length()!=0 && chmpStat1.equalsIgnoreCase("yes")) validVO.setChampStatus(true);
                    else validVO.setChampStatus(false);
                    
                    if(areaId!=null && areaId.trim().length()!=0) validVO.setAreaId(areaId);
                    else validVO.setAreaId(null);
                    
                    boolean result=false;
                    result = calRemote.updateValidationDetails(validVO);
                    Debug.print("No Champ Staus Result: "+result);
                    if(result){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }
                
                ArrayList viewValidationDetails = new ArrayList();
                String eventTypeId="";
                String userTypeId="";
                
                request.setAttribute("viewValidationDetails",viewValidationDetails);
                request.setAttribute("eventTypeDetails",eventTypeDetails);
                request.setAttribute("userTypeDetails",userTypeDetails);
                request.setAttribute("eventTypeId",eventType);
                request.setAttribute("userTypeId",userType);
                request.setAttribute("divisionTypeDetails",divisionTypeDetails);
                request.setAttribute("divisionId",divisionId);
                request.setAttribute("areaId",areaId);
                request.setAttribute("areaDetails",areaDetails);
                request.setAttribute("chmpStat1",chmpStat1);
                if(flag){
                    request.setAttribute("updateStatus","success");
                    return mapping.findForward("updateSuccess");
                }else{
                    request.setAttribute("updateStatus","failed");
                    return mapping.findForward("updateSuccess");
                }
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapping.findForward(SUCCESS);
    }
}