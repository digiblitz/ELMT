/*
 * Program Name     :   BreedAction.java
 * Created Date     :   November 17, 2006, 12:35 PM
 * Author           :   Hari
 * Version          :   1.4
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */
package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hlcmrm.util.HLCBreedVO;
import java.util.Vector;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

public class BreedAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = "ejb/HLCKaveryMrmJNDI";
        try {
            MessageResources mr = getResources(request);
            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();
            Debug.print("ActionMessage.jndiname:" + name);
            Object objref = jndiContext.lookup(name);

            HttpSession session = request.getSession();

            HLCkaveryStatelessRemoteHome home = (HLCkaveryStatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote remote = home.create();
            String breedProcess = request.getParameter("breedProcess");
            String SpeciesProcess = request.getParameter("SpeciesProcess");
            Debug.print("breedProcess is " + breedProcess);
            Debug.print("Inside Servlet");


            //====================================================redirects to list Species Details  ==================================================

            /*
             *
             *      Process flow directed to list  Species information
             */

            if (breedProcess.equals("initBreedView")) {
                String uTypeId = request.getParameter("uTypeId");
                String reDirectVal = request.getParameter("reDirectVal");
                String retSpeciesName = request.getParameter("retSpeciesName");
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("retSpeciesName", retSpeciesName);
                request.setAttribute("uTypeId", uTypeId);
                System.out.println("nnn" + uTypeId);
                System.out.print("enter the initBreedViewcontrol********************");
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 10-Mar-2011
                Vector vDisUserType = remote.displayUserTypeDetails();
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 10-Mar-2011
                System.out.print("After calling displayUserTypeDetails  ");
                int i = 0;
                while (i < vDisUserType.size()) {
                    String[] val = (String[]) vDisUserType.get(i);
                    String ID = val[0];
                    String name1 = val[1];
                    Debug.print("Value [0] is " + ID);
                    Debug.print("Value [1] is " + name1);
                    i++;
                }
                session.setAttribute("displayUserTypeDetails", null);
                session.setAttribute("displayUserTypeDetails", vDisUserType);
                System.out.println("satheesh" + (String) request.getAttribute("uTypeId"));
                return mapping.findForward("frmAddBreedDet");

            }
            //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 10-Mar-2011



            //====================================================redirects to insert Breed Details  ==================================================

            if (breedProcess.equals("insert")) {
                Debug.print("\n Inside Breed Insert...\n");
                /*     HERE GET BREED DESCRIBTION AND BREED ACTIVE STATUS
                 *      HERE GET SPECIES ID AND SET THAT ID
                 *      Process flow directed to list breed information on success
                 *      Process flow directed to add page on failure
                 */
                // String userpe = request.getParameter("breedDesc");
                String breedDesc = request.getParameter("breedDesc");
                String breedStatus = request.getParameter("status");
                String uTypeId = request.getParameter("uType");
                String retSpeciesName = request.getParameter("retSpeciesName");

                String reDirectVal = request.getParameter("reDirectVal");
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retSpeciesName", retSpeciesName);
                request.setAttribute("reDirectVal", reDirectVal);
                boolean bol = remote.createHorseBreed(breedDesc, breedStatus, uTypeId);
                Debug.print("Result for add in Servlet : " + bol);
                if (bol == true) {
                    /*HERE GET SPECIES ID AND SET THAT ID */
                    ArrayList objlist = (ArrayList) remote.getAllHorseBreedDetailsByspecieId(uTypeId);
                    // uTybe ID is Species ID use for select breed List
                    Debug.print("breedDesc" + breedDesc);
                    Debug.print("Result for add in Servlet : " + bol);
                    session.setAttribute("list", null);
                    session.setAttribute("list", objlist);
                    /*HERE GET SPECIES ID AND SET THAT ID */
                    if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                        //return mapping.findForward("frmBreedConf");
                        return mapping.findForward("frmReDirectPage");
                    } else {
                        return mapping.findForward("frmBreedList");
                    }

                } else {
                    request.setAttribute("err", "st");
                    return mapping.findForward("frmAddBreedDet");
                }
            }

            /*      Edit
             *      Process flow directed to Editing breed info based on Breed ID
             *      Process flow directed to list breed information on success
             *      Process flow directed to add page on failure
             */

            if (breedProcess.equals("edit")) {
                Debug.print("\n Inside Breed Editing...\n");
                String breedId = request.getParameter("breedId");
                //String breedName = request.getParameter("breedName");
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 10-Mar-2011
                String reDirectVal = request.getParameter("reDirectVal");
                // String uTypeName = request.getParameter("uTypeName");
                // System.out.println("utybename"+uTypeName);

                String uTypeId = request.getParameter("uTypeId");
                String retSpeciesName = request.getParameter("retSpeciesName");
                HLCBreedVO Vobj = remote.getHorseBreedDetailsById(breedId);
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 10-Mar-2011

                request.setAttribute("Vobj", Vobj);
                request.setAttribute("reDirectVal", reDirectVal);
                // request.setAttribute("uTypeName",uTypeName);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retSpeciesName", retSpeciesName);
                return mapping.findForward("frmEditBreedDet");
            }




            //=============================================Update Breed Details=========================================

            /*  Update
             *
             *      Process flow directed to Update breed info based on Breed ID
             *      On success it redirects to List Breed Information
             *      On failure it redirects to Edit Breed Information
             *
             */
            if (breedProcess.equals("update")) {
                Debug.print("\n Inside Breed Update...\n");
                String breedDesc, breedStatus, breedId;

                breedDesc = request.getParameter("breedDesc");
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
                breedStatus = request.getParameter("breedstatus");
                System.out.println("" + breedStatus);
                breedId = request.getParameter("breedId");
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 10-Mar-2011
                String uTypeId = request.getParameter("uTypeId");
                String retSpeciesName = request.getParameter("retSpeciesName");
                String reDirectVal = request.getParameter("reDirectVal");

                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retSpeciesName", retSpeciesName);
                request.setAttribute("reDirectVal", reDirectVal);
                System.out.println("" + uTypeId);
                Debug.print("breedId is " + breedId);
                boolean bol = remote.editHorseBreed(breedDesc, breedStatus, breedId);
                Debug.print("Update result is" + bol);
                if (bol == true) {
                    ArrayList objlist = (ArrayList) remote.getAllHorseBreedDetailsByspecieId(uTypeId);
                    // uTybe ID is Species ID use for select breed List
                    session.setAttribute("list", null);
                    session.setAttribute("list", objlist);
                    //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 11-Mar-2011
                    // uTybe ID is Species ID
                    // request.setAttribute("uTypeId",uTypeId);
                    if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                        return mapping.findForward("frmReDirectPage");
                    } else {
                        return mapping.findForward("frmBreedList");
                    }
                } else {
                    request.setAttribute("err", "st");
                    HLCBreedVO Vobj = remote.getHorseBreedDetailsById(breedId);
                    request.setAttribute("Vobj", Vobj);
                    //For Debugs Starts
                    request.setAttribute("breedDesc", breedDesc);
                    //For Debugs Ends
                    return mapping.findForward("frmEditBreedDet");
                }
            }

            //====================================================redirects to insert Breed Details  ==================================================
