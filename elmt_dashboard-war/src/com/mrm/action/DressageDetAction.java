/*
 * DressageDetAction.java
 *
 * Created on December 20, 2006, 1:01 PM
 */

package com.mrm.action;

import com.hlcmro.org.HLCVaigaiSessionRemote;
import com.hlcmro.org.HLCVaigaiSessionRemoteHome;
import java.util.Enumeration;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlccommon.util.Debug;
import javax.naming.Context;
import javax.naming.InitialContext;
/**
 *
 * @author hari
 * @version
 */

public class DressageDetAction extends Action {
    
    private final static String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");

        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();

        Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();   
        
        HttpSession session=request.getSession();
        
        String process = (String) request.getParameter("process");
        Debug.print("Process in Servlet "+process);
 
        if(process.equals("addevnt")){
            Debug.print("\n Inside Insert Dressage Lvl Details\n");                                          
            return mapping.findForward("frmDressageLvlAdd");
        }

        if(process.equals("insertevnt")){
            Debug.print("\n Inside Insert Dressage Lvl Details\n");
            String LevelName =  request.getParameter("LevelName");
            Debug.print("Level Name "+LevelName);
            LevelName = LevelName.trim();
            boolean bol = remote.insertHorseDressageTrial(LevelName);
            Debug.print(" Succeffully Inserted : "+bol);            
            if (bol == true){
                return mapping.findForward("frmDressageLvlConf");
            }
            else{
                request.setAttribute("err","st");
                return mapping.findForward("frmDressageLvlAdd");
            }
        }
        
        if(process.equals("listevnt")){
            Debug.print("Inside List Of Dressage Lvl Details");
            request.setAttribute("listevnt",null);
            return mapping.findForward("frmDressageLvlList");
        }
        
        if(process.equals("lstevnt")){
            Debug.print("Inside Lst Of Dressage Lvl Details");
            String stat = (String)  request.getParameter("status");
            boolean bol= false;
            Vector vObj = null;
                if(stat==null){
                    Debug.print("Null");
                    request.setAttribute("value","novalue");
                    vObj = (Vector)remote.displayAllHorseDressageTrial(bol);
                }
                else if(stat.equalsIgnoreCase("Activate")){
                Debug.print("Inside Activate");
                    bol = true;
                    vObj = (Vector)remote.displayAllHorseDressageTrial(bol);
                    Enumeration it = vObj.elements();
                            while (it.hasMoreElements()) {
                                String[] s = (String[]) it.nextElement();
                                Debug.print(" List Content...\n");
                                System.out.println("=========================");
                                for(int i=0;i<s.length;i++)
                                System.out.println(i+" " + s[i]);
                                System.out.println("=========================");
                            }
                }
                else if (stat.equalsIgnoreCase("Deactivate")){
                Debug.print("Inside Deactivate");
                    bol = false;
                    vObj = (Vector)remote.displayAllHorseDressageTrial(bol);
                    Enumeration it = vObj.elements();
                        while (it.hasMoreElements()) {
                            String[] s = (String[]) it.nextElement();
                            Debug.print(" List Content...\n");
                            System.out.println("=========================");
                            for(int i=0;i<s.length;i++)
                            System.out.println(i+" " + s[i]);
                            System.out.println("=========================");
                        }
                }
            request.setAttribute("listevnt",vObj);
            return mapping.findForward("frmDressageLvlList");
        }

        if(process.equals("editevnt")){
            Debug.print("\n Inside Edit Dressage Lvl Details\n");
            String eventId = (String) request.getParameter("eventId");
            Vector vObj = (Vector)remote.displayHorseDressageTrial(eventId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(i+" " + s[i]);
                System.out.println("=========================");
            }
            request.setAttribute("editevnt",vObj);
            return mapping.findForward("frmDressageLvlEdit");
        }
        
        if(process.equals("evntActivate")){            
            Debug.print("\n Inside Edit Dressage Lvl Details\n");   
            String eventId = (String) request.getParameter("eventId");            
            boolean bol = remote.activateHorseDressageTrial(eventId);
            Debug.print(" Succeffully Activated : "+bol);
            //Vector vObj = (Vector)remote.displayAllHorseDressageTrial(true);
            request.setAttribute("listevnt",null);
            request.setAttribute("status","Activate");
            return mapping.findForward("frmDressageLvlList");
        }
        
