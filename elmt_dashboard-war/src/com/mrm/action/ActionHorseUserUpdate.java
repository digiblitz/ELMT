/*
 * ActionHorseUpdate.java
 *
 * Created on February 6, 2007, 7:33 PM
 */
package com.mrm.action;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.*;
import com.hlccommon.util.HLCHorseRegisterationVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.*; 

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

 

public class ActionHorseUserUpdate extends Action {
    
 

    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       
            try  {
                    MessageResources mr=getResources(request);
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
               
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                   
//For Horse MemberType
                    String jndiname1=mr.getMessage("jndi.kaverysless");
                    Object objref1 = jndiContext.lookup(jndiname1);
                    HLCkaverySessionBeanStatlessRemoteHome horsehome =(HLCkaverySessionBeanStatlessRemoteHome) 
                    PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);                    
                   HLCkaverySessionBeanStatlessRemote horseremote = horsehome.create();
                //For User Name
                    String jname=mr.getMessage("jndi.kavery");
                    Object oref = jndiContext.lookup(jname);
                    HLCKaverySessionBeanStatfulRemoteHome statefulhome = (HLCKaverySessionBeanStatfulRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(oref,HLCKaverySessionBeanStatfulRemoteHome.class);
                    HLCKaverySessionBeanStatfulRemote statefulremote = statefulhome.create();
                            
                    String name = "ejb/HLCKaveryMrmJNDI";
                    System.out.println("\n after InitialContext Beginning emp Client...\n" + name);
                    Object kaveryobj = jndiContext.lookup(name);
                    HLCkaveryStatelessRemoteHome kaveryhome = (HLCkaveryStatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(kaveryobj, HLCkaveryStatelessRemoteHome.class);
                    HLCkaveryStatelessRemote kaveryremote = kaveryhome.create();
                    System.out.println("After Create...");
                    
                    String jndiname2=mr.getMessage("jndi.usrreg");
                    Object objref2 = jndiContext.lookup(jndiname2);

                    HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote1 = home1.create(); 
                   
                    Debug.print("Inside Servlet");
                    HttpSession session=request.getSession();
                    Debug.print("HorseRegDAO Obj Creation");
                    ArrayList hrseObj = new ArrayList();
                    String process = request.getParameter("process");
                    if(process.equals("desc")){
                        Debug.print("Inside Describing Page");
                        String memId = request.getParameter("memid");
                        Debug.print("   MemberId" + memId);
                        HLCHorseRegisterationVO HorseDisp = null;
                        HLCHorseRegisterationVO OwnerHorseDisp = null;
                                if(memId!=null && memId.trim().length()!=0){
                                    HorseDisp = (HLCHorseRegisterationVO)statefulremote.getHorseDetailsByHorseMemberId(memId);
                                    String ownerId = HorseDisp.getOwnerId();
                                    String addownerId = HorseDisp.getAddOwnerId();                            
                                    String prevownerId = HorseDisp.getPrevOwnerId();
                                    String riderId = HorseDisp.getRiderMemberId();
                                    String prevRiderId = HorseDisp.getPrevRiderMemberId();
                                    String addRiderId = HorseDisp.getAddRiderMemberId();  

                                    Debug.print("In Servlet ownerId "+ ownerId);
                                    Debug.print("In Servlet addownerId "+ addownerId);
                                    Debug.print("In Servlet prevownerId "+ prevownerId);
                                    Debug.print("In Servlet riderId "+ riderId);                            
                                    Debug.print("In Servlet prevRiderId "+ prevRiderId);
                                    Debug.print("In Servlet addRiderId "+ addRiderId);                                                                
// Owner Details Info       
                                    Debug.print("User Details owner ID "+ownerId);
                           
                            if(ownerId!=null){
                                Debug.print("Inside If of Owner Id"+ownerId);
                                ArrayList ownerDetails = (ArrayList)remote1.getUserContactDetails(ownerId);                                
                                request.setAttribute("ownerDetails",ownerDetails);
                            } 
                            else{
                                Debug.print("Inside Else of Owner Id"+ownerId);
                                request.setAttribute("ownerDetails",null);
                            }
//Start Setting Info                            
//Additional Owner Setting Info                            
                            if(addownerId!=null){
                                Debug.print("Inside If of addownerId "+addownerId);
                                ArrayList addownerDet = (ArrayList)remote1.getUserContactDetails(addownerId);
                                request.setAttribute("addownerDet",addownerDet);
                            } 
                            else{
                                Debug.print("Inside Else of addownerId"+addownerId);                                
                                request.setAttribute("addownerDet",null);
                            }
//Previous Owner Setting Info                            
                            if(prevownerId!=null){
                                Debug.print("Inside If of prevownerId "+prevownerId);
                                ArrayList prevownerDet = (ArrayList)remote1.getUserContactDetails(prevownerId);
                                request.setAttribute("prevOwnerDetails",prevownerDet);
                            } 
                            else{
                                Debug.print("Inside Else of prevownerId "+prevownerId);
                                request.setAttribute("prevOwnerDetails",null);
                            }                            
//Rider Info Setting
                            if(riderId!=null){
                                Debug.print("Inside If of riderId"+riderId);
                                ArrayList prevriderDet = (ArrayList)remote1.getMemberContactDetails(riderId);
                                request.setAttribute("riderInfoDetails",prevriderDet);
                            } 
                            else {
                                Debug.print("Inside Else of riderId "+riderId);
                                request.setAttribute("riderInfoDetails",null);
                            }     
//End Setting Info                            
                            request.setAttribute("ListVO",HorseDisp);
                            Debug.print("User Details owner ID "+ownerId);
                                          
                           
                            ArrayList colorDetails = kaveryremote.getAllHorseColorDetails();
                            Debug.print("colorDetails:" + colorDetails);
                            request.setAttribute("DisplayColorDetails", colorDetails);

                            ArrayList breedDetails = kaveryremote.getAllHorseBreedDetails();
                            Debug.print("breedDetails:" + breedDetails);
                            request.setAttribute("DisplayBreedDetails", breedDetails);
                           
                            ArrayList HorseDispDetails = (ArrayList)statefulremote.getHorseRelationshipDetailsDetails(memId);
                            request.setAttribute("ListVODet",HorseDispDetails);
                            
                            return mapping.findForward("EditHorseRegistration");
                        }
                        else{
                             return mapping.findForward(" ");
                        }
                    }
                    
                    
                   if(process.equals("horseUpdate")){
                      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                      HLCHorseDescriptionVO objHorseDesc = new HLCHorseDescriptionVO();  
                        String colorselect = "";
                        String genderselect = "";
                        String height = "";
                        String foaled = "";
                        String breed = "";
                        String breedTwo = "";
                        String countryOrigin = "";
                        String sireName = "";
                        String sireBreed = "";
                        String sireBreedTwo = "";
                        String damName = "";
                        String damBreed = "";
                        String damBreedTwo = "";
                        String importedFrm = "";
                        String importedDate = "";
                        String foreignGrade = "";
                        String foreignPoints = "";
                        String hlcNo = "";
                        String arhlcNo = "";
                        String prhlcNo = "";
                        String horseMemberId = "";
                         
                        if(request.getParameter("horseMemberId")!=null && request.getParameter("horseMemberId").trim().length()!=0){
                        horseMemberId = request.getParameter("horseMemberId");
                        }
                        else{
                            horseMemberId = null;
                        }
                       
                                colorselect = request.getParameter("colorselect");
                                genderselect = request.getParameter("genderselect");
                                height = request.getParameter("height");
                                foaled = request.getParameter("foaled");
                                breed = request.getParameter("breed");
                                breedTwo = request.getParameter("breedTwo");
                                countryOrigin = request.getParameter("countryOrigin");
                                sireName = request.getParameter("sireName");
                                sireBreed = request.getParameter("sireBreed");
                                sireBreedTwo = request.getParameter("sireBreedTwo");
                                damName = request.getParameter("damName");
                                damBreed = request.getParameter("damBreed");
                                damBreedTwo = request.getParameter("damBreedTwo");
                                importedFrm = request.getParameter("importedFrm");
                                importedDate = request.getParameter("importedDate");
                                foreignGrade = request.getParameter("foreignGrade");
                                foreignPoints = request.getParameter("foreignPoints"); 
                        Debug.print("colorselect:" + colorselect);
                        Debug.print("genderselect:" + genderselect);
                        Debug.print("height:" + height);
                        Debug.print("foaled:" + foaled);
                        Debug.print("breed:" + breed);
                        Debug.print("breedTwo:" + breedTwo);
                        Debug.print("countryOrigin:" + countryOrigin);
                        Debug.print("sireName:" + sireName);
                        Debug.print("sireBreed:" + sireBreed);
                        Debug.print("sireBreedTwo:" + sireBreedTwo);
                        Debug.print("damName:" + damName);
                        Debug.print("damBreed:" + damBreed);
                        Debug.print("damBreedTwo:" + damBreedTwo);
                        Debug.print("importedFrm:" + importedFrm);
                        Debug.print("importedDate:" + importedDate);
                        Debug.print("foreignGrade:" + foreignGrade);
                        Debug.print("foreignPoints:" + foreignPoints);
                        Debug.print("hlcNo:" + hlcNo);
                        Debug.print("arhlcNo:" + arhlcNo);
                        Debug.print("prhlcNo:" + prhlcNo);
                        Debug.print("horseMemberId:" +horseMemberId );
                        
                        if(colorselect!=null && colorselect.trim().length()!=0){
                            objHorseDesc.setColor(colorselect);
                        }
                        else{
                            objHorseDesc.setColor(null);
                        } 
                        if(genderselect!=null && genderselect.trim().length()!=0){
                             objHorseDesc.setGender(genderselect);
                        }
                        else{
                            objHorseDesc.setGender(null);
                        }
                        if(height!=null && height.trim().length()!=0){
                             objHorseDesc.setHeight(height);
                        }
                        else{
                             objHorseDesc.setHeight(null);
                        }
                        
                        if(foaled!=null && foaled.trim().length()!=0){
                             objHorseDesc.setYearFoaled(foaled);
                        }
                        else{
                             objHorseDesc.setYearFoaled(null);
                        }
                         if(breed!=null && breed.trim().length()!=0){
                              objHorseDesc.setBreed(breed);
                        }
                        else{
                              objHorseDesc.setBreed(null);
                        }
                         if(breedTwo!=null && breedTwo.trim().length()!=0){
                             objHorseDesc.setBreed2(breedTwo);
                        }
                        else{
                             objHorseDesc.setBreed2(null);
                        }
                        if(countryOrigin!=null && countryOrigin.trim().length()!=0){
                             objHorseDesc.setCountry(countryOrigin);
                        }
                        else{
                             objHorseDesc.setCountry(null);
                        }
                        
                         if(sireName!=null && sireName.trim().length()!=0){
                              objHorseDesc.setSire(sireName);
                        }
                        else{
                            objHorseDesc.setSire(null);
                        }
                        if(sireBreed!=null && sireBreed.trim().length()!=0){
                             objHorseDesc.setSireBreed(sireBreed);
                        }
                        else{
                              objHorseDesc.setSireBreed(null);
                        }  
                        if(sireBreedTwo!=null && sireBreedTwo.trim().length()!=0){
                            objHorseDesc.setSireBreed2(sireBreedTwo);
                        }
                        else{
                            objHorseDesc.setSireBreed2(null);
                        }  
                        if(damName!=null && damName.trim().length()!=0){
                            objHorseDesc.setDam(damName);
                        }
                        else{
                            objHorseDesc.setDam(null);
                        }  
                        if(damBreed!=null && damBreed.trim().length()!=0){
                            objHorseDesc.setDamBreed(damBreed);
                        }
                        else{
                           objHorseDesc.setDamBreed(null);
                        }  
                        if(damBreedTwo!=null && damBreedTwo.trim().length()!=0){
                            objHorseDesc.setDamBreed2(damBreedTwo);
                        }
                        else{
                            objHorseDesc.setDamBreed2(null);
                        }  
                        if(importedFrm!=null && importedFrm.trim().length()!=0){
                            objHorseDesc.setImportedFrom(importedFrm);
                        }
                        else{
                            objHorseDesc.setImportedFrom(null);
                        }  
                        if(importedDate!=null && importedDate.trim().length()!=0){
                             objHorseDesc.setImportDate(sdf.parse(importedDate));
                        }
                        else{
                            objHorseDesc.setImportDate(null);
                        }  
                        
                        if(foreignGrade!=null && foreignGrade.trim().length()!=0){
                             objHorseDesc.setForeignGrade(foreignGrade);
                        }
                        else{
                             objHorseDesc.setForeignGrade(null);
                        }  
                        if(foreignPoints!=null && foreignPoints.trim().length()!=0){
                             objHorseDesc.setForeignPoints(Float.parseFloat(foreignPoints));
                        }
                        else{
                           objHorseDesc.setForeignPoints(0);
                        }
                        
                        Debug.print("   Rider Id:" + hlcNo);
                        Debug.print("   Privious Rider" + prhlcNo);
                        Debug.print("   Additional OwnerId:" + arhlcNo);
                        String competitionName = request.getParameter("competitionName");
                        String membertype = request.getParameter("membertype");
                       Debug.print("membertype:" + membertype);
                        Debug.print("competitionName:" + competitionName);
                        boolean updateRes =statefulremote.updateHorseDescriptionByOwnerOrRider(horseMemberId,objHorseDesc); 
                        Debug.print("updateRes:" + updateRes);
                                if(updateRes==true){
                                        request.setAttribute("horseMemberId",horseMemberId);
                                        request.setAttribute("horseName",competitionName);
                                        request.setAttribute("registrationLevel",membertype);
                                        return mapping.findForward("updateSuccess");
                                }
                                else{

                                }
                     } 
        
    }
    catch(Exception e){
        Debug.print("General Exception in Edit Horse:" + e);
    }
        return null;
    }
}

