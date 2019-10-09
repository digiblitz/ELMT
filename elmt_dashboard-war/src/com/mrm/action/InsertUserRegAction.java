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
package com.mrm.action;
/*
 * InsertUserRegAction.java
 *
 * Created on August 26, 2006, 12:37 AM
 */

import com.mrm.actionform.InsertUserRegActionForm;
import com.hlcrole.management.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.mysql.dao.MySQLDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import com.hlcform.stateless.*;
import com.hlcform.util.*;
import org.apache.struts.util.MessageResources;
import org.jasypt.util.text.BasicTextEncryptor;


/**
 *
 * @author karthikeyan
 * @version
 */
public class InsertUserRegAction extends Action {

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            /*
             * Service Locator for ejb/MemberRegistrationJNDI JNDI
             */
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword("sa");

            MessageResources mr = getResources(request);

            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");
            String jndiname = mr.getMessage("jndi.usrreg");

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);

            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();

            String source = request.getParameter("source");
            String jndiname2 = mr.getMessage("jndi.rolemanagement");
            String defrole = mr.getMessage("default.role");
            Debug.print("defrole :" + defrole);

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);

            Object objref2 = jndiContext.lookup(jndiname2);
            Debug.print("ActionRoleMangement.jndiname:" + jndiname2);

            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome) PortableRemoteObject.narrow(objref2, HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote roleRemote = roleHome.create();


            HLCUserMaster objUserMaster = new HLCUserMaster();
            HLCContactDetails objContact = new HLCContactDetails();
            HLCMemberDetails objMember = new HLCMemberDetails();
            HLCPaymentDetails objPayment = new HLCPaymentDetails();

            InsertUserRegActionForm usrbean = (InsertUserRegActionForm) form;

            /*
             * Inserting User Details by ejb/MemberRegistrationJNDI
             */
            System.out.println("inside user registration ..........");

            System.out.println(usrbean.getUSelect());
            System.out.println(usrbean.getFname());
            System.out.println(usrbean.getMname());
            System.out.println(usrbean.getLname());
            System.out.println(usrbean.getSname());
            System.out.println(usrbean.getBirthmonth());
            System.out.println(usrbean.getBirthday());
            System.out.println(usrbean.getBirthyear());
            System.out.println(usrbean.getGender());
            System.out.println(usrbean.getPhone());
            System.out.println(usrbean.getMobile());
            System.out.println(usrbean.getFax());
            System.out.println(request.getParameter("email"));
            System.out.println(request.getParameter("cpwd"));
            System.out.println(request.getParameter("QSelect"));
            System.out.println(usrbean.getAns());
            System.out.println(request.getParameter("priAdd_cbx"));
            System.out.println(request.getParameter("secAdd"));
            System.out.println(usrbean.getPadd_txt());
            System.out.println(usrbean.getPadd_txt2());
            System.out.println(usrbean.getPcountry_sel());
            System.out.println(usrbean.getPstate_sel());
            System.out.println(usrbean.getPcity_txt());
            System.out.println(usrbean.getPzip_txt());
            System.out.println(request.getParameter("sadd_txt"));
            System.out.println(request.getParameter("sadd_txt1"));
            System.out.println(request.getParameter("scountry_txt"));
            System.out.println(request.getParameter("sstate_txt"));
            System.out.println(request.getParameter("scity_txt"));
            System.out.println(request.getParameter("szip_txt"));
            System.out.println(request.getParameter("myselect"));
            System.out.println(request.getParameter("usrname"));

            String bday = usrbean.getBirthyear() + "-" + usrbean.getBirthmonth() + "-" + usrbean.getBirthday();
            System.out.println(bday);

            objUserMaster.setUserTypeName("");
            objUserMaster.setPrefix(usrbean.getUSelect());
            objUserMaster.setFirstName(usrbean.getFname());
            objUserMaster.setMiddleName(usrbean.getMname());
            objUserMaster.setLastName(usrbean.getLname());
            objUserMaster.setSufix(usrbean.getSname());
            objUserMaster.setDob(bday);
            objUserMaster.setGender(usrbean.getGender());
            objUserMaster.setEmailId(request.getParameter("email"));
            //objUserMaster.setPassword(request.getParameter("cpwd"));
            String str=request.getParameter("cpwd");
            String encryptedPWD = textEncryptor.encrypt(str);
             objUserMaster.setPassword(encryptedPWD);
             objUserMaster.setSecretQuestion(request.getParameter("QSelect"));
            objUserMaster.setSecretAnswer(usrbean.getAns());
            objUserMaster.setLoginName(request.getParameter("usrname"));
            if (request.getParameter("nonUseaEmail") != null) {
                objUserMaster.setNonUseaEmailStatus(true);
                System.out.println("commit nonUseaEMail status--true");
            } else {
                objUserMaster.setNonUseaEmailStatus(false);
                System.out.println("commit nonUseaEMail status--false");
            }
            if (request.getParameter("nonUseaMail") != null) {
                objUserMaster.setNonUseaMailingStatus(true);
                System.out.println("commit nonUseaMail status--true");
            } else {
                objUserMaster.setNonUseaMailingStatus(false);
                System.out.println("commit nonUseaMail status--false");
            }
            if (request.getParameter("addr").equalsIgnoreCase("Secondary")) {
                objUserMaster.setCommunicationAddress("Primary");
            }

            if (request.getParameter("addr").equalsIgnoreCase("Primary")) {
                objUserMaster.setCommunicationAddress(request.getParameter("myselect"));
            }
            String usrCrit=request.getParameter("usrCriteria");
            // remote.editUserDetails(objUserMaster);

            String usrid = remote.addUserRegistration(objUserMaster);

            Debug.print("remote.addUserRegistration(objUserMaster) usrid :" + usrid);
            
            if(usrid!=null){
            	boolean result = remote.addUserCriteria(usrid, usrCrit);
            }

            Vector uid = new Vector();

            //uid=(Vector)remote.getUserIdByEmailId(request.getParameter("email"));
            uid = (Vector) remote.getUserIdByLoginName(request.getParameter("usrname"));
            String usrId = (String) uid.elementAt(0);
            Debug.print("usrId after reg :" + usrId);
            //Comment the below lines for not a add assign any role eg- User while registering
            //boolean rolestat = roleRemote.createDefaultRole(usrId, defrole);
            //Debug.print("rolestatus :" + rolestat);


            String finalPrimaryPh = "";

            if (request.getParameter("pphone_txt") != null) {
                Debug.print("request.getParameter(pphone_txt) value:" + request.getParameter("pphone_txt"));

                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pphone_txt"), "[](),.-{}");

                while (strTkns.hasMoreTokens()) {
                    try {
                        String phNo = (String) strTkns.nextToken();

                        if (phNo != null && phNo.trim().length() != 0) {
                            Debug.print("ph no Added from Stokenizer:" + phNo);
                            finalPrimaryPh = finalPrimaryPh + phNo;
                        }
                    } catch (Exception e) {
                        Debug.print("ph no tokanizing exception:" + e);
                    }
                }

                Debug.print("finally appended primary phNo :" + finalPrimaryPh);
            }

            String finalPrimaryMob = "";

            if (request.getParameter("pmob_txt") != null) {
                Debug.print("request.getParameter(pmob_txt) value:" + request.getParameter("pmob_txt"));

                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pmob_txt"), "[](),.-{}");

                while (strTkns.hasMoreTokens()) {
                    try {
                        String phNo = (String) strTkns.nextToken();

                        if (phNo != null && phNo.trim().length() != 0) {
                            Debug.print("ph no Added from Stokenizer:" + phNo);
                            finalPrimaryMob = finalPrimaryMob + phNo;
                        }
                    } catch (Exception e) {
                        Debug.print("ph no tokanizing exception:" + e);
                    }
                }

                Debug.print("finally appended primary finalPrimaryMob :" + finalPrimaryMob);
            }

            String finalPrimaryFax = "";

            if (request.getParameter("pfax_txt") != null) {
                Debug.print("request.getParameter(pfax_txt) value:" + request.getParameter("pfax_txt"));

                StringTokenizer strTkns = new StringTokenizer(request.getParameter("pfax_txt"), "[](),.-{}");

                while (strTkns.hasMoreTokens()) {
                    try {
                        String phNo = (String) strTkns.nextToken();

                        if (phNo != null && phNo.trim().length() != 0) {
                            Debug.print("ph no Added from Stokenizer:" + phNo);
                            finalPrimaryFax = finalPrimaryFax + phNo;
                        }
                    } catch (Exception e) {
                        Debug.print("ph no tokanizing exception:" + e);
                    }
                }

                Debug.print("finally appended primary finalPrimaryFax :" + finalPrimaryFax);
            }

            objContact.setContactType("Primary");
            objContact.setAddress1(usrbean.getPadd_txt());
            objContact.setAddress2(usrbean.getPadd_txt2());
            objContact.setCity(usrbean.getPcity_txt());
            objContact.setState(usrbean.getPstate_sel());
            objContact.setCountry(usrbean.getPcountry_sel());
            objContact.setZip(usrbean.getPzip_txt());
            objContact.setPhoneNo(finalPrimaryPh);
            objContact.setMobileNo(finalPrimaryMob);
            objContact.setFaxNo(finalPrimaryFax);
            remote.addContactDetails(objContact);


            boolean result = new MySQLDAO().insertUserDetailToMqSQL(objUserMaster, objContact);
            Debug.print("                MySql Result :" + result);


            if (request.getParameter("addr").equalsIgnoreCase("Primary")) {

                String finalSecPh = "";
                if (request.getParameter("sphone_txt") != null) {

                    StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sphone_txt"), "[](),.-{}");


                    while (strTkns1.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns1.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalSecPh = finalSecPh + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("Secondary ph no tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended Secondary phNo :" + finalSecPh);
                }

                String finalSecMob = "";
                if (request.getParameter("smob_txt") != null) {

                    StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("smob_txt"), "[](),.-{}");


                    while (strTkns1.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns1.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalSecMob = finalSecMob + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("Secondary ph no tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended Secondary finalSecMob :" + finalSecMob);
                }

                String finalSecFax = "";
                if (request.getParameter("sfax_txt") != null) {

                    StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sfax_txt"), "[](),.-{}");


                    while (strTkns1.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns1.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalSecFax = finalSecFax + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("Secondary ph no tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended Secondary finalSecFax :" + finalSecFax);
                }

                objContact.setContactType("Secondary");
                objContact.setAddress1(request.getParameter("sadd_txt"));
                objContact.setAddress2(request.getParameter("sadd_txt1"));
                objContact.setCity(request.getParameter("scity_txt"));
                objContact.setState(request.getParameter("sstate_txt"));
                objContact.setCountry(request.getParameter("scountry_txt"));
                objContact.setZip(request.getParameter("szip_txt"));
                objContact.setPhoneNo(finalSecPh);
                objContact.setMobileNo(finalSecMob);
                objContact.setFaxNo(finalSecFax);
                remote.addContactDetails(objContact);

            }

            request.setAttribute("pwd", request.getParameter("cpwd"));
            request.setAttribute("loginName", request.getParameter("usrname"));


            /* =====================================
             *
             * Sending confirmation E-mail
             *
             * ====================================*/
            String emailid = request.getParameter("email");
            String toMailIds[] = {emailid};
            EmailContent email = new EmailContent();
            email.setTo(toMailIds);
            email.setFrom("anandv@digiblitz.com");
            email.setSubject("Your Account Info !");

            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                    " <tr>" +
                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                    "<tr>" +
                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                    " </tr>" +
                    "  <tr>" +
                    "<td valign=\"top\">" +
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                    "<tr>" +
                    "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                    "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                    "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                    "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                    "<span class=\"boldTxt\">Dear " + usrbean.getFname() + "</span>,<br /><br />" +
                    "<p>Please save this email for your records. Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p>UserName :" + request.getParameter("usrname") + "<p> password: " + request.getParameter("cpwd") + "<p> ----------------------------<p>" +
                    /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                    "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
                    "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>" +
                    "Thank You <br />" +
                    "------------------ <br />" +
                    "<span class=\"boldRedTxt\">HLC Team</span></td>" +
                    "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                    "</tr>" +
                    "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                    "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                    "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                    "</tr>" +
                    " </table>" +
                    "</td></tr>" +
                    "+<tr>" +
                    "<td valign=\"top\" style=\"padding:10px;\">" +
                    "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />" +
                    "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want." +
                    "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:" +
                    "<ul>" +
                    "<li>Unlimited shopping online.</li>" +
                    "<li>Place advertisements online and/or on-site.</li>" +
                    "<li>Sponsor competitions held by HLC.</li>" +
                    "</ul>" +
                    "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as " +
                    "per your �Role� assigned:" +
                    "<ul>" +
                    "<li>Compete in Equestrian Events held by HLC.</li>" +
                    "<li>Take part in other events like; Annual Meetings, Educational events," +
                    "Activity Meetings held by HLC etc.</li>" +
                    "<li>Send Messages to other members.</li>" +
                    "<li>Create your own Distribution Lists.</li>" +
                    "<li>Create/Join a group and share your thoughts and common ideas.</li>" +
                    " <li>Unlimited Shopping online.</li>" +
                    " <li>Place advertisements online and/or on-site.</li>" +
                    " <li>Sponsor competitions held by HLC.</li>" +
                    "</ul>" +
                    "and much more..." +
                    "So go ahead and <a href=http://dashboard.useventing.com/>LOGIN NOW!</a></td>" +
                    "</tr>" +
                    " <tr>" +
                    "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                    "</tr>" +
                    "</table>";


            email.setBody(content);
            //email.setAttachments();

            EmailEngine emailEngine = new EmailEngine();
            boolean emailFlag = emailEngine.sendMimeEmail(email);
            Debug.print("Email sent sucessfully :" + emailFlag);
            request.setAttribute("source", source);

        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        /*
         * Redirected to Confirmation page
         */
        return mapping.findForward("Confirmation");
    }
}

