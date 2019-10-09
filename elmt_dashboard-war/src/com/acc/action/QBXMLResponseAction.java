/*
 * QBXMLResponseAction.java
 *
 * Created on May 13, 2007, 7:17 PM
 */

package com.acc.action;

import com.hlcaccounts.util.Debug;
import com.hlcqbxml.parser.HLCQBXMLParser;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.util.MessageResources;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.Date;
import java.text.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author punitha/Suresh
 * @version
 */

public class QBXMLResponseAction extends Action {
    
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
        HttpSession session=request.getSession(true);
        
        String userId=(String)session.getAttribute("userId");
        Debug.print("%%%%%% QBXMLResponseAction.userId ^^^^^^^^" + userId);
        String process = request.getParameter("process");
        String reqAtt = request.getParameter("cmd");
        
        if(process==null) process = "";
        HLCQBXMLParser parser = new HLCQBXMLParser();
        
        Debug.print("QBXMLResponseAction Process:" + process);
        try{
            if(process.equalsIgnoreCase("QBSARes")){
                Debug.print("QBXMLResponseAction.QBSARes");
                MessageResources mr=getResources(request);
                String xmlPath = mr.getMessage("QBXMLPath");
                Debug.print("xmlPath:" + xmlPath);
                
                String responseFile = xmlPath + "\\qbXmlResponse.xml";
                Debug.print("Response XML Path:" + responseFile);
                File file = new File(responseFile);
                Debug.print("File Existing Checking in QBXMLResponseAction.QBSARes:"+ file.exists());
                
                String salesReceiptAddXML = "";
                if(file.exists()) {
                    //BufferedReader br = new BufferedReader(new FileReader("E:\\QB_HLC_XML\\XmlResponse\\qbXmlResponse.xml"));
                    BufferedReader br = new BufferedReader(new FileReader(responseFile));
                    String strReadLine ="";
                    while((strReadLine=br.readLine())!=null){
                        salesReceiptAddXML = salesReceiptAddXML + strReadLine;
                        //Debug.print(strReadLine);
                    }
                    br.close();
                }
                
                Date transDate = null;
                String paymentMode = "";
                if(reqAtt!=null){
                    Debug.print("######## This is from req true part #########");
                    String tempTransDate = request.getParameter("td");
                    paymentMode = request.getParameter("pm");
                    Debug.print("transDate :"+tempTransDate);
                    Debug.print("paymentMode :"+paymentMode);
                    if(tempTransDate!=null){
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
                        transDate = (Date)sdf.parse(tempTransDate);
                    }
                } else{
                    Debug.print("######## This is from req else Part #########");
                    transDate = (Date) request.getAttribute("transDate");
                    paymentMode = (String) request.getAttribute("paymentMode");
                }
                //Debug.print("request.transDate in QBXMLResponse:"+ transDate);
                //Debug.print("request.paymentMode in QBXMLResponse:"+ paymentMode);
                
                request.setAttribute("transDate", transDate);
                request.setAttribute("paymentMode", paymentMode);
                
                Debug.print("QBXML:" + salesReceiptAddXML);
                
                if(salesReceiptAddXML.trim().length()!=0){
                    boolean errorResult= parser.getErrorCheck(salesReceiptAddXML,request,response);
                    Debug.print("Error Checking Message:" + errorResult);
                    if(errorResult==true){
                        Debug.print("Error Checking Message True");
                        parser.getSalesReceiptAddDetails(salesReceiptAddXML, request,response, userId);
                        return mapping.findForward("QBSalesAddResSuccess");
                    } else{
                        Debug.print("Error Checking Message False");
                        return mapping.findForward("QBSalesAddFailure");
                    }
                } else{
                    Debug.print("salesReceiptAddXML is not Available");
                    return mapping.findForward("QBSalesAddFailure");
                }
            } else{
                return mapping.findForward("QBSalesAddFailure");
            }
        } catch(Exception e){
            Debug.print("Exception while calling QBXMLResponseAction:" + e.getMessage());
        }
        return null;
    }
    
    private void traverseTree(Node node) throws Exception {
        if(node == null) {
            return;
        }
        int type = node.getNodeType();
        System.out.println("Type:" + type);
        
        switch (type) {
            // handle document nodes
            case Node.DOCUMENT_NODE: {
                //System.out.println("==");
                traverseTree(((Document)node).getDocumentElement());
                break;
            }
            
            case Node.DOCUMENT_TYPE_NODE: {
                System.out.println("DOCUMENT_NODE_TYPE");
                //traverseTree(((Document)node).getDocumentElement());
                break;
            }
            // handle element nodes
            case Node.ELEMENT_NODE: {
                String elementName = node.getNodeName();
                System.out.println("Element Name:" + elementName);
                //if(elementName.equals("stock")) {
                //System.out.println("====");
                //}
                NodeList childNodes = node.getChildNodes();
                if(childNodes != null) {
                    int length = childNodes.getLength();
                    for (int loopIndex = 0; loopIndex <length ; loopIndex++){
                        traverseTree(childNodes.item(loopIndex));
                    }
                }
                break;
            }
            // handle text nodes
            case Node.TEXT_NODE: {
                String data = node.getNodeValue().trim();
                System.out.println("TEXT Data:" + data);
                //if((data.indexOf("\n") <0) && (data.length()> 0)) {
                //System.out.println(data);
                //}
            }
        }
    }
    
    public static Document getDocument(String fileName) throws Exception{
        System.out.println(" getDocument() Called");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));
        System.out.println(" getDocument() File Name" + fileName );
        return doc;
    }
    
    /*
    public static void main(String args[]) throws Exception {
        QBXMLResponseAction ds = new QBXMLResponseAction();
        //System.out.println("Main Method Called");
        Document doc = ds.getDocument("D://QB_HLC_XML//XmlRequest/qbxmlResponse.xml");
     
        System.out.println("After Document Read:");
        ds.traverseTree(doc);
/*		Element top = doc.getDocumentElement();
     
                NodeList nl = top.getElementsByTagName("ReceivePaymentRet");
                System.out.println(nl);
     
                //for(int i = 0 ; i<nl.getLength(); i++){
                if(nl.getLength()>0){
                        Element resp = (Element)responses.item(0);
                        NodeList dtResponses = resp.getElementsByTagName("TxnID");
                        //Element e = (Element) nl.item(0);
                        //String statusCode = e.getAttribute("requestID");
                        //Node child = nl.item(i);
                        //NodeList nl1 = child.getChildNodes();
                        //String elementName = child.getNodeName();
     
                        if (dtResponses.getLength() >0) {
                                // got an AppCert response, check the status code for an
                                // error...
                                Element dtResponse = (Element)dtResponses.item(0);
                                String dtStatusCode = dtResponse.getAttribute("statusCode");
                        }
     
                        //Text txt = (Text)e.getFirstChild();
                        System.out.println(elementName);
                        //System.out.println(nl1);
                }
     */
    // ReceivePaymentAddRq.xml file generation.
    //String xml = ds.generateReceivePayment();
    //String xml = ds.generateSalesReceipt();
    //String xml = ds.generateCustomer();
    //System.out.println(xml);
      /*
        try{
            File file = new File("qbxmlResponse.xml");
            FileWriter fw= new FileWriter(file);
            PrintWriter out= new PrintWriter(fw);
            //out.println(xml);
            out.close();
            System.out.println("Sucessfully Generated ReceivePaymentAddRq.xml:");
        } catch(Exception e){
            System.out.println("Exception:" + e);
        }
    }
       */
}
