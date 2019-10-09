/*
 * QBXMLParser.java
 *
 * Created on May 30, 2007, 1:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcqbxml.parser;

/**
 *
 * @author Suresh
 */

/*
 * imports for XML/DOM
 */
import com.hlcaccounts.util.HLCAccountResponseVO;
import com.hlcaccounts.util.HLCCustomerResponseVO;
import com.hlcaccounts.util.Debug;
import com.hlcaccounts.util.HLCSalesReceiptResponseVO;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Attr;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.naming.*;
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import java.text.*;
import java.util.Date;


/**
 *
 * @author suresh
 * @version
 */

public class HLCQBXMLParser {
     /*
      * set up a DOM parser to parse xml and return the resulting DOM document
      */
    private HLCMahanadhiSessionRemote getRemote(){
        HLCMahanadhiSessionRemote remote = null;
        try{
            String name = "ejb/HLCMahanadhiSessionBean";
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup(name);
            HLCMahanadhiSessionRemoteHome home = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCMahanadhiSessionRemoteHome.class);
            remote = home.create();
        } catch(Exception e){
            Debug.print("Exception QBXMLParser.getRemote():" + e.getMessage());
        }
        return remote;
    }
    
    public static Context getInitialContext() throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
    
    
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
    
    
    public boolean getAccountDetails(String resXML, HttpServletRequest request, HttpServletResponse response) {
        HLCAccountResponseVO objAccResVO = new HLCAccountResponseVO();
        boolean result = false;
        boolean chkResult = checkSession(resXML, request, response);
        if(chkResult==true){
            try {
                System.out.println("This is from getAccountDetails");
                // parse the response from QuickBooks
                Document doc = parseXML(resXML);
                Element top = doc.getDocumentElement();
                Node          node;
                Element       element;
                NamedNodeMap  nnm = null;
                //int len =0;
                // Look for the Signon response
                NodeList nodelist = top.getElementsByTagName("AccountQueryRs");
                int len = nodelist.getLength();
                if(len>0){
                    element = (Element)nodelist.item(0);
                    String dtStatusCode = element.getAttribute("statusCode");
                    System.out.println("Attribute Code:" + dtStatusCode);
                    //Checking StatusCode
                    if (dtStatusCode.compareTo("0") == 0) { // error!
                        NodeList res = element.getElementsByTagName("AccountRet");
                        int resLen = res.getLength();
                        System.out.println("resLen:" + resLen);
                        for(int i=0;i<resLen;i++){
                            Element element1 = (Element)res.item(i);
                            NodeList children = element1.getChildNodes();
                            //System.out.println("It is there in AccountResponseVO");
                            for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                                if (child.getNodeType() == child.ELEMENT_NODE) {
                                    String nodeName = child.getNodeName();
                                    String nodeValue = child.getFirstChild().getNodeValue();
                                    //System.out.println("It is there in nodeName" + nodeName);
                                    if(nodeName.equals("ListID")) objAccResVO.setListId(nodeValue);
                                    if(nodeName.equals("TimeCreated")) objAccResVO.setTimeCreated(nodeValue);
                                    if(nodeName.equals("TimeModified")) objAccResVO.setTimeModified(nodeValue);
                                    if(nodeName.equals("EditSequence")) objAccResVO.setEditSequence(nodeValue);
                                    if(nodeName.equals("Name")) objAccResVO.setName(nodeValue);
                                    if(nodeName.equals("FullName")) objAccResVO.setFullName(nodeValue);
                                    if(nodeName.equals("Sublevel")) objAccResVO.setSublevel(nodeValue);
                                    if(nodeName.equals("AccountType")) objAccResVO.setAccountType(nodeValue);
                                    if(nodeName.equals("DetailAccountType")) objAccResVO.setDetailAccountType(nodeValue);
                                    if(nodeName.equals("Desc")) objAccResVO.setDesc(nodeValue);
                                    if(nodeName.equals("ParentRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("ParentRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objAccResVO.setParentRefListId(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objAccResVO.setParentFullName(nodeParentValue);
                                            }
                                        }
                                    }
                                    if(nodeName.equals("Balance")) objAccResVO.setBalance(nodeValue);
                                    if(nodeName.equals("TotalBalance")) objAccResVO.setTotalBalance(nodeValue);
                                    //System.out.println("End of First Loop"  + i );
                                }//End of If.
                            }//End of For
                            //System.out.println("Success2");
                            //objListAccount.add(objAccResVO);
                            System.out.println("Object Value:" + objAccResVO);
                        }//End of For
                        result = true;
                        request.setAttribute("accountDetails",objAccResVO);
                    } else{
                        System.out.println("Response is failure");
                        String errorReport = element.getAttribute("statusMessage");
                        request.setAttribute("errMsg", errorReport);
                    }
                }
            } catch (Exception e) {
                System.out.println("getAccountDetails Caught exception: " + e.toString());
            }
        }
        return result;
    }
    
    public boolean getAllAccountDetails(String resXML,HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean result = false;
        ArrayList objListAccount = new ArrayList();
        boolean chkResult = checkSession(resXML, request, response);
        if(chkResult==false)
            return false;
        try {
            System.out.println("This is from getAllAccountDetails");
            // parse the response from QuickBooks
            Document doc = parseXML(resXML);
            
            Element top = doc.getDocumentElement();
            Node          node;
            Element       element;
            NamedNodeMap  nnm = null;
            //int len =0;
            // Look for the Signon response
            NodeList nodelist = top.getElementsByTagName("AccountQueryRs");
            int len = nodelist.getLength();
            if(len>0){
                element = (Element)nodelist.item(0);
                String dtStatusCode = element.getAttribute("statusCode");
                System.out.println("Attribute Code:" + dtStatusCode);
                //Checking StatusCode
                if (dtStatusCode.compareTo("0") == 0) { // error!
                    NodeList res = element.getElementsByTagName("AccountRet");
                    int resLen = res.getLength();
                    for(int i=0;i<resLen;i++){
                        Element element1 = (Element)res.item(i);
                        NodeList children = element1.getChildNodes();
                        HLCAccountResponseVO objAccResVO = new HLCAccountResponseVO();
                        //System.out.println("It is there in AccountResponseVO");
                        for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                            if (child.getNodeType() == child.ELEMENT_NODE) {
                                String nodeName = child.getNodeName();
                                String nodeValue = child.getFirstChild().getNodeValue();
                                //System.out.println("It is there in nodeName" + nodeName);
                                if(nodeName.equals("ListID")) objAccResVO.setListId(nodeValue);
                                if(nodeName.equals("TimeCreated")) objAccResVO.setTimeCreated(nodeValue);
                                if(nodeName.equals("TimeModified")) objAccResVO.setTimeModified(nodeValue);
                                if(nodeName.equals("EditSequence")) objAccResVO.setEditSequence(nodeValue);
                                if(nodeName.equals("Name")) objAccResVO.setName(nodeValue);
                                if(nodeName.equals("FullName")) objAccResVO.setFullName(nodeValue);
                                if(nodeName.equals("Sublevel")) objAccResVO.setSublevel(nodeValue);
                                if(nodeName.equals("AccountType")) objAccResVO.setAccountType(nodeValue);
                                if(nodeName.equals("DetailAccountType")) objAccResVO.setDetailAccountType(nodeValue);
                                if(nodeName.equals("Desc")) objAccResVO.setDesc(nodeValue);
                                if(nodeName.equals("ParentRef")){
                                    //System.out.println("It is there in ParentRef");
                                    NodeList resParentRef = element1.getElementsByTagName("ParentRef");
                                    Element element2 = (Element)resParentRef.item(0);
                                    NodeList children2 = element2.getChildNodes();
                                    //System.out.println("It is there in children2" + children2);
                                    for (Node child1 = element2.getFirstChild();
                                    child1 != null;
                                    child1 = child1.getNextSibling()) {
                                        if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                            String nodeParentName = child1.getNodeName();
                                            String nodeParentValue = child1.getFirstChild().getNodeValue();
                                            if(nodeParentName.equals("ListID")) objAccResVO.setParentRefListId(nodeParentValue);
                                            if(nodeParentName.equals("FullName")) objAccResVO.setParentFullName(nodeParentValue);
                                        }
                                    }
                                }
                                if(nodeName.equals("Balance")) objAccResVO.setBalance(nodeValue);
                                if(nodeName.equals("TotalBalance")) objAccResVO.setTotalBalance(nodeValue);
                                //System.out.println("End of First Loop"  + i );
                            }//End of If.
                        }//End of For
                        //System.out.println("Success2");
                        objListAccount.add(objAccResVO);
                        System.out.println("Object Value:" + objAccResVO);
                    }//End of For
                    result = true;
                    request.setAttribute("accList",objListAccount);
                } else{
                    String errorReport = element.getAttribute("statusMessage");
                    System.out.println("Response is failure:" + errorReport);
                    request.setAttribute("errMsg", errorReport);
                }
            } else{
                System.out.println("This is from AccountAddRs");
                NodeList nodelistAdd = top.getElementsByTagName("AccountAddRs");
                int lenRs = nodelistAdd.getLength();
                if(lenRs>0){
                    element = (Element)nodelistAdd.item(0);
                    String dtStatusCode = element.getAttribute("statusCode");
                    System.out.println("Attribute Code:" + dtStatusCode);
                    //Checking StatusCode
                    if (dtStatusCode.compareTo("0") == 0) { // error!
                        NodeList res = element.getElementsByTagName("AccountRet");
                        int resLen = res.getLength();
                        for(int i=0;i<resLen;i++){
                            Element element1 = (Element)res.item(i);
                            NodeList children = element1.getChildNodes();
                            HLCAccountResponseVO objAccResVO = new HLCAccountResponseVO();
                            //System.out.println("It is there in AccountResponseVO");
                            for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                                if (child.getNodeType() == child.ELEMENT_NODE) {
                                    String nodeName = child.getNodeName();
                                    String nodeValue = child.getFirstChild().getNodeValue();
                                    //System.out.println("It is there in nodeName" + nodeName);
                                    if(nodeName.equals("ListID")) objAccResVO.setListId(nodeValue);
                                    if(nodeName.equals("TimeCreated")) objAccResVO.setTimeCreated(nodeValue);
                                    if(nodeName.equals("TimeModified")) objAccResVO.setTimeModified(nodeValue);
                                    if(nodeName.equals("EditSequence")) objAccResVO.setEditSequence(nodeValue);
                                    if(nodeName.equals("Name")) objAccResVO.setName(nodeValue);
                                    if(nodeName.equals("FullName")) objAccResVO.setFullName(nodeValue);
                                    if(nodeName.equals("Sublevel")) objAccResVO.setSublevel(nodeValue);
                                    if(nodeName.equals("AccountType")) objAccResVO.setAccountType(nodeValue);
                                    if(nodeName.equals("DetailAccountType")) objAccResVO.setDetailAccountType(nodeValue);
                                    if(nodeName.equals("Desc")) objAccResVO.setDesc(nodeValue);
                                    if(nodeName.equals("ParentRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("ParentRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objAccResVO.setParentRefListId(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objAccResVO.setParentFullName(nodeParentValue);
                                            }
                                        }
                                    }
                                    if(nodeName.equals("Balance")) objAccResVO.setBalance(nodeValue);
                                    if(nodeName.equals("TotalBalance")) objAccResVO.setTotalBalance(nodeValue);
                                    //System.out.println("End of First Loop"  + i );
                                }//End of If.
                            }//End of For
                            //System.out.println("Success2");
                            objListAccount.add(objAccResVO);
                            System.out.println("Object Value:" + objAccResVO);
                        }//End of For
                        result = true;
                        request.setAttribute("accList",objListAccount);
                    } else{
                        String errorReport = element.getAttribute("statusMessage");
                        System.out.println("Response is failure:" + errorReport);
                        request.setAttribute("errMsg", errorReport);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println("getAllAccountDetails Caught exception: " + e.toString());
        }
        return result;
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
            NodeList nodelist = top.getElementsByTagName("SalesReceiptQueryRs");
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
                        HLCSalesReceiptResponseVO objSalesResVO = new HLCSalesReceiptResponseVO();
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
            } else{
                System.out.println("This is from SalesReceiptQueryRq");
                NodeList nodelistAdd = top.getElementsByTagName("SalesReceiptQueryRq");
                int lenRs = nodelistAdd.getLength();
                if(lenRs>0){
                    element = (Element)nodelistAdd.item(0);
                    String dtStatusCode = element.getAttribute("statusCode");
                    System.out.println("Attribute Code:" + dtStatusCode);
                    //Checking StatusCode
                    if (dtStatusCode.compareTo("0") == 0) { // error!
                        NodeList res = element.getElementsByTagName("SalesReceiptRet");
                        int resLen = res.getLength();
                        for(int i=0;i<resLen;i++){
                            Element element1 = (Element)res.item(i);
                            NodeList children = element1.getChildNodes();
                            HLCAccountResponseVO objAccResVO = new HLCAccountResponseVO();
                            //System.out.println("It is there in AccountResponseVO");
                            for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                                if (child.getNodeType() == child.ELEMENT_NODE) {
                                    String nodeName = child.getNodeName();
                                    String nodeValue = child.getFirstChild().getNodeValue();
                                    //System.out.println("It is there in nodeName" + nodeName);
                                    if(nodeName.equals("ListID")) objAccResVO.setListId(nodeValue);
                                    if(nodeName.equals("TimeCreated")) objAccResVO.setTimeCreated(nodeValue);
                                    if(nodeName.equals("TimeModified")) objAccResVO.setTimeModified(nodeValue);
                                    if(nodeName.equals("EditSequence")) objAccResVO.setEditSequence(nodeValue);
                                    if(nodeName.equals("Name")) objAccResVO.setName(nodeValue);
                                    if(nodeName.equals("FullName")) objAccResVO.setFullName(nodeValue);
                                    if(nodeName.equals("Sublevel")) objAccResVO.setSublevel(nodeValue);
                                    if(nodeName.equals("AccountType")) objAccResVO.setAccountType(nodeValue);
                                    if(nodeName.equals("DetailAccountType")) objAccResVO.setDetailAccountType(nodeValue);
                                    if(nodeName.equals("Desc")) objAccResVO.setDesc(nodeValue);
                                    if(nodeName.equals("ParentRef")){
                                        //System.out.println("It is there in ParentRef");
                                        NodeList resParentRef = element1.getElementsByTagName("ParentRef");
                                        Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                if(nodeParentName.equals("ListID")) objAccResVO.setParentRefListId(nodeParentValue);
                                                if(nodeParentName.equals("FullName")) objAccResVO.setParentFullName(nodeParentValue);
                                            }
                                        }
                                    }
                                    if(nodeName.equals("Balance")) objAccResVO.setBalance(nodeValue);
                                    if(nodeName.equals("TotalBalance")) objAccResVO.setTotalBalance(nodeValue);
                                    //System.out.println("End of First Loop"  + i );
                                }//End of If.
                            }//End of For
                            //System.out.println("Success2");
                            objListSalesReceipt.add(objAccResVO);
                            System.out.println("Object Value:" + objAccResVO);
                        }//End of For
                        result = true;
                        request.setAttribute("accList",objListSalesReceipt);
                    } else{
                        String errorReport = element.getAttribute("statusMessage");
                        System.out.println("Response is failure:" + errorReport);
                        request.setAttribute("errMsg", errorReport);
                    }
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
        HLCSalesReceiptResponseVO objSalesResVO = new HLCSalesReceiptResponseVO();
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
                                    // NodeList res = element.getElementsByTagName("SalesReceiptRet");
                                    
                                    NodeList resParentRef = element1.getElementsByTagName("SalesReceiptLineRet");
                                    int resLineRetLen = resParentRef.getLength();
                                    //System.out.println("	resLineRetLen length:" + resLineRetLen);
                                    for(int j=0;j<resLineRetLen;j++) {
                                        Element element2 = (Element)resParentRef.item(j);
                                        // NodeList children = element1.getChildNodes();
                                        
                                        //Element element2 = (Element)resParentRef.item(0);
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
                                    
                                }//End of for loop(Line Ret)
                                
                            }//End of If.
                        }//End of For
                        System.out.println("Success2");
                        //System.out.println("\n\nObject Value:" + objSalesResVO);
                    }//End of For
                    result = true;
                    request.setAttribute("salesReceiptAddDetails",objSalesResVO);
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
    
    public boolean getSalesReceiptAddDetails(String resXML, HttpServletRequest request, HttpServletResponse response, String userId) throws Exception {
        boolean result = false;
        Debug.print("QBXMlParser.getSalesReceiptAddDetails Calling");
        HLCSalesReceiptResponseVO objSalesResVO = new HLCSalesReceiptResponseVO();
        boolean chkResult = checkStatusMessage(resXML, request, response, "SalesReceiptAddRs");
        // boolean chkResult = true;;
        if(chkResult==false){
            return false;
        } else{
             Date transDate = (Date) request.getAttribute("transDate");
             String paymentMode = (String) request.getAttribute("paymentMode");

            System.out.print("request.transDate in QBXMLParser:"+ transDate);
            System.out.print("request.paymentMode in QBXMLParser:"+ paymentMode);
            try{
                HLCMahanadhiSessionRemote remote = getRemote();
                Date lstdate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
               // sdf.parse()
               // lstdate = (Date)sdf..parse(transDate);
                System.out.print("QBXMLParser inside Loop transDate:"+ transDate);
                System.out.print("QBXMLParser inside Loop paymentMode:"+ paymentMode);
                String ipAddress = request.getRemoteAddr();
                if(transDate!=null && paymentMode!=null) {
                    boolean updateQBSynStatus = remote.updateQBSynStatus(transDate, paymentMode, userId, ipAddress);
                    Debug.print("!!!!!!!!QB Integration Status updateQBSynStatus in getSalesReceiptAddDetails!!!!!!!!:" + updateQBSynStatus);
                }
                //boolean updateQBStatus = remote.updateQBStatus(transDate, paymentMode, false, true);
                //Debug.print("!!!!!!!!QB Integration Status updateQBSynStatus in getSalesReceiptAddDetails!!!!!!!!:" + updateQBStatus);                
            }
            catch (Exception e){
                Debug.print("Exception in getSalesReceiptAddDetails.while Calling getRemote()");
            }
        }
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
                                ArrayList itemList = new ArrayList();
                                if(nodeName.equals("SalesReceiptLineRet")){
                                    //System.out.println("It is there in ParentRef");
                                    // NodeList res = element.getElementsByTagName("SalesReceiptRet");
                                    
                                    NodeList resParentRef = element1.getElementsByTagName("SalesReceiptLineRet");
                                    int resLineRetLen = resParentRef.getLength();
                                    //System.out.println("	resLineRetLen length:" + resLineRetLen);
                                    for(int j=0;j<resLineRetLen;j++) {
                                        Element element2 = (Element)resParentRef.item(j);
                                        // NodeList children = element1.getChildNodes();
                                        
                                        //Element element2 = (Element)resParentRef.item(0);
                                        NodeList children2 = element2.getChildNodes();
                                        //System.out.println("It is there in children2" + children2);
                                        
                                        String txtLineId = "";
                                        String itemRefName = "";
                                        String itemDesc = "";
                                        String itemAmount = "";
                                        String itemClassName = "";
                                        for (Node child1 = element2.getFirstChild();
                                        child1 != null;
                                        child1 = child1.getNextSibling()) {
                                            if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                String nodeParentName = child1.getNodeName();
                                                String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                // Debug.print("Node Name in SalesReceiptLineRet:" + nodeParentName);
                                                if(nodeParentName.equals("TxnLineID")) txtLineId = nodeParentValue;
                                                //objSalesResVO.setSalesReceiptLineRetTxnLineID(nodeParentValue);
                                                if(nodeParentName.equals("Desc")) itemDesc = nodeParentValue;
                                                //objSalesResVO.setSalesReceiptLineRetDesc(nodeParentValue);
                                                if(nodeParentName.equals("Amount")) itemAmount = nodeParentValue;
                                                
                                                if(nodeParentName.equals("ClassRef")){
                                                    // System.out.println("It is there in ClassRef");
                                                    NodeList child1Class = element2.getElementsByTagName("ClassRef");
                                                    Element child2Class = (Element)child1Class.item(0);
                                                    NodeList child3Class = child2Class.getChildNodes();
                                                    // System.out.println("It is there in child2Class" + child2Class);
                                                    for (Node childC1 = child2Class.getFirstChild();
                                                    childC1 != null;
                                                    childC1 = childC1.getNextSibling()) {
                                                        if (childC1.getNodeType() == childC1.ELEMENT_NODE) {
                                                            String nodeParentClassName = childC1.getNodeName();
                                                            String nodeParentClassValue = childC1.getFirstChild().getNodeValue();
                                                            // System.out.println("It is there in ClassRef nodeParentClassName:" + nodeParentClassName);
                                                            if(nodeParentClassName.equals("ListID")) objSalesResVO.setPaymentMethodRefListID(nodeParentClassValue);
                                                            if(nodeParentClassName.equals("FullName")) itemClassName = nodeParentClassValue;
                                                            //objSalesResVO.setPaymentMethodFullName(nodeParentClassValue);
                                                            //Debug.print("itemClassName" + itemClassName);
                                                        }
                                                    }//End of For
                                                }// End of If
                                                
                                                //objSalesResVO.setSalesReceiptLineRetAmount(nodeParentValue);
                                                //ItemRef - Ref
                                                //itemRefName = "";
                                                if(nodeParentName.equals("ItemRef")){
                                                    //System.out.println("It is there in ItemRef");
                                                    //Element element11 = null;
                                                    NodeList resParentRefItem = element2.getElementsByTagName("ItemRef");
                                                    Element element21 = (Element)resParentRefItem.item(0);
                                                    NodeList children21 = element21.getChildNodes();
                                                    //System.out.println("It is there in children2" + children21);
                                                    for (Node child11 = element21.getFirstChild();
                                                    child11 != null;
                                                    child11 = child11.getNextSibling()) {
                                                        if (child11.getNodeType() == child11.ELEMENT_NODE) {
                                                            String nodeParentItemName = child11.getNodeName();
                                                            String nodeParentItemValue = child11.getFirstChild().getNodeValue();
                                                            // Debug.print("===========nodeParentItemValue:======"+ nodeParentItemValue);
                                                            if(nodeParentItemName.equals("ListID")) objSalesResVO.setSalesReceiptLineRetItemRefListID(nodeParentItemValue);
                                                            if(nodeParentItemName.equals("FullName")) itemRefName =  nodeParentItemValue;
                                                            //objSalesResVO.setSalesReceiptLineRetItemRefFullName(nodeParentItemValue);
                                                            
                                                            //Debug.print("===========itemRefName:======"+ itemRefName);
                                                        }
                                                    }//End of For
                                                }// End of If
                                            }
                                        }//End of For
                                        // Debug.print("txtLineId" + txtLineId);
                                        //Debug.print("itemRefName" + itemRefName);
                                        // Debug.print("itemDesc" + itemDesc);
                                        //  Debug.print("itemAmount" + itemAmount);
                                        //Debug.print("itemClassName" + itemClassName);
                                        String salesResRet[] = {txtLineId, itemRefName, itemDesc, itemAmount,  itemClassName};
                                        itemList.add(salesResRet);
                                    }// End of If
                                    objSalesResVO.setSalesReceiptLineRet(itemList);
                                }//End of for loop(Line Ret)
                            }//End of If.
                        }//End of For
                        //System.out.println("Success2");
                        //System.out.println("\n\nObject Value:" + objSalesResVO);
                    }//End of For
                    result = true;
                    request.setAttribute("salesReceiptAddDetails",objSalesResVO);
                    System.out.println("Sucessfully Set in Response VO");
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
    
    public boolean checkStatusMessage(String resXML, HttpServletRequest request, HttpServletResponse response, String tagName){
        boolean result = true;
        try {
            Debug.print("checkStatusMessage");
            // parse the response from QuickBooks
            Document doc = parseXML(resXML);
            Element top = doc.getDocumentElement();
            Node          node;
            Element       element;
            NamedNodeMap  nnm = null;
            //int len =0;
            
            // Look for the Signon response
            NodeList nodelist = top.getElementsByTagName(tagName);
            int len = nodelist.getLength();
            if(len>0){
                element = (Element)nodelist.item(0);
                String dtStatusCode = element.getAttribute("statusCode");
                //System.out.println("Attribute Code:" + dtStatusCode);
                
                //Checking StatusCode
                if (dtStatusCode.compareTo("0") == 0) { // error!
                    System.out.println("	Status:" + dtStatusCode);
                    Date transDate = (Date) request.getAttribute("transDate");
                    String paymentMode = (String) request.getAttribute("paymentMode");
                    //Debug.print("transDate in XMLParser" + transDate);
                    //Debug.print("PaymentMode in XMLParser" + paymentMode);
                    request.setAttribute("transDate", transDate);
                    request.setAttribute("paymentMode", paymentMode);
                    result = true;
                } else{
                    String errorReport = element.getAttribute("statusMessage");
                   // System.out.println("	Error in checkStatusMessage:" + errorReport);
                    request.setAttribute("errMsg", errorReport);
                    result = false;
                }
            } else{
                result = true;
            }
        } catch (Exception e) {
            System.out.println("checkSession Caught exception: " + e.toString());
        }
        System.out.println("	Error Result in checkStatusMessage:" + result);
        return result;
    }
    
    public boolean getErrorCheck(String resXML, HttpServletRequest request, HttpServletResponse response){
        boolean result = true;
        try {
            Debug.print("getErrorCheck Calling");
            // parse the response from QuickBooks
            Document doc = parseXML(resXML);
            
            Element top = doc.getDocumentElement();
            Node          node;
            Element       element;
            NamedNodeMap  nnm = null;
            //int len =0;
            
            // Look for the Signon response
            NodeList nodelist = top.getElementsByTagName("USEA-Error");
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
                    // String errorReport = element.getAttribute("statusCode");
                    System.out.println("	Error in getErrorCheck:" + dtStatusCode);
                    request.setAttribute("errMsg", dtStatusCode);
                    result = false;
                }
            } else{
                Debug.print("USEA-Error Len 0");
                result = true;
            }
        } catch (Exception e) {
            System.out.println("getErrorCheck Caught exception: " + e.toString());
        }
        System.out.println("	getErrorCheck Result::" + result);
        return result;
    }
    
    public boolean getAllCustomerDetails(String resXML,HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean result = false;
        ArrayList objListCustomer = new ArrayList();
        boolean chkResult = checkSession(resXML, request, response);
        if(chkResult==false)
            return false;
        try {
            System.out.println("This is from getAllCustomerDetails");
            // parse the response from QuickBooks
            Document doc = parseXML(resXML);
            
            Element top = doc.getDocumentElement();
            Node          node;
            Element       element;
            NamedNodeMap  nnm = null;
            //int len =0;
            // Look for the Signon response
            NodeList nodelist = top.getElementsByTagName("CustomerQueryRs");
            int len = nodelist.getLength();
            if(len>0){
                element = (Element)nodelist.item(0);
                String dtStatusCode = element.getAttribute("statusCode");
                System.out.println("Attribute Code:" + dtStatusCode);
                //Checking StatusCode
                if (dtStatusCode.compareTo("0") == 0) { // error!
                    NodeList res = element.getElementsByTagName("CustomerRet");
                    int resLen = res.getLength();
                    System.out.println("	length:" + resLen);
                    for(int i=0;i<resLen;i++){
                        Element element1 = (Element)res.item(i);
                        NodeList children = element1.getChildNodes();
                        HLCCustomerResponseVO objCutomerVO = new HLCCustomerResponseVO();
                        //System.out.println("It is there in AccountResponseVO");
                        for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                            if (child.getNodeType() == child.ELEMENT_NODE) {
                                String nodeName = child.getNodeName();
                                String nodeValue = child.getFirstChild().getNodeValue();
                                //System.out.println("It is there in nodeName" + nodeName);
                                if(nodeName.equals("ListID")) objCutomerVO.setListId(nodeValue);
                                if(nodeName.equals("TimeCreated")) objCutomerVO.setTimeCreated(nodeValue);
                                if(nodeName.equals("TimeModified")) objCutomerVO.setTimeModified(nodeValue);
                                if(nodeName.equals("EditSequence")) objCutomerVO.setEditSequence(nodeValue);
                                if(nodeName.equals("Name")) objCutomerVO.setName(nodeValue);
                                if(nodeName.equals("FullName")) objCutomerVO.setFullName(nodeValue);
                                if(nodeName.equals("Sublevel")) objCutomerVO.setSubLevel(nodeValue);
                                if(nodeName.equals("CompanyName")) objCutomerVO.setCompanyName(nodeValue);
                                if(nodeName.equals("Salutation")) objCutomerVO.setSalutation(nodeValue);
                                if(nodeName.equals("FirstName")) objCutomerVO.setFirstName(nodeValue);
                                if(nodeName.equals("LastName")) objCutomerVO.setLastName(nodeValue);
                                if(nodeName.equals("Phone")) objCutomerVO.setPhone(nodeValue);
                                if(nodeName.equals("AltPhone")) objCutomerVO.setAltPhone(nodeValue);
                                if(nodeName.equals("Email")) objCutomerVO.setEmail(nodeValue);
                                if(nodeName.equals("Balance")) objCutomerVO.setBalance(nodeValue);
                                if(nodeName.equals("TotalBalance")) objCutomerVO.setTotalBalance(nodeValue);
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
                                            if(nodeParentName.equals("Addr1")) objCutomerVO.setAddress(nodeParentValue);
                                            if(nodeParentName.equals("City")) objCutomerVO.setCity(nodeParentValue);
                                            if(nodeParentName.equals("State")) objCutomerVO.setState(nodeParentValue);
                                            if(nodeParentName.equals("PostalCode")) objCutomerVO.setPostal(nodeParentValue);
                                            if(nodeParentName.equals("Country")) objCutomerVO.setCountry(nodeParentValue);
                                        }
                                    }//End of For
                                    
                                }// End of If
                                
//TermsRef - Ref
                                if(nodeName.equals("TermsRef")){
                                    //System.out.println("It is there in ParentRef");
                                    NodeList resParentRef = element1.getElementsByTagName("TermsRef");
                                    Element element2 = (Element)resParentRef.item(0);
                                    NodeList children2 = element2.getChildNodes();
                                    //System.out.println("It is there in children2" + children2);
                                    for (Node child1 = element2.getFirstChild();
                                    child1 != null;
                                    child1 = child1.getNextSibling()) {
                                        if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                            String nodeParentName = child1.getNodeName();
                                            String nodeParentValue = child1.getFirstChild().getNodeValue();
                                            if(nodeParentName.equals("ListID")) objCutomerVO.setTermsRefListId(nodeParentValue);
                                            if(nodeParentName.equals("FullName")) objCutomerVO.setTermsRefFullName(nodeParentValue);
                                        }
                                    }//End of For
                                    
                                }// End of If
                                
                            }//End of If.
                        }//End of For
                        System.out.println("Success2");
                        objListCustomer.add(objCutomerVO);
                        System.out.println("\n\nObject Value:" + objCutomerVO);
                    }//End of For
                    result = true;
                    request.setAttribute("customerList",objListCustomer);
                    
                } else{
                    String errorReport = element.getAttribute("statusMessage");
                    System.out.println("Response is failure:" + errorReport);
                    request.setAttribute("errMsg", errorReport);
                }
            }
                        /*else{
                                System.out.println("This is from SalesReceiptQueryRq");
                                NodeList nodelistAdd = top.getElementsByTagName("CustomerAddRs");
                                int lenRs = nodelistAdd.getLength();
                                if(lenRs>0){
                                        element = (Element)nodelistAdd.item(0);
                                        String dtStatusCode = element.getAttribute("statusCode");
                                        System.out.println("Attribute Code:" + dtStatusCode);
                                        //Checking StatusCode
                                        if (dtStatusCode.compareTo("0") == 0) { // error!
                                                NodeList res = element.getElementsByTagName("CustomerRet");
                                                int resLen = res.getLength();
                                                //System.out.println("	length:" + resLen);
                                                for(int i=0;i<resLen;i++){
                                                        Element element1 = (Element)res.item(i);
                                                        NodeList children = element1.getChildNodes();
                                                        CustomerResponseVO objCutomerVO = new CustomerResponseVO();
                                                        //System.out.println("It is there in AccountResponseVO");
                                                        for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                                                                        if (child.getNodeType() == child.ELEMENT_NODE) {
                                                                                String nodeName = child.getNodeName();
                                                                                String nodeValue = child.getFirstChild().getNodeValue();
                                                                                //System.out.println("It is there in nodeName" + nodeName);
                                                                                if(nodeName.equals("ListID")) objCutomerVO.setListId(nodeValue);
                                                                                if(nodeName.equals("TimeCreated")) objCutomerVO.setTimeCreated(nodeValue);
                                                                                if(nodeName.equals("TimeModified")) objCutomerVO.setTimeModified(nodeValue);
                                                                                if(nodeName.equals("EditSequence")) objCutomerVO.setEditSequence(nodeValue);
                                                                                if(nodeName.equals("Name")) objCutomerVO.setName(nodeValue);
                                                                                if(nodeName.equals("FullName")) objCutomerVO.setFullName(nodeValue);
                                                                                if(nodeName.equals("Sublevel")) objCutomerVO.setSubLevel(nodeValue);
                                                                                if(nodeName.equals("CompanyName")) objCutomerVO.setCompanyName(nodeValue);
                                                                                if(nodeName.equals("Salutation")) objCutomerVO.setSalutation(nodeValue);
                                                                                if(nodeName.equals("FirstName")) objCutomerVO.setFirstName(nodeValue);
                                                                                if(nodeName.equals("LastName")) objCutomerVO.setLastName(nodeValue);
                                                                                if(nodeName.equals("Phone")) objCutomerVO.setPhone(nodeValue);
                                                                                if(nodeName.equals("AltPhone")) objCutomerVO.setAltPhone(nodeValue);
                                                                                if(nodeName.equals("Email")) objCutomerVO.setEmail(nodeValue);
                                                                                if(nodeName.equals("Balance")) objCutomerVO.setBalance(nodeValue);
                                                                                if(nodeName.equals("TotalBalance")) objCutomerVO.setTotalBalance(nodeValue);
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
                                                                                                        if(nodeParentName.equals("Addr1")) objCutomerVO.setAddress(nodeParentValue);
                                                                                                        if(nodeParentName.equals("City")) objCutomerVO.setCity(nodeParentValue);
                                                                                                        if(nodeParentName.equals("State")) objCutomerVO.setState(nodeParentValue);
                                                                                                        if(nodeParentName.equals("PostalCode")) objCutomerVO.setPostal(nodeParentValue);
                                                                                                }
                                                                                        }//End of For
                         
                                                                                }// End of If
                         
//TermsRef - Ref
                                                                                if(nodeName.equals("TermsRef")){
                                                                                        //System.out.println("It is there in ParentRef");
                                                                                        NodeList resParentRef = element1.getElementsByTagName("TermsRef");
                                                                                        Element element2 = (Element)resParentRef.item(0);
                                                                                        NodeList children2 = element2.getChildNodes();
                                                                                        //System.out.println("It is there in children2" + children2);
                                                                                        for (Node child1 = element2.getFirstChild();
                                                                                                 child1 != null;
                                                                                                 child1 = child1.getNextSibling()) {
                                                                                                if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                                                                                        String nodeParentName = child1.getNodeName();
                                                                                                        String nodeParentValue = child1.getFirstChild().getNodeValue();
                                                                                                        if(nodeParentName.equals("ListID")) objCutomerVO.setTermsRefListId(nodeParentValue);
                                                                                                        if(nodeParentName.equals("FullName")) objCutomerVO.setTermsRefFullName(nodeParentValue);
                                                                                                }
                                                                                        }//End of For
                         
                                                                                }// End of If
                         
                                                                        }//End of If.
                                                        }//End of For
                                                        //System.out.println("Success2");
                                                        objListCustomer.add(objCutomerVO);
                                                        System.out.println("\n\nObject Value:" + objCutomerVO);
                                                }//End of For
                                                result = true;
                                                request.setAttribute("customerList",objListCustomer);
                                        }
                                        else{
                                                System.out.println("Response is failure");
                                                String errorReport = element.getAttribute("statusMessage");
                                                request.setAttribute("errMsg", errorReport);
                                        }
                                }
                        }
                        return result;
                         */
        } catch (Exception e) {
            System.out.println("getAllAccountDetails Caught exception: " + e.toString());
        }
        return result;
    }
    
    public boolean getCustomerDetails(String resXML,HttpServletRequest request, HttpServletResponse response, String customerString) throws Exception {
        boolean result = false;
        HLCCustomerResponseVO objCutomerVO = new HLCCustomerResponseVO();
        boolean chkResult = checkSession(resXML, request, response);
        if(chkResult==false)
            return false;
        try {
            System.out.println("This is from getCustomerDetails");
            // parse the response from QuickBooks
            Document doc = parseXML(resXML);
            
            Element top = doc.getDocumentElement();
            Node          node;
            Element       element;
            NamedNodeMap  nnm = null;
            //int len =0;
            // Look for the Signon response
            NodeList nodelist = top.getElementsByTagName(customerString.trim());
            int len = nodelist.getLength();
            if(len>0){
                element = (Element)nodelist.item(0);
                String dtStatusCode = element.getAttribute("statusCode");
                System.out.println("Attribute Code:" + dtStatusCode);
                //Checking StatusCode
                if (dtStatusCode.compareTo("0") == 0) { // error!
                    NodeList res = element.getElementsByTagName("CustomerRet");
                    int resLen = res.getLength();
                    System.out.println("	length:" + resLen);
                    for(int i=0;i<resLen;i++){
                        Element element1 = (Element)res.item(i);
                        NodeList children = element1.getChildNodes();
                        //System.out.println("It is there in AccountResponseVO");
                        for (Node child = element1.getFirstChild();child != null;child = child.getNextSibling()) {
                            if (child.getNodeType() == child.ELEMENT_NODE) {
                                String nodeName = child.getNodeName();
                                String nodeValue = child.getFirstChild().getNodeValue();
                                //System.out.println("It is there in nodeName" + nodeName);
                                if(nodeName.equals("ListID")) objCutomerVO.setListId(nodeValue);
                                if(nodeName.equals("TimeCreated")) objCutomerVO.setTimeCreated(nodeValue);
                                if(nodeName.equals("TimeModified")) objCutomerVO.setTimeModified(nodeValue);
                                if(nodeName.equals("EditSequence")) objCutomerVO.setEditSequence(nodeValue);
                                if(nodeName.equals("Name")) objCutomerVO.setName(nodeValue);
                                if(nodeName.equals("FullName")) objCutomerVO.setFullName(nodeValue);
                                if(nodeName.equals("Sublevel")) objCutomerVO.setSubLevel(nodeValue);
                                if(nodeName.equals("CompanyName")) objCutomerVO.setCompanyName(nodeValue);
                                if(nodeName.equals("Salutation")) objCutomerVO.setSalutation(nodeValue);
                                if(nodeName.equals("FirstName")) objCutomerVO.setFirstName(nodeValue);
                                if(nodeName.equals("LastName")) objCutomerVO.setLastName(nodeValue);
                                if(nodeName.equals("Phone")) objCutomerVO.setPhone(nodeValue);
                                if(nodeName.equals("AltPhone")) objCutomerVO.setAltPhone(nodeValue);
                                if(nodeName.equals("Email")) objCutomerVO.setEmail(nodeValue);
                                if(nodeName.equals("Balance")) objCutomerVO.setBalance(nodeValue);
                                if(nodeName.equals("TotalBalance")) objCutomerVO.setTotalBalance(nodeValue);
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
                                            if(nodeParentName.equals("Addr1")) objCutomerVO.setAddress(nodeParentValue);
                                            if(nodeParentName.equals("City")) objCutomerVO.setCity(nodeParentValue);
                                            if(nodeParentName.equals("State")) objCutomerVO.setState(nodeParentValue);
                                            if(nodeParentName.equals("Country")) objCutomerVO.setCountry(nodeParentValue);
                                            if(nodeParentName.equals("PostalCode")) objCutomerVO.setPostal(nodeParentValue);
                                        }
                                    }//End of For
                                    
                                }// End of If
                                
//TermsRef - Ref
                                if(nodeName.equals("TermsRef")){
                                    //System.out.println("It is there in ParentRef");
                                    NodeList resParentRef = element1.getElementsByTagName("TermsRef");
                                    Element element2 = (Element)resParentRef.item(0);
                                    NodeList children2 = element2.getChildNodes();
                                    //System.out.println("It is there in children2" + children2);
                                    for (Node child1 = element2.getFirstChild();
                                    child1 != null;
                                    child1 = child1.getNextSibling()) {
                                        if (child1.getNodeType() == child1.ELEMENT_NODE) {
                                            String nodeParentName = child1.getNodeName();
                                            String nodeParentValue = child1.getFirstChild().getNodeValue();
                                            if(nodeParentName.equals("ListID")) objCutomerVO.setTermsRefListId(nodeParentValue);
                                            if(nodeParentName.equals("FullName")) objCutomerVO.setTermsRefFullName(nodeParentValue);
                                        }
                                    }//End of For
                                    
                                }// End of If
                                
                            }//End of If.
                        }//End of For
                        System.out.println("Success2");
                        System.out.println("\n\nObject Value:" + objCutomerVO);
                    }//End of For
                    result = true;
                    request.setAttribute("customerDetails",objCutomerVO);
                } else{
                    String errorReport = element.getAttribute("statusMessage");
                    System.out.println("Response is failure:" + errorReport);
                    request.setAttribute("errMsg", errorReport);
                }
            }
        } catch (Exception e) {
            System.out.println("getAllAccountDetails Caught exception: " + e.toString());
        }
        return result;
    }
    
}


