/*
 * SubItemAjaxAction.java
 *
 * Created on July 5, 2007, 11:58 AM
 */

package com.acc.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlccommon.util.Debug;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
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

public class SubItemAjaxAction extends Action {
    
    private String encoding = "UTF-8";
    
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
                
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter writer = response.getWriter();

            String process = (String)request.getParameter("process");
            Debug.print("process  "+process);
            
          if(process!=null){
               if(process.equalsIgnoreCase("subItemdisp")){
                String serviceTypeName = request.getParameter("serviceName");
                ArrayList subItems = mahaRemote.getSubItemName(serviceTypeName);
                Debug.print("subItems"+ subItems.size());

                String finalXML = null;
                StringBuffer xml = new StringBuffer();
                
                    xml.append("<?xml version=\"1.0\"");
                    if (encoding != null) {
                        xml.append(" encoding=\"");
                        xml.append(encoding);
                        xml.append("\"");
                    }
                    xml.append(" ?>\n");
                    xml.append("<SubItem>\n");
                    xml.append(subItemArray(subItems));
                    xml.append("</SubItem>");
                    
                    response.setContentType("text/xml; charset=UTF-8");
                    response.setHeader("Cache-Control", "no-cache");
                    
                    finalXML = xml.toString();
                    //Debug.print("Final XML is "+finalXML);
                    
                    PrintWriter pw = response.getWriter();
                    pw.write(finalXML);
                    pw.close();                    
                }
               
                if(process.equalsIgnoreCase("subAccdisp")){                   
                    String accType = request.getParameter("accType");
                    ArrayList subAccounts = mahaRemote.getSubAccountList(accType);
                    Debug.print("subAccounts"+ subAccounts.size());

                    String finalXML = null;
                    StringBuffer xml = new StringBuffer();

                        xml.append("<?xml version=\"1.0\"");
                        if (encoding != null) {
                            xml.append(" encoding=\"");
                            xml.append(encoding);
                            xml.append("\"");
                        }
                        xml.append(" ?>\n");
                        xml.append("<SubAccounts>\n");
                        xml.append(subAccountArray(subAccounts));
                        xml.append("</SubAccounts>");

                        response.setContentType("text/xml; charset=UTF-8");
                        response.setHeader("Cache-Control", "no-cache");

                        finalXML = xml.toString();
                        //Debug.print("Final XML is "+finalXML);

                        PrintWriter pw = response.getWriter();
                        pw.write(finalXML);
                        pw.close();
                }
            }
        }
        catch(Exception e){
            Debug.print("Error in SERVLET SubItemAjaxAction "+e.getMessage());
        }
        return null;
    }
  
// Sub Item Display  
    private String subItemArray(ArrayList arrList){
        StringBuffer xml = new StringBuffer();
        String itemId = null;
        String serviceTypeName = null;
        String itemNo = null;
        String parentItemNo = null;
        String itemDesc = null;
        float rate = 0.0f;
        String accountNo = null;   
        Iterator itr = arrList.iterator();
        
        while(itr.hasNext()){
            String subItemArray [] = (String[]) itr.next();
            itemId = subItemArray[0];
            serviceTypeName = subItemArray[1];
            itemNo = subItemArray[2];
            parentItemNo = subItemArray[3];
            itemDesc = subItemArray[4];
            accountNo = subItemArray[5];
            
            xml.append(" <subList> \n");
            xml.append("	<itemId>"+itemId+ "</itemId>\n");
            xml.append("	<serviceTypeName>"+serviceTypeName+"</serviceTypeName>\n");
            xml.append("	<itemNo>"+itemNo+"</itemNo>\n");
            xml.append("	<parentItemNo>"+parentItemNo+"</parentItemNo>\n");
            xml.append("	<itemDesc>"+itemDesc+"</itemDesc>\n");
            xml.append("	<accountNo>"+accountNo+"</accountNo>\n");
            xml.append(" </subList>\n");
        }
        return xml.toString();
    }
    
    
// Sub Account Display
    private String subAccountArray(ArrayList arrList){
        StringBuffer xml = new StringBuffer();
        String accountId = null;
        String accountType = null;
        String accountNo = null;
        String accountName = null;
        String parent_accountNo = null;
        String accountDesc = null;
        String bankAccountNo = null;
        Iterator itr = arrList.iterator();
        
        while(itr.hasNext()){
            String subAccountArray [] = (String[]) itr.next();
            //accountId, accountType, accountNo, accountName, parent_accountNo, accountDesc, bankAccountNo            
            accountId = subAccountArray[0];
            accountType = subAccountArray[1];
            accountNo = subAccountArray[2];
            accountName = subAccountArray[3];
            parent_accountNo = subAccountArray[4];
            accountDesc = subAccountArray[5];
            bankAccountNo = subAccountArray[6];
            
            xml.append(" <subAccList> \n");
            xml.append("	<accountId>"+accountId+ "</accountId>\n");
            xml.append("	<accountType>"+accountType+"</accountType>\n");
            xml.append("	<accountNo>"+accountNo+"</accountNo>\n");
            xml.append("	<accountName>"+accountName+"</accountName>\n");
            xml.append("	<parent_accountNo>"+parent_accountNo+"</parent_accountNo>\n");
            xml.append("	<accountDesc>"+accountDesc+"</accountDesc>\n");
            xml.append(" </subAccList>\n");
        }
        return xml.toString();
    }    
    
}