//Starts:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
            if (breedProcess.equals("insertAddspecies")) {
                String reDirectVal = request.getParameter("reDirectVal");
                request.setAttribute("reDirectVal", reDirectVal);
                return mapping.findForward("frmAddSpecies");
            }
            if (breedProcess.equals("insertspecies")) {

                /*     HERE GET BREED DESCRIBTION AND BREED ACTIVE STATUS
                 *      HERE GET SPECIES ID AND SET THAT ID
                 *      Process flow directed to list breed information on success
                 *      Process flow directed to add page on failure
                 */
                // String userpe = request.getParameter("breedDesc");
                String speciesname = request.getParameter("speciesname");
                String speciesStatus = request.getParameter("status");
                String uTypeId = request.getParameter("uTypeId");


                String reDirectVal = request.getParameter("reDirectVal");
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("reDirectVal", reDirectVal);

                boolean bol = remote.createHorseSpecies(speciesname, speciesStatus);
                Debug.print("Result for add in Servlet : " + bol);
                if (bol == true) {
                    /*HERE GET SPECIES ID AND SET THAT ID */
                    //return mapping.findForward("frmBreedConf");

                    if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                        return mapping.findForward("frmReDirectPage");
                    } else {
                        return mapping.findForward("frmMemSpeciesListRe");
                    }

                } else {
                    request.setAttribute("err", "st");
                    return mapping.findForward("frmAddSpecies");
                }
            }



            if (breedProcess.equals("deleteSpecies")) {

                String reDirectVal = request.getParameter("reDirectVal1");
                //  String uTypeId = request.getParameter("uTypeId");
                //String status = request.getParameter("status");
                String chkRoleIdArr[] = request.getParameterValues("chk");
                request.setAttribute("reDirectVal", reDirectVal);
                for (int i = 0; i < chkRoleIdArr.length; i++) {
                    Debug.print("ActionRoleMangement.deletebreed() checked records: " + chkRoleIdArr[i]);
                }

                boolean result = false;

                if (chkRoleIdArr != null) {

                    result = remote.deleteSpecies(chkRoleIdArr);
                    Debug.print("ActionBreeedMangement.deletebreed() result:" + result);
                    if (result == true) {
                        ArrayList objlistSpecies = (ArrayList) remote.getAllHorseSpeciesDetails();
                        // uTybe ID is Species ID use for select breed List
                        session.setAttribute("specieslist", null);
                        session.setAttribute("specieslist", objlistSpecies);
                        Debug.print("ActionRoleMangement.deletebreed() result:" + result);

                        if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectPage");
                        } else {
                            return mapping.findForward("frmMemSpeciesListRe");
                        }
                    } else {
                        //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 11-Mar-2011

                        request.setAttribute("err", "st");
                        return mapping.findForward("frmMemSpeciesList");
                    }
                }
                Debug.print("ActionBreedMangement.deleteRole() sucessfully comes from servlet.");
            }



            if (breedProcess.equals("speciesView")) {
                // uTybe ID is Species ID use for select breed List
                System.out.println("objlistSpecies in view");
                String uTypeId = request.getParameter("uTypeId");
                String reDirectVal = request.getParameter("redirectVal");
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("uTypeId", uTypeId);
                ArrayList objlistSpecies = (ArrayList) remote.getAllHorseSpeciesDetails();
                System.out.println("objlistSpecies in view 1");
                Iterator itr = objlistSpecies.iterator();

                while (itr.hasNext()) {
                    HLCBreedVO donObj = (HLCBreedVO) itr.next();
                    System.out.println("objlistSpecies in view it");
                    String speciesId1 = donObj.getUserTypeId();
                    String SpeciesName = donObj.getUserTybeName();
                    String speciesStatus = donObj.getUserTypeStatus();

                }
                //System.out.println("before objlistSpecies+"+objlistSpecies.size());
                //Start:[BreedMgt] For breed Listing Addition, Editing and Deletion changes dated 10-Mar-2011
                session.setAttribute("specieslist", null);
                session.setAttribute("specieslist", objlistSpecies);
                //  System.out.println("objlistSpecies+"+objlistSpecies.size());
                          /*HERE GET SPECIES ID AND SET THAT ID */

                if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                    return mapping.findForward("frmReDirectPage");
                } else {
                    return mapping.findForward("frmMemSpeciesList");
                }

            }
            if (breedProcess.equals("editSpecies")) {
                Debug.print("\n Inside Breed Editing...\n");
                String speciesId = request.getParameter("speciesId");
                String SpeciesName = request.getParameter("SpeciesName");
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 11-Mar-2011

                String speciesStatus = request.getParameter("speciesStatus");
                String reDirectVal = request.getParameter("reDirectVal");
                HLCBreedVO Vobj = remote.getHorseSpeciesDetailsById(speciesId);
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 11-Mar-2011

                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("Vobj", Vobj);
                request.setAttribute("uTypeName", SpeciesName);
                return mapping.findForward("frmMemEditSpecies");
            }

            if (breedProcess.equals("updateSpecies")) {
                Debug.print("\n Inside Breed Update...\n");


                String speciesId = request.getParameter("speciesId");
                String SpeciesName = request.getParameter("SpeciesName");
                //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 11-Mar-2011

                String speciesStatus = request.getParameter("speciesStatus");

                String reDirectVal = request.getParameter("reDirectVal");
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("uTypeId", speciesId);
                System.out.println("" + speciesId);
                Debug.print("breedId is " + speciesId);
                boolean bol = remote.editHorseSpecies(SpeciesName, speciesStatus, speciesId);
                Debug.print("Update result is" + bol);
                if (bol == true) {
                    ArrayList objlist = (ArrayList) remote.getAllHorseSpeciesDetails();
                    // uTybe ID is Species ID use for select breed List
                    session.setAttribute("specieslist", null);
                    session.setAttribute("specieslist", objlist);
                    //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 11-Mar-2011
                    // uTybe ID is Species ID

                    if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                        return mapping.findForward("frmReDirectPage");
                    } else {
                        return mapping.findForward("frmMemSpeciesListRe");
                    }
                } else if (bol == false) {
                    request.setAttribute("err", "st");
                    HLCBreedVO Vobj = remote.getHorseSpeciesDetailsById(speciesId);
                    request.setAttribute("Vobj", Vobj);
                    //For Debugs Starts
                    request.setAttribute("SpeciesName", SpeciesName);
                    //For Debugs Ends
                    return mapping.findForward("frmMemEditSpecies");
                }
            }


