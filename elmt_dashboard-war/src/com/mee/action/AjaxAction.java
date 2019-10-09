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
package com.mee.action;

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.Debug;
import com.hlcevent.edu.HLCEducationalSessionRemote;
import com.hlcevent.edu.HLCEducationalSessionRemoteHome;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmro.display.HLCVaigaiStatelessRemote;
import com.hlcmro.display.HLCVaigaiStatelessRemoteHome;
import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import com.util.OptionsBuilder;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.hlchorse.form.display.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author vijitha
 */
public class AjaxAction extends DispatchAction{
    
    /** Creates a new instance of UserDetailsAjaxAction */
    public AjaxAction() {
    }
    public ActionForward eventType(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        Debug.print("Ajax Action executing....");
        Debug.print("eventType Validation executing....");
        String  eventTypeId = request.getParameter("eventTypeId");
        Debug.print("Event Type Id from input: "+eventTypeId);
        
        
        HLCkaverySessionBeanStatlessRemote remote  = initializeKaverySessionBeanStatlessEJB(request);
        
        HLCVaigaiStatelessRemote remote2 = initializeVaigaiStatelessEJB(request);
          Vector allTypesVect = new Vector();
        allTypesVect = remote2.getAllMapTyLe();
        HashMap hm = new HashMap();
        HashMap eventTypeMap = new HashMap();
        ArrayList subLevel = new ArrayList();
        
        if(allTypesVect!=null ){
            Enumeration e = allTypesVect.elements();
            while(e.hasMoreElements()){
                String[] typeDetails = (String[])e.nextElement();
                String mapId = typeDetails[0];
                String typeId = typeDetails[1];
                String levelId = typeDetails[2];
                String typeName = typeDetails[3];
                String levelCode = typeDetails[4];
                String levelName = typeDetails[5];
                if(hm.containsKey(typeId)){
                    String[] subEntry = {mapId,levelId,levelCode,levelName};
                    subLevel.add(subEntry);
                    eventTypeMap.put(typeId,subLevel);
                }else{
                    subLevel = new ArrayList();
                    String[] subEntry = {mapId,levelId,levelCode,levelName};
                    subLevel.add(subEntry);
                }
                hm.put(typeId,typeName);
                
            }
        }
        
        if(eventTypeId!=null && eventTypeId.trim().length()!=0 ) {
            Debug.print("Area and Zip validation in ajax action executing....");
            //ArrayList memberDetails = eduRemote.getMemberContactDetails(memberId);
            ArrayList subLevelDetails = remote.getMappingDetailsForEventTypeAndEventLevels(eventTypeId);
            String encoding = "UTF-8";
            StringBuffer xml = new StringBuffer();
            if(subLevelDetails!=null && subLevelDetails.size()!=0){
                Iterator itr = subLevelDetails.iterator();
                xml.append("<?xml version=\"1.0\"");
                if (encoding != null) {
                    xml.append(" encoding=\"");
                    xml.append(encoding);
                    xml.append("\"");
                }
                xml.append(" ?>\n");
                //xml.append("<size>"+subLevelDetails.size()+"</size>");
                
               /* while(itr.hasNext()){
                    String[] levelStr = (String[])itr.next();
                    String mapTypeId = levelStr[0];
                    String eventTypId = levelStr[1];
                    String eventLevelId = levelStr[2];
                    String eventTypeName = levelStr[3];
                    String eventLevelName = levelStr[4];
                    String areaName = levelStr[1];*/
                    ArrayList levelSub = (ArrayList)eventTypeMap.get(eventTypeId);                   
                    Iterator itr2 = levelSub.iterator();
                    while(itr2.hasNext()){
                        String[] subDetails = (String[])itr2.next();
                        String mapId = subDetails[0];
                        String levelId = subDetails[1];
                        String levelCode = subDetails[2];
                        String levelName = subDetails[3];
                        //xml.append("<SubLevelDetails>"+mapId+"#"+levelId+"#"+levelName+"#"+levelCode+"</SubLevelDetails>");
                        xml.append("<SubLevelDetails>\n <MapId>"+mapId+"</MapId>\n <Levelcode>"+levelCode+"</Levelcode>\n </SubLevelDetails>");
                    }
                            
                //}
            }
            // Set content to xml
            response.setContentType("text/xml; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter pw = response.getWriter();
            Debug.print("XmlContent = "+xml);
            pw.write(xml.toString());
            pw.close();
            return null;
        } else{
            return null;
        }
    }
    public ActionForward areaZipValidation(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        
        Debug.print("Ajax Action executing....");
        Debug.print("areaZipValidation executing....");
        String  stateId = request.getParameter("stateId");
        String zipValue = request.getParameter("zip");
        Debug.print("State Id from input: "+stateId);
        
        
        com.hlcmro.org.HLCVaigaiSessionRemote remote  = initializeVaigaiMROEJB(request);
        if(stateId!=null && zipValue!=null && stateId.trim().length()!=0 && zipValue.trim().length()!=0) {
            Debug.print("Area and Zip validation in ajax action executing....");
            //ArrayList memberDetails = eduRemote.getMemberContactDetails(memberId);
            ArrayList areaDetails = remote.getZipcodeFromToOnStateId(stateId,zipValue);
            String encoding = "UTF-8";
            StringBuffer xml = new StringBuffer();
            if(areaDetails!=null && areaDetails.size()!=0){
                Iterator itr = areaDetails.iterator();
                xml.append("<?xml version=\"1.0\"");
                if (encoding != null) {
                    xml.append(" encoding=\"");
                    xml.append(encoding);
                    xml.append("\"");
                }
                xml.append(" ?>\n");
                
                while(itr.hasNext()){
                    String[] areaStr = (String[])itr.next();
                    String areaId = areaStr[0];
                    String areaName = areaStr[1];
                    xml.append("<AreaDetails>"+areaId+"#"+areaName+"</AreaDetails>");
                }
            }
            // Set content to xml
            response.setContentType("text/xml; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter pw = response.getWriter();
            Debug.print("XmlContent = "+xml);
            pw.write(xml.toString());
            pw.close();
            return null;
        } else{
            return null;
        }
    }
    
    public ActionForward memberDetail(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        
        Debug.print("Ajax Action executing....");
        Debug.print("memberDetail executing....");
        String  memberId = request.getParameter("memberId");
        Debug.print("MemberId from input: "+memberId);
        
        
        HLCEducationalSessionRemote eduRemote  = initializeEducationEJB(request);
        if(memberId!=null) {
            Debug.print("Member Details in ajax action executing....");
            //ArrayList memberDetails = eduRemote.getMemberContactDetails(memberId);
            ArrayList memberDetails = eduRemote.getMemberContactDetailsForAnnualMeeting(memberId);
            String[] memberArr = (String[]) memberDetails.toArray(new String[memberDetails.size()]);
            Debug.print("getMemberContactDetailsForAnnualMeeting" + memberArr.length);
            String result = null;
            for (int i = 0; i < memberArr.length; i++) {
                result += memberArr[i]+",";
            }
            Debug.print("MemberDetails : "+result);
            return getXmlCon(request,response,memberArr);
        } else{
            return null;
        }
    }
    
    public ActionForward memDetails(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        
        Debug.print("Non Member Details executing....");
        String  nonMemberId = request.getParameter("nonmemberId");
        Debug.print("MemberId from input: "+nonMemberId);
        HLCEducationalSessionRemote eduRemote  = initializeEducationEJB(request);
        if(nonMemberId!=null) {
            Debug.print("Mem Details  ajax action executing....");
            //ArrayList memberDetails = eduRemote.getMemberContactDetails(memberId);
            //ArrayList nons = (ArrayList)eduRemote.getAnnualUserContactDetails(nonMemberId);
            //ArrayList nons = (ArrayList)eduRemote.getAnnualUserContactDetailsByLoginName(nonMemberId);
            ArrayList nons = (ArrayList)eduRemote.getAnnualUserContactDetails(nonMemberId);
            Debug.print("getAnnualUserContactDetails" + nons);
            String[] memberArr = (String[]) nons.toArray(new String[nons.size()]);
            String result = null;
            for (int i = 0; i < memberArr.length; i++) {
                result += memberArr[i]+",";
            }
            Debug.print("MemberDetails : "+result);
            return getXmlUserCon(request,response,memberArr);
        } else{
            return null;
        }
    }
    
    public ActionForward calcAmount(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        Debug.print("calAmount executing....");
        String  specificationId = request.getParameter("specId");
        Debug.print("specificationId: "+specificationId);
        String  userTypeId = request.getParameter("userTypeId");
        Debug.print("userTypeId : "+userTypeId);
        Date currentDate = new Date();
        Debug.print("CurrentDate : "+currentDate);
        HLCVaigaiSessionRemote vaiRemote  = initializeVaigaiEJB(request);
        Debug.print("getDueOrAfterDatePriceForAnnual Calling from calcAmount : " + userTypeId);
        String priceDet[] = vaiRemote.getDueOrAfterDatePriceForAnnual(currentDate,specificationId,userTypeId);
        return getAmount(request,response,priceDet);
    }
    
    
    public ActionForward dailyBasis(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        Debug.print("calAmount executing....");
        String  annualMeeName = request.getParameter("annualMeeName");
        Debug.print("annualMeeName: "+annualMeeName);
        HLCVaigaiSessionRemote vaiRemote  = initializeVaigaiEJB(request);
        Vector annualMeetings = vaiRemote.dispAnnualMeeForNoOfDays(annualMeeName);
        Debug.print("AnnualMeetings  : "+annualMeetings);
        return (null==annualMeetings)?null:getNoOfDays(request,response,(String[])annualMeetings.get(0));
    }
    
    
    
    
    public ActionForward getStateZip(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        
        Debug.print("getStateZip executing....");
        
        String  hlcAreaId = request.getParameter("hlcAreaId");
        Debug.print("UseaAreaId from input: "+hlcAreaId);
        HLCMessageSessionRemote msgRemote  = getMessageeEJB(request);
        List statezipDetails  = null;
        if(hlcAreaId!=null) {
            Debug.print("getStateZip Details executing....");
            statezipDetails = msgRemote.getStateByAreaId(hlcAreaId);
            
            Debug.print("ZipDetails : "+statezipDetails.size());
            
            String xml = null;
            try {
                xml = new OptionsBuilder().getStateZipContent(statezipDetails);
                Debug.print("XmlContent = "+xml);
            } catch (Exception ex) {
                // Send back a 500 error code.
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Can not create response");
                return null;
            }
            // Set content to xml
            response.setContentType("text/xml; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter pw = response.getWriter();
            pw.write(xml);
            pw.close();
            
        }
        
        return null;
    }
    
    public ActionForward memberValid(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        
        String  mid = request.getParameter("mid");
        Debug.print("memberId : "+mid);
        
        if(mid!=null) {
            //HLCKaverySessionStatefulRemote kavRemote  = initializeEJB(request);
            boolean isValid =  mid.equals("100000") ? true: false;//kavRemote.getRiderDetails(mid);
            Debug.print("Member Valid : "+isValid);
            return geMemberInfo(request,response,isValid);
        }
        
        return null;
    }
    
    
    
    public final ActionForward getStateZipXmlContent(HttpServletRequest request,HttpServletResponse response,List lists) throws Exception {
        
        return null;
    }
    
    
    private HLCVaigaiStatelessRemote initializeVaigaiStatelessEJB(HttpServletRequest request) throws Exception{
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.vaigaisless");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCVaigaiStatelessRemoteHome home = (HLCVaigaiStatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiStatelessRemoteHome.class);
        HLCVaigaiStatelessRemote remote = home.create();
        
        return remote;
        
    }
    private HLCkaverySessionBeanStatlessRemote initializeKaverySessionBeanStatlessEJB(HttpServletRequest request) throws Exception{
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.kaverysless");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCkaverySessionBeanStatlessRemoteHome home = (HLCkaverySessionBeanStatlessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverySessionBeanStatlessRemoteHome.class);
        HLCkaverySessionBeanStatlessRemote remote = home.create();
        
        return remote;
        
    }
    private HLCVaigaiSessionRemote initializeVaigaiEJB(HttpServletRequest request) throws Exception{
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.icp");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();
        
        return remote;
        
    }
    private com.hlcmro.org.HLCVaigaiSessionRemote initializeVaigaiMROEJB(HttpServletRequest request) throws Exception{
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.vaigaisession");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        com.hlcmro.org.HLCVaigaiSessionRemoteHome home = (com.hlcmro.org.HLCVaigaiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,com.hlcmro.org.HLCVaigaiSessionRemoteHome.class);
        com.hlcmro.org.HLCVaigaiSessionRemote remote = home.create();
        
        return remote;
        
    }
    
    private HLCEducationalSessionRemote initializeEducationEJB(HttpServletRequest request) throws Exception{
        
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.education");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object obj = jndiContext.lookup(jndiname);
        Debug.print("ActionOrganiserRecap obj" );
        
        HLCEducationalSessionRemoteHome eduHome = (HLCEducationalSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(obj,HLCEducationalSessionRemoteHome.class);
        HLCEducationalSessionRemote eduRemote = eduHome.create();
        
        return eduRemote;
        
    }
    
    
    /**
     * Getting the EJB Remote object to access the bean
     */
    
    private HLCKaverySessionStatefulRemote initializeEJB(HttpServletRequest request) throws Exception{
        
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.kaverysful");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCKaverySessionStatefulRemoteHome home = (HLCKaverySessionStatefulRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionStatefulRemoteHome.class);
        HLCKaverySessionStatefulRemote remote = home.create();
        
        return remote;
        
    }
    
    
    /**
     *  return the xml content for the given drop down
     */
    public final ActionForward getXmlUserCon(HttpServletRequest request,HttpServletResponse response,String[] strArr) throws Exception {
        String xml = null;
        try {
            xml = new OptionsBuilder().getUserDetail(strArr);
            Debug.print("XmlContent = "+xml);
        } catch (Exception ex) {
            // Send back a 500 error code.
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Can not create response");
            return null;
        }
        // Set content to xml
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        pw.write(xml);
        pw.close();
        return null;
    }
    
    public final ActionForward getXmlCon(HttpServletRequest request,HttpServletResponse response,String[] strArr) throws Exception {
        String xml = null;
        try {
            xml = new OptionsBuilder().getUserDet(strArr);
            Debug.print("XmlContent = "+xml);
        } catch (Exception ex) {
            // Send back a 500 error code.
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Can not create response");
            return null;
        }
        // Set content to xml
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        pw.write(xml);
        pw.close();
        return null;
    }
    public final ActionForward geMemberInfo(HttpServletRequest request,HttpServletResponse response,boolean valid)
    throws Exception {
        String xml = null;
        try {
            xml = new OptionsBuilder().getMemberInfo(valid);
            Debug.print("XmlContent = "+xml);
        } catch (Exception ex) {
            // Send back a 500 error code.
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Can not create response");
            return null;
        }
        // Set content to xml
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        pw.write(xml);
        pw.close();
        return null;
    }
    
    public final ActionForward getAmount(HttpServletRequest request,HttpServletResponse response,String amount) throws Exception {
        
        String encoding = "UTF-8";
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\"");
        if (encoding != null) {
            xml.append(" encoding=\"");
            xml.append(encoding);
            xml.append("\"");
        }
        xml.append(" ?>\n");
        xml.append("<amount>"+amount+"</amount>");
        
        // Set content to xml
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        Debug.print("XmlContent = "+xml);
        pw.write(xml.toString());
        pw.close();
        return null;
    }
    
    
    public final ActionForward getNoOfDays(HttpServletRequest request,HttpServletResponse response,String[] noOfDays) throws Exception {
        
        String encoding = "UTF-8";
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\"");
        if (encoding != null) {
            xml.append(" encoding=\"");
            xml.append(encoding);
            xml.append("\"");
        }
        xml.append(" ?>\n");
        xml.append("<MeetindDates>\n");
        // Date will come from DATABASE as 2006-09-23 i.e Year month day
        int dayNos = Integer.parseInt(noOfDays[3]);
        Calendar meetingDate =  Calendar.getInstance();
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date myDate = null;
        try {
            myDate = myDateFormat.parse(noOfDays[2]);
        }catch (ParseException e) {
            System.out.println("Invalid Date Parser Exception ");
            e.printStackTrace();
        }
        meetingDate.setTime(myDate);
        Calendar tempDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE,MMM yy");
        for (int i = 0; i < dayNos; i++) {
            tempDate = (Calendar) meetingDate.clone();
            tempDate.add(Calendar.DAY_OF_WEEK, +i);
            xml.append("<meetingDate>"+(String)formatter.format(tempDate.getTime())+"</meetingDate>\n");
        }
        
        xml.append("</MeetindDates>");
        
        // Set content to xml
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = response.getWriter();
        Debug.print("XmlContent = "+xml);
        pw.write(xml.toString());
        pw.close();
        return null;
    }
    
    private ActionForward getAmount(HttpServletRequest request, HttpServletResponse response, String[] priceDetails) throws Exception {
        
        String encoding = "UTF-8";
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\"");
        if (encoding != null) {
            xml.append(" encoding=\"");
            xml.append(encoding);
            xml.append("\"");
        }
        xml.append(" ?>\n");
        xml.append("<priceDetails>\n");
        xml.append(" <priceId>"+priceDetails[0]+"</priceId>\n");
        xml.append(" <amount>"+priceDetails[1]+"</amount>\n");
        xml.append("</priceDetails>");
        // Set content to xml
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter pw=response.getWriter();
        Debug.print("XmlContent = "+xml);
        pw.write(xml.toString());
        pw.close();
        return null;
    }
    
    private HLCMessageSessionRemote getMessageeEJB(HttpServletRequest request) throws Exception{
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.message");
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
        HLCMessageSessionRemote remote = home.create();
        
        return remote;
        
    }
}