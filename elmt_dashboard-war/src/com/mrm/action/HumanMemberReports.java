/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.action;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmember.type.util.Debug;
import java.text.SimpleDateFormat;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import com.util.BIRTGraphsConfig;
import com.birt.graphs.BarGraph;
import com.birt.graphs.BarMarkerRangeGraph;
import com.birt.graphs.ConeGraph;
import com.birt.graphs.LineGraph;
import com.birt.graphs.PieGraph;
import com.birt.graphs.PlotFormat;
import com.birt.graphs.PyramidGraph;
import com.birt.graphs.TubeGraph;
import com.oee.action.EventsYearWise;
import com.oee.action.HumanMembersEventsParticipated;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.eclipse.birt.chart.model.Chart;
/**
 *
 * @author parteek
 */
public class HumanMemberReports extends org.apache.struts.action.Action{
    
    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
           MessageResources mr=getResources(request);
           String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            String jndiname=mr.getMessage("jndi.usrreg");
            Object objref = jndiContext.lookup(jndiname);
            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();
            HttpSession session=request.getSession();
             SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
             ServletContext theApplicationsServletContext = getServlet().getServletContext();
        String realPath = theApplicationsServletContext.getRealPath("/") + "images/";
            String process=request.getParameter("process");
            String findForward=null;
            if(process!=null) {
                if(process.equalsIgnoreCase("humanMembersStatusWise")){
                      
                       String fromDate = request.getParameter("fromDate");
                        Date startDate=null;
                        String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");

                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        
                        HashMap humanMembersCountStatusWise=remote.getHumanMembersStatusWise(startDate, endDate);
                        /*Set human=humanMembersCountStatusWise.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String status_name=(String)it.next();
                                      String total= (String)humanMembersCountStatusWise.get(status_name);
                                      Debug.print("Status-Name "+status_name);
                                      Debug.print("Total  For"+" "+total);
                            }*/
                        if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                //PlotFormat Graph
                        if(graph[i].equalsIgnoreCase("pie")) 
                        {
                         // Pie Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPie =PieGraph.createStatusWiseHumanMembersNumberPie(humanMembersCountStatusWise);
                         PieGraph.generatePieGraphImage(chartPie, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("chart")){
                        
                         //Bar Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartBar=BarGraph.createStatusWiseHumanMembersNumberBar(humanMembersCountStatusWise);
                         BarGraph.generateBarGraphImage(chartBar, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("Linechart")) {

                          // Line Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                       Chart chartLine = LineGraph.createStatusWiseHumanMembersNumberLine(humanMembersCountStatusWise);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("PyramidChart")) {
                        // Pyramid Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartPyramid = PyramidGraph.createStatusWiseHumanMembersNumberPyramid(humanMembersCountStatusWise);
                        PyramidGraph.generatePyramidGraphImage(chartPyramid, realPath);
                        }
                    }
                    request.setAttribute("graphsCount",graphNew);  
                    request.setAttribute("StartDate",fromDate);
                    request.setAttribute("EndDate",toDate);
                    return mapping.findForward("humanMemberNumberStatusWise");
                        }
                        else
                        {
                            List list = new ArrayList();
                            Set human=humanMembersCountStatusWise.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String status_name=(String)it.next();
                                      String total= (String)humanMembersCountStatusWise.get(status_name);
                                      EventsYearWise year=new EventsYearWise();
                                      year.setYear(status_name);
                                      year.setCount(Integer.parseInt(total));
                                      list.add(year);
                                      Debug.print("Status-Name "+status_name);
                                      Debug.print("Total  For"+" "+total);
                            }
                            session.setAttribute("StartDate",fromDate);
                            
                            session.setAttribute("EndDate",toDate);
                            session.setAttribute("demo", list);
                    return mapping.findForward("humanMemberNumberStatusWiseTabular");
                        }
                                                          
                }
                if(process.equalsIgnoreCase("humanMembersMembershipAndStatusWise"))
                {
                     String fromDate = request.getParameter("fromDate");
                        Date startDate=null;
                        String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        
                        String status=request.getParameter("status");
                        String status_Name=null;
                        if(status.equalsIgnoreCase("44615b7f-2911-4bfc-85ef-02dea8305963"))
                                   status_Name="Duplicate";
                        else if(status.equalsIgnoreCase("572456ea-04b0-4a4c-a169-35ec0ccce826"))
                                   status_Name="Merged";
                        else if(status.equalsIgnoreCase("cf7e60e9-3a9a-4645-819f-369cbc7b851a"))
                                   status_Name="Expired";
                        else if(status.equalsIgnoreCase("118756d9-fb79-4304-8647-36f0d7579b45"))
                                   status_Name="Active";
                        else if(status.equalsIgnoreCase("b9f3f76a-1aa7-4e19-b457-43104413828e"))
                                   status_Name="Pending";
                         else if(status.equalsIgnoreCase("59a33c45-bacf-4b3a-ae42-62282d61029c"))
                                   status_Name="Inactive";
                        else if(status.equalsIgnoreCase("bef5db6b-2b64-4382-a91e-666ab253db2e"))
                                   status_Name="Suspended";
                        else if(status.equalsIgnoreCase("60b2e6f6-bc60-4827-b0d6-69957ebfd911"))
                                   status_Name="Deceased";
                        else if(status.equalsIgnoreCase("b7f9ed03-2aca-455f-a17d-883c35ac52b8"))
                                   status_Name="TBD";
                        else if(status.equalsIgnoreCase("a368676e-26e6-4c81-9582-adbd97a991f7"))
                                   status_Name="Retired";
                        
                        HashMap HumanMembersMemberShips=remote.getHumanMembersMemberShipsByStatus(startDate,endDate,status);
                           if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                //PlotFormat Graph
                        if(graph[i].equalsIgnoreCase("pie")) 
                        {
                        // Pie Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPie =PieGraph.createMembershipWiseHumanNumberByStatusPie(HumanMembersMemberShips, status_Name);
                         PieGraph.generatePieGraphImage(chartPie, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("chart")) 
                        {
                          //Bar Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartBar=BarGraph.createMembershipWiseHumanNumberByStatusBar(HumanMembersMemberShips, status_Name);
                         BarGraph.generateBarGraphImage(chartBar, realPath);
                        }
                             else if(graph[i].equalsIgnoreCase("Linechart")){
                           // Line Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartLine = LineGraph.createMembershipWiseHumanNumberByStatusLine(HumanMembersMemberShips, status_Name);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                             }
                        else if(graph[i].equalsIgnoreCase("PyramidChart")){
                        // Pyramid Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartPyramid = PyramidGraph.createMembershipWiseHumanNumberByStatusPyramid(HumanMembersMemberShips, status_Name);
                        PyramidGraph.generatePyramidGraphImage(chartPyramid, realPath);
                        }
                    }
                        request.setAttribute("status",status_Name);
                        request.setAttribute("graphsCount",graphNew); 
                         request.setAttribute("StartDate",fromDate);
                    request.setAttribute("EndDate",toDate);
                        return mapping.findForward("humanMemberNumberMemberShipWise");
                           }
                           else
                           {
                            List list=new ArrayList(); 
                            Set human=HumanMembersMemberShips.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String membership_name=(String)it.next();
                                      String total= (String)HumanMembersMemberShips.get(membership_name);
                                      EventsYearWise memb=new EventsYearWise();
                                      memb.setYear(membership_name);
                                      memb.setCount(Integer.parseInt(total));
                                      list.add(memb);
                                      Debug.print("Membership-Name "+membership_name);
                                      Debug.print("Total  For"+" "+total);
                            }
                                session.setAttribute("demo", list);
                                session.setAttribute("StartDate",fromDate);
                                 session.setAttribute("EndDate",toDate);
                                session.setAttribute("status",status_Name);
                    return mapping.findForward("humanMemberNumberMemberShipWiseTabular");
                           }
                }
                if(process.equalsIgnoreCase("humanSpecificMembershipStatusWise"))
                {
                    String fromDate = request.getParameter("fromDate");
                        Date startDate=null;
                        String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        
                        String status=request.getParameter("status");
                        String status_Name=null;
                        if(status.equalsIgnoreCase("44615b7f-2911-4bfc-85ef-02dea8305963"))
                                   status_Name="Duplicate";
                        else if(status.equalsIgnoreCase("572456ea-04b0-4a4c-a169-35ec0ccce826"))
                                   status_Name="Merged";
                        else if(status.equalsIgnoreCase("cf7e60e9-3a9a-4645-819f-369cbc7b851a"))
                                   status_Name="Expired";
                        else if(status.equalsIgnoreCase("118756d9-fb79-4304-8647-36f0d7579b45"))
                                   status_Name="Active";
                        else if(status.equalsIgnoreCase("b9f3f76a-1aa7-4e19-b457-43104413828e"))
                                   status_Name="Pending";
                         else if(status.equalsIgnoreCase("59a33c45-bacf-4b3a-ae42-62282d61029c"))
                                   status_Name="Inactive";
                        else if(status.equalsIgnoreCase("bef5db6b-2b64-4382-a91e-666ab253db2e"))
                                   status_Name="Suspended";
                        else if(status.equalsIgnoreCase("60b2e6f6-bc60-4827-b0d6-69957ebfd911"))
                                   status_Name="Deceased";
                        else if(status.equalsIgnoreCase("b7f9ed03-2aca-455f-a17d-883c35ac52b8"))
                                   status_Name="TBD";
                        else if(status.equalsIgnoreCase("a368676e-26e6-4c81-9582-adbd97a991f7"))
                                   status_Name="Retired";
                        String membership=request.getParameter("membership");
                        String membership_Name=null;
                             if(membership.equalsIgnoreCase("60BC8025-B043-4771-AD1E-09C9BC160AEE"))
                                  membership_Name="Junior Member";
                        else if(membership.equalsIgnoreCase("A95FAB3A-4C2F-47F5-9161-0AE519290131"))
                                  membership_Name="Life Member";
                        else if(membership.equalsIgnoreCase("E750ED08-5C4B-4FCA-BFD7-52EBB22BDB2B"))
                                  membership_Name="Full Member";
                        else if(membership.equalsIgnoreCase("74F758E9-6E03-40DB-A402-9F6B4BD36606"))
                                  membership_Name="Subscribing Member";
                        else if(membership.equalsIgnoreCase("C57DD232-C425-417F-BC27-CE2C9B7EBC83"))
                                  membership_Name="Family Member";
                        else if(membership.equalsIgnoreCase("19EB90A6-5B73-46BD-9CD5-DFFB6DC96E12"))
                                  membership_Name="Non-Competing Member";
                          String total=remote.getHumanMembersSpecificMembershipByStatus(startDate, endDate, status, membership);
                                     if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                //PlotFormat Graph
                        if(graph[i].equalsIgnoreCase("plotformat")) 
                        {
                          // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.createHumanMembersSpecificMembershipByStatusPlotFormatGraph(total, membership_Name,status_Name);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("chart")){
                         //Bar Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartBar=BarGraph.createHumanMembersSpecificMembershipByStatusBar(total, membership_Name, status_Name);
                          BarGraph.generateBarGraphImage(chartBar, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("tube")) {
                        // Tube Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartTube = TubeGraph.createHumanMembersSpecificMembershipByStatusTube(total, membership_Name, status_Name);
                         TubeGraph.generateTubeGraphImage(chartTube, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("cone")) {
                         // Cone Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartCone = ConeGraph.createHumanMembersSpecificMembershipByStatusCone(total, membership_Name, status_Name);
                         ConeGraph.generateConeGraphImage(chartCone, realPath); 
                        }
                    }
                          request.setAttribute("graphsCount",graphNew); 
                         request.setAttribute("membership_type_name",membership_Name);
                         request.setAttribute("status_name",status_Name);
                         request.setAttribute("StartDate",fromDate);
                                 request.setAttribute("EndDate",toDate);
                         return mapping.findForward("humanSpecificMembershipStatusWise"); 
                                     }
                                     else
                                     {
                                        List list=new ArrayList(); 
                                        HumanMembersEventsParticipated part=new HumanMembersEventsParticipated();
                                        part.setEventsConducted(Integer.parseInt(total));
                                        list.add(part);
                                        session.setAttribute("demo",list);
                                        session.setAttribute("membership_type_name",membership_Name);
                                         session.setAttribute("status_name",status_Name);
                                         session.setAttribute("StartDate",fromDate);
                                        session.setAttribute("EndDate",toDate);
                                         return mapping.findForward("humanSpecificMembershipStatusWiseTabular"); 
                                        
                                     }
                }
                
                if(process.equalsIgnoreCase("humanMembersAreaWise"))
                {
                    String fromDate = request.getParameter("fromDate");
                        Date startDate=null;
                        String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        ArrayList totalHumanAreaWise=remote.getHumanMembersAreaWise(startDate,endDate);
                        Debug.print("Debug Inside Action Oh Human members Reports "+totalHumanAreaWise.size());
                               
                        if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                
                        if(graph[i].equalsIgnoreCase("chart")) 
                        {
                         //Bar Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartBar= BarGraph.createTotalHumanMembersAreaWiseBar(totalHumanAreaWise);
                         BarGraph.generateBarGraphImage(chartBar, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("pie")) {
                        // Pie Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPie =PieGraph.createTotalHumanMembersAreaWisePie(totalHumanAreaWise);
                         PieGraph.generatePieGraphImage(chartPie, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("cone")) {
                           // Cone Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartCone = ConeGraph.createTotalHumanMembersAreaWiseCone(totalHumanAreaWise);
                         ConeGraph.generateConeGraphImage(chartCone, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("plotformat")) {
                          // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.createTotalHumanMembersAreaWisePlotFormatGraph(totalHumanAreaWise);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                        }
                    }
                    request.setAttribute("StartDate",fromDate);
                                        request.setAttribute("EndDate",toDate);
                    request.setAttribute("graphsCount",graphNew);
                      return mapping.findForward("humanMembersAreaWise");
                        }
                        /*for(int i=0;i<totalHumanAreaWise.size();i++)
                        {
                              HashMap humanMembersCountStatusWise=(HashMap)totalHumanAreaWise.get(i);
                              Set human=humanMembersCountStatusWise.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String area_name=(String)it.next();
                                      Integer total= (Integer)humanMembersCountStatusWise.get(area_name);
                                      Debug.print("Area-Name "+area_name);
                                      Debug.print("Total  For"+" "+total.intValue());
                            }
                        }*/
                        else
                        {
                            List list=new ArrayList();
                           for(int i=0;i<totalHumanAreaWise.size();i++)
                        {
                              HashMap humanMembersCountStatusWise=(HashMap)totalHumanAreaWise.get(i);
                              Set human=humanMembersCountStatusWise.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String area_name=(String)it.next();
                                      Double total= (Double)humanMembersCountStatusWise.get(area_name);
                                      EventsYearWise year=new EventsYearWise();
                                      year.setYear(area_name);
                                      year.setCount(total.intValue());
                                      list.add(year);
                                      Debug.print("Area-Name "+area_name);
                                      Debug.print("Total  For"+" "+total.intValue());
                            }
                        } 
                    session.setAttribute("demo", list);
                    session.setAttribute("StartDate",fromDate);
                    session.setAttribute("EndDate",toDate);
                    return mapping.findForward("humanMemberNumberStatusWiseTabular");
                        }
                    
                }
               if(process.equalsIgnoreCase("humanMembersAllAreaStatusWise"))
               {
                   String fromDate = request.getParameter("fromDate");
                        Date startDate=null;
                        String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        String status=request.getParameter("status");
                        String status_Name=null;
                        if(status.equalsIgnoreCase("44615b7f-2911-4bfc-85ef-02dea8305963"))
                                   status_Name="Duplicate";
                        else if(status.equalsIgnoreCase("572456ea-04b0-4a4c-a169-35ec0ccce826"))
                                   status_Name="Merged";
                        else if(status.equalsIgnoreCase("cf7e60e9-3a9a-4645-819f-369cbc7b851a"))
                                   status_Name="Expired";
                        else if(status.equalsIgnoreCase("118756d9-fb79-4304-8647-36f0d7579b45"))
                                   status_Name="Active";
                        else if(status.equalsIgnoreCase("b9f3f76a-1aa7-4e19-b457-43104413828e"))
                                   status_Name="Pending";
                         else if(status.equalsIgnoreCase("59a33c45-bacf-4b3a-ae42-62282d61029c"))
                                   status_Name="Inactive";
                        else if(status.equalsIgnoreCase("bef5db6b-2b64-4382-a91e-666ab253db2e"))
                                   status_Name="Suspended";
                        else if(status.equalsIgnoreCase("60b2e6f6-bc60-4827-b0d6-69957ebfd911"))
                                   status_Name="Deceased";
                        else if(status.equalsIgnoreCase("b7f9ed03-2aca-455f-a17d-883c35ac52b8"))
                                   status_Name="TBD";
                        else if(status.equalsIgnoreCase("a368676e-26e6-4c81-9582-adbd97a991f7"))
                                   status_Name="Retired";
                       ArrayList totalHumanAllAreasStatusWise=remote.getHumanMembersAllAreasStatusWise(startDate,endDate,status);
                        
                             
                        /*for(int i=0;i<totalHumanAllAreasStatusWise.size();i++)
                        {
                              HashMap humanMembersCountStatusWise=(HashMap)totalHumanAllAreasStatusWise.get(i);
                              Set human=humanMembersCountStatusWise.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String area_name=(String)it.next();
                                      Double total= (Double)humanMembersCountStatusWise.get(area_name);
                                      Debug.print("Area-Name "+area_name);

                                      Debug.print("Total  For"+" "+total.doubleValue());
                            }
                        }*/
                            if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                
                        if(graph[i].equalsIgnoreCase("barMarker")) 
                        {
                        //Bar Marker Range Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                       Chart barMarker=BarMarkerRangeGraph.generateTotalHumanAllAreasStatusWiseBarMarkerRangeGraph(totalHumanAllAreasStatusWise);
                       BarMarkerRangeGraph.generateBarMarkerRangeGraphImage(barMarker,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("pie")){
                       // Pie Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPie =PieGraph.generateTotalHumanAllAreasStatusWisePie(totalHumanAllAreasStatusWise);
                         PieGraph.generatePieGraphImage(chartPie, realPath);
                        }
                         else if(graph[i].equalsIgnoreCase("plotformat")){
                          // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.createTotalHumanAllAreasStatusWisePlotFormatGraph(totalHumanAllAreasStatusWise);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                         }
                         else if(graph[i].equalsIgnoreCase("tube")){
                         // Tube Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartTube = TubeGraph.createTotalHumanAllAreasStatusWiseTube(totalHumanAllAreasStatusWise);
                         TubeGraph.generateTubeGraphImage(chartTube, realPath);
                         }
                    }
                         request.setAttribute("status",status_Name);
                          request.setAttribute("graphsCount",graphNew);
                          request.setAttribute("StartDate",fromDate);
                          request.setAttribute("EndDate",toDate);
                     return mapping.findForward("totalHumanAllAreasStatusWise");   
                            }
                            else
                            {
                           List list=new ArrayList();
                           for(int i=0;i<totalHumanAllAreasStatusWise.size();i++)
                        {
                              HashMap humanMembersCountStatusWise=(HashMap)totalHumanAllAreasStatusWise.get(i);
                              Set human=humanMembersCountStatusWise.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String area_name=(String)it.next();
                                      Double total= (Double)humanMembersCountStatusWise.get(area_name);
                                      EventsYearWise year=new EventsYearWise();
                                      year.setYear(area_name);
                                      year.setCount(total.intValue());
                                      list.add(year);
                                      Debug.print("Area-Name "+area_name);
                                      Debug.print("Total  For"+" "+total.doubleValue());
                            }
                        }
                            session.setAttribute("status",status_Name);
                            session.setAttribute("demo",list);
                            session.setAttribute("StartDate",fromDate);
                            session.setAttribute("EndDate",toDate);
                     return mapping.findForward("totalHumanAllAreasStatusWiseTabular");
                            }                 
                
               }
                if(process.equalsIgnoreCase("humanMembersAllMemberhipTypesByArea"))
                {
                     String fromDate = request.getParameter("fromDate");
                        Date startDate=null;
                        String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        String area_id=(String)request.getParameter("area");
                        String area_name=null;
                        if(area_id.equalsIgnoreCase("09133F6D-48B0-4F07-846D-B44599A7D570"))
                            area_name="Area I";
                        else if(area_id.equalsIgnoreCase("440AC928-187D-4F6E-ADB7-4B2435B22104"))
                            area_name="Area II";
                        else if(area_id.equalsIgnoreCase("FD39E89E-7806-4332-A7D1-09736886E194"))
                            area_name="Area III";
                        else if(area_id.equalsIgnoreCase("A41186F2-717A-4042-89BA-90AE4160A1F7"))
                            area_name="Area IV";
                        else if(area_id.equalsIgnoreCase("F627A4EA-FD66-4419-AC4E-AF9CAEF2F587"))
                            area_name="Area V";
                        else if(area_id.equalsIgnoreCase("FCEFCF4F-DB92-42F8-B005-B4C7F2650EC6"))
                            area_name="Area VI";
                        else if(area_id.equalsIgnoreCase("D12543D7-CB00-43BA-87F5-594CD6E32BD5"))
                            area_name="Area VII";
                        else if(area_id.equalsIgnoreCase("C830B545-6ADB-4260-9AD1-DD36F663ED4B"))
                            area_name="Area VIII";
                        else if(area_id.equalsIgnoreCase("C9DB9ED2-C5D6-4671-8B44-87D34C72251C"))
                            area_name="Area IX";
                        else if(area_id.equalsIgnoreCase("33E1594B-E12A-4652-9279-B85A741D4D05"))
                            area_name="Area X";
                        ArrayList totalHumanMembersAllMemberhipTypesByArea=remote.getHumanMembersAllMemberhipTypesByArea(startDate, endDate, area_id);
                        /*for(int i=0;i<totalHumanMembersAllMemberhipTypesByArea.size();i++)
                        {
                              HashMap humanMembersAllMemberhipTypesByArea=(HashMap)totalHumanMembersAllMemberhipTypesByArea.get(i);
                              Set human=humanMembersAllMemberhipTypesByArea.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String area_Name=(String)it.next();
                                      Double total= (Double)humanMembersAllMemberhipTypesByArea.get(area_Name);
                                      Debug.print("Area-Name "+area_Name);
                                      Debug.print("Total  For"+" "+total.doubleValue());
                            }
                        }*/
                              if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                
                        if(graph[i].equalsIgnoreCase("barMarker")) 
                        {
                       //Bar Marker Range Chart Generation
                       BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                       Chart barMarker=BarMarkerRangeGraph.generateTotalHumanAllMembershipsAreaWiseBarMarkerRangeGraph(totalHumanMembersAllMemberhipTypesByArea);
                       BarMarkerRangeGraph.generateBarMarkerRangeGraphImage(barMarker,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("pie"))  {
                       // Pie Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPie =PieGraph.generateTotalHumanAllMembershipsAreaWisePie(totalHumanMembersAllMemberhipTypesByArea);
                         PieGraph.generatePieGraphImage(chartPie, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("plotformat"))  {
                         // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.generateTotalHumanAllMembershipsAreaWisePlotFormat(totalHumanMembersAllMemberhipTypesByArea);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("tube")) {
                         // Tube Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartTube = TubeGraph.generateTotalHumanAllMembershipsAreaWiseTube(totalHumanMembersAllMemberhipTypesByArea);
                         TubeGraph.generateTubeGraphImage(chartTube, realPath);
                        }
                    }
                     request.setAttribute("graphsCount",graphNew);     
                    request.setAttribute("totalHumanMembersAllMemberhipTypesByArea",area_name);
                    request.setAttribute("StartDate",fromDate);
                            request.setAttribute("EndDate",toDate);
                       return mapping.findForward("humanMembersAllMemberhipTypesByArea"); 
                              }
                              else{
                            List list=new ArrayList();
                            for(int i=0;i<totalHumanMembersAllMemberhipTypesByArea.size();i++)
                        {
                              HashMap humanMembersAllMemberhipTypesByArea=(HashMap)totalHumanMembersAllMemberhipTypesByArea.get(i);
                              Set human=humanMembersAllMemberhipTypesByArea.keySet();
                            for(Iterator it=human.iterator();it.hasNext();)
                            {
                                      String area_Name=(String)it.next();
                                      Double total= (Double)humanMembersAllMemberhipTypesByArea.get(area_Name);
                                      EventsYearWise yr=new EventsYearWise();
                                      yr.setYear(area_Name);yr.setCount(total.intValue());
                                      list.add(yr);
                                      Debug.print("Area-Name "+area_Name);
                                      Debug.print("Total  For"+" "+total.doubleValue());
                            }
                        }
                            session.setAttribute("demo",list);
                            session.setAttribute("StartDate",fromDate);
                            session.setAttribute("EndDate",toDate);
                            session.setAttribute("totalHumanMembersAllMemberhipTypesByArea",area_name);
                            return mapping.findForward("humanMembersAllMemberhipTypesByAreaTabular"); 
                            
                              }
                }
                if(process.equalsIgnoreCase("humanMembersAllMemberhipTypesByAreaAndStatus"))
                {
                        String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                    String fromDate = request.getParameter("fromDate");
                        Date startDate=null;

                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        String area_id=(String)request.getParameter("area");
                        String area_name=null;
                        if(area_id.equalsIgnoreCase("09133F6D-48B0-4F07-846D-B44599A7D570"))
                            area_name="Area I";
                        else if(area_id.equalsIgnoreCase("440AC928-187D-4F6E-ADB7-4B2435B22104"))
                            area_name="Area II";
                        else if(area_id.equalsIgnoreCase("FD39E89E-7806-4332-A7D1-09736886E194"))
                            area_name="Area III";
                        else if(area_id.equalsIgnoreCase("A41186F2-717A-4042-89BA-90AE4160A1F7"))
                            area_name="Area IV";
                        else if(area_id.equalsIgnoreCase("F627A4EA-FD66-4419-AC4E-AF9CAEF2F587"))
                            area_name="Area V";
                        else if(area_id.equalsIgnoreCase("FCEFCF4F-DB92-42F8-B005-B4C7F2650EC6"))
                            area_name="Area VI";
                        else if(area_id.equalsIgnoreCase("D12543D7-CB00-43BA-87F5-594CD6E32BD5"))
                            area_name="Area VII";
                        else if(area_id.equalsIgnoreCase("C830B545-6ADB-4260-9AD1-DD36F663ED4B"))
                            area_name="Area VIII";
                        else if(area_id.equalsIgnoreCase("C9DB9ED2-C5D6-4671-8B44-87D34C72251C"))
                            area_name="Area IX";
                        else if(area_id.equalsIgnoreCase("33E1594B-E12A-4652-9279-B85A741D4D05"))
                            area_name="Area X";
                        String status=request.getParameter("status");
                        String status_Name=null;
                        if(status.equalsIgnoreCase("44615b7f-2911-4bfc-85ef-02dea8305963"))
                                   status_Name="Duplicate";
                        else if(status.equalsIgnoreCase("572456ea-04b0-4a4c-a169-35ec0ccce826"))
                                   status_Name="Merged";
                        else if(status.equalsIgnoreCase("cf7e60e9-3a9a-4645-819f-369cbc7b851a"))
                                   status_Name="Expired";
                        else if(status.equalsIgnoreCase("118756d9-fb79-4304-8647-36f0d7579b45"))
                                   status_Name="Active";
                        else if(status.equalsIgnoreCase("b9f3f76a-1aa7-4e19-b457-43104413828e"))
                                   status_Name="Pending";
                         else if(status.equalsIgnoreCase("59a33c45-bacf-4b3a-ae42-62282d61029c"))
                                   status_Name="Inactive";
                        else if(status.equalsIgnoreCase("bef5db6b-2b64-4382-a91e-666ab253db2e"))
                                   status_Name="Suspended";
                        else if(status.equalsIgnoreCase("60b2e6f6-bc60-4827-b0d6-69957ebfd911"))
                                   status_Name="Deceased";
                        else if(status.equalsIgnoreCase("b7f9ed03-2aca-455f-a17d-883c35ac52b8"))
                                   status_Name="TBD";
                        else if(status.equalsIgnoreCase("a368676e-26e6-4c81-9582-adbd97a991f7"))
                                   status_Name="Retired";
                        ArrayList humanMembersAllMemberhipTypesByAreaAndStatus=remote.getHumanMembersAllMemberhipTypesByAreaAndStatus(startDate,endDate,area_id,status);
                         /*for(int i=0;i<humanMembersAllMemberhipTypesByAreaAndStatus.size();i++)
                                {
                                      HashMap humanMembersAllMemberhipTypesByArea=(HashMap)humanMembersAllMemberhipTypesByAreaAndStatus.get(i);
                                      Set human=humanMembersAllMemberhipTypesByArea.keySet();
                                    for(Iterator it=human.iterator();it.hasNext();)
                                    {
                                              String membership_Name=(String)it.next();
                                              Double total= (Double)humanMembersAllMemberhipTypesByArea.get(membership_Name);
                                              Debug.print("Membership-Name "+membership_Name);
                                              Debug.print("Total  For"+" "+total.doubleValue());
                                    }
                                }*/
                        //ArrayList totalHumanMembersAllMemberhipTypesByArea=remote.getHumanMembersAllMemberhipTypesByAreaAndStatus(startDate, endDate, area_id, status);
                        //All the Below methods are same for Report No. 6 
                               if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                
                        if(graph[i].equalsIgnoreCase("barMarker")) 
                        {
                        //Bar Marker Range Chart Generation
                       BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                       Chart barMarker=BarMarkerRangeGraph.generateTotalHumanAllMembershipsAreaWiseBarMarkerRangeGraph(humanMembersAllMemberhipTypesByAreaAndStatus);
                       BarMarkerRangeGraph.generateBarMarkerRangeGraphImage(barMarker,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("pie")) 
                        {
                       
                       // Pie Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPie =PieGraph.generateTotalHumanAllMembershipsAreaWisePie(humanMembersAllMemberhipTypesByAreaAndStatus);
                         PieGraph.generatePieGraphImage(chartPie, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("plotformat")) {
                         // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.generateTotalHumanAllMembershipsAreaWisePlotFormat(humanMembersAllMemberhipTypesByAreaAndStatus);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("tube")) {
                         // Tube Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartTube = TubeGraph.generateTotalHumanAllMembershipsAreaWiseTube(humanMembersAllMemberhipTypesByAreaAndStatus);
                         TubeGraph.generateTubeGraphImage(chartTube, realPath);
                        }
                    }
                        request.setAttribute("area",area_name);
                        request.setAttribute("status",status_Name);
                        request.setAttribute("graphsCount",graphNew);
                        return mapping.findForward("humanMembersAllMemberhipTypesByAreaAndStatus");
                               }
                               else{
                                List list=new ArrayList();
                                for(int i=0;i<humanMembersAllMemberhipTypesByAreaAndStatus.size();i++)
                                {
                                      HashMap humanMembersAllMemberhipTypesByArea=(HashMap)humanMembersAllMemberhipTypesByAreaAndStatus.get(i);
                                      Set human=humanMembersAllMemberhipTypesByArea.keySet();
                                    for(Iterator it=human.iterator();it.hasNext();)
                                    {
                                              String membership_Name=(String)it.next();
                                              Double total= (Double)humanMembersAllMemberhipTypesByArea.get(membership_Name);
                                              EventsYearWise yr=new EventsYearWise();
                                              yr.setYear(membership_Name);
                                              yr.setCount(total.intValue());
                                              list.add(yr);
                                              Debug.print("Membership-Name "+membership_Name);
                                              Debug.print("Total  For"+" "+total.doubleValue());
                                    }
                                }
                                session.setAttribute("demo",list);
                                session.setAttribute("area",area_name);
                                session.setAttribute("status",status_Name);
                                return mapping.findForward("humanMembersAllMemberhipTypesByAreaAndStatusTabular");
                               }
                }
                if(process.equalsIgnoreCase("humanMembersSpecificAreaAndMembershipType"))
                {
                    String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                    String fromDate = request.getParameter("fromDate");
                        Date startDate=null;

                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        String area_id=(String)request.getParameter("area");
                        String area_name=null;
                        if(area_id.equalsIgnoreCase("09133F6D-48B0-4F07-846D-B44599A7D570"))
                            area_name="Area I";
                        else if(area_id.equalsIgnoreCase("440AC928-187D-4F6E-ADB7-4B2435B22104"))
                            area_name="Area II";
                        else if(area_id.equalsIgnoreCase("FD39E89E-7806-4332-A7D1-09736886E194"))
                            area_name="Area III";
                        else if(area_id.equalsIgnoreCase("A41186F2-717A-4042-89BA-90AE4160A1F7"))
                            area_name="Area IV";
                        else if(area_id.equalsIgnoreCase("F627A4EA-FD66-4419-AC4E-AF9CAEF2F587"))
                            area_name="Area V";
                        else if(area_id.equalsIgnoreCase("FCEFCF4F-DB92-42F8-B005-B4C7F2650EC6"))
                            area_name="Area VI";
                        else if(area_id.equalsIgnoreCase("D12543D7-CB00-43BA-87F5-594CD6E32BD5"))
                            area_name="Area VII";
                        else if(area_id.equalsIgnoreCase("C830B545-6ADB-4260-9AD1-DD36F663ED4B"))
                            area_name="Area VIII";
                        else if(area_id.equalsIgnoreCase("C9DB9ED2-C5D6-4671-8B44-87D34C72251C"))
                            area_name="Area IX";
                        else if(area_id.equalsIgnoreCase("33E1594B-E12A-4652-9279-B85A741D4D05"))
                            area_name="Area X";
                        String membership=request.getParameter("membership");
                        String membership_Name=null;
                             if(membership.equalsIgnoreCase("60BC8025-B043-4771-AD1E-09C9BC160AEE"))
                                  membership_Name="Junior Member";
                        else if(membership.equalsIgnoreCase("A95FAB3A-4C2F-47F5-9161-0AE519290131"))
                                  membership_Name="Life Member";
                        else if(membership.equalsIgnoreCase("E750ED08-5C4B-4FCA-BFD7-52EBB22BDB2B"))
                                  membership_Name="Full Member";
                        else if(membership.equalsIgnoreCase("74F758E9-6E03-40DB-A402-9F6B4BD36606"))
                                  membership_Name="Subscribing Member";
                        else if(membership.equalsIgnoreCase("C57DD232-C425-417F-BC27-CE2C9B7EBC83"))
                                  membership_Name="Family Member";
                        else if(membership.equalsIgnoreCase("19EB90A6-5B73-46BD-9CD5-DFFB6DC96E12"))
                                  membership_Name="Non-Competing Member";
                        int count=remote.getHumanMembersSpecificAreaAndMembershipType(startDate,endDate,area_id,membership);
                             if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                
                        if(graph[i].equalsIgnoreCase("plotformat")) 
                        {
                         // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.createHumanMembersSpecificAreaAndMembershipTypePlotFormat(count, membership_Name,area_name);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("chart")){
                         //Bar Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartBar=BarGraph.createHumanMembersSpecificAreaAndMembershipTypeBar(count, membership_Name, area_name);
                          BarGraph.generateBarGraphImage(chartBar, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("tube")) {
                        // Tube Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartTube = TubeGraph.createHumanMembersSpecificAreaAndMembershipTypeTube(count, membership_Name, area_name);
                         TubeGraph.generateTubeGraphImage(chartTube, realPath); 
                        }
                        else if(graph[i].equalsIgnoreCase("cone")) {
                         // Cone Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartCone = ConeGraph.createHumanMembersSpecificAreaAndMembershipTypeCone(count, membership_Name, area_name);
                         ConeGraph.generateConeGraphImage(chartCone, realPath);
                        }
                    }
                         request.setAttribute("membership_type_name",membership_Name);
                         request.setAttribute("areaName",area_name);
                         request.setAttribute("graphsCount",graphNew);
                         return mapping.findForward("humanMembersSpecificAreaAndMembershipType");
                    }
                             else{
                            List list=new ArrayList();
                        HumanMembersEventsParticipated humanCount=new HumanMembersEventsParticipated();
                        humanCount.setEventsConducted(count);
                        list.add(humanCount);
                        
                          session.setAttribute("demo",list);  
                         session.setAttribute("membership_type_name",membership_Name);
                         session.setAttribute("areaName",area_name);
                         return mapping.findForward("humanMembersSpecificAreaAndMembershipTypeTabular");
                   
                             }
                }
                if(process.equalsIgnoreCase("humanMembersSpecificAreaAndMembershipTypeAndStatus"))
                {
                    String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                    
                    String fromDate = request.getParameter("fromDate");
                        Date startDate=null;

                        if(fromDate!=null && fromDate.trim().length()!=0){
                            startDate=(Date)sdf.parse(fromDate);
                            Debug.print("startDate in servlet"+startDate);
                        }
                        
                        String toDate = request.getParameter("toDate");
                        Date endDate=null;

                        if(toDate!=null && toDate.trim().length()!=0){
                            endDate=(Date)sdf.parse(toDate);
                            Debug.print("startDate in servlet"+endDate);
                        }
                        String area_id=(String)request.getParameter("area");
                        String area_name=null;
                        if(area_id.equalsIgnoreCase("09133F6D-48B0-4F07-846D-B44599A7D570"))
                            area_name="Area I";
                        else if(area_id.equalsIgnoreCase("440AC928-187D-4F6E-ADB7-4B2435B22104"))
                            area_name="Area II";
                        else if(area_id.equalsIgnoreCase("FD39E89E-7806-4332-A7D1-09736886E194"))
                            area_name="Area III";
                        else if(area_id.equalsIgnoreCase("A41186F2-717A-4042-89BA-90AE4160A1F7"))
                            area_name="Area IV";
                        else if(area_id.equalsIgnoreCase("F627A4EA-FD66-4419-AC4E-AF9CAEF2F587"))
                            area_name="Area V";
                        else if(area_id.equalsIgnoreCase("FCEFCF4F-DB92-42F8-B005-B4C7F2650EC6"))
                            area_name="Area VI";
                        else if(area_id.equalsIgnoreCase("D12543D7-CB00-43BA-87F5-594CD6E32BD5"))
                            area_name="Area VII";
                        else if(area_id.equalsIgnoreCase("C830B545-6ADB-4260-9AD1-DD36F663ED4B"))
                            area_name="Area VIII";
                        else if(area_id.equalsIgnoreCase("C9DB9ED2-C5D6-4671-8B44-87D34C72251C"))
                            area_name="Area IX";
                        else if(area_id.equalsIgnoreCase("33E1594B-E12A-4652-9279-B85A741D4D05"))
                            area_name="Area X";
                        String membership=request.getParameter("membership");
                        String membership_Name=null;
                             if(membership.equalsIgnoreCase("60BC8025-B043-4771-AD1E-09C9BC160AEE"))
                                  membership_Name="Junior Member";
                        else if(membership.equalsIgnoreCase("A95FAB3A-4C2F-47F5-9161-0AE519290131"))
                                  membership_Name="Life Member";
                        else if(membership.equalsIgnoreCase("E750ED08-5C4B-4FCA-BFD7-52EBB22BDB2B"))
                                  membership_Name="Full Member";
                        else if(membership.equalsIgnoreCase("74F758E9-6E03-40DB-A402-9F6B4BD36606"))
                                  membership_Name="Subscribing Member";
                        else if(membership.equalsIgnoreCase("C57DD232-C425-417F-BC27-CE2C9B7EBC83"))
                                  membership_Name="Family Member";
                        else if(membership.equalsIgnoreCase("19EB90A6-5B73-46BD-9CD5-DFFB6DC96E12"))
                                  membership_Name="Non-Competing Member";
                           String status=request.getParameter("status");
                        String status_Name=null;
                        if(status.equalsIgnoreCase("44615b7f-2911-4bfc-85ef-02dea8305963"))
                                   status_Name="Duplicate";
                        else if(status.equalsIgnoreCase("572456ea-04b0-4a4c-a169-35ec0ccce826"))
                                   status_Name="Merged";
                        else if(status.equalsIgnoreCase("cf7e60e9-3a9a-4645-819f-369cbc7b851a"))
                                   status_Name="Expired";
                        else if(status.equalsIgnoreCase("118756d9-fb79-4304-8647-36f0d7579b45"))
                                   status_Name="Active";
                        else if(status.equalsIgnoreCase("b9f3f76a-1aa7-4e19-b457-43104413828e"))
                                   status_Name="Pending";
                         else if(status.equalsIgnoreCase("59a33c45-bacf-4b3a-ae42-62282d61029c"))
                                   status_Name="Inactive";
                        else if(status.equalsIgnoreCase("bef5db6b-2b64-4382-a91e-666ab253db2e"))
                                   status_Name="Suspended";
                        else if(status.equalsIgnoreCase("60b2e6f6-bc60-4827-b0d6-69957ebfd911"))
                                   status_Name="Deceased";
                        else if(status.equalsIgnoreCase("b7f9ed03-2aca-455f-a17d-883c35ac52b8"))
                                   status_Name="TBD";
                        else if(status.equalsIgnoreCase("a368676e-26e6-4c81-9582-adbd97a991f7"))
                                   status_Name="Retired";
                        int count=remote.getHumanMembersSpecificAreaAndMembershipTypeAndStatus(startDate,endDate,area_id,membership,status);
                        //All Below methods for different graphs are same used in above report
                                if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                
                        if(graph[i].equalsIgnoreCase("plotformat")) 
                        {
                        // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.createHumanMembersSpecificAreaAndMembershipTypePlotFormat(count, membership_Name,area_name);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("chart")) {
                         //Bar Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartBar=BarGraph.createHumanMembersSpecificAreaAndMembershipTypeBar(count, membership_Name, area_name);
                          BarGraph.generateBarGraphImage(chartBar, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("tube")) {
                        // Tube Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartTube = TubeGraph.createHumanMembersSpecificAreaAndMembershipTypeTube(count, membership_Name, area_name);
                         TubeGraph.generateTubeGraphImage(chartTube, realPath); 
                        }
                        else if(graph[i].equalsIgnoreCase("cone")){
                         // Cone Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartCone = ConeGraph.createHumanMembersSpecificAreaAndMembershipTypeCone(count, membership_Name, area_name);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                        }
                    }
                        request.setAttribute("membership_type_name",membership_Name);
                         request.setAttribute("areaName",area_name);
                         request.setAttribute("status",status_Name);
                         request.setAttribute("graphsCount",graphNew);
                         request.setAttribute("StartDate",fromDate);
                            request.setAttribute("EndDate",toDate);
                         return mapping.findForward("humanMembersSpecificAreaAndMembershipTypeAndStatus");
                                }
                                else
                                {
                             List list=new ArrayList();
                        HumanMembersEventsParticipated humanCount=new HumanMembersEventsParticipated();
                        humanCount.setEventsConducted(count);
                        list.add(humanCount);
                        
                         session.setAttribute("demo",list);  
                         session.setAttribute("membership_type_name",membership_Name);
                         session.setAttribute("areaName",area_name);
                         session.setAttribute("status",status_Name);
                         session.setAttribute("StartDate",fromDate);
                          session.setAttribute("EndDate",toDate);
                         return mapping.findForward("humanMembersSpecificAreaAndMembershipTypeStatusTabular");
                                }
                }
                if(process.equalsIgnoreCase("membersJoinedYearWise"))
                {
                     String view=request.getParameter("view");
                        Debug.print("view value is "+view);
                        String graphNew=request.getParameter("graph");
                        String graph[]=graphNew.split(",");
                        String year=request.getParameter("year");
                        LinkedHashMap membersCount=remote.getMemberJoined(year);
                          if(view.equalsIgnoreCase("1"))
                {
                    for(int i=0;i<graph.length;i++)
                    {
                
                        if(graph[i].equalsIgnoreCase("plotformat")) 
                        {
                        // PLot Format Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPlotFormat =PlotFormat.generateMembersJoinedForParticularYearPlotFormat(membersCount);
                         PlotFormat.generatePlotFormatGraphImage(chartPlotFormat,realPath);
                        }// add for pie here
                        else if(graph[i].equalsIgnoreCase("pie")) {
                         //Bar Chart Generation
                         BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                         Chart chartPie=PieGraph.generateMembersJoinedForParticularYearPie(membersCount);
                          PieGraph.generatePieGraphImage(chartPie, realPath);
                        }
                        else if(graph[i].equalsIgnoreCase("tube")) {
                        // Tube Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartTube = TubeGraph.generateMembersJoinedForParticularYearTube(membersCount);
                         TubeGraph.generateTubeGraphImage(chartTube, realPath); 
                        }
                        else if(graph[i].equalsIgnoreCase("cone")){
                         // Cone Chart Generation
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        Chart chartCone = ConeGraph.generateMembersJoinedForParticularYearCone(membersCount);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                        }
                    }
                        
                         request.setAttribute("graphsCount",graphNew);
                         request.setAttribute("year",year);
                         return mapping.findForward("memberCountGraphical");
                                }
                         else
                                {
                             List list=new ArrayList();
                             Set human=membersCount.keySet();
                                    for(Iterator it=human.iterator();it.hasNext();)
                                    {
                                              String month_Name=(String)it.next();
                                              Integer total= (Integer)membersCount.get(month_Name);
                                              MembersCount count=new MembersCount();
                                              count.setMonth(month_Name);
                                              count.setMemberCount(total.intValue());
                                              list.add(count);
                                              System.out.print("Month "+month_Name);
                                             System.out.print("Total  For"+total.intValue());
                                    }
                       
                       
                        
                        
                         session.setAttribute("demo",list);  
                        
                          session.setAttribute("year",year);
                         return mapping.findForward("memberCountTabular");
                                }
                        
                }
            }
            return null;
    }
}