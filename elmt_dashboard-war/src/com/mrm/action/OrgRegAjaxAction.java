/*
 * OrgRegAjaxAction.java
 *
 * Created on September 25, 2006, 3:18 PM
 */

package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.util.MessageResources;
import javax.naming.Context;
import java.util.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class OrgRegAjaxAction extends Action {
    
  
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
        
           //set the content type 
        response.setContentType("text/xml"); 
         
        response.setHeader("Cache-Control", "no-cache"); 
               
        //get the PrintWriter object to write the html page 
        PrintWriter writer = response.getWriter(); 
        
          
         try {   
                    MessageResources mr=getResources(request);
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                                                       
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    String jndiname1=mr.getMessage("jndi.usrreg");
                    Object objref1 = jndiContext.lookup(jndiname1);

                    HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref1,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote = home.create();
                                                          
                    String secId=request.getParameter("secId");
                    
                    if(secId!=null&&!(secId.equals("")))
                     {
                        
                        ArrayList ar=new ArrayList();
                        ar=remote.getUserContactDetailsByUserCode(secId);
                        
                        System.out.println("Servlet Called.........");
                       
                        if(ar.size()!=0)
                        {
                        String secName=(String)ar.get(0)+ar.get(1)+ar.get(2)+ar.get(3);
                        String secAddr1=(String)ar.get(7);
                        String secAddr2=(String)ar.get(8);
                        String secPh=(String)ar.get(13);
                        String secFx=(String)ar.get(15);
                        String secMail=(String)ar.get(5);
                        
                        String secDet=secName+"#"+secAddr1+"#"+secAddr2+"#"+secPh+"#"+secFx+"#"+secMail;
                        writer.println("<secretary>"+ secDet + "</secretary>"); 
                     
                        System.out.println("size :" +ar.size());
                        }
                        
                        else
                        {
                             String secDet=""+"#"+""+"#"+""+"#"+""+"#"+""+"#"+"";
                             writer.println("<secretary>"+ secDet + "</secretary>"); 
                        }
                    }
                      //close the write 
                      writer.close();              
                     
            }
        
         catch(Exception e)
         {
             System.out.println(e);
         }
        return null;
        
    }
}
