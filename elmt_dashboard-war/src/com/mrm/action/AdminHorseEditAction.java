/*
 * Program Name     :   AdminHorseEditAction.java
 * Created Date     :   December 11, 2006, 3:17 PM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.2
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

public class AdminHorseEditAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            MessageResources mr = getResources(request);
            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();

            //For Horse MemberType
            String jndiname1 = mr.getMessage("jndi.kaverysless");
            Object objref1 = jndiContext.lookup(jndiname1);
            HLCkaverySessionBeanStatlessRemoteHome horsehome = (HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote horseremote = horsehome.create();

            //For User Name
            String jname = mr.getMessage("jndi.kavery");
            Object oref = jndiContext.lookup(jname);
            HLCKaverySessionBeanStatfulRemoteHome statefulhome = (HLCKaverySessionBeanStatfulRemoteHome) javax.rmi.PortableRemoteObject.narrow(oref, HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote statefulremote = statefulhome.create();


            //For User Contact and member contact Details
            String jndiname3 = mr.getMessage("jndi.usrreg");
            Object objref3 = jndiContext.lookup(jndiname3);

            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref3, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();

            //For Horse Details           
            String name = "ejb/HLCKaveryMrmJNDI";
            System.out.println("\n after InitialContext Beginning emp Client...\n" + name);
            Object kaveryobj = jndiContext.lookup(name);
            HLCkaveryStatelessRemoteHome kaveryhome = (HLCkaveryStatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(kaveryobj, HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote kaveryremote = kaveryhome.create();
            System.out.println("After Create...");


            Debug.print("Inside Servlet");
            HttpSession session = request.getSession();
            Debug.print("HorseRegDAO Obj Creation");
            ArrayList hrseObj = new ArrayList();
            String process = request.getParameter("process");
            /*
             *     Listing details of the Horse, Rider Id, Additional Rider ID, Previous Rider Id, Owner Information, 
             *     Additional Owner Information, Previous Owner Information
             *
             */
            if (process.equals("desc")) {
                Debug.print("Inside Describing Page");
                String memId = request.getParameter("memid");

                session.setAttribute("memId", memId);
                Debug.print("   MemberId" + memId);
                HLCHorseRegisterationVO HorseDisp = null;
                HLCHorseRegisterationVO OwnerHorseDisp = null;
                if (memId != null && memId.trim().length() != 0) {
                    HorseDisp = (HLCHorseRegisterationVO) statefulremote.getHorseDetailsByHorseMemberId(memId);
                    String ownerId = HorseDisp.getOwnerId();
                    String addownerId = HorseDisp.getAddOwnerId();
                    String prevownerId = HorseDisp.getPrevOwnerId();
                    String riderId = HorseDisp.getRiderMemberId();
                    String prevRiderId = HorseDisp.getPrevRiderMemberId();
                    String addRiderId = HorseDisp.getAddRiderMemberId();
                    String trainerId = HorseDisp.getTrainerId();

                    Debug.print("In Servlet ownerId " + ownerId);
                    Debug.print("In Servlet addownerId " + addownerId);
                    Debug.print("In Servlet prevownerId " + prevownerId);
                    Debug.print("In Servlet riderId " + riderId);
                    Debug.print("In Servlet prevRiderId " + prevRiderId);
                    Debug.print("In Servlet addRiderId " + addRiderId);
                    Debug.print("In Servlet trainerId " + trainerId);
                    // Owner Details Info       
                    Debug.print("User Details owner ID " + ownerId);

                    if (ownerId != null) {
                        Debug.print("Inside If of Owner Id" + ownerId);
                        ArrayList ownerDetails = (ArrayList) remote.getUserContactDetails(ownerId);
                        request.setAttribute("ownerDetails", ownerDetails);
                    } else {
                        Debug.print("Inside Else of Owner Id" + ownerId);
                        request.setAttribute("ownerDetails", null);
                    }
                    // Trainer Details Info       
                    Debug.print("Trainer Details owner ID " + trainerId);

                    if (trainerId != null) {
                        Debug.print("Inside If of Trainer Id" + trainerId);
                        ArrayList ownerDetails = (ArrayList) remote.getUserContactDetails(trainerId);
                        request.setAttribute("TrainerDetails", ownerDetails);
                    } else {
                        Debug.print("Inside Else of Trainer Id" + trainerId);
                        request.setAttribute("TrainerDetails", null);
                    }
                    //Start Setting Info                            
                    //Additional Owner Setting Info                            
                    if (addownerId != null) {
                        Debug.print("Inside If of addownerId " + addownerId);
                        ArrayList addownerDet = (ArrayList) remote.getUserContactDetails(addownerId);
                        request.setAttribute("addownerDet", addownerDet);
                    } else {
                        Debug.print("Inside Else of addownerId" + addownerId);
                        request.setAttribute("addownerDet", null);
                    }
                    //Additional Rider Details
                    if (addownerId != null) {
                        Debug.print("Inside If of addRiderId " + addRiderId);
                        ArrayList addownerDet = (ArrayList) remote.getUserContactDetails(addRiderId);
                        request.setAttribute("addriderDetails", addRiderId);
                    } else {
                        Debug.print("Inside Else of addRiderId" + addRiderId);
                        request.setAttribute("addriderDetails", null);
                    }
                    //Previous Owner Setting Info                            
                    if (prevownerId != null) {
                        Debug.print("Inside If of prevownerId " + prevownerId);
                        ArrayList prevownerDet = (ArrayList) remote.getUserContactDetails(prevownerId);
                        request.setAttribute("prevOwnerDetails", prevownerDet);
                    } else {
                        Debug.print("Inside Else of prevownerId " + prevownerId);
                        request.setAttribute("prevOwnerDetails", null);
                    }
                    //Rider Info Setting
                    if (riderId != null) {
                        Debug.print("Inside If of riderId" + riderId);
                        ArrayList prevriderDet = (ArrayList) remote.getMemberContactDetails(riderId);
                        request.setAttribute("riderInfoDetails", prevriderDet);
                    } else {
                        Debug.print("Inside Else of riderId " + riderId);
                        request.setAttribute("riderInfoDetails", null);
                    }
                    //End Setting Info                            
                    request.setAttribute("ListVO", HorseDisp);
                    Debug.print("User Details owner ID " + ownerId);


                    ArrayList colorDetails = kaveryremote.getAllHorseColorDetails();
                    Debug.print("colorDetails:" + colorDetails);
                    request.setAttribute("DisplayColorDetails", colorDetails);

                    ArrayList breedDetails = kaveryremote.getAllHorseBreedDetails();
                    Debug.print("breedDetails:" + breedDetails);
                    request.setAttribute("DisplayBreedDetails", breedDetails);

                    ArrayList HorseDispDetails = (ArrayList) statefulremote.getHorseRelationshipDetailsDetails(memId);
                    request.setAttribute("ListVODet", HorseDispDetails);
                    return mapping.findForward("adminEdit");
                } else {
                    return mapping.findForward("adminEdit");
                }
            } //Suresh Here
            else if (process.equals("reqHrFrmView")) {
                String memId = request.getParameter("memid");
                String requestId = request.getParameter("requestId");
                Debug.print("   MemberId" + memId);
                HLCHorseRegisterationVO HorseDet = null;
                if (memId != null && memId.trim().length() != 0) {
                    HorseDet = (HLCHorseRegisterationVO) statefulremote.getHorseDetailsByHorseMemberId(memId);
                    String ownerId = HorseDet.getOwnerId();
                    String addownerId = HorseDet.getAddOwnerId();
                    String prevownerId = HorseDet.getPrevOwnerId();
                    String riderId = HorseDet.getRiderMemberId();
                    String prevRiderId = HorseDet.getPrevRiderMemberId();
                    String addRiderId = HorseDet.getAddRiderMemberId();
                    String paymentId = HorseDet.getPaymentId();
                    // Owner Details Info
                    Debug.print("User Details owner ID " + ownerId);
                    if (ownerId != null && ownerId.trim().length() != 0) {
                        ArrayList ownerDetails = (ArrayList) remote.getUserContactDetails(ownerId);
                        request.setAttribute("ownerDetails", ownerDetails);
                    } else {
                        request.setAttribute("ownerDetails", null);
                    }
                    //Rider Info Setting
                    if (riderId != null && riderId.trim().length() != 0) {
                        ArrayList prevownerDet = (ArrayList) remote.getMemberContactDetails(riderId);
                        request.setAttribute("riderInfoDetails", prevownerDet);
                    } else {
                        request.setAttribute("riderInfoDetails", null);
                    }

                    //End Setting Info
                    request.setAttribute("HorseDet", HorseDet);
                    ArrayList HorseDisp = (ArrayList) statefulremote.getHorseRelationshipDetailsDetails(memId);
                    String[] paydet = (String[]) statefulremote.getHorsePaymentDetails(paymentId);
                    request.setAttribute("paydet", paydet);
                    request.setAttribute("ListVO", HorseDisp);
                    request.setAttribute("memId", memId);
                    String reqOwnerId = "";
                    String reqRiderId = "";
                    HLCRequestHorseDetVO objReqDet = null;
                    if (requestId != null) {
                        objReqDet = statefulremote.getHorseRequestFormById(requestId);
                        Debug.print("objReqDet: " + objReqDet);
                        reqOwnerId = objReqDet.getReqOwnerId();
                        reqRiderId = objReqDet.getReqRiderId();
                    }
                    request.setAttribute("objReqDet", objReqDet);



                    Debug.print("User Details owner ID " + reqOwnerId);
                    Debug.print("User Details reqRiderId " + reqRiderId);
                    if (reqOwnerId != null && reqOwnerId.trim().length() != 0) {
                        ArrayList reqOwnerDetails = (ArrayList) remote.getUserContactDetails(reqOwnerId);
                        request.setAttribute("reqOwnerDetails", reqOwnerDetails);
                    } else {
                        request.setAttribute("reqOwnerDetails", null);
                    }
                    //Rider Info Setting
                    Debug.print("reqRiderId value is :" + reqRiderId);
                    if (reqRiderId != null && reqRiderId.trim().length() != 0) {
                        String rid = statefulremote.getMemberIdForRider(reqRiderId);
                        Debug.print("Rider Id:" + rid);
                        ArrayList reqRiderDet = (ArrayList) remote.getMemberContactDetails(rid);
                        Debug.print("reqRiderDet value:" + reqRiderDet);
                        request.setAttribute("rid", rid);
                        request.setAttribute("reqRiderDet", reqRiderDet);
                    } else {
                        request.setAttribute("reqRiderDet", null);
                    }

                    String[] reqPaydet = {};
                    if (objReqDet != null) {
                        String reqPaymentId = objReqDet.getPaymentId();
                        if (reqPaymentId != null && reqPaymentId.trim().length() != 0) {
                            reqPaydet = (String[]) statefulremote.getHorsePaymentDetails(reqPaymentId);
                            ArrayList childPayment = statefulremote.getHorseChildPaymentDetailsByAdmin(reqPaymentId);
                            request.setAttribute("childPayment", childPayment);
                        }
                    }
                    request.setAttribute("reqPaydet", reqPaydet);
                    request.setAttribute("reqPaydet", reqPaydet);

                    return mapping.findForward("frmAdminHorseRequestFrmEdit");
                }
            }

            ////////////
            // Updating Horse Description Details, Horse Member ID, Horse Ba Past Name, Horse Name, Ba Registered Name,
            // Rider ID, Additional Rider ID, Previous Rider ID
            if (process.equals("horseUpdate")) {
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
                String assignedGrade = "";
                String assignedPoints = "";

                String hlcNo = "";
                String horseMemberId = "";
                String competitionName = "";
                String regstrBy = "";
                String breename = "";
                String baPastName = "";
                String riderMemberId = "";
                String prevRiderMemberId = "";
                String addRiderMemberId = "";
                String registeredName = "";
                if (request.getParameter("horseMemberId") != null && request.getParameter("horseMemberId").trim().length() != 0) {
                    horseMemberId = request.getParameter("horseMemberId");
                    Debug.print("In Update horseMemberId " + horseMemberId);
                } else {
                    horseMemberId = null;
                }
                if (request.getParameter("competitionName") != null && request.getParameter("competitionName").trim().length() != 0) {
                    competitionName = request.getParameter("competitionName");
                    Debug.print("In Update competitionName " + competitionName);
                } else {
                    competitionName = null;
                }
                if (request.getParameter("regstrBy") != null && request.getParameter("regstrBy").trim().length() != 0) {
                    regstrBy = request.getParameter("regstrBy");
                    Debug.print("In Update regstrBy " + regstrBy);
                } else {
                    regstrBy = null;
                }
                if (request.getParameter("breename") != null && request.getParameter("breename").trim().length() != 0) {
                    breename = request.getParameter("breename");
                    Debug.print("In Update breename " + breename);
                } else {
                    breename = null;
                }
                if (request.getParameter("baPastName") != null && request.getParameter("baPastName").trim().length() != 0) {
                    baPastName = request.getParameter("baPastName");
                    Debug.print("In Update baPastName " + baPastName);
                } else {
                    baPastName = null;
                }
                if (request.getParameter("hlcNo") != null && request.getParameter("hlcNo").trim().length() != 0) {
                    riderMemberId = request.getParameter("hlcNo");
                    Debug.print("In Update riderMemberId " + riderMemberId);
                } else {
                    riderMemberId = null;
                }
                if (request.getParameter("prhlcNo") != null && request.getParameter("prhlcNo").trim().length() != 0) {
                    prevRiderMemberId = request.getParameter("prhlcNo");
                    Debug.print("In Update prevRiderMemberId " + prevRiderMemberId);
                } else {
                    prevRiderMemberId = null;
                }
                if (request.getParameter("arhlcNo") != null && request.getParameter("arhlcNo").trim().length() != 0) {
                    addRiderMemberId = request.getParameter("arhlcNo");
                    Debug.print("In Update addRiderMemberId " + addRiderMemberId);
                } else {
                    addRiderMemberId = null;
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
                assignedGrade = request.getParameter("assignedGrade");
                assignedPoints = request.getParameter("assignedPoints");
                registeredName = request.getParameter("registeredName");
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
                Debug.print("assignedGrade:" + assignedGrade);
                Debug.print("assignedPoints:" + assignedPoints);
                Debug.print("hlcNo:" + hlcNo);
                Debug.print("horseMemberId:" + horseMemberId);
                Debug.print("regstrBy " + regstrBy);
                Debug.print("breename " + breename);
                Debug.print("baPastName " + baPastName);
                Debug.print("riderMemberId " + riderMemberId);
                Debug.print("prevRiderMemberId " + prevRiderMemberId);
                Debug.print("addRiderMemberId " + addRiderMemberId);
                if (colorselect != null && colorselect.trim().length() != 0) {
                    objHorseDesc.setColor(colorselect);
                } else {
                    objHorseDesc.setColor(null);
                }
                if (genderselect != null && genderselect.trim().length() != 0) {
                    objHorseDesc.setGender(genderselect);
                } else {
                    objHorseDesc.setGender(null);
                }
                if (height != null && height.trim().length() != 0) {
                    objHorseDesc.setHeight(height);
                } else {
                    objHorseDesc.setHeight(null);
                }

                if (foaled != null && foaled.trim().length() != 0) {
                    objHorseDesc.setYearFoaled(foaled);
                } else {
                    objHorseDesc.setYearFoaled(null);
                }
                if (breed != null && breed.trim().length() != 0) {
                    objHorseDesc.setBreed(breed);
                } else {
                    objHorseDesc.setBreed(null);
                }
                if (breedTwo != null && breedTwo.trim().length() != 0) {
                    objHorseDesc.setBreed2(breedTwo);
                } else {
                    objHorseDesc.setBreed2(null);
                }
                if (countryOrigin != null && countryOrigin.trim().length() != 0) {
                    objHorseDesc.setCountry(countryOrigin);
                } else {
                    objHorseDesc.setCountry(null);
                }

                if (sireName != null && sireName.trim().length() != 0) {
                    objHorseDesc.setSire(sireName);
                } else {
                    objHorseDesc.setSire(null);
                }
                if (sireBreed != null && sireBreed.trim().length() != 0) {
                    objHorseDesc.setSireBreed(sireBreed);
                } else {
                    objHorseDesc.setSireBreed(null);
                }
                if (sireBreedTwo != null && sireBreedTwo.trim().length() != 0) {
                    objHorseDesc.setSireBreed2(sireBreedTwo);
                } else {
                    objHorseDesc.setSireBreed2(null);
                }
                if (damName != null && damName.trim().length() != 0) {
                    objHorseDesc.setDam(damName);
                } else {
                    objHorseDesc.setDam(null);
                }
                if (damBreed != null && damBreed.trim().length() != 0) {
                    objHorseDesc.setDamBreed(damBreed);
                } else {
                    objHorseDesc.setDamBreed(null);
                }
                if (damBreedTwo != null && damBreedTwo.trim().length() != 0) {
                    objHorseDesc.setDamBreed2(damBreedTwo);
                } else {
                    objHorseDesc.setDamBreed2(null);
                }
                if (importedFrm != null && importedFrm.trim().length() != 0) {
                    objHorseDesc.setImportedFrom(importedFrm);
                } else {
                    objHorseDesc.setImportedFrom(null);
                }
                if (importedDate != null && importedDate.trim().length() != 0) {
                    objHorseDesc.setImportDate(sdf.parse(importedDate));
                } else {
                    objHorseDesc.setImportDate(null);
                }
                if (foreignGrade != null && foreignGrade.trim().length() != 0) {
                    objHorseDesc.setForeignGrade(foreignGrade);
                } else {
                    objHorseDesc.setForeignGrade(null);
                }
                if (foreignPoints != null && foreignPoints.trim().length() != 0) {
                    objHorseDesc.setForeignPoints(Float.parseFloat(foreignPoints));
                } else {
                    objHorseDesc.setForeignPoints(0);
                }
                if (assignedPoints != null && assignedPoints.trim().length() != 0) {
                    objHorseDesc.setAssignedPoints(Float.parseFloat(assignedPoints));
                } else {
                    objHorseDesc.setAssignedPoints(0);
                }
                if (assignedGrade != null && assignedGrade.trim().length() != 0) {
                    objHorseDesc.setAssignedGrade(assignedGrade);
                } else {
                    objHorseDesc.setAssignedGrade(null);
                }

                boolean updateRes = statefulremote.updateHorseDetailsByAdmin(horseMemberId, competitionName, registeredName, breename, baPastName, riderMemberId, prevRiderMemberId, addRiderMemberId, objHorseDesc);
                Debug.print("updateRes:" + updateRes);
                if (updateRes == true) {
                    request.setAttribute("horseMemberId", horseMemberId);
                    request.setAttribute("horseName", competitionName);
                    request.setAttribute("regstrBy", regstrBy);

                    //request.setAttribute("registrationLevel",membertype);
                    return mapping.findForward("horseUpdateSuccess");
                } else {
                    String memId = (String) session.getAttribute("memId");
                    Debug.print("   MemberId" + memId);
                    HLCHorseRegisterationVO HorseDisp = null;
                    HLCHorseRegisterationVO OwnerHorseDisp = null;
                    if (memId != null && memId.trim().length() != 0) {
                        HorseDisp = (HLCHorseRegisterationVO) statefulremote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDisp.getOwnerId();
                        String addownerId = HorseDisp.getAddOwnerId();
                        String prevownerId = HorseDisp.getPrevOwnerId();
                        String riderId = HorseDisp.getRiderMemberId();
                        String prevRiderId = HorseDisp.getPrevRiderMemberId();
                        String addRiderId = HorseDisp.getAddRiderMemberId();

                        Debug.print("In Servlet ownerId " + ownerId);
                        Debug.print("In Servlet addownerId " + addownerId);
                        Debug.print("In Servlet prevownerId " + prevownerId);
                        Debug.print("In Servlet riderId " + riderId);
                        Debug.print("In Servlet prevRiderId " + prevRiderId);
                        Debug.print("In Servlet addRiderId " + addRiderId);
                        // Owner Details Info       
                        Debug.print("User Details owner ID " + ownerId);

                        if (ownerId != null) {
                            Debug.print("Inside If of Owner Id" + ownerId);
                            ArrayList ownerDetails = (ArrayList) remote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails", ownerDetails);
                        } else {
                            Debug.print("Inside Else of Owner Id" + ownerId);
                            request.setAttribute("ownerDetails", null);
                        }
                        //Start Setting Info                            
                        //Additional Owner Setting Info                            
                        if (addownerId != null) {
                            Debug.print("Inside If of addownerId " + addownerId);
                            ArrayList addownerDet = (ArrayList) remote.getUserContactDetails(addownerId);
                            request.setAttribute("addownerDet", addownerDet);
                        } else {
                            Debug.print("Inside Else of addownerId" + addownerId);
                            request.setAttribute("addownerDet", null);
                        }
                        //Previous Owner Setting Info                            
                        if (prevownerId != null) {
                            Debug.print("Inside If of prevownerId " + prevownerId);
                            ArrayList prevownerDet = (ArrayList) remote.getUserContactDetails(prevownerId);
                            request.setAttribute("prevOwnerDetails", prevownerDet);
                        } else {
                            Debug.print("Inside Else of prevownerId " + prevownerId);
                            request.setAttribute("prevOwnerDetails", null);
                        }
                        //Rider Info Setting
                        if (riderId != null) {
                            Debug.print("Inside If of riderId" + riderId);
                            ArrayList prevriderDet = (ArrayList) remote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails", prevriderDet);
                        } else {
                            Debug.print("Inside Else of riderId " + riderId);
                            request.setAttribute("riderInfoDetails", null);
                        }
                        //End Setting Info                            
                        request.setAttribute("ListVO", HorseDisp);
                        Debug.print("User Details owner ID " + ownerId);

                        ArrayList colorDetails = kaveryremote.getAllHorseColorDetails();
                        Debug.print("colorDetails:" + colorDetails);
                        request.setAttribute("DisplayColorDetails", colorDetails);

                        ArrayList breedDetails = kaveryremote.getAllHorseBreedDetails();
                        Debug.print("breedDetails:" + breedDetails);
                        request.setAttribute("DisplayBreedDetails", breedDetails);
                        return mapping.findForward("adminEdit");
                    }
                }
            }
        } catch (Exception e) {
            Debug.print("General Exception in Edit Horse:" + e);
        }
        return mapping.findForward("adminEdit");

    }
}
