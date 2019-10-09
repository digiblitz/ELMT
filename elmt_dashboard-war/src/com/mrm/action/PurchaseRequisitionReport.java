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
// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 19-03-2012 10:26:34
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PurchaseRequisitionReport.java

package com.mrm.action;

import com.birt.graphs.*;
import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.util.BIRTGraphsConfig;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import org.apache.struts.action.*;

// Referenced classes of package com.mrm.action:
//            Purchase, Status, StockRequirementAndAvailabilty, Stock, 
//            SuppliedStock

public class PurchaseRequisitionReport extends Action
{

    public PurchaseRequisitionReport()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm frm, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        org.apache.struts.util.MessageResources mr = getResources(request);
        System.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        System.setProperty("java.naming.provider.url", "jnp://");
        Context jndiContext = new InitialContext();
        jndiContext = getInitialContext();
        Object obj = jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)PortableRemoteObject.narrow(obj,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
        ServletContext theApplicationsServletContext = getServlet().getServletContext();
        String realPath = (new StringBuilder()).append(theApplicationsServletContext.getRealPath("/")).append("images/").toString();
        System.out.println("after create.............");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String process = request.getParameter("process");
        if(process != null)
        {
            if(process.equalsIgnoreCase("purchaseRequisitionPeriodWise"))
            {
                String graph = request.getParameter("graph");
                Debug.print((new StringBuilder()).append("Graph is").append(graph).toString());
                String fromDate = request.getParameter("fromDate");
                java.util.Date startDate = null;
                if(fromDate != null && fromDate.trim().length() != 0)
                    startDate = sdf.parse(fromDate);
                String toDate = request.getParameter("toDate");
                java.util.Date endDate = null;
                String view = request.getParameter("view");
                Debug.print((new StringBuilder()).append("Itemselected ").append(view).toString());
                if(toDate != null && toDate.trim().length() != 0)
                    endDate = sdf.parse(toDate);
                ArrayList list = remote.getStatusWisePurchaseRequisition(startDate, endDate);
                if(view.equals("1"))
                {
                    for(int i = 0; i < list.size(); i++)
                    {
                        HashMap typeWise = (HashMap)list.get(i);
                        Set type = typeWise.keySet();
                        String type_Name;
                        Double db;
                        for(Iterator it = type.iterator(); it.hasNext(); Debug.print((new StringBuilder()).append("Status:").append(type_Name).append("  Count is ").append(db.doubleValue()).toString()))
                        {
                            type_Name = (String)it.next();
                            db = (Double)typeWise.get(type_Name);
                        }

                    }

                    if(graph.equalsIgnoreCase("Pie"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPie = PieGraph.generatePurchaseRequisitionPeriodWisePie(list);
                        PieGraph.generatePieGraphImage(chartPie, realPath);
                    } else
                    if(graph.equalsIgnoreCase("PlotFormat"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPlotFormat = PlotFormat.generatePurchaseRequisitionPeriodWisePlotFormat(list);
                        PlotFormat.generatePlotFormatGraphImage(chartPlotFormat, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Cone"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartCone = ConeGraph.generatePurchaseRequisitionPeriodWiseCone(list);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Line"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartLine = LineGraph.generatePurchaseRequisitionPeriodWisLine(list);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "purchaseRequisitionPeriodWise");
                        return mapping.findForward("purchaseRequisitionMessage");
                    }
                    request.setAttribute("title", "Status Wise Purchase Requisitions");
                    return mapping.findForward("purchaseRequisitionPeriodWise");
                }
                HttpSession session = request.getSession();
                List count = new ArrayList();
                for(int i = 0; i < list.size(); i++)
                {
                    HashMap typeWise = (HashMap)list.get(i);
                    Set type = typeWise.keySet();
                    Purchase purchase;
                    for(Iterator it = type.iterator(); it.hasNext(); count.add(purchase))
                    {
                        String status = (String)it.next();
                        Double db = (Double)typeWise.get(status);
                        Debug.print((new StringBuilder()).append("Quater Type Name is:").append(status).append("  Count is ").append(db.doubleValue()).toString());
                        purchase = new Purchase();
                        purchase.setStatus(status);
                        purchase.setPurcahseRequistion(db);
                    }

                }

                session.setAttribute("demo", count);
                session.setAttribute("fromDate", fromDate);
                session.setAttribute("toDate", toDate);
                return mapping.findForward("frmPurchaseTabular");
            }
            if(process.equalsIgnoreCase("purchaseRequisitionParticularStatus"))
            {
                Debug.print("Inside purchaseRequisitionParticularStatus");
                String graph = request.getParameter("graph");
                String status = request.getParameter("status");
                String view = request.getParameter("view");
                Debug.print((new StringBuilder()).append("Itemselected ").append(view).toString());
                HttpSession session = request.getSession();
                List list = new ArrayList();
                Double count = remote.getPurchaseRequisitionPerStatus(status);
                System.out.println((new StringBuilder()).append("Double Value").append(count).append("For staus").append(status).toString());
                if(view.equals("1"))
                {
                    if(graph.equalsIgnoreCase("PlotFormat"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPlotChart = PlotFormat.generatePurchaseRequisitionPerStatusPlotFormat(count, status);
                        PlotFormat.generatePlotFormatGraphImage(chartPlotChart, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Tube"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartTube = TubeGraph.generatePurchaseRequisitionPerStatusTube(count, status);
                        TubeGraph.generateTubeGraphImage(chartTube, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Pyramid"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart pyramidChart = PyramidGraph.generatePurchaseRequisitionPerStatusPyramid(count, status);
                        PyramidGraph.generatePyramidGraphImage(pyramidChart, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Cone"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartCone = ConeGraph.generatePurchaseRequisitionPerStatusCone(count, status);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "purchaseRequisitionParticularStatus");
                        return mapping.findForward("purchaseRequisitionMessage");
                    }
                    request.setAttribute("title", (new StringBuilder()).append("Number Of Purchase Requisitions for ").append(status).append(" status").toString());
                    return mapping.findForward("purchaseRequisitionParticularStatus");
                } else
                {
                    Status s = new Status();
                    s.setStatus(status);
                    s.setNumber(count);
                    list.add(s);
                    session.setAttribute("demo", list);
                    session.setAttribute("status", status);
                    return mapping.findForward("frmPurchaseStatusTabular");
                }
            }
            if(process.equalsIgnoreCase("stockReqAndAvail"))
            {
                String graph = request.getParameter("graph");
                String fromDate = request.getParameter("fromDate");
                String view = request.getParameter("view");
                java.util.Date startDate = null;
                if(fromDate != null && fromDate.trim().length() != 0)
                    startDate = sdf.parse(fromDate);
                HashMap list = remote.getStockReqAndAvailable(startDate);
                if(view.equals("1"))
                {
                    if(graph.equalsIgnoreCase("Pyramid"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPyramid = PyramidGraph.stockReqAndAvailPyramidGraph(list);
                        PyramidGraph.generatePyramidGraphImage(chartPyramid, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Bar"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartCone = ConeGraph.stockReqAndAvailCone(list);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Line"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartLine = LineGraph.getStockAndAvailLine(list);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Tube"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartTube = TubeGraph.stockReqAndAvailTube(list);
                        TubeGraph.generateTubeGraphImage(chartTube, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "stockReqAndAvail");
                        return mapping.findForward("stockReqAndAvailMessage");
                    }
                    request.setAttribute("title", "Stock Availability vs Requirement Report");
                    return mapping.findForward("getStockAvail");
                }
                HttpSession session = request.getSession();
                List list1 = new ArrayList();
                Set type = list.keySet();
                for(Iterator it = type.iterator(); it.hasNext();)
                {
                    String item_Name = (String)it.next();
                    StockRequirementAndAvailabilty stockReqAvail = new StockRequirementAndAvailabilty();
                    System.out.println((new StringBuilder()).append("Item Name").append(item_Name).toString());
                    stockReqAvail.setStockName(item_Name);
                    ArrayList listData = (ArrayList)list.get(item_Name);
                    int i = 2;
                    while(i <= listData.size()) 
                    {
                        Double db = (Double)listData.get(0);
                        Double db1 = (Double)listData.get(1);
                        System.out.println((new StringBuilder()).append("Item Name ").append(item_Name).append("with value").append(db.doubleValue()).append("with value").append(db1.doubleValue()).toString());
                        stockReqAvail.setStockAvailability(db);
                        stockReqAvail.setStockRequirement(db1);
                        System.out.print((new StringBuilder()).append("Value obtained: for itemName").append(item_Name).append(" ").append(db.doubleValue()).toString());
                        list1.add(stockReqAvail);
                        i++;
                    }
                }

                session.setAttribute("demo", list1);
                session.setAttribute("fromDate", fromDate);
                return mapping.findForward("stockReqAndAvailTabular");
            }
            if(process.equalsIgnoreCase("defectiveItemSuppliedByVendor"))
            {
                ArrayList list = null;
                String chartYAxisLabel = null;
                String chartTitle = null;
                String year = request.getParameter("year");
                String view = request.getParameter("view");
                String graph = request.getParameter("graph");
                chartYAxisLabel = "Defective Stocks ";
                chartTitle = (new StringBuilder()).append("Defective Stocks Supplied By Vendors in ").append(year).toString();
                list = remote.getDefectiveStocksSuppliedByEachVendorForYear(year);
                if(view.equals("1"))
                {
                    if(graph.equalsIgnoreCase("Pie"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPie = PieGraph.generateVendorAnalysisReportPie(list);
                        PieGraph.generatePieGraphImage(chartPie, realPath);
                    } else
                    if(graph.equalsIgnoreCase("PlotFormat"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPlotFormat = PlotFormat.generateVendorAnalysisReportPlotFormat(list, chartYAxisLabel);
                        PlotFormat.generatePlotFormatGraphImage(chartPlotFormat, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Cone"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartCone = ConeGraph.generateVendorAnalysisReportCone(list, chartYAxisLabel);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Line"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartLine = LineGraph.generateVendorAnalysisReportLine(list);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "defectiveItemSuppliedByVendor");
                        return mapping.findForward("stockReqAndAvailMessage");
                    }
                    request.setAttribute("title", chartTitle);
                    return mapping.findForward("purchaseRequisitionPeriodWise");
                }
                HttpSession session = request.getSession();
                List count = new ArrayList();
                for(int i = 0; i < list.size(); i++)
                {
                    HashMap typeWise = (HashMap)list.get(i);
                    Set type = typeWise.keySet();
                    Stock stock;
                    for(Iterator it = type.iterator(); it.hasNext(); count.add(stock))
                    {
                        String y = (String)it.next();
                        Double db = (Double)typeWise.get(y);
                        Debug.print((new StringBuilder()).append("year is:").append(y).append("  Count is ").append(db.doubleValue()).toString());
                        stock = new Stock();
                        stock.setVendors(y);
                        stock.setDefectiveStocks(db);
                    }

                }

                session.setAttribute("demo", count);
                session.setAttribute("year", year);
                return mapping.findForward("frmStockTabular");
            }
            if(process.equalsIgnoreCase("stocksSuppliedByVendor"))
            {
                ArrayList list = null;
                String chartYAxisLabel = null;
                String chartTitle = null;
                String year = request.getParameter("year");
                String view = request.getParameter("view");
                String graph = request.getParameter("graph");
                chartYAxisLabel = "Stocks Supplied";
                chartTitle = (new StringBuilder()).append("Stocks Supplied By Vendors in ").append(year).toString();
                list = remote.getStocksSuppliedByEachVendorForYear(year);
                if(view.equals("1"))
                {
                    for(int i = 0; i < list.size(); i++)
                    {
                        HashMap typeWise = (HashMap)list.get(i);
                        Set type = typeWise.keySet();
                        String vendor_Name;
                        Double db;
                        for(Iterator it = type.iterator(); it.hasNext(); Debug.print((new StringBuilder()).append("Status:").append(vendor_Name).append("  Stocks count is ").append(db.doubleValue()).toString()))
                        {
                            vendor_Name = (String)it.next();
                            db = (Double)typeWise.get(vendor_Name);
                        }

                    }

                    if(graph.equalsIgnoreCase("Pie"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPie = PieGraph.generateVendorAnalysisReportPie(list);
                        PieGraph.generatePieGraphImage(chartPie, realPath);
                    } else
                    if(graph.equalsIgnoreCase("PlotFormat"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartPlotFormat = PlotFormat.generateVendorAnalysisReportPlotFormat(list, chartYAxisLabel);
                        PlotFormat.generatePlotFormatGraphImage(chartPlotFormat, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Cone"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartCone = ConeGraph.generateVendorAnalysisReportCone(list, chartYAxisLabel);
                        ConeGraph.generateConeGraphImage(chartCone, realPath);
                    } else
                    if(graph.equalsIgnoreCase("Line"))
                    {
                        BIRTGraphsConfig.initializeDesignConfig(theApplicationsServletContext);
                        org.eclipse.birt.chart.model.Chart chartLine = LineGraph.generateVendorAnalysisReportLine(list);
                        LineGraph.generateLineGraphImage(chartLine, realPath);
                    } else
                    {
                        request.setAttribute("message", (new StringBuilder()).append(graph).append(" graph does not exist for this Report").toString());
                        request.setAttribute("path", "stocksSuppliedByVendor");
                        return mapping.findForward("stockReqAndAvailMessage");
                    }
                    request.setAttribute("title", chartTitle);
                    return mapping.findForward("purchaseRequisitionPeriodWise");
                }
                HttpSession session = request.getSession();
                List count = new ArrayList();
                for(int i = 0; i < list.size(); i++)
                {
                    HashMap typeWise = (HashMap)list.get(i);
                    Set type = typeWise.keySet();
                    SuppliedStock s;
                    for(Iterator it = type.iterator(); it.hasNext(); count.add(s))
                    {
                        String y = (String)it.next();
                        Double db = (Double)typeWise.get(y);
                        Debug.print((new StringBuilder()).append("year is:").append(y).append("  Count is ").append(db.doubleValue()).toString());
                        s = new SuppliedStock();
                        s.setVendors(y);
                        s.setSuppliedStock(db);
                    }

                }

                session.setAttribute("demo", count);
                session.setAttribute("year", year);
                return mapping.findForward("frmSuppliedStockTabular");
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
