/*
 * Program Name     :   HorseColorAction.java
 * Created Date     :   November 18, 2006, 6:13 PM
 *
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.3
 *
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
import com.hlcmrm.util.HLCHorseColorVO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

public class HorseColorAction extends Action {

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
            String horseprocess = (String) request.getParameter("horseprocess");
            Debug.print("Process is " + horseprocess);
            Debug.print("Inside Servlet");
            /*  Add
             *
             *      Process flow directed to add horse color information
             */
            /* <!--Start:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/

            if (horseprocess.equals("add")) {
                Debug.print("\n Inside Horse Adding...\n");
                ArrayList DemoType = null;
                DemoType = remote.getAllDemographicType();
                if (DemoType != null) {
                    System.out.println("**********contType is " + DemoType.size());
                }
                request.setAttribute("DemoType", DemoType);
                try {
                    request.setAttribute("err", "");
                    return mapping.findForward("frmAddDemo");
                } catch (Exception e) {
                    Debug.print(" Error : " + e.getMessage());
                }
            }

            /*  Insert
             *
             *      Process flow directed to list horse color information on success
             *      Process flow directed to add page on failure
             */

            if (horseprocess.equals("insert")) {
                Debug.print("\n Inside Horse Insert...\n");

                String Demographic = request.getParameter("Demo");
                String Description = request.getParameter("Desc");
                String Type = request.getParameter("DemogType");
                //boolean  Status = request.getParameter("status");
                String status = request.getParameter("status");
                System.out.println("status in string " + status);
                boolean bolStatus = Boolean.parseBoolean(status);
                System.out.println("status in boolean " + bolStatus);
                boolean bol1 = remote.createDemographic(Demographic, Description, Type, bolStatus);
                Debug.print("Result for add in Servlet : " + bol1);
                if (bol1 == true) {
                    Debug.print("Result for add in Servlet : " + bol1);
                    request.setAttribute("err", "");
                    return mapping.findForward("frmListDemograph");
                } else {
                    request.setAttribute("err", "st");
                    ArrayList DemoType = null;
                    DemoType = remote.getAllDemographicType();
                    if (DemoType != null) {
                        System.out.println("**********contType is " + DemoType.size());
                    }
                    request.setAttribute("DemoType", DemoType);
                    return mapping.findForward("frmAddDemo");
                }
            }

            /*  Edit
             *
             *      Process flow directed to Editing horse color info based on horse color ID
             */
            if (horseprocess.equals("edit")) {
                Debug.print("\n Inside Horse Editing...\n");
                ArrayList DemoType = null;
                String DemoId = request.getParameter("demoid");
                DemoType = remote.getAllDemographicType();
                if (DemoType != null) {
                    System.out.println("**********contType is " + DemoType.size());
                }
                request.setAttribute("DemoType", DemoType);
                Debug.print("\n Inside Demo Editing...\n" + DemoId);
                HLCHorseColorVO Vobj = remote.getDemoById(DemoId);
                Debug.print("\n after HLCHorseColorVO...\n");
                request.setAttribute("Vobj", Vobj);
                request.setAttribute("err", "");
                return mapping.findForward("frmEditDemo");
            }
            /*  Update
             *
             *      Process flow directed to Update horse color info based on horse color ID
             *      On success it redirects to List horse color Information
             *      On failure it redirects to Edit horse color Information
             */
            if (horseprocess.equals("update")) {
                Debug.print("\n Inside Horse Update...\n");
                String demoId, demographic, demoDesc, demoType;
                String status;
                demoId = request.getParameter("demoId");
                System.out.println("demoId in string " + demoId);
                demographic = request.getParameter("demographic");
                System.out.println("demographic " + demographic);
                demoDesc = request.getParameter("demoDesc");
                System.out.println("demoDesc " + demoDesc);
                demoType = request.getParameter("DemogType");
                System.out.println("demoType " + demoType);
                status = request.getParameter("status");
                System.out.println("status in string " + status);
                boolean bolStatus = Boolean.parseBoolean(status);
                System.out.println("status in boolean " + bolStatus);
                boolean bol = remote.editDemo(demoId, demographic, demoDesc, demoType, bolStatus);
                if (bol == true) {
                    ArrayList objlist = (ArrayList) remote.getAllDemographicType();
                    Iterator itr = objlist.iterator();
                    request.setAttribute("list", objlist);
                    request.setAttribute("err", "");
                    return mapping.findForward("frmListDemograph");
                } else {

                    ArrayList DemoType = null;
                    DemoType = remote.getAllDemographicType();
                    if (DemoType != null) {
                        System.out.println("**********contType is " + DemoType.size());
                    }

                    request.setAttribute("DemoType", DemoType);
                    HLCHorseColorVO Vobj = remote.getDemoById(demoId);
                    request.setAttribute("Vobj", Vobj);
                    //For Debugs Starts
                    request.setAttribute("demographic", demographic);
                    Debug.print("demographic" +demographic);
                    //For Debugs Ends
                    request.setAttribute("err", "st");
                    return mapping.findForward("frmEditDemo");
                }
            }
            /*  List
             *
             *      Process flow directed to Listing horse color info along with horse color ID
             */
            if (horseprocess.equals("list")) {
                Debug.print("\n Inside Horse Listing...\n");
                ArrayList objlist = (ArrayList) remote.getAllDemoDetails();
                Debug.print("\n after Horse Listing...objlist\n" + objlist.size());
                Debug.print("\n after Horse Listing...\n");
                //Iterator itr = objlist.iterator();
                Debug.print("\n before request setatt...\n");
                request.setAttribute("list", null);
                request.setAttribute("list", objlist);
                Debug.print("\n after request setatt...\n");
                return mapping.findForward("frmListDemo");
            }

            if (horseprocess.equalsIgnoreCase("delete")) {

                String chkConIdArr[] = request.getParameterValues("chk");

                for (int i = 0; i < chkConIdArr.length; i++) {
                    Debug.print("Horse Delete() checked records: " + chkConIdArr[i]);
                }

                boolean resultDelete = false;

                if (chkConIdArr != null) {
                    resultDelete = remote.deletedemo(chkConIdArr);
                    Debug.print("Horse Delete() result:" + resultDelete);

                    return mapping.findForward("frmListDemograph");

                } else {
                    System.out.println("delete unsuccessfull");

                }

                Debug.print("ContactTypeAction conformDelete() sucessfully comes from servlet.");
            }
            //===================================end======================================================================


            /*<!--End:[DemographicDetails] For Demographic Type Addition, Editing and Deletion changes dated 11-Apr-2011 by Deepa-->*/


            //========================Dhivya Here=================================
            /*
             * Adding Demographic Categories: Non Human
             */

            if (horseprocess.equals("initAddNonHum")) {
                Debug.print("HorseColorAction.initAddNonHum() sucessfully comes from servlet.");
                String speciesId = request.getParameter("speciesId");
                ArrayList typeList = remote.getAllTypeDetails();
                ArrayList speciesList = remote.getAllSpeciesDetails();

                request.setAttribute("typeList", typeList);
                request.setAttribute("speciesList", speciesList);
                request.setAttribute("speciesId", speciesId);
                return mapping.findForward("frmAddNonHuman");
            } else if (horseprocess.equals("addNonHDemo")) {

                String speciesId = request.getParameter("selSpecies");
                String demo = request.getParameter("txtDemo");
                String demoDesc = request.getParameter("txtDemoDesc");
                String TypId = request.getParameter("selTyp");
                String demoStatus = request.getParameter("demoStat");

                boolean bol = remote.createDemographicCategoryForNonHuman(speciesId, demo, demoDesc, TypId, demoStatus);

                if (bol == true) {
                    ArrayList speciesList = remote.getAllSpeciesDetails();
                    request.setAttribute("speciesList", speciesList);
                    request.setAttribute("speciesId", speciesId);
                    return mapping.findForward("frmNewListNonHumanDemo");

                } else {
                    ArrayList typeList = remote.getAllTypeDetails();
                    ArrayList speciesList = remote.getAllSpeciesDetails();
                    request.setAttribute("err", "st");
                    request.setAttribute("typeList", typeList);
                    request.setAttribute("speciesList", speciesList);
                     request.setAttribute("speciesId", speciesId);
                    return mapping.findForward("frmAddNonHuman");

                }
            /*
             * Listing up of Demographic Categories: Non Human
             */

            } else if (horseprocess.equals("initNonHumDemoList")) {

                ArrayList speciesList = remote.getAllSpeciesDetails();
                request.setAttribute("speciesList", speciesList);

                return mapping.findForward("frmListNonHumanDemo");
            } else if (horseprocess.equals("reqList")) {
                
            String speciesId ="";
             Debug.print("speciesId"+speciesId);
            if(request.getParameter("selSpecies")!=null && request.getParameter("selSpecies").length()!=0){
             speciesId=request.getParameter("selSpecies");
              Debug.print("speciesId in servlet request"+speciesId);
            }else{
              speciesId =(String)request.getAttribute("speciesId");
               Debug.print("speciesId in servlet getAttribute"+speciesId);
            }
          Debug.print("speciesId in servlet"+speciesId);
            ArrayList speciesList = remote.getAllSpeciesDetails();
            ArrayList demoList = remote.getDemographicListForNonHuman(speciesId);
            ArrayList typeList = remote.getAllTypeDetails();

            request.setAttribute("demoList", demoList);
            request.setAttribute("speciesId", speciesId);
            request.setAttribute("speciesList", speciesList);
            request.setAttribute("typeList", typeList);
            return mapping.findForward("frmListNonHumanDemo");
            }

            /*else if (horseprocess.equalsIgnoreCase("demoListForPagin")) {
                Debug.print("demoListForPagin process Called");
                String speciesId = request.getParameter("selSpecies");
                ArrayList speciesList = remote.getAllSpeciesDetails();
                ArrayList typeList = remote.getAllTypeDetails();
                if (speciesId.length() != 0 && speciesId != null) {
                    try {
                        int rCnt = 0;
                        int pNo = 1;
                        String pageNo = request.getParameter("pn");
                        //Debug.print("pageNo:"+pageNo);
                        if (pageNo != null) {
                            pNo = Integer.parseInt(pageNo);

                        }
                        Debug.print("Page NO :" + pNo);

                        rCnt = remote.demographicCategoryRowCount(speciesId);
                        ArrayList objList = (ArrayList) remote.getDemographicCategoryForPagination(speciesId, pNo);
                        request.setAttribute("demoList", objList);
                        request.setAttribute("speciesId", speciesId);
                        request.setAttribute("rCnt", String.valueOf(rCnt));
                        request.setAttribute("pNo", String.valueOf(pNo));
                        request.setAttribute("typeList", typeList);
                        request.setAttribute("speciesList", speciesList);
                        return mapping.findForward("frmListNonHumanDemo");
                    } catch (Exception emember) {
                        Debug.print("while Loading User Type ID page" + emember);

                    }
                }
            }*/
            
            /*
             * Edit and update Demographic Categories: Non Human
             */

             else if (horseprocess.equals("initEditNonHuman")) {

                ArrayList speciesList = remote.getAllSpeciesDetails();
                ArrayList typeList = remote.getAllTypeDetails();
                String nonHumanDemoId = request.getParameter("chk");
                String speciesId = request.getParameter("speciesId");
                Debug.print("nonHumanDemoId in servlet" + nonHumanDemoId);
                ArrayList demoDets = remote.getDemographicDetailsForNonHuman(nonHumanDemoId);

                request.setAttribute("speciesId", speciesId);
                request.setAttribute("demoDets", demoDets);
                request.setAttribute("speciesList", speciesList);
                request.setAttribute("typeList", typeList);
                request.setAttribute("nonHumanDemoId", nonHumanDemoId);
                return mapping.findForward("frmEditNonHumanDemo");
            } else if (horseprocess.equals("updateNonHDemo")) {

                String speciesId = request.getParameter("speciesId");
                String nonHumanDemoId = request.getParameter("nonHumanDemoId");
                //Debugs Starts by Lakshmi
                String demo = request.getParameter("txtDemo");
                //Debugs Ends by Lakshmi
                String demoDesc = request.getParameter("txtDemoDesc");
                String TypId = request.getParameter("selTyp");
                String demoStatus = request.getParameter("demoStat");
                //For Debugs Starts
                Debug.print("speciesId" +speciesId);
                Debug.print("nonHumanDemoId" +nonHumanDemoId);
                Debug.print("Demo" +demo);
                Debug.print("demoDesc" +demoDesc);
                Debug.print("TypId" +TypId);
                //For Debugs Ends

                //Debugs Starts by Lakshmi
              //  boolean bol = remote.updateDemographicCategoryForNonHuman(speciesId, nonHumanDemoId,demoDesc, TypId, demoStatus);
                boolean bol = remote.updateDemographicCategoryForNonHuman(speciesId, nonHumanDemoId, demo, demoDesc, TypId, demoStatus);
                Debug.print("result" +bol);
                //Debugs Ends by Lakshmi
                ArrayList speciesList = remote.getAllSpeciesDetails();
               if(bol==true){
                   Debug.print("if statement");
                      ArrayList objlist = (ArrayList) remote.getDemographicListForNonHuman(speciesId);
                      request.setAttribute("demoList", objlist);
                    request.setAttribute("speciesId", speciesId);
                  request.setAttribute("speciesList", speciesList);
                  return mapping.findForward("frmListNonHumanDemo");
                  //Debugs Starts by Lakshmi
                }
          else{
                Debug.print("else statement");
                ArrayList typeList = remote.getAllTypeDetails();
                Debug.print("nonHumanDemoId in servlet" + nonHumanDemoId);
                ArrayList demoDets = remote.getDemographicDetailsForNonHuman(nonHumanDemoId);

                request.setAttribute("speciesId", speciesId);
                request.setAttribute("demoDets", demoDets);
                request.setAttribute("speciesList", speciesList);
                request.setAttribute("typeList", typeList);
                request.setAttribute("nonHumanDemoId", nonHumanDemoId);
                //For Debug Starts
                 request.setAttribute("demo",demo);
                 Debug.print("demo" +demo);
                 //For Debugs Ends
                 request.setAttribute("err", "st");
                 return mapping.findForward("frmEditNonHumanDemo");
          }
                //Debugs Ends by Lakshmi
       }
             /*
             * Delete Demographic Categories: Non Human
             */

            else if (horseprocess.equals("deleteDemoDets")) {

                String selSpecies = request.getParameter("selSpecies1");
                String chkNonHumanDemoIdArr[] = request.getParameterValues("chk");

                for (int i = 0; i < chkNonHumanDemoIdArr.length; i++) {
                    Debug.print("HorseColorAction.deleteDemoDets() checked records: " + chkNonHumanDemoIdArr[i]);
                }

                boolean result = false;

                if (chkNonHumanDemoIdArr != null) {
                    ArrayList speciesList = remote.getAllSpeciesDetails();
                   
                    result = remote.deleteDemographicCategoryForNonHuman(chkNonHumanDemoIdArr);
                    Debug.print("HorseColorAction.deleteDemoDets() result:" + result);
                     
                    if (result == true) {
                        ArrayList objlist = (ArrayList) remote.getDemographicListForNonHuman(selSpecies);
                      
                        request.setAttribute("demoList", objlist);
                        Debug.print("HorseColorAction.deleteDemoDets() result:" + result);

                        request.setAttribute("speciesId", selSpecies);

                        request.setAttribute("speciesList", speciesList);
                        return mapping.findForward("frmListNonHumanDemo");
                    } else {
                        request.setAttribute("speciesList", speciesList);
                        request.setAttribute("err", "st");
                        return mapping.findForward("frmListNonHumanDemo");
                    }
                }
                
            }



        } catch (Exception einsert) {
            System.out.println("HorseColorAction Error " + einsert.toString());
        }
        return null;
    }
}
