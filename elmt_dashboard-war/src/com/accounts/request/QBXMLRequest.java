/*
 * QBXMLRequest.java
 *
 * Created on October 11, 2006, 10:02 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.accounts.request;

/**
 *
 * @author suresh
 */
    

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class QBXMLRequest implements Serializable{
    
    /** Creates a new instance of QBXMLRequest */
    public QBXMLRequest() {
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
  
    public String generateReceivePayment() {
    StringBuffer receivePaymentXML = new StringBuffer();

    receivePaymentXML.append("<?xml version=\"1.0\"?>\n");
    receivePaymentXML.append("<?qbxml version=\"2.0\"?>\n");

    receivePaymentXML.append("<QBXML>\n");

        receivePaymentXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");

            receivePaymentXML.append("\t\t<ReceivePaymentAddRq requestID = \"2\">\n");

                receivePaymentXML.append("\t\t\t<ReceivePaymentAdd>\n");

                    receivePaymentXML.append("\t\t\t\t<CustomerRef>\n");
                        receivePaymentXML.append("\t\t\t\t\t<FullName>");
                            receivePaymentXML.append("suresh:digiBlitz");
                        receivePaymentXML.append("</FullName>\n");
                    receivePaymentXML.append("\t\t\t\t</CustomerRef>\n");

                    receivePaymentXML.append("\t\t\t\t<ARAccountRef>\n");
                        receivePaymentXML.append("\t\t\t\t\t<FullName>");
                            receivePaymentXML.append("suresh:digiBlitz");
                        receivePaymentXML.append("</FullName>\n");
                    receivePaymentXML.append("\t\t\t\t</ARAccountRef>\n");


                    receivePaymentXML.append("\t\t\t\t<TxnDate>");
                        receivePaymentXML.append("suresh:digiBlitz");
                    receivePaymentXML.append("</TxnDate>\n");

                    receivePaymentXML.append("\t\t\t\t<RefNumber>");
                        receivePaymentXML.append("suresh:digiBlitz");
                    receivePaymentXML.append("</RefNumber>\n");

                    receivePaymentXML.append("\t\t\t\t<TotalAmount>");
                        receivePaymentXML.append("suresh:digiBlitz");
                    receivePaymentXML.append("</TotalAmount>\n");

                    receivePaymentXML.append("\t\t\t\t<PaymentMethodRef>\n");
                        receivePaymentXML.append("\t\t\t\t\t<FullName>");
                            receivePaymentXML.append("suresh:digiBlitz");
                        receivePaymentXML.append("</FullName>\n");
                    receivePaymentXML.append("\t\t\t\t</PaymentMethodRef>\n");

                    receivePaymentXML.append("\t\t\t\t<DepositToAccountRef>\n");
                        receivePaymentXML.append("\t\t\t\t\t<FullName>");
                            receivePaymentXML.append("suresh:digiBlitz");
                        receivePaymentXML.append("</FullName>\n");
                    receivePaymentXML.append("\t\t\t\t</DepositToAccountRef>\n");

                    receivePaymentXML.append("\t\t\t\t<AppliedToTxnAdd>\n");
                        receivePaymentXML.append("\t\t\t\t\t<TxnID>");
                            receivePaymentXML.append("44F3-1071525107");
                        receivePaymentXML.append("</TxnID>\n");

                        receivePaymentXML.append("\t\t\t\t\t<PaymentAmount>");
                                receivePaymentXML.append("100.00");
                        receivePaymentXML.append("</PaymentAmount>\n");

                        receivePaymentXML.append("\t\t\t\t\t<TxnLineDetail>\n");
                            receivePaymentXML.append("\t\t\t\t\t\t<TxnLineID>");
                                receivePaymentXML.append("44F3-1071525107");
                            receivePaymentXML.append("</TxnLineID>\n");

                            receivePaymentXML.append("\t\t\t\t\t\t<Amount>");
                                receivePaymentXML.append("100.00");
                            receivePaymentXML.append("</Amount>\n");
                        receivePaymentXML.append("\t\t\t\t\t</TxnLineDetail>\n");

                        receivePaymentXML.append("\t\t\t\t\t<SetCredit>\n");
                            receivePaymentXML.append("\t\t\t\t\t\t<CreditTxnID>");
                            receivePaymentXML.append("44F3-1071525107");
                            receivePaymentXML.append("</CreditTxnID>\n");

                            receivePaymentXML.append("\t\t\t\t\t\t<TxnLineID>");
                            receivePaymentXML.append("100.00");
                            receivePaymentXML.append("</TxnLineID>\n");

                            receivePaymentXML.append("\t\t\t\t\t\t<AppliedAmount>");
                            receivePaymentXML.append("100.00");
                            receivePaymentXML.append("</AppliedAmount>\n");
                        receivePaymentXML.append("\t\t\t\t\t</SetCredit>\n");

                        receivePaymentXML.append("\t\t\t\t\t<DiscountAmount>");
                            receivePaymentXML.append("100.00");
                        receivePaymentXML.append("</DiscountAmount>\n");

                        receivePaymentXML.append("\t\t\t\t\t<DiscountAccountRef>\n");
                            receivePaymentXML.append("\t\t\t\t\t\t<ListID>");
                                receivePaymentXML.append("44F3-1071525107");
                            receivePaymentXML.append("</ListID>\n");

                            receivePaymentXML.append("\t\t\t\t\t\t<FullName>");
                                receivePaymentXML.append("suresh:digiBlitz");
                            receivePaymentXML.append("</FullName>\n");
                        receivePaymentXML.append("\t\t\t\t\t</DiscountAccountRef>\n");

                    receivePaymentXML.append("\t\t\t\t</AppliedToTxnAdd>\n");

                receivePaymentXML.append("\t\t\t</ReceivePaymentAdd>\n");

            receivePaymentXML.append("\t\t</ReceivePaymentAddRq>\n");

        receivePaymentXML.append("\t</QBXMLMsgsRq>\n");

    receivePaymentXML.append("</QBXML>\n");

    return receivePaymentXML.toString();
    }

    public String generateSalesReceipt() {
        StringBuffer salesReceiptXML = new StringBuffer();

        salesReceiptXML.append("<?xml version=\"1.0\"?>\n");
        salesReceiptXML.append("<?qbxml version=\"2.0\"?>\n");
        salesReceiptXML.append("<QBXML>\n");
        salesReceiptXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
            salesReceiptXML.append("\t\t<SalesReceiptAddRq requestID = \"2\">\n");
                salesReceiptXML.append("\t\t\t<SalesReceiptAdd>\n");
                    salesReceiptXML.append("\t\t\t\t<CustomerRef>\n");
                        salesReceiptXML.append("\t\t\t\t\t<FullName>");
                            salesReceiptXML.append("suresh:digiBlitz");
                        salesReceiptXML.append("</FullName>\n");
                    salesReceiptXML.append("\t\t\t\t</CustomerRef>\n");

                    salesReceiptXML.append("\t\t\t\t<ClassRef >\n");
                        salesReceiptXML.append("\t\t\t\t\t<FullName>");
                            salesReceiptXML.append("suresh:digiBlitz");
                        salesReceiptXML.append("</FullName>\n");
                    salesReceiptXML.append("\t\t\t\t</ClassRef >\n");

                    salesReceiptXML.append("\t\t\t\t\t<TxnDate>");
                        salesReceiptXML.append("TxnDate");
                    salesReceiptXML.append("</TxnDate>\n");

                    salesReceiptXML.append("\t\t\t\t\t<RefNumber>");
                        salesReceiptXML.append("RefNumber");
                    salesReceiptXML.append("</RefNumber>\n");

                    salesReceiptXML.append("\t\t\t\t<BillAddress>\n");
                        salesReceiptXML.append("\t\t\t\t\t<Addr1>");
                            salesReceiptXML.append("Addr1");
                        salesReceiptXML.append("</Addr1>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Addr2>");
                            salesReceiptXML.append("Addr2");
                        salesReceiptXML.append("</Addr2>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Addr3>");
                            salesReceiptXML.append("Addr3");
                        salesReceiptXML.append("</Addr3>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Addr4>");
                            salesReceiptXML.append("Addr4");
                        salesReceiptXML.append("</Addr4>\n");

                        salesReceiptXML.append("\t\t\t\t\t<City>");
                            salesReceiptXML.append("City");
                        salesReceiptXML.append("</City>\n");

                        salesReceiptXML.append("\t\t\t\t\t<State>");
                            salesReceiptXML.append("State");
                        salesReceiptXML.append("</State>\n");

                        salesReceiptXML.append("\t\t\t\t\t<PostalCode>");
                            salesReceiptXML.append("PostalCode");
                        salesReceiptXML.append("</PostalCode>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Country>");
                            salesReceiptXML.append("Country");
                        salesReceiptXML.append("</Country>\n");

                    salesReceiptXML.append("\t\t\t\t</BillAddress>\n");


                    salesReceiptXML.append("\t\t\t\t<ShipAddress>\n");
                        salesReceiptXML.append("\t\t\t\t\t<Addr1>");
                            salesReceiptXML.append("Addr1");
                        salesReceiptXML.append("</Addr1>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Addr2>");
                            salesReceiptXML.append("Addr2");
                        salesReceiptXML.append("</Addr2>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Addr3>");
                            salesReceiptXML.append("Addr3");
                        salesReceiptXML.append("</Addr3>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Addr4>");
                            salesReceiptXML.append("Addr4");
                        salesReceiptXML.append("</Addr4>\n");

                        salesReceiptXML.append("\t\t\t\t\t<City>");
                            salesReceiptXML.append("City");
                        salesReceiptXML.append("</City>\n");

                        salesReceiptXML.append("\t\t\t\t\t<State>");
                            salesReceiptXML.append("State");
                        salesReceiptXML.append("</State>\n");

                        salesReceiptXML.append("\t\t\t\t\t<PostalCode>");
                            salesReceiptXML.append("PostalCode");
                        salesReceiptXML.append("</PostalCode>\n");

                        salesReceiptXML.append("\t\t\t\t\t<Country>");
                            salesReceiptXML.append("Country");
                        salesReceiptXML.append("</Country>\n");
                    salesReceiptXML.append("\t\t\t\t</ShipAddress>\n");

                    salesReceiptXML.append("\t\t\t\t<CheckNumber>");
                        salesReceiptXML.append("CheckNumber");
                    salesReceiptXML.append("</CheckNumber>\n");

                    salesReceiptXML.append("\t\t\t\t<PaymentMethodRef>\n");
                        salesReceiptXML.append("\t\t\t\t\t<FullName>");
                            salesReceiptXML.append("suresh:digiBlitz");
                        salesReceiptXML.append("</FullName>\n");
                    salesReceiptXML.append("\t\t\t\t</PaymentMethodRef>\n");

                    salesReceiptXML.append("\t\t\t\t<CreditCardTxnInfo>\n");
                        salesReceiptXML.append("\t\t\t\t\t<CreditCardTxnInputInfo>\n");
                            salesReceiptXML.append("\t\t\t\t\t\t<CreditCardNumber>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</CreditCardNumber>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<ExpirationMonth>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</ExpirationMonth>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<ExpirationYear>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</ExpirationYear>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<NameOnCard>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</NameOnCard>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<CreditCardAddress>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</CreditCardAddress>\n");


                            salesReceiptXML.append("\t\t\t\t\t\t<CreditCardPostalCode>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</CreditCardPostalCode>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<CommercialCardCode>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</CommercialCardCode>\n");

                        salesReceiptXML.append("\t\t\t\t\t</CreditCardTxnInputInfo>\n");

                        salesReceiptXML.append("\t\t\t\t\t<CreditCardTxnResultInfo>\n");
                            salesReceiptXML.append("\t\t\t\t\t\t<ResultCode>");
                                salesReceiptXML.append("0");
                            salesReceiptXML.append("</ResultCode>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<ResultMessage>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</ResultMessage>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<CreditCardTransID>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</CreditCardTransID>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<MerchantAccountNumber>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</MerchantAccountNumber>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<AuthorizationCode>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</AuthorizationCode>\n");


                            salesReceiptXML.append("\t\t\t\t\t\t<AVSStreet>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</AVSStreet>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<AVSZip>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</AVSZip>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<ReconBatchID>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</ReconBatchID>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<PaymentGroupingCode>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</PaymentGroupingCode>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<PaymentStatus>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</PaymentStatus>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<TxnAuthorizationTime>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</TxnAuthorizationTime>\n");

                            salesReceiptXML.append("\t\t\t\t\t\t<TxnAuthorizationStamp>");
                                salesReceiptXML.append("5555555555554444");
                            salesReceiptXML.append("</TxnAuthorizationStamp>\n");

                        salesReceiptXML.append("\t\t\t\t\t</CreditCardTxnResultInfo>\n");

                    salesReceiptXML.append("\t\t\t\t</CreditCardTxnInfo>\n");

                salesReceiptXML.append("\t\t\t</SalesReceiptAdd>\n");

            salesReceiptXML.append("\t\t</SalesReceiptAddRq>\n");

            salesReceiptXML.append("\t</QBXMLMsgsRq>\n");

        salesReceiptXML.append("</QBXML>\n");

        return salesReceiptXML.toString();
    }

    public String generateCustomer() {
            StringBuffer customerXML = new StringBuffer();

            customerXML.append("<?xml version=\"1.0\"?>\n");
            customerXML.append("<?qbxml version=\"2.0\"?>\n");

            customerXML.append("<QBXML>\n");

                customerXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");

                    customerXML.append("\t\t<CustomerAddRq requestID = \"2\">\n");

                        customerXML.append("\t\t\t<CustomerAdd>\n");

                        customerXML.append("\t\t\t\t<Name>");
                            customerXML.append("suresh:digiBlitz");
                        customerXML.append("</Name>\n");

                        customerXML.append("\t\t\t\t<CompanyName>");
                            customerXML.append("Sury Info tech");
                        customerXML.append("</CompanyName>\n");

                        customerXML.append("\t\t\t\t<Salutation>");
                            customerXML.append("Salutation");
                        customerXML.append("</Salutation>\n");

                        customerXML.append("\t\t\t\t<FirstName>");
                            customerXML.append("Suresh");
                        customerXML.append("</FirstName>\n");

                        customerXML.append("\t\t\t\t<LastName>");
                            customerXML.append("Kumar");
                        customerXML.append("</LastName>\n");

                        customerXML.append("\t\t\t\t<BillAddress>\n");
                            customerXML.append("\t\t\t\t\t<Addr1>");
                                customerXML.append("Addr1");
                            customerXML.append("</Addr1>\n");


                            customerXML.append("\t\t\t\t\t<City>");
                                customerXML.append("Chennai");
                            customerXML.append("</City>\n");

                            customerXML.append("\t\t\t\t\t<State>");
                                customerXML.append("TN");
                            customerXML.append("</State>\n");

                            customerXML.append("\t\t\t\t\t<PostalCode>");
                                customerXML.append("32894");
                            customerXML.append("</PostalCode>\n");

                        customerXML.append("\t\t\t\t</BillAddress>\n");

                            customerXML.append("\t\t\t\t<Phone>");
                                customerXML.append("4324432432");
                            customerXML.append("</Phone>\n");

                            customerXML.append("\t\t\t\t<AltPhone>");
                                customerXML.append("32432432");
                            customerXML.append("</AltPhone>\n");


                            customerXML.append("\t\t\t\t<Email>");
                                customerXML.append("suryey@yahoo.co.in");
                            customerXML.append("</Email>\n");

                            customerXML.append("\t\t\t\t<TermsRef>\n");
                                customerXML.append("\t\t\t\t\t<FullName>");
                                    customerXML.append("Net 30");
                                customerXML.append("</FullName>\n");
                            customerXML.append("\t\t\t\t</TermsRef>\n");


                            customerXML.append("\t\t\t\t<OpenBalance>");
                                customerXML.append("200.00");
                            customerXML.append("</OpenBalance>\n");

                            customerXML.append("\t\t\t\t<OpenBalanceDate>");
                                customerXML.append("2006-12-22");
                            customerXML.append("</OpenBalanceDate>\n");

                            customerXML.append("\t\t\t</CustomerAdd>\n");

                        customerXML.append("\t\t</CustomerAddRq>\n");

                customerXML.append("\t</QBXMLMsgsRq>\n");

            customerXML.append("</QBXML>\n");

            return customerXML.toString();
    }
    
    public String customerUpdate() {
            StringBuffer customerXML = new StringBuffer();

            customerXML.append("<?xml version=\"1.0\"?>\n");
            customerXML.append("<?qbxml version=\"2.0\"?>\n");

            customerXML.append("<QBXML>\n");

                customerXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");

                    customerXML.append("\t\t<CustomerModRq requestID = \"2\">\n");
                    customerXML.append("\t\t\t<CustomerMod>\n");

                        customerXML.append("\t\t\t\t<ListID>");
                            customerXML.append("48");
                        customerXML.append("</ListID>\n");

                        customerXML.append("\t\t\t\t<EditSequence>");
                            customerXML.append("0");
                        customerXML.append("</EditSequence>\n");

                        customerXML.append("\t\t\t\t<Name>");
                            customerXML.append("Suresh:digiBLitz");
                        customerXML.append("</Name>\n");


                        customerXML.append("\t\t\t\t<CompanyName>");
                            customerXML.append("CompanyName");
                        customerXML.append("</CompanyName>\n");

                        customerXML.append("\t\t\t\t<Salutation>");
                            customerXML.append("Salutation");
                        customerXML.append("</Salutation>\n");

                        customerXML.append("\t\t\t\t<FirstName>");
                            customerXML.append("FirstName");
                        customerXML.append("</FirstName>\n");

                        customerXML.append("\t\t\t\t<LastName>");
                            customerXML.append("LastName");
                        customerXML.append("</LastName>\n");
                        
                        customerXML.append("\t\t\t\t<Suffix>");
                            customerXML.append("Suffix");
                        customerXML.append("</Suffix>\n");

                        customerXML.append("\t\t\t\t<BillAddress>\n");
                            customerXML.append("\t\t\t\t\t<Addr1>");
                                customerXML.append("Addr1");
                            customerXML.append("</Addr1>\n");
                            
                            customerXML.append("\t\t\t\t\t<City>");
                                customerXML.append("City");
                            customerXML.append("</City>\n");

                            customerXML.append("\t\t\t\t\t<State>");
                                customerXML.append("State");
                            customerXML.append("</State>\n");

                            customerXML.append("\t\t\t\t\t<PostalCode>");
                                customerXML.append("PostalCode");
                            customerXML.append("</PostalCode>\n");
                            
                            customerXML.append("\t\t\t\t\t<Country>");
                                customerXML.append("Country");
                            customerXML.append("</Country>\n");

                        customerXML.append("\t\t\t\t</BillAddress>\n");
                        
                        customerXML.append("\t\t\t\t<ShipAddress>\n");
                            customerXML.append("\t\t\t\t\t<Addr1>");
                                customerXML.append("Addr1");
                            customerXML.append("</Addr1>\n");
                            
                            customerXML.append("\t\t\t\t\t<City>");
                                customerXML.append("City");
                            customerXML.append("</City>\n");

                            customerXML.append("\t\t\t\t\t<State>");
                                customerXML.append("State");
                            customerXML.append("</State>\n");

                            customerXML.append("\t\t\t\t\t<PostalCode>");
                                customerXML.append("PostalCode");
                            customerXML.append("</PostalCode>\n");
                            
                            customerXML.append("\t\t\t\t\t<Country>");
                                customerXML.append("Country");
                            customerXML.append("</Country>\n");

                        customerXML.append("\t\t\t\t</ShipAddress>\n");

                            customerXML.append("\t\t\t\t<PrintAs>");
                                customerXML.append("PrintAs");
                            customerXML.append("</PrintAs>\n");
                            
                            customerXML.append("\t\t\t\t<Phone>");
                                customerXML.append("Phone");
                            customerXML.append("</Phone>\n");
                            
                            customerXML.append("\t\t\t\t<Mobile>");
                                customerXML.append("Mobile");
                            customerXML.append("</Mobile>\n");
                            
                            customerXML.append("\t\t\t\t<Pager>");
                                customerXML.append("Pager");
                            customerXML.append("</Pager>\n");

                            customerXML.append("\t\t\t\t<AltPhone>");
                                customerXML.append("AltPhone");
                            customerXML.append("</AltPhone>\n");
                            
                             customerXML.append("\t\t\t\t<Fax>");
                                customerXML.append("Fax");
                            customerXML.append("</Fax>\n");

                            customerXML.append("\t\t\t\t<Email>");
                                customerXML.append("Email");
                            customerXML.append("</Email>\n");

                            customerXML.append("\t\t\t</CustomerMod>\n");

                        customerXML.append("\t\t</CustomerModRq>\n");
                customerXML.append("\t</QBXMLMsgsRq>\n");

            customerXML.append("</QBXML>\n");

            return customerXML.toString();
    }
    
    public String customerQuery() {
            StringBuffer customerQueryXML = new StringBuffer();

            customerQueryXML.append("<?xml version=\"1.0\"?>\n");
            customerQueryXML.append("<?qbxml version=\"2.0\"?>\n");

            customerQueryXML.append("<QBXML>\n");

                customerQueryXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");

                    customerQueryXML.append("\t\t<CustomerQueryRq requestID = \"2\">\n");

                        customerQueryXML.append("\t\t\t<FullName>");
                            customerQueryXML.append("suresh:digiBlitz");
                        customerQueryXML.append("</FullName>\n");

                        /*
                       
                         *
                         *  customerQueryXML.append("\t\t\t<ListID>");
                            customerQueryXML.append("suresh:digiBlitz");
                        customerQueryXML.append("</ListID>\n");
                        

                        customerQueryXML.append("\t\t\t<MaxReturned>");
                            customerQueryXML.append("MaxReturned");
                        customerQueryXML.append("</MaxReturned>\n");


                        customerQueryXML.append("\t\t\t<FromModifiedDate>");
                            customerQueryXML.append("FromModifiedDate");
                        customerQueryXML.append("</FromModifiedDate>\n");

                        customerQueryXML.append("\t\t\t<ToModifiedDate>");
                            customerQueryXML.append("ToModifiedDate");
                        customerQueryXML.append("</ToModifiedDate>\n");

                        customerQueryXML.append("\t\t\t\t<NameRangeFilter>\n");
                            customerQueryXML.append("\t\t\t\t\t<FromName>");
                                customerQueryXML.append("FromName");
                            customerQueryXML.append("</FromName>\n");

                            customerQueryXML.append("\t\t\t\t\t<ToName>");
                                customerQueryXML.append("ToName");
                            customerQueryXML.append("</ToName>\n");
                        customerQueryXML.append("\t\t\t\t</NameRangeFilter>\n");
                         *
                         */
                    customerQueryXML.append("\t\t</CustomerQueryRq>\n");
                customerQueryXML.append("\t</QBXMLMsgsRq>\n");
            customerQueryXML.append("</QBXML>\n");
            return customerQueryXML.toString();
    }

    public String paymentMethodAdd() {
            StringBuffer paymentMethodAddXML = new StringBuffer();

            paymentMethodAddXML.append("<?xml version=\"1.0\"?>\n");
            paymentMethodAddXML.append("<?qbxml version=\"2.0\"?>\n");

            paymentMethodAddXML.append("<QBXML>\n");

                paymentMethodAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");

                    paymentMethodAddXML.append("\t\t<PaymentMethodAddRq requestID = \"2\">\n");

                     paymentMethodAddXML.append("\t\t\t<PaymentMethodAdd>\n");

                        paymentMethodAddXML.append("\t\t\t\t<Name>");
                            paymentMethodAddXML.append("suresh:digiBlitz");
                        paymentMethodAddXML.append("</Name>\n");

                        paymentMethodAddXML.append("\t\t\t<PaymentMethodAdd>\n");


                    paymentMethodAddXML.append("\t\t</PaymentMethodAddRq>\n");
                paymentMethodAddXML.append("\t</QBXMLMsgsRq>\n");
            paymentMethodAddXML.append("</QBXML>\n");
            return paymentMethodAddXML.toString();
    }
    
    public String checkAdd() {
            StringBuffer CheckAddXML = new StringBuffer();

            CheckAddXML.append("<?xml version=\"1.0\"?>\n");
            CheckAddXML.append("<?qbxml version=\"2.0\"?>\n");

            CheckAddXML.append("<QBXML>\n");

                CheckAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    CheckAddXML.append("\t\t<CheckAddRq requestID = \"2\">\n");
                     CheckAddXML.append("\t\t\t<CheckAdd>\n");
                     
                      CheckAddXML.append("\t\t\t\t<AccountRef>\n");
                            CheckAddXML.append("\t\t\t\t\t<ListID>");
                                CheckAddXML.append("ListID");
                            CheckAddXML.append("</ListID>\n");
                            
                            CheckAddXML.append("\t\t\t\t\t<FullName>");
                                CheckAddXML.append("FullName");
                            CheckAddXML.append("</FullName>\n");
                            
                        CheckAddXML.append("\t\t\t\t</AccountRef>\n");
                        
                        CheckAddXML.append("\t\t\t\t<PayeeEntityRef>\n");
                            CheckAddXML.append("\t\t\t\t\t<ListID>");
                                CheckAddXML.append("ListID");
                            CheckAddXML.append("</ListID>\n");
                            
                            CheckAddXML.append("\t\t\t\t\t<FullName>");
                                CheckAddXML.append("FullName");
                            CheckAddXML.append("</FullName>\n");
                        CheckAddXML.append("\t\t\t\t</PayeeEntityRef>\n");
                        
                        CheckAddXML.append("\t\t\t\t<RefNumber>");
                         CheckAddXML.append("RefNumber");
                        CheckAddXML.append("</RefNumber>\n");
                        
                        CheckAddXML.append("\t\t\t\t<TxnDate>");
                         CheckAddXML.append("TxnDate");
                        CheckAddXML.append("</TxnDate>\n");
                        
                        CheckAddXML.append("\t\t\t\t<Memo>");
                         CheckAddXML.append("Memo");
                        CheckAddXML.append("</Memo>\n");
                        
                        CheckAddXML.append("\t\t\t\t<IsToBePrinted>");
                         CheckAddXML.append("IsToBePrinted");
                        CheckAddXML.append("</IsToBePrinted>\n");
                        
                        
                       CheckAddXML.append("\t\t\t\t<ExpenseLineAdd>\n");
                            CheckAddXML.append("\t\t\t\t\t<AccountRef>\n");
                                CheckAddXML.append("\t\t\t\t\t\t<ListID>");
                                    CheckAddXML.append("ListID");
                                CheckAddXML.append("</ListID>\n");
                                
                                 CheckAddXML.append("\t\t\t\t\t\t<FullName>");
                                    CheckAddXML.append("FullName");
                                CheckAddXML.append("</FullName>\n");
                            CheckAddXML.append("\t\t\t\t\t</AccountRef>\n");
                            
                            CheckAddXML.append("\t\t\t\t\t<Amount>");
                                CheckAddXML.append("Amount");
                            CheckAddXML.append("</Amount>\n");
                            
                            CheckAddXML.append("\t\t\t\t\t<Memo>");
                                CheckAddXML.append("Memo");
                            CheckAddXML.append("</Memo>\n");
                            
                            CheckAddXML.append("\t\t\t\t\t<CustomerRef>\n");
                                CheckAddXML.append("\t\t\t\t\t\t<ListID>");
                                    CheckAddXML.append("ListID");
                                CheckAddXML.append("</ListID>\n");
                                
                                 CheckAddXML.append("\t\t\t\t\t\t<FullName>");
                                    CheckAddXML.append("FullName");
                                CheckAddXML.append("</FullName>\n");
                            CheckAddXML.append("\t\t\t\t\t</CustomerRef>\n");
                            
                            CheckAddXML.append("\t\t\t\t\t<ClassRef>\n");
                                CheckAddXML.append("\t\t\t\t\t\t<ListID>");
                                    CheckAddXML.append("ListID");
                                CheckAddXML.append("</ListID>\n");
                                
                                 CheckAddXML.append("\t\t\t\t\t\t<FullName>");
                                    CheckAddXML.append("FullName");
                                CheckAddXML.append("</FullName>\n");
                            CheckAddXML.append("\t\t\t\t\t</ClassRef>\n");
                            
                        CheckAddXML.append("\t\t\t\t</ExpenseLineAdd>\n");

                        CheckAddXML.append("\t\t\t<CheckAdd>\n");
                    CheckAddXML.append("\t\t</CheckAddRq>\n");
                CheckAddXML.append("\t</QBXMLMsgsRq>\n");
            CheckAddXML.append("</QBXML>\n");
            return CheckAddXML.toString();
    }
    
    public String checkQuery() {
            StringBuffer checkQueryXML = new StringBuffer();

            checkQueryXML.append("<?xml version=\"1.0\"?>\n");
            checkQueryXML.append("<?qbxml version=\"2.0\"?>\n");

            checkQueryXML.append("<QBXML>\n");

                checkQueryXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    checkQueryXML.append("\t\t<CheckQueryRq requestID = \"2\">\n");
                    
                        checkQueryXML.append("\t\t\t<TxnID>");
                            checkQueryXML.append("TxnID");
                        checkQueryXML.append("</TxnID>\n");
                        
                        checkQueryXML.append("\t\t\t<RefNumber>");
                            checkQueryXML.append("RefNumber");
                        checkQueryXML.append("</RefNumber>\n");
                        
                        checkQueryXML.append("\t\t\t<RefNumberCaseSensitive>");
                            checkQueryXML.append("RefNumberCaseSensitive");
                        checkQueryXML.append("</RefNumberCaseSensitive>\n");
                        
                        checkQueryXML.append("\t\t\t<MaxReturned>\n");
                        
                            checkQueryXML.append("\t\t\t\t<ModifiedDateRangeFilter>\n");
                                checkQueryXML.append("\t\t\t\t\t<FromModifiedDate>");
                                    checkQueryXML.append("FromModifiedDate");
                                checkQueryXML.append("</FromModifiedDate>\n");
                                
                                checkQueryXML.append("\t\t\t\t\t<ToModifiedDate>");
                                    checkQueryXML.append("ToModifiedDate");
                                checkQueryXML.append("</ToModifiedDate>\n");
                            checkQueryXML.append("\t\t\t\t</ModifiedDateRangeFilter>\n");
                            
                            checkQueryXML.append("\t\t\t\t<TxnDateRangeFilter>\n");
                                checkQueryXML.append("\t\t\t\t\t<FromTxnDate>");
                                    checkQueryXML.append("FromTxnDate");
                                checkQueryXML.append("</FromTxnDate>\n");
                                
                                checkQueryXML.append("\t\t\t\t\t<ToTxnDate>");
                                    checkQueryXML.append("ToTxnDate");
                                checkQueryXML.append("</ToTxnDate>\n");
                                
                                checkQueryXML.append("\t\t\t\t\t<DateMacro>");
                                    checkQueryXML.append("DateMacro");
                                checkQueryXML.append("</DateMacro>\n");
                            checkQueryXML.append("\t\t\t\t</TxnDateRangeFilter>\n");
                            
                        checkQueryXML.append("\t\t\t</MaxReturned>\n");
                        
                         checkQueryXML.append("\t\t\t<EntityFilter>\n");
                        
                            checkQueryXML.append("\t\t\t\t<ListID>");
                                    checkQueryXML.append("ListID");
                            checkQueryXML.append("</ListID>\n");
                            
                            checkQueryXML.append("\t\t\t\t<FullName>");
                                    checkQueryXML.append("FullName");
                            checkQueryXML.append("</FullName>\n");
                            
                            checkQueryXML.append("\t\t\t\t<ListIDWithChildren>");
                                    checkQueryXML.append("ListIDWithChildren");
                            checkQueryXML.append("</ListIDWithChildren>\n");
                            
                            checkQueryXML.append("\t\t\t\t<FullNameWithChildren>");
                                    checkQueryXML.append("FullNameWithChildren");
                            checkQueryXML.append("</FullNameWithChildren>\n");
                            
                            checkQueryXML.append("\t\t\t\t<RefNumberFilter>\n");
                            
                                checkQueryXML.append("\t\t\t\t\t<MatchCriterion>");
                                        checkQueryXML.append("MatchCriterion");
                                checkQueryXML.append("</MatchCriterion>\n");
                                
                                checkQueryXML.append("\t\t\t\t\t<RefNumber>");
                                        checkQueryXML.append("RefNumber");
                                checkQueryXML.append("</RefNumber>\n");
                            checkQueryXML.append("\t\t\t\t</RefNumberFilter>\n");
                            
                            checkQueryXML.append("\t\t\t\t<RefNumberRangeFilter>\n");
                            
                                checkQueryXML.append("\t\t\t\t\t<FromRefNumber>");
                                        checkQueryXML.append("FromRefNumber");
                                checkQueryXML.append("</FromRefNumber>\n");
                                
                                checkQueryXML.append("\t\t\t\t\t<ToRefNumber>");
                                        checkQueryXML.append("ToRefNumber");
                                checkQueryXML.append("</ToRefNumber>\n");
                            checkQueryXML.append("\t\t\t\t</RefNumberRangeFilter>\n");
                        checkQueryXML.append("\t\t\t</EntityFilter>\n");
                        

                    checkQueryXML.append("\t\t</CheckQueryRq>\n");
                checkQueryXML.append("\t</QBXMLMsgsRq>\n");
            checkQueryXML.append("</QBXML>\n");
            return checkQueryXML.toString();
    }
    
    public String dateDrivenTermsAdd() {
        StringBuffer dateDrivenTermsAddXML = new StringBuffer();

            dateDrivenTermsAddXML.append("<?xml version=\"1.0\"?>\n");
            dateDrivenTermsAddXML.append("<?qbxml version=\"2.0\"?>\n");

            dateDrivenTermsAddXML.append("<QBXML>\n");

                dateDrivenTermsAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    dateDrivenTermsAddXML.append("\t\t<DateDrivenTermsAddRq requestID = \"2\">\n");
                    
                        dateDrivenTermsAddXML.append("\t\t\t<DateDrivenTermsAdd>\n");
                            
                            dateDrivenTermsAddXML.append("\t\t\t\t<Name>");
                                dateDrivenTermsAddXML.append("suresh:digiBlitz");
                            dateDrivenTermsAddXML.append("</Name>\n");
                            
                            dateDrivenTermsAddXML.append("\t\t\t\t<DayOfMonthDue>");
                                dateDrivenTermsAddXML.append("DayOfMonthDue");
                            dateDrivenTermsAddXML.append("</DayOfMonthDue>\n");
                            
                            dateDrivenTermsAddXML.append("\t\t\t\t<DueNextMonthDays>");
                                dateDrivenTermsAddXML.append("DueNextMonthDays");
                            dateDrivenTermsAddXML.append("</DueNextMonthDays>\n");
                            
                            dateDrivenTermsAddXML.append("\t\t\t\t<DiscountDayOfMonth>");
                                dateDrivenTermsAddXML.append("DiscountDayOfMonth");
                            dateDrivenTermsAddXML.append("</DiscountDayOfMonth>\n");
                            
                            dateDrivenTermsAddXML.append("\t\t\t\t<DiscountPct>");
                                dateDrivenTermsAddXML.append("DiscountPct");
                            dateDrivenTermsAddXML.append("</DiscountPct>\n");
                            
                        dateDrivenTermsAddXML.append("\t\t\t<DateDrivenTermsAdd>\n");
                        
                    dateDrivenTermsAddXML.append("\t\t</DateDrivenTermsAddRq>\n");
                dateDrivenTermsAddXML.append("\t</QBXMLMsgsRq>\n");
            dateDrivenTermsAddXML.append("</QBXML>\n");
        return dateDrivenTermsAddXML.toString();
}
   
    public String standardTermsAdd() {
        StringBuffer standardTermsAddXML = new StringBuffer();

            standardTermsAddXML.append("<?xml version=\"1.0\"?>\n");
            standardTermsAddXML.append("<?qbxml version=\"2.0\"?>\n");

            standardTermsAddXML.append("<QBXML>\n");

                standardTermsAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    standardTermsAddXML.append("\t\t<StandardTermsAddRq requestID = \"2\">\n");
                    
                        standardTermsAddXML.append("\t\t\t<StandardTermsAdd>\n");
                            
                            standardTermsAddXML.append("\t\t\t\t<Name>");
                                standardTermsAddXML.append("suresh:digiBlitz");
                            standardTermsAddXML.append("</Name>\n");
                            
                            standardTermsAddXML.append("\t\t\t\t<StdDueDays>");
                                standardTermsAddXML.append("StdDueDays");
                            standardTermsAddXML.append("</StdDueDays>\n");
                            
                            standardTermsAddXML.append("\t\t\t\t<StdDiscountDays>");
                                standardTermsAddXML.append("StdDiscountDays");
                            standardTermsAddXML.append("</StdDiscountDays>\n");
                            
                            standardTermsAddXML.append("\t\t\t\t<DiscountPct>");
                                standardTermsAddXML.append("DiscountPct");
                            standardTermsAddXML.append("</DiscountPct>\n");
                            
                        standardTermsAddXML.append("\t\t\t<StandardTermsAdd>\n");
                        
                    standardTermsAddXML.append("\t\t</StandardTermsAddRq>\n");
                standardTermsAddXML.append("\t</QBXMLMsgsRq>\n");
            standardTermsAddXML.append("</QBXML>\n");
        return standardTermsAddXML.toString();
}
    
    public String standardTermsQuery() {
        StringBuffer standardTermsQueryXML = new StringBuffer();

            standardTermsQueryXML.append("<?xml version=\"1.0\"?>\n");
            standardTermsQueryXML.append("<?qbxml version=\"2.0\"?>\n");

            standardTermsQueryXML.append("<QBXML>\n");

                standardTermsQueryXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    standardTermsQueryXML.append("\t\t<StandardTermsQueryRq requestID = \"2\">\n");
                    
                        standardTermsQueryXML.append("\t\t\t<ListID>");
                            standardTermsQueryXML.append("ListID");
                        standardTermsQueryXML.append("</ListID>\n");
                        
                        standardTermsQueryXML.append("\t\t\t<FullName>");
                            standardTermsQueryXML.append("FullName");
                        standardTermsQueryXML.append("</FullName>\n");
                    
                    
                    
                       standardTermsQueryXML.append("\t\t\t<MaxReturned>");
                            standardTermsQueryXML.append("MaxReturne>");
                       standardTermsQueryXML.append("</MaxReturned>\n");
                       
                            standardTermsQueryXML.append("\t\t\t\t<FromModifiedDate>");
                                standardTermsQueryXML.append("FromModifiedDate");
                            standardTermsQueryXML.append("</FromModifiedDate>\n");

                            standardTermsQueryXML.append("\t\t\t\t<ToModifiedDate>\n");

                                standardTermsQueryXML.append("\t\t\t\t\t<NameRangeFilter>\n");

                                    standardTermsQueryXML.append("\t\t\t\t\t\t<FromName>");
                                        standardTermsQueryXML.append("FromName");
                                    standardTermsQueryXML.append("</FromName>\n");

                                    standardTermsQueryXML.append("\t\t\t\t\t\t<ToName>");
                                        standardTermsQueryXML.append("ToName");
                                    standardTermsQueryXML.append("</ToName>\n");

                                standardTermsQueryXML.append("\t\t\t\t\t</NameRangeFilter>\n");

                            standardTermsQueryXML.append("\t\t\t\t</ToModifiedDate>\n");

                        
                    standardTermsQueryXML.append("\t\t</StandardTermsQueryRq>\n");
                standardTermsQueryXML.append("\t</QBXMLMsgsRq>\n");
            standardTermsQueryXML.append("</QBXML>\n");
        return standardTermsQueryXML.toString();
    }
    
    public String billAdd() {
            StringBuffer billAddXML = new StringBuffer();

            billAddXML.append("<?xml version=\"1.0\"?>\n");
            billAddXML.append("<?qbxml version=\"2.0\"?>\n");

            billAddXML.append("<QBXML>\n");

                billAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    billAddXML.append("\t\t<BillAddRq requestID = \"2\">\n");
                     billAddXML.append("\t\t\t<BillAdd>\n");
                     
                      billAddXML.append("\t\t\t\t<VendorRef>\n");
                            billAddXML.append("\t\t\t\t\t<ListID>");
                                billAddXML.append("ListID");
                            billAddXML.append("</ListID>\n");
                            
                            billAddXML.append("\t\t\t\t\t<FullName>");
                                billAddXML.append("FullName");
                            billAddXML.append("</FullName>\n");
                            
                        billAddXML.append("\t\t\t\t</VendorRef>\n");
                        
                        billAddXML.append("\t\t\t\t<APAccountRef>\n");
                            billAddXML.append("\t\t\t\t\t<ListID>");
                                billAddXML.append("ListID");
                            billAddXML.append("</ListID>\n");
                            
                            billAddXML.append("\t\t\t\t\t<FullName>");
                                billAddXML.append("FullName");
                            billAddXML.append("</FullName>\n");
                        billAddXML.append("\t\t\t\t</APAccountRef>\n");
                        
                        billAddXML.append("\t\t\t\t<TxnDate>");
                         billAddXML.append("TxnDate");
                        billAddXML.append("</TxnDate>\n");
                        
                    
                        billAddXML.append("\t\t\t\t<DueDate>");
                         billAddXML.append("DueDate");
                        billAddXML.append("</DueDate>\n");
                        
                        billAddXML.append("\t\t\t\t<RefNumber>");
                         billAddXML.append("RefNumber");
                        billAddXML.append("</RefNumber>\n");
                            
                        billAddXML.append("\t\t\t\t<TermsRef>\n");
                         
                             billAddXML.append("\t\t\t\t\t<ListID>");
                                billAddXML.append("ListID");
                            billAddXML.append("</ListID>\n");
                            
                            billAddXML.append("\t\t\t\t\t<FullName>");
                                billAddXML.append("FullName");
                            billAddXML.append("</FullName>\n");
                            
                        billAddXML.append("\t\t\t\t</TermsRef>\n");
                        
                        
                        billAddXML.append("\t\t\t\t<Memo>");
                         billAddXML.append("Memo");
                        billAddXML.append("</Memo>\n");
                        
                        
                       billAddXML.append("\t\t\t\t<ExpenseLineAdd>\n");
                            billAddXML.append("\t\t\t\t\t<AccountRef>\n");
                                billAddXML.append("\t\t\t\t\t\t<ListID>");
                                    billAddXML.append("ListID");
                                billAddXML.append("</ListID>\n");
                                
                                 billAddXML.append("\t\t\t\t\t\t<FullName>");
                                    billAddXML.append("FullName");
                                billAddXML.append("</FullName>\n");
                            billAddXML.append("\t\t\t\t\t</AccountRef>\n");
                            
                            billAddXML.append("\t\t\t\t\t<Amount>");
                                billAddXML.append("Amount");
                            billAddXML.append("</Amount>\n");
                            
                            billAddXML.append("\t\t\t\t\t<Memo>");
                                billAddXML.append("Memo");
                            billAddXML.append("</Memo>\n");
                            
                            billAddXML.append("\t\t\t\t\t<CustomerRef>\n");
                                billAddXML.append("\t\t\t\t\t\t<ListID>");
                                    billAddXML.append("ListID");
                                billAddXML.append("</ListID>\n");
                                
                                 billAddXML.append("\t\t\t\t\t\t<FullName>");
                                    billAddXML.append("FullName");
                                billAddXML.append("</FullName>\n");
                            billAddXML.append("\t\t\t\t\t</CustomerRef>\n");
                            
                            billAddXML.append("\t\t\t\t\t<ClassRef>\n");
                                billAddXML.append("\t\t\t\t\t\t<ListID>");
                                    billAddXML.append("ListID");
                                billAddXML.append("</ListID>\n");
                                
                                 billAddXML.append("\t\t\t\t\t\t<FullName>");
                                    billAddXML.append("FullName");
                                billAddXML.append("</FullName>\n");
                            billAddXML.append("\t\t\t\t\t</ClassRef>\n");
                            
                        billAddXML.append("\t\t\t\t</ExpenseLineAdd>\n");

                        billAddXML.append("\t\t\t<BillAdd>\n");
                    billAddXML.append("\t\t</BillAddRq>\n");
                billAddXML.append("\t</QBXMLMsgsRq>\n");
            billAddXML.append("</QBXML>\n");
            return billAddXML.toString();
    }
     
    public String billQuery() {
            StringBuffer billQueryXML = new StringBuffer();

            billQueryXML.append("<?xml version=\"1.0\"?>\n");
            billQueryXML.append("<?qbxml version=\"2.0\"?>\n");

            billQueryXML.append("<QBXML>\n");

                billQueryXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    billQueryXML.append("\t\t<BillQueryRq requestID = \"2\">\n");
                    
                        billQueryXML.append("\t\t\t<TxnID>");
                            billQueryXML.append("TxnID");
                        billQueryXML.append("</TxnID>\n");
                        
                        billQueryXML.append("\t\t\t<RefNumber>");
                            billQueryXML.append("RefNumber");
                        billQueryXML.append("</RefNumber>\n");
                        
                        billQueryXML.append("\t\t\t<RefNumberCaseSensitive>");
                            billQueryXML.append("RefNumberCaseSensitive");
                        billQueryXML.append("</RefNumberCaseSensitive>\n");
                        
                        billQueryXML.append("\t\t\t<MaxReturned>\n");
                        
                            billQueryXML.append("\t\t\t\t<ModifiedDateRangeFilter>\n");
                                billQueryXML.append("\t\t\t\t\t<FromModifiedDate>");
                                    billQueryXML.append("FromModifiedDate");
                                billQueryXML.append("</FromModifiedDate>\n");
                                
                                billQueryXML.append("\t\t\t\t\t<ToModifiedDate>");
                                    billQueryXML.append("ToModifiedDate");
                                billQueryXML.append("</ToModifiedDate>\n");
                            billQueryXML.append("\t\t\t\t</ModifiedDateRangeFilter>\n");
                            
                            billQueryXML.append("\t\t\t\t<TxnDateRangeFilter>\n");
                                billQueryXML.append("\t\t\t\t\t<FromTxnDate>");
                                    billQueryXML.append("FromTxnDate");
                                billQueryXML.append("</FromTxnDate>\n");
                                
                                billQueryXML.append("\t\t\t\t\t<ToTxnDate>");
                                    billQueryXML.append("ToTxnDate");
                                billQueryXML.append("</ToTxnDate>\n");
                                
                                billQueryXML.append("\t\t\t\t\t<DateMacro>");
                                    billQueryXML.append("DateMacro");
                                billQueryXML.append("</DateMacro>\n");
                            billQueryXML.append("\t\t\t\t</TxnDateRangeFilter>\n");
                            
                        billQueryXML.append("\t\t\t</MaxReturned>\n");
                        
                         billQueryXML.append("\t\t\t<EntityFilter>\n");
                        
                            billQueryXML.append("\t\t\t\t<ListID>");
                                    billQueryXML.append("ListID");
                            billQueryXML.append("</ListID>\n");
                            
                            billQueryXML.append("\t\t\t\t<FullName>");
                                    billQueryXML.append("FullName");
                            billQueryXML.append("</FullName>\n");
                            
                            billQueryXML.append("\t\t\t\t<ListIDWithChildren>");
                                    billQueryXML.append("ListIDWithChildren");
                            billQueryXML.append("</ListIDWithChildren>\n");
                            
                            billQueryXML.append("\t\t\t\t<FullNameWithChildren>");
                                    billQueryXML.append("FullNameWithChildren");
                            billQueryXML.append("</FullNameWithChildren>\n");
                            
                            billQueryXML.append("\t\t\t\t<RefNumberFilter>\n");
                            
                                billQueryXML.append("\t\t\t\t\t<MatchCriterion>");
                                        billQueryXML.append("MatchCriterion");
                                billQueryXML.append("</MatchCriterion>\n");
                                
                                billQueryXML.append("\t\t\t\t\t<RefNumber>");
                                        billQueryXML.append("RefNumber");
                                billQueryXML.append("</RefNumber>\n");
                            billQueryXML.append("\t\t\t\t</RefNumberFilter>\n");
                            
                            billQueryXML.append("\t\t\t\t<RefNumberRangeFilter>\n");
                            
                                billQueryXML.append("\t\t\t\t\t<FromRefNumber>");
                                        billQueryXML.append("FromRefNumber");
                                billQueryXML.append("</FromRefNumber>\n");
                                
                                billQueryXML.append("\t\t\t\t\t<ToRefNumber>");
                                        billQueryXML.append("ToRefNumber");
                                billQueryXML.append("</ToRefNumber>\n");
                            billQueryXML.append("\t\t\t\t</RefNumberRangeFilter>\n");
                        billQueryXML.append("\t\t\t</EntityFilter>\n");
                        

                    billQueryXML.append("\t\t</BillQueryRq>\n");
                billQueryXML.append("\t</QBXMLMsgsRq>\n");
            billQueryXML.append("</QBXML>\n");
            return billQueryXML.toString();
    }
     
    public String invoiceAdd() {
        StringBuffer invoiceAddXML = new StringBuffer();

        invoiceAddXML.append("<?xml version=\"1.0\"?>\n");
        invoiceAddXML.append("<?qbxml version=\"2.0\"?>\n");
        invoiceAddXML.append("<QBXML>\n");
        invoiceAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
            invoiceAddXML.append("\t\t<InvoiceAddRq requestID = \"2\">\n");
                invoiceAddXML.append("\t\t\t<InvoiceAdd>\n");
                
                    invoiceAddXML.append("\t\t\t\t<CustomerRef>\n");
                        invoiceAddXML.append("\t\t\t\t\t<ListID>");
                            invoiceAddXML.append("ListID");
                        invoiceAddXML.append("</ListID>\n");
                    
                        invoiceAddXML.append("\t\t\t\t\t<FullName>");
                            invoiceAddXML.append("suresh:digiBlitz");
                        invoiceAddXML.append("</FullName>\n");
                    invoiceAddXML.append("\t\t\t\t</CustomerRef>\n");

                    invoiceAddXML.append("\t\t\t\t<ClassRef>\n");
                        invoiceAddXML.append("\t\t\t\t\t<ListID>");
                            invoiceAddXML.append("ListID");
                        invoiceAddXML.append("</ListID>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t<FullName>");
                            invoiceAddXML.append("suresh:digiBlitz");
                        invoiceAddXML.append("</FullName>\n");
                    invoiceAddXML.append("\t\t\t\t</ClassRef>\n");
                    
                    
                   invoiceAddXML.append("\t\t\t\t<ARAccountRef>\n");
                        invoiceAddXML.append("\t\t\t\t\t<ListID>");
                            invoiceAddXML.append("ListID");
                        invoiceAddXML.append("</ListID>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t<FullName>");
                            invoiceAddXML.append("suresh:digiBlitz");
                        invoiceAddXML.append("</FullName>\n");
                    invoiceAddXML.append("\t\t\t\t</ARAccountRef>\n");

                    invoiceAddXML.append("\t\t\t\t<TxnDate>");
                        invoiceAddXML.append("TxnDate");
                    invoiceAddXML.append("</TxnDate>\n");

                    invoiceAddXML.append("\t\t\t\t<RefNumber>");
                        invoiceAddXML.append("RefNumber");
                    invoiceAddXML.append("</RefNumber>\n");

                    invoiceAddXML.append("\t\t\t\t<BillAddress>\n");
                        invoiceAddXML.append("\t\t\t\t\t<Addr1>");
                            invoiceAddXML.append("Addr1");
                        invoiceAddXML.append("</Addr1>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Addr2>");
                            invoiceAddXML.append("Addr2");
                        invoiceAddXML.append("</Addr2>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Addr3>");
                            invoiceAddXML.append("Addr3");
                        invoiceAddXML.append("</Addr3>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Addr4>");
                            invoiceAddXML.append("Addr4");
                        invoiceAddXML.append("</Addr4>\n");

                        invoiceAddXML.append("\t\t\t\t\t<City>");
                            invoiceAddXML.append("City");
                        invoiceAddXML.append("</City>\n");

                        invoiceAddXML.append("\t\t\t\t\t<State>");
                            invoiceAddXML.append("State");
                        invoiceAddXML.append("</State>\n");

                        invoiceAddXML.append("\t\t\t\t\t<PostalCode>");
                            invoiceAddXML.append("PostalCode");
                        invoiceAddXML.append("</PostalCode>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Country>");
                            invoiceAddXML.append("Country");
                        invoiceAddXML.append("</Country>\n");

                    invoiceAddXML.append("\t\t\t\t</BillAddress>\n");


                    invoiceAddXML.append("\t\t\t\t<ShipAddress>\n");
                        invoiceAddXML.append("\t\t\t\t\t<Addr1>");
                            invoiceAddXML.append("Addr1");
                        invoiceAddXML.append("</Addr1>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Addr2>");
                            invoiceAddXML.append("Addr2");
                        invoiceAddXML.append("</Addr2>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Addr3>");
                            invoiceAddXML.append("Addr3");
                        invoiceAddXML.append("</Addr3>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Addr4>");
                            invoiceAddXML.append("Addr4");
                        invoiceAddXML.append("</Addr4>\n");

                        invoiceAddXML.append("\t\t\t\t\t<City>");
                            invoiceAddXML.append("City");
                        invoiceAddXML.append("</City>\n");

                        invoiceAddXML.append("\t\t\t\t\t<State>");
                            invoiceAddXML.append("State");
                        invoiceAddXML.append("</State>\n");

                        invoiceAddXML.append("\t\t\t\t\t<PostalCode>");
                            invoiceAddXML.append("PostalCode");
                        invoiceAddXML.append("</PostalCode>\n");

                        invoiceAddXML.append("\t\t\t\t\t<Country>");
                            invoiceAddXML.append("Country");
                        invoiceAddXML.append("</Country>\n");
                    invoiceAddXML.append("\t\t\t\t</ShipAddress>\n");
                    
                    
                    invoiceAddXML.append("\t\t\t\t<TermsRef>\n");
                        invoiceAddXML.append("\t\t\t\t\t<ListID>");
                            invoiceAddXML.append("ListID");
                        invoiceAddXML.append("</ListID>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t<FullName>");
                            invoiceAddXML.append("suresh:digiBlitz");
                        invoiceAddXML.append("</FullName>\n");
                    invoiceAddXML.append("\t\t\t\t</TermsRef>\n");

                    invoiceAddXML.append("\t\t\t\t<DueDate>");
                        invoiceAddXML.append("DueDate");
                    invoiceAddXML.append("</DueDate>\n");
                    
                    
                    invoiceAddXML.append("\t\t\t\t<DiscountLineAdd>\n");
                        invoiceAddXML.append("\t\t\t\t\t<Amount>");
                            invoiceAddXML.append("Amount");
                        invoiceAddXML.append("</Amount>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t<RatePercent>");
                            invoiceAddXML.append("RatePercent");
                        invoiceAddXML.append("</RatePercent>\n");
                        
                        
                        invoiceAddXML.append("\t\t\t\t\t<IsTaxable>");
                            invoiceAddXML.append("IsTaxable");
                        invoiceAddXML.append("</IsTaxable>\n");
                        
                        
                        invoiceAddXML.append("\t\t\t\t\t<AccountRef>\n");
                            invoiceAddXML.append("\t\t\t\t\t\t<ListID>");
                                invoiceAddXML.append("ListID");
                            invoiceAddXML.append("</ListID>\n");
                        
                            invoiceAddXML.append("\t\t\t\t\t\t<FullName>");
                                invoiceAddXML.append("FullName");
                            invoiceAddXML.append("</FullName>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t</AccountRef>\n");
                    invoiceAddXML.append("\t\t\t\t</DiscountLineAdd>\n");
                    
                    invoiceAddXML.append("\t\t\t\t<SalesTaxLineAdd>\n");
                        invoiceAddXML.append("\t\t\t\t\t<Amount>");
                            invoiceAddXML.append("Amount");
                        invoiceAddXML.append("</Amount>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t<RatePercent>");
                            invoiceAddXML.append("RatePercent");
                        invoiceAddXML.append("</RatePercent>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t<AccountRef>\n");
                            invoiceAddXML.append("\t\t\t\t\t\t<ListID>");
                                invoiceAddXML.append("ListID");
                            invoiceAddXML.append("</ListID>\n");
                        
                            invoiceAddXML.append("\t\t\t\t\t\t<FullName>");
                                invoiceAddXML.append("FullName");
                            invoiceAddXML.append("</FullName>\n");
                        invoiceAddXML.append("\t\t\t\t\t</AccountRef>\n");
                        
                    invoiceAddXML.append("\t\t\t\t</ShippingLineAdd>\n");
                    
                    invoiceAddXML.append("\t\t\t\t<SalesTaxLineAdd>\n");
                        invoiceAddXML.append("\t\t\t\t\t<Amount>");
                            invoiceAddXML.append("Amount");
                        invoiceAddXML.append("</Amount>\n");
                        
                        invoiceAddXML.append("\t\t\t\t\t<AccountRef>\n");
                            invoiceAddXML.append("\t\t\t\t\t\t<ListID>");
                                invoiceAddXML.append("ListID");
                            invoiceAddXML.append("</ListID>\n");
                        
                            invoiceAddXML.append("\t\t\t\t\t\t<FullName>");
                                invoiceAddXML.append("FullName");
                            invoiceAddXML.append("</FullName>\n");
                        invoiceAddXML.append("\t\t\t\t\t</AccountRef>\n");
                        
                    invoiceAddXML.append("\t\t\t\t</ShippingLineAdd>\n");
                    
                invoiceAddXML.append("\t\t\t</InvoiceAdd >\n");

            invoiceAddXML.append("\t\t</InvoiceAddRq>\n");

            invoiceAddXML.append("\t</QBXMLMsgsRq>\n");

        invoiceAddXML.append("</QBXML>\n");

        return invoiceAddXML.toString();
    }
    
    public String invoiceQuery() {
        StringBuffer checkQueryXML = new StringBuffer();

        checkQueryXML.append("<?xml version=\"1.0\"?>\n");
        checkQueryXML.append("<?qbxml version=\"2.0\"?>\n");

        checkQueryXML.append("<QBXML>\n");

            checkQueryXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                checkQueryXML.append("\t\t<InvoiceQueryRq requestID = \"2\">\n");

                    checkQueryXML.append("\t\t\t<TxnID>");
                        checkQueryXML.append("TxnID");
                    checkQueryXML.append("</TxnID>\n");

                    checkQueryXML.append("\t\t\t<RefNumber>");
                        checkQueryXML.append("RefNumber");
                    checkQueryXML.append("</RefNumber>\n");

                    checkQueryXML.append("\t\t\t<RefNumberCaseSensitive>");
                        checkQueryXML.append("RefNumberCaseSensitive");
                    checkQueryXML.append("</RefNumberCaseSensitive>\n");

                    checkQueryXML.append("\t\t\t<MaxReturned>\n");

                        checkQueryXML.append("\t\t\t\t<ModifiedDateRangeFilter>\n");
                            checkQueryXML.append("\t\t\t\t\t<FromModifiedDate>");
                                checkQueryXML.append("FromModifiedDate");
                            checkQueryXML.append("</FromModifiedDate>\n");

                            checkQueryXML.append("\t\t\t\t\t<ToModifiedDate>");
                                checkQueryXML.append("ToModifiedDate");
                            checkQueryXML.append("</ToModifiedDate>\n");
                        checkQueryXML.append("\t\t\t\t</ModifiedDateRangeFilter>\n");

                        checkQueryXML.append("\t\t\t\t<TxnDateRangeFilter>\n");
                            checkQueryXML.append("\t\t\t\t\t<FromTxnDate>");
                                checkQueryXML.append("FromTxnDate");
                            checkQueryXML.append("</FromTxnDate>\n");

                            checkQueryXML.append("\t\t\t\t\t<ToTxnDate>");
                                checkQueryXML.append("ToTxnDate");
                            checkQueryXML.append("</ToTxnDate>\n");

                            checkQueryXML.append("\t\t\t\t\t<DateMacro>");
                                checkQueryXML.append("DateMacro");
                            checkQueryXML.append("</DateMacro>\n");
                        checkQueryXML.append("\t\t\t\t</TxnDateRangeFilter>\n");

                    checkQueryXML.append("\t\t\t</MaxReturned>\n");

                     checkQueryXML.append("\t\t\t<EntityFilter>\n");

                        checkQueryXML.append("\t\t\t\t<ListID>");
                                checkQueryXML.append("ListID");
                        checkQueryXML.append("</ListID>\n");

                        checkQueryXML.append("\t\t\t\t<FullName>");
                                checkQueryXML.append("FullName");
                        checkQueryXML.append("</FullName>\n");

                        checkQueryXML.append("\t\t\t\t<ListIDWithChildren>");
                                checkQueryXML.append("ListIDWithChildren");
                        checkQueryXML.append("</ListIDWithChildren>\n");

                        checkQueryXML.append("\t\t\t\t<FullNameWithChildren>");
                                checkQueryXML.append("FullNameWithChildren");
                        checkQueryXML.append("</FullNameWithChildren>\n");

                        checkQueryXML.append("\t\t\t\t<RefNumberFilter>\n");

                            checkQueryXML.append("\t\t\t\t\t<MatchCriterion>");
                                    checkQueryXML.append("MatchCriterion");
                            checkQueryXML.append("</MatchCriterion>\n");

                            checkQueryXML.append("\t\t\t\t\t<RefNumber>");
                                    checkQueryXML.append("RefNumber");
                            checkQueryXML.append("</RefNumber>\n");
                        checkQueryXML.append("\t\t\t\t</RefNumberFilter>\n");

                        checkQueryXML.append("\t\t\t\t<RefNumberRangeFilter>\n");

                            checkQueryXML.append("\t\t\t\t\t<FromRefNumber>");
                                    checkQueryXML.append("FromRefNumber");
                            checkQueryXML.append("</FromRefNumber>\n");

                            checkQueryXML.append("\t\t\t\t\t<ToRefNumber>");
                                    checkQueryXML.append("ToRefNumber");
                            checkQueryXML.append("</ToRefNumber>\n");
                        checkQueryXML.append("\t\t\t\t</RefNumberRangeFilter>\n");
                    checkQueryXML.append("\t\t\t</EntityFilter>\n");


                checkQueryXML.append("\t\t</InvoiceQueryRq>\n");
            checkQueryXML.append("\t</QBXMLMsgsRq>\n");
        checkQueryXML.append("</QBXML>\n");
        return checkQueryXML.toString();
    }
    
    public String itemServiceAdd() {
            StringBuffer itemServiceAddXML = new StringBuffer();

            itemServiceAddXML.append("<?xml version=\"1.0\"?>\n");
            itemServiceAddXML.append("<?qbxml version=\"2.0\"?>\n");

            itemServiceAddXML.append("<QBXML>\n");

                itemServiceAddXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    itemServiceAddXML.append("\t\t<ItemServiceAddRq requestID = \"2\">\n");
                    itemServiceAddXML.append("\t\t\t<ItemServiceAdd>\n");
                     
                        itemServiceAddXML.append("\t\t\t\t<Name>");
                            itemServiceAddXML.append("Name");
                        itemServiceAddXML.append("</Name>\n");

                        itemServiceAddXML.append("\t\t\t\t<ParentRef>\n");
                            itemServiceAddXML.append("\t\t\t\t\t<ListID>");
                                itemServiceAddXML.append("ListID");
                            itemServiceAddXML.append("</ListID>\n");

                            itemServiceAddXML.append("\t\t\t\t\t<FullName>");
                                itemServiceAddXML.append("FullName");
                            itemServiceAddXML.append("</FullName>\n");
                            
                            
                            itemServiceAddXML.append("\t\t\t\t\t<SalesOrPurchase>\n");
                            
                                itemServiceAddXML.append("\t\t\t\t\t\t<Desc>\n");
                                
                                    itemServiceAddXML.append("\t\t\t\t\t\t\t<Price>");
                                        itemServiceAddXML.append("Price");
                                    itemServiceAddXML.append("</Price>\n");
                                    
                                    itemServiceAddXML.append("\t\t\t\t\t\t\t<PricePercent>");
                                        itemServiceAddXML.append("PricePercent");
                                    itemServiceAddXML.append("</PricePercent>\n");
                                    
                                itemServiceAddXML.append("\t\t\t\t\t\t</Desc>\n");
                                
                                itemServiceAddXML.append("\t\t\t\t\t\t<AccountRef>\n");
                                        
                                    itemServiceAddXML.append("\t\t\t\t\t\t\t<ListID>");
                                        itemServiceAddXML.append("ListID");
                                    itemServiceAddXML.append("</ListID>\n");

                                    itemServiceAddXML.append("\t\t\t\t\t\t\t<FullName>");
                                        itemServiceAddXML.append("FullName");
                                    itemServiceAddXML.append("</FullName>\n");

                                itemServiceAddXML.append("\t\t\t\t\t\t</AccountRef>\n");
                                
                            itemServiceAddXML.append("\t\t\t\t\t</SalesOrPurchase>\n");
                            
                        itemServiceAddXML.append("\t\t\t\t</ParentRef>\n");

                        itemServiceAddXML.append("\t\t\t<ItemServiceAdd>\n");
                    itemServiceAddXML.append("\t\t</ItemServiceAddRq>\n");
                itemServiceAddXML.append("\t</QBXMLMsgsRq>\n");
            itemServiceAddXML.append("</QBXML>\n");
            return itemServiceAddXML.toString();
    }
    
    public String itemServiceMod() {
            StringBuffer itemServiceModXML = new StringBuffer();

            itemServiceModXML.append("<?xml version=\"1.0\"?>\n");
            itemServiceModXML.append("<?qbxml version=\"2.0\"?>\n");

            itemServiceModXML.append("<QBXML>\n");

                itemServiceModXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    itemServiceModXML.append("\t\t<ItemServiceModRq requestID = \"2\">\n");
                    itemServiceModXML.append("\t\t\t<ItemServiceMod>\n");
                     
                        itemServiceModXML.append("\t\t\t\t<ListID>");
                            itemServiceModXML.append("ListID");
                        itemServiceModXML.append("</ListID>\n");
                        
                        itemServiceModXML.append("\t\t\t\t<EditSequence>");
                            itemServiceModXML.append("EditSequence");
                        itemServiceModXML.append("</EditSequence>\n");
                        
                        itemServiceModXML.append("\t\t\t\t<Name>");
                            itemServiceModXML.append("Name");
                        itemServiceModXML.append("</Name>\n");
                        

                        itemServiceModXML.append("\t\t\t\t<ParentRef>\n");
                            itemServiceModXML.append("\t\t\t\t\t<ListID>");
                                itemServiceModXML.append("ListID");
                            itemServiceModXML.append("</ListID>\n");

                            itemServiceModXML.append("\t\t\t\t\t<FullName>");
                                itemServiceModXML.append("FullName");
                            itemServiceModXML.append("</FullName>\n");

                            itemServiceModXML.append("\t\t\t\t\t<SalesOrPurchase>\n");
                            
                                itemServiceModXML.append("\t\t\t\t\t\t<Desc>\n");
                                
                                    itemServiceModXML.append("\t\t\t\t\t\t\t<Price>");
                                        itemServiceModXML.append("Price");
                                    itemServiceModXML.append("</Price>\n");
                                    
                                    itemServiceModXML.append("\t\t\t\t\t\t\t<PricePercent>");
                                        itemServiceModXML.append("PricePercent");
                                    itemServiceModXML.append("</PricePercent>\n");
                                    
                                itemServiceModXML.append("\t\t\t\t\t\t</Desc>\n");
                                
                            itemServiceModXML.append("\t\t\t\t\t</SalesOrPurchase>\n");
                            
                        itemServiceModXML.append("\t\t\t\t</ParentRef>\n");

                        itemServiceModXML.append("\t\t\t<ItemServiceMod>\n");
                    itemServiceModXML.append("\t\t</ItemServiceModRq>\n");
                itemServiceModXML.append("\t</QBXMLMsgsRq>\n");
            itemServiceModXML.append("</QBXML>\n");
            return itemServiceModXML.toString();
    }
    
    public String itemServiceQuery() {
            StringBuffer itemServiceQueryXML = new StringBuffer();

            itemServiceQueryXML.append("<?xml version=\"1.0\"?>\n");
            itemServiceQueryXML.append("<?qbxml version=\"2.0\"?>\n");

            itemServiceQueryXML.append("<QBXML>\n");

                itemServiceQueryXML.append("\t<QBXMLMsgsRq onError = \"stopOnError\">\n");
                    itemServiceQueryXML.append("\t\t<ItemServiceQueryRq requestID = \"2\">\n");
                    
                        itemServiceQueryXML.append("\t\t\t<ListID>");
                            itemServiceQueryXML.append("ListID");
                        itemServiceQueryXML.append("</ListID>\n");
                        
                        itemServiceQueryXML.append("\t\t\t<FullName>");
                            itemServiceQueryXML.append("FullName");
                        itemServiceQueryXML.append("</FullName>\n");
                        
                        itemServiceQueryXML.append("\t\t\t<MaxReturned>");
                            itemServiceQueryXML.append("MaxReturned");
                        itemServiceQueryXML.append("</MaxReturned>\n");
                        
                        itemServiceQueryXML.append("\t\t\t<FromModifiedDate>");
                            itemServiceQueryXML.append("FromModifiedDate");
                        itemServiceQueryXML.append("</FromModifiedDate>\n");

                        itemServiceQueryXML.append("\t\t\t<ToModifiedDate>\n");
                            itemServiceQueryXML.append("\t\t\t\t<NameRangeFilter>\n");
                            
                                itemServiceQueryXML.append("\t\t\t\t\t<FromName>");
                                        itemServiceQueryXML.append("FromName");
                                itemServiceQueryXML.append("</FromName>\n");
                                
                                itemServiceQueryXML.append("\t\t\t\t\t<ToName>");
                                        itemServiceQueryXML.append("ToName");
                                itemServiceQueryXML.append("</ToName>\n");
                            itemServiceQueryXML.append("\t\t\t\t</NameRangeFilter>\n");   
                            
                        itemServiceQueryXML.append("\t\t\t</ToModifiedDate>\n");

                    itemServiceQueryXML.append("\t\t</ItemServiceQueryRq>\n");
                itemServiceQueryXML.append("\t</QBXMLMsgsRq>\n");
            itemServiceQueryXML.append("</QBXML>\n");
            return itemServiceQueryXML.toString();
    }
    
    public static void main(String args[]) throws Exception{
            QBXMLRequest ds = new QBXMLRequest();
            //System.out.println("Main Method Called");
           // Document doc = ds.getDocument("ReceivePaymentAddRs.xml");

            //System.out.println("After Document Read:");
           // ds.traverseTree(doc);
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
            
            //String xml = ds.generateCustomer();
            //String xml = ds.generateReceivePayment();
            //String xml = ds.generateSalesReceipt();
            String xml = ds.customerUpdate();
            //String xml = ds.customerQuery();
            //String xml = ds.dateDrivenTermsAdd();
            //String xml = ds.standardTermsQuery();
            //String xml = ds.billAdd();
            //String xml = ds.billQuery();
            //String xml = ds.invoiceAdd();
            //String xml = ds.invoiceQuery();
            //String xml = ds.itemServiceAdd();
            //String xml = ds.itemServiceMod();
            //String xml = ds.itemServiceQuery();
            
            //System.out.println(xml);
            try{
                    //File file = new File("CustomerAddRq.xml");
                    File file = new File("CustomerModRq.xml");
                    //File file = new File("CustomerQueryRq.xml");
                    //File file = new File("PaymentMethodAddRq.xml");
                    //File file = new File("CheckAddRq.xml");
                    //File file = new File("StandardTermsAddRq.xml");
                    //File file = new File("StandardTermsQueryRq.xml");
                    //File file = new File("BillAddRq.xml");
                    //File file = new File("BillQueryRq.xml");
                    //File file = new File("InvoiceAddRq.xml");
                    //File file = new File("InvoiceQueryRq.xml");
                    //File file = new File("ItemServiceAddRq.xml");
                    //File file = new File("ItemServiceModRq.xml");
                    //File file = new File("ItemServiceQueryRq.xml");
                
                    FileWriter fw= new FileWriter(file);
                    PrintWriter out= new PrintWriter(fw);
                    out.println(xml);
                    out.close();
                    System.out.println("Sucessfully Generated ItemServiceQueryRq.xml:" + file.getAbsolutePath());
            }
            catch(Exception e){
                    System.out.println("Exception:" + e);
            }



    }
    }

