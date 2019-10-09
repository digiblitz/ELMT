/*
 * SearchHorseAction.java
 *
 * Created on November 17, 2006, 1:18 PM
 */

package com.mrm.action;

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.Debug;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.rmi.PortableRemoteObject;
import java.util.*;
import javax.naming.*;
import org.apache.struts.util.*;

/**
 *
 * @author punitha
 * @version
 */

public class SearchHorseAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
              String namingfactory,contextfactory,urlprovider,lookupip,jndiname,jndiname2;
              Context jndiContext = new InitialContext();
              try{
                    MessageResources mr=getResources(request);
                    namingfactory=mr.getMessage("ejbclient.namingfactory");
                    contextfactory=mr.getMessage("ejbclient.contextfactory");
                    urlprovider=mr.getMessage("ejbclient.urlprovider");
                    lookupip=mr.getMessage("ejbclient.ip");
                    jndiname=mr.getMessage("jndi.kaverysless");
                     
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                   
                    Object objref = jndiContext.lookup(jndiname); 
                    HLCkaverySessionBeanStatlessRemoteHome home =(HLCkaverySessionBeanStatlessRemoteHome)
                    PortableRemoteObject.narrow(objref, HLCkaverySessionBeanStatlessRemoteHome.class);
                    HLCkaverySessionBeanStatlessRemote remote = home.create();
                    
                    String searchProcess = request.getParameter("searchProcess");
                    HttpSession session = request.getSession();
                    String memberId = (String)session.getAttribute("memberId");
                    System.out.println(memberId+"=memberId");
                    String source = request.getParameter("source");
                    
                    if(source!=null && source.trim().length()!=0 && source.equalsIgnoreCase("fromEventEntry")){
                        session.setAttribute("forEE",source);
                    }
                    if(searchProcess!=null && searchProcess.equals("initView")){
                        return mapping.findForward("frmMemExistingHorse");
                    }
                        
                    if(searchProcess!=null  && searchProcess.equalsIgnoreCase("searchResult")){
                        
                            String hName =request.getParameter("horseSearchName");
                            String horseMembId = request.getParameter("memberId");
                            String oName = request.getParameter("ownerName");
                            String rName = request.getParameter("riderName");
                            String oLastName = request.getParameter("ownerLastName");
                            String rLastName = request.getParameter("riderLastName");
                            
                            String horseName =hName.trim();
                            String horseMemberId = horseMembId.trim();
                            String ownerName = oName.trim();
                            String riderName = rName.trim();
                            String ownerLastName = oLastName.trim();
                            String riderLastName = rLastName.trim();
                            
                            Debug.print("Search Action horseName:" + horseName);
                            Debug.print("Search Action horseMemberId:" + horseMemberId);
                            Debug.print("Search Action ownerName:" +ownerName);
                            Debug.print("Search Action riderName:" +riderName); 
                            Debug.print("Search Action ownerLastName:" +ownerLastName);
                            Debug.print("Search Action riderLastName:" +riderLastName); 
                            
                            Debug.print("Search Action horseName:" + hName);
                            Debug.print("Search Action horseMemberId:" + horseMembId);
                            Debug.print("Search Action ownerName:" +oName);
                            Debug.print("Search Action riderName:" +rName); 
                          
                            ArrayList resultDetails =(ArrayList)remote.searchHorse(horseMemberId,horseName,ownerName,riderName,ownerLastName,riderLastName);
                            //ArrayList resultDetails =(ArrayList)remote.searchHorse(horseMemberId,horseName,ownerName,riderName);
                            request.setAttribute("horseResultDetails",resultDetails);
                            String memberStatus=remote.getMemberStatus(memberId);
                            Debug.print("memberStatus :"+memberStatus);
                            request.setAttribute("memberStatus",memberStatus);
                            return mapping.findForward("frmHorseSearchResult");  
                    }
                   
                else if(searchProcess!=null  && searchProcess.equalsIgnoreCase("initAdminPage")){
                        return mapping.findForward("AdminHorseSearch");
                }     
                else if(searchProcess!=null  && searchProcess.equalsIgnoreCase("searchAdminResult")){
                            String hName =request.getParameter("horseSearchName");
                            String horseMembId = request.getParameter("memberId");
                            String oName = request.getParameter("ownerName");
                            String rName = request.getParameter("riderName");
                            String oLastName = request.getParameter("ownerLastName");
                            String rLastName = request.getParameter("riderLastName");
                            
                            String horseName =hName.trim();
                            String horseMemberId = horseMembId.trim();
                            String ownerName = oName.trim();
                            String riderName = rName.trim();
                            String ownerLastName = oLastName.trim();
                            String riderLastName = rLastName.trim();
                            
                            Debug.print("Search Action horseName:" + horseName);
                            Debug.print("Search Action horseMemberId:" + horseMemberId);
                            Debug.print("Search Action ownerName:" +ownerName);
                            Debug.print("Search Action riderName:" +riderName); 
                            
                            Debug.print("Search Action horseName:" + hName);
                            Debug.print("Search Action horseMemberId:" + horseMembId);
                            Debug.print("Search Action ownerName:" +oName);
                            Debug.print("Search Action riderName:" +rName);
                          
                            //ArrayList resultDetails =(ArrayList)remote.searchHorse(horseMemberId,horseName,ownerName,riderName);
                            ArrayList resultDetails =(ArrayList)remote.searchHorse(horseMemberId,horseName,ownerName,riderName,ownerLastName,riderLastName);
                            request.setAttribute("horseResultDetails",resultDetails);
                            return mapping.findForward("AdminSearchHorseResult");
                        
                }
                    
            }
            catch(Exception horseSearch){
                   Debug.print("Exception occurs while getting elements for Horse Search::" + horseSearch.getMessage());  
            }
        
        return null;
        
    }
}
