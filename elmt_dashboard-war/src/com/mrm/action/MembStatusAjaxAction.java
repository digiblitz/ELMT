/*
 * MembStatusAjaxAction.java
 *
 * Created on November 3, 2006, 11:12 AM
 */

package com.mrm.action;

import com.hlcform.util.Debug;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import java.io.PrintWriter;
import java.util.List;
import javax.naming.*;
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

public class MembStatusAjaxAction extends Action {
    
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
        
            //set the content type
            response.setContentType("text/xml");

            response.setHeader("Cache-Control", "no-cache");

            //get the PrintWriter object to write the html page
            PrintWriter writer = response.getWriter();
        
        try {
            
            Debug.print("MembStatusAjaxAction servlet called .........");
            
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname2=mr.getMessage("jndi.usrreg");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            
            HLCMemberDetails objMember = new HLCMemberDetails();
            Object objref2 = jndiContext.lookup(jndiname2);
            HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote1 = home1.create();
            
            String memberid=request.getParameter("mid");
            Debug.print("Member id in servlet MembStatusAjaxAction servlet :" +memberid);
            
            objMember=remote1.displayMemberDetail(memberid);
            
            String membstatus=objMember.getStatusName();
            String membtyp=objMember.getMembershipTypeName();
           // String addon=objMember.getFamilyAddOns();
            
            int addon=0;
            
	    if(objMember.getFamilyAddOn()!=null)
	    {
		List lst=(List)objMember.getFamilyAddOn();
		addon=lst.size();
            }
            else
            {
		addon=0;
            }
            
            String addct=String.valueOf(addon);
            Debug.print("addon :"+addct);
            
            
            String membdet=membstatus+"#"+membtyp+"#"+addct;
            
            Debug.print("objMember.getStatusName() :"+membstatus);
            Debug.print("objMember.getMembershipTypeName() :"+membtyp);
            Debug.print("objMember.getFamilyAddOn() :"+addon);
            
            //get the member id existing or not status and respond
            
            writer.println("<memberstatus><![CDATA[" + membdet + "]]></memberstatus>");
            
            //close the write
            writer.close();
            
        }
   
        catch(Exception e) {
            System.out.println(e);
        }
        
        return null;
        
    }
}
