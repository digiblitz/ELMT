/*
 * RenewMembTypAjaxAction.java
 *
 * Created on November 22, 2006, 9:25 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mrm.action;

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import com.util.OptionsBuilder;
import com.hlcform.util.Debug;
import com.hlcform.util.HLCMemberDetails;
import java.io.PrintWriter;
import java.util.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author karthikeyan
 * @version
 */

public class RenewMembTypAjaxAction extends DispatchAction {
    
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
     public final ActionForward memberType(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

            Debug.print("RenewMembTypAjaxAction servlet called .........");
            
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.kaverysless");
            String jndiname2=mr.getMessage("jndi.usrreg");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            
            HLCMemberDetails objMember = new HLCMemberDetails();
            Object objref2 = jndiContext.lookup(jndiname2);
            HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote1 = home1.create();
            
             Object objref = jndiContext.lookup(jndiname);
                                     
            HLCkaverySessionBeanStatlessRemoteHome home =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote remote = home.create();
                       
            Debug.print(" :::: MembStatusAjaxAction servlet inside upgrade block ::::");
            
            HttpSession session=request.getSession();
            String memberid=(String)session.getAttribute("memberId");
            String userTypeId=(String)session.getAttribute("userTypeId");
            String periodval=(String)session.getAttribute("periodValue");
            String process=(String)request.getParameter("process");
            String Str_year = request.getParameter("year");
            
            
            int periodValue=Integer.parseInt(periodval);
            
            Debug.print("Member id in servlet MembStatusAjaxAction servlet :" +memberid);
            Debug.print("userTypeId in servlet MembStatusAjaxAction servlet :" +userTypeId);
            Debug.print("periodValue in servlet MembStatusAjaxAction servlet :" +periodValue);
            Debug.print("process in servlet MembStatusAjaxAction servlet :" +process);
            Debug.print("In servlet year is "+Str_year);    
            int year = 0;
            if(Str_year!=null && Str_year.trim().length()!=0){
                year = Integer.parseInt(Str_year);
            }

                Debug.print("memberType method is calling...........");
                //AdvertiseSessionRemote advRemote  = initializeEJB(request);
                                          
		 ArrayList membTypes=new ArrayList();
                membTypes=remote1.getMebershipTypeBasedOnPeriodValue(userTypeId,periodValue,process,year);
		return getXmlContent(request,response,membTypes);

    }
      public final ActionForward getXmlContent(HttpServletRequest request,HttpServletResponse response,ArrayList vec) throws Exception {
		 String xml = null;
		    try {
                       xml = new OptionsBuilder().getMemberContent(vec);
                       Debug.print("XmlContent = "+xml); 		   		     
		    } catch (Exception ex) {
		      // Send back a 500 error code.
		      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Can not create response");
		      return null;
		    }
		    // Set content to xml
		    response.setContentType("text/xml; charset=UTF-8");
		    response.setHeader("Cache-Control", "no-cache");
		    PrintWriter pw = response.getWriter();
		    pw.write(xml);
		    pw.close();
		    return null;     
	}
      
     public final ActionForward regiMemberType(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

            Debug.print("RenewMembTypAjaxAction Regi Member Type called .........");
            
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");            
            
            String newJNDI = mr.getMessage("ejb/MembershipTypeJNDI");
            Context jndiContext = new InitialContext();
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            
            
            HLCMemberDetails objMember = new HLCMemberDetails();

            String name2 = "ejb/HLCKaveryMembershipTypeJNDI";

            Object obj2=jndiContext.lookup(name2);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome2 = (HLCKaveryMembershipTypeSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj2,HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote2 = memberHome2.create();
            
            String Str_year = request.getParameter("year");
            
            Debug.print("In servlet year is "+Str_year);    
            
            int year = 0;
            if(Str_year!=null && Str_year.trim().length()!=0){
                year = Integer.parseInt(Str_year);
            }

                Debug.print("memberType method is calling...........");
                //AdvertiseSessionRemote advRemote  = initializeEJB(request);
                                          
		Vector membTypes1 =memberRemote2.displayMembershipTypeOnYearAndTypeName("human",year);
                Debug.print("membTypes.size() "+ membTypes1.size());
                
                ArrayList membTypes = new ArrayList();
                Enumeration itrators= (Enumeration)membTypes1.elements();
                
                while(itrators.hasMoreElements()){
                        String[] sarray = (String[]) itrators.nextElement();
                        membTypes.add(sarray);
                }

                Debug.print("membTypes size is "+membTypes.size());
                
		return getXmlContent(request,response,membTypes);

    }
     
     public final ActionForward memberTypeIdandAmt(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");            
            
            String newJNDI = mr.getMessage("ejb/MembershipTypeJNDI");
            Context jndiContext = new InitialContext();
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            String name2 = "ejb/HLCKaveryMembershipTypeJNDI";

            Object obj2=jndiContext.lookup(name2);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome2 = (HLCKaveryMembershipTypeSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj2,HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote2 = memberHome2.create();
            
            Debug.print("-------Get Member Type Id And Amount On Type Name and Year---------");
            
            String membType = request.getParameter("membType");
            String year = request.getParameter("year");
            int yr =0;
            if(year!=null && year.trim().length()!=0){
                yr = Integer.parseInt(year);
            }
            Debug.print("Parsed new Year is "+yr);
            
            ArrayList newId_amount = memberRemote2.getMembershipTypeIdAndAmt(membType,yr);
            
            String idValue = (String) newId_amount.get(0);
            String amount = (String) newId_amount.get(1);
            
            String finalStr = idValue+"#"+membType+"#"+amount;
            
            Debug.print("Final String is "+finalStr);
            
            PrintWriter out = response.getWriter();
            
            response.setContentType("text/xml; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");            
            
            out.println("<membValue>"+finalStr+"</membValue>");
            
            return null;
     }
}