/*
 * ItemMasterAction.java
 *
 * Created on July 4, 2007, 6:12 PM
 */

package com.acc.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCItemMaster;
import com.hlccommon.util.Debug;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class ItemMasterAction extends Action {

 
    public ActionForward execute(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            
        try{
        /// Transaction
                MessageResources mr=getResources(request);
                String mahanadhiJndi=mr.getMessage("jndi.acc");
                Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
                Context jndiContext = new InitialContext();
                Object maha=jndiContext.lookup(mahanadhiJndi);
                HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
                HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();               
                HttpSession session = request.getSession();
                String process = request.getParameter("process");
                Debug.print("Process in Servlet is "+process);
                
                if(process!=null){
                    ArrayList serviceMaster = null;
                    ArrayList HLCItemMaster = null;
                    ArrayList accMaster = null;
                   
                    /*
                     * Adding a new Item Master
                     */
                    
                    if(process.trim().equalsIgnoreCase("addListMaster")){
                            serviceMaster = mahaRemote.getServiceTypeMaster();
                            accMaster = mahaRemote.selectAccountMaster();
                            
                            request.setAttribute("serviceMaster",serviceMaster);
                            //request.setAttribute("HLCItemMaster",HLCItemMaster);
                            request.setAttribute("accMaster",accMaster);
                            
                            return mapping.findForward("addItemMaster");
                    }
                    
                    /*
                     * Save to an Existing Item Master
                     */
                    else if(process.trim().equalsIgnoreCase("saveListMaster")){
                        String servMasterName = request.getParameter("servMasterNo");
                        String itemNameNumber = request.getParameter("itemNameNumber");
                        String subItemNo = request.getParameter("subItemNo");
                        String itemDescrption = request.getParameter("itemDescrption");
                        String itemRate = request.getParameter("itemRate");
                        String AccountNo = request.getParameter("AccountNo");
                        String subItem = request.getParameter("subItem");
                        Debug.print("Master Name "+servMasterName);
                        Debug.print("Item Number"+itemNameNumber);
                        Debug.print("Sub Item Number"+subItemNo);
                        Debug.print("Item Description"+itemDescrption);
                        Debug.print("Item Name"+itemRate);
                        Debug.print("Account No "+AccountNo);
                        
                        if(servMasterName!=null && itemNameNumber!=null && itemDescrption!=null && AccountNo!=null ){
                            HLCItemMaster itemVO = new HLCItemMaster();
                            itemVO.setAccountNo(AccountNo);
                            itemVO.setItemDesc(itemDescrption);
                            itemVO.setItemNo(itemNameNumber);
                            
                            if(itemRate!=null && itemRate.trim().length()!=0){
                                itemVO.setRate(Float.parseFloat(itemRate));
                            }
                            
                            itemVO.setServiceTypeName(servMasterName);
                            
                            if(subItem!=null){
                                if(subItemNo!=null && subItemNo.trim().length()!=0){
                                    itemVO.setParentItemNo(subItemNo);
                                }
                            }
                            Debug.print("itemVO is \n"+itemVO);
                            boolean createItem = mahaRemote.createAccItemDetails(itemVO);
                            Debug.print("create Result is "+createItem);
                            if(createItem==true){
                                return mapping.findForward("itemMasterConf");
                            }
                        }
                        else{
                            serviceMaster = mahaRemote.getServiceTypeMaster();
                            accMaster = mahaRemote.selectAccountMaster();
                            
                            request.setAttribute("serviceMaster",serviceMaster);
                            request.setAttribute("accMaster",accMaster);                            
                            
                            return mapping.findForward("addItemMaster");
                        }
                    }
                    
                    /*
                     * Edit an existing Item Master
                     */
                    
                    else if(process.trim().equalsIgnoreCase("editListMaster")){
                        String itemId = request.getParameter("itemId");
                        serviceMaster = mahaRemote.getServiceTypeMaster();
                        accMaster = mahaRemote.selectAccountMaster();
                        HLCItemMaster itemVO = mahaRemote.getItemDetails(itemId);
                        
                        
                        request.setAttribute("HLCItemMaster",itemVO);    
                        request.setAttribute("serviceMaster",serviceMaster);
                        request.setAttribute("accMaster",accMaster);
                         
                        return mapping.findForward("editItemMaster");
                    }

                    /*
                     * Edit an existing Item Master
                     */
                    
                    else if(process.trim().equalsIgnoreCase("listAllMaster")){
                        serviceMaster = mahaRemote.getServiceTypeMaster();
                        request.setAttribute("serviceMaster",serviceMaster);
                        request.setAttribute("subitemType",null);
                        session.setAttribute("serviceType",null);
                        return mapping.findForward("listItemMaster");
                    }
                    
                    /*
                     * Update an existing Item Master
                     */                    
                    
                    else if(process.trim().equalsIgnoreCase("listSubitems")){
                       String servType = request.getParameter("servType");
                       Debug.print("Service Type "+servType);
                       ArrayList subservType = null;
                       if(servType!=null && servType.trim().length()!=0){
                            subservType = mahaRemote.getSubItemName(servType);
                            //session.setAttribute("serviceType",servType);
                       }
                       serviceMaster = mahaRemote.getServiceTypeMaster();
                       request.setAttribute("serviceMaster",serviceMaster);                      
                       request.setAttribute("subitemType",subservType);
                       session.setAttribute("serviceType",servType);
                       return mapping.findForward("listItemMaster");
                    }
                    /*
                     * Update an existing Item Master
                     */                    
                    else if(process.trim().equalsIgnoreCase("updateListMaster")){
                        String itemID =  request.getParameter("itemID");
                        String serviceName = request.getParameter("servMasterNo");
                        String itemNo =  request.getParameter("itemNameNumber");
                        String parentItemNo = request.getParameter("subItemNo");     
                        String itemDesc = request.getParameter("itemDescrption");
                        String rate =  request.getParameter("itemRate");
                        String accountNo = request.getParameter("AccountNo"); 
                        float float_rate  = 0.0f;
                        
                        if(rate!=null && rate.trim().length()>0){
                            float_rate = Float.parseFloat(rate);
                        }
                        
                        boolean udpate_Result = mahaRemote.updateAccItemDetails(itemID,serviceName,itemNo,parentItemNo, itemDesc, float_rate, accountNo);
                        Debug.print("Update Result is "+udpate_Result);
                        if(udpate_Result==true){
                            serviceMaster = mahaRemote.getServiceTypeMaster();
                            request.setAttribute("serviceMaster",serviceMaster);
                            request.setAttribute("subitemType",null);
                            session.setAttribute("serviceType",null);
                            //session.removeAttribute("serviceType");
                            return mapping.findForward("listItemMaster");
                        }
                        else{
                            serviceMaster = mahaRemote.getServiceTypeMaster();
                            accMaster = mahaRemote.selectAccountMaster();
                            HLCItemMaster itemVO = mahaRemote.getItemDetails(itemID);

                            request.setAttribute("HLCItemMaster",itemVO);    
                            request.setAttribute("serviceMaster",serviceMaster);
                            request.setAttribute("accMaster",accMaster);

                            return mapping.findForward("editItemMaster");                            
                        }
                    }
                    
                    /*
                     * Return to Login Page
                     */                                        
                    else{
                         return mapping.findForward("login");
                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
               
        return mapping.findForward("login");
        
    }
}