//Ends:[SpeciesMgt] For Species Addition, Editing and Deletion changes dated 10-Mar-2011
            //=============================================Delete Breed details=========================================

            /*        Delete

            //       Process flow directed to Delete breed info based on Breed ID
            //       On success it redirects to List Breed Information
            //       On failure it redirects to Edit Breed Information
             */

            if (breedProcess.equals("delete")) {
                Debug.print("ActionRoleMangement.deletebreed()");
                //Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 12-Mar-2011
                //Start:[BreedMgt] For uTybe for species ID Addition, Editing and Deletion changes for Breed dated 12-Mar-2011
                         /*HERE GET SPECIES ID AND SET THAT ID */
                String uTypeId = request.getParameter("uTypeId");
                String breedId = request.getParameter("breedId");
                //String status = request.getParameter("status");
                String chkRoleIdArr[] = request.getParameterValues("chk");
                String reDirectVal = request.getParameter("reDirectVal");
                String retSpeciesName = request.getParameter("retSpeciesName");
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("retSpeciesName", retSpeciesName);
                for (int i = 0; i < chkRoleIdArr.length; i++) {
                    Debug.print("ActionRoleMangement.deletebreed() checked records: " + chkRoleIdArr[i]);
                }

                boolean result = false;

                if (chkRoleIdArr != null) {

                    result = remote.deleteBreed(chkRoleIdArr);
                    Debug.print("ActionBreeedMangement.deletebreed() result:" + result);
                    if (result == true) {
                        ArrayList objlist = (ArrayList) remote.getAllHorseBreedDetailsByspecieId(uTypeId);
                        // uTybe ID is Species ID use for select breed List
                        session.setAttribute("list", null);
                        session.setAttribute("list", objlist);
                        Debug.print("ActionRoleMangement.deletebreed() result:" + result);
                        /*HERE GET SPECIES ID AND SET THAT ID */

                        if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectPage");
                        } else {
                            return mapping.findForward("frmBreedList");
                        }
                    } else {
                        //Start:[BreedMgt]For Species Id Addition, Editing and Deletion changes for Breed  dated 11-Mar-2011

                        request.setAttribute("err", "st");
                        return mapping.findForward("frmEditBreedDet");
                    }
                }
                Debug.print("ActionBreedMangement.deleteRole() sucessfully comes from servlet.");
            }


            //=============================================List Species details=========================================
                  /*             List Species
            //             Process flow directed to Listing Species info along with UsertybeID
            //             Start:[BreedMgt] For breed Addition, Editing and Deletion changes dated 10-Mar-2011
            //             THIS USED FOR SELECT SPECIES AND SPECIES ID
             */
            if (breedProcess.equals("list")) {
                // Start:[BreedMgt] For Species and  breed List  changes dated 10-Mar-2011
                System.out.print("enter the initBreedViewcontrol********************");
                Vector vDisUserType = remote.displayUserTypeDetails();

                System.out.print("After calling displayUserTypeDetails  ");
                int i = 0;
                while (i < vDisUserType.size()) {
                    String[] val = (String[]) vDisUserType.get(i);
                    String ID = val[0];
                    String name1 = val[1];
                    Debug.print("Value [0] is " + ID);
                    Debug.print("Value [1] is " + name1);
                    i++;
                }
                session.setAttribute("displayUserTypeDetails", null);
                session.setAttribute("displayUserTypeDetails", vDisUserType);
                return mapping.findForward("frmBreedList");

            }

            //=============================================List Breed details=========================================
                 /*       List Breed
            //       Process flow directed to Listing Breed info along with Species Id
             */

            if (breedProcess.equals("BreedView")) {
                // uTybe ID is Species ID use for select breed List
                String uTypeId = request.getParameter("uTypeId");
                String reDirectVal = request.getParameter("reDirectVal");
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("reDirectVal", reDirectVal);
                ArrayList objlist = (ArrayList) remote.getAllHorseBreedDetailsByspecieId(uTypeId);
                Iterator itr = objlist.iterator();

                while (itr.hasNext()) {
                    HLCBreedVO donObj = (HLCBreedVO) itr.next();
                    String breedId = donObj.getBreedId();
                    //String breedCode = donObj.getBreedCode();
                    //Start:[BreedMgt] For breed Listing  Addition, Editing and Deletion changes dated 10-Mar-2011
                    String breedStatus = donObj.getBreedStatus();
                    String breedDesc = donObj.getBreedDesc();

                }
                //Start:[BreedMgt] For breed Listing Addition, Editing and Deletion changes dated 10-Mar-2011
                session.setAttribute("list", objlist);
                /*HERE GET SPECIES ID AND SET THAT ID */

                if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                    return mapping.findForward("frmReDirectPage");
                } else {
                    return mapping.findForward("frmBreedList");
                }
            } //=======================Dhivya Here (Species Breed Management)===============
            else if (breedProcess.equals("speciesBreedsMgt")) {
                String retSpeciesName = (String) request.getAttribute("retSpeciesName");
                String uTypeId = (String) request.getAttribute("uTypeId");
                ArrayList objlist = (ArrayList) remote.getAllHorseBreedDetailsByspecieId(uTypeId);
                ArrayList objlistSpecies = (ArrayList) remote.getAllHorseSpeciesDetails();
                request.setAttribute("specieslist", objlistSpecies);
                request.setAttribute("refcnt", "0");
                request.setAttribute("speciesName", retSpeciesName);
                request.setAttribute("speciesId", uTypeId);
                request.setAttribute("list", objlist);
                return mapping.findForward("frmSpeciesBreedMgt");

            } else if (breedProcess.equals("breedDets")) {

                String speciesId = request.getParameter("speciesId");
                String refcnt = request.getParameter("refcnt");

                Debug.print("BreedAction speciesId:" + speciesId);
                ArrayList objlist = (ArrayList) remote.getAllHorseBreedDetailsByspecieId(speciesId);
                ArrayList objlistSpecies = (ArrayList) remote.getAllHorseSpeciesDetails();
                String speciesName = (String) remote.getSpeciesName(speciesId);

                request.setAttribute("list", objlist);
                request.setAttribute("speciesId", speciesId);
                request.setAttribute("speciesName", speciesName);
                request.setAttribute("specieslist", objlistSpecies);
                request.setAttribute("refcnt", refcnt);
                return mapping.findForward("frmSpeciesBreedMgt");

            }



            //===================Start for Breed Characteristic=======================================
            /* InitList For Breed Characteristic
             * Process flow to InitList For Breed Characteristic
             */
            // Starts:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011
            if (breedProcess.equals("listBreedCharDetail")) {
              

         //========For Delete process==============
      String uTypeId = request.getParameter("uTypeId");
               // String charId = request.getParameter("characId");
     String charId = request.getParameter("charId");


           //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                  String radCharId = request.getParameter("radCharId");
   //======Ends Here==============================================================

                Debug.print("BreedAction.initSelectBreedCharacter()");
                if (uTypeId != null && charId != null) {
                    Vector vDisBreedCharDetail = (Vector) remote.getAllBreedCharacterDetails(uTypeId, charId);
                    request.setAttribute("displayBreedCharDetails", null);
                    request.setAttribute("displayBreedCharDetails", vDisBreedCharDetail);
                }
                Vector vDisUserType = remote.displayUserTypeDetails();
                request.setAttribute("displayUserTypeDetails", null);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                Vector vDisBreedChar = remote.getBreedCharacterCategory(uTypeId);
                request.setAttribute("displayBreedCharacters", null);
                request.setAttribute("displayBreedCharacters", vDisBreedChar);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retCharId", charId);
                 //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
 request.setAttribute("list", objlist);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("radCharId", radCharId);
//=========Ends Here==================================================
                Debug.print("BreedAction.initSelectBreedCharacter() sucessfully comes from servlet.");

                if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                             return mapping.findForward("frmBrdCharList");
                        }
            }
    


 if (breedProcess.equals("listBreedCharDetails")) {
              

         //========For Delete process==============
      String uTypeId = request.getParameter("olduTypeId");
               // String charId = request.getParameter("characId");
     String charId = request.getParameter("oldretCharId");


           //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                  String radCharId = request.getParameter("radCharId");
   //======Ends Here==============================================================

                Debug.print("BreedAction.initSelectBreedCharacter()");
                if (uTypeId != null && charId != null) {
                    Vector vDisBreedCharDetail = (Vector) remote.getAllBreedCharacterDetails(uTypeId, charId);
                    request.setAttribute("displayBreedCharDetails", null);
                    request.setAttribute("displayBreedCharDetails", vDisBreedCharDetail);
                }
                Vector vDisUserType = remote.displayUserTypeDetails();
                request.setAttribute("displayUserTypeDetails", null);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                Vector vDisBreedChar = remote.getBreedCharacterCategory(uTypeId);
                request.setAttribute("displayBreedCharacters", null);
                request.setAttribute("displayBreedCharacters", vDisBreedChar);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retCharId", charId);
                 //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
 request.setAttribute("list", objlist);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("radCharId", radCharId);
//=========Ends Here==================================================
                Debug.print("BreedAction.initSelectBreedCharacter() sucessfully comes from servlet.");

                if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                             return mapping.findForward("frmBrdCharList");
                        }
            }
































            if (breedProcess.equals("initInsert")) {
                String uTypeId = request.getParameter("uTypeId");
                String retCharId = request.getParameter("charId");
             //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                  String radCharId = request.getParameter("radCharId");
//======Ends Here==============================================================
                Vector vDisUserType = remote.displayUserTypeDetails();
                request.setAttribute("displayUserTypeDetails", null);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                Vector vDisBreedChar = remote.getBreedCharacterCategory(uTypeId);
                request.setAttribute("displayBreedCharacters", null);
                request.setAttribute("displayBreedCharacters", vDisBreedChar);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retCharId", retCharId);
                 request.setAttribute("olduTypeId", uTypeId);
                 request.setAttribute("oldretCharId", retCharId);
                   //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
               
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                 request.setAttribute("radCharId", radCharId);

  //======Ends Here==============================================================
                return mapping.findForward("frmAddBrdCharDet");
            }

           if (breedProcess.equals("iniInsert")) {
                String uTypeId = request.getParameter("uTypeId");
                String retCharId = request.getParameter("charId");
				 String olduTypeId = request.getParameter("olduTypeId");
                String oldretCharId = request.getParameter("oldretCharId");
             //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                  String radCharId = request.getParameter("radCharId");
//======Ends Here==============================================================
                Vector vDisUserType = remote.displayUserTypeDetails();
                request.setAttribute("displayUserTypeDetails", null);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                Vector vDisBreedChar = remote.getBreedCharacterCategory(uTypeId);
                request.setAttribute("displayBreedCharacters", null);
                request.setAttribute("displayBreedCharacters", vDisBreedChar);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retCharId", retCharId);

                 request.setAttribute("olduTypeId", olduTypeId);
                 request.setAttribute("oldretCharId", oldretCharId);
                    //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
               
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                 request.setAttribute("radCharId", radCharId);

  //======Ends Here==============================================================
                return mapping.findForward("frmAddBrdCharDet");
            }


            if (breedProcess.equals("insertCharDet")) {
                String charDet = request.getParameter("charDet");
                String status = request.getParameter("status");
                String charId = request.getParameter("charId");
                String uTypeId = request.getParameter("uTypeId");

 //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
                ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                 String radCharId = request.getParameter("radCharId");
                 String retCharacId = request.getParameter("retCharacId");
                  Vector vDisBreedCharDetail = (Vector) remote.getAllBreedCharacterDetails(uTypeId, retCharacId);
   //======Ends Here==============================================================
                
                Vector vDisUserType = remote.displayUserTypeDetails();
                request.setAttribute("displayUserTypeDetails", null);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                Vector vDisBreedChar = remote.getBreedCharacterCategory(uTypeId);
                request.setAttribute("displayBreedCharacters", null);
                request.setAttribute("displayBreedCharacters", vDisBreedChar);
                request.setAttribute("uTypeId", uTypeId);
               

                   //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================

                request.setAttribute("retCharId", retCharacId);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("list", objlist);
                 request.setAttribute("radCharId", radCharId);
                 request.setAttribute("displayBreedCharDetails", vDisBreedCharDetail);
//=========Ends Here==================================================
                boolean bol = remote.createBreedCharDetails(charDet, charId, status);
                Debug.print("Result for add in Servlet : " + bol);
                if (bol == true) {


 if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                            return mapping.findForward("frmBrdCharListRe");
                        }
                } else {
                    request.setAttribute("err", "st");
                      request.setAttribute("olduTypeId", uTypeId);
                     request.setAttribute("oldretCharId", charId);
                  request.setAttribute("retCharId", charId);
                    return mapping.findForward("frmAddBrdCharDet");
                }
            }
            if (breedProcess.equals("editCharDet")) {
                String characterdetId = request.getParameter("characterdetId");
//=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
                 String retCharId = request.getParameter("retCharId");
                String uTypeId = request.getParameter("uTypeId");
                     String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                  String radCharId = request.getParameter("radCharId");

   //=========Ends Here==================================================

                Vector vBrdCharEdit = (Vector) remote.getBreedCharacterEditDetails(characterdetId);
                request.setAttribute("characterdetId", characterdetId);
                request.setAttribute("Editlist", vBrdCharEdit);
                System.out.println("Editlist" + vBrdCharEdit.size());


                   //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
                  request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retCharId", retCharId);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                 request.setAttribute("radCharId", radCharId);
//=========Ends Here==================================================
                return mapping.findForward("frmEditBrdCharDet");
            }
            if (breedProcess.equals("updateBrdCharDet")) {
                String charId = request.getParameter("retCharacId");
                String charDet = request.getParameter("charDet");
                String status = request.getParameter("status");
                String characterdetId = request.getParameter("characterdetId");
                String uTypeId = request.getParameter("uTypeId");
          //=======Dhivya Here(Breed  Characteristics and Details Mgt)============================
                ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);
                String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                 String radCharId = request.getParameter("radCharId");
   Vector vDisBreedCharDetail = (Vector) remote.getAllBreedCharacterDetails(uTypeId, charId);
       //=========Ends Here==================================================
                boolean bol = remote.updateBreedCharDetail(characterdetId, charDet, status, charId);
                Debug.print("Update result is" + bol);

                    //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retCharId", charId);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("list", objlist);
                 request.setAttribute("radCharId", radCharId);
                  request.setAttribute("displayBreedCharDetails", vDisBreedCharDetail);
