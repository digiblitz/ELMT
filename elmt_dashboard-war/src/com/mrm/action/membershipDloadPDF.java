/*
 * membershipDloadPDF.java
 *
 * Created on January 20, 2007, 4:48 PM
 */

package com.mrm.action;

import com.hlcspnr.util.Debug;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author karthikeyan
 * @version
 */
public class membershipDloadPDF extends Action {
    
    String fwd="";
    
      public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            
                 MessageResources mr=getResources(request);
                 String filePath=mr.getMessage("membership.pdfPath");
                 String renewFilePath=mr.getMessage("membershipRenew.pdfPath");
                 
                String process=request.getParameter("process");
                Debug.print("process :"+process);
                
                if(process.equalsIgnoreCase("reg"))
                {
                      String filePath1 = filePath;
                      Debug.print("filePath1" + filePath1);
                      
                      File file = new File(filePath1);
                      Debug.print("file" + file);
                      
                       outputImage(file,filePath1,response);
                       
                       return mapping.findForward("regConf");
                       
                }
                
                if(process.equalsIgnoreCase("renew"))
                {
                    
                      Debug.print("renewFilePath :" + renewFilePath);
                      
                      File file2 = new File(renewFilePath);
                      Debug.print("file2" + file2);
                      
                       outputImage(file2,renewFilePath,response);
                       
                       return mapping.findForward("regConf");
                       
                }
                   
           }
        
        
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
            return null;
        }
      
       public String outputImage(File thumbfile, String filePath1, HttpServletResponse response) throws FileNotFoundException, IOException {
                 if (thumbfile == null) {
                      Debug.print("Extra path info was null; should be a resource to view");
                  }

		//response.reset();
		//response.setContentType("text/plain");
                response.setContentType("application/octet-stream");
               //response.setContentType("application/" + type);
                response.setHeader("Content-Disposition", "attachment;filename=\"" + filePath1 + "\"");
                //response.setHeader("Content-Disposition","attachment; filename=\"" + thumbfile + "\"");

		OutputStream os = response.getOutputStream();
		FileInputStream fin = new FileInputStream(thumbfile);
		byte[] buf = new byte[4096];
		int count = 0;
		while(true) {
                   //  byte[] buf = new byte[4 * 1024]; // 4K buffer
   // int bytesRead;
   // while ((bytesRead = in.read(buf)) != -1) {
      //out.write(buf, 0, bytesRead);

			int n = fin.read(buf);
			if(n == -1) {
				break;
			}
			count = count + n;
			os.write(buf,0,n);
		}
		os.flush();
		os.close();
		fin.close();
                
                return "regConf";
	}
    
}
