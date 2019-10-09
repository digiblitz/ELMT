/*
 * AddFixedPriceAction.java
 *
 * Created on November 26, 2007, 12:48 PM
 */

package com.oee.action;

import com.hlccommon.util.Debug;
import com.hlcutil.HLCPriceMatrixVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;
import java.util.Date;
import java.text.*;
import org.apache.struts.util.MessageResources;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemote;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemoteHome;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;

/**
 *
 * @author Vidhya
 * @version
 */
public class AddFixedPriceAction extends Action {
    
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
            
            Object krishnaObj = jndiContext.lookup(jndiname);
            HLCKrishnaStatelessRemoteHome krishnaHome = (HLCKrishnaStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(krishnaObj,HLCKrishnaStatelessRemoteHome.class);
            HLCKrishnaStatelessRemote calRemote = krishnaHome.create();
            
            Object qualObj = jndiContext.lookup(jndiName);
            HLCMembershipQualificationMatrixRemoteHome qhome = (HLCMembershipQualificationMatrixRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(qualObj,HLCMembershipQualificationMatrixRemoteHome.class);
            HLCMembershipQualificationMatrixRemote qremote = qhome.create();
            
            HLCPriceMatrixVO priceVO= new HLCPriceMatrixVO();
            
            String cmd = request.getParameter("cmd");
            Debug.print("cmd function:" + cmd);
            if(cmd!=null){
                 if(cmd.equals("initViewPrice")){
                    Debug.print("cmd in AddFixedPriceAction :"+cmd);
                    ArrayList allItem=new ArrayList();
                    Vector allEventType=new Vector();
                    ArrayList allArea=new ArrayList();
                    
                    allItem=qremote.getAllItemNamesForStaff();
                    allEventType=calRemote.getAllEventTypes();
                    allArea=calRemote.getAllAreaMasters();
                    
                    request.setAttribute("allItem",allItem);
                    request.setAttribute("allEventType",allEventType);
                    request.setAttribute("allArea",allArea);
                    
                    return mapping.findForward("frmOEEStaffViewPrice");
                }
                if(cmd.equals("staffViewPrice")){
                     Debug.print("cmd in AddFixedPriceAction :"+cmd);
                    String itemId=request.getParameter("selItem");
                    String eventTypeId=request.getParameter("selType");
                    String areaId=request.getParameter("selArea");

                    ArrayList allItem=new ArrayList();
                    Vector allEventType=new Vector();
                    ArrayList allArea=new ArrayList();
                    ArrayList eventDetails=new ArrayList();
                    ArrayList eventDetailsChamp=new ArrayList();
                    
                    allItem=qremote.getAllItemNamesForStaff();
                    allEventType=calRemote.getAllEventTypes();
                    allArea=calRemote.getAllAreaMasters();
                    eventDetails=qremote.getFixedPriceMatrixDetails(itemId,eventTypeId);
                    eventDetailsChamp=qremote.getFixedPriceMatrixForChamp(areaId);
                    
                    request.setAttribute("itemId",itemId);
                    request.setAttribute("eventTypeId",eventTypeId);
                    request.setAttribute("areaId",areaId);

                    request.setAttribute("allItem",allItem);
                    request.setAttribute("allEventType",allEventType);
                    request.setAttribute("allArea",allArea);
                    request.setAttribute("eventDetails",eventDetails);
                    request.setAttribute("eventDetailsChamp",eventDetailsChamp);
                    request.setAttribute("enableButton","enableButton");
                    return mapping.findForward("frmOEEStaffViewPrice"); 
                } 
                 
                
                 if(cmd.equals("initEditPrice")){
                 Debug.print("cmd in AddFixedPriceAction :"+cmd);  
                    String itemId=request.getParameter("itemID");
                    String eventTypeId=request.getParameter("eventTypeId");
                    String areaId=request.getParameter("areaID");

                    ArrayList allItem=new ArrayList();
                    Vector allEventType=new Vector();
                    ArrayList allArea=new ArrayList();
                    ArrayList eventDetails=new ArrayList();
                    ArrayList eventDetailsChamp=new ArrayList();
                            
                    allItem=qremote.getAllItemNamesForStaff();
                    allEventType=calRemote.getAllEventTypes();
                    allArea=calRemote.getAllAreaMasters();
                    eventDetails=qremote.getFixedPriceMatrixDetails(itemId,eventTypeId);
                    eventDetailsChamp=qremote.getFixedPriceMatrixForChamp(areaId);
                    
                    request.setAttribute("itemId",itemId);
                    request.setAttribute("eventTypeId",eventTypeId);
                    request.setAttribute("areaId",areaId);

                    request.setAttribute("allItem",allItem);
                    request.setAttribute("allEventType",allEventType);
                    request.setAttribute("allArea",allArea);
                    
                    request.setAttribute("eventDetails",eventDetails);
                    request.setAttribute("eventDetailsChamp",eventDetailsChamp);
                    
                    return mapping.findForward("frmOEEStaffEditPrice");   
                 } 
                   if(cmd.equals("updatePriceDetails")){
                    Debug.print("cmd in AddFixedPriceAction :"+cmd);
                    String itemId=request.getParameter("itemID");
                    String eventTypeId=request.getParameter("eventTypeId");
                    String areaId=request.getParameter("areaID");
                    Debug.print("itemId in insertPriceDetails"+itemId);
                    Debug.print("eventTypeId in insertPriceDetails"+eventTypeId);
                    Debug.print("areaId in insertPriceDetails"+areaId);
                    boolean flag = true;
                    
                    int noChampSize = 0;
                    String noChSize = request.getParameter("noChampSize");
                    Debug.print("noChSize in AddFixedPriceAction: "+noChSize);
                    
                    if(noChSize!=null && noChSize.trim().length()!=0){
                        noChampSize = Integer.parseInt(noChSize);
                    }
                    for(int i=1; i<=noChampSize; i++){
                        Debug.print("inside loop: "+i);
                        String priceId=request.getParameter("priceId"+i);
                        String eventLevelId=request.getParameter("eventLevelId"+i);
                        String duePrice=request.getParameter("txtDue"+i);
                        String afterDuePrice=request.getParameter("txtAfterDue"+i);
                        String depAmt=request.getParameter("txtDepAmt"+i);
                        Debug.print("priceIdin AddFixedPriceAction: "+priceId);
                        Debug.print("eventLevelIdin AddFixedPriceAction: "+eventLevelId);
                        Debug.print("duePricein AddFixedPriceAction: "+duePrice);
                        Debug.print("afterDuePricein AddFixedPriceAction: "+afterDuePrice);
                        Debug.print("depAmtin AddFixedPriceAction: "+depAmt);
                        
                        //if(duePrice!=null && duePrice.trim().length()!=0 && afterDuePrice!=null && afterDuePrice.trim().length()!=0
                               // && depAmt!=null && depAmt.trim().length()!=0){
                            
                            if(priceId!=null && priceId.trim().length()!=0) priceVO.setPriceId(priceId);
                            else priceVO.setPriceId(null);
                                                       
                            if(itemId!=null && itemId.trim().length()!=0) priceVO.setEntryItemId(itemId);
                            else priceVO.setEntryItemId(null);
                            
                            if(eventTypeId!=null && eventTypeId.trim().length()!=0) priceVO.setEventTypeId(eventTypeId);
                            else priceVO.setEventTypeId(null);
                            
                            if(eventLevelId!=null && eventLevelId.trim().length()!=0) priceVO.setEventLevelId(eventLevelId);
                            else priceVO.setEventLevelId(null);
                            
                           
                            if(duePrice!=null && duePrice.trim().length()!=0) priceVO.setDueDatePrice(Float.parseFloat(duePrice));
                            else priceVO.setDueDatePrice(0);
                            
                            if(afterDuePrice!=null && afterDuePrice.trim().length()!=0) priceVO.setAfterDueDatePrice(Float.parseFloat(afterDuePrice));
                            else priceVO.setAfterDueDatePrice(0);
                            
                            if(depAmt!=null && depAmt.trim().length()!=0) priceVO.setDepositPrice(Float.parseFloat(depAmt));
                            else priceVO.setDepositPrice(0);
                            
                            priceVO.setChampionshipStatus(false);
                            priceVO.setAreaId(null);
                            boolean noChampResult=false;
                            noChampResult=qremote.updateFixedPriceMatrixDetails(priceVO);
                            if(noChampResult==true){
                                flag=true;
                            }else{
                                flag=false;
                            }
                        //}
                    }
                    int champSize = 0;
                    String chSize = request.getParameter("champSize");
                    Debug.print("chSize in AddFixedPriceAction: "+chSize);
                    
                    if(chSize!=null && chSize.trim().length()!=0){
                        champSize = Integer.parseInt(chSize);
                    }
                    Debug.print("champSize in AddFixedPriceAction: "+champSize);
                    for(int i=1;i<=champSize; i++){
                        Debug.print("inside Champ loop: "+i);
                        String CpriceId=request.getParameter("CpriceId"+i);
                        String CeventLevelId=request.getParameter("CeventLevelId"+i);
                        String CduePrice=request.getParameter("txtDueC"+i);
                        String CafterDuePrice=request.getParameter("txtAfterDueC"+i);
                        String CdepAmt=request.getParameter("txtDepAmtC"+i);
                        Debug.print("CpriceIdin AddFixedPriceAction: "+CpriceId);
                        Debug.print("CeventLevelIdin AddFixedPriceAction: "+CeventLevelId);
                        Debug.print("CduePricein AddFixedPriceAction: "+CduePrice);
                        Debug.print("CafterDuePricein AddFixedPriceAction: "+CafterDuePrice);
                        Debug.print("CdepAmtin AddFixedPriceAction: "+CdepAmt);
                       // if(CduePrice!=null && CduePrice.trim().length()!=0 && CafterDuePrice!=null && CafterDuePrice.trim().length()!=0
                                //&& CdepAmt!=null && CdepAmt.trim().length()!=0){
                            
                             if(CpriceId!=null && CpriceId.trim().length()!=0) priceVO.setPriceId(CpriceId);
                            else priceVO.setPriceId(null);
                            
                            if(itemId!=null && itemId.trim().length()!=0) priceVO.setEntryItemId(itemId);
                            else priceVO.setEntryItemId(null);
                            
                            priceVO.setEventTypeId(null);
                            
                            if(CeventLevelId!=null && CeventLevelId.trim().length()!=0) priceVO.setEventLevelId(CeventLevelId);
                            else priceVO.setEventLevelId(null);
                            
                            if(CduePrice!=null && CduePrice.trim().length()!=0) priceVO.setDueDatePrice(Float.parseFloat(CduePrice));
                            else priceVO.setDueDatePrice(0);
                            
                            if(CafterDuePrice!=null && CafterDuePrice.trim().length()!=0) priceVO.setAfterDueDatePrice(Float.parseFloat(CafterDuePrice));
                            else priceVO.setAfterDueDatePrice(0);
                            
                            if(CdepAmt!=null && CdepAmt.trim().length()!=0) priceVO.setDepositPrice(Float.parseFloat(CdepAmt));
                            else priceVO.setDepositPrice(0);
                            
                            if(areaId!=null && areaId.trim().length()!=0) priceVO.setAreaId(areaId);
                            else priceVO.setAreaId(null);
                            
                            priceVO.setChampionshipStatus(true);
                            
                            boolean noChampResult=false;
                            noChampResult=qremote.updateFixedPriceMatrixDetails(priceVO);
                            if(noChampResult==true){
                                flag=true;
                            }else{
                                flag=false;
                            }
                        //}
                    }
                    if(flag==true){
                        request.setAttribute("updateStatus","success");
                        return mapping.findForward("frmOEEStaffEditSuccess");
                    }else{
                        request.setAttribute("updateStatus","failed");
                        return mapping.findForward("frmOEEStaffEditSuccess");
                    }
         
                }
                 
                if(cmd.equals("initPrice")){
                    Debug.print("cmd in AddFixedPriceAction :"+cmd);
                    String itemId=request.getParameter("itemID");
                    String eventTypeId=request.getParameter("eventTypeId");
                    String areaId=request.getParameter("areaID");

                    ArrayList allItem=new ArrayList();
                    Vector allEventType=new Vector();
                    ArrayList allArea=new ArrayList();
                    ArrayList eventLevelByType=new ArrayList();
                    ArrayList eventLevelByArea=new ArrayList();

                    allItem=qremote.getAllItemNamesForStaff();
                    allEventType=calRemote.getAllEventTypes();
                    allArea=calRemote.getAllAreaMasters();
                    eventLevelByType=calRemote.getEventLevelsBasedOnEventId(eventTypeId);
                    eventLevelByArea=calRemote.getEventLevelsForArea(areaId);

                    request.setAttribute("itemId",itemId);
                    request.setAttribute("eventTypeId",eventTypeId);
                    request.setAttribute("areaId",areaId);

                    request.setAttribute("allItem",allItem);
                    request.setAttribute("allEventType",allEventType);
                    request.setAttribute("allArea",allArea);
                    request.setAttribute("eventLevelByType",eventLevelByType);
                    request.setAttribute("eventLevelByArea",eventLevelByArea);
                    
                    return mapping.findForward("frmOEEStaffInsertPrice");
                } 
                if(cmd.equals("initPriceDetails")){
                    Debug.print("cmd in AddFixedPriceAction :"+cmd);
                    String itemId=request.getParameter("selItem");
                    String eventTypeId=request.getParameter("selType");
                    String areaId=request.getParameter("selArea");
                    
                    ArrayList allItem=new ArrayList();
                    Vector allEventType=new Vector();
                    ArrayList allArea=new ArrayList();
                    ArrayList eventLevelByType=new ArrayList();
                    ArrayList eventLevelByArea=new ArrayList();
                    
                    allItem=qremote.getAllItemNamesForStaff();
                    allEventType=calRemote.getAllEventTypes();
                    allArea=calRemote.getAllAreaMasters();
                    eventLevelByType=calRemote.getEventLevelsBasedOnEventId(eventTypeId);
                    eventLevelByArea=calRemote.getEventLevelsForArea(areaId);
                    
                    request.setAttribute("itemId",itemId);
                    request.setAttribute("eventTypeId",eventTypeId);
                    request.setAttribute("areaId",areaId);
                    
                    request.setAttribute("allItem",allItem);
                    request.setAttribute("allEventType",allEventType);
                    request.setAttribute("allArea",allArea);
                    request.setAttribute("eventLevelByType",eventLevelByType);
                    request.setAttribute("eventLevelByArea",eventLevelByArea);
                    request.setAttribute("enableButton","enableButton");
                    return mapping.findForward("frmOEEStaffInsertPrice");
                }
                
                if(cmd.equals("insertPriceDetails")){
                    Debug.print("cmd in AddFixedPriceAction :"+cmd);
                    String itemId=request.getParameter("itemID");
                    String eventTypeId=request.getParameter("eventTypeId");
                    String areaId=request.getParameter("areaID");
                    Debug.print("itemId in insertPriceDetails"+itemId);
                    Debug.print("eventTypeId in insertPriceDetails"+eventTypeId);
                    Debug.print("areaId in insertPriceDetails"+areaId);
                    boolean flag = true;
                     boolean flag1 = true;
                    
                    int noChampSize = 0;
                    String noChSize = request.getParameter("noChampSize");
                    Debug.print("noChSize in AddFixedPriceAction: "+noChSize);
                    
                    if(noChSize!=null && noChSize.trim().length()!=0){
                        noChampSize = Integer.parseInt(noChSize);
                    }
                    for(int i=1; i<=noChampSize; i++){
                        Debug.print("inside loop: "+i);
                        String eventLevelId=request.getParameter("eventLevelId"+i);
                        String duePrice=request.getParameter("txtDue"+i);
                        String afterDuePrice=request.getParameter("txtAfterDue"+i);
                        String depAmt=request.getParameter("txtDepAmt"+i);
                        Debug.print("duePricein AddFixedPriceAction: "+duePrice);
                        Debug.print("afterDuePricein AddFixedPriceAction: "+afterDuePrice);
                        Debug.print("depAmtin AddFixedPriceAction: "+depAmt);
                                                                               
                            if(itemId!=null && itemId.trim().length()!=0) priceVO.setEntryItemId(itemId);
                            else priceVO.setEntryItemId(null);
                            
                            if(eventTypeId!=null && eventTypeId.trim().length()!=0) priceVO.setEventTypeId(eventTypeId);
                            else priceVO.setEventTypeId(null);
                            
                            if(eventLevelId!=null && eventLevelId.trim().length()!=0) priceVO.setEventLevelId(eventLevelId);
                            else priceVO.setEventLevelId(null);
                            
                           
                            if(duePrice!=null && duePrice.trim().length()!=0) priceVO.setDueDatePrice(Float.parseFloat(duePrice));
                            else priceVO.setDueDatePrice(0);
                            
                            if(afterDuePrice!=null && afterDuePrice.trim().length()!=0) priceVO.setAfterDueDatePrice(Float.parseFloat(afterDuePrice));
                            else priceVO.setAfterDueDatePrice(0);
                            
                            if(depAmt!=null && depAmt.trim().length()!=0) priceVO.setDepositPrice(Float.parseFloat(depAmt));
                            else priceVO.setDepositPrice(0);
                            
                            priceVO.setChampionshipStatus(false);
                            priceVO.setAreaId(null);
                            boolean noChampResult=false;
                            boolean isExist=false;
                            isExist=qremote.isFixedPriceMatrixExist(itemId,eventTypeId,eventLevelId,null);  
                            if(isExist==true){                          
                            request.setAttribute("AlreadyExist","exist");
                           // return mapping.findForward("frmOEEStaffPriceSuccess");
                            }else{
                            noChampResult=qremote.insertFixedPriceMatrix(priceVO);    
                            }
                            if(noChampResult==true){
                                request.setAttribute("insertStatus","success");
                                flag=true;
                            }else{
                                //request.setAttribute("insertStatus","failed");
                                flag=false;
                            }
                        
                    }
                    int champSize = 0;
                    String chSize = request.getParameter("champSize");
                    Debug.print("chSize in AddFixedPriceAction: "+chSize);
                    
                    if(chSize!=null && chSize.trim().length()!=0){
                        champSize = Integer.parseInt(chSize);
                    }
                    Debug.print("champSize in AddFixedPriceAction: "+champSize);
                    for(int i=1;i<=champSize; i++){
                        Debug.print("inside Champ loop: "+i);
                        String CeventLevelId=request.getParameter("CeventLevelId"+i);
                        String CduePrice=request.getParameter("txtDueC"+i);
                        String CafterDuePrice=request.getParameter("txtAfterDueC"+i);
                        String CdepAmt=request.getParameter("txtDepAmtC"+i);
                        Debug.print("CeventLevelIdin AddFixedPriceAction: "+CeventLevelId);
                        Debug.print("CduePricein AddFixedPriceAction: "+CduePrice);
                        Debug.print("CafterDuePricein AddFixedPriceAction: "+CafterDuePrice);
                        Debug.print("CdepAmtin AddFixedPriceAction: "+CdepAmt);
                        
                            
                            if(itemId!=null && itemId.trim().length()!=0) priceVO.setEntryItemId(itemId);
                            else priceVO.setEntryItemId(null);
                            
                            priceVO.setEventTypeId(null);
                            
                            if(CeventLevelId!=null && CeventLevelId.trim().length()!=0) priceVO.setEventLevelId(CeventLevelId);
                            else priceVO.setEventLevelId(null);
                            
                            if(CduePrice!=null && CduePrice.trim().length()!=0) priceVO.setDueDatePrice(Float.parseFloat(CduePrice));
                            else priceVO.setDueDatePrice(0);
                            
                            if(CafterDuePrice!=null && CafterDuePrice.trim().length()!=0) priceVO.setAfterDueDatePrice(Float.parseFloat(CafterDuePrice));
                            else priceVO.setAfterDueDatePrice(0);
                            
                            if(CdepAmt!=null && CdepAmt.trim().length()!=0) priceVO.setDepositPrice(Float.parseFloat(CdepAmt));
                            else priceVO.setDepositPrice(0);
                            
                            if(areaId!=null && areaId.trim().length()!=0) priceVO.setAreaId(areaId);
                            else priceVO.setAreaId(null);
                            
                            priceVO.setChampionshipStatus(true);
                            
                            boolean ChampResult=false;
                            boolean isExist=false;
                            isExist=qremote.isFixedPriceMatrixExist(itemId,null,CeventLevelId,areaId);  
                            if(isExist==true){
                            request.setAttribute("AlreadyExistChmp","chmpExist");
                            //return mapping.findForward("frmOEEStaffPriceSuccess");
                            }else{
                            ChampResult=qremote.insertFixedPriceMatrix(priceVO);    
                            }
                            if(ChampResult==true){
                                request.setAttribute("chmpInsertStatus","chmpsuccess");
                                flag=true;
                            }else{
                                //request.setAttribute("chmpInsertStatus","chmpfailed");
                                flag=false;
                            }
                        
                    }                    
                    if(flag==true){
                       
                        return mapping.findForward("frmOEEStaffPriceSuccess");
                    }else if(flag==false){
                        
                        return mapping.findForward("frmOEEStaffPriceSuccess");
                    }                   
                   
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
