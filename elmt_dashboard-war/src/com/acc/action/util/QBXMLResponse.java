/*
 * QBXMLResponse.java
 *
 * Created on May 14, 2007, 10:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.acc.action.util;
/*
 * imports for XML/DOM
 */
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import org.xml.sax.*;
//import org.apache.axis.utils.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
/**
 *
 * @author punitha
 */
public class QBXMLResponse {
    
    /** Creates a new instance of QBXMLResponse */
    public QBXMLResponse() {
    }
    
    public class QBXMLParser {
        
        
     /*
      * set up a DOM parser to parse xml and return the resulting DOM document
      */
        
        private Document parseXML(String xml) {
            Document doc = null;
            try {
                javax.xml.parsers.DocumentBuilder parser;
                javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
                // First, parse what we have...
                parser = factory.newDocumentBuilder();
                doc = parser.parse(new org.xml.sax.InputSource(new StringReader(xml)));
            } catch (Exception e) {
                System.out.println("parseXML caught exception: " + e.toString());
                doc = null;
            }
            return doc;
        }
        
    /*
     * Returns a short description of the servlet.
     */
        public String getServletInfo() {
            return "SDKTest written as a Java Servlet";
        }
        
    /*
     * Generate a string representation of a DOM document tree
     */
        private void xmlwalk(Node node, StringBuffer buf) {
            int type = node.getNodeType();
            switch(type) {
                
                case Node.DOCUMENT_NODE: {
                    System.out.println("Node Type:" + type);
                    buf.append("<?xml version=\"1.0\"?>\n");
                    break;
                }//end of document
                
                case Node.ELEMENT_NODE: {
                    buf.append('<' + node.getNodeName() );
                    NamedNodeMap nnm = node.getAttributes();
                    if(nnm != null ) {
                        int len = nnm.getLength() ;
                        Attr attr;
                        for ( int i = 0; i < len; i++ ) {
                            attr = (Attr)nnm.item(i);
                            buf.append(' '
                                    + attr.getNodeName()
                                    + "=\""
                                    + attr.getNodeValue()
                                    +  '"' );
                        }
                    }
                    buf.append(">");
                    break;
                }//end of element
                
                case Node.ENTITY_REFERENCE_NODE: {
                    buf.append('&' + node.getNodeName() + ';' );
                    break;
                }//end of entity
                
                case Node.CDATA_SECTION_NODE: {
                    buf.append( "<![CDATA["
                            + node.getNodeValue()
                            + "]]>" );
                    break;
                }
                
                case Node.TEXT_NODE: {
                    buf.append(node.getNodeValue());
                    break;
                }
                
                case Node.PROCESSING_INSTRUCTION_NODE: {
                    buf.append("<?"
                            + node.getNodeName() ) ;
                    String data = node.getNodeValue();
                    if ( data != null && data.length() > 0 ) {
                        buf.append(' ');
                        buf.append(data);
                    }
                    buf.append("?>");
                    break;
                }
            }//end of switch
            
            //recurse
            for(Node child = node.getFirstChild();
            child != null; child = child.getNextSibling()) {
                xmlwalk(child,buf);
            }
            
            //without this the ending tags will miss
            if ( type == Node.ELEMENT_NODE ) {
                buf.append("</" + node.getNodeName() + ">");
            }
        }
        
