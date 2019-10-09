/*
 * ActionAJAXCompetitionValidation.java
 *
 * Created on April 19, 2007, 4:12 PM
 */

package com.mrm.action;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import java.io.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.hlccommon.util.*;
/*
 *
 * @author Suresh
 * @version
 */

public class ActionAJAXCompetitionValidation extends DispatchAction {
    private String encoding = "UTF-8";
    
    public final ActionForward meeHorseRelVal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Debug.print("meeHorseRelVal method is calling...........");
        HLCVaigaiSessionRemote meeRemote  = initializeEJB(request);
        String hrName = request.getParameter("horseName");
        ArrayList objRelation = (ArrayList)meeRemote.getHorseRelation(hrName);
        return getXmlContent(request,response,objRelation);
    }
    
    public final ActionForward getXmlContent(HttpServletRequest request, HttpServletResponse response, ArrayList arrList) throws Exception {
        String xml = null;
        try {
            xml = getContent(arrList);
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
    
    public String getContent(ArrayList arrList) {
        int count = 0;
        Debug.print("ActionAJAXCompetitionValidation.getContent() Calling");
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\"");
        if (encoding != null) {
            xml.append(" encoding=\"");
            xml.append(encoding);
            xml.append("\"");
        }
        xml.append(" ?>\n");
        xml.append("<options>\n");
        xml.append(getEntry(arrList));
        xml.append("</options>");
        
        return xml.toString();
    }
    
    private String getEntry(ArrayList arrList){
        Debug.print("ActionAJAXCompetitionValidation.getEntry() Calling");
        StringBuffer xml = new StringBuffer();
        for (Iterator iter = arrList.iterator(); iter.hasNext(); ) {
            HLCRelationVO objRelationVO = (HLCRelationVO)iter.next();
            String horseName = objRelationVO.getHorseName();
            String horseMemberId = objRelationVO.getHorseMemberId();
            String firstName =objRelationVO.getFirstName();
            String lastName = objRelationVO.getLastName();
            String memberId = objRelationVO.getMemberId();
            String userId= objRelationVO.getUserId();
            
            xml.append(" <entry> \n");
            xml.append("	<optionHrMemberId>"+horseMemberId+ "</optionHrMemberId>\n");
            xml.append("	<optionHrName>"+horseName+"</optionHrName>\n");
            xml.append("	<optionRFName>"+firstName+"</optionRFName>\n");
            xml.append("	<optionRLName>"+lastName+"</optionRLName>\n");
            xml.append("	<optionRId>"+memberId+"</optionRId>\n");
            xml.append("	<optionUserId>"+userId+"</optionUserId>\n");
            xml.append(" </entry>\n");
        }
        return xml.toString();
    }
    
    private HLCVaigaiSessionRemote initializeEJB(HttpServletRequest request) throws Exception{
        Debug.print("ActionAJAXCompetitionValidation.initializeEJB() Calling");
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
        HLCVaigaiSessionRemote meeRemote = home.create();
        return meeRemote;
    }
}
