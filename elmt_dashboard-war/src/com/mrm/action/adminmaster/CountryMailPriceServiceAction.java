/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.mrm.action.adminmaster;

import com.hlccountry.mail.stateless.HLCKaveryCountryMailPriceSeaaionRemote;
import com.hlccountry.mail.stateless.HLCKaveryCountryMailPriceSeaaionRemoteHome;
import com.hlccountry.mail.util.HLCCountryMailPriceMaster;
import com.hlccountry.mail.util.Debug;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

public class CountryMailPriceServiceAction extends Action {
 
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
                Properties p =new Properties();
                p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
                p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
                p.setProperty( "java.naming.provider.url", "localhost:11199" );
                Context jndiContext = new InitialContext(p);
                
                String name = "ejb/HLCKaveryMailPriceJNDI";
                String name1="ejb/HLCKaveryMembershipTypeJNDI";
                Object obj=jndiContext.lookup(name);
                Object obj1=jndiContext.lookup(name1);
                HLCKaveryCountryMailPriceSeaaionRemoteHome home = (HLCKaveryCountryMailPriceSeaaionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryCountryMailPriceSeaaionRemoteHome.class);
                HLCKaveryCountryMailPriceSeaaionRemote remote = home.create();
                
                HLCKaveryMembershipTypeSessionRemoteHome yearHome = (HLCKaveryMembershipTypeSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj1,HLCKaveryMembershipTypeSessionRemoteHome.class);
                HLCKaveryMembershipTypeSessionRemote yearRemote = yearHome.create();
                
                String countryProcess = request.getParameter("countryProcess");
                               
                HLCCountryMailPriceMaster objCountryMailPrice = new HLCCountryMailPriceMaster();
                HttpSession session = request.getSession();
                System.out.println("\n after InitialContext Beginning emp Client...\n");
/*
* redirects to country price mail price add page......
*/
              if(countryProcess.equals("addCountrymail")){
                    
               
                    //ArrayList lst = remote.getAllMembershipType();
                    //session.setAttribute("mail_lst",lst);
                    int year=2007;
                    Debug.print("year in init add:"+year);
                    Vector objMemb=yearRemote.displayMembershipTypeDetails1(year);
                    request.setAttribute("objMemb",objMemb);
                    request.setAttribute("year",new Integer(year).toString());
                    return mapping.findForward("membership-assign-country-mailprice");
                }
/*
* create a country mail price
*/              else if(countryProcess.equals("memb")){
                    String year = (String)request.getParameter("year");
                   Debug.print("year:"+year);
                  
                   int yr=Integer.parseInt(year);
                   Debug.print("year in :"+year);
                   Vector objMemb=yearRemote.displayMembershipTypeDetails1(yr);
                   request.setAttribute("objMemb",objMemb);
                   request.setAttribute("year",new Integer(year).toString());
                  return mapping.findForward("membership-assign-country-mailprice"); 
                  
                  
}
/*
* create a country mail price
*/
                else if(countryProcess.equals("insert")){
                    
                String countryName = request.getParameter("countryMailTypeName");
                String countryPrice = request.getParameter("countryMailPrice");
                String membership_typeId = request.getParameter("memberMailtypId");
                objCountryMailPrice.setCountryMailTypeName(countryName);
                objCountryMailPrice.setCountryMailPrice(countryPrice);
                objCountryMailPrice.setMembershipTypeId(membership_typeId);
                
                boolean result = remote.addCountryMailPriceMaster(objCountryMailPrice); 
                    if (result == true){
                    return mapping.findForward("home");
                    }
                    else{
                    int year=2007;
                    Debug.print("year in init add:"+year);
                    Vector objMemb=yearRemote.displayMembershipTypeDetails1(year);
                    request.setAttribute("objMemb",objMemb);
                    request.setAttribute("year",new Integer(year).toString());
                    request.setAttribute("err","st");
                    return mapping.findForward("membership-assign-country-mailprice");
                    }
               }
/*
 * show all country mail details...
 */                
                else if(countryProcess.equals("lstCountrymail")){
                 
                  Debug.print("Inside lstCountrymail in CountryMailPriceServiceAction"); 
                  return mapping.findForward("membership-country-mail-list");
                 } 
                 else if(countryProcess.equals("listByYear")){
                    Debug.print("Inside listByYear in CountryMailPriceServiceAction");
                    String year=request.getParameter("year");
                    int finalYear=Integer.parseInt(year);
                    Debug.print("year in lstCountrymail:"+finalYear);
                    ArrayList list = (ArrayList) remote.getCountryMailDetails(finalYear);
                    System.out.print("List size is "+list.size());
                    System.out.print("List value is "+list.toString());
                    request.setAttribute("DisplayCountryDetails",list);
                    request.setAttribute("year",year);
                    return mapping.findForward("membership-country-mail-list");
                 } 
/*
 * updating country price service action..
 */                
        else if(countryProcess.equals("editCountryPrice")){
                String countryTypeId = request.getParameter("countryTypeId");
                String txtCountryName = request.getParameter("txtCountryName");
                String txtCountryAmount = request.getParameter("txtCountryAmount");
                String membership_typeId = request.getParameter("memberMailtypId");
                String year=request.getParameter("year");
                int finalYear=Integer.parseInt(year);
                Debug.print("year in lstCountrymail:"+finalYear);
                System.out.print("countryTypeId" +countryTypeId);
                System.out.print("txtCountryName" +txtCountryName);
                System.out.print("txtCountryAmount" +txtCountryAmount);
                System.out.print("membership_typeId" +membership_typeId);
                        
                    objCountryMailPrice.setCountryMailTypeId(countryTypeId);
                    objCountryMailPrice.setCountryMailPrice(txtCountryAmount);
                    objCountryMailPrice.setCountryMailTypeName(txtCountryName);
                    objCountryMailPrice.setMembershipTypeId(membership_typeId);
              
                boolean resultedit = remote.editCountryMailPriceMaster(objCountryMailPrice);
                if(resultedit==true){
                    request.setAttribute("year",year);
                    ArrayList list = (ArrayList) remote.getCountryMailDetails(finalYear);
//                  Vector vdisp = (Vector)remote.displayCountryMailDetail();
                    request.setAttribute("DisplayCountryDetails",list);
                    return mapping.findForward("membership-country-mail-list");
                }
                else{
                    request.setAttribute("err","st");
                    return mapping.findForward("membership-edit-country-mailprice"); 
                }   
            }
                
