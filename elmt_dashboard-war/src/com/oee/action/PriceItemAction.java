/*
 * PriceItemAction.java
 *
 * Created on November 16, 2007, 1:47 PM
 */

/*
 * AddPriceAction.java
 *
 * Created on November 15, 2007, 1:08 PM
 */

package com.oee.action;

import com.hlcutil.Debug;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Date;
import java.text.*;
import org.apache.struts.util.MessageResources;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemote;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemoteHome;

/**
 *
 * @author Vidhya
 * @version
 */
public class PriceItemAction extends Action {
    
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
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            
            Object qualObj = jndiContext.lookup(jndiName);
            HLCMembershipQualificationMatrixRemoteHome qhome = (HLCMembershipQualificationMatrixRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(qualObj,HLCMembershipQualificationMatrixRemoteHome.class);
            HLCMembershipQualificationMatrixRemote qremote = qhome.create();
            
            String cmd = request.getParameter("cmd");
            Debug.print("cmd function:" + cmd);
            if(cmd!=null){
                if(cmd.equals("initCreateItem")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    return mapping.findForward("frmOEEPriceItemCreate");
                }
                if(cmd.equals("insertItem")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    String itemName=request.getParameter("txtItemName");
                    String orgStatus=request.getParameter("orgStatus");
                    boolean createResult=false;
                    boolean isExist=qremote.isItemNameExist(itemName);  
                    if(isExist==true){
                        request.setAttribute("err","st");
                    return mapping.findForward("frmOEEPriceItemCreate");
                    }
                    else{
                    createResult=qremote.insertPriceItem(itemName,orgStatus);      
                    }
                    if(createResult==true){
                        return mapping.findForward("frmOEECreateConf");
                    }else{
                        return mapping.findForward("frmOEEPriceItemCreate");
                    }
                }
                if(cmd.equals("initListItem")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    //String status=request.getParameter("acStatus");
                    //request.setAttribute("status",status);                    
                    return mapping.findForward("frmOEEPriceItemList");
                }
                if(cmd.equals("listDetails")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    String status=request.getParameter("acStatus");
                    Debug.print("status in servlet: "+status);
                    String result="";
                    if(status!=null && status.equalsIgnoreCase("Activate")){
                    result ="true";  
                    }else{
                    result ="false";   
                    }
                    ArrayList itemList=new ArrayList();
                    itemList=qremote.getAllPriceItems(result);
                    request.setAttribute("itemList",itemList);
                    request.setAttribute("status",status);
                    return mapping.findForward("frmOEEPriceItemList");
                }
                if(cmd.equals("activate")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    String itemId=request.getParameter("itemId");
                    String status="true";
                    boolean statusResult=qremote.updatePriceItemstatus(itemId,status);
                    return mapping.findForward("frmOEEPriceItemList");
                }
                if(cmd.equals("deActivate")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    String itemId=request.getParameter("itemId");
                    String status="false";
                    boolean statusResult=qremote.updatePriceItemstatus(itemId,status);
                    return mapping.findForward("frmOEEPriceItemList");
                }
                if(cmd.equals("initUpdateItem")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    String itemId=request.getParameter("itemId");
                    ArrayList singleDet=new ArrayList();
                    singleDet=qremote.getSingleItemDetails(itemId);
                    request.setAttribute("singleDet",singleDet);
                    return mapping.findForward("frmOEEPriceItemEdit");
                }
                if(cmd.equals("updateItem")){
                    Debug.print("cmd in PriceItemAction :"+cmd);
                    String itemId=request.getParameter("itemId");
                    String itemName=request.getParameter("txtItemName");
                    String orgStatus=request.getParameter("orgStatus");
                    boolean updateResult=false;
                    boolean isExist =qremote.isEditItemNameExist(itemId,itemName);
                    if(isExist==true){
                    ArrayList singleDet=new ArrayList();
                    singleDet=qremote.getSingleItemDetails(itemId);
                    request.setAttribute("singleDet",singleDet);
                    request.setAttribute("err","st");
                    return mapping.findForward("frmOEEPriceItemEdit");
                    }
                    else{
                    updateResult=qremote.updatePriceItem(itemId,itemName,orgStatus);     
                    }
                   
                    if(updateResult==true){
                        return mapping.findForward("frmOEEPriceItemList");
                    }else{
                        return mapping.findForward("frmOEEPriceItemEdit");
                    }
                    
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
