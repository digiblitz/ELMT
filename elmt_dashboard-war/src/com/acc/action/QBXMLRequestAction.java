/*
 * QBXMLRequest.java
 *
 * Created on May 13, 2007, 3:51 PM
 */

package com.acc.action;

import com.hlcaccounts.session.*;
import com.hlcmro.util.Debug;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import com.acc.action.util.*;
import org.apache.struts.util.MessageResources;
import com.hlcaccounts.util.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class QBXMLRequestAction extends Action {
    
    String fwd="";
    
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
        try {
            String name = "ejb/HLCMahanadhiSessionBean";
            MessageResources mr=getResources(request);
            
            Context jndiContext = getInitialContext();
            
            Object obj=jndiContext.lookup(name);
            HLCMahanadhiSessionRemoteHome home = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote remote = home.create();
            HttpSession session=request.getSession(true); 
            
            
            String process = request.getParameter("process");
            if(process==null) process = "";
            //===========================================================
            if(process.equalsIgnoreCase("salesReceiptAdd")){
                String transDate = request.getParameter("transDate");
                String paymentMode = request.getParameter("paymentMode");
                Debug.print("transDate :"+transDate);
                Debug.print("paymentMode :"+paymentMode);
                
                ArrayList accDet = new ArrayList();
                Date lstdate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
                lstdate = (Date)sdf.parse(transDate);
                
                String userId = (String) session.getAttribute("userId");
                String ipAddress = request.getRemoteAddr();
                
                Debug.print("!!!!!!! QBXMLRequestAction.userId !!!!!!!:" + userId);
                
                Debug.print("\n after InitialContext Beginning emp Client...\n");
                //accDet = remote.listAccTxnDetails(lstdate,false);
                accDet = remote.getMembershipTranctionDetails(lstdate, paymentMode,userId);
                //public boolean updateQBStatus(Date transDate,  String paymentMode, boolean qbStatus, boolean contQBStatus) throws RemoteException;
                boolean updateQBStatus = remote.updateQBStatus(lstdate, paymentMode, true, false, userId, ipAddress);
                Debug.print("!!!!!!!!!!!updateQBStatus Result in QBXMLRequestAction!!!!!!!!" + updateQBStatus);
                String xmlPath = mr.getMessage("QBXMLPath");
                Debug.print("xmlPath:" + xmlPath);
                String deleteFilePath = xmlPath + "\\qbXmlResponse.xml";
                Debug.print("deletePath:" + deleteFilePath);
                
                boolean fileDeleteResult = HLCFileDeletion.deleteFile(deleteFilePath);
                Debug.print("Delete File Result in QBXMLRequestAction:" + fileDeleteResult);
                String paymentTypeVal = "";
                
                if(paymentMode!=null && paymentMode.equalsIgnoreCase("Check")){
                    paymentTypeVal = "A";
                }
                else if(paymentMode!=null && paymentMode.equalsIgnoreCase("Visa, MasterCard")){
                     paymentTypeVal = "B";
                }
                else if(paymentMode!=null && paymentMode.equalsIgnoreCase("Amex")){
                     paymentTypeVal = "C";
                }
                if(accDet.size()!=0) {
                     String userCode = (String)session.getAttribute("userCode");
                     String uCode = "";
                     
                     Debug.print("=========userCode in Session==========:" + userCode);
            
                    if(userCode!=null && userCode.trim().length()!=0){
                         userCode = userCode.substring(0,2);
                    }
                    else{
                         userCode = "";
                    }
                     
                    SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
                    Date today = new Date();
                    uCode = formatter.format(today) + "-" + paymentTypeVal + "-" + userCode;

                    //YYMMDD-[Type]-[UserID]
                    //A - Cash/Checks 
                    //B - Master Card / Visa Credit Cards
                    //C - American Express

                    Debug.print("uCode :" + uCode);
                    QBXMLRequest qbxmlRequest = new QBXMLRequest();
                    String salesRepXml = qbxmlRequest.createSalesReciptAddXML(accDet, paymentMode, uCode);
                    Debug.print("salesRepXml :"+salesRepXml);
                    
                    String QBXML_request_parent_dir = mr.getMessage("QBXML_request_parent_dir");
                    Debug.print("QBXML_request_parent_dir ="+QBXML_request_parent_dir);
                    String QBXML_request_sub_dir = mr.getMessage("QBXML_request_sub_dir");
                    Debug.print("QBXML_request_sub_dir ="+QBXML_request_sub_dir);
                    String  QBXML_request_file = mr.getMessage("QBXML_request_file");
                    Debug.print(" QBXML_request_file = "+ QBXML_request_file);
                    
                    String imageFilePath = QBXML_request_sub_dir+File.separator+QBXML_request_file;
                    Debug.print("ExactFilePath :"+imageFilePath);
                    boolean flagFileCreate = false;
                    FileWriter out = null;
                    try {
                        //creating the temp dir if not exists
                        File fDir = new File(QBXML_request_parent_dir);
                        if (!fDir.exists()) {
                            if (!fDir.mkdir()) {
                                throw new IOException("Cannot create parent directory ");
                            }
                        }
                        
                        fDir = new File(QBXML_request_sub_dir);
                        if (!fDir.exists()) {
                            if (!fDir.mkdir()) {
                                throw new IOException("Cannot create child directory ");
                            }
                        }
                        
                        File newFile = new File(imageFilePath);
                        Debug.print("NewFilePath ="+newFile.getAbsolutePath());
                        out = new FileWriter(newFile);
                        out.write(salesRepXml);
                        flagFileCreate=true;
                    } catch (IOException ie) {
                        Debug.print("Error while writing into the file" + ie.getMessage());
                    } finally {
                        if(out!=null){
                            out.flush();
                            out.close();
                        }
                    }
                    
                    //Suresh Here
                    //===========================================================
                    if(flagFileCreate==true){
                        try {
                            //String fileName="F:\\QBVB\\QBVB.exe";
                            // String fileName="D:\\QBSales\\bin\\QBSales.exe";
                            //String fileName="C:\\Program Files\\db\\QBSetup\\QBSales.exe";
                            String fileName = mr.getMessage("QBExePath");
                            Debug.print("exe File Path :" + fileName);
                            File file = new File(fileName);
                            Debug.print("Exe File Existing Checking in QBXMLRequestAction:"+ file.exists());

                            String salesReceiptAddXML = "";
                            if(!file.exists()) {
                                Debug.print("QB Exe file is not Found.");
                                //response.sendRedirect("QBConfigNotes");
                                return mapping.findForward("QBConfigNotes");
                            }
                            Debug.print("init File Loaded for QB Synchronization.");
                            Runtime rt = Runtime.getRuntime() ;
                            Process p = rt.exec(fileName) ;
                            Debug.print("Calling File Loaded for QB Synchronization.");
                            InputStream in = p.getInputStream() ;
                            OutputStream outP = p.getOutputStream();
                            InputStream err = p.getErrorStream();
                            //p.destroy() ;
                            // String a = "This is from suresh";
                            // RequestDispatcher reDisp = null;
                            // reDisp = request.getRequestDispatcher("/index.jsp?var="+a);
                            // reDisp.forward(request,response);
                            Debug.print("Sucessfully Synchronized WIth QB.");
                        } catch(Exception exc){
                            System.out.println("Exception while calling QBSales exe:" + exc.getMessage());
                        }
                    }
                    
                    Debug.print(" forwarding to QBXMLResponse Action");
                    request.setAttribute("transDate", lstdate);
                    request.setAttribute("paymentMode", paymentMode);
                    
                    //System.out.print("request.lstdate in QBXMLRequest:"+ lstdate);
                    //System.out.print("request.paymentMode in QBXMLRequest:"+ paymentMode);
                    //String tempTransDate = request.getParameter("transDate");
                   // paymentMode = request.getParameter("paymentMode");
                    String param = "";
                    if(lstdate!=null){
                        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                        String tempTransDate = sdf1.format(lstdate);
                        param = param + "&td=" + tempTransDate;
                    }
                    if(paymentMode!=null){
                        param = param + "&pm=" + paymentMode;
                    }
                    
                    Debug.print("====param:" +  param);
                    response.sendRedirect("QBXMLResponse.do?process=QBSARes&cmd=reqParam"+ param);
                    //return mapping.findForward("QBSalesAddSuccess");
                }
                else{
                    return mapping.findForward("QBError");
                }
                //===========================================================
            }
           else {
                return mapping.findForward("QBError");
            }
        } catch(Exception e) {
            System.out.println("client exception :" +e);
        }
        
        return null;
    }
    
    public static Context getInitialContext()
    throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
    
}

