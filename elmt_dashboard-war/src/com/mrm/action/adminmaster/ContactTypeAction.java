/*
 * ContactTypeAction.java
 *
 * Created on September 7, 2006, 2:33 PM
 */

package com.mrm.action.adminmaster;

import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlcmrm.util.HLCContactTypeMaster;
import com.hlcmrm.util.Debug;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.regex.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class ContactTypeAction extends Action {
    
       public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
      try {

            Properties p =new Properties();
            p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
            p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
            p.setProperty( "java.naming.provider.url", "localhost:11199" );
            Context jndiContext = new InitialContext(p);
            
                    String name = "ejb/HLCKaveryMrmJNDI";
                    System.out.println("\n after InitialContext Beginning emp Client...\n");
                    Object objref = jndiContext.lookup(name);
                   
                    HLCkaveryStatelessRemoteHome home = (HLCkaveryStatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref, HLCkaveryStatelessRemoteHome.class);

                    HLCkaveryStatelessRemote remote = home.create();
                    System.out.println("After Create...");
                    HLCContactTypeMaster objContactTypeMaster = new HLCContactTypeMaster();
                    
                    String conprocess=request.getParameter("conprocess");
                    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                    System.out.println("ContactTypeAction Process:"+conprocess);

		  if(conprocess!=null){

		               ArrayList contType = null;
                    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                    /*
                     * Redirects to contact type add page
                     */
                    if(conprocess.equals("contacttype")){
                       //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
			contType = remote.getAllContactType();
                        if(contType != null){
                            System.out.println("**********contType is "+contType.size());
                        }
                        request.setAttribute("contType",contType);
                        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                        return mapping.findForward("addcontacttype");
                        
                    }
                    /*
                     * Creation of new contact type
                     */
                    else if (conprocess.equals("create")){
                    System.out.println("\n Create contact type has been initiated...\n");

                    String textfield1=request.getParameter("txtcontactname");
                    System.out.println("contact name:"+textfield1);
                    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
		            String textfield2=request.getParameter("txtcontactdescription");
                    System.out.println("before type");
		            String textfield4=request.getParameter("ContactType");
                    System.out.println("contact Type:"+textfield4);
                    String textfield5=request.getParameter("status");
                    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011

                    objContactTypeMaster.setContactTypeName(textfield1);
                    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
		    objContactTypeMaster.setContactTypeDescription(textfield2);
		    objContactTypeMaster.setContactType(textfield4);
                    System.out.println("contactType"+textfield4);
                    objContactTypeMaster.setStatus(textfield5);
                    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                    boolean stat=remote.addContactTypeMaster(objContactTypeMaster);
                    String status=String.valueOf(stat);
                    
                    request.setAttribute("status","st");
                    Debug.print("Result of insert is "+stat);
                    if(stat==true)
                    {
                        request.setAttribute("err",null);
                        //return(mapping.findForward("home"));
                        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                        return(mapping.findForward("LstContactType"));
                        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                    }
                    else
                    {
                        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
			contType = remote.getAllContactType();
		        request.setAttribute("contType",contType);
                        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                        request.setAttribute("err","st");
                         return(mapping.findForward("addcontacttype"));
                    }
                   
                                            
                    }
                    
                    /*
                     * Listing of the contact type master
                     */
                    
                    else if (conprocess.equals("list")){
                        System.out.println("\n Listing of contact types has been initiated...\n");

                        Vector vlistObj = (Vector)remote.displayContactTypedetails();
                        Debug.print("Size of vector is "+vlistObj.size());
                        request.setAttribute("vlistObj",vlistObj);
                        return(mapping.findForward("lstcontacttype"));
                        
                    }
                    
                    /*
                     * Editing the existing contact type master list
                     */
                    
                    else if(conprocess.equals("editcontype")){
                            System.out.println("\n Opening edit mode for contact types ...\n");
                            String proButton = request.getParameter("butValue");
                            Debug.print("proButton" + proButton);
                            String contypeId = request.getParameter("contacttypeid");
                            Debug.print("contypeId" +  contypeId );
                                 if(proButton.equals("Edit")){
                                        String editcontypedetails [] = remote.getContactTypedetailsById(contypeId);
                                        Debug.print("editcontypedetails" +  editcontypedetails );
                                        request.setAttribute("editcontypedetails",editcontypedetails);
                                        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                        contType = remote.getAllContactType();
                                    if(contType != null){
                                       System.out.println("**********contType is "+contType.size());
                                       }

                                     request.setAttribute("contType",contType);
                                     //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                        return mapping.findForward("edtcontacttype");
                                  }
//================================================Delete Contact Type==============================================                         
                                 else if(proButton.equals("Delete")){
                                        String editcontypedetails [] = remote.getContactTypedetailsById(contypeId);
                                        request.setAttribute("editcontypedetails",editcontypedetails);
                                        return mapping.findForward("dltcontacttype");
                                  }
                             
                    }
                    
//================================================Edit & Update =====================================================================================                        
            else if(conprocess.equals("updateContype")){
                String contacttypeId = request.getParameter("contacttypeId");
                String textfield3=request.getParameter("txtcontactname");
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                String textfield6=request.getParameter("txtcontactdescription");
                String textfield7=request.getParameter("ContactType");
                String textfield8=request.getParameter("status");
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                String oldContactName = request.getParameter("oldContactName");
                System.out.println("contact name:"+textfield3);
                objContactTypeMaster.setContactTypeId(contacttypeId);
                objContactTypeMaster.setContactTypeName(textfield3);
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                 System.out.println("contact name:"+textfield3);
                objContactTypeMaster.setContactTypeDescription(textfield6);
                 System.out.println("contact desc:"+textfield6);
                objContactTypeMaster.setContactType(textfield7);
                 System.out.println("contact type:"+textfield7);
                objContactTypeMaster.setStatus(textfield8);
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                
                boolean resultedit = false;
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                /*if(textfield3.equalsIgnoreCase(oldContactName)){
                    resultedit = true;
                }else{*/
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                    resultedit = remote.editContactTypeMaster(objContactTypeMaster);
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                //}
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                Debug.print("Result of update is "+resultedit);
                if(resultedit==true){
                Vector vlistObj = (Vector)remote.displayContactTypedetails();
                request.setAttribute("vlistObj",vlistObj);
                request.setAttribute("err",null);
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                //return(mapping.findForward("lstcontacttype"));
                return(mapping.findForward("LstContactType"));
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                }
                else{
                request.setAttribute("err","st");
                String editcontypedetails [] = remote.getContactTypedetailsById(contacttypeId);
                Debug.print("editcontypedetails" +  editcontypedetails );
                request.setAttribute("editcontypedetails",editcontypedetails);
                request.setAttribute("ContactName",textfield3);
                System.out.println("contact name:"+textfield3);
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                 contType = remote.getAllContactType();
                 request.setAttribute("contType",contType);
                 //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                return mapping.findForward("edtcontacttype");
                
                }   
            }
                    
//===============================================Confirm Delete ====================================================================================                                        

       else if(conprocess.equalsIgnoreCase("confirmDelete")){
            
                        String contacttypeId = request.getParameter("contacttypeid");
                        Debug.print("contacttypeId"+contacttypeId);
			String textfield3=request.getParameter("txtcontactname");
                        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                        String textfield6=request.getParameter("txtcontactdescription");
                        String textfield7=request.getParameter("ContactType");
                        // String textfield8=request.getParameter("status");
			String textfield8=request.getParameter("contacttypeid");
			String chkConIdArr[] = request.getParameterValues("chk");
                        
                        for(int i=0;i<chkConIdArr.length;i++)
                              Debug.print("ContactTypeAction Delete() checked records: "+chkConIdArr[i]);

                        boolean resultDelete=false;

	                if(chkConIdArr!=null){
                                      //boolean resultDelete = remote.deleteContactTypeMaster(contacttypeId);
                                       resultDelete = remote.deleteContactTypeMaster(chkConIdArr);
                                       Debug.print("ContactTypeAction Delete() result:"+resultDelete);
                                       // Vector vlistObj = (Vector)remote.displayContactTypedetails();
                                       //  request.setAttribute("vlistObj",vlistObj);
                       //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                    if(resultDelete == true){
                       //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
		                       Debug.print("ContactTypeAction Delete() result:"+resultDelete);
				       if(chkConIdArr!=null){
						 String editcontypedetails [] = remote.getContactTypedetailsById(chkConIdArr[0]);
                                                 request.setAttribute("editcontypedetails",editcontypedetails);
					        }
                                             return(mapping.findForward("LstContactType"));
                                             //return(mapping.findForward("lstcontacttype"));
                        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                    }
                                    else{
                        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                        if(chkConIdArr!=null){
                                                 String del=String.valueOf(resultDelete);
                                                 request.setAttribute("resultDelete",del);
		                               }
                                             return mapping.findForward("lstcontacttype");
                        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                                    }
                                }
                                
                            } 
                        //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                          }

	       }

            
         catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        
                     }
         return(mapping.findForward("home"));
}
}


