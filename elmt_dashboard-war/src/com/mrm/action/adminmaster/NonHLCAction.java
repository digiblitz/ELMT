/*
 * NonUseaAction.java
 *
 * Created on September 7, 2006, 3:37 PM
 */

package com.mrm.action.adminmaster;

import com.hlcnonhlc.org.util.Debug;
import com.hlcnonhlc.org.util.HLCNonHLCOrgMaster;
import com.hlcnonhlc.stateless.HLCKaveryNonHLCOrgSessionRemote;
import com.hlcnonhlc.stateless.HLCKaveryNonHLCOrgSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import java.util.Properties;
import java.text.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class NonHLCAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        boolean bol;
        
      	String process="";
	String name = "ejb/HLCKaveryNonHLCJNDI";
        String fwd="";
        HttpSession session=request.getSession();
         try {
                Context jndiContext = getInitialContext();
                Object obj=jndiContext.lookup(name);
                HLCKaveryNonHLCOrgSessionRemoteHome home = (HLCKaveryNonHLCOrgSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryNonHLCOrgSessionRemoteHome.class);
                HLCKaveryNonHLCOrgSessionRemote remote = home.create();

		//ContactTypeMasterBean objNonHLC=new ContactTypeMasterBean();
                HLCNonHLCOrgMaster objNonHLC = new HLCNonHLCOrgMaster();

                process=request.getParameter("process");
                
                if(process!=null)
                {
                if(process.equals("Add"))
                {
                    fwd="nonUseaAddPage";
                     System.out.println("\n inside add...\n");
                }
               
                
                if(process.equals("Insert"))
                {
                    
                System.out.println("\n inside add...\n");
                String textfield=request.getParameter("txtorgname");

                System.out.println("orgname:"+textfield);

                objNonHLC.setNonuseaOrgName(textfield);

                boolean stat=remote.addNonUSEAOrgMaster(objNonHLC);
                System.out.println("\n add Status. ..\n"+stat);
                String statusadd=String.valueOf(stat);
                
                    System.out.println("\n after add list...\n");
                    Vector nonUseaList=new Vector();
                    nonUseaList = remote.displayNonUSEAOrgDetails();
                    request.setAttribute("nonUseaList",nonUseaList);   
                    
                    if(stat==false)
                    {
                        request.setAttribute("statusadd",statusadd);
                        request.setAttribute("err","st"); 
                        fwd="nonUseaAddPage";
                    }
                    else
                    {
                        request.setAttribute("err","");
                        fwd="nonUseaAddConfirm";
                    }
                }
              
                 if(process.equals("List"))
                {
                   
                    System.out.println("\n inside list...\n");
                    Vector nonUseaList=new Vector();
                    nonUseaList = remote.displayNonUSEAOrgDetails();
                    request.setAttribute("nonUseaList",nonUseaList);  
                    request.setAttribute("err",""); 
                    fwd="ListDisp";
                 }
                }
                 if(process.equals("Edit"))
                {
                   
                    System.out.println("\n inside edit...\n");
                    
                    Vector nonUseaEdit=new Vector();
                    nonUseaEdit=(Vector)session.getAttribute("nonUseaEdit");
                    String vid=request.getParameter("hlcid");
                    System.out.print("vid :" +vid);
                  //  System.out.print("nonUseaEdit :"+nonUseaEdit.size());
                     
                    String[] nonhlc=(String[])nonUseaEdit.elementAt(Integer.parseInt(vid));
                    request.setAttribute("nonUseaEdPage",nonhlc);
                    session.setAttribute("non1",nonhlc);
                    //session.setAttribute("");
                    request.setAttribute("err","");                  
                     fwd="nonUseaEdDisp";
                    
                 }
                
                  if(process.equals("Update"))
                {
       
                    System.out.println("\n inside Update...\n");
                    String hlctyp=request.getParameter("hlctyp");
                    String hlctypid=request.getParameter("hlctypid");
                    
                    objNonHLC.setNonuseaOrgId(hlctypid);
                    objNonHLC.setNonuseaOrgName(hlctyp);
//                    request.setAttribute("err","");                                        
                    bol =  remote.editNonUSEAOrgMaster(objNonHLC);
                    System.out.println("Result is : " +bol);
                    if(bol==true)
                    {
                        request.setAttribute("err","");
                        Debug.print("fwd is true "+fwd);                        
                        fwd="nonUseaList";
                    }
                    else
                    {
                  request.setAttribute("statusadd",String.valueOf(bol));
                  request.setAttribute("err","st"); 
                  
                  String[] nonhlc = (String[])session.getAttribute("non1");
                  request.setAttribute("nonUseaEdPage",nonhlc);
                  fwd="nonUseaEdDisp";
                    }
                    
                 }
                
                if(process.equals("Delete"))
                {
                    System.out.println("\n inside Delete confirmation ...\n");
                    Vector nonUseaEdit=new Vector();
                    nonUseaEdit=(Vector)session.getAttribute("nonUseaEdit");
                    String vid=request.getParameter("hlcid");
                    System.out.print("vid :" +vid);
                    System.out.print("nonUseaEdit :"+nonUseaEdit.size());
                     
                    String[] nonhlc=(String[])nonUseaEdit.elementAt(Integer.parseInt(vid));
                    request.setAttribute("nonUseaEdPage",nonhlc);  
                     
                     fwd="nonUseaDelConfirm";
                }
                
                if(process.equals("confirmDelete"))
                {
                    
                    System.out.println("\n inside Delete...\n");
                    String nonhlcOrgId=request.getParameter("orgid");
                    boolean st = remote.deleteNonUSEAOrgMaster(nonhlcOrgId);
                    System.out.println("Result is : "+st);
                    String stat=String.valueOf(st);
                    request.setAttribute("status",stat);
                    
                    fwd="nonUseaList";
                 }                
                             
            } 
            
         catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
        
             return mapping.findForward(fwd);
       
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