//=========Ends Here==================================================
                if (bol == true) {

                    if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                             return mapping.findForward("frmBrdCharListRe");
                        }
                } else {
                    request.setAttribute("err", "st");
                    Vector vBrdCharEdit = (Vector) remote.getBreedCharacterEditDetails(characterdetId);
                    request.setAttribute("Editlist", vBrdCharEdit);
                    //For Debugs Starts
                    request.setAttribute("charDet", charDet);
                    //For Debugs Ends
                    return mapping.findForward("frmEditBrdCharDet");
                }
            }
            if (breedProcess.equals("deleteBrdChar")) {
                Debug.print("BreedAction.deleteBrdChar()");

                String uTypeId = request.getParameter("userTypeId");
                String charId = request.getParameter("characId");
             //=======Dhivya Here(Breed  Characteristics and Details Mgt)============================
                
                String radCharId = request.getParameter("radCharacId");
                String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal1");
                  Vector vDisUserType = remote.displayUserTypeDetail();
                    ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);
                    Vector vDisBreedChar = remote.getBreedCharacterCategory(uTypeId);
       //=========Ends Here==================================================

                String characterdetId = request.getParameter("characterdetId");

                String chkCharIdArr[] = request.getParameterValues("chk");

                for (int i = 0; i < chkCharIdArr.length; i++) {
                    Debug.print("BreedAction.deletebreed character() checked records: " + chkCharIdArr[i]);
                }

                boolean result = false;

                if (chkCharIdArr != null) {

                    result = remote.deleteBrdCharDet(chkCharIdArr);
                    Debug.print("BreedAction.deleteBrdChar() result:" + result);
                        //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                request.setAttribute("displayBreedCharacters", vDisBreedChar);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("retCharId", charId);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("list", objlist);
                
                  request.setAttribute("radCharId", radCharId);
                   
                   Vector vDisBreedCharDetail = (Vector) remote.getAllBreedCharacterDetails(uTypeId, charId);
                    
                        request.setAttribute("displayBreedCharDetails", null);
                        request.setAttribute("displayBreedCharDetails", vDisBreedCharDetail);
                       
//=========Ends Here==================================================
                    if (result == true) {
                     
                         if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                            
                            return mapping.findForward("frmBrdCharList");
                        }
                    }

                }
                Debug.print("BreedAction.deleteBrdCharDet() sucessfully comes from servlet.");
            }
            // Ends:[MemMgnt] For Breed Characteristic Detail Management Dated:21-4-2011
