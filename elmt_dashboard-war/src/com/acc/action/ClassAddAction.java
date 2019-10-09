/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acc.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlccommon.util.Debug;
import java.io.*;
import java.net.*;

import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author vardeep
 */
public class ClassAddAction extends Action {
   
   
    
   public ActionForward execute(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       try{
       MessageResources mr=getResources(request);
                String mahanadhiJndi=mr.getMessage("jndi.acc");
                Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
                Context jndiContext = new InitialContext();
                Object maha=jndiContext.lookup(mahanadhiJndi);
                HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
                HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();               
                HttpSession session = request.getSession();
                String process = request.getParameter("process");
                
                if(process!=null && process.equalsIgnoreCase("initCreate"))
                {
                    return mapping.findForward("classAdd");
                }
                
                
                if(process!=null && process.equalsIgnoreCase("createClass"))
                {
                    String classname = request.getParameter("className");
                    boolean bol = mahaRemote.createClass(classname);
                    Debug.print("Result for add in Servlet : "+bol);
                      if (bol == true){
                       
                          Debug.print("Result for add in Servlet : "+bol);
                          return mapping.findForward("classAddSuccess");                          
                      }
                      else{
                          request.setAttribute("err","st");
                          return mapping.findForward("classAdd");
                       }
                }
                
         
                if(process!= null && process.equalsIgnoreCase("list"))
         {
             ArrayList objList=(ArrayList)mahaRemote.getClassDetails();
             request.setAttribute("list", objList);
             return mapping.findForward("classList");
         }
                 if(process.equals("edit")){
                           
                    String classId = request.getParameter("classId");
                    String className = request.getParameter("className");
                    Debug.print("classId"+classId);
                    Debug.print("className"+className);

                    ArrayList objList1 = mahaRemote.getClassNameDetailsById(classId);
                    Debug.print("got final results");
                    request.setAttribute("objList1",objList1);
                    return mapping.findForward("frmEditClassName");            
                }
                
                 if(process.equals("update")){
                    Debug.print("\n Inside Class Update...\n");                                          
                    String className,classId;

                    className = request.getParameter("className");
                    classId = request.getParameter("classId");
                    
                    Debug.print("classId is "+classId);
                    Debug.print("className is "+className);
                    boolean bol = mahaRemote.editClassName(classId,className);
                    Debug.print("Update result is "+bol);
                    if(bol==true){
                            ArrayList objlist = (ArrayList)mahaRemote.getClassDetails();
                            Iterator itr = objlist.iterator();
                            request.setAttribute("list",objlist);
                            Debug.print("got in true");
                            request.setAttribute("err","");
                            return mapping.findForward("classList");
                    }
                    else{
                        request.setAttribute("err","st");
                        ArrayList objList1 = mahaRemote.getClassNameDetailsById(classId);
                        request.setAttribute("objList1",objList1);
                        return mapping.findForward("frmEditClassName");
                    }
                }
   }
   
   catch(Exception e)
   {
       System.out.println("Class Action Error " + e.getMessage());
   }
            return null;
   }
     
} 

   
    

