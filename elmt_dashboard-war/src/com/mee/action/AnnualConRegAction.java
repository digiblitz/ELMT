/*
 * AnnualConRegAction.java
 *
 * Created on September 24, 2006, 2:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mee.action;

import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.Debug;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Shiva Kumar Subbiaha
 */
public class AnnualConRegAction extends Action {
    //  /annualConRegAction.do?method=initAnnaul
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {

            String annProcess = request.getParameter("annProcess");
            if (annProcess.equals("initAnnual")) {
                Debug.print("AnnualConRegAction.initAnnaul() executed.....");
                HLCVaigaiSessionRemote vaiRemote = initializeVaigaiEJB(request);

                Vector reg = vaiRemote.displayActivityTypeRegister();

                Debug.print("DROP DOWN REGISTERATION TYPE :" + reg);
                HLCKaveryMembershipTypeSessionRemote memRemote = initializeKaveryEJB(request);
                Vector memberTypeDetails = memRemote.displayAnnualMemberDetails();
                //ArrayList memberType = getMemberDropDown(memberTypeDetails);
                Debug.print("displayAnnualMemberDetails :" + memberTypeDetails);
                Vector otherAct = vaiRemote.displayActivityTypeActivity();

                request.setAttribute("DisplayRegTypeDetails", reg);
                request.setAttribute("MEMBER_TYPE", memberTypeDetails);
                request.setAttribute("MEETING_DATES", dailyBasis(request));
                request.setAttribute("OTHER_ACTIVITY", otherAct);

                return mapping.findForward("formView");
            }

        } catch (Exception e) {
            Debug.print("Exception is" + e);
        }
        return null;
    }

    private boolean isNotNull(String data) {
        return (data != null && data.trim().length() > 0) ? true : false;
    }

    public static Context getInitialContext()
            throws javax.naming.NamingException {
        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        p.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        p.setProperty("java.naming.provider.url", "");
        return new javax.naming.InitialContext(p);
    }

    private HLCVaigaiSessionRemote initializeVaigaiEJB(HttpServletRequest request) throws Exception {
        MessageResources mr = getResources(request);
        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.icp");

        System.setProperty(namingfactory, contextfactory);
        System.setProperty(urlprovider, lookupip);

        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();

        return remote;
    }

    private HLCKaveryMembershipTypeSessionRemote initializeKaveryEJB(HttpServletRequest request) throws Exception {
        MessageResources mr = getResources(request);
        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.kaveryMembership");

        System.setProperty(namingfactory, contextfactory);
        System.setProperty(urlprovider, lookupip);

        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCKaveryMembershipTypeSessionRemoteHome home = (HLCKaveryMembershipTypeSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCKaveryMembershipTypeSessionRemoteHome.class);
        HLCKaveryMembershipTypeSessionRemote remote = home.create();

        return remote;
    }

    private String getAsString(String[] arr) {
        if (arr == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        int len = (arr.length - 1);
        for (int i = 0; i < len; i++) {
            buffer.append(arr[i] + "#");
        }
        if (arr.length > 0) {
            buffer.append(arr[arr.length - 1]);
        }
        //buffer.append();
        return buffer.toString();
    }

    private ArrayList dailyBasis(HttpServletRequest request) throws Exception {

        Debug.print("dailyBasis executing....");
        String annualMeeName = "Daily Basis";
        Debug.print("annualMeeName: " + annualMeeName);
        HLCVaigaiSessionRemote vaiRemote = initializeVaigaiEJB(request);
        Vector annualMeetings = vaiRemote.dispAnnualMeeForNoOfDays(annualMeeName);
        Debug.print("AnnualMeetings  : " + annualMeetings);
        String[] noOfDays = (String[]) annualMeetings.get(0);

        ArrayList dropDwonLists = new ArrayList();
        // Date will come from DATABASE as 2006-09-23 i.e Year month day
        int dayNos = Integer.parseInt(noOfDays[3]);
        Calendar meetingDate = Calendar.getInstance();
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date myDate = null;
        try {
            myDate = myDateFormat.parse(noOfDays[2]);
        } catch (ParseException e) {
            System.out.println("Invalid Date Parser Exception ");
            e.printStackTrace();
        }
        meetingDate.setTime(myDate);
        Calendar tempDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE,MMM yy");
        Debug.print("Number of Days :" + dayNos);
        for (int i = 0; i < dayNos; i++) {
            tempDate = (Calendar) meetingDate.clone();
            tempDate.add(Calendar.DAY_OF_WEEK, +i);
            String meetingDateValue = (String) formatter.format(tempDate.getTime());
            //dropDwonLists.add(new LabelValueBean(meetingDateValue,meetingDateValue));
            dropDwonLists.add(meetingDateValue);
        }
        return dropDwonLists;
    }
}