//===================End for Breed Characteristic=======================================
            //Satheesh-Start- MMMA_0008:For each species, allow for customer definition of breed characteristic categories
            if (breedProcess.equals("charList")) {
                String uTypeId = request.getParameter("uTypeId");

                //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============

                 String retCharId = request.getParameter("retCharId");
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                  Vector vDisUserType = remote.displayUserTypeDetail();
//======Ends Here==============================================================
                ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);
                Iterator itr = objlist.iterator();
                while (itr.hasNext()) {
                    HLCBreedVO donObj = (HLCBreedVO) itr.next();
                    String charId = donObj.getCharId();
                    String charflag = donObj.getFlag();
                    String charStatus = donObj.getCharStatus();
                    String charDesc = donObj.getCharDesc();

                }
                request.setAttribute("list", objlist);

                request.setAttribute("uTypeId", uTypeId);
                //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
                request.setAttribute("retCharId", retCharId);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
                request.setAttribute("displayUserTypeDetails", vDisUserType);

                if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                         return mapping.findForward("frmSpeciescharList");
                        }
  //======Ends Here==============================================================
            }
            
            if (breedProcess.equals("initCharViewList")) {

                System.out.print("enter the initBreedViewcontrol********************");
                Vector vDisUserType = remote.displayUserTypeDetail();

                int i = 0;
                while (i < vDisUserType.size()) {
                    String[] val = (String[]) vDisUserType.get(i);
                    String ID = val[0];
                    String name1 = val[1];
                    Debug.print("Value [0] is " + ID);
                    Debug.print("Value [1] is " + name1);
                    i++;
                }
                request.setAttribute("displayUserTypeDetails", null);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                return mapping.findForward("frmSpeciescharList");

            }


            if (breedProcess.equals("initCharView")) {
                String uTypeId = request.getParameter("uTypeId");

                //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============

                // String retCharId = request.getParameter("retCharId");
               //  String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
//======Ends Here==============================================================

                Vector vDisUserType = remote.displayUserTypeDetail();

                System.out.print("After calling displayUserTypeDetails  ");
                int i = 0;
                while (i < vDisUserType.size()) {
                    String[] val = (String[]) vDisUserType.get(i);
                    String ID = val[0];
                    String name1 = val[1];
                    Debug.print("Value [0] is " + ID);
                    Debug.print("Value [1] is " + name1);
                    i++;
                }
                request.setAttribute("displayUserTypeDetails", null);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                request.setAttribute("uTypeId", uTypeId);
                System.out.println("satheesh" + (String) request.getAttribute("uTypeId"));
   //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
              //  request.setAttribute("retCharId", retCharId);
              //  request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
//=========Ends Here==================================================
                            return mapping.findForward("frmCharList");
      
            }

            if (breedProcess.equals("speciesCharinsert")) {
                Debug.print("\n Inside Breed Insert...\n");
             //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
                 // String retCharId = request.getParameter("retCharId");
                // String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
   //======Ends Here==============================================================
                String charDesc = request.getParameter("charDesc");
                String charStatus = request.getParameter("status");
                String charSpecieId = request.getParameter("uType");
                System.out.println("utybeID" + charSpecieId);

                boolean bol = remote.createSpeciesChar(charDesc, charStatus, charSpecieId);
                Debug.print("Result for add in Servlet : " + bol);

                  //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
           //     request.setAttribute("retCharId", retCharId);
            //    request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);

                Vector vDisUserType = remote.displayUserTypeDetail();
                 request.setAttribute("displayUserTypeDetails", vDisUserType);
//=========Ends Here==================================================
                 String uTypeId = request.getParameter("uType");
                 request.setAttribute("uTypeId", uTypeId);

                    ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);

                    Debug.print("Result for add in Servlet : " + bol);
                    request.setAttribute("list", null);
                    request.setAttribute("list", objlist);

        
                if (bol == true) {
               
 if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                             return mapping.findForward("frmSpeciescharList");
                        }
                  

                } else {
                    request.setAttribute("err", "st");
                  //  String uTypeId1 = request.getParameter("uType");
                    request.setAttribute("uTypeId", uTypeId);

                    return mapping.findForward("frmCharList");


                }
            }
            if (breedProcess.equals("SpeciesEdit")) {
                Debug.print("\n Inside Breed Editing...\n");
                String charId = request.getParameter("charId");
                String breedName = request.getParameter("breedName");


                String uTypeName = request.getParameter("uTypeName");
                System.out.println("charIdname" + charId);

       //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============

                 String uTypeId = request.getParameter("uTypeId");
               //  String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
//======Ends Here==============================================================
                HLCBreedVO Vobj = remote.getSpeciesCharDetailsById(charId);


                request.setAttribute("Vobj", Vobj);
                request.setAttribute("uTypeName", uTypeName);
                 //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
                request.setAttribute("retCharId", charId);
                request.setAttribute("uTypeId", uTypeId);
               // request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
//=========Ends Here==================================================
                return mapping.findForward("frmCharEdit");
            }

            if (breedProcess.equals("Charupdate")) {
                Debug.print("\n Inside Breed Update...\n");
                String charDesc, charStatus, charId;

                charDesc = request.getParameter("charDesc");

                charStatus = request.getParameter("charstatus");

                charId = request.getParameter("charId");

                String uTypeId = request.getParameter("SpecieId");

             //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
                  String usrTypeId = request.getParameter("uTypeId");
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                   Vector vDisUserType = remote.displayUserTypeDetail();
                 request.setAttribute("displayUserTypeDetails", vDisUserType);
   //======Ends Here==============================================================


                System.out.println("" + uTypeId);
                System.out.println("" + charDesc);
                System.out.println("" + charStatus);
                System.out.println("" + charId);
                boolean bol = remote.editSpeciesChar(charDesc, charStatus, charId, uTypeId);
                Debug.print("Update result is" + bol);
                
                      //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
                  request.setAttribute("uTypeId", usrTypeId);
                request.setAttribute("retCharId", charId);

                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
//=========Ends Here==================================================
                if (bol == true) {
                    ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);

                    request.setAttribute("list", null);
                    request.setAttribute("list", objlist);

 if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                             return mapping.findForward("frmSpeciescharList");
                        }
                } else {
                    request.setAttribute("err", "st");
                    HLCBreedVO Vobj = remote.getSpeciesCharDetailsById(charId);
                    request.setAttribute("Vobj", Vobj);
                    //For Debugs Starts
                    request.setAttribute("charDesc", charDesc);
                    //For Debugs Ends
                    return mapping.findForward("frmCharEdit");
                }
            }

            if (breedProcess.equals("deleteChar")) {
                Debug.print("ActionRoleMangement.deletebreed()");

                String uTypeId = request.getParameter("uType");
                String breedId = request.getParameter("breedId");

      //=======Dhivya Here(Breed  Characteristics and Details Mgt)=============
                  String retCharId = request.getParameter("retCharId");
                 String characteristicName = request.getParameter("characteristicName");
                 String reDirectVal = request.getParameter("reDirectVal");
                   Vector vDisUserType = remote.displayUserTypeDetail();
                 request.setAttribute("displayUserTypeDetails", vDisUserType);
   //======Ends Here==============================================================



                String chkRoleIdArr[] = request.getParameterValues("chk");

                for (int i = 0; i < chkRoleIdArr.length; i++) {
                    Debug.print("ActionRoleMangement.deletebreed() checked records: " + chkRoleIdArr[i]);
                }

                boolean result = false;

                if (chkRoleIdArr != null) {

                    result = remote.deleteChar(chkRoleIdArr);
                    Debug.print("ActionBreeedMangement.deletebreed() result:" + result);
                        //=======Dhivya Here(Breed  Characteristics and Details Mgt)======================================
                  
                request.setAttribute("retCharId", retCharId);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("reDirectVal", reDirectVal);
//=========Ends Here==================================================
                  request.setAttribute("uTypeId", uTypeId);
                        System.out.println("utypeID" + uTypeId);
                    if (result == true) {
                        ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);

                        request.setAttribute("list", null);
                        request.setAttribute("list", objlist);
                        Debug.print("ActionRoleMangement.deletebreed() result:" + result);
                        if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                              return mapping.findForward("frmSpeciescharList");
                        }
                    } else {

                        request.setAttribute("err", "st");
                        if (reDirectVal != null && reDirectVal.equalsIgnoreCase("frmMgt")) {
                            return mapping.findForward("frmReDirectListPage");
                        } else {
                              return mapping.findForward("frmSpeciescharList");
                        }
                        
                    }
                }
                Debug.print("ActionBreedMangement.deleteRole() sucessfully comes from servlet.");
            } //Satheesh-Start- MMMA_0008:For each species, allow for customer definition of breed characteristic categories
            //end:[BreedMgt] For breed Listing Addition, Editing and Deletion changes dated 18-Mar-2011
            //=======================Dhivya Here (Breed Characteristics [Category and Details Management])===============

            else if (breedProcess.equals("initbreedCategoryDets")) {
                 //String retCharId = (String) request.getAttribute("retCharId");
               // String characteristicName = (String) request.getAttribute("characteristicName");
                //String uTypeId = (String) request.getAttribute("uTypeId");
               //ArrayList lst = (ArrayList) request.getAttribute("list");
             //String radCharId = (String) request.getAttribute("radCharId");

                Vector vDisUserType = remote.displayUserTypeDetail();
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                request.setAttribute("refcnt", "0");
                //request.setAttribute("retCharId", retCharId);
                //request.setAttribute("characteristicName", characteristicName);
                //request.setAttribute("uTypeId", uTypeId);
                // request.setAttribute("list", lst);
                // request.setAttribute("radCharId", radCharId);
                return mapping.findForward("frmBreedCategDets");

            } else if (breedProcess.equals("breedCategoryDetsList")) {
                String uTypeId = request.getParameter("uTypeId");
                ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);
                Vector vDisUserType = remote.displayUserTypeDetail();
                 String refcnt = request.getParameter("refcnt");
                   String radCharId = (String) request.getAttribute("radCharId");

                request.setAttribute("refcnt", refcnt);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                request.setAttribute("list", objlist);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("radCharId", radCharId);
                return mapping.findForward("frmBreedCategDets");
            } else if (breedProcess.equals("breedCategoryDets")) {
                String charId = request.getParameter("charId");
                String radCharId = request.getParameter("radCharId");
                String uTypeId = request.getParameter("uTypeId");
                String refcnt = request.getParameter("refcnt");
                

                Vector vDisBreedCharDetail = (Vector) remote.getAllBreedCharacterDetails(uTypeId, charId);
                String characteristics = (String) remote.getCharacteristicName(radCharId);
                Vector vDisUserType = remote.displayUserTypeDetail();
                ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);

                request.setAttribute("list", objlist);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                request.setAttribute("displayBreedCharDetails", vDisBreedCharDetail);
                request.setAttribute("retCharId", charId);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("Characteristics", characteristics);
                request.setAttribute("refcnt", refcnt);
                request.setAttribute("radCharId", radCharId);
                return mapping.findForward("frmBreedCategDets");

            }
            else if (breedProcess.equals("retBreedCategoryDets")) {
                  String retCharId = (String) request.getAttribute("retCharId");
                String characteristicName = (String) request.getAttribute("characteristicName");
                String uTypeId = (String) request.getAttribute("uTypeId");
               ArrayList lst = (ArrayList) request.getAttribute("list");
             String radCharId = (String) request.getAttribute("radCharId");

                Vector vDisUserType = remote.displayUserTypeDetail();
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                request.setAttribute("refcnt", "1");
                request.setAttribute("retCharId", retCharId);
                request.setAttribute("characteristicName", characteristicName);
                request.setAttribute("uTypeId", uTypeId);
                 request.setAttribute("list", lst);
                 request.setAttribute("radCharId", radCharId);

                Vector vDisBreedCharDetail = (Vector) remote.getAllBreedCharacterDetails(uTypeId, retCharId);
                String characteristics = (String) remote.getCharacteristicName(radCharId);
               
                ArrayList objlist = (ArrayList) remote.getAllSpeciesCharDetailsByspecieId(uTypeId);

                request.setAttribute("list", objlist);
                request.setAttribute("displayUserTypeDetails", vDisUserType);
                request.setAttribute("displayBreedCharDetails", vDisBreedCharDetail);
                request.setAttribute("retCharId", retCharId);
                request.setAttribute("uTypeId", uTypeId);
                request.setAttribute("Characteristics", characteristics);
                request.setAttribute("radCharId", radCharId);
                return mapping.findForward("frmBreedCategDets");

            }





        } catch (Exception einsert) {
            System.out.println("Breed Action Error " + einsert.getMessage());
        }
        return null;
    }
}
