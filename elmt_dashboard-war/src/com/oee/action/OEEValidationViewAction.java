/*
 * OEEValidationViewAction.java
 *
 * Created on November 7, 2007, 11:36 AM
 */

package com.oee.action;

//import bsh.Remote;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.*;
import com.hlcaccounts.util.Debug;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import org.apache.struts.actions.DispatchAction;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;
import com.hlcutil.*;
import java.util.regex.Pattern;
import java.sql.*;
import java.math.*;

/**
 *
 * @author Vidhya
 * @version
 */
public class OEEValidationViewAction extends DispatchAction {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet OEEValidationViewAction</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet OEEValidationViewAction at " + request.getContextPath () + "</h1>");
        out.println("</body>");
        out.println("</html>");
         */
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
    
    public ActionForward initView(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            Debug.print("OEEValidationViewAction.initView() method calling ..........");
            HLCKrishnaStatelessRemote remote =  initializeEJB(request);
            
            Vector eventTypeDetails = null;
            ArrayList userTypeDetails = new ArrayList();
            ArrayList divisionTypeDetails = new ArrayList();
            ArrayList areaDetails = new ArrayList();
           
            
            String areaId="";
            String divisionId="";
            eventTypeDetails = remote.getAllEventTypes();
            userTypeDetails = remote.getUserTypes();
            divisionTypeDetails = remote.getDivisions();
            areaDetails = remote.getAllAreaMasters();
                      
            request.setAttribute("eventTypeDetails",eventTypeDetails);
            request.setAttribute("userTypeDetails",userTypeDetails);
            request.setAttribute("divisionId",divisionId);
            request.setAttribute("divisionTypeDetails",divisionTypeDetails);
            request.setAttribute("areaDetails",areaDetails);
            request.setAttribute("areaId",areaId);                      
            return mapping.findForward("frmViewQualificationDetails");
     
    }
     public ActionForward qualificationView(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            Debug.print("OEEValidationViewAction.qualificationView() method calling .........."); 
            HLCKrishnaStatelessRemote remote =  initializeEJB(request);
            
            String eventTypeId = request.getParameter("eventType");
            Debug.print("eventTypeId in servlet"+eventTypeId);
            String userTypeId = request.getParameter("userType");
            Debug.print("userTypeId in servlet"+userTypeId);
            String divisionId = request.getParameter("division");
            Debug.print("divisionId in servlet"+divisionId);
            
            /*String divId="";            
            if(divisionId.equalsIgnoreCase(" ")){              
             divId="";     
            }
            else 
            {
               divId="divisionId";   
            }*/
            
            String areaId = request.getParameter("selArea");
            Debug.print("areaId in servlet"+areaId);            
            String chmpStatus = request.getParameter("chmpStatus");
            Debug.print("chmpStatus in servlet"+chmpStatus);
            
            String chmpStat="";
            if(chmpStatus.equalsIgnoreCase("yes")){              
             chmpStat="True";     
            }
            else 
            {
               chmpStat="False";   
            }
            
            Vector eventTypeDetails = null;
            ArrayList userTypeDetails = new ArrayList();
            ArrayList viewEventDetails=new ArrayList();
            ArrayList divisionTypeDetails = new ArrayList();
            ArrayList areaDetails = new ArrayList();
            
            areaDetails = remote.getAllAreaMasters();
            eventTypeDetails = remote.getAllEventTypes();
            userTypeDetails = remote.getUserTypes();
            divisionTypeDetails = remote.getDivisions();
            viewEventDetails=remote.getValidationDetails(eventTypeId,userTypeId,divisionId,areaId,chmpStat);
            
            request.setAttribute("viewEventDetails",viewEventDetails);
            request.setAttribute("eventTypeDetails",eventTypeDetails);
            request.setAttribute("userTypeDetails",userTypeDetails);
            request.setAttribute("eventTypeId",eventTypeId);
            request.setAttribute("userTypeId",userTypeId);
            request.setAttribute("divisionId",divisionId);
            request.setAttribute("divisionTypeDetails",divisionTypeDetails);
            request.setAttribute("areaId",areaId); 
            request.setAttribute("areaDetails",areaDetails);
            request.setAttribute("chmpStatus",chmpStatus); 
            return mapping.findForward("frmViewQualificationDetails");
     
    }
    private HLCKrishnaStatelessRemote initializeEJB(HttpServletRequest request) throws Exception{              
            
                MessageResources mr = getResources(request);
                String namingfactory=mr.getMessage("ejbclient.namingfactory");
                String contextfactory=mr.getMessage("ejbclient.contextfactory");
                String urlprovider=mr.getMessage("ejbclient.urlprovider");
                String lookupip=mr.getMessage("ejbclient.ip");
                String jndiname=mr.getMessage("jndi.pc");

                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);
                
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname);  
                HLCKrishnaStatelessRemoteHome home = (HLCKrishnaStatelessRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objref,HLCKrishnaStatelessRemoteHome.class);
                HLCKrishnaStatelessRemote remote = home.create();
                
                return remote;   
          
    } 

   
}
