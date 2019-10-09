/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.message.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Ravi
 */
public class ChangeTitle extends org.apache.struts.action.Action {
    
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
            throws Exception 
    {
         String gettitle=request.getParameter("title");   
         MessageResources mr=getResources(request); 
          String changetitle=mr.getMessage("jboss.path");
          System.out.println("Path get"+changetitle);
        try
        {    
            System.out.println("Title from Jsp "+gettitle); 
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    //String xmlFilePath="E:\\Xmlwrite\\src\\xmlwrite\\book.xml";
    //String xmlFilePath="E:/ravi/src/xmlwrite";
    String xmlFilePath="changetitle";
            System.out.println("Enter the element");
    //String first = bf.readLine();

    String fileData=createSalesReciptAddXML(gettitle);
    System.out.println(fileData);
//String imageFilePath = "C:\\QB_USEA_XML\\XmlRequest\\qbxmlRequest.xml";
		// Debug.print("ExactFilePath :"+imageFilePath);
		boolean flagFileCreate = false;
		FileWriter out = null;
		
			// creating the temp dir if not exists
			File fDir = new File("changetitle");
			if (!fDir.exists()) {
				if (!fDir.mkdir()) {
					throw new IOException("Cannot create parent directory ");
				}
			}

			fDir = new File("changetitle");
			if (!fDir.exists()) {
				if (!fDir.mkdir()) {
					throw new IOException("Cannot create child directory ");
				}
			}

			File newFile = new File(changetitle+"association-config.xml");
//File newFile = new File("association-config.xml");
                        // Debug.print("NewFilePath ="+newFile.getAbsolutePath());
			out = new FileWriter(newFile);
			out.write(fileData);
                        out.flush();
                        out.close();
        }

catch(Exception e)
{ 
    e.printStackTrace();    
}
return mapping.findForward("newTitle");
        
    }
    public static String createSalesReciptAddXML(String first)
{       System.out.println("createSalesReciptAddXML() called :");
        //SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        //String txnDate=sd.format(new Date());
        StringBuffer accountAddXML = new StringBuffer();
        System.out.print("createSalesReciptAddXML() called :");
        try{
     
            accountAddXML.append("<association-config>\n");
             
             accountAddXML.append("<first>\n");
             accountAddXML.append("<title>");
             accountAddXML.append(""+first+"");
             accountAddXML.append("</title>\n");
             
             accountAddXML.append("</first>\n");
           
             accountAddXML.append("</association-config>\n");
             
         
            
        } catch(Exception e) {
            System.out.print("Error in createSalesReciptAddXML :"+e);
        }
        return accountAddXML.toString();
    }
  
}