  public boolean getAllSalesReciptDetails(String resXML,HttpServletRequest request, HttpServletResponse response) throws Exception {
            boolean result = false;
            ArrayList objListSalesReceipt = new ArrayList();
            boolean chkResult = checkSession(resXML, request, response);
            if(chkResult==false)
                return false;
            try {
                System.out.println("This is from getAllSalesReciptDetails");
                // parse the response from QuickBooks
                Document doc = parseXML(resXML);
                
                Element top = doc.getDocumentElement();
                Node          node;
                Element       element;
                NamedNodeMap  nnm = null;
                //int len =0;
                // Look for the Signon response
                NodeList nodelist = top.getElementsByTagName("SalesReceiptAddRs");
                int len = nodelist.getLength();
                if(len>0){
                    element = (Element)nodelist.item(0);
                    String dtStatusCode = element.getAttribute("statusCode");
                    System.out.println("Attribute Code:" + dtStatusCode);
                    //Checking StatusCode
                    if (dtStatusCode.compareTo("0") == 0) { // error!
                        NodeList res = element.getElementsByTagName("SalesReceiptRet");
                        int resLen = res.getLength();
                        //System.out.println("	length:" + resLen);
                        for(int i=0;i<resLen;i++){
                            Element element1 = (Element)res.item(i);
                            NodeList children = element1.getChildNodes();
                            SalesReceiptResponseVO objSalesResVO = new SalesReceiptResponseVO();
                            //System.out.println("It is there in AccountResponseVO");
                            for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                                if (child.getNodeType() == child.ELEMENT_NODE) {
                                    String nodeName = child.getNodeName();
                                    String nodeValue = child.getFirstChild().getNodeValue();
                                    //System.out.println("It is there in nodeName" + nodeName);
                                    if(nodeName.equals("TxnID")) objSalesResVO.setTxnID(nodeValue);
                                    if(nodeName.equals("TimeCreated")) objSalesResVO.setTimeCreated(nodeValue);
                                    if(nodeName.equals("TimeModified")) objSalesResVO.setTimeModified(nodeValue);
                                    if(nodeName.equals("EditSequence")) objSalesResVO.setEditSequence(nodeValue);
                                    if(nodeName.equals("TxnNumber")) objSalesResVO.setTxnNumber(nodeValue);
                                    if(nodeName.equals("TxnDate")) objSalesResVO.setTxnDate(nodeValue);
                                    if(nodeName.equals("RefNumber")) objSalesResVO.setSalRefNumber(nodeValue);
                                    if(nodeName.equals("Subtotal")) objSalesResVO.setSubtotal(nodeValue);
                                    if(nodeName.equals("SalesTaxPercentage")) objSalesResVO.setSalesTaxPercentage(nodeValue);
                                    if(nodeName.equals("TotalAmount")) objSalesResVO.setTotalAmount(nodeValue);
//Customer - Ref
                                    if(nodeName.equals("CustomerRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("CustomerRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setCustomerRefListID(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setCustomerRefFullName(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
//BillAddress - Ref
                                    if(nodeName.equals("BillAddress")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("BillAddress");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("Addr1")) objSalesResVO.setBillAddressAddr1(nodeParentValue);
                                                if(nodeParentName.equals("City")) objSalesResVO.setBillAddressCity(nodeParentValue);
                                                if(nodeParentName.equals("State")) objSalesResVO.setBillAddressState(nodeParentValue);
                                                if(nodeParentName.equals("PostalCode")) objSalesResVO.setBillAddressPostalCode(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
//ShipAddress - Ref
                                    if(nodeName.equals("ShipAddress")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("ShipAddress");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("Addr1")) objSalesResVO.setShipAddressAddr1(nodeParentValue);
                                                if(nodeParentName.equals("City")) objSalesResVO.setShipAddressCity(nodeParentValue);
                                                if(nodeParentName.equals("State")) objSalesResVO.setShipAddressState(nodeParentValue);
                                                if(nodeParentName.equals("PostalCode")) objSalesResVO.setShipAddressPostalCode(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
//PaymentMethodRef - Ref
                                    if(nodeName.equals("PaymentMethodRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("PaymentMethodRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setPaymentMethodRefListID(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setPaymentMethodFullName(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
                                    
                                    
//DepositToAccountRef - Ref
                                    if(nodeName.equals("DepositToAccountRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("DepositToAccountRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setDepositToAccountRefListID(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setDepositToAccountRefFullName(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
//SalesReceiptLineRet - Ref
                                    if(nodeName.equals("SalesReceiptLineRet")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("SalesReceiptLineRet");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("TxnLineID")) objSalesResVO.setSalesReceiptLineRetTxnLineID(nodeParentValue);
                                                if(nodeParentName.equals("Desc")) objSalesResVO.setSalesReceiptLineRetDesc(nodeParentValue);
                                                if(nodeParentName.equals("Amount")) objSalesResVO.setSalesReceiptLineRetAmount(nodeParentValue);
                                                //ItemRef - Ref
                                                if(nodeParentName.equals("ItemRef")){
                                                    //System.out.println("It is there in ItemRef");
                                                    //Element element11 = null;
                                                    NodeList resParentRefItem = element1.getElementsByTagName("ItemRef");
                                                    Element element21 = (Element)resParentRefItem.item(0);
                                                    NodeList children21 = element21.getChildNodes();
                                                    //System.out.println("It is there in children2" + children21);
                                                    for (Node child11 = element21.getFirstChild();
                                                    child11 != null;
                                                    child11 = child11.getNextSibling()) {
                                                        if (child11.getNodeType() == child11.ELEMENT_NODE) {
                                                            String nodeParentItemName = child11.getNodeName();
                                                            String nodeParentItemValue = child11.getFirstChild().getNodeValue();
                                                            
                                                            if(nodeParentItemName.equals("ListID")) objSalesResVO.setSalesReceiptLineRetItemRefListID(nodeParentItemValue);
                                                            if(nodeParentItemName.equals("FullName")) objSalesResVO.setSalesReceiptLineRetItemRefFullName(nodeParentItemValue);
                                                        }
                                                    }//End of For
                                                    
                                                }// End of If
                                            }
                                        }//End of For
                                        
                                    }// End of If
                                    
                                    //if(nodeName.equals("Balance")) objSalesResVO.setBalance(nodeValue);
                                    //if(nodeName.equals("TotalBalance")) objSalesResVO.setTotalBalance(nodeValue);
                                    //System.out.println("End of First Loop"  + i );
                                }//End of If.
                            }//End of For
                            System.out.println("Success2");
                            objListSalesReceipt.add(objSalesResVO);
                            System.out.println("\n\nObject Value:" + objSalesResVO);
                        }//End of For
                        result = true;
                        request.setAttribute("salesReceiptList",objListSalesReceipt);
                    } else{
                        String errorReport = element.getAttribute("statusMessage");
                        System.out.println("Response is failure:" + errorReport);
                        request.setAttribute("errMsg", errorReport);
                    }
                }   
                return result;
            } catch (Exception e) {
                System.out.println("getAllAccountDetails Caught exception: " + e.toString());
            }
            return result;
        }
        
        
        public boolean getSalesReceiptDetails(String resXML,HttpServletRequest request, HttpServletResponse response, String salesResceiptResponse) throws Exception {
            boolean result = false;
            SalesReceiptResponseVO objSalesResVO = new SalesReceiptResponseVO();
            boolean chkResult = checkSession(resXML, request, response);
            if(chkResult==false)
                return false;
            try {
                System.out.println("This is from getSalesReceiptDetails");
                // parse the response from QuickBooks
                Document doc = parseXML(resXML);
                
                Element top = doc.getDocumentElement();
                Node          node;
                Element       element;
                NamedNodeMap  nnm = null;
                //int len =0;
                // Look for the Signon response
                NodeList nodelist = top.getElementsByTagName(salesResceiptResponse);
                int len = nodelist.getLength();
                if(len>0){
                    element = (Element)nodelist.item(0);
                    String dtStatusCode = element.getAttribute("statusCode");
                    System.out.println("Attribute Code:" + dtStatusCode);
                    //Checking StatusCode
                    if (dtStatusCode.compareTo("0") == 0) { // error!
                        NodeList res = element.getElementsByTagName("SalesReceiptRet");
                        int resLen = res.getLength();
                        //System.out.println("	length:" + resLen);
                        for(int i=0;i<resLen;i++){
                            Element element1 = (Element)res.item(i);
                            NodeList children = element1.getChildNodes();
                            //System.out.println("It is there in AccountResponseVO");
                            for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                                if (child.getNodeType() == child.ELEMENT_NODE) {
                                    String nodeName = child.getNodeName();
                                    String nodeValue = child.getFirstChild().getNodeValue();
                                    //System.out.println("It is there in nodeName" + nodeName);
                                    if(nodeName.equals("TxnID")) objSalesResVO.setTxnID(nodeValue);
                                    if(nodeName.equals("TimeCreated")) objSalesResVO.setTimeCreated(nodeValue);
                                    if(nodeName.equals("TimeModified")) objSalesResVO.setTimeModified(nodeValue);
                                    if(nodeName.equals("EditSequence")) objSalesResVO.setEditSequence(nodeValue);
                                    if(nodeName.equals("TxnNumber")) objSalesResVO.setTxnNumber(nodeValue);
                                    if(nodeName.equals("TxnDate")) objSalesResVO.setTxnDate(nodeValue);
                                    if(nodeName.equals("RefNumber")) objSalesResVO.setSalRefNumber(nodeValue);
                                    if(nodeName.equals("Subtotal")) objSalesResVO.setSubtotal(nodeValue);
                                    if(nodeName.equals("SalesTaxPercentage")) objSalesResVO.setSalesTaxPercentage(nodeValue);
                                    if(nodeName.equals("TotalAmount")) objSalesResVO.setTotalAmount(nodeValue);
                                    if(nodeName.equals("IsPending")) objSalesResVO.setTotalAmount(nodeValue);
                                    if(nodeName.equals("DueDate")) objSalesResVO.setTotalAmount(nodeValue);
                                    if(nodeName.equals("ShipDate")) objSalesResVO.setTotalAmount(nodeValue);
                                    if(nodeName.equals("Subtotal")) objSalesResVO.setTotalAmount(nodeValue);
                                    
                                    
                                    
//Customer - Ref
                                    if(nodeName.equals("CustomerRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("CustomerRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setCustomerRefListID(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setCustomerRefFullName(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
//BillAddress - Ref
                                    if(nodeName.equals("ClassRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("ClassRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setCustomerRefListID(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setCustomerRefFullName(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
                                    if(nodeName.equals("BillAddress")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("BillAddress");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("Addr1")) objSalesResVO.setBillAddressAddr1(nodeParentValue);
                                                if(nodeParentName.equals("City")) objSalesResVO.setBillAddressCity(nodeParentValue);
                                                if(nodeParentName.equals("State")) objSalesResVO.setBillAddressState(nodeParentValue);
                                                if(nodeParentName.equals("PostalCode")) objSalesResVO.setBillAddressPostalCode(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
                                    
                                     if(nodeName.equals("ItemSalesTaxRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("ItemSalesTaxRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setBillAddressAddr1(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setBillAddressCity(nodeParentValue);
                                                
                                            }
                                        }//End of For
                                        
                                    }// End of If
                                     if(nodeName.equals("CustomerSalesTaxCodeRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("CustomerSalesTaxCodeRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setBillAddressAddr1(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setBillAddressCity(nodeParentValue);
                                                
                                            }
                                        }//End of For
                                        
                                    }// End of If
 //DepositToAccountRef - Ref
                                    if(nodeName.equals("DepositToAccountRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("DepositToAccountRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setDepositToAccountRefListID(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setDepositToAccountRefFullName(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
//ShipAddress - Ref 
                                    if(nodeName.equals("ShipAddress")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("ShipAddress");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("Addr1")) objSalesResVO.setShipAddressAddr1(nodeParentValue);
                                                if(nodeParentName.equals("City")) objSalesResVO.setShipAddressCity(nodeParentValue);
                                                if(nodeParentName.equals("State")) objSalesResVO.setShipAddressState(nodeParentValue);
                                                if(nodeParentName.equals("PostalCode")) objSalesResVO.setShipAddressPostalCode(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
//PaymentMethodRef - Ref
                                    if(nodeName.equals("PaymentMethodRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("PaymentMethodRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objSalesResVO.setPaymentMethodRefListID(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objSalesResVO.setPaymentMethodFullName(nodeParentValue);
                                            }
                                        }//End of For
                                        
                                    }// End of If
                                    
                                    

//SalesReceiptLineRet - Ref
                                    if(nodeName.equals("SalesReceiptLineRet")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("SalesReceiptLineRet");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("TxnLineID")) objSalesResVO.setSalesReceiptLineRetTxnLineID(nodeParentValue);
                                                if(nodeParentName.equals("Desc")) objSalesResVO.setSalesReceiptLineRetDesc(nodeParentValue);
                                                if(nodeParentName.equals("Rate")) objSalesResVO.setSalesReceiptLineRetAmount(nodeParentValue);
                                                //ItemRef - Ref
                                                if(nodeParentName.equals("ItemRef")){
                                                    //System.out.println("It is there in ItemRef");
                                                    //Element element11 = null;
                                                    NodeList resParentRefItem = element1.getElementsByTagName("ItemRef");
                                                    Element element21 = (Element)resParentRefItem.item(0);
                                                    NodeList children21 = element21.getChildNodes();
                                                    //System.out.println("It is there in children2" + children21);
                                                    for (Node child11 = element21.getFirstChild();
                                                    child11 != null;
                                                    child11 = child11.getNextSibling()) {
                                                        if (child11.getNodeType() == child11.ELEMENT_NODE) {
                                                            String nodeParentItemName = child11.getNodeName();
                                                            String nodeParentItemValue = child11.getFirstChild().getNodeValue();
                                                            
                                                            if(nodeParentItemName.equals("ListID")) objSalesResVO.setSalesReceiptLineRetItemRefListID(nodeParentItemValue);
                                                            if(nodeParentItemName.equals("FullName")) objSalesResVO.setSalesReceiptLineRetItemRefFullName(nodeParentItemValue);
                                                        }
                                                    }//End of For
                                                    
                                                }// End of If
                                            }
                                        }//End of For
                                        
                                    }// End of If
                                    
                                }//End of If.
                            }//End of For
                            System.out.println("Success2");
                            //System.out.println("\n\nObject Value:" + objSalesResVO);
                        }//End of For
                        result = true;
                        request.setAttribute("salesReceiptDetails",objSalesResVO);
                    } else{
                        String errorReport = element.getAttribute("statusMessage");
                        System.out.println("Response is failure:" + errorReport);
                        request.setAttribute("errMsg", errorReport);
                    }
                }
            } catch(Exception e){
                System.out.println("Excption ");
            }
            return result;
        }
        
        public boolean checkSession(String resXML, HttpServletRequest request, HttpServletResponse response){
            boolean result = true;
            try {
                System.out.println("	This is from checkSession");
                // parse the response from QuickBooks
                Document doc = parseXML(resXML);
                
                Element top = doc.getDocumentElement();
                Node          node;
                Element       element;
                NamedNodeMap  nnm = null;
                //int len =0;
                
                // Look for the Signon response
                NodeList nodelist = top.getElementsByTagName("SignonTicketRs");
                int len = nodelist.getLength();
                if(len>0){
                    element = (Element)nodelist.item(0);
                    String dtStatusCode = element.getAttribute("statusCode");
                    System.out.println("Attribute Code:" + dtStatusCode);
                    
                    //Checking StatusCode
                    if (dtStatusCode.compareTo("0") == 0) { // error!
                        System.out.println("	Status:" + dtStatusCode);
                        result = true;
                    } else{
                        String errorReport = element.getAttribute("statusMessage");
                        System.out.println("	Error in checkSession:" + errorReport);
                        request.setAttribute("errMsg", errorReport);
                        result = false;
                    }
                } else{
                    NodeList nodeCert = top.getElementsByTagName("SignonAppCertRs");
                    int lenCert = nodeCert.getLength();
                    if(lenCert>0){
                        element = (Element)nodeCert.item(0);
                        String dtStatusCode = element.getAttribute("statusCode");
                        System.out.println("Attribute Code:" + dtStatusCode);
                        
                        //Checking StatusCode
                        if (dtStatusCode.compareTo("0") == 0) { // error!
                            System.out.println("	Status:" + dtStatusCode);
                            result = true;
                        } else{
                            String errorReport = element.getAttribute("statusMessage");
                            System.out.println("	Error in checkSession:" + errorReport);
                            request.setAttribute("errMsg", errorReport);
                            result = false;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("checkSession Caught exception: " + e.toString());
            }
            System.out.println("	Error Result in checkSession:" + result);
            return result;
        }
        
       
        
    }
    
}
