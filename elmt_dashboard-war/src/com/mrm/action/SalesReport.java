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
// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 19-03-2012 10:27:08
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SalesReport.java

package com.mrm.action;

import com.birt.graphs.*;
import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.util.BIRTGraphsConfig;
import java.io.PrintStream;
import java.util.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import org.apache.struts.action.*;

// Referenced classes of package com.mrm.action:
//            Demo, Item, Volume

public class SalesReport extends Action
{

    public SalesReport()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        org.apache.struts.util.MessageResources mr = getResources(request);
        System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        System.setProperty("java.naming.provider.url", "jnp://");
        Context jndiContext = new InitialContext();
        jndiContext = getInitialContext();
        Object obj = jndiContext.lookup("ejb/MFGMemberRegistrationJNDI");
        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)PortableRemoteObject.narrow(obj,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
        ServletContext theApplicationsServletContext = getServlet().getServletContext();
        String realPath = (new StringBuilder()).append(theApplicationsServletContext.getRealPath("/")).append("images/").toString();
        System.out.println("after create.............");
        String process = request.getParameter("process");
        if(process != null)
        {
            if(process.equalsIgnoreCase("quarterSalesByItemParticularYear"))
            {
                ArrayList yearlyEvents = null;
                String graph = request.getParameter("graph");
                Debug.print((new StringBuilder()).append("Graph selected ").append(graph).toString());
                String year = request.getParameter("year");
                Debug.print((new StringBuilder()).append("Year selected ").append(year).toString());
                String item = request.getParameter("item");
                Debug.print((new StringBuilder()).append("Itemselected ").append(item).toString());
                String view = request.getParameter("view");
                Debug.print((new StringBuilder()).append("Itemselected ").append(view).toString());
                String item_name = null;
                if(item.equalsIgnoreCase("101"))
                    item_name = "Engine Assembly";
                else
                if(item.equalsIgnoreCase("102"))
                    item_name = "Transmission Assembly";
                else
                    item_name = "Engine Control Module";
                ArrayList count = remote.getQuarterlySalesByItemForYear(year, item);
                if(view.equals("1"))
                {
                    for(int i = 0; i < count.size(); i++)
                    {
                        LinkedHashMap typeWise = (LinkedHashMap)count.get(i);
                        Set type = typeWise.keySet();
                        String quarter_Name;
                        Double db;
                        for(Iterator it = type.iterator(); it.hasNext(); Debug.print((new StringBuilder()).append("Quater Type Name is:").append(quarter_Name).append("  Count is ").append(db.doubleValue()).toString()))
                        {
                            quarter_Name = (String)it.next();
                            db = (Double)typeWise.get(quarter_Name);
                        }

                    }

                    if(graph.equalsIgnoreCase("Pie"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPie = PieGraph.generateQuarterSalesByItemParticularYearPie(count);
                        PieGraph.generatePieGraphImage(chartPie, realPath);
                    } else
                    if(graph.equalsIgnoreCase("PlotFormat"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPlotFormat = PlotFormat.generateQuarterSalesByItemParticularYearPlotFormat(count);
                        PlotFormat.generatePlotFormatGraphImage(chartPlotFormat, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Cone"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartCone = ConeGraph.generateQuarterSalesByItemParticularYearCone(count);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Line"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartLine = LineGraph.generateQuarterSalesByItemParticularYearLine(count);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "quarterSalesByItemParticularYear");
                        return mapping.findForward("salesMessage");
                    }
                    request.setAttribute("title", (new StringBuilder()).append(year).append(" Quarterly Sales Report of ").append(item_name).toString());
                    return mapping.findForward("showSalesReport");
                }
                HttpSession session = request.getSession();
                List list = new ArrayList();
                for(int i = 0; i < count.size(); i++)
                {
                    HashMap typeWise = (HashMap)count.get(i);
                    Set type = typeWise.keySet();
                    Demo demo;
                    for(Iterator it = type.iterator(); it.hasNext(); list.add(demo))
                    {
                        String quarter_Name = (String)it.next();
                        Double db = (Double)typeWise.get(quarter_Name);
                        Debug.print((new StringBuilder()).append("Quater Type Name is:").append(quarter_Name).append("  Count is ").append(db.doubleValue()).toString());
                        demo = new Demo();
                        demo.setQuaters(quarter_Name);
                        demo.setValues(db);
                    }

                }

                session.setAttribute("demo", list);
                session.setAttribute("year", year);
                return mapping.findForward("frmTabular");
            }
            if(process.equalsIgnoreCase("quarterSalesAllItemsParticularYear"))
            {
                ArrayList yearlyEvents = null;
                String year = request.getParameter("year");
                Debug.print((new StringBuilder()).append("Year selected ").append(year).toString());
                String graph = request.getParameter("graph");
                Debug.print((new StringBuilder()).append("Graph selected ").append(graph).toString());
                String quarter = request.getParameter("quarter");
                Debug.print((new StringBuilder()).append("quarter selected ").append(quarter).toString());
                String view = request.getParameter("view");
                Debug.print((new StringBuilder()).append("Itemselected ").append(view).toString());
                ArrayList count = remote.getQuarterWiseSalesAllItemsForYear(quarter, year);
                if(view.equals("1"))
                {
                    Debug.print((new StringBuilder()).append("Size is ").append(count.size()).toString());
                    for(int i = 0; i < count.size(); i++)
                    {
                        HashMap typeWise = (HashMap)count.get(i);
                        Set type = typeWise.keySet();
                        String item_Name;
                        Double db;
                        for(Iterator it = type.iterator(); it.hasNext(); Debug.print((new StringBuilder()).append("item_Name  Name is:").append(item_Name).append("  Count is ").append(db.doubleValue()).toString()))
                        {
                            item_Name = (String)it.next();
                            db = (Double)typeWise.get(item_Name);
                        }

                    }

                    if(graph.equalsIgnoreCase("Pie"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPie = PieGraph.generateQuarterSalesAllItemParticularYearPie(count);
                        PieGraph.generatePieGraphImage(chartPie, realPath);
                    } else
                    if(graph.equalsIgnoreCase("PlotFormat"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPlotFormat = PlotFormat.generateQuarterSalesAllItemParticularYearPlotFormat(count);
                        PlotFormat.generatePlotFormatGraphImage(chartPlotFormat, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Tube"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartTube = TubeGraph.generateQuarterSalesAllItemParticularYearTube(count);
                        TubeGraph.generateTubeGraphImage(chartTube, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Cone"))
                    {
                        org.eclipse.birt.chart.model.Chart chartCone = ConeGraph.generateQuarterSalesAllItemParticularYearCone(count);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Line"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartLine = LineGraph.generateQuarterSalesAllItemParticularYearLine(count);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "quarterSalesAllItemsParticularYear");
                        return mapping.findForward("salesMessage");
                    }
                    request.setAttribute("title", (new StringBuilder()).append(year).append(" Quarter Wise  Sales For All Items").toString());
                    return mapping.findForward("showSalesReport");
                }
                HttpSession session = request.getSession();
                List list = new ArrayList();
                for(int i = 0; i < count.size(); i++)
                {
                    HashMap typeWise = (HashMap)count.get(i);
                    Set type = typeWise.keySet();
                    Item item;
                    for(Iterator it = type.iterator(); it.hasNext(); list.add(item))
                    {
                        String item_Name = (String)it.next();
                        Double db = (Double)typeWise.get(item_Name);
                        Debug.print((new StringBuilder()).append("Quater Type Name is:").append(item_Name).append("  Count is ").append(db.doubleValue()).toString());
                        item = new Item();
                        item.setItems(item_Name);
                        item.setValues(db);
                    }

                }

                session.setAttribute("demo", list);
                session.setAttribute("year", year);
                return mapping.findForward("frmItemTabular");
            }
            if(process.equalsIgnoreCase("quarterVolumeSalesByItemParticularYear"))
            {
                ArrayList yearlyEvents = null;
                String graph = request.getParameter("graph");
                Debug.print((new StringBuilder()).append("Graph selected :").append(graph).toString());
                String year = request.getParameter("year");
                Debug.print((new StringBuilder()).append("Year selected ").append(year).toString());
                String item = request.getParameter("item");
                Debug.print((new StringBuilder()).append("Itemselected ").append(item).toString());
                String view = request.getParameter("view");
                Debug.print((new StringBuilder()).append("Itemselected ").append(view).toString());
                String item_name = null;
                if(item.equalsIgnoreCase("101"))
                    item_name = "Engine Assembly";
                else
                if(item.equalsIgnoreCase("102"))
                    item_name = "Transmission Assembly";
                else
                    item_name = "Engine Control Module";
                ArrayList count = remote.getQuarterlyVolumeSalesByItemForYear(year, item);
                if(view.equals("1"))
                {
                    for(int i = 0; i < count.size(); i++)
                    {
                        LinkedHashMap typeWise = (LinkedHashMap)count.get(i);
                        Set type = typeWise.keySet();
                        String quarter_Name;
                        Double db;
                        for(Iterator it = type.iterator(); it.hasNext(); Debug.print((new StringBuilder()).append("Quater Type Name is:").append(quarter_Name).append("  Count is ").append(db.doubleValue()).toString()))
                        {
                            quarter_Name = (String)it.next();
                            db = (Double)typeWise.get(quarter_Name);
                        }

                    }

                    if(graph.equalsIgnoreCase("Pie"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPie = PieGraph.generateQuarterVolumeSalesByItemParticularYearPie(count);
                        PieGraph.generatePieGraphImage(chartPie, realPath);
                    } else
                    if(graph.equalsIgnoreCase("PlotFormat"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPlotFormat = PlotFormat.generateQuarterVolumeSalesByItemParticularYearPlotFormat(count);
                        PlotFormat.generatePlotFormatGraphImage(chartPlotFormat, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Tube"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartTube = TubeGraph.generateQuarterSalesByItemParticularYearTube(count);
                        TubeGraph.generateTubeGraphImage(chartTube, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Line"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartLine = LineGraph.generateQuarterVolumeSalesByItemParticularYearLine(count);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "quarterVolumeSalesByItemParticularYear");
                        return mapping.findForward("salesVolumeMessage");
                    }
                    request.setAttribute("title", (new StringBuilder()).append(year).append(" Quarterly Volume Sales Report of ").append(item_name).toString());
                    return mapping.findForward("showSalesReport");
                }
                HttpSession session = request.getSession();
                List list = new ArrayList();
                for(int i = 0; i < count.size(); i++)
                {
                    HashMap typeWise = (HashMap)count.get(i);
                    Set type = typeWise.keySet();
                    Volume volume;
                    for(Iterator it = type.iterator(); it.hasNext(); list.add(volume))
                    {
                        String ouarter = (String)it.next();
                        Double db = (Double)typeWise.get(ouarter);
                        Debug.print((new StringBuilder()).append("Quater Type Name is:").append(ouarter).append("  Count is ").append(db.doubleValue()).toString());
                        volume = new Volume();
                        volume.setQuarter(ouarter);
                        volume.setUnitSales(db);
                    }

                }

                session.setAttribute("demo", list);
                session.setAttribute("year", year);
                return mapping.findForward("frmSalesTabular");
            }
        }
        return null;
    }

    public static Context getInitialContext()
        throws NamingException
    {
        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        p.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        p.setProperty("java.naming.provider.url", "");
        return new InitialContext(p);
    }
}
