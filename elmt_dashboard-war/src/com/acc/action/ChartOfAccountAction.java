/*
 * ChartOfAccountAction.java
 *
 * Created on July 4, 2007, 6:12 PM
 */

package com.acc.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccountsMasterVO;
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

public class ChartOfAccountAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
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
                    ArrayList HLCHLCItemMaster = null;
                    ArrayList accMaster = null;
                    ArrayList accTypeMaster = null;
                   
                    /*
                     * Adding a new Item Master
                     */
                    
                    if(process.trim().equalsIgnoreCase("addAccMaster")){
                        accTypeMaster = mahaRemote.getAllAccountTypeMaster();
                        request.setAttribute("accTypeMaster",accTypeMaster);
                        request.setAttribute("errStat","none");
                        return mapping.findForward("addAccMaster");
                    }
                    
                    /*
                     * Save a new Item Master
                     */
                    
                    if(process.trim().equalsIgnoreCase("saveAccMaster")){
                        HLCAccountsMasterVO accMasterVO = new HLCAccountsMasterVO();
                        String accType = request.getParameter("accType");
                        String accNumber = request.getParameter("accNumber");
                        String accName = request.getParameter("accName");
                        String subItem = request.getParameter("subItem");
                        String subItemNo = request.getParameter("subItemNo");
                        String accDescrption = request.getParameter("accDescrption");
                        String accBank = request.getParameter("accBank");
                        
                        if(accType!=null && accNumber!=null && accName!=null){
                            accMasterVO.setAccount_desc(accDescrption);
                            accMasterVO.setAccount_name(accName);
                            accMasterVO.setAccount_no(accNumber);
                            accMasterVO.setAccount_type(accType);
                            
                            if(accBank!=null && accBank.trim().length()>0){
                                accMasterVO.setBank_account_no(accBank);
                            }
                            
                            if(subItem!=null){
                                accMasterVO.setParent_account_no(subItemNo);
                            }
                            
                            Debug.print("accMasterVO  "+accMasterVO);

                            String result = mahaRemote.createAccountDetails(accMasterVO);
                            Debug.print("result is "+result);

                            if(result.equalsIgnoreCase("ok")){
                                    return mapping.findForward("itemAccConf");
                            }
                            else{
                                accTypeMaster = mahaRemote.getAllAccountTypeMaster();
                                request.setAttribute("accTypeMaster",accTypeMaster);
                                request.setAttribute("errStat",result);
                                return mapping.findForward("addAccMaster");
                            }
                        }
                    }
                    
                    /*
                     * List a new Item Master
                     */
                    
                    if(process.trim().equalsIgnoreCase("listAccMaster")){
                        accTypeMaster = mahaRemote.getAllAccountTypeMaster();
                        request.setAttribute("accTypeMaster",accTypeMaster);
                        request.setAttribute("subAccountLists",null);
                        session.setAttribute("accountType",null);
                        return mapping.findForward("listAccMaster");
                    }
                    
                    if(process.trim().equalsIgnoreCase("listOnAccType")){
                        String accType = request.getParameter("accType");
                        Debug.print("accType is :"+accType);
                        ArrayList subAccountLists = null;
                        if(accType!=null && accType.trim().length()!=0){
                            subAccountLists = mahaRemote.getSubAccountList(accType);
                            
                        }
                        accTypeMaster = mahaRemote.getAllAccountTypeMaster();
                        request.setAttribute("accTypeMaster",accTypeMaster);                        
                        request.setAttribute("subAccountLists",subAccountLists);
                        session.setAttribute("accountType",accType);
                        return mapping.findForward("listAccMaster");
                    }
                    
                    /*
                     * View Single Item Master
                     */
                    
                    if(process.trim().equalsIgnoreCase("viewSingleAccDet")){
                        String accId = request.getParameter("accId");
                        if(accId!=null){
                            HLCAccountsMasterVO accMasterVO = mahaRemote.getAccountsDetails(accId);
                            Debug.print("accMasterVO "+accMasterVO);
                            accTypeMaster = mahaRemote.getAllAccountTypeMaster();
                            request.setAttribute("accTypeMaster",accTypeMaster);
                            request.setAttribute("errStat","none");                            
                            request.setAttribute("accMasterVO",accMasterVO);
                            return mapping.findForward("listSingleAccDet");
                        }
                    }
                    
                    /*
                     * Edit a new Item Master
                     */
                    
                    if(process.trim().equalsIgnoreCase("editAccMaster")){
                        String accId = request.getParameter("accId");
                        if(accId!=null){
                            HLCAccountsMasterVO accMasterVO = mahaRemote.getAccountsDetails(accId);
                            Debug.print("accMasterVO "+accMasterVO);
                            accTypeMaster = mahaRemote.getAllAccountTypeMaster();
                            request.setAttribute("accTypeMaster",accTypeMaster);
                            request.setAttribute("errStat","none");                            
                            request.setAttribute("accMasterVO",accMasterVO);
                            return mapping.findForward("editAccMaster");
                        }
                    }                    
                    /*
                     * Update a new Item Master
                     */
                    
                    if(process.trim().equalsIgnoreCase("updteAccMaster")){
                        HLCAccountsMasterVO accMasterVO = new HLCAccountsMasterVO();
                        String accType = request.getParameter("accType");
                        String accNumber = request.getParameter("accNumber");
                        String accName = request.getParameter("accName");
                        String subItem = request.getParameter("subItem");
                        String subItemNo = request.getParameter("subItemNo");
                        String accDescrption = request.getParameter("accDescrption");
                        String accBank = request.getParameter("accBank");
                        String accId = request.getParameter("accId");


                        if(accType!=null && accNumber!=null && accName!=null){
                            if(accBank!=null && accBank.trim().length()!=0){
                                accMasterVO.setBank_account_no(accBank);
                            }
                            if(accDescrption!=null && accDescrption.trim().length()!=0){
                                accMasterVO.setAccount_desc(accDescrption);
                            }
                            if(subItem!=null && subItem.trim().length()!=0){
                                accMasterVO.setParent_account_no(subItemNo);
                            }

                            accMasterVO.setAccount_name(accName);
                            accMasterVO.setAccount_no(accNumber);
                            accMasterVO.setAccount_type(accType);
                            accMasterVO.setAccount_id(accId);
                            
                            Debug.print("AccMaster VO" +accMasterVO.toString());
                            
                            String update_result = mahaRemote.updateAccountsDetails(accMasterVO);
                            Debug.print("update_result "+update_result);
                            if(update_result.equalsIgnoreCase("ok")){
                                accTypeMaster = mahaRemote.getAllAccountTypeMaster();
                                request.setAttribute("accTypeMaster",accTypeMaster);
                                request.setAttribute("subAccountLists",null);
                                session.setAttribute("accountType",null);
                                //session.removeAttribute("accountType");
                                return mapping.findForward("listAccMaster");
                            }
                            else{
                                request.setAttribute("errStat",update_result);
                                accMasterVO = mahaRemote.getAccountsDetails(accId);
                                Debug.print("accMasterVO "+accMasterVO);
                                request.setAttribute("accMasterVO",accMasterVO); 
                                return mapping.findForward("editAccMaster");
                            }
                        }
                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
           //Debug.print("Error in Servlet "+e.getMessage());
        }
        
        return mapping.findForward("login");
    }
}