        if(process.equals("evntDeactivate")){
            Debug.print("\n Inside Edit Dressage Lvl Details\n");   
            String eventId = (String) request.getParameter("eventId");            
            boolean bol = remote.deleteHorseDressageTrial(eventId);
            Debug.print(" Succeffully Dectivated : "+bol);
            //Vector vObj = (Vector)remote.displayAllHorseDressageTrial(false);
            request.setAttribute("status","Deactivate");
            request.setAttribute("listevnt",null);
            return mapping.findForward("frmDressageLvlList");
        }        
        
        if(process.equals("updateevnt")){
            Debug.print("\n Inside Update Dressage Lvl Details\n");
            String eventId = (String) request.getParameter("eventId");
            String eventName=(String) request.getParameter("lvlNme");
            eventName = eventName.trim();
            Debug.print("Session prevname is "+session.getAttribute("prevename"));
 
            boolean bol = remote.updateHorseDressageTrial(eventName,eventId);
            Debug.print("Update Result :"+bol);
            String prevnme = (String)session.getAttribute("prevename");
            Debug.print("Previous Name is "+prevnme);            
            if(bol==true){
                 request.setAttribute("listevnt",null);
                 return mapping.findForward("frmDressageLvlList");
/*               Vector vObj = (Vector)remote.displayAllHorseDressageTrial(true);
                request.setAttribute("listevnt",vObj);
                return mapping.findForward("frmDressageLvlList");*/
            }
            else{
                String id = "";
                String nme = "";
                Vector vObj = (Vector)remote.displayHorseDressageTrial(eventId);
                Enumeration it1 = vObj.elements();
                    while (it1.hasMoreElements()) {
                        String[] s = (String[]) it1.nextElement();
                        id  = s[0];
                        nme = s[1];
                    }
                    prevnme = (String)session.getAttribute("prevename");
                    prevnme = prevnme.trim();
                    nme = nme.trim();
                    Debug.print("Previous Name in Else "+prevnme);
                    Debug.print("Event Name is "+eventName);
                    if(prevnme.equalsIgnoreCase(eventName)){
                        Vector vObj1 = (Vector)remote.displayAllHorseDressageTrial(false);
                        request.setAttribute("listevnt",vObj1);
                        return mapping.findForward("frmDressageLvlList");
                    }
                    session.removeAttribute("prevename");
                    session.removeAttribute("preveventId");
                request.setAttribute("editevnt",vObj);
                request.setAttribute("err","st");
                return mapping.findForward("frmDressageLvlEdit");
            }
        }

///        Areana Size
        
       if(process.equals("addsize")){
            Debug.print("\n Inside Add Divisions Details\n");
            return mapping.findForward("frmDressageSizeAdd");
            }
        
        if(process.equals("insertsize")){
            Debug.print("\n Inside Insert Divisions Details\n");  
            String ArenaSz =  request.getParameter("ArenaSz");
            ArenaSz = ArenaSz.trim();
            boolean bol = remote.insertArenaSize(ArenaSz);
            Debug.print(" Succeffully Inserted : "+bol);            
            if (bol == true){
                return mapping.findForward("frmDressageSizeConf");
            }
            else{
                request.setAttribute("err","st");
                return mapping.findForward("frmDressageSizeAdd");
            }
        }
        
        if(process.equals("listsize")){
            Debug.print("Inside List Of Dressage Size Details");
            request.setAttribute("sizeList",null);
            return mapping.findForward("frmDressageSizeList");
        }
        
        if(process.equals("lstsize")){
            Debug.print("Inside Lst Of Dressage Size Details");
            String stat = (String)request.getParameter("status");
            Debug.print("Session prevname is "+session.getAttribute("prevename"));
            boolean bol= false;
            Vector vObj = null;
                if(stat==null){
                    Debug.print("Null");
                    request.setAttribute("value","novalue");
                    vObj = (Vector)remote.displayAllArenaSize(bol);
                }
                else if(stat.equalsIgnoreCase("Activate")){
                Debug.print("Inside Activate");
                    bol = true;
                    vObj = (Vector)remote.displayAllArenaSize(bol);
                    Enumeration it = vObj.elements();
                            while (it.hasMoreElements()) {
                                String[] s = (String[]) it.nextElement();
                                Debug.print(" List Content...\n");
                                System.out.println("=========================");
                                for(int i=0;i<s.length;i++)
                                System.out.println(i+" " + s[i]);
                                System.out.println("=========================");
                            }
                }
                else if (stat.equalsIgnoreCase("Deactivate")){
                Debug.print("Inside Deactivate");
                    bol = false;
                    vObj = (Vector)remote.displayAllArenaSize(bol);
                    Enumeration it = vObj.elements();
                        while (it.hasMoreElements()) {
                            String[] s = (String[]) it.nextElement();
                            Debug.print(" List Content...\n");
                            System.out.println("=========================");
                            for(int i=0;i<s.length;i++)
                            System.out.println(i+" " + s[i]);
                            System.out.println("=========================");
                        }
                }
            request.setAttribute("sizeList",vObj);
            return mapping.findForward("frmDressageSizeList");
        }

