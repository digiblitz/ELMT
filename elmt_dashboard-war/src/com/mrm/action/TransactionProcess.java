/*
 * Program Name     :   TransactionProcess.java
 * Created Date     :   May 28, 2007, 4:18 PM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.2
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */

package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlcaccounts.util.Debug;
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

public class TransactionProcess extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
    
            String name = "ejb/HLCMahanadhiSessionBean";
            Context jndiContext = new InitialContext();
            Object obj=jndiContext.lookup(name);
            HLCMahanadhiSessionRemoteHome mahanadhiHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahanadhiRemote = mahanadhiHome.create();
            
            HttpSession session=request.getSession();
            
            String process = request.getParameter("process");
            Debug.print("Process in Servlet "+process);
            try{
                if(process.equalsIgnoreCase("mapTransac")){

                    /*ArrayList accMaster = (ArrayList) mahanadhiRemote.selectAccountMaster();
                    request.setAttribute("accMaster",accMaster);*/

                    ArrayList itemMaster = (ArrayList) mahanadhiRemote.getItemMaster();
                    request.setAttribute("itemMaster",itemMaster);

                    ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();
                    request.setAttribute("classMaster",classMaster);
                    return mapping.findForward("transacMap");
                }

                else if(process.equalsIgnoreCase("updateMap")){
                    String fwd = "";
                    HLCAccTxnTypeDetailVO accTxnTypVO = new HLCAccTxnTypeDetailVO();

                    String usrId=(String) session.getAttribute("userId");                        
                    String transacName = request.getParameter("transacName");
                    String transacType = request.getParameter("transacType");
                    String transacAccNo = request.getParameter("transacAccNo");
                    //String transacSubAccNo = request.getParameter("transacSubAccNo");
                   // String transacItemNo = request.getParameter("transacItemNo");
                    String transacClass = request.getParameter("transacClass");

                    // Account Separation
                    String parent_Sub_AcNo[] = transacAccNo.split("#");
                        String transacItemNo = parent_Sub_AcNo[0];                
                        String AccNo = parent_Sub_AcNo[1];
                    Debug.print("AccNo        "+AccNo);
                    accTxnTypVO.setAccount_no(AccNo);

                    /* Debug.print("parentAccNo  "+parentAccNo);
                    if(parentAccNo==null || parentAccNo.equalsIgnoreCase("null")){
                        Debug.print("Inside parentAccNo == null");
                        accTxnTypVO.setAccount_no(AccNo);
                        accTxnTypVO.setSub_account_no(null);
                    }
                    else{
                        Debug.print("Inside parentAccNo != null");
                        accTxnTypVO.setAccount_no(parentAccNo);
                        accTxnTypVO.setSub_account_no(AccNo);  
                    }*/

                    String generatedItemNo = mahanadhiRemote.createItemNoFromAccMaster(transacItemNo);
                    Debug.print("generatedItemNo :"+generatedItemNo);

                    accTxnTypVO.setItem_no(generatedItemNo);
                    accTxnTypVO.setClass_name(transacClass);
                    //accTxnTypVO.setItem_no(transacItemNo);
                    accTxnTypVO.setTransaction_name(transacName);
                    accTxnTypVO.setTransaction_type(transacType);

                    boolean stat = mahanadhiRemote.isTransactionExists(transacName,generatedItemNo,transacClass);

                    if(stat)
                    {     

                         ArrayList itemMaster = (ArrayList) mahanadhiRemote.getItemMaster();
                         request.setAttribute("itemMaster",itemMaster);

                         ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();
                         request.setAttribute("classMaster",classMaster);

                        request.setAttribute("existStat","yes");
                        fwd = "transacMap";
                    }
                    else
                    {
                        mahanadhiRemote.insertAccTxnTypeDetails(accTxnTypVO);
                        fwd = "confMap";                    
                    }

                    Debug.print("fwd path :"+fwd);

                    return mapping.findForward(fwd);
                }


                else if(process.trim().equalsIgnoreCase("listAllTransac")){
                    ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();
                    request.setAttribute("classMaster",classMaster);
                    request.setAttribute("listTransacItem",null);
                    session.setAttribute("className",null);
                    return mapping.findForward("listtransac");
                }            


                else if(process.trim().equalsIgnoreCase("listtransac")){
                    String className = request.getParameter("className");
                    ArrayList listTransacItem = null;
                    
                    if(className!=null && className.trim().length()!=0){
                           listTransacItem = (ArrayList) mahanadhiRemote.listAccTxnTypeDetailsOnClass(className);
                           session.setAttribute("className",className);
                    }
                    
                    ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();
                    request.setAttribute("classMaster",classMaster);                    
                    request.setAttribute("listTransacItem",listTransacItem);
                    
                    return mapping.findForward("listtransac");
                }
                
                else if(process.trim().equalsIgnoreCase("edittransac")){
                    String transId = request.getParameter("transId");
                    
                    HLCAccTxnTypeDetailVO accTxnTypVO = new HLCAccTxnTypeDetailVO();
                    ArrayList listTransacItem = null;

                    if(transId!=null && transId.trim().length()!=0){
                        accTxnTypVO = mahanadhiRemote.selectAccTransactionTypeDetail(transId);    
                    }
                    
                    ArrayList itemMaster = (ArrayList) mahanadhiRemote.getItemMaster();
                    request.setAttribute("itemMaster",itemMaster);
                    
                    ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();
                    request.setAttribute("classMaster",classMaster);
                    request.setAttribute("TransactionDet",accTxnTypVO);
                    request.setAttribute("existStat","none");
                    return mapping.findForward("edittransac");
                }

                else if(process.trim().equalsIgnoreCase("updatetransac")){
                    String transName = request.getParameter("transacName");
                    String transacType = request.getParameter("transacType");
                    String transacAccNo = request.getParameter("transacAccNo");
                    String itemNo  =request.getParameter("transacItemNo");
                    String transId  = request.getParameter("transId");
                    String className = request.getParameter("transacClass");

                    Debug.print(" Transcat Name "+transName);
                    Debug.print(" transacType Name "+transacType);
                    Debug.print(" transacAccNo Name "+transacAccNo);
                    Debug.print(" Transcat itemNo "+itemNo);
                    Debug.print(" Transcat transId "+transId);
                    Debug.print(" Transcat className "+className);
                    
                    HLCAccTxnTypeDetailVO accTxnTypVO = new HLCAccTxnTypeDetailVO();
                    
                    if(transName!=null && transacType!=null && transacAccNo!=null && itemNo!=null && className!=null){
                        
                        if(transId!=null && transId.trim().length()!=0){
                            
                            accTxnTypVO = mahanadhiRemote.selectAccTransactionTypeDetail(transId);
                            String dbTransName = accTxnTypVO.getTransaction_name();
                            String dbTranstype = accTxnTypVO.getTransaction_type();
                            String dbItemNo = accTxnTypVO.getItem_no();
                            String dbTransId = accTxnTypVO.getTransaction_type_id();
                            String dbClassName = accTxnTypVO.getClass_name();
                            String dbAccNo = accTxnTypVO.getAccount_no();
                            
                            String finalValue = null;
                            String[] tempValue = itemNo.split("#");
                            String finalItemNo = null;
                            
                                if(tempValue.length>0){
                                    finalValue = tempValue[0];
                                }
                                
                                Debug.print("itemNo is "+itemNo);

                                if(itemNo!=null || itemNo.trim().length()>0){
                                    finalItemNo = mahanadhiRemote.createItemNoFromAccMaster(finalValue);
                                }
                                Debug.print("finalValue is "+finalValue);
                                Debug.print("Final Item No :"+finalItemNo);
                                Debug.print("Transac Type in DB is "+dbTranstype);
                                Debug.print("Transac Type in Form is "+transacType);
                                
                                if(dbTransName.equalsIgnoreCase(transName) && dbTranstype.equalsIgnoreCase(transacType)){
                                    Debug.print("Inside Equals of transName,transacType, transId");
                                    ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();
                                    request.setAttribute("classMaster",classMaster);
                                    request.setAttribute("listTransacItem",null);
                                    session.setAttribute("className",null);
                                    
                                    accTxnTypVO.setAccount_no(transacAccNo);
                                    accTxnTypVO.setClass_name(className);
                                    accTxnTypVO.setItem_no(finalItemNo);
                                    accTxnTypVO.setTransaction_name(transName);
                                    accTxnTypVO.setTransaction_type(transacType);                            

                                    boolean update_result = mahanadhiRemote.updateAccTxnTypeDetails(accTxnTypVO);
                                    Debug.print("update Result is "+update_result);
                                    
                                    return mapping.findForward("listtransac");
                                    
                                }
                                else{
                                    
                                    boolean transExists = mahanadhiRemote.isTransactionExists(transName,finalItemNo,dbClassName);
                                    if(transExists==true){
                                        ArrayList listTransacItem = null;
                                        Debug.print("~~~~~~~~~~~~~~~transExists==true~~~~~~~~~~~~~~~~~");
                                        if(transId!=null && transId.trim().length()!=0){
                                            accTxnTypVO = mahanadhiRemote.selectAccTransactionTypeDetail(transId);    
                                        }

                                        ArrayList itemMaster = (ArrayList) mahanadhiRemote.getItemMaster();
                                        ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();

                                        request.setAttribute("itemMaster",itemMaster);
                                        request.setAttribute("classMaster",classMaster);
                                        request.setAttribute("TransactionDet",accTxnTypVO);
                                        request.setAttribute("existStat","yes");

                                        return mapping.findForward("edittransac");                        
                                    }
                                    else{
                                        Debug.print("~~~~~~~~~~~~~~~transExists==FALSE~~~~~~~~~~~~~~~~~");
                                        if(transName!=null && transacType!=null && transacAccNo!=null && className!=null && finalItemNo!=null){

                                            accTxnTypVO.setAccount_no(transacAccNo);
                                            accTxnTypVO.setClass_name(className);
                                            accTxnTypVO.setItem_no(finalItemNo);
                                            accTxnTypVO.setTransaction_name(transName);
                                            accTxnTypVO.setTransaction_type(transacType);                            

                                            boolean update_result = mahanadhiRemote.updateAccTxnTypeDetails(accTxnTypVO);
                                            Debug.print("update Result is "+update_result);

                                                if(update_result==true){
                                                    ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();
                                                    request.setAttribute("classMaster",classMaster);
                                                    request.setAttribute("listTransacItem",null);
                                                    session.setAttribute("className",null);
                                                    return mapping.findForward("listtransac");
                                                }
                                                else{
                                                    ArrayList listTransacItem = null;

                                                    if(transId!=null && transId.trim().length()!=0){
                                                        accTxnTypVO = mahanadhiRemote.selectAccTransactionTypeDetail(transId);    
                                                    }

                                                    ArrayList itemMaster = (ArrayList) mahanadhiRemote.getItemMaster();
                                                    ArrayList classMaster = (ArrayList) mahanadhiRemote.selectClassMaster();

                                                    request.setAttribute("itemMaster",itemMaster);
                                                    request.setAttribute("classMaster",classMaster);
                                                    request.setAttribute("TransactionDet",accTxnTypVO);
                                                    request.setAttribute("existStat","yes");

                                                    return mapping.findForward("edittransac");                                  
                                                }
                                        }
                                    }
                                }
                        }
                    }

                }
                
                else{
                    Debug.print("Iniside else of all possible");
                    return mapping.findForward("login");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
           return mapping.findForward("login");
    }
}