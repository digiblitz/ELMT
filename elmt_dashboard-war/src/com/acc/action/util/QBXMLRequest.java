/*
 * QBXMLRequest.java
 *
 * Created on May 13, 2007, 3:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.acc.action.util;

import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcform.util.Debug;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.hlcaccounts.util.HLCMemberShipTransactionVO;
import java.util.*;

/**
 *
 * @author karthikeyan
 */
public class QBXMLRequest {
    
    /** Creates a new instance of QBXMLRequest */
    public QBXMLRequest() {
    }
    
    private String getSysDate() {
        SimpleDateFormat f = new SimpleDateFormat();
        StringBuffer out = new StringBuffer();
        f.applyPattern("yyyy-MM-dd");
        f.format(new Date(), out, new FieldPosition(1));
        return out.toString();
    }
    
    public String createSalesReciptAddXML(ArrayList salesReciptContent, String paymentMode, String userCode){
        Debug.print("createSalesReciptAddXML() called :");
        
        StringBuffer accountAddXML = new StringBuffer();
        
        try{
            accountAddXML.append("<?xml version=\"1.0\"?>\n");
            accountAddXML.append("<?qbxml version=\"2.0\"?>\n");
            
            accountAddXML.append("<QBXML>\n");
            accountAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
            accountAddXML.append("\t\t<SalesReceiptAddRq>\n");
            
            String classRef = "";
            String tempRef = "";
           // int refNo = 1000;
            
//            if(salesReciptContent!=null && salesReciptContent.size()!=0) {
//                HLCAccTransactionVO accDet = new HLCAccTransactionVO();
//                accDet = (com.hlcaccounts.util.HLCAccTransactionVO)salesReciptContent.get(0);
//                classRef = accDet.getClass_Typ();
//            }
            
            
            accountAddXML.append("\t\t\t<SalesReceiptAdd>\n");
            
            accountAddXML.append("<CustomerRef>");
            accountAddXML.append("<FullName>");
            accountAddXML.append("Member");
            accountAddXML.append("</FullName>");
            accountAddXML.append("</CustomerRef>");
            
//            accountAddXML.append("<ClassRef>");
//            accountAddXML.append("<FullName>");
//            accountAddXML.append(classRef);
//            accountAddXML.append("</FullName>");
//            accountAddXML.append("</ClassRef>");
            
            accountAddXML.append("<TxnDate>");
            accountAddXML.append(getSysDate());
            accountAddXML.append("</TxnDate>");
            
            if(tempRef!=null && tempRef.trim().length()!=0){
                accountAddXML.append("<TemplateRef>");
                accountAddXML.append(tempRef);
                accountAddXML.append("</TemplateRef>");
            }
            
            //String userCode = (String)session.getAttribute("userCode");
            
            if(userCode!=null && userCode.trim().length()!=0){
                accountAddXML.append("\t\t\t\t<RefNumber>");
                accountAddXML.append(userCode);
                accountAddXML.append("</RefNumber>");
            }
            
            /*
            accountAddXML.append("\t\t\t\t<PaymentMethodRef>");
            accountAddXML.append("<FullName>");
            accountAddXML.append(paymentMode);
            accountAddXML.append("</FullName>");
            accountAddXML.append("</PaymentMethodRef>\n");
            */
            
            Iterator it = salesReciptContent.iterator();
            while(it.hasNext()) {
                HLCMemberShipTransactionVO accDet = (HLCMemberShipTransactionVO)it.next();
                
                String itemRef = accDet.getItemNo();
                double tmpamt = accDet.getAmount();
                
                String amt = "";
                String dblamt = "0.00";
                if(tmpamt!=0){
                    amt = String.valueOf(tmpamt);
                    
                    //System.out.println("tmpamt" + tmpamt);
                    DecimalFormat result  = new DecimalFormat("0.00");
                    
                    if(amt!=null && amt.trim().length()!=0){
                       // System.out.print(Double.parseDouble(amt));
                        dblamt = result.format(Double.parseDouble(amt));
                    }
                }
                
                accountAddXML.append("\t\t\t\t<SalesReceiptLineAdd>");
                
                accountAddXML.append("\t\t\t\t<ItemRef>");
                accountAddXML.append("<FullName>");
                accountAddXML.append(itemRef);
                accountAddXML.append("</FullName>");
                accountAddXML.append("</ItemRef>\n");
                
               /* if(accDet.getAccountName()!=null && accDet.getAccountName().trim().length()!=0){
                    accountAddXML.append("\t\t\t\t<Desc>");
                            accountAddXML.append(accDet.getAccountName());
                    accountAddXML.append("</Desc>\n");
                }
                **/
                
                accountAddXML.append("<ClassRef>");
                accountAddXML.append("<FullName>");
                accountAddXML.append(accDet.getAccountClass());
                accountAddXML.append("</FullName>");
                accountAddXML.append("</ClassRef>");
                
                
               
                
               /* 
                accountAddXML.append("\t\t\t\t<Quantity>");
                        accountAddXML.append(accDet.getQuantity());
                accountAddXML.append("</Quantity>\n");
                 */
                
                accountAddXML.append("\t\t\t\t<Amount>");
                accountAddXML.append(dblamt);
                accountAddXML.append("</Amount>\n");
                
                accountAddXML.append("</SalesReceiptLineAdd>\n");
            }
            accountAddXML.append("\t\t\t</SalesReceiptAdd>\n");
            
            accountAddXML.append("\t\t</SalesReceiptAddRq>\n");
            accountAddXML.append("\t</QBXMLMsgsRq>\n");
            accountAddXML.append("</QBXML>\n");
            
        } catch(Exception e) {
            Debug.print("Error in createSalesReciptAddXML :"+e);
        }
        return accountAddXML.toString();
    }
}