        if(process.equals("updatesize")){
            Debug.print("\n Inside Update Dressage Size Details\n");
            String sizeId = (String) request.getParameter("sizeId");
            String arenaSz=(String) request.getParameter("ArnaSz");
            arenaSz = arenaSz.trim();
            String stat = (String)request.getParameter("status");
            //session.setAttribute("prevSz",arenaSz);
            Debug.print("Previous Size is "+arenaSz);
            boolean bol = remote.updateArenaSize(arenaSz,sizeId);
            Debug.print("Update Result :"+bol);
            if(bol==true){
                 request.setAttribute("sizeList",null);
                 return mapping.findForward("frmDressageSizeList");
 /*              Vector vObj = (Vector)remote.displayAllArenaSize(true);
                 request.setAttribute("sizeList",vObj);
                 return mapping.findForward("frmDressageSizeList");*/
            }
            else{
                String id = "";
                String nme = "";
                Vector vObj = (Vector)remote.displayArenaSize(sizeId);
                Enumeration it1 = vObj.elements();
                    while (it1.hasMoreElements()) {
                        String[] s = (String[]) it1.nextElement();
                        id  = s[0];
                        nme = s[1];
                    }
                    String prevSz = (String)session.getAttribute("prevSz");
                    prevSz = prevSz.trim();
                    Debug.print("Previous Name is "+prevSz);
                    Debug.print("New Name is "+nme);
                    if(prevSz.equalsIgnoreCase(nme)){
                        Vector vObj1 = (Vector)remote.displayAllArenaSize(true);
                        Enumeration it = vObj1.elements();
                        while (it.hasMoreElements()) {
                            String[] s = (String[]) it.nextElement();
                            Debug.print(" List Content...\n");
                            System.out.println("=========================");
                            for(int i=0;i<s.length;i++)
                            System.out.println(" " + s[i]);
                            System.out.println("=========================");
                        }                        
                        request.setAttribute("sizeList",vObj1);
                        return mapping.findForward("frmDressageSizeList");
                    }
                    session.removeAttribute("prevSz");
                Vector vObj1 = (Vector)remote.displayArenaSize(sizeId);
                request.setAttribute("sizeEdit",vObj1);
                request.setAttribute("err","st");
                return mapping.findForward("frmDressageSizeEdit");
            }
        }
        
        if(process.equals("editsize")){
            Debug.print("\n Inside Edit Divisions Details\n");  
            String sizeId = (String) request.getParameter("sizeId");
            String stat = (String)request.getParameter("status");
            Debug.print("Status is "+stat);
            Vector vObj = (Vector)remote.displayArenaSize(sizeId);
            Enumeration it = vObj.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                System.out.println("=========================");
                for(int i=0;i<s.length;i++)
                System.out.println(i+" "+s[i]);
                System.out.println("=========================");
            }
            request.setAttribute("sizeEdit",vObj);
            return mapping.findForward("frmDressageSizeEdit");
        }
        
        if(process.equals("evntSizeActivate")){
            Debug.print("\n Inside Activate Dressage Size Details\n");   
            String sizeId = (String) request.getParameter("sizeId");            
            boolean bol = remote.activateArenaSize(sizeId);
            Debug.print(" Succeffully Activated : "+bol);
            Vector vObj = (Vector)remote.displayAllArenaSize(true);
            request.setAttribute("status","Activate");
            request.setAttribute("sizeList",null);
            return mapping.findForward("frmDressageSizeList");
        }
        
        if(process.equals("evntSizeDeactivate")){
            Debug.print("\n Inside Deactivate Dressage Size Details\n");   
            String sizeId = (String) request.getParameter("sizeId");            
            boolean bol = remote.deleteArenaSize(sizeId);
            Debug.print(" Succeffully Dectivated : "+bol);
            Vector vObj = (Vector)remote.displayAllArenaSize(true);
            request.setAttribute("status","Deactivate");
            request.setAttribute("sizeList",null);
            return mapping.findForward("frmDressageSizeList");
        }   
        return null;
    }
}