        else if(countryProcess.equals("detailsByYear")){ 
        Debug.print("Inside detailsByYear");            
        String year=request.getParameter("year");
        int finalYear=Integer.parseInt(year);
        
        Debug.print("Year in detailsByYear:"+finalYear);  
        Vector vObj=yearRemote.displayMembershipTypeDetails1(finalYear);
        request.setAttribute("MembershipTypeName",vObj);
        request.setAttribute("year",year);
        return mapping.findForward("membership-edit-country-mailprice"); 
        }             
/*      
 * delete country price service action..
 *
 *
 */
 /*
 * getting the countyrmail price id form Lst.jsp for edit .....
 */
            else if(countryProcess.equals("manupPrice")){
                 try{
                    String butVal = request.getParameter("process");
                    String countrymailId = request.getParameter("countrymailId");
                    Debug.print("countrymailId" + countrymailId);
 
                        if(butVal.equals("Edit")){
                                Debug.print("This is from Edit:" + countrymailId);
                                HLCCountryMailPriceMaster  objc = remote.getCountryMailDetailByCntMailId(countrymailId);
				String countryMailTypeName =  objc.getCountryMailTypeName();
				String mailtypeId = objc.getCountryMailTypeId();
				String membertypeId = objc.getMembershipTypeId();
				String mailPrice = objc.getCountryMailPrice();
				String memberName =objc.getMemberShipName();
                                String year=objc.getYear();
                                int finalYear=Integer.parseInt(year);
                                
                                ArrayList lst = remote.getAllMembershipType();
                                session.setAttribute("DisplayEditDetails",objc);                        
                                session.setAttribute("list",lst);
                                Iterator itr = lst.iterator();
                                Vector vObj=yearRemote.displayMembershipTypeDetails1(finalYear);
                                request.setAttribute("MembershipTypeName",vObj);
                                request.setAttribute("year",year);
		while(itr.hasNext())
		{
	  	    String[] s= (String [])itr.next();
		    String membershipTypeId = s[0];
                    String membershipTypeName = s[1];
                }
             return mapping.findForward("membership-edit-country-mailprice");
       }

/*getCountryMailDetailByCntMailId
 * getting the countyrmail price id from lst for delete .....
 */
                     else if(butVal.equals("Delete")){
                            Debug.print("This is from delete:" + countrymailId);
                             HLCCountryMailPriceMaster  objMaster = remote.getCountryMailDetailByCntMailId(countrymailId);
                            session.setAttribute("DisplayDeleteDetails",objMaster);
                            return mapping.findForward("membership-delete-country-mailprice");
                        }
                  }
             catch(Exception eAdvMaupEditDelete){
                 Debug.print("Exception in Action Advertisement Media Edit & Delete:::::::" + eAdvMaupEditDelete);
              }
         }
/*getCountryMailDetailByCntMailId
 * getting dimension id from frmAdvDimensiondelete.jsp for confirmation.....
 */
             else if(countryProcess.equals("deleteCountryPrice")){
               try{
                String countryTypeId = request.getParameter("countryTypeId");
                System.out.println("countrymailId" +countryTypeId);
                String year=request.getParameter("year");
                int finalYear=Integer.parseInt(year);
                Debug.print("year in lstCountrymail:"+finalYear);
                boolean resultDelete = remote.deleteCountryMailPriceMaster(countryTypeId);
                   System.out.println("resultDelete" +resultDelete);
                    if(resultDelete == true){
                    ArrayList list = (ArrayList) remote.getCountryMailDetails(finalYear);
                    int i=0;
                    request.setAttribute("DisplayCountryDetails",list);
                    request.setAttribute("year",year);
                    return mapping.findForward("membership-country-mail-list");
                    }
                    else{
                        request.setAttribute("errStat","eConfirmDelete");
                        ArrayList list = (ArrayList) remote.getCountryMailDetails(finalYear);
                        request.setAttribute("DisplayCountryDetails",list);
                        return mapping.findForward("membership-country-mail-list"); 
                    }
              }
             catch(Exception eConfirmDelete){
                   Debug.print("Exception in  Edit & Delete:::::::" + eConfirmDelete);
                }
             
             }
                 
                
         
                
/* membership-delete-country-mailprice.jsp
 * try block end
 */               
            }
/*
 * catch block starts
 */        
                catch (Exception ex) {
                System.out.println("Caught an exception." + ex);
                ex.printStackTrace();
                }
/*
 * global forward page...
 */        
            return(mapping.findForward("login"));
            }
}